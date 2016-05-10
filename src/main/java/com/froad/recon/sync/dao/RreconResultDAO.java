package com.froad.recon.sync.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.froad.comon.dao.impl.DBException;
import com.froad.comon.dao.impl.DBManager;
import com.froad.recon.sync.model.RreconResult;


/**
 * 
 * @author zhangjwei
 * @created 2015-07-31 10:32:55
 * @version 1.0
 */

public interface RreconResultDAO extends DBManager{
	/**
	 * @Title: inser 
	 * @Description: 插入单条数据
	 * @param rreconResult  rreconResult 对象
	 * @return RreconResult   返回插入完成的 rreconResult对象
	 */
	public RreconResult insert(RreconResult rreconResult) throws DBException;
	
	/**
	 * @Title: batchInser 
	 * @Description: 插入多条数据 
	 * @param listRreconResult 插入数据的list    
	 * @throws
	 */
	public void batchInser (List<RreconResult> lists) throws DBException;
	
	/**
	 * @Title: delete
	 * @Description: 根据对象删除
	 * @param objId void    
	 */
	
	public void delete(RreconResult rreconResult) throws DBException ;
	/**
	 * @Title: batchDelete 
	 * @Description: 根据 根据对象集合删除数据
	 * @param lists id集合   
	 */
	public void batchDelete(List<RreconResult> lists) throws DBException ;
	public int deleteByRecon(String recon) throws DBException;
	/**
	 * @Title: update 
	 * @Description: 跟新一条数据 rreconResult
	 * @param rreconResult  rreconResult对象
	 * @throws
	 */
	public void update(RreconResult rreconResult) throws DBException;
	
	/**
	 * @Title: batchUpdate
	 * @Description: 跟新多条数据 rreconResult集合
	 * @param listRreconResult  rreconResult集合  
	 * @throws
	 */
	
	public void batchUpdate(List<RreconResult> lists) throws DBException ;
	/**
	 * @Title: getById
	 * @Description: 根据编号查询一条数据
	 * @param objID 编号
	 * @return RreconResult    rreconResult 对象
	 * @throws
	 */
	public RreconResult getById(String objID) ;
	
	/**
	 * @Title: getAll
	 * @Description: 查询所有的rreconResult数据
	 * @return List    返回一个rreconResult的集合
	 * @throws
	 */
	public List getAll();
	
	/**
	 * @Title: getList
	 * @Description: 根据 rreconResult对象 查询符合的数据
	 * @param rreconResult rreconResult对象
	 * @return List    返回一个结合。 
	 * @throws
	 */
	public List getList(RreconResult rreconResult) ;
	
	/**
	 * @Description: 根据 以paramsMap中key为字段名value为值， 查询符合条件的记录
	 * @param paramsMap 
	 * @param pageNum  页号
	 * @param pageSize 页大小
	 * @throws DBException
	 */
	public List<RreconResult> selectForPagin(Map<String, Object> paramsMap, Integer pageNum, Integer pageSize) 
			throws DBException;
	
	/**
	 * @Description: 根据 以paramsMap中key为字段名value为值， 总条数
	 * @param paramsMap 
	 * @param pageNum  页号
	 * @param pageSize 页大小
	 * @throws DBException
	 */
	public Integer selectForPaginCount(Map<String, Object> paramsMap) throws DBException;
	
	/**成功笔数和金额**/
	public Map<String,Object> querySuccessTotal(String tableName, String reconDate) throws DBException;
	/**差错笔数**/
	public Integer queryExceptionTotal(String tableName, String reconDate) throws DBException ;
	/**延迟笔数**/
	public Integer queryDelayTotal(String tableName,String platformNo, String reconDate) throws DBException;
	/**延迟笔数**/
	public Integer queryNoTotal(String tableName, String platformNo,String reconDate) throws DBException;
}
