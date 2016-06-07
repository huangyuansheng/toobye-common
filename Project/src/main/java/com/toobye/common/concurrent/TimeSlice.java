/*
 * Copyright 2015 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2015/05/05.
 * 
 */
package com.toobye.common.concurrent;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.toobye.common.lang.Checks;

/**
 * <pre> 时间片.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2015/05/05  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class TimeSlice implements Cloneable, Comparable<TimeSlice> {
	
	private TimeUnit unit;
	private long value;
	
	private TimeSlice(@Nonnull final TimeUnit unit, @Nonnull final long value) {
		this.unit = unit;
		this.value = value;
	}
	
	/**
	 * <pre> 获得时间单位.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/05  huangys  Create
	 * </pre>
	 * 
	 * @return 时间单位
	 */
	public TimeUnit getUnit() {
		return unit;
	}
	
	/**
	 * <pre> 获得时间值.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/05  huangys  Create
	 * </pre>
	 * 
	 * @return 时间值
	 */
	public long getValue() {
		return value;
	}
	
	/**
	 * <pre> 加.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/07  huangys  Create
	 * </pre>
	 * 
	 * @param add 加数
	 */
	public void add(@Nonnull final TimeSlice add) {
		Checks.nullThrow(add);
		value = value + add.to(unit);
	}
	
	/**
	 * <pre> 减.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/07  huangys  Create
	 * </pre>
	 * 
	 * @param minus 减数
	 */
	public void minus(@Nonnull final TimeSlice minus) {
		Checks.nullThrow(minus);
		value = value - minus.to(unit);
	}
	
	/**
	 * <pre> 加于时间.
	 * 忽略毫秒以下精度。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/07  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @return 偏移后的时间
	 */
	@Nonnull
	public Date addTo(@Nonnull final Date date) {
		Checks.nullThrow(date);
		return new Date(date.getTime() + toMillis());
	}
	
	/**
	 * <pre> 减于时间.
	 * 忽略毫秒以下精度。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/07  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @return 偏移后的时间
	 */
	@Nonnull
	public Date minusTo(@Nonnull final Date date) {
		Checks.nullThrow(date);
		return new Date(date.getTime() - toMillis());
	}
	
	/**
	 * <pre> 沉睡时间片中设定的时间跨度.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/05  huangys  Create
	 * </pre>
	 * 
	 */
	public void sleep() {
		try {
			unit.sleep(value);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 沉睡（除去已用的时间片）.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/05  huangys  Create
	 * </pre>
	 * 
	 * @param timeUsed 已用的时间片
	 */
	public void sleepExceptUsed(@Nullable final TimeSlice timeUsed) {
		if (timeUsed == null) {
			sleep();
		} else {
			nanoSeconds(toNanos() - timeUsed.toNanos()).sleep();
		}
	}
	
	/**
	 * <pre> 沉睡.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/05  huangys  Create
	 * </pre>
	 * 
	 * @param bgnTime 开始时间
	 */
	public void sleepExceptUsed(@Nonnull final Date bgnTime) {
		Checks.nullThrow(bgnTime);
		sleepExceptUsed(milliSeconds(new Date().getTime() - bgnTime.getTime()));
	}
	
	/**
	 * <pre> 纳秒.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/05  huangys  Create
	 * </pre>
	 * 
	 * @param value 值
	 * @return 时间片
	 */
	@Nonnull
	public static TimeSlice nanoSeconds(@Nonnull final long value) {
		return new TimeSlice(TimeUnit.NANOSECONDS, value);
	}
	
	/**
	 * <pre> 微秒.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/05  huangys  Create
	 * </pre>
	 * 
	 * @param value 值
	 * @return 时间片
	 */
	@Nonnull
	public static TimeSlice microSeconds(@Nonnull final long value) {
		return new TimeSlice(TimeUnit.MICROSECONDS, value);
	}
	
	/**
	 * <pre> 毫秒.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/05  huangys  Create
	 * </pre>
	 * 
	 * @param value 值
	 * @return 时间片
	 */
	@Nonnull
	public static TimeSlice milliSeconds(@Nonnull final long value) {
		return new TimeSlice(TimeUnit.MILLISECONDS, value);
	}
	
	/**
	 * <pre> 秒.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/05  huangys  Create
	 * </pre>
	 * 
	 * @param value 值
	 * @return 时间片
	 */
	@Nonnull
	public static TimeSlice seconds(@Nonnull final long value) {
		return new TimeSlice(TimeUnit.SECONDS, value);
	}
	
	/**
	 * <pre> 分钟.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/05  huangys  Create
	 * </pre>
	 * 
	 * @param value 值
	 * @return 时间片
	 */
	@Nonnull
	public static TimeSlice minutes(@Nonnull final long value) {
		return new TimeSlice(TimeUnit.MINUTES, value);
	}
	
	/**
	 * <pre> 小时.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/05  huangys  Create
	 * </pre>
	 * 
	 * @param value 值
	 * @return 时间片
	 */
	@Nonnull
	public static TimeSlice hours(@Nonnull final long value) {
		return new TimeSlice(TimeUnit.HOURS, value);
	}
	
	/**
	 * <pre> 天.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/05  huangys  Create
	 * </pre>
	 * 
	 * @param value 值
	 * @return 时间片
	 */
	@Nonnull
	public static TimeSlice days(@Nonnull final long value) {
		return new TimeSlice(TimeUnit.DAYS, value);
	}
	
	/**
	 * <pre> 转为指定单位.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/05  huangys  Create
	 * </pre>
	 * 
	 * @param toUnit 指定单位
	 * @return 指定单位对应值
	 */
	@Nonnull
	public long to(@Nonnull final TimeUnit toUnit) {
		Checks.nullThrow(toUnit);
		switch (toUnit) {
		case NANOSECONDS:
			return unit.toNanos(value);
		case MICROSECONDS:
			return unit.toMicros(value);
		case MILLISECONDS:
			return unit.toMillis(value);
		case SECONDS:
			return unit.toSeconds(value);
		case MINUTES:
			return unit.toMinutes(value);
		case HOURS:
			return unit.toHours(value);
		case DAYS:
			return unit.toDays(value);
		default:
			throw new RuntimeException("Unknown Time Unit(" + unit + ")");
		}
	}
	
	/**
	 * <pre> 转为纳秒.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/05  huangys  Create
	 * </pre>
	 * 
	 * @return 纳秒值
	 */
	@Nonnull
	public long toNanos() {
		return to(TimeUnit.NANOSECONDS);
	}
	
	/**
	 * <pre> 转为微秒.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/05  huangys  Create
	 * </pre>
	 * 
	 * @return 微秒值
	 */
	@Nonnull
	public long toMicros() {
		return to(TimeUnit.MICROSECONDS);
	}
	
	/**
	 * <pre> 转为毫秒.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/05  huangys  Create
	 * </pre>
	 * 
	 * @return 毫秒值
	 */
	@Nonnull
	public long toMillis() {
		return to(TimeUnit.MILLISECONDS);
	}
	
	/**
	 * <pre> 转为秒.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/05  huangys  Create
	 * </pre>
	 * 
	 * @return 秒数
	 */
	@Nonnull
	public long toSeconds() {
		return to(TimeUnit.SECONDS);
	}
	
	/**
	 * <pre> 转为分钟.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/05  huangys  Create
	 * </pre>
	 * 
	 * @return 分钟数
	 */
	@Nonnull
	public long toMinutes() {
		return to(TimeUnit.MINUTES);
	}
	
	/**
	 * <pre> 转为小时.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/05  huangys  Create
	 * </pre>
	 * 
	 * @return 小时数
	 */
	@Nonnull
	public long toHours() {
		return to(TimeUnit.HOURS);
	}
	
	/**
	 * <pre> 转为天.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/05  huangys  Create
	 * </pre>
	 * 
	 * @return 天数
	 */
	@Nonnull
	public long toDays() {
		return to(TimeUnit.DAYS);
	}
	
	@Override
	public String toString() {
		return value + unit.toString();
	}
	
	@Override
	public TimeSlice clone() {
	    try {
	        return (TimeSlice) super.clone();
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	
	@Override
	public int compareTo(final TimeSlice another) {
		Checks.nullThrow(another);
		return Long.valueOf(toNanos()).compareTo(another.toNanos());
	}
	
	@Override
	public boolean equals(final Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj instanceof TimeSlice) {
			return toNanos() == ((TimeSlice) obj).toNanos();
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return (unit.toString() + value).hashCode();
	}
	
}
