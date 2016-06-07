/*
 * Copyright 2014 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2014/05/26.
 * 
 */
package com.toobye.common.dbenums;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.toobye.common.lang.Checks;
import com.toobye.common.reflect.Methods;
import com.toobye.common.reflect.Reflections;

/**
 * <pre> 数据库枚举类型-工具类.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2014/05/26  huangys  v1.0      Create
 * </pre>
 * 
 */
@SuppressWarnings("unchecked")
public final class DBEnums {
	
	private DBEnums() { }
	
	@SuppressWarnings("rawtypes")
	private static final Set<Class<? extends DBEnum>> DBENUM_SET;
	private static final Map<String, Map<String, Enum<? extends DBEnum<?>>>> DBENUM_POOL = new HashMap<String, Map<String, Enum<? extends DBEnum<?>>>>();
	
	static {
		// 获取所有数据库枚举类
		DBENUM_SET = Reflections.getSubTypesOf(DBEnum.class.getPackage().getName(), DBEnum.class);
		// 校验显示值和实际保存值是否有重复
		for (@SuppressWarnings("rawtypes") Class<? extends DBEnum> dbEnum : DBENUM_SET) {
			addEnumType((Class<? extends Enum<? extends DBEnum<?>>>) dbEnum);
		}
	}
	
	private static <T extends Enum<? extends DBEnum<?>>> void addEnumType(final Class<T> enumType) {
		Map<String, Enum<? extends DBEnum<?>>> map = new HashMap<String, Enum<? extends DBEnum<?>>>();
		// 通过调用values方法返回枚举清单
		for (Enum<? extends DBEnum<?>> em : (Enum<? extends DBEnum<?>>[]) Methods.invokeMethod(enumType, "values")) {
			Checks.containsThrow(map, em.toString(), "Duplicate enum(" + enumType + ") value<" + em + ">");
			map.put(em.toString(), em);
			if (!em.toString().equals(((DBEnum<?>) em).getValue().toString())) {
				Checks.containsThrow(map, ((DBEnum<?>) em).getValue().toString(), "Duplicate enum(" + enumType + ") value<" + ((DBEnum<?>) em).getValue() + ">");
				map.put(((DBEnum<?>) em).getValue().toString(), em);
			}
		}
		DBENUM_POOL.put(enumType.toString(), map);
	}
	
	/**
	 * <pre> 根据枚举类型、数据库的实际保存值找到对应的枚举值.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/06/06  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 数据库枚举类型
	 * @param enumType 枚举类型
	 * @param value 数据库的实际保存值
	 * @return 枚举值
	 */
	public static <T extends Enum<? extends DBEnum<?>>> T valueOf(final Class<T> enumType, final String value) {
		if (!DBENUM_SET.contains(enumType)) {
			addEnumType(enumType);
		}
		T ret = (T) DBENUM_POOL.get(enumType.toString()).get(value);
		Checks.nullThrow(ret, "Cannot find the Enum Value(" + enumType + ", " + value + ")");
		return ret;
	}
	
	/**
	 * <pre> 通过枚举值获得对应数据库的实际保存值.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/06/06  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 数据库枚举类型
	 * @param em 枚举值
	 * @return 数据库的实际保存值
	 */
	@edu.umd.cs.findbugs.annotations.SuppressFBWarnings("BC_UNCONFIRMED_CAST")
	public static <T> T getValue(final Enum<? extends DBEnum<T>> em) {
		return ((DBEnum<T>) em).getValue();
	}

}
