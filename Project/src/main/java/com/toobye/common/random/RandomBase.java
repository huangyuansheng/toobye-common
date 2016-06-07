/*
 * Copyright 2015 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2015/05/16.
 * 
 */
package com.toobye.common.random;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.toobye.common.collection.Iterables;
import com.toobye.common.lang.Checks;

/**
 * <pre> 随机基础类.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2015/05/16  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class RandomBase {
	
	private RandomBase() { }

	private static final Random RND = new Random();
	
	/**
	 * <pre> 随机布尔值.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/18  huangys  Create
	 * </pre>
	 * 
	 * @return 布尔值
	 */
	@Nonnull
	public static boolean nextBoolean() {
		return nextInt(2) == 1 ? true : false;
	}
	
	/**
	 * <pre> 随机非负整数.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/16  huangys  Create
	 * </pre>
	 * 
	 * @return 非负整数
	 */
	@Nonnull
	public static int nextInt() {
		return RND.nextInt();
	}
	
	/**
	 * <pre> 随机[0, n).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/16  huangys  Create
	 * </pre>
	 * 
	 * @param n 限定范围
	 * @return [0, n)
	 */
	@Nonnull
	public static int nextInt(@Nonnull final int n) {
		return RND.nextInt(n);
	}
	
	/**
	 * <pre> 随机[min, max].
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/18  huangys  Create
	 * </pre>
	 * 
	 * @param min 最小值
	 * @param max 最大值
	 * @return [min, max]
	 */
	@Nonnull
	public static int nextInt(@Nonnull final int min, @Nonnull final int max) {
		Checks.matchThrow(min > max, "Max cannot be less than min.");
		return (int) (nextLong((max - min) + 1) + min);
	}
	
	/**
	 * <pre> 随机[-Integer.MAX_VALUE, Integer.MAX_VALUE].
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/10/15  huangys  Create
	 * </pre>
	 * 
	 * @return [-Integer.MAX_VALUE, Integer.MAX_VALUE]
	 */
	@Nonnull
	public static int nextIntSigned() {
		return nextInt(-Integer.MAX_VALUE, Integer.MAX_VALUE);
	}
	
	/**
	 * <pre> 随机[-n, n].
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/10/15  huangys  Create
	 * </pre>
	 * 
	 * @param n 限定范围
	 * @return [-n, n]
	 */
	@Nonnull
	public static int nextIntSigned(final int n) {
		Checks.nonPositiveThrow(n);
		return nextInt(-n, n);
	}
	
	/**
	 * <pre> 随机非负长整型.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/16  huangys  Create
	 * </pre>
	 * 
	 * @return 非负长整型
	 */
	@Nonnull
	public static long nextLong() {
		return RND.nextLong();
	}
	
	/**
	 * <pre> 随机[0, n).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/16  huangys  Create
	 * </pre>
	 * 
	 * @param n 限定范围
	 * @return [0, n)
	 */
	@Nonnull
	public static long nextLong(@Nonnull final long n) {
		Checks.nonPositiveThrow(n);
		return (long) (nextDouble() * n);
	}
	
	/**
	 * <pre> 随机[min, max].
	 * max - min有可能数值溢出异常。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/18  huangys  Create
	 * </pre>
	 * 
	 * @param min 最小值
	 * @param max 最大值
	 * @return [min, max]
	 */
	@Nonnull
	public static long nextLong(@Nonnull final long min, @Nonnull final long max) {
		Checks.matchThrow(min > max, "Max cannot be less than min.");
		return nextLong((max - min) + 1) + min;
	}
	
	/**
	 * <pre> 随机[0, 1)浮点数.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/16  huangys  Create
	 * </pre>
	 * 
	 * @return [0, 1)浮点数
	 */
	@Nonnull
	public static double nextDouble() {
		return RND.nextDouble();
	}
	
	/**
	 * <pre> 随机字节数组.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/16  huangys  Create
	 * </pre>
	 * 
	 * @param bytes 待修改字节数组
	 */
	@Nonnull
	public static void nextBytes(@Nonnull final byte[] bytes) {
		Checks.nullThrow(bytes);
		RND.nextBytes(bytes);
	}
	
	/**
	 * <pre> 随机分配.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/18  huangys  Create
	 * </pre>
	 * 
	 * @param total 总数
	 * @param pieces 份数
	 * @param min 最小分配数
	 * @return 分配结果
	 */
	@Nonnull
	public static int[] randomSplit(@Nonnull final int total, @Nonnull final int pieces, @Nonnull final int min) {
		Checks.nonPositiveThrow(pieces);
		Checks.negativeThrow(min);
		int left = total - min * pieces;
		Checks.negativeThrow(left, "Total is not enough for min setting.");
		int[] ret = new int[pieces];
		// 取分割点
		List<Integer> pointList = new ArrayList<>();
		for (int i = 0; i < pieces - 1; i++) {
			pointList.add(nextInt(left + 1));
		}
		// 排序分割点
		Collections.sort(pointList);
		pointList.add(left);
		int last = 0;
		for (int i = 0; i < pieces; i++) {
			int point = pointList.get(i);
			ret[i] = min + pointList.get(i) - last;
			last = point;
		}
		return ret;
	}
	
	/**
	 * <pre> 随机分配.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/18  huangys  Create
	 * </pre>
	 * 
	 * @param total 总数
	 * @param pieces 份数
	 * @param min 最小分配数
	 * @param max 最大分配数
	 * @return 分配结果
	 */
	@Nonnull
	public static int[] randomSplit(@Nonnull final int total, @Nonnull final int pieces, @Nonnull final int min, @Nonnull final int max) {
		Checks.nonPositiveThrow(pieces);
		Checks.negativeThrow(min);
		Checks.matchThrow(min > max, "Max cannot be less than min.");
		int left = total - min * pieces;
		Checks.negativeThrow(left, "Total is not enough for min setting.");
		Checks.matchThrow(total > max * pieces, "Total is too much for max setting.");
		int[] ret = new int[pieces];
		// 取分割点
		List<Integer> pointList = new ArrayList<>();
		for (int i = 0; i < pieces - 1; i++) {
			pointList.add(nextInt(left + 1));
		}
		// 排序分割点
		Collections.sort(pointList);
		// 增加末尾分割点
		pointList.add(left);
		int last = 0;
		// 首轮分配，并获取超配数量
		int over = 0;
		for (int i = 0; i < pieces; i++) {
			int point = pointList.get(i);
			int temp = min + pointList.get(i) - last;
			if (temp > max) {
				ret[i] = max;
				over += temp - max;
			} else {
				ret[i] = temp;
			}
			last = point;
		}
		// 分配超配
		int pos = nextInt(pieces - 1);
		while (over > 0) {
			if (ret[pos] < max) {
				ret[pos]++;
				over--;
			}
			pos++;
			if (pos == pieces) {
				pos = 0;
			}
		}
		return ret;
	}

	/**
	 * <pre> 数组内随机.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/29  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 元素类型
	 * @param arr 数组
	 * @return 数组元素
	 */
	@Nullable
	public static <T> T random(@Nonnull final T[] arr) {
		Checks.emptyThrow(arr);
		return arr[nextInt(arr.length)];
	}
	
	/**
	 * <pre> 集合内随机.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/29  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 元素类型
	 * @param iterable 可迭代对象
	 * @return 列表元素
	 */
	@Nonnull
	public static <T> T random(@Nonnull final Iterable<T> iterable) {
		Checks.emptyThrow(iterable);
		return Iterables.get(iterable, nextInt(Iterables.size(iterable)));
	}
	
}
