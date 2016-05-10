package com.froad.flow.service;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.froad.beans.Rckflowdetail;
import com.froad.comon.SystemConstant;
import com.froad.comon.mail.MailSenderInfo;
import com.froad.comon.mail.SimpleMailSender;
import com.froad.comon.util.CommonUtil;
import com.froad.comon.util.PropertyUtil;
import com.froad.flow.FlowException;
import com.froad.flow.dao.RckflowdetailDao;

import freemarker.template.utility.StringUtil;

/**
 * 定义切面
 *
 * @Aspect : 标记为切面类
 * @Pointcut : 指定匹配切点
 * @Before : 指定前置通知，value中指定切入点匹配
 * @AfterReturning ：后置通知，具有可以指定返回值
 * @AfterThrowing ：异常通知
 */
@Component
@Aspect
public class FlowAspectAdvice {

    final static Logger logger = LoggerFactory.getLogger(FlowAspectAdvice.class);
    
    @Autowired
    private RckflowdetailDao rckflowdetailDao;

    /**
     * 指定切入点匹配表达式，注意它是以方法的形式进行声明的。
     */
    @Pointcut(value = "execution(* com.froad.flow.impl..*.execute(..)) || execution(* com.froad.recon.reconciliation.flow.*.execute(..))||execution(* com.froad.recon.importfile.flow.*.exe*(..))|| execution(* com.froad.recon.sync.flow.*.execute(..))")
    public void anyMethod() {
        logger.info("@Pointcut(execution(* com.froad.flow.impl..*.*(..))");
    }

    /**
     * 前置通知
     *
     * @param jp
     */
    @Before(value = "anyMethod()")
    public void doBefore(JoinPoint jp) {
        /*StringBuilder before=new StringBuilder("===========前置通知============");
    	Object objects[]=jp.getArgs();
    	for (Object object : objects) {
			logger.info("obj arg"+object);
		}
    	before.append("\r\n")
    	.append("类名：").append(jp.getTarget().getClass().getSimpleName())
    	.append(".").append(jp.getSignature().getName());
    	logger.info(before.toString());*/
        //logger.info("---------前置通知开始");
        Rckflowdetail rckflowdetail = (Rckflowdetail) jp.getArgs()[0];
        rckflowdetail.setFlowstate(SystemConstant.FLOWSTATE_RUNNING);
        rckflowdetail.setDealdatetime(new Date());
        rckflowdetailDao.update(rckflowdetail);
        //logger.info("---------前置通知结束");
    }

    /**
     * 后置通知
     *
     * @param jp     连接点
     * @param result 返回值
     */
    @AfterReturning(value = "anyMethod()", returning = "result")
    public void doAfter(JoinPoint jp, Object result) {
    	/*StringBuilder after=new StringBuilder("===========后置通知============");
    	after.append("\r\n")
    	.append("类名：").append(jp.getTarget().getClass().getSimpleName())
    	.append(".").append(jp.getSignature().getName()).append("\r\n")
    	.append("参数：").append(jp.getArgs()[0]).append("\r\n")
    	.append("结果：").append(result);
    	logger.info(after.toString());*/
        //logger.info("---------后置通知开始");
        Rckflowdetail rckflowdetail = (Rckflowdetail) jp.getArgs()[0];
        rckflowdetail.setFlowstate(SystemConstant.FLOWSTATE_COMPLETE);
      
        rckflowdetailDao.update(rckflowdetail);
        //logger.info("---------后置通知结束");

    }

    /**
     * 异常处理页面
     *
     * @param jp
     * @param e
     */
    @AfterThrowing(value = "anyMethod()", throwing = "e")
    public void doThrow(JoinPoint jp, FlowException e) {
        logger.error("清算异常：****************************");
        Rckflowdetail  rckflowdetail = (Rckflowdetail) jp.getArgs()[0];
        
        String cleardate = rckflowdetail.getId().getCleardate();
		logger.error("清算日期：" + cleardate);
        String rcktype = rckflowdetail.getId().getRcktype();
		logger.error("异常模块：" + rcktype);
        rckflowdetail.setExceptiondesc(CommonUtil.cutStr(e.getMessage(), 2048));
        rckflowdetail.setFlowstate(SystemConstant.FLOWSTATE_EXCEPTION);
        rckflowdetailDao.update(rckflowdetail);
        logger.error("异常步骤：" + jp.getTarget().getClass().getName());
        logger.error(e.getMessage(), e);
        logger.error("清算异常：****************************");
        //单步执行异常增加邮件发送功能
        String message = "清算日期：" + cleardate + " 清算类型：" + rcktype + "\n异常步骤：" + jp.getTarget().getClass().getName() + "\n异常信息:" + e.getMessage();
        MailSenderInfo mailInfo=new MailSenderInfo();
		mailInfo.setToAddress(PropertyUtil.getProperties("mailToAddress"));
		mailInfo.setSubject("日期：" + cleardate + " 类型：" + rcktype +" 单步执行清算异常");
		mailInfo.setContent(message);
		SimpleMailSender.sendTextMail(mailInfo);
    }
}
