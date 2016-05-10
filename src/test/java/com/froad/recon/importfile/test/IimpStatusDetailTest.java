
package com.froad.recon.importfile.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.froad.recon.base.BasicTest;
import com.froad.recon.importfile.model.IimpStatusDetail;
import com.froad.recon.importfile.service.IimpStatusDetailService;

public class IimpStatusDetailTest  extends BasicTest  {
	
	@Test
	public void add(){
		try {
			IimpStatusDetailService resourceService = (IimpStatusDetailService) 
					applicationContext.getBean("iimpStatusDetailService");
			
			IimpStatusDetail insertResult=new IimpStatusDetail();
			
			resourceService.insert(insertResult);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			initialSpring();
			IimpStatusDetailService resourceService = (IimpStatusDetailService) 
					applicationContext.getBean("iimpStatusDetailService");
			
			IimpStatusDetail insertResult=new IimpStatusDetail();
//			insertResult.setId("0150615132037000001");
//			insertResult.setOrderNo("12309");
			
			List<IimpStatusDetail>  list=resourceService.getAll();
			for (IimpStatusDetail iimpStatusDetail : list) {
			 // System.out.println( iimpStatusDetail.getId());	
			}
			
			IimpStatusDetail query=resourceService.getById("0150615132037000001");
//		    System.out.println("query"+ query.getId());
		    
		    list=resourceService.getList(insertResult);
			for (IimpStatusDetail iimpStatusDetail : list) {
			  //System.out.println("list"+ iimpStatusDetail.getId());	
			}
			
			Map<String, Object> paramsMap=new HashMap<String, Object>();
			paramsMap.put("id_like", "01");
			
			list=resourceService.selectForPagin(paramsMap, 1, 10);
			for (IimpStatusDetail iimpStatusDetail : list) {
				//System.out.println("map"+ iimpStatusDetail.getId());	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

