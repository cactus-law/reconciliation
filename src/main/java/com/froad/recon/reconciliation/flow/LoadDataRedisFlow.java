package com.froad.recon.reconciliation.flow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import com.froad.beans.Rckflowdetail;
import com.froad.cache.redis.RedisService;
import com.froad.comon.constant.AppConstant;
import com.froad.comon.constant.BusinessConstant;
import com.froad.comon.constant.ReconBusinessConstant;
import com.froad.comon.constant.TableConstant;
import com.froad.comon.exception.ServiceException;
import com.froad.comon.util.DateUtil;
import com.froad.comon.util.Function;
import com.froad.comon.util.Logger;
import com.froad.comon.util.PageUtil;
import com.froad.flow.FlowException;
import com.froad.flow.FlowInterface;
import com.froad.recon.importfile.service.DynamicService;
import com.froad.recon.reconciliation.model.Sdelay;
import com.froad.recon.reconciliation.model.SnoRecon;
import com.froad.recon.reconciliation.service.SdelayService;
import com.froad.recon.reconciliation.service.SnoReconService;
import com.froad.recon.sys.model.PlatformDetail;
import com.froad.recon.sys.service.PlatformService;

/**
 * 2 根据平台号来生产一个集合（set）放到redis中（放之前要先清空redis中该结合）
3 平台信息配置表和对账结果表关联查询导入状态为成功和当前平台号的所有导入信息，遍历该导入信息
4 通过导入信息，获取表名, 关键字段信息， 动态组装sql,以分页的形式查询出来，批量导入对应的平台集合中（及对应的redis集合中）
未对账入redis
。*/
public class LoadDataRedisFlow implements FlowInterface{
	private final static Logger logger = Logger.getLogger(LoadDataRedisFlow.class);
	private PlatformService platformService;
	private DynamicService dynamicService;
	private RedisService redisManager;
	private SnoReconService snoReconService;
	private SdelayService sdelayService;
	
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

	public SnoReconService getSnoReconService() {
		return snoReconService;
	}

	public void setSnoReconService(SnoReconService snoReconService) {
		this.snoReconService = snoReconService;
	}

	public SdelayService getSdelayService() {
		return sdelayService;
	}

	public void setSdelayService(SdelayService sdelayService) {
		this.sdelayService = sdelayService;
	}

	/**
	 * 读取数据 放到redis中
	 * */
    public Rckflowdetail execute(Rckflowdetail rckflowdetail) throws FlowException{
    	try {
			String cleardate=rckflowdetail.getId().getCleardate();
			String reconType=rckflowdetail.getReconType();
			
			StringBuffer msgBf=new StringBuffer();
			String msg="";
			String id=rckflowdetail.getMsg();
			if(StringUtils.isBlank(id)){
				msg="msg信息为空";
				rckflowdetail.setExceptiondesc(msg);
				throw  new FlowException(msg);
			}
		    if(!(BusinessConstant.IMP_TYPE.ALL.equals(reconType)||BusinessConstant.IMP_TYPE.PART.equals(reconType))){
		    	msg="对账类型只能为全部或者部分";
		    	rckflowdetail.setExceptiondesc(msg);
		    	throw  new FlowException(msg);
		    }
		    //根据对账类型、对账日期、平台号，查询平台下成功导入数据的多对账方 （查出需要入redies的多数据表）
			List<PlatformDetail> platformDetails=platformService.getDetailByImport(id, cleardate, reconType);
			
			String key=AppConstant.RECON_KEY_PREFIX+id;
			
			Long startTime=System.currentTimeMillis();
			//1设置redis 过期
			redisManager.expire(AppConstant.RECON_KEY_PREFIX+id, 0);
			
			msg=String.format("\n1设置redis 平台编号：%s 过期用时:%s",id,startTime);
			logger.info(msg);
			msgBf.append(msg);
		    
			
			Integer  totalCount=0;
			Integer  index=0;
			/**导入表入redis*/
			for (PlatformDetail item : platformDetails) {
				startTime=System.currentTimeMillis();
				
				String tableName=item.getTableName();
				
				String platformNo=item.getPlatformNo();
				String channelNo=item.getChannelNo();

				Map<String, Object> paramsMap=new HashMap<String, Object>(); 
				if(ReconBusinessConstant.PlatformNo.FRONT.equals(platformNo)){
					//获取前端表名，只获取一次拉取所有数据
					index++;
					paramsMap.put("transfer_type_notIn", BusinessConstant.REFOUD_LIST);
					tableName="i_front_trade";
					if(index>1){
						break;
					}
					//paramsMap.put(TableConstant.FRONT_PARTNER_NO, channelNo);
				}else{
					if(PlatformDetail.IS_COMMON_TABLE_YES.equals(item.getIsCommonTable())){
						//获取非前端表数据，根据渠道号获取数据。
						paramsMap.put(TableConstant.CHANNEL_NO, channelNo);
					}
				}
				Integer count=dynamicService.selectForMapCount(tableName, cleardate, paramsMap);
				
				Integer pageSize=AppConstant.PAGE_SIZE;
				Integer totalPage=PageUtil.getTotalPage(count,pageSize);
				
				Integer pageNo = 1;
				while (pageNo <= totalPage) {
					List<Map<String,Object>> queryList=dynamicService.selectForMap(tableName, cleardate, paramsMap, pageNo, pageSize);
					/**往redis放数据*/
					loadImportRedis(key, queryList);
					pageNo++;
				}
				
				msg=String.format("\n导入表入redis:%s、记录条数:%s、用时: %s", item.getPlatformDetailName()+tableName,count,Function.getProcessTime(startTime)); 
				logger.info(msg);
				msgBf.append(msg);
				
				totalCount+=count;
			}
			msg=String.format("\n导入表入redis 总笔数:%s、",totalCount); 
			logger.info(msg);
			msgBf.append(msg);
			
			if(BusinessConstant.IMP_TYPE.PART.equals(reconType)){/**手动导入未对账*/
				startTime=System.currentTimeMillis();
				/**未对账表入redis*/
				Map<String, Object> paramsMap=new HashMap<String, Object>();
				paramsMap.put("platformNo", id);
				paramsMap.put("reconDate", cleardate);
				Integer count=snoReconService.selectForPaginCount(paramsMap);
				Integer pageSize=AppConstant.PAGE_SIZE;
				Integer totalPage=PageUtil.getTotalPage(count,pageSize);
				Integer pageNo = 1;
				while (pageNo <= totalPage) {
					List<SnoRecon> queryList=snoReconService.selectForPagin(paramsMap, pageNo, pageSize);
					/**往redis放数据*/
					loadSnoReconRedis(key, queryList);
					pageNo++;
				}
				msg=String.format("\n未对账表入redis、记录条数:%s、用时: %s",count,Function.getProcessTime(startTime)); 
				logger.info(msg);
				msgBf.append(msg);
			}
			
			/**1延迟对账的数据***/
			startTime=System.currentTimeMillis();
			Map<String, Object> paramsMap=new HashMap<String, Object>();
			paramsMap.put("platformNo", id);
			paramsMap.put("reconDate", DateUtil.getStringFromDate(DateUtil.addDaysToDate(cleardate, -1)));
			paramsMap.put("businessType", Sdelay.BUSINESSTYPE_TRADE);
			
			Integer count=sdelayService.selectForPaginCount(paramsMap);
			Integer pageSize=AppConstant.PAGE_SIZE;
			Integer totalPage=PageUtil.getTotalPage(count,pageSize);
			Integer pageNo = 1;
			while (pageNo <= totalPage) {
				List<Sdelay> queryList=sdelayService.selectForPagin(paramsMap, pageNo, pageSize);
				/**往redis放数据*/
				loadSDelayRedis(key, queryList);
				pageNo++;
			}
			msg=String.format("\n延迟对账的数据、记录条数:%s、用时: %s",count,Function.getProcessTime(startTime)); 
			logger.info(msg);
			msgBf.append(msg);
			
			
			if(ReconBusinessConstant.PlatformNo.FRONT.equals(id)){
				/**2退款延迟对账的数据***/
				startTime=System.currentTimeMillis();
				paramsMap=new HashMap<String, Object>();
				paramsMap.put("platformNo", id);
				paramsMap.put("reconDate_min", DateUtil.getStringFromDate(DateUtil.addDaysToDate(cleardate, -(AppConstant.DELAY_DAYS+1))));
				paramsMap.put("reconDate_max", cleardate);
				paramsMap.put("businessType", Sdelay.BUSINESSTYPE_REFUND);
				if(BusinessConstant.IMP_TYPE.PART.equals(reconType)){//部分对账
					paramsMap.put("reconStatus_in", new String []{Sdelay.RECON_STATUS_NOBIIL,Sdelay.RECON_STATUS_NORECON});
				}
				count=sdelayService.selectForPaginCount(paramsMap);
				pageSize=AppConstant.PAGE_SIZE;
				totalPage=PageUtil.getTotalPage(count,pageSize);
				pageNo = 1;
				Integer countdelay=0;
				while (pageNo <= totalPage) {
					List<Sdelay> queryList=sdelayService.refundSdelayUpdate(paramsMap, pageNo, pageSize);
					/**往redis放数据*/
					loadSDelayRedis(key, queryList);
					pageNo++;
					
					countdelay+=queryList.size();
				}
				msg=String.format("\n 2退款延迟对账的数据、查询总记录条数:%s、退款延迟入redis总条数:%s、用时: %s",count,countdelay,Function.getProcessTime(startTime)); 
				logger.info(msg);
				msgBf.append(msg);
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
    
    /**往redis放数据*/
    public void loadImportRedis(String key, List<Map<String,Object>> queryList){
    	Set<String> valueSet=new HashSet<String>();
    	for (Map<String, Object> map : queryList) {
    		String orderNo=MapUtils.getString(map, "order_no");
    		if(StringUtils.isNotEmpty(orderNo)){
    			valueSet.add(orderNo);
    		}else{
    			logger.error(map.toString());
    		}
		}
    	redisManager.putSet(key, valueSet);
    }
    /**未对账 往redis放数据*/
    public void loadSnoReconRedis(String key, List<SnoRecon> queryList){
    	Set<String> valueSet=new HashSet<String>();
    	for (SnoRecon item : queryList) {
    		String orderNo=item.getOrderNo();
    		if(StringUtils.isNotEmpty(orderNo)){
    			valueSet.add(orderNo);
    		}else{
    			logger.error(item.toString());
    		}
    		
    	}
    	redisManager.putSet(key, valueSet);
    }
    
    /**延迟 往redis放数据*/
    public void loadSDelayRedis(String key, List<Sdelay> queryList){
    	Set<String> valueSet=new HashSet<String>();
    	for (Sdelay item : queryList) {
    		String orderNo=item.getOrderNo();
    		if(StringUtils.isNotEmpty(orderNo)){
    			valueSet.add(orderNo);
    		}else{
    			logger.error(item.toString());
    		}
    	}
    	redisManager.putSet(key, valueSet);
    }
    
    public static void main(String[] args) {
		String aa="1b";
		System.out.println(Integer.parseInt(aa));
		
		
	}
}
