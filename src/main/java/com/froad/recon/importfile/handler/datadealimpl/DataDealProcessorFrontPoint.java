package com.froad.recon.importfile.handler.datadealimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import com.froad.comon.constant.BusinessConstant;
import com.froad.comon.idgenerator.GeneratorHelp;
import com.froad.recon.importfile.handler.DataDealProcessor;

/**
 * 整理对账数据(前端积分)
 * @author Administrator
 *
 */
public class DataDealProcessorFrontPoint implements DataDealProcessor {

	/**
	 * 整理对账数据
	 */
	@Override
	public Map<String, Object> execute(Map<String, Object> reqMap) {
		Map<String,Object> respMap = new HashMap<String, Object>();
		respMap.put(BusinessConstant.RESULT, BusinessConstant.SUCCESS);
		Object channelNo = reqMap.get("channelNo");//渠道编号
		
		List<Map<String,Object>> neatenList=new ArrayList<Map<String,Object>>();
		
		@SuppressWarnings("unchecked")
		List<Map<String,Object>> dataList = (List<Map<String, Object>>) reqMap.get("dataList");
		for(Map<String,Object> data : dataList){
			if(StringUtils.isNotEmpty( MapUtils.getString(data, "order_no"))){
				data.put("id", GeneratorHelp.generate());//主键
				data.put("chanel_type", BusinessConstant.CHANEL_TYPE.POINTS);//渠道0.积分1.现金
				data.put("front_partner_no", channelNo);//渠道编号
				neatenList.add(data);
			}
		}
		
		
		
		
		
		
		respMap.put("dataList", neatenList);
		return respMap;
	}

}
