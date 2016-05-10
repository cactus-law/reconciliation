package com.froad.recon.sync.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 
 * @author zhangjwei
 * @created 2015-07-31 10:32:55
 * @version 1.0
 */

public class RreconResult implements java.io.Serializable{
    private static final long serialVersionUID = 1L;

	/**编号Pk**/
	private String id;
	/**平台明细编号PK**/
	private String platformDetailNo;
	/**平台明细名称**/
	private String platformDetailName;
	/**平台编号**/
	private String platformNo;
	/**平台名称**/
	private String platformName;
	/**导入总笔数**/
	private Integer importTotal;
	/**对账成功总笔数**/
	private Integer successTotal;
	/**对账成功总金额**/
	private BigDecimal successTotalMoney;
	/**对账退款总金额**/
	private BigDecimal refundTotalMoney;
	/**对账差错总笔数**/
	private Integer exceptionTotal;
	/**对账延迟总笔数**/
	private Integer delayTotal;
	/**未对账总笔数**/
	private Integer noTotal;
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
	
	public void setPlatformDetailNo(String platformDetailNo) {
		this.platformDetailNo = platformDetailNo;
	}
	
	public String getPlatformDetailNo() {
		return this.platformDetailNo;
	}
	
	public void setPlatformDetailName(String platformDetailName) {
		this.platformDetailName = platformDetailName;
	}
	
	public String getPlatformDetailName() {
		return this.platformDetailName;
	}
	
	public void setPlatformNo(String platformNo) {
		this.platformNo = platformNo;
	}
	
	public String getPlatformNo() {
		return this.platformNo;
	}
	
	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}
	
	public String getPlatformName() {
		return this.platformName;
	}
	
	public void setImportTotal(Integer importTotal) {
		this.importTotal = importTotal;
	}
	
	public Integer getImportTotal() {
		return this.importTotal;
	}
	
	public void setSuccessTotal(Integer successTotal) {
		this.successTotal = successTotal;
	}
	
	public Integer getSuccessTotal() {
		return this.successTotal;
	}
	
	public BigDecimal getRefundTotalMoney() {
		return refundTotalMoney;
	}

	public void setRefundTotalMoney(BigDecimal refundTotalMoney) {
		this.refundTotalMoney = refundTotalMoney;
	}

	public void setSuccessTotalMoney(BigDecimal successTotalMoney) {
		this.successTotalMoney = successTotalMoney;
	}
	
	public BigDecimal getSuccessTotalMoney() {
		return this.successTotalMoney;
	}
	
	public void setExceptionTotal(Integer exceptionTotal) {
		this.exceptionTotal = exceptionTotal;
	}
	
	public Integer getExceptionTotal() {
		return this.exceptionTotal;
	}
	
	public void setDelayTotal(Integer delayTotal) {
		this.delayTotal = delayTotal;
	}
	
	public Integer getDelayTotal() {
		return this.delayTotal;
	}
	
	public void setNoTotal(Integer noTotal) {
		this.noTotal = noTotal;
	}
	
	public Integer getNoTotal() {
		return this.noTotal;
	}
	
	public void setReconDate(String reconDate) {
		this.reconDate = reconDate;
	}
	
	public String getReconDate() {
		return this.reconDate;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getCreateTime() {
		return this.createTime;
	}
	
}

