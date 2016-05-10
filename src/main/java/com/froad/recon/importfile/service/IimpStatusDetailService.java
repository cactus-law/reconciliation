package com.froad.recon.importfile.service;

import java.util.List;
import java.util.Map;

import com.froad.comon.exception.ServiceException;
import com.froad.recon.importfile.model.IimpStatusDetail;
import com.froad.recon.importfile.model.IimpStatusDetailId;


/**
 * 导入状态明细表
 *
 * @author zhangjwei
 * @created 2015-06-16 11:07:24
 * @version 1.0
 */

public interface IimpStatusDetailService {
	/**
	 * @Title: inser 
	 * @Description: 插入单条数据
	 * @param iimpStatusDetail  iimpStatusDetail 对象
	 * @return IimpStatusDetail   返回插入完成的 iimpStatusDetail对象
	 */
	public IimpStatusDetail insert(IimpStatusDetail iimpStatusDetail) throws ServiceException;
	
	/**
	 * 根据主键查询导入状态明细
	 * @param id
	 * @return
	 */
	public IimpStatusDetail getIimpStatusDetail(IimpStatusDetailId id);

	/**
	 * @Title: delete
	 * @Description: 根据对象删除
	 * @param objId void    
	 */
	
	public void delete(IimpStatusDetail iimpStatusDetail) throws ServiceException ;
	/**
	 * @Title: batchDelete 
	 * @Description: 根据 根据对象集合删除数据
	 * @param lists id集合   
	 */
	public void batchDelete(List<IimpStatusDetail> lists) throws ServiceException ;

	/**
	 * @Title: update 
	 * @Description: 跟新一条数据 iimpStatusDetail
	 * @param iimpStatusDetail  iimpStatusDetail对象
	 * @throws
	 */
	public void update(IimpStatusDetail iimpStatusDetail) throws ServiceException;

	/**
	 * @Title: batchUpdate
	 * @Description: 跟新多条数据 iimpStatusDetail集合
	 * @param listIimpStatusDetail  iimpStatusDetail集合  
	 * @throws
	 */
	
	public void batchUpdate(List<IimpStatusDetail> lists) throws ServiceException ;
	
	/**
	 * @Title: updateByReconDate
	 * @Description: sql更新状态为对账日导入状态明细表
	 * @param 
	 * @throws
	 */
	public void updateByReconDate(String reconDate,String status) throws ServiceException; 
	/**
	 * @Title: getById
	 * @Description: 根据编号查询一条数据
	 * @param objID 编号
	 * @return IimpStatusDetail    iimpStatusDetail 对象
	 * @throws
	 */
	public IimpStatusDetail getById(String objID)  throws ServiceException ;

	/**
	 * @Title: getAll
	 * @Description: 查询所有的iimpStatusDetail数据
	 * @return List    返回一个iimpStatusDetail的集合
	 * @throws
	 */
	@SuppressWarnings("rawtypes")
	public List getAll() throws ServiceException ;
	
	/**
     * @Title: getList
     * @Description: 根据 iimpStatusDetail对象 查询符合的数据
     * @param iimpStatusDetail iimpStatusDetail对象
     * @return List    返回一个结合。 
     * @throws
     */
	@SuppressWarnings("rawtypes")
	public List getList(IimpStatusDetail iimpStatusDetail)  throws ServiceException ;
	
	/**
	 * @Description: 根据 以paramsMap中key为字段名value为值， 查询符合条件的记录
	 * @param paramsMap 
	 * @param pageNum  页号
	 * @param pageSize 页大小
	 * @throws ServiceException
	 */
	public List<IimpStatusDetail> selectForPagin(Map<String, Object> paramsMap, Integer pageNum, Integer pageSize) 
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
