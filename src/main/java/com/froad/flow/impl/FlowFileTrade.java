package com.froad.flow.impl;

import com.froad.beans.Rckflowdetail;
import com.froad.flow.FlowException;
import com.froad.flow.FlowInterface;
import com.froad.comon.util.Logger;
import org.springframework.stereotype.Component;

/**
 * TRADE 文件下载导入
 *
 * @author silence
 */
@Component("flowFileTrade")
public class FlowFileTrade implements FlowInterface {
    private final static Logger logger = Logger.getLogger(FlowFileTrade.class);

    /*@Autowired
    private FileTradeService fileTradeService;*/
    public Rckflowdetail execute(Rckflowdetail rckflowdetail) throws FlowException {
        logger.info("Trade文件服务.");
        /*try {
			fileTradeService.executeDownload(rckflowdetail);
			fileTradeService.executeImport(rckflowdetail);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new FlowException(rckflowdetail, e);
		}*/
        return rckflowdetail;
    }

}
