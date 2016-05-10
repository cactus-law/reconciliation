package com.froad.comon.dao.impl;

import com.froad.comon.exception.BaseException;

/**
 * 数据访问层操作统一抛出的异常.
 * 
 */
public class DBException extends BaseException {

	private static final long serialVersionUID = -5822759870702599001L;

	public DBException(String errorCode, String errorMsg) {
		super(errorCode, errorMsg);
	}

	public DBException(String errorCode, Throwable caused) {
		super(errorCode, caused);
	}

	public DBException(String errorCode, String errorMsg, Throwable caused) {
		super(errorCode, errorMsg, caused);
	}
}
