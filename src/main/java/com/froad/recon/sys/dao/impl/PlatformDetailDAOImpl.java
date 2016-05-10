
package com.froad.recon.sys.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.froad.comon.constant.BusinessConstant;
import com.froad.comon.dao.impl.DBException;
import com.froad.comon.dao.impl.HibernateBaseDao;
import com.froad.comon.idgenerator.GeneratorHelp;
import com.froad.comon.util.Logger;
import com.froad.comon.util.SqlUtil;
import com.froad.recon.sys.dao.PlatformDetailDAO;
import com.froad.recon.sys.model.PlatformDetail;


/**
 * 平台信息配置表
 * @author zhangjwei
 * @created 2015-06-16 11:20:22
 * @version 1.0
 */

public class PlatformDetailDAOImpl extends HibernateBaseDao implements  PlatformDetailDAO {
	private final static Logger logger = Logger.getLogger(PlatformDetailDAOImpl.class);
	
	/**
	 * @Title: inser 
	 * @Description: 插入单条数据
	 * @param platformDetail  platformDetail 对象
	 * @return PlatformDetail   返回插入完成的 platformDetail对象
	 */
	@SuppressWarnings("unchecked")
	public PlatformDetail insert(PlatformDetail platformDetail) throws DBException {
		if(StringUtils.isBlank(platformDetail.getPlatformDetailNo())){
			String id=GeneratorHelp.generatePlatformDetail();
			if (id!=null&&!"".equals(id)){
				platformDetail.setPlatformDetailNo(id);
			}
		}
		super.save(platformDetail);
		return platformDetail;
	}
	
	/**
	 * @Title: batchInser 
	 * @Description: 插入多条数据 
	 * @param listPlatformDetail 插入数据的list    
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public void batchInser (List<PlatformDetail> lists) throws DBException  {
		if (lists==null|| lists.size()<=0){
			return;
		}
		for (int i = 0; i < lists.size(); i++) {
			PlatformDetail item=lists.get(i);
			if(StringUtils.isBlank(item.getPlatformDetailNo())){
				String id=GeneratorHelp.generatePlatformDetail();
				if (id!=null&&!"".equals(id)){
			        item.setPlatformDetailNo(id);
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
	public void delete(PlatformDetail platformDetail) throws DBException {
		super.delete(platformDetail);
	}
	
	/**
	 * @Title: batchDelete 
	 * @Description: 根据 根据对象集合删除数据
	 * @param lists id集合   
	 */
	@SuppressWarnings("unchecked")
	public void batchDelete(List<PlatformDetail> lists) throws DBException  {
		if (lists==null|| lists.size()<=0){
			return;
		}
		for (int i = 0; i < lists.size(); i++) {
			PlatformDetail item=lists.get(i);
			super.delete(item);
		}
	}
	
	/**
	 * @Title: update 
	 * @Description: 跟新一条数据 platformDetail
	 * @param platformDetail  platformDetail对象
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public void update(PlatformDetail platformDetail) throws DBException {
		super.update(platformDetail);
	}
	
	/**
	 * @Title: batchUpdate
	 * @Description: 跟新多条数据 platformDetail集合
	 * @param listPlatformDetail  platformDetail集合  
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public void batchUpdate(List<PlatformDetail> lists) throws DBException {
		if (lists==null|| lists.size()<=0){
			return;
		}
		for (int i = 0; i < lists.size(); i++) {
			PlatformDetail item=lists.get(i);
			super.update(item);
		}
		
	}
	/**
	 * @Title: getById
	 * @Description: 根据编号查询一条数据
	 * @param objID 编号
	 * @return PlatformDetail    platformDetail 对象
	 * @throws
	 */
	public PlatformDetail getById(String objID) {
		return  (PlatformDetail)super.getHibernateTemplate().get(PlatformDetail.class, objID);
	}
	
	/**
	 * @Title: getAll
	 * @Description: 查询所有的platformDetail数据
	 * @return List    返回一个platformDetail的集合
	 * @throws
	 */
	public List getAll(){
		return  getHibernateTemplate().findByExample(new PlatformDetail());
	}
	
	/**
	 * @Title: getList
	 * @Description: 根据 platformDetail对象 查询符合的数据
	 * @param platformDetail platformDetail对象
	 * @return List    返回一个结合。 
	 * @throws
	 */
	public List getList(PlatformDetail platformDetail) {
		return  getHibernateTemplate().findByExample(platformDetail);
	}
	
	/**
	 * @Description: 根据 以paramsMap中key为字段名value为值， 查询符合条件的记录
	 * @param paramsMap 
	 * @param pageNum  页号
	 * @param pageSize 页大小
	 * @return
	 * @throws DBException
	 */
	public List<PlatformDetail> selectForPagin(Map<String, Object> paramsMap, Integer pageNum, Integer pageSize) 
			throws DBException{
		List<Object> params =new ArrayList<Object>();
		String hql= createHql(paramsMap, params, "list");
		List<PlatformDetail> list = this.findByPaged(hql, params.toArray(), pageNum, pageSize);
		if (list == null || list.size() == 0) {
			return new ArrayList<PlatformDetail>();
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
		hql.append("from PlatformDetail t where 1=1");
		
		SqlUtil.appendResearchConditionMap(paramsMap, hql, params);
		if ("list".equals(type)) {
			hql.append(" order by id desc");
		}
		logger.info("createHql:" +hql.toString()+"==params:"+params.toString());
		return hql.toString();
	}
	
	/**
	 * @param platformNo
	 * @param date
	 * @param reconType
	 * @return
	 * @throws DBException
	 * @see com.froad.recon.sys.dao.PlatformDetailDAO#getListByImport(java.lang.String, java.lang.String, java.lang.String)
	 */
	public List getListByImport(String platformNo ,String date,String reconType) throws DBException{
		List<Object> params =new ArrayList<Object>();
		
		StringBuffer sql=new StringBuffer();
		sql.append(" select b.* From i_imp_status_detail a INNER JOIN platform_detail b  ");
		sql.append(" on a.platform_detail_no=b.platform_detail_no   ");
		sql.append(" where  ");
		sql.append(" b.status=?  ");
		params.add(PlatformDetail.STATUS_YES);
		if(StringUtils.isNotEmpty(platformNo)){
			sql.append(" AND b.platform_no =?  ");
			params.add(platformNo);
		}
		sql.append(" AND a.imp_date =?  ");
		params.add(date);
		if(BusinessConstant.IMP_TYPE.PART.equals(reconType)){
			sql.append(" AND  a.status = ?  ");
			params.add(BusinessConstant.IMP_STATUS.SUCCESS);
		}else{
			sql.append(" AND  a.status in (?,?)  ");
			params.add(BusinessConstant.IMP_STATUS.SUCCESS);
			params.add(BusinessConstant.IMP_STATUS.RECON_SUCCESS);
		}
		logger.info("getListByImportsql :"+  sql.toString() );
		logger.info("getListByImportsqlparams:"+  params );
		List<PlatformDetail> list = this.findBySql(sql.toString(), params.toArray(), PlatformDetail.class);
		if (list == null || list.size() == 0) {
			return new ArrayList<PlatformDetail>();
		}
		return list;
	}
}
