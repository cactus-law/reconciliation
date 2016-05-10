package com.froad.recon.importfile.handler.datadealimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.froad.comon.constant.BusinessConstant;
import com.froad.recon.importfile.handler.DataDealProcessor;

/**
 * 整理对账数据(前端冲正交易处理)
 * @author Administrator
 *
 */
public class DataDealProcessorCZRemove implements DataDealProcessor {

	/**
	 * 整理对账数据
	 */
	@Override
	public Map<String, Object> execute(Map<String, Object> reqMap) {
		Map<String,Object> respMap = new HashMap<String, Object>();
		respMap.put(BusinessConstant.RESULT, BusinessConstant.SUCCESS);
		
		@SuppressWarnings("unchecked")
		List<Map<String,Object>> dataList = (List<Map<String, Object>>) reqMap.get("dataList");
		List<Map<String,Object>> resultList=new ArrayList<Map<String,Object>>();
		for(Map<String,Object> data : dataList){
			if(!"冲转".equals(data.get("tradetype"))){
				resultList.add(data);	
			}
		}
		
		reqMap.put("dataList", resultList);
		return respMap;
	}

}
