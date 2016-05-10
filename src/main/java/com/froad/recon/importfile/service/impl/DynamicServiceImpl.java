
package com.froad.recon.importfile.service.impl;

import java.util.List;
import java.util.Map;

import com.froad.comon.dao.impl.DBException;
import com.froad.comon.exception.ServiceException;
import com.froad.comon.util.Logger;
import com.froad.recon.importfile.dao.DynamicDAO;
import com.froad.recon.importfile.service.DynamicService;


/**
 * @author zhangjwei
 * @created 2015-06-16 11:07:28
 * @version 1.0
 */
public class DynamicServiceImpl implements DynamicService{
	private final static Logger logger = Logger.getLogger(DynamicServiceImpl.class);
	
	private DynamicDAO dynamicDAO;
	
	public DynamicDAO getDynamicDAO() {
		return dynamicDAO;
	}

	public void setDynamicDAO(DynamicDAO dynamicDAO) {
		this.dynamicDAO = dynamicDAO;
	}

	/**
	 * @Description: 根据 以paramsMap中key为字段名value为值， 查询符合条件的记录
	 * @param tableName 表明 
	 * @param cleardate  对账日期
	 * @param paramsMap 
	 * @param pageNum  页号
	 * @param pageSize 页大小
	 * @throws ServiceException
	 */
	public List<Map<String,Object>> selectForMap(String tableName,String cleardate, Map<String, Object> paramsMap, Integer pageNum, Integer pageSize) 
			throws ServiceException{
		try {
			return dynamicDAO.selectForMap(tableName, cleardate, paramsMap, pageNum, pageSize);
		} catch (DBException e) {
			logger.error(e);
			throw new ServiceException(e.getErrorCode(),e.getErrorMsg(),e);
		}catch (Exception e) {
			logger.error(e);
			throw new ServiceException(null,e.getMessage(),e);
		}
	}
	
	/**
	 * @Description: 根据 以paramsMap中key为字段名value为值， 总条数
	 * @param tableName 表明 
	 * @param cleardate  对账日期
	 * @param paramsMap 
	 * @param pageNum  页号
	 * @param pageSize 页大小
	 * @return
	 * @throws ServiceException
	 */
	public Integer selectForMapCount(String tableName,String cleardate,Map<String, Object> paramsMap) 
			throws ServiceException{
		try {
			return dynamicDAO.selectForMapCount(tableName, cleardate, paramsMap);
		} catch (DBException e) {
			logger.error(e);
			throw new ServiceException(e.getErrorCode(),e.getErrorMsg(),e);
		}catch (Exception e) {
			logger.error(e);
			throw new ServiceException(null,e.getMessage(),e);
		}
	}
	
	/**
	 * @Description: 查询指定日期上传文件的 记录
	 * @param cleardate  对账日期
	 * @param paramsMap 
	 * @return b.platform_no as \"platformNo\",b.channel_no as \"channelNo\"
	 * @throws DBException
	 */
	public List<Map<String,Object>> selectPlatformByCleardate(String cleardate, Map<String, Object> paramsMap) 
			throws ServiceException{
		try {
			return dynamicDAO.selectPlatformByCleardate(cleardate, paramsMap);
		} catch (DBException e) {
			logger.error(e);
			throw new ServiceException(e.getErrorCode(),e.getErrorMsg(),e);
		}catch (Exception e) {
			logger.error(e);
			throw new ServiceException(null,e.getMessage(),e);
		}
	}
}
