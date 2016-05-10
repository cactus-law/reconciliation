package com.froad.recon.reconciliation.flow;

import java.util.HashMap;
import java.util.Map;

import com.froad.beans.Rckflowdetail;
import com.froad.comon.constant.BusinessConstant;
import com.froad.comon.exception.ServiceException;
import com.froad.comon.util.DateUtil;
import com.froad.comon.util.Function;
import com.froad.comon.util.Logger;
import com.froad.flow.FlowException;
import com.froad.flow.FlowInterface;
import com.froad.recon.importfile.service.IimpStatusDetailService;
import com.froad.recon.reconciliation.model.Sdelay;
import com.froad.recon.reconciliation.service.SdelayService;
import com.froad.recon.reconciliation.service.SexceptionService;
import com.froad.recon.reconciliation.service.SnoReconService;
import com.froad.recon.reconciliation.service.SsuccessService;

/**
 *结果分离后处理
 * */
public class DataAfterFlow implements FlowInterface {
	private final static Logger logger = Logger
			.getLogger(DataAfterFlow.class);
	 
    private SnoReconService snoReconService;
    private SdelayService sdelayService;
    private SsuccessService ssuccessService;
    private SexceptionService sexceptionService;
    
    private IimpStatusDetailService iimpStatusDetailService;

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
	
	public IimpStatusDetailService getIimpStatusDetailService() {
		return iimpStatusDetailService;
	}

	public void setIimpStatusDetailService(
			IimpStatusDetailService iimpStatusDetailService) {
		this.iimpStatusDetailService = iimpStatusDetailService;
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
			Long startTime=System.currentTimeMillis();
			
			
			/***更新导入状态明细表，把导入成功的修改为对账成功*/
			iimpStatusDetailService.updateByReconDate(cleardate, BusinessConstant.IMP_STATUS.RECON_SUCCESS);
			/**把延迟对账日前10天的数据删除掉***/
			String beforeClearDate=DateUtil.getStringFromDate(DateUtil.addDaysToDate(cleardate, -10));
			Map<String, Object> paramsMap=new HashMap<String, Object>();
			paramsMap.put("businessType", Sdelay.BUSINESSTYPE_TRADE);
			sdelayService.batchDeleteByReconDate(beforeClearDate,paramsMap);
		   
			msg="\n 1更新导入状态明细表，把导入成功的修改为对账成功，用时:"+ Function.getProcessTime(startTime);
			logger.info(msg);
			msgBf.append(msg);
		} catch (ServiceException e) {
			logger.error(e.getErrorMsg(),e);
			throw new FlowException(rckflowdetail,e.getErrorMsg(),e);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			throw new FlowException(rckflowdetail,e.getMessage(),e);
		}
		return rckflowdetail;
	}
}
