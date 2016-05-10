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
import com.froad.recon.importfile.handler.DataDealProcessor;
import com.froad.recon.importfile.service.ImpDataService;
import com.froad.recon.importfile.service.QueryService;

/**
 * <pre>
 *  整理积分系统对账数据 boss退分
 * </pre>
 *
 * @author xueyunlong
 * @create 2015年9月14日 上午10:58:01
 * @modify
 * @since   JDK1.6
 */
public class DataDealProcessorBossRefund implements DataDealProcessor {
	
	final static Logger logger = LoggerFactory.getLogger(DataDealProcessorBossRefund.class);
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
//		paramsMap.put("object_desc", "手机银行联盟BOSS退分");
		paramsMap.put("t.create_time_max", DateUtil.getDateFromStringN(reconDate+"235959", DateUtil.anotherByte14Format));
		paramsMap.put("t.create_time_min", DateUtil.getDateFromStringN(reconDate+"000000", DateUtil.anotherByte14Format));
		List<Map<String,Object>> dataList = queryService.queryRefundPointsTrade(paramsMap);
		if(null!=dataList && dataList.size()>0){
			List<Map<String,Object>> dataListRemove=new ArrayList<Map<String,Object>>();
			//补充map中需要的配置数据
				for(Map<String,Object> data : dataList){
					//银行转联盟退分时，会生成两条交易记录，一条银行、一条联盟的，在此过滤联盟交易
					if("009".equals(data.get("status"))&&"100010002".equals(data.get("org_no"))){
						dataListRemove.add(data);
						continue;
					}
					data.put("id", GeneratorHelp.generate());//主键
					data.put("table", "i_front_trade");
					data.put("recon_date", reconDate);
					data.put("chanel_type", "0");
					if("100010002".equals(data.get("org_no"))){
						//联盟退分
					data.put("transfer_type", "1140");
					}else{
						//银行退分
						data.put("transfer_type", "1040");
					}
					data.put("front_partner_no", "103");
					data.put("create_time", data.get("order_time"));
					data.put("front_platform_name", "交易退分");
					
			}
				dataList.removeAll(dataListRemove);
				
				
		}
		
		respMap.put("dataList", dataList);
		return respMap;
	}
	
	 
	

}
