package com.froad.comon.dao.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.froad.comon.util.Logger;

/**
 * 数据访问层基类.
 */
@SuppressWarnings("unchecked")
public class HibernateBaseDao<T extends Object> extends HibernateDaoSupport implements
		DBManager<T> {
	
	public void clear(){
		this.getHibernateTemplate().clear();
	}
	
	public void flush(){
		this.getHibernateTemplate().flush();
	}
	/**
	 * 数据插入
	 * @param obj
	 * @return
	 * @throws DBException
	 */
	public Object save(T obj) throws DBException {
		try {
			Object newObj = this.getHibernateTemplate().save(obj);
			return newObj;
		} catch (Exception ex) {
			throw new DBException(ErrorCodes.E_DB_SAVE, ex.getMessage(), ex);
		}
	}

	/**
	 * 数据更新
	 * 
	 * @param obj
	 * @throws DBException
	 */
	public void update(T obj) throws DBException {
		try {
			this.getHibernateTemplate().saveOrUpdate(obj);
		} catch (Exception ex) {
			throw new DBException(ErrorCodes.E_DB_UPDATE, ex.getMessage(), ex);
		}
	}

	/**
	 * 数据删除
	 * 
	 * @param obj
	 * @throws DBException
	 */
	public void delete(T obj) throws DBException {
		try {
			this.getHibernateTemplate().delete(obj);
		} catch (Exception ex) {
			throw new DBException(ErrorCodes.E_DB_DEL, ex.getMessage(), ex);
		}
	}

	/**
	 * 通用查找功能
	 * 
	 * @param hql
	 *            hql语句
	 * @param params
	 *            参数
	 * @return
	 * @throws DBException
	 */
	public List<T> findByHql(String hql, Object[] params) {
		List<T> list = this.getHibernateTemplate().find(hql, params);
		if (list == null || list.size() == 0)
			return new ArrayList<T>();
		return list;
	}

	/**
	 * 通用查找功能
	 * 
	 * @param sql
	 *            sql语句
	 * @param params
	 *            参数
	 * @return
	 * @throws DBException
	 */
	public List<T> findBySql(final String sql, final Object[] params) {
		return (List<T>) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);

						for (int i = 0; i < params.length; i++) {
							query.setParameter(i, params[i]);
						}
						return query.list();
					}

				});
	}

	public List<T> findBySql(final String sql, final Object[] params,
			final Class clazz) {
		return (List<T>) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						SQLQuery query = session.createSQLQuery(sql);
						if (clazz != null)
							query.addEntity(clazz);
						for (int i = 0; i < params.length; i++) {
							query.setParameter(i, params[i]);
						}
						return query.list();
					}
				});
	}

	/**
	 * 根据ParamPttern模式里的参数设置，设置参数
	 * 
	 * @param hql
	 *            hql
	 * @param params
	 *            参数
	 * @return
	 */
	public List findByHqlParamPattern(final String hql,
			final List<ParamPattern> params) {
		return this.findByHqlParamPatternPaged(hql, params, null, null);
	}

	/**
	 * 根据ParamPttern模式里的参数设置，设置参数
	 * 
	 * @param hql
	 *            hql
	 * @param params
	 *            参数
	 * @param page
	 *            页号
	 * @param pageSize
	 *            页大小
	 * @return
	 */
	public List<T> findByHqlParamPatternPaged(final String hql,
			final List<ParamPattern> params, Integer page, Integer pageSize) {
		return (List<T>) this.getHibernateTemplate().execute(
				new QueryParamPattern(hql, params, page, pageSize));
	}

	private class QueryParamPattern implements HibernateCallback {
		/** 查询hql语句 */
		private String hql;
		/** 查询参数对象数组 */
		private List<ParamPattern> params;
		/** 页号 从1开始 */
		private Integer page;
		/** 页大小 */
		private Integer pageSize;

		public QueryParamPattern(String hql, List<ParamPattern> params,
				Integer page, Integer pageSize) {
			this.hql = hql;
			this.params = params;
			this.page = page;
			this.pageSize = pageSize;
		}

		public List<T> doInHibernate(Session session)
				throws HibernateException, SQLException {
			Query query = session.createQuery(hql);
			if (params != null && !params.isEmpty()) {
				int index = 0;
				for (ParamPattern param : params) {
					String mode = param.getLikeMode();
					Object value = param.getValue();
					Object newValue = "";
					if (param.isLike()) {
						if (ParamPattern.LIKE_PATTERN_START.equals(mode)) {
							newValue = "%" + value + "";
						} else if (ParamPattern.LIKE_PATTERN_END.equals(mode)) {
							newValue = "" + value + "%";
						} else if (ParamPattern.LIKE_PATTERN_NONE.equals(mode)) {
							newValue = value;
						} else {
							newValue = "%" + value + "%";
						}
					} else {
						newValue = value;
					}
					query.setParameter(index, newValue);
					index++;
				}
			}
			if (page != null && pageSize != null) {
				query.setFirstResult(page * pageSize - pageSize);
				query.setMaxResults(pageSize);
			}
			List<T> rs = query.list();
			if (rs == null || rs.isEmpty()) {
				return new ArrayList<T>();
			}
			return rs;
		}
	}

	/**
	 * 通用的更新方法
	 * 
	 * @param hql
	 * @param params
	 * @throws DBException
	 */
	public void updateByHql(String hql, Object[] params) throws DBException {
		try {
			this.getHibernateTemplate().execute(
					new UpdateExecuteBack(hql, params));
		} catch (Exception ex) {
			throw new DBException(ErrorCodes.E_DB_UPDATE, ex.getMessage(), ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.handpay.core.dao.common.DBManager#updateDataBySql(java.lang.String,
	 * java.lang.Object[])
	 */
	@Override
	public Object updateDataBySql(final String sql, final Object[] params)
			throws DBException {
		try {
			Object obj = this.getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException {
							SQLQuery query = session.createSQLQuery(sql);
							for (int i = 0; i < params.length; i++) {
								query.setParameter(i, params[i]);
							}
							return query.executeUpdate();
						}
					});
			return obj;
		} catch (Exception ex) {
			throw new DBException(ErrorCodes.E_DB_UPDATE, ex.getMessage(), ex);
		}
	}

	/**
	 * <p>
	 * 使用 HQL 更新数据
	 * </p>
	 * 
	 * @param hql
	 *            HQL 语句
	 * @param params
	 *            参数
	 * @return 更新的数据行数
	 * @throws DBException
	 * @author gaobaowen
	 * @since 2012-5-18 16:05:40
	 */
	public Integer updateByHqlReturnCount(String hql, Object... params)
			throws DBException {
		try {
			return (Integer) this.getHibernateTemplate().execute(
					new UpdateExecuteBack(hql, params));
		} catch (Exception ex) {
			throw new DBException(ErrorCodes.E_DB_UPDATE, ex.getMessage(), ex);
		}
	}

	/**
	 * 根据hql删除
	 * 
	 * @param hql
	 * @param params
	 * @throws DBException
	 * @return 删除的记录数
	 */
	public int deleteByHql(String hql, Object[] params) throws DBException {
		try {
			
			return (Integer) this.getHibernateTemplate().execute(
					new DeleteExecuteBack(hql, params));
		} catch (Exception ex) {
			throw new DBException(ErrorCodes.E_DB_UPDATE, ex.getMessage(), ex);
		}
	}
	/**
	 * 根据sql删除
	 * 
	 * @param hql
	 * @param params
	 * @throws DBException
	 * @return 删除的记录数
	 */
	public int deleteBySql(String sql, Object[] params) throws DBException {
		try {
			
			return (Integer) this.getHibernateTemplate().execute(
					new DeleteExecuteSql(sql, params));
		} catch (Exception ex) {
			throw new DBException(ErrorCodes.E_DB_UPDATE, ex.getMessage(), ex);
		}
	}

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
			throws DBException {

		try {
			T obj = (T) this.getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException, SQLException {
							Query query = session.createQuery(hql);
							if (params != null) {
								for (int i = 0; i < params.length; i++) {
									query.setParameter(i, params[i]);
								}
							}
							Object obj = null;
							obj = query.uniqueResult();
							return obj;
						}
					});
			return obj;
		} catch (Exception e) {
			Logger.getLogger(this.getClass()).error(e.getMessage(), e);
			throw new DBException(ErrorCodes.E_DB_FIND, "返回多条记录");
		}
	}

	public T findUniqueObjectBySql(final String sql, final Object[] params)
			throws DBException {
		try {
			T obj = (T) this.getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException, SQLException {
							SQLQuery query = session.createSQLQuery(sql);
							if (params != null) {
								for (int i = 0; i < params.length; i++) {
									query.setParameter(i, params[i]);
								}
							}
							Object obj = null;
							obj = query.uniqueResult();
							return obj;
						}
					});
			return obj;
		} catch (Exception e) {
			Logger.getLogger(this.getClass()).error(e.getMessage(), e);
			throw new DBException(ErrorCodes.E_DB_FIND, "返回多条记录");
		}
	}

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
			int pageSize) {

		List<T> list = (List<T>) this.getHibernateTemplate().execute(
				new PagedCallBack(hql, params, page, pageSize));
		if (list == null || list.size() == 0)
			return new ArrayList<T>();

		return list;

	}

	public List<T> findByIndex(String hql, Object[] params, int start, int size) {

		List<T> list = (List<T>) this.getHibernateTemplate().execute(
				new IndexCallBack(hql, params, start, size));
		if (list == null || list.size() == 0)
			return new ArrayList<T>();

		return list;

	}

	public List<T> findByIndexSql(String sql, Object[] params, int start,
			int size) {

		List<T> list = (List<T>) this.getHibernateTemplate().execute(
				new IndexSQLCallBack(sql, params, null, start, size));
		if (list == null || list.size() == 0)
			return new ArrayList<T>();

		return list;

	}

	public List<T> findByIndexSql(String sql, Object[] params, Class clz,
			int start, int size) {

		List<T> list = (List<T>) this.getHibernateTemplate().execute(
				new IndexSQLCallBack(sql, params, clz, start, size));
		if (list == null || list.size() == 0)
			return new ArrayList<T>();

		return list;

	}

	/**
	 * 
	 * 分页查找callBack类.
	 * 
	 * @version 1.0 2009-8-4
	 * @author yzhu
	 * @history
	 * 
	 */
	private class PagedCallBack implements HibernateCallback {
		/** 查询hql语句 */
		private String hql;

		/** 查询参数对象数组 */
		private Object[] params;

		/** 页号 从1开始 */
		private int page;

		/** 页大小 */
		private int pageSize;

		public PagedCallBack(String hql, Object[] params, int page, int pageSize) {
			this.hql = hql;
			this.params = params;
			this.page = page;
			this.pageSize = pageSize;
		}

		/**
		 * 分页查询
		 */
		public Object doInHibernate(Session session) throws HibernateException,
				SQLException {
			Query query = session.createQuery(hql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					query.setParameter(i, params[i]);
				}
			}
			query.setFirstResult(page * pageSize - pageSize);
			query.setMaxResults(pageSize);

			return query.list();
		}
	}

	private class IndexCallBack implements HibernateCallback {
		/** 查询hql语句 */
		private String hql;

		/** 查询参数对象数组 */
		private Object[] params;

		/** 页号 从1开始 */
		private int start;

		/** 页大小 */
		private int size;

		public IndexCallBack(String hql, Object[] params, int start, int size) {
			this.hql = hql;
			this.params = params;
			this.start = start;
			this.size = size;
		}

		/**
		 * 分页查询
		 */
		public Object doInHibernate(Session session) throws HibernateException,
				SQLException {
			Query query = session.createQuery(hql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					query.setParameter(i, params[i]);
				}
			}
			query.setFirstResult(start);
			query.setMaxResults(size);

			return query.list();
		}
	}

	private class IndexSQLCallBack implements HibernateCallback {
		/** 查询hql语句 */
		private String sql;

		/** 查询参数对象数组 */
		private Object[] params;

		private Class clz;

		/** 页号 从1开始 */
		private int start;

		/** 页大小 */
		private int size;

		public IndexSQLCallBack(String sql, Object[] params, Class clz,
				int start, int size) {
			this.sql = sql;
			this.params = params;
			this.clz = clz;
			this.start = start;
			this.size = size;
		}

		/**
		 * 分页查询
		 */
		public Object doInHibernate(Session session) throws HibernateException,
				SQLException {
			SQLQuery query = session.createSQLQuery(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					query.setParameter(i, params[i]);
				}
			}
			if (this.clz != null) {
				query.addEntity(clz);
			}
			query.setFirstResult(start);
			query.setMaxResults(size);

			return query.list();
		}
	}

	private class UpdateExecuteBack implements HibernateCallback {
		/** 查询hql语句 */
		private String hql;

		/** 查询参数对象数组 */
		private Object[] params;

		public UpdateExecuteBack(String hql, Object[] params) {
			this.hql = hql;
			this.params = params;
		}

		/**
		 * 分页查询
		 */
		public Object doInHibernate(Session session) throws HibernateException,
				SQLException {
			Query query = session.createQuery(hql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					query.setParameter(i, params[i]);
				}
			}
			int count = query.executeUpdate();

			return new Integer(count);
		}
	}

	private class DeleteExecuteBack implements HibernateCallback {
		/** 查询hql语句 */
		private String hql;

		/** 查询参数对象数组 */
		private Object[] params;

		public DeleteExecuteBack(String hql, Object[] params) {
			this.hql = hql;
			this.params = params;
		}

		public Object doInHibernate(Session session) throws HibernateException,
				SQLException {
			Query query = session.createQuery(hql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					query.setParameter(i, params[i]);
				}
			}
			int count = query.executeUpdate();

			return new Integer(count);
		}
	}

	private class DeleteExecuteSql implements HibernateCallback {
		/** 查询hql语句 */
		private String sql;

		/** 查询参数对象数组 */
		private Object[] params;

		public DeleteExecuteSql(String sql, Object[] params) {
			this.sql = sql;
			this.params = params;
		}

		public Object doInHibernate(Session session) throws HibernateException,
				SQLException {
			Query query = session.createSQLQuery(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					query.setParameter(i, params[i]);
				}
			}
			int count = query.executeUpdate();

			return new Integer(count);
		}
	}
	/**
	 * 查询分页总记录数
	 * 
	 * @param hql
	 *            HQL语句
	 * @param params
	 *            参数
	 */
	public int queryCount(String hql, Object[] params) {
		try {
			List<Object> list = this.getHibernateTemplate().find(hql, params);
			if (list == null || list.size() == 0)
				return 0;
			return ((Long) list.get(0)).intValue();
		} catch (Exception e) {
			Logger.getLogger(this.getClass()).error(
					"查询分页总记录数[" + e.getMessage() + "]", e);
			return 0;
		}
	}

	/**
	 * 根据提供的 sql 查询分页总记录数
	 * 
	 * @param sql
	 *            sql语句
	 * @param params
	 *            参数
	 */
	public int queryCountBySql(final String sql, final Object[] params) {
		Object row_total_count = this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						SQLQuery query = session.createSQLQuery(sql);
						for (int i = 0; i < params.length; i++) {
							query.setParameter(i, params[i]);
						}
						return query.uniqueResult();
					}
				});
		if (row_total_count != null)
			return Integer.parseInt(row_total_count.toString());
		else
			return 0;
	}

	/**
	 * 根据sql语句查找
	 * 
	 * @param sql
	 * @return
	 */
	public Object findBySql(final String sql) {
		Object list = this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						SQLQuery query = session.createSQLQuery(sql);
						return query.list();
					}
				});
		return list;
	}

	/**
	 * 获取数据库的当前时间
	 * 
	 * @return
	 */
	public String getDBTime() {
		String sql = "select to_char(sysdate,'yyyyMMddHH24miss') from dual";
		List list = (List) this.findBySql(sql);
		return (String) list.get(0);
	}

	/**
	 * 查询分页总记录数
	 * 
	 * @param sql
	 *            sql语句
	 * @param params
	 *            参数
	 */
	public int findBySql(final StringBuffer hql, final Object[] params) {
		return (Integer) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(hql.toString());

						for (int i = 0; i < params.length; i++) {
							query.setParameter(i, params[i]);
						}
						if (query.list().size() > 0) {
							return ((BigDecimal) query.list().get(0))
									.intValue();
						}
						return 0;
					}

				});
	}

	/**
	 * 通用查找功能
	 * 
	 * @param sql
	 *            . sql语句
	 * @param params
	 *            参数
	 * @return
	 * @throws SQLException
	 * @throws DBException
	 */
	public List<T> findByPageSql(String sql, Object[] params, int page,
			int pageSize) {

		List<T> list = (List<T>) this.getHibernateTemplate().execute(
				new PageCallBackSql(sql, params, page, pageSize));
		if (list == null || list.size() == 0)
			return new ArrayList<T>();

		return list;
	}

	public List<T> findByPageSql(String sql, Object[] params, Class clazz,
			int page, int pageSize) {
		List<T> list = (List<T>) this.getHibernateTemplate().execute(
				new PageCallBackSql(sql, params, clazz, page, pageSize));
		if (list == null || list.size() == 0)
			return new ArrayList<T>();
		return list;
	}

	public int bulkUpdate(String hql, Object[] params) throws DBException {
		try {
			int count = this.getHibernateTemplate().bulkUpdate(hql, params);
			return count;
		} catch (Exception ex) {
			throw new DBException(ErrorCodes.E_DB_UPDATE, ex.getMessage(), ex);
		}
	}

	/**
	 * 
	 * 分页查找callBack类.
	 * 
	 * @version 1.0 2009-8-4
	 * @author PageCallBackSql
	 * @history
	 * 
	 */
	private class PageCallBackSql implements HibernateCallback {
		/** 查询hql语句 */
		private String hql;

		/** 查询参数对象数组 */
		private Object[] params;

		/** 数据映射对象类 */
		private Class clazz;

		/** 页号 从1开始 */
		private int page;

		/** 页大小 */
		private int pageSize;

		public PageCallBackSql(String sql, Object[] params, int page,
				int pageSize) {
			this.hql = sql;
			this.params = params;
			this.page = page;
			this.pageSize = pageSize;
		}

		public PageCallBackSql(String sql, Object[] params, Class clazz,
				int page, int pageSize) {
			this.hql = sql;
			this.params = params;
			this.clazz = clazz;
			this.page = page;
			this.pageSize = pageSize;
		}

		/**
		 * 分页查询
		 */
		public Object doInHibernate(Session session) throws HibernateException,
				SQLException {
			SQLQuery query = session.createSQLQuery(hql.toString());
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					query.setParameter(i, params[i]);
				}
			}
			if (this.clazz != null)
				query.addEntity(clazz);
			query.setFirstResult(page * pageSize - pageSize);
			query.setMaxResults(pageSize);
			return query.list();
		}
	}
	
	/***通过sql 查询返回一个对应的map**/
	public List<Map> queryMapBysql(String sql,List<Object> params){
		SQLQuery query = getSession().createSQLQuery(sql);
		//设定结果结果集中的每个对象为Map类型   
		query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
	   
		int i=0;
		for (Object item : params) {
			if(item!=null){
				if(item instanceof String){
					if(StringUtils.isNotBlank((String)item)){
						query.setString(i, (String)item);
						i++;
					}
				}else if(item instanceof Long){
					query.setLong(i, (Long)item);
					i++;
				}else if(item instanceof Integer){
					query.setInteger(i, (Integer)item);
					i++;
				}else if(item instanceof Double){
					query.setDouble(i, (Double)item);
					i++;
				}else if(item instanceof Date){
					query.setDate(i, (Date)item);
					i++;
				}else{
					query.setParameter(i, item);
					i++;
				}
			}
		}
		//执行查询
		List<Map> resultList = query.list();
		return resultList;
	}
	
	
	/***通过sql 查询返回一个对应的map**/
	public List<Map>  queryMapBysql(String sql,List<Object> params,Integer page,Integer pageSize){
		SQLQuery query = getSession().createSQLQuery(sql);
		//设定结果结果集中的每个对象为Map类型   
		query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
	   
		int i=0;
		for (Object item : params) {
			if(item!=null){
				if(item instanceof String){
					if(StringUtils.isNotBlank((String)item)){
						query.setString(i, (String)item);
						i++;
					}
				}else if(item instanceof Long){
					query.setLong(i, (Long)item);
					i++;
				}else if(item instanceof Integer){
					query.setInteger(i, (Integer)item);
					i++;
				}else if(item instanceof Double){
					query.setDouble(i, (Double)item);
					i++;
				}else if(item instanceof Date){
					query.setDate(i, (Date)item);
					i++;
				}else{
					query.setParameter(i, item);
					i++;
				}
			}
		}
		query.setFirstResult(page * pageSize - pageSize);
		query.setMaxResults(pageSize);
		//执行查询
		List<Map> resultList = query.list();
		return resultList;
	}
}
