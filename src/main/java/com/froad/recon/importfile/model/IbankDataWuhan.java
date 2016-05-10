package com.froad.recon.importfile.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 武汉银行对账数据表
 * @author zhangjwei
 * @created 2015-06-16 11:07:17
 * @version 1.0
 */

public class IbankDataWuhan implements java.io.Serializable{
    private static final long serialVersionUID = 1L;

	/**交易码**/
	private String orderNo;
	/**机构编号**/
	private String orgNo;
	/**日期**/
	private Date orderTime;
	/**卡号**/
	private String cardNo;
	/**账户编号**/
	private String accountNo;
	/**积分调整类型**/
	private String pointsType;
	/**积分**/
	private BigDecimal points;
	/**交易参考号**/
	private String tradeNo;
	
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	public String getOrderNo() {
		return this.orderNo;
	}
	
	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}
	
	public String getOrgNo() {
		return this.orgNo;
	}
	
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	
	public Date getOrderTime() {
		return this.orderTime;
	}
	
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	
	public String getCardNo() {
		return this.cardNo;
	}
	
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	
	public String getAccountNo() {
		return this.accountNo;
	}
	
	public void setPointsType(String pointsType) {
		this.pointsType = pointsType;
	}
	
	public String getPointsType() {
		return this.pointsType;
	}
	
	public void setPoints(BigDecimal points) {
		this.points = points;
	}
	
	public BigDecimal getPoints() {
		return this.points;
	}
	
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	
	public String getTradeNo() {
		return this.tradeNo;
	}
	
}

