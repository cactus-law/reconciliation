package com.froad.comon;

import java.io.Serializable;


/**
 * 清算常量
 */
public class RckConstant implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 字段缺省值
     * DEFAULTVALUE_X:定长字段,DEFAULTVALUE_XX;变长字段
     */
    public static final String DEFAULTVALUE_X = "x";
    public static final String DEFAULTVALUE_XX = "xx";

    /**
     * 清算结果表中必输字段默认值
     */
    public static final String RES_REQUIRE = "0";

    /**
     * 特殊计费表微额打包商户标识
     */
    public static final String MICRO_SHOP = "020";
    public static final String MICRO_SPECOUNTTYPE = "02";
    public static final String MICRO_SPECOUNTLEVEL = "0";


    /**
     * 清算结果表微额打包标识
     */
    public static final String RCKRES_MICRO = "01";

    /**
     * 手续费是否已计算
     * 已计算-Y,未计算-N
     */
    public static final String ISCOMPUTE = "Y";
    public static final String UNCOMPUTE = "N";


    /**
     * 手续费规则类型
     * FEERULE_MICRO-微额打包,FEERULE_SEGMENT-分段,FEERULE_COMMON-一般
     */
    public static final String FEERULE_MICRO = "01";
    public static final String FEERULE_SEGMENT = "02";
    public static final String FEERULE_COMMON = "03";


    /**
     * 手续费类型
     * FEETYPE_CONSTRANT-定值,FEETYPE_RATE-比例
     */
    public static final String FEETYPE_CONSTRANT = "01";
    public static final String FEETYPE_RATE = "02";


    /**
     * 运算符
     * OPERATOR_PLUS:正号,OPERATOR_MINUS:负号
     */
    public static final String OPERATOR_PLUS = "+";
    public static final String OPERATOR_MINUS = "-";

    /**
     * RCK2FLAG_0:商户标识一清商户
     */
    public static final String RCK2FLAG_0 = "0";
    /**
     * RCK2FLAG_1:商户标识二清商户
     */
    public static final String RCK2FLAG_1 = "1";
    /**
     * RCKTYPE_00:一清消费
     */
    public static final String TRAD_RCKTYPE_00 = "00";
    /**
     * RCKTYPE_01:一清退货
     */
    public static final String TRAD_RCKTYPE_01 = "01";
    /**
     * RCKTYPE_02:二清消费
     */
    public static final String TRAD_RCKTYPE_02 = "02";
    /**
     * RCKTYPE_03:二清退货
     */
    public static final String TRAD_RCKTYPE_03 = "03";
    /**
     * RCKTYPE_04:一清一联盟积分
     */
    public static final String TRAD_RCKTYPE_04 = "04";
    /**
     * RCKTYPE_05:二清一联盟积分
     */
    public static final String TRAD_RCKTYPE_05 = "05";
    /**
     * RCKTYPE_06:直联数据划款
     */
    public static final String TRAD_RCKTYPE_06 = "06";
    /**
     * RCKTYPE_07:分期交易划款
     */
    public static final String TRAD_RCKTYPE_07 = "07";
    /**
     * RCKTYPE_08:分期退货划款
     */
    public static final String TRAD_RCKTYPE_08 = "08";
    /**
     * 非税
     */
    public static final String TRAD_RCKTYPE_FS = "FS";
    /**
     * 理财
     */
    public static final String TRAD_RCKTYPE_LC = "LC";
    /**
     * 实时（T+0）
     */
    public static final String TRAD_RCKTYPE_SS = "SS";

    /**
     * 清算划款交易-货币代号(01)
     */
    public static final String TRAD_LOCTNNO = "01";


    /**
     * 清算划款交易-交易类型:
     * B01-1联盟积分-商户暂收到资金池
     * A11-消费清算入账-间联(含借记卡、信用卡、他行卡)
     * A12-一清商户下挂二清商户消费清算入账-间联(含借记卡、信用卡、他行卡)
     * A21-退货清算入账-间联借记卡
     * A22-退货清算入账-间联信用卡
     * A23-退货清算入账-间联他行卡
     * A24-二清账户退货入账
     * A14-消费清算入账-直联
     * A15-消费清算入账-直联(差错入账)
     * A16-二清入账-间联
     * XXX-业务类型缺省值
     * P01 非税收入POS 一次入账（暂收账户）
     * P02 百科收入 POS二次入账（商户）
     * B11 分期消费汇总
     * B22 分期退货汇总
     */
    public static final String TRAD_BUSITYPE_B01 = "B01";
    public static final String TRAD_BUSITYPE_A11 = "A11";
    public static final String TRAD_BUSITYPE_A12 = "A12";
    public static final String TRAD_BUSITYPE_A21 = "A21";
    public static final String TRAD_BUSITYPE_A22 = "A22";
    public static final String TRAD_BUSITYPE_A23 = "A23";
    public static final String TRAD_BUSITYPE_A14 = "A14";
    public static final String TRAD_BUSITYPE_A15 = "A15";
    public static final String TRAD_BUSITYPE_A16 = "A16";
    public static final String TRAD_BUSITYPE_A24 = "A24";
    public static final String TRAD_BUSITYPE_XXX = "XXX";
    public static final String TRAD_BUSITYPE_P01 = "P01";
    public static final String TRAD_BUSITYPE_P02 = "P02";
    public static final String TRAD_BUSITYPE_B11 = "B11";
    public static final String TRAD_BUSITYPE_B22 = "B22";
    /**
     * L01理财入账业务代码
     */
    public static final String TRAD_BUSITYPE_L01 = "L01";


    /**
     * 交易入账交易描述
     * 消费交易资金入账-A11,A14,A15
     * 二清商户清算-A16
     * 一清商户清算-A12
     * 退货-A21,A22,A23
     */
    public static final String TRAD_SUMRY_A11 = "消费交易资金入账";
    public static final String TRAD_SUMRY_A14 = "消费交易资金入账";
    public static final String TRAD_SUMRY_A15 = "消费交易资金入账";
    public static final String TRAD_SUMRY_A16 = "二清商户清算";
    public static final String TRAD_SUMRY_A12 = "一清商户清算";
    public static final String TRAD_SUMRY_A21 = "退货";
    public static final String TRAD_SUMRY_A22 = "退货";
    public static final String TRAD_SUMRY_A23 = "退货";

    /**
     * 交易备注与交易返回描述分隔符
     */
    public static final String TRAD_SPLIT = "|";

    /**
     * 退货交易
     */
    public static final String TRAD_RETURNGOODS_0502 = "0502";
    /**
     * 手工退货
     */
    public static final String TRAD_RETURNGOODS_2002 = "2002";
    /**
     * 二清退货
     */
    public static final String TRAD_RETURNGOODS_2302 = "2302";
    /**
     * IC卡脱机退货
     */
    public static final String TRAD_RETURNGOODS_2802 = "2802";

    /**
     * 清算划款交易-渠道编号
     * dev:03
     * sit:03I
     */
    public static final String TRAD_CHANLNO = "03I";

    /**
     * 清算划款交易-交易接口编号
     */
    public static final String TRAD_INTERFACE = "002001990003151";

    /**
     * 清算划款交易-机构编号
     */
    public static final String TRAD_BRCH = "009901";

    /**
     * 清算划款交易-柜员
     */
    public static final String TRAD_OPER = "999899";


    /**
     * 清算划款交易-请求方系统代码
     */
    public static final String TRAD_REQSYSCODE = "009901";


    /**
     * 清算划款交易-soapenv服务码
     */
    public static final String TRAD_TXCODE = "002001990003151";


    /**
     * 清算划款交易-终端号
     * dev:POSP0001
     * sit:  POSTEL
     */
    public static final String TRAD_TERMNO = "  POSTEL";

    /**
     * 清算划款交易-请求方交易流水号
     */
    public static final String TRAD_REQSEQNO = "03201210301612551351584775620";

    /**
     * 清算划款交易-请求方交易流水号(2012110201)
     */
    public static final String TRAD_VERSIONNO = "2012110201";


    /**
     * 清算划款交易-反馈报文成功标志
     */
    public static final String TRAD_SUCESSFLAG = "AAAAAAA";

    /**
     * 交易超时码,超时描述
     * TRAD_TIMEOUTDESC="交易超时"
     */
    public static final String TRAD_TIMEOUTCODE = "timeout";
    public static final String TRAD_TIMEOUTDESC = "交易超时";


    /**
     * 直联划款交易,净计金额为负时,交易返回码默认值(直联净计负值)
     * TRAD_DIRECTMINUSCODE="QSA0004";
     * TRAD_DIRECTMINUSDESC="直联净计负值未发送";
     */
    public static final String TRAD_DIRECTMINUSCODE = "QSA0004";
    public static final String TRAD_DIRECTMINUSDESC = "直联净计负值未发送";

    /**
     * 清算划款交易-交易无应答标志
     */
    public static final String TRAD_NOANSWER = "noanswer";

    /**
     * 清算划款交易重发-交易重发状态
     * 重发状态(0-未发,1-发送中,2-交易发送已完成)
     */
    public static final String TRADAGAINSTATUS_0 = "0";
    public static final String TRADAGAINSTATUS_1 = "1";
    public static final String TRADAGAINSTATUS_2 = "2";


    /**
     * 清算划款交易重发-交易重发状态
     * soket清算与前段交易返回码:
     * 正常：SUCESS,错误:ERROR
     */
    public static final String TRADAGAINSOKET_SUCESS = "SUCESS";
    public static final String TRADAGAINSOKET_ERROR = "ERROR";


    /**
     * 交易重发-交易状态：
     * 0-空闲,1-繁忙
     */
    public static final String TRADAGAINSTATE_BUSY = "1";
    public static final String TRADAGAINSTATE_FREE = "0";

    /**
     * 清算划款交易-反馈报文标签
     */
    public static final String TRAD_RETURNTARGET = "FaultCode";

    /**
     * 清算划款交易-反馈报文标签(详细信息)
     */
    public static final String TRAD_FAULTTRING = "FaultString";
    public static final String TRAD_TXSTATUS = "TxStatus";

    /**
     * 清算划款交易-ESB服务码
     * 0-正常 1-冲销2-冲正 3-重发
     */
    public static final String TRAD_TXMODE_0 = "0";
    public static final String TRAD_TXMODE_1 = "1";
    public static final String TRAD_TXMODE_2 = "2";
    public static final String TRAD_TXMODE_3 = "3";

    /**
     * 工作日判断交易(3159交易):报文体固定值
     * TRAD_3159_DEFAULT="0"
     */
    public static final String TRAD_3159_DEFAULT = "0";

    /**
     * 工作日判断交易(3159交易),反馈报文标签
     */
    public static final String TRAD_3159_RETURNTARGET = "Flag";


    /**
     * 工作日判断交易(3159交易),反馈报文工作日标志
     * 0-非工作日,1-工作日
     */
    public static final String TRAD_3159_WOKEDATE_0 = "0";
    public static final String TRAD_3159_WOKEDATE_1 = "1";


    /**
     * 商户锁定状态
     */
    public static final String MER_LOCK_STATUS = "5";

    /**
     * 清算结果表-数据来源方：
     * 银联-1,银联数据-2,银行-3,重庆银联-4
     */
    public static final String RES_SOURCEFLAG_1 = "1";
    public static final String RES_SOURCEFLAG_2 = "2";
    public static final String RES_SOURCEFLAG_3 = "3";
    public static final String RES_SOURCEFLAG_4 = "4";

    
    /**
	 * 支付/4查询，支付/6冲正查询，前置返回的： 支付状态，包括3个状态：0－正在处理、1－已成功、2－已失败。
	 * 冲正状态，包括4个状态：0－未冲正、1－正在处理、2－已成功、3－已失败。
	 */
	public static final String PAY_STATE_PROCESSING = "正在处理";
	public static final String PAY_STATE_SUCCESS = "已成功";
	public static final String PAY_STATE_FAILURE = "已失败";
	public static final String PAY_STATE_UNKNOWN = "未知";
	
	
	public static final String PAY_STATE_PROCESSING_CODE = "0";   //正在处理
	public static final String PAY_STATE_SUCCESS_CODE = "1";      //已成功
	public static final String PAY_STATE_FAILURE_CODE = "2";      //已失败
	public static final String PAY_STATE_UNKNOWN_CODE = "99";       //未知

	public static final String CZ_STATE_PROCESSING = "正在处理";
	public static final String CZ_STATE_SUCCESS = "已成功";
	public static final String CZ_STATE_FAILURE = "已失败";
	public static final String CZ_STATE_NOTHING = "未冲正";

}
