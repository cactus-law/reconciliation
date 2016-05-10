package com.froad.recon.sys.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.froad.comon.constant.BusinessConstant;
import com.froad.comon.dao.impl.DBException;
import com.froad.comon.exception.ServiceException;
import com.froad.comon.util.Logger;
import com.froad.flow.FlowException;
import com.froad.recon.sys.dao.PlatformDAO;
import com.froad.recon.sys.dao.PlatformDetailDAO;
import com.froad.recon.sys.dao.impl.PlatformDAOImpl;
import com.froad.recon.sys.model.Platform;
import com.froad.recon.sys.model.PlatformDetail;
import com.froad.recon.sys.service.PlatformService;

/**
 * 平台表Service实现类
 * 
 * @author zhangjwei
 * @created 2015-06-16 11:20:16
 * @version 1.0
 */

public class PlatformServiceImpl implements PlatformService {
	private final static Logger logger = Logger
			.getLogger(PlatformDAOImpl.class);
	private PlatformDAO platformDAO;
	private PlatformDetailDAO platformDetailDAO;

	public PlatformDAO getplatformDAO() {
		return platformDAO;
	}

	public void setplatformDAO(PlatformDAO platformDAO) {
		this.platformDAO = platformDAO;
	}

	public PlatformDetailDAO getPlatformDetailDAO() {
		return platformDetailDAO;
	}

	public void setPlatformDetailDAO(PlatformDetailDAO platformDetailDAO) {
		this.platformDetailDAO = platformDetailDAO;
	}

	/**
	 * @Title: inser
	 * @Description: 插入单条数据
	 * @param platform
	 *            platform 对象
	 * @return Platform 返回插入完成的 platform对象
	 */
	public Platform insert(Platform platform) throws ServiceException {
		try {
			return platformDAO.insert(platform);
		} catch (DBException e) {
			logger.error(e);
			throw new ServiceException(e.getErrorCode(), e.getErrorMsg(), e);
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceException(null, e.getMessage(), e);
		}
	}

	/**
	 * @Title: batchInser
	 * @Description: 插入多条数据
	 * @param listPlatform
	 *            插入数据的list
	 * @throws
	 */
	public void batchInser(List<Platform> lists) throws ServiceException {
		try {
			platformDAO.batchInser(lists);
		} catch (DBException e) {
			logger.error(e);
			throw new ServiceException(e.getErrorCode(), e.getErrorMsg(), e);
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceException(null, e.getMessage(), e);
		}
	}

	/**
	 * @Title: delete
	 * @Description: 根据对象删除
	 * @param objId
	 *            void
	 */

	public void delete(Platform platform) throws ServiceException {
		try {
			platformDAO.delete(platform);
		} catch (DBException e) {
			logger.error(e);
			throw new ServiceException(e.getErrorCode(), e.getErrorMsg(), e);
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceException(null, e.getMessage(), e);
		}
	}

	/**
	 * @Title: batchDelete
	 * @Description: 根据 根据对象集合删除数据
	 * @param lists
	 *            id集合
	 */
	public void batchDelete(List<Platform> lists) throws ServiceException {
		try {
			platformDAO.batchDelete(lists);
		} catch (DBException e) {
			logger.error(e);
			throw new ServiceException(e.getErrorCode(), e.getErrorMsg(), e);
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceException(null, e.getMessage(), e);
		}
	}

	/**
	 * @Title: update
	 * @Description: 跟新一条数据 platform
	 * @param platform
	 *            platform对象
	 * @throws
	 */
	public void update(Platform platform) throws ServiceException {
		try {
			platformDAO.update(platform);
		} catch (DBException e) {
			logger.error(e);
			throw new ServiceException(e.getErrorCode(), e.getErrorMsg(), e);
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceException(null, e.getMessage(), e);
		}
	}

	/**
	 * @Title: batchUpdate
	 * @Description: 跟新多条数据 platform集合
	 * @param listPlatform
	 *            platform集合
	 * @throws
	 */

	public void batchUpdate(List<Platform> lists) throws ServiceException {
		try {
			platformDAO.batchUpdate(lists);
		} catch (DBException e) {
			logger.error(e);
			throw new ServiceException(e.getErrorCode(), e.getErrorMsg(), e);
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceException(null, e.getMessage(), e);
		}
	}

	/**
	 * @Title: getById
	 * @Description: 根据编号查询一条数据
	 * @param objID
	 *            编号
	 * @return Platform platform 对象
	 * @throws
	 */
	public Platform getById(String objID) throws ServiceException {
		try {
			return platformDAO.getById(objID);
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceException(null, e.getMessage(), e);
		}
	}

	/**
	 * @Title: getAll
	 * @Description: 查询所有的platform数据
	 * @return List 返回一个platform的集合
	 * @throws
	 */
	public List getAll() throws ServiceException {
		try {
			return platformDAO.getAll();
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceException(null, e.getMessage(), e);
		}
	}

	/**
	 * @Title: getList
	 * @Description: 根据 platform对象 查询符合的数据
	 * @param platform
	 *            platform对象
	 * @return List 返回一个结合。
	 * @throws
	 */
	public List getList(Platform platform) throws ServiceException {
		try {
			return platformDAO.getList(platform);
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceException(null, e.getMessage(), e);
		}
	}

	/**
	 * @Description: 根据 以paramsMap中key为字段名value为值， 查询符合条件的记录
	 * @param paramsMap
	 * @param pageNum
	 *            页号
	 * @param pageSize
	 *            页大小
	 * @throws ServiceException
	 */
	public List<Platform> selectForPagin(Map<String, Object> paramsMap,
			Integer pageNum, Integer pageSize) throws ServiceException {
		try {
			return platformDAO.selectForPagin(paramsMap, pageNum, pageSize);
		} catch (DBException e) {
			logger.error(e);
			throw new ServiceException(e.getErrorCode(), e.getErrorMsg(), e);
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceException(null, e.getMessage(), e);
		}
	}

	/**
	 * @Description: 根据 以paramsMap中key为字段名value为值， 总条数
	 * @param paramsMap
	 * @param pageNum
	 *            页号
	 * @param pageSize
	 *            页大小
	 * @throws ServiceException
	 */
	public Integer selectForPaginCount(Map<String, Object> paramsMap)
			throws ServiceException {
		try {
			return platformDAO.selectForPaginCount(paramsMap);
		} catch (DBException e) {
			logger.error(e);
			throw new ServiceException(e.getErrorCode(), e.getErrorMsg(), e);
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceException(null, e.getMessage(), e);
		}
	}

	/**
	 * 通过平台编号获取 平台明细信息
	 * 
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	public List<PlatformDetail> getDetailByImport(String platformNo, String date,
			String impType) throws ServiceException {
		try {
			List<PlatformDetail> result = new ArrayList<PlatformDetail>();
			String ErrMsg = "";
			Platform queryPlatform = new Platform();
			queryPlatform.setStatus(Platform.STATUS_YES);
			queryPlatform.setPlatformNo(platformNo);
			List<Platform> platforms = platformDAO.getList(queryPlatform);
			if (platforms.size() < 1) {
				ErrMsg = "msg在平台配置中不存在";
				throw new ServiceException(ErrMsg);
			} else {
				result = platformDetailDAO.getListByImport(platformNo, date,impType);
			}
			return result;
		} catch (DBException e) {
			logger.error(e);
			throw new ServiceException(e.getErrorCode(), e.getErrorMsg(), e);
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceException(null, e.getMessage(), e);
		}
	}
	
	/**
	 * @Title: getListByImport
	 * @Description: 对账日期  查询符合条件的平台信息
	 * @param 
	 * @return List    返回一个集合。 
	 * @throws
	 */
	public List getListByImport(String date) throws ServiceException{
		try {
			return platformDetailDAO.getListByImport(null, date,BusinessConstant.IMP_TYPE.ALL);
		} catch (DBException e) {
			logger.error(e);
			throw new ServiceException(e.getErrorCode(), e.getErrorMsg(), e);
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceException(null, e.getMessage(), e);
		}
	}
}
