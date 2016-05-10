
package com.froad.recon.importfile.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.froad.recon.base.BasicTest;
import com.froad.recon.importfile.model.IbankDataWuhan;
import com.froad.recon.importfile.service.IbankDataWuhanService;

public class IbankDataWuhanTest  extends BasicTest  {
	
	@Test
	public void add(){
		try {
			IbankDataWuhanService resourceService = (IbankDataWuhanService) 
					applicationContext.getBean("ibankDataWuhanService");
			
			IbankDataWuhan insertResult=new IbankDataWuhan();
			
			resourceService.insert(insertResult);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			initialSpring();
			IbankDataWuhanService resourceService = (IbankDataWuhanService) 
					applicationContext.getBean("ibankDataWuhanService");
			
			IbankDataWuhan insertResult=new IbankDataWuhan();
//			insertResult.setId("0150615132037000001");
//			insertResult.setOrderNo("12309");
			
			List<IbankDataWuhan>  list=resourceService.getAll();
			for (IbankDataWuhan ibankDataWuhan : list) {
			 // System.out.println( ibankDataWuhan.getId());	
			}
			
			IbankDataWuhan query=resourceService.getById("0150615132037000001");
//		    System.out.println("query"+ query.getId());
		    
		    list=resourceService.getList(insertResult);
			for (IbankDataWuhan ibankDataWuhan : list) {
			  //System.out.println("list"+ ibankDataWuhan.getId());	
			}
			
			Map<String, Object> paramsMap=new HashMap<String, Object>();
			paramsMap.put("id_like", "01");
			
			list=resourceService.selectForPagin(paramsMap, 1, 10);
			for (IbankDataWuhan ibankDataWuhan : list) {
				//System.out.println("map"+ ibankDataWuhan.getId());	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

