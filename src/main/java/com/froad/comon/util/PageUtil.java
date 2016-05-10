package com.froad.comon.util;

import com.froad.comon.constant.AppConstant;

public class PageUtil {
	/***
	 * 根据总条数和页大小 计算出来总页数
	 * @param count
	 * @return
	 */
	public static Integer getTotalPage(Integer count ,Integer pageSize) {
		return count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
	}
}
