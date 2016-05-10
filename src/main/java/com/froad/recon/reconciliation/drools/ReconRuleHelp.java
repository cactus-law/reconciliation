package com.froad.recon.reconciliation.drools;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.froad.comon.constant.ReconBusinessConstant;
import com.froad.comon.util.DateUtil;
import com.froad.recon.reconciliation.model.STradeResult;

public class ReconRuleHelp {
	
	/****
	 * 初始化几方对账
	 * @param sTradeResult
	 * @param sources
	 * @return
	 */
	public static void initialSTradeResult(STradeResult sTradeResult,
			List<String> sources) {
		if(!sources.contains(ReconBusinessConstant.PlatformNo.FRONT)){
			sTradeResult.setIsFront(STradeResult.IS_NO);
		}
		if (!sources.contains(ReconBusinessConstant.PlatformNo.BANK_POINTS)) {
			sTradeResult.setIsBankPoints(STradeResult.IS_NO);
		}
		if (!sources.contains(ReconBusinessConstant.PlatformNo.PAY)) {
			sTradeResult.setIsPay(STradeResult.IS_NO);
		}
		if (!sources.contains(ReconBusinessConstant.PlatformNo.PARTNER)) {
			sTradeResult.setIsPartner(STradeResult.IS_NO);
		}
		if (!sources.contains(ReconBusinessConstant.PlatformNo.POINTS)) {
			sTradeResult.setIsPoints(STradeResult.IS_NO);
		}
	}
	
	/***
	 * 通过几方对账，如果几方对账文件是否全部入库成功
	 * @param sTradeResult 结果集记录
	 * @param sources  几方对账的来源方 编号，及平台编号
	 * @return
	 */
	public static Boolean isSourceAllSuccess(STradeResult sTradeResult,
			List<String> sources) {
		List<String> platformDetails = sTradeResult.getPlatfromYesfiles();
		/*** 判断是否是未对账 */
		if (sources.contains(ReconBusinessConstant.PlatformNo.FRONT)) {
			String key = ReconBusinessConstant.PlatformNo.FRONT + "_"
					+ sTradeResult.getFrontPartnerNo();
			if (!platformDetails.contains(key)) {
				return false;
			}
		}
		if (sources.contains(ReconBusinessConstant.PlatformNo.BANK_POINTS)) {
			String key = ReconBusinessConstant.PlatformNo.BANK_POINTS + "_"
					+ sTradeResult.getPointBankGroup();
			if (!platformDetails.contains(key)) {
				return false;
			}
		}
		if (sources.contains(ReconBusinessConstant.PlatformNo.PAY)) {
			String key = ReconBusinessConstant.PlatformNo.PAY + "_"
					+ sTradeResult.getBackGroup();
			if (!platformDetails.contains(key)) {
				return false;
			}
		}
		if (sources.contains(ReconBusinessConstant.PlatformNo.PARTNER)) {
			String key = ReconBusinessConstant.PlatformNo.PARTNER + "_"
					+ sTradeResult.getSupplierNo();
			if (!platformDetails.contains(key)) {
				return false;
			}
		}
		if (sources.contains(ReconBusinessConstant.PlatformNo.POINTS)) {
			String key = ReconBusinessConstant.PlatformNo.POINTS + "_"
					+ sTradeResult.getPointOrg();
			if (!platformDetails.contains(key)) {
				return false;
			}
		}
		return true;
	}
	
	/***
	 * 通过几方对账，获取该几方的文件是否上传成功， 如果上传成功为true，否则为false
	 * @param sTradeResult 结果集记录
	 * @param sources  几方对账的来源方 编号，及平台编号
	 * @return
	 */
	public static Boolean isSourceHasFile(STradeResult sTradeResult,
			List<String> sources) {
		String isFront = sTradeResult.getIsFront();
		String isBankPoints = sTradeResult.getIsBankPoints();
		String isPartner = sTradeResult.getIsPartner();
		String isPoints = sTradeResult.getIsPoints();
		String isPay = sTradeResult.getIsPay();

		List<String> platformDetails = sTradeResult.getPlatformDetails();
		/*** 判断是否是未上传 */
		if (sources.contains(ReconBusinessConstant.PlatformNo.FRONT)) {
			if (!STradeResult.IS_YES.equals(isFront)) {
				String key = ReconBusinessConstant.PlatformNo.FRONT + "_"
						+ sTradeResult.getFrontPartnerNo();
				if (platformDetails.contains(key)) {
					return false;
				}
			}
		}
		if (sources.contains(ReconBusinessConstant.PlatformNo.BANK_POINTS)) {
			if (!STradeResult.IS_YES.equals(isBankPoints)) {
				String key = ReconBusinessConstant.PlatformNo.BANK_POINTS + "_"
						+ sTradeResult.getPointBankGroup();
				if (platformDetails.contains(key)) {
					return false;
				}
			}
		}
		if (sources.contains(ReconBusinessConstant.PlatformNo.PAY)) {
			if (!STradeResult.IS_YES.equals(isPay)) {
				if("700".equals(sTradeResult.getBackGroup())){
					System.out.println("700");
				}
				String key = ReconBusinessConstant.PlatformNo.PAY + "_"
						+ sTradeResult.getBackGroup();
				if (platformDetails.contains(key)) {
					return false;
				}
			}
		}
		if (sources.contains(ReconBusinessConstant.PlatformNo.PARTNER)) {
			if (!STradeResult.IS_YES.equals(isPartner)) {
				String key = ReconBusinessConstant.PlatformNo.PARTNER + "_"
						+ sTradeResult.getSupplierNo();
				if (platformDetails.contains(key)) {
					return false;
				}
			}
		}
		if (sources.contains(ReconBusinessConstant.PlatformNo.POINTS)) {
			if (!STradeResult.IS_YES.equals(isPoints)) {
				String key = ReconBusinessConstant.PlatformNo.POINTS + "_"
						+ sTradeResult.getPointOrg();
				if (platformDetails.contains(key)) {
					return false;
				}
			}

		}
		return true;
	}
	
	/***
	 * 通过几方对账，判断该记录是否要放到延迟对账 中
	 * @param sTradeResult 结果集记录
	 * @param sources  几方对账的来源方 编号，及平台编号
	 * @return
	 * @throws Exception 
	 */
	public static Boolean isSourceHasDelay(STradeResult sTradeResult,
			List<String> sources,String hour) throws Exception {
		String frontCode = sTradeResult.getFrontCode();
		String isFront = sTradeResult.getIsFront();
		String isBankPoints = sTradeResult.getIsBankPoints();
		String isPartner = sTradeResult.getIsPartner();
		String isPoints = sTradeResult.getIsPoints();
		String isPay = sTradeResult.getIsPay();
		
		String reconDate=  sTradeResult.getReconDate();  //对账日
		Date orderTime=sTradeResult.getOrderTime();
		String format=DateUtil.standardFormat+"HH";
	    Integer resultInt=DateUtil.compare(DateUtil.getStringFromDate(orderTime,format),reconDate+hour,format);

	    if(resultInt>0){
	    	System.out.println("resultInt");
	    }
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
	
	/***
	 * 通过几方对账，获取几方中标志位 为 “1” 的平台编号集合
	 * @param sTradeResult 结果集记录
	 * @param sources  几方对账的来源方 编号，及平台编号
	 * @return
	 */
	public static List<String> getSourceSuccessPlatform(STradeResult sTradeResult,
			List<String> sources) {
		List<String> result=new ArrayList<String>();
		
		String isFront = sTradeResult.getIsFront();
		String isBankPoints = sTradeResult.getIsBankPoints();
		String isPartner = sTradeResult.getIsPartner();
		String isPoints = sTradeResult.getIsPoints();
		String isPay = sTradeResult.getIsPay();

		/***判断来源方，标志位 1，放到未上传结果集表中  */
		if (sources.contains(ReconBusinessConstant.PlatformNo.FRONT)) {
			if (STradeResult.IS_YES.equals(isFront)) {
				result.add(ReconBusinessConstant.PlatformNo.FRONT);
			}
		}
		if (sources.contains(ReconBusinessConstant.PlatformNo.BANK_POINTS)) {
			if (STradeResult.IS_YES.equals(isBankPoints)) {
				result.add(ReconBusinessConstant.PlatformNo.BANK_POINTS);
			}
		}
		if (sources.contains(ReconBusinessConstant.PlatformNo.PAY)) {
			if (STradeResult.IS_YES.equals(isPay)) {
				result.add(ReconBusinessConstant.PlatformNo.PAY);
			}
		}
		if (sources.contains(ReconBusinessConstant.PlatformNo.PARTNER)) {
			if (STradeResult.IS_YES.equals(isPartner)) {
				result.add(ReconBusinessConstant.PlatformNo.PARTNER);
			}
		}
		if (sources.contains(ReconBusinessConstant.PlatformNo.POINTS)) {
			if (STradeResult.IS_YES.equals(isPoints)) {
				result.add(ReconBusinessConstant.PlatformNo.POINTS);
			}
		}
		return result;
	}
	
	/***
	 * 通过几方对账，获取几方中标志位 为 “1” 的平台编号集合
	 * @param sTradeResult 结果集记录
	 * @param sources  几方对账的来源方 编号，及平台编号
	 * @return
	 */
	public static Boolean isSuccess(STradeResult sTradeResult,
			List<String> sources) {
		
		String isFront = sTradeResult.getIsFront();
		String isBankPoints = sTradeResult.getIsBankPoints();
		String isPartner = sTradeResult.getIsPartner();
		String isPoints = sTradeResult.getIsPoints();
		String isPay = sTradeResult.getIsPay();
		String frontCode = sTradeResult.getFrontCode();

		/***判断来源方，标志位 1，放到未上传结果集表中  */
		if(!STradeResult.FRONT_CODE_SUCCESS.equals(frontCode)){
			return false;
		}
		if (sources.contains(ReconBusinessConstant.PlatformNo.FRONT)) {
			if (!STradeResult.IS_YES.equals(isFront)) {
				return false;
			}
		}
		if (sources.contains(ReconBusinessConstant.PlatformNo.BANK_POINTS)) {
			if (!STradeResult.IS_YES.equals(isBankPoints)) {
				return false;
			}
		}
		if (sources.contains(ReconBusinessConstant.PlatformNo.PAY)) {
			if (!STradeResult.IS_YES.equals(isPay)) {
				return false;
			}
		}
		if (sources.contains(ReconBusinessConstant.PlatformNo.PARTNER)) {
			if (!STradeResult.IS_YES.equals(isPartner)) {
				return false;
			}
		}
		if (sources.contains(ReconBusinessConstant.PlatformNo.POINTS)) {
			if (!STradeResult.IS_YES.equals(isPoints)) {
				return false;
			}
		}
		return true;
	}


}
