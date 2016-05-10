package com.froad.recon.importfile.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.froad.comon.util.ExcelUtil;

/**
 * Excel解析规则
 * @author Administrator
 *
 */
public class ExcelRuleUtil {

	final static Logger logger = LoggerFactory.getLogger(ExcelRuleUtil.class);
	
	/**
	 * 根据规则将数据入库
	 * @param ruleFile
	 * @param dataFile
	 * @throws Exception 
	 */
	public static List<Map<String,Object>> impData(String ruleFile, String dataFile, String reconDate, String table) throws Exception{
		Map<String,Object> ruleDataMap = read(ruleFile);
		ruleDataMap.put("table", table);
		List<Map<String,Object>> list= write(ruleDataMap, dataFile, reconDate);
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
        FileInputStream in = null;
        try {   
        	in = new FileInputStream(file);
            prop.load(in);   
            
            Map<String,Object> dataMap = new HashMap<String, Object>();
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
            	if("table".equals(key) || "colCount".equals(key) 
            			|| "start".equals(key) || "end".equals(key)||"readType".equals(key)
            			||"charset".equals(key)||"mark".equals(key)){
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
	protected static List<Map<String,Object>> write(Map<String,Object> ruleDataMap, String dataFile, String reconDate) throws Exception{
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		
		ExcelUtil readExcel = new ExcelUtil();
		String endStr = (String)ruleDataMap.get("end");
		int end = endStr!=null ? Integer.parseInt(endStr) : 0;
		List<List<String>> dataList = readExcel.read(dataFile, end);
		if(dataList==null || dataList.isEmpty()){
			return list;
		}
		int start = Integer.parseInt((String)ruleDataMap.get("start"));
		int colCount = Integer.parseInt((String)ruleDataMap.get("colCount"));
		String readType = (String) ruleDataMap.get("readType");//解析方式
		String mark = (String) ruleDataMap.get("mark");//解析方式
		int index = 0;
		for(List<String> rowList : dataList){
			++index;
			if(index<start){
				continue;
			}
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("table", ruleDataMap.get("table"));
			Map<Integer,Map<String,String>> sortField = (Map<Integer, Map<String,String>>) ruleDataMap.get("sortField");
			
			//1.以配置列为主
			if(readType!=null && "1".equals(readType)){
				for(Entry<Integer, Map<String,String>> fieldMap : sortField.entrySet()){
					Integer keyInt=fieldMap.getKey();
					if(StringUtils.isNotBlank(mark)){
						if(rowList.contains(mark)){
							setField(resultMap, fieldMap.getValue(), rowList.get(keyInt-1));
						}else{
							if((keyInt-2)<rowList.size()){
								setField(resultMap, fieldMap.getValue(), rowList.get(keyInt-2));
							}else{
								setField(resultMap,fieldMap.getValue(), "");
							}
						}
					}else{
						setField(resultMap, fieldMap.getValue(), rowList.get(keyInt-1));
					}
				}
			}else{
				for(int i=0; i<colCount; i++){
					setField(resultMap, sortField.get(i+1), rowList.get(i));
				}
			}
			resultMap.put("recon_date", reconDate);
			
			list.add(resultMap);
		}
		
		return list;
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
		val = val!=null?val.trim():"";
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
	
	public static void main(String[] args) {
		
		try {
			/*List<Map<String,Object>> list=impData("F:\\data\\ubank\\config\\web\\reconciliation\\rule\\bank_money_stk_guilin.srl", 
					"E:\\桌面\\datanew\\20150801\\桂林银行0801.xlsx", "20150801", "i_bank_money_stk");
			*/
			
			List<Map<String,Object>> list=impData("F:\\data\\ubank\\config\\web\\reconciliation\\rule\\bank_money_stk_guilin.srl", 
					"E:\\桌面\\datanew\\0919\\桂林银行.xlsx", "20150801", "i_bank_money_stk");
		    for (Map<String, Object> map : list) {
		    	
		    	 Set<Map.Entry<String, Object>> set=map.entrySet();
		    	 for (Entry<String, Object> entry : set) {
				   System.out.print(entry.getKey()+":"+entry.getValue().toString()+",");
				 }
		    	 System.out.println();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
