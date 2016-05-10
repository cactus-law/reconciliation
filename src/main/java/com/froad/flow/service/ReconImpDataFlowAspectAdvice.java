package com.froad.flow.service;

import java.sql.Timestamp;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.froad.beans.Rckflowdetail;
import com.froad.comon.constant.BusinessConstant;
import com.froad.flow.FlowException;
import com.froad.flow.dao.RckflowdetailDao;
import com.froad.recon.importfile.model.IimpStatusDetail;
import com.froad.recon.importfile.model.IimpStatusDetailId;
import com.froad.recon.importfile.service.IimpStatusDetailService;
import com.froad.recon.sys.model.PlatformDetail;
import com.froad.recon.sys.service.PlatformDetailService;

/**
 * 定义切面
 *
 * @Aspect : 标记为切面类
 * @Pointcut : 指定匹配切点
 * @Before : 指定前置通知，value中指定切入点匹配
 * @AfterReturning ：后置通知，具有可以指定返回值
 * @AfterThrowing ：异常通知
 */
//@Component("reconImpDataFlowAspectAdvice")
//@Aspect
public class ReconImpDataFlowAspectAdvice {
	
    final static Logger logger = LoggerFactory.getLogger(ReconImpDataFlowAspectAdvice.class);
    
    @Autowired
    private RckflowdetailDao rckflowdetailDao;

    @Autowired
	private PlatformDetailService platformDetailService;
    
    @Autowired
    private IimpStatusDetailService iimpStatusDetailService;
    
    /**
     * 指定切入点匹配表达式，注意它是以方法的形式进行声明的。
     */
    //@Pointcut(value = "execution(* com.froad.flow.impl.points.*.exe*(..))")
    public void pointcut() {
        logger.info("@Pointcut(execution(* com.froad.flow.impl.points.*.exe*(..))");
    }

    /**
     * 前置通知
     *
     * @param jp
     */
    //@Before(value="pointcut()")
    public void doBefore(JoinPoint jp) {
    	
    	logger.info("前置通知开始.");

		try {
				Rckflowdetail rckflowdetail = (Rckflowdetail) jp.getArgs()[0];
				PlatformDetail platformDetail = platformDetailService.getById(rckflowdetail.getMsg());
				
				String transDate = rckflowdetail.getId().getCleardate();
		        IimpStatusDetail impStatusDetail = new IimpStatusDetail();
		    	IimpStatusDetailId id = new IimpStatusDetailId();
		    	id.setPlatformDetailNo(platformDetail.getPlatformDetailNo());//平台明细编号
		    	id.setImpDate(transDate);//导入日期
				impStatusDetail.setId(id);//联合主键
				//平台明细名称
				impStatusDetail.setPlatformDetailName(platformDetail.getPlatformDetailName());
				impStatusDetail.setImpType(rckflowdetail.getReconType());//导入类型
				impStatusDetail.setDownloadUrl("");//文件存放物理路径
				impStatusDetail.setCreateTime(new Timestamp(System.currentTimeMillis()));//创建时间
				
				impStatusDetail.setStatus(BusinessConstant.IMP_STATUS.INITIAL);//状态
				iimpStatusDetailService.insert(impStatusDetail);
				rckflowdetail.setIimpStatusDetail(impStatusDetail);
				
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
        logger.info("前置通知结束.");
        
    }

    /**
     * 后置通知
     *
     * @param jp     连接点
     * @param result 返回值
     */
    //@AfterReturning(value = "pointcut()", returning = "result")
    public void doAfter(JoinPoint jp, Object result) {

        logger.info("后置通知开始");

        try {
        	Rckflowdetail rckflowdetail = (Rckflowdetail) jp.getArgs()[0];
            IimpStatusDetail iimpStatusDetail = rckflowdetail.getIimpStatusDetail();
			this.iimpStatusDetailService.update(iimpStatusDetail);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
        
        logger.info("后置通知结束");

    }
    
    /**
     * 异常通知
     *
     * @param jp
     * @param e
     */
    //@AfterThrowing(value="pointcut()", throwing="e")
    public void doThrow(JoinPoint jp, FlowException e) {
    	
    	logger.info("异常通知开始");

        try {
        	Rckflowdetail rckflowdetail = (Rckflowdetail) jp.getArgs()[0];
            IimpStatusDetail iimpStatusDetail = rckflowdetail.getIimpStatusDetail();
        	iimpStatusDetail.setStatus(BusinessConstant.IMP_STATUS.FAIL);//状态
			this.iimpStatusDetailService.update(iimpStatusDetail);
		} catch (Exception e2) {
			logger.error(e.getMessage(), e2);
		}
        
        logger.info("异常通知结束");
        
    }
}
