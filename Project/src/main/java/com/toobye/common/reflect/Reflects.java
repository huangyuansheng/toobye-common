/*
 * Copyright 2016 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2016/04/14.
 * 
 */
package com.toobye.common.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.toobye.common.lang.Checks;

/**
 * <pre> 反射工具类.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2016/04/14  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class Reflects {
	
	private Reflects() { }
	
	/**
	 * <pre> 创建实例.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/04/14  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 类型
	 * @param className 类名
	 * @return 实例
	 */
	@SuppressWarnings("unchecked")
	@Nonnull
	public static <T> T newInstance(@Nonnull final String className) {
		return newInstance((Class<T>) getClass(className));
	}
	
	/**
	 * <pre> 创建实例.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/04/14  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 类型
	 * @param cls 类
	 * @return 实例
	 */
	@Nonnull
	public static <T> T newInstance(@Nonnull final Class<T> cls) {
		return newInstance(cls, null, null);
	}

	/**
	 * <pre> 创建实例.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/04/14  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 类型
	 * @param className 类名
	 * @param paramsType 参数类型
	 * @param params 参数
	 * @return 实例
	 */
	@SuppressWarnings("unchecked")
	@Nonnull
	public static <T> T newInstance(@Nonnull final String className, @Nullable final Class<?>[] paramsType, @Nullable final Object[] params) {
		return newInstance((Class<T>) getClass(className), paramsType, params);
	}

	/**
	 * <pre> 创建实例.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/04/14  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 类型
	 * @param cls 类
	 * @param paramsType 参数类型
	 * @param params 参数
	 * @return 实例
	 */
	@Nonnull
	public static <T> T newInstance(@Nonnull final Class<T> cls, @Nullable final Class<?>[] paramsType, @Nullable final Object[] params) {
		Checks.nullThrow(cls);
		Checks.notSameSizeThrow(paramsType, params);
		
		try {
			if (paramsType == null) {
				Constructor<T> c = cls.getDeclaredConstructor();
				return c.newInstance();
			} else {
				Constructor<T> c = cls.getDeclaredConstructor(paramsType);
				return c.newInstance(params);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 获取类.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/04/14  huangys  Create
	 * </pre>
	 * 
	 * @param className 类名
	 * @return 类
	 */
	@Nonnull
	public static Class<?> getClass(@Nonnull final String className) {
		try {
			return Class.forName(className);   
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 创建已参数化类型的指定参数实例.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/04/14  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 类型
	 * @param className 类名
	 * @param index 参数位置
	 * @return 实例
	 */
	@Nonnull
	public static <T> T newInstanceGenericArgument(@Nonnull final String className, @Nonnull final int index) {
		return newInstanceGenericArgument(getClass(className), index);
	}

	/**
	 * <pre> 创建已参数化类型的指定参数实例.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/04/14  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 类型
	 * @param cls 类
	 * @param index 参数位置
	 * @return 实例
	 */
	@Nonnull
	public static <T> T newInstanceGenericArgument(@Nonnull final Class<?> cls, @Nonnull final int index) {
		return newInstanceGenericArgument(cls, index, null, null);
	}

	/**
	 * <pre> 创建已参数化类型的指定参数实例.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/04/14  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 类型
	 * @param className 类名
	 * @param index 参数位置
	 * @param paramsType 参数类型
	 * @param params 参数
	 * @return 实例
	 */
	@Nonnull
	public static <T> T newInstanceGenericArgument(@Nonnull final String className, @Nonnull final int index, @Nullable final Class<?>[] paramsType, @Nullable final Object[] params) {
		return newInstanceGenericArgument(getClass(className), index, paramsType, params);
	}

	/**
	 * <pre> 创建已参数化类型的指定参数实例.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/04/14  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 类型
	 * @param cls 类
	 * @param index 参数位置
	 * @param paramsType 参数类型
	 * @param params 参数
	 * @return 实例
	 */
	@Nonnull
	public static <T> T newInstanceGenericArgument(@Nonnull final Class<?> cls, @Nonnull final int index, @Nullable final Class<?>[] paramsType, @Nullable final Object[] params) {
		Checks.nullThrow(cls);
		Checks.notSameSizeThrow(paramsType, params);
		
		try {
			@SuppressWarnings("unchecked")
			Class<T> paramClass = (Class<T>) getGenericArgumentType(cls, index);
			if (paramsType == null) {
				Constructor<T> c = paramClass.getDeclaredConstructor();
				return c.newInstance();
			} else {
				Constructor<T> c = paramClass.getDeclaredConstructor(paramsType);
				return c.newInstance(params);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 获得已参数化类型的指定参数类型.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/04/14  huangys  Create
	 * </pre>
	 * 
	 * @param cls 类
	 * @param index 参数位置
	 * @return 参数类型
	 */
	public static Class<?> getGenericArgumentType(@Nonnull final Class<?> cls, @Nonnull final int index) {
		Checks.nullThrow(cls);
		return (Class<?>) ((ParameterizedType) cls.getGenericSuperclass()).getActualTypeArguments()[index];
	}
	
}
