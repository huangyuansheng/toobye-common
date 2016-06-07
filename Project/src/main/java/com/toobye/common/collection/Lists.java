/*
 * Copyright 2015 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2015/06/03.
 * 
 */
package com.toobye.common.collection;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.toobye.common.lang.Checks;

/**
 * <pre> List工具.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2015/06/03  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class Lists {
	
	private Lists() { }
	
	/**
	 * <pre> 空列表. </pre>
	 */
	public static final List<String> EMPTY = new ArrayList<>();
	
	/**
	 * <pre> 列表转数组.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/19  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 元素类型
	 * @param list 列表
	 * @return 数组
	 */
	@Nullable
	public static <T> T[] toArray(@Nullable final List<T> list) {
		if (Collections.isEmpty(list)) {
			return null;
		}
		return list.toArray(Iterables.newInstance(list));
	}
	
	/**
	 * <pre> 列表降维（剃重元素）.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/03  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 元素类型
	 * @param list 列表
	 * @return 降维后列表
	 */
	@Nonnull
	public static <T> List<T> toUnique(@Nonnull final List<T> list) {
		List<T> ret = new ArrayList<>();
		for (T t : list) {
			if (!ret.contains(t)) {
				ret.add(t);
			}
		}
		return ret;
	}
	
	/**
	 * <pre> 列表降维.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/03  huangys  Create
	 * </pre>
	 * 
	 * @param <E> 元素类型
	 * @param <T> 元素类型
	 * @param list 列表
	 * @return 降维后列表
	 */
	@Nonnull
	public static <E, T extends E> List<E> toExpand(@Nonnull final List<List<T>> list) {
		Checks.nullThrow(list);
		List<E> ret = new ArrayList<>();
		for (List<T> l : list) {
			ret.addAll(l);
		}
		return ret;
	}
	
	/**
	 * <pre> 列表降维（剃重元素）.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/03  huangys  Create
	 * </pre>
	 * 
	 * @param <E> 元素类型
	 * @param <T> 元素类型
	 * @param list 列表
	 * @return 降维后列表
	 */
	@Nonnull
	public static <E, T extends E> List<E> toUniqueExpand(@Nonnull final List<List<T>> list) {
		Checks.nullThrow(list);
		List<E> ret = new ArrayList<>();
		for (List<T> l : list) {
			for (E e : l) {
				if (!ret.contains(e)) {
					ret.add(e);
				}
			}
		}
		return ret;
	}

	/**
	 * <pre> 切分.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/08/10  huangys  Create
	 * </pre>
	 * 
	 * @param <E> 元素类型
	 * @param list 列表
	 * @param pieces 份数
	 * @return 新切片列表
	 */
	@Nonnull
	public static <E> List<List<E>> split(@Nonnull final List<E> list, @Nonnull final int pieces) {
		Checks.emptyThrow(list);
		int base = list.size() / pieces;
		int left = list.size() % pieces;
		List<List<E>> ret = new ArrayList<>();
		int index = 0;
		for (int i = 0; i < left; i++) {
			int toIndex = index + base + 1;
			ret.add(list.subList(index, toIndex));
			index = toIndex;
		}
		for (int i = left; i < pieces; i++) {
			int toIndex = index + base;
			ret.add(list.subList(index, toIndex));
			index = toIndex;
		}
		return ret;
	}
	
	/**
	 * <pre> 切分.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/08/10  huangys  Create
	 * </pre>
	 * 
	 * @param <E> 元素类型
	 * @param list 列表
	 * @param size 每份大小
	 * @return 新切片列表
	 */
	@Nonnull
	public static <E> List<List<E>> splitAssignedSize(@Nonnull final List<E> list, @Nonnull final int size) {
		Checks.emptyThrow(list);
		
		int pieces = (list.size() - 1) / size + 1;
		
		List<List<E>> ret = new ArrayList<>();
		int index = 0;
		for (int i = 0; i < pieces - 1; i++) {
			int toIndex = index + size;
			ret.add(list.subList(index, toIndex));
			index = toIndex;
		}
		ret.add(list.subList(index, list.size()));
		return ret;
	}
	
}