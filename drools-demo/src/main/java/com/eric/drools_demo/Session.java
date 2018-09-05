package com.eric.drools_demo;

import java.util.Map;

public class Session {
	private Map<String, Object> attrMap = null;
	
	
	public Session(Map<String, Object> attrMap) {
		this.attrMap = attrMap;
	}
	public Object valueOf(String key) {
		return attrMap.get(key);
	}
}
