/*
 * Copyright 2013 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2013/08/09.
 * 
 */
package com.toobye.common.time;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.toobye.common.lang.Checks;

/**
 * <pre> 时间格式化工具.
 * 
 * Char	描述							Example
 * G	Era designator				AD
 * y	Year in four digits			2001
 * M	Month in year				July or 07
 * d	Day in month				10
 * h	Hour in A.M./P.M. (1~12)	12
 * H	Hour in day (0~23)			22
 * m	Minute in hour				30
 * s	Second in minute			55
 * S	Millisecond					234
 * E	Day in week					Tuesday
 * D	Day in year					360
 * F	Day of week in month		2 (second Wed. in July)
 * w	Week in year				40
 * W	Week in month				1
 * a	A.M./P.M. marker			PM
 * k	Hour in day (1~24)			24
 * K	Hour in A.M./P.M. (0~11)	10
 * z	Time zone					Eastern Standard Time
 * '	Escape for text				Delimiter
 * "	Single quote				`
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2013/08/09  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class DateFormat {
	
	private DateFormat() { }
	
	/**
	 * <pre> yyyy-MM-dd. </pre>
	 */
	public static final String SIMPLE = "yyyy-MM-dd";
	/**
	 * <pre> yyyy-MM-dd HH:mm:ss. </pre>
	 */
	public static final String DETAIL = "yyyy-MM-dd HH:mm:ss";
	/**
	 * <pre> yyyy-MM-dd HH:mm:ss.SSS. </pre>
	 */
	public static final String PRECISENESS = "yyyy-MM-dd HH:mm:ss.SSS";
	
	/**
	 * <pre> yyyyMMdd. </pre>
	 */
	public static final String SIMPLE_NS = "yyyyMMdd";
	/**
	 * <pre> yyyyMMddHHmmss. </pre>
	 */
	public static final String DETAIL_NS = "yyyyMMddHHmmss";
	/**
	 * <pre> yyyyMMddHHmmssSSS. </pre>
	 */
	public static final String PRECISENESS_NS = "yyyyMMddHHmmssSSS";

	/**
	 * <pre> 格式化当前时间yyyy-MM-dd.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/01  huangys  Create
	 * </pre>
	 * 
	 * @return 格式化字符串
	 */
	@Nonnull
	public static String getCurrSimple() {
		return getCurr(SIMPLE);
	}
	
	/**
	 * <pre> 格式化当前时间yyyy-MM-dd HH:mm:ss.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/01  huangys  Create
	 * </pre>
	 * 
	 * @return 格式化字符串
	 */
	@Nonnull
	public static String getCurrDetail() {
		return getCurr(DETAIL);
	}
	
	/**
	 * <pre> 格式化当前时间yyyy-MM-dd HH:mm:ss.SSS.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/01  huangys  Create
	 * </pre>
	 * 
	 * @return 格式化字符串
	 */
	@Nonnull
	public static String getCurrPreciseness() {
		return getCurr(PRECISENESS);
	}
	
	/**
	 * <pre> 格式化当前时间yyyyMMdd.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/01  huangys  Create
	 * </pre>
	 * 
	 * @return 格式化字符串
	 */
	@Nonnull
	public static String getCurrSimpleNS() {
		return getCurr(SIMPLE_NS);
	}
	
	/**
	 * <pre> 格式化当前时间yyyyMMddHHmmss.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/01  huangys  Create
	 * </pre>
	 * 
	 * @return 格式化字符串
	 */
	@Nonnull
	public static String getCurrDetailNS() {
		return getCurr(DETAIL_NS);
	}
	
	/**
	 * <pre> 格式化当前时间yyyyMMddHHmmssSSS.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/01  huangys  Create
	 * </pre>
	 * 
	 * @return 格式化字符串
	 */
	@Nonnull
	public static String getCurrPrecisenessNS() {
		return getCurr(PRECISENESS_NS);
	}
	
	/**
	 * <pre> 格式化当前时间.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/01  huangys  Create
	 * </pre>
	 * 
	 * @param format 时间格式
	 * @return 格式化字符串
	 */
	@Nonnull
	public static String getCurr(@Nullable final String format) {
		return get(new Date(), format);
	}
	
	/**
	 * <pre> 格式化时间yyyy-MM-dd.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/01  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @return 格式化字符串
	 */
	@Nonnull
	public static String getSimple(@Nonnull final Date date) {
		Checks.nullThrow(date);
		return get(date, SIMPLE);
	}
	
	/**
	 * <pre> 格式化时间yyyy-MM-dd HH:mm:ss.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/01  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @return 格式化字符串
	 */
	@Nonnull
	public static String getDetail(@Nonnull final Date date) {
		Checks.nullThrow(date);
		return get(date, DETAIL);
	}
	
	/**
	 * <pre> 格式化时间yyyy-MM-dd HH:mm:ss.SSS.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/01  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @return 格式化字符串
	 */
	@Nonnull
	public static String getPreciseness(@Nonnull final Date date) {
		Checks.nullThrow(date);
		return get(date, PRECISENESS);
	}
	
	/**
	 * <pre> 格式化时间yyyyMMdd.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/01  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @return 格式化字符串
	 */
	@Nonnull
	public static String getSimpleNS(@Nonnull final Date date) {
		Checks.nullThrow(date);
		return get(date, SIMPLE_NS);
	}
	
	/**
	 * <pre> 格式化时间yyyyMMddHHmmss.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/01  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @return 格式化字符串
	 */
	@Nonnull
	public static String getDetailNS(@Nonnull final Date date) {
		Checks.nullThrow(date);
		return get(date, DETAIL_NS);
	}
	
	/**
	 * <pre> 格式化时间yyyyMMddHHmmssSSS.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/01  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @return 格式化字符串
	 */
	@Nonnull
	public static String getPrecisenessNS(@Nonnull final Date date) {
		Checks.nullThrow(date);
		return get(date, PRECISENESS_NS);
	}
	
	/**
	 * <pre> 格式化.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/01  huangys  Create
	 * </pre>
	 * 
	 * @param date 时间
	 * @param format 时间格式
	 * @return 格式化字符串
	 */
	@Nonnull
	public static String get(@Nonnull final Date date, @Nullable final String format) {
		Checks.nullThrow(date);
		SimpleDateFormat sdf = new SimpleDateFormat(format == null ? PRECISENESS : format);
		return sdf.format(date);
	}
	
	/**
	 * <pre> 解析时间字符串yyyy-MM-dd.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/01  huangys  Create
	 * </pre>
	 * 
	 * @param dateStr 时间字符串
	 * @return 时间
	 */
	@Nonnull
	public static Date parseSimple(@Nonnull final String dateStr) {
		Checks.emptyThrow(dateStr);
		return parse(dateStr, SIMPLE);
	}
	
	/**
	 * <pre> 解析时间字符串yyyy-MM-dd HH:mm:ss.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/01  huangys  Create
	 * </pre>
	 * 
	 * @param dateStr 时间字符串
	 * @return 时间
	 */
	@Nonnull
	public static Date parseDetail(@Nonnull final String dateStr) {
		Checks.emptyThrow(dateStr);
		return parse(dateStr, DETAIL);
	}
	
	/**
	 * <pre> 解析时间字符串yyyy-MM-dd HH:mm:ss.SSS.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/01  huangys  Create
	 * </pre>
	 * 
	 * @param dateStr 时间字符串
	 * @return 时间
	 */
	@Nonnull
	public static Date parsePreciseness(@Nonnull final String dateStr) {
		Checks.emptyThrow(dateStr);
		return parse(dateStr, PRECISENESS);
	}
	
	/**
	 * <pre> 解析时间字符串yyyyMMdd.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/01  huangys  Create
	 * </pre>
	 * 
	 * @param dateStr 时间字符串
	 * @return 时间
	 */
	@Nonnull
	public static Date parseSimpleNS(@Nonnull final String dateStr) {
		Checks.emptyThrow(dateStr);
		return parse(dateStr, SIMPLE_NS);
	}
	
	/**
	 * <pre> 解析时间字符串yyyyMMddHHmmss.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/01  huangys  Create
	 * </pre>
	 * 
	 * @param dateStr 时间字符串
	 * @return 时间
	 */
	@Nonnull
	public static Date parseDetailNS(@Nonnull final String dateStr) {
		Checks.emptyThrow(dateStr);
		return parse(dateStr, DETAIL_NS);
	}
	
	/**
	 * <pre> 解析时间字符串yyyyMMddHHmmssSSS.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/01  huangys  Create
	 * </pre>
	 * 
	 * @param dateStr 时间字符串
	 * @return 时间
	 */
	@Nonnull
	public static Date parsePrecisenessNS(@Nonnull final String dateStr) {
		Checks.emptyThrow(dateStr);
		return parse(dateStr, PRECISENESS_NS);
	}
	
	/**
	 * <pre> 解析时间字符串.
	 * eg: yyyy-MM-dd HH:mm:ss
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/01  huangys  Create
	 * </pre>
	 * 
	 * @param dateStr 时间字符串
	 * @param formats 时间格式
	 * @return 时间
	 */
	@Nonnull
	public static Date parse(@Nonnull final String dateStr, @Nonnull final String... formats) {
		return parseDateWithLeniency(dateStr, null, formats, false);
	}
	
	/**
	 * <pre> 解析时间字符串.
	 * eg: yyyy-MM-dd HH:mm:ss
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/01  huangys  Create
	 * </pre>
	 * 
	 * @param dateStr 时间字符串
	 * @param locale 地域
	 * @param formats 时间格式
	 * @return 时间
	 */
	@Nonnull
	public static Date parse(@Nonnull final String dateStr, @Nullable final Locale locale, @Nonnull final String... formats) {
		return parseDateWithLeniency(dateStr, locale, formats, false);
	}
	
	/**
	 * <pre> 解析时间字符串.
	 * EEE星期
	 * MMM月份
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/29  huangys  Create
	 * </pre>
	 * 
	 * @param dateStr 时间字符串
	 * @param formats 时间格式
	 * @return 时间
	 */
	@Nonnull
	public static Date parseUs(@Nonnull final String dateStr, @Nonnull final String... formats) {
		return parseDateWithLeniency(dateStr, Locale.US, formats, false);
	}

	/**
     * <p>Parses a string representing a date by trying a variety of different parsers.</p>
     * 
     * <p>The parse will try each parse pattern in turn.
     * A parse is only deemed successful if it parses the whole of the input string.
     * If no parse patterns match, a ParseException is thrown.</p>
     * 
     * @param str  the date to parse, not null
     * @param locale the locale to use when interpretting the pattern, can be null in which
     * case the default system locale is used
     * @param parsePatterns  the date format patterns to use, see SimpleDateFormat, not null
     * @param lenient Specify whether or not date/time parsing is to be lenient.
     * @return the parsed date
     * @see java.util.Calendar#isLenient()
     */
	@Nonnull
    private static Date parseDateWithLeniency(@Nonnull final String str, @Nullable final Locale locale, @Nonnull final String[] parsePatterns, @Nonnull final boolean lenient) {
    	Checks.emptyThrow(str);
    	Checks.emptyThrow(parsePatterns);

		SimpleDateFormat parser;
		if (locale == null) {
			parser = new SimpleDateFormat();
		} else {
			parser = new SimpleDateFormat("", locale);
		}

		parser.setLenient(lenient);
		final ParsePosition pos = new ParsePosition(0);
		for (final String parsePattern : parsePatterns) {

			String pattern = parsePattern;

			// LANG-530 - need to make sure 'ZZ' output doesn't get passed to
			// SimpleDateFormat
			if (parsePattern.endsWith("ZZ")) {
				pattern = pattern.substring(0, pattern.length() - 1);
			}

			parser.applyPattern(pattern);
			pos.setIndex(0);

			String str2 = str;
			// LANG-530 - need to make sure 'ZZ' output doesn't hit
			// SimpleDateFormat as it will ParseException
			if (parsePattern.endsWith("ZZ")) {
				str2 = str.replaceAll("([-+][0-9][0-9]):([0-9][0-9])$", "$1$2");
			}

			final Date date = parser.parse(str2, pos);
			if (date != null && pos.getIndex() == str2.length()) {
				return date;
			}
		}
		throw new RuntimeException("Unable to parse the date: " + str);
    }
	
	/**
	 * <pre> 毫秒数转时间.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/02/24  huangys  Create
	 * </pre>
	 * 
	 * @param ms 毫秒数
	 * @return 时间
	 */
	@Nonnull
	public static Date of(@Nonnull final long ms) {
		return new Date(ms);
	}
	
	/**
	 * <pre> 解析Unix毫秒时间字符串.
	 * 如：1392829261.568
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/02/24  huangys  Create
	 * </pre>
	 * 
	 * @param unixMs Unix毫秒数
	 * @return 时间
	 */
	@Nonnull
	public static Date ofUnixMs(@Nonnull final String unixMs) {
		Checks.emptyThrow(unixMs);
		return of((long) (Double.parseDouble(unixMs) * 1000));
	}
	
}
