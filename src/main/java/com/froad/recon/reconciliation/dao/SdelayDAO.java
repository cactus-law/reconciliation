package com.froad.recon.reconciliation.dao;

import java.util.List;
import java.util.Map;

import com.froad.comon.dao.impl.DBException;
import com.froad.comon.dao.impl.DBManager;
import com.froad.recon.reconciliation.model.Sdelay;


/**
 * 延时对账表
 * @author zhangjwei
 * @created 2015-06-16 11:25:50
 * @version 1.0
 */

public interface SdelayDAO extends DBManager{
	/**
	 * @Title: inser 
	 * @Description: 插入单条数据
	 * @param sdelay  sdelay 对象
	 * @return Sdelay   返回插入完成的 sdelay对象
	 */
	public Sdelay insert(Sdelay sdelay) throws DBException;
	
	/**
	 * @Title: batchInser 
	 * @Description: 插入多条数据 
	 * @param listSdelay 插入数据的list    
	 * @throws
	 */
	public void batchInser (List<Sdelay> lists) throws DBException;
	
	/**
	 * @Title: delete
	 * @Description: 根据对象删除
	 * @param objId void    
	 */
	
	public void delete(Sdelay sdelay) throws DBException ;
	/**
	 * @Title: batchDelete 
	 * @Description: 根据 根据对象集合删除数据
	 * @param lists id集合   
	 */
	public void batchDelete(List<Sdelay> lists) throws DBException ;
	
	/**
	 * @Title: update 
	 * @Description: 跟新一条数据 sdelay
	 * @param sdelay  sdelay对象
	 * @throws
	 */
	public void update(Sdelay sdelay) throws DBException;
	
	/**
	 * @Title: batchUpdate
	 * @Description: 跟新多条数据 sdelay集合
	 * @param listSdelay  sdelay集合  
	 * @throws
	 */
	
	public void batchUpdate(List<Sdelay> lists) throws DBException ;
	/**
	 * @Title: getById
	 * @Description: 根据编号查询一条数据
	 * @param objID 编号
	 * @return Sdelay    sdelay 对象
	 * @throws
	 */
	public Sdelay getById(String objID) ;
	
	/**
	 * @Title: getAll
	 * @Description: 查询所有的sdelay数据
	 * @return List    返回一个sdelay的集合
	 * @throws
	 */
	public List getAll();
	
	/**
	 * @Title: getList
	 * @Description: 根据 sdelay对象 查询符合的数据
	 * @param sdelay sdelay对象
	 * @return List    返回一个结合。 
	 * @throws
	 */
	public List getList(Sdelay sdelay) ;
	
	/**
	 * @Description: 根据 以paramsMap中key为字段名value为值， 查询符合条件的记录
	 * @param paramsMap 
	 * @param pageNum  页号
	 * @param pageSize 页大小
	 * @throws DBException
	 */
	public List<Sdelay> selectForPagin(Map<String, Object> paramsMap, Integer pageNum, Integer pageSize) 
			throws DBException;
	
	/**
	 * @Description: 根据 以paramsMap中key为字段名value为值， 总条数
	 * @param paramsMap 
	 * @param pageNum  页号
	 * @param pageSize 页大小
	 * @throws DBException
	 */
	public Integer selectForPaginCount(Map<String, Object> paramsMap) throws DBException;
	/***
	 * 批量删除指定对账日的数据
	 * @param reconDate
	 * @param paramsMap
	 * @throws DBException
	 */
	public void batchDeleteByReconDate(String reconDate,
			Map<String, Object> paramsMap) throws DBException;
	
	/***
	 * 根据对象删除
	 * @throws DBException
	 */
	public Integer deleteByObj(Sdelay obj)throws DBException;
	
	/***
	 * 更新延迟对账的状体
	 * @throws DBException
	 */
	public Integer updateSdelay(Sdelay obj)throws DBException;
}
