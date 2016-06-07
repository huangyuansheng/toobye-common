/*
 * Copyright 2016 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2016/01/09.
 * 
 */
package com.toobye.common.reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.annotation.Nonnull;

import com.toobye.common.lang.Checks;

/**
 * <pre> 类型.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2016/01/09  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class Types {
	
	private Types() { }
	
	/**
	 * <pre> 是否为基础类型的封装类.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/09  huangys  Create
	 * </pre>
	 * 
	 * @param cls 类
	 * @return 是否
	 */
	@Nonnull
	public static boolean isWrapClass(@Nonnull final Class<?> cls) {
		Checks.nullThrow(cls);
		try {
			return ((Class<?>) cls.getField("TYPE").get(null)).isPrimitive();
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * <pre> 获得泛型参数类型.
	 * 仅对泛型实现的类有效，直接用泛型类创建的对象无效。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/03  huangys  Create
	 * </pre>
	 * 
	 * @param obj 对象
	 * @return 泛型参数类型
	 */
	@Nonnull
	public static Type[] getActualTypeArguments(@Nonnull final Object obj) {
		Checks.nullThrow(obj);
		return ((ParameterizedType) obj.getClass().getGenericSuperclass()).getActualTypeArguments();
	}
	
}
