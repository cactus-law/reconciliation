package com.froad.recon.importfile.handler.datadealimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.froad.comon.constant.BusinessConstant;
import com.froad.recon.importfile.handler.DataDealProcessor;

/**
 * 整理对账数据(支付宝现金)
 * @author Administrator
 *
 */
public class DataDealProcessorCalipay implements DataDealProcessor {

	/**
	 * 整理对账数据
	 */
	@Override
	public Map<String, Object> execute(Map<String, Object> reqMap) {
		Map<String,Object> respMap = new HashMap<String, Object>();
		respMap.put(BusinessConstant.RESULT, BusinessConstant.SUCCESS);
		
		@SuppressWarnings("unchecked")
		List<Map<String,Object>> dataList = (List<Map<String, Object>>) reqMap.get("dataList");
		List<Map<String,Object>> tempList = new ArrayList<Map<String,Object>>();
		for(Map<String,Object> data : dataList){
			if("服务费".equals(data.get("finance_type"))){
				tempList.add(data);
			}
		}
		dataList.removeAll(tempList);
		
		return respMap;
	}

}
