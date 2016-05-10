package com.froad.recon.importfile.model;

import java.sql.Timestamp;

/**
 * IImpStatusDetail entity. @author MyEclipse Persistence Tools
 */

public class IimpStatusDetail implements java.io.Serializable {
	/**获取状态 1=已获取 **/
	public final static String FILE_STATUS_YES = "1";
	/**获取状态0=未获取 **/
	public final static String FILE_STATUS_NO = "0";
	
	/**导入类型 1=自动 **/
	public final static String IMPTYPE_AUTO = "0";
	/**导入类型  0=手动 **/
	public final static String IMPTYPE_MANU = "1";
	
	private IimpStatusDetailId id;
	private String platformDetailName;
	private Integer successCount;
	private Integer errorCount;
	private Integer total;
	/**导入类型	0 自动1 手动*/
	private String impType;
	private String status;
	private Timestamp createTime;
	private String downloadUrl;
	
	/**获取状态1=已获取，0=未获取**/
	private String fileStatus;
	/** default constructor */
	public IimpStatusDetail() {
	}

	/** minimal constructor */
	public IimpStatusDetail(IimpStatusDetailId id) {
		this.id = id;
	}

	/** full constructor */
	public IimpStatusDetail(IimpStatusDetailId id, String platformDetailName,
			Integer successCount, Integer errorCount, Integer total,
			String impType, String status, Timestamp createTime,
			String downloadUrl) {
		this.id = id;
		this.platformDetailName = platformDetailName;
		this.successCount = successCount;
		this.errorCount = errorCount;
		this.total = total;
		this.impType = impType;
		this.status = status;
		this.createTime = createTime;
		this.downloadUrl = downloadUrl;
	}

	// Property accessors

	public IimpStatusDetailId getId() {
		return this.id;
	}

	public void setId(IimpStatusDetailId id) {
		this.id = id;
	}

	public String getPlatformDetailName() {
		return this.platformDetailName;
	}

	public void setPlatformDetailName(String platformDetailName) {
		this.platformDetailName = platformDetailName;
	}

	public Integer getSuccessCount() {
		return this.successCount;
	}

	public void setSuccessCount(Integer successCount) {
		this.successCount = successCount;
	}

	public Integer getErrorCount() {
		return this.errorCount;
	}

	public void setErrorCount(Integer errorCount) {
		this.errorCount = errorCount;
	}

	public Integer getTotal() {
		return this.total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public String getImpType() {
		return this.impType;
	}

	public void setImpType(String impType) {
		this.impType = impType;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getDownloadUrl() {
		return this.downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public String getFileStatus() {
		return fileStatus;
	}

	public void setFileStatus(String fileStatus) {
		this.fileStatus = fileStatus;
	}

}