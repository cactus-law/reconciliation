package com.froad.recon.importfile.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 定长解析规则
 * @author Administrator
 *
 */
public class FixlengthRuleUtil {
	final static Logger logger = LoggerFactory.getLogger(FixlengthRuleUtil.class);
	/**
	 * 根据规则将数据入库
	 * @param ruleFile
	 * @param dataFile
	 * @throws Exception 
	 */
	public static List<Map<String,Object>> impData(String ruleFile, String dataFile, String reconDate, String table) throws Exception{
		Map<String,Object> dataMap = read(ruleFile);
		dataMap.put("table", table);
		List<Map<String,Object>> list= write(dataMap, dataFile, reconDate);
		return list;
	}
	
	/**
	 * 读取规则文件
	 * @param fileName
	 * @return
	 * @throws Exception 
	 */
	protected static Map<String,Object> read(String fileName) throws Exception{
		Properties prop = new Properties();   
        File file = new File(fileName);
        
        try {   
        	FileInputStream in = new FileInputStream(file);
            prop.load(in);   
            
            Map<String,Object> ruleMap = new HashMap<String, Object>();
            Map<Integer,Object> headField = new TreeMap<Integer,Object>();
            Map<Integer,Object> sortField = new TreeMap<Integer,Object>();
            ruleMap.put("headField", headField);
            ruleMap.put("sortField", sortField);
            for(Map.Entry<Object,Object> entry : prop.entrySet()){
            	
            	String key = (String) entry.getKey();
            	String val = (String) entry.getValue();
            	
            	if(key==null || val==null){
            		continue;
            	}
            	key = key.trim();
            	val = val.trim();
            	if(key.startsWith("h")){//文件头
            		Map<String,String> field = new HashMap<String, String>();
            		String[] keys = key.split("[_]");
            		String index = keys[0].substring(1);
            		field.put(keys[1], val);
            		headField.put(Integer.parseInt(index), field);
            	}else if("charset".equals(key) || "start".equals(key) || "table".equals(key)){
            		ruleMap.put(key, val);
            	}else{
            		String[] keys = key.split("[_]");
            		Map<String,String> field = new HashMap<String, String>();
            		field.put("name", val);
            		field.put("type", keys[1]);
            		field.put("length", keys[2]);
            		sortField.put(Integer.parseInt(keys[0]), field);
            	}
            	
            }
            
            return ruleMap;
        } catch (Exception e) {   
        	logger.error("解析规则文件异常.[e="+e.getMessage()+"]", e); 
        	throw e;
        }
	}
	
	/**
	 * 根据规则解析数据
	 * @param dataMap
	 * @param dataFile
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	protected static List<Map<String,Object>> write(Map<String,Object> ruleMap, String dataFile, String reconDate) throws Exception{
		try{
			@SuppressWarnings("unused")
			Map<Integer,Map<String,String>> headMap = (Map<Integer,Map<String,String>>) ruleMap.get("headField");
			String charset = (String)ruleMap.get("charset");
			charset = charset != null ? charset : "UTF-8";
			File file = new File(dataFile);
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis, charset);
			BufferedReader br = new BufferedReader(isr);
			
			
			String str = null;
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			int row = 0;
			int start = Integer.parseInt((String)ruleMap.get("start"));
			while((str=br.readLine()) != null){
				++row;
				if(row < start){//文件头解析	
					
//					for(Entry<Integer, Map<String,String>> entry : headMap.entrySet()){
//						Map<String,String> field = entry.getValue();
//						for(Entry<String, String> temp : field.entrySet()){
//							String val = temp.getValue();
//							String[] vals = val.split("[_]");
//							String type = vals[0];
//							String length = vals[1];
//						}
//					}
				}else{//数据域解析
					Map<String,Object> resultMap = new HashMap<String,Object>();
					resultMap.put("table", ruleMap.get("table"));
					Map<Integer,Map<String,String>> sortField = (Map<Integer, Map<String,String>>) ruleMap.get("sortField");
					
					int[] strIndex = {0};
					for(Entry<Integer,Map<String,String>> entry : sortField.entrySet()){
						Map<String,String> field = entry.getValue();
						setField(resultMap, field, str, strIndex);
					}
					resultMap.put("recon_date", reconDate);
					list.add(resultMap);
				}
 			}
			br.close();
			return list;
		} catch (Exception e) {
			logger.error(e.getMessage(), e); 
        	throw e;
		}
	}
	
	/**
	 * 赋值
	 * @param resultMap
	 * @param field
	 * @param val
	 */
	protected static void setField(Map<String,Object> resultMap, Map<String,String> field, 
			String val, int[] strIndex){
		String name = field.get("name");
		String type = field.get("type");
		int length = Integer.parseInt(field.get("length"));
		val = val.substring(strIndex[0], strIndex[0]+length).trim();
		if(StringUtils.isNotEmpty(name)){
			if ("string".equals(type)) {
				resultMap.put(name, val);
			} else if("int".equals(type)||"integer".equals(type)) {
				resultMap.put(name, Integer.parseInt(val));
			} else if("long".equals(type)) {
				resultMap.put(name, Long.parseLong(val));
			} else if("double".equals(type)) {
				resultMap.put(name, Double.parseDouble(val));
			} else if("bigdecimal".equals(type)) {
				resultMap.put(name, new BigDecimal(val));
			} else if(type.startsWith("date")) {
				String[] temp = type.split("[$]");
				resultMap.put(name, stringToDate(val, temp[1]));
			}
		}
		strIndex[0] += length;
	}
	
	/**
	 * 将字符串转化为日期类型
	 *
	 * @param date
	 *
	 */
	protected static Date stringToDate(String date, String fmt) {
		SimpleDateFormat formater = new SimpleDateFormat(fmt);
		try {
			Date time = formater.parse(date);
			return time;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
