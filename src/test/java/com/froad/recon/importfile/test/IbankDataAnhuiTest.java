
package com.froad.recon.importfile.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.froad.recon.base.BasicTest;
import com.froad.recon.importfile.model.IbankDataAnhui;
import com.froad.recon.importfile.service.IbankDataAnhuiService;

public class IbankDataAnhuiTest  extends BasicTest  {
	
	@Test
	public void add(){
		try {
			IbankDataAnhuiService resourceService = (IbankDataAnhuiService) 
					applicationContext.getBean("ibankDataAnhuiService");
			
			IbankDataAnhui insertResult=new IbankDataAnhui();
			
			resourceService.insert(insertResult);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			initialSpring();
			IbankDataAnhuiService resourceService = (IbankDataAnhuiService) 
					applicationContext.getBean("ibankDataAnhuiService");
			
			IbankDataAnhui insertResult=new IbankDataAnhui();
//			insertResult.setId("0150615132037000001");
//			insertResult.setOrderNo("12309");
			
			List<IbankDataAnhui>  list=resourceService.getAll();
			for (IbankDataAnhui ibankDataAnhui : list) {
			 // System.out.println( ibankDataAnhui.getId());	
			}
			
			IbankDataAnhui query=resourceService.getById("0150615132037000001");
//		    System.out.println("query"+ query.getId());
		    
		    list=resourceService.getList(insertResult);
			for (IbankDataAnhui ibankDataAnhui : list) {
			  //System.out.println("list"+ ibankDataAnhui.getId());	
			}
			
			Map<String, Object> paramsMap=new HashMap<String, Object>();
			paramsMap.put("id_like", "01");
			
			list=resourceService.selectForPagin(paramsMap, 1, 10);
			for (IbankDataAnhui ibankDataAnhui : list) {
				//System.out.println("map"+ ibankDataAnhui.getId());	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

