package com.froad.flow.dao;

import com.froad.comon.dao.BaseDao;
import com.froad.comon.util.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component("rckFroadBillPayDao")
public class RckFroadBillPayDao extends BaseDao {
    private final static Logger logger = Logger.getLogger(RckFroadBillPayDao.class);

    /**
     * 插入 froadbill.pay 历史表
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return
     */
    public int insertHis(String startDate, String endDate) {
        final String sql = "INSERT INTO froadbill.pay_history SELECT t.* FROM froadbill.pay  t WHERE t.pay_time>='" + startDate + "'  AND t.pay_time<'" + endDate + "'";
        logger.info("插入froadbill.pay历史表sql:" + sql);
        Object result = this.hibernateTemplate.execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                return session.createSQLQuery(sql).executeUpdate();
            }
        });
        return Integer.valueOf(result.toString());
    }

    /**
     * 删除 froadbill.pay流水表数据
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return
     */
    public int deleteIncre(String startDate, String endDate) {
        final String sql = "DELETE FROM froadbill.pay WHERE pay_time>='" + startDate + "'  AND pay_time<'" + endDate + "'";
        logger.info("删除froadbill.pay流水表sql:" + sql);
        Object result = this.hibernateTemplate.execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                return session.createSQLQuery(sql).executeUpdate();
            }
        });
        return Integer.valueOf(result.toString());
    }
}
