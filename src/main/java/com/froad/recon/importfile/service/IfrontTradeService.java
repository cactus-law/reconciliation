package com.froad.recon.importfile.service;

import java.util.List;
import java.util.Map;

import com.froad.comon.exception.ServiceException;
import com.froad.recon.importfile.model.IfrontTrade;


/**
 * 前端对账数据表
 *
 * @author zhangjwei
 * @created 2015-06-16 11:07:20
 * @version 1.0
 */

public interface IfrontTradeService {
	/**
	 * @Title: inser 
	 * @Description: 插入单条数据
	 * @param ifrontTrade  ifrontTrade 对象
	 * @return IfrontTrade   返回插入完成的 ifrontTrade对象
	 */
	public IfrontTrade insert(IfrontTrade ifrontTrade) throws ServiceException;

	 /**
     * @Title: batchInser 
     * @Description: 插入多条数据 
     * @param listIfrontTrade 插入数据的list    
     * @throws
     */
	public void batchInser(List<IfrontTrade> lists) throws ServiceException;

	/**
	 * @Title: delete
	 * @Description: 根据对象删除
	 * @param objId void    
	 */
	
	public void delete(IfrontTrade ifrontTrade) throws ServiceException ;
	/**
	 * @Title: batchDelete 
	 * @Description: 根据 根据对象集合删除数据
	 * @param lists id集合   
	 */
	public void batchDelete(List<IfrontTrade> lists) throws ServiceException ;

	/**
	 * @Title: update 
	 * @Description: 跟新一条数据 ifrontTrade
	 * @param ifrontTrade  ifrontTrade对象
	 * @throws
	 */
	public void update(IfrontTrade ifrontTrade) throws ServiceException;

	/**
	 * @Title: batchUpdate
	 * @Description: 跟新多条数据 ifrontTrade集合
	 * @param listIfrontTrade  ifrontTrade集合  
	 * @throws
	 */
	
	public void batchUpdate(List<IfrontTrade> lists) throws ServiceException ;
	/**
	 * @Title: getById
	 * @Description: 根据编号查询一条数据
	 * @param objID 编号
	 * @return IfrontTrade    ifrontTrade 对象
	 * @throws
	 */
	public IfrontTrade getById(String objID)  throws ServiceException ;
	
	/**
	 * @Title: getByOrderNos
	 * @Description: 根据编号查询数据
	 * @param objID 编号
	 * @return List<IfrontTrade>    
	 * @throws
	 */
	public List<IfrontTrade> getByOrderNos(List<String> orderNos)  throws ServiceException ;

	/**
	 * @Title: getAll
	 * @Description: 查询所有的ifrontTrade数据
	 * @return List    返回一个ifrontTrade的集合
	 * @throws
	 */
	public List getAll() throws ServiceException ;
	
	/**
     * @Title: getList
     * @Description: 根据 ifrontTrade对象 查询符合的数据
     * @param ifrontTrade ifrontTrade对象
     * @return List    返回一个结合。 
     * @throws
     */
	public List getList(IfrontTrade ifrontTrade)  throws ServiceException ;
	
	/**
	 * @Description: 根据 以paramsMap中key为字段名value为值， 查询符合条件的记录
	 * @param paramsMap 
	 * @param pageNum  页号
	 * @param pageSize 页大小
	 * @throws ServiceException
	 */
	public List<IfrontTrade> selectForPagin(Map<String, Object> paramsMap, Integer pageNum, Integer pageSize) 
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
