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
import com.froad.comon.constant.BusinessConstant;
import com.froad.comon.constant.ReconBusinessConstant;
import com.froad.comon.exception.ServiceException;
import com.froad.comon.util.DateUtil;
import com.froad.comon.util.Function;
import com.froad.comon.util.Logger;
import com.froad.comon.util.PageUtil;
import com.froad.flow.FlowException;
import com.froad.flow.FlowInterface;
import com.froad.recon.importfile.service.DynamicService;
import com.froad.recon.reconciliation.common.PageMessageUtil;
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
import com.froad.recon.sys.dao.TdiractionaryDAO;
import com.froad.recon.sys.model.PlatformDetail;
import com.froad.recon.sys.model.Tdiractionary;
import com.froad.recon.sys.service.PlatformDetailService;
import com.froad.recon.sys.service.PlatformService;
import com.froad.recon.sys.service.TdiractionaryService;

/**
 *1遍历对账结果表
 *2根据订单的交易渠道、交易类别获取规则引擎，执行规则引擎
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
public class TradeResultSeparateFlow implements FlowInterface {
	private final static Logger logger = Logger
			.getLogger(TradeResultSeparateFlow.class);
	private PlatformService platformService;
	private PlatformDetailService platformDetailService;
	private STradeResultService sTradeResultService;
	private DynamicService dynamicService;
	 
    private SnoReconService snoReconService;
    private SdelayService sdelayService;
    private SsuccessService ssuccessService;
    private SexceptionService sexceptionService;
    private DroolsRuleEngine droolsRuleEngine;
    private PageMessageUtil pageMessageUtil;
    private TdiractionaryService tdiractionaryService;
    
    
	public PlatformService getPlatformService() {
		return platformService;
	}

	public void setPlatformService(PlatformService platformService) {
		this.platformService = platformService;
	}
	

	public PlatformDetailService getPlatformDetailService() {
		return platformDetailService;
	}

	public void setPlatformDetailService(PlatformDetailService platformDetailService) {
		this.platformDetailService = platformDetailService;
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

	
	public PageMessageUtil getPageMessageUtil() {
		return pageMessageUtil;
	}

	public void setPageMessageUtil(PageMessageUtil pageMessageUtil) {
		this.pageMessageUtil = pageMessageUtil;
	}

	public TdiractionaryService getTdiractionaryService() {
		return tdiractionaryService;
	}

	public void setTdiractionaryService(TdiractionaryService tdiractionaryService) {
		this.tdiractionaryService = tdiractionaryService;
	}

	public Rckflowdetail execute(Rckflowdetail rckflowdetail)
			throws FlowException {
		try {
			StringBuffer msgBf=new StringBuffer();
			String msg="";
			
			String cleardate = rckflowdetail.getId().getCleardate();
			String reconType = rckflowdetail.getReconType();
			
			if(!(BusinessConstant.IMP_TYPE.ALL.equals(reconType)||BusinessConstant.IMP_TYPE.PART.equals(reconType))){
				msg="对账类型只能为全部或者部分";
				rckflowdetail.setExceptiondesc(msg);
				throw  new FlowException(msg);
			}
			/**获取交易类型数据*/
			Tdiractionary tdiractionary=new Tdiractionary();
			tdiractionary.setStatus(Tdiractionary.STATUS_YES);
			tdiractionary.setTypeNo(Tdiractionary.TYPE_NO_TRADE_TYPE);
			List<Tdiractionary> tdiractionaries=tdiractionaryService.getList(tdiractionary);
			Map<String,String> tdiractionarieMap=new HashMap<String, String>();
			for (Tdiractionary item : tdiractionaries) {
				tdiractionarieMap.put(item.getTypeNo()+"_"+item.getDiractionaryNo(), item.getDiractionaryName());
			}
			
			/**获取平台明细信息*/
			PlatformDetail platformDetail=new PlatformDetail();
//			platformDetail.setStatus(PlatformDetail.FILE_STATUS_YES);
			List<PlatformDetail> platformDetails=platformDetailService.getList(platformDetail);
			Map<String,String> platformDetailMap=new HashMap<String, String>();
			for (PlatformDetail item : platformDetails) {
				platformDetailMap.put(item.getPlatformNo()+"_"+item.getChannelNo(), item.getPlatformDetailName());
			}

			Long startTime=System.currentTimeMillis();
			if(BusinessConstant.IMP_TYPE.ALL.equals(reconType)){/**全部对账*/
				/***删除分离的结果表*/
				ssuccessService.batchDeleteByReconDate(cleardate, new HashMap<String, Object>());
				msg=String.format("\n删除成功表用时: %s",Function.getProcessTime(startTime)); 
				logger.info(msg);
				msgBf.append(msg);
				
				 
				startTime=System.currentTimeMillis();
				sexceptionService.batchDeleteByReconDate(cleardate, new HashMap<String, Object>());
				msg=String.format("\n删除异常表用时: %s",Function.getProcessTime(startTime)); 
				logger.info(msg);
				msgBf.append(msg);
				
				startTime=System.currentTimeMillis();
				snoReconService.batchDeleteByReconDate(cleardate, new HashMap<String, Object>());
				msg=String.format("\n删除未对账表用时: %s",Function.getProcessTime(startTime)); 
				logger.info(msg);
				msgBf.append(msg);
				
				startTime=System.currentTimeMillis();
				Map<String, Object> paramsMap=new HashMap<String, Object>();
				paramsMap.put("businessType", Sdelay.BUSINESSTYPE_TRADE);
				sdelayService.batchDeleteByReconDate(cleardate, paramsMap);
				msg=String.format("\n删除延迟表用时: %s",Function.getProcessTime(startTime)); 
				logger.info(msg);
				msgBf.append(msg);
				
			}
			
			/**删除结果集表对账日前一天的数据***/
			String beforeClearDate=DateUtil.getStringFromDate(DateUtil.addDaysToDate(cleardate, -1));
			startTime=System.currentTimeMillis();
			sTradeResultService.batchDeleteByReconDate(beforeClearDate, new HashMap<String, Object>());
			msg=String.format("\n删除结果集表: %s",Function.getProcessTime(startTime)); 
			logger.info(msg);
			msgBf.append(msg);
			
			
			/***查询指定日期上传文件的 记录 */
			List<Map<String,Object>>  platfroms=dynamicService.selectPlatformByCleardate(cleardate, new HashMap<String, Object>());
			List<String> platfromNofiles=new ArrayList<String>();
			List<String> platfromYesfiles=new ArrayList<String>();
			for (Map<String,Object> item : platfroms) {
			    String status=MapUtils.getString(item, "status");
				
			    String value=MapUtils.getString(item, "platform_no","")+"_"+MapUtils.getString(item, "channel_no","");
			    if(BusinessConstant.IMP_STATUS.INITIAL.equals(status)||
			    		BusinessConstant.IMP_STATUS.FAIL.equals(status)){
			    	platfromNofiles.add(value);
			    }else if(BusinessConstant.IMP_STATUS.SUCCESS.equals(status)||
			    		BusinessConstant.IMP_STATUS.RECON_SUCCESS.equals(status)){
			    	platfromYesfiles.add(value);
			    }
			}
			
			startTime=System.currentTimeMillis();
			Map<String, Object> paramsMap=new HashMap<String, Object>();
			paramsMap.put("reconDate", cleardate);
			Integer count=sTradeResultService.selectForPaginCount(paramsMap);
			
			msg=String.format("\n分离结果集、记录总条数:%s、用时: %s",count,Function.getProcessTime(startTime)); 
			logger.info(msg);
			msgBf.append(msg);
			
			Integer pageSize=AppConstant.RENCON_SIZE;
			Integer totalPage=PageUtil.getTotalPage(count,pageSize);
			
			Integer pageNo = 1;
			Integer[] counts=new Integer[5];
			for (int i = 0; i < counts.length; i++) {
				counts[i]=0;
			}
			while (pageNo <= totalPage) {
				startTime=System.currentTimeMillis();
				List<STradeResult> queryList=sTradeResultService.selectForPagin(paramsMap, pageNo, pageSize);
				/**规则引擎处理入库*/
				ruleEngineHandler(queryList,platfromNofiles,platfromYesfiles);
				msg=String.format("\n规则引擎处理数据条数: %s、用时: %s",queryList.size(),Function.getProcessTime(startTime)); 
				logger.info(msg);
				msgBf.append(msg);
				
				
				/**根据 tableName分别入不同的表**/
				startTime=System.currentTimeMillis();
			   
				dataHandlerAndSave(queryList, cleardate,reconType,msgBf,counts,tdiractionarieMap,platformDetailMap);
				
				msg=String.format("\n分离结果集、入数据库条数：%s、用时: %s",queryList.size(),Function.getProcessTime(startTime)); 
				logger.info(msg);
				msgBf.append(msg);
				pageNo++;
			}
			msg=String.format("\n分离结果集总数、入成功表条数%s、入差错表条数%s、入延迟表条数%s、入未对账表条数%s、未处理条数%s",
					counts[0],counts[1],counts[2],counts[3],counts[4]); 
			logger.info(msg);
			msgBf.append(msg);
			rckflowdetail.setExceptiondesc(msgBf.toString());
			
			
			/*startTime=System.currentTimeMillis();
			sTradeResultService.batchDeleteByReconDate(cleardate, new HashMap<String, Object>());
			msg=String.format("\n删除结果集表: %s",Function.getProcessTime(startTime)); 
			logger.info(msg);
			msgBf.append(msg);*/
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
	 * @param platfromNofiles 
	 * @param platfromYesfiles ***/
	public void ruleEngineHandler(List<STradeResult> queryList, List<String> platfromNofiles, List<String> platfromYesfiles){
		for (STradeResult item : queryList) {
			item.setPlatformDetails(platfromNofiles);
			item.setPlatfromYesfiles(platfromYesfiles);
		}
		/**调用引擎执行*/
		droolsRuleEngine.executeRuleEngineBatch(queryList);
	}
	
	/***规则引擎处理入库
	 * @param reconType 
	 * @param msgBf 
	 * @param counts 
	 * @param platformDetailMap 
	 * @param tdiractionarieMap 
	 * @throws ServiceException ***/
	public void dataHandlerAndSave(List<STradeResult> queryList,String reconDate, String reconType,
			StringBuffer msgBf, Integer[] counts, Map<String, String> tdiractionarieMap, Map<String, String> platformDetailMap) throws ServiceException{
		List<Ssuccess>  insertSsuccesss=new ArrayList<Ssuccess>();
		List<Sexception>  insertSexceptions=new ArrayList<Sexception>();
		List<SnoRecon>  insertSnoRecons=new ArrayList<SnoRecon>();
		List<Sdelay>  insertSdelays=new ArrayList<Sdelay>();
		
		List<Sdelay> updateSdelays=new ArrayList<Sdelay>();
		
		Integer  otherCount=0;
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
			}else if(STradeResult.TABLE_NAME_S_EXCEPTION.equals(tableName)){/**差错*/
				Sexception insertSexception=new Sexception();
				insertSexception.setOrderNo(orderNo);
				insertSexception.setOrderTime(orderTime);
				insertSexception.setChanelType(chanelType);
				insertSexception.setTradeType(tradeType);
				insertSexception.setTradeMoney(tradeMoney);
				
				insertSexception.setCreateTime(new Date());
				insertSexception.setReconDate(reconDate);
				insertSexception.setProcessStatus(Sexception.PROCESS_STATUS_NO);
				
				sexceptionHandler(item,errorCode, insertSexception,tdiractionarieMap,platformDetailMap);
				
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
					insertSdelay.setBusinessType(Sdelay.BUSINESSTYPE_TRADE);
					
					insertSdelays.add(insertSdelay);
				}
			}else if(STradeResult.TABLE_NAME_S_OTHER.equals(tableName)){
				Sexception insertSexception=new Sexception();
				insertSexception.setOrderNo(orderNo);
				insertSexception.setOrderTime(orderTime);
				insertSexception.setChanelType(chanelType);
				insertSexception.setTradeType(tradeType);
				insertSexception.setTradeMoney(tradeMoney);
				
				insertSexception.setCreateTime(new Date());
				insertSexception.setReconDate(reconDate);
				insertSexception.setProcessStatus(Sexception.PROCESS_STATUS_SUCCESS);
				
				sexceptionHandler(item,errorCode, insertSexception,tdiractionarieMap,platformDetailMap);
				
				insertSexceptions.add(insertSexception);
				
				otherCount++;
			}
			/**现金退款对完账后，更新延迟对账表状态改为已对账**/
			if(BusinessConstant.REFOUD_LIST.contains(tradeType)){
				Sdelay updateSdelay=new Sdelay();
				updateSdelay.setOrderNo(orderNo);
				updateSdelay.setPlatformNo(ReconBusinessConstant.PlatformNo.FRONT);
				updateSdelay.setReconStatus(Sdelay.RECON_STATUS_RECONED);
				updateSdelays.add(updateSdelay);
			}
		}
		counts[0]+=insertSsuccesss.size();
		counts[1]+=insertSexceptions.size();
		counts[2]+=insertSdelays.size();
		counts[3]+=insertSnoRecons.size();
		counts[4]+=otherCount;
				
		String	msg=String.format("\n分离结果集、入成功表条数%s、入差错表条数%s、入延迟表条数%s、入未对账表条数%s、未处理条数%s",
				insertSsuccesss.size(),insertSexceptions.size(),insertSdelays.size(),insertSnoRecons.size(),otherCount); 
		logger.info(msg);
		msgBf.append(msg);
		
		if(BusinessConstant.IMP_TYPE.ALL.equals(reconType)){
			/***数据入库*/
			sTradeResultService.separateDataHandler(insertSsuccesss, insertSexceptions, insertSnoRecons, insertSdelays, queryList);
		}else{
			sTradeResultService.separateDataHandlerPart(insertSsuccesss, insertSexceptions, insertSnoRecons, insertSdelays, queryList);
		}
		
		/**退款延迟更新***/
		sdelayService.updateSdelays(updateSdelays);
	}

	/**差错处理*/
	private void sexceptionHandler(STradeResult result, String errorCode, Sexception insertSexception,
			Map<String, String> tdiractionarieMap, Map<String, String> platformDetailMap)
			throws ServiceException {
		insertSexception.setIsFront(result.getIsFront());
		insertSexception.setIsBankPoints(result.getIsBankPoints());
		insertSexception.setIsPay(result.getIsPay());
		insertSexception.setIsPartner(result.getIsPartner());
		insertSexception.setIsPoints(result.getIsPoints());
		
		insertSexception.setFrontCode(result.getFrontCode());
//		insertSexception.setProcessStatus(Sexception.PROCESS_STATUS_NO);

		insertSexception.setErrorCode(errorCode);
		String	errorDesc=pageMessageUtil.getFdErrorCodeMessage(errorCode);
		insertSexception.setErrorDesc(errorDesc);/**错误描述*/

		String tradeType=result.getTradeType();
		String tradeTypeName=MapUtils.getString(tdiractionarieMap, Tdiractionary.TYPE_NO_TRADE_TYPE+"_"+tradeType);
		insertSexception.setTradeTypeName(tradeTypeName);
		
		String frontNo=result.getFrontPartnerNo();
		String bankNo=result.getBackGroup();
		String pointBankNo=result.getPointBankGroup();
		String supplierNo=result.getSupplierNo();
		String pointOrgNo=result.getPointOrg();
		
		insertSexception.setFrontNo(frontNo);
		insertSexception.setBankNo(bankNo);
		insertSexception.setPointBankNo(pointBankNo);
		insertSexception.setSupplierNo(supplierNo);
		insertSexception.setPointOrgNo(pointOrgNo);
		
		String frontName=MapUtils.getString(platformDetailMap, ReconBusinessConstant.PlatformNo.FRONT+"_"+frontNo);
		String bankName=MapUtils.getString(platformDetailMap, ReconBusinessConstant.PlatformNo.PAY+"_"+bankNo);
		String pointBankName=MapUtils.getString(platformDetailMap, ReconBusinessConstant.PlatformNo.BANK_POINTS+"_"+pointBankNo);
		String supplierName=MapUtils.getString(platformDetailMap, ReconBusinessConstant.PlatformNo.PARTNER+"_"+supplierNo);
		String pointOrgName=MapUtils.getString(platformDetailMap, ReconBusinessConstant.PlatformNo.POINTS+"_"+pointOrgNo);
		
		insertSexception.setFrontName(frontName);
		insertSexception.setBankName(bankName);
		insertSexception.setPointBankName(pointBankName);
		insertSexception.setSupplierName(supplierName);
		insertSexception.setPointOrgName(pointOrgName);
	}
}
