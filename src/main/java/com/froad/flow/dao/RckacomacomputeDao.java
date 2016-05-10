package com.froad.flow.dao;

import com.froad.comon.SystemConstant;
import com.froad.comon.dao.BaseDao;
import com.froad.comon.RckConstant;
import com.froad.comon.util.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component("rckacomacomputeDao")
public class RckacomacomputeDao extends BaseDao {
    private final Logger logger = Logger.getLogger(RckacomacomputeDao.class);

    /**
     * RCKNONTAX数据导入RCKACOMACOMPUTE
     *
     * @param dateStr8 ： 执行清算时间
     * @param rcktype  ：清算类型
     */
    public int insterFees(String dateStr8, String rcktype) {
        String sql = "INSERT INTO RCKACOMACOMPUTE SELECT M.*,0,0,MERFEE,0,AMOUNT+MERFEE,'03','" + RckConstant.ISCOMPUTE + "','" + RckConstant.TRAD_BUSITYPE_P02 + "',null,null,null,null,null,null FROM RCKNONTAX M WHERE M.RCKTYPE = '" + rcktype + "' AND M.CLEARDATE ='" + dateStr8 + "' AND  SOURCEFLAG='" + SystemConstant.SOURCEFLAG_FS_SUCCESS + "'";
        logger.info("RCKNONTAX》RCKACOMACOMPUTE:----->" + sql);
        int count = this.sqlExecuteUpdate(sql);
        logger.info("RCKNONTAX》RCKACOMACOMPUTE count:----->" + count);
        return count;
    }

    /**
     * AOMA 流水手续费计算
     *
     * @param cleardate
     * @param rcktype
     * @param busytype
     * @param sourceflag
     * @return
     */
    public int acomaCompFees(String cleardate, String rcktype, String busytype, String sourceflag) {
        String sql = "INSERT INTO RCKACOMACOMPUTE SELECT M.*,0,0,MERFEE,0,AMOUNT+MERFEE,'03','" + RckConstant.ISCOMPUTE + "','" + busytype + "',null,null,null,null,null,null FROM RCKNONTAX M WHERE M.RCKTYPE = '" + rcktype + "' AND M.CLEARDATE ='" + cleardate + "' AND  SOURCEFLAG='" + sourceflag + "'";
        logger.info("RCKNONTAX》RCKACOMACOMPUTE:----->" + sql);
        int count = this.sqlExecuteUpdate(sql);
        logger.info("RCKNONTAX》RCKACOMACOMPUTE count:----->" + count);
        return count;
    }

    /**
     * AOMA 流水手续费计算 理财特殊处理
     *
     * @param cleardate
     * @return
     */
    public int acomaCompFeesLc(String cleardate) {
        String sql = "INSERT INTO RCKACOMACOMPUTE SELECT M.*,0,0,MERFEE,0,AMOUNT+MERFEE,'03','" + RckConstant.ISCOMPUTE + "','" + RckConstant.TRAD_BUSITYPE_L01 + "',null,null,null,null,null,null FROM RCKNONTAX M WHERE M.RCKTYPE = '" + SystemConstant.RCKTYPE_LC + "' AND M.CLEARDATE ='" + cleardate + "' AND  SOURCEFLAG IN('" + SystemConstant.SOURCEFLAG_LC_SUCCESSA + "','" + SystemConstant.SOURCEFLAG_LC_SUCCESSB + "')";
        logger.info("RCKNONTAX》RCKACOMACOMPUTE:----->" + sql);
        int count = this.sqlExecuteUpdate(sql);
        logger.info("RCKNONTAX》RCKACOMACOMPUTE count:----->" + count);
        return count;
    }

    public int deleteAcomacompute(String cleardate, String rcktype) {
        String sql = "DELETE RCKACOMACOMPUTE WHERE RCKTYPE = '" + rcktype + "' and CLEARDATE ='" + cleardate + "'";
        logger.info("DELETE RCKACOMACOMPUTE:----->" + sql);
        int count = this.sqlExecuteUpdate(sql);
        logger.info("DELETE RCKACOMACOMPUTE count:----->" + count);
        return count;
    }

    /**
     * 测试
     *
     * @param args
     */
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml", "action-servlet.xml"});
        RckacomacomputeDao bean = (RckacomacomputeDao) ctx.getBean("rckacomcomputeDao");
        bean.insterFees("20120626", "FS");
    }
}
