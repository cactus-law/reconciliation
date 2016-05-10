package com.froad.recon.importfile.common;

import java.util.List;
import java.util.Map;

import com.froad.recon.importfile.vo.DataRule;

/**
 * 规则工具类
 * @author Administrator
 *
 */
public class RuleUtil {

	/**
	 * 解析对账数据
	 * @param dataRule
	 * @return
	 * @throws Exception 
	 */
	public static List<Map<String,Object>>  resolveData(DataRule dataRule) throws Exception{
		List<Map<String,Object>> list = null;
		switch (dataRule.getRuleType()) {
			case 0://分隔符
					list = SplitRuleUtil.analysysData(dataRule.getRuleFile(), dataRule.getDataFile(), 
															dataRule.getReconDate(), dataRule.getTable());
				break;
			case 1://定长字符
					list = FixlengthRuleUtil.impData(dataRule.getRuleFile(), dataRule.getDataFile(), 
															dataRule.getReconDate(), dataRule.getTable());
				break;
			case 2://Excel
					list = ExcelRuleUtil.impData(dataRule.getRuleFile(), dataRule.getDataFile(), 
															dataRule.getReconDate(), dataRule.getTable());
				break;
		}
		return list;
	}
	
}
