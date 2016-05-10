package com.froad.recon.reconciliation.service;

import java.util.List;
import java.util.Map;

import com.froad.comon.dao.impl.DBException;
import com.froad.comon.exception.ServiceException;
import com.froad.recon.reconciliation.model.Sdelay;
import com.froad.recon.sys.model.PlatformDetail;


/**
 * 延时对账表
 *
 * @author zhangjwei
 * @created 2015-06-16 11:25:49
 * @version 1.0
 */

public interface SdelayService {
	/**
	 * @Title: inser 
	 * @Description: 插入单条数据
	 * @param sdelay  sdelay 对象
	 * @return Sdelay   返回插入完成的 sdelay对象
	 */
	public Sdelay insert(Sdelay sdelay) throws ServiceException;

	 /**
     * @Title: batchInser 
     * @Description: 插入多条数据 
     * @param listSdelay 插入数据的list    
     * @throws
     */
	public void batchInser(List<Sdelay> lists) throws ServiceException;

	/**
	 * @Title: delete
	 * @Description: 根据对象删除
	 * @param objId void    
	 */
	
	public void delete(Sdelay sdelay) throws ServiceException ;
	/**
	 * @Title: batchDelete 
	 * @Description: 根据 根据对象集合删除数据
	 * @param lists id集合   
	 */
	public void batchDelete(List<Sdelay> lists) throws ServiceException ;

	/***
	 * 批量删除指定对账日的数据
	 * @param reconDate
	 * @param paramsMap
	 * @throws DBException
	 */
	public void batchDeleteByReconDate(String reconDate,Map<String, Object> paramsMap)throws ServiceException;
	/**
	 * @Title: update 
	 * @Description: 跟新一条数据 sdelay
	 * @param sdelay  sdelay对象
	 * @throws
	 */
	public void update(Sdelay sdelay) throws ServiceException;

	/**
	 * @Title: batchUpdate
	 * @Description: 跟新多条数据 sdelay集合
	 * @param listSdelay  sdelay集合  
	 * @throws
	 */
	
	public void batchUpdate(List<Sdelay> lists) throws ServiceException ;
	/**
	 * @Title: getById
	 * @Description: 根据编号查询一条数据
	 * @param objID 编号
	 * @return Sdelay    sdelay 对象
	 * @throws
	 */
	public Sdelay getById(String objID)  throws ServiceException ;

	/**
	 * @Title: getAll
	 * @Description: 查询所有的sdelay数据
	 * @return List    返回一个sdelay的集合
	 * @throws
	 */
	public List getAll() throws ServiceException ;
	
	/**
     * @Title: getList
     * @Description: 根据 sdelay对象 查询符合的数据
     * @param sdelay sdelay对象
     * @return List    返回一个结合。 
     * @throws
     */
	public List getList(Sdelay sdelay)  throws ServiceException ;
	
	/**
	 * @Description: 根据 以paramsMap中key为字段名value为值， 查询符合条件的记录
	 * @param paramsMap 
	 * @param pageNum  页号
	 * @param pageSize 页大小
	 * @throws ServiceException
	 */
	public List<Sdelay> selectForPagin(Map<String, Object> paramsMap, Integer pageNum, Integer pageSize) 
	       throws ServiceException;
	
	/**
	 * @Description: 根据 以paramsMap中key为字段名value为值， 总条数
	 * @param paramsMap 
	 * @param pageNum  页号
	 * @param pageSize 页大小
	 * @throws ServiceException
	 */
	public Integer selectForPaginCount(Map<String, Object> paramsMap) throws ServiceException;
	
	/***
	 * 根据对象删除
	 * @throws DBException
	 */
	public Integer deleteByObj(Sdelay obj)throws ServiceException;
	
	/**
	 * @Title: batchInser 
	 * @Description: 退款延迟数据处理
	 * @throws
	 */
	public void refundSdelay(List<Map<String,Object>> lists,PlatformDetail platformDetail,String reconDate) throws ServiceException;
	
	/***
	 * 通过退款流水号 ，查询账单系统相关表，获取账户号，修改延迟对账表
	 * @param paramsMap
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 */
	public List<Sdelay> refundSdelayUpdate(Map<String, Object> paramsMap, Integer pageNum, Integer pageSize) 
			throws ServiceException;
	

	/***
	 * 更新延迟对账的状体
	 * @throws DBException
	 */
	public void updateSdelays(List<Sdelay> list)throws ServiceException;
}
