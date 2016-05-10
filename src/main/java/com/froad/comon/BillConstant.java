package com.froad.comon;

import java.io.Serializable;

/**
 * @ClassName: BillConstant
 * @Description: 账单平台常量类
 * @author zhaojianguo
 */
@SuppressWarnings("serial")
public class BillConstant implements Serializable{
	/**
	 @Fields RESPONSE_SUCCESS_CODE : 操作成功
	 */
	public static final String RESPONSE_SUCCESS_CODE = "0";
	
	/**
	 @Fields RESPONSE_FAILURE_CODE : 操作失败
	 */
	public static final String RESPONSE_FAILURE_CODE = "1";
	
	/**
	 @Fields RESPONSE_TIMEOUT_CODE : 银行响应超时
	 */
	public static final String RESPONSE_TIMEOUT_CODE = "2";
	
	/**
	 @Fields RESPONSE_NOTRAN_CODE : 前置无此笔交易记录
	 */
	public static final String RESPONSE_NOTRAN_CODE = "100";
	
	/**
	 * bill 支付状态(0－正在处理、1－已成功、2－已失败)
	 */
	public static final String PAY_STATE_PROCESSING_CODE = "0";//正在处理
	public static final String PAY_STATE_SUCCESS_CODE = "1";//已成功
	public static final String PAY_STATE_FAILURE_CODE = "2";//已失败
	public static final String PAY_STATE_PROCESSING = "正在处理";
	public static final String PAY_STATE_SUCCESS = "已成功";
	public static final String PAY_STATE_FAILURE = "已失败";
	public static final String PAY_STATE_UNKNOWN = "未知";
	
	/**
	 * bill 冲正状态(0－未冲正、1－正在处理、2－已成功、3－已失败)
	 */
	public static final String CZ_STATE_NOTHING = "未冲正";
	public static final String CZ_STATE_PROCESSING = "正在处理";
	public static final String CZ_STATE_SUCCESS = "已成功";
	public static final String CZ_STATE_FAILURE = "已失败";
	
	/**
	 * bill  key
	 */
	public static final String BILL_PAY_STATU = "支付状态";
	public static final String BILL_PAY_CZSTATE = "冲正状态";
	public static final String BILL_PAY_SEQ_NO = "支付流水号";
	public static final String COMMAND_RESPONSE = "回应";
	public static final String OPERATE_RESULT = "操作结果";
	
	/**
	 * 账单状态
	 */
	public static final String BILL_STATE_1_1 = "1初始化";
	public static final String BILL_STATE_1 = "1已创建";
	public static final String BILL_STATE_2 = "2已确认";
	public static final String BILL_STATE_3 = "3已拒绝";
	public static final String BILL_STATE_4 = "4已支付";
	public static final String BILL_STATE_5 = "5已取消";
	public static final String BILL_STATE_6 = "6已退款";
	public static final String BILL_STATE_8 = "7已失败";
	public static final String BILL_STATE_9 = "8异常";// 需要人工退款的账单
	public static final String BILL_STATE_10 = "9已部分退款";// 需要人工退款的账单
	public static final String BILL_STATE_11 = "10退款异常";// 需要人工退款的账单
	
	/**
	 * 账单支付步骤
	 */
	public static final String BILL_CREATE = "账单/1创建";
	public static final String BILL_MODIFY = "账单/2修改";
	public static final String BILL_CANCEL = "账单/3取消";
	public static final String BILL_QUERY = "账单/4查询";
	public static final String BILL_QUERY_STATUS = "账单/4状态查询";
	public static final String BILL_PAY = "账单/5支付确认";
	public static final String BILL_REFUSE = "账单/6支付拒绝";
	public static final String BILL_PAYBACK = "账单/7退款请求";
	public static final String BILL_NOTIFY_CREATE = "账单/8创建通知";
	public static final String BILL_NOTIFY_PAYED = "账单/9已支付通知";
	public static final String BILL_NOTIFY_REFUSED = "账单/10支付拒绝通知";
	public static final String BILL_NOTIFY_FAILED = "账单/11支付失败通知";
	public static final String BILL_NOTIFY_PAYBACKED = "账单/12支付已退款通知";
	public static final String BILL_NOTIFY_CANCEL = "账单/12支付取消通知";
	public static final String BILL_NOTIFY_TIMEOUT = "账单/15超时通知";
	public static final String BILL_NOTIFY_EXCEPTION = "账单/16异常通知";
	public static final String BILL_NOTIFY_NO_CONNECTION = "账单/17创建未连接通知";
	public static final String BILL_PAY_REQUEST= "账单/21请求支付";
	public static final String BILL_REFUND_REQUEST= "账单/22请求退款";
	public static final String BILL_TRADE_CANCEL= "账单/23交易取消";
	public static final String BILL_TRADE_CONFIRM= "账单/24交易确认";
	public static final String BILL_TRADE_QUERY= "账单/25交易查询";
	
	/**
	 * 支付类型
	 */
	public static final String BILL_PAY_TYPE_REQUEST = "请求支付";
	public static final String BILL_PAY_TYPE_AUTH = "授权支付";
	public static final String BILL_PAY_TYPE_PHONE = "话费充值";
	public static final String BILL_PAY_TYPE_OUTSIDE = "外部支付";
	public static final String BILL_PAY_TYPE_WITHHOLDING = "代扣支付";
	public static final String BILL_PAY_TYPE_COLLECTION = "代收支付";
	public static final String BILL_PAY_TYPE_TRANSFER = "转账支付";
	public static final String BILL_PAY_TYPE_IMMEDIATE = "即时到帐";
	public static final String BILL_PAY_TYPE_RETURN = "退款支付";
	public static final String BILL_PAY_TYPE_WITHHOLDING_RETURN = "代扣退款";
	public static final String BILL_PAY_TYPE_COLLECTION_RETURN = "代收退款";
	public static final String BILL_PAY_TYPE_UNIONPAYUPOMP = "银联无卡支付";
	public static final String BILL_PAY_TYPE_UNIONPAYUP = "订单号支付";
	public static final String BILL_PAY_TYPE_MANUL_PAY = "贴膜卡手工支付";
	public static final String BILL_PAY_TYPE_SD_PAY = "SD卡支付";
}
