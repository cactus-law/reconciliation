
package com.froad.recon.importfile.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.froad.comon.constant.BusinessConstant;
import com.froad.comon.constant.TableConstant;
import com.froad.comon.dao.impl.DBException;
import com.froad.comon.dao.impl.HibernateBaseDao;
import com.froad.comon.util.Logger;
import com.froad.comon.util.SqlUtil;
import com.froad.recon.importfile.dao.DynamicDAO;
import com.froad.recon.sys.model.PlatformDetail;


/**
 * @author zhangjwei
 * @created 2015-06-16 11:07:28
 * @version 1.0
 */

public class DynamicDAOImpl extends HibernateBaseDao  implements DynamicDAO{
	private final static Logger logger = Logger.getLogger(DynamicDAOImpl.class);
	/**
	 * @Description: 根据 以paramsMap中key为字段名value为值， 查询符合条件的记录
	 * @param tableName 表明 
	 * @param cleardate  对账日期
	 * @param paramsMap 
	 * @param pageNum  页号
	 * @param pageSize 页大小
	 * @return
	 * @throws DBException
	 */
	public List<Map<String,Object>> selectForMap(String tableName,String cleardate, Map<String, Object> paramsMap, Integer pageNum, Integer pageSize) 
			throws DBException{
		List<Object> params =new ArrayList<Object>();
		String sql= createSql(tableName,cleardate,paramsMap, params, "list");
		List<Map<String,Object>> list = this.queryMapBysql(sql, params, pageNum, pageSize);
		if (list == null || list.size() == 0) {
			return new ArrayList<Map<String,Object>>();
		}
		return list;
	}
	
	/**
	 * @Description: 根据 以paramsMap中key为字段名value为值， 总条数
	 * @param tableName 表明 
	 * @param cleardate  对账日期
	 * @param paramsMap 
	 * @param pageNum  页号
	 * @param pageSize 页大小
	 * @return
	 * @throws DBException
	 */
	public Integer selectForMapCount(String tableName,String cleardate,Map<String, Object> paramsMap) throws DBException{
		List<Object> params =new ArrayList<Object>();
		String sql= createSql(tableName, cleardate, paramsMap, params, "count");
		List list = this.findBySql(sql, params.toArray());
		if (list == null || list.isEmpty()) {
			return 0;
		}
		return  Integer.parseInt(list.get(0).toString());
	}
	
	/***根据参数封装sql语句
	 * @param cleardate 
	 * @param tableName **/
	private String createSql(String tableName, String cleardate, Map<String, Object> paramsMap,
			List<Object> params, String type) {
		if(paramsMap==null){
			paramsMap=new HashMap<String, Object>();
		}
		StringBuffer sql=new StringBuffer();
		sql.append(" select ");
		if ("list".equals(type)) {
			sql.append( TableConstant.RECON_FILED +"  , ");
			sql.append( TableConstant.RECON_DATE_FILED +"  ");
		} else {
			sql.append(" count(*) ");
		}
		sql.append("from ");
		sql.append(tableName);
		sql.append("  t ");
		sql.append("  where "+TableConstant.RECON_DATE_FILED+"=? ");
		params.add(cleardate);
		SqlUtil.appendResearchConditionMap(paramsMap, sql, params);
		if ("list".equals(type)) {
			sql.append(" order by  "+TableConstant.RECON_DATE_FILED+" desc");
		}
		logger.info("createHql:" +sql.toString()+"==params:"+params.toString());
		return sql.toString();
	}
	
	
	/**
	 * @Description: 查询指定日期上传文件的 记录
	 * @param cleardate  对账日期
	 * @param paramsMap 
	 * @return b.platform_no as \"platformNo\",b.channel_no as \"channelNo\"
	 * @throws DBException
	 */
	public List<Map<String,Object>> selectPlatformByCleardate(String cleardate, Map<String, Object> paramsMap) 
			throws DBException{
		List<Object> params =new ArrayList<Object>();
		
		StringBuffer sql=new StringBuffer();
		sql.append(" select  a.status as \"status\",b.platform_no  as \"platform_no\",b.channel_no as \"channel_no\"  ");
		sql.append(" from i_imp_status_detail a ");
		sql.append(" INNER JOIN platform_detail b on  a.platform_detail_no=b.platform_detail_no ");
		sql.append("  WHERE a.imp_date= ? AND b.status= ? ");
		params.add(cleardate);
		params.add(PlatformDetail.STATUS_YES);
		
		List<Map<String,Object>> list = this.queryMapBysql(sql.toString(), params);
		if (list == null || list.size() == 0) {
			return new ArrayList<Map<String,Object>>();
		}
		return list;
	}
	
}
