package com.froad.recon.importfile.dao.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Component;

import com.froad.comon.dao.BaseDao;
import com.froad.recon.importfile.dao.QueryDao;

/**
 * 查询数据DAO实现类
 * @author Administrator
 *
 */
@Component("queryDao")
public class QueryDaoImpl extends BaseDao implements QueryDao {
	
	final static Logger logger = LoggerFactory.getLogger(QueryDaoImpl.class);

	/**
	 * 查询
	 * @param sql
	 * @param params
	 * @return
	 */
    @SuppressWarnings("unchecked")
	public List<Map<String,Object>> queryRefoud(final String sql, final Object...params) {

        try {

    		Object object = this.getHibernateTemplate().execute(new HibernateCallback() {

				public Object doInHibernate(Session session) throws HibernateException, SQLException {
                    
                	SQLQuery sqlQuery = session.createSQLQuery(sql);
                	//设定结果结果集中的每个对象为Map类型   
                	sqlQuery.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
                	
                	int k = 0;
					for(Object obj : params) {
						
						if (obj instanceof String) {
							sqlQuery.setString(k, (String) obj);
						} else if(obj instanceof Integer) {
							sqlQuery.setInteger(k, (Integer) obj);
						} else if(obj instanceof Long) {
							sqlQuery.setLong(k, (Long) obj);
						} else if(obj instanceof BigDecimal) {
							sqlQuery.setBigDecimal(k, (BigDecimal) obj);
						} else if(obj instanceof Date) {
							Date date = (Date) obj;
							sqlQuery.setTimestamp(k,  new Timestamp(date.getTime()));
						}
						++k;
					}
					
					List<Map<String,Object>> list = sqlQuery.list();
					return list;
                }
            });

    		return (List<Map<String, Object>>) object;
    		
        }catch (Exception e) {
        	logger.error("查询数据库异常.[e="+e.getMessage()+"]", e);
            throw new RuntimeException(e);
        }
        
    }

    /**
	 * 修改
	 * @param sql
	 * @param params
	 * @return
	 */
	@Override
	public int executeSql(final String sql, final Object... params) {
		try {

    		Object object = this.getHibernateTemplate().execute(new HibernateCallback() {

				public Object doInHibernate(Session session) throws HibernateException, SQLException {
                    
                	SQLQuery sqlQuery = session.createSQLQuery(sql);
                	
                	int k = 0;
					for(Object obj : params) {
						
						if (obj instanceof String) {
							sqlQuery.setString(k, (String) obj);
						} else if(obj instanceof Integer) {
							sqlQuery.setInteger(k, (Integer) obj);
						} else if(obj instanceof Long) {
							sqlQuery.setLong(k, (Long) obj);
						} else if(obj instanceof BigDecimal) {
							sqlQuery.setBigDecimal(k, (BigDecimal) obj);
						} else if(obj instanceof Date) {
							Date date = (Date) obj;
							sqlQuery.setTimestamp(k,  new Timestamp(date.getTime()));
						}
						++k;
					}
					
					return sqlQuery.executeUpdate();
                }
            });

    		return (Integer) object;
    		
        }catch (Exception e) {
        	logger.error("更新数据库异常.[e="+e.getMessage()+"]", e);
            throw new RuntimeException(e);
        }
	}

}
