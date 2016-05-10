package com.froad.recon.importfile.dao;

import java.util.List;
import java.util.Map;

/**
 * 查询数据DAO接口
 * @author Administrator
 *
 */
public interface QueryDao {

	/**
	 * 查询
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<Map<String,Object>> queryRefoud(final String sql, final Object...params);
	
	
	/**
	 * 修改
	 * @param sql
	 * @param params
	 * @return
	 */
	public int executeSql(final String sql, final Object...params);
}
