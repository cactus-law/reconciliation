package com.froad.flow.impl;

import com.froad.beans.Rckflowdetail;
import com.froad.flow.FlowException;
import com.froad.flow.FlowInterface;
import com.froad.comon.util.Logger;
import org.springframework.stereotype.Component;

/**
 * 清算汇总
 *
 * @author silence
 */
@Component("flowSummAcoma")
public class FlowSummAcoma implements FlowInterface {
    private final static Logger logger = Logger.getLogger(FlowSummAcoma.class);
/*	@Autowired
    private RckremitService rckremitService;*/

    public Rckflowdetail execute(Rckflowdetail rckflowdetail) throws FlowException {
        logger.info("清算汇总流程开始！");
/*		try {
			rckflowdetail=rckremitService.gather(rckflowdetail);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FlowException(rckflowdetail, e);
		}*/
        return rckflowdetail;
    }
}
