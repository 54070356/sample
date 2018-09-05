package com.eric.drools_demo;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.drools.core.impl.InternalKnowledgeBase;
import org.drools.core.impl.KnowledgeBaseFactory;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.AgendaFilter;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.api.runtime.rule.Match;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderError;
import org.kie.internal.builder.KnowledgeBuilderErrors;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
 

/**
 * Hello world!
 *
 */
public class RuleEngine {
	/**
	 * 1. get values from redis - ok
	 * 2. execute some rules - ok
	 * 3. support trace - 
	 * 4. add/update/remove rules dynamically
	 * 5. performance
	 * @param args
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		//http://blog.csdn.net/wmj2003/article/details/79076752
		
		Map<String, Object> attrMap0 = new HashMap<>();
		
		Map<String, Object> attrMap = new HashMap<>();
		attrMap.put("age", 10);
		attrMap.put("gender", "male");
		attrMap.put("married", false);
		
		Session session = new Session(attrMap);
		
		// 从工厂中获得KieServices实例
		KieServices kieServices = KieServices.Factory.get();
		// 从KieServices中获得KieContainer实例，其会加载kmodule.xml文件并load规则文件
		
		KieContainer kieContainer = kieServices.getKieClasspathContainer();
		
		KieBase kieBase = kieContainer.getKieBase("rules");
		
	
		// 建立KieSession到规则文件的通信管道
		KieSession kSession = kieContainer.newKieSession("ksession1");
		// 将实体类插入执行规则
		FactHandle sessionHandle = kSession.insert(session);
		
		System.out.println("-------------");
		System.out.println("execute rule age only");
		AgendaFilter ageFilter = new AgendaFilterByRuleName("age");
		int count = kSession.fireAllRules(ageFilter);
		System.out.println("fire all rules 10: " + count);
		System.out.println("-------------");
		

		System.out.println("rules don't run if fact is not changed");
		count = kSession.fireAllRules();
		System.out.println("fire all rules 20: " + count);
		System.out.println("-------------");
		
		
		System.out.println("update fact and run rule gender ");
		kSession.delete(sessionHandle);
		sessionHandle = kSession.insert(session);
		AgendaFilter genderFilter = new AgendaFilterByRuleName("gender");
		count = kSession.fireAllRules(genderFilter);
		System.out.println("fire all rules 30: " + count);
		System.out.println("-------------");
		
		System.out.println("remove rule age and run all rules");
		kieBase.removeRule("eric.rules", "age");
		kSession.delete(sessionHandle);
		sessionHandle = kSession.insert(session);
		count = kSession.fireAllRules();
		System.out.println("fire all rules 40: " + count);
		System.out.println("-------------");
		
		System.out.println("fire all rules using new session");
		KieSession kSessionNew = kieContainer.newKieSession("ksession1");
		kSessionNew.insert(session);
		count = kSessionNew.fireAllRules();
		System.out.println("fire all rules 50: " + count);
		System.out.println("-------------");
		
		System.out.println("run rule messageName");
		Msg msg = new Msg("msg1");
		kSession.insert(msg);
		count = kSession.fireAllRules();
		System.out.println("fire all rules 60: " + count);
		System.out.println("-------------");
		
		String rule = "package eric.rules\r\n";  
        rule += "import com.fei.drools.Session;\r\n";  
        rule += "rule \"age2\"\r\n";  
        rule += "\twhen\r\n";  
        rule += "Session(getAttributeValue(\"age\") != 10)";  
        rule += "\tthen\r\n";  
        rule += "\t\tSystem.out.println(\"age!=10\" );\r\n";  
        rule += "end\r\n";  
        KnowledgeBuilder kb = KnowledgeBuilderFactory.newKnowledgeBuilder();  
        //装入规则，可以装入多个  
        kb.add(ResourceFactory.newByteArrayResource(rule.getBytes("utf-8")), ResourceType.DRL);
        KnowledgeBuilderErrors errors = kb.getErrors();  
        for (KnowledgeBuilderError error : errors) {  
            System.out.println(error);  
        }  
       // kieBase.getk
        
        InternalKnowledgeBase kBase = KnowledgeBaseFactory.newKnowledgeBase();  
        kBase.addPackages(kb.getKnowledgePackages());  
	}
}

class AgendaFilterByRuleName implements AgendaFilter {
	private Set<String> names = new HashSet<>();
	
	public AgendaFilterByRuleName(String... names) {
		for(String name:names) {
			this.names.add(name);
		}
	}
	
	@Override
	public boolean accept(Match match) {
		return names.contains(match.getRule().getName());
	}
	
}
