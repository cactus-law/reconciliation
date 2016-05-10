
package com.froad.recon.sys.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.froad.recon.sys.model.Tdiractionary;
import com.froad.recon.sys.service.TdiractionaryService;
import com.froad.recon.base.BasicTest;

public class TdiractionaryTest  extends BasicTest  {
	
	@Test
	public void add(){
		try {
			TdiractionaryService resourceService = (TdiractionaryService) 
					applicationContext.getBean("tdiractionaryService");
			
			Tdiractionary insertResult=new Tdiractionary();
			
			resourceService.insert(insertResult);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			initialSpring();
			TdiractionaryService resourceService = (TdiractionaryService) 
					applicationContext.getBean("tdiractionaryService");
			
			Tdiractionary insertResult=new Tdiractionary();
//			insertResult.setId("0150615132037000001");
//			insertResult.setOrderNo("12309");
			
			List<Tdiractionary>  list=resourceService.getAll();
			for (Tdiractionary tdiractionary : list) {
			 // System.out.println( tdiractionary.getId());	
			}
			
			Tdiractionary query=resourceService.getById("0150615132037000001");
//		    System.out.println("query"+ query.getId());
		    
		    list=resourceService.getList(insertResult);
			for (Tdiractionary tdiractionary : list) {
			  //System.out.println("list"+ tdiractionary.getId());	
			}
			
			Map<String, Object> paramsMap=new HashMap<String, Object>();
			paramsMap.put("id_like", "01");
			
			list=resourceService.selectForPagin(paramsMap, 1, 10);
			for (Tdiractionary tdiractionary : list) {
				//System.out.println("map"+ tdiractionary.getId());	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

