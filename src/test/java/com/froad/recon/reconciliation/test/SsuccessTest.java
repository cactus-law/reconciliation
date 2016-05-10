
package com.froad.recon.reconciliation.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.froad.recon.base.BasicTest;
import com.froad.recon.reconciliation.model.Ssuccess;
import com.froad.recon.reconciliation.service.SsuccessService;

public class SsuccessTest  extends BasicTest  {
	
	@Test
	public void add(){
		try {
			SsuccessService resourceService = (SsuccessService) 
					applicationContext.getBean("ssuccessService");
			
			Ssuccess insertResult=new Ssuccess();
			
			resourceService.insert(insertResult);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			initialSpring();
			SsuccessService resourceService = (SsuccessService) 
					applicationContext.getBean("ssuccessService");
			
			resourceService.batchDeleteByReconDate("20150629", new HashMap<String, Object>());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

