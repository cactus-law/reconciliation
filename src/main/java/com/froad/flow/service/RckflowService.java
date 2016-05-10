package com.froad.flow.service;

import com.froad.beans.Rckflow;
import com.froad.flow.FlowException;

import java.util.List;

public interface RckflowService {
    public List<Rckflow> getRckflows(String type) throws FlowException;

}
