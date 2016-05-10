package com.froad.flow.impl;

import com.froad.beans.Rckflowdetail;
import com.froad.flow.FlowException;
import com.froad.flow.FlowInterface;
import com.froad.comon.util.Logger;
import org.springframework.stereotype.Component;

/**
 * 银联文件ACOM下载导入
 *
 * @author choky
 */
@Component("flowFileAcom")
public class FlowFileAcom implements FlowInterface {
    private final static Logger logger = Logger.getLogger(FlowFileAcom.class);
/*	@Autowired
    private FileAcomService fileacomService;*/

    /* 通过FTP直接下载银联文件，远程存放目录和本地存放目录由RCKFILELIST存放
     * @see com.froad.flow.FlowInterface#execute(com.froad.flow.FlowParams)
     */
    public Rckflowdetail execute(Rckflowdetail rckflowdetail) throws FlowException {
        logger.info("银联（ACOM）文件服务.");
		/*try {
			fileacomService.executeDownload(rckflowdetail);
			fileacomService.executeImport(rckflowdetail);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("银联(ACOM)文件处理异常："+e.getMessage());
			throw new FlowException(rckflowdetail, e);
		}*/
        return rckflowdetail;
    }


}
