
package com.froad.recon.sync.flow;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;

import com.froad.beans.Rckflowdetail;
import com.froad.comon.constant.BusinessConstant;
import com.froad.comon.constant.ReconBusinessConstant;
import com.froad.comon.exception.ServiceException;
import com.froad.comon.util.DateUtil;
import com.froad.comon.util.Function;
import com.froad.comon.util.Logger;
import com.froad.flow.FlowException;
import com.froad.flow.FlowInterface;
import com.froad.recon.importfile.service.DynamicService;
import com.froad.recon.importfile.service.IimpStatusDetailService;
import com.froad.recon.reconciliation.service.SdelayService;
import com.froad.recon.reconciliation.service.SexceptionService;
import com.froad.recon.reconciliation.service.SnoReconService;
import com.froad.recon.reconciliation.service.SsuccessService;
import com.froad.recon.sync.model.RreconResult;
import com.froad.recon.sync.service.RreconResultService;
import com.froad.recon.sys.model.Platform;
import com.froad.recon.sys.model.PlatformDetail;
import com.froad.recon.sys.service.PlatformDetailService;
import com.froad.recon.sys.service.PlatformService;

/**
 *结果分离后处理
 * */
public class ReconResultFlow implements FlowInterface {
	private final static Logger logger = Logger
			.getLogger(ReconResultFlow.class);
	 
	private PlatformService platformService;
	private DynamicService dynamicService;
	private RreconResultService rreconResultService;

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
	public RreconResultService getRreconResultService() {
		return rreconResultService;
	}
	public void setRreconResultService(RreconResultService rreconResultService) {
		this.rreconResultService = rreconResultService;
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
			
			/**删除数据*/
			rreconResultService.deleteByRecon(cleardate);
			
			Map<String, String> platformMap=new HashMap<String, String>();
			List<Platform>	platforms=platformService.getAll();
		    for (Platform item : platforms) {
		    	platformMap.put(item.getPlatformNo(), item.getPlatformName());
			}
			
			PlatformDetail queryPlatformDetail=new PlatformDetail();
			queryPlatformDetail.setStatus(PlatformDetail.STATUS_YES);
			
			Integer total=0;
			List<PlatformDetail> details=platformService.getListByImport(cleardate);
			List<RreconResult> insertList=new ArrayList<RreconResult>();
			for (PlatformDetail item : details) {
				if(ReconBusinessConstant.PlatformNo.FRONT.equals(item.getPlatformNo())){
					continue;
				}
				total++;
			    String  tableName=item.getTableName();
			    String platformNo=item.getPlatformNo();
				RreconResult result=new RreconResult();
				
				result.setPlatformDetailNo(item.getPlatformDetailNo());
				result.setPlatformDetailName(item.getPlatformDetailName());
				result.setPlatformNo(item.getPlatformNo());
				result.setPlatformName(MapUtils.getString(platformMap, item.getPlatformNo()));
				result.setReconDate(cleardate);
				result.setCreateTime(new Date());
				
				Integer importTotal=dynamicService.selectForMapCount(tableName, cleardate, new HashMap<String, Object>());
				result.setImportTotal(importTotal);
 
				rreconResultService.queryHandler(result,tableName,platformNo,cleardate);
				
				insertList.add(result);
				
				rreconResultService.insert(result);
			}
			//rreconResultService.batchInser(insertList);
			msg="\n 对账导入结果表同步成功，用时:"+ Function.getProcessTime(startTime);
			logger.info(msg);
			msgBf.append(msg);
			rckflowdetail.setExceptiondesc(msgBf.toString());
			rckflowdetail.setSuccesscount(total);
			rckflowdetail.setDealcount(total);
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
