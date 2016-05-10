package com.froad.flow.service;

import com.froad.comon.ServiceException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 日切流程
 */
public interface RqService {
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor=ServiceException.class)
	public int rqOpenApiTransOrder(String startDate, String endDate) throws ServiceException;
	
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor=ServiceException.class)
	public int rqOpenApiPay(String startDate, String endDate) throws ServiceException;

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor=ServiceException.class)
	public int rqFroadBillPay(String startDate, String endDate) throws ServiceException;
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor=ServiceException.class)
	public int rqFroadBillBill(String startDate, String endDate) throws ServiceException;
}
