package com.froad.recon.reconciliation.test;

import org.junit.Test;

import com.froad.beans.Rckflowdetail;
import com.froad.beans.RckflowdetailId;
import com.froad.comon.constant.BusinessConstant;
import com.froad.flow.FlowInterface;
import com.froad.recon.base.BasicTest;
import com.froad.recon.reconciliation.flow.LoadDataRedisFlow;

public class LoadDataRedisFlowTest extends BasicTest  {
	
	@Test
	public void add(){
		try {
			LoadDataRedisFlow flow = (LoadDataRedisFlow) 
					applicationContext.getBean("loadDataRedisFlow");
			
			
			Rckflowdetail rckflowdetail=new Rckflowdetail();
			RckflowdetailId id=new RckflowdetailId();
			id.setCleardate("20150505");
			id.setRckorder(2001);
			id.setRcktype("RECON");
			rckflowdetail.setId(id);
			rckflowdetail.setMsg("bank_points");
			rckflowdetail.setReconType(BusinessConstant.IMP_TYPE.ALL);
			
			flow.execute(rckflowdetail);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void flow2(){
		try {
			FlowInterface flow = (FlowInterface) 
					applicationContext.getBean("tradeResultFlow");
			
			
			Rckflowdetail rckflowdetail=new Rckflowdetail();
			RckflowdetailId id=new RckflowdetailId();
			id.setCleardate("20150505");
			id.setRckorder(3001);
			id.setRcktype("RECON");
			rckflowdetail.setId(id);
			rckflowdetail.setMsg("bank_points");
			rckflowdetail.setReconType(BusinessConstant.IMP_TYPE.ALL);
			
			flow.execute(rckflowdetail);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void flow3(){
		try {
			FlowInterface flow = (FlowInterface) 
					applicationContext.getBean("tradeResultSeparateFlow");
			
			
			Rckflowdetail rckflowdetail=new Rckflowdetail();
			RckflowdetailId id=new RckflowdetailId();
			id.setCleardate("20150505");
			id.setRckorder(4001);
			id.setRcktype("RECON");
			rckflowdetail.setId(id);
			rckflowdetail.setMsg("bank_points");
			rckflowdetail.setReconType(BusinessConstant.IMP_TYPE.ALL);
			
			flow.execute(rckflowdetail);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void flow4(){
		try {
			FlowInterface flow = (FlowInterface) 
					applicationContext.getBean("dataAfterFlow");
			
			Rckflowdetail rckflowdetail=new Rckflowdetail();
			RckflowdetailId id=new RckflowdetailId();
			id.setCleardate("20150505");
			id.setRckorder(5001);
			id.setRcktype("RECON");
			rckflowdetail.setId(id);
			rckflowdetail.setMsg("bank_points");
			rckflowdetail.setReconType(BusinessConstant.IMP_TYPE.ALL);
			
			flow.execute(rckflowdetail);
			
			Thread.sleep(60000);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			initialSpring();
			LoadDataRedisFlowTest test=new LoadDataRedisFlowTest();
			test.flow4();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}