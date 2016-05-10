
package com.froad.recon.importfile.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.froad.recon.base.BasicTest;
import com.froad.recon.importfile.model.Ipoints;
import com.froad.recon.importfile.service.IpointsService;
import com.froad.recon.importfile.service.QueryService;

public class IpointsTest  extends BasicTest  {
	
	@Test
	public void add(){
		try {
			IpointsService resourceService = (IpointsService) 
					applicationContext.getBean("ipointsService");
			
			Ipoints insertResult=new Ipoints();
			
			resourceService.insert(insertResult);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			initialSpring();
			QueryService queryService = (QueryService) 
					applicationContext.getBean("queryService");
		
			List<String> paraList = new ArrayList<String>();	
			paraList.add("2141115095123156");
			List<Map<String, Object>> list=queryService.queryBill(paraList.toArray());
			
			System.out.println("wejije");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

