package com.froad.recon.drools;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.froad.recon.reconciliation.drools.DroolsRuleEngine;
import com.froad.recon.reconciliation.drools.Rule;
import com.froad.recon.reconciliation.model.STradeResult;

public class DroolsTest {

	@Test
	public void testDrools() {
		try {

			DroolsRuleEngine rule=new DroolsRuleEngine();
			rule.setDrlCode("session-result");
			rule.initial();
			
			List<String> platformDetails=new ArrayList<String>();
			
			for (int i = 0; i < 1; i++) {
				STradeResult result = new STradeResult();
				result.setFrontCode("0000");
				result.setTradeType("2020");
				result.setChanelType("1");
				result.setIsFront("1");
				result.setIsPay("0");
				result.setIsPartner("0");
				result.setOrderTime(new Date());
				result.setReconDate("20150622");
//				result.setVirtualTradeType("1");
				
				result.setPlatformDetails(platformDetails);
				result.setPlatfromYesfiles(platformDetails);
				
			 
				
				rule.executeRuleEngine(result);
				System.out.println(result.getTableName()+ "   "+result.getPlatformNos()+"   "+
						result.getErrorCode()+ "   "+ result.getErrorDesc());
			}
			
			
		
			System.err.println("----------------执行规则结束--------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
