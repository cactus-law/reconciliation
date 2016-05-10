package com.froad.recon.importfile.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 安徽银行对账数据表
 * @author zhangjwei
 * @created 2015-06-16 11:07:09
 * @version 1.0
 */

public class IbankDataAnhui implements java.io.Serializable{
    private static final long serialVersionUID = 1L;

	/**订单编号**/
	private String orderNo;
	/**订单时间**/
	private Date orderTime;
	/**订单类型**/
	private String orderType;
	/**原订单号**/
	private String orgOrderNo;
	/**用户标识**/
	private String userId;
	/**积分**/
	private BigDecimal points;
	/**积分平台订单编号**/
	private String bankOrderNo;
	/**余额**/
	private BigDecimal blance;
	
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
	
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	
	public String getOrderType() {
		return this.orderType;
	}
	
	public void setOrgOrderNo(String orgOrderNo) {
		this.orgOrderNo = orgOrderNo;
	}
	
	public String getOrgOrderNo() {
		return this.orgOrderNo;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserId() {
		return this.userId;
	}
	
	public void setPoints(BigDecimal points) {
		this.points = points;
	}
	
	public BigDecimal getPoints() {
		return this.points;
	}
	
	public void setBankOrderNo(String bankOrderNo) {
		this.bankOrderNo = bankOrderNo;
	}
	
	public String getBankOrderNo() {
		return this.bankOrderNo;
	}
	
	public void setBlance(BigDecimal blance) {
		this.blance = blance;
	}
	
	public BigDecimal getBlance() {
		return this.blance;
	}
	
}

