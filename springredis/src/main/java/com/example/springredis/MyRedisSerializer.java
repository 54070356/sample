package com.example.springredis;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

public class MyRedisSerializer<T> implements RedisSerializer<T> {
	private Class<T> type;
	private Field[] fields;

	public MyRedisSerializer(Class<T> type) {
		this.type = type;
		this.fields = type.getDeclaredFields();
	}

	@Override
	public byte[] serialize(T t) throws SerializationException {
		Class<?> cls  = t.getClass();
		if (t == null) {
			return null;
		}
		try {
			StringBuilder builder = new StringBuilder();
			for (Field field : fields) {
				field.setAccessible(true);
				Object obj = field.get(t);
				ObjectStringConverter converter = ObjectStringConverterFactory.getConverter(field.getType());
				if (converter != null) {
					builder.append(converter.toString(obj));
					builder.append(ObjectStringConverter.UNIT_SEPARATOR);
				}
			}
			return builder.toString().getBytes();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public T deserialize(byte[] bytes) throws SerializationException {
		if (bytes == null) {
			return null;
		}
		try {
			T obj = type.newInstance();
			String str = new String(bytes, StandardCharsets.UTF_8);
			String[] values = StringUtil.split(str);
			int i = 0;
			for (Field field : fields) {
				ObjectStringConverter converter = ObjectStringConverterFactory.getConverter(field.getType());
				if (converter != null) {
					field.set(obj, converter.toObject(values[i++]));
				}
			}

			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
