
package com.froad.recon.importfile.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.froad.recon.base.BasicTest;
import com.froad.recon.importfile.model.IbankDataTaizhou;
import com.froad.recon.importfile.service.IbankDataTaizhouService;

public class IbankDataTaizhouTest  extends BasicTest  {
	
	@Test
	public void add(){
		try {
			IbankDataTaizhouService resourceService = (IbankDataTaizhouService) 
					applicationContext.getBean("ibankDataTaizhouService");
			
			IbankDataTaizhou insertResult=new IbankDataTaizhou();
			
			resourceService.insert(insertResult);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			initialSpring();
			IbankDataTaizhouService resourceService = (IbankDataTaizhouService) 
					applicationContext.getBean("ibankDataTaizhouService");
			
			IbankDataTaizhou insertResult=new IbankDataTaizhou();
//			insertResult.setId("0150615132037000001");
//			insertResult.setOrderNo("12309");
			
			List<IbankDataTaizhou>  list=resourceService.getAll();
			for (IbankDataTaizhou ibankDataTaizhou : list) {
			 // System.out.println( ibankDataTaizhou.getId());	
			}
			
			IbankDataTaizhou query=resourceService.getById("0150615132037000001");
//		    System.out.println("query"+ query.getId());
		    
		    list=resourceService.getList(insertResult);
			for (IbankDataTaizhou ibankDataTaizhou : list) {
			  //System.out.println("list"+ ibankDataTaizhou.getId());	
			}
			
			Map<String, Object> paramsMap=new HashMap<String, Object>();
			paramsMap.put("id_like", "01");
			
			list=resourceService.selectForPagin(paramsMap, 1, 10);
			for (IbankDataTaizhou ibankDataTaizhou : list) {
				//System.out.println("map"+ ibankDataTaizhou.getId());	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

