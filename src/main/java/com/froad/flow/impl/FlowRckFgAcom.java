package com.froad.flow.impl;

import com.froad.beans.Rckflowdetail;
import com.froad.flow.FlowException;
import com.froad.flow.FlowInterface;
import com.froad.comon.util.Logger;
import org.springframework.stereotype.Component;

/**
 * 非税对账
 *
 * @author silence
 */
@Component("flowRckFgAcom")
public class FlowRckFgAcom implements FlowInterface {
    private final static Logger logger = Logger.getLogger(FlowRckFgAcom.class);
/*	@Autowired
    private RckAcomAService rckAcomAService;
	@Autowired
	private RcknontaxService rcknontaxService;*/

    public Rckflowdetail execute(Rckflowdetail rckflowdetail) throws FlowException {
        logger.info("非税对账服务.");
		/*try {
			rckAcomAService.rckFg(rckflowdetail);
			rcknontaxService.rcknontaxhisDeal(rckflowdetail);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FlowException(rckflowdetail, e);
		}*/
        return rckflowdetail;
    }

}
