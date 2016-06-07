/*
 * Copyright 2013 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2013/08/09.
 * 
 */
package com.toobye.common.time;

import java.util.Date;

import javax.annotation.Nonnull;

import com.toobye.common.concurrent.TimeSlice;
import com.toobye.common.lang.Checks;

/**
 * <pre> 时间比较工具类.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2013/08/09  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class DateComparator {
	
	private DateComparator() { };

	/**
	 * <pre> 日期比较（忽略日以下信息）.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2012/02/27  huangys  Create
	 * </pre>
	 * 
	 * @param date1 时间1
	 * @param date2 时间2
	 * @return 比较值
	 */
	@Nonnull
	public static int dateCompare(@Nonnull final Date date1, @Nonnull final Date date2) {
		Checks.nullThrow(date1);
		Checks.nullThrow(date2);
		return Dates.truncDate(date1).compareTo(Dates.truncDate(date2));
	}
	
	/**
	 * <pre> 日期相差.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/10  huangys  Create
	 * </pre>
	 * 
	 * @param date1 时间1
	 * @param date2 时间2
	 * @return 日期相差
	 */
	@Nonnull
	public static TimeSlice dateMinus(@Nonnull final Date date1, @Nonnull final Date date2) {
		Checks.nullThrow(date1);
		Checks.nullThrow(date2);
		return TimeSlice.milliSeconds(date1.getTime() - date2.getTime());
	}
	
	/**
	 * <pre> 日期相差秒数.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/10  huangys  Create
	 * </pre>
	 * 
	 * @param date1 时间1
	 * @param date2 时间2
	 * @return 日期相差秒数
	 */
	@Nonnull
	public static long dateMinusReturnSeconds(@Nonnull final Date date1, @Nonnull final Date date2) {
		return dateMinus(date1, date2).toSeconds();
	}

}
