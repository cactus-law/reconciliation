package com.froad.recon.sys.dao;

import java.util.List;
import java.util.Map;

import com.froad.comon.dao.impl.DBException;
import com.froad.comon.dao.impl.DBManager;
import com.froad.recon.sys.model.Platform;


/**
 * 平台表
 * @author zhangjwei
 * @created 2015-06-16 11:20:18
 * @version 1.0
 */

public interface PlatformDAO extends DBManager{
	/**
	 * @Title: inser 
	 * @Description: 插入单条数据
	 * @param platform  platform 对象
	 * @return Platform   返回插入完成的 platform对象
	 */
	public Platform insert(Platform platform) throws DBException;
	
	/**
	 * @Title: batchInser 
	 * @Description: 插入多条数据 
	 * @param listPlatform 插入数据的list    
	 * @throws
	 */
	public void batchInser (List<Platform> lists) throws DBException;
	
	/**
	 * @Title: delete
	 * @Description: 根据对象删除
	 * @param objId void    
	 */
	
	public void delete(Platform platform) throws DBException ;
	/**
	 * @Title: batchDelete 
	 * @Description: 根据 根据对象集合删除数据
	 * @param lists id集合   
	 */
	public void batchDelete(List<Platform> lists) throws DBException ;
	
	/**
	 * @Title: update 
	 * @Description: 跟新一条数据 platform
	 * @param platform  platform对象
	 * @throws
	 */
	public void update(Platform platform) throws DBException;
	
	/**
	 * @Title: batchUpdate
	 * @Description: 跟新多条数据 platform集合
	 * @param listPlatform  platform集合  
	 * @throws
	 */
	
	public void batchUpdate(List<Platform> lists) throws DBException ;
	/**
	 * @Title: getById
	 * @Description: 根据编号查询一条数据
	 * @param objID 编号
	 * @return Platform    platform 对象
	 * @throws
	 */
	public Platform getById(String objID) ;
	
	/**
	 * @Title: getAll
	 * @Description: 查询所有的platform数据
	 * @return List    返回一个platform的集合
	 * @throws
	 */
	public List getAll();
	
	/**
	 * @Title: getList
	 * @Description: 根据 platform对象 查询符合的数据
	 * @param platform platform对象
	 * @return List    返回一个结合。 
	 * @throws
	 */
	public List getList(Platform platform) ;
	
	/**
	 * @Description: 根据 以paramsMap中key为字段名value为值， 查询符合条件的记录
	 * @param paramsMap 
	 * @param pageNum  页号
	 * @param pageSize 页大小
	 * @throws DBException
	 */
	public List<Platform> selectForPagin(Map<String, Object> paramsMap, Integer pageNum, Integer pageSize) 
			throws DBException;
	
	/**
	 * @Description: 根据 以paramsMap中key为字段名value为值， 总条数
	 * @param paramsMap 
	 * @param pageNum  页号
	 * @param pageSize 页大小
	 * @throws DBException
	 */
	public Integer selectForPaginCount(Map<String, Object> paramsMap) throws DBException;
}
