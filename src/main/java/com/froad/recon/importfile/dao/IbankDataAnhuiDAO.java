package com.froad.recon.importfile.dao;

import java.util.List;
import java.util.Map;

import com.froad.comon.dao.impl.DBException;
import com.froad.comon.dao.impl.DBManager;
import com.froad.recon.importfile.model.IbankDataAnhui;


/**
 * 安徽银行对账数据表
 * @author zhangjwei
 * @created 2015-06-16 11:07:09
 * @version 1.0
 */

public interface IbankDataAnhuiDAO extends DBManager{
	/**
	 * @Title: inser 
	 * @Description: 插入单条数据
	 * @param ibankDataAnhui  ibankDataAnhui 对象
	 * @return IbankDataAnhui   返回插入完成的 ibankDataAnhui对象
	 */
	public IbankDataAnhui insert(IbankDataAnhui ibankDataAnhui) throws DBException;
	
	/**
	 * @Title: batchInser 
	 * @Description: 插入多条数据 
	 * @param listIbankDataAnhui 插入数据的list    
	 * @throws
	 */
	public void batchInser (List<IbankDataAnhui> lists) throws DBException;
	
	/**
	 * @Title: delete
	 * @Description: 根据对象删除
	 * @param objId void    
	 */
	
	public void delete(IbankDataAnhui ibankDataAnhui) throws DBException ;
	/**
	 * @Title: batchDelete 
	 * @Description: 根据 根据对象集合删除数据
	 * @param lists id集合   
	 */
	public void batchDelete(List<IbankDataAnhui> lists) throws DBException ;
	
	/**
	 * @Title: update 
	 * @Description: 跟新一条数据 ibankDataAnhui
	 * @param ibankDataAnhui  ibankDataAnhui对象
	 * @throws
	 */
	public void update(IbankDataAnhui ibankDataAnhui) throws DBException;
	
	/**
	 * @Title: batchUpdate
	 * @Description: 跟新多条数据 ibankDataAnhui集合
	 * @param listIbankDataAnhui  ibankDataAnhui集合  
	 * @throws
	 */
	
	public void batchUpdate(List<IbankDataAnhui> lists) throws DBException ;
	/**
	 * @Title: getById
	 * @Description: 根据编号查询一条数据
	 * @param objID 编号
	 * @return IbankDataAnhui    ibankDataAnhui 对象
	 * @throws
	 */
	public IbankDataAnhui getById(String objID) ;
	
	/**
	 * @Title: getAll
	 * @Description: 查询所有的ibankDataAnhui数据
	 * @return List    返回一个ibankDataAnhui的集合
	 * @throws
	 */
	public List getAll();
	
	/**
	 * @Title: getList
	 * @Description: 根据 ibankDataAnhui对象 查询符合的数据
	 * @param ibankDataAnhui ibankDataAnhui对象
	 * @return List    返回一个结合。 
	 * @throws
	 */
	public List getList(IbankDataAnhui ibankDataAnhui) ;
	
	/**
	 * @Description: 根据 以paramsMap中key为字段名value为值， 查询符合条件的记录
	 * @param paramsMap 
	 * @param pageNum  页号
	 * @param pageSize 页大小
	 * @throws DBException
	 */
	public List<IbankDataAnhui> selectForPagin(Map<String, Object> paramsMap, Integer pageNum, Integer pageSize) 
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
