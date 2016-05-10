package com.froad.recon.importfile.handler.datadealimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.froad.comon.constant.AppConstant;
import com.froad.comon.constant.BusinessConstant;
import com.froad.recon.importfile.handler.DataDealProcessor;
import com.froad.recon.importfile.service.ImpDataService;
import com.froad.recon.importfile.service.QueryService;

/**
 * 整理对账数据(各个银行 积分文件的整理)
 * @author Administrator
 *
 */
public class DataDealProcessorPoint implements DataDealProcessor {
	
	final static Logger logger = LoggerFactory.getLogger(DataDealProcessorPoint.class);
	@Autowired
	private QueryService queryService;
	
	@Autowired
	private ImpDataService impDataService;
	
	/**
	 * 整理对账数据
	 */
	@Override
	public Map<String, Object> execute(Map<String, Object> reqMap) {
		Map<String,Object> respMap = new HashMap<String, Object>();
		respMap.put(BusinessConstant.RESULT, BusinessConstant.SUCCESS);
		String channelNo = MapUtils.getString(reqMap, "channelNo");//渠道编号
		
		@SuppressWarnings("unchecked")
		List<Map<String,Object>> dataList = (List<Map<String, Object>>) reqMap.get("dataList");
	
		//分页处理对账数据
		Integer pageSize=AppConstant.PAGE_SIZE_IN;
		int totalPage = (dataList.size()-1)/pageSize+1;
		for(int pageNo=1;pageNo<=totalPage;pageNo++){
			int start = (pageNo-1)*pageSize;
			int end = pageNo*pageSize;
			end = end>dataList.size()?dataList.size():end;
			List<Map<String,Object>> tempList = dataList.subList(start, end);
			handlerPoint(tempList,channelNo);
		}
		return respMap;
	}
	
	 
	protected void handlerPoint(List<Map<String,Object>> dataList,String channelNo){
		if(dataList.isEmpty()){
			return;
		}
 	}

}
