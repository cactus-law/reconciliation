package com.froad.recon.importfile.service;

import java.util.List;
import java.util.Map;

import com.froad.recon.importfile.vo.DataRule;
import com.froad.recon.sys.model.PlatformDetail;

/**
 * 对账数据导入Service接口
 * @author Administrator
 *
 */
public interface ImpDataService {

	/**
	 * 根据分隔符规则入库
	 * @param dataRule
	 * @return
	 */
	public int impSplitData(DataRule dataRule) throws Exception;
	
	/**
	 * 根据定长字符规则入库
	 * @param dataRule
	 * @return
	 */
	public int impFixlengthData(DataRule dataRule)throws Exception;
	
	/**
	 * 根据excel规则入库
	 * @param dataRule
	 * @return
	 */
	public int impExcelData(DataRule dataRule)throws Exception;
	
	/**
	 * 根据指定规则入库
	 * @param dataRule
	 * @return
	 */
	public int impData(DataRule dataRule)throws Exception;
	
	/**
	 * 批量添加数据
	 * @param list
	 * @param platformDetail 
	 * @return
	 */
	public int addList(List<Map<String,Object>> list, PlatformDetail platformDetail);
	
}
