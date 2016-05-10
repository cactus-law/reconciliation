package com.froad.recon.sys.service.impl;

import java.util.List;
import java.util.Map;

import com.froad.comon.dao.impl.DBException;
import com.froad.comon.exception.ServiceException;
import com.froad.comon.util.Logger;
import com.froad.recon.sys.dao.TdiractionaryTypeDAO;
import com.froad.recon.sys.dao.impl.TdiractionaryTypeDAOImpl;
import com.froad.recon.sys.model.TdiractionaryType;
import com.froad.recon.sys.service.TdiractionaryTypeService;


/**
 * 字典类型表Service实现类
 *
 * @author zhangjwei
 * @created 2015-06-16 11:20:28
 * @version 1.0
 */

public class TdiractionaryTypeServiceImpl implements TdiractionaryTypeService{
	private final static Logger logger = Logger.getLogger(TdiractionaryTypeDAOImpl.class);
	private TdiractionaryTypeDAO tdiractionaryTypeDAO;
	public TdiractionaryTypeDAO gettdiractionaryTypeDAO() {
		return tdiractionaryTypeDAO;
	}
	public void settdiractionaryTypeDAO(TdiractionaryTypeDAO tdiractionaryTypeDAO) {
		this.tdiractionaryTypeDAO = tdiractionaryTypeDAO;
	}
	
	/**
	 * @Title: inser 
	 * @Description: 插入单条数据
	 * @param tdiractionaryType  tdiractionaryType 对象
	 * @return TdiractionaryType   返回插入完成的 tdiractionaryType对象
	 */
	public TdiractionaryType insert(TdiractionaryType tdiractionaryType) throws ServiceException{
		try {
			return tdiractionaryTypeDAO.insert(tdiractionaryType);
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
	 * @param listTdiractionaryType 插入数据的list    
	 * @throws
	 */
	public void batchInser(List<TdiractionaryType> lists) throws ServiceException{
		try {
			tdiractionaryTypeDAO.batchInser(lists);
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
	
	public void delete(TdiractionaryType tdiractionaryType) throws ServiceException {
		try {
			tdiractionaryTypeDAO.delete(tdiractionaryType);
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
	public void batchDelete(List<TdiractionaryType> lists) throws ServiceException {
		try {
			tdiractionaryTypeDAO.batchDelete(lists);
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
	 * @Description: 跟新一条数据 tdiractionaryType
	 * @param tdiractionaryType  tdiractionaryType对象
	 * @throws
	 */
	public void update(TdiractionaryType tdiractionaryType) throws ServiceException{
		try {
			tdiractionaryTypeDAO.update(tdiractionaryType);
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
	 * @Description: 跟新多条数据 tdiractionaryType集合
	 * @param listTdiractionaryType  tdiractionaryType集合  
	 * @throws
	 */
	
	public void batchUpdate(List<TdiractionaryType> lists) throws ServiceException {
		try {
			tdiractionaryTypeDAO.batchUpdate(lists);
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
	 * @return TdiractionaryType    tdiractionaryType 对象
	 * @throws
	 */
	public TdiractionaryType getById(String objID) throws ServiceException {
		try {
			return tdiractionaryTypeDAO.getById(objID);
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceException(null,e.getMessage(),e);
		}
	}
	
	/**
	 * @Title: getAll
	 * @Description: 查询所有的tdiractionaryType数据
	 * @return List    返回一个tdiractionaryType的集合
	 * @throws
	 */
	public List getAll() throws ServiceException {
		try {
			return tdiractionaryTypeDAO.getAll();
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceException(null,e.getMessage(),e);
		}
	}
	
	/**
	 * @Title: getList
	 * @Description: 根据 tdiractionaryType对象 查询符合的数据
	 * @param tdiractionaryType tdiractionaryType对象
	 * @return List    返回一个结合。 
	 * @throws
	 */
	public List getList(TdiractionaryType tdiractionaryType) throws ServiceException{
		try {
			return tdiractionaryTypeDAO.getList(tdiractionaryType);
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
	public List<TdiractionaryType> selectForPagin(Map<String, Object> paramsMap, Integer pageNum, Integer pageSize) 
			throws ServiceException{
		try {
			return tdiractionaryTypeDAO.selectForPagin(paramsMap, pageNum, pageSize);
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
			return tdiractionaryTypeDAO.selectForPaginCount(paramsMap);
		} catch (DBException e) {
			logger.error(e);
			throw new ServiceException(e.getErrorCode(),e.getErrorMsg(),e);
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceException(null,e.getMessage(),e);
		}
	}
}
