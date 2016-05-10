package com.froad.flow.impl;

import com.froad.beans.Rckflowdetail;
import com.froad.flow.FlowException;
import com.froad.flow.FlowInterface;
import com.froad.comon.util.Logger;
import org.springframework.stereotype.Component;

/**
 * 核心文件下载导入
 *
 * @author silence
 */
@Component("flowFileAs400")
public class FlowFileAs400 implements FlowInterface {
    private final static Logger logger = Logger.getLogger(FlowFileAs400.class);
    /*@Autowired
	private FileAs400Service fileAs400Service;*/

    public Rckflowdetail execute(Rckflowdetail rckflowdetail) throws FlowException {
        logger.info("核心文件（AS400）服务.");
		/*try {
			fileAs400Service.executeDownload(rckflowdetail);
			fileAs400Service.executeImport(rckflowdetail);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new FlowException(rckflowdetail, e);
		}*/
        return rckflowdetail;
    }
}
