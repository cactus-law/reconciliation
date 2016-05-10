package com.froad.recon.importfile.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 分隔符解析规则
 * @author Administrator
 *
 */
public class SplitRuleUtil {
	
	final static Logger logger = LoggerFactory.getLogger(SplitRuleUtil.class);
	
	/**
	 * 根据规则将数据入库
	 * @param ruleFile
	 * @param dataFile
	 * @throws Exception 
	 */
	public static List<Map<String,Object>> analysysData(String ruleFile, String dataFile, String reconDate, String table) throws Exception{
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
		Map<String,Object> dataMap = new HashMap<String, Object>();
		Properties prop = new Properties();   
        File file = new File(fileName);
        FileInputStream in = null;
        try {   
        	in = new FileInputStream(file);
            prop.load(in);   
            
            Map<Integer,Object> sortField = new TreeMap<Integer,Object>();
            dataMap.put("sortField", sortField);
            for(Map.Entry<Object,Object> entry : prop.entrySet()){
            	
            	String key = (String) entry.getKey();
            	String val = (String) entry.getValue();
            	
            	if(key==null || val==null){
            		continue;
            	}
            	key = key.trim();
            	val = val.trim();
            	if("split".equals(key)||"readType".equals(key)
            			||"start".equals(key)||"charset".equals(key)){
            		dataMap.put(key, val);
            	}else{
            		String[] keys = key.split("[_]");
            		Map<String,String> field = new HashMap<String, String>();
            		field.put("name", val);
            		field.put("type", keys[1]);
            		sortField.put(Integer.parseInt(keys[0]), field);
            	}
            	
            }
            
            return dataMap;
        } catch (Exception e) {
        	logger.error("解析规则文件异常.[e="+e.getMessage()+"]", e); 
        	throw e;
        }finally{
        	if(in != null){
        		try {
					in.close();
				} catch (IOException e) {
					in = null;
				}
        	}
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
	protected static List<Map<String,Object>> write(Map<String,Object> dataMap, String dataFile, String reconDate) throws Exception{
		
		File file = new File(dataFile);
		BufferedReader br = null;
		try{
			String charset = (String)dataMap.get("charset");
			charset = charset!=null?charset:"UTF-8";
			
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis, charset);
			br = new BufferedReader(isr);

			String str = null;
			String[] temp = null;
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			String startStr = (String)dataMap.get("start");
			int start = startStr!=null?Integer.parseInt(startStr):0;
			int index =0;
			String split = (String)dataMap.get("split");//分隔符
			String readType = (String) dataMap.get("readType");//解析方式
			while((str=br.readLine()) != null){
				//注释内容
				if(str.startsWith("#")){
					continue;
				}
				++index;
				if(index<start){//判断开始
					continue;
				}
				temp = str.split(split);
				str = str.trim();
				if(temp==null || temp.length<2){
					continue;
				}
				
				Map<String,Object> resultMap = new HashMap<String,Object>();
				resultMap.put("table", dataMap.get("table"));
				Map<Integer,Map<String,String>> sortField = (Map<Integer, Map<String,String>>) dataMap.get("sortField");
				
				//1.以配置列为主
				if(readType!=null && "1".equals(readType)){
					for(Entry<Integer, Map<String,String>> fieldMap : sortField.entrySet()){
						if((fieldMap.getKey()-1)<temp.length){
							setField(resultMap, fieldMap.getValue(), temp[fieldMap.getKey()-1]);
						}
					}
				}else{//0.以数据为主(默认)
					for(int i=0;i<temp.length;i++){
						setField(resultMap, sortField.get(i+1), temp[i]);
					}
				}
				
				resultMap.put("recon_date", reconDate);//对账日期(对账约定字段)
				
				list.add(resultMap);
 			}
			return list;
		} catch (Exception e) {
			logger.error("根据规则解析数据异常.[e="+e.getMessage()+"]", e);
			throw  e;
		}finally{
			try {
				if(br != null){
					br.close();
				}
			} catch (IOException e) {
				br = null;
			}
		}
	}
	
	/**
	 * 赋值
	 * @param resultMap
	 * @param field
	 * @param val
	 */
	protected static void setField(Map<String,Object> resultMap, Map<String,String> field, String val){
		String name = field.get("name");
		String type = field.get("type");
		val = val.replaceAll("'", "").trim();
		if ("string".equals(type)) {
    		resultMap.put(name, val);
		} else if("int".equals(type)||"integer".equals(type)) {
			resultMap.put(name, Integer.parseInt(val));
		} else if("long".equals(type)) {
			resultMap.put(name, Long.parseLong(val));
		} else if("double".equals(type)) {
			resultMap.put(name, Double.parseDouble(val));
		} else if("bigdecimal".equals(type)) {
				val = "".equals(val)?"0":val;
				resultMap.put(name, new BigDecimal(val));
		} else if(type.startsWith("date")) {
			String[] temp = type.split("[$]");
			resultMap.put(name, stringToDate(val, temp[1]));
		}
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
