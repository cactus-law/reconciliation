package com.froad.recon.reconciliation.flow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.froad.beans.Rckflowdetail;
import com.froad.cache.redis.RedisService;
import com.froad.comon.constant.AppConstant;
import com.froad.comon.exception.ServiceException;
import com.froad.comon.util.Function;
import com.froad.comon.util.GroupUtil;
import com.froad.comon.util.Logger;
import com.froad.comon.util.PageUtil;
import com.froad.flow.FlowException;
import com.froad.flow.FlowInterface;
import com.froad.recon.importfile.model.IfrontTrade;
import com.froad.recon.importfile.service.DynamicService;
import com.froad.recon.importfile.service.IfrontTradeService;
import com.froad.recon.reconciliation.model.STradeResult;
import com.froad.recon.reconciliation.service.STradeResultService;
import com.froad.recon.sys.model.Platform;
import com.froad.recon.sys.service.PlatformService;

/**
 * 1 获取平台表中所有需要对账的平台，根据平台编号升序排序，把排序结果放到数组中
 * 2对数组求组合，由最多个元素组合到最少个元素组合的结果情况，依次放到集合list中
 * 3 遍历组合list 
 * 4调用redis的方法sinter(组合记录[组合情况是数据])求交集
 * 5 交集结果是一个含有对账标示的集合，
 * 把该集合中的数据依次组装成结果信息java对象（订单号，平台编号标示默认是零，组合记录的值和平台编号标示字段对应，如果对应该字段为1）
 * 6 在redis中删除掉求完交集的集合元素
 * 7通过订单号（多个订单号in查询，减少查询次数），查询前端表获取（渠道类别、渠道编号、交易类型、订单时间、交易额、
 * 前端应答码、前端平台号、供应商编号）再次组装结果信息java对象
 *8 先删除指定对账日的对账结果信息记录（批量删除）
 * 9 导入指定对账日的对账结果信息记录（批量插入）
 * */
public class TradeResultFlow implements FlowInterface {
	private final static Logger logger = Logger
			.getLogger(TradeResultFlow.class);
	private PlatformService platformService;
	private DynamicService dynamicService;
	private RedisService redisManager;
	private IfrontTradeService ifrontTradeService;
	private STradeResultService sTradeResultService;

	public PlatformService getPlatformService() {
		return platformService;
	}

	public void setPlatformService(PlatformService platformService) {
		this.platformService = platformService;
	}

	public DynamicService getDynamicService() {
		return dynamicService;
	}

	public void setDynamicService(DynamicService dynamicService) {
		this.dynamicService = dynamicService;
	}

	public RedisService getRedisManager() {
		return redisManager;
	}

	public void setRedisManager(RedisService redisManager) {
		this.redisManager = redisManager;
	}
	
	public IfrontTradeService getIfrontTradeService() {
		return ifrontTradeService;
	}

	public void setIfrontTradeService(IfrontTradeService ifrontTradeService) {
		this.ifrontTradeService = ifrontTradeService;
	}

	public STradeResultService getsTradeResultService() {
		return sTradeResultService;
	}

	public void setsTradeResultService(STradeResultService sTradeResultService) {
		this.sTradeResultService = sTradeResultService;
	}

	/**
	 * 读取数据 放到redis中
	 * */
	public Rckflowdetail execute(Rckflowdetail rckflowdetail)
			throws FlowException {
		try {
			String cleardate = rckflowdetail.getId().getCleardate();

			StringBuffer msgBf=new StringBuffer();
			String msg="";

			Platform platform = new Platform();
			platform.setStatus(Platform.STATUS_YES);
			/** 查询需要对账的平台 */
			List<Platform> platforms = platformService.getList(platform);
			
			Long startTime=System.currentTimeMillis();
			/**批量删除结果集数据*/
			sTradeResultService.batchDeleteByReconDate(cleardate, new HashMap<String, Object>());

			msg=String.format("\n 批量删除结果集数据、用时: %s",Function.getProcessTime(startTime)); 
			logger.info(msg);
			msgBf.append(msg);
			
			if (platforms.size() > 0) {
				String[] platformArray = new String[platforms.size()];
				for (int i = 0; i < platforms.size(); i++) {
					platformArray[i] = platforms.get(i).getPlatformNo();
				}

				/**获取组合的结果数*/
				List<String[]> groupResult = GroupUtil.sortArray(platformArray);
				for (int i = 0; i < groupResult.size(); i++) {
					startTime=System.currentTimeMillis();
					String[] groupArray = groupResult.get(i);
					
					/***redis求交集*/
					String[] orderNos=sinterOrRemove(groupArray);
					msg=String.format("\n redis求交集获取组合是:%s、交集条数：%s 用时: %s",Arrays.toString(groupArray),orderNos.length,Function.getProcessTime(startTime)); 
					logger.info(msg);
					msgBf.append(msg);
					
					startTime=System.currentTimeMillis();
					/*数据处理*/
					handlerData(orderNos, groupArray,cleardate);
					msg=String.format("\n 数据处理和入结果集表条数：%s 用时: %s",orderNos.length,Function.getProcessTime(startTime)); 
					logger.info(msg);
					msgBf.append(msg);
				}
			} else {
				msg = "没有配置平台信息";
				rckflowdetail.setExceptiondesc(msg);
				throw new FlowException(msg);
			}
			rckflowdetail.setExceptiondesc(msgBf.toString());
		} catch (ServiceException e) {
			logger.error(e.getErrorMsg(),e);
			throw new FlowException(rckflowdetail,e.getErrorMsg(),e);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			throw new FlowException(rckflowdetail,e.getMessage(),e);
		}
		return rckflowdetail;
	}

	/** 指定redis中的key求 交集， 并且把把各个集合的交集内容删除掉 */
	public String[] sinterOrRemove(String[] groupArray) {
		String[] redisKeys = new String[groupArray.length];
		for (int i = 0; i < groupArray.length; i++) {
			redisKeys[i] = AppConstant.RECON_KEY_PREFIX + groupArray[i];
		}
		/**redis中求交集*/
		Set<String> resultSet = redisManager.sinter(redisKeys);
		String[] removeValues = new String[resultSet.size()];
		resultSet.toArray(removeValues);
		for (int i = 0; i < redisKeys.length; i++) {
			if(removeValues!=null&&removeValues.length>0){
				redisManager.srem(redisKeys[i], removeValues);
			}
		}
		return removeValues;
	}


	/**数据处理
	 * @param cleardate */
	public void handlerData(String[] orderNos,String[] groupArray, String cleardate) throws ServiceException{
		STradeResult tempResult=new STradeResult();
		tempResult.setIsBankPoints("0");
		tempResult.setIsFront("0");
		tempResult.setIsPartner("0");
		tempResult.setIsPay("0");
		tempResult.setIsPoints("0");
		for (int i = 0; i < groupArray.length; i++) {
			if(STradeResult.MARK_BANK_POINTS.equals(groupArray[i])){
				tempResult.setIsBankPoints("1");
			}
			if(STradeResult.MARK_FRONT.equals(groupArray[i])){
				tempResult.setIsFront("1");
			}
			if(STradeResult.MARK_PARTNER.equals(groupArray[i])){
				tempResult.setIsPartner("1");
			}
			if(STradeResult.MARK_PAY.equals(groupArray[i])){
				tempResult.setIsPay("1");
			}
			if(STradeResult.MARK_POINTS.equals(groupArray[i])){
				tempResult.setIsPoints("1");
			}
		}
		
		Integer count=orderNos.length;
		Integer pageSize=AppConstant.PAGE_SIZE_IN;
		Integer totalPage=PageUtil.getTotalPage(count,pageSize);
		Integer pageNo = 1;
		while (pageNo <= totalPage) {
		    Integer start=(pageNo-1)*pageSize;
		    Integer end=pageNo*pageSize>count?count:pageNo*pageSize;
		    List<String> orderNoList=new ArrayList<String>();
		   
		    for (int i = start; i < end; i++) {
		    	orderNoList.add(orderNos[i]);
			}
		    /***分页处理数据*/
		    handlerDataPage(orderNoList, tempResult,cleardate);
			pageNo++;
		}
	}
	
	/***保存结果集数据*/
	public void handlerDataPage(List<String> orderNos,STradeResult tempResult, String cleardate) throws ServiceException{
		if(orderNos.size()<1){
			return;
		}
		/**批量查询前台数据 放到map中*/
		List<IfrontTrade> ifrontTrades=ifrontTradeService.getByOrderNos(orderNos);
		Map<String, IfrontTrade> tradeMap=new HashMap<String, IfrontTrade>();
		for (IfrontTrade item : ifrontTrades) {
			tradeMap.put(item.getOrderNo(),item);
		}
		
		List<STradeResult> inserList=new ArrayList<STradeResult>();
		for (String orderNo : orderNos) {
			STradeResult insert=new STradeResult();
			insert.setOrderNo(orderNo);  //对账订单号
			insert.setReconDate(cleardate);
			/***来源方标志设置*/
			insert.setIsBankPoints(tempResult.getIsBankPoints());
			insert.setIsFront(tempResult.getIsFront());
			insert.setIsPartner(tempResult.getIsPartner());
			insert.setIsPay(tempResult.getIsPay());
			insert.setIsPoints(tempResult.getIsPoints());
			
			/***前端信息设置*/
			IfrontTrade ifront=tradeMap.get(orderNo);
			if(ifront!=null){
				insert.setChanelType(ifront.getChanelType());/**渠道类别	0 积分 1 现金**/
				insert.setTradeType(ifront.getTransferType());/**交易类型**/
				insert.setOrderTime(ifront.getOrderTime());/**订单时间**/
				insert.setTradeMoney(ifront.getTradeMoney());/**交易额**/
				insert.setFrontCode(ifront.getResultCode());/**前端应答码**/
				
				insert.setFrontPartnerNo(ifront.getFrontPartnerNo());/**前端平台号**/
				insert.setSupplierNo(ifront.getSupplierNo());/**供应商编号**/
				insert.setPointBankGroup(ifront.getPointBankGroup());/**积分银行组号**/
				insert.setBackGroup(ifront.getBankGroup());/**现金银行组号**/
				insert.setPointOrg(ifront.getPointOrg());/**积分机构号**/
				insert.setVirtualTradeType(ifront.getVirtualTradeType()); /**虚拟类型*/
				
				insert.setImportDate(ifront.getReconDate());/**导入日期 */
			}
			insert.setCreateTime(new Date());
			inserList.add(insert);
		}
		/**批量插入*/
		sTradeResultService.batchInser(inserList);
	}
}
