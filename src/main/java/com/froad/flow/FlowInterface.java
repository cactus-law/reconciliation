package com.froad.flow;

import com.froad.beans.Rckflowdetail;
import com.froad.comon.exception.ServiceException;

public interface FlowInterface {
    public Rckflowdetail execute(Rckflowdetail rckflowdetail) throws FlowException,ServiceException;
}
