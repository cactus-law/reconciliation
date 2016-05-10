package com.froad.flow.service.impl;

import com.froad.beans.Rckflow;
import com.froad.flow.FlowException;
import com.froad.flow.dao.RckflowDao;
import com.froad.flow.service.RckflowService;
import com.froad.comon.util.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("rckflowService")
public class RckflowServiceImpl implements RckflowService {
    private static final Logger logger = Logger.getLogger(RckflowServiceImpl.class);
    @Autowired
    private RckflowDao rckflowDao;

    /**
     * 获取流程列表
     */
    public List<Rckflow> getRckflows(String type) throws FlowException {
        logger.info("获取" + type + "流程列表......");
        try {
            return rckflowDao.getRckflows(type);
        } catch (Exception e) {
            logger.error("获取流程列表出错...");
            throw new FlowException("获取流程列表出错...");
        }
    }

    public RckflowDao getRckflowDao() {
        return rckflowDao;
    }

    public void setRckflowDao(RckflowDao rckflowDao) {
        this.rckflowDao = rckflowDao;
    }
}
