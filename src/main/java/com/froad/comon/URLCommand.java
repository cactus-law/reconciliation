package com.froad.comon;


/**
 * @ClassName: URLCommand
 * @Description: 内部项目调用地址
 */
public class URLCommand {
    public static String BILL_COMMAND_URL;//账单平台通知接口地址
    public static String OPENAPI_COMMAND_URL;//openApi查询接口地址
    
	public String getBILL_COMMAND_URL() {
		return BILL_COMMAND_URL;
	}
	public void setBILL_COMMAND_URL(String bILL_COMMAND_URL) {
		BILL_COMMAND_URL = bILL_COMMAND_URL;
	}
	public String getOPENAPI_COMMAND_URL() {
		return OPENAPI_COMMAND_URL;
	}
	public void setOPENAPI_COMMAND_URL(String oPENAPI_COMMAND_URL) {
		OPENAPI_COMMAND_URL = oPENAPI_COMMAND_URL;
	}
}
