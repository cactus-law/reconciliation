package com.froad.recon.importfile.handler.datadealimpl;

import java.util.HashMap;
import java.util.Map;

import com.froad.comon.constant.BusinessConstant;
import com.froad.recon.importfile.handler.DataDealProcessor;

/**
 * 整理对账数据(不整理)
 * @author Administrator
 *
 */
public class DataDealProcessorNo implements DataDealProcessor {

	/**
	 * 整理对账数据
	 */
	@Override
	public Map<String, Object> execute(Map<String, Object> reqMap) {
		Map<String,Object> respMap = new HashMap<String, Object>();
		respMap.put(BusinessConstant.RESULT, BusinessConstant.SUCCESS);
		return respMap;
	}

}
