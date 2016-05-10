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
import com.froad.comon.exception.ServiceException;
import com.froad.comon.idgenerator.GeneratorHelp;
import com.froad.flow.FlowException;
import com.froad.recon.importfile.handler.DataDealProcessor;
import com.froad.recon.importfile.service.QueryService;
import com.froad.recon.reconciliation.model.Sdelay;
import com.froad.recon.reconciliation.service.SdelayService;
import com.froad.recon.sys.model.PlatformDetail;

/**
 * 整理对账数据(前端安徽社区银行)
 * @author Administrator
 *
 */
public class DataDealProcessorAnhui implements DataDealProcessor {
	final static Logger logger = LoggerFactory.getLogger(DataDealProcessorAnhui.class);
	
	private final int pageSize = 800;

	@Autowired
	private QueryService queryService;
	
	@Autowired
	private SdelayService sdelayService;
	
	/**
	 * 整理对账数据
	 * @throws FlowException 
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
		
		@SuppressWarnings("unchecked")
		List<Map<String,Object>> dataList = (List<Map<String, Object>>) reqMap.get("dataList");
		int totalSize = dataList.size();
		int totalPage = (totalSize-1)/pageSize+1;
		
		//分页处理对账数据
		for(int pageNo=1;pageNo<=totalPage;pageNo++){
			int start = (pageNo-1)*pageSize;
			int end = pageNo*pageSize;
			end = end>totalSize?totalSize:end;
			List<Map<String,Object>> tempList = dataList.subList(start, end);
			this.dealData(tempList, platformDetail,reconDate);
		}
		return respMap;
	}
	
	protected void dealData(List<Map<String,Object>> dataList, PlatformDetail platformDetail, String reconDate) throws ServiceException{
/*		//查询退款参数
		List<String> paraList = new ArrayList<String>();
*/		//退款交易数据
		List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
		
		//对账数据整理
		for(Map<String,Object> data : dataList){
			
			data.put("id", GeneratorHelp.generate());//主键
			data.put("chanel_type", platformDetail.getChanelType());//渠道0.积分1.现金
			data.put("front_partner_no", platformDetail.getChannelNo());//渠道编号
			if(BusinessConstant.REFOUD_LIST.contains(data.get("transfer_type"))){//退款交易
//				paraList.add((String)data.get("order_no"));
				mapList.add(data);
				/**退款流水号*/
				data.put("request_no",MapUtils.getString(data,"order_no") );
			}
		}
		
		if(mapList!=null && mapList.size()>0){
			//获取非自动退款的交易，现只有快捷支付为自动退款
			List<Map<String,Object>> removerefundDataList=new ArrayList<Map<String,Object>>();
			for (Map<String, Object> map : mapList) {
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
				mapList.removeAll(removerefundDataList);
			}
			
		
		sdelayService.refundSdelay(mapList, platformDetail, reconDate);
		/*if(paraList.isEmpty()){
			return;
		}
		
		//根据请求流水查询退款交易账单号
		List<Map<String,Object>> refoudList = queryService.queryRefoud(paraList.toArray());
		Map<String,Object> map = new HashMap<String, Object>();
		if(refoudList==null||refoudList.isEmpty()){
			return;
		}
		
		//请求流水和账单号关系映射
		for(Map<String,Object> refoud : refoudList){
			map.put((String)refoud.get("refoud_order_ID"), refoud.get("refundInfoId"));
		}
		
		//退款请求流水转换成账单号
		for(Map<String,Object> data : mapList){
			if(map.containsKey(data.get("order_no"))){
				data.put("order_no", map.get(data.get("order_no")));
			}
		}*/
 	}

}
