/*
 * Copyright 2014 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2014/03/27.
 * 
 */
package com.toobye.common.collection;

import java.util.Comparator;

import javax.annotation.Nonnull;

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
public class QueueComparable<T extends Comparable<T>> extends Queue<T> {

	/**
	 * <pre> 构造器. </pre>
	 *
	 * @param maxSize 队列长度限制
	 */
	public QueueComparable(@Nonnull final int maxSize) {
		super(maxSize, new Comparator<T>() {
			@Override
			public int compare(final T o1, final T o2) {
				return o1.compareTo(o2);
			}
		});
	}

}
