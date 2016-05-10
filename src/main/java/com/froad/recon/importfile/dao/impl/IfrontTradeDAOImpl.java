
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
import com.froad.recon.importfile.dao.IfrontTradeDAO;
import com.froad.recon.importfile.model.IfrontTrade;


/**
 * 前端对账数据表
 * @author zhangjwei
 * @created 2015-06-16 11:07:21
 * @version 1.0
 */

public class IfrontTradeDAOImpl extends HibernateBaseDao implements  IfrontTradeDAO {
	private final static Logger logger = Logger.getLogger(IfrontTradeDAOImpl.class);
	
	/**
	 * @Title: inser 
	 * @Description: 插入单条数据
	 * @param ifrontTrade  ifrontTrade 对象
	 * @return IfrontTrade   返回插入完成的 ifrontTrade对象
	 */
	@SuppressWarnings("unchecked")
	public IfrontTrade insert(IfrontTrade ifrontTrade) throws DBException {
		String id=GeneratorHelp.generateIfrontTrade();
		if (id!=null&&!"".equals(id)){
	        ifrontTrade.setId(id);
		}
		super.save(ifrontTrade);
		return ifrontTrade;
	}
	
	/**
	 * @Title: batchInser 
	 * @Description: 插入多条数据 
	 * @param listIfrontTrade 插入数据的list    
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public void batchInser (List<IfrontTrade> lists) throws DBException  {
		if (lists==null|| lists.size()<=0){
			return;
		}
		for (int i = 0; i < lists.size(); i++) {
			String id=GeneratorHelp.generateIfrontTrade();
			IfrontTrade item=lists.get(i);
			if (id!=null&&!"".equals(id)){
		        item.setId(id);
				
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
	public void delete(IfrontTrade ifrontTrade) throws DBException {
		super.delete(ifrontTrade);
	}
	
	/**
	 * @Title: batchDelete 
	 * @Description: 根据 根据对象集合删除数据
	 * @param lists id集合   
	 */
	@SuppressWarnings("unchecked")
	public void batchDelete(List<IfrontTrade> lists) throws DBException  {
		if (lists==null|| lists.size()<=0){
			return;
		}
		for (int i = 0; i < lists.size(); i++) {
			IfrontTrade item=lists.get(i);
			super.delete(item);
		}
	}
	
	/**
	 * @Title: update 
	 * @Description: 跟新一条数据 ifrontTrade
	 * @param ifrontTrade  ifrontTrade对象
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public void update(IfrontTrade ifrontTrade) throws DBException {
		super.update(ifrontTrade);
	}
	
	/**
	 * @Title: batchUpdate
	 * @Description: 跟新多条数据 ifrontTrade集合
	 * @param listIfrontTrade  ifrontTrade集合  
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public void batchUpdate(List<IfrontTrade> lists) throws DBException {
		if (lists==null|| lists.size()<=0){
			return;
		}
		for (int i = 0; i < lists.size(); i++) {
			IfrontTrade item=lists.get(i);
			super.update(item);
		}
		
	}
	/**
	 * @Title: getById
	 * @Description: 根据编号查询一条数据
	 * @param objID 编号
	 * @return IfrontTrade    ifrontTrade 对象
	 * @throws
	 */
	public IfrontTrade getById(String objID) {
		return  (IfrontTrade)super.getHibernateTemplate().get(IfrontTrade.class, objID);
	}
	
	/**
	 * @Title: getAll
	 * @Description: 查询所有的ifrontTrade数据
	 * @return List    返回一个ifrontTrade的集合
	 * @throws
	 */
	public List getAll(){
		return  getHibernateTemplate().findByExample(new IfrontTrade());
	}
	
	/**
	 * @Title: getList
	 * @Description: 根据 ifrontTrade对象 查询符合的数据
	 * @param ifrontTrade ifrontTrade对象
	 * @return List    返回一个结合。 
	 * @throws
	 */
	public List getList(IfrontTrade ifrontTrade) {
		return  getHibernateTemplate().findByExample(ifrontTrade);
	}
	
	/**
	 * @Description: 根据 以paramsMap中key为字段名value为值， 查询符合条件的记录
	 * @param paramsMap 
	 * @return
	 * @throws DBException
	 */
	public List<IfrontTrade> selectMap(Map<String, Object> paramsMap) 
			throws DBException{
		List<Object> params =new ArrayList<Object>();
		String hql= createHql(paramsMap, params, "list");
		List<IfrontTrade> list = this.findByHql(hql, params.toArray());
		if (list == null || list.size() == 0) {
			return new ArrayList<IfrontTrade>();
		}
		return list;
	}
	
	/**
	 * @Description: 根据 以paramsMap中key为字段名value为值， 查询符合条件的记录
	 * @param paramsMap 
	 * @param pageNum  页号
	 * @param pageSize 页大小
	 * @return
	 * @throws DBException
	 */
	public List<IfrontTrade> selectForPagin(Map<String, Object> paramsMap, Integer pageNum, Integer pageSize) 
			throws DBException{
		List<Object> params =new ArrayList<Object>();
		String hql= createHql(paramsMap, params, "list");
		List<IfrontTrade> list = this.findByPaged(hql, params.toArray(), pageNum, pageSize);
		if (list == null || list.size() == 0) {
			return new ArrayList<IfrontTrade>();
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
		hql.append("from IfrontTrade t where 1=1");
		
		SqlUtil.appendResearchConditionMap(paramsMap, hql, params);
		if ("list".equals(type)) {
			hql.append(" order by id desc");
		}
		logger.info("createHql:" +hql.toString()+"==params:"+params.toString());
		return hql.toString();
	}
}
