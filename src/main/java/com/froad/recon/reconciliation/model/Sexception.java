package com.froad.recon.reconciliation.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 差错表
 * @author zhangjwei
 * @created 2015-06-16 11:25:54
 * @version 1.0
 */

public class Sexception implements java.io.Serializable{
    private static final long serialVersionUID = 1L;
    public static final String PROCESS_STATUS_NO = "0";
    public static final String PROCESS_STATUS_SUCCESS = "1";
    public static final String PROCESS_STATUS_FAIL = "2";
    public static final String PROCESS_STATUS_ING = "3";

    /**订单编号PK**/
	private String orderNo;
	/**订单时间**/
	private Date orderTime;
	/**渠道类别	0 积分 1 现金**/
	private String chanelType;
	/**交易类型	如退分、支付**/
	private String tradeType;
	/**交易类型名称**/
	private String tradeTypeName;
	/**错误代码**/
	private String errorCode;
	/**错误描述**/
	private String errorDesc;
	/**交易额**/
	private BigDecimal tradeMoney;
	/**创建时间**/
	private Date createTime;
	/**对账日**/
	private String reconDate;
	/**处理状态， 0未处理，1处理成功,2处理失败，3处理中**/
	private String processStatus;
	/**处理信息**/
	private String processInfo;
	/**前端应答码**/
	private String frontCode;
	/**前端平台明细编号**/
	private String frontNo;
	/**前端平台明细名称**/
	private String frontName;
	/**供应商平台明细编号**/
	private String supplierNo;
	/**供应商平台明细名称**/
	private String supplierName;
	/**积分银行平台明细编号**/
	private String pointBankNo;
	/**积分银行平台明细名称**/
	private String pointBankName;
	/**现金银行平台明细编号**/
	private String bankNo;
	/**现金银行平台明细名称**/
	private String bankName;
	/**积分机构平台明细编号**/
	private String pointOrgNo;
	/**积分机构平台明细名称**/
	private String pointOrgName;
	
	/**平台编号1	1=是0=否**/
	private String isPoints;
	/**平台编号2	1=是0=否**/
	private String isBankPoints;
	/**平台编号3	1=是0=否**/
	private String isPay;
	/**平台编号4	1=是0=否**/
	private String isPartner;
	/**平台编号5	1=是0=否**/
	private String isFront;
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	public String getOrderNo() {
		return this.orderNo;
	}
	
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	
	public Date getOrderTime() {
		return this.orderTime;
	}
	
	public void setChanelType(String chanelType) {
		this.chanelType = chanelType;
	}
	
	public String getChanelType() {
		return this.chanelType;
	}
	
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	
	public String getTradeType() {
		return this.tradeType;
	}
	
	public void setTradeTypeName(String tradeTypeName) {
		this.tradeTypeName = tradeTypeName;
	}
	
	public String getTradeTypeName() {
		return this.tradeTypeName;
	}
	
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	public String getErrorCode() {
		return this.errorCode;
	}
	
	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}
	
	public String getErrorDesc() {
		return this.errorDesc;
	}
	
	public void setTradeMoney(BigDecimal tradeMoney) {
		this.tradeMoney = tradeMoney;
	}
	
	public BigDecimal getTradeMoney() {
		return this.tradeMoney;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getCreateTime() {
		return this.createTime;
	}
	
	public void setReconDate(String reconDate) {
		this.reconDate = reconDate;
	}
	
	public String getReconDate() {
		return this.reconDate;
	}
	
	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}
	
	public String getProcessStatus() {
		return this.processStatus;
	}
	
	public void setProcessInfo(String processInfo) {
		this.processInfo = processInfo;
	}
	
	public String getProcessInfo() {
		return this.processInfo;
	}
	
	public void setFrontCode(String frontCode) {
		this.frontCode = frontCode;
	}
	
	public String getFrontCode() {
		return this.frontCode;
	}
	
	public void setFrontNo(String frontNo) {
		this.frontNo = frontNo;
	}
	
	public String getFrontNo() {
		return this.frontNo;
	}
	
	public void setFrontName(String frontName) {
		this.frontName = frontName;
	}
	
	public String getFrontName() {
		return this.frontName;
	}
	
	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
	}
	
	public String getSupplierNo() {
		return this.supplierNo;
	}
	
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	
	public String getSupplierName() {
		return this.supplierName;
	}
	
	public void setPointBankNo(String pointBankNo) {
		this.pointBankNo = pointBankNo;
	}
	
	public String getPointBankNo() {
		return this.pointBankNo;
	}
	
	public void setPointBankName(String pointBankName) {
		this.pointBankName = pointBankName;
	}
	
	public String getPointBankName() {
		return this.pointBankName;
	}
	
	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public void setPointOrgNo(String pointOrgNo) {
		this.pointOrgNo = pointOrgNo;
	}
	
	public String getPointOrgNo() {
		return this.pointOrgNo;
	}
	
	public void setPointOrgName(String pointOrgName) {
		this.pointOrgName = pointOrgName;
	}
	
	public String getPointOrgName() {
		return this.pointOrgName;
	}

	public String getIsPoints() {
		return isPoints;
	}

	public void setIsPoints(String isPoints) {
		this.isPoints = isPoints;
	}

	public String getIsBankPoints() {
		return isBankPoints;
	}

	public void setIsBankPoints(String isBankPoints) {
		this.isBankPoints = isBankPoints;
	}

	public String getIsPay() {
		return isPay;
	}

	public void setIsPay(String isPay) {
		this.isPay = isPay;
	}

	public String getIsPartner() {
		return isPartner;
	}

	public void setIsPartner(String isPartner) {
		this.isPartner = isPartner;
	}

	public String getIsFront() {
		return isFront;
	}

	public void setIsFront(String isFront) {
		this.isFront = isFront;
	}

	@Override
	public String toString() {
		return "Sexception [orderNo=" + orderNo + ", orderTime=" + orderTime
				+ ", chanelType=" + chanelType + ", tradeType=" + tradeType
				+ ", tradeTypeName=" + tradeTypeName + ", errorCode="
				+ errorCode + ", errorDesc=" + errorDesc + ", tradeMoney="
				+ tradeMoney + ", createTime=" + createTime + ", reconDate="
				+ reconDate + ", processStatus=" + processStatus
				+ ", processInfo=" + processInfo + ", frontCode=" + frontCode
				+ ", frontNo=" + frontNo + ", frontName=" + frontName
				+ ", supplierNo=" + supplierNo + ", supplierName="
				+ supplierName + ", pointBankNo=" + pointBankNo
				+ ", pointBankName=" + pointBankName + ", bankNo=" + bankNo
				+ ", bankName=" + bankName + ", pointOrgNo=" + pointOrgNo
				+ ", pointOrgName=" + pointOrgName + ", isPoints=" + isPoints
				+ ", isBankPoints=" + isBankPoints + ", isPay=" + isPay
				+ ", isPartner=" + isPartner + ", isFront=" + isFront + "]";
	}
	
}

