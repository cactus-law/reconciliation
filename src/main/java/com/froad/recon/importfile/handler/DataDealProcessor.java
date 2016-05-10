package com.froad.recon.importfile.handler;

import java.util.Map;

import com.froad.flow.FlowException;

/**
 * 数据整理接口
 * @author Administrator
 *
 */
public interface DataDealProcessor {

	/**
	 * 数据整理
	 * @param reqMap
	 * @return
	 */
	public Map<String,Object> execute(Map<String,Object> reqMap) throws Exception;
	
}
