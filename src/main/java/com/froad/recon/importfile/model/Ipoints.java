package com.froad.recon.importfile.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 积分对账数据表
 * @author zhangjwei
 * @created 2015-06-16 11:07:28
 * @version 1.0
 */

public class Ipoints implements java.io.Serializable{
    private static final long serialVersionUID = 1L;

	/**交易id**/
	private String transferId;
	/**交易类别**/
	private String transferType;
	/**请求流水号**/
	private String requestNo;
	/**商户编号**/
	private String partnerNo;
	/**积分额**/
	private BigDecimal points;
	/**渠道类别	0 积分**/
	private String chanelType;
	/**支付渠道编号,指银行组号或机构号**/
	private String channelNo;
	/**订单时间**/
	private Date orderTime;
	/**创建时间**/
	private Date createTime;
	/**会员标识**/
	private String memberId;
	
	public void setTransferId(String transferId) {
		this.transferId = transferId;
	}
	
	public String getTransferId() {
		return this.transferId;
	}
	
	public void setTransferType(String transferType) {
		this.transferType = transferType;
	}
	
	public String getTransferType() {
		return this.transferType;
	}
	
	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}
	
	public String getRequestNo() {
		return this.requestNo;
	}
	
	public void setPartnerNo(String partnerNo) {
		this.partnerNo = partnerNo;
	}
	
	public String getPartnerNo() {
		return this.partnerNo;
	}
	
	public void setPoints(BigDecimal points) {
		this.points = points;
	}
	
	public BigDecimal getPoints() {
		return this.points;
	}
	
	public void setChanelType(String chanelType) {
		this.chanelType = chanelType;
	}
	
	public String getChanelType() {
		return this.chanelType;
	}
	
	public void setChannelNo(String channelNo) {
		this.channelNo = channelNo;
	}
	
	public String getChannelNo() {
		return this.channelNo;
	}
	
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	
	public Date getOrderTime() {
		return this.orderTime;
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
	
}

