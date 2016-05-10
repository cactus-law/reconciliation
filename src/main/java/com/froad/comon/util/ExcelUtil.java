package com.froad.comon.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 读取excel工具类
 * @author Administrator
 *
 */
public class ExcelUtil {
	
	private static Log logger = LogFactory.getLog(ExcelUtil.class);
	
	/** 错误信息 */
	private String errorInfo;

	/**
	 * @throws Exception 
	 * @描述：根据文件名读取excel文件
	 */
	public List<List<String>> read(String filePath, int end) throws Exception {
		List<List<String>> dataList = new ArrayList<List<String>>();
		InputStream is = null;
		try {
			/** 验证文件是否合法 */
			if (!validateExcel(filePath)) {
				logger.info(errorInfo);
				return null;
			}
			
			/** 判断文件的类型，是2003还是2007 */
			boolean isExcel2003 = true;
			if (isExcel2007(filePath)) {
				isExcel2003 = false;
			}
			
			/** 调用本类提供的根据流读取的方法 */
			is = new FileInputStream(new File(filePath));
			Workbook wb = null;
			if (isExcel2003) {
				wb = new HSSFWorkbook(is);
			} else {
				wb = new XSSFWorkbook(is);
			}
			
			dataList = read(wb, end);
		} catch (IOException e) {
			logger.error("读取excelIO异常", e);
		    throw e;
		} catch (Exception e) {
			logger.error("读取excel异常", e);
			throw e;
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					is = null;
				}
			}
		}
		return dataList;
	}

	/**
	 * @描述：读取数据
	 */
	protected List<List<String>> read(Workbook wb, int end) {
		List<List<String>> dataList = new ArrayList<List<String>>();
		
		/** 得到第一个shell */
		Sheet sheet = wb.getSheetAt(0);
		
		/** 得到Excel的行数 */
		int rowCount = sheet.getPhysicalNumberOfRows()-end;
		logger.info("rowCount="+rowCount);
		
		/** 循环Excel的行 */
		for (int r = 0; r < rowCount; r++) {
			Row row = sheet.getRow(r);
			if (row == null) {
				continue;
			}
			List<String> rowList = new ArrayList<String>();
			/** 循环Excel的列 */
			for (int c = 0; c < row.getPhysicalNumberOfCells(); c++) {
				Cell cell = row.getCell(c);
				String cellValue = "";
				if (null != cell) {
					// 以下是判断数据的类型
					switch (cell.getCellType()) {
						// XSSFCell可以达到相同的效果
						case HSSFCell.CELL_TYPE_NUMERIC: // 数字
							double d = cell.getNumericCellValue();
							if (HSSFDateUtil.isCellDateFormatted(cell)) {// 日期类型
								Date date = HSSFDateUtil.getJavaDate(d);
								cellValue = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
							} else {// 数值类型
								cellValue = cell.getNumericCellValue() + "";
							}
							break;
							
						case HSSFCell.CELL_TYPE_STRING: // 字符串
							cellValue = cell.getStringCellValue();
							cellValue = "null".equalsIgnoreCase(cellValue)?"":cellValue;
							break;
							
						case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
							cellValue = cell.getBooleanCellValue() + "";
							break;
							
						case HSSFCell.CELL_TYPE_FORMULA: // 公式
							cellValue = cell.getCellFormula() + "";
							break;
							
						case HSSFCell.CELL_TYPE_BLANK: // 空值
							cellValue = "";
							break;
							
						case HSSFCell.CELL_TYPE_ERROR: // 故障
							cellValue = "非法字符";
							break;
							
						default:
							cellValue = "未知类型";
							break;
					}
				}
				if(cellValue.startsWith("#")){
					continue;
				}
				rowList.add(cellValue);
			}
			if(rowList.isEmpty()){
				continue;
			}
			dataList.add(rowList);
		}
		return dataList;
	}
	
	/**
	 * 验证EXCEL文件是否合法
	 */
	protected boolean validateExcel(String filePath) {

		/** 判断文件名是否为空或者文件是否存在 */
		if (!fileExist(filePath)) {
			errorInfo = "文件不存在"+filePath;
			return false;
		}

		/** 检查文件是否是Excel格式的文件 */
		if (!isExcel(filePath)) {
			errorInfo = "文件名不是excel格式";
			return false;
		}
		return true;
	}
	
	/**
	 * 依据后缀名判断读取的是否为Excel文件
	 * 
	 * @param filePath
	 * @return
	 */
	protected boolean isExcel(String filePath) {
		if (filePath.matches("^.+\\.(?i)(xls)$")
				|| filePath.matches("^.+\\.(?i)(xlsx)$")) {
			return true;
		}
		return false;
	}

	/**
	 * 检查文件是否存在
	 */
	protected boolean fileExist(String filePath) {
		if (filePath == null || filePath.trim().equals(""))
			return false;
		File file = new File(filePath);
		if (file == null || !file.exists()) {
			return false;
		}
		return true;
	}

	/**
	 * 依据内容判断是否为excel2003及以下
	 */
	protected boolean isExcel2003(String filePath) {
		BufferedInputStream bis = null;
		try {
			bis = new BufferedInputStream(
					new FileInputStream(filePath));
			if (POIFSFileSystem.hasPOIFSHeader(bis)) {
				logger.info("Excel版本为excel2003及以下");
				return true;
			}
		} catch (IOException e) {
			logger.error("判断excel版本IO异常", e);
		}finally{
			if(bis != null){
				try {
					bis.close();
				} catch (IOException e) {
					bis = null;
				}
			}
		}
		return false;
	}

	/**
	 * 依据内容判断是否为excel2007及以上
	 */
	protected boolean isExcel2007(String filePath) {
		BufferedInputStream bis = null;
		try {
			bis = new BufferedInputStream(
					new FileInputStream(filePath));
			if (POIXMLDocument.hasOOXMLHeader(bis)) {
				logger.info("Excel版本为excel2007及以上");
				return true;
			}
		} catch (IOException e) {
			logger.error("判断excel版本IO异常", e);
		}finally{
			if(bis != null){
				try {
					bis.close();
				} catch (IOException e) {
					bis = null;
				}
			}
		}
		return false;
	}
	public static void main(String[] args) throws Exception {
		ExcelUtil readExcel = new ExcelUtil();
		List<List<String>> dataList = readExcel.read("e://22320150701.csv", 0);
		System.out.println(dataList);
	}
}
