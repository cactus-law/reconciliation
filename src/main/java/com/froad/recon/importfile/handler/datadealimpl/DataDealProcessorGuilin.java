package com.froad.recon.importfile.handler.datadealimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.froad.comon.constant.AppConstant;
import com.froad.comon.constant.BusinessConstant;
import com.froad.recon.importfile.handler.DataDealProcessor;
import com.froad.recon.importfile.service.QueryService;
import com.froad.recon.sys.model.PlatformDetail;

/**
 * 桂林银行
 * @author Administrator
 *
 */
public class DataDealProcessorGuilin implements DataDealProcessor {

	@Autowired
	private QueryService queryService;
	/**
	 * 整理对账数据
	 */
	@Override
	public Map<String, Object> execute(Map<String, Object> reqMap) {
		Map<String,Object> respMap = new HashMap<String, Object>();
		respMap.put(BusinessConstant.RESULT, BusinessConstant.SUCCESS);
		
		PlatformDetail platformDetail=(PlatformDetail)reqMap.get("platformDetail");
		String reconDate=(String)reqMap.get("transDate");
		
		@SuppressWarnings("unchecked")
		List<Map<String,Object>> dataList = (List<Map<String, Object>>) reqMap.get("dataList");

		Integer pageSize=AppConstant.PAGE_SIZE_IN;
		int totalSize = dataList.size();
		int totalPage = (totalSize-1)/pageSize+1;
		
		//分页处理对账数据
		for(int pageNo=1;pageNo<=totalPage;pageNo++){
			int start = (pageNo-1)*pageSize;
			int end = pageNo*pageSize;
			end = end>totalSize?totalSize:end;
			List<Map<String,Object>> tempList = dataList.subList(start, end);
			this.handerData(tempList, platformDetail,reconDate);
		}
		return respMap;
	}

	/**数据整理*/
	private void handerData(List<Map<String, Object>> tempList,
			PlatformDetail platformDetail, String reconDate) {
	
		List<String> paySeqNos=new ArrayList<String>();
		for (Map<String, Object> map : tempList) {
			
		    String channelNo=MapUtils.getString(map, "channel_no");
		    if("mall".equals(channelNo)){
		    	paySeqNos.add(MapUtils.getString(map, "front_order_no"));
		    }
		}
		if(paySeqNos.size()<1){
			return;
		}
		List<Map<String,Object>> paySeqNoBills=queryService.queryBillNoByPaySeqnos(paySeqNos);

		Map<String,String> billMap=new HashMap<String, String>();
		for (Map<String, Object> map : paySeqNoBills) {
			billMap.put(MapUtils.getString(map, "pay_seq_no"),MapUtils.getString(map, "order_no"));
		}
		
		for (Map<String, Object> map : tempList) {
			String channelNo=MapUtils.getString(map, "channel_no");
		    if("mall".equals(channelNo)){
		    	String frontOrderNo=MapUtils.getString(map, "front_order_no");
				if(billMap.containsKey(frontOrderNo)){
					/**通过订单号获取账单号**/
					map.put("order_no",MapUtils.getString(billMap, frontOrderNo));
				}else{
					map.put("order_no",frontOrderNo);
				}
		    }
		}
	}

}
