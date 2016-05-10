
package com.froad.recon.reconciliation.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.froad.recon.base.BasicTest;
import com.froad.recon.reconciliation.model.Sexception;
import com.froad.recon.reconciliation.service.SexceptionService;

public class SexceptionTest  extends BasicTest  {
	
	@Test
	public void add(){
		try {
			SexceptionService resourceService = (SexceptionService) 
					applicationContext.getBean("sexceptionService");
			
			Sexception insertResult=new Sexception();
			
			resourceService.insert(insertResult);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			initialSpring();
			SexceptionService resourceService = (SexceptionService) 
					applicationContext.getBean("sexceptionService");
			
			Sexception insertResult=new Sexception();
//			insertResult.setId("0150615132037000001");
//			insertResult.setOrderNo("12309");
			
			List<Sexception>  list=resourceService.getAll();
			for (Sexception sexception : list) {
			 // System.out.println( sexception.getId());	
			}
			
			Sexception query=resourceService.getById("0150615132037000001");
//		    System.out.println("query"+ query.getId());
		    
		    list=resourceService.getList(insertResult);
			for (Sexception sexception : list) {
			  //System.out.println("list"+ sexception.getId());	
			}
			
			Map<String, Object> paramsMap=new HashMap<String, Object>();
			paramsMap.put("id_like", "01");
			
			list=resourceService.selectForPagin(paramsMap, 1, 10);
			for (Sexception sexception : list) {
				//System.out.println("map"+ sexception.getId());	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

