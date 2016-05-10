
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
import com.froad.recon.sys.dao.TdiractionaryTypeDAO;
import com.froad.recon.sys.model.TdiractionaryType;


/**
 * 字典类型表
 * @author zhangjwei
 * @created 2015-06-16 11:20:30
 * @version 1.0
 */

public class TdiractionaryTypeDAOImpl extends HibernateBaseDao implements  TdiractionaryTypeDAO {
	private final static Logger logger = Logger.getLogger(TdiractionaryTypeDAOImpl.class);
	
	/**
	 * @Title: inser 
	 * @Description: 插入单条数据
	 * @param tdiractionaryType  tdiractionaryType 对象
	 * @return TdiractionaryType   返回插入完成的 tdiractionaryType对象
	 */
	@SuppressWarnings("unchecked")
	public TdiractionaryType insert(TdiractionaryType tdiractionaryType) throws DBException {
		if(StringUtils.isBlank(tdiractionaryType.getTypeNo())){
			String id=GeneratorHelp.generateTdiractionaryType();
			if (id!=null&&!"".equals(id)){
				tdiractionaryType.setTypeNo(id);
			}
		}
		super.save(tdiractionaryType);
		return tdiractionaryType;
	}
	
	/**
	 * @Title: batchInser 
	 * @Description: 插入多条数据 
	 * @param listTdiractionaryType 插入数据的list    
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public void batchInser (List<TdiractionaryType> lists) throws DBException  {
		if (lists==null|| lists.size()<=0){
			return;
		}
		for (int i = 0; i < lists.size(); i++) {
			TdiractionaryType item=lists.get(i);
			if(StringUtils.isBlank(item.getTypeNo())){
				String id=GeneratorHelp.generateTdiractionaryType();
				if (id!=null&&!"".equals(id)){
					item.setTypeNo(id);
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
	public void delete(TdiractionaryType tdiractionaryType) throws DBException {
		super.delete(tdiractionaryType);
	}
	
	/**
	 * @Title: batchDelete 
	 * @Description: 根据 根据对象集合删除数据
	 * @param lists id集合   
	 */
	@SuppressWarnings("unchecked")
	public void batchDelete(List<TdiractionaryType> lists) throws DBException  {
		if (lists==null|| lists.size()<=0){
			return;
		}
		for (int i = 0; i < lists.size(); i++) {
			TdiractionaryType item=lists.get(i);
			super.delete(item);
		}
	}
	
	/**
	 * @Title: update 
	 * @Description: 跟新一条数据 tdiractionaryType
	 * @param tdiractionaryType  tdiractionaryType对象
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public void update(TdiractionaryType tdiractionaryType) throws DBException {
		super.update(tdiractionaryType);
	}
	
	/**
	 * @Title: batchUpdate
	 * @Description: 跟新多条数据 tdiractionaryType集合
	 * @param listTdiractionaryType  tdiractionaryType集合  
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public void batchUpdate(List<TdiractionaryType> lists) throws DBException {
		if (lists==null|| lists.size()<=0){
			return;
		}
		for (int i = 0; i < lists.size(); i++) {
			TdiractionaryType item=lists.get(i);
			super.update(item);
		}
		
	}
	/**
	 * @Title: getById
	 * @Description: 根据编号查询一条数据
	 * @param objID 编号
	 * @return TdiractionaryType    tdiractionaryType 对象
	 * @throws
	 */
	public TdiractionaryType getById(String objID) {
		return  (TdiractionaryType)super.getHibernateTemplate().get(TdiractionaryType.class, objID);
	}
	
	/**
	 * @Title: getAll
	 * @Description: 查询所有的tdiractionaryType数据
	 * @return List    返回一个tdiractionaryType的集合
	 * @throws
	 */
	public List getAll(){
		return  getHibernateTemplate().findByExample(new TdiractionaryType());
	}
	
	/**
	 * @Title: getList
	 * @Description: 根据 tdiractionaryType对象 查询符合的数据
	 * @param tdiractionaryType tdiractionaryType对象
	 * @return List    返回一个结合。 
	 * @throws
	 */
	public List getList(TdiractionaryType tdiractionaryType) {
		return  getHibernateTemplate().findByExample(tdiractionaryType);
	}
	
	/**
	 * @Description: 根据 以paramsMap中key为字段名value为值， 查询符合条件的记录
	 * @param paramsMap 
	 * @param pageNum  页号
	 * @param pageSize 页大小
	 * @return
	 * @throws DBException
	 */
	public List<TdiractionaryType> selectForPagin(Map<String, Object> paramsMap, Integer pageNum, Integer pageSize) 
			throws DBException{
		List<Object> params =new ArrayList<Object>();
		String hql= createHql(paramsMap, params, "list");
		List<TdiractionaryType> list = this.findByPaged(hql, params.toArray(), pageNum, pageSize);
		if (list == null || list.size() == 0) {
			return new ArrayList<TdiractionaryType>();
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
		hql.append("from TdiractionaryType t where 1=1");
		
		SqlUtil.appendResearchConditionMap(paramsMap, hql, params);
		if ("list".equals(type)) {
			hql.append(" order by id desc");
		}
		logger.info("createHql:" +hql.toString()+"==params:"+params.toString());
		return hql.toString();
	}
}
