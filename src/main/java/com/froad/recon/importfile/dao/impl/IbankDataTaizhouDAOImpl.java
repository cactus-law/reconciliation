
package com.froad.recon.importfile.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.froad.comon.dao.impl.DBException;
import com.froad.comon.dao.impl.HibernateBaseDao;
import com.froad.comon.idgenerator.GeneratorHelp;
import com.froad.comon.util.Logger;
import com.froad.comon.util.SqlUtil;
import com.froad.recon.importfile.dao.IbankDataTaizhouDAO;
import com.froad.recon.importfile.model.IbankDataTaizhou;


/**
 * 台州银行对账数据表
 * @author zhangjwei
 * @created 2015-06-16 11:07:13
 * @version 1.0
 */

public class IbankDataTaizhouDAOImpl extends HibernateBaseDao implements  IbankDataTaizhouDAO {
	private final static Logger logger = Logger.getLogger(IbankDataTaizhouDAOImpl.class);
	
	/**
	 * @Title: inser 
	 * @Description: 插入单条数据
	 * @param ibankDataTaizhou  ibankDataTaizhou 对象
	 * @return IbankDataTaizhou   返回插入完成的 ibankDataTaizhou对象
	 */
	@SuppressWarnings("unchecked")
	public IbankDataTaizhou insert(IbankDataTaizhou ibankDataTaizhou) throws DBException {
		String id=GeneratorHelp.generateIbankDataTaizhou();
		if (id!=null&&!"".equals(id)){
	        ibankDataTaizhou.setOrderNo(id);
		}
		super.save(ibankDataTaizhou);
		return ibankDataTaizhou;
	}
	
	/**
	 * @Title: batchInser 
	 * @Description: 插入多条数据 
	 * @param listIbankDataTaizhou 插入数据的list    
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public void batchInser (List<IbankDataTaizhou> lists) throws DBException  {
		if (lists==null|| lists.size()<=0){
			return;
		}
		for (int i = 0; i < lists.size(); i++) {
			String id=GeneratorHelp.generateIbankDataTaizhou();
			IbankDataTaizhou item=lists.get(i);
			if (id!=null&&!"".equals(id)){
		        item.setOrderNo(id);
				
				super.save(item);
			}
		}
	}
	
	/**
	 * @Title: delete
	 * @Description: 根据对象删除
	 * @param objId void    
	 */
	@SuppressWarnings("unchecked")
	public void delete(IbankDataTaizhou ibankDataTaizhou) throws DBException {
		super.delete(ibankDataTaizhou);
	}
	
	/**
	 * @Title: batchDelete 
	 * @Description: 根据 根据对象集合删除数据
	 * @param lists id集合   
	 */
	@SuppressWarnings("unchecked")
	public void batchDelete(List<IbankDataTaizhou> lists) throws DBException  {
		if (lists==null|| lists.size()<=0){
			return;
		}
		for (int i = 0; i < lists.size(); i++) {
			IbankDataTaizhou item=lists.get(i);
			super.delete(item);
		}
	}
	
	/**
	 * @Title: update 
	 * @Description: 跟新一条数据 ibankDataTaizhou
	 * @param ibankDataTaizhou  ibankDataTaizhou对象
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public void update(IbankDataTaizhou ibankDataTaizhou) throws DBException {
		super.update(ibankDataTaizhou);
	}
	
	/**
	 * @Title: batchUpdate
	 * @Description: 跟新多条数据 ibankDataTaizhou集合
	 * @param listIbankDataTaizhou  ibankDataTaizhou集合  
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public void batchUpdate(List<IbankDataTaizhou> lists) throws DBException {
		if (lists==null|| lists.size()<=0){
			return;
		}
		for (int i = 0; i < lists.size(); i++) {
			IbankDataTaizhou item=lists.get(i);
			super.update(item);
		}
		
	}
	/**
	 * @Title: getById
	 * @Description: 根据编号查询一条数据
	 * @param objID 编号
	 * @return IbankDataTaizhou    ibankDataTaizhou 对象
	 * @throws
	 */
	public IbankDataTaizhou getById(String objID) {
		return  (IbankDataTaizhou)super.getHibernateTemplate().get(IbankDataTaizhou.class, objID);
	}
	
	/**
	 * @Title: getAll
	 * @Description: 查询所有的ibankDataTaizhou数据
	 * @return List    返回一个ibankDataTaizhou的集合
	 * @throws
	 */
	public List getAll(){
		return  getHibernateTemplate().findByExample(new IbankDataTaizhou());
	}
	
	/**
	 * @Title: getList
	 * @Description: 根据 ibankDataTaizhou对象 查询符合的数据
	 * @param ibankDataTaizhou ibankDataTaizhou对象
	 * @return List    返回一个结合。 
	 * @throws
	 */
	public List getList(IbankDataTaizhou ibankDataTaizhou) {
		return  getHibernateTemplate().findByExample(ibankDataTaizhou);
	}
	
	/**
	 * @Description: 根据 以paramsMap中key为字段名value为值， 查询符合条件的记录
	 * @param paramsMap 
	 * @param pageNum  页号
	 * @param pageSize 页大小
	 * @return
	 * @throws DBException
	 */
	public List<IbankDataTaizhou> selectForPagin(Map<String, Object> paramsMap, Integer pageNum, Integer pageSize) 
			throws DBException{
		List<Object> params =new ArrayList<Object>();
		String hql= createHql(paramsMap, params, "list");
		List<IbankDataTaizhou> list = this.findByPaged(hql, params.toArray(), pageNum, pageSize);
		if (list == null || list.size() == 0) {
			return new ArrayList<IbankDataTaizhou>();
		}
		return list;
	}
	
	/**
	 * @Description: 根据 以paramsMap中key为字段名value为值， 总条数
	 * @param paramsMap 
	 * @param pageNum  页号
	 * @param pageSize 页大小
	 * @return
	 * @throws DBException
	 */
	public Integer selectForPaginCount(Map<String, Object> paramsMap) throws DBException{
		List<Object> params =new ArrayList<Object>();
		String hql= createHql(paramsMap, params, "count");
		List list = this.findByHql(hql, params.toArray());
		if (list == null || list.isEmpty()) {
			return 0;
		}
		return  Integer.parseInt(list.get(0).toString());
	}
	
	/***根据参数封装 HQL语句**/
	private String createHql(Map<String, Object> paramsMap,
			List<Object> params, String type) {
		if(paramsMap==null){
			paramsMap=new HashMap<String, Object>();
		}
		StringBuffer hql=new StringBuffer();
		if ("list".equals(type)) {
		} else {
			hql.append("select count(*) ");
		}
		hql.append("from IbankDataTaizhou t where 1=1");
		
		SqlUtil.appendResearchConditionMap(paramsMap, hql, params);
		if ("list".equals(type)) {
			hql.append(" order by id desc");
		}
		logger.info("createHql:" +hql.toString()+"==params:"+params.toString());
		return hql.toString();
	}
}
