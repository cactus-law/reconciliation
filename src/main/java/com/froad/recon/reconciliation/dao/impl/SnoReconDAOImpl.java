
package com.froad.recon.reconciliation.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.froad.recon.reconciliation.dao.SnoReconDAO;
import com.froad.recon.reconciliation.model.SnoRecon;
import com.froad.recon.reconciliation.model.Ssuccess;


/**
 * 未对账表
 * @author zhangjwei
 * @created 2015-06-16 11:25:58
 * @version 1.0
 */

public class SnoReconDAOImpl extends HibernateBaseDao implements  SnoReconDAO {
	private final static Logger logger = Logger.getLogger(SnoReconDAOImpl.class);
	
	/**
	 * @Title: inser 
	 * @Description: 插入单条数据
	 * @param snoRecon  snoRecon 对象
	 * @return SnoRecon   返回插入完成的 snoRecon对象
	 */
	@SuppressWarnings("unchecked")
	public SnoRecon insert(SnoRecon snoRecon) throws DBException {
		if(StringUtils.isBlank(snoRecon.getId())){
			String id=GeneratorHelp.generateSnoRecon();
			if (id!=null&&!"".equals(id)){
				snoRecon.setId(id);
			}
		}
		super.save(snoRecon);
		return snoRecon;
	}
	
	/**
	 * @Title: batchInser 
	 * @Description: 插入多条数据 
	 * @param listSnoRecon 插入数据的list    
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public void batchInser (List<SnoRecon> lists) throws DBException  {
		if (lists==null|| lists.size()<=0){
			return;
		}
		for (int i = 0; i < lists.size(); i++) {
			SnoRecon item=lists.get(i);
			if(StringUtils.isBlank(item.getId())){
				String id=GeneratorHelp.generateSnoRecon();
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
	public void delete(SnoRecon snoRecon) throws DBException {
		super.delete(snoRecon);
	}
	
	/**
	 * @Title: batchDelete 
	 * @Description: 根据 根据对象集合删除数据
	 * @param lists id集合   
	 */
	@SuppressWarnings("unchecked")
	public void batchDelete(List<SnoRecon> lists) throws DBException  {
		if (lists==null|| lists.size()<=0){
			return;
		}
		for (int i = 0; i < lists.size(); i++) {
			SnoRecon item=lists.get(i);
			super.delete(item);
		}
	}
	
	/**
	 * @Title: update 
	 * @Description: 跟新一条数据 snoRecon
	 * @param snoRecon  snoRecon对象
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public void update(SnoRecon snoRecon) throws DBException {
		super.update(snoRecon);
	}
	
	/**
	 * @Title: batchUpdate
	 * @Description: 跟新多条数据 snoRecon集合
	 * @param listSnoRecon  snoRecon集合  
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public void batchUpdate(List<SnoRecon> lists) throws DBException {
		if (lists==null|| lists.size()<=0){
			return;
		}
		for (int i = 0; i < lists.size(); i++) {
			SnoRecon item=lists.get(i);
			super.update(item);
		}
		
	}
	/**
	 * @Title: getById
	 * @Description: 根据编号查询一条数据
	 * @param objID 编号
	 * @return SnoRecon    snoRecon 对象
	 * @throws
	 */
	public SnoRecon getById(String objID) {
		return  (SnoRecon)super.getHibernateTemplate().get(SnoRecon.class, objID);
	}
	
	/**
	 * @Title: getAll
	 * @Description: 查询所有的snoRecon数据
	 * @return List    返回一个snoRecon的集合
	 * @throws
	 */
	public List getAll(){
		return  getHibernateTemplate().findByExample(new SnoRecon());
	}
	
	/**
	 * @Title: getList
	 * @Description: 根据 snoRecon对象 查询符合的数据
	 * @param snoRecon snoRecon对象
	 * @return List    返回一个结合。 
	 * @throws
	 */
	public List getList(SnoRecon snoRecon) {
		return  getHibernateTemplate().findByExample(snoRecon);
	}
	
	/**
	 * @Description: 根据 以paramsMap中key为字段名value为值， 查询符合条件的记录
	 * @param paramsMap 
	 * @param pageNum  页号
	 * @param pageSize 页大小
	 * @return
	 * @throws DBException
	 */
	public List<SnoRecon> selectForPagin(Map<String, Object> paramsMap, Integer pageNum, Integer pageSize) 
			throws DBException{
		List<Object> params =new ArrayList<Object>();
		String hql= createHql(paramsMap, params, "list");
		List<SnoRecon> list = this.findByPaged(hql, params.toArray(), pageNum, pageSize);
		if (list == null || list.size() == 0) {
			return new ArrayList<SnoRecon>();
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
		hql.append("from SnoRecon t where 1=1");
		
		SqlUtil.appendResearchConditionMap(paramsMap, hql, params);
		if ("list".equals(type)) {
			hql.append(" order by orderNo desc");
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
		StringBuffer hql=new StringBuffer();
		List<Object> params =new ArrayList<Object>();
		hql.append("select count(*) from SnoRecon t where reconDate=?");
		params.add(reconDate);
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
			try{
				Transaction transaction=session.beginTransaction();
				StringBuffer sql=new StringBuffer();
				params =new ArrayList<Object>();
				sql.append(" DELETE FROM  s_no_recon  where recon_date=? LIMIT ?  ");
				params.add(reconDate);
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
	public void deleteByObj(SnoRecon obj)throws DBException{
		StringBuffer sql=new StringBuffer();
		List<Object> params =new ArrayList<Object>();
		params =new ArrayList<Object>();
		sql.append(" DELETE FROM  s_no_recon  where recon_date=?  and order_no=? and platform_no=? ");
		params.add(obj.getReconDate());
		params.add(obj.getOrderNo());
		params.add(obj.getPlatformNo());
		deleteBySql(sql.toString(), params.toArray());
	}
}
