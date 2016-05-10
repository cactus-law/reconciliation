package com.froad.flow.handler;


import com.froad.beans.Rckflowdetail;
import com.froad.beans.RckflowdetailId;
import com.froad.comon.SystemConstant;
import com.froad.comon.constant.BusinessConstant.IMP_TYPE;
import com.froad.comon.util.DateTime;
import com.froad.comon.util.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * 对账定时调度程序
 */
public class TaskQuartzRck {
    private final static Logger logger = Logger.getLogger(TaskQuartzRck.class);
    @Autowired
    private TaskQuartzSocket taskQuartzSocket;
    @Autowired
    private RckFlowThread rckFlowThread;

    /**
     * 自动对账
     */
    public void quartzRecon() {
        logger.info("=======================执行清算定时调度程序RECON" + new Date() + "=======================");
        this.commonRck(DateTime.GetData(), SystemConstant.RECONTYPE_RECON,IMP_TYPE.ALL);
    }

    /**
     * 管理平台通过SOCKET调度清算
     */

    public void execute() {
        Thread thread = new Thread(taskQuartzSocket);
        thread.start();
        logger.info("TaskScheduleSocket线程启动状态：" + thread.isAlive());
        logger.info("TaskScheduleSocket线程是否中断：" + thread.isInterrupted());
    }

    /**
     * 入口方法
     *
     * @param cleardate 清算日期
     * @param rcktype   Constant.RCKTYPE 清算类型
     * @param auto 
     */
    public void commonRck(String cleardate, String rcktype, String reconType) {
        logger.info("流程类型：" + rcktype + " 日期：" + cleardate);
        RckflowdetailId id = new RckflowdetailId(cleardate, rcktype, 0);
        Rckflowdetail rckflowdetail = new Rckflowdetail(id);
        rckFlowThread.setRckflowdetail(rckflowdetail);
        rckflowdetail.setReconType(reconType);
        Thread thread = new Thread(rckFlowThread);
        thread.start();
        logger.info("RckFlowThread线程启动状态：" + thread.isAlive());
        logger.info("RckFlowThread线程是否中断：" + thread.isInterrupted());
    }


}