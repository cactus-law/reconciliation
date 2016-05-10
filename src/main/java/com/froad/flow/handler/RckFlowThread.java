package com.froad.flow.handler;

import java.util.List;

import com.froad.comon.SystemConstant;
import com.froad.comon.util.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.froad.beans.Rckflow;
import com.froad.beans.Rckflowdetail;
import com.froad.beans.RckflowdetailId;
import com.froad.comon.util.AppUtil;
import com.froad.comon.util.CommonUtil;
import com.froad.comon.util.ErrorMsgUtil;
import com.froad.comon.util.PropertyUtil;
import com.froad.comon.mail.MailSenderInfo;
import com.froad.comon.mail.SimpleMailSender;
import com.froad.flow.FlowInterface;
import com.froad.flow.service.RckflowService;
import com.froad.flow.service.RckflowdetailService;

/**
 * 总调试口
 *
 * @author silence
 */
@Component("rckFlowThread")
public class RckFlowThread implements Runnable {
    private static final Logger logger = Logger.getLogger(RckFlowThread.class);
    public Rckflowdetail rckflowdetail;
    @Autowired
    private RckflowService rckflowService;
    @Autowired
    private RckflowdetailService rckflowdetailService;

    public void run() {
        //判断是哪种对账
        RckflowdetailId id = rckflowdetail.getId();
        String reconType=    rckflowdetail.getReconType();
        String rcktype = id.getRcktype();
        String cleardate = id.getCleardate();
        try {
            // 初始化流程数据
            rckflowdetailService.initFlowDetail(cleardate, rcktype);
            // 第一步验证(true:无正在运行的流程,前一天运行已完成,取得更新优先权)
            boolean flag = rckflowdetailService.flowFirst(cleardate, rcktype);
            if (flag) {
                //流程流水初始化成功
                logger.info("执行流程,对账日期：" + cleardate + " 对账类型：" + rcktype);
                //执行流程
                List<Rckflow> listRckflows = rckflowService.getRckflows(rcktype);
                //对账总体状态
                String flowstate=SystemConstant.FLOWSTATE_COMPLETE;
                for (Rckflow r : listRckflows) {
                    FlowInterface f = (FlowInterface) AppUtil.getBean(r.getBeanid());
                    id.setRckorder(r.getId().getRckorder());
                    rckflowdetail = rckflowdetailService.getObjectById(id);
                    rckflowdetail.setMsg(r.getMsg());
                    rckflowdetail.setReconType(reconType);
                    // 判断是否执行
                    boolean isExecute = SystemConstant.BOOLEAN_YES.equals(rckflowdetail.getIsexecute());
                    if (isExecute) {
                        // 处理单步异常是否影响整体执行结果
                        if (!SystemConstant.BOOLEAN_YES.equals(r.getExpcont())) { // 影响
                            f.execute(rckflowdetail);
                        } else {
                            try {
                                f.execute(rckflowdetail);
                            } catch (Exception e) {
//                            	flowstate=SystemConstant.FLOWSTATE_EXCEPTION;
                                e.printStackTrace();
                                logger.error(e);
                            }
                        }
                    } else {
                        logger.info("此步骤不执行，步骤：" + rckflowdetail.getId().getRckorder() + "  模块名称：" + rckflowdetail.getFlowname());
                    }
                }
                rckflowdetailService.flowLast(cleardate, rcktype, flowstate,"");
            }
        } catch (Exception e) {
            try {
            	String msg=ErrorMsgUtil.printMsg(e);
                rckflowdetailService.flowLast(cleardate, rcktype, SystemConstant.FLOWSTATE_EXCEPTION,msg);
            } catch (Exception e1) {
            	  logger.error(e1);
            }
            //增加邮件发送功能
            String message = "对账日期：" + cleardate + " 对账类型：" + rcktype + "\n异常信息:" + CommonUtil.cutExceptionStr(e.getMessage(), 500);
            MailSenderInfo mailInfo = new MailSenderInfo();
            mailInfo.setToAddress(PropertyUtil.getProperties("mailToAddress"));
            mailInfo.setSubject("日期：" + cleardate + " 类型：" + rcktype + " 对账异常");
            mailInfo.setContent(message);
            SimpleMailSender.sendTextMail(mailInfo);
            logger.error(e.getMessage(),e);
        }
    }

    public Rckflowdetail getRckflowdetail() {
        return rckflowdetail;
    }

    public void setRckflowdetail(Rckflowdetail rckflowdetail) {
        this.rckflowdetail = rckflowdetail;
    }

    public RckflowService getRckflowService() {
        return rckflowService;
    }

    public void setRckflowService(RckflowService rckflowService) {
        this.rckflowService = rckflowService;
    }

    public RckflowdetailService getRckflowdetailService() {
        return rckflowdetailService;
    }

    public void setRckflowdetailService(RckflowdetailService rckflowdetailService) {
        this.rckflowdetailService = rckflowdetailService;
    }

}
