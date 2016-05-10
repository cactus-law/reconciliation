package com.froad.recon.importfile.dao.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Component;

import com.froad.comon.dao.BaseDao;
import com.froad.recon.importfile.dao.ImpDataDao;

/**
 * 导入数据DAO实现类
 * @author Administrator
 *
 */
@Component("impDataDao")
public class ImpDataDaoImpl extends BaseDao implements ImpDataDao {
	
	final static Logger logger = LoggerFactory.getLogger(ImpDataDaoImpl.class);

	/**
     * 添加
     * @param para
     */
    protected int add(Map<String,Object> para) {

        try {
        	Set<Entry<String, Object>> set  = para.entrySet();
        	StringBuffer sql = new StringBuffer();
        	StringBuffer keySql = new StringBuffer("(");
        	StringBuffer valSql = new StringBuffer("(");
        	List<Object> valList = new ArrayList<Object>();
            for(Entry<String, Object> entry : set){
            	String key = entry.getKey();
            	Object val = entry.getValue();
            	if("table".equals(key)){
            		sql.append(String.format("insert into %s", entry.getValue()));
            	}else{
            		keySql.append(key).append(",");
            		valSql.append("?,");
            		valList.add(val);
            	}
            }
            sql.append(keySql.toString().substring(0, keySql.toString().length()-1));
            sql.append(") ");
            sql.append("values");
            sql.append(valSql.toString().substring(0, valSql.toString().length()-1));
            sql.append(")");

    		final String prePareSql = sql.toString();
    		final Object[] objs = valList.toArray();
    		logger.debug("导入sql[SQL={}]", sql);
    		logger.debug("参数[params={}]", valList);
    		
    		Object object = this.getHibernateTemplate().execute(new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException, SQLException {
                    
                	SQLQuery sqlQuery = session.createSQLQuery(prePareSql);
                	int k = 0;
					for(Object obj : objs) {
						
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
						}else if(obj == null){
							sqlQuery.setParameter(k, obj);
						}else{
							sqlQuery.setParameter(k, obj);
						}
						++k;
					}
					int ret = sqlQuery.executeUpdate();
					return ret;
                }
            });
    		
    		return (Integer) object;
    		
        }catch (Exception e) {
        	logger.error("添加数据库异常.[e="+e.getMessage()+"]", e);
            throw new RuntimeException(e);
        }
        
        
        
    }
    
    /**
     * 添加
     * @param list
     * @return 本次成功记录数
     */
    public int addList(List<Map<String,Object>> list){
    	if(list == null){
    		return -1;
    	}
    	int count = 0;
    	for(Map<String,Object> para : list){
			this.add(para);
			++count;
		}
    	return count;
    }

	@Override
	public int delReconData(String sql) {
		int ret = super.sqlExecuteUpdate(sql);
		return ret;
	}

}
