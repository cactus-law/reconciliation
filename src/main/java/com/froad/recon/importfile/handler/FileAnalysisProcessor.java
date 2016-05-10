package com.froad.recon.importfile.handler;

import java.util.Map;

import com.froad.flow.FlowException;

/**
 * 解析对账文件接口
 * @author Administrator
 *
 */
public interface FileAnalysisProcessor {
	
	/**
	 * 解析对账文件返回 数据
	 * @param reqMap
	 * @return
	 */
	public Map<String,Object> execute(Map<String,Object> reqMap) throws FlowException;

}
