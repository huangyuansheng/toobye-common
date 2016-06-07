/*
 * Copyright 2015 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2015/12/10.
 * 
 */
package com.toobye.common.collection;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.toobye.common.lang.Checks;
import com.toobye.common.lang.Condition;

/**
 * <pre> 工具.
 * 参考com.google.common.collect.Iterables
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2015/12/10  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class Iterables {

	private Iterables() { }

	/**
	 * <pre> 是否为空.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/10  huangys  Create
	 * </pre>
	 * 
	 * @param iterable 可迭代对象
	 * @return 是否为空
	 */
	@Nonnull
	public static boolean isEmpty(@Nullable final Iterable<?> iterable) {
		if (iterable == null) {
			return true;
		}
		return com.google.common.collect.Iterables.isEmpty(iterable);
	}
	
	/**
	 * <pre> 大小.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/10  huangys  Create
	 * </pre>
	 * 
	 * @param iterable 可迭代对象
	 * @return 大小
	 */
	@Nonnull
	public static int size(@Nullable final Iterable<?> iterable) {
		if (iterable == null) {
			return 0;
		}
		return com.google.common.collect.Iterables.size(iterable);
	}
	
	/**
	 * <pre> 获得指定位置元素.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/15  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 元素类型
	 * @param iterable 可迭代对象
	 * @param position 位置
	 * @return 元素
	 */
	public static <T> T get(@Nonnull final Iterable<T> iterable, @Nonnull final int position) {
		Checks.emptyThrow(iterable);
		return com.google.common.collect.Iterables.get(iterable, position);
	}
	
	/**
	 * <pre> 创建新数组.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/19  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 元素类型
	 * @param iterable 集合
	 * @return 新数组
	 */
	@Nullable
	public static <T> T[] newInstance(@Nullable final Iterable<T> iterable) {
		if (iterable == null) {
			return null;
		}
		return newInstance(iterable, size(iterable));
	}
	
	/**
	 * <pre> 创建新数组.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/19  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 元素类型
	 * @param iterable 集合
	 * @param size 数组大小
	 * @return 新数组
	 */
	@SuppressWarnings("unchecked")
	@Nullable
	public static <T> T[] newInstance(@Nullable final Iterable<T> iterable, @Nonnull final int size) {
		if (isEmpty(iterable)) {
			return null;
		}
		return (T[]) Arrays.newInstance(iterable.iterator().next().getClass(), size);
	}
	
	/**
	 * <pre> 创建新数组.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/19  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 元素类型
	 * @param iterable 集合
	 * @return 新数组
	 */
	@SuppressWarnings("unchecked")
	@Nullable
	public static <T> T[] toArray(@Nullable final Iterable<T> iterable) {
		if (isEmpty(iterable)) {
			return null;
		}
		return com.google.common.collect.Iterables.toArray(iterable, (Class<T>) iterable.iterator().next().getClass());
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
	 * @param iterable 可迭代对象
	 * @param conditions 条件
	 */
	@SafeVarargs
	public static <T extends E, E> void delete(@Nullable final Iterable<T> iterable, @Nullable final Condition<E>... conditions) {
		if (iterable != null) {
			Iterators.delete(iterable.iterator(), conditions);
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
	 * @param iterable 可迭代对象
	 * @param conditions 条件
	 */
	public static <T extends E, E> void delete(@Nullable final Iterable<T> iterable, @Nullable final Iterable<Condition<E>> conditions) {
		if (iterable != null) {
			Iterators.delete(iterable.iterator(), conditions);
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
	 * @param iterable 可迭代对象
	 * @param conditions 条件
	 */
	@SafeVarargs
	public static <T extends E, E> void reserve(@Nullable final Iterable<T> iterable, @Nullable final Condition<E>... conditions) {
		if (iterable != null) {
			Iterators.reserve(iterable.iterator(), conditions);
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
	 * @param iterable 可迭代对象
	 * @param conditions 条件
	 */
	public static <T extends E, E> void reserve(@Nullable final Iterable<T> iterable, @Nullable final Iterable<Condition<E>> conditions) {
		if (iterable != null) {
			Iterators.reserve(iterable.iterator(), conditions);
		}
	}
	
}
