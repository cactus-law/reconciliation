package com.froad.recon.importfile.handler.datadealimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.froad.comon.constant.AppConstant;
import com.froad.comon.constant.BusinessConstant;
import com.froad.recon.importfile.handler.DataDealProcessor;
import com.froad.recon.importfile.service.QueryService;

/**
 * 整理对账数据(前端冲正交易处理)
 * @author Administrator
 *
 */
public class DataDealProcessorCZ implements DataDealProcessor {

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
		List<Map<String,Object>> orderOrEmptys=new ArrayList<Map<String,Object>>();
		for(Map<String,Object> data : dataList){
			if("转出".equals(data.get("fund_direction"))){
				data.put("order_no", BusinessConstant.CZ+data.get("order_no"));
			}
			if("冲正".equals(data.get("tradetype"))){
				data.put("order_no", "cz"+data.get("order_no"));
			}
			
			if(StringUtils.isNotEmpty(MapUtils.getString(data, "order_no"))){
				orderOrEmptys.add(data);
			}
			
		}
		
		Integer pageSize=AppConstant.PAGE_SIZE_IN;
		//分页处理对账数据
		int totalPage = (orderOrEmptys.size()-1)/pageSize+1;
		for(int pageNo=1;pageNo<=totalPage;pageNo++){
			int start = (pageNo-1)*pageSize;
			int end = pageNo*pageSize;
			end = end>orderOrEmptys.size()?orderOrEmptys.size():end;
			List<Map<String,Object>> tempList = orderOrEmptys.subList(start, end);
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
		
		List<Map<String,Object>> paySeqNoBills=queryService.queryBillNoByPaySeqnos(paySeqNos);
		
		Map<String,String> billMap=new HashMap<String, String>();
		for (Map<String, Object> map : paySeqNoBills) {
			billMap.put(MapUtils.getString(map, "pay_seq_no"),MapUtils.getString(map, "order_no"));
		}
		
		for (Map<String, Object> map : tempList) {
			String frontOrderNo=MapUtils.getString(map, "front_order_no");
			String orderNo= MapUtils.getString(billMap, frontOrderNo);
			if(StringUtils.isNotEmpty(orderNo)){
				/**通过订单号获取账单号**/
				map.put("order_no",orderNo);
			}else{
				map.put("order_no",frontOrderNo);
			}
		}
	}
}
