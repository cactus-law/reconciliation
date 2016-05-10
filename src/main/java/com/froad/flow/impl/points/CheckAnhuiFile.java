package com.froad.flow.impl.points;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.froad.beans.Rckflowdetail;
import com.froad.comon.constant.BusinessConstant;
import com.froad.comon.constant.ParamCommand;
import com.froad.comon.exception.ServiceException;
import com.froad.comon.util.FileUtil;
import com.froad.comon.util.HttpClientUtil;
import com.froad.flow.FlowException;
import com.froad.flow.FlowInterface;
import com.froad.recon.importfile.model.IimpStatusDetail;
import com.froad.recon.importfile.service.IimpStatusDetailService;
import com.froad.recon.importfile.service.ImpDataService;
import com.froad.recon.importfile.vo.DataRule;
import com.froad.recon.sys.model.PlatformDetail;
import com.froad.recon.sys.service.PlatformDetailService;

/**
 * 安徽银行获取对账文件
 * 
 * @author Administrator
 * 
 */
@Component("checkAnhuiFile")
public class CheckAnhuiFile implements FlowInterface {

	final static Logger logger = LoggerFactory.getLogger(CheckAnhuiFile.class);

	@Autowired
	private PlatformDetailService platformDetailService;

	@Autowired
	private ImpDataService impDataService;

	@Autowired
	private IimpStatusDetailService iimpStatusDetailService;

	@Override
	public Rckflowdetail execute(Rckflowdetail rckflowdetail)
			throws FlowException, ServiceException {
		try {
			PlatformDetail platformDetail = platformDetailService
					.getById(rckflowdetail.getMsg());
			IimpStatusDetail impStatusDetail = rckflowdetail
					.getIimpStatusDetail();
			// 获取对账文件
			StringBuffer url = new StringBuffer(
					ParamCommand.bankpoints_datafile_url);
			String transDate = rckflowdetail.getId().getCleardate();
			String bankGroup = platformDetail.getChannelNo();
			url.append("?transDate=").append(transDate);
			url.append("&bankGroup=").append(bankGroup);
			url.append("&channel=");

			// 获取对一个对账文件
			String channel1 = "1001";
			String channel2 = "1002";
			String channel5 = "1005";
			String filepath1 = ParamCommand.data_dir + transDate + "/anhui"
					+ channel1 + ".zip";
			String filepath2 = ParamCommand.data_dir + transDate + "/anhui"
					+ channel2 + ".zip";
			String filepath5 = ParamCommand.data_dir + transDate + "/anhui"
					+ channel5 + ".zip";

			int ret1 = HttpClientUtil.download(url.toString() + channel1,
					filepath1);
			int ret2 = HttpClientUtil.download(url.toString() + channel2,
					filepath2);
			int ret5 = HttpClientUtil.download(url.toString() + channel5,
					filepath5);
			if (ret1 < 1 || ret2 < 1 || ret5 < 1) {
				logger.info(
						"获取对账文件失败.[filepath1={},filepath2={},filepath5={}]",
						new Object[] { filepath1, filepath2, filepath5 });
				impStatusDetail.setStatus(BusinessConstant.IMP_STATUS.INITIAL);// 状态
				return rckflowdetail;
			}
			logger.info("获取对账文件成功.[filepath1={},filepath2,filepath5]",
					new Object[] { filepath1, filepath2, filepath5 });

			// 解压对账文件
			try {
				FileUtil.unZipFiles(filepath1, ParamCommand.data_dir
						+ transDate + "/anhui" + channel1 + "/");
				FileUtil.unZipFiles(filepath2, ParamCommand.data_dir
						+ transDate + "/anhui" + channel2 + "/");
				FileUtil.unZipFiles(filepath5, ParamCommand.data_dir
						+ transDate + "/anhui" + channel5 + "/");
			} catch (IOException e) {
				logger.error("解压对账文件异常.", e);
				impStatusDetail.setStatus(BusinessConstant.IMP_STATUS.INITIAL);// 状态
				return rckflowdetail;
			}
			logger.info("解压对账文件成功.[dirpath1={},dirpath2={},dirpath5={}]",
					new Object[] {
							ParamCommand.data_dir + transDate + "/anhui"
									+ channel1 + "/",
							ParamCommand.data_dir + transDate + "/anhui"
									+ channel2 + "/",
							ParamCommand.data_dir + transDate + "/anhui"
									+ channel5 + "/" });

			// 设置规则参数
			DataRule dataRule = new DataRule();
			dataRule.setRuleType(BusinessConstant.RULE_TYPE.SPLIT);
			dataRule.setReconDate(transDate);
			dataRule.setRuleFile(ParamCommand.rule_dir + "anhui.srl");
			String fileName1 = ParamCommand.data_dir + transDate + "/anhui"
					+ channel1 + "/ccqf.txt";
			String fileName2 = ParamCommand.data_dir + transDate + "/anhui"
					+ channel2 + "/ccqf.txt";
			String fileName5 = ParamCommand.data_dir + transDate + "/anhui"
					+ channel5 + "/ccqf.txt";

			int rows1 = FileUtil.checkContent(fileName1);
			int rows2 = FileUtil.checkContent(fileName2);
			int rows5 = FileUtil.checkContent(fileName5);
			// 判断文件内容
			if (rows1 < 0 || rows2 < 0 || rows5 < 0) {
				logger.info("检查对账数据失败.[rows1={},rows2={},rows5={}]",
						new Object[] { rows1, rows2, rows5 });
				impStatusDetail.setStatus(BusinessConstant.IMP_STATUS.INITIAL);// 状态
				return rckflowdetail;
			}
			logger.info("检查对账数据成功.[rows1={},rows2={},rows5={}]", new Object[] {
					rows1, rows2, rows5 });

			int total = rows1 + rows2 + rows5;

			// 解析第一个对账文件入库
			dataRule.setDataFile(fileName1);
			int count = impDataService.impData(dataRule);

			// 解析第二个对账文件入库
			dataRule.setDataFile(fileName2);
			count += impDataService.impData(dataRule);

			// 解析第三个对账文件入库
			dataRule.setDataFile(fileName5);
			count += impDataService.impData(dataRule);

			// AOP修改成功记录
			rckflowdetail.setDealcount(count);

			impStatusDetail.setSuccessCount(count);// 导入成功总数
			impStatusDetail.setErrorCount(total - count);// 导入失败总数
			impStatusDetail.setTotal(total);// 导入总记录数
			if (count == total) {// 导入成功
				impStatusDetail.setStatus(BusinessConstant.IMP_STATUS.SUCCESS);// 状态
			} else {// 导入失败
				impStatusDetail.setStatus(BusinessConstant.IMP_STATUS.FAIL);// 状态
			}
		} catch (ServiceException e) {
			logger.error(e.getErrorMsg(), e);
			throw new FlowException(rckflowdetail, e.getErrorMsg(), e);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new FlowException(rckflowdetail, e.getMessage(), e);
		}
		return rckflowdetail;
	}

}
