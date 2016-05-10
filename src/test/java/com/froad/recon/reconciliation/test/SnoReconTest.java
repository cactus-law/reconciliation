
package com.froad.recon.reconciliation.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.froad.recon.base.BasicTest;
import com.froad.recon.reconciliation.model.SnoRecon;
import com.froad.recon.reconciliation.service.SnoReconService;

public class SnoReconTest  extends BasicTest  {
	
	@Test
	public void add(){
		try {
			SnoReconService resourceService = (SnoReconService) 
					applicationContext.getBean("snoReconService");
			
			SnoRecon insertResult=new SnoRecon();
			
			resourceService.insert(insertResult);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			initialSpring();
			SnoReconService resourceService = (SnoReconService) 
					applicationContext.getBean("snoReconService");
			
			SnoRecon insertResult=new SnoRecon();
//			insertResult.setId("0150615132037000001");
//			insertResult.setOrderNo("12309");
			
			List<SnoRecon>  list=resourceService.getAll();
			for (SnoRecon snoRecon : list) {
			 // System.out.println( snoRecon.getId());	
			}
			
			SnoRecon query=resourceService.getById("0150615132037000001");
//		    System.out.println("query"+ query.getId());
		    
		    list=resourceService.getList(insertResult);
			for (SnoRecon snoRecon : list) {
			  //System.out.println("list"+ snoRecon.getId());	
			}
			
			Map<String, Object> paramsMap=new HashMap<String, Object>();
			paramsMap.put("id_like", "01");
			
			list=resourceService.selectForPagin(paramsMap, 1, 10);
			for (SnoRecon snoRecon : list) {
				//System.out.println("map"+ snoRecon.getId());	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

