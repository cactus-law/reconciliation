package com.froad.comon.util;

/**
 * Created by zhou on 2015/3/31.
 */
public class StringUtil {

    public static boolean isNull(String str) {
   		if (str != null && str.length()>0){
   			return false;
   		}
   		return true;
   	}
    public static boolean isNutNull(String str) {
   		if (str != null && str.length()>0){
   			return true;
   		}
   		return false;
   	}
}
