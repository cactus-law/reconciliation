package com.froad.flow.impl;

import com.froad.beans.Rckflowdetail;
import com.froad.comon.SystemConstant;
import com.froad.flow.FlowException;
import com.froad.flow.FlowInterface;
import com.froad.comon.util.Logger;
import org.springframework.stereotype.Component;

/**
 * 理财 TRADE 文件下载导入
 *
 * @author silence
 */
@Component("flowFileLcTrade")
public class FlowFileLcTrade implements FlowInterface {
    private final static Logger logger = Logger.getLogger(FlowFileLcTrade.class);

    //@Autowired
    //private FileFinancTradeService fileFinancTradeService;
    public Rckflowdetail execute(Rckflowdetail rckflowdetail) throws FlowException {
        logger.info(SystemConstant.SIGN + "LC TRADE 文件下载导入服务");
        /*try {
			fileFinancTradeService.executeDownload(rckflowdetail);
			fileFinancTradeService.executeImport(rckflowdetail);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new FlowException(rckflowdetail, e);
		}*/
        return rckflowdetail;
    }

}
