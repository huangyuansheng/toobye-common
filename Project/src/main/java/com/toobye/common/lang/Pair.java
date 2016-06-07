/*
 * Copyright 2014 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2014/03/28.
 * 
 */
package com.toobye.common.lang;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * <pre> 对值.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2014/06/07  huangys  v1.0      Create
 * </pre>
 * 
 * @param <L> Left
 * @param <R> Right
 */
public final class Pair<L, R> {
	
	private Pair(final L left, final R right) {
		this.left = left;
		this.right = right;
	}
	
	private L left;
	private R right;
	
	/**
	 * <pre> 获取左侧值.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/06/07  huangys  Create
	 * </pre>
	 * 
	 * @return 左侧值
	 */
	@Nullable
	public L getLeft() {
		return left;
	}
	/**
	 * <pre> 设置左侧值.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/06/07  huangys  Create
	 * </pre>
	 * 
	 * @param left 左侧值
	 */
	public void setLeft(@Nullable final L left) {
		this.left = left;
	}
	/**
	 * <pre> 获取右侧值.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/06/07  huangys  Create
	 * </pre>
	 * 
	 * @return 右侧值
	 */
	@Nullable
	public R getRight() {
		return right;
	}
	/**
	 * <pre> 设置右侧值.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/06/07  huangys  Create
	 * </pre>
	 * 
	 * @param right 右侧值
	 */
	public void setRight(@Nullable final R right) {
		this.right = right;
	}
	
	/**
	 * <pre> 返回实例化对象.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/06/07  huangys  Create
	 * </pre>
	 * 
	 * @param <L> 左侧值类型
	 * @param <R> 右侧值类型
	 * @param left 左侧值
	 * @param right 右侧值
	 * @return 实例化对象
	 */
	@Nonnull
	public static <L, R> Pair<L, R> of(@Nullable final L left, @Nullable final R right) {
		return new Pair<L, R>(left, right);
	}
	
	@Override
	public String toString() {
		return "Left: " + (left == null ? "null" : left.toString()) + "; "
				+ "Right: " + (right == null ? "null" : right.toString());
	}
	
}
