package com.froad.comon.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class ErrorMsgUtil {
	public static String printMsg(Throwable e){
		int maxByteNumber=4000;
		StringWriter sw=null;  
		try {
			sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw, true));  
			String str= sw.toString();
			
			byte[] bytes=str.getBytes();
			
			int byteLength=0;
			if(bytes.length>=maxByteNumber){
				byteLength=maxByteNumber-1;
			}else{
				byteLength=bytes.length;
			}
			int tempLen =new String(bytes, 0, byteLength).length();
			str = str.substring(0, tempLen);
			return str;
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally{
			try {
				if(sw!=null){
					sw.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return "error";
	}
}
