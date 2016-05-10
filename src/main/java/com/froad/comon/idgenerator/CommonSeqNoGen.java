package com.froad.comon.idgenerator;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.froad.comon.constant.ConstantSeqName;
import com.froad.comon.constant.ParamCommand;

/**
 * @author huhuabo
 * 不支持多系统同时运行，单独系统可以生成 16位，并发999999个,纯数字
 * 1305121247049314
 * 年月月日日时时分分秒秒XXXX
 * 并追加四位"0-9"的自增字符串.<br/> 
 * 支持年份为2010年-2110年<br/>
 */
public class CommonSeqNoGen {
 
	private final static SimpleDateFormat sf = new SimpleDateFormat("yyMMddHHmmss");
	
	//private static volatile BigDecimal postFix = new BigDecimal(0); // 后缀自增值
	
	
	private static volatile  Map<String, BigDecimal> POST_FIX_MAP=new HashMap<String, BigDecimal>();  //申请单状态
	static {
		POST_FIX_MAP.put(ConstantSeqName.SEQ_S_TRADE_RESULT,new BigDecimal(0));
		POST_FIX_MAP.put(ConstantSeqName.SEQ_S_NO_RECON,new BigDecimal(0));
		POST_FIX_MAP.put(ConstantSeqName.SEQ_S_DELAY,new BigDecimal(0));
		POST_FIX_MAP.put(ConstantSeqName.SEQ_SYSTEM,new BigDecimal(0));
	 }
	
	private final static BigDecimal bentchMark = new BigDecimal(999999); // 最大999999 可以并发999999

	public synchronized static String generateOfSleep(){
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		
		return generate("101");
	}
	
	/**
	 * 业务上传入前缀以示区别
	 * @param prefix
	 * @return
	 */
	final public synchronized static String generate(String prefix)  {
		String dateString = sf.format(new Date());
		
		BigDecimal postFix=POST_FIX_MAP.get(prefix);
		postFix = postFix.add(new BigDecimal(1));
		
		String strPostFix = "";
		
		if(postFix.compareTo(bentchMark) < 0) { // 小于999999
			strPostFix += postFix.toString();
			while(strPostFix.length() < 6) {
				strPostFix = "0" + strPostFix;
			}
		} else if(postFix.compareTo(bentchMark) == 0) { // =999999 - 返回999999
			strPostFix = bentchMark.toString();
			postFix = new BigDecimal(0);
		} else {
			// 不会出现
		}

		POST_FIX_MAP.put(prefix, postFix);
		return ParamCommand.HOST_ID.trim() + prefix + dateString + strPostFix;
	}
 
	
	static final  Set<String> set = new HashSet<String>();
	/**
	 * @author Sean
	 * FroadBullNoGen 测试说明
	 * <p>并发10个线程，间隔10毫秒启动
	 * <p>每个线程间隔10毫秒取一个ID,并加入Set
	 * <p>线程执行结束,打印线程号+Set.Size
	 * <p>正确结果为 size<=10000 <br/>
	 * 为模拟线程并发情况 Set<String> 不是线程安全，可能存在线程资源争强后无法释放
	 */
	public static void main1(String[] args) throws InterruptedException {
		
		long x = System.currentTimeMillis();
		for(int i=0;i<10;i++){
			new Thread(new MyRun("10" + i)).start();
			Thread.sleep(10);
		}
		long y = System.currentTimeMillis();
		
		System.out.println("启动耗时："+(y-x)+"毫秒");
	}
	
	public static void main(String[] args) {
		String dateString = sf.format(new Date());
		System.out.println(dateString);
	}
	
	
	static class MyRun implements Runnable{
		String x = "";
		public MyRun(String as) {
			x = as;
		}
		
		@Override
		public void run() {
			for(int i=0;i<1000;i++){
				String s = CommonSeqNoGen.generate(x);
				System.out.println(s);
				set.add(s);
				
				try {
					Thread.currentThread().sleep(10);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			}
			
			System.out.println(Thread.currentThread().getId() + ":" + set.size());
			
		}
		
	}
	
} 

