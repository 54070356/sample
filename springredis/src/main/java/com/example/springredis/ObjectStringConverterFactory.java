package com.example.springredis;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class ObjectStringConverterFactory {
	public static BooleanStringConverter booleanStringConverter = new BooleanStringConverter();
	public static DateStringConverter dateStringConverter = new DateStringConverter();
	public static DoubleStringConverter doubleStringConverter = new DoubleStringConverter();
	public static IntegerStringConverter integerStringConverter = new IntegerStringConverter();
	public static LongStringConverter longStringConverter = new LongStringConverter();
	public static StringStringConverter stringStringConverter = new StringStringConverter();
	public static ShortStringConverter shortStringConverter = new ShortStringConverter();
	public static FloatStringConverter floatStringConverter = new FloatStringConverter();
	
	private static Map<Class<?>, ObjectStringConverter> defaultConverters = new HashMap<Class<?>, ObjectStringConverter>();
	static {
		defaultConverters.put(Boolean.class,booleanStringConverter);
		defaultConverters.put(boolean.class, booleanStringConverter);
		defaultConverters.put(Date.class, dateStringConverter);
		defaultConverters.put(Double.class, doubleStringConverter);
		defaultConverters.put(double.class, doubleStringConverter);
		defaultConverters.put(Integer.class, integerStringConverter);
		defaultConverters.put(int.class, integerStringConverter);
		defaultConverters.put(Long.class, longStringConverter);
		defaultConverters.put(long.class, longStringConverter);
		defaultConverters.put(String.class, stringStringConverter);
		defaultConverters.put(Short.class, shortStringConverter);
		defaultConverters.put(short.class, shortStringConverter);
		defaultConverters.put(Float.class, floatStringConverter);
		defaultConverters.put(float.class, floatStringConverter);
	}
	
	public static Map<Class<?>, ObjectStringConverter>  getDefaultConverters() {
		return defaultConverters;
	}
	
	
	public static ObjectStringConverter getConverter(Class<?> cls) {
		return defaultConverters.get(cls);
	}
	
	
	
	public static abstract class AbstractObectStringConverter<T> implements ObjectStringConverter {
		private static Log logger = LogFactory.getLog(AbstractObectStringConverter .class);
		
		protected abstract Object convertToObject(String value);

		@Override
		public String toString(Object object) {
			return object == null ? null : object.toString();
		}

		@Override
		public Object toObject(String value) {
			try {
				return value == null ? null : convertToObject(value);
			}catch(Exception e) {
				logger.error("Failed to convert string to object.Value = " + value);
				return null;
			}
		}
	}
	
	private static class StringStringConverter extends AbstractObectStringConverter<String> {
		@Override
		protected String convertToObject(String value) {
			return value;
		} 
	}

	private static class BooleanStringConverter extends AbstractObectStringConverter<Boolean> {
		@Override
		protected Boolean convertToObject(String value) {
			return StringUtils.isEmpty(value) ? null : Boolean.parseBoolean(value);
		} 
	}

	private static class IntegerStringConverter extends AbstractObectStringConverter<Integer> {
		@Override
		protected Integer convertToObject(String value) {
			return StringUtils.isEmpty(value) ? null : Integer.parseInt(value);
		}
	}

	private static class DoubleStringConverter extends AbstractObectStringConverter<Double> {
		@Override
		protected Double convertToObject(String value) {
			return StringUtils.isEmpty(value) ? null : Double.parseDouble(value);
		}
	}
	
	private static class LongStringConverter extends AbstractObectStringConverter<Long> {
		@Override
		protected Long convertToObject(String value) {
			return StringUtils.isEmpty(value) ? null : Long.parseLong(value);
		}
	}
	
	private static class ShortStringConverter extends AbstractObectStringConverter<Short> {
		@Override
		protected Short convertToObject(String value) {
			return StringUtils.isEmpty(value) ? null : Short.parseShort(value);
		}
	}
	
	private static class FloatStringConverter extends AbstractObectStringConverter<Float> {
		@Override
		protected Float convertToObject(String value) {
			return StringUtils.isEmpty(value) ? null : Float.parseFloat(value);
		}
	}
	
	private static class DateStringConverter extends AbstractObectStringConverter<Date> {
		@Override
		protected Date convertToObject(String value) {
			return StringUtils.isEmpty(value) ? null : new Date(Long.parseLong(value));
		}
		
		@Override
		public String toString(Object object) {
			return object == null ? null : Long.toString(((Date)object).getTime());
		}
	}

}
