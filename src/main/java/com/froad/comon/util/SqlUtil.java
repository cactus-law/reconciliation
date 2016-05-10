package com.froad.comon.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

public class SqlUtil {
	public final static String EQUAL_TEMP = "${key}=?";
	public final static String NO_EQUAL_TEMP = "${key}!=?";
	public final static String LIKE_TEMP = "${key} like ?";
	public final static String IN_TEMP = "${key} in (${values})";
	public final static String NOT_IN_TEMP = "${key} not in (${values})";
	public final static String MIN_TEMP = "${key}>=?";
	public final static String MAX_TEMP = "${key}<=?";

	public final static String SEARCH_RELATION_AND = " and ";
	public final static String SEARCH_RELATION_OR = " or ";
	
	/***查询字段后缀*/
	public final static String FILE_POSTFIX_LIKE = "_like";
	public final static String FILE_POSTFIX_IN = "_in";
	public final static String FILE_POSTFIX_NOTIN = "_notIn";
	public final static String FILE_POSTFIX_MAX = "_max";
	public final static String FILE_POSTFIX_MIN = "_min";
	public final static String FILE_POSTFIX_NOEQUAL = "_noEqual";
	
	

	/**
	* @Title: appendSql 
	* @Description: 在指定的sb 追加appendSql (当appendSql不为空的时候) 
	* @param sb
	* @param appendSql   
	* @return void    
	* @throws
	 */
	public static void appendSql(StringBuffer sb,StringBuffer appendSql){
		appendSql(sb,null,appendSql);
	}
	
 /**
 * @Title: appendSql 
 * @Description: 在指定的sb 依次追加  beforeAppendSql，appendSql
 * @param sb
 * @param beforeAppendSql
 * @param appendSql   
 * @return void    
 * @throws
  */
	public static void appendSql(StringBuffer sb,String beforeAppendSql,StringBuffer appendSql){
		appendSql(sb, beforeAppendSql, appendSql, null);
	}
	/**
	* @Title: appendSql 
	* @Description: 在指定的sb 依次追加  beforeAppendSql，appendSql，afterAppendSql(当appendSql不为空)
	* @param sb
	* @param beforeAppendSql
	* @param appendSql
	* @param afterAppendSql   
	* @return void    
	* @throws
	 */
	public static void appendSql(StringBuffer sb,String beforeAppendSql,StringBuffer appendSql,String afterAppendSql){
		if(appendSql!=null){
			String  sql=appendSql.toString();
			if (!"".equals(sql)&&!"".equals(sql.trim())){
				if(beforeAppendSql!=null){
					sb.append(beforeAppendSql);
				}
				sb.append(sql);
				if(afterAppendSql!=null){
					sb.append(afterAppendSql);
				}
			}
		}
	}
	public static void appendResearchCondition(String fieldKey, Object value,
			String sqlTemp, StringBuffer sb, List<Object> parameters) {
		appendResearchCondition(null, fieldKey, value, sqlTemp, sb, parameters,
				true);
	}

	public static void appendResearchCondition(String fieldKey, Object value,
			String sqlTemp, StringBuffer sb, List<Object> parameters,
			Boolean isCommandParameter) {
		appendResearchCondition(null, fieldKey, value, sqlTemp, sb, parameters,
				isCommandParameter);
	}
	
	/** 
	* @Title: appendResearchConditionMap 
	* @Description: 将map中的key和value追加到 sb后面
	* @param paramsMap
	* @param hql
	* @param parameters   
	* @return void    
	* @throws 
	*/
	public static void appendResearchConditionMap(Map<String, Object> paramsMap,
			StringBuffer sb, List<Object> parameters) {
		Set<Entry<String, Object>>  paramsSet=paramsMap.entrySet();
		for (Iterator<Entry<String, Object>> iterator = paramsSet.iterator(); iterator.hasNext();) {
			Entry<String, Object> entry = iterator.next();
		    String key=entry.getKey();
		    if(StringUtils.isNotBlank(key)){
		    	if(key.endsWith(FILE_POSTFIX_LIKE)){
		    		String value = null;
		    		if(entry.getValue()!=null){
		    		 value= "%"+entry.getValue()+"%";
		    		}
		    		String fileKey=key.substring(0,key.lastIndexOf(FILE_POSTFIX_LIKE));
		    		SqlUtil.appendResearchCondition(fileKey, value,SqlUtil.LIKE_TEMP, sb, parameters);
		    	}else if(key.endsWith(FILE_POSTFIX_IN)){
		    		String fileKey=key.substring(0,key.lastIndexOf(FILE_POSTFIX_IN));
		    		SqlUtil.appendResearchCondition(fileKey, entry.getValue(),SqlUtil.IN_TEMP, sb, parameters);
		    	}else if(key.endsWith(FILE_POSTFIX_NOTIN)){
		    		String fileKey=key.substring(0,key.lastIndexOf(FILE_POSTFIX_NOTIN));
		    		SqlUtil.appendResearchCondition(fileKey, entry.getValue(),SqlUtil.NOT_IN_TEMP, sb, parameters);
		    	}else if(key.endsWith(FILE_POSTFIX_MAX)){
		    		String fileKey=key.substring(0,key.lastIndexOf(FILE_POSTFIX_MAX));
		    		SqlUtil.appendResearchCondition(fileKey, entry.getValue(),SqlUtil.MAX_TEMP, sb, parameters);
		    	}else if(key.endsWith(FILE_POSTFIX_MIN)){
		    		String fileKey=key.substring(0,key.lastIndexOf(FILE_POSTFIX_MIN));
		    		SqlUtil.appendResearchCondition(fileKey, entry.getValue(),SqlUtil.MIN_TEMP, sb, parameters);
		    	}else if(key.endsWith(FILE_POSTFIX_NOEQUAL)){
		    		String fileKey=key.substring(0,key.lastIndexOf(FILE_POSTFIX_NOEQUAL));
		    		SqlUtil.appendResearchCondition(fileKey, entry.getValue(),SqlUtil.NO_EQUAL_TEMP, sb, parameters);
		    	}else{
		    		SqlUtil.appendResearchCondition(key, entry.getValue(),SqlUtil.EQUAL_TEMP, sb, parameters);
		    	}
		    }
		}
	}

	
	public static void main(String[] args) {
		System.out.println("abc_like".substring(0,"abc_like".lastIndexOf("_like")));
	}
	public static void appendResearchCondition(List value,
			String sqlTemp, StringBuffer sb, List<Object> parameters) {
		if (sb == null || value == null||value.size()<=0) {
			return;
		} 
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer commandValues = new StringBuffer();
		
		for (int i = 0; i < value.size(); i++) {
			if (i == value.size() - 1) {
				commandValues.append("?");
			} else {
				commandValues.append("?,");
			}
			parameters.add(value.get(i));
		}
		map.put("values", commandValues);
	 
		String sql = Function.replaceTargetString(sqlTemp, map);
		sb.append(sql);
	}

	public static void appendResearchCondition(Object value,
			String sqlTemp, StringBuffer sb, List<Object> parameters) {
		if (sb == null || value == null) {
			return;
		} 
		sb.append(sqlTemp);
	    parameters.add(value);
	}
	/**
	* @Title: 拼sql语句 
	* @Description: TODO 
	* @param searchRelation 查询关系 and 或者 or
	* @param fieldKey  数据库查询字段名称 如 a.name
	* @param value 数据库查询字段名称 所对应的值
	* @param sqlTemp  模板sql
	* @param sb 拼接的sql
	* @param parameters 拼接的sql 参数
	* @param isCommandParameter  是否加到 "拼接的sql 参数" 中
	* @return void    
	* @throws
	 */
	public static void appendResearchCondition(String searchRelation,
			String fieldKey, Object value, String sqlTemp, StringBuffer sb,
			List<Object> parameters, Boolean isCommandParameter) {
		if (searchRelation == null || "".equals(searchRelation)) {
			searchRelation = SEARCH_RELATION_AND;
		}
		if (sb == null || value == null||"".equals(value.toString().trim())) {
			return;
		} else if (!"".equals(sb.toString())) {
			sb.append(searchRelation);
		}

		Map<String, Object> map = new HashMap<String, Object>();
		if(fieldKey!=null){
			map.put("key", fieldKey);
		}
		if (isCommandParameter) {
			if (value instanceof List) {
				List commandlist = (List) value;
				StringBuffer commandValues = new StringBuffer();

				for (int i = 0; i < commandlist.size(); i++) {
					if (i == commandlist.size() - 1) {
						commandValues.append("?");
					} else {
						commandValues.append("?,");
					}
					parameters.add(commandlist.get(i));
				}
				map.put("values", commandValues);
			} else if (value instanceof Object[]) {
				StringBuffer commandValues = new StringBuffer();
				Object[] commandArray = (Object[]) value;
				for (int i = 0; i < commandArray.length; i++) {
					if (i == commandArray.length - 1) {
						commandValues.append("?");
					} else {
						commandValues.append("?,");
					}
					parameters.add(commandArray[i]);
				}
				map.put("values", commandValues);
			} else if (value instanceof String[]){
				parameters.add(value.toString().trim());
			} else {
				parameters.add(value);
			}
			String sql = Function.replaceTargetString(sqlTemp, map);
			sb.append(sql);
		}
	}

	
	
	
	public static void main1(String[] args) {
 
		StringBuffer sb =null;
	    StringBuffer masterSql=new StringBuffer();
		masterSql.append(sb);
		System.out.println(masterSql);
	}
}
