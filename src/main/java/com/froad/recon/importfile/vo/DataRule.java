package com.froad.recon.importfile.vo;

/**
 * 导入规则VO
 * @author Administrator
 *
 */
public class DataRule {

	private int ruleType;//规则类型
	private String ruleFile;//规则文件
	private String dataFile;//数据文件
	private String reconDate;//对账日期
	private String table;//对账数据表名
	
	
	public int getRuleType() {
		return ruleType;
	}
	public void setRuleType(int ruleType) {
		this.ruleType = ruleType;
	}
	public String getRuleFile() {
		return ruleFile;
	}
	public void setRuleFile(String ruleFile) {
		this.ruleFile = ruleFile;
	}
	public String getDataFile() {
		return dataFile;
	}
	public void setDataFile(String dataFile) {
		this.dataFile = dataFile;
	}
	public String getReconDate() {
		return reconDate;
	}
	public void setReconDate(String reconDate) {
		this.reconDate = reconDate;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	
}
