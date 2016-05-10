package com.froad.recon.importfile.service;

import java.util.List;
import java.util.Map;

import com.froad.comon.exception.ServiceException;
import com.froad.recon.importfile.model.IbankDataTaizhou;


/**
 * 台州银行对账数据表
 *
 * @author zhangjwei
 * @created 2015-06-16 11:07:12
 * @version 1.0
 */

public interface IbankDataTaizhouService {
	/**
	 * @Title: inser 
	 * @Description: 插入单条数据
	 * @param ibankDataTaizhou  ibankDataTaizhou 对象
	 * @return IbankDataTaizhou   返回插入完成的 ibankDataTaizhou对象
	 */
	public IbankDataTaizhou insert(IbankDataTaizhou ibankDataTaizhou) throws ServiceException;

	 /**
     * @Title: batchInser 
     * @Description: 插入多条数据 
     * @param listIbankDataTaizhou 插入数据的list    
     * @throws
     */
	public void batchInser(List<IbankDataTaizhou> lists) throws ServiceException;

	/**
	 * @Title: delete
	 * @Description: 根据对象删除
	 * @param objId void    
	 */
	
	public void delete(IbankDataTaizhou ibankDataTaizhou) throws ServiceException ;
	/**
	 * @Title: batchDelete 
	 * @Description: 根据 根据对象集合删除数据
	 * @param lists id集合   
	 */
	public void batchDelete(List<IbankDataTaizhou> lists) throws ServiceException ;

	/**
	 * @Title: update 
	 * @Description: 跟新一条数据 ibankDataTaizhou
	 * @param ibankDataTaizhou  ibankDataTaizhou对象
	 * @throws
	 */
	public void update(IbankDataTaizhou ibankDataTaizhou) throws ServiceException;

	/**
	 * @Title: batchUpdate
	 * @Description: 跟新多条数据 ibankDataTaizhou集合
	 * @param listIbankDataTaizhou  ibankDataTaizhou集合  
	 * @throws
	 */
	
	public void batchUpdate(List<IbankDataTaizhou> lists) throws ServiceException ;
	/**
	 * @Title: getById
	 * @Description: 根据编号查询一条数据
	 * @param objID 编号
	 * @return IbankDataTaizhou    ibankDataTaizhou 对象
	 * @throws
	 */
	public IbankDataTaizhou getById(String objID)  throws ServiceException ;

	/**
	 * @Title: getAll
	 * @Description: 查询所有的ibankDataTaizhou数据
	 * @return List    返回一个ibankDataTaizhou的集合
	 * @throws
	 */
	public List getAll() throws ServiceException ;
	
	/**
     * @Title: getList
     * @Description: 根据 ibankDataTaizhou对象 查询符合的数据
     * @param ibankDataTaizhou ibankDataTaizhou对象
     * @return List    返回一个结合。 
     * @throws
     */
	public List getList(IbankDataTaizhou ibankDataTaizhou)  throws ServiceException ;
	
	/**
	 * @Description: 根据 以paramsMap中key为字段名value为值， 查询符合条件的记录
	 * @param paramsMap 
	 * @param pageNum  页号
	 * @param pageSize 页大小
	 * @throws ServiceException
	 */
	public List<IbankDataTaizhou> selectForPagin(Map<String, Object> paramsMap, Integer pageNum, Integer pageSize) 
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
