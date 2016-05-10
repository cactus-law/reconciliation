
package com.froad.recon.sys.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.froad.recon.sys.model.PlatformDetail;
import com.froad.recon.sys.service.PlatformDetailService;
import com.froad.recon.base.BasicTest;

public class PlatformDetailTest  extends BasicTest  {
	
	@Test
	public void add(){
		try {
			PlatformDetailService resourceService = (PlatformDetailService) 
					applicationContext.getBean("platformDetailService");
			
			PlatformDetail insertResult=new PlatformDetail();
			
			resourceService.insert(insertResult);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			initialSpring();
			PlatformDetailService resourceService = (PlatformDetailService) 
					applicationContext.getBean("platformDetailService");
			
			PlatformDetail insertResult=new PlatformDetail();
//			insertResult.setId("0150615132037000001");
//			insertResult.setOrderNo("12309");
			
			List<PlatformDetail>  list=resourceService.getAll();
			for (PlatformDetail platformDetail : list) {
			 // System.out.println( platformDetail.getId());	
			}
			
			PlatformDetail query=resourceService.getById("0150615132037000001");
//		    System.out.println("query"+ query.getId());
		    
		    list=resourceService.getList(insertResult);
			for (PlatformDetail platformDetail : list) {
			  //System.out.println("list"+ platformDetail.getId());	
			}
			
			Map<String, Object> paramsMap=new HashMap<String, Object>();
			paramsMap.put("id_like", "01");
			
			list=resourceService.selectForPagin(paramsMap, 1, 10);
			for (PlatformDetail platformDetail : list) {
				//System.out.println("map"+ platformDetail.getId());	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

