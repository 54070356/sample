package com.example.springredis;

import org.apache.commons.lang3.StringUtils;

public interface ObjectStringConverter {
	public static final char UNIT_SEPARATOR = '\037';
	public static String join(String... items) {
		return StringUtils.join(items, UNIT_SEPARATOR);
	}

	public static String[] split(String str) {
		return StringUtils.split(str, UNIT_SEPARATOR);
	}
	
	public static String objectToString(Object obj) {
		return null;
	}
	
	
	public String toString(Object object);
	public Object toObject(String value);
	
	
}
