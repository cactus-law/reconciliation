package com.froad.comon.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

public class MapUtil {

	/**
	* @Title: findMapByStartWith 
	* @Description: 判断一个map中key值是否包含指定的字符串 ，如果包含返回true, 否则返回false
	* @param map
	* @param targetStartWith
	* @return   
	* @return boolean    
	* @throws
	 */
	public static boolean findMapByStartWith(Map<String, Object> map, String targetStartWith){
		Set<Entry<String, Object>> set=map.entrySet();
		for (Iterator<Entry<String, Object>> iterator = set.iterator(); iterator.hasNext();) {
			Entry<String, Object> entry = iterator.next();
			String itemKey=entry.getKey();
			if(StringUtils.isNotEmpty(itemKey)&&itemKey.contains(targetStartWith)){
				return true;
			}
		}
		return false;
	}
}