
package com.froad.recon.importfile.dao;

import java.util.List;
import java.util.Map;

import com.froad.comon.dao.impl.DBException;


/**
 * @author zhangjwei
 * @created 2015-06-16 11:07:28
 * @version 1.0
 */
public interface DynamicDAO   {
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
			throws DBException;
	
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
	public Integer selectForMapCount(String tableName,String cleardate,Map<String, Object> paramsMap) 
			throws DBException;
	
	/**
	 * @Description: 查询指定日期没有上传文件的 记录
	 * @param cleardate  对账日期
	 * @param paramsMap 
	 * @return b.platform_no as \"platformNo\",b.channel_no as \"channelNo\"
	 * @throws DBException
	 */
	public List<Map<String,Object>> selectPlatformByCleardate(String cleardate, Map<String, Object> paramsMap) 
			throws DBException;
}
