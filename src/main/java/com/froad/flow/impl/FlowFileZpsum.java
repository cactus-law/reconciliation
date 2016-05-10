package com.froad.flow.impl;

import com.froad.beans.Rckflowdetail;
import com.froad.flow.FlowException;
import com.froad.flow.FlowInterface;
import com.froad.comon.util.Logger;
import org.springframework.stereotype.Component;

/**
 * 直联文件ZPSUM下载导入
 *
 * @author silence
 */
@Component("flowFileZpsum")
public class FlowFileZpsum implements FlowInterface {
    private final static Logger logger = Logger.getLogger(FlowFileZpsum.class);
    /*@Autowired
	private FileZpsumService fileZpsumService;*/

    public Rckflowdetail execute(Rckflowdetail rckflowdetail) throws FlowException {
        logger.info("Zpsum文件服务.");
		/*try {
			fileZpsumService.executeDownload(rckflowdetail);
			fileZpsumService.executeImport(rckflowdetail);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new FlowException(rckflowdetail, e);
		}*/
        return rckflowdetail;
    }
}
