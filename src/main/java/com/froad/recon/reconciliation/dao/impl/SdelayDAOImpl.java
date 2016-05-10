
package com.froad.recon.reconciliation.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import com.froad.comon.constant.AppConstant;
import com.froad.comon.dao.impl.DBException;
import com.froad.comon.dao.impl.HibernateBaseDao;
import com.froad.comon.idgenerator.GeneratorHelp;
import com.froad.comon.util.Logger;
import com.froad.comon.util.PageUtil;
import com.froad.comon.util.SqlUtil;
import com.froad.recon.reconciliation.dao.SdelayDAO;
import com.froad.recon.reconciliation.model.Sdelay;
import com.froad.recon.reconciliation.model.Sexception;


/**
 * 延时对账表
 * @author zhangjwei
 * @created 2015-06-16 11:25:50
 * @version 1.0
 */

public class SdelayDAOImpl extends HibernateBaseDao implements  SdelayDAO {
	private final static Logger logger = Logger.getLogger(SdelayDAOImpl.class);
	
	/**
	 * @Title: inser 
	 * @Description: 插入单条数据
	 * @param sdelay  sdelay 对象
	 * @return Sdelay   返回插入完成的 sdelay对象
	 */
	@SuppressWarnings("unchecked")
	public Sdelay insert(Sdelay sdelay) throws DBException {
		if(StringUtils.isBlank(sdelay.getId())){
			String id=GeneratorHelp.generateSdelay();
			if (id!=null&&!"".equals(id)){
				sdelay.setId(id);
			}
		}
		super.save(sdelay);
		return sdelay;
	}
	
	/**
	 * @Title: batchInser 
	 * @Description: 插入多条数据 
	 * @param listSdelay 插入数据的list    
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public void batchInser (List<Sdelay> lists) throws DBException  {
		if (lists==null|| lists.size()<=0){
			return;
		}
		for (int i = 0; i < lists.size(); i++) {
			Sdelay item=lists.get(i);
			if(StringUtils.isBlank(item.getId())){
				String id=GeneratorHelp.generateSdelay();
				if (id!=null&&!"".equals(id)){
			        item.setId(id);
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
	public void delete(Sdelay sdelay) throws DBException {
		super.delete(sdelay);
	}
	
	/**
	 * @Title: batchDelete 
	 * @Description: 根据 根据对象集合删除数据
	 * @param lists id集合   
	 */
	@SuppressWarnings("unchecked")
	public void batchDelete(List<Sdelay> lists) throws DBException  {
		if (lists==null|| lists.size()<=0){
			return;
		}
		for (int i = 0; i < lists.size(); i++) {
			Sdelay item=lists.get(i);
			super.delete(item);
		}
	}
	
	/**
	 * @Title: update 
	 * @Description: 跟新一条数据 sdelay
	 * @param sdelay  sdelay对象
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public void update(Sdelay sdelay) throws DBException {
		super.update(sdelay);
	}
	
	/**
	 * @Title: batchUpdate
	 * @Description: 跟新多条数据 sdelay集合
	 * @param listSdelay  sdelay集合  
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public void batchUpdate(List<Sdelay> lists) throws DBException {
		if (lists==null|| lists.size()<=0){
			return;
		}
		for (int i = 0; i < lists.size(); i++) {
			Sdelay item=lists.get(i);
			super.update(item);
		}
		
	}
	/**
	 * @Title: getById
	 * @Description: 根据编号查询一条数据
	 * @param objID 编号
	 * @return Sdelay    sdelay 对象
	 * @throws
	 */
	public Sdelay getById(String objID) {
		return  (Sdelay)super.getHibernateTemplate().get(Sdelay.class, objID);
	}
	
	/**
	 * @Title: getAll
	 * @Description: 查询所有的sdelay数据
	 * @return List    返回一个sdelay的集合
	 * @throws
	 */
	public List getAll(){
		return  getHibernateTemplate().findByExample(new Sdelay());
	}
	
	/**
	 * @Title: getList
	 * @Description: 根据 sdelay对象 查询符合的数据
	 * @param sdelay sdelay对象
	 * @return List    返回一个结合。 
	 * @throws
	 */
	public List getList(Sdelay sdelay) {
		return  getHibernateTemplate().findByExample(sdelay);
	}
	
	/**
	 * @Description: 根据 以paramsMap中key为字段名value为值， 查询符合条件的记录
	 * @param paramsMap 
	 * @param pageNum  页号
	 * @param pageSize 页大小
	 * @return
	 * @throws DBException
	 */
	public List<Sdelay> selectForPagin(Map<String, Object> paramsMap, Integer pageNum, Integer pageSize) 
			throws DBException{
		List<Object> params =new ArrayList<Object>();
		String hql= createHql(paramsMap, params, "list");
		List<Sdelay> list = this.findByPaged(hql, params.toArray(), pageNum, pageSize);
		if (list == null || list.size() == 0) {
			return new ArrayList<Sdelay>();
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
		hql.append("from Sdelay t where 1=1");
		
		SqlUtil.appendResearchConditionMap(paramsMap, hql, params);
		if ("list".equals(type)) {
			hql.append(" order by id desc");
		}
		logger.info("createHql:" +hql.toString()+"==params:"+params.toString());
		return hql.toString();
	}
	
	/***
	 * 批量删除指定对账日的数据
	 * @param reconDate
	 * @param paramsMap
	 * @throws DBException
	 */
	public void batchDeleteByReconDate(String reconDate,Map<String, Object> paramsMap)throws DBException{
		
		String businessType=MapUtils.getString(paramsMap, "businessType");
		StringBuffer hql=new StringBuffer();
		List<Object> params =new ArrayList<Object>();
		hql.append("select count(*) from Sdelay t where reconDate=?");
		params.add(reconDate);
		SqlUtil.appendResearchCondition("businessType", businessType,SqlUtil.EQUAL_TEMP, hql, params);
		
		List list = this.findByHql(hql.toString(),params.toArray());
		int count=0;
		if (list.size()>0) {
			count= Integer.parseInt(list.get(0).toString());
		}
		
		Integer pageSize=AppConstant.BATCH_DELETE;
		Integer totalPage=PageUtil.getTotalPage(count,pageSize);
		Integer pageNo = 1;
		while (pageNo <= totalPage) {
			Session session=getSessionFactory().openSession();
			try {
				Transaction transaction=session.beginTransaction();
				StringBuffer sql=new StringBuffer();
				params =new ArrayList<Object>();
				sql.append(" DELETE FROM  s_delay  where recon_date=? ");
				params.add(reconDate);
				SqlUtil.appendResearchCondition("business_type", businessType,SqlUtil.EQUAL_TEMP, sql, params);
				sql.append(" LIMIT ? ");
				params.add(pageSize);
				Query query=session.createSQLQuery(sql.toString());
				if (params != null) {
					for (int i = 0; i < params.size(); i++) {
						query.setParameter(i, params.get(i));
					}
				}
				query.executeUpdate();
				pageNo++;
				transaction.commit();
			}finally{
				session.close();
			}
		}
	}
	
	/***
	 * 根据对象删除
	 * @throws DBException
	 */
	public Integer deleteByObj(Sdelay obj)throws DBException{
		StringBuffer sql=new StringBuffer();
		List<Object> params =new ArrayList<Object>();
		params =new ArrayList<Object>();
		sql.append(" DELETE FROM  s_delay  where recon_date=?  ");
		params.add(obj.getReconDate());
		if(StringUtils.isNotEmpty(obj.getOrderNo())){
			sql.append(" and order_no=? ");
			params.add(obj.getOrderNo());
		}
		if(StringUtils.isNotEmpty(obj.getPlatformNo())){
			sql.append(" and platform_no=? ");
			params.add(obj.getPlatformNo());
		}
		if(StringUtils.isNotEmpty(obj.getChannelNo())){
			sql.append(" and channel_no=? ");
			params.add(obj.getChannelNo());
		}
		return deleteBySql(sql.toString(), params.toArray());
	}
	
	
	/***
	 * 更新延迟对账的状体
	 * @throws DBException
	 */
	public Integer updateSdelay(Sdelay obj)throws DBException{
		StringBuffer sql=new StringBuffer();
		List<Object> params =new ArrayList<Object>();
		params =new ArrayList<Object>();
		sql.append("update s_delay set recon_status=? where order_no=? and platform_no=? ");
		params.add(obj.getReconStatus());
		params.add(obj.getOrderNo());
		params.add(obj.getPlatformNo());
		
		return (Integer)updateDataBySql(sql.toString(), params.toArray());
	}
	
	
}
