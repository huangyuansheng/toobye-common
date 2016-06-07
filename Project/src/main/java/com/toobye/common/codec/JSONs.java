/*
 * Copyright 2015 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2015/07/20.
 * 
 */
package com.toobye.common.codec;

import javax.annotation.Nonnull;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

/**
 * <pre> Json工具.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2015/07/19  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class JSONs {

	private JSONs() { }
	
	private static final ObjectMapper MAPPER = new ObjectMapper();
	private static final ObjectMapper ALLOW_SINGLE_QUOTES_MAPPER;
	private static final ObjectMapper ALLOW_BACKSLASH_ESCAPING_MAPPER;
	static {
		ALLOW_SINGLE_QUOTES_MAPPER = new ObjectMapper();
		ALLOW_BACKSLASH_ESCAPING_MAPPER = new ObjectMapper();
		ALLOW_SINGLE_QUOTES_MAPPER.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		ALLOW_BACKSLASH_ESCAPING_MAPPER.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
	}
	
	/**
	 * <pre> Mapper类型.
	 * 
	 * Modification History:
	 * Date        Author   Version   Action
	 * 2016/03/05  huangys  v1.0      Create
	 * </pre>
	 * 
	 */
	public enum MapperType { NORMAL, ALLOW_SINGLE_QUOTES, ALLOW_BACKSLASH_ESCAPING };
	
	/**
	 * <pre> 获取对应Mapper.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/03/05  huangys  Create
	 * </pre>
	 * 
	 * @param type 类型
	 * @return Mapper
	 */
	public static ObjectMapper getMapper(final MapperType type) {
		switch (type) {
		case NORMAL:
			return MAPPER;
		case ALLOW_SINGLE_QUOTES:
			return ALLOW_SINGLE_QUOTES_MAPPER;
		case ALLOW_BACKSLASH_ESCAPING:
			return ALLOW_BACKSLASH_ESCAPING_MAPPER;
		default:
			throw new RuntimeException("Unknown Type: " + type);
		}
	}
	
	/**
	 * <pre> 转为List.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/02/16  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 元素类型
	 * @param json json字符串
	 * @return 对象
	 */
	public static <T> List<T> toList(@Nonnull final String json) {
		return toList(json, MapperType.NORMAL);
	}
	
	/**
	 * <pre> 转为Map.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/02/16  huangys  Create
	 * </pre>
	 * 
	 * @param <K> Key类型
	 * @param <V> Value类型
	 * @param json json字符串
	 * @return 对象
	 */
	public static <K, V> Map<K, V> toMap(@Nonnull final String json) {
		return toMap(json, MapperType.NORMAL);
	}
	
	/**
	 * <pre> 转为对象.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/02/16  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 对象类型
	 * @param json json字符串
	 * @param valueType 对象类型
	 * @return 对象
	 */
	public static <T> T toObject(@Nonnull final String json, @Nonnull final Class<T> valueType) {
		return toObject(json, valueType, MapperType.NORMAL);
	}
	
	/**
	 * <pre> 转为json字符串.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/02/16  huangys  Create
	 * </pre>
	 * 
	 * @param obj 对象
	 * @return json字符串
	 */
	public static String toJson(@Nonnull final Object obj) {
		return toJson(obj, MapperType.NORMAL);
	}

	/**
	 * <pre> 转为List.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/02/16  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 元素类型
	 * @param json json字符串
	 * @param type 类型
	 * @return 对象
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> toList(@Nonnull final String json, @Nonnull final MapperType type) {
		try {
			return getMapper(type).readValue(json, List.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 转为Map.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/02/16  huangys  Create
	 * </pre>
	 * 
	 * @param <K> Key类型
	 * @param <V> Value类型
	 * @param json json字符串
	 * @param type 类型
	 * @return 对象
	 */
	@SuppressWarnings("unchecked")
	public static <K, V> Map<K, V> toMap(@Nonnull final String json, @Nonnull final MapperType type) {
		try {
			return getMapper(type).readValue(json, Map.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 转为对象.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/02/16  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 对象类型
	 * @param json json字符串
	 * @param valueType 对象类型
	 * @param type 类型
	 * @return 对象
	 */
	public static <T> T toObject(@Nonnull final String json, @Nonnull final Class<T> valueType, @Nonnull final MapperType type) {
		try {
			return getMapper(type).readValue(json, valueType);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 转为json字符串.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/02/16  huangys  Create
	 * </pre>
	 * 
	 * @param obj 对象
	 * @param type 类型
	 * @return json字符串
	 */
	public static String toJson(@Nonnull final Object obj, @Nonnull final MapperType type) {
		try {
			return getMapper(type).writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
