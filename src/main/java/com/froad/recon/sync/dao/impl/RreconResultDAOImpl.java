
package com.froad.recon.sync.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.froad.comon.dao.impl.DBException;
import com.froad.comon.dao.impl.HibernateBaseDao;
import com.froad.comon.idgenerator.GeneratorHelp;
import com.froad.comon.util.Logger;
import com.froad.comon.util.SqlUtil;
import com.froad.recon.sync.dao.RreconResultDAO;
import com.froad.recon.sync.model.RreconResult;


/**
 * 
 * @author zhangjwei
 * @created 2015-07-31 10:32:55
 * @version 1.0
 */

public class RreconResultDAOImpl extends HibernateBaseDao implements  RreconResultDAO {
	private final static Logger logger = Logger.getLogger(RreconResultDAOImpl.class);
	
	/**
	 * @Title: inser 
	 * @Description: 插入单条数据
	 * @param rreconResult  rreconResult 对象
	 * @return RreconResult   返回插入完成的 rreconResult对象
	 */
	@SuppressWarnings("unchecked")
	public RreconResult insert(RreconResult rreconResult) throws DBException {
		String id=GeneratorHelp.generate();
		if (id!=null&&!"".equals(id)){
	        rreconResult.setId(id);
		}
		super.save(rreconResult);
		return rreconResult;
	}
	
	/**
	 * @Title: batchInser 
	 * @Description: 插入多条数据 
	 * @param listRreconResult 插入数据的list    
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public void batchInser (List<RreconResult> lists) throws DBException  {
		if (lists==null|| lists.size()<=0){
			return;
		}
		for (int i = 0; i < lists.size(); i++) {
			String id=GeneratorHelp.generate();
			RreconResult item=lists.get(i);
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
	public void delete(RreconResult rreconResult) throws DBException {
		super.delete(rreconResult);
	}
	
	/**
	 * @Title: batchDelete 
	 * @Description: 根据 根据对象集合删除数据
	 * @param lists id集合   
	 */
	@SuppressWarnings("unchecked")
	public void batchDelete(List<RreconResult> lists) throws DBException  {
		if (lists==null|| lists.size()<=0){
			return;
		}
		for (int i = 0; i < lists.size(); i++) {
			RreconResult item=lists.get(i);
			super.delete(item);
		}
	}
	/**根据对账日删除*/
	public int deleteByRecon(String recon) throws DBException  {
		List<Object> params =new ArrayList<Object>();
		StringBuffer sql=new StringBuffer();
		sql.append(" DELETE FROM  r_recon_result  where recon_date=?   ");
		params.add(recon);
		return super.deleteBySql(sql.toString(), params.toArray());
	}
	
	
	/**
	 * @Title: update 
	 * @Description: 跟新一条数据 rreconResult
	 * @param rreconResult  rreconResult对象
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public void update(RreconResult rreconResult) throws DBException {
		super.update(rreconResult);
	}
	
	/**
	 * @Title: batchUpdate
	 * @Description: 跟新多条数据 rreconResult集合
	 * @param listRreconResult  rreconResult集合  
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public void batchUpdate(List<RreconResult> lists) throws DBException {
		if (lists==null|| lists.size()<=0){
			return;
		}
		for (int i = 0; i < lists.size(); i++) {
			RreconResult item=lists.get(i);
			super.update(item);
		}
		
	}
	
	
	/**
	 * @Title: getById
	 * @Description: 根据编号查询一条数据
	 * @param objID 编号
	 * @return RreconResult    rreconResult 对象
	 * @throws
	 */
	public RreconResult getById(String objID) {
		return  (RreconResult)super.getHibernateTemplate().get(RreconResult.class, objID);
	}
	
	/**
	 * @Title: getAll
	 * @Description: 查询所有的rreconResult数据
	 * @return List    返回一个rreconResult的集合
	 * @throws
	 */
	public List getAll(){
		return  getHibernateTemplate().findByExample(new RreconResult());
	}
	
	/**
	 * @Title: getList
	 * @Description: 根据 rreconResult对象 查询符合的数据
	 * @param rreconResult rreconResult对象
	 * @return List    返回一个结合。 
	 * @throws
	 */
	public List getList(RreconResult rreconResult) {
		return  getHibernateTemplate().findByExample(rreconResult);
	}
	
	/**
	 * @Description: 根据 以paramsMap中key为字段名value为值， 查询符合条件的记录
	 * @param paramsMap 
	 * @param pageNum  页号
	 * @param pageSize 页大小
	 * @return
	 * @throws DBException
	 */
	public List<RreconResult> selectForPagin(Map<String, Object> paramsMap, Integer pageNum, Integer pageSize) 
			throws DBException{
		List<Object> params =new ArrayList<Object>();
		String hql= createHql(paramsMap, params, "list");
		List<RreconResult> list = this.findByPaged(hql, params.toArray(), pageNum, pageSize);
		if (list == null || list.size() == 0) {
			return new ArrayList<RreconResult>();
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
		hql.append("from RreconResult t where 1=1");
		
		SqlUtil.appendResearchConditionMap(paramsMap, hql, params);
		if ("list".equals(type)) {
			hql.append(" order by id desc");
		}
		logger.info("createHql:" +hql.toString()+"==params:"+params.toString());
		return hql.toString();
	}
	
	
	/**成功笔数和金额**/
	public Map<String,Object> querySuccessTotal(String tableName, String reconDate) throws DBException {
		List<Object> params =new ArrayList<Object>();
		StringBuffer sql=new StringBuffer();
		sql.append(" select  count(*)  as \"total\" ");
		sql.append(" ,sum(case when t.trade_type!='2040' then t.trade_money  else 0 end ) as \"totalMoney\" ");
		sql.append(" ,sum(case when t.trade_type='2040'  then t.trade_money  else 0 end ) as \"refundTotalMoney\" ");
		sql.append("  from s_success t INNER JOIN ").append(tableName+" i " );
		sql.append(" on t.order_no=i.order_no  where  t.recon_date=? ");
		params.add(reconDate);
		List<Map<String, Object>> list = this.queryMapBysql(sql.toString(), params);
		if (list == null || list.isEmpty()) {
			return new HashMap<String, Object>(); 
		}  
		return list.get(0);
	}  
	
	/**差错笔数**/
	public Integer queryExceptionTotal(String tableName, String reconDate) throws DBException {
		List<Object> params =new ArrayList<Object>();
		StringBuffer sql=new StringBuffer();
		sql.append(" select  count(*)  as \"total\" from   ");
		sql.append(" s_exception t INNER JOIN ").append(tableName+" i " );
		sql.append(" on t.order_no=i.order_no  where  t.recon_date=? ");
		params.add(reconDate);
		List list = this.findBySql(sql.toString(), params.toArray());
		if (list == null || list.isEmpty()) {
			return 0;
		}
		return  Integer.parseInt(list.get(0).toString());
	}
	
	
	/**延迟笔数**/
	public Integer queryDelayTotal(String tableName,String platformNo, String reconDate) throws DBException {
		List<Object> params =new ArrayList<Object>();
		StringBuffer sql=new StringBuffer();
		sql.append(" select  count(*)  as \"total\" from   ");
		sql.append(" s_delay t INNER JOIN ").append(tableName+" i " );
		sql.append(" on t.order_no=i.order_no  where  t.recon_date=? and t.platform_no=? ");
		params.add(reconDate);
		params.add(platformNo);
		List list = this.findBySql(sql.toString(), params.toArray());
		if (list == null || list.isEmpty()) {
			return 0;
		}
		return  Integer.parseInt(list.get(0).toString());
	}
	
	
	/**延迟笔数**/
	public Integer queryNoTotal(String tableName,String platformNo, String reconDate) throws DBException {
		List<Object> params =new ArrayList<Object>();
		StringBuffer sql=new StringBuffer();
		sql.append(" select  count(*)  as \"total\" from   ");
		sql.append(" s_no_recon t INNER JOIN ").append(tableName+" i " );
		sql.append(" on t.order_no=i.order_no  where  t.recon_date=?  and t.platform_no=? ");
		params.add(reconDate);
		params.add(platformNo);
		
		List list = this.findBySql(sql.toString(), params.toArray());
		if (list == null || list.isEmpty()) {
			return 0;
		}
		return  Integer.parseInt(list.get(0).toString());
	}
}
