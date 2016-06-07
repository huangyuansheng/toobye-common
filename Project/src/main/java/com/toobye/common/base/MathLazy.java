/*
 * Copyright 2014 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2014/06/28.
 * 
 */
package com.toobye.common.base;

import javax.annotation.Nonnull;

import org.apache.commons.math.util.MathUtils;

/**
 * <pre> 懒人算术.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2014/06/28  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class MathLazy {
	
	private MathLazy() { }
	
	/**
	 * <pre> 四舍五入，保留2位小数.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/01/09  huangys  Create
	 * </pre>
	 * 
	 * @param d 数值
	 * @return 四舍五入，保留2位小数
	 */
	public static double round(final double d) {
		return MathUtils.round(d, 2);
	}
	
	/**
	 * <pre> 相除，结果保留2位小数.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/11/09  huangys  Create
	 * </pre>
	 * 
	 * @param a 数值1
	 * @param b 数值2
	 * @return 相除结果
	 */
	public static double div(final String a, final String b) {
		return div(Double.parseDouble(a), Double.parseDouble(b));
	}
	
	/**
	 * <pre> 相除，结果保留2位小数.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/11/09  huangys  Create
	 * </pre>
	 * 
	 * @param a 数值1
	 * @param b 数值2
	 * @return 相除结果
	 */
	public static double div(final double a, final double b) {
		return MathUtils.round(a / b, 2);
	}
	
	/**
	 * <pre> 相乘，结果保留2位小数.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/11/09  huangys  Create
	 * </pre>
	 * 
	 * @param a 数值1
	 * @param b 数值2
	 * @return 相乘结果
	 */
	public static double mul(final String a, final String b) {
		return mul(Double.parseDouble(a), Double.parseDouble(b));
	}
	
	/**
	 * <pre> 相乘，结果保留2位小数.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/11/09  huangys  Create
	 * </pre>
	 * 
	 * @param a 数值1
	 * @param b 数值2
	 * @return 相乘结果
	 */
	public static double mul(final double a, final double b) {
		return MathUtils.round(a * b, 2);
	}

	/**
	 * <pre> 相除并返回百分比结果，保留2位小数.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/11/09  huangys  Create
	 * </pre>
	 * 
	 * @param a 数值1
	 * @param b 数值2
	 * @return 相除结果
	 */
	public static double divPercent(final double a, final double b) {
		return MathUtils.round(a * 100 / b, 2);
	}
	
	/**
	 * <pre> 相乘并返回百分比结果，保留2位小数.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/11/09  huangys  Create
	 * </pre>
	 * 
	 * @param a 数值1
	 * @param b 数值2
	 * @return 相乘结果
	 */
	public static double mulPercent(final double a, final double b) {
		return MathUtils.round(a * b * 100, 2);
	}
	
	/**
	 * <pre> 计算差异百分比，保留2位小数.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/11/09  huangys  Create
	 * </pre>
	 * 
	 * @param orig 初始值
	 * @param now 当前值
	 * @return 差异百分比
	 */
	public static double changePercent(final double orig, final double now) {
		return divPercent(now - orig, orig);
	}
	
	/**
	 * <pre> 最小值.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/11/09  huangys  Create
	 * </pre>
	 * 
	 * @param intArray 数值
	 * @return 最小值
	 */
	public static int min(final int... intArray) {
		int ret = Integer.MAX_VALUE;
		for (int i : intArray) {
			ret = Math.min(ret, i);
		}
		return ret;
	}
	
	/**
	 * <pre> 最小值.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/11/09  huangys  Create
	 * </pre>
	 * 
	 * @param longArray 数值
	 * @return 最小值
	 */
	public static long min(final long... longArray) {
		long ret = Long.MAX_VALUE;
		for (long l : longArray) {
			ret = Math.min(ret, l);
		}
		return ret;
	}
	
	/**
	 * <pre> 最小值.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/11/09  huangys  Create
	 * </pre>
	 * 
	 * @param doubleArray 数值
	 * @return 最小值
	 */
	public static double min(final double... doubleArray) {
		double ret = Long.MAX_VALUE;
		for (double d : doubleArray) {
			ret = Math.min(ret, d);
		}
		return ret;
	}
	
	/**
	 * <pre> 最大值.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/11/09  huangys  Create
	 * </pre>
	 * 
	 * @param intArray 数值
	 * @return 最大值
	 */
	public static int max(final int... intArray) {
		int ret = Integer.MIN_VALUE;
		for (int i : intArray) {
			ret = Math.max(ret, i);
		}
		return ret;
	}
	
	/**
	 * <pre> 最大值.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/11/09  huangys  Create
	 * </pre>
	 * 
	 * @param longArray 数值
	 * @return 最大值
	 */
	public static long max(final long... longArray) {
		long ret = Long.MIN_VALUE;
		for (long l : longArray) {
			ret = Math.max(ret, l);
		}
		return ret;
	}
	
	/**
	 * <pre> 最大值.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/11/09  huangys  Create
	 * </pre>
	 * 
	 * @param doubleArray 数值
	 * @return 最大值
	 */
	public static double max(final double... doubleArray) {
		double ret = Long.MIN_VALUE;
		for (double d : doubleArray) {
			ret = Math.max(ret, d);
		}
		return ret;
	}
	
	/**
	 * <pre> sin.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/24  huangys  Create
	 * </pre>
	 * 
	 * @param degree 角度
	 * @return sin
	 */
	@Nonnull
	public static double sinDegree(@Nonnull final double degree) {
		return Math.sin(Math.toRadians(degree));
	}
	
	/**
	 * <pre> cos.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/24  huangys  Create
	 * </pre>
	 * 
	 * @param degree 角度
	 * @return cos
	 */
	@Nonnull
	public static double cosDegree(@Nonnull final double degree) {
		return Math.cos(Math.toRadians(degree));
	}
	
	/**
	 * <pre> tan.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/24  huangys  Create
	 * </pre>
	 * 
	 * @param degree 角度
	 * @return tan
	 */
	@Nonnull
	public static double tanDegree(@Nonnull final double degree) {
		return Math.tan(Math.toRadians(degree));
	}
	
	/**
	 * <pre> asin.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/24  huangys  Create
	 * </pre>
	 * 
	 * @param value 值 
	 * @return 角度
	 */
	@Nonnull
	public static double asinDegree(@Nonnull final double value) {
		return Math.toDegrees(Math.asin(value));
	}
	
	/**
	 * <pre> acos.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/24  huangys  Create
	 * </pre>
	 * 
	 * @param value 值 
	 * @return 角度
	 */
	@Nonnull
	public static double acosDegree(@Nonnull final double value) {
		return Math.toDegrees(Math.acos(value));
	}
	
	/**
	 * <pre> atan.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/24  huangys  Create
	 * </pre>
	 * 
	 * @param value 值 
	 * @return 角度
	 */
	@Nonnull
	public static double atanDegree(@Nonnull final double value) {
		return Math.toDegrees(Math.atan(value));
	}
	
}
