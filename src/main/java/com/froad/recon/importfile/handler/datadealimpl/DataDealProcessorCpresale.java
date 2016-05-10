package com.froad.recon.importfile.handler.datadealimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.froad.comon.constant.BusinessConstant;
import com.froad.comon.idgenerator.GeneratorHelp;
import com.froad.recon.importfile.handler.DataDealProcessor;
import com.froad.recon.sys.model.PlatformDetail;

/**
 * 整理对账数据(预售平台)
 * @author Administrator
 *
 */
public class DataDealProcessorCpresale implements DataDealProcessor {

	/**
	 * 整理对账数据
	 */
	@Override
	public Map<String, Object> execute(Map<String, Object> reqMap) {
		Map<String,Object> respMap = new HashMap<String, Object>();
		respMap.put(BusinessConstant.RESULT, BusinessConstant.SUCCESS);
		PlatformDetail platformDetail=(PlatformDetail)reqMap.get("platformDetail");
		Object channelNo = reqMap.get("channelNo");//渠道编号
		
		@SuppressWarnings("unchecked")
		List<Map<String,Object>> dataList = (List<Map<String, Object>>) reqMap.get("dataList");
		List<Map<String,Object>> tempList = new ArrayList<Map<String,Object>>();
		for(Map<String,Object> data : dataList){
			if(data.get("order_no")==null || "".equals(data.get("order_no"))){
				tempList.add(data);
				continue;
			}
			data.put("id", GeneratorHelp.generate());//主键
			data.put("chanel_type", platformDetail.getChanelType());//渠道0.积分1.现金
			data.put("front_partner_no", channelNo);//渠道编号
		}
		dataList.removeAll(tempList);
		
		return respMap;
	}

}
