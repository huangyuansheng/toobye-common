/*
 * Copyright 2015 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2015/12/15.
 * 
 */
package com.toobye.common.time;

import java.util.Date;

import javax.annotation.Nonnull;

import com.toobye.common.lang.Checks;

/**
 * <pre> 时间.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2015/12/15  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class Time implements Comparable<Time> {
	
	private int value;

	private Time(final int value) {
		this.value = value;
	}
	
	/**
	 * <pre> 获得时间值.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/15  huangys  Create
	 * </pre>
	 * 
	 * @return 时间值
	 */
	@Nonnull
	public int getValue() {
		return value;
	}
	
	/**
	 * <pre> 实例化时间对象.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/15  huangys  Create
	 * </pre>
	 * 
	 * @param value 时间值HHmmss
	 * @return 时间
	 */
	@Nonnull
	public static Time of(@Nonnull final int value) {
		return of(String.format("%06d", value));
	}
	
	/**
	 * <pre> 实例化时间对象.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/15  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @return 时间
	 */
	@Nonnull
	public static Time of(@Nonnull final Date date) {
		Checks.nullThrow(date);
		return new Time(Integer.parseInt(DateFormat.get(date, "HHmmss")));
	}
	
	/**
	 * <pre> 实例化时间对象.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/15  huangys  Create
	 * </pre>
	 * 
	 * @param timeStr 时间字符串HHmmss或HH:mm:ss
	 * @return 时间
	 */
	@Nonnull
	public static Time of(@Nonnull final String timeStr) {
		Checks.emptyThrow(timeStr);
		if (timeStr.trim().length() == 6) {
			return of(DateFormat.parse(timeStr.trim(), "HHmmss"));
		} else {
			return of(DateFormat.parse(timeStr.trim(), "HH:mm:ss"));
		}
	}
	
	/**
	 * <pre> 实例化系统当前时间对象.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/15  huangys  Create
	 * </pre>
	 * 
	 * @return 时间
	 */
	@Nonnull
	public static Time now() {
		return of(new Date());
	}
	
	/**
	 * <pre> 是否在指定时间之前.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/15  huangys  Create
	 * </pre>
	 * 
	 * @param another 指定时间
	 * @return 是否
	 */
	public boolean before(final Time another) {
		Checks.nullThrow(another);
		return value < another.value;
	}
	
	/**
	 * <pre> 是否在指定时间之后.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/15  huangys  Create
	 * </pre>
	 * 
	 * @param another 指定时间
	 * @return 是否
	 */
	public boolean after(final Time another) {
		Checks.nullThrow(another);
		return value > another.value;
	}
	
	/**
	 * <pre> 是否在指定时间之间.
	 * 可以跨天式比较，即开始时间可以大于结束时间。
	 * 如3点是在20点与8点之间
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/15  huangys  Create
	 * </pre>
	 * 
	 * @param begin 开始时间
	 * @param end 结束时间
	 * @return 是否
	 */
	public boolean between(final Time begin, final Time end) {
		Checks.nullThrow(begin);
		Checks.nullThrow(end);
		if (begin.value <= end.value) {
			if (begin.value  <= value && end.value  >= value) {
				return true;
			}
		} else {
			if (begin.value  <= value || end.value  >= value) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * <pre> 是否在指定时间之间.
	 * 不可以跨天式比较，即开始时间必须小于结束时间。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/15  huangys  Create
	 * </pre>
	 * 
	 * @param begin 开始时间
	 * @param end 结束时间
	 * @return 是否
	 */
	public boolean betweenStrict(final Time begin, final Time end) {
		Checks.nullThrow(begin);
		Checks.nullThrow(end);
		Checks.matchThrow(begin.value > end.value, "Begin must be less than end.");
		return begin.value  <= value && end.value  >= value;
	}
	
	@Override
	public int compareTo(final Time another) {
		Checks.nullThrow(another);
		return Integer.valueOf(this.value).compareTo(another.value);
	}
	
	@Override
	public boolean equals(final Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj instanceof Time) {
			return value == ((Time) obj).value;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return Integer.valueOf(value).hashCode();
	}
	
	@Override
	public String toString() {
		String str = String.format("%06d", value);
		return str.substring(0, 2) + ":" + str.substring(2, 4) + ":" + str.substring(4, 6);
	}
	
	/**
	 * <pre> 当前时间是否允许接收SMS.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/05/05  huangys  Create
	 * </pre>
	 * 
	 * @param allowTime 允许时间，格式HH:mm-HH:mm，支持多项以空格间隔
	 * @return 是否允许
	 */
	public static boolean isAllow(@Nonnull final String allowTime) {
		String[] arr = allowTime.split(" ");
		boolean ret = false;
		for (String one : arr) {
			String[] tmp = one.split("-");
			if (Time.now().between(Time.of(tmp[0] + ":00"), Time.of(tmp[1] + ":00"))) {
				return true;
			}
		}
		return ret;
	}
	
}
