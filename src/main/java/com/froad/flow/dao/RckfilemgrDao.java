package com.froad.flow.dao;

import com.froad.comon.dao.BaseDao;
import org.springframework.stereotype.Component;

@Component("rckfilemgrDao")
public class RckfilemgrDao extends BaseDao {
    /**
     * 统计文件
     *
     * @param cleardate
     * @param filetype
     * @return
     * @throws Exception
     */
    public int countFile(String cleardate, String filetype) throws Exception {
        Object object = this.hqlUniqueResult("select count(*) from Rckfilemgr r " +
                "where r.id.cleardate='" + cleardate + "' " +
                "and r.id.filetype='" + filetype + "' ");
        return Integer.valueOf(object.toString());
    }
}
