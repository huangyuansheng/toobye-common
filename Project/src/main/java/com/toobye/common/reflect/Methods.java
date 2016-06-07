/*
 * Copyright 2014 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2014/03/01.
 * 
 */
package com.toobye.common.reflect;

import java.lang.reflect.Method;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.toobye.common.lang.Checks;
import com.toobye.common.string.StringArray;

/**
 * <pre> 方法工具类.
 * 
 * Exact需准确匹配方法和参数类型。
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2014/03/01  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class Methods {
	
	private Methods() { }
	
	/**
	 * <pre> 其他方法.
	 * 
	 * Modification History:
	 * Date        Author   Version   Action
	 * 2014/08/07  huangys  v1.0      Create
	 * </pre>
	 * 
	 */
	private static class INSTANCE extends org.apache.commons.beanutils.MethodUtils { };
	
	/**
	 * <pre> 调用方法/静态方法（无参）.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/16  huangys  Create
	 * </pre>
	 * 
	 * @param objOrClass 对象实例（方法）/类（静态方法）
	 * @param methodName 方法名称
	 * @return 方法返回值
	 */
	public static Object invokeMethod(final Object objOrClass, final String methodName) {
		return invokeMethod(objOrClass, methodName, null);
	}
	
	/**
	 * <pre> 调用方法/静态方法（单个参数）.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/16  huangys  Create
	 * </pre>
	 * 
	 * @param objOrClass 对象实例（方法）/类（静态方法）
	 * @param methodName 方法名称
	 * @param param 参数值
	 * @return 方法返回值
	 */
	public static Object invokeMethod(final Object objOrClass, final String methodName, final Object param) {
		return invokeMethod(objOrClass, methodName, param == null ? null : new Object[]{param});
	}
	
	/**
	 * <pre> 调用方法/静态方法（参数集合）.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/16  huangys  Create
	 * </pre>
	 * 
	 * @param objOrClass 对象实例（方法）/类（静态方法）
	 * @param methodName 方法名称
	 * @param params 参数值集合
	 * @return 方法返回值
	 */
	@Nullable
	public static Object invokeMethod(@Nonnull final Object objOrClass, @Nonnull final String methodName, @Nullable final Object[] params) {
		return invokeInvisibleMethod(objOrClass, methodName, params, null);
	}
	
	/**
	 * <pre> 调用方法/静态方法（指定参数类型集合）.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/16  huangys  Create
	 * </pre>
	 * 
	 * @param objOrClass 对象实例（方法）/类（静态方法）
	 * @param methodName 方法名称
	 * @param params 参数值集合
	 * @param paramTypes 参数类型集合
	 * @return 方法返回值
	 */
	@Nullable
	public static Object invokeMethod(@Nonnull final Object objOrClass, @Nonnull final String methodName, @Nullable final Object[] params, @Nullable final Class<?>[] paramTypes) {
		Checks.nullThrow(objOrClass);
		Checks.nullThrow(methodName);
		try {
			if (objOrClass instanceof Class<?>) {
				return INSTANCE.invokeStaticMethod((Class<?>) objOrClass, methodName, params, paramTypes);
			} else {
				return INSTANCE.invokeMethod(objOrClass, methodName, params, paramTypes);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 调用方法/静态方法（无参），可以用于调用可不见方法.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/16  huangys  Create
	 * </pre>
	 * 
	 * @param objOrClass 对象实例（方法）/类（静态方法）
	 * @param methodName 方法名称
	 * @return 方法返回值
	 */
	@Nullable
	public static Object invokeInvisibleMethod(@Nonnull final Object objOrClass, @Nonnull final String methodName) {
		return invokeInvisibleMethod(objOrClass, methodName, null, null);
	}
	
	/**
	 * <pre> 调用方法/静态方法（单个参数），可以用于调用可不见方法.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/16  huangys  Create
	 * </pre>
	 * 
	 * @param objOrClass 对象实例（方法）/类（静态方法）
	 * @param methodName 方法名称
	 * @param param 参数值
	 * @return 方法返回值
	 */
	@Nullable
	public static Object invokeInvisibleMethod(@Nonnull final Object objOrClass, @Nonnull final String methodName, @Nullable final Object param) {
		if (param == null) {
			return invokeInvisibleMethod(objOrClass, methodName, null, null);
		} else {
			return invokeInvisibleMethod(objOrClass, methodName, new Object[]{param}, new Class<?>[]{param.getClass()});
		}
	}
	
	/**
	 * <pre> 调用方法/静态方法（参数集合），可以用于调用可不见方法.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/16  huangys  Create
	 * </pre>
	 * 
	 * @param objOrClass 对象实例（方法）/类（静态方法）
	 * @param methodName 方法名称
	 * @param params 参数值集合
	 * @return 方法返回值
	 */
	@Nullable
	public static Object invokeInvisibleMethod(@Nonnull final Object objOrClass, @Nonnull final String methodName, @Nullable final Object[] params) {
		if (params == null) {
			return invokeInvisibleMethod(objOrClass, methodName, null, null);
        } else {
            int len = params.length;
            Class<?>[] paramTypes = new Class<?>[len];
            for (int i = 0; i < len; i++) {
            	paramTypes[i] = params[i].getClass();
            }
    		return invokeInvisibleMethod(objOrClass, methodName, params, paramTypes);
		}
	}
	
	/**
	 * <pre> 调用方法/静态方法（指定参数类型集合），可以用于调用可不见方法.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/16  huangys  Create
	 * </pre>
	 * 
	 * @param objOrClass 对象实例（方法）/类（静态方法）
	 * @param methodName 方法名称
	 * @param params 参数值集合
	 * @param paramTypes 参数类型集合
	 * @return 方法返回值
	 */
	@Nullable
	public static Object invokeInvisibleMethod(@Nonnull final Object objOrClass, @Nonnull final String methodName, @Nullable final Object[] params, @Nullable final Class<?>[] paramTypes) {
		Checks.nullThrow(objOrClass);
		Checks.nullThrow(methodName);
		try {
			if (objOrClass instanceof Class<?>) {
				Method method = getMethod((Class<?>) objOrClass, methodName, paramTypes);
				if (!method.isAccessible()) {
					method.setAccessible(true);
				}
				return method.invoke(null, params);
			} else {
				Method method = getMethod(objOrClass.getClass(), methodName, paramTypes);
				if (!method.isAccessible()) {
					method.setAccessible(true);
				}
				return method.invoke(objOrClass, params);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 递归方式查找方法.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/16  huangys  Create
	 * </pre>
	 * 
	 * @param cls
	 * @param methodName
	 * @param paramTypes
	 * @return
	 * @throws Exception
	 */
	@Nonnull
	private static Method getMethod(@Nonnull final Class<?> cls, @Nonnull final String methodName, @Nullable final Class<?>[] paramTypes) {
		try {
			// 返回自身类文件中声明的所有方法
			return cls.getDeclaredMethod(methodName, paramTypes);
		} catch (NoSuchMethodException e) {
			try {
				// 返回类的所有公用方法，并包含其继承类的公用方法
				return cls.getMethod(methodName, paramTypes);
			} catch (NoSuchMethodException ex) {
				Class<?> superCls = cls.getSuperclass();
				Checks.nullThrow(superCls, "Method(" + methodName + ", " + StringArray.join(paramTypes, ",") + ") not found.");
				return getMethod(superCls, methodName, paramTypes);
			}
		}
	}

}
