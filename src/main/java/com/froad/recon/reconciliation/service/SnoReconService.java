package com.froad.recon.reconciliation.service;

import java.util.List;
import java.util.Map;

import com.froad.comon.dao.impl.DBException;
import com.froad.comon.exception.ServiceException;
import com.froad.recon.reconciliation.model.SnoRecon;


/**
 * 未对账表
 *
 * @author zhangjwei
 * @created 2015-06-16 11:25:57
 * @version 1.0
 */

public interface SnoReconService {
	/**
	 * @Title: inser 
	 * @Description: 插入单条数据
	 * @param snoRecon  snoRecon 对象
	 * @return SnoRecon   返回插入完成的 snoRecon对象
	 */
	public SnoRecon insert(SnoRecon snoRecon) throws ServiceException;

	 /**
     * @Title: batchInser 
     * @Description: 插入多条数据 
     * @param listSnoRecon 插入数据的list    
     * @throws
     */
	public void batchInser(List<SnoRecon> lists) throws ServiceException;

	/**
	 * @Title: delete
	 * @Description: 根据对象删除
	 * @param objId void    
	 */
	
	public void delete(SnoRecon snoRecon) throws ServiceException ;
	/**
	 * @Title: batchDelete 
	 * @Description: 根据 根据对象集合删除数据
	 * @param lists id集合   
	 */
	public void batchDelete(List<SnoRecon> lists) throws ServiceException ;

	/***
	 * 批量删除指定对账日的数据
	 * @param reconDate
	 * @param paramsMap
	 * @throws DBException
	 */
	public void batchDeleteByReconDate(String reconDate,Map<String, Object> paramsMap)throws ServiceException;
	/**
	 * @Title: update 
	 * @Description: 跟新一条数据 snoRecon
	 * @param snoRecon  snoRecon对象
	 * @throws
	 */
	public void update(SnoRecon snoRecon) throws ServiceException;

	/**
	 * @Title: batchUpdate
	 * @Description: 跟新多条数据 snoRecon集合
	 * @param listSnoRecon  snoRecon集合  
	 * @throws
	 */
	
	public void batchUpdate(List<SnoRecon> lists) throws ServiceException ;
	/**
	 * @Title: getById
	 * @Description: 根据编号查询一条数据
	 * @param objID 编号
	 * @return SnoRecon    snoRecon 对象
	 * @throws
	 */
	public SnoRecon getById(String objID)  throws ServiceException ;

	/**
	 * @Title: getAll
	 * @Description: 查询所有的snoRecon数据
	 * @return List    返回一个snoRecon的集合
	 * @throws
	 */
	public List getAll() throws ServiceException ;
	
	/**
     * @Title: getList
     * @Description: 根据 snoRecon对象 查询符合的数据
     * @param snoRecon snoRecon对象
     * @return List    返回一个结合。 
     * @throws
     */
	public List getList(SnoRecon snoRecon)  throws ServiceException ;
	
	/**
	 * @Description: 根据 以paramsMap中key为字段名value为值， 查询符合条件的记录
	 * @param paramsMap 
	 * @param pageNum  页号
	 * @param pageSize 页大小
	 * @throws ServiceException
	 */
	public List<SnoRecon> selectForPagin(Map<String, Object> paramsMap, Integer pageNum, Integer pageSize) 
	       throws ServiceException;
	
	/**
	 * @Description: 根据 以paramsMap中key为字段名value为值， 总条数
	 * @param paramsMap 
	 * @param pageNum  页号
	 * @param pageSize 页大小
	 * @throws ServiceException
	 */
	public Integer selectForPaginCount(Map<String, Object> paramsMap) throws ServiceException;
}
