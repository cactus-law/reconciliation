package com.froad.flow.dao;

import com.froad.comon.dao.BaseDao;
import com.froad.comon.util.Logger;
import org.springframework.stereotype.Component;

@Component("rckas400Dao")
public class Rckas400Dao extends BaseDao {
    private final static Logger logger = Logger.getLogger(Rckas400Dao.class);

    public void deleteByDate(String rcktype) {
        int count = this.hibernateTemplate.bulkUpdate("delete Rckas400 rck where rck.id.rcktype = '" + rcktype + "'");
        logger.info("清空类型为[" + rcktype + "] 的数据，条数为[" + count + "] !");

    }

    public void deleteByDatehis(String dateStr8, String rcktype) {
        int count = this.hibernateTemplate.bulkUpdate("delete Rckas400his rck where rck.id.rcktype = '" + rcktype + "' and rck.id.cleardate = '" + dateStr8 + "'");
        logger.info("清空类型为[" + rcktype + "]时间为[" + dateStr8 + "] 的历史数据，条数为[" + count + "] !");

    }

    public void deleteByDateerr(String dateStr8, String rcktype) {
        int count = this.hibernateTemplate.bulkUpdate("delete Rckas400err rck where rck.id.rcktype = '" + rcktype + "' and rck.id.cleardate = '" + dateStr8 + "'");
        logger.info("清空类型为[" + rcktype + "]时间为[" + dateStr8 + "] 的错误 数据，条数为[" + count + "] !");

    }

}
