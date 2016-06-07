/*
 * Copyright 2015 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2015/05/20.
 * 
 */
package com.toobye.common.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.toobye.common.lang.Checks;
import com.toobye.common.lang.Condition;
import com.toobye.common.lang.Processors;

/**
 * <pre> 迭代器.
 * 参考com.google.common.collect.Iterators
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2015/05/20  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class Iterators {
	
	private Iterators() { }
	
	/**
	 * <pre> 是否为空.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/10  huangys  Create
	 * </pre>
	 * 
	 * @param iterator 可迭器
	 * @return 是否为空
	 */
	@Nonnull
	public static boolean isEmpty(@Nullable final Iterator<?> iterator) {
		if (iterator == null || !iterator.hasNext()) {
			return true;
		}
		return false;
	}
	
	/**
	 * <pre> 大小.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/10  huangys  Create
	 * </pre>
	 * 
	 * @param iterator 迭代器
	 * @return 大小
	 */
	@Nonnull
	public static int size(@Nullable final Iterator<?> iterator) {
		if (iterator == null) {
			return 0;
		}
		return com.google.common.collect.Iterators.size(iterator);
	}
	
	/**
	 * <pre> 清空.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/10  huangys  Create
	 * </pre>
	 * 
	 * @param iterator 迭代器
	 */
	@Nonnull
	public static void clear(@Nullable final Iterator<?> iterator) {
		if (iterator != null) {
			while (iterator.hasNext()) {
				iterator.next();
				iterator.remove();
			} 
		}
	}
	
	/**
	 * <pre> 获得数值迭代器[0, n).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/02  huangys  Create
	 * </pre>
	 * 
	 * @param n 范围
	 * @return 数值迭代器
	 */
	@Nonnull
	public static Iterator<Integer> numeric(@Nonnull final int n) {
		return numeric(0, n, 1);
	}
	
	/**
	 * <pre> 获得数值迭代器.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/02  huangys  Create
	 * </pre>
	 * 
	 * @param start 起始
	 * @param count 数量
	 * @return 数值迭代器
	 */
	@Nonnull
	public static Iterator<Integer> numeric(@Nonnull final int start, @Nonnull final int count) {
		return numeric(start, count, 1);
	}
	
	/**
	 * <pre> 获得数值迭代器.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/02  huangys  Create
	 * </pre>
	 * 
	 * @param start 起始
	 * @param count 数量
	 * @param add 差值
	 * @return 数值迭代器
	 */
	@Nonnull
	public static Iterator<Integer> numeric(@Nonnull final int start, @Nonnull final int count, @Nonnull final int add) {
		if (count <= 0) {
			throw new RuntimeException("Error");
		}
		
		return new Iterator<Integer>() {
			private int curr = start - add;
			private int n = count;
			
			@Override
			public boolean hasNext() {
				return n > 0;
			}

			@Override
			public Integer next() {
				if (hasNext()) {
					curr += add;
					n--;
					return curr;
				}
				throw new NoSuchElementException();
			}

			@Override
			public void remove() {
				throw new RuntimeException("Not Supported.");
			}
		};
	}
	
	
	/**
	 * <pre> 转数组.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/02  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 元素类型
	 * @param iterator 迭代器
	 * @param type 元素类型
	 * @return 数组
	 */
	@Nullable
	public static <T> T[] toArray(@Nullable final Iterator<? extends T> iterator, @Nonnull final Class<T> type) {
		if (iterator == null) {
			return null;
		}
		Checks.nullThrow(type);
		return com.google.common.collect.Iterators.toArray(iterator, type);
	}
	
	/**
	 * <pre> 转字符串.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/02  huangys  Create
	 * </pre>
	 * 
	 * @param iterator 迭代器
	 * @return 字符串
	 */
	@Nullable
	public static String toString(@Nullable final Iterator<?> iterator) {
		if (iterator == null) {
			return null;
		}
		return com.google.common.collect.Iterators.toString(iterator);
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
	 * @param iterator 可迭器
	 * @param conditions 条件
	 */
	@SafeVarargs
	public static <T extends E, E> void delete(@Nullable final Iterator<T> iterator, @Nullable final Condition<E>... conditions) {
		if (isEmpty(iterator) || Arrays.isEmpty(conditions)) {
			return;
		}
		
		Condition<E> orCondition = Processors.or(conditions);
		while (iterator.hasNext()) {
			T one = iterator.next();
			if (orCondition.match(one)) {
				iterator.remove();
			}
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
	 * @param iterator 可迭器
	 * @param conditions 条件
	 */
	public static <T extends E, E> void delete(@Nullable final Iterator<T> iterator, @Nullable final Iterable<Condition<E>> conditions) {
		if (isEmpty(iterator) || Iterables.isEmpty(conditions)) {
			return;
		}
		
		Condition<E> orCondition = Processors.or(conditions);
		while (iterator.hasNext()) {
			T one = iterator.next();
			if (orCondition.match(one)) {
				iterator.remove();
			}
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
	 * @param iterator 可迭器
	 * @param conditions 条件
	 */
	@SafeVarargs
	public static <T extends E, E> void reserve(@Nullable final Iterator<T> iterator, @Nullable final Condition<E>... conditions) {
		if (isEmpty(iterator)) {
			return;
		}
		
		if (Arrays.isEmpty(conditions)) {
			clear(iterator);
		} else {
			delete(iterator, Processors.not(Processors.or(conditions)));
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
	 * @param iterator 可迭器
	 * @param conditions 条件
	 */
	public static <T extends E, E> void reserve(@Nullable final Iterator<T> iterator, @Nullable final Iterable<Condition<E>> conditions) {
		if (isEmpty(iterator)) {
			return;
		}
		
		if (Iterables.isEmpty(conditions)) {
			clear(iterator);
		} else {
			delete(iterator, Processors.not(Processors.or(conditions)));
		}
	}
	
}
