package com.froad.comon.constant;

public class AppConstant {
	/**应用一次查询页大小*/
	public final static Integer  PAGE_SIZE=5000;
	/**应用in 查询的个数大小*/
	public final static Integer  PAGE_SIZE_IN=500;
	/**应用一次插入条数*/
	public final static Integer  BATCH_SIZE=1000;
	/**分离结果集条数*/
	public final static Integer  RENCON_SIZE=1000;
	
	/**应用一次删除条数*/
	public final static Integer  BATCH_DELETE=2000;

	/***对象系统入redis的前缀*/
	public final static String  RECON_KEY_PREFIX="app:recon:";
	
	/*** 前端和银行 两方对账 日切时间***/
	public final static String CUT_HOUR="21";
	
	/***前端银行供应商 三对账 日切时间***/
	public final static String CUT_HOUR_FPS="21";
	
	/***退款延迟对账 ， 天数***/
	public final static Integer DELAY_DAYS=14;
	
}
