package com.froad.recon.reconciliation.drools;

import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/*** 规则引擎类 */
public class DroolsRuleEngine {
	private String drlCode;
	KieContainer kContainer = null;

	/*** 初始化drools */
	public void initial() {
		KieServices ks = KieServices.Factory.get();
		kContainer = ks.getKieClasspathContainer();
	}

	/*** 单条执行 */
	public void executeRuleEngine(Object obj) {
		KieSession kSession = kContainer.newKieSession(drlCode);
		if (kSession == null) {
			return;
		}
		try {
			kSession.insert(obj);
			kSession.fireAllRules();
		} finally {
			kSession.dispose();
		}
	}

	/*** 批量执行 */
	public void executeRuleEngineBatch(List objs) {
		KieSession kSession = kContainer.newKieSession(drlCode);
		if (kSession == null) {
			return;
		}
		try {
			for (Object obj : objs) {
				kSession.insert(obj);
				kSession.fireAllRules();
			}
		} finally {
			kSession.dispose();
		}
	}
	
	public String getDrlCode() {
		return drlCode;
	}

	public void setDrlCode(String drlCode) {
		this.drlCode = drlCode;
	}
}
