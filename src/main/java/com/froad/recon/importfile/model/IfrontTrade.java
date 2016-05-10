package com.froad.recon.importfile.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 前端对账数据表
 * @author zhangjwei
 * @created 2015-06-16 11:07:21
 * @version 1.0
 */

public class IfrontTrade implements java.io.Serializable{
    private static final long serialVersionUID = 1L;

	/**主键**/
	private String id;
	/**请求流水号**/
	private String requestNo;
	/**账单号**/
	private String orderNo;
	/**交易额**/
	private BigDecimal tradeMoney;
	/**订单时间**/
	private Date orderTime;
	/**渠道类别**/
	private String chanelType;
	/**交易类型**/
	private String transferType;
	/**前端平台号**/
	private String frontPartnerNo;
	/**供应商编号**/
	private String supplierNo;
	/**积分银行组号**/
	private String pointBankGroup;
	/**现金银行组号**/
	private String bankGroup;
	/**积分机构号**/
	private String pointOrg;
	

	/**结果代码**/
	private String resultCode;
	/**创建时间**/
	private Date createTime;
	/**会员标识**/
	private String memberId;
	/**对账日**/
	private String reconDate;
	
	/**虚拟类型**/
	private String virtualTradeType;
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return this.id;
	}
	
	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}
	
	public String getRequestNo() {
		return this.requestNo;
	}
	
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	public String getOrderNo() {
		return this.orderNo;
	}
	
	public void setTradeMoney(BigDecimal tradeMoney) {
		this.tradeMoney = tradeMoney;
	}
	
	public BigDecimal getTradeMoney() {
		return this.tradeMoney;
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
	
	
	
	public void setTransferType(String transferType) {
		this.transferType = transferType;
	}
	
	public String getTransferType() {
		return this.transferType;
	}
	
	public String getFrontPartnerNo() {
		return frontPartnerNo;
	}

	public void setFrontPartnerNo(String frontPartnerNo) {
		this.frontPartnerNo = frontPartnerNo;
	}

	public String getSupplierNo() {
		return supplierNo;
	}

	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
	}

	public String getPointBankGroup() {
		return pointBankGroup;
	}

	public void setPointBankGroup(String pointBankGroup) {
		this.pointBankGroup = pointBankGroup;
	}

	public String getBankGroup() {
		return bankGroup;
	}

	public void setBankGroup(String bankGroup) {
		this.bankGroup = bankGroup;
	}

	public String getPointOrg() {
		return pointOrg;
	}

	public void setPointOrg(String pointOrg) {
		this.pointOrg = pointOrg;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	
	public String getResultCode() {
		return this.resultCode;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getCreateTime() {
		return this.createTime;
	}
	
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	public String getMemberId() {
		return this.memberId;
	}

	public String getReconDate() {
		return reconDate;
	}

	public void setReconDate(String reconDate) {
		this.reconDate = reconDate;
	}

	public String getVirtualTradeType() {
		return virtualTradeType;
	}

	public void setVirtualTradeType(String virtualTradeType) {
		this.virtualTradeType = virtualTradeType;
	}
	
}

