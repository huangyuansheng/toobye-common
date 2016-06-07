/*
 * Copyright 2013 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2013/08/09.
 * 
 */
package com.toobye.common.time;

import com.toobye.common.base.Exceptions;
import com.toobye.common.dbenums.Freq;
import com.toobye.common.lang.Checks;
import com.toobye.common.string.StringUtils;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.lang3.time.DateUtils;

/**
 * <pre> 周期起始时间计算.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2013/08/09  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class DatePeriod {
	
	private DatePeriod() { }
	
	/**
	 * <pre> 获得指定周期的期初时间.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/09  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @param freq 时间频率
	 * @param freqDetail 时间频率扩展信息
	 * @param addPeriods 偏移周期
	 * @return 时间
	 */
	@Nonnull
	public static Date get(@Nonnull final Date date, @Nonnull final Freq freq, @Nullable final String freqDetail, @Nonnull final int addPeriods) {
		Checks.nullThrow(date);
		Checks.nullThrow(freq);
		switch (freq) {
		case CRON:
			return getCron(date, freqDetail, addPeriods);
		case SECONDS:
			return getSeconds(date, freqDetail, addPeriods);
		case DAY:
			Checks.notNullThrow(freqDetail);
			return getDay(date, addPeriods);
		case WEEK:
			return getWeek(date, freqDetail, addPeriods);
		case TENDAYS:
			Checks.notNullThrow(freqDetail);
			return getTendays(date, addPeriods);
		case MONTH:
			Checks.notNullThrow(freqDetail);
			return getMonth(date, addPeriods);
		case QUARTER:
			Checks.notNullThrow(freqDetail);
			return getQuarter(date, addPeriods);
		case HALFYEAR:
			Checks.notNullThrow(freqDetail);
			return getHalfyear(date, addPeriods);
		case YEAR:
			Checks.notNullThrow(freqDetail);
			return getYear(date, addPeriods);
		default:
			throw new RuntimeException("Unknown Frequency.");
		}
	}

	/**
	 * <pre> 获得当前周期的期初时间.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/09  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @param freq 时间频率
	 * @param freqDetail 时间频率扩展信息
	 * @return 时间
	 */
	@Nonnull
	public static Date getCurr(@Nonnull final Date date, @Nonnull final Freq freq, @Nullable final String freqDetail) {
		return get(date, freq, freqDetail, 0);
	}
	
	/**
	 * <pre> 获得上一周期的期初时间.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/09  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @param freq 时间频率
	 * @param freqDetail 时间频率扩展信息
	 * @return 时间
	 */
	@Nonnull
	public static Date getLast(@Nonnull final Date date, @Nonnull final Freq freq, @Nullable final String freqDetail) {
		return get(date, freq, freqDetail, -1);
	}

	/**
	 * <pre> 获得下一周期的期初时间.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/09  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @param freq 时间频率
	 * @param freqDetail 时间频率扩展信息
	 * @return 时间
	 */
	@Nonnull
	public static Date getNext(@Nonnull final Date date, @Nonnull final Freq freq, @Nullable final String freqDetail) {
		return get(date, freq, freqDetail, 1);
	}
	
	/**
	 * <pre> 获得秒粒度的期初时间.
	 * 秒粒度是日内的切片粒度，其每日的首个切片的期初时间为00:00:00。
	 * freqDetail为间隔秒数，该秒数必须能够整除86400（即单日）。
	 * 例freqDetail为3600，即代表小时粒度。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/09  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @param freqDetail 时间频率扩展信息
	 * @param addPeriods 偏移周期
	 * @return 时间
	 */
	@Nonnull
	public static Date getSeconds(@Nonnull final Date date, @Nonnull final String freqDetail, @Nonnull final int addPeriods) {
		Checks.nullThrow(date);
		Checks.nullThrow(freqDetail);
		int interval = Integer.parseInt(freqDetail);
		int seconds = (int) DateUtils.getFragmentInSeconds(date, Calendar.DATE);
		int num = seconds / interval;
		return Dates.addSeconds(Dates.truncDate(date), interval * (num + addPeriods));
	}
	
	/**
	 * <pre> 获得日粒度的期初时间.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/09  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @param addPeriods 偏移周期
	 * @return 时间
	 */
	@Nonnull
	public static Date getDay(@Nonnull final Date date, @Nonnull final int addPeriods) {
		Checks.nullThrow(date);
		return Dates.addDays(Dates.truncDate(date), addPeriods);
	}
	
	/**
	 * <pre> 获得周粒度的期初时间.
	 * 
	 * 空时默认为自然周，即周一至周日。
	 * 当取值为1～6，则对应每周截止至星期一～星期六。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/09  liulekai Create
	 * 2013/08/09  huangys  Modify
	 * </pre>
	 * 
	 * @param date 时间
	 * @param freqDetail 时间频率扩展信息
	 * @param addPeriods 偏移周期
	 * @return 时间
	 */
	@Nonnull
	public static Date getWeek(@Nonnull final Date date, @Nullable final String freqDetail, @Nonnull final int addPeriods) {
		Checks.nullThrow(date);
		// 检查freqDetail是否合法
		if (StringUtils.isEmpty(freqDetail) || freqDetail.matches("[1-6]{1}")) {
			// 星期一到星期日为1～7。
			int shift = StringUtils.isEmpty(freqDetail) ? 7 : Integer.parseInt(freqDetail);
			int weekDay = Dates.getDayOfWeekNature(date);
			return Dates.addDays(Dates.truncDate(date), (weekDay > shift ? 1 : -6) - weekDay + shift  +  7 * addPeriods);
		} else {
			throw new RuntimeException("Illegal freqDetail, freqDetail must be in 1-6 or NULL.");
		}
	}

	/**
	 * <pre> 获得旬粒度的期初时间.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/09  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @param addPeriods 偏移周期
	 * @return 时间
	 */
	@Nonnull
	public static Date getTendays(@Nonnull final Date date, @Nonnull final int addPeriods) {
		Checks.nullThrow(date);
		// 计算总旬数，包含的当月旬数（每月的旬数记为0～2）
		int dayOfMonth = Dates.getDate(date);
		int total = addPeriods;
		if (dayOfMonth <= 10) {
			total += 0;
		} else if (dayOfMonth <= 20) {
			total += 1;
		} else {
			total += 2;
		}

		// 计算可以折合多少个月份
		int tenDaysNum = total % 3;
		int monthNum = total / 3;
		// 负数特殊处理
		if (tenDaysNum < 0) {
			monthNum--;
			tenDaysNum = tenDaysNum + 3;
		}
		
		// 得出旬初日期
		Date ret = Dates.addMonths(Dates.truncMonth(date), monthNum);
		return Dates.addDays(ret, tenDaysNum * 10);
	}

	/**
	 * <pre> 获得月粒度的期初时间.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/09  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @param addPeriods 偏移周期
	 * @return 时间
	 */
	@Nonnull
	public static Date getMonth(@Nonnull final Date date, @Nonnull final int addPeriods) {
		Checks.nullThrow(date);
		return Dates.addMonths(Dates.truncMonth(date), addPeriods);
	}

	/**
	 * <pre> 获得季粒度的期初时间.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/09  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @param addPeriods 偏移周期
	 * @return 时间
	 */
	@Nonnull
	public static Date getQuarter(@Nonnull final Date date, @Nonnull final int addPeriods) {
		Checks.nullThrow(date);
		Date ret = Dates.addMonths(Dates.truncMonth(date), addPeriods * 3);
		return Dates.setMonths(ret, (Dates.getMonth(ret) / 3) * 3);
	}

	/**
	 * <pre> 获得半年粒度的期初时间.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/09  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @param addPeriods 偏移周期
	 * @return 时间
	 */
	@Nonnull
	public static Date getHalfyear(@Nonnull final Date date, @Nonnull final int addPeriods) {
		Checks.nullThrow(date);
		Date ret = Dates.addMonths(Dates.truncMonth(date), addPeriods * 6);
		return Dates.setMonths(ret, (Dates.getMonth(ret) / 6) * 6);
	}

	/**
	 * <pre> 获得年粒度的期初时间.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/09  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @param addPeriods 偏移周期
	 * @return 时间
	 */
	@Nonnull
	public static Date getYear(@Nonnull final Date date, @Nonnull final int addPeriods) {
		Checks.nullThrow(date);
		return Dates.addYears(Dates.truncYear(date), addPeriods);
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
	 * @param freqDetail 时间频率扩展信息
	 * @param addPeriods 偏移周期
	 * @return 时间
	 */
	@Nonnull
	public static Date getCron(@Nonnull final Date date, @Nonnull final String freqDetail, @Nonnull final int addPeriods) {
		return DateCronParser.get(date, freqDetail, addPeriods);
	}
	
	/**
	 * <pre> 判断时间是否为对应频率的期初时间.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/10  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @param freq 时间频率
	 * @param freqDetail 时间频率扩展信息
	 * @return 是否满足
	 */
	@Nonnull
	public static boolean match(@Nonnull final Date date, @Nonnull final Freq freq, @Nullable final String freqDetail) {
		Checks.nullThrow(date);
		Checks.nullThrow(freq);
		return getCurr(date, freq, freqDetail).equals(date);
	}

	/**
	 * <pre> 判断时间是否满足触发作业的时间要求.
	 * 
	 * 先求出该时间的下一周期的期初时间，之后判断是否满足被触发的作业频率要求。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2012/10/18  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @param freq 时间频率
	 * @param freqDetail 时间频率扩展信息
	 * @param triggerJobFreq 被触发作业的时间频率
	 * @param triggerJobFreqDetail 被触发作业的时间频率扩展信息
	 * @return 是否满足
	 */
	@Nonnull
	public static boolean matchTrigger(@Nonnull final Date date, @Nonnull final Freq freq, @Nullable final String freqDetail, @Nonnull final Freq triggerJobFreq, @Nullable final String triggerJobFreqDetail) {
		Checks.nullThrow(date);
		Checks.nullThrow(freq);
		Checks.nullThrow(triggerJobFreq);
		return match(getNext(date, freq, freqDetail), triggerJobFreq, triggerJobFreqDetail);
	}
	
	/**
	 * <pre> 计算时间区间内包含多少周期.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/10  huangys  Create
	 * </pre>
	 * 
	 * @param beginDate 开始时间
	 * @param endDate 结束时间
	 * @param freq 时间频率
	 * @param freqDetail 时间频率扩展信息
	 * @return 包含周期数
	 */
	@Nonnull
	public static long countPeriod(@Nonnull final Date beginDate, @Nonnull final Date endDate, @Nonnull final Freq freq, @Nullable final String freqDetail) {
		Checks.nullThrow(beginDate);
		Checks.nullThrow(endDate);
		Checks.nullThrow(freq);
		// 时间大小校验
		Checks.matchThrow(beginDate.after(endDate), "StartDate is larger then endDate.");
		// 时间频率校验
		Checks.notMatchThrow(match(beginDate, freq, freqDetail),
				DateFormat.getDetail(beginDate) + " doesn't match frequency setting(" + freq + "," + freqDetail + ")");
		Checks.notMatchThrow(match(endDate, freq, freqDetail),
				DateFormat.getDetail(endDate) + " doesn't match frequency setting(" + freq + "," + freqDetail + ")");
		
		switch (freq) {
		case CRON:
			Checks.nullThrow(freqDetail);
			long guessInterval = DateCronParser.guessInterval(freqDetail);
			if (guessInterval == 0) {
				Date tmp = beginDate;
				int i = 0;
				while (!endDate.equals(tmp)) {
					i++;
					tmp = getNext(tmp, Freq.CRON, freqDetail);
				}
				return i + 1;
			} else {
				return (endDate.getTime() - beginDate.getTime()) / 1000 / guessInterval + 1;
			}
		case SECONDS:
			Checks.nullThrow(freqDetail);
			return (endDate.getTime() - beginDate.getTime()) / 1000 / Integer.parseInt(freqDetail) + 1;
		case DAY:
			return (endDate.getTime() - beginDate.getTime()) / 1000 / 86400 + 1;
		case WEEK:
			return (endDate.getTime() - beginDate.getTime()) / 1000 / 86400 / 7 + 1;
		case TENDAYS:
			return (Dates.getYear(endDate) - Dates.getYear(beginDate)) * 36 + (Dates.getMonth(endDate) - Dates.getMonth(beginDate)) * 3 + (Dates.getDate(endDate) - Dates.getDate(beginDate)) / 10 + 1;
		case MONTH:
			return (Dates.getYear(endDate) - Dates.getYear(beginDate)) * 12 + (Dates.getMonth(endDate) - Dates.getMonth(beginDate)) + 1;
		case QUARTER:
			return (Dates.getYear(endDate) - Dates.getYear(beginDate)) * 4 + (Dates.getMonth(endDate) - Dates.getMonth(beginDate)) / 3 + 1;
		case HALFYEAR:
			return (Dates.getYear(endDate) - Dates.getYear(beginDate)) * 2 + (Dates.getMonth(endDate) - Dates.getMonth(beginDate)) / 6 + 1;
		case YEAR:
			return Dates.getYear(endDate) - Dates.getYear(beginDate) + 1;
		default:
			Exceptions.throwNoImplementation();
			return 0;
		}
	}
	
}
