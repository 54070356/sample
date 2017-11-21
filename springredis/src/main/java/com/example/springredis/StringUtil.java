
package com.example.springredis;
import org.apache.commons.lang3.StringUtils;

public class StringUtil {
	public static final char UNIT_SEPARATOR = '\037';
	
	public static String join(String[] items) {
		return StringUtils.join(items, UNIT_SEPARATOR);
	}

	public static String[] split(String str) {
		return StringUtils.split(str, UNIT_SEPARATOR);
	}
	
	public static String[] split(String str, char  separator) {
		return StringUtils.split(str, separator);
	}
	
	public static Object toObject(Class<?> cls, String str) {
		return null;
	}
}
