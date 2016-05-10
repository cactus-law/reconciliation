package com.froad.recon.importfile.handler.datadealimpl.supplier;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.froad.comon.constant.BusinessConstant;
import com.froad.recon.importfile.handler.DataDealProcessor;
import com.froad.recon.importfile.service.QueryService;

/**
 * 整理对账数据(供应商欧飞)
 * @author Administrator
 *
 */
public class DataDealProcessorOfcard implements DataDealProcessor {
	
	private final int pageSize = 800;
	
	@Autowired
	private QueryService queryService;

	/**
	 * 整理对账数据
	 */
	@Override
	public Map<String, Object> execute(Map<String, Object> reqMap) {
		Map<String,Object> respMap = new HashMap<String, Object>();
		respMap.put(BusinessConstant.RESULT, BusinessConstant.SUCCESS);
		
		@SuppressWarnings("unchecked")
		List<Map<String,Object>> dataList = (List<Map<String, Object>>) reqMap.get("dataList");
		
		//移除支付和退款同时存在的数据 并找出有效数据
		List<Map<String,Object>> rmList = new ArrayList<Map<String,Object>>();
		Map<String,Map<String,Object>> incomeMap = new HashMap<String, Map<String,Object>>();		
		Map<String,Map<String,Object>> outMap = new HashMap<String, Map<String,Object>>();		
		
		for(Map<String,Object> data : dataList){
			String orderNo = MapUtils.getString(data,"order_no","");  
			data.put("business_order_no", orderNo);
			String businesstype = MapUtils.getString(data,"business_type","");  
			if("订单收入".equals(businesstype.trim())){
				incomeMap.put(orderNo, data);
			}else if ("订单支出".equals(businesstype)){
				outMap.put(orderNo, data);
			}else{
				rmList.add(data);
			}
		}
		dataList.removeAll(rmList);
		rmList.clear();
		
		Set<Entry<String,Map<String,Object>>> incomeSet=incomeMap.entrySet();
		for (Entry<String, Map<String,Object>> entry : incomeSet) {
			String orderNo=entry.getKey();
			Map<String,Object> data =entry.getValue();
			if(outMap.containsKey(orderNo)){
				rmList.add(data);
				rmList.add(outMap.get(orderNo));
			}else{
				System.out.println(orderNo);
			}
		}
		dataList.removeAll(rmList);
		rmList.clear();
		 
		//下面是账单号转换
		int totalSize = dataList.size();
		int totalPage = (totalSize-1)/pageSize+1;
		
		//分页处理对账数据
		for(int pageNo=1;pageNo<=totalPage;pageNo++){
			int start = (pageNo-1)*pageSize;
			int end = pageNo*pageSize; 
			end = end>totalSize?totalSize:end;
			List<Map<String,Object>> tempList = dataList.subList(start, end);
			this.dealData(tempList);
		}
		
		return respMap;
	}
	
	/**
	 * 数据处理
	 * 把年年卡供应商文件中的供应商单号转化为定单号 用于统一对账
	 * @param dataList
	 */
	protected void dealData(List<Map<String,Object>> dataList){
		
		if(dataList.isEmpty()){
			return;
		}
		
		//查询供应商订单参数
		List<String> paraList = new ArrayList<String>();
		
		//对账数据整理
		for(Map<String,Object> data : dataList){
			paraList.add((String)data.get("order_no"));
		}
		
		//根据供应商订单号查询供应商账单号
		List<Map<String,Object>> partnerTradeList = queryService.queryPartnerTrade(paraList);
		Map<String,Object> map = new HashMap<String, Object>();
		if(partnerTradeList==null||partnerTradeList.isEmpty()){
			return;
		}
		
		//请求流水和账单号关系映射  partner_no ,order_no
		for(Map<String,Object> pertnerTrade : partnerTradeList){
			map.put((String)pertnerTrade.get("partner_no"), pertnerTrade.get("order_no"));
		}
		
		//退款请求流水转换成账单号
		for(Map<String,Object> data : dataList){
			if(map.containsKey(data.get("order_no"))){
				data.put("order_no", map.get(data.get("order_no")));
			}
			data.put("create_time",new Date());
		}
 	}
}
