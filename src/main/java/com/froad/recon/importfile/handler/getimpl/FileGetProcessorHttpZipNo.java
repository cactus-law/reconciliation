package com.froad.recon.importfile.handler.getimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.froad.beans.Rckflowdetail;
import com.froad.comon.constant.BusinessConstant;
import com.froad.comon.constant.ParamCommand;
import com.froad.comon.util.FileUtil;
import com.froad.comon.util.HttpClientUtil;
import com.froad.flow.FlowException;
import com.froad.recon.importfile.handler.FileGetProcessor;
import com.froad.recon.sys.model.PlatformDetail;

/**
 * (ZIP压缩文件 不解压)
 * @author Administrator
 *
 */
public class FileGetProcessorHttpZipNo implements FileGetProcessor {

	final static Logger logger = LoggerFactory.getLogger(FileGetProcessorHttpZipNo.class);

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
		String channelNo=platformDetail.getChannelNo();
		String fileType=platformDetail.getFileType();
		
		String filePath = "";//本地文件存放路径(ZIP压缩文件)
		String fileUrlTemp = platformDetail.getFileUrl();//获取文件地址
		String fileUrl="";
		//1.获取对账文件
		try {
	    	String platformDetailNo = platformDetail.getPlatformDetailNo();//平台明细编号
	    	
			//获取对账文件
			String transDate = (String)reqMap.get("transDate");//交易日期
			String temp = "";
			if("0".equals(platformDetail.getFileType())){
				temp = transDate.subSequence(0, 4)+"-"+transDate.subSequence(4, 6)+"-"+transDate.subSequence(6, 8);
	    	}else{
	    		temp = transDate;
	    	}
			fileUrl = String.format(fileUrlTemp, temp,channelNo);
			
			filePath = ParamCommand.data_dir+transDate+"/"+platformDetailNo+"/"+channelNo+"."+fileType;
			String msg = HttpClientUtil.getDownLoad(fileUrl, filePath);
			if(StringUtils.isNotEmpty(msg)){
				throw new FlowException(rckflowdetail,msg);
			}
			logger.info("获取对账文件成功.[fileUrl={},filePath={}]", new Object[]{fileUrl, filePath});
		} catch (Exception e) {
			logger.error("获取对账文件失败.[fileUrl={},filePath={}]", new Object[]{fileUrl, filePath});
			logger.error(e.getMessage(), e);
			respMap.put(BusinessConstant.RESULT, BusinessConstant.ERROR);
			throw new FlowException(rckflowdetail, e.getMessage(), e);
		}
		return respMap;
	}
}
