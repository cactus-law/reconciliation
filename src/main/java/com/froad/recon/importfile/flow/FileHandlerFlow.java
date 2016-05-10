package com.froad.recon.importfile.flow;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.froad.beans.Rckflowdetail;
import com.froad.comon.constant.BusinessConstant;
import com.froad.comon.constant.ParamCommand;
import com.froad.comon.exception.ServiceException;
import com.froad.flow.FlowException;
import com.froad.flow.FlowInterface;
import com.froad.recon.importfile.handler.DataDealProcessor;
import com.froad.recon.importfile.handler.FileAnalysisProcessor;
import com.froad.recon.importfile.handler.FileCheckProcessor;
import com.froad.recon.importfile.handler.FileGetProcessor;
import com.froad.recon.importfile.handler.FileHandlerFactory;
import com.froad.recon.importfile.model.IimpStatusDetail;
import com.froad.recon.importfile.model.IimpStatusDetailId;
import com.froad.recon.importfile.service.IimpStatusDetailService;
import com.froad.recon.importfile.service.ImpDataService;
import com.froad.recon.sys.model.PlatformDetail;
import com.froad.recon.sys.service.PlatformDetailService;

/**
 * 对账文件处理流程
 * 
 * @author Administrator
 */
@Component("fileHandlerFlow")
public class FileHandlerFlow implements FlowInterface {

	final static Logger logger = LoggerFactory.getLogger(FileHandlerFlow.class);

	@Autowired
	private FileHandlerFactory fileHandlerFactory;

	@Autowired
	private PlatformDetailService platformDetailService;

	@Autowired
	private ImpDataService impDataService;

	@Autowired
	private IimpStatusDetailService iimpStatusDetailService;

	
	private void  initialIimp( Rckflowdetail rckflowdetail,PlatformDetail platformDetail) throws ServiceException{
		String transDate = rckflowdetail.getId().getCleardate();
        
    	IimpStatusDetailId id = new IimpStatusDetailId();
    	id.setPlatformDetailNo(platformDetail.getPlatformDetailNo());//平台明细编号
    	id.setImpDate(transDate);//导入日期
    	
    	IimpStatusDetail impStatusDetail = iimpStatusDetailService.getIimpStatusDetail(id);
    	if(impStatusDetail==null){
    		impStatusDetail = new IimpStatusDetail();
			impStatusDetail.setId(id);//联合主键
			//平台明细名称
			impStatusDetail.setPlatformDetailName(platformDetail.getPlatformDetailName());
			impStatusDetail.setDownloadUrl("");//文件存放物理路径
			impStatusDetail.setCreateTime(new Timestamp(System.currentTimeMillis()));//创建时间
		
			impStatusDetail.setStatus(BusinessConstant.IMP_STATUS.INITIAL);//状态
			iimpStatusDetailService.insert(impStatusDetail);
    	}else{
    		impStatusDetail.setStatus(BusinessConstant.IMP_STATUS.INITIAL);//状态
    		iimpStatusDetailService.update(impStatusDetail);
    	}
    	rckflowdetail.setIimpStatusDetail(impStatusDetail);
	}
	/**
	 * 对账文件处理流程入口
	 */
	@Override
	public Rckflowdetail execute(Rckflowdetail rckflowdetail)
			throws FlowException {
		try {
			String msg="";
			PlatformDetail platformDetail = platformDetailService
					.getById(rckflowdetail.getMsg());
			
			logger.debug("flowDetail start0 : "+platformDetail.toString());
			String transDate=rckflowdetail.getId().getCleardate();//对账日期
			String platformDetailNo=platformDetail.getPlatformDetailNo();//平台明细编号
			
			String reconType=rckflowdetail.getReconType();	
			if(BusinessConstant.IMP_TYPE.PART.equals(reconType)){//部分
				if(Rckflowdetail.FLOW_STATE_SUCCESS.equals(rckflowdetail.getFlowstate())){
					return rckflowdetail;
				}
			}
			
			initialIimp(rckflowdetail,platformDetail);
			IimpStatusDetail impStatusDetail = rckflowdetail.getIimpStatusDetail();
			
			Map<String, Object> reqMap = new HashMap<String, Object>();
			reqMap.put("rckflowdetail", rckflowdetail);
			reqMap.put("platformDetail", platformDetail);
			reqMap.put("impStatusDetail", impStatusDetail);
			reqMap.put("transDate",transDate);// 对账日期
			reqMap.put("channelNo", platformDetail.getChannelNo());//渠道编号
			reqMap.put("table", platformDetail.getTableName());// 表名
			// 1.获取对账文件
			FileGetProcessor fileGetProcessor = fileHandlerFactory
					.getFileGetProcessor(platformDetail.getFileBean());
			if (fileGetProcessor == null) {
				msg=String.format("获取对账文件 bean【%s】 未找到！",platformDetail.getFileBean());
				throw new FlowException(msg);
			}
			
			Map<String, Object> respMap = null;
			
			String  fileStatus=platformDetail.getFileStatus(); //文件状态0.自动1.手动
			String  impFileStatus=impStatusDetail.getFileStatus(); //文件是否已经获取
			String  impType=impStatusDetail.getImpType();//真正的获取方式， 自动或者手动
			
			logger.debug("flowDetail fileGetProcessor1 : "+platformDetail.getPlatformDetailNo());
			if(IimpStatusDetail.FILE_STATUS_YES.equals(impFileStatus)){ //已获取
				if(IimpStatusDetail.IMPTYPE_MANU.equals(impType)){ //手动获取
					reqMap.put("fileDir", ParamCommand.data_dir+"/"+transDate+"/"+platformDetailNo+"/");
				}else{//自动获取
					if (BusinessConstant.IMP_TYPE.ALL.equals(rckflowdetail.getReconType())) {//全部
						try {
							respMap = fileGetProcessor.execute(reqMap);	
							impStatusDetail.setImpType(IimpStatusDetail.IMPTYPE_AUTO);//导入类型
							impStatusDetail.setFileStatus(IimpStatusDetail.FILE_STATUS_YES);//已获取
						} catch (FlowException e) {
							impStatusDetail.setImpType(IimpStatusDetail.IMPTYPE_AUTO);//导入类型
							impStatusDetail.setFileStatus(IimpStatusDetail.FILE_STATUS_NO);//未获取
							throw e;
						}
					}else if (BusinessConstant.IMP_TYPE.PART.equals(rckflowdetail.getReconType())) {//未对账执行
						reqMap.put("fileDir", ParamCommand.data_dir+"/"+transDate+"/"+platformDetailNo+"/");
					} 
				}
			}else{//未获取文件
				if(PlatformDetail.FILE_STATUS_YES.equals(fileStatus)){
					try {
						respMap = fileGetProcessor.execute(reqMap);	
						impStatusDetail.setImpType(IimpStatusDetail.IMPTYPE_AUTO);//导入类型
						impStatusDetail.setFileStatus(IimpStatusDetail.FILE_STATUS_YES);//已获取
					} catch (FlowException e) {
						impStatusDetail.setImpType(IimpStatusDetail.IMPTYPE_AUTO);//导入类型
						impStatusDetail.setFileStatus(IimpStatusDetail.FILE_STATUS_NO);//未获取
						throw e;
					}
				}else{
					msg=String.format("手动文件获取失败,平台明细编号.[platformDetailNo=%s]", platformDetailNo);
					logger.error(msg);
					throw new FlowException(rckflowdetail,msg);
				}
			}
			
			//获取对账文件失败
			if(respMap!=null && BusinessConstant.ERROR.equals(respMap.get(BusinessConstant.RESULT))){
				rckflowdetail.setDealcount(0);//导入成功总数
				impStatusDetail.setSuccessCount(0);//导入成功总数
				impStatusDetail.setStatus(BusinessConstant.IMP_STATUS.SUCCESS);// 状态
				rckflowdetail.setExceptiondesc("导入成功");
				return rckflowdetail;
			}
			
			logger.debug("flowDetail fileAnalysisProcessor2: "+platformDetail.getPlatformDetailNo());
			// 2.解析对账文件
			FileAnalysisProcessor fileAnalysisProcessor = fileHandlerFactory
					.getFileAnalysisProcessor(platformDetail.getFileReadBean());
			if (fileAnalysisProcessor == null) {
				msg=String.format("解析对账文件 bean【%s】 未找到！",platformDetail.getFileReadBean());
				throw new FlowException(msg);
			}
			respMap = fileAnalysisProcessor.execute(reqMap);
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> dataList = (List<Map<String, Object>>) respMap
					.get("dataList");
			if (dataList == null || dataList.isEmpty()) {
				logger.info("对账数据没有记录.[dataList={}]", dataList);
				// AOP修改成功记录
				rckflowdetail.setSuccesscount(0);
				impStatusDetail.setSuccessCount(0);// 导入成功总数
				impStatusDetail.setErrorCount(0);// 导入失败记录数
				impStatusDetail.setTotal(0);// 导入总记录数
				impStatusDetail.setStatus(BusinessConstant.IMP_STATUS.SUCCESS);// 状态
				rckflowdetail.setDealcount(0);//导入成功记录数
				rckflowdetail.setExceptiondesc("导入成功");
				return rckflowdetail;
			}
			logger.debug("flowDetail fileCheckProcessor3: "+platformDetail.getPlatformDetailNo());
			// 3.校验对账文件
			FileCheckProcessor fileCheckProcessor = fileHandlerFactory
					.getFileCheckProcessor(platformDetail.getCheckDataBean());
			if (fileCheckProcessor == null) {
				msg=String.format("校验对账文件 bean【%s】 未找到！",platformDetail.getCheckDataBean());
				throw new FlowException(msg);
			}
			respMap = fileCheckProcessor.execute(reqMap);
			Integer rows = (Integer) respMap.get("rows");// 总的记录数

			logger.debug("flowDetail dataDealProcessor4: "+platformDetail.getPlatformDetailNo());
			// 4.数据整理
			DataDealProcessor dataDealProcessor = fileHandlerFactory
					.getDataDealProcessor(platformDetail.getDataDealBean());
			if (dataDealProcessor == null) {
				msg=String.format("数据整理 bean【%s】 未找到！",platformDetail.getDataDealBean());
				throw new FlowException(msg);
			}
			respMap = dataDealProcessor.execute(reqMap);
			
			/***order_no为空处理*/
			orderNoEmptyHandler(reqMap);

			
			logger.debug("flowDetail saveData5: "+platformDetail.getPlatformDetailNo());
			dataList = (List<Map<String, Object>>) reqMap.get("dataList");
			// 5.对账数据入库
			int count = impDataService.addList(dataList, platformDetail);
			logger.info("对账数据入库成功.[count={}]", new Object[] { count });

			// AOP修改成功记录
			rckflowdetail.setDealcount(count);

			impStatusDetail.setSuccessCount(count);// 导入成功总数
			if (rows != null) {
				impStatusDetail.setErrorCount(rows - count);// 导入失败记录数
				impStatusDetail.setTotal(rows);// 导入总记录数
			}
			impStatusDetail.setStatus(BusinessConstant.IMP_STATUS.SUCCESS);// 状态
			rckflowdetail.setExceptiondesc("导入成功");

		} catch (ServiceException e) {
			logger.error(e.getErrorMsg(), e);
			throw new FlowException(rckflowdetail, e.getErrorMsg(), e);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new FlowException(rckflowdetail, e.getMessage(), e);
		}
		return rckflowdetail;
	}
	
	/***
	 * order_no为空处理
	 * @param reqMap
	 */
	private void orderNoEmptyHandler(Map<String, Object> reqMap ){
		@SuppressWarnings("unchecked")
		List<Map<String,Object>> dataList = (List<Map<String, Object>>) reqMap.get("dataList");
		List<Map<String,Object>> resultList=new ArrayList<Map<String,Object>>();
		for (Map<String, Object> map : dataList) {
			if(StringUtils.isNotEmpty(MapUtils.getString(map, "order_no"))){
				resultList.add(map);
			}
		}
		reqMap.put("dataList", resultList);
	}

}
