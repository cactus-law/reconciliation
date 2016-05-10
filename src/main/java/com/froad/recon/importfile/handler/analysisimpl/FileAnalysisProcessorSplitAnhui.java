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
import com.froad.comon.util.FileUtil;
import com.froad.flow.FlowException;
import com.froad.recon.importfile.common.FileConstants;
import com.froad.recon.importfile.common.RuleUtil;
import com.froad.recon.importfile.handler.FileAnalysisProcessor;
import com.froad.recon.importfile.model.IimpStatusDetail;
import com.froad.recon.importfile.vo.DataRule;
import com.froad.recon.sys.model.PlatformDetail;

/**
 * 解析对账文件(分隔符)
 * @author Administrator
 *
 */
public class FileAnalysisProcessorSplitAnhui implements FileAnalysisProcessor {
	
	final static Logger logger = LoggerFactory.getLogger(FileAnalysisProcessorSplitAnhui.class);

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
		IimpStatusDetail impStatusDetail = (IimpStatusDetail) reqMap.get("impStatusDetail");
		String platformDetailNo=platformDetail.getPlatformDetailNo();
		
		String transDate = (String)reqMap.get("transDate");//交易日期
		try {
			List<Map<String,Object>> dataListAll=new ArrayList<Map<String,Object>>();
			for (String channe : FileConstants.CHANNE_LIST) {
				String filePath = ParamCommand.data_dir+transDate+"/"+platformDetailNo+"/"+channe+".zip";
				
				if(IimpStatusDetail.IMPTYPE_MANU.equals(impStatusDetail.getImpType())){
					File filetemp = new File(filePath);
					if(!filetemp.exists()){
						logger.info("对账文件不存在，这里标识为成功.[filePath={}]", filetemp);
						continue;
					}
				}
				
				String fileDir=ParamCommand.data_dir+transDate+"/"+platformDetailNo+"/"+channe+"/";
				FileUtil.unZipFiles(filePath, fileDir);
				logger.info("解压对账文件成功.[filePath={},fileDir={}]", new Object[]{filePath, fileDir});
				
				String ruleFileName = platformDetail.getRuleFileName();//规则文件名称
				String ruleFilePath = ParamCommand.rule_dir+ruleFileName;//规则文件路径
				filePath = fileDir + String.format(platformDetail.getLocalFilePath(), transDate);//对账数据文件路径
				
				//判断文件是否存在
				File file = new File(filePath);
				if(!file.exists()){
					logger.info("对账文件不存在，这里标识为成功.[filePath={}]", filePath);
					
					List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
					respMap.put("dataList", dataList);
					reqMap.put("dataList", dataList);
					return respMap;
				}
				
				//设置规则参数
				DataRule dataRule = new DataRule();
				dataRule.setRuleType(BusinessConstant.RULE_TYPE.SPLIT);
				dataRule.setReconDate(transDate); 
				dataRule.setRuleFile(ruleFilePath);
				dataRule.setDataFile(filePath);
				dataRule.setTable((String)reqMap.get("table"));
				//解析对账数据
				List<Map<String,Object>> dataList = RuleUtil.resolveData(dataRule);
				dataListAll.addAll(dataList);
			}
			respMap.put("dataList", dataListAll);
			reqMap.put("dataList", dataListAll);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new FlowException(rckflowdetail, e.getMessage(), e);
		}
		return respMap;
	}
}
