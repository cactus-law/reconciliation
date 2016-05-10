package com.froad.recon.reconciliation.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 对账成功表
 * @author zhangjwei
 * @created 2015-06-16 11:26:02
 * @version 1.0
 */

public class Ssuccess implements java.io.Serializable{
    private static final long serialVersionUID = 1L;

	/**订单编号PK**/
	private String orderNo;
	/**订单时间**/
	private Date orderTime;
	/**渠道类别	0 积分 1 现金**/
	private String chanelType;
	/**交易类型	如退分、支付**/
	private String tradeType;
	/**交易额**/
	private BigDecimal tradeMoney;
	/**创建时间**/
	private Date createTime;
	
	/**对账日**/
	private String reconDate;
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

	public String getReconDate() {
		return reconDate;
	}

	public void setReconDate(String reconDate) {
		this.reconDate = reconDate;
	}

	@Override
	public String toString() {
		return "Ssuccess [orderNo=" + orderNo + ", orderTime=" + orderTime
				+ ", chanelType=" + chanelType + ", tradeType=" + tradeType
				+ ", tradeMoney=" + tradeMoney + ", createTime=" + createTime
				+ ", reconDate=" + reconDate + "]";
	}
	
}

