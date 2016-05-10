package com.froad.recon.importfile.handler.datadealimpl.o2obill;

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
import com.froad.comon.idgenerator.GeneratorHelp;
import com.froad.comon.util.DateUtil;
import com.froad.recon.importfile.handler.DataDealProcessor;
import com.froad.recon.importfile.service.ImpDataService;
import com.froad.recon.importfile.service.QueryService;
import com.froad.recon.reconciliation.model.Sdelay;
import com.froad.recon.reconciliation.service.SdelayService;
import com.froad.recon.sys.model.PlatformDetail;

/**
 * 整理对账数据(积分商城前端)
 * @author Administrator
 *
 */
public class DataDealProcessorCfront implements DataDealProcessor {

	
	@Autowired
	private QueryService queryService;
	
	@Autowired
	private ImpDataService impDataService;
	
	@Autowired
	private SdelayService sdelayService;
	
	/**
	 * 整理对账数据
	 * @throws Exception 
	 */
	@Override
	public Map<String, Object> execute(Map<String, Object> reqMap) throws Exception {
		
		Map<String,Object> respMap = new HashMap<String, Object>();
		respMap.put(BusinessConstant.RESULT, BusinessConstant.SUCCESS);
		PlatformDetail platformDetail=(PlatformDetail)reqMap.get("platformDetail");
		String reconDate=(String)reqMap.get("transDate");
		/**删除延迟对账数据**/
		Sdelay deleteItem=new Sdelay();
		deleteItem.setReconDate(reconDate);
		deleteItem.setPlatformNo(platformDetail.getPlatformNo());
		deleteItem.setChannelNo(platformDetail.getChannelNo());
		sdelayService.deleteByObj(deleteItem);
		
		Map<String, Object> paramsMap=new HashMap<String, Object>();
		paramsMap.put("payment.modify_date_max", DateUtil.getDateFromStringN(reconDate+"235959", DateUtil.anotherByte14Format));
		paramsMap.put("payment.modify_date_min", DateUtil.getDateFromStringN(reconDate+"000000", DateUtil.anotherByte14Format));
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> pdataList=queryService.queryO2OBillPayTrade(paramsMap);
		dataList.addAll(pdataList);
		Map<String, Object> refundParamsMap=new HashMap<String, Object>();
		refundParamsMap.put("refunds.modify_date_max", DateUtil.getDateFromStringN(reconDate+"235959", DateUtil.anotherByte14Format));
		refundParamsMap.put("refunds.modify_date_min", DateUtil.getDateFromStringN(reconDate+"000000", DateUtil.anotherByte14Format));
		List<Map<String,Object>> refundDataList=queryService.queryO2OBillPayRefundTrade(refundParamsMap);
		if(refundDataList!=null && refundDataList.size()>0){
			//获取非自动退款的交易，现只有快捷支付为自动退款
			List<Map<String,Object>> removerefundDataList=new ArrayList<Map<String,Object>>();
			for (Map<String, Object> map : refundDataList) {
				String orderNo=(String)map.get("order_no");
				Map<String, Object> _paramsMap=new HashMap<String, Object>();
				_paramsMap.put("r.refund_order_ID", orderNo);
				List<Map<String,Object>> maps= queryService.checkrefundTrade(_paramsMap);
				if(null!=maps&&maps.size()>0){
					for (Map<String, Object> map2 : maps) {
						String payType=(String) map2.get("payType");
						if(!"快捷支付".equals(payType)){
							removerefundDataList.add(map);
						}
					}
				}
			}
			//删除非快捷支付退款数据
			refundDataList.removeAll(removerefundDataList);
			//退款入延迟对账表
			sdelayService.refundSdelay(refundDataList, platformDetail, reconDate);
			//退款入前端表   在入redies时只从延迟对账表中获取数据，前端表数据查询展示
			dataList.addAll(refundDataList);
		}
		
		if(null!=dataList && dataList.size()>0){
			//补充map中需要的配置数据
			for(Map<String,Object> data : dataList){
				data.put("id", GeneratorHelp.generate());//主键
				data.put("table", "i_front_trade");
				data.put("recon_date", reconDate);
				data.put("chanel_type", platformDetail.getChanelType());
				data.put("front_partner_no", platformDetail.getChannelNo());
				data.put("create_time", new Date());
				data.put("front_platform_name", platformDetail.getPlatformDetailName());
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
