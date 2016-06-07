/*
 * Copyright 2015 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2015/12/14.
 * 
 */
package com.toobye.common.collection;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.toobye.common.lang.Condition;
import com.toobye.common.lang.Function;
import com.toobye.common.lang.Objects;
import com.toobye.common.lang.Processors;

/**
 * <pre> 集合工具类.
 * ConcurrentLinkedQueue
 * ConcurrentHashMap
 * CopyOnWriteArrayList
 * CopyOnWriteArraySet
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2015/12/14  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class Collections {
	
	private Collections() { }

	/**
	 * <pre> 是否为空.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/10  huangys  Create
	 * </pre>
	 * 
	 * @param collection 可迭代对象
	 * @return 是否为空
	 */
	@Nonnull
	public static boolean isEmpty(@Nullable final Collection<?> collection) {
		return collection == null || collection.isEmpty();
	}
	
	/**
	 * <pre> 数组加工.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/08  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 元素类型
	 * @param <E> 元素类型
	 * @param collection 集合
	 * @param funcs 函数集
	 */
	@SafeVarargs
	public static <T extends E, E> void process(@Nullable final Collection<T> collection, @Nullable final Function<E, T>... funcs) {
		if (isEmpty(collection) || Arrays.isEmpty(funcs)) {
			return;
		}
		Collection<T> tmp = new ArrayList<>();
		for (T t : collection) {
			for (Function<E, T> func : funcs) {
				t = func.apply(t);
			}
			tmp.add(t);
		}
		collection.clear();
		collection.addAll(tmp);
	}
	
	/**
	 * <pre> 数组加工.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/08  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 元素类型
	 * @param <E> 元素类型
	 * @param collection 集合
	 * @param funcs 函数集
	 */
	public static <T extends E, E> void process(@Nullable final Collection<T> collection, @Nullable final Iterable<Function<E, T>> funcs) {
		if (isEmpty(collection) || Iterables.isEmpty(funcs)) {
			return;
		}
		Collection<T> tmp = new ArrayList<>();
		for (T t : collection) {
			for (Function<E, T> func : funcs) {
				t = func.apply(t);
			}
			tmp.add(t);
		}
		collection.clear();
		collection.addAll(tmp);
	}
	
	/**
	 * <pre> 满足条件的移除.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/21  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 元素类型
	 * @param <E> 元素类型
	 * @param collection 集合
	 * @param conditions 条件
	 */
	@SafeVarargs
	public static <T extends E, E> void delete(@Nullable final Collection<T> collection, @Nullable final Condition<E>... conditions) {
		if (collection != null) {
			Iterables.delete(collection, conditions);
		}
	}
	
	/**
	 * <pre> 满足条件的移除.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/21  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 元素类型
	 * @param <E> 元素类型
	 * @param collection 集合
	 * @param conditions 条件
	 */
	public static <T extends E, E> void delete(@Nullable final Collection<T> collection, @Nullable final Iterable<Condition<E>> conditions) {
		if (collection != null) {
			Iterables.delete(collection, conditions);
		}
	}
	
	/**
	 * <pre> 满足条件的保留.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/21  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 元素类型
	 * @param <E> 元素类型
	 * @param collection 集合
	 * @param conditions 条件
	 */
	@SafeVarargs
	public static <T extends E, E> void reserve(@Nullable final Collection<T> collection, @Nullable final Condition<E>... conditions) {
		if (collection != null) {
			Iterables.reserve(collection, conditions);
		}
	}
	
	/**
	 * <pre> 满足条件的保留.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/21  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 元素类型
	 * @param <E> 元素类型
	 * @param collection 集合
	 * @param conditions 条件
	 */
	public static <T extends E, E> void reserve(@Nullable final Collection<T> collection, @Nullable final Iterable<Condition<E>> conditions) {
		if (collection != null) {
			Iterables.reserve(collection, conditions);
		}
	}
	
	/**
	 * <pre> 移除Null元素.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/17  huangys  Create
	 * </pre>
	 * 
	 * @param collection 集合
	 */
	public static void removeNull(@Nullable final Collection<?> collection) {
		reserve(collection, Processors.not(Objects.IS_NULL));
	}
	
	/**
	 * <pre> 移除空元素.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/17  huangys  Create
	 * </pre>
	 * 
	 * @param collection 集合
	 */
	public static void removeEmpty(@Nullable final Collection<?> collection) {
		reserve(collection, Processors.not(Objects.IS_EMPTY));
	}
	
}
