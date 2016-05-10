package com.froad.comon.constant;


public class ParamCommand {
	public static String SECRETKEY_FROADBILL_PRIVATE ;		//方付通私钥
	public static String refund_bpoints_to_fpoints_orgno;//#银行积分退分转联盟积分的机构号 多个用逗号分隔
	public static String refund_unbpoints_to_fpoints_orgno;//#银行间联积分退分转联盟积分的机构号 多个用逗号分隔
	public static String cutDays;

	public static String HOST_ID = "";//负债均衡机器标识，每台机器配置必须不同
	
	public static String rule_dir;//规则文件目录
	public static String data_dir;//数据文件目录
	public static String bankpoints_datafile_url;//银行对账文件
	public static String bankgroup_anhui;//银行组号

	/**
	 * @return the cutDays
	 */
	public String getCutDays() {
		return cutDays;
	}

	/**
	 * @param cutDays the cutDays to set
	 */
	public void setCutDays(String cutDays) {
		this.cutDays = cutDays;
	}

	public String getRefund_bpoints_to_fpoints_orgno() {
		return refund_bpoints_to_fpoints_orgno;
	}

	public void setRefund_bpoints_to_fpoints_orgno(
			String refund_bpoints_to_fpoints_orgno) {
		this.refund_bpoints_to_fpoints_orgno = refund_bpoints_to_fpoints_orgno;
	}

	
	public String getRefund_unbpoints_to_fpoints_orgno() {
		return refund_unbpoints_to_fpoints_orgno;
	}

	public void setRefund_unbpoints_to_fpoints_orgno(String refund_unbpoints_to_fpoints_orgno) {
		this.refund_unbpoints_to_fpoints_orgno = refund_unbpoints_to_fpoints_orgno;
	}

	public String getSECRETKEY_FROADBILL_PRIVATE() {
		return SECRETKEY_FROADBILL_PRIVATE;
	}

	public void setSECRETKEY_FROADBILL_PRIVATE(String sECRETKEY_FROADBILL_PRIVATE) {
		SECRETKEY_FROADBILL_PRIVATE = sECRETKEY_FROADBILL_PRIVATE;
	}

	public void setHostId(String hostId) {
		if(hostId==null) {
			hostId = "";
		}
		ParamCommand.HOST_ID = hostId;
	}

	public  String getRule_dir() {
		return rule_dir;
	}

	public  void setRule_dir(String rule_dir) {
		ParamCommand.rule_dir = rule_dir;
	}

	public  String getData_dir() {
		return data_dir;
	}

	public  void setData_dir(String data_dir) {
		ParamCommand.data_dir = data_dir;
	}

	public  String getBankpoints_datafile_url() {
		return bankpoints_datafile_url;
	}

	public  void setBankpoints_datafile_url(String bankpoints_datafile_url) {
		ParamCommand.bankpoints_datafile_url = bankpoints_datafile_url;
	}

	public  String getBankgroup_anhui() {
		return bankgroup_anhui;
	}

	public  void setBankgroup_anhui(String bankgroup_anhui) {
		ParamCommand.bankgroup_anhui = bankgroup_anhui;
	}
	
}
