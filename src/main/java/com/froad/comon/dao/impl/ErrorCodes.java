package com.froad.comon.dao.impl;

/**
 * 异常错误代码常量类 BaseException的errorCode采用该class的常量定义 定义规则:
 * E_产生系统(PAYMENT,USER,MERCHANT,DB)_具体含义标识
 */
public class ErrorCodes {
	/** 系统错误 */
	public static final String SYS_ERROR = "9999";
	/** 重复操作 */
	public static final String E_DOUBLE_OPERATE = "9998";
	
	/** 不支持的操作 */
	public static final String E_NOT_SUPPORT_INTERFACE = "9997";
	
	/** 不应该发生的系统异常 */
	public static final String E_NOT_REACHABLE = "9996";

	/** 由数据访问层产生的异常 */
	public static final String E_DB = "D";

	/** 数据库异常代码定义 */
	/** 数据添加异常 */
	public static final String E_DB_SAVE = E_DB + "001";
	// 文件名称重复
	public static final String E_DB_SAVE_CF = E_DB + "010";

	/** 数据更新异常 */
	public static final String E_DB_UPDATE = E_DB + "002";

	/** 数据删除异常 */
	public static final String E_DB_DEL = E_DB + "003";

	/** 数据查询异常 */
	public static final String E_DB_FIND = E_DB + "004";

	/** 序列号生成错误 */
	public static final String E_DB_SEQ_CREATE = E_DB + "005";

	/** 文件格式错误！ */
	public static final String E_DB_NUMBERFORMATEXCEPTION = E_DB + "006";

	/** 文件IO错误，找不到文件 */
	public static final String E_DB_IOEXCEPTION = E_DB + "007";

	/** 读写XML文件异常 */
	public static final String E_DB_EXCEPTION = E_DB + "008";

	/** 读写session异常 */
	public static final String E_DB_SESSION = E_DB + "009";

	/** 导致事务失败回滚的异常, gaobaowen, 2012.05.18 **/
	public static final String E_DB_ROLLBACK = E_DB + "010";

	/** 由于乐观锁的原因导致更新失败, gaobaowen, 2012.05.18 **/
	public static final String E_DB_OPTIMISTIC_FAILED = E_DB + "011";

	/** DAO 中由于参数错误导致的处理失败, gaobaowen, 2012.05.18 **/
	public static final String E_DB_PARAMETER_ERROR = E_DB + "012";
}
