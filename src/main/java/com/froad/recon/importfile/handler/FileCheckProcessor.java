package com.froad.recon.importfile.handler;

import java.util.Map;

/**
 * 	校验对账数据文件
 * @author Administrator
 *
 */
public interface FileCheckProcessor {
	
	/**
	 * 校验文件
	 * @param reqMap
	 * @return
	 */
	public Map<String,Object> execute(Map<String,Object> reqMap);

}
