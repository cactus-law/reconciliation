package com.froad.comon;

public class CommonConstant {
	/**
	 @Fields PUBLIC_KEY : 对应加签公钥
	 */
	public static String PUBLIC_KEY;
	
	/**
	 @Fields OPENAPI_PARTNER_ID : 商户ID
	 */
	public static String OPENAPI_PARTNER_ID;

	public String getPUBLIC_KEY() {
		return PUBLIC_KEY;
	}

	public void setPUBLIC_KEY(String pUBLIC_KEY) {
		PUBLIC_KEY = pUBLIC_KEY;
	}

	public String getOPENAPI_PARTNER_ID() {
		return OPENAPI_PARTNER_ID;
	}

	public void setOPENAPI_PARTNER_ID(String oPENAPI_PARTNER_ID) {
		OPENAPI_PARTNER_ID = oPENAPI_PARTNER_ID;
	}
}
