package com.froad.recon.sys.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 平台表
 * @author zhangjwei
 * @created 2015-06-16 11:20:18
 * @version 1.0
 */

public class Platform implements java.io.Serializable{
    private static final long serialVersionUID = 1L;
    
    /**是否对账状态 1=是**/
    public final static String STATUS_YES="1";
    /**是否对账状态 0=否**/
    public final static String STATUS_NO="0";
    
    
	/**平台编号PK**/
	private String platformNo;
	/**平台名称**/
	private String platformName;
	/**是否对账状态	1=是0=否**/
	private String status;
	/**创建时间**/
	private Date createTime;
	/**更新时间**/
	private Date updateTime;
	
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
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return this.status;
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
	
}

