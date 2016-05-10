package com.froad.flow.dao;

import com.froad.comon.dao.BaseDao;
import com.froad.comon.util.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component("rckFroadBillBillDao")
public class RckFroadBillBillDao extends BaseDao {
    private final static Logger logger = Logger.getLogger(RckFroadBillBillDao.class);

    /**
     * 插入 froadbill.bill 历史表
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return
     */
    public int insertHis(String startDate, String endDate) {
        final String sql = "INSERT INTO froadbill.bill_history SELECT t.* FROM froadbill.bill  t WHERE t.create_time>='" + startDate + "'  AND t.create_time<'" + endDate + "'";
        logger.info("插入froadbill.bill历史表sql:" + sql);
        Object result = this.hibernateTemplate.execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                return session.createSQLQuery(sql).executeUpdate();
            }
        });
        return Integer.valueOf(result.toString());
    }

    /**
     * 删除 froadbill.bill流水表数据
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return
     */
    public int deleteIncre(String startDate, String endDate) {
        final String sql = "DELETE FROM froadbill.bill WHERE create_time>='" + startDate + "'  AND create_time<'" + endDate + "'";
        logger.info("删除froadbill.bill流水表sql:" + sql);
        Object result = this.hibernateTemplate.execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                return session.createSQLQuery(sql).executeUpdate();
            }
        });
        return Integer.valueOf(result.toString());
    }
}
