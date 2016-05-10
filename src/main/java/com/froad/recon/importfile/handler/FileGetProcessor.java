package com.froad.recon.importfile.handler;

import java.util.Map;

import com.froad.flow.FlowException;

/**
 * 获取对账文件接口
 * @author Administrator
 *
 */
public interface FileGetProcessor {

	/**
	 * 获取文件
	 * @param reqMap
	 * @return
	 */
	public Map<String,Object> execute(Map<String,Object> reqMap)  throws FlowException;

}
