package com.froad.recon.importfile.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.froad.recon.importfile.dao.ImpDataDao;
import com.froad.recon.importfile.service.ImpDataWrapService;

/**
 * 导入数据事物处理实现类
 * @author Administrator
 *
 */
@Component("impDataWrapService")
public class ImpDataWrapServiceImpl implements ImpDataWrapService {

	@Autowired
	private ImpDataDao impDataDao;
	
	@Override
	public int addList(List<Map<String, Object>> list) {
		int ret = impDataDao.addList(list);
		return ret;
	}

	@Override
	public int delReconData(String sql) {
		int ret = this.impDataDao.delReconData(sql);
		return ret;
	}

}
