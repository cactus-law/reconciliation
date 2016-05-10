package com.froad.flow.impl;

import com.froad.beans.Rckflowdetail;
import com.froad.flow.FlowException;
import com.froad.flow.FlowInterface;
import com.froad.comon.util.Logger;
import org.springframework.stereotype.Component;

/**
 * 清算划账
 *
 * @author silence
 */
@Component("flowRemit")
public class FlowRemit implements FlowInterface {
    private final static Logger logger = Logger.getLogger(FlowRemit.class);

    public Rckflowdetail execute(Rckflowdetail rckflowdetail) throws FlowException {
        logger.info("清算划账流程开始!");
/*		try {
            boolean flag=wokedateService.isWokedate();//判断工作日
			if(flag){
				rckflowdetail=rckremitService.remit(rckflowdetail);
				rckremitService.remitHisdeal(rckflowdetail);
			}else{
				logger.info("非工作日不进行划账.");
			}
		} catch (Exception e) {
			if(e instanceof ServiceException){
			}else{
				e.printStackTrace();
				logger.error(e);
			}
			throw new FlowException(rckflowdetail, e);
		}*/
        return rckflowdetail;
    }
}
