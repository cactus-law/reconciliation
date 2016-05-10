package com.froad.recon.sys.dao;

import java.util.List;
import java.util.Map;

import com.froad.comon.dao.impl.DBException;
import com.froad.comon.dao.impl.DBManager;
import com.froad.recon.sys.model.PlatformDetail;


/**
 * 平台信息配置表
 * @author zhangjwei
 * @created 2015-06-16 11:20:22
 * @version 1.0
 */

public interface PlatformDetailDAO extends DBManager{
	/**
	 * @Title: inser 
	 * @Description: 插入单条数据
	 * @param platformDetail  platformDetail 对象
	 * @return PlatformDetail   返回插入完成的 platformDetail对象
	 */
	public PlatformDetail insert(PlatformDetail platformDetail) throws DBException;
	
	/**
	 * @Title: batchInser 
	 * @Description: 插入多条数据 
	 * @param listPlatformDetail 插入数据的list    
	 * @throws
	 */
	public void batchInser (List<PlatformDetail> lists) throws DBException;
	
	/**
	 * @Title: delete
	 * @Description: 根据对象删除
	 * @param objId void    
	 */
	
	public void delete(PlatformDetail platformDetail) throws DBException ;
	/**
	 * @Title: batchDelete 
	 * @Description: 根据 根据对象集合删除数据
	 * @param lists id集合   
	 */
	public void batchDelete(List<PlatformDetail> lists) throws DBException ;
	
	/**
	 * @Title: update 
	 * @Description: 跟新一条数据 platformDetail
	 * @param platformDetail  platformDetail对象
	 * @throws
	 */
	public void update(PlatformDetail platformDetail) throws DBException;
	
	/**
	 * @Title: batchUpdate
	 * @Description: 跟新多条数据 platformDetail集合
	 * @param listPlatformDetail  platformDetail集合  
	 * @throws
	 */
	
	public void batchUpdate(List<PlatformDetail> lists) throws DBException ;
	/**
	 * @Title: getById
	 * @Description: 根据编号查询一条数据
	 * @param objID 编号
	 * @return PlatformDetail    platformDetail 对象
	 * @throws
	 */
	public PlatformDetail getById(String objID) ;
	
	/**
	 * @Title: getAll
	 * @Description: 查询所有的platformDetail数据
	 * @return List    返回一个platformDetail的集合
	 * @throws
	 */
	public List getAll();
	
	/**
	 * @Title: getList
	 * @Description: 根据 platformDetail对象 查询符合的数据
	 * @param platformDetail platformDetail对象
	 * @return List    返回一个结合。 
	 * @throws
	 */
	public List getList(PlatformDetail platformDetail) ;
	
	/**
	 * @Description: 根据 以paramsMap中key为字段名value为值， 查询符合条件的记录
	 * @param paramsMap 
	 * @param pageNum  页号
	 * @param pageSize 页大小
	 * @throws DBException
	 */
	public List<PlatformDetail> selectForPagin(Map<String, Object> paramsMap, Integer pageNum, Integer pageSize) 
			throws DBException;
	
	/**
	 * @Description: 根据 以paramsMap中key为字段名value为值， 总条数
	 * @param paramsMap 
	 * @param pageNum  页号
	 * @param pageSize 页大小
	 * @throws DBException
	 */
	public Integer selectForPaginCount(Map<String, Object> paramsMap) throws DBException;
	
	/**
	 * @Title: getListByImport
	 * @Description: 通过平台号 ，对账日期、对账类型（全部，部分） 查询符合条件的平台信息
	 * @param 
	 * @return List    返回一个集合。 
	 * @throws
	 */
	public List getListByImport(String platformNo ,String date,String reconType) throws DBException;
}
