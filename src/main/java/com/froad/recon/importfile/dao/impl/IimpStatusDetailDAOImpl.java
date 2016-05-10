
package com.froad.recon.importfile.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.froad.comon.constant.BusinessConstant;
import com.froad.comon.dao.impl.DBException;
import com.froad.comon.dao.impl.HibernateBaseDao;
import com.froad.comon.util.Logger;
import com.froad.comon.util.SqlUtil;
import com.froad.recon.importfile.dao.IimpStatusDetailDAO;
import com.froad.recon.importfile.model.IimpStatusDetail;
import com.froad.recon.importfile.model.IimpStatusDetailId;


/**
 * 导入状态明细表
 * @author zhangjwei
 * @created 2015-06-16 11:07:24
 * @version 1.0
 */

@SuppressWarnings("rawtypes")
public class IimpStatusDetailDAOImpl extends HibernateBaseDao implements  IimpStatusDetailDAO {
	private final static Logger logger = Logger.getLogger(IimpStatusDetailDAOImpl.class);
	
	/**
	 * @Title: inser 
	 * @Description: 插入单条数据
	 * @param iimpStatusDetail  iimpStatusDetail 对象
	 * @return IimpStatusDetail   返回插入完成的 iimpStatusDetail对象
	 */
	public IimpStatusDetail insert(IimpStatusDetail iimpStatusDetail) throws DBException {
		this.getHibernateTemplate().save(iimpStatusDetail);
 		return iimpStatusDetail;
	}
	
	/**
	 * 根据主键查询导入状态明细
	 * @param id
	 * @return
	 */
	public IimpStatusDetail getIimpStatusDetail(IimpStatusDetailId id){
		IimpStatusDetail iimpStatusDetail = (IimpStatusDetail) this.getHibernateTemplate().get(IimpStatusDetail.class, id);
		return iimpStatusDetail;
	}
	
	/**
	 * @Title: delete
	 * @Description: 根据对象删除
	 * @param objId void    
	 */
	@SuppressWarnings("unchecked")
	public void delete(IimpStatusDetail iimpStatusDetail) throws DBException {
		super.delete(iimpStatusDetail);
	}
	
	/**
	 * @Title: batchDelete 
	 * @Description: 根据 根据对象集合删除数据
	 * @param lists id集合   
	 */
	@SuppressWarnings("unchecked")
	public void batchDelete(List<IimpStatusDetail> lists) throws DBException  {
		if (lists==null|| lists.size()<=0){
			return;
		}
		for (int i = 0; i < lists.size(); i++) {
			IimpStatusDetail item=lists.get(i);
			super.delete(item);
		}
	}
	
	/**
	 * @Title: update 
	 * @Description: 跟新一条数据 iimpStatusDetail
	 * @param iimpStatusDetail  iimpStatusDetail对象
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public void update(IimpStatusDetail iimpStatusDetail) throws DBException {
		super.update(iimpStatusDetail);
	}
	
	/**
	 * @Title: batchUpdate
	 * @Description: 跟新多条数据 iimpStatusDetail集合
	 * @param listIimpStatusDetail  iimpStatusDetail集合  
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public void batchUpdate(List<IimpStatusDetail> lists) throws DBException {
		if (lists==null|| lists.size()<=0){
			return;
		}
		for (int i = 0; i < lists.size(); i++) {
			IimpStatusDetail item=lists.get(i);
			super.update(item);
		}
	}
	
	/**
	 * @Title: updateByReconDate
	 * @Description: sql更新状态为对账日导入状态明细表
	 * @param 
	 * @throws
	 */
	public void updateByReconDate(String reconDate,String status) throws DBException {
		List<Object> params =new ArrayList<Object>();
		StringBuffer sql=new StringBuffer();
		sql.append(" update   i_imp_status_detail  set status=? where status=? and imp_date=? ");
		params.add( status);
		params.add( BusinessConstant.IMP_STATUS.SUCCESS);
		params.add( reconDate);
		logger.info(this.getClass()+" updateByReconDate: "+sql.toString()+"  params"+params);
		updateDataBySql(sql.toString(), params.toArray());
		
	}
	/**
	 * @Title: getById
	 * @Description: 根据编号查询一条数据
	 * @param objID 编号
	 * @return IimpStatusDetail    iimpStatusDetail 对象
	 * @throws
	 */
	public IimpStatusDetail getById(String objID) {
		return  (IimpStatusDetail)super.getHibernateTemplate().get(IimpStatusDetail.class, objID);
	}
	
	/**
	 * @Title: getAll
	 * @Description: 查询所有的iimpStatusDetail数据
	 * @return List    返回一个iimpStatusDetail的集合
	 * @throws
	 */
	public List getAll(){
		return  getHibernateTemplate().findByExample(new IimpStatusDetail());
	}
	
	/**
	 * @Title: getList
	 * @Description: 根据 iimpStatusDetail对象 查询符合的数据
	 * @param iimpStatusDetail iimpStatusDetail对象
	 * @return List    返回一个结合。 
	 * @throws
	 */
	public List getList(IimpStatusDetail iimpStatusDetail) {
		return  getHibernateTemplate().findByExample(iimpStatusDetail);
	}
	
	/**
	 * @Description: 根据 以paramsMap中key为字段名value为值， 查询符合条件的记录
	 * @param paramsMap 
	 * @param pageNum  页号
	 * @param pageSize 页大小
	 * @return
	 * @throws DBException
	 */
	public List<IimpStatusDetail> selectForPagin(Map<String, Object> paramsMap, Integer pageNum, Integer pageSize) 
			throws DBException{
		List<Object> params =new ArrayList<Object>();
		String hql= createHql(paramsMap, params, "list");
		@SuppressWarnings("unchecked")
		List<IimpStatusDetail> list = this.findByPaged(hql, params.toArray(), pageNum, pageSize);
		if (list == null || list.size() == 0) {
			return new ArrayList<IimpStatusDetail>();
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
		hql.append("from IimpStatusDetail t where 1=1");
		
		SqlUtil.appendResearchConditionMap(paramsMap, hql, params);
		if ("list".equals(type)) {
			hql.append(" order by id desc");
		}
		logger.info("createHql:" +hql.toString()+"==params:"+params.toString());
		return hql.toString();
	}
}
