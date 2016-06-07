/*
 * Copyright 2014 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2014/03/27.
 * 
 */
package com.toobye.common.collection;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.annotation.Nonnull;

import com.toobye.common.lang.Checks;

/**
 * <pre> 实现指定长度的有序队列，可快速查找.
 * 添加尾部，移除头部。
 * 需有序添加(升序)，否则抛出异常。
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2014/03/27  huangys  v1.0      Create
 * </pre>
 * 
 * @param <T> 队列元素类型
 */
public class Queue<T> {

	private int maxSize;
	private Comparator<T> comparator;
	private List<T> list = new CopyOnWriteArrayList<T>();
	
	/**
	 * <pre> 构造器. </pre>
	 * 
	 * @param maxSize 队列长度限制
	 * @param comparator 比较器
	 */
	public Queue(@Nonnull final int maxSize, final Comparator<T> comparator) {
		Checks.matchThrow(maxSize < 16, "MaxSize cannot be less than 16.");
		this.maxSize = maxSize;
		this.comparator = comparator;
	}
	
	/**
	 * <pre> 在尾部添加.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/03/27  huangys  Create
	 * </pre>
	 * 
	 * @param e 元素
	 */
	public final void add(@Nonnull final T e) {
		Checks.nullThrow(e);
		if (list.isEmpty()) {
			list.add(e);
		} else {
			T last = list.get(list.size() - 1);
			Checks.matchThrow(comparator.compare(last, e) > 0, "Order error.");
			while (list.size() >= maxSize) {
				remove();
			}
			list.add(e);
		}
	}

	/**
	 * <pre> 在头部移除.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/03/27  huangys  Create
	 * </pre>
	 * 
	 * @return 被移除元素
	 */
	protected final T remove() {
		return list.remove(0);
	}
	
	/**
	 * <pre> 获得头部记录.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/03/27  huangys  Create
	 * </pre>
	 * 
	 * @param count 条数
	 * @return 头部记录
	 */
	@Nonnull
	public final List<T> getHead(@Nonnull final int count) {
		return getHead(count, list.size());
	}
	
	/**
	 * <pre> 获得尾部记录.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/03/27  huangys  Create
	 * </pre>
	 * 
	 * @param count 条数
	 * @return 尾部记录
	 */
	@Nonnull
	public final List<T> getTail(@Nonnull final int count) {
		return getTail(count, 0);
	}
	
	/**
	 * <pre> 获得头部记录(指定位置前exclusive).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/03/27  huangys  Create
	 * </pre>
	 * 
	 * @param count 条数
	 * @param index 索引
	 * @return 头部记录
	 */
	@Nonnull
	private List<T> getHead(@Nonnull final int count, @Nonnull final int index) {
		Checks.negativeThrow(count);
		Checks.negativeThrow(index);
		return list.subList(0, Math.min(Math.min(count, list.size()), index));
	}
	
	/**
	 * <pre> 获得尾部记录(指定位置后inclusive).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/03/27  huangys  Create
	 * </pre>
	 * 
	 * @param count 条数
	 * @param index 索引
	 * @return 尾部记录
	 */
	@Nonnull
	private List<T> getTail(@Nonnull final int count, @Nonnull final int index) {
		Checks.negativeThrow(count);
		Checks.negativeThrow(index);
		int size = list.size();
		return list.subList(Math.max(Math.max(0, size - count), index), size);
	}
	
	/**
	 * <pre> 获得头部记录(相比e之前).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/03/27  huangys  Create
	 * </pre>
	 * 
	 * @param e 用于比较的元素
	 * @param count 条数
	 * @return 头部记录
	 */
	@Nonnull
	public final List<T> getHead(@Nonnull final T e, @Nonnull final int count) {
		Checks.nullThrow(e);
		int index = Collections.binarySearch(list, e, comparator);
		if (index < 0) {
			index = -index - 1;
		} else {
			index++;
		}
		return getHead(count, index);
	}
	
	/**
	 * <pre> 获得尾部记录(相比e之后).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/03/27  huangys  Create
	 * </pre>
	 * 
	 * @param e 用于比较的元素
	 * @param count 条数
	 * @return 尾部记录
	 */
	@Nonnull
	public final List<T> getTail(@Nonnull final T e, @Nonnull final int count) {
		Checks.nullThrow(e);
		int index = Collections.binarySearch(list, e, comparator);
		if (index < 0) {
			index = -index - 1;
		}
		return getTail(count, index);
	}
	
	/**
	 * <pre> 获得数据列表.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/29  huangys  Create
	 * </pre>
	 * 
	 * @return 数据列表
	 */
	@Nonnull
	public final List<T> getList() {
		return list;
	}

	/**
	 * <pre> 设置数据列表.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/29  huangys  Create
	 * </pre>
	 * 
	 * @param list 数据列表
	 */
	public final void setList(@Nonnull final List<T> list) {
		Checks.nullThrow(list);
		Checks.matchThrow(list.size() > maxSize, "Size cannot be larger than maxSize.");
		T last = null;
		for (T t : list) {
			Checks.nullThrow(t, "List cannot contain null value.");
			if (last != null) {
				Checks.matchThrow(comparator.compare(last, t) > 0, "List must be ordered.");
			}
			last = t;
		}
		this.list = list;
	}
	
}
