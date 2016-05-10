package com.froad.recon.importfile.handler.analysisimpl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.froad.beans.Rckflowdetail;
import com.froad.comon.constant.BusinessConstant;
import com.froad.comon.constant.ParamCommand;
import com.froad.comon.util.FileUtil;
import com.froad.flow.FlowException;
import com.froad.recon.importfile.common.RuleUtil;
import com.froad.recon.importfile.handler.FileAnalysisProcessor;
import com.froad.recon.importfile.vo.DataRule;
import com.froad.recon.sys.model.PlatformDetail;

/**
 * 解析对账文件(分隔符)
 * @author Administrator
 *
 */
public class FileAnalysisProcessorSplitZip implements FileAnalysisProcessor {
	
	final static Logger logger = LoggerFactory.getLogger(FileAnalysisProcessorSplitZip.class);

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
		String platformDetailNo=platformDetail.getPlatformDetailNo();
		String channelNo=platformDetail.getChannelNo();
		String fileType=platformDetail.getFileType();
		
		String transDate = (String)reqMap.get("transDate");//交易日期
		try {
			List<Map<String,Object>> dataListAll=new ArrayList<Map<String,Object>>();
			String filePath = ParamCommand.data_dir+transDate+"/"+platformDetailNo+"/"+channelNo+"."+fileType;
			String fileDir=ParamCommand.data_dir+transDate+"/"+platformDetailNo+"/"+channelNo+"/";
			FileUtil.unZipFiles(filePath, fileDir);
			logger.info("解压对账文件成功.[filePath={},fileDir={}]", new Object[]{filePath, fileDir});
			
			
			String ruleFileName = platformDetail.getRuleFileName();//规则文件名称
			String ruleFilePath = ParamCommand.rule_dir+ruleFileName;//规则文件路径
			filePath = fileDir + String.format(platformDetail.getLocalFilePath(), transDate);//对账数据文件路径
			
			//判断文件是否存在
			File file = new File(filePath);
			file=getRealPath(file);
			filePath=file.getPath();
			if(!file.exists()){
				String msg=String.format("对账文件不存在 ,文件为：%s", filePath);
				throw new FlowException(rckflowdetail,msg);
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
				
			respMap.put("dataList", dataListAll);
			reqMap.put("dataList", dataListAll);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new FlowException(rckflowdetail, e.getMessage(), e);
		}
		return respMap;
	}
	
	/**通过正则的文件路径获取真实的文件路径**/
	public static File getRealPath(File file){
		File [] files=file.getParentFile().listFiles();
		for (int i = 0; i < files.length; i++) {
			if(isRegularRptCode(files[i].getName(), file.getName())){
				return files[i];
			}
		}
		return file;
	}
	
	public static boolean isRegularRptCode(String rptCode,String regEx1) { 
		Pattern p1 = Pattern.compile(regEx1); 
		Matcher m1 = p1.matcher(rptCode); 
		return m1.matches(); 
	} 
	
	public static void main(String[] args) {
		String path="D:/tmp/debuginfo_[0-9]+.txt";
		File file=new File(path);
		System.out.println(getRealPath(file));
		System.out.println( isRegularRptCode("CCF_20150919_382.txt", "CCF_20150919_[0-9]+.txt"));
	}
}