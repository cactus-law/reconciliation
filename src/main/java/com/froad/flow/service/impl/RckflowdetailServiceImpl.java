package com.froad.flow.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.froad.beans.Rckflow;
import com.froad.beans.RckflowId;
import com.froad.beans.Rckflowdetail;
import com.froad.beans.RckflowdetailId;
import com.froad.comon.ServiceException;
import com.froad.comon.SystemConstant;
import com.froad.comon.constant.BusinessConstant;
import com.froad.comon.util.AppUtil;
import com.froad.comon.util.DateTime;
import com.froad.comon.util.Logger;
import com.froad.flow.FlowException;
import com.froad.flow.FlowInterface;
import com.froad.flow.dao.RckflowDao;
import com.froad.flow.dao.RckflowdetailDao;
import com.froad.flow.service.RckflowdetailService;

@Component("rckflowdetailService")
public class RckflowdetailServiceImpl implements RckflowdetailService {
    private static final Logger logger = Logger.getLogger(RckflowdetailServiceImpl.class);
    @Autowired
    private RckflowdetailDao rckflowdetailDao;
    @Autowired
    private RckflowDao rckflowDao;

    /**
     * 查看历史初始化流程
     *
     * @param rckflowdetail
     * @return
     */
    public List<Rckflowdetail> initFlowDetail(Rckflowdetail rckflowdetail) throws FlowException {
        try {
            String cleardate = rckflowdetail.getId().getCleardate();
            String rcktype = rckflowdetail.getId().getRcktype();
            // 查询流程定义表
            List<Rckflow> listFlow = rckflowDao.getRckflows(rcktype);
            // 返回前端页面的数据
            List<Rckflowdetail> listFlowdetail = new ArrayList<Rckflowdetail>();
            // 将流程定义表的数据拷贝到流程执行记录表
            if (!listFlow.isEmpty()) {
                // 查询流程执行记录表
                listFlowdetail = rckflowdetailDao.getRckflowdetaiList(cleardate, rcktype);
                // 为空代表未执行，未执行时才拷贝数据
                if (listFlowdetail.isEmpty()) {
                    logger.info("查看历史：对账执行日期：" + cleardate + " 对账类型 ：" + rcktype + "初始化开始！");
                    // 第0条，初始化值
                    RckflowdetailId idinit = new RckflowdetailId();
                    idinit.setCleardate(cleardate);
                    idinit.setRcktype(rcktype);
                    idinit.setRckorder(0);
                    Rckflowdetail rinit = new Rckflowdetail();
                    rinit.setId(idinit);
                    rinit.setFlowname("对账整体状态");
                    rinit.setIsexecute(SystemConstant.BOOLEAN_YES);
                    rinit.setFlowstate(SystemConstant.FLOWSTATE_NOTRUN);
                    // 插入第0条初始数据
                    rckflowdetailDao.save(rinit);
                    listFlowdetail.add(rinit);
                    // 插入第N条初始数据
                    for (Rckflow rckflow : listFlow) {
                        RckflowdetailId id = new RckflowdetailId();
                        id.setCleardate(cleardate);
                        id.setRcktype(rcktype);
                        id.setRckorder(rckflow.getId().getRckorder());
                        Rckflowdetail r = new Rckflowdetail();
                        r.setId(id);
                        r.setFlowname(rckflow.getRemark());
                        r.setFlowstate(SystemConstant.FLOWSTATE_NOTRUN);
                        r.setIsexecute(rckflow.getInitstate());
                        r.setAlowchange(rckflow.getAlowchange());
                        rckflowdetailDao.save(r);
                        listFlowdetail.add(r);
                    }
                    logger.info("对账执行日期：" + cleardate + " 对账类型 ：" + rcktype + "初始化完成！");
                }
            } else {
                throw new ServiceException("对账类型：" + rcktype + "没有定义流程!");
            }
            return listFlowdetail;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            throw new FlowException(e);
        }
    }

    /**
     * 执行对账初始化流程
     *
     * @param cleardate
     * @param rcktype
     * @return
     * @throws ServiceException
     */
    public List<Rckflowdetail> initFlowDetail(String cleardate, String rcktype) throws ServiceException {
        try {
            List<Rckflow> listFlow = rckflowDao.getRckflows(rcktype);
            List<Rckflowdetail> listFlowdetail = new ArrayList<Rckflowdetail>();
            if (!listFlow.isEmpty()) {
                listFlowdetail = rckflowdetailDao.getRckflowdetaiList(cleardate, rcktype);
                if (listFlowdetail.isEmpty()) {// 未执行
                    logger.info("执行对账：对账执行日期：" + cleardate + " 对账类型 ：" + rcktype + "初始化开始！");
                    RckflowdetailId idinit = new RckflowdetailId();
                    idinit.setCleardate(cleardate);
                    idinit.setRcktype(rcktype);
                    idinit.setRckorder(0);
                    Rckflowdetail rinit = new Rckflowdetail();
                    rinit.setId(idinit);
                    rinit.setFlowname("对账整体状态");
                    rinit.setFlowstate(SystemConstant.FLOWSTATE_NOTRUN);
                    rinit.setIsexecute(SystemConstant.BOOLEAN_YES);
                    rckflowdetailDao.save(rinit);
                    listFlowdetail.add(rinit);
                    for (Rckflow rckflow : listFlow) {
                        RckflowdetailId id = new RckflowdetailId();
                        id.setCleardate(cleardate);
                        id.setRcktype(rcktype);
                        id.setRckorder(rckflow.getId().getRckorder());
                        Rckflowdetail r = new Rckflowdetail();
                        r.setId(id);
                        r.setFlowname(rckflow.getRemark());
                        r.setFlowstate(SystemConstant.FLOWSTATE_NOTRUN);
                        r.setIsexecute(rckflow.getInitstate());
                        r.setAlowchange(rckflow.getAlowchange());
                        rckflowdetailDao.save(r);
                        listFlowdetail.add(r);
                    }
                    logger.info("对账执行日期：" + cleardate + " 对账类型 ：" + rcktype + "初始化完成！");
                }
            } else {
                throw new ServiceException("对账类型：" + rcktype + "没有定义流程!");
            }
            return listFlowdetail;
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public List<Rckflowdetail> initFlowDetailBz(String cleardate) throws ServiceException {
        try {
            String rcktype = SystemConstant.RCKTYPE_BZ;
            String info = "对账执行日期：" + cleardate + "对账类型：" + rcktype;
            /**
             * ***********************验证前一天对账信息**************************
             */
            String yesterday = DateTime.getNDayBeforeForDate(cleardate, 1);
            Object object = rckflowdetailDao.rckflowdetailYesterday(yesterday, rcktype);
            if (object != null) {
                Rckflowdetail rd = (Rckflowdetail) object;
                if (!SystemConstant.FLOWSTATE_COMPLETE.equals(rd.getFlowstate())) {
                    logger.error(info + " 前一天：" + yesterday + " 的状态为：" + rd.getFlowstate() + " 对账中止......");
                    throw new ServiceException(info + " 前一天：" + yesterday + " 的状态为：" + rd.getFlowstate() + " 对账中止......");
                }
            } else {
                logger.error(info + " 前一天：" + yesterday + " 未执行,对账中止......");
                throw new ServiceException(info + " 前一天：" + yesterday + " 未执行,对账中止......");
            }

            List<Rckflow> listFlow = rckflowDao.getRckflows(rcktype);
            List<Rckflowdetail> listFlowdetail = new ArrayList<Rckflowdetail>();
            if (!listFlow.isEmpty()) {
                listFlowdetail = rckflowdetailDao.getRckflowdetaiList(
                        cleardate, rcktype);
                if (listFlowdetail.isEmpty()) {// 未执行
                    RckflowdetailId idinit = new RckflowdetailId();
                    idinit.setCleardate(cleardate);
                    idinit.setRcktype(rcktype);
                    idinit.setRckorder(0);
                    Rckflowdetail rinit = new Rckflowdetail();
                    rinit.setId(idinit);
                    rinit.setFlowname("对账整体状态");
                    rinit.setIsexecute(SystemConstant.BOOLEAN_YES);
                    rinit.setFlowstate(SystemConstant.FLOWSTATE_RUNNING);
                    rckflowdetailDao.save(rinit);
                    listFlowdetail.add(rinit);
                    for (Rckflow rckflow : listFlow) {
                        RckflowdetailId id = new RckflowdetailId();
                        id.setCleardate(cleardate);
                        id.setRcktype(rcktype);
                        id.setRckorder(rckflow.getId().getRckorder());
                        Rckflowdetail r = new Rckflowdetail();
                        r.setId(id);
                        r.setFlowname(rckflow.getRemark());
                        r.setFlowstate(SystemConstant.FLOWSTATE_RUNNING);
                        r.setIsexecute(rckflow.getInitstate());
                        r.setAlowchange(rckflow.getAlowchange());
                        rckflowdetailDao.save(r);
                        listFlowdetail.add(r);
                    }
                }
                logger.info("对账执行日期：" + cleardate + " 对账类型 ：" + rcktype
                        + "初始化完成！");
            } else {
                throw new ServiceException("对账类型：" + rcktype + "没有定义流程!");
            }
            return listFlowdetail;
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public void flowLast(String cleardate, String rcktype, String flowstate,String msg) throws ServiceException {
        try {
            String hqlInit = "update Rckflowdetail r set r.flowstate='" + flowstate + "', r.exceptiondesc ='" +msg+"' "+
                    "where r.id.cleardate='" + cleardate + "' and r.id.rcktype='" + rcktype + "' and r.id.rckorder =0 ";
            rckflowdetailDao.hqlExecuteUpdate(hqlInit);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
    }

    public Rckflowdetail getObjectById(RckflowdetailId id) throws ServiceException {
        Rckflowdetail rf = null;
        try {
            Object object = rckflowdetailDao.getObjectById(Rckflowdetail.class, id);
            if (object != null) {
                rf = (Rckflowdetail) object;
            } else {
                throw new ServiceException("对象为空！" + id.getCleardate() + " " + id.getRcktype() + " " + id.getRcktype());
            }
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
        return rf;
    }

    /**
     * **********************************************
     */
    public RckflowdetailDao getRckflowdetailDao() {
        return rckflowdetailDao;
    }

    public void setRckflowdetailDao(RckflowdetailDao rckflowdetailDao) {
        this.rckflowdetailDao = rckflowdetailDao;
    }

    public RckflowDao getRckflowDao() {
        return rckflowDao;
    }

    public void setRckflowDao(RckflowDao rckflowDao) {
        this.rckflowDao = rckflowDao;
    }

    public boolean flowFirst(String cleardate, String rcktype) throws ServiceException {
        boolean flag = false;
        String n = SystemConstant.FLOWSTATE_NOTRUN;
        String e = SystemConstant.FLOWSTATE_EXCEPTION;
        String r = SystemConstant.FLOWSTATE_RUNNING;
        String c = SystemConstant.FLOWSTATE_COMPLETE;
        try {
            String info = "对账执行日期：" + cleardate + "，对账类型：" + rcktype;
            logger.info(info + " 执行对账");
            /**
             * ***********************验证所有对账模块**************************
             */
            List<Rckflowdetail> list = rckflowdetailDao.countAllRunning(rcktype);
            if (!list.isEmpty() && list.size() > 0) {
                StringBuffer sb = new StringBuffer();
                for (Rckflowdetail rd : list) {
                    sb.append(" 对账执行日期：").append(rd.getId().getCleardate()).append(" 对账类型：" + rd.getId().getRcktype() + "正在运行!" + "\n");
                }
                logger.error(sb.toString());
                throw new ServiceException(sb.toString());
            }
            /**
             * ***********************验证前一天对账信息**************************
             */
            String yesterday = DateTime.getNDayBeforeForDate(cleardate, 1);
            Object object = rckflowdetailDao.rckflowdetailYesterday(yesterday, rcktype);
            if (object != null) {
                Rckflowdetail rd = (Rckflowdetail) object;
                if (!c.equals(rd.getFlowstate())) {
                    logger.error(info + " 前一天：" + yesterday + " 的状态为：" + rd.getFlowstate() + " 对账中止......");
                    throw new ServiceException(info + " 前一天：" + yesterday + " 的状态为：" + rd.getFlowstate() + " 对账中止......");
                } else {
                    logger.error(info + " 前一天：" + yesterday + " 的状态为：" + rd.getFlowstate() + " 对账成功......");
                }
            } else {
                logger.error(info + " 前一天：" + yesterday + " 未执行,对账中止......");
                throw new ServiceException(info + " 前一天：" + yesterday + " 未执行,对账中止......");
            }
            /**
             * ***********************验证当天对账信息**************************
             */
            RckflowdetailId id = new RckflowdetailId(cleardate, rcktype, 0);
            Rckflowdetail temp = (Rckflowdetail) rckflowdetailDao.getObjectById(Rckflowdetail.class, id);
            String flowstate = temp.getFlowstate();
            if (n.equals(flowstate) || e.equals(flowstate)) {
                //更新取得优先权
                String hqlInit = "update Rckflowdetail r set r.flowstate='" + r + "', r.exceptiondesc ='' "+
                        "where r.id.cleardate='" + cleardate + "' and r.id.rcktype='" + rcktype + "' and r.id.rckorder =0 and r.flowstate in ('" + n + "','" + e + "')";
                int result = rckflowdetailDao.hqlExecuteUpdate(hqlInit);
                if (result > 0) {
                    flag = true;
                }
            } else if (r.equals(flowstate)) {
                logger.info(info + "对账正在运行!对账中止......");
                throw new ServiceException(info + "对账正在运行!对账中止......");
            } else if (c.equals(flowstate)) {
                logger.info(info + "对账已正常运行完成!对账中止......");
                throw new ServiceException(info + "对账已正常运行完成!对账中止......");
            } else {
                logger.error(info + "对账未知状态!对账中止......");
                throw new ServiceException(info + "对账未知状态!对账中止......");
            }
        } catch (Exception e1) {
            logger.error(e1.getMessage(), e1);
            if (e1 instanceof ServiceException) {
                throw (ServiceException) e1;
            } else {
                e1.printStackTrace();
                throw new ServiceException(e1);
            }
        }
        return flag;
    }

    public void updateFlowstate(String cleardate, String rcktype, int rckorder,
                                String flowstate) throws ServiceException {
        String hqlInit = "update Rckflowdetail r set r.flowstate='" + flowstate + "'  " +
                "where r.id.cleardate='" + cleardate + "' and r.id.rcktype='" + rcktype + "' and r.id.rckorder =" + rckorder + " ";
        rckflowdetailDao.hqlExecuteUpdate(hqlInit);
    }

    public void changeExecute(String cleardate, String rcktype, int rckorder) throws ServiceException {
        logger.info("cleardate, rcktype, rckorder" + cleardate + rcktype + rckorder);
        Rckflowdetail rf = this.getObjectById(new RckflowdetailId(cleardate, rcktype, rckorder));
        logger.info("rf" + rf);
        logger.info("rf.getAlowchange()" + rf.getAlowchange());
        if (SystemConstant.BOOLEAN_YES.equals(rf.getAlowchange())) {
            if (SystemConstant.BOOLEAN_NO.equals(rf.getIsexecute())) {
                rf.setIsexecute(SystemConstant.BOOLEAN_YES);
            } else {
                rf.setIsexecute(SystemConstant.BOOLEAN_NO);
            }
            rckflowdetailDao.save(rf);
        } else {
            throw new ServiceException("不允许更改可执行状态！");
        }
    }

    /**
     * 单独执行调用方法
     *
     * @param cleardate
     * @param rcktype
     * @param rckorder
     * @throws ServiceException
     */
    public void singleExt(String cleardate, String rcktype, int rckorder)
            throws ServiceException {
        RckflowId id = new RckflowId(rcktype, rckorder);
        Rckflow rckflow = (Rckflow) rckflowDao.getObjectById(Rckflow.class, id);

        if (null != rckflow && SystemConstant.BOOLEAN_YES.equals(rckflow.getSingleext())) {
        	final FlowInterface f = (FlowInterface) AppUtil.getBean(rckflow.getBeanid());
            RckflowdetailId detailId = new RckflowdetailId(cleardate, rcktype, rckorder);
            final Rckflowdetail detail = (Rckflowdetail) rckflowdetailDao.getObjectById(Rckflowdetail.class, detailId);
            detail.setMsg(rckflow.getMsg());
            detail.setReconType(BusinessConstant.IMP_TYPE.ALL);
            Thread thread=new Thread(new Runnable() {
            	public void run() {
            		try {
            			f.execute(detail);
            		} catch (com.froad.comon.exception.ServiceException e) {
            			logger.error(e.getErrorMsg(),e);
            		} catch (FlowException e) {
            			logger.error(e.getMessage(),e);
            		}
            	}
            });
            thread.start();
        } else {
            throw new ServiceException("对账日期：" + cleardate + ", 对账类型：" + rcktype + ", 对账步骤：" + rckorder + ", 不允许单独执行！");
        }
    }
}
