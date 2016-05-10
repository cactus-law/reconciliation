
package com.froad.recon.importfile.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.froad.recon.base.BasicTest;
import com.froad.recon.importfile.model.IfrontTrade;
import com.froad.recon.importfile.service.IfrontTradeService;

public class IfrontTradeTest  extends BasicTest  {
	
	@Test
	public void add(){
		try {
			IfrontTradeService resourceService = (IfrontTradeService) 
					applicationContext.getBean("ifrontTradeService");
			
			IfrontTrade insertResult=new IfrontTrade();
			
			resourceService.insert(insertResult);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			initialSpring();
			IfrontTradeService resourceService = (IfrontTradeService) 
					applicationContext.getBean("ifrontTradeService");
			
			IfrontTrade insertResult=new IfrontTrade();
//			insertResult.setId("0150615132037000001");
//			insertResult.setOrderNo("12309");
			
			List<IfrontTrade>  list=resourceService.getAll();
			for (IfrontTrade ifrontTrade : list) {
			 // System.out.println( ifrontTrade.getId());	
			}
			
			IfrontTrade query=resourceService.getById("0150615132037000001");
//		    System.out.println("query"+ query.getId());
		    
		    list=resourceService.getList(insertResult);
			for (IfrontTrade ifrontTrade : list) {
			  //System.out.println("list"+ ifrontTrade.getId());	
			}
			
			Map<String, Object> paramsMap=new HashMap<String, Object>();
			paramsMap.put("id_like", "01");
			
			list=resourceService.selectForPagin(paramsMap, 1, 10);
			for (IfrontTrade ifrontTrade : list) {
				//System.out.println("map"+ ifrontTrade.getId());	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

