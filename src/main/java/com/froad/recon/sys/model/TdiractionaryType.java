package com.froad.recon.sys.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 字典类型表
 * @author zhangjwei
 * @created 2015-06-16 11:20:30
 * @version 1.0
 */

public class TdiractionaryType implements java.io.Serializable{
    private static final long serialVersionUID = 1L;

	/**编号PK**/
	private String typeNo;
	/**名称**/
	private String typeName;
	/**时间**/
	private Date createTime;
	
	public void setTypeNo(String typeNo) {
		this.typeNo = typeNo;
	}
	
	public String getTypeNo() {
		return this.typeNo;
	}
	
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	public String getTypeName() {
		return this.typeName;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getCreateTime() {
		return this.createTime;
	}
	
}

