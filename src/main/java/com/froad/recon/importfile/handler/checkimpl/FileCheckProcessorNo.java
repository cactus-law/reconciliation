package com.froad.recon.importfile.handler.checkimpl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.froad.comon.constant.BusinessConstant;
import com.froad.recon.importfile.handler.FileCheckProcessor;

/**
 * 校验对账数据(不校验)
 * @author Administrator
 *
 */
public class FileCheckProcessorNo implements FileCheckProcessor {

	final static Logger logger = LoggerFactory.getLogger(FileCheckProcessorNo.class);
	
	@Override
	public Map<String, Object> execute(Map<String, Object> reqMap) {
		Map<String,Object> respMap = new HashMap<String, Object>();
		respMap.put(BusinessConstant.RESULT, BusinessConstant.SUCCESS);

		return respMap;
	}

}
