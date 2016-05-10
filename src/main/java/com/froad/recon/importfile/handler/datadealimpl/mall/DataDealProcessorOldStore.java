package com.froad.recon.importfile.handler.datadealimpl.mall;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.froad.comon.constant.AppConstant;
import com.froad.comon.constant.BusinessConstant;
import com.froad.comon.idgenerator.GeneratorHelp;
import com.froad.comon.util.DateUtil;
import com.froad.recon.importfile.handler.DataDealProcessor;
import com.froad.recon.importfile.handler.datadealimpl.bill.DataDealProcessorPay;
import com.froad.recon.importfile.service.ImpDataService;
import com.froad.recon.importfile.service.QueryService;
import com.froad.recon.sys.model.PlatformDetail;

/**
 * 整理对账数据( 桂林 手机)
 * @author Administrator
 *
 */
public class DataDealProcessorOldStore implements DataDealProcessor {

	final static Logger logger = LoggerFactory.getLogger(DataDealProcessorOldStore.class);
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
		PlatformDetail platformDetail=(PlatformDetail)reqMap.get("platformDetail");
		
		Map<String, Object> paramsMap=new HashMap<String, Object>();
		paramsMap.put("t.bargainTime_max", DateUtil.getNewFormatFromOldFormat(reconDate, DateUtil.standardFormat, DateUtil.byte10Format)+" 23:59:59");
		paramsMap.put("t.bargainTime_min", DateUtil.getNewFormatFromOldFormat(reconDate, DateUtil.standardFormat, DateUtil.byte10Format)+" 00:00:00");
		paramsMap.put("t.buyerType", "user");
		
		List<Map<String,Object>> dataList=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> dataListUser = queryService.queryFrontMall(paramsMap);
		paramsMap.remove("t.buyerType");
		paramsMap.put("t.buyer", "guilinbank");
		List<Map<String,Object>> dataListguilin = queryService.queryFrontMall(paramsMap);
		dataList.addAll(dataListUser);
		dataList.addAll(dataListguilin);
		if(null!=dataList && dataList.size()>0){
			//补充map中需要的配置数据
			for(Map<String,Object> data : dataList){
				data.put("id", GeneratorHelp.generate());//主键
				data.put("table", platformDetail.getTableName());
				data.put("recon_date", reconDate);
				data.put("chanel_type", "1");
				data.put("front_partner_no", channelNo);
				data.put("front_platform_name", platformDetail.getPlatformDetailName());
				data.put("transfer_type", "2020");
				
				String supplierName=MapUtils.getString(data, "supplier_name");
				if("欧飞".equals(supplierName)||"007ka".equals(supplierName)){
					String supplierNo="";
					if("欧飞".equals(supplierName)){
						supplierNo="ofcard";//供应商名称
				    }else{
				    	supplierNo=supplierName;//供应商代码
				    }
					data.put("supplier_no",supplierNo);
					data.put("virtual_trade_type","1");
				}
			}
		}
		respMap.put("dataList", dataList);
		return respMap;
	}
}
