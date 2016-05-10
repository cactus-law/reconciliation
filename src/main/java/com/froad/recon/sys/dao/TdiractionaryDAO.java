package com.froad.recon.sys.dao;

import java.util.List;
import java.util.Map;

import com.froad.comon.dao.impl.DBException;
import com.froad.comon.dao.impl.DBManager;
import com.froad.recon.sys.model.Tdiractionary;


/**
 * 字典表
 * @author zhangjwei
 * @created 2015-06-16 11:20:26
 * @version 1.0
 */

public interface TdiractionaryDAO extends DBManager{
	/**
	 * @Title: inser 
	 * @Description: 插入单条数据
	 * @param tdiractionary  tdiractionary 对象
	 * @return Tdiractionary   返回插入完成的 tdiractionary对象
	 */
	public Tdiractionary insert(Tdiractionary tdiractionary) throws DBException;
	
	/**
	 * @Title: batchInser 
	 * @Description: 插入多条数据 
	 * @param listTdiractionary 插入数据的list    
	 * @throws
	 */
	public void batchInser (List<Tdiractionary> lists) throws DBException;
	
	/**
	 * @Title: delete
	 * @Description: 根据对象删除
	 * @param objId void    
	 */
	
	public void delete(Tdiractionary tdiractionary) throws DBException ;
	/**
	 * @Title: batchDelete 
	 * @Description: 根据 根据对象集合删除数据
	 * @param lists id集合   
	 */
	public void batchDelete(List<Tdiractionary> lists) throws DBException ;
	
	/**
	 * @Title: update 
	 * @Description: 跟新一条数据 tdiractionary
	 * @param tdiractionary  tdiractionary对象
	 * @throws
	 */
	public void update(Tdiractionary tdiractionary) throws DBException;
	
	/**
	 * @Title: batchUpdate
	 * @Description: 跟新多条数据 tdiractionary集合
	 * @param listTdiractionary  tdiractionary集合  
	 * @throws
	 */
	
	public void batchUpdate(List<Tdiractionary> lists) throws DBException ;
	/**
	 * @Title: getById
	 * @Description: 根据编号查询一条数据
	 * @param objID 编号
	 * @return Tdiractionary    tdiractionary 对象
	 * @throws
	 */
	public Tdiractionary getById(String objID) ;
	
	/**
	 * @Title: getAll
	 * @Description: 查询所有的tdiractionary数据
	 * @return List    返回一个tdiractionary的集合
	 * @throws
	 */
	public List getAll();
	
	/**
	 * @Title: getList
	 * @Description: 根据 tdiractionary对象 查询符合的数据
	 * @param tdiractionary tdiractionary对象
	 * @return List    返回一个结合。 
	 * @throws
	 */
	public List getList(Tdiractionary tdiractionary) ;
	
	/**
	 * @Description: 根据 以paramsMap中key为字段名value为值， 查询符合条件的记录
	 * @param paramsMap 
	 * @param pageNum  页号
	 * @param pageSize 页大小
	 * @throws DBException
	 */
	public List<Tdiractionary> selectForPagin(Map<String, Object> paramsMap, Integer pageNum, Integer pageSize) 
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
