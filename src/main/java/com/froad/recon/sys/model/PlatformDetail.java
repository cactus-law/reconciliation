package com.froad.recon.sys.model;

import java.util.Date;

/**
 * 平台信息配置表
 * 
 * @author zhangjwei
 * @created 2015-06-16 11:20:22
 * @version 1.0
 */

public class PlatformDetail implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 是否对账状态 1=是 **/
	public final static String STATUS_YES = "1";
	/** 是否对账状态 0=否 **/
	public final static String STATUS_NO = "0";
	
	/** 文件状态 0=自动 **/
	public final static String FILE_STATUS_YES = "0";
	/**文件状态 1=手动 **/
	public final static String FILE_STATUS_NO = "1";
	
	/**是否公用表,1=公用表， 0=不共用**/
	public final static String IS_COMMON_TABLE_YES = "1";
	public final static String IS_COMMON_TABLE_NO = "0";
	

	/** 平台明细编号PK **/
	private String platformDetailNo;
	/** 平台明细名称 **/
	private String platformDetailName;
	/** 平台编号 **/
	private String platformNo;
	/** 是否参与对账 1.是0.否 **/
	private String status;
	/** 对账表名 **/
	private String tableName;
	/** 对账字段 **/
	private String tableField;
	/** 渠道类别 0 积分 1 现金 **/
	private String chanelType;
	/** 渠道编号 指银行组号或机构号 **/
	private String channelNo;
	/** 是否必须对账 1.是0.否(如果是1，表示对账必须要有) **/
	private String flag;
	/** 创建时间 **/
	private Date createTime;
	/** 更新时间 **/
	private Date updateTime;

	/**
	 * 获取文件地址
	 */
	private String fileUrl;
	
	/**
	 * 获取文件参数
	 */
	private String fileParam;
	/**
	 * 获取文件类型0.文本文件1.zip压缩文件
	 */
	private String fileType;
	/**
	 * 本地文件路径
	 */
	private String localFilePath;
	/**
	 * 获取文件器
	 */
	private String fileBean;
	/**
	 * 文件解析器
	 */
	private String fileReadBean;
	/**
	 * 规则文件名
	 */
	private String ruleFileName;
	/**
	 * 数据校验器
	 */
	private String checkDataBean;
	/**
	 * 数据整理器
	 */
	private String dataDealBean;
	
	/**文件状态0.自动1.手动**/
	private String fileStatus;
	/**是否公用表,1=公用表， 0=不共用**/
	private String isCommonTable;

	/**
	 * Getter method for property <tt>fileUrl</tt>.
	 * 
	 * @return property value of fileUrl
	 */
	public String getFileUrl() {
		return fileUrl;
	}

	/**
	 * Setter method for property <tt>fileUrl</tt>.
	 * 
	 * @param fileUrl value to be assigned to property fileUrl
	 */
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	/**
	 * Getter method for property <tt>fileParam</tt>.
	 * 
	 * @return property value of fileParam
	 */
	public String getFileParam() {
		return fileParam;
	}

	/**
	 * Setter method for property <tt>fileParam</tt>.
	 * 
	 * @param fileParam value to be assigned to property fileParam
	 */
	public void setFileParam(String fileParam) {
		this.fileParam = fileParam;
	}

	/**
	 * Getter method for property <tt>fileType</tt>.
	 * 
	 * @return property value of fileType
	 */
	public String getFileType() {
		return fileType;
	}

	/**
	 * Setter method for property <tt>fileType</tt>.
	 * 
	 * @param fileType value to be assigned to property fileType
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	/**
	 * Getter method for property <tt>localFilePath</tt>.
	 * 
	 * @return property value of localFilePath
	 */
	public String getLocalFilePath() {
		return localFilePath;
	}

	/**
	 * Setter method for property <tt>localFilePath</tt>.
	 * 
	 * @param localFilePath value to be assigned to property localFilePath
	 */
	public void setLocalFilePath(String localFilePath) {
		this.localFilePath = localFilePath;
	}

	public String getFileBean() {
		return fileBean;
	}

	public void setFileBean(String fileBean) {
		this.fileBean = fileBean;
	}

	/**
	 * Getter method for property <tt>fileReadBean</tt>.
	 * 
	 * @return property value of fileReadBean
	 */
	public String getFileReadBean() {
		return fileReadBean;
	}

	/**
	 * Setter method for property <tt>fileReadBean</tt>.
	 * 
	 * @param fileReadBean value to be assigned to property fileReadBean
	 */
	public void setFileReadBean(String fileReadBean) {
		this.fileReadBean = fileReadBean;
	}

	public String getRuleFileName() {
		return ruleFileName;
	}

	public void setRuleFileName(String ruleFileName) {
		this.ruleFileName = ruleFileName;
	}

	/**
	 * Getter method for property <tt>checkDataBean</tt>.
	 * 
	 * @return property value of checkDataBean
	 */
	public String getCheckDataBean() {
		return checkDataBean;
	}

	/**
	 * Setter method for property <tt>checkDataBean</tt>.
	 * 
	 * @param checkDataBean value to be assigned to property checkDataBean
	 */
	public void setCheckDataBean(String checkDataBean) {
		this.checkDataBean = checkDataBean;
	}

	/**
	 * Getter method for property <tt>dataDealBean</tt>.
	 * 
	 * @return property value of dataDealBean
	 */
	public String getDataDealBean() {
		return dataDealBean;
	}

	/**
	 * Setter method for property <tt>dataDealBean</tt>.
	 * 
	 * @param dataDealBean value to be assigned to property dataDealBean
	 */
	public void setDataDealBean(String dataDealBean) {
		this.dataDealBean = dataDealBean;
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

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableName() {
		return this.tableName;
	}

	public void setTableField(String tableField) {
		this.tableField = tableField;
	}

	public String getTableField() {
		return this.tableField;
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

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public String getFileStatus() {
		return fileStatus;
	}

	public void setFileStatus(String fileStatus) {
		this.fileStatus = fileStatus;
	}

	public String getIsCommonTable() {
		return isCommonTable;
	}

	public void setIsCommonTable(String isCommonTable) {
		this.isCommonTable = isCommonTable;
	}

	@Override
	public String toString() {
		return "PlatformDetail [platformDetailNo=" + platformDetailNo
				+ ", platformDetailName=" + platformDetailName
				+ ", platformNo=" + platformNo + ", status=" + status
				+ ", tableName=" + tableName + ", tableField=" + tableField
				+ ", chanelType=" + chanelType + ", channelNo=" + channelNo
				+ ", flag=" + flag + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", fileUrl=" + fileUrl
				+ ", fileParam=" + fileParam + ", fileType=" + fileType
				+ ", localFilePath=" + localFilePath + ", fileBean=" + fileBean
				+ ", fileReadBean=" + fileReadBean + ", ruleFileName="
				+ ruleFileName + ", checkDataBean=" + checkDataBean
				+ ", dataDealBean=" + dataDealBean + ", fileStatus="
				+ fileStatus + ", isCommonTable=" + isCommonTable + "]";
	}
	
}
