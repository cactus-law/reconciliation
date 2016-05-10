package com.froad.recon.importfile.service;

import java.util.List;
import java.util.Map;

/**
 * 导入数据事物处理接口
 * @author Administrator
 *
 */
public interface ImpDataWrapService {

	/**
     * 添加
     * @param list
     * @return 本次成功记录数
     */
    public int addList(List<Map<String,Object>> list);
    
    /**
     * 删除
     * @param sql
     * @return
     */
    public int delReconData(String sql);
}
