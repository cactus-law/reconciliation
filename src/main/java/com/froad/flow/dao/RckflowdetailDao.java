package com.froad.flow.dao;

import com.froad.beans.Rckflowdetail;
import com.froad.comon.SystemConstant;
import com.froad.comon.dao.BaseDao;
import com.froad.comon.util.Logger;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("rckflowdetailDao")
public class RckflowdetailDao extends BaseDao {
    private static final Logger logger = Logger.getLogger(RckflowdetailDao.class);

    /**
     * 获取指定时间，模块流程流水条数
     *
     * @param date
     * @param type
     * @return
     * @throws Exception
     */
    public int selectCounts(String date, String type) throws Exception {
        Object object = this.sqlUniqueResult("select COUNT(*) from rckflowdetail where cleardate = '" + date + "' and rcktype = '" + type + "'");
        if (object != null) {
            return Integer.valueOf(object.toString());
        } else {
            return 0;
        }
    }

    /**
     * 获取昨天状态
     *
     * @param yesterday
     * @param rcktype
     * @return
     */
    public Object rckflowdetailYesterday(String yesterday, String rcktype) throws Exception {
        return this.hqlUniqueResult("from Rckflowdetail t where t.id.cleardate='" + yesterday + "' and t.id.rcktype='" + rcktype + "' and t.id.rckorder=0 ");
    }

    /**
     * 统计所有正在运行的流程
     *
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	public List<Rckflowdetail> countAllRunning() throws Exception {
        String hql = "from Rckflowdetail t where t.flowstate='" + SystemConstant.FLOWSTATE_RUNNING + "' and t.id.rckorder=0 ";
        logger.info("统计所有正在运行的流程:----->" + hql);
        return this.hqlList(hql);
    }
    
    /**
     * 统计所有正在运行的流程
     *
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	public List<Rckflowdetail> countAllRunning(String rcktype) throws Exception {
        String hql = "from Rckflowdetail t where  t.id.rcktype='"+ rcktype+"' and t.flowstate='" + SystemConstant.FLOWSTATE_RUNNING + "' and t.id.rckorder=0 ";
        logger.info("统计所有正在运行的流程:----->" + hql);
        return this.hqlList(hql);
    }

    /**
     * 获取流程流水列表
     *
     * @param date 执行清算时间
     * @param type 清算类型
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<Rckflowdetail> getRckflowdetaiList(String date, String type) throws Exception {
        return this.hqlList("from Rckflowdetail r where r.id.cleardate = '" + date + "' and r.id.rcktype = '" + type + "'");
    }

    /**
     * 获取前一天运行状态
     *
     * @param yesterday
     * @param type
     * @return
     */
    public Rckflowdetail getEntity(String yesterday, String type) {
        Object object = this.hqlUniqueResult("from Rckflowdetail r where  r.id.cleardate = '" + yesterday + "' and r.id.rcktype = '" + type + "' and r.id.rckorder = 0");
        return (Rckflowdetail) object;
    }

    public void initRunmoduleRn(String date) {

    }
}
