package com.froad.recon.importfile.handler.datadealimpl.bill;

import java.util.ArrayList;
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
import com.froad.recon.importfile.service.ImpDataService;
import com.froad.recon.importfile.service.QueryService;
import com.froad.recon.sys.model.PlatformDetail;

/**
 * <pre>
 *  整理现金平台 管理平台    贴膜卡手机银行 话费充值的
 * </pre>
 *
 * @author xueyunlong
 * @create 2015年9月14日 上午10:58:01
 * @modify
 * @since   JDK1.6
 */
public class DataDealProcessorPay implements DataDealProcessor {
	
	final static Logger logger = LoggerFactory.getLogger(DataDealProcessorPay.class);
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
		paramsMap.put("p.pay_time_max", DateUtil.getDateFromStringN(reconDate+"235959", DateUtil.anotherByte14Format));
		paramsMap.put("p.pay_time_min", DateUtil.getDateFromStringN(reconDate+"000000", DateUtil.anotherByte14Format));
		/**获取银行发起的，去掉 行内账单（及桂林银行）  */
		List<Map<String,Object>> dataList = queryService.queryPaySTrade(paramsMap);
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
		if(tempList.size()<1){
			return;
		}
		
		List<String> paySeqNos = new ArrayList<String>();
		//对账数据整理
		for(Map<String,Object> data : tempList){
			paySeqNos.add((String)data.get("order_no"));//老商城订单号
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
			String id=MapUtils.getString(map, "order_no");
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
			}
		}
	}
}
