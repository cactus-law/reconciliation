package com.froad.comon;

/**
 * Created by zhou on 2015/4/1.
 * 报文相关常量
 */
public class MessageConstant {
    // ===========报文的key/value===========
    public static String sign = "sign"; // 加签的签名值
    public static String body = "body";// 报文体
    public static String seqNo = "seqNo";// 账单号
    public static String partnerID = "partnerID"; // openApi商户编号
    public static String requestTime = "requestTime";// 查询时间
    public static String content = "content";// 请求参数
    public static String payer = "payer";// 支付人
    public static String respCode = "respCode";//  响应码
    public static String respMsg = "respMsg";//  响应信息
    public static String payBackResult = "payBackResult";//  冲正返回结果
    public static String command = "command";//  命令
    public static String createSeqNo = "createSeqNo";// 创建方流水
    public static String tradeType = "tradeType";// 交易类别
    public static String payStep = "payStep";//  支付步骤
    public static String notifyType = "notifyType";// 通知类型  payFailed,aliPaySuccess,normal
    public static String notifyMsg = "notifyMsg";//  通知类型为normal时需要内容。其他为空
    public static String notifyCommand = "notifyCommand";// 通知命令类型
    public static String notifyResult = "notifyResult";// 通知返回结果
    public static String trade25Query = "trade25Query";// 账单/25交易查询trade4Query
    public static String trade4Query = "trade4Query";// 账单/4交易查询
    public static String reqSeqNo = "reqSeqNo";// 请求流水号  即支付流水号
    public static String payState = "payState";// 支付结果
    public static String responseTime = "responseTime";// 响应时间
    public static String resultCode = "resultCode";// 操作结果
    public static String errMsg = "errMsg";// 错误信息
    public static String depositCode = "depositCode";// 凭据
    public static String payTime = "payTime";// 支付时间
    public static String payQuery = "payQuery";// 请求支付
    public static String confirmPay = "confirmPay";// 请求支付
    public static String paySeqNo = "paySeqNo";// 支付流水号
    public static String payCZState = "payCZState";//冲正状态
    public static String payBack = "payBack";//  冲正命令
    public static String phoneChargeQuery = "phoneChargeQuery";//  话费查询
    public static String withholdingQuery = "withholdingQuery";// 代扣查询
    public static String collectionQuery = "collectionQuery";// 代收查询
}
