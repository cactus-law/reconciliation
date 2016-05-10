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
@Component("flowComacomputeAcoma")
public class FlowComacomputeAcoma implements FlowInterface {
    private final static Logger logger = Logger.getLogger(FlowComacomputeAcoma.class);

    public Rckflowdetail execute(Rckflowdetail rckflowdetail) throws FlowException {
        logger.info("手续费清算服务.");
        /*try {
			int counts=rckacomacomputeService.liquidateFees(rckflowdetail);
			rckflowdetail.setDealcount(counts);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FlowException(rckflowdetail, e);
		}*/
        return rckflowdetail;
    }
}
