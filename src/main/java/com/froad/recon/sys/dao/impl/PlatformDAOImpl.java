
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
import com.froad.recon.sys.dao.PlatformDAO;
import com.froad.recon.sys.model.Platform;


/**
 * 平台表
 * @author zhangjwei
 * @created 2015-06-16 11:20:18
 * @version 1.0
 */

public class PlatformDAOImpl extends HibernateBaseDao implements  PlatformDAO {
	private final static Logger logger = Logger.getLogger(PlatformDAOImpl.class);
	
	/**
	 * @Title: inser 
	 * @Description: 插入单条数据
	 * @param platform  platform 对象
	 * @return Platform   返回插入完成的 platform对象
	 */
	@SuppressWarnings("unchecked")
	public Platform insert(Platform platform) throws DBException {
		if(StringUtils.isBlank(platform.getPlatformNo())){
			String id=GeneratorHelp.generatePlatform();
			if (id!=null&&!"".equals(id)){
				platform.setPlatformNo(id);
			}
		}
		super.save(platform);
		return platform;
	}
	
	/**
	 * @Title: batchInser 
	 * @Description: 插入多条数据 
	 * @param listPlatform 插入数据的list    
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public void batchInser (List<Platform> lists) throws DBException  {
		if (lists==null|| lists.size()<=0){
			return;
		}
		for (int i = 0; i < lists.size(); i++) {
			Platform item=lists.get(i);
			if(StringUtils.isBlank(item.getPlatformNo())){
				String id=GeneratorHelp.generatePlatform();
				if (id!=null&&!"".equals(id)){
			        item.setPlatformNo(id);
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
	public void delete(Platform platform) throws DBException {
		super.delete(platform);
	}
	
	/**
	 * @Title: batchDelete 
	 * @Description: 根据 根据对象集合删除数据
	 * @param lists id集合   
	 */
	@SuppressWarnings("unchecked")
	public void batchDelete(List<Platform> lists) throws DBException  {
		if (lists==null|| lists.size()<=0){
			return;
		}
		for (int i = 0; i < lists.size(); i++) {
			Platform item=lists.get(i);
			super.delete(item);
		}
	}
	
	/**
	 * @Title: update 
	 * @Description: 跟新一条数据 platform
	 * @param platform  platform对象
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public void update(Platform platform) throws DBException {
		super.update(platform);
	}
	
	/**
	 * @Title: batchUpdate
	 * @Description: 跟新多条数据 platform集合
	 * @param listPlatform  platform集合  
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public void batchUpdate(List<Platform> lists) throws DBException {
		if (lists==null|| lists.size()<=0){
			return;
		}
		for (int i = 0; i < lists.size(); i++) {
			Platform item=lists.get(i);
			super.update(item);
		}
		
	}
	/**
	 * @Title: getById
	 * @Description: 根据编号查询一条数据
	 * @param objID 编号
	 * @return Platform    platform 对象
	 * @throws
	 */
	public Platform getById(String objID) {
		return  (Platform)super.getHibernateTemplate().get(Platform.class, objID);
	}
	
	/**
	 * @Title: getAll
	 * @Description: 查询所有的platform数据
	 * @return List    返回一个platform的集合
	 * @throws
	 */
	public List getAll(){
		return  getHibernateTemplate().findByExample(new Platform());
	}
	
	/**
	 * @Title: getList
	 * @Description: 根据 platform对象 查询符合的数据
	 * @param platform platform对象
	 * @return List    返回一个结合。 
	 * @throws
	 */
	public List getList(Platform platform) {
		return  getHibernateTemplate().findByExample(platform);
	}
	
	/**
	 * @Description: 根据 以paramsMap中key为字段名value为值， 查询符合条件的记录
	 * @param paramsMap 
	 * @param pageNum  页号
	 * @param pageSize 页大小
	 * @return
	 * @throws DBException
	 */
	public List<Platform> selectForPagin(Map<String, Object> paramsMap, Integer pageNum, Integer pageSize) 
			throws DBException{
		List<Object> params =new ArrayList<Object>();
		String hql= createHql(paramsMap, params, "list");
		List<Platform> list = this.findByPaged(hql, params.toArray(), pageNum, pageSize);
		if (list == null || list.size() == 0) {
			return new ArrayList<Platform>();
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
		hql.append("from Platform t where 1=1");
		
		SqlUtil.appendResearchConditionMap(paramsMap, hql, params);
		if ("list".equals(type)) {
			hql.append(" order by id desc");
		}
		logger.info("createHql:" +hql.toString()+"==params:"+params.toString());
		return hql.toString();
	}
}
