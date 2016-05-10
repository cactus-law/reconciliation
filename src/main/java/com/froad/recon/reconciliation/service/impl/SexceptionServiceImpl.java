package com.froad.recon.reconciliation.service.impl;

import java.util.List;
import java.util.Map;

import com.froad.comon.dao.impl.DBException;
import com.froad.comon.exception.ServiceException;
import com.froad.comon.util.Logger;
import com.froad.recon.reconciliation.dao.SexceptionDAO;
import com.froad.recon.reconciliation.dao.impl.SexceptionDAOImpl;
import com.froad.recon.reconciliation.model.Sexception;
import com.froad.recon.reconciliation.service.SexceptionService;


/**
 * 差错表Service实现类
 *
 * @author zhangjwei
 * @created 2015-06-16 11:25:52
 * @version 1.0
 */

public class SexceptionServiceImpl implements SexceptionService{
	private final static Logger logger = Logger.getLogger(SexceptionDAOImpl.class);
	private SexceptionDAO sexceptionDAO;
	public SexceptionDAO getsexceptionDAO() {
		return sexceptionDAO;
	}
	public void setsexceptionDAO(SexceptionDAO sexceptionDAO) {
		this.sexceptionDAO = sexceptionDAO;
	}
	
	/**
	 * @Title: inser 
	 * @Description: 插入单条数据
	 * @param sexception  sexception 对象
	 * @return Sexception   返回插入完成的 sexception对象
	 */
	public Sexception insert(Sexception sexception) throws ServiceException{
		try {
			return sexceptionDAO.insert(sexception);
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
	 * @param listSexception 插入数据的list    
	 * @throws
	 */
	public void batchInser(List<Sexception> lists) throws ServiceException{
		try {
			sexceptionDAO.batchInser(lists);
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
	
	public void delete(Sexception sexception) throws ServiceException {
		try {
			sexceptionDAO.delete(sexception);
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
	public void batchDelete(List<Sexception> lists) throws ServiceException {
		try {
			sexceptionDAO.batchDelete(lists);
		} catch (DBException e) {
			logger.error(e);
			throw new ServiceException(e.getErrorCode(),e.getErrorMsg(),e);
		}catch (Exception e) {
			logger.error(e);
			throw new ServiceException(null,e.getMessage(),e);
		}
	}
	/***
	 * 批量删除指定对账日的数据
	 * @param reconDate
	 * @param paramsMap
	 * @throws DBException
	 */
	public void batchDeleteByReconDate(String reconDate,Map<String, Object> paramsMap)throws ServiceException{
		try {
			sexceptionDAO.batchDeleteByReconDate(reconDate,paramsMap);
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
	 * @Description: 跟新一条数据 sexception
	 * @param sexception  sexception对象
	 * @throws
	 */
	public void update(Sexception sexception) throws ServiceException{
		try {
			sexceptionDAO.update(sexception);
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
	 * @Description: 跟新多条数据 sexception集合
	 * @param listSexception  sexception集合  
	 * @throws
	 */
	
	public void batchUpdate(List<Sexception> lists) throws ServiceException {
		try {
			sexceptionDAO.batchUpdate(lists);
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
	 * @return Sexception    sexception 对象
	 * @throws
	 */
	public Sexception getById(String objID) throws ServiceException {
		try {
			return sexceptionDAO.getById(objID);
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceException(null,e.getMessage(),e);
		}
	}
	
	/**
	 * @Title: getAll
	 * @Description: 查询所有的sexception数据
	 * @return List    返回一个sexception的集合
	 * @throws
	 */
	public List getAll() throws ServiceException {
		try {
			return sexceptionDAO.getAll();
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceException(null,e.getMessage(),e);
		}
	}
	
	/**
	 * @Title: getList
	 * @Description: 根据 sexception对象 查询符合的数据
	 * @param sexception sexception对象
	 * @return List    返回一个结合。 
	 * @throws
	 */
	public List getList(Sexception sexception) throws ServiceException{
		try {
			return sexceptionDAO.getList(sexception);
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
	public List<Sexception> selectForPagin(Map<String, Object> paramsMap, Integer pageNum, Integer pageSize) 
			throws ServiceException{
		try {
			return sexceptionDAO.selectForPagin(paramsMap, pageNum, pageSize);
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
			return sexceptionDAO.selectForPaginCount(paramsMap);
		} catch (DBException e) {
			logger.error(e);
			throw new ServiceException(e.getErrorCode(),e.getErrorMsg(),e);
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceException(null,e.getMessage(),e);
		}
	}
}
