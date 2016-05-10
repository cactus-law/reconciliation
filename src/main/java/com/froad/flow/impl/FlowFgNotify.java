package com.froad.flow.impl;

import com.froad.beans.Rckflowdetail;
import com.froad.flow.FlowException;
import com.froad.flow.FlowInterface;
import com.froad.comon.util.Logger;
import org.springframework.stereotype.Component;

/**
 * 非改对账文件生成、上传、通知
 *
 * @author silence
 */
@Component("flowFgNotify")
public class FlowFgNotify implements FlowInterface {
    private final static Logger logger = Logger.getLogger(FlowFgNotify.class);

    public Rckflowdetail execute(Rckflowdetail rckflowdetail) throws FlowException {
        logger.info("非改对账文件生成、上传、通知");
        /*try {
			rileFgTradeService.fgRckfile(rckflowdetail);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FlowException(rckflowdetail, e);
		}*/
        return rckflowdetail;
    }
}
