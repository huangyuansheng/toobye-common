/*
 * Copyright 2015 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2015/01/05.
 * 
 */
package com.toobye.common.collection;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

import javax.annotation.Nonnull;

import com.toobye.common.lang.Checks;

/**
 * <pre> 集合工具类.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2015/01/05  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class Sets {
	
	private Sets() { }
	
	/**
	 * <pre> 创建集合.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/30  huangys  Create
	 * </pre>
	 * 
	 * @param <E> 元素类型
	 * @return 集合
	 */
	@Nonnull
	public static <E> HashSet<E> newHashSet() {
		return com.google.common.collect.Sets.newHashSet();
	}
	
	/**
	 * <pre> 创建集合.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/02  huangys  Create
	 * </pre>
	 * 
	 * @param array 数组
	 * @return 集合
	 */
	@Nonnull
	public static HashSet<Character> newHashSet(@Nonnull final char[] array) {
		Checks.nullThrow(array);
		return newHashSet(Arrays.toObject(array));
	}
	
	/**
	 * <pre> 创建集合.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/02  huangys  Create
	 * </pre>
	 * 
	 * @param array 数组
	 * @return 集合
	 */
	@Nonnull
	public static HashSet<Byte> newHashSet(@Nonnull final byte[] array) {
		Checks.nullThrow(array);
		return newHashSet(Arrays.toObject(array));
	}
	
	/**
	 * <pre> 创建集合.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/02  huangys  Create
	 * </pre>
	 * 
	 * @param array 数组
	 * @return 集合
	 */
	@Nonnull
	public static HashSet<Short> newHashSet(@Nonnull final short[] array) {
		Checks.nullThrow(array);
		return newHashSet(Arrays.toObject(array));
	}
	
	/**
	 * <pre> 创建集合.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/02  huangys  Create
	 * </pre>
	 * 
	 * @param array 数组
	 * @return 集合
	 */
	@Nonnull
	public static HashSet<Integer> newHashSet(@Nonnull final int[] array) {
		Checks.nullThrow(array);
		return newHashSet(Arrays.toObject(array));
	}
	
	/**
	 * <pre> 创建集合.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/02  huangys  Create
	 * </pre>
	 * 
	 * @param array 数组
	 * @return 集合
	 */
	@Nonnull
	public static HashSet<Long> newHashSet(@Nonnull final long[] array) {
		Checks.nullThrow(array);
		return newHashSet(Arrays.toObject(array));
	}

	/**
	 * <pre> 创建集合.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/02  huangys  Create
	 * </pre>
	 * 
	 * @param array 数组
	 * @return 集合
	 */
	@Nonnull
	public static HashSet<Float> newHashSet(@Nonnull final float[] array) {
		Checks.nullThrow(array);
		return newHashSet(Arrays.toObject(array));
	}
	
	/**
	 * <pre> 创建集合.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/02  huangys  Create
	 * </pre>
	 * 
	 * @param array 数组
	 * @return 集合
	 */
	@Nonnull
	public static HashSet<Double> newHashSet(@Nonnull final double[] array) {
		Checks.nullThrow(array);
		return newHashSet(Arrays.toObject(array));
	}
	
	/**
	 * <pre> 创建集合.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/30  huangys  Create
	 * </pre>
	 * 
	 * @param <E> 元素类型
	 * @param elements 元素
	 * @return 集合
	 */
	@Nonnull
	public static <E> HashSet<E> newHashSet(@Nonnull final Iterable<? extends E> elements) {
		Checks.emptyThrow(elements);
		return com.google.common.collect.Sets.newHashSet(elements);
	}

	/**
	 * <pre> 创建集合.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/30  huangys  Create
	 * </pre>
	 * 
	 * @param <E> 元素类型
	 * @param elements 元素
	 * @return 集合
	 */
	@Nonnull
	public static <E> HashSet<E> newHashSet(@Nonnull final Iterator<? extends E> elements) {
		Checks.emptyThrow(elements);
		return com.google.common.collect.Sets.newHashSet(elements);
	}
	
	/**
	 * <pre> 创建集合.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/30  huangys  Create
	 * </pre>
	 * 
	 * @param <E> 元素类型
	 * @param expectedSize 集合规模
	 * @return 集合
	 */
	@Nonnull
	public static <E> HashSet<E> newHashSetWithExpectedSize(@Nonnull final int expectedSize) {
		return com.google.common.collect.Sets.newHashSetWithExpectedSize(expectedSize);
	}
	
	/**
	 * <pre> 创建集合.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/30  huangys  Create
	 * </pre>
	 * 
	 * @param <E> 元素类型
	 * @param elements 元素
	 * @return 集合
	 */
	@SafeVarargs
	public static <E> HashSet<E> newHashSet(final E... elements) {
	    HashSet<E> set = newHashSetWithExpectedSize(elements.length);
	    Collections.addAll(set, elements);
	    return set;
	}
	
}
