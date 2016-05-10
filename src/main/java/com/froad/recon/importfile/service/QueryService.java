package com.froad.recon.importfile.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查询数据Service接口
 * @author Administrator
 *
 */
public interface QueryService {

	/**
	 * 查询退款交易(账单平台数据库)
	 * @param params
	 * @return
	 */
	public List<Map<String,Object>> queryRefoud(Object...params);
	
	/**
	 * 查询退款交易详细信息(账单平台数据库)
	 * @param params
	 * @return
	 */
	public List<Map<String,Object>> queryRefoudInfo(Object...params);
	
	/**
	 * 查询查询账单信息(账单平台数据库)
	 * @param params
	 * @return
	 */
	public List<Map<String,Object>> queryBill(Object...params);
	
	/**
	 * 查询商户交易
	 * @param params
	 * @return
	 */
	public List<Map<String,Object>> queryPartnerTrade(List<String> paraList);
	
	/**
	 * 查询前端积分商城现金交易
	 * @param params
	 * @return
	 */
	public List<Map<String,Object>> queryFrontMallCTrade(Object...params);
	
	/**
	 * 修改积分商城对账数据
	 * @param params
	 * @return
	 */
	public int updateFrontMall(Object...params);
	
	/**
	 * 修改前端退款数据
	 * @param params
	 * @return
	 */
	public int updateFrontMall(String refundSeq,String orderNo);
	/**
	 * 通过前端订单号 查找账单号
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> queryFrontMall(List<String> frontOrderNos);
	
	/**
	 * 通过 积分账单号 查找账单号
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> queryFrontMallBypoint(Object... params);
	
	/**
	 * 查询积分交易记录
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> queryPointsTrade(Map<String, Object> paramsMap);
	
	/**
	 * 查询安徽积分兑换积分交易记录
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> queryAhExchPointsTrade(Map<String, Object> paramsMap);
	
	
	
	/**
	 * 查询退积分记录
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> queryRefundPointsTrade(Map<String, Object> paramsMap);
	
	/**
	 * 查询积分商城前端积分消费交易数据
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> queryO2OBillPointsTrade(Map<String, Object> paramsMap);
	
	
	/**
	 * 查询积分商城前端积分退分交易数据
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> queryO2OBillPointsRefundTrade(Map<String, Object> paramsMap);
	
	
	
	/**
	 * 只查询成功的退款记录
	 * @param paramsMap
	 * @return
	 */
	public List<Map<String, Object>> queryPayRefundsTrade(
			Map<String, Object> paramsMap);
	
	/**
	 * 现金 冲正交易
	 * @param paramsMap
	 * @return
	 */
	public List<Map<String, Object>> queryPayCZsTrade(
			Map<String, Object> paramsMap);
	
	/**现金 话费*/
	public List<Map<String, Object>> queryPaySTrade(
			Map<String, Object> paramsMap);
	/**
	 * 查询积分商城前端现金支付交易数据
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> queryO2OBillPayTrade(Map<String, Object> paramsMap);

	/**
	 * 通过paySeqnos查询对应的账单号
	 */
	public List<Map<String, Object>> queryBillNoByPaySeqnos(
			List<String> paySeqnos);

	
	/**
	 * 查询老商城信息
	 */
	public List<Map<String, Object>> queryMall(
			Map<String,Object> paramsMap);
	
	/**
	 * 查询老商城信息  桂林银行和 前端是银行发起的
	 */
	public List<Map<String, Object>> queryFrontMall(
			Map<String,Object> paramsMap);
	
	/**
	 * 查询积分商城前端现金退分交易数据
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> queryO2OBillPayRefundTrade(Map<String, Object> paramsMap);
	
	
	/**
	 * 验证是否是快捷支付发起的退款， 目前仅有快捷支付的退款可从银行获取退款对账文件，所有只有快捷支付的交易可以对账
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> checkrefundTrade(Map<String, Object> paramsMap);

	/**查询预售现金（正常交易）**/
	public List<Map<String, Object>> queryPresellPayTrade(
			Map<String, Object> paramsMap);

	/**查询预售现金（退款）**/
	public List<Map<String, Object>> queryPresellPayRefundTrade(
			Map<String, Object> refundParamsMap);
	
	
	/**
	 * 查询预售积分支付交易数据
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> queryYushouPointsTrade(Map<String, Object> paramsMap);
	
	/**
	 * 查询预售积分退分交易数据
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> queryYushouPointsRefundTrade(Map<String, Object> paramsMap);
	
	/**
	 * <pre>
	 * 查询机构信息
	 * </pre>
	 *
	 * @param paramsMap
	 * @return
	 */
	public List<Map<String, Object>> queryPointsOrg(
			Map<String, Object> paramsMap) ;
}

