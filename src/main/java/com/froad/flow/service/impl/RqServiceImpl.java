package com.froad.flow.service.impl;

import com.froad.comon.ServiceException;
import com.froad.flow.dao.RckFroadBillBillDao;
import com.froad.flow.dao.RckFroadBillPayDao;
import com.froad.flow.dao.RckOpenApiTransOrderDao;
import com.froad.flow.dao.RckOpenApiPayDao;
import com.froad.flow.service.RqService;
import com.froad.comon.util.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("rqService")
public class RqServiceImpl implements RqService {
    private static final Logger logger = Logger.getLogger(RqServiceImpl.class);
    @Autowired
    private RckOpenApiTransOrderDao rckOpenApiTransOrderDao;
    @Autowired
    private RckOpenApiPayDao rckOpenApiPayDao;
    @Autowired
    private RckFroadBillPayDao rckFroadBillPayDao;
    @Autowired
    private RckFroadBillBillDao rckFroadBillBillDao;


    @Override
    public int rqOpenApiTransOrder(String startDate, String endDate) throws ServiceException {
        try {
            int insertHis = rckOpenApiTransOrderDao.insertHis(startDate, endDate);
            logger.info("插入openapi.trans_order历史表数量:" + insertHis);
            int deleteIncre = rckOpenApiTransOrderDao.deleteIncre(startDate, endDate);
            logger.info("删除openapi.trans_order流水表数量:" + deleteIncre);
            if (insertHis != deleteIncre) {
                logger.error("openapi.trans_order交易日切处理条数不一致......");
                throw new ServiceException("处理条数不一致!");
            }
            return insertHis;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            throw new ServiceException("openapi.trans_order交易日切异常:" + e.getMessage(), e);
        }
    }

    @Override
    public int rqOpenApiPay(String startDate, String endDate) throws ServiceException {
        try {
            int insertHis = rckOpenApiPayDao.insertHis(startDate, endDate);
            logger.info("插入openapi.pay历史表数量:" + insertHis);
            int deleteIncre = rckOpenApiPayDao.deleteIncre(startDate, endDate);
            logger.info("删除openapi.pay流水表数量:" + deleteIncre);
            if (insertHis != deleteIncre) {
                logger.error("openapi.pay交易日切处理条数不一致......");
                throw new ServiceException("处理条数不一致!");
            }
            return insertHis;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            throw new ServiceException("openapi.pay交易日切异常:" + e.getMessage(), e);
        }
    }

    @Override
    public int rqFroadBillPay(String startDate, String endDate) throws ServiceException {
        try {
            int insertHis = rckFroadBillPayDao.insertHis(startDate, endDate);
            logger.info("插入froadbill.pay历史表数量:" + insertHis);
            int deleteIncre = rckFroadBillPayDao.deleteIncre(startDate, endDate);
            logger.info("删除froadbill.pay流水表数量:" + deleteIncre);
            if (insertHis != deleteIncre) {
                logger.error("froadbill.pay交易日切处理条数不一致......");
                throw new ServiceException("处理条数不一致!");
            }
            return insertHis;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            throw new ServiceException("froadbill.pay交易日切异常:" + e.getMessage(), e);
        }
    }

    @Override
    public int rqFroadBillBill(String startDate, String endDate) throws ServiceException {
        try {
            int insertHis = rckFroadBillBillDao.insertHis(startDate, endDate);
            logger.info("插入froadbill.bill历史表数量:" + insertHis);
            int deleteIncre = rckFroadBillBillDao.deleteIncre(startDate, endDate);
            logger.info("删除froadbill.bill流水表数量:" + deleteIncre);
            if (insertHis != deleteIncre) {
                logger.error("froadbill.bill交易日切处理条数不一致......");
                throw new ServiceException("处理条数不一致!");
            }
            return insertHis;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            throw new ServiceException("froadbill.bill交易日切异常:" + e.getMessage(), e);
        }
    }


}
