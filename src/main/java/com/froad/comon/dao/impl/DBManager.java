package com.froad.comon.dao.impl;

import java.util.List;

/**
 * DB层访问基类接口.
 */
public interface DBManager<T extends Object> {
	/**
	 * 清空session 
	 */
	public void clear();
	
	/**
	 * 刷新session
	 */
	public void flush();
	
	/**
	 * 数据插入
	 * @param obj
	 * @return
	 * @throws DBException
	 */
	public Object save(T obj) throws DBException;

	/**
	 * 数据更新
	 * @param obj
	 * @throws DBException
	 */
	public void update(T obj) throws DBException;

	/**
	 * 数据删除
	 * @param obj
	 * @throws DBException
	 */
	public void delete(T obj) throws DBException;

	/**
	 * 通用查找功能
	 * @param hql
	 *            hql语句
	 * @param params
	 *            参数
	 * @return
	 * @throws DBException
	 */
	public List<T> findByHql(String hql, Object[] params);
	
	/**
	 * 根据sql语句查找
	 * @param sql
	 * @return
	 */
	public Object findBySql(String sql);

	/**
	 * 通用查找功能
	 * @param sql
	 *            sql语句
	 * @param params
	 *            参数
	 * @return
	 * @throws DBException
	 */
	public List<T> findBySql(String sql, Object[] params);
	
	/**
	 * 通用的更新方法
	 * 
	 * @param hql
	 * @param params
	 * @throws DBException
	 */
	public void updateByHql(String hql, Object[] params) throws DBException;
	
	
	
	/**
	 * 通用的更新方法
	 * 
	 * @param sql
	 * @param params
	 * @throws DBException
	 */
	public Object updateDataBySql(String sql, Object[] params) throws DBException;
	
	

	/**
	 * 通用查找功能,查找一个对象
	 * 
	 * @param hql
	 *            hql语句
	 * @param params
	 *            参数
	 * @return
	 * @throws DBException
	 */
	public T findUniqueObjectByHql(final String hql, final Object[] params)
			throws DBException;

	/**
	 * 分页查找功能
	 * 
	 * @param hql
	 *            查找语句
	 * @param params
	 *            查找参数
	 * @param page
	 *            页号
	 * @param pageSize
	 *            页大小
	 * @return
	 * @throws DBException
	 */
	public List<T> findByPaged(String hql, Object[] params, int page,
			int pageSize);
	
	public String getDBTime();

}
