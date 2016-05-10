
package com.froad.recon.sys.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.froad.recon.sys.model.TdiractionaryType;
import com.froad.recon.sys.service.TdiractionaryTypeService;
import com.froad.recon.base.BasicTest;

public class TdiractionaryTypeTest  extends BasicTest  {
	
	@Test
	public void add(){
		try {
			TdiractionaryTypeService resourceService = (TdiractionaryTypeService) 
					applicationContext.getBean("tdiractionaryTypeService");
			
			TdiractionaryType insertResult=new TdiractionaryType();
			
			resourceService.insert(insertResult);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			initialSpring();
			TdiractionaryTypeService resourceService = (TdiractionaryTypeService) 
					applicationContext.getBean("tdiractionaryTypeService");
			
			TdiractionaryType insertResult=new TdiractionaryType();
//			insertResult.setId("0150615132037000001");
//			insertResult.setOrderNo("12309");
			
			List<TdiractionaryType>  list=resourceService.getAll();
			for (TdiractionaryType tdiractionaryType : list) {
			 // System.out.println( tdiractionaryType.getId());	
			}
			
			TdiractionaryType query=resourceService.getById("0150615132037000001");
//		    System.out.println("query"+ query.getId());
		    
		    list=resourceService.getList(insertResult);
			for (TdiractionaryType tdiractionaryType : list) {
			  //System.out.println("list"+ tdiractionaryType.getId());	
			}
			
			Map<String, Object> paramsMap=new HashMap<String, Object>();
			paramsMap.put("id_like", "01");
			
			list=resourceService.selectForPagin(paramsMap, 1, 10);
			for (TdiractionaryType tdiractionaryType : list) {
				//System.out.println("map"+ tdiractionaryType.getId());	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

