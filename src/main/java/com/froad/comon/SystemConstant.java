package com.froad.comon;

import java.io.Serializable;

/**
 * 系统常量
 */
public class SystemConstant implements Serializable {
    private static final long serialVersionUID = 3228212812406302983L;
    /**
     * 输出符号
     */
    public static final String SIGN = "=========>";
    /**
     * 本行AS400文件名
     */
    public static final String AS400_FILENAME = "[YYYYMMDD]SEQ29";
    /**
     * 银联文件名
     */
    public static final String UNION_FILENAME = "IND[YYMMDD]01ACOM";
    /**
     * 银联数据文件名
     */
    public static final String UNIONDATA_FILENAME = "1513-EVE-[YYYYMMDD]";
    /**
     * ZPSUM文件名
     */
    public static final String ZPSUM_FILENAME = "INO[YYMMDD]99ZPSUM";

    /**
     * 曲靖ZPSUM文件名
     */
    public static final String ZPSUM_FILENAMEQJ = "INO[YYMMDD]99ZPSUMQJ";

    /**
     * 积分文件名
     */
    public static final String POINT_FILENAME = "03[YYYYMMDD]_S.txt";
    /**
     * 清算初始化
     */
    public static final String MODULE_RUNMODULE = "00";
    /**
     * 收取文件模块_AS400
     */
    public static final String MODULE_GET_FILE_AS400 = "01";
    /**
     * 收取文件模块_积分
     */
    public static final String MODULE_GET_FILE_POINT = "02";
    /**
     * 收取文件模块_银联
     */
    public static final String MODULE_GET_FILE_UNION = "03";
    /**
     * 收取文件模块_银联数据
     */
    public static final String MODULE_GET_FILE_UNIONDATA = "04";
    /**
     * 收取文件模块_ZPSUM
     */
    public static final String MODULE_GET_FILE_ZPSUM = "05";
    /**
     * 收取文件模块_ZPSUMQJ
     */
    public static final String MODULE_GET_FILE_ZPSUMQJ = "06";

    /**
     * 数据导入模块_POSP
     */
    public static final String MODULE_DATA_IMP_POSP = "07";
    /**
     * 数据导入模块_AS400
     */
    public static final String MODULE_DATA_IMP_AS400 = "08";
    /**
     * 数据导入模块_积分
     */
    public static final String MODULE_DATA_IMP_POINT = "09";
    /**
     * 数据导入模块_银联
     */
    public static final String MODULE_DATA_IMP_UNION = "10";
    /**
     * 数据导入模块_银联数据
     */
    public static final String MODULE_DATA_IMP_UNIONDATA = "11";
    /**
     * 数据导入模块_ZPSUM
     */
    public static final String MODULE_DATA_IMP_ZPSUM = "12";

    /**
     * 对账模块_POSP
     */
    public static final String MODULE_REC_POSP = "13";
    /**
     * 对账模块_银联
     */
    public static final String MODULE_REC_UNION = "14";
    /**
     * 对账模块_银联数据
     */
    public static final String MODULE_REC_UNIONDATA = "15";
    /**
     * 对账模块_积分
     */
    public static final String MODULE_REC_POINT = "16";
    /**
     * 对账模块_AS400
     */
    public static final String MODULE_REC_AS400 = "17";
    /**
     * 对账模块_对账结果处理
     */
    public static final String MODULE_REC_RESULTDEAL = "18";
    /**
     * 对账模块_一清
     */
    public static final String MODULE_REC_MAINEXECUTE = "19";
    /**
     * 对账模块_二清
     */
    public static final String MODULE_REC_MAINEXECUTE2 = "20";
    /**
     * 清算模块-清算历史
     */
    public static final String MODULE_REC_TRANSHIS = "21";
    /**
     * 对账模块_交易
     */
    public static final String MODULE_REC_REMITEXCUTE = "22";
    /**
     * 清算模块-交易历史
     */
    public static final String MODULE_REC_TRADHIS = "23";


    /**
     * POSP对账标记
     */
    public static final String SOURCEFLAG_POSP = "0000000001";
    /**
     * 银联数据对账标记
     */
    public static final String SOURCEFLAG_UNIONDATA = "0000000010";
    /**
     * 银联对账标记
     */
    public static final String SOURCEFLAG_UNION = "0000000100";
    /**
     * 400对账标记
     */
    public static final String SOURCEFLAG_AS400 = "0000001000";
    /**
     * 积分对账标记
     */
    public static final String SOURCEFLAG_POINT = "0000010000";
    /**
     * TRADE对账标记
     */
    public static final String SOURCEFLAG_TRADE = "0000100000";
    /**
     * 重复记录
     */
    public static final String SOURCEFLAG_REPEAT = "1000000000";
    /**
     * 非税成功对账
     */
    public static final String SOURCEFLAG_FS_SUCCESS = "0000100100";
    /**
     * 非税差错
     */
    public static final String SOURCEFLAG_FS_FAIL = "0000000100";
    /**
     * 非税重复记录
     */
    public static final String SOURCEFLAG_FS_REPEAT = "1000100100";
    /**
     * 理财成功A
     */
    public static final String SOURCEFLAG_LC_SUCCESSA = "0000101100";
    /**
     * 理财成功B
     */
    public static final String SOURCEFLAG_LC_SUCCESSB = "0000001100";
    /**
     * 理财差错
     */
    public static final String SOURCEFLAG_LC_FAIL = "0000000100";
    /**
     * 理财重上账
     */
    public static final String SOURCEFLAG_LC_RETRY = "0000100100";

    /**
     * 模块状态-未运行
     */
    public static final String MODULESTATE_NOTRUN = "0";
    /**
     * 模块状态-运行中
     */
    public static final String MODULESTATE_RUNNING = "1";
    /**
     * 模块状态-运行完成
     */
    public static final String MODULESTATE_COMPLETE = "2";
    /**
     * 模块状态-异常
     */
    public static final String MODULESTATE_EXCEPTION = "3";
    /**
     * 不允许重复下载
     */
    public static final String REDOWNLOAD_N = "0";
    /**
     * 允许重复下载
     */
    public static final String REDOWNLOAD_Y = "1";

    /**
     * 不执行
     */
    public static final String EXCUTE_N = "0";
    /**
     * 执行
     */
    public static final String EXCUTE_Y = "1";
    /**
     * 非税
     */
    public static final String RCKTYPE_FS = "FS";
    /**
     * 非税改造
     */
    public static final String RCKTYPE_FG = "FG";
    /**
     * 理财
     */
    public static final String RCKTYPE_LC = "LC";
    /**
     * 日切
     */
    public static final String RCKTYPE_RQ = "RQ";
    /**
     * 普通清算
     */
    public static final String RCKTYPE_PT = "PT";
    /**
     * 实时清算
     */
    public static final String RCKTYPE_SS = "SS";
    /**
     * 请求支付对账
     */
    public static final String RCKTYPE_RP = "RP";
    /**
     * 授权支付对账
     */
    public static final String RCKTYPE_AP = "AP";
    /**
     * 转账支付对账
     */
    public static final String RCKTYPE_TP = "TP";
    /**
     * 话费充值对账
     */
    public static final String RCKTYPE_PC = "PC";
    /**
     * 即时支付对账
     */
    public static final String RCKTYPE_IP = "IP";
    /**
     * 下推账单对账
     */
    public static final String RCKTYPE_PP = "PP";
    /**
     * 代收支付/退款对账
     */
    public static final String RCKTYPE_CP = "CP";
    /**
     * 代扣支付/退款对账
     */
    public static final String RCKTYPE_WP = "WP";
    /**
     * 退款对账
     */
    public static final String  RCKTYPE_TK="TK";
    /**
     * 并账
     */
    public static final String RCKTYPE_BZ = "BZ";
    /**
     * 通知openApi
     */
    public static final String RCKTYPE_NO = "NO";
    /**
     * 未运行
     */
    public static final String FILE_STATE_0 = "0";
    /**
     * 正在运行
     */
    public static final String FILE_STATE_1 = "1";
    /**
     * 执行成功
     */
    public static final String FILE_STATE_2 = "2";
    /**
     * 执行失败
     */
    public static final String FILE_STATE_3 = "3";
    /**
     * 流程状态-未运行
     */
    public static final String FLOWSTATE_NOTRUN = "N";
    /**
     * 流程状态-运行中
     */
    public static final String FLOWSTATE_RUNNING = "R";
    /**
     * 流程状态-运行完成
     */
    public static final String FLOWSTATE_COMPLETE = "C";
    /**
     * 流程状态-异常
     */
    public static final String FLOWSTATE_EXCEPTION = "E";
    /**
     * 通用布尔-是
     */
    public static final String BOOLEAN_YES = "Y";
    /**
     * 通用布尔-否
     */
    public static final String BOOLEAN_NO = "N";
    /**
     * 发送状态:未发送
     */
    public static final String SEND_STATE_N = "N";
    /**
     * 发送状态  :发送中
     */
    public static final String SEND_STATE_R = "R";
    /**
     * 发送状态 :发送完毕
     */
    public static final String SEND_STATE_C = "C";
    /**
     * 发送状态  E:异常
     */
    public static final String SEND_STATE_E = "E";
    /**
     * 通用状态：未处理 NOT
     */
    public static final String COMMON_STATE_N = "N";
    /**
     * 通用状态：正在处理 RUNNING
     */
    public static final String COMMON_STATE_R = "R";
    /**
     * 通用状态：处理完成 COMPLETE
     */
    public static final String COMMON_STATE_C = "C";
    /**
     * 通用状态：处理异常 EXCEPTION
     */
    public static final String COMMON_STATE_E = "E";
    /**
     * 实时清算标记
     */
    public static final String ACT_FLAG = "1";

    /**
     * 默认值
     */
    public static final String DEFAULT_X6 = "XXXXXX";
    /**
     * 货币代号： 人民币
     */
    public static final String RCK_CCY_01 = "01";
    /**
     * 微额打包 - 01
     */
    public static final String RCKRECRULE_CALCULATEFLAG_01 = "01";
    /**
     * 分段(分段手续需要设置交易金额上限,下限)-02
     */
    public static final String RCKRECRULE_CALCULATEFLAG_02 = "02";
    /**
     * 一般-03
     */
    public static final String RCKRECRULE_CALCULATEFLAG_03 = "03";
    /**
     * 手续费类型 定值-01
     */
    public static final String RCKRECRULE_FEETYPE_01 = "01";
    /**
     * 手续费类型 比例（费率）-02
     */
    public static final String RCKRECRULE_FEETYPE_02 = "02";
    /**
     * 文件准备状态 OK
     */
    public static final String FILE_OK = "OK";
    /**
     * 文件准备状态 NO
     */
    public static final String FILE_NO = "NO";
    /**
     * 银联交易码 0200 消费
     */
    public static final String TRANSTYPE_0200 = "0200";
    /**
     * 银联交易码 0420 冲正
     */
    public static final String TRANSTYPE_0420 = "0420";

    /**
     * 分期所有交易
     */
    public static final String TRANSTYPE_RSV1_FQ = "FQ";
    
    /**
     * 自动对账
     */
    public static final String RECONTYPE_RECON = "RECON";


}
