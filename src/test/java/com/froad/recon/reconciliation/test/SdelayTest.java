
package com.froad.recon.reconciliation.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.froad.recon.base.BasicTest;
import com.froad.recon.reconciliation.model.Sdelay;
import com.froad.recon.reconciliation.service.SdelayService;

public class SdelayTest  extends BasicTest  {
	
	@Test
	public void add(){
		try {
			SdelayService resourceService = (SdelayService) 
					applicationContext.getBean("sdelayService");
			
			Sdelay insertResult=new Sdelay();
			
			resourceService.insert(insertResult);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			initialSpring();
			SdelayService resourceService = (SdelayService) 
					applicationContext.getBean("sdelayService");
			
			Sdelay insertResult=new Sdelay();
//			insertResult.setId("0150615132037000001");
//			insertResult.setOrderNo("12309");
			
			List<Sdelay>  list=resourceService.getAll();
			for (Sdelay sdelay : list) {
			 // System.out.println( sdelay.getId());	
			}
			
			Sdelay query=resourceService.getById("0150615132037000001");
//		    System.out.println("query"+ query.getId());
		    
		    list=resourceService.getList(insertResult);
			for (Sdelay sdelay : list) {
			  //System.out.println("list"+ sdelay.getId());	
			}
			
			Map<String, Object> paramsMap=new HashMap<String, Object>();
			paramsMap.put("id_like", "01");
			
			list=resourceService.selectForPagin(paramsMap, 1, 10);
			for (Sdelay sdelay : list) {
				//System.out.println("map"+ sdelay.getId());	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

