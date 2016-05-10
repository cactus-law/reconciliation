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
import com.froad.comon.util.DateUtil;
import com.froad.recon.importfile.handler.DataDealProcessor;
import com.froad.recon.importfile.service.ImpDataService;
import com.froad.recon.importfile.service.QueryService;

/**
 * <pre>
 *  整理积分系统对账数据
 * </pre>
 *
 * @author xueyunlong
 * @create 2015年9月14日 上午10:58:01
 * @modify
 * @since   JDK1.6
 */
public class DataDealProcessorPointO2O implements DataDealProcessor {
	
	final static Logger logger = LoggerFactory.getLogger(DataDealProcessorPointO2O.class);
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
		String channelNo = MapUtils.getString(reqMap, "channelNo");//渠道编号
		String reconDate=MapUtils.getString(reqMap, "transDate");
		Map<String, Object> paramsMap=new HashMap<String, Object>();
		paramsMap.put("org_no", channelNo);
		paramsMap.put("create_time_max", DateUtil.getDateFromStringN(reconDate+"235959", DateUtil.anotherByte14Format));
		paramsMap.put("create_time_min", DateUtil.getDateFromStringN(reconDate+"000000", DateUtil.anotherByte14Format));
		List<Map<String,Object>> dataList = queryService.queryPointsTrade(paramsMap);
		if(null!=dataList && dataList.size()>0){
			//补充map中需要的配置数据
			//分页处理对账数据
			Integer pageSize=AppConstant.PAGE_SIZE_IN;
			int totalPage = (dataList.size()-1)/pageSize+1;
			for(int pageNo=1;pageNo<=totalPage;pageNo++){
				int start = (pageNo-1)*pageSize;
				int end = pageNo*pageSize;
				end = end>dataList.size()?dataList.size():end;
				List<Map<String,Object>> tempList = dataList.subList(start, end);
				handlerPoint(tempList,channelNo,reconDate);
			}
		}
		
		respMap.put("dataList", dataList);
		return respMap;
	}
	
	 
	protected void handlerPoint(List<Map<String,Object>> dataList,String channelNo,String reconDate){
		if(dataList.isEmpty()){
			return;
		}
		//增加公用信息
		for(Map<String,Object> data : dataList){
			data.put("channel_no", channelNo);
			data.put("table", "i_bank_points");
			data.put("recon_date", reconDate);
		 }
		}
	

}
