
package com.froad.recon.reconciliation.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.froad.recon.base.BasicTest;
import com.froad.recon.reconciliation.model.STradeResult;
import com.froad.recon.reconciliation.service.STradeResultService;

public class STradeResultTest  extends BasicTest  {
	
	@Test
	public void add(){
		try {
			STradeResultService resourceService = (STradeResultService) 
					applicationContext.getBean("sTradeResultService");
			
			STradeResult insertResult=new STradeResult();
			
			resourceService.insert(insertResult);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			initialSpring();
			STradeResultService resourceService = (STradeResultService) 
					applicationContext.getBean("sTradeResultService");
			
			STradeResult insertResult=new STradeResult();
			insertResult.setId("0150615132037000001");
			insertResult.setOrderNo("12309");
			
			
			resourceService.insert(insertResult);
			
			
			List<STradeResult>  list=resourceService.getAll();
			for (STradeResult sTradeResult : list) {
			  System.out.println( sTradeResult.getId());	
			}
			
			STradeResult query=resourceService.getById("0150615132037000001");
		    System.out.println("query"+ query.getId());
		    
		    list=resourceService.getList(insertResult);
			for (STradeResult sTradeResult : list) {
			  System.out.println("list"+ sTradeResult.getId());	
			}
			
			Map<String, Object> paramsMap=new HashMap<String, Object>();
			paramsMap.put("id_like", "01");
			
			list=resourceService.selectForPagin(paramsMap, 1, 10);
			for (STradeResult sTradeResult : list) {
				System.out.println("map"+ sTradeResult.getId());	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

