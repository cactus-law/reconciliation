package com.froad.recon.importfile.handler.datadealimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;

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
 * 整理对账数据(预算现金)
 * 
 * @author Administrator
 * 
 */
public class DataDealProcessorPresellPay implements DataDealProcessor {

	@Autowired
	private QueryService queryService;

	@Autowired
	private ImpDataService impDataService;

	@Autowired
	private SdelayService sdelayService;

	/**
	 * 整理对账数据
	 * 
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> execute(Map<String, Object> reqMap)
			throws Exception {
		Map<String, Object> respMap = new HashMap<String, Object>();
		respMap.put(BusinessConstant.RESULT, BusinessConstant.SUCCESS);
		PlatformDetail platformDetail = (PlatformDetail) reqMap
				.get("platformDetail");
		String reconDate = (String) reqMap.get("transDate");
		String channelNo = MapUtils.getString(reqMap, "channelNo");// 渠道编号
		/** 删除延迟对账数据 **/
		Sdelay deleteItem = new Sdelay();
		deleteItem.setReconDate(reconDate);
		deleteItem.setPlatformNo(platformDetail.getPlatformNo());
		deleteItem.setChannelNo(platformDetail.getChannelNo());
		sdelayService.deleteByObj(deleteItem);

		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("p.create_time_max", DateUtil.getDateFromStringN(reconDate+ "235959", DateUtil.anotherByte14Format));
		paramsMap.put("p.create_time_min", DateUtil.getDateFromStringN(reconDate+ "000000", DateUtil.anotherByte14Format));
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> pdataList = queryService.queryPresellPayTrade(paramsMap);
		dataList.addAll(pdataList);
		Map<String, Object> refundParamsMap = new HashMap<String, Object>();
		refundParamsMap.put("p.update_time_max", DateUtil.getDateFromStringN(reconDate + "235959", DateUtil.anotherByte14Format));
		refundParamsMap.put("p.update_time_min", DateUtil.getDateFromStringN(reconDate + "000000", DateUtil.anotherByte14Format));
		List<Map<String, Object>> refundDataList = queryService
				.queryPresellPayRefundTrade(refundParamsMap);

		if (refundDataList != null && refundDataList.size() > 0) {
			// 获取非自动退款的交易，现只有快捷支付为自动退款
			List<Map<String, Object>> removerefundDataList = new ArrayList<Map<String, Object>>();
			for (Map<String, Object> map : refundDataList) {
				String orderNo = (String) map.get("order_no");
				Map<String, Object> _paramsMap = new HashMap<String, Object>();
				_paramsMap.put("r.refund_order_ID", orderNo);
				List<Map<String, Object>> maps = queryService
						.checkrefundTrade(_paramsMap);
				if (null != maps && maps.size() > 0) {
					for (Map<String, Object> map2 : maps) {
						String payType = (String) map2.get("payType");
						if (!"快捷支付".equals(payType)) {
							removerefundDataList.add(map);
						}
					}
				}
			}
			// 删除非快捷支付退款数据
			refundDataList.removeAll(removerefundDataList);
			// 退款入延迟对账表
			sdelayService.refundSdelay(refundDataList, platformDetail,
					reconDate);
			// 退款入前端表 在入redies时只从延迟对账表中获取数据，前端表数据查询展示
			dataList.addAll(refundDataList);
		}

		if (null != dataList && dataList.size() > 0) {
			// 补充map中需要的配置数据
			for (Map<String, Object> data : dataList) {
				data.put("id", GeneratorHelp.generate());// 主键
				data.put("table", platformDetail.getTableName());
				data.put("recon_date", reconDate);
				data.put("chanel_type", "1");
				data.put("front_partner_no", channelNo);
				data.put("front_platform_name",platformDetail.getPlatformDetailName());
				data.put("create_time", new Date());
				// data.put("transfer_type", "2020");
			}
		}
		respMap.put("dataList", dataList);
		return respMap;
	}
}
