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
public class Demo1 {
	/*
	 * @param args
	 * @throws UnsupportedEncodingException
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		// http://blog.csdn.net/wmj2003/article/details/79076752

		
		KnowledgeBuilder kb = KnowledgeBuilderFactory.newKnowledgeBuilder();
		// 装入规则，可以装入多个
		//kb.add(ResourceFactory.newByteArrayResource(rule.getBytes("utf-8")), ResourceType.DRL);  
		kb.add(ResourceFactory.newClassPathResource("rules1/demo1.drl"), ResourceType.DRL);
		KnowledgeBuilderErrors errors = kb.getErrors();
		for (KnowledgeBuilderError error : errors) {
			System.out.println(error);
		}
		

		InternalKnowledgeBase kBase = KnowledgeBaseFactory.newKnowledgeBase();
		kBase.addPackages(kb.getKnowledgePackages());
		
		KieSession kSession = kBase.newKieSession(); 
//		kSession.addEventListener( new DefaultAgendaEventListener() {
//		    public void afterMatchFired(AfterMatchFiredEvent event) {
//		        super.afterMatchFired( event );
//		        System.out.println( "Rule name = " + event.getMatch().getRule().getName() );
//		    }
//		});
		
		Message message1 = new Message("msg1",Message.HELLO);
		kSession.insert(message1);
		Message message2 = new Message("msg2",Message.HELLO); 
		kSession.insert(message2);
		message2 = new Message("msg2",Message.HELLO); 
		kSession.insert(message2);
//		List<Message> msgs = new ArrayList<>();
//		msgs.add(msg1);
		
		Msg msg1=new Msg("msg1");
		//Msg msg1=new Msg("msg1");
		kSession.insert(msg1);
		
		
		
		
		
		Car car1 = new Car();
		car1.setPrice(100.0);
		kSession.insert(car1);
		Car car2 = new Car();
		car2.setPrice(300.0);
		kSession.insert(car2);
		
		List<Double> numbers = new ArrayList<>();
		numbers.add(1.0);
		numbers.add(3.0);
		kSession.insert(numbers);
		
		List<Double> scores = new ArrayList<>();
		scores.add(10.0);
		scores.add(30.0);
		kSession.insert(numbers);
		Map<String, Object> attrMap = new HashMap<>();
		attrMap.put("age", 10);
		attrMap.put("gender", "male");
		attrMap.put("married", false);
		attrMap.put("scores", scores);

		Session session = new Session(attrMap);
		kSession.insert(session);
		
	//	kSession.insert(msg1);
		System.out.println("-------------");
		int count = kSession.fireAllRules();
		System.out.println("fire all rules : " + count);
		 
 
	}
}
