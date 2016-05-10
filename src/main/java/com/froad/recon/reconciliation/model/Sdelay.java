package com.froad.recon.reconciliation.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 延时对账表
 * @author zhangjwei
 * @created 2015-06-16 11:25:50
 * @version 1.0
 */

public class Sdelay implements java.io.Serializable{
    private static final long serialVersionUID = 1L;
    /**业务类型退款交易**/
    public final static String BUSINESSTYPE_REFUND="refund";
    /**业务类型 一般交易**/
    public final static String BUSINESSTYPE_TRADE="trade";
    
    /**未获取账单号**/
    public final static String RECON_STATUS_NOBIIL="noBiil";
    /**未对账（已有账单号）**/
    public final static String RECON_STATUS_NORECON="noRecon";
    /**已对账**/
    public final static String RECON_STATUS_RECONED="reconed";

    /**id**/
    private String id;
	/**订单编号PK**/
	private String orderNo;
	/**订单时间**/
	private Date orderTime;
	/**平台编号**/
	private String platformNo;
	/**渠道类别	0 积分 1 现金**/
	private String chanelType;
	/**渠道编号	指银行组号或机构号**/
	private String tradeType;
	/**创建时间**/
	private Date createTime;
	/**对账日**/
	private String reconDate;
	/**业务类型refund=退款，trade=交易**/
	private String businessType;
	/**退款流水号**/
	private String seqNo;
	/**noBiil=未获取账单号,order_no存的退款流水号,noRecon=未对账（已有账单号）,reconed=已对账**/
	private String reconStatus;
	/**前端渠道标示**/
	private String channelNo;
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getPlatformNo() {
		return platformNo;
	}

	public void setPlatformNo(String platformNo) {
		this.platformNo = platformNo;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	public String getReconStatus() {
		return reconStatus;
	}

	public void setReconStatus(String reconStatus) {
		this.reconStatus = reconStatus;
	}

	public String getChannelNo() {
		return channelNo;
	}

	public void setChannelNo(String channelNo) {
		this.channelNo = channelNo;
	}

	@Override
	public String toString() {
		return "Sdelay [id=" + id + ", orderNo=" + orderNo + ", orderTime="
				+ orderTime + ", platformNo=" + platformNo + ", chanelType="
				+ chanelType + ", tradeType=" + tradeType + ", createTime="
				+ createTime + ", reconDate=" + reconDate + ", businessType="
				+ businessType + ", seqNo=" + seqNo + ", reconStatus="
				+ reconStatus + ", channelNo=" + channelNo + "]";
	}

}

