package com.froad.recon.importfile.handler.datadealimpl;

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
import com.froad.recon.importfile.handler.DataDealProcessor;
import com.froad.recon.importfile.service.ImpDataService;
import com.froad.recon.importfile.service.QueryService;
import com.froad.recon.sys.model.PlatformDetail;

/**
 * <pre>
 *  整理前端安徽积分兑换数据
 * </pre>
 *
 * @author xueyunlong
 * @create 2015年9月14日 上午10:58:01
 * @modify
 * @since   JDK1.6
 */
public class DataDealProcessorAhExch implements DataDealProcessor {
	
	final static Logger logger = LoggerFactory.getLogger(DataDealProcessorAhExch.class);
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
		PlatformDetail platformDetail=(PlatformDetail)reqMap.get("platformDetail");
		String reconDate=MapUtils.getString(reqMap, "transDate");
		Map<String, Object> paramsMap=new HashMap<String, Object>();
		paramsMap.put("business_type_like", "网点积分兑换_ah_mobilepay");
		paramsMap.put("t.create_time_max", DateUtil.getDateFromStringN(reconDate+"235959", DateUtil.anotherByte14Format));
		paramsMap.put("t.create_time_min", DateUtil.getDateFromStringN(reconDate+"000000", DateUtil.anotherByte14Format));
		List<Map<String,Object>> dataList = queryService.queryAhExchPointsTrade(paramsMap);
		if(null!=dataList && dataList.size()>0){
			//补充map中需要的配置数据
				for(Map<String,Object> data : dataList){
					data.put("id", GeneratorHelp.generate());//主键
					data.put("table", "i_front_trade");
					data.put("recon_date", reconDate);
					data.put("chanel_type", BusinessConstant.CHANEL_TYPE.POINTS);
					data.put("transfer_type", "1020");
					data.put("front_partner_no", platformDetail.getChannelNo());
					data.put("create_time", data.get("order_time"));
					data.put("front_platform_name", platformDetail.getPlatformDetailName());
					
			}
				
		}
		
		respMap.put("dataList", dataList);
		return respMap;
	}
	
	 
	

}
