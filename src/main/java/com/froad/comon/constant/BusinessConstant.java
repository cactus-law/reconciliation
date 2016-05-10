package com.froad.comon.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * 业务常量
 * @author Administrator
 *
 */
public class BusinessConstant {
	
	public static final String RESULT = "resultCode";
	
	/**
	 * 成功
	 */
	public static final String SUCCESS = "0000";
	
	/**
	 * 失败
	 */
	public static final String ERROR = "9999";
	
	/**
	 *	对账数据导入类型
	 */
	public static class IMP_TYPE {
		/**
		 * 全部对账（包含自动和手动触发）
		 */
		public static final String ALL = "0";
		/**
		 * 部分对账（针对末对账处理）
		 */
		public static final String PART = "1";
	}

	/**
	 *	对账数据导入状态
	 */
	public static class IMP_STATUS {
		/**
		 * 初始化
		 */
		public static final String INITIAL = "0";
		/**
		 * 导入成功
		 */
		public static final String SUCCESS = "1";
		/**
		 * 导入失败
		 */
		public static final String FAIL = "2";
		/**
		 * 对账成功
		 */
		public static final String RECON_SUCCESS = "3";
	}
	
	/**
	 *	对账文件规则类型
	 */
	public static class RULE_TYPE {
		/**
		 * 分隔符
		 */
		public static final int SPLIT = 0;
		/**
		 * 定长字符
		 */
		public static final int FIXLENGTH = 1;
		/**
		 * Excel
		 */
		public static final int EXCEL = 2;
	}
	
	/**
	 *	获取文件方式
	 */
	public static class FILE_METHOD {

		/**
		 * HTTP
		 */
		public static final String HTTP = "0";
		
		/**
		 * FTP
		 */
		public static final String FTP = "1";
	}
	
	/**
	 *	获取文件类型
	 */
	public static class FILE_TYPE {

		/**
		 * 文本文件
		 */
		public static final String TXT = "0";
		
		/**
		 * EXCEL
		 */
		public static final String EXCEL = "1";
		
		/**
		 * ZIP压缩文件
		 */
		public static final String ZIP = "2";
	}
	
	/**
	 *	渠道类型
	 */
	public static class CHANEL_TYPE {

		/**
		 * 积分
		 */
		public static final String POINTS = "0";
		
		/**
		 * 现金
		 */
		public static final String CASH = "1";

	}
	
	/**
	 * 冲正
	 */
	public static final String CZ = "cz";
	
	/**
	 * 现金退款
	 */
//	public static final String REFOUD = "2040";
	public static final List<String> REFOUD_LIST=new ArrayList<String>();
	static{
		REFOUD_LIST.add("2040");
		REFOUD_LIST.add("2041");
		REFOUD_LIST.add("2042");
		REFOUD_LIST.add("2043");
		REFOUD_LIST.add("2044");
		REFOUD_LIST.add("2045");
	}
	
	/**
	 * 退款
	 */
	public static final List<String> REFOUD_POINT_LIST=new ArrayList<String>();
	static{
		REFOUD_POINT_LIST.add("1040");
		REFOUD_POINT_LIST.add("1140");
	}
	
}
