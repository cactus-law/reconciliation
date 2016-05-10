package com.froad.recon.reconciliation.drools;

import java.util.ArrayList;
import java.util.List;

import com.froad.comon.constant.AppConstant;
import com.froad.comon.constant.ReconBusinessConstant;
import com.froad.recon.reconciliation.model.STradeResult;

public class Rule {

	
	public  static void aaa(STradeResult result) throws Exception{
		String tableName="";
		String isFront = result.getIsFront();
		
		String frontCode=result.getFrontCode();
		List<String> sources=new ArrayList<String>();
		sources.add(ReconBusinessConstant.PlatformNo.FRONT);
		sources.add(ReconBusinessConstant.PlatformNo.BANK_POINTS);
		sources.add(ReconBusinessConstant.PlatformNo.PARTNER);
		
	
		if(STradeResult.IS_YES.equals(isFront)){
			if(ReconRuleHelp.isSourceHasFile(result, sources)){//几方文件上传成功
				/***对账成功**/
			    if(ReconRuleHelp.isSuccess(result, sources)){
			    	tableName=STradeResult.TABLE_NAME_S_SUCCESS;
			    }else{
			    	if(ReconRuleHelp.isSourceHasDelay(result, sources, AppConstant.CUT_HOUR)){ //是延迟对账
			    		tableName=STradeResult.TABLE_NAME_S_DELAY;
			    		result.setPlatformNos(ReconRuleHelp.getSourceSuccessPlatform(result, sources));
			    	}else{
			    		tableName=STradeResult.TABLE_NAME_S_EXCEPTION;
			    	}
			    }
				
			}else{//上传失败，入未对账表
				tableName=STradeResult.TABLE_NAME_S_NO_RECON;
				result.setPlatformNos( ReconRuleHelp.getSourceSuccessPlatform(result, sources));
			}
			result.setTableName(tableName);
		}
		
		
	}
	//前端和银行
	public static void forntPay(STradeResult result) throws Exception{
		String tableName="";
		String errorCode="";
		String errorDesc="";
		 
		String isFront = result.getIsFront();
		String frontCode=result.getFrontCode();
		List<String> sources=new ArrayList<String>();
		sources.add(ReconBusinessConstant.PlatformNo.FRONT);
		sources.add(ReconBusinessConstant.PlatformNo.PAY);
		ReconRuleHelp.initialSTradeResult(result, sources);
		
		/**
		 * 1  如果前端来源方标示为0、银行来源标识符为1：显示银行单边 
		 * 2 判断该订单是否是因为没有上传对账文件，如果是因为没有上传文件导致的， 会进入未对账
		 * 3 前端代码是0000，并且前端、银行来源方标示为1,对账成功
		 * 4 
		 * **/
		
		if(STradeResult.IS_YES.equals(isFront)){
			if(ReconRuleHelp.isSourceHasFile(result, sources)){//几方文件上传成功
				/***对账成功**/
				if(ReconRuleHelp.isSuccess(result, sources)){
					tableName=STradeResult.TABLE_NAME_S_SUCCESS;
				}else{
					
					if(ReconRuleHelp.isSourceAllSuccess(result,sources)){
						/*if(ReconRuleHelp.isSourceHasDelay(result, sources, AppConstant.CUT_HOUR)){ //是延迟对账
							tableName=STradeResult.TABLE_NAME_S_DELAY;
							result.setPlatformNos(ReconRuleHelp.getSourceSuccessPlatform(result, sources));
						}*/
						if(!STradeResult.FRONT_CODE_SUCCESS.equals(frontCode)){
							if(STradeResult.IS_YES.equals(result.getIsPay())){
								errorCode="f_fail_process";
								//errorDesc="前端数据错误,修改前端数据";
							}else{
								errorCode="f_fail";
								//errorDesc="前端数据错误";
							}
						}else{//代码成功
							errorCode="f_unilateral";
							//errorDesc="前端单边";
						}
						tableName=STradeResult.TABLE_NAME_S_EXCEPTION;
					}
				}
			}else{//上传失败，入未对账表
				tableName=STradeResult.TABLE_NAME_S_NO_RECON;
				result.setPlatformNos(ReconRuleHelp.getSourceSuccessPlatform(result, sources));
			}
		}else{//差错
			errorCode="p_unilateral";
			//errorDesc="银行现金单边";
			tableName=STradeResult.TABLE_NAME_S_EXCEPTION;
		}
		result.setTableName(tableName);
		result.setErrorCode(errorCode);
		result.setErrorDesc(errorDesc);
	}
	
	//前端和银行 和 供应商
	public static void forntPayPartner(STradeResult result) throws Exception{
		String tableName="";
		String errorCode="";
		String errorDesc="";
		 
		String isFront = result.getIsFront();
		String frontCode=result.getFrontCode();
		List<String> sources=new ArrayList<String>();
		sources.add(ReconBusinessConstant.PlatformNo.FRONT);
		sources.add(ReconBusinessConstant.PlatformNo.PAY);
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
							   if(!STradeResult.IS_YES.equals(result.getIsPay())&& !STradeResult.IS_YES.equals(result.getIsPartner())){
									tableName=STradeResult.TABLE_NAME_S_EXCEPTION;
									errorCode="f_unilateral";	//"前端单边";
							   }else if (!STradeResult.IS_YES.equals(result.getIsPay())&& STradeResult.IS_YES.equals(result.getIsPartner())){
								   tableName=STradeResult.TABLE_NAME_S_EXCEPTION;
								   errorCode="f_fail_process";	//"该数据有误";
							   }else if (STradeResult.IS_YES.equals(result.getIsPay())&& !STradeResult.IS_YES.equals(result.getIsPartner())){
								   tableName=STradeResult.TABLE_NAME_S_EXCEPTION;
								   errorCode="f_fail_process";	//"该数据有误";
							   }
							}else{
								if(!STradeResult.IS_YES.equals(result.getIsPay())&& !STradeResult.IS_YES.equals(result.getIsPartner())){
									tableName=STradeResult.TABLE_NAME_S_OTHER;
									errorCode="f_fail";	//errorDesc="前端数据错误"，银行没有交易或银行交易未成功
								}else if (!STradeResult.IS_YES.equals(result.getIsPay())&& STradeResult.IS_YES.equals(result.getIsPartner())){
									tableName=STradeResult.TABLE_NAME_S_EXCEPTION;
									errorCode="f_fail_process";	//"该数据有误";
								}else if (STradeResult.IS_YES.equals(result.getIsPay())&& !STradeResult.IS_YES.equals(result.getIsPartner())){
									tableName=STradeResult.TABLE_NAME_S_EXCEPTION;
									errorCode="f_fail_process";	//"该数据有误";
								}else{
									tableName=STradeResult.TABLE_NAME_S_EXCEPTION;
									errorCode="f_fail_process";	//"该数据有误";
								}
							}
						}
					}
				}
			}else{//上传失败，入未对账表    银行文件未生成或者未获取到  一方数据入末对账表
				tableName=STradeResult.TABLE_NAME_S_NO_RECON;
				result.setPlatformNos(ReconRuleHelp.getSourceSuccessPlatform(result, sources));
			}
		}else{//差错
			errorCode="f_not";
			//errorDesc="前端没有";
			tableName=STradeResult.TABLE_NAME_S_EXCEPTION;
		}
		result.setTableName(tableName);
		result.setErrorCode(errorCode);
		result.setErrorDesc(errorDesc);
	}

	//前端和银行 退款
	public static void refund(STradeResult result) throws Exception{
		String tableName="";
		String errorCode="";
		String errorDesc="";
		 
		String frontCode=result.getFrontCode();
		List<String> sources=new ArrayList<String>();
		sources.add(ReconBusinessConstant.PlatformNo.FRONT);
		sources.add(ReconBusinessConstant.PlatformNo.PAY);
		ReconRuleHelp.initialSTradeResult(result, sources);
		
		/***对账成功**/
		if(ReconRuleHelp.isSuccess(result, sources)){
			tableName=STradeResult.TABLE_NAME_S_SUCCESS;
		}else{
			if( !STradeResult.FRONT_CODE_SUCCESS.equals(frontCode)&& !STradeResult.IS_YES.equals(result.getIsPay())){
				tableName=STradeResult.TABLE_NAME_S_EXCEPTION;
				errorCode="f_fail_process";	//errorDesc="前端数据错误,修改前端数据"; 如：前端状态错误，超时等
			}else{
				if(ReconRefundRuleHelp.isSourceHasDelay(result, sources, AppConstant.DELAY_DAYS)){
					tableName=STradeResult.TABLE_NAME_S_EXCEPTION;
					errorCode="f_delay_fail";	//延迟指定的天数，如果还没有匹配上就差错 
				} 
			}
		}
		result.setTableName(tableName);
		result.setErrorCode(errorCode);
		result.setErrorDesc(errorDesc);
	}
	
	public static void main(String[] args) {
		 
		System.out.println("线程1 开始");
		AA a= new AA("1234");
		AA b= new AA("123");
		
		Thread  thread1=new Thread(a);
		thread1.start();
		System.out.println("线程1 end");
		System.out.println("线程2 start");
		thread1=new Thread(b);
		thread1.start();
		System.out.println("线程2 end");
	}
}


class AA implements Runnable {
	 private byte[] locka = new byte[0]; 
	
	private String lock;
	public AA(){
	}
	public AA(String lock){
		this.lock=lock;
	}
	
	public void run() {
		abc(lock);
	}
	public  void abc(String abc){
		String a=new String(abc);
		synchronized (locka) {
		    System.out.println(Thread.currentThread()+"start:"+abc);
			try {
				Thread.sleep(5000l);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread()+"end 执行了,你需要等一会 5s ");
		}
	}
}
