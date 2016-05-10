package com.froad.recon.importfile.flow;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.froad.beans.Rckflowdetail;
import com.froad.comon.constant.BusinessConstant;
import com.froad.comon.exception.ServiceException;
import com.froad.flow.FlowException;
import com.froad.flow.FlowInterface;
import com.froad.recon.importfile.handler.DataDealProcessor;
import com.froad.recon.importfile.handler.FileHandlerFactory;
import com.froad.recon.importfile.model.IimpStatusDetail;
import com.froad.recon.importfile.model.IimpStatusDetailId;
import com.froad.recon.importfile.service.IimpStatusDetailService;
import com.froad.recon.importfile.service.ImpDataService;
import com.froad.recon.sys.model.PlatformDetail;
import com.froad.recon.sys.service.PlatformDetailService;

/**
 * <pre>
 *  直接从数据库获取数据
 * </pre>
 *
 * @author xueyunlong
 * @create 2015年9月14日 上午10:24:14
 * @modify
 * @since   JDK1.6
 */
@Component("dataHandlerFlow")
public class DataHandlerFlow implements FlowInterface {

	final static Logger logger = LoggerFactory.getLogger(DataHandlerFlow.class);


	@Autowired
	private PlatformDetailService platformDetailService;

	@Autowired
	private ImpDataService impDataService;

	@Autowired
	private IimpStatusDetailService iimpStatusDetailService;
	
	@Autowired
	private FileHandlerFactory fileHandlerFactory;

	
	public void  initialIimp( Rckflowdetail rckflowdetail,PlatformDetail platformDetail) throws ServiceException{
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
			
			String transDate=rckflowdetail.getId().getCleardate();//对账日期
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
			
			
			// 获取对账数据
			DataDealProcessor dataDealProcessor = fileHandlerFactory
					.getDataDealProcessor(platformDetail.getDataDealBean());
			if (dataDealProcessor == null) {
				msg=String.format("数据整理 bean【%s】 未找到！",platformDetail.getDataDealBean());
				throw new FlowException(msg);
			}
			Map<String, Object> respMap = dataDealProcessor.execute(reqMap);
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
			// 对账数据入库
			int count = impDataService.addList(dataList, platformDetail);
			logger.info("对账数据入库成功.[count={}]", new Object[] { count });

			// AOP修改成功记录
			rckflowdetail.setDealcount(count);

			impStatusDetail.setSuccessCount(count);// 导入成功总数
				impStatusDetail.setErrorCount(0);// 导入失败记录数
				impStatusDetail.setTotal(count);// 导入总记录数
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

}
