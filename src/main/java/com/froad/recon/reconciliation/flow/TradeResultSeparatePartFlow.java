package com.froad.recon.reconciliation.flow;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;

import com.froad.beans.Rckflowdetail;
import com.froad.comon.constant.AppConstant;
import com.froad.comon.exception.ServiceException;
import com.froad.comon.util.Function;
import com.froad.comon.util.Logger;
import com.froad.comon.util.PageUtil;
import com.froad.flow.FlowException;
import com.froad.flow.FlowInterface;
import com.froad.recon.importfile.service.DynamicService;
import com.froad.recon.reconciliation.drools.DroolsRuleEngine;
import com.froad.recon.reconciliation.model.STradeResult;
import com.froad.recon.reconciliation.model.Sdelay;
import com.froad.recon.reconciliation.model.Sexception;
import com.froad.recon.reconciliation.model.SnoRecon;
import com.froad.recon.reconciliation.model.Ssuccess;
import com.froad.recon.reconciliation.service.STradeResultService;
import com.froad.recon.reconciliation.service.SdelayService;
import com.froad.recon.reconciliation.service.SexceptionService;
import com.froad.recon.reconciliation.service.SnoReconService;
import com.froad.recon.reconciliation.service.SsuccessService;
import com.froad.recon.sys.service.PlatformService;

/**
 *1遍历对账结果表
 *2根据订单的交易渠道、交易类别获取规则引擎，执行规则引擎
 *
 *
 *1批量删除对账日的对账成功表
2批量插入对账成功表
3批量删除对应的对账结果表记录


1批量删除对账日的差错表
2批量插入差错表
3批量删除对应的对账结果表记录



1批量删除对账日的延迟对账表
2批量插入延迟对账表
3批量删除对应的对账结果表记录


1批量删除对账日的未对账表
2批量插入未对账表
3批量删除对应的对账结果表记录

 * */
public class TradeResultSeparatePartFlow implements FlowInterface {
	private final static Logger logger = Logger
			.getLogger(TradeResultSeparatePartFlow.class);
	private PlatformService platformService;
	private STradeResultService sTradeResultService;
	private DynamicService dynamicService;
	 
    private SnoReconService snoReconService;
    private SdelayService sdelayService;
    private SsuccessService ssuccessService;
    private SexceptionService sexceptionService;
    private DroolsRuleEngine droolsRuleEngine;
    
	public PlatformService getPlatformService() {
		return platformService;
	}

	public void setPlatformService(PlatformService platformService) {
		this.platformService = platformService;
	}

	public STradeResultService getsTradeResultService() {
		return sTradeResultService;
	}

	public void setsTradeResultService(STradeResultService sTradeResultService) {
		this.sTradeResultService = sTradeResultService;
	}
	
	public DynamicService getDynamicService() {
		return dynamicService;
	}

	public void setDynamicService(DynamicService dynamicService) {
		this.dynamicService = dynamicService;
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

	public SsuccessService getSsuccessService() {
		return ssuccessService;
	}

	public void setSsuccessService(SsuccessService ssuccessService) {
		this.ssuccessService = ssuccessService;
	}

	public SexceptionService getSexceptionService() {
		return sexceptionService;
	}

	public void setSexceptionService(SexceptionService sexceptionService) {
		this.sexceptionService = sexceptionService;
	}

	public DroolsRuleEngine getDroolsRuleEngine() {
		return droolsRuleEngine;
	}

	public void setDroolsRuleEngine(DroolsRuleEngine droolsRuleEngine) {
		this.droolsRuleEngine = droolsRuleEngine;
	}

	public Rckflowdetail execute(Rckflowdetail rckflowdetail)
			throws FlowException {
		try {
			StringBuffer msgBf=new StringBuffer();
			String msg="";
			String cleardate = rckflowdetail.getId().getCleardate();
			
			Map<String, Object> paramsMap=new HashMap<String, Object>();
			paramsMap.put("reconDate", cleardate);
			
			/***获取未对账文件 平台明细信息*/
			List<Map<String,Object>>  platfroms=dynamicService.selectPlatformByCleardate(cleardate, new HashMap<String, Object>());
			List<String> platfromNofiles=new ArrayList<String>();
			for (Map<String,Object> item : platfroms) {
				 String value=MapUtils.getString(item, "platformNo","")+"_"+MapUtils.getString(item, "channelNo","");
				platfromNofiles.add(value);
			}
			
			Long startTime=System.currentTimeMillis();
			
			Integer count=sTradeResultService.selectForPaginCount(paramsMap);
			
			msg=String.format("分离结果集、记录总条数:%s、用时: %s",count,Function.getProcessTime(startTime)); 
			logger.info(msg);
			msgBf.append(msg);
			
			Integer pageSize=AppConstant.RENCON_SIZE;
			Integer totalPage=PageUtil.getTotalPage(count,pageSize);
			
			Integer pageNo = 1;
			while (pageNo <= totalPage) {
				startTime=System.currentTimeMillis();
				List<STradeResult> queryList=sTradeResultService.selectForPagin(paramsMap, pageNo, pageSize);
				/**规则引擎处理入库*/
				ruleEngineHandler(queryList,platfromNofiles);
				msg=String.format("规则引擎处理数据条数: %s、用时: %s",queryList.size(),Function.getProcessTime(startTime)); 
				logger.info(msg);
				msgBf.append(msg);
				
				
				/**根据 tableName分别入不同的表**/
				startTime=System.currentTimeMillis();
				dataHandlerAndSave(queryList, cleardate);
				
				msg=String.format("分离结果集、入数据库条数：%s、用时: %s",queryList.size(),Function.getProcessTime(startTime)); 
				logger.info(msg);
				msgBf.append(msg);
				pageNo++;
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

	/***规则引擎处理入库
	 * @param platfromNofiles ***/
	public void ruleEngineHandler(List<STradeResult> queryList, List<String> platfromNofiles){
		for (STradeResult item : queryList) {
			item.setPlatformDetails(platfromNofiles);
		}
		/**调用引擎执行*/
		droolsRuleEngine.executeRuleEngine(queryList);
	}
	
	/***规则引擎处理入库
	 * @throws ServiceException ***/
	public void dataHandlerAndSave(List<STradeResult> queryList,String reconDate) throws ServiceException{
		List<Ssuccess>  insertSsuccesss=new ArrayList<Ssuccess>();
		List<Sexception>  insertSexceptions=new ArrayList<Sexception>();
		List<SnoRecon>  insertSnoRecons=new ArrayList<SnoRecon>();
		List<Sdelay>  insertSdelays=new ArrayList<Sdelay>();
		
		for (STradeResult item : queryList) {
			String tableName=item.getTableName();
			String orderNo=item.getOrderNo();
			Date orderTime=item.getOrderTime();
			String chanelType=item.getChanelType();
			String tradeType=item.getTradeType();
			BigDecimal tradeMoney=item.getTradeMoney();
			
			String errorCode=item.getErrorCode();
			String errorDesc=item.getErrorDesc();
			List<String> platformNos=item.getPlatformNos();
			
			if(STradeResult.TABLE_NAME_S_SUCCESS.equals(tableName)){/**成功表*/
				Ssuccess insertSsuccess=new Ssuccess();
				
				insertSsuccess.setOrderNo(orderNo);
				insertSsuccess.setOrderTime(orderTime);
				insertSsuccess.setChanelType(chanelType);
				insertSsuccess.setTradeType(tradeType);
				insertSsuccess.setTradeMoney(tradeMoney);
				
				insertSsuccess.setCreateTime(new Date());
			    insertSsuccess.setReconDate(reconDate);
			    
				insertSsuccesss.add(insertSsuccess);
			}else if(STradeResult.TABLE_NAME_S_EXCEPTION.equals(tableName)){/**差异*/
				Sexception insertSexception=new Sexception();
				insertSexception.setOrderNo(orderNo);
				insertSexception.setOrderTime(orderTime);
				insertSexception.setChanelType(chanelType);
				insertSexception.setTradeType(tradeType);
				insertSexception.setTradeMoney(tradeMoney);
				
				insertSexception.setCreateTime(new Date());
				insertSexception.setReconDate(reconDate);
				
				insertSexception.setErrorCode(errorCode);
				insertSexception.setErrorDesc(errorDesc);
				
				insertSexception.setProcessStatus(Sexception.PROCESS_STATUS_NO);/*未处理*/
				insertSexceptions.add(insertSexception);
				
			}else if(STradeResult.TABLE_NAME_S_NO_RECON.equals(tableName)){/**未对账*/
				
				for (int i = 0; i < platformNos.size(); i++) {
					SnoRecon insertSnoRecon=new SnoRecon();
					insertSnoRecon.setOrderNo(orderNo);
					insertSnoRecon.setOrderTime(orderTime);
					insertSnoRecon.setChanelType(chanelType);
					insertSnoRecon.setTradeType(tradeType);
					
					insertSnoRecon.setCreateTime(new Date());
					insertSnoRecon.setReconDate(reconDate);
					insertSnoRecon.setPlatformNo(platformNos.get(i));
					
					insertSnoRecons.add(insertSnoRecon);
				}
				
			}else if(STradeResult.TABLE_NAME_S_DELAY.equals(tableName)){/**延迟*/
				for (int i = 0; i < platformNos.size(); i++) {
					Sdelay insertSdelay=new Sdelay();
					
					insertSdelay.setOrderNo(orderNo);
					insertSdelay.setOrderTime(orderTime);
					insertSdelay.setPlatformNo(platformNos.get(i));
					
					insertSdelay.setChanelType(chanelType);
					insertSdelay.setTradeType(tradeType);
					
					insertSdelay.setCreateTime(new Date());
					insertSdelay.setReconDate(reconDate);
					
					insertSdelays.add(insertSdelay);
				}
			}
		}
		/***数据入库*/
		sTradeResultService.separateDataHandlerPart(insertSsuccesss, insertSexceptions, insertSnoRecons, insertSdelays, queryList);
	}
}
