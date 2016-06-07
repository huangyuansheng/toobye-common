/*
 * Copyright 2015 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2015/12/07.
 * 
 */
package com.toobye.common.lang;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.toobye.common.collection.Arrays;
import com.toobye.common.io.Files;

/**
 * <pre> 校验工具类.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2015/12/07  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class Checks {

	private Checks() { }
	
	/**
	 * <pre> 抛出校验失败异常.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/14  huangys  Create
	 * </pre>
	 * 
	 * @param msg 消息
	 */
	public static void throwException(@Nullable final String msg) {
		if (msg == null) {
			throw new CheckException();
		} else {
			throw new CheckException(msg);
		}
	}
	
	/**
	 * <pre> 条件满足时抛出异常.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/01  huangys  Create
	 * </pre>
	 * 
	 * @param condition 条件
	 */
	public static void matchThrow(@Nonnull final boolean condition) {
		matchThrow(condition, null);
	}
	
	/**
	 * <pre> 条件不满足时抛出异常.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/01  huangys  Create
	 * </pre>
	 * 
	 * @param condition 条件
	 */
	public static void notMatchThrow(@Nonnull final boolean condition) {
		notMatchThrow(condition, null);
	}
	
	/**
	 * <pre> 条件满足时抛出异常.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/01  huangys  Create
	 * </pre>
	 * 
	 * @param condition 条件
	 * @param msg 异常消息
	 */
	public static void matchThrow(@Nonnull final boolean condition, @Nullable final String msg) {
		if (condition) {
			throwException(msg == null ? "Condition match!" : msg);
		}
	}
	
	/**
	 * <pre> 条件不满足时抛出异常.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/01  huangys  Create
	 * </pre>
	 * 
	 * @param condition 条件
	 * @param msg 异常消息
	 */
	public static void notMatchThrow(@Nonnull final boolean condition, @Nullable final String msg) {
		matchThrow(!condition, msg == null ? "Condition not match!" : msg);
	}
	
	/**
	 * <pre> 对象为Null则抛出异常.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/01  huangys  Create
	 * </pre>
	 * 
	 * @param obj 被检测对象
	 */
  	public static void nullThrow(@Nullable final Object obj) {
  		nullThrow(obj, null);
	}
	
	/**
	 * <pre> 对象不为Null则抛出异常.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/01  huangys  Create
	 * </pre>
	 * 
	 * @param obj 被检测对象
	 */
	public static void notNullThrow(@Nullable final Object obj) {
		notNullThrow(obj, null);
	}
	
	/**
	 * <pre> 对象为Null则抛出异常.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/01  huangys  Create
	 * </pre>
	 * 
	 * @param obj 被检测对象
	 * @param msg 异常消息
	 */
  	public static void nullThrow(@Nullable final Object obj, @Nullable final String msg) {
		matchThrow(obj == null, msg == null ? "Paramter cannot be null!" : msg);
	}
	
	/**
	 * <pre> 对象不为Null则抛出异常.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/01  huangys  Create
	 * </pre>
	 * 
	 * @param obj 被检测对象
	 * @param msg 异常消息
	 */
	public static void notNullThrow(@Nullable final Object obj, @Nullable final String msg) {
		matchThrow(obj != null, msg == null ? "Paramter must be null(fact: " + obj + ")!" : msg);
	}

	/**
	 * <pre> 集合中已存在指定元素则抛出异常.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/01  huangys  Create
	 * </pre>
	 * 
	 * @param c 集合
	 * @param obj 元素
	 */
	public static void containsThrow(@Nonnull final Collection<?> c, @Nullable final Object obj) {
		containsThrow(c, obj, null);
	}
	
	/**
	 * <pre> 集合中不存在指定元素则抛出异常.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/01  huangys  Create
	 * </pre>
	 * 
	 * @param c 集合
	 * @param obj 元素
	 */
	public static void notContainsThrow(@Nonnull final Collection<?> c, @Nullable final Object obj) {
		notContainsThrow(c, obj, null);
	}
	
	/**
	 * <pre> 集合中已存在指定元素则抛出异常.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/01  huangys  Create
	 * </pre>
	 * 
	 * @param c 集合
	 * @param obj 元素
	 * @param msg 异常消息
	 */
	public static void containsThrow(@Nonnull final Collection<?> c, @Nullable final Object obj, @Nullable final String msg) {
		nullThrow(c);
		matchThrow(c.contains(obj), msg == null ? "Duplicate(" + obj + ")!" : msg);
	}
	
	/**
	 * <pre> 集合中不存在指定元素则抛出异常.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/01  huangys  Create
	 * </pre>
	 * 
	 * @param c 集合
	 * @param obj 元素
	 * @param msg 异常消息
	 */
	public static void notContainsThrow(@Nonnull final Collection<?> c, @Nullable final Object obj, @Nullable final String msg) {
		nullThrow(c);
		matchThrow(!c.contains(obj), msg == null ? "Not contains(" + obj + ")!" : msg);
	}
	
	/**
	 * <pre> Map中已存在指定元素则抛出异常.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/01  huangys  Create
	 * </pre>
	 * 
	 * @param m Map
	 * @param obj 元素
	 */
	public static void containsThrow(@Nonnull final Map<?, ?> m, @Nullable final Object obj) {
		containsThrow(m, obj, null);
	}
	
	/**
	 * <pre> Map中不存在指定元素则抛出异常.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/01  huangys  Create
	 * </pre>
	 * 
	 * @param m Map
	 * @param obj 元素
	 */
	public static void notContainsThrow(@Nonnull final Map<?, ?> m, @Nullable final Object obj) {
		notContainsThrow(m, obj, null);
	}
	
	/**
	 * <pre> Map中存在指定元素则抛出异常.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/01  huangys  Create
	 * </pre>
	 * 
	 * @param m Map
	 * @param obj 元素
	 * @param msg 异常消息
	 */
	public static void containsThrow(@Nonnull final Map<?, ?> m, @Nullable final Object obj, @Nullable final String msg) {
		nullThrow(m);
		matchThrow(m.containsKey(obj), msg == null ? "Duplicate(Key: " + obj + ")!" : msg);
	}
	
	/**
	 * <pre> Map中不存在指定元素则抛出异常.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/01  huangys  Create
	 * </pre>
	 * 
	 * @param m Map
	 * @param obj 元素
	 * @param msg 异常消息
	 */
	public static void notContainsThrow(@Nonnull final Map<?, ?> m, @Nullable final Object obj, @Nullable final String msg) {
		nullThrow(m);
		matchThrow(!m.containsKey(obj), msg == null ? "Not contains(Key: " + obj + ")!" : msg);
	}
	
	/**
	 * <pre> 对象为空则抛出异常.
	 * 
	 * 为空的条件：
	 *   null
	 *   char/byte/short/int/long/float/double == 0
	 *   String/Collection/Map => isEmpty
	 *   Array => length == 0
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/01  huangys  Create
	 * </pre>
	 * 
	 * @param obj 被检测对象
	 */
	public static void emptyThrow(@Nullable final Object obj) {
		emptyThrow(obj, null);
	}
	
	/**
	 * <pre> 对象不为空则抛出异常.
	 * 
	 * 为空的条件：
	 *   null
	 *   char/byte/short/int/long/float/double == 0
	 *   String/Collection/Map => isEmpty
	 *   Array => length == 0
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/01  huangys  Create
	 * </pre>
	 * 
	 * @param obj 被检测对象
	 */
	public static void notEmptyThrow(@Nullable final Object obj) {
		notEmptyThrow(obj, null);
	}
	
	/**
	 * <pre> 对象为空则抛出异常.
	 * 
	 * 为空的条件：
	 *   null
	 *   char/byte/short/int/long/float/double == 0
	 *   String/Collection/Map => isEmpty
	 *   Array => length == 0
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/01  huangys  Create
	 * </pre>
	 * 
	 * @param obj 被检测对象
	 * @param msg 异常消息
	 */
	public static void emptyThrow(@Nullable final Object obj, @Nullable final String msg) {
		matchThrow(Objects.isEmpty(obj), msg == null ? "Parameter cannot be empty!" : msg);
	}
	
	/**
	 * <pre> 对象不为空则抛出异常.
	 * 
	 * 为空的条件：
	 *   null
	 *   char/byte/short/int/long/float/double == 0
	 *   String/Collection/Map => isEmpty
	 *   Array => length == 0
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/01  huangys  Create
	 * </pre>
	 * 
	 * @param obj 被检测对象
	 * @param msg 异常消息
	 */
	public static void notEmptyThrow(@Nullable final Object obj, @Nullable final String msg) {
		matchThrow(!Objects.isEmpty(obj), msg == null ? "Parameter must be empty(fact: " + obj + ")!" : msg);
	}
	
	/**
	 * <pre> 数组大小不同则抛出异常.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/14  huangys  Create
	 * </pre>
	 * 
	 * @param arr1 数组1
	 * @param arr2 数组2
	 */
	public static void notSameSizeThrow(@Nullable final Object[] arr1, @Nullable final Object[] arr2) {
		int size1 = Arrays.isEmpty(arr1) ? 0 : arr1.length;
		int size2 = Arrays.isEmpty(arr1) ? 0 : arr2.length;
		if (size1 != size2) {
			throwException("Size of Arrays must be same(" + size1 + ", " + size2 + ").");
		}
	}
	
	/**
	 * <pre> 正数抛出异常.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/15  huangys  Create
	 * </pre>
	 * 
	 * @param n 待校验参数
	 */
	public static void positiveThrow(@Nonnull final double n) {
		positiveThrow(n, null);
	}
	
	/**
	 * <pre> 非正数抛出异常.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/15  huangys  Create
	 * </pre>
	 * 
	 * @param n 待校验参数
	 */
	public static void nonPositiveThrow(@Nonnull final double n) {
		nonPositiveThrow(n, null);
	}
	
	/**
	 * <pre> 负数抛出异常.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/15  huangys  Create
	 * </pre>
	 * 
	 * @param n 待校验参数
	 */
	public static void negativeThrow(@Nonnull final double n) {
		negativeThrow(n, null);
	}
	
	/**
	 * <pre> 非负数抛出异常.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/15  huangys  Create
	 * </pre>
	 * 
	 * @param n 待校验参数
	 */
	public static void nonNegativeThrow(@Nonnull final double n) {
		nonNegativeThrow(n, null);
	}
	
	/**
	 * <pre> 正数抛出异常.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/15  huangys  Create
	 * </pre>
	 * 
	 * @param n 待校验参数
	 * @param msg 异常消息
	 */
	public static void positiveThrow(@Nonnull final double n, @Nullable final String msg) {
		matchThrow(n > 0, msg == null ? "Parameter cannot be positive." : msg);
	}
	
	/**
	 * <pre> 非正数抛出异常.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/15  huangys  Create
	 * </pre>
	 * 
	 * @param n 待校验参数
	 * @param msg 异常消息
	 */
	public static void nonPositiveThrow(@Nonnull final double n, @Nullable final String msg) {
		matchThrow(n <= 0, msg == null ? "Parameter must be positive." : msg);
	}
	
	/**
	 * <pre> 负数抛出异常.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/15  huangys  Create
	 * </pre>
	 * 
	 * @param n 待校验参数
	 * @param msg 异常消息
	 */
	public static void negativeThrow(@Nonnull final double n, @Nullable final String msg) {
		matchThrow(n < 0, msg == null ? "Parameter cannot be negative." : msg);
	}
	
	/**
	 * <pre> 非负数抛出异常.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/15  huangys  Create
	 * </pre>
	 * 
	 * @param n 待校验参数
	 * @param msg 异常消息
	 */
	public static void nonNegativeThrow(@Nonnull final double n, @Nullable final String msg) {
		matchThrow(n >= 0, msg == null ? "Parameter must be negative." : msg);
	}
	
	/**
	 * <pre> 文件存在抛出异常.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/15  huangys  Create
	 * </pre>
	 * 
	 * @param file 文件
	 */
	public static void fileExistsThrow(@Nullable final File file) {
		fileExistsThrow(file, null);
	}
	
	/**
	 * <pre> 文件存在抛出异常.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/15  huangys  Create
	 * </pre>
	 * 
	 * @param file 文件
	 */
	public static void fileNotExistsThrow(@Nullable final File file) {
		fileNotExistsThrow(file, null);
	}
	
	/**
	 * <pre> 文件存在抛出异常.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/15  huangys  Create
	 * </pre>
	 * 
	 * @param filename 文件名称
	 */
	public static void fileExistsThrow(@Nullable final String filename) {
		fileExistsThrow(filename, null);
	}
	
	/**
	 * <pre> 文件存在抛出异常.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/15  huangys  Create
	 * </pre>
	 * 
	 * @param filename 文件名称
	 */
	public static void fileNotExistsThrow(@Nullable final String filename) {
		fileNotExistsThrow(filename, null);
	}
	
	/**
	 * <pre> 文件存在抛出异常.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/15  huangys  Create
	 * </pre>
	 * 
	 * @param file 文件
	 * @param msg 异常消息
	 */
	public static void fileExistsThrow(@Nullable final File file, @Nullable final String msg) {
		matchThrow(Files.exists(file), msg == null ? "File(" + file.getAbsolutePath() + ") cannot found." : msg);
	}
	
	/**
	 * <pre> 文件存在抛出异常.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/15  huangys  Create
	 * </pre>
	 * 
	 * @param file 文件
	 * @param msg 异常消息
	 */
	public static void fileNotExistsThrow(@Nullable final File file, @Nullable final String msg) {
		notMatchThrow(Files.exists(file), msg == null ? "File(" + (file == null ? null : file.getAbsolutePath()) + ") cannot exist." : msg);
	}
	
	/**
	 * <pre> 文件存在抛出异常.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/15  huangys  Create
	 * </pre>
	 * 
	 * @param filename 文件名称
	 * @param msg 异常消息
	 */
	public static void fileExistsThrow(@Nullable final String filename, @Nullable final String msg) {
		fileExistsThrow(filename == null ? null : new File(filename), msg);
	}
	
	/**
	 * <pre> 文件存在抛出异常.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/15  huangys  Create
	 * </pre>
	 * 
	 * @param filename 文件名称
	 * @param msg 异常消息
	 */
	public static void fileNotExistsThrow(@Nullable final String filename, @Nullable final String msg) {
		fileNotExistsThrow(filename == null ? null : new File(filename), msg);
	}
	
	/**
	 * <pre> 有重复则抛出异常.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/02/05  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 元素类型
	 * @param list 待校验列表
	 */
	public static <T> void duplicateThrow(@Nonnull final List<T> list) {
		duplicateThrow(list, null);
	}
	
	/**
	 * <pre> 有重复则抛出异常.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/02/05  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 元素类型
	 * @param list 待校验列表
	 * @param msg 异常消息
	 */
	public static <T> void duplicateThrow(@Nonnull final List<T> list, @Nullable final String msg) {
		nullThrow(list);
		Set<T> set = new HashSet<>();
		for (T t : list) {
			containsThrow(set, t);
			set.add(t);
		}
	}
	
	/**
	 * <pre> 有重复则抛出异常.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/02/05  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 元素类型
	 * @param arr 待校验列表
	 */
	public static <T> void duplicateThrow(@Nonnull final T[] arr) {
		duplicateThrow(arr, null);
	}
	
	/**
	 * <pre> 有重复则抛出异常.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/02/05  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 元素类型
	 * @param arr 待校验列表
	 * @param msg 异常消息
	 */
	public static <T> void duplicateThrow(@Nonnull final T[] arr, @Nullable final String msg) {
		nullThrow(arr);
		Set<T> set = new HashSet<>();
		for (T t : arr) {
			containsThrow(set, t);
			set.add(t);
		}
	}
	
}
