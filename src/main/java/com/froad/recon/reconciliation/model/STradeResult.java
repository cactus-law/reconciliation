package com.froad.recon.reconciliation.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 对账结果表
 * @author zhangjwei
 * @created 2015-06-16 11:26:05
 * @version 1.0
 */

public class STradeResult implements java.io.Serializable{
    private static final long serialVersionUID = 1L;
    
    /**业务规则字段 
     *根据tableName 把结果分离到对应的表中*/
    public static final String TABLE_NAME_S_NO_RECON="SnoRecon";   //未对账
    public static final String TABLE_NAME_S_DELAY="Sdelay";        //延迟
    public static final String TABLE_NAME_S_SUCCESS="Ssuccess";    //成功
    public static final String TABLE_NAME_S_EXCEPTION="Sexception";//有差错
    public static final String TABLE_NAME_S_OTHER="Other";//不处理
    
    /**来源方标准字段名称*/
    public static final String MARK_POINTS="points";
    public static final String MARK_BANK_POINTS="bank_points";
    public static final String MARK_PAY="pay";
    public static final String MARK_PARTNER="partner";
    public static final String MARK_FRONT="front";
    
    /***来源方标志有1*/
    public static final String IS_YES="1";
    /***来源方标志无0**/
    public static final String IS_NOT="0";
    /**来源方标志 2=不需要对账**/
    public static final String IS_NO="2";
    
    public static final String FRONT_CODE_SUCCESS="0000";
    
    
    
	/**编号Fk**/
	private String id;
	/**渠道类别	0 积分 1 现金**/
	private String chanelType;
	/**交易类型**/
	private String tradeType;
	/**定单号**/
	private String orderNo;
	/**订单时间**/
	private Date orderTime;
	/**交易额**/
	private BigDecimal tradeMoney;
	/**创建时间**/
	private Date createTime;
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
	/**前端应答码**/
	private String frontCode;
	/**前端平台号**/
	private String frontPartnerNo;
	/**供应商编号**/
	private String supplierNo;
	/**积分银行组号**/
	private String pointBankGroup;
	/**现金银行组号**/
	private String backGroup;
	/**积分机构号**/
	private String pointOrg;
	/**对账日**/
	private String reconDate;
	/**虚拟类型**/
	private String virtualTradeType;
	/**导入日期，用于退款延迟对账**/
	private String importDate;
	
	/*****************************以下是业务逻辑字段********************************************************************/
	
	
	/*** 结果分离时，分离到哪张表 */
	private String tableName;
	/**错误代码**/
	private String errorCode;
	/**错误描述**/
	private String errorDesc;
	
	/***平台编号集合*/
	private List<String> platformNos;
	
	/***未上传或导入数据失败的 平台明细id集合 */
	private List<String> platformDetails;

	/***成功 平台明细id集合 */
	private List<String> platfromYesfiles;
	/**
	 * Getter method for property <tt>tableName</tt>.
	 * 
	 * @return property value of tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * Setter method for property <tt>tableName</tt>.
	 * 
	 * @param tableName value to be assigned to property tableName
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

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
	
	public void setIsPoints(String isPoints) {
		this.isPoints = isPoints;
	}
	
	public String getIsPoints() {
		return this.isPoints;
	}
	
	public void setIsBankPoints(String isBankPoints) {
		this.isBankPoints = isBankPoints;
	}
	
	public String getIsBankPoints() {
		return this.isBankPoints;
	}
	
	public void setIsPay(String isPay) {
		this.isPay = isPay;
	}
	
	public String getIsPay() {
		return this.isPay;
	}
	
	public void setIsPartner(String isPartner) {
		this.isPartner = isPartner;
	}
	
	public String getIsPartner() {
		return this.isPartner;
	}
	
	public void setIsFront(String isFront) {
		this.isFront = isFront;
	}
	
	public String getIsFront() {
		return this.isFront;
	}
	
	public void setFrontCode(String frontCode) {
		this.frontCode = frontCode;
	}
	
	public String getFrontCode() {
		return this.frontCode;
	}
	
	public void setFrontPartnerNo(String frontPartnerNo) {
		this.frontPartnerNo = frontPartnerNo;
	}
	
	public String getFrontPartnerNo() {
		return this.frontPartnerNo;
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

	public String getBackGroup() {
		return backGroup;
	}

	public void setBackGroup(String backGroup) {
		this.backGroup = backGroup;
	}

	public String getPointOrg() {
		return pointOrg;
	}

	public void setPointOrg(String pointOrg) {
		this.pointOrg = pointOrg;
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

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}
	
	public List<String> getPlatformNos() {
		return platformNos;
	}

	public void setPlatformNos(List<String> platformNos) {
		this.platformNos = platformNos;
	}

	public List<String> getPlatformDetails() {
		return platformDetails;
	}

	public void setPlatformDetails(List<String> platformDetails) {
		this.platformDetails = platformDetails;
	}

	public List<String> getPlatfromYesfiles() {
		return platfromYesfiles;
	}

	public void setPlatfromYesfiles(List<String> platfromYesfiles) {
		this.platfromYesfiles = platfromYesfiles;
	}

	public String getImportDate() {
		return importDate;
	}

	public void setImportDate(String importDate) {
		this.importDate = importDate;
	}

	@Override
	public String toString() {
		return "STradeResult [id=" + id + ", chanelType=" + chanelType
				+ ", tradeType=" + tradeType + ", orderNo=" + orderNo
				+ ", orderTime=" + orderTime + ", tradeMoney=" + tradeMoney
				+ ", createTime=" + createTime + ", isPoints=" + isPoints
				+ ", isBankPoints=" + isBankPoints + ", isPay=" + isPay
				+ ", isPartner=" + isPartner + ", isFront=" + isFront
				+ ", frontCode=" + frontCode + ", frontPartnerNo="
				+ frontPartnerNo + ", supplierNo=" + supplierNo
				+ ", pointBankGroup=" + pointBankGroup + ", backGroup="
				+ backGroup + ", pointOrg=" + pointOrg + ", reconDate="
				+ reconDate + ", virtualTradeType=" + virtualTradeType
				+ ", importDate=" + importDate + ", tableName=" + tableName
				+ ", errorCode=" + errorCode + ", errorDesc=" + errorDesc
				+ ", platformNos=" + platformNos + ", platformDetails="
				+ platformDetails + ", platfromYesfiles=" + platfromYesfiles
				+ "]";
	}
	
}

