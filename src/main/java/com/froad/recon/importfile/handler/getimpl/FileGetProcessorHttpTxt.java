package com.froad.recon.importfile.handler.getimpl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.froad.beans.Rckflowdetail;
import com.froad.comon.constant.BusinessConstant;
import com.froad.comon.constant.ParamCommand;
import com.froad.comon.util.HttpClientUtil;
import com.froad.flow.FlowException;
import com.froad.recon.importfile.handler.FileGetProcessor;
import com.froad.recon.sys.model.PlatformDetail;

/**
 * 获取对账文件(文本文件)
 * @author Administrator
 *
 */
public class FileGetProcessorHttpTxt implements FileGetProcessor {

	final static Logger logger = LoggerFactory.getLogger(FileGetProcessorHttpTxt.class);
	
	/**
	 * 获取文件
	 * @param reqMap
	 * @return
	 * @throws FlowException 
	 */
	public Map<String,Object> execute(Map<String,Object> reqMap) throws FlowException{
		Map<String,Object> respMap = new HashMap<String, Object>();
		respMap.put(BusinessConstant.RESULT, BusinessConstant.SUCCESS);
		Rckflowdetail rckflowdetail = (Rckflowdetail) reqMap.get("rckflowdetail");
		PlatformDetail platformDetail = (PlatformDetail) reqMap.get("platformDetail");
		try {
			String fileUrl = platformDetail.getFileUrl();//获取文件地址
//	    	String fileParam = platformDetail.getFileParam();//获取文件参数
	    	String localFilePath = platformDetail.getLocalFilePath();//本地文件路径
	    	String platformDetailNo = platformDetail.getPlatformDetailNo();//平台明细编号
	    	
			//获取对账文件
			String transDate = (String)reqMap.get("transDate");//交易日期
			String temp = "";
			if("0".equals(platformDetail.getFileType())){
				temp = transDate.subSequence(0, 4)+"-"+transDate.subSequence(4, 6)+"-"+transDate.subSequence(6, 8);
	    	}else{
	    		temp = transDate;
	    	}
			
			fileUrl = String.format(fileUrl, temp);
			//本地文件存放路径(文本文件)
			localFilePath = String.format(localFilePath, transDate);
			String filePath = ParamCommand.data_dir+"/"+transDate+"/"+platformDetailNo+"/"+localFilePath;
			String msg = HttpClientUtil.getDownLoad(fileUrl, filePath);
			if(StringUtils.isNotEmpty(msg)){
				throw new FlowException(rckflowdetail,msg);
			}
			logger.info("获取对账文件成功.[fileUrl={},filePath={}]", 
					new Object[]{fileUrl, filePath});

	    	reqMap.put("fileDir", ParamCommand.data_dir+"/"+transDate+"/"+platformDetailNo+"/");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new FlowException(rckflowdetail, e.getMessage(), e);
		}
		return respMap;
	}

}
