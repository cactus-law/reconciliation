package com.froad.comon.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 * @author choky
 *         基础dao,所有dao继承此dao，以获得hibernateTemplate
 */
public class BaseDao {
    @Autowired
    public HibernateTemplate hibernateTemplate;

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    public void saveOrUpdate(Object obj) {
        this.getHibernateTemplate().saveOrUpdate(obj);
    }

    public void delete(Class c, Serializable id) {
        this.getHibernateTemplate().delete(this.getObjectById(c, id));
    }

    public Object getObjectById(Class c, Serializable id) {
        return this.getHibernateTemplate().get(c, id);
    }

    public Serializable save(Object obj) {
        return this.getHibernateTemplate().save(obj);
    }

    public Object update(Object o) {
        this.getHibernateTemplate().update(o);
        return o;
    }

    public void delete(Object o) {
        this.getHibernateTemplate().delete(o);
    }

    public void saveOrUpdateAll(Collection entities) {
        this.getHibernateTemplate().saveOrUpdateAll(entities);
    }

    public List<?> listInfo(String className) {
        return this.getHibernateTemplate().find("from " + className);
    }

    public Object sqlUniqueResult(final String sql) {
        Object object = this.getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session sn) throws HibernateException, SQLException {
                return sn.createSQLQuery(sql).uniqueResult();
            }
        });
        return object;
    }

    public Object hqlUniqueResult(final String hql) {
        Object object = this.getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session sn) throws HibernateException, SQLException {
                return sn.createQuery(hql).uniqueResult();
            }
        });
        return object;
    }

    public int sqlExecuteUpdate(final String sql) {
        Object object = this.getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session sn) throws HibernateException, SQLException {
                return sn.createSQLQuery(sql).executeUpdate();
            }
        });
        return Integer.valueOf(object.toString());
    }

    public int hqlExecuteUpdate(final String hql) {
        Object object = this.getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session sn) throws HibernateException, SQLException {
                return sn.createQuery(hql).executeUpdate();
            }
        });
        return Integer.valueOf(object.toString());
    }

    public List sqlList(final String sql) {
        Object object = this.getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session sn) throws HibernateException, SQLException {
                return sn.createSQLQuery(sql).list();
            }
        });
        return (List) object;
    }

    public List hqlList(final String hql) {
        Object object = this.getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session sn) throws HibernateException, SQLException {
                return sn.createQuery(hql).list();
            }
        });
        return (List) object;
    }

}
