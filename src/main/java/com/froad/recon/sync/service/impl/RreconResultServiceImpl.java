package com.froad.recon.sync.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;

import com.froad.comon.dao.impl.DBException;
import com.froad.comon.exception.ServiceException;
import com.froad.comon.util.Logger;
import com.froad.recon.sync.dao.RreconResultDAO;
import com.froad.recon.sync.dao.impl.RreconResultDAOImpl;
import com.froad.recon.sync.model.RreconResult;
import com.froad.recon.sync.service.RreconResultService;


/**
 * Service实现类
 *
 * @author zhangjwei
 * @created 2015-07-31 10:32:53
 * @version 1.0
 */

public class RreconResultServiceImpl implements RreconResultService{
	private final static Logger logger = Logger.getLogger(RreconResultDAOImpl.class);
	private RreconResultDAO rreconResultDAO;
	public RreconResultDAO getrreconResultDAO() {
		return rreconResultDAO;
	}
	public void setrreconResultDAO(RreconResultDAO rreconResultDAO) {
		this.rreconResultDAO = rreconResultDAO;
	}
	
	/**
	 * @Title: inser 
	 * @Description: 插入单条数据
	 * @param rreconResult  rreconResult 对象
	 * @return RreconResult   返回插入完成的 rreconResult对象
	 */
	public RreconResult insert(RreconResult rreconResult) throws ServiceException{
		try {
			return rreconResultDAO.insert(rreconResult);
		} catch (DBException e) {
			logger.error(e);
			throw new ServiceException(e.getErrorCode(),e.getErrorMsg(),e);
		}catch (Exception e) {
			logger.error(e);
			throw new ServiceException(null,e.getMessage(),e);
		}
	}
	
	/**
	 * @Title: batchInser 
	 * @Description: 插入多条数据 
	 * @param listRreconResult 插入数据的list    
	 * @throws
	 */
	public void batchInser(List<RreconResult> lists) throws ServiceException{
		try {
			rreconResultDAO.batchInser(lists);
		} catch (DBException e) {
			logger.error(e);
			throw new ServiceException(e.getErrorCode(),e.getErrorMsg(),e);
		}catch (Exception e) {
			logger.error(e);
			throw new ServiceException(null,e.getMessage(),e);
		}
	}
	
	/**
	 * @Title: delete
	 * @Description: 根据对象删除
	 * @param objId void    
	 */
	
	public void delete(RreconResult rreconResult) throws ServiceException {
		try {
			rreconResultDAO.delete(rreconResult);
		} catch (DBException e) {
			logger.error(e);
			throw new ServiceException(e.getErrorCode(),e.getErrorMsg(),e);
		}catch (Exception e) {
			logger.error(e);
			throw new ServiceException(null,e.getMessage(),e);
		}
	}
	/**
	 * @Title: batchDelete 
	 * @Description: 根据 根据对象集合删除数据
	 * @param lists id集合   
	 */
	public void batchDelete(List<RreconResult> lists) throws ServiceException {
		try {
			rreconResultDAO.batchDelete(lists);
		} catch (DBException e) {
			logger.error(e);
			throw new ServiceException(e.getErrorCode(),e.getErrorMsg(),e);
		}catch (Exception e) {
			logger.error(e);
			throw new ServiceException(null,e.getMessage(),e);
		}
	}
	/**根据对账日期删除*/
	public int deleteByRecon(String recon) throws ServiceException{
		try {
			return rreconResultDAO.deleteByRecon(recon);
		} catch (DBException e) {
			logger.error(e);
			throw new ServiceException(e.getErrorCode(),e.getErrorMsg(),e);
		}catch (Exception e) {
			logger.error(e);
			throw new ServiceException(null,e.getMessage(),e);
		}	
	}
	
	/**
	 * @Title: update 
	 * @Description: 跟新一条数据 rreconResult
	 * @param rreconResult  rreconResult对象
	 * @throws
	 */
	public void update(RreconResult rreconResult) throws ServiceException{
		try {
			rreconResultDAO.update(rreconResult);
		} catch (DBException e) {
			logger.error(e);
			throw new ServiceException(e.getErrorCode(),e.getErrorMsg(),e);
		}catch (Exception e) {
			logger.error(e);
			throw new ServiceException(null,e.getMessage(),e);
		}
	}
	
	/**
	 * @Title: batchUpdate
	 * @Description: 跟新多条数据 rreconResult集合
	 * @param listRreconResult  rreconResult集合  
	 * @throws
	 */
	
	public void batchUpdate(List<RreconResult> lists) throws ServiceException {
		try {
			rreconResultDAO.batchUpdate(lists);
		} catch (DBException e) {
			logger.error(e);
			throw new ServiceException(e.getErrorCode(),e.getErrorMsg(),e);
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceException(null,e.getMessage(),e);
		}
	}
	/**
	 * @Title: getById
	 * @Description: 根据编号查询一条数据
	 * @param objID 编号
	 * @return RreconResult    rreconResult 对象
	 * @throws
	 */
	public RreconResult getById(String objID) throws ServiceException {
		try {
			return rreconResultDAO.getById(objID);
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceException(null,e.getMessage(),e);
		}
	}
	
	/**
	 * @Title: getAll
	 * @Description: 查询所有的rreconResult数据
	 * @return List    返回一个rreconResult的集合
	 * @throws
	 */
	public List getAll() throws ServiceException {
		try {
			return rreconResultDAO.getAll();
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceException(null,e.getMessage(),e);
		}
	}
	
	/**
	 * @Title: getList
	 * @Description: 根据 rreconResult对象 查询符合的数据
	 * @param rreconResult rreconResult对象
	 * @return List    返回一个结合。 
	 * @throws
	 */
	public List getList(RreconResult rreconResult) throws ServiceException{
		try {
			return rreconResultDAO.getList(rreconResult);
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceException(null,e.getMessage(),e);
		}
	}
	
	/**
	 * @Description: 根据 以paramsMap中key为字段名value为值， 查询符合条件的记录
	 * @param paramsMap 
	 * @param pageNum  页号
	 * @param pageSize 页大小
	 * @throws ServiceException
	 */
	public List<RreconResult> selectForPagin(Map<String, Object> paramsMap, Integer pageNum, Integer pageSize) 
			throws ServiceException{
		try {
			return rreconResultDAO.selectForPagin(paramsMap, pageNum, pageSize);
		} catch (DBException e) {
			logger.error(e);
			throw new ServiceException(e.getErrorCode(),e.getErrorMsg(),e);
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceException(null,e.getMessage(),e);
		}
	}
	
	/**
	 * @Description: 根据 以paramsMap中key为字段名value为值， 总条数
	 * @param paramsMap 
	 * @param pageNum  页号
	 * @param pageSize 页大小
	 * @throws ServiceException
	 */
	public Integer selectForPaginCount(Map<String, Object> paramsMap) throws ServiceException{
		try {
			return rreconResultDAO.selectForPaginCount(paramsMap);
		} catch (DBException e) {
			logger.error(e);
			throw new ServiceException(e.getErrorCode(),e.getErrorMsg(),e);
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceException(null,e.getMessage(),e);
		}
	}
	
	/***数据处理**/
	public void queryHandler(RreconResult result, String tableName,String platformNo,
			String reconDate)throws ServiceException{
		try {
			Map<String, Object>  successMap=rreconResultDAO.querySuccessTotal(tableName, reconDate);

			Integer successTotal=MapUtils.getInteger(successMap, "total");
			BigDecimal successTotalMoney=new BigDecimal(0);
			BigDecimal refundTotalMoneyBig=new BigDecimal(0);
			String totalMoney=MapUtils.getString(successMap, "totalMoney");
			String refundTotalMoney=MapUtils.getString(successMap, "refundTotalMoney");
			if(totalMoney!=null){
				successTotalMoney=new BigDecimal(totalMoney);
			}
			if(refundTotalMoney!=null){
				refundTotalMoneyBig=new BigDecimal(refundTotalMoney);
			}
			
			Integer exceptionTotal=rreconResultDAO.queryExceptionTotal(tableName, reconDate); 
			Integer delayTotal=rreconResultDAO.queryDelayTotal(tableName,platformNo, reconDate); 
			Integer noTotal=rreconResultDAO.queryNoTotal(tableName,platformNo, reconDate); 
		
			result.setSuccessTotal(successTotal);
			result.setSuccessTotalMoney(successTotalMoney);
			result.setRefundTotalMoney(refundTotalMoneyBig);
			result.setExceptionTotal(exceptionTotal);
			result.setDelayTotal(delayTotal);
			result.setNoTotal(noTotal);
		} catch (DBException e) {
			logger.error(e);
			throw new ServiceException(e.getErrorCode(),e.getErrorMsg(),e);
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceException(null,e.getMessage(),e);
		}
		
	}
}
