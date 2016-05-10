package com.froad.recon.importfile.dao;

import java.util.List;
import java.util.Map;

import com.froad.comon.dao.impl.DBException;
import com.froad.comon.dao.impl.DBManager;
import com.froad.recon.importfile.model.Ipoints;


/**
 * 积分对账数据表
 * @author zhangjwei
 * @created 2015-06-16 11:07:28
 * @version 1.0
 */

public interface IpointsDAO extends DBManager{
	/**
	 * @Title: inser 
	 * @Description: 插入单条数据
	 * @param ipoints  ipoints 对象
	 * @return Ipoints   返回插入完成的 ipoints对象
	 */
	public Ipoints insert(Ipoints ipoints) throws DBException;
	
	/**
	 * @Title: batchInser 
	 * @Description: 插入多条数据 
	 * @param listIpoints 插入数据的list    
	 * @throws
	 */
	public void batchInser (List<Ipoints> lists) throws DBException;
	
	/**
	 * @Title: delete
	 * @Description: 根据对象删除
	 * @param objId void    
	 */
	
	public void delete(Ipoints ipoints) throws DBException ;
	/**
	 * @Title: batchDelete 
	 * @Description: 根据 根据对象集合删除数据
	 * @param lists id集合   
	 */
	public void batchDelete(List<Ipoints> lists) throws DBException ;
	
	/**
	 * @Title: update 
	 * @Description: 跟新一条数据 ipoints
	 * @param ipoints  ipoints对象
	 * @throws
	 */
	public void update(Ipoints ipoints) throws DBException;
	
	/**
	 * @Title: batchUpdate
	 * @Description: 跟新多条数据 ipoints集合
	 * @param listIpoints  ipoints集合  
	 * @throws
	 */
	
	public void batchUpdate(List<Ipoints> lists) throws DBException ;
	/**
	 * @Title: getById
	 * @Description: 根据编号查询一条数据
	 * @param objID 编号
	 * @return Ipoints    ipoints 对象
	 * @throws
	 */
	public Ipoints getById(String objID) ;
	
	/**
	 * @Title: getAll
	 * @Description: 查询所有的ipoints数据
	 * @return List    返回一个ipoints的集合
	 * @throws
	 */
	public List getAll();
	
	/**
	 * @Title: getList
	 * @Description: 根据 ipoints对象 查询符合的数据
	 * @param ipoints ipoints对象
	 * @return List    返回一个结合。 
	 * @throws
	 */
	public List getList(Ipoints ipoints) ;
	
	/**
	 * @Description: 根据 以paramsMap中key为字段名value为值， 查询符合条件的记录
	 * @param paramsMap 
	 * @param pageNum  页号
	 * @param pageSize 页大小
	 * @throws DBException
	 */
	public List<Ipoints> selectForPagin(Map<String, Object> paramsMap, Integer pageNum, Integer pageSize) 
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
