package com.froad.recon.reconciliation.drools;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.froad.comon.constant.ReconBusinessConstant;
import com.froad.comon.util.DateUtil;
import com.froad.recon.reconciliation.model.STradeResult;

public class ReconRefundRuleHelp {
	
	/***
	 * 通过几方对账，判断该记录是否要放到延迟对账 中
	 * @param sTradeResult 结果集记录
	 * @param sources  几方对账的来源方 编号，及平台编号
	 * @return
	 * @throws Exception 
	 */
	public static Boolean isSourceHasDelay(STradeResult sTradeResult,
			List<String> sources,Integer days) throws Exception {
		String frontCode = sTradeResult.getFrontCode();
		String isFront = sTradeResult.getIsFront();
		String isBankPoints = sTradeResult.getIsBankPoints();
		String isPartner = sTradeResult.getIsPartner();
		String isPoints = sTradeResult.getIsPoints();
		String isPay = sTradeResult.getIsPay();
		
		String reconDate=  sTradeResult.getReconDate();  //对账日
		String importDate=  sTradeResult.getImportDate();  //导入日期
		if(StringUtils.isEmpty(importDate)|| StringUtils.isEmpty(importDate)){
			return false;
		}
		importDate=DateUtil.getStringFromDate(DateUtil.addDaysToDate(importDate, days),DateUtil.standardFormat) ;
	    Integer resultInt=DateUtil.compare(reconDate,importDate,DateUtil.standardFormat);
	   
		/*** 判断是否是未对账 */
		if (sources.contains(ReconBusinessConstant.PlatformNo.FRONT)) {
			if (!STradeResult.IS_YES.equals(isFront)) {
				if(resultInt>0){
					return true;
				}
			}
		}
		if (sources.contains(ReconBusinessConstant.PlatformNo.BANK_POINTS)) {
			if (!STradeResult.IS_YES.equals(isBankPoints)) {
				if(resultInt>0){
					return true;
				}
			}
		}
		if (sources.contains(ReconBusinessConstant.PlatformNo.PAY)) {
			if (!STradeResult.IS_YES.equals(isPay)) {
				if(resultInt>0){
					return true;
				}
			}
		}
		if (sources.contains(ReconBusinessConstant.PlatformNo.PARTNER)) {
			if (!STradeResult.IS_YES.equals(isPartner)) {
				if(resultInt>0){
					return true;
				}
			}
		}
		if (sources.contains(ReconBusinessConstant.PlatformNo.POINTS)) {
			if (!STradeResult.IS_YES.equals(isPoints)) {
				if(resultInt>0){
					return true;
				}
			}

		}
		return false;
	}

	
	public static void main(String[] args) throws Exception {
		String reconDate=  "20180816";  //对账日
		String importDate=  "20180815";  //导入日期
		importDate=DateUtil.getStringFromDate(DateUtil.addDaysToDate(importDate, 1),DateUtil.standardFormat) ;
		System.out.println(importDate);
	    Integer resultInt=DateUtil.compare(importDate,reconDate,DateUtil.standardFormat);
	    
	    System.out.println(resultInt);
	}

}
