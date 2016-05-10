package com.froad.flow.impl;

import com.froad.beans.Rckflowdetail;
import com.froad.flow.FlowException;
import com.froad.flow.FlowInterface;
import com.froad.comon.util.Logger;
import org.springframework.stereotype.Component;

/**
 * 银联文件ACOMA下载导入
 *
 * @author choky
 */
@Component("flowFileAcoma")
public class FlowFileAcoma implements FlowInterface {
    private final static Logger logger = Logger.getLogger(FlowFileAcoma.class);
/*	@Autowired
    private FileAcomAService fileAcomAService;*/

    /* 通过FTP直接下载银联文件，远程存放目录和本地存放目录由RCKFILELIST存放
     * @see com.froad.flow.FlowInterface#execute(com.froad.flow.FlowParams)
     */
    public Rckflowdetail execute(Rckflowdetail rckflowdetail) throws FlowException {
        logger.info("银联（ACOMA）文件服务.");
        logger.info("清算日期" + rckflowdetail.getId().getCleardate());
        logger.info("清算类型" + rckflowdetail.getId().getRcktype());
		/*try {
			fileAcomAService.executeDownload(rckflowdetail);
			fileAcomAService.executeImport(rckflowdetail);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("银联(ACOMA)文件处理异常："+e.getMessage());
			throw new FlowException(rckflowdetail, e);
		}*/
        return rckflowdetail;
    }

}
