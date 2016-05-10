package com.froad.recon.importfile.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.froad.comon.constant.ReconBusinessConstant;
import com.froad.recon.importfile.common.ExcelRuleUtil;
import com.froad.recon.importfile.common.FixlengthRuleUtil;
import com.froad.recon.importfile.common.SplitRuleUtil;
import com.froad.recon.importfile.service.ImpDataService;
import com.froad.recon.importfile.service.ImpDataWrapService;
import com.froad.recon.importfile.vo.DataRule;
import com.froad.recon.sys.model.PlatformDetail;

/**
 * 对账数据导入Service实现类
 * @author Administrator
 *
 */
@Component("impDataService")
public class ImpDataServiceImpl implements ImpDataService {
	
	final static Logger logger = LoggerFactory.getLogger(ImpDataServiceImpl.class);
	
	private final int pageSize = 1000;
	
	@Autowired
	private ImpDataWrapService impDataWrapService;

	@Override
	public int impSplitData(DataRule dataRule) throws Exception {
		
		List<Map<String,Object>> list = SplitRuleUtil.analysysData(dataRule.getRuleFile(), 
				dataRule.getDataFile(), dataRule.getReconDate(), dataRule.getTable());
		
		if(list==null || list.isEmpty()){
			return 0;
		}
		
		int totalSize = list.size();
		int totalPage = (totalSize-1)/pageSize+1;
		int count = 0;
		for(int pageNo=1;pageNo<=totalPage;pageNo++){
			int start = (pageNo-1)*pageSize;
			int end = pageNo*pageSize;
			end = end>totalSize?totalSize:end;
			List<Map<String,Object>> tempList = list.subList(start, end);
			int ret = this.impDataWrapService.addList(tempList);
			logger.info("数据添加成功.[count={}]", ret);
			if(ret < 1){
				break;
			}
			count += ret;
		}
		
		return count;
	}
	
	@Override
	public int impFixlengthData(DataRule dataRule) throws Exception {
		
		List<Map<String,Object>> list = FixlengthRuleUtil.impData(dataRule.getRuleFile(), 
				dataRule.getDataFile(), dataRule.getReconDate(), dataRule.getTable());
		
		if(list==null || list.isEmpty()){
			return 0;
		}
		
		int totalSize = list.size();
		int totalPage = (totalSize-1)/pageSize+1;
		int count = 0;
		for(int pageNo=1;pageNo<=totalPage;pageNo++){
			int start = (pageNo-1)*pageSize;
			int end = pageNo*pageSize;
			end = end>totalSize?totalSize:end;
			List<Map<String,Object>> tempList = list.subList(start, end);
			int ret = this.impDataWrapService.addList(tempList);
			logger.info("数据添加成功.[count={}]", ret);
			if(ret < 1){
				break;
			}
			count += ret;
		}
		
		return count;
	}

	@Override
	public int impExcelData(DataRule dataRule) throws Exception {
		
		List<Map<String,Object>> list = ExcelRuleUtil.impData(dataRule.getRuleFile(), 
				dataRule.getDataFile(), dataRule.getReconDate(), dataRule.getTable());
		
		if(list==null || list.isEmpty()){
			return 0;
		}
		
		int totalSize = list.size();
		int totalPage = (totalSize-1)/pageSize+1;
		int count = 0;
		for(int pageNo=1;pageNo<=totalPage;pageNo++){
			int start = (pageNo-1)*pageSize;
			int end = pageNo*pageSize;
			end = end>totalSize?totalSize:end;
			List<Map<String,Object>> tempList = list.subList(start, end);
			int ret = this.impDataWrapService.addList(tempList);
			logger.info("数据添加成功.[count={}]", ret);
			if(ret < 1){
				break;
			}
			count += count;
		}
		
		return count;
	}
	
	@Override
	public int impData(DataRule dataRule) throws Exception {
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
			case 2://excel
					list = ExcelRuleUtil.impData(dataRule.getRuleFile(), dataRule.getDataFile(), 
							dataRule.getReconDate(), dataRule.getTable());
				break;
		}
		
		if(list==null || list.isEmpty()){
			return 0;
		}
		
		int totalSize = list.size();
		int totalPage = (totalSize-1)/pageSize+1;
		int count = 0;
		for(int pageNo=1;pageNo<=totalPage;pageNo++){
			int start = (pageNo-1)*pageSize;
			int end = pageNo*pageSize;
			end = end>totalSize?totalSize:end;
			List<Map<String,Object>> tempList = list.subList(start, end);
			int ret = this.impDataWrapService.addList(tempList);
			logger.info("数据添加成功.[count={}]", ret);
			if(ret < 1){
				break;
			}
			count += ret;
		}
		
		return count;
	}
	
	@Override
	public int addList(List<Map<String, Object>> list,PlatformDetail platformDetail) {
		if(list==null || list.isEmpty()){
			return 0;
		}
		
		Map<String,Object> map = list.get(0);
		Object table = map.get("table");
		Object reconDate = map.get("recon_date");
		StringBuffer sql = new StringBuffer("delete from ");
		sql.append(table);
		sql.append(" where recon_date='").append(reconDate).append("'");
		//前端过滤
		if(StringUtils.isNotEmpty((String)map.get("front_partner_no"))){
			sql.append(" and front_partner_no='").append(map.get("front_partner_no")).append("'");
		}
	    /*if(ReconBusinessConstant.PlatformNo.BANK_POINTS.equals(platformDetail.getPlatformNo())){
	    	if(StringUtils.isNotEmpty((String)map.get("channel_no"))){
				sql.append(" and channel_no='").append(map.get("channel_no")).append("'");
			}
	    }*/
		
		if(!ReconBusinessConstant.PlatformNo.FRONT.equals(platformDetail.getPlatformNo())){
			String channelNo=platformDetail.getChannelNo();
			if(PlatformDetail.IS_COMMON_TABLE_YES.equals(platformDetail.getIsCommonTable())){
				//积分账单号转换成账单号
				for(Map<String,Object> data : list){
					data.put("channel_no", channelNo);
				}
				sql.append(" and channel_no='").append(channelNo).append("'");
			}
		}
		
		int rows = this.impDataWrapService.delReconData(sql.toString());
		logger.info("数据删除成功.[rows={}]", rows);
		
		int totalSize = list.size();
		int totalPage = (totalSize-1)/pageSize+1;
		int count = 0;
		for(int pageNo=1;pageNo<=totalPage;pageNo++){
			int start = (pageNo-1)*pageSize;
			int end = pageNo*pageSize;
			end = end>totalSize?totalSize:end;
			List<Map<String,Object>> tempList = list.subList(start, end);
			int ret = this.impDataWrapService.addList(tempList);
			logger.info("数据添加成功.[count={}]", ret);
			if(ret < 1){
				break;
			}
			count += ret;
		}
		
		return count;
	}

}
