package com.eric.drools_demo;

import java.util.List;

public class Functions {
	public static boolean hello() {
		System.out.println("hello");
		return true;
	}
	
	public static double avg(Object values) {
		List<Object> valueList = (List<Object>)values;
		double sum = 0;
		for(Object value : valueList) {
			if(value instanceof Number) {
				sum += ((Number) value).doubleValue();
			}else {
				return 0;
			}
		}
		
		return sum/valueList.size();
	}

}
