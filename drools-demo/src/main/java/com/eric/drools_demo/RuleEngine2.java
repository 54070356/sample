package com.eric.drools_demo;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.drools.core.event.DefaultAgendaEventListener;
import org.drools.core.impl.InternalKnowledgeBase;
import org.drools.core.impl.KnowledgeBaseFactory;
import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.AgendaFilter;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderError;
import org.kie.internal.builder.KnowledgeBuilderErrors;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;

/**
 * Hello world!
 *
 */
public class RuleEngine2 {
	/**
	 * 1. get values from redis - ok 
	 * 2. execute some rules - ok 
	 * 3. support trace - ok
	 * 4. add/update/remove rules dynamically - ok
	 * 5. performance
	 * 6. get rule result
	 * 
	 * @param args
	 * @throws UnsupportedEncodingException
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		// http://blog.csdn.net/wmj2003/article/details/79076752

		Map<String, Object> attrMap = new HashMap<>();
		attrMap.put("age", 10);
		attrMap.put("gender", "male");
		attrMap.put("married", false);

		Session session = new Session(attrMap);

		KnowledgeBuilder kb = KnowledgeBuilderFactory.newKnowledgeBuilder();
		// 装入规则，可以装入多个
		//kb.add(ResourceFactory.newByteArrayResource(rule.getBytes("utf-8")), ResourceType.DRL);  
		kb.add(ResourceFactory.newClassPathResource("rules1/session.drl"), ResourceType.DRL);
		KnowledgeBuilderErrors errors = kb.getErrors();
		for (KnowledgeBuilderError error : errors) {
			System.out.println(error);
		}
		

		InternalKnowledgeBase kBase = KnowledgeBaseFactory.newKnowledgeBase();
		kBase.addPackages(kb.getKnowledgePackages());
		
		KieSession kSession = kBase.newKieSession(); 
		kSession.addEventListener( new DefaultAgendaEventListener() {
		    public void afterMatchFired(AfterMatchFiredEvent event) {
		        super.afterMatchFired( event );
		        System.out.println( "Rule name = " + event.getMatch().getRule().getName() );
		    }
		});
		
		// 将实体类插入执行规则
		FactHandle sessionHandle = kSession.insert(session);

		System.out.println("-------------");
		System.out.println("execute rule age only");
		AgendaFilter ageFilter = new AgendaFilterByRuleName("age");
		int count = kSession.fireAllRules(ageFilter);
		System.out.println("fire all rules 10: " + count);
		System.out.println("-------------");

		System.out.println("rules don't run if fact is not changed");
		AgendaFilter genderFilter = new AgendaFilterByRuleName("gender");
		count = kSession.fireAllRules(genderFilter);
		System.out.println("fire all rules 20: " + count);
		System.out.println("-------------");

		System.out.println("update fact and run rule gender ");
		kSession.delete(sessionHandle);
		sessionHandle = kSession.insert(session);
		genderFilter = new AgendaFilterByRuleName("gender");
		count = kSession.fireAllRules(genderFilter);
		System.out.println("fire all rules 30: " + count);
		System.out.println("-------------");

		System.out.println("remove rule age and run all rules");
		kBase.removeRule("eric.rules", "age");
		kSession.delete(sessionHandle);
		sessionHandle = kSession.insert(session);
		count = kSession.fireAllRules();
		System.out.println("fire all rules 40: " + count);
		System.out.println("-------------");

		System.out.println("fire all rules using new session");
		KieSession kSession2 = kBase.newKieSession();
		kSession2.insert(session);
		count = kSession2.fireAllRules();
		kSession2.dispose();
		System.out.println("fire all rules 50: " + count);
		System.out.println("-------------");

		System.out.println("run rule messageName");
		Msg msg = new Msg("msg1");
		kSession.insert(msg);
		count = kSession.fireAllRules();
		System.out.println("fire all rules 60: " + count);
		System.out.println("-------------");
		
		
		System.out.println("run rule messageName");
		List<Integer> numbers = new ArrayList<>();
		numbers.add(1);
		numbers.add(2);
		kSession.insert(numbers);
		count = kSession.fireAllRules();
		System.out.println("fire all rules 70: " + count);
		System.out.println("-------------");

		
		kb = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kb.add(ResourceFactory.newClassPathResource("rules1/rule2.drl"), ResourceType.DRL);
		errors = kb.getErrors();
		for (KnowledgeBuilderError error : errors) {
			System.out.println(error);
		}
		
		System.out.println("run rule msg2");
		Msg msg2 = new Msg("msg2");
		kSession.insert(msg2);
		kBase.addPackages(kb.getKnowledgePackages());
		count = kSession.fireAllRules();
		System.out.println("fire all rules 70: " + count);
		System.out.println("-------------");
	}
}
