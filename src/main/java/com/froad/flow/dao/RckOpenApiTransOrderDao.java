package com.froad.flow.dao;

import com.froad.comon.dao.BaseDao;
import com.froad.comon.util.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component("rckOpenApiTransOrderDao")
public class RckOpenApiTransOrderDao extends BaseDao {
    private final static Logger logger = Logger.getLogger(RckOpenApiTransOrderDao.class);

    /**
     * 插入openapi.trans_order历史表
     *
     * @param startDate 开始时间
     * @param dateEnd   结束时间
     * @return
     */
    public int insertHis(String startDate, String dateEnd) {
        final String sql = "INSERT INTO openapi.trans_order_history SELECT t.* FROM openapi.trans_order t WHERE t.create_time >= '" + startDate + "'  and t.create_time < '" + dateEnd + "'";
        logger.info("插入openapi.trans_order历史表sql:" + sql);
        Object result = this.hibernateTemplate.execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                return session.createSQLQuery(sql).executeUpdate();
            }
        });
        return Integer.valueOf(result.toString());
    }

    /**
     * 删除trans_order流水表数据
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return
     */
    public int deleteIncre(String startDate, String endDate) {
        final String sql = "DELETE FROM openapi.trans_order WHERE  create_time >= '" + startDate + "'  AND  create_time < '" + endDate + "'";
        logger.info("删除openapi.trans_order流水表sql:" + sql);
        Object result = this.hibernateTemplate.execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                return session.createSQLQuery(sql).executeUpdate();
            }
        });
        return Integer.valueOf(result.toString());
    }
}
