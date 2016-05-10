package com.froad.recon.importfile.handler.datadealimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.froad.comon.constant.BusinessConstant;
import com.froad.recon.importfile.handler.DataDealProcessor;

/**
 * 整理对账数据(衡水)
 * @author Administrator
 *
 */
public class DataDealProcessorHengShui implements DataDealProcessor {

	/**
	 * 整理对账数据
	 */
	@Override
	public Map<String, Object> execute(Map<String, Object> reqMap) {
		Map<String,Object> respMap = new HashMap<String, Object>();
		respMap.put(BusinessConstant.RESULT, BusinessConstant.SUCCESS);
		
		@SuppressWarnings("unchecked")
		List<Map<String,Object>> dataList = (List<Map<String, Object>>) reqMap.get("dataList");
		for(Map<String,Object> data : dataList){
			if("冲正".equals(data.get("tradetype"))){
				data.put("order_no", "cz"+data.get("order_no"));
			}
		}
		
		return respMap;
	}

}
