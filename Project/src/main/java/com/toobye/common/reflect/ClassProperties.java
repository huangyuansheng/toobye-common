/*
 * Copyright 2016 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2016/01/05.
 * 
 */
package com.toobye.common.reflect;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nonnull;

import org.apache.commons.beanutils.PropertyUtils;

import com.toobye.common.lang.Checks;

/**
 * <pre> 类属性.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2016/01/05  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class ClassProperties {
	
	private static final Map<String, ClassProperties> POOL = new HashMap<>();
	
	private Map<String, PropertyDescriptor> properties;
	private Map<String, Field> publicProperties;
	private Map<String, Object> smartProperties;
	
	/**
	 * <pre> 解析类属性信息.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/05  huangys  Create
	 * </pre>
	 * 
	 * @param clsName 类名
	 * @return 类属性信息
	 */
	public static ClassProperties parse(@Nonnull final String clsName) {
		Checks.nullThrow(clsName);
		try {
			return parse(Class.forName(clsName));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 解析类属性信息.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/05  huangys  Create
	 * </pre>
	 * 
	 * @param cls 类
	 * @return 类属性信息
	 */
	public static ClassProperties parse(@Nonnull final Class<?> cls) {
		Checks.nullThrow(cls);
		String clsName = cls.getName();
		if (POOL.containsKey(clsName)) {
			return POOL.get(clsName);
		} else {
			ClassProperties cp = new ClassProperties(cls);
			POOL.put(clsName, cp);
			return cp;
		}
	}
	
	private ClassProperties(@Nonnull final Class<?> cls) {
		// get/set属性
		properties = new HashMap<>();
		for (PropertyDescriptor pd : PropertyUtils.getPropertyDescriptors(cls)) {
			properties.put(pd.getName(), pd);
		}
		properties.remove("class");
		// public属性
		publicProperties = getPublic(cls);
		// get/set[优先] & public
		smartProperties = new HashMap<>();
		smartProperties.putAll(properties);
		for (Entry<String, Field> entry : publicProperties.entrySet()) {
			if (!smartProperties.containsKey(entry.getKey())) {
				smartProperties.put(entry.getKey(), entry.getValue());
			}
		}
	}
	
	/**
	 * <pre> 获得公共属性信息.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/18  huangys  Create
	 * </pre>
	 * 
	 * @param cls 类
	 * @return 公共属性信息
	 */
	@Nonnull
	private static Map<String, Field> getPublic(@Nonnull final Class<?> cls) {
		try {
			Map<String, Field> ret = new HashMap<>();
			for (Field field : cls.getFields()) {
				int modifiers = field.getModifiers();
				if (Modifier.isPublic(modifiers) && !Modifier.isStatic(modifiers)) {
					ret.put(field.getName(), field);
				}
			}
			return ret;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 获得属性（get/set）.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/05  huangys  Create
	 * </pre>
	 * 
	 * @return 属性集合
	 */
	@Nonnull
	public Map<String, PropertyDescriptor> get() {
		return properties;
	}

	/**
	 * <pre> 获得属性（public）.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/05  huangys  Create
	 * </pre>
	 * 
	 * @return 属性集合
	 */
	@Nonnull
	public Map<String, Field> getPublic() {
		return publicProperties;
	}

	/**
	 * <pre> 获得属性（get/set[优先] & public）.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/05  huangys  Create
	 * </pre>
	 * 
	 * @return 属性集合
	 */
	@Nonnull
	public Map<String, Object> getSmart() {
		return smartProperties;
	}

}
