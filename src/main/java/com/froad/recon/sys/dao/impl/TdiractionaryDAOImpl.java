
package com.froad.recon.sys.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.froad.comon.dao.impl.DBException;
import com.froad.comon.dao.impl.HibernateBaseDao;
import com.froad.comon.idgenerator.GeneratorHelp;
import com.froad.comon.util.Logger;
import com.froad.comon.util.SqlUtil;
import com.froad.recon.sys.dao.TdiractionaryDAO;
import com.froad.recon.sys.model.Tdiractionary;


/**
 * 字典表
 * @author zhangjwei
 * @created 2015-06-16 11:20:26
 * @version 1.0
 */

public class TdiractionaryDAOImpl extends HibernateBaseDao implements  TdiractionaryDAO {
	private final static Logger logger = Logger.getLogger(TdiractionaryDAOImpl.class);
	
	/**
	 * @Title: inser 
	 * @Description: 插入单条数据
	 * @param tdiractionary  tdiractionary 对象
	 * @return Tdiractionary   返回插入完成的 tdiractionary对象
	 */
	@SuppressWarnings("unchecked")
	public Tdiractionary insert(Tdiractionary tdiractionary) throws DBException {
		if(StringUtils.isBlank(tdiractionary.getDiractionaryNo())){
			String id=GeneratorHelp.generateTdiractionary();
			if (id!=null&&!"".equals(id)){
				tdiractionary.setDiractionaryNo(id);
			}
		}
		super.save(tdiractionary);
		return tdiractionary;
	}
	
	/**
	 * @Title: batchInser 
	 * @Description: 插入多条数据 
	 * @param listTdiractionary 插入数据的list    
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public void batchInser (List<Tdiractionary> lists) throws DBException  {
		if (lists==null|| lists.size()<=0){
			return;
		}
		for (int i = 0; i < lists.size(); i++) {
			Tdiractionary item=lists.get(i);
			if(StringUtils.isBlank(item.getDiractionaryNo())){
				String id=GeneratorHelp.generateTdiractionary();
				if (id!=null&&!"".equals(id)){
			        item.setDiractionaryNo(id);
				}
			}
			super.save(item);
		}
	}
	
	/**
	 * @Title: delete
	 * @Description: 根据对象删除
	 * @param objId void    
	 */
	@SuppressWarnings("unchecked")
	public void delete(Tdiractionary tdiractionary) throws DBException {
		super.delete(tdiractionary);
	}
	
	/**
	 * @Title: batchDelete 
	 * @Description: 根据 根据对象集合删除数据
	 * @param lists id集合   
	 */
	@SuppressWarnings("unchecked")
	public void batchDelete(List<Tdiractionary> lists) throws DBException  {
		if (lists==null|| lists.size()<=0){
			return;
		}
		for (int i = 0; i < lists.size(); i++) {
			Tdiractionary item=lists.get(i);
			super.delete(item);
		}
	}
	
	/**
	 * @Title: update 
	 * @Description: 跟新一条数据 tdiractionary
	 * @param tdiractionary  tdiractionary对象
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public void update(Tdiractionary tdiractionary) throws DBException {
		super.update(tdiractionary);
	}
	
	/**
	 * @Title: batchUpdate
	 * @Description: 跟新多条数据 tdiractionary集合
	 * @param listTdiractionary  tdiractionary集合  
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public void batchUpdate(List<Tdiractionary> lists) throws DBException {
		if (lists==null|| lists.size()<=0){
			return;
		}
		for (int i = 0; i < lists.size(); i++) {
			Tdiractionary item=lists.get(i);
			super.update(item);
		}
		
	}
	/**
	 * @Title: getById
	 * @Description: 根据编号查询一条数据
	 * @param objID 编号
	 * @return Tdiractionary    tdiractionary 对象
	 * @throws
	 */
	public Tdiractionary getById(String objID) {
		return  (Tdiractionary)super.getHibernateTemplate().get(Tdiractionary.class, objID);
	}
	
	/**
	 * @Title: getAll
	 * @Description: 查询所有的tdiractionary数据
	 * @return List    返回一个tdiractionary的集合
	 * @throws
	 */
	public List getAll(){
		return  getHibernateTemplate().findByExample(new Tdiractionary());
	}
	
	/**
	 * @Title: getList
	 * @Description: 根据 tdiractionary对象 查询符合的数据
	 * @param tdiractionary tdiractionary对象
	 * @return List    返回一个结合。 
	 * @throws
	 */
	public List getList(Tdiractionary tdiractionary) {
		return  getHibernateTemplate().findByExample(tdiractionary);
	}
	
	/**
	 * @Description: 根据 以paramsMap中key为字段名value为值， 查询符合条件的记录
	 * @param paramsMap 
	 * @param pageNum  页号
	 * @param pageSize 页大小
	 * @return
	 * @throws DBException
	 */
	public List<Tdiractionary> selectForPagin(Map<String, Object> paramsMap, Integer pageNum, Integer pageSize) 
			throws DBException{
		List<Object> params =new ArrayList<Object>();
		String hql= createHql(paramsMap, params, "list");
		List<Tdiractionary> list = this.findByPaged(hql, params.toArray(), pageNum, pageSize);
		if (list == null || list.size() == 0) {
			return new ArrayList<Tdiractionary>();
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
		hql.append("from Tdiractionary t where 1=1");
		
		SqlUtil.appendResearchConditionMap(paramsMap, hql, params);
		if ("list".equals(type)) {
			hql.append(" order by id desc");
		}
		logger.info("createHql:" +hql.toString()+"==params:"+params.toString());
		return hql.toString();
	}
}
