/*
 * Copyright 2014 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2014/09/02.
 * 
 */
package com.toobye.common.collection;

import java.util.LinkedList;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.toobye.common.lang.Checks;

/**
 * <pre> 定长列表.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2014/09/02  huangys  v1.0      Create
 * </pre>
 * 
 * @param <E> 元素类型
 */
public final class FixedSizeQueue<E> extends LinkedList<E> {
	
	private static final long serialVersionUID = 1L;
	
	private int maxSize;

    private FixedSizeQueue(@Nonnull final int maxSize) {
    	Checks.matchThrow(maxSize <= 0, "MaxSize must larger then 0.");
        this.maxSize = maxSize;
    }
    
    /**
     * <pre> 创建定长列表.
     * 
     * Modification History:
     * Date        Author   Action
     * 2014/09/02  huangys  Create
     * </pre>
     * 
     * @param maxSize 最大长度
     * @param <E> 参数类型
     * @return 定长列表
     */
    @Nonnull
    public static <E> FixedSizeQueue<E> create(@Nonnull final int maxSize) {
    	return new FixedSizeQueue<>(maxSize);
    }

    /**
     * <pre> 添加元素，并返回溢出元素.
     * 若未溢出，则返回null。
     * 
     * Modification History:
     * Date        Author   Action
     * 2014/09/02  huangys  Create
     * </pre>
     * 
     * @param e 待添加元素
     * @return 溢出元素
     */
    @Nullable
	public E append(@Nonnull final E e) {
    	Checks.nullThrow(e);
		if (size() == maxSize) {
			E ret = remove();
			add(e);
			return ret;
		} else {
			add(e);
			return null;
		}
	}

}
