package com.froad.recon.sys.service;

import java.util.List;
import java.util.Map;

import com.froad.comon.exception.ServiceException;
import com.froad.recon.sys.model.Platform;
import com.froad.recon.sys.model.PlatformDetail;


/**
 * 平台表
 *
 * @author zhangjwei
 * @created 2015-06-16 11:20:17
 * @version 1.0
 */

public interface PlatformService {
	/**
	 * @Title: inser 
	 * @Description: 插入单条数据
	 * @param platform  platform 对象
	 * @return Platform   返回插入完成的 platform对象
	 */
	public Platform insert(Platform platform) throws ServiceException;

	 /**
     * @Title: batchInser 
     * @Description: 插入多条数据 
     * @param listPlatform 插入数据的list    
     * @throws
     */
	public void batchInser(List<Platform> lists) throws ServiceException;

	/**
	 * @Title: delete
	 * @Description: 根据对象删除
	 * @param objId void    
	 */
	
	public void delete(Platform platform) throws ServiceException ;
	/**
	 * @Title: batchDelete 
	 * @Description: 根据 根据对象集合删除数据
	 * @param lists id集合   
	 */
	public void batchDelete(List<Platform> lists) throws ServiceException ;

	/**
	 * @Title: update 
	 * @Description: 跟新一条数据 platform
	 * @param platform  platform对象
	 * @throws
	 */
	public void update(Platform platform) throws ServiceException;

	/**
	 * @Title: batchUpdate
	 * @Description: 跟新多条数据 platform集合
	 * @param listPlatform  platform集合  
	 * @throws
	 */
	
	public void batchUpdate(List<Platform> lists) throws ServiceException ;
	/**
	 * @Title: getById
	 * @Description: 根据编号查询一条数据
	 * @param objID 编号
	 * @return Platform    platform 对象
	 * @throws
	 */
	public Platform getById(String objID)  throws ServiceException ;

	/**
	 * @Title: getAll
	 * @Description: 查询所有的platform数据
	 * @return List    返回一个platform的集合
	 * @throws
	 */
	public List getAll() throws ServiceException ;
	
	/**
     * @Title: getList
     * @Description: 根据 platform对象 查询符合的数据
     * @param platform platform对象
     * @return List    返回一个结合。 
     * @throws
     */
	public List getList(Platform platform)  throws ServiceException ;
	
	/**
	 * @Description: 根据 以paramsMap中key为字段名value为值， 查询符合条件的记录
	 * @param paramsMap 
	 * @param pageNum  页号
	 * @param pageSize 页大小
	 * @throws ServiceException
	 */
	public List<Platform> selectForPagin(Map<String, Object> paramsMap, Integer pageNum, Integer pageSize) 
	       throws ServiceException;
	
	/**
	 * @Description: 根据 以paramsMap中key为字段名value为值， 总条数
	 * @param paramsMap 
	 * @param pageNum  页号
	 * @param pageSize 页大小
	 * @throws ServiceException
	 */
	public Integer selectForPaginCount(Map<String, Object> paramsMap) throws ServiceException;
	
	
	/**
	 * 通过平台编号获取 平台明细信息
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
   public List<PlatformDetail> getDetailByImport(String platformNo, String date,
			String impType) throws ServiceException;
   
   /**
	 * @Title: getListByImport
	 * @Description: 对账日期  查询符合条件的平台信息
	 * @param 
	 * @return List    返回一个集合。 
	 * @throws
	 */
	public List<PlatformDetail> getListByImport(String date) throws ServiceException;
}
