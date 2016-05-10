package com.froad.recon.reconciliation.service.impl;

import java.util.List;
import java.util.Map;

import com.froad.comon.dao.impl.DBException;
import com.froad.comon.exception.ServiceException;
import com.froad.comon.util.Logger;
import com.froad.recon.reconciliation.dao.STradeResultDAO;
import com.froad.recon.reconciliation.dao.SdelayDAO;
import com.froad.recon.reconciliation.dao.SexceptionDAO;
import com.froad.recon.reconciliation.dao.SnoReconDAO;
import com.froad.recon.reconciliation.dao.SsuccessDAO;
import com.froad.recon.reconciliation.dao.impl.STradeResultDAOImpl;
import com.froad.recon.reconciliation.model.STradeResult;
import com.froad.recon.reconciliation.model.Sdelay;
import com.froad.recon.reconciliation.model.Sexception;
import com.froad.recon.reconciliation.model.SnoRecon;
import com.froad.recon.reconciliation.model.Ssuccess;
import com.froad.recon.reconciliation.service.STradeResultService;
import com.froad.recon.reconciliation.service.SdelayService;
import com.froad.recon.reconciliation.service.SexceptionService;
import com.froad.recon.reconciliation.service.SnoReconService;
import com.froad.recon.reconciliation.service.SsuccessService;


/**
 * 对账结果表Service实现类
 *
 * @author zhangjwei
 * @created 2015-06-16 11:26:04
 * @version 1.0
 */

public class STradeResultServiceImpl implements STradeResultService{
	private final static Logger logger = Logger.getLogger(STradeResultDAOImpl.class);
	private STradeResultDAO sTradeResultDAO;
	
	private SnoReconDAO snoReconDAO;
    private SdelayDAO sdelayDAO;
    private SsuccessDAO ssuccessDAO;
    private SexceptionDAO sexceptionDAO;
	public STradeResultDAO getsTradeResultDAO() {
		return sTradeResultDAO;
	}
	public void setsTradeResultDAO(STradeResultDAO sTradeResultDAO) {
		this.sTradeResultDAO = sTradeResultDAO;
	}
	
	
	public SnoReconDAO getSnoReconDAO() {
		return snoReconDAO;
	}
	public void setSnoReconDAO(SnoReconDAO snoReconDAO) {
		this.snoReconDAO = snoReconDAO;
	}
	public SdelayDAO getSdelayDAO() {
		return sdelayDAO;
	}
	public void setSdelayDAO(SdelayDAO sdelayDAO) {
		this.sdelayDAO = sdelayDAO;
	}
	public SsuccessDAO getSsuccessDAO() {
		return ssuccessDAO;
	}
	public void setSsuccessDAO(SsuccessDAO ssuccessDAO) {
		this.ssuccessDAO = ssuccessDAO;
	}
	public SexceptionDAO getSexceptionDAO() {
		return sexceptionDAO;
	}
	public void setSexceptionDAO(SexceptionDAO sexceptionDAO) {
		this.sexceptionDAO = sexceptionDAO;
	}
	/**
	 * @Title: inser 
	 * @Description: 插入单条数据
	 * @param sTradeResult  sTradeResult 对象
	 * @return STradeResult   返回插入完成的 sTradeResult对象
	 */
	public STradeResult insert(STradeResult sTradeResult) throws ServiceException{
		try {
			return sTradeResultDAO.insert(sTradeResult);
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
	 * @param listSTradeResult 插入数据的list    
	 * @throws
	 */
	public void batchInser(List<STradeResult> lists) throws ServiceException{
		try {
			sTradeResultDAO.batchInser(lists);
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
	
	public void delete(STradeResult sTradeResult) throws ServiceException {
		try {
			sTradeResultDAO.delete(sTradeResult);
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
	public void batchDelete(List<STradeResult> lists) throws ServiceException {
		try {
			sTradeResultDAO.batchDelete(lists);
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
			sTradeResultDAO.batchDeleteByReconDate(reconDate,paramsMap);
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
	 * @Description: 跟新一条数据 sTradeResult
	 * @param sTradeResult  sTradeResult对象
	 * @throws
	 */
	public void update(STradeResult sTradeResult) throws ServiceException{
		try {
			sTradeResultDAO.update(sTradeResult);
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
	 * @Description: 跟新多条数据 sTradeResult集合
	 * @param listSTradeResult  sTradeResult集合  
	 * @throws
	 */
	
	public void batchUpdate(List<STradeResult> lists) throws ServiceException {
		try {
			sTradeResultDAO.batchUpdate(lists);
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
	 * @return STradeResult    sTradeResult 对象
	 * @throws
	 */
	public STradeResult getById(String objID) throws ServiceException {
		try {
			return sTradeResultDAO.getById(objID);
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceException(null,e.getMessage(),e);
		}
	}
	
	/**
	 * @Title: getAll
	 * @Description: 查询所有的sTradeResult数据
	 * @return List    返回一个sTradeResult的集合
	 * @throws
	 */
	public List getAll() throws ServiceException {
		try {
			return sTradeResultDAO.getAll();
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceException(null,e.getMessage(),e);
		}
	}
	
	/**
	 * @Title: getList
	 * @Description: 根据 sTradeResult对象 查询符合的数据
	 * @param sTradeResult sTradeResult对象
	 * @return List    返回一个结合。 
	 * @throws
	 */
	public List getList(STradeResult sTradeResult) throws ServiceException{
		try {
			return sTradeResultDAO.getList(sTradeResult);
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
	public List<STradeResult> selectForPagin(Map<String, Object> paramsMap, Integer pageNum, Integer pageSize) 
			throws ServiceException{
		try {
			return sTradeResultDAO.selectForPagin(paramsMap, pageNum, pageSize);
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
			return sTradeResultDAO.selectForPaginCount(paramsMap);
		} catch (DBException e) {
			logger.error(e);
			throw new ServiceException(e.getErrorCode(),e.getErrorMsg(),e);
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceException(null,e.getMessage(),e);
		}
	}
	
	/**分离表入库， 和结果集表删除**/
	public void separateDataHandler(List<Ssuccess> insertSsuccesss,
			List<Sexception> insertSexceptions, List<SnoRecon> insertSnoRecons,
			List<Sdelay> insertSdelays, List<STradeResult> deleteResults)
			throws ServiceException {
		try {
		/***批量插入*/
		ssuccessDAO.batchInser(insertSsuccesss);
		sexceptionDAO.batchInser(insertSexceptions);
		snoReconDAO.batchInser(insertSnoRecons);
		sdelayDAO.batchInser(insertSdelays);
		/***删除结果集表*/
		//batchDelete(deleteResults);
		} catch (DBException e) {
			logger.error(e);
			throw new ServiceException(e.getErrorCode(),e.getErrorMsg(),e);
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceException(null,e.getMessage(),e);
		}
	}
	
	/** part 分离表入库， 和结果集表删除**/
	public void separateDataHandlerPart(List<Ssuccess> insertSsuccesss,
			List<Sexception> insertSexceptions, List<SnoRecon> insertSnoRecons,
			List<Sdelay> insertSdelays, List<STradeResult> deleteResults)
			throws ServiceException {
		try {
			/**批量删除*/
			for (Ssuccess item : insertSsuccesss) {
				ssuccessDAO.deleteByObj(item);
			}
			/**批量删除*/
			for (Sexception item : insertSexceptions) {
				sexceptionDAO.deleteByObj(item);
			}
			/**批量删除*/
			for (SnoRecon item : insertSnoRecons) {
				snoReconDAO.deleteByObj(item);
			}
			/**批量删除*/
			for (Sdelay item : insertSdelays) {
				sdelayDAO.deleteByObj(item);
			}
			
			/***批量插入*/
			ssuccessDAO.batchInser(insertSsuccesss);
			sexceptionDAO.batchInser(insertSexceptions);
			snoReconDAO.batchInser(insertSnoRecons);
			sdelayDAO.batchInser(insertSdelays);
			/***删除结果集表*/
			//batchDelete(deleteResults);
		} catch (DBException e) {
			logger.error(e);
			throw new ServiceException(e.getErrorCode(),e.getErrorMsg(),e);
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceException(null,e.getMessage(),e);
		}
	}
}
