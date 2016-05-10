package com.froad.recon.sys.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 字典表
 * @author zhangjwei
 * @created 2015-06-16 11:20:26
 * @version 1.0
 */

public class Tdiractionary implements java.io.Serializable{
    private static final long serialVersionUID = 1L;
    
    /** 交易类型*/
    public static String TYPE_NO_TRADE_TYPE="tradeType";
    public static String STATUS_YES="Y";
    public static String STATUS_NO="N";

	/**编号PK**/
	private String diractionaryNo;
	/**类型FK**/
	private String typeNo;
	/**名称**/
	private String diractionaryName;
	/**状态 Y=开启，N=关闭**/
	private String status;
	/**时间**/
	private Date createTime;
	
	public void setDiractionaryNo(String diractionaryNo) {
		this.diractionaryNo = diractionaryNo;
	}
	
	public String getDiractionaryNo() {
		return this.diractionaryNo;
	}
	
	public void setTypeNo(String typeNo) {
		this.typeNo = typeNo;
	}
	
	public String getTypeNo() {
		return this.typeNo;
	}
	
	public void setDiractionaryName(String diractionaryName) {
		this.diractionaryName = diractionaryName;
	}
	
	public String getDiractionaryName() {
		return this.diractionaryName;
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
	
}

