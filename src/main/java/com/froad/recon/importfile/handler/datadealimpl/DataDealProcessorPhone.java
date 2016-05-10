package com.froad.recon.importfile.handler.datadealimpl;

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

import com.froad.comon.constant.BusinessConstant;
import com.froad.comon.idgenerator.GeneratorHelp;
import com.froad.recon.importfile.handler.DataDealProcessor;
import com.froad.recon.importfile.service.ImpDataService;
import com.froad.recon.importfile.service.QueryService;
import com.froad.recon.sys.model.PlatformDetail;

/**
 * 整理对账数据(话费交易)
 * @author Administrator
 *
 */
public class DataDealProcessorPhone implements DataDealProcessor {
	
	final static Logger logger = LoggerFactory.getLogger(DataDealProcessorPhone.class);

	private final int pageSize = 800;
	
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
		String channelNo = (String)reqMap.get("channelNo");//渠道编号
		PlatformDetail platformDetail =(PlatformDetail)reqMap.get("platformDetail");//渠道编号
		
		@SuppressWarnings("unchecked")
		List<Map<String,Object>> dataList = (List<Map<String, Object>>) reqMap.get("dataList");
		//积分商城o2obill
		List<Map<String,Object>> o2obillList = new ArrayList<Map<String,Object>>();
		//非o2obill
		List<Map<String,Object>> uno2obillList = new ArrayList<Map<String,Object>>();
		for(Map<String,Object> data : dataList){
			
			String saleChannel = (String) data.get("sale_channel");
			saleChannel = saleChannel!=null ? saleChannel:"";
			if(saleChannel.endsWith("o2obill")){//积分商城o2obill
				o2obillList.add(data);
			}else{
				uno2obillList.add(data);
			}
			
		}
		
		//分页处理对账数据
		int totalPage = (o2obillList.size()-1)/pageSize+1;
		for(int pageNo=1;pageNo<=totalPage;pageNo++){
			int start = (pageNo-1)*pageSize;
			int end = pageNo*pageSize;
			end = end>o2obillList.size()?o2obillList.size():end;
			List<Map<String,Object>> tempList = o2obillList.subList(start, end);
			deelO2obillData(tempList);
		}
		
		//非o2obill
		List<Map<String,Object>> frontList = this.deelUno2obillData(uno2obillList, channelNo);
		impDataService.addList(frontList,platformDetail);
	
		return respMap;
	}
	
	/**
	 * 修改前端数据
	 * @param dataList
	 */
	protected void deelO2obillData(List<Map<String,Object>> dataList){
		
		int ret = -1;
		int count  = 0;
		List<String> frontOrderNos = new ArrayList<String>();
		for(Map<String,Object> data : dataList){
			String  supplierNo;
			String  resultCode;
			if("欧飞".equals(data.get("supplier_name"))){
				supplierNo="ofcard";//供应商名称
		    }else{
		    	supplierNo=data.get("supplier_name").toString();//供应商代码
		    }
			if("已发货".equals(data.get("recharge_status"))){
				resultCode="0000";
			}else{
				resultCode="9999";
			}
			Object[] params = {"1",  
					supplierNo,
					resultCode,
					data.get("supplier_name"),
					data.get("partner_trade_no"),
					BusinessConstant.CHANEL_TYPE.CASH,
			};
			ret = queryService.updateFrontMall(params);
			count += ret;
			frontOrderNos.add(MapUtils.getString(data, "partner_trade_no"));
		}
		  
		/**获取账单号*/
		List<Map<String,Object>> billList = queryService.queryFrontMall(frontOrderNos);
		Map<String,String> frontOrderNoMap=new HashMap<String, String>();
		for (Map<String, Object> map : billList) {
			System.out.println(MapUtils.getString(map, "front_order_no")+"============="+ MapUtils.getString(map, "order_no"));
			frontOrderNoMap.put(MapUtils.getString(map, "front_order_no"), MapUtils.getString(map, "order_no"));
		}
		for(Map<String,Object> data : dataList){
			String orderNo=MapUtils.getString(frontOrderNoMap, data.get("partner_trade_no"));
			if(StringUtils.isNotEmpty(orderNo)){
				data.put("order_no", orderNo);
			}else{
				data.put("order_no", MapUtils.getString(data, "supplier_order_no"));
			}
		}
		logger.info("前端积分商城话费更新记录.[count={}]", count);
		
	}
	
	/**
	 * 添加前端数据
	 * @param dataList
	 */
	protected List<Map<String,Object>> deelUno2obillData(List<Map<String,Object>> dataList, Object channelNo){

		//话费交易中提取前端交易数据
		List<Map<String,Object>> frontList = new ArrayList<Map<String,Object>>();
		for(Map<String,Object> data : dataList){
			data.put("order_no",  data.get("froad_order_no"));
			
			Map<String,Object> frontMap = new HashMap<String, Object>();
			frontMap.put("table", "i_front_trade");//表名
			frontMap.put("id", GeneratorHelp.generate());//主键
			frontMap.put("chanel_type", BusinessConstant.CHANEL_TYPE.CASH);//渠道0.积分1.现金
			frontMap.put("front_partner_no", channelNo);//渠道编号
			
			frontMap.put("order_no", data.get("froad_order_no"));//订单号 方付通账单号
			frontMap.put("recon_date", data.get("recon_date"));//对账日期
			frontMap.put("supplier_name", data.get("supplier_name"));//供应商名称
		    if("欧飞".equals( data.get("supplier_name"))){
		    	frontMap.put("supplier_no","ofcard");//供应商名称
		    }else{
		    	frontMap.put("supplier_no", data.get("supplier_name"));//供应商代码
		    }
		    
			frontMap.put("bank_name", data.get("bank"));//银行名称
//			frontMap.put("partner_trade_no", data.get("partner_trade_no"));
			frontMap.put("front_order_no", data.get("order_no"));//话费交易流水号
			
			frontMap.put("virtual_trade_type", "1");//话费交易
			frontMap.put("front_platform_name", "话费交易各前端");//定义前端名称
			frontMap.put("transfer_type","2020");//现金话费
			frontMap.put("create_time",new Date());
			if("已发货".equals(data.get("recharge_status"))){
				frontMap.put("result_code", "0000");
			}else{
				frontMap.put("result_code", "9999");
			}
			
			frontList.add(frontMap);
		}
		
		//到账单平台数据库中补全部分信息
		this.dealBillData(frontList);
		
		return frontList;
	}
	
	/**
	 * 处理账单平台查询到的数据
	 * @param dataList
	 */
	@SuppressWarnings("unchecked")
	protected void dealBillData(List<Map<String,Object>> dataList){
		
		if(dataList.isEmpty()){
			return;
		}
		
		//查询账单参数
		List<String> paraList = new ArrayList<String>();
		
		//对账数据整理
		for(Map<String,Object> data : dataList){
			paraList.add((String)data.get("order_no"));
		}
		
		//根据账单号查询账单信息
		List<Map<String,Object>> billList = queryService.queryBill(paraList.toArray());
		Map<String,Object> map = new HashMap<String, Object>();
		if(billList==null||billList.isEmpty()){
			return;
		}
		
		//请账单号和账单对象映射
		for(Map<String,Object> bill : billList){
			map.put((String)bill.get("seq_no"), bill);
		}
		
		//退款请求流水转换成账单号
		for(Map<String,Object> data : dataList){
			if(map.containsKey(data.get("order_no"))){
				Map<String,Object> billMap = (Map<String, Object>) map.get(data.get("order_no"));
				data.put("bank_group", billMap.get("bankGroup"));//银行组号
				data.put("trade_money", billMap.get("money"));//金额
				data.put("order_time", billMap.get("create_time"));//时间
			}
			
			//删除流水号
//			data.remove("partner_trade_no");
		}
 	}

}
