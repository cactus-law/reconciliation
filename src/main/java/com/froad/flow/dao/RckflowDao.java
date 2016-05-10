package com.froad.flow.dao;


import com.froad.beans.Rckflow;
import com.froad.comon.dao.BaseDao;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("rckflowDao")
public class RckflowDao extends BaseDao {

    /**
     * 获取指定类型清算流程列表
     *
     * @param type
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<Rckflow> getRckflows(String type) throws Exception {
        List<Rckflow> list = this.getHibernateTemplate().find("from Rckflow  r where r.id.rcktype = '" + type + "' order by rckorder");
        return list;
    }


}
