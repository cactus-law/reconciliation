
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
import com.froad.recon.reconciliation.dao.SsuccessDAO;
import com.froad.recon.reconciliation.model.Ssuccess;


/**
 * 对账成功表
 * @author zhangjwei
 * @created 2015-06-16 11:26:02
 * @version 1.0
 */

public class SsuccessDAOImpl extends HibernateBaseDao implements  SsuccessDAO {
	private final static Logger logger = Logger.getLogger(SsuccessDAOImpl.class);
	
	/**
	 * @Title: inser 
	 * @Description: 插入单条数据
	 * @param ssuccess  ssuccess 对象
	 * @return Ssuccess   返回插入完成的 ssuccess对象
	 */
	@SuppressWarnings("unchecked")
	public Ssuccess insert(Ssuccess ssuccess) throws DBException {
		if(StringUtils.isBlank(ssuccess.getOrderNo())){
			String id=GeneratorHelp.generateSsuccess();
			if (id!=null&&!"".equals(id)){
				ssuccess.setOrderNo(id);
			}
		}
		super.save(ssuccess);
		return ssuccess;
	}
	
	/**
	 * @Title: batchInser 
	 * @Description: 插入多条数据 
	 * @param listSsuccess 插入数据的list    
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public void batchInser (List<Ssuccess> lists) throws DBException  {
		if (lists==null|| lists.size()<=0){
			return;
		}
		for (int i = 0; i < lists.size(); i++) {
			Ssuccess item=lists.get(i);
			if(StringUtils.isBlank(item.getOrderNo())){
				String id=GeneratorHelp.generateSsuccess();
				if (id!=null&&!"".equals(id)){
			        item.setOrderNo(id);
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
	public void delete(Ssuccess ssuccess) throws DBException {
		super.delete(ssuccess);
	}
	
	/**
	 * @Title: batchDelete 
	 * @Description: 根据 根据对象集合删除数据
	 * @param lists id集合   
	 */
	@SuppressWarnings("unchecked")
	public void batchDelete(List<Ssuccess> lists) throws DBException  {
		if (lists==null|| lists.size()<=0){
			return;
		}
		for (int i = 0; i < lists.size(); i++) {
			Ssuccess item=lists.get(i);
			super.delete(item);
		}
	}
	
	/**
	 * @Title: update 
	 * @Description: 跟新一条数据 ssuccess
	 * @param ssuccess  ssuccess对象
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public void update(Ssuccess ssuccess) throws DBException {
		super.update(ssuccess);
	}
	
	/**
	 * @Title: batchUpdate
	 * @Description: 跟新多条数据 ssuccess集合
	 * @param listSsuccess  ssuccess集合  
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public void batchUpdate(List<Ssuccess> lists) throws DBException {
		if (lists==null|| lists.size()<=0){
			return;
		}
		for (int i = 0; i < lists.size(); i++) {
			Ssuccess item=lists.get(i);
			super.update(item);
		}
		
	}
	/**
	 * @Title: getById
	 * @Description: 根据编号查询一条数据
	 * @param objID 编号
	 * @return Ssuccess    ssuccess 对象
	 * @throws
	 */
	public Ssuccess getById(String objID) {
		return  (Ssuccess)super.getHibernateTemplate().get(Ssuccess.class, objID);
	}
	
	/**
	 * @Title: getAll
	 * @Description: 查询所有的ssuccess数据
	 * @return List    返回一个ssuccess的集合
	 * @throws
	 */
	public List getAll(){
		return  getHibernateTemplate().findByExample(new Ssuccess());
	}
	
	/**
	 * @Title: getList
	 * @Description: 根据 ssuccess对象 查询符合的数据
	 * @param ssuccess ssuccess对象
	 * @return List    返回一个结合。 
	 * @throws
	 */
	public List getList(Ssuccess ssuccess) {
		return  getHibernateTemplate().findByExample(ssuccess);
	}
	
	/**
	 * @Description: 根据 以paramsMap中key为字段名value为值， 查询符合条件的记录
	 * @param paramsMap 
	 * @param pageNum  页号
	 * @param pageSize 页大小
	 * @return
	 * @throws DBException
	 */
	public List<Ssuccess> selectForPagin(Map<String, Object> paramsMap, Integer pageNum, Integer pageSize) 
			throws DBException{
		List<Object> params =new ArrayList<Object>();
		String hql= createHql(paramsMap, params, "list");
		List<Ssuccess> list = this.findByPaged(hql, params.toArray(), pageNum, pageSize);
		if (list == null || list.size() == 0) {
			return new ArrayList<Ssuccess>();
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
		hql.append("from Ssuccess t where 1=1");
		
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
		StringBuffer hql=new StringBuffer();
		List<Object> params =new ArrayList<Object>();
		hql.append("select count(*) from Ssuccess t where reconDate=?");
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
				sql.append(" DELETE FROM  s_success  where recon_date=? LIMIT ?  ");
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
	public void deleteByObj(Ssuccess ssuccess)throws DBException{
		StringBuffer sql=new StringBuffer();
		List<Object> params =new ArrayList<Object>();
		params =new ArrayList<Object>();
		sql.append(" DELETE FROM  s_success  where recon_date=?  and order_no=? ");
		params.add(ssuccess.getReconDate());
		params.add(ssuccess.getOrderNo());
		deleteBySql(sql.toString(), params.toArray());
	}
}
