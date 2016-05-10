package com.froad.flow.impl;

import com.froad.beans.Rckflowdetail;
import com.froad.comon.SystemConstant;
import com.froad.flow.FlowException;
import com.froad.flow.FlowInterface;
import com.froad.comon.util.Logger;
import org.springframework.stereotype.Component;

@Component("flowRckfinanc")
public class FlowRckfinanc implements FlowInterface {
    private final static Logger logger = Logger.getLogger(FlowRckfinanc.class);
    /*@Autowired
	private RckfinancService rckfinancService;*/

    public Rckflowdetail execute(Rckflowdetail rckflowdetail) throws FlowException {
        logger.info(SystemConstant.SIGN + "理财对账服务开始");
		/*try {
			rckfinancService.rckExecute(rckflowdetail);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FlowException(rckflowdetail, e);
		}*/
        return rckflowdetail;

    }
}
