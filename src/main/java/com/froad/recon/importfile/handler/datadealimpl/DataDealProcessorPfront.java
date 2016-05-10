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
import com.froad.recon.sys.model.PlatformDetail;

/**
 * 整理社区银行积分对账数据(前端)
 * @author Administrator
 *
 */
public class DataDealProcessorPfront implements DataDealProcessor {

	/**
	 * 整理对账数据
	 */
	@Override
	public Map<String, Object> execute(Map<String, Object> reqMap) {
		Map<String,Object> respMap = new HashMap<String, Object>();
		respMap.put(BusinessConstant.RESULT, BusinessConstant.SUCCESS);
		Object channelNo = reqMap.get("channelNo");//渠道编号
		
		PlatformDetail platformDetail =(PlatformDetail)reqMap.get("platformDetail");
		
		List<Map<String,Object>> neatenList=new ArrayList<Map<String,Object>>();
		
		@SuppressWarnings("unchecked")
		List<Map<String,Object>> dataList = (List<Map<String, Object>>) reqMap.get("dataList");
		for(Map<String,Object> data : dataList){
			data.put("id", GeneratorHelp.generate());//主键
			//对前端系统的退分交易转换交易类别，只记录前端表，不对账  对账从积分系统中获取
			if(StringUtils.isNotEmpty( MapUtils.getString(data, "transfer_type"))){
				String transferType=MapUtils.getString(data, "transfer_type");
				if(BusinessConstant.REFOUD_POINT_LIST.contains(transferType.trim())){
					transferType="0"+transferType;
					data.put("transfer_type",transferType);//交易类别
				}
			}
			if(StringUtils.isNotEmpty( MapUtils.getString(data, "order_no"))){
				data.put("chanel_type",platformDetail.getChanelType());//渠道0.积分1.现金
				data.put("front_partner_no", channelNo);//渠道编号
				neatenList.add(data);
			}
		}
		
		reqMap.put("dataList", neatenList);
		return respMap;
	}

}
