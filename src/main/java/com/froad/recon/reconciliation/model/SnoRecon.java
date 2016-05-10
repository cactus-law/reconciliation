package com.froad.recon.reconciliation.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 未对账表
 * @author zhangjwei
 * @created 2015-06-16 11:25:58
 * @version 1.0
 */

public class SnoRecon implements java.io.Serializable{
    private static final long serialVersionUID = 1L;

	/**编号Fk**/
	private String id;
	/**渠道类别	0 积分 1 现金**/
	private String chanelType;
	/**平台编号**/
	private String platformNo;
	/**交易类型	如退分、支付**/
	private String tradeType;
	/**定单号	对账唯一标识**/
	private String orderNo;
	/**订单时间**/
	private Date orderTime;
	/**对账日**/
	private String reconDate;
	/**创建时间**/
	private Date createTime;
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return this.id;
	}
	
	public void setChanelType(String chanelType) {
		this.chanelType = chanelType;
	}
	
	public String getChanelType() {
		return this.chanelType;
	}
	
	public void setPlatformNo(String platformNo) {
		this.platformNo = platformNo;
	}
	
	public String getPlatformNo() {
		return this.platformNo;
	}
	
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	
	public String getTradeType() {
		return this.tradeType;
	}
	
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
	
	
	
	public String getReconDate() {
		return reconDate;
	}

	public void setReconDate(String reconDate) {
		this.reconDate = reconDate;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getCreateTime() {
		return this.createTime;
	}

	@Override
	public String toString() {
		return "SnoRecon [id=" + id + ", chanelType=" + chanelType
				+ ", platformNo=" + platformNo + ", tradeType=" + tradeType
				+ ", orderNo=" + orderNo + ", orderTime=" + orderTime
				+ ", reconDate=" + reconDate + ", createTime=" + createTime
				+ "]";
	}
}

