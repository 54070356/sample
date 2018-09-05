package com.eric.drools_demo;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		
		// 从工厂中获得KieServices实例
		KieServices kieServices = KieServices.Factory.get();
		kieServices.getCommands();
		// 从KieServices中获得KieContainer实例，其会加载kmodule.xml文件并load规则文件
		KieContainer kieContainer = kieServices.getKieClasspathContainer();
		// 建立KieSession到规则文件的通信管道
		KieSession kSession = kieContainer.newKieSession("ksession1");
		Message message = new Message("Hello World",Message.HELLO);
		 
		// 将实体类插入执行规则
		kSession.insert(message);
		kSession.fireAllRules();
	}
}
