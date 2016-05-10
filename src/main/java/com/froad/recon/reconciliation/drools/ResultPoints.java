package com.froad.recon.reconciliation.drools;

import java.util.ArrayList;
import java.util.List;

import com.froad.comon.constant.AppConstant;
import com.froad.comon.constant.ReconBusinessConstant;
import com.froad.recon.reconciliation.model.STradeResult;

public class ResultPoints {

	
	/**前端和积分   (前端f 积分points  现金 pay 供应商 partner) **/
	public static void stepfPoints(STradeResult result ) throws Exception{
		
		String tableName="";
		String errorCode="";
		String errorDesc="";
		 
		String isFront = result.getIsFront();
		String frontCode=result.getFrontCode();
		List<String> sources=new ArrayList<String>();
		sources.add(ReconBusinessConstant.PlatformNo.FRONT);
		sources.add(ReconBusinessConstant.PlatformNo.BANK_POINTS);
		ReconRuleHelp.initialSTradeResult(result, sources);
		
		if(STradeResult.IS_YES.equals(isFront)){
			if(ReconRuleHelp.isSourceHasFile(result, sources)){//几方文件上传成功
				/***对账成功**/
				if(ReconRuleHelp.isSuccess(result, sources)){
					tableName=STradeResult.TABLE_NAME_S_SUCCESS;
				}else{
					//各方都已上传
					if(ReconRuleHelp.isSourceAllSuccess(result,sources)){
						if(ReconRuleHelp.isSourceHasDelay(result, sources, AppConstant.CUT_HOUR)){ //是延迟对账
							tableName=STradeResult.TABLE_NAME_S_DELAY;
							result.setPlatformNos(ReconRuleHelp.getSourceSuccessPlatform(result, sources));
						}else{
							if(STradeResult.FRONT_CODE_SUCCESS.equals(frontCode)){
							   if(!STradeResult.IS_YES.equals(result.getIsPay())){
									tableName=STradeResult.TABLE_NAME_S_EXCEPTION;
									errorCode="f_unilateral_fPoints";	//"前端单边";
							   }
							}else{
								if(STradeResult.IS_YES.equals(result.getIsPay())){
									tableName=STradeResult.TABLE_NAME_S_EXCEPTION;
									errorCode="f_fail_process_fPoints";	//errorDesc=前端数据错误								}else{
								}else{
									tableName=STradeResult.TABLE_NAME_S_OTHER;
									errorCode="f_success_fPoints";	//errorDesc="前端无差错"
								}
							}
						}
					}else{
					  tableName=STradeResult.TABLE_NAME_S_OTHER;
					  errorCode="flieNoUpload_fPoints";	//未上传
					}
				}
			}else{//上传失败，入未对账表    银行文件未生成或者未获取到  一方数据入末对账表
				tableName=STradeResult.TABLE_NAME_S_NO_RECON;
				result.setPlatformNos(ReconRuleHelp.getSourceSuccessPlatform(result, sources));
			}
		}else{//差错
			errorCode="unilateral_fPoints";//非前方单边
			tableName=STradeResult.TABLE_NAME_S_EXCEPTION;
		}
		result.setTableName(tableName);
		result.setErrorCode(errorCode);
		result.setErrorDesc(errorDesc);
	}
	
	
	/**前端、积分和现金   (前端f 积分points  现金 pay 供应商 partner) **/
	public static void stepfPointsPay(STradeResult result ) throws Exception{
		
		String tableName="";
		String errorCode="";
		String errorDesc="";
		 
		String isFront = result.getIsFront();
		String frontCode=result.getFrontCode();
		List<String> sources=new ArrayList<String>();
		sources.add(ReconBusinessConstant.PlatformNo.FRONT);
		sources.add(ReconBusinessConstant.PlatformNo.BANK_POINTS);
		sources.add(ReconBusinessConstant.PlatformNo.PAY);
		ReconRuleHelp.initialSTradeResult(result, sources);
		
		if(STradeResult.IS_YES.equals(isFront)){
			if(ReconRuleHelp.isSourceHasFile(result, sources)){//几方文件上传成功
				/***对账成功**/
				if(ReconRuleHelp.isSuccess(result, sources)){
					tableName=STradeResult.TABLE_NAME_S_SUCCESS;
				}else{
					//各方都已上传
					if(ReconRuleHelp.isSourceAllSuccess(result,sources)){
						if(ReconRuleHelp.isSourceHasDelay(result, sources, AppConstant.CUT_HOUR_FPS)){ //是延迟对账
							tableName=STradeResult.TABLE_NAME_S_DELAY;
							result.setPlatformNos(ReconRuleHelp.getSourceSuccessPlatform(result, sources));
						}else{
							if(STradeResult.FRONT_CODE_SUCCESS.equals(frontCode)){
							   if(!STradeResult.IS_YES.equals(result.getIsBankPoints())&& !STradeResult.IS_YES.equals(result.getIsPay())){
									tableName=STradeResult.TABLE_NAME_S_EXCEPTION;
									errorCode="f_unilateral_fPointsPay";	//前端单边
							   }else if (!STradeResult.IS_YES.equals(result.getIsBankPoints())&& STradeResult.IS_YES.equals(result.getIsPay())){
								   tableName=STradeResult.TABLE_NAME_S_EXCEPTION;
								   errorCode="f_fail_process_fPointsPay";	//前端数据错误
							   }else if (STradeResult.IS_YES.equals(result.getIsBankPoints())&& !STradeResult.IS_YES.equals(result.getIsPay())){
								   tableName=STradeResult.TABLE_NAME_S_EXCEPTION;
								   errorCode="f_fail_process_fPointsPay";	//前端数据错误
							   }
							}else{
								if(!STradeResult.IS_YES.equals(result.getIsBankPoints())&& !STradeResult.IS_YES.equals(result.getIsPay())){
									tableName=STradeResult.TABLE_NAME_S_OTHER;
									errorCode="f_success_fPointsPay";	//前端无差错
								}else if (!STradeResult.IS_YES.equals(result.getIsBankPoints())&& STradeResult.IS_YES.equals(result.getIsPay())){
									tableName=STradeResult.TABLE_NAME_S_EXCEPTION;
									errorCode="f_fail_process_fPointsPay";	//前端数据错误
								}else if (STradeResult.IS_YES.equals(result.getIsBankPoints())&& !STradeResult.IS_YES.equals(result.getIsPay())){
									tableName=STradeResult.TABLE_NAME_S_EXCEPTION;
									errorCode="f_fail_process_fPointsPay";	//前端数据错误
								}else{
									tableName=STradeResult.TABLE_NAME_S_EXCEPTION;
									errorCode="f_fail_process_fPointsPay";	//前端数据错误
								}
							}
						}
					}else{
					  tableName=STradeResult.TABLE_NAME_S_OTHER;
					  errorCode="flieNoUpload_fPointsPay";	//未上传
					}
				}
			}else{//上传失败，入未对账表    银行文件未生成或者未获取到  一方数据入末对账表
				tableName=STradeResult.TABLE_NAME_S_NO_RECON;
				result.setPlatformNos(ReconRuleHelp.getSourceSuccessPlatform(result, sources));
			}
		}else{//差错
			errorCode="unilateral_fPointsPay";//非前方单边
			tableName=STradeResult.TABLE_NAME_S_EXCEPTION;
		}
		result.setTableName(tableName);
		result.setErrorCode(errorCode);
		result.setErrorDesc(errorDesc);
	}
	
	

	/**前端、积分和供应商   (前端f 积分points  现金 pay 供应商 partner) **/
	public static void stepfPointsPartner(STradeResult result ) throws Exception{
		
		String tableName="";
		String errorCode="";
		String errorDesc="";
		 
		String isFront = result.getIsFront();
		String frontCode=result.getFrontCode();
		List<String> sources=new ArrayList<String>();
		sources.add(ReconBusinessConstant.PlatformNo.FRONT);
		sources.add(ReconBusinessConstant.PlatformNo.BANK_POINTS);
		sources.add(ReconBusinessConstant.PlatformNo.PARTNER);
		ReconRuleHelp.initialSTradeResult(result, sources);
		
		if(STradeResult.IS_YES.equals(isFront)){
			if(ReconRuleHelp.isSourceHasFile(result, sources)){//几方文件上传成功
				/***对账成功**/
				if(ReconRuleHelp.isSuccess(result, sources)){
					tableName=STradeResult.TABLE_NAME_S_SUCCESS;
				}else{
					//各方都已上传
					if(ReconRuleHelp.isSourceAllSuccess(result,sources)){
						if(ReconRuleHelp.isSourceHasDelay(result, sources, AppConstant.CUT_HOUR_FPS)){ //是延迟对账
							tableName=STradeResult.TABLE_NAME_S_DELAY;
							result.setPlatformNos(ReconRuleHelp.getSourceSuccessPlatform(result, sources));
						}else{
							if(STradeResult.FRONT_CODE_SUCCESS.equals(frontCode)){
							   if(!STradeResult.IS_YES.equals(result.getIsBankPoints())&& !STradeResult.IS_YES.equals(result.getIsPay())){
									tableName=STradeResult.TABLE_NAME_S_EXCEPTION;
									errorCode="f_unilateral_fPointsPartner";	//前端单边
							   }else if (!STradeResult.IS_YES.equals(result.getIsBankPoints())&& STradeResult.IS_YES.equals(result.getIsPartner())){
								   tableName=STradeResult.TABLE_NAME_S_EXCEPTION;
								   errorCode="f_fail_process_fPointsPartner";	//前端数据错误
							   }else if (STradeResult.IS_YES.equals(result.getIsBankPoints())&& !STradeResult.IS_YES.equals(result.getIsPartner())){
								   tableName=STradeResult.TABLE_NAME_S_EXCEPTION;
								   errorCode="f_fail_process_fPointsPartner";	//前端数据错误
							   }
							}else{
								if(!STradeResult.IS_YES.equals(result.getIsBankPoints())&& !STradeResult.IS_YES.equals(result.getIsPartner())){
									tableName=STradeResult.TABLE_NAME_S_OTHER;
									errorCode="f_success_fPointsPartner";	//前端无差错
								}else if (!STradeResult.IS_YES.equals(result.getIsBankPoints())&& STradeResult.IS_YES.equals(result.getIsPartner())){
									tableName=STradeResult.TABLE_NAME_S_EXCEPTION;
									errorCode="f_fail_process_fPointsPartner";	//前端数据错误
								}else if (STradeResult.IS_YES.equals(result.getIsBankPoints())&& !STradeResult.IS_YES.equals(result.getIsPartner())){
									tableName=STradeResult.TABLE_NAME_S_EXCEPTION;
									errorCode="f_fail_process_fPointsPartner";	//前端数据错误;
								}else{
									tableName=STradeResult.TABLE_NAME_S_EXCEPTION;
									errorCode="f_fail_process_fPointsPartner";	//前端数据错误;
								}
							}
						}
					}else{
					  tableName=STradeResult.TABLE_NAME_S_OTHER;
					  errorCode="flieNoUpload_fPointsPartner";	//未上传
					}
				}
			}else{//上传失败，入未对账表    银行文件未生成或者未获取到  一方数据入末对账表
				tableName=STradeResult.TABLE_NAME_S_NO_RECON;
				result.setPlatformNos(ReconRuleHelp.getSourceSuccessPlatform(result, sources));
			}
		}else{//差错
			errorCode="unilateral_fPointsPartner";//非前方单边
			tableName=STradeResult.TABLE_NAME_S_EXCEPTION;
		}
		result.setTableName(tableName);
		result.setErrorCode(errorCode);
		result.setErrorDesc(errorDesc);
	}
	
	
	
	/**前端、积分、现金和供应商   (前端f 积分points  现金 pay 供应商 partner) **/
	public static void stepfPointsPayPartner(STradeResult result ) throws Exception{
		
		String tableName="";
		String errorCode="";
		String errorDesc="";
		 
		String isFront = result.getIsFront();
		String frontCode=result.getFrontCode();
		List<String> sources=new ArrayList<String>();
		sources.add(ReconBusinessConstant.PlatformNo.FRONT);
		sources.add(ReconBusinessConstant.PlatformNo.BANK_POINTS);
		sources.add(ReconBusinessConstant.PlatformNo.PAY);
		ReconRuleHelp.initialSTradeResult(result, sources);
		
		if(STradeResult.IS_YES.equals(isFront)){
			if(ReconRuleHelp.isSourceHasFile(result, sources)){//几方文件上传成功
				/***对账成功**/
				if(ReconRuleHelp.isSuccess(result, sources)){
					tableName=STradeResult.TABLE_NAME_S_SUCCESS;
				}else{
					//各方都已上传
					if(ReconRuleHelp.isSourceAllSuccess(result,sources)){
						if(ReconRuleHelp.isSourceHasDelay(result, sources, AppConstant.CUT_HOUR_FPS)){ //是延迟对账
							tableName=STradeResult.TABLE_NAME_S_DELAY;
							result.setPlatformNos(ReconRuleHelp.getSourceSuccessPlatform(result, sources));
						}else{
							if(STradeResult.FRONT_CODE_SUCCESS.equals(frontCode)){
								if(!STradeResult.IS_YES.equals(result.getIsBankPoints())&& 
										!STradeResult.IS_YES.equals(result.getIsPay())&&
										!STradeResult.IS_YES.equals(result.getIsPartner())){
									
									tableName=STradeResult.TABLE_NAME_S_EXCEPTION;
									errorCode="f_unilateral_fPointsPayPartner";	//前端单边
								}else{
									tableName=STradeResult.TABLE_NAME_S_EXCEPTION;
									errorCode="f_fail_process_fPointsPayPartner";	//前端数据错误
								}
							}else{
								if(!STradeResult.IS_YES.equals(result.getIsBankPoints())&& 
										!STradeResult.IS_YES.equals(result.getIsPay())&&
										!STradeResult.IS_YES.equals(result.getIsPartner())){
									tableName=STradeResult.TABLE_NAME_S_OTHER;
									errorCode="f_success_fPointsPayPartner";	//前端无差错
								}else{
									tableName=STradeResult.TABLE_NAME_S_EXCEPTION;
									errorCode="f_fail_process_fPointsPayPartner";	//前端数据错误;
								}
							}
						}
					}else{
					  tableName=STradeResult.TABLE_NAME_S_OTHER;
					  errorCode="flieNoUpload_fPointsPayPartner";	//未上传
					}
				}
			}else{//上传失败，入未对账表    银行文件未生成或者未获取到  一方数据入末对账表
				tableName=STradeResult.TABLE_NAME_S_NO_RECON;
				result.setPlatformNos(ReconRuleHelp.getSourceSuccessPlatform(result, sources));
			}
		}else{//差错
			errorCode="unilateral_fPointsPayPartner";//非前方单边
			tableName=STradeResult.TABLE_NAME_S_EXCEPTION;
		}
		result.setTableName(tableName);
		result.setErrorCode(errorCode);
		result.setErrorDesc(errorDesc);
	}
}
