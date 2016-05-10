package com.froad.recon.importfile.handler.analysisimpl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.froad.beans.Rckflowdetail;
import com.froad.comon.constant.BusinessConstant;
import com.froad.comon.constant.ParamCommand;
import com.froad.flow.FlowException;
import com.froad.recon.importfile.common.RuleUtil;
import com.froad.recon.importfile.handler.FileAnalysisProcessor;
import com.froad.recon.importfile.vo.DataRule;
import com.froad.recon.sys.model.PlatformDetail;

/**
 * 解析对账文件(定长字符)
 * @author Administrator
 *
 */
public class FileAnalysisProcessorFixlength implements FileAnalysisProcessor {
	
	final static Logger logger = LoggerFactory.getLogger(FileAnalysisProcessorFixlength.class);

	@Override
	/**
	 * 解析对账文件返回 数据
	 * @param reqMap
	 * @return
	 */
	public Map<String,Object> execute(Map<String,Object> reqMap) throws FlowException {
		Map<String,Object> respMap = new HashMap<String, Object>();
		respMap.put(BusinessConstant.RESULT, BusinessConstant.SUCCESS);
		Rckflowdetail rckflowdetail = (Rckflowdetail) reqMap.get("rckflowdetail");
		PlatformDetail platformDetail = (PlatformDetail) reqMap.get("platformDetail");
		String transDate = (String)reqMap.get("transDate");//交易日期
		String fileDir = (String)reqMap.get("fileDir");
		if(fileDir==null){//手动上传文件
			fileDir = ParamCommand.data_dir+transDate+"/"+platformDetail.getPlatformDetailNo()+"/";
		}
		try {

			String ruleFileName = platformDetail.getRuleFileName();//规则文件名称
			String ruleFilePath = ParamCommand.rule_dir+ruleFileName;//规则文件路径
			String filePath = fileDir + String.format(platformDetail.getLocalFilePath(), transDate);//对账数据文件路径
			reqMap.put("filePath", filePath);
			
			//判断文件是否存在
			File file = new File(filePath);
			if(!file.exists()){
				String msg=String.format("对账文件不存在 ,文件为：%s", filePath);
				throw new FlowException(rckflowdetail,msg);
			}
			
			//设置规则参数
			DataRule dataRule = new DataRule();
			dataRule.setRuleType(BusinessConstant.RULE_TYPE.FIXLENGTH);
			dataRule.setReconDate(transDate); 
			dataRule.setRuleFile(ruleFilePath);
			dataRule.setDataFile(filePath);
			dataRule.setTable((String)reqMap.get("table"));
			//解析对账数据
			List<Map<String,Object>> dataList = RuleUtil.resolveData(dataRule);
			respMap.put("dataList", dataList);
			reqMap.put("dataList", dataList);
			logger.info("解析对账文件完成.");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new FlowException(rckflowdetail, e.getMessage(), e);
		}
		
		return respMap;
	}

}
