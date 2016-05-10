package com.froad.flow.impl;

import com.froad.beans.Rckflowdetail;
import com.froad.flow.FlowException;
import com.froad.flow.FlowInterface;
import com.froad.comon.util.Logger;
import org.springframework.stereotype.Component;

/**
 * FGTRADE 文件下载导入
 *
 * @author silence
 */
@Component("flowFileFgTrade")
public class FlowFileFgTrade implements FlowInterface {
    private final static Logger logger = Logger.getLogger(FlowFileFgTrade.class);

    /*@Autowired
    private FileFgTradeService fileFgTradeService;*/
    public Rckflowdetail execute(Rckflowdetail rckflowdetail) throws FlowException {
        logger.info("FGTrade文件服务.");
        /*try {
			fileFgTradeService.executeDownload(rckflowdetail);
			fileFgTradeService.executeImport(rckflowdetail);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new FlowException(rckflowdetail, e);
		}*/
        return rckflowdetail;
    }

}
