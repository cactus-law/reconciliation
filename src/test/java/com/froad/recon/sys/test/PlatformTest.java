
package com.froad.recon.sys.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.froad.recon.sys.model.Platform;
import com.froad.recon.sys.service.PlatformService;
import com.froad.recon.base.BasicTest;

public class PlatformTest  extends BasicTest  {
	
	@Test
	public void add(){
		try {
			PlatformService resourceService = (PlatformService) 
					applicationContext.getBean("platformService");
			
			Platform insertResult=new Platform();
			
			resourceService.insert(insertResult);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			initialSpring();
			PlatformService resourceService = (PlatformService) 
					applicationContext.getBean("platformService");
			
			Platform insertResult=new Platform();
//			insertResult.setId("0150615132037000001");
//			insertResult.setOrderNo("12309");
			
			List<Platform>  list=resourceService.getAll();
			for (Platform platform : list) {
			 // System.out.println( platform.getId());	
			}
			
			Platform query=resourceService.getById("0150615132037000001");
//		    System.out.println("query"+ query.getId());
		    
		    list=resourceService.getList(insertResult);
			for (Platform platform : list) {
			  //System.out.println("list"+ platform.getId());	
			}
			
			Map<String, Object> paramsMap=new HashMap<String, Object>();
			paramsMap.put("id_like", "01");
			
			list=resourceService.selectForPagin(paramsMap, 1, 10);
			for (Platform platform : list) {
				//System.out.println("map"+ platform.getId());	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

