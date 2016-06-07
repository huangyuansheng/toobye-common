/*
 * Copyright 2015 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2015/12/16.
 * 
 */
package com.toobye.common.time;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Nonnull;

import org.quartz.CronExpression;

import com.toobye.common.concurrent.TimeSlice;
import com.toobye.common.lang.Checks;
import com.toobye.common.string.StringArray;

/**
 * <pre> 时间Cron表达式解析.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2015/12/16  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class DateCronParser {
	
	private DateCronParser() { }

	private static final int GUESS_TIMES = 5;
	/**
	 * <pre> 猜测Cron表达式的时间间隔，返回0表示无法解析.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/16  huangys  Create
	 * </pre>
	 * 
	 * @param cronExp cron表达式
	 * @return 单个时间周期总秒数
	 */
	@Nonnull
	public static long guessInterval(@Nonnull final String cronExp) {
		Checks.nullThrow(cronExp);
		CronExpression exp = getCronExp(cronExp);
		
		Date date = exp.getNextValidTimeAfter(new Date());
		Date next = exp.getNextValidTimeAfter(date);
		TimeSlice interval = DateComparator.dateMinus(next, date);
		date = next;
		for (int i = 0; i < GUESS_TIMES; i++) {
			next = exp.getNextValidTimeAfter(date);
			if (!DateComparator.dateMinus(next, date).equals(interval)) {
				return 0;
			}
		}
		return interval.toSeconds();
	}
	
	/**
	 * <pre> 获得Cron表达式对象.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/16  huangys  Create
	 * </pre>
	 * 
	 * @param cronExp cron表达式
	 * @return Cron表达式对象
	 */
	@Nonnull
	public static CronExpression getCronExp(@Nonnull final String cronExp) {
		Checks.nullThrow(cronExp);
		try {
			String exp = cronExp.trim();
			// 自动纠正表达式
			// 第4位和第六位不能同时有效，需指定其中一位为问号
			String[] arr = exp.split(" ");
			if (arr[5].equals("*")) {
				arr[5] = "?";
			} else if (arr[3].equals("*")) {
				arr[3] = "?";
			}
			exp = StringArray.join(arr, " ");
			if (exp.endsWith("* *")) {
				exp = exp.substring(0, exp.length() - 1) + "?";
			}
			return new CronExpression(exp);
		} catch (ParseException e) {
			throw new RuntimeException("Error Cron Expression.", e);
		}
	}
	
	/**
	 * <pre> 获得CRON粒度下一周期的期初时间.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/09  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @param cronExp cron表达式
	 * @return 时间
	 */
	@Nonnull
	public static Date getNext(@Nonnull final Date date, @Nonnull final String cronExp) {
		return get(date, cronExp, 1);
	}
	
	/**
	 * <pre> 获得CRON粒度当前周期的期初时间.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/09  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @param cronExp cron表达式
	 * @return 时间
	 */
	@Nonnull
	public static Date getCurr(@Nonnull final Date date, @Nonnull final String cronExp) {
		return get(date, cronExp, 0);
	}
	
	/**
	 * <pre> 获得CRON粒度上一周期的期初时间.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/09  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @param cronExp cron表达式
	 * @return 时间
	 */
	@Nonnull
	public static Date getLast(@Nonnull final Date date, @Nonnull final String cronExp) {
		return get(date, cronExp, -1);
	}
	
	/**
	 * <pre> 获得CRON粒度的期初时间.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/09  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @param cronExp cron表达式
	 * @param addPeriods 偏移周期
	 * @return 时间
	 */
	@Nonnull
	public static Date get(@Nonnull final Date date, @Nonnull final String cronExp, @Nonnull final int addPeriods) {
		Checks.nullThrow(date);
		Checks.nullThrow(cronExp);
		
		CronExpression exp = getCronExp(cronExp);
		Date ret = Dates.truncSecond(date);
		if (addPeriods > 0) {
			for (int i = 0; i < addPeriods; i++) {
				ret = exp.getNextValidTimeAfter(ret);
			}
			return ret;
		} else {
			return getPast(ret, exp, addPeriods);
		}
	}
	
	/**
	 * <pre> 获得历史周期的期初时间.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/05/11  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @param exp cron表达式
	 * @param addPeriods 历史周期，是非正数
	 * @return 历史周期的期初时间
	 */
	private static Date getPast(@Nonnull final Date date, @Nonnull final CronExpression exp, @Nonnull final int addPeriods) {
		Checks.positiveThrow(addPeriods);
		if (exp.isSatisfiedBy(date)) {
			if (addPeriods == 0) {
				return date;
			} else {
				Tmp tmp = new Tmp();
				tmp.date = date;
				tmp.exp = exp;
				tmp.addPeriods = -addPeriods;
				return getPastInternal(tmp);
			}
		}
		Tmp tmp = new Tmp();
		tmp.date = exp.getNextValidTimeAfter(date);
		tmp.exp = exp;
		tmp.addPeriods = -addPeriods + 1;
		return getPastInternal(tmp);
	}
	
	/**
	 * <pre> 用于方法getPastInternal计算过程中传递参数.
	 * 
	 * Modification History:
	 * Date        Author   Version   Action
	 * 2016/05/11  huangys  v1.0      Create
	 * </pre>
	 * 
	 */
	private static class Tmp {
		private Date date;
		private CronExpression exp;
		private int addPeriods;
		private int interval = -10;
	}
	
	private static final int SPEED = 10;
	private static Date getPastInternal(@Nonnull final Tmp tmp) {
		List<Date> list = new ArrayList<>();
		while (true) {
			Date bgnTime = Dates.addSeconds(tmp.date, tmp.interval);
			Date nextTime = tmp.exp.getNextValidTimeAfter(bgnTime);
			if (!nextTime.equals(tmp.date)) {
				list.clear();
				list.add(nextTime);
				while (true) {
					nextTime = tmp.exp.getNextValidTimeAfter(nextTime);
					if (nextTime.equals(tmp.date)) {
						break;
					}
					list.add(nextTime);
				}
				if (list.size() >= tmp.addPeriods) {
					return list.get(list.size() - tmp.addPeriods);
				}
				tmp.date = list.get(0);
				tmp.addPeriods = tmp.addPeriods - list.size();
				continue;
			} else {
				tmp.interval = tmp.interval * SPEED;
			}
		}
	}
	
}
