/*
 * Copyright 2013 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2013/08/09.
 * 
 */
package com.toobye.common.time;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Nonnull;

import com.toobye.common.lang.Checks;

/**
 * <pre> 时间修改工具类.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2013/08/09  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class Dates {

	private Dates() { }
	
	/**
	 * <pre> 大部分方法的实现来源，可从这里更多实用方法.
	 * 
	 * Modification History:
	 * Date        Author   Version   Action
	 * 2014/01/01  huangys  v1.0      Create
	 * </pre>
	 * 
	 */
	public static final class INSTANCE extends org.apache.commons.lang3.time.DateUtils { };
	
	/**
	 * <pre> 获得年.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/01  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @return 年份
	 */
	@Nonnull
	public static int getYear(@Nonnull final Date date) {
		return get(date, Calendar.YEAR);
	}
	
	/**
	 * <pre> 获得月，首月为0.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/01  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @return 月份
	 */
	@Nonnull
	public static int getMonth(@Nonnull final Date date) {
		return get(date, Calendar.MONTH);
	}
	
	/**
	 * <pre> 获得月，首月为1.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/01  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @return 月份
	 */
	@Nonnull
	public static int getMonthNature(@Nonnull final Date date) {
		return getMonth(date) + 1;
	}
	
	/**
	 * <pre> 获得年内第几星期.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/01  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @return 年内第几星期
	 */
	@Nonnull
	public static int getWeekOfYear(@Nonnull final Date date) {
		return get(date, Calendar.WEEK_OF_YEAR);
	}
	
	/**
	 * <pre> 获得月内第几星期.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/01  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @return 月内第几星期
	 */
	@Nonnull
	public static int getWeekOfMonth(@Nonnull final Date date) {
		return get(date, Calendar.WEEK_OF_MONTH);
	}
	
	/**
	 * <pre> 获得年内第几天.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/01  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @return 年内第几天
	 */
	@Nonnull
	public static int getDayOfYear(@Nonnull final Date date) {
		return get(date, Calendar.DAY_OF_YEAR);
	}
	
	/**
	 * <pre> 获得星期几.
	 * 日一二三四五六：1234567
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/01  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @return 星期几
	 */
	@Nonnull
	public static int getDayOfWeek(@Nonnull final Date date) {
		return get(date, Calendar.DAY_OF_WEEK);
	}
	
	/**
	 * <pre> 获得星期几(自然).
	 * 一二三四五六日：1234567
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/01  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @return 星期几
	 */
	@Nonnull
	public static int getDayOfWeekNature(@Nonnull final Date date) {
		int ret = getDayOfWeek(date);
		return ret - 1 == 0 ? 7 : ret - 1;
	}
	
	/**
	 * <pre> 获得日期（月内第几日）.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/01  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @return 日期
	 */
	@Nonnull
	public static int getDate(@Nonnull final Date date) {
		return get(date, Calendar.DATE);
	}
	
	/**
	 * <pre> 获得小时.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/01  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @return 小时
	 */
	@Nonnull
	public static int getHour(@Nonnull final Date date) {
		return get(date, Calendar.HOUR);
	}
	
	/**
	 * <pre> 获得分钟.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/01  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @return 分钟
	 */
	@Nonnull
	public static int getMinute(@Nonnull final Date date) {
		return get(date, Calendar.MINUTE);
	}
	
	/**
	 * <pre> 获得秒.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/01  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @return 秒
	 */
	@Nonnull
	public static int getSecond(@Nonnull final Date date) {
		return get(date, Calendar.SECOND);
	}
	
	/**
	 * <pre> 获得毫秒.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/01  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @return 毫秒
	 */
	@Nonnull
	public static int getMillisecond(@Nonnull final Date date) {
		return get(date, Calendar.MILLISECOND);
	}
	
	/**
	 * <pre> 获取时间对应元素.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/01  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @param calendar 对应元素
	 * @return 对应元素值
	 */
	@Nonnull
	public static int get(@Nonnull final Date date, @Nonnull final int calendar) {
		Checks.nullThrow(date);
		return INSTANCE.toCalendar(date).get(calendar);
	}
	
	/**
	 * <pre> 截断年（不含）以下信息.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/01  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @return 截取后时间
	 */
	@Nonnull
	public static Date truncYear(@Nonnull final Date date) {
		return trunc(date, Calendar.YEAR);
	}
	
	/**
	 * <pre> 截断月（不含）以下信息.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/01  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @return 截取后时间
	 */
	@Nonnull
	public static Date truncMonth(@Nonnull final Date date) {
		return trunc(date, Calendar.MONTH);
	}
	
	/**
	 * <pre> 截断日（不含）以下信息.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/01  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @return 截取后时间
	 */
	@Nonnull
	public static Date truncDate(@Nonnull final Date date) {
		return trunc(date, Calendar.DATE);
	}
	
	/**
	 * <pre> 截断小时（不含）以下信息.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/01  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @return 截取后时间
	 */
	@Nonnull
	public static Date truncHour(@Nonnull final Date date) {
		return trunc(date, Calendar.HOUR);
	}
	
	/**
	 * <pre> 截断分（不含）以下信息.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/01  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @return 截取后时间
	 */
	@Nonnull
	public static Date truncMinute(@Nonnull final Date date) {
		return trunc(date, Calendar.MINUTE);
	}
	
	/**
	 * <pre> 截断秒（不含）以下信息.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/01  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @return 截取后时间
	 */
	@Nonnull
	public static Date truncSecond(@Nonnull final Date date) {
		return trunc(date, Calendar.SECOND);
	}
	
	/**
	 * <pre> 截断.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/01  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @param calendar 对应元素
	 * @return 截取后时间
	 */
	@Nonnull
	public static Date trunc(@Nonnull final Date date, @Nonnull final int calendar) {
		Checks.nullThrow(date);
		return INSTANCE.truncate(date, calendar);
	}
	
	/**
	 * <pre> 毫秒偏移.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/09  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @param amount 偏移量
	 * @return 偏移后时间
	 */
	@Nonnull
	public static Date addMilliseconds(@Nonnull final Date date, @Nonnull final int amount) {
		return add(date, Calendar.MILLISECOND, amount);
	}
	
	/**
	 * <pre> 秒偏移.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/09  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @param amount 偏移量
	 * @return 偏移后时间
	 */
	@Nonnull
	public static Date addSeconds(@Nonnull final Date date, @Nonnull final int amount) {
		return add(date, Calendar.SECOND, amount);
	}
	
	/**
	 * <pre> 分偏移.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/09  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @param amount 偏移量
	 * @return 偏移后时间
	 */
	@Nonnull
	public static Date addMinutes(@Nonnull final Date date, @Nonnull final int amount) {
		return add(date, Calendar.MINUTE, amount);
	}
	
	/**
	 * <pre> 小时偏移.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/09  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @param amount 偏移量
	 * @return 偏移后时间
	 */
	@Nonnull
	public static Date addHours(@Nonnull final Date date, @Nonnull final int amount) {
		return add(date, Calendar.HOUR_OF_DAY, amount);
	}
	
	/**
	 * <pre> 天偏移.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/09  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @param amount 偏移量
	 * @return 偏移后时间
	 */
	@Nonnull
	public static Date addDays(@Nonnull final Date date, @Nonnull final int amount) {
		return add(date, Calendar.DAY_OF_MONTH, amount);
	}
	
	/**
	 * <pre> 周偏移.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/09  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @param amount 偏移量
	 * @return 偏移后时间
	 */
	@Nonnull
	public static Date addWeeks(@Nonnull final Date date, @Nonnull final int amount) {
		return add(date, Calendar.WEEK_OF_YEAR, amount);
	}
	
	/**
	 * <pre> 月偏移.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/09  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @param amount 偏移量
	 * @return 偏移后时间
	 */
	@Nonnull
	public static Date addMonths(@Nonnull final Date date, @Nonnull final int amount) {
		return add(date, Calendar.MONTH, amount);
	}
	
	/**
	 * <pre> 年偏移.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/09  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @param amount 偏移量
	 * @return 偏移后时间
	 */
	@Nonnull
	public static Date addYears(@Nonnull final Date date, @Nonnull final int amount) {
		return add(date, Calendar.YEAR, amount);
	}
	
    /**
     * Adds to a date returning a new object.
     * The original {@code Date} is unchanged.
     *
     * @param date  the date, not null
     * @param calendarField  the calendar field to add to
     * @param amount  the amount to add, may be negative
     * @return the new {@code Date} with the amount added
     */
	@Nonnull
    public static Date add(@Nonnull final Date date, @Nonnull final int calendarField, @Nonnull final int amount) {
    	Checks.nullThrow(date);
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarField, amount);
        return c.getTime();
    }
	
	/**
	 * <pre> 设置年.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/01  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @param amount 数值
	 * @return 修改后时间
	 */
	@Nonnull
	public static Date setYears(@Nonnull final Date date, @Nonnull final int amount) {
		return set(date, Calendar.YEAR, amount);
	}
	
	/**
	 * <pre> 设置月，首月为0.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/01  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @param amount 数值
	 * @return 修改后时间
	 */
	@Nonnull
	public static Date setMonths(@Nonnull final Date date, @Nonnull final int amount) {
		return set(date, Calendar.MONTH, amount);
	}
	
	/**
	 * <pre> 设置月，首月为1.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/01  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @param amount 数值
	 * @return 修改后时间
	 */
	@Nonnull
	public static Date setMonthsNature(@Nonnull final Date date, @Nonnull final int amount) {
		return set(date, Calendar.MONTH, amount - 1);
	}
	
	/**
	 * <pre> 设置日.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/01  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @param amount 数值
	 * @return 修改后时间
	 */
	@Nonnull
	public static Date setDays(@Nonnull final Date date, @Nonnull final int amount) {
		return set(date, Calendar.DAY_OF_MONTH, amount);
	}
	
	/**
	 * <pre> 设置小时.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/01  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @param amount 数值
	 * @return 修改后时间
	 */
	@Nonnull
	public static Date setHours(@Nonnull final Date date, @Nonnull final int amount) {
		return set(date, Calendar.HOUR, amount);
	}
	
	/**
	 * <pre> 设置分钟.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/01  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @param amount 数值
	 * @return 修改后时间
	 */
	@Nonnull
	public static Date setMinutes(@Nonnull final Date date, @Nonnull final int amount) {
		return set(date, Calendar.MINUTE, amount);
	}
	
	/**
	 * <pre> 设置秒.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/01  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @param amount 数值
	 * @return 修改后时间
	 */
	@Nonnull
	public static Date setSeconds(@Nonnull final Date date, @Nonnull final int amount) {
		return set(date, Calendar.SECOND, amount);
	}
	
	/**
	 * <pre> 设置毫秒.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/01  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @param amount 数值
	 * @return 修改后时间
	 */
	@Nonnull
	public static Date setMilliseconds(@Nonnull final Date date, @Nonnull final int amount) {
		return set(date, Calendar.MILLISECOND, amount);
	}
	
	/**
	 * <pre> 设置毫秒.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/01  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @param calendar 对应元素
	 * @param amount 数值
	 * @return 修改后时间
	 */
	public static Date set(@Nonnull final Date date, @Nonnull final int calendar, @Nonnull final int amount) {
		Checks.nullThrow(date);
		final Calendar c = Calendar.getInstance();
        c.setLenient(false);
        c.setTime(date);
        c.set(calendar, amount);
        return c.getTime();
    }

}
