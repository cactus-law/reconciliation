package com.froad.recon.importfile.handler.checkimpl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.froad.beans.Rckflowdetail;
import com.froad.comon.constant.BusinessConstant;
import com.froad.comon.util.FileUtil;
import com.froad.recon.importfile.handler.FileCheckProcessor;

/**
 * 校验对账数据(第一行为总的记录数)
 * @author Administrator
 *
 */
public class FileCheckProcessorFirstTotal implements FileCheckProcessor {

	final static Logger logger = LoggerFactory.getLogger(FileCheckProcessorFirstTotal.class);
	
	@Override
	public Map<String, Object> execute(Map<String, Object> reqMap) {
		Map<String,Object> respMap = new HashMap<String, Object>();
		respMap.put(BusinessConstant.RESULT, BusinessConstant.SUCCESS);
		Rckflowdetail rckflowdetail = (Rckflowdetail) reqMap.get("rckflowdetail");
		String filePath = (String) reqMap.get("filePath");
		//检查对账数据
		int rows = FileUtil.checkContent(filePath);
		//判断文件内容
		if(rows<0){
			respMap.put(BusinessConstant.RESULT, BusinessConstant.ERROR);
			rckflowdetail.setExceptiondesc("校验对账数据失败");
			logger.info("检查对账数据失败.[filePath={},rows={}]", 
					new Object[]{filePath, rows});
			return respMap;
		}
		logger.info("检查对账数据成功.[filePath={},rows={}]", 
				new Object[]{filePath, rows});
		
		respMap.put("rows", rows);
		
		return respMap;
	}

}
