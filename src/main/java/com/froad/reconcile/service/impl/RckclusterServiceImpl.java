package com.froad.reconcile.service.impl;

import com.froad.beans.Rckcluster;
import com.froad.comon.SystemConstant;
import com.froad.comon.util.CommonUtil;
import com.froad.comon.util.DateTime;
import com.froad.comon.ServiceException;
import com.froad.flow.dao.CommonDao;
import com.froad.reconcile.service.RckclusterService;
import com.froad.comon.util.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("rckclusterService")
public class RckclusterServiceImpl implements RckclusterService {
    private final static Logger logger = Logger.getLogger(RckclusterServiceImpl.class);
    @Autowired
    private CommonDao commonDao;

    @SuppressWarnings("rawtypes")
	public Rckcluster initCluster(String clustertype) throws ServiceException {
        Rckcluster rc = null;
        try {
            String hql = "from Rckcluster t where t.clustertype='" + clustertype + "' ";
            List list = commonDao.hqlList(hql);
            if (list.size() == 0) {
                rc = new Rckcluster(clustertype);
                rc.setExedate(DateTime.GetData());
                //rc.setExestarttime(DateTime.GetTime());
                rc.setExestate(SystemConstant.COMMON_STATE_N);
                rc.setIsexecute(SystemConstant.BOOLEAN_YES);
                rc.setHostaddr(CommonUtil.getHostAddress());
                rc.setIsassign(SystemConstant.BOOLEAN_NO);
                rc.setRemark("初始化！");
                commonDao.save(rc);
            } else {
                rc = (Rckcluster) list.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
        return rc;
    }

    /**
     * 抢夺执行
     *
     * @param clustertype
     * @return
     * @throws ServiceException
     */
    public boolean snatchExecute(String clustertype) throws ServiceException {
        boolean flag = false;
        try {
            Rckcluster rc = this.initCluster(clustertype);
            String hostAddr = CommonUtil.getHostAddress();
            String exetime = DateTime.GetTime();
            String r = SystemConstant.COMMON_STATE_R;
            String y = SystemConstant.BOOLEAN_YES;
            String n = SystemConstant.BOOLEAN_NO;
            String asd = rc.getAssignhostaddr();
            if (y.equals(rc.getIsexecute())) {
                if (!r.equals(rc.getExestate())) {
                    if ((y.equals(rc.getIsassign()) && hostAddr.equals(asd))
                            || n.equals(rc.getIsassign())) {
                        String sql = "UPDATE RCKCLUSTER R SET R.REMARK='running', R.EXESTATE='"
                                + SystemConstant.COMMON_STATE_R + "',R.HOSTADDR='"
                                + hostAddr + "',R.EXESTARTTIME='" + exetime
                                + "' WHERE R.CLUSTERTYPE='"+clustertype+"' AND ( R.EXESTATE='" + SystemConstant.COMMON_STATE_N
                                + "' OR R.EXESTATE='" + SystemConstant.COMMON_STATE_E
                                + "') ";
                        logger.debug("互斥更新SQL：" + sql);
                        int result = commonDao.sqlExecuteUpdate(sql);
                        logger.debug("互斥更新结果：" + result);
                        if (result != 0) {
                            flag = true;
                        }
                    }
                } else {
                    logger.error("有系统正在运行：" + clustertype + " " + rc.getHostaddr());
                }
            } else {
                logger.debug(clustertype + "不执行！");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
        return flag;
    }

    public void udpateExestate(String clustertype, String exestate, String remark) throws ServiceException {
        try {
            Object object = commonDao.getObjectById(Rckcluster.class, clustertype);
            if (object != null) {
                Rckcluster rc = (Rckcluster) object;
                rc.setExedate(DateTime.GetData());
                rc.setExeendtime(DateTime.GetTime());
                rc.setExestate(exestate);
                rc.setRemark(remark);
                commonDao.update(rc);
            } else {
                throw new ServiceException("对象为空：" + clustertype);
            }
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @SuppressWarnings("unchecked")
	public List<Rckcluster> listRckcluster(String clustertype) throws ServiceException {
        String hql = "from Rckcluster t where t.clustertype='" + clustertype + "' ";
        if (clustertype == null || "".equals(clustertype.trim())) {
            hql = "from Rckcluster t ";
        }
        return commonDao.hqlList(hql);
    }

    public void changeClusterExecute(String clustertype, String isexecute)
            throws ServiceException {
        Rckcluster rc = (Rckcluster) commonDao.getObjectById(Rckcluster.class, clustertype);
        if (SystemConstant.BOOLEAN_NO.equals(isexecute)) {
            isexecute = SystemConstant.BOOLEAN_YES;
        } else {
            isexecute = SystemConstant.BOOLEAN_NO;
        }
        rc.setIsexecute(isexecute);
        commonDao.saveOrUpdate(rc);
    }

    @Override
    public void changeExecuteState( String clustertype, String isexecute ) throws ServiceException
    {
        Rckcluster rc = (Rckcluster) commonDao.getObjectById(Rckcluster.class, clustertype);
        rc.setExestate(isexecute);
        rc.setRemark("手动改状态");
        commonDao.saveOrUpdate(rc);
    }

    public List<Rckcluster> initClusterAll() throws ServiceException {
        List<Rckcluster> list = new ArrayList<Rckcluster>();
        Rckcluster rcSs = initCluster(SystemConstant.RCKTYPE_SS);
        list.add(rcSs);
        return list;
    }

}
