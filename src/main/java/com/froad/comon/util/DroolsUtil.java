package com.froad.comon.util;

import java.util.Properties;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.froad.recon.reconciliation.model.STradeResult;

/**
 * <pre>
 *  kie 执行规则文件 工具类
 * </pre>
 *
 * @author xueyunlong
 * @create 2015年6月17日 下午4:07:51
 * @modify
 * @since   JDK1.6
 */
public class DroolsUtil {

	/**
	 * 执行规则文件
	 * @param drlcode 执行规则kiesession
	 * @param objectbean 封装bean类
	 */
	public static void runRule(String drlcode,Object objectbean){
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession kSession = kContainer.newKieSession(drlcode);
		kSession.insert(objectbean);
		kSession.fireAllRules();
		kSession.dispose();
	}
	
	public static void main(String[] args) {
		 Properties props = new Properties(); 
        props.setProperty("drools.dialect.java.compiler", "JANINO");  
		STradeResult sTradeResult=new STradeResult();
		sTradeResult.setFrontCode("0000");
		runRule("session-result", sTradeResult);
		System.out.println(sTradeResult.getTableName());
	}
}
