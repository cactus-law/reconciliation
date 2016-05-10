package com.froad.recon.importfile.handler.datadealimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.froad.comon.constant.BusinessConstant;
import com.froad.comon.idgenerator.GeneratorHelp;
import com.froad.comon.util.DateUtil;
import com.froad.comon.util.StringUtil;
import com.froad.recon.importfile.handler.DataDealProcessor;
import com.froad.recon.importfile.service.ImpDataService;
import com.froad.recon.importfile.service.QueryService;

/**
 * <pre>
 *  整理积分系统对账数据 积分预售前端数据
 * </pre>
 *
 * @author xueyunlong
 * @create 2015年9月14日 上午10:58:01
 * @modify
 * @since   JDK1.6
 */
public class PointsFYUSHOUDataDeal implements DataDealProcessor {
	
	final static Logger logger = LoggerFactory.getLogger(PointsFYUSHOUDataDeal.class);
	@Autowired
	private QueryService queryService;
	
	@Autowired
	private ImpDataService impDataService;
	
	
	/**
	 * 整理对账数据
	 * @throws Exception 
	 */
	@Override
	public Map<String, Object> execute(Map<String, Object> reqMap) throws Exception {
		Map<String,Object> respMap = new HashMap<String, Object>();
		respMap.put(BusinessConstant.RESULT, BusinessConstant.SUCCESS);
		String reconDate=MapUtils.getString(reqMap, "transDate");
		Map<String, Object> paramsMap=new HashMap<String, Object>();
		paramsMap.put("p.create_time_min", DateUtil.getDateFromStringN(reconDate+"000000", DateUtil.anotherByte14Format));
		paramsMap.put("p.create_time_max", DateUtil.getDateFromStringN(reconDate+"235959", DateUtil.anotherByte14Format));
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> pdataList=queryService.queryYushouPointsTrade(paramsMap);
		dataList.addAll(pdataList);
		Map<String, Object> refundParamsMap=new HashMap<String, Object>();
		refundParamsMap.put("p.update_time_min", DateUtil.getDateFromStringN(reconDate+"000000", DateUtil.anotherByte14Format));
		refundParamsMap.put("p.update_time_max", DateUtil.getDateFromStringN(reconDate+"235959", DateUtil.anotherByte14Format));
		List<Map<String,Object>> refundDataList=queryService.queryYushouPointsRefundTrade(refundParamsMap);
		dataList.addAll(refundDataList);
		if(null!=dataList&&dataList.size()>0){
			for(Map<String,Object> data : dataList){
				if(null!=data.get("order_no")){
				Map<String, Object> pointsParamsMap= new HashMap<String, Object>();
				pointsParamsMap.put("id", data.get("order_no"));
				//补充积分机构、银行组号、银行名称相关数据
				List<Map<String, Object>> maps= queryService.queryPointsTrade(pointsParamsMap);
				if(null!=maps && !maps.isEmpty()){
					for (Map<String, Object> map : maps) {
						data.put("point_org", map.get("channel_no"));
						Map<String, Object> orgParamsMap= new HashMap<String, Object>();
						orgParamsMap.put("org_no", map.get("channel_no"));
						List<Map<String, Object>> orgMaps= queryService.queryPointsOrg(orgParamsMap);
						data.put("point_bank_group", orgMaps.get(0).get("bankGroup"));
						data.put("bank_name", orgMaps.get(0).get("org_name"));
					}
				}
				}
				//预售当result_code为空时，也代表成功
				if(null==data.get("result_code")||"".equals(data.get("result_code"))){
					data.put("result_code", "0000");
				}
				//补充map中需要的配置数据
				data.put("id", GeneratorHelp.generate());//主键
				data.put("table", "i_front_trade");
				data.put("recon_date", reconDate);
				data.put("chanel_type", "0");
				data.put("front_partner_no", "108");
				data.put("create_time", data.get("order_time"));
			}
		}
		
		respMap.put("dataList", dataList);
		return respMap;
	}
	
	 
	

}
