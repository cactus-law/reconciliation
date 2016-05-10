package com.froad.flow.impl;

import com.froad.beans.Rckflowdetail;
import com.froad.comon.SystemConstant;
import com.froad.flow.FlowException;
import com.froad.flow.FlowInterface;
import com.froad.comon.util.Logger;
import org.springframework.stereotype.Component;

/**
 * 理财核心文件下载导入
 *
 * @author silence
 */
@Component("flowFileAs400Lc")
public class FlowFileAs400Lc implements FlowInterface {
    private final static Logger logger = Logger.getLogger(FlowFileAs400Lc.class);
    //@Autowired
    //private Rckas400LcService rckas400LcService;

    public Rckflowdetail execute(Rckflowdetail rckflowdetail) throws FlowException {
        logger.info(SystemConstant.SIGN + "理财核心文件服务开始");
        /*try {
			rckas400LcService.executeDownload(rckflowdetail);
			rckas400LcService.executeImport(rckflowdetail);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new FlowException(rckflowdetail, e);
		}*/
        logger.info(SystemConstant.SIGN + "理财核心文件服务结束");
        return rckflowdetail;
    }
}
