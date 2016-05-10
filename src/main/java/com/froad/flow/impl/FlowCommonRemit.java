package com.froad.flow.impl;

import com.froad.beans.Rckflowdetail;
import com.froad.comon.SystemConstant;
import com.froad.flow.FlowException;
import com.froad.flow.FlowInterface;
import com.froad.comon.util.Logger;
import org.springframework.stereotype.Component;

/**
 * 清算划账
 *
 * @author silence
 */
@Component("flowCommonRemit")
public class FlowCommonRemit implements FlowInterface {
    private final static Logger logger = Logger.getLogger(FlowCommonRemit.class);

    public Rckflowdetail execute(Rckflowdetail rckflowdetail) throws FlowException {
        logger.info(SystemConstant.SIGN + "AOMA划账流程开始!");
        String rcktype = rckflowdetail.getId().getRcktype();
/*		try {
            if(Constant.RCKTYPE_LC.equals(rcktype)){
				rckflowdetail=rckremitService.commonRemit(rckflowdetail);
				rckremitService.remitHisdeal(rckflowdetail);
			}else{
				boolean flag=wokedateService.isWokedate();//判断工作日
				if(flag){
					rckflowdetail=rckremitService.commonRemit(rckflowdetail);
					rckremitService.remitHisdeal(rckflowdetail);
				}else{
					logger.info(Constant.SIGN+"非工作日不进行划账.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			throw new FlowException(rckflowdetail, e);
		}*/
        return rckflowdetail;
    }
}
