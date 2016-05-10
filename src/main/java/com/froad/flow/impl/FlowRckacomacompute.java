package com.froad.flow.impl;

import com.froad.beans.Rckflowdetail;
import com.froad.flow.FlowException;
import com.froad.flow.FlowInterface;
import com.froad.comon.util.Logger;
import org.springframework.stereotype.Component;

/**
 * 手续费计算
 *
 * @author silence
 */
@Component("flowRckacomacompute")
public class FlowRckacomacompute implements FlowInterface {
    private final static Logger logger = Logger.getLogger(FlowRckacomacompute.class);
    /*@Autowired
	private RckacomacomputeService rckacomacomputeService;*/

    public Rckflowdetail execute(Rckflowdetail rckflowdetail) throws FlowException {
        logger.info("手续费清算服务.");
		/*try {
			rckacomacomputeService.acomaComputeFees(rckflowdetail);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FlowException(rckflowdetail, e);
		}*/
        return rckflowdetail;
    }
}
