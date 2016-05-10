package com.froad.recon.importfile.handler.datadealimpl.o2obill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.froad.comon.constant.AppConstant;
import com.froad.comon.constant.BusinessConstant;
import com.froad.comon.idgenerator.GeneratorHelp;
import com.froad.comon.util.DateUtil;
import com.froad.recon.importfile.handler.DataDealProcessor;
import com.froad.recon.importfile.service.ImpDataService;
import com.froad.recon.importfile.service.QueryService;

/**
 * <pre>
 *  整理积分系统对账数据 积分商城前端数据
 * </pre>
 *
 * @author xueyunlong
 * @create 2015年9月14日 上午10:58:01
 * @modify
 * @since   JDK1.6
 */
public class PointsFO2ObillDataDeal implements DataDealProcessor {
	
	final static Logger logger = LoggerFactory.getLogger(PointsFO2ObillDataDeal.class);
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
		paramsMap.put("payment.create_date_max", DateUtil.getDateFromStringN(reconDate+"235959", DateUtil.anotherByte14Format));
		paramsMap.put("payment.create_date_min", DateUtil.getDateFromStringN(reconDate+"000000", DateUtil.anotherByte14Format));
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> pdataList=queryService.queryO2OBillPointsTrade(paramsMap);
		dataList.addAll(pdataList);
		Map<String, Object> refundParamsMap=new HashMap<String, Object>();
		refundParamsMap.put("payment.modify_date_max", DateUtil.getDateFromStringN(reconDate+"235959", DateUtil.anotherByte14Format));
		refundParamsMap.put("payment.modify_date_min", DateUtil.getDateFromStringN(reconDate+"000000", DateUtil.anotherByte14Format));
		List<Map<String,Object>> refundDataList=queryService.queryO2OBillPointsRefundTrade(refundParamsMap);
		if(null!=refundDataList&&refundDataList.size()>0){
			for(Map<String,Object> data : refundDataList){
				Map<String, Object> pointsParamsMap= new HashMap<String, Object>();
				pointsParamsMap.put("object_no", data.get("order_no"));
				//根据商城返回的object_no，查退分交易
				List<Map<String, Object>> maps= queryService.queryPointsTrade(pointsParamsMap);
				if(null!=maps && !maps.isEmpty()){
					for (Map<String, Object> map : maps) {
						data.put("order_no", map.get("order_no"));
					}
				}
			}
			dataList.addAll(refundDataList);
		}
		if(null!=dataList && dataList.size()>0){
			//补充map中需要的配置数据
				for(Map<String,Object> data : dataList){
					data.put("id", GeneratorHelp.generate());//主键
					data.put("table", "i_front_trade");
					data.put("recon_date", reconDate);
					data.put("chanel_type", "0");
					data.put("front_partner_no", "101");
					data.put("create_time", data.get("order_time"));
					data.put("front_platform_name", "积分商城");
					
			}
		}
		
		respMap.put("dataList", dataList);
		Integer pageSize=AppConstant.PAGE_SIZE_IN;
		//分页处理对账数据
		int totalPage = (dataList.size()-1)/pageSize+1;
		for(int pageNo=1;pageNo<=totalPage;pageNo++){
			int start = (pageNo-1)*pageSize;
			int end = pageNo*pageSize;
			end = end>dataList.size()?dataList.size():end;
			List<Map<String,Object>> tempList = dataList.subList(start, end);
			handerData(tempList);
		}
		
		return respMap;
	}

	private void handerData(List<Map<String, Object>> tempList) {
		List<String> paySeqNos = new ArrayList<String>();
		//对账数据整理
		for(Map<String,Object> data : tempList){
			paySeqNos.add((String)data.get("front_order_no"));
		}
		
		List<Map<String,Object>> frontOrderNos=queryService.queryFrontMallCTrade(paySeqNos.toArray());
		/**通过订单号， 查询对应的前端记录*/
		List<String> frontOrderNoList=new ArrayList<String>();
		for (Map<String, Object> map : frontOrderNos) {
			frontOrderNoList.add(MapUtils.getString(map, "front_order_no"));
		}
		
		
		Map<String,Object> paramsMap=new HashMap<String, Object>();
		paramsMap.put("t.SPID_in", paySeqNos);
		/*t.ID,t.SPID,t.billSeqNo,t.CPID,t.status,t.buyer,t.buyerType,t.payTime, p.proname*/
		List<Map<String,Object>> paySeqNoBills=queryService.queryMall(paramsMap);
		
		
		Map<String,Map<String,Object>> billMap=new HashMap<String, Map<String,Object>>();
		for (Map<String, Object> map : paySeqNoBills) {
			billMap.put(MapUtils.getString(map, "SPID"),map);
		}
		
		for (Map<String, Object> map : tempList) {
			String id=MapUtils.getString(map, "front_order_no");
			
			if(!frontOrderNos.contains(id)){
				Map<String,Object> mallMap= billMap.get(id);
				if(mallMap!=null){
					/**通过订单号获取账单号**/
					map.put("virtual_trade_type","1");
					String supplierName= MapUtils.getString(mallMap, "proname");
					String supplierNo="";
					if("欧飞".equals(supplierName)){
						supplierNo="ofcard";//供应商名称
				    }else{
				    	supplierNo=supplierName;//供应商代码
				    }
					map.put("supplier_name",supplierName);
					map.put("supplier_no",supplierNo);
					map.put("partner_no",MapUtils.getString(mallMap, "CPID"));
					map.put("result_code",MapUtils.getString(mallMap, "result_code"));
				}
			}
		}
	}
}
