/*
 * Copyright 2013 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2013/12/30.
 * 
 */
package com.toobye.common.string;

import com.toobye.common.base.Exceptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.toobye.common.lang.Checks;

/**
 * <pre> 字符串匹配工具.
 * 
 * JAVA正则表达式说明
 * 详见：http://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
 * (?d) UNIX_LINES
 * (?i) CASE_INSENSITIVE 大小写不敏感
 * (?x) COMMENTS #开始的注释行忽略
 * (?m) MULTILINE 多行匹配
 * (?s) DOTALL
 * (?u) UNICODE_CASE
 * (?U) UNICODE_CHARACTER_CLASS
 *  ?:  忽略分组
 *  ?   跟在+*量词后表示最少匹配
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2013/12/30  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class StringRE {
	
	private StringRE() { }
	
	/**
	 * <pre> Cache. </pre>
	 */
	private static final Map<String, Pattern> PATTERN_MAP = new HashMap<String, Pattern>();

	/**
	 * <pre> 获取正则表达式解析器.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/12/31  huangys  Create
	 * </pre>
	 * 
	 * @param regex 正则表达式
	 * @return Java-REGEX-Pattern
	 */
	@Nonnull
	private static Pattern getRe(@Nonnull final String regex) {
		Checks.nullThrow(regex);
		Pattern p = PATTERN_MAP.get(regex);
		if (p != null) {
			return p;
		} else {
			p = Pattern.compile(regex);
			PATTERN_MAP.put(regex, p);
			return p;
		}
	}
	
	/**
	 * <pre> 返回首条匹配内容.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/12/31  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param regex 正则表达式
	 * @return 匹配的字符串内容
	 */
	@Nullable
	public static String getOne(@Nullable final String str, @Nonnull final String regex) {
		List<String> ret = getHead(str, regex, 1);
		return ret.isEmpty() ? null : ret.get(0);
	}
	
	/**
	 * <pre> 返回所有匹配内容.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/12/31  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param regex 正则表达式
	 * @return 匹配的字符串内容
	 */
	@Nonnull
	public static List<String> getAll(@Nullable final String str, @Nonnull final String regex) {
		return getHead(str, regex, 0);
	}
	
	/**
	 * <pre> 返回前N条的匹配内容.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/12/31  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param regex 正则表达式
	 * @param rownum 匹配条数限制
	 * @return 匹配的字符串内容
	 */
	@Nonnull
	public static List<String> getHead(@Nullable final String str, @Nonnull final String regex, @Nonnull final int rownum) {
		return getHeadAssignedGroup(str, regex, 0, rownum);
	}
	
	/**
	 * <pre> 返回首条匹配的第一个分组内容.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/12/31  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param regex 正则表达式
	 * @return 匹配的字符串内容
	 */
	@Nullable
	public static String getOneGroupFirst(@Nullable final String str, @Nonnull final String regex) {
		return getOneAssignedGroup(str, regex, 1);
	}
	
	/**
	 * <pre> 返回首条匹配的指定分组内容.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/12/31  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param regex 正则表达式
	 * @param group 正则表达式对应分组号
	 * @return 匹配的字符串内容
	 */
	@Nullable
	public static String getOneAssignedGroup(@Nullable final String str, @Nonnull final String regex, @Nonnull final int group) {
		List<String> ret = getHeadAssignedGroup(str, regex, group, 1);
		return ret.isEmpty() ? null : ret.get(0);
	}
	
	/**
	 * <pre> 返回首条匹配的指定分组内容.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/12/31  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param regex 正则表达式
	 * @param groups 正则表达式对应分组号集合
	 * @return 匹配的字符串内容
	 */
	@Nullable
	public static String[] getOneAssignedGroup(@Nullable final String str, @Nonnull final String regex, @Nonnull final int[] groups) {
		List<String[]> ret = getHeadAssignedGroup(str, regex, groups, 1);
		return ret.isEmpty() ? null : ret.get(0);
	}
	
	/**
	 * <pre> 返回首条匹配的全部分组内容.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/12/31  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param regex 正则表达式
	 * @return 匹配的字符串内容
	 */
	@Nullable
	public static String[] getOneGroupAll(@Nullable final String str, @Nonnull final String regex) {
		List<String[]> ret = getHeadAssignedGroup(str, regex, null, 1);
		return ret.isEmpty() ? null : ret.get(0);
	}
	
	/**
	 * <pre> 返回所有匹配的第一个分组内容.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/12/31  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param regex 正则表达式
	 * @return 匹配的字符串内容
	 */
	@Nonnull
	public static List<String> getAllGroupFirst(@Nullable final String str, @Nonnull final String regex) {
		return getAllAssignedGroup(str, regex, 1);
	}
	
	/**
	 * <pre> 返回所有匹配的指定分组内容.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/12/31  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param regex 正则表达式
	 * @param group 正则表达式对应分组号
	 * @return 匹配的字符串内容
	 */
	@Nonnull
	public static List<String> getAllAssignedGroup(@Nullable final String str, @Nonnull final String regex, @Nonnull final int group) {
		return getHeadAssignedGroup(str, regex, group, 0);
	}
	
	/**
	 * <pre> 返回所有匹配的指定分组内容.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/12/31  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param regex 正则表达式
	 * @param groups 正则表达式对应分组号集合
	 * @return 匹配的字符串内容
	 */
	@Nonnull
	public static List<String[]> getAllAssignedGroup(@Nullable final String str, @Nonnull final String regex, @Nonnull final int[] groups) {
		return getHeadAssignedGroup(str, regex, groups, 0);
	}
	
	/**
	 * <pre> 返回所有匹配的全部分组内容.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/12/31  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param regex 正则表达式
	 * @return 匹配的字符串内容
	 */
	@Nonnull
	public static List<String[]> getAllGroupAll(@Nullable final String str, @Nonnull final String regex) {
		return getHeadAssignedGroup(str, regex, null, 0);
	}

	/**
	 * <pre> 返回前N条匹配的指定分组内容.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/12/31  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param regex 正则表达式
	 * @param rownum 匹配条数限制
	 * @param group 正则表达式对应分组号
	 * @return 匹配的字符串内容
	 */
	@Nonnull
	public static List<String> getHeadAssignedGroup(@Nullable final String str, @Nonnull final String regex, @Nonnull final int group, @Nonnull final int rownum) {
		List<String> ret = new ArrayList<String>();
		// 快速判断
		if (str == null) {
			return ret;
		}
		Matcher m = getRe(regex).matcher(str);
		int count = 0;
		while (m.find()) {
			count++;
			// 返回所需的组
			ret.add(m.group(group));
			// 个数限制判断
			if (rownum > 0 && count >= rownum) {
				break;
			}
		}
		return ret;
	}
	
	/**
	 * <pre> 返回前N条匹配的指定分组内容.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/12/31  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param regex 正则表达式
	 * @param rownum 匹配条数限制
	 * @param groups 正则表达式对应分组号集合
	 * @return 匹配的字符串内容
	 */
	@Nonnull
	public static List<String[]> getHeadAssignedGroup(@Nullable final String str, @Nonnull final String regex, @Nullable final int[] groups, @Nonnull final int rownum) {
		List<String[]> ret = new ArrayList<String[]>();
		if (str == null) {
			return ret;
		}
		Matcher m = getRe(regex).matcher(str);
		int count = 0;
		while (m.find()) {
			count++;
			String[] one = null;
			if (groups == null) {
				// 返回所有组
				one = new String[m.groupCount()];
				for (int i = 0; i < m.groupCount(); i++) {
					one[i] = m.group(i + 1);
				}
			} else {
				// 返回所需的所有组
				one = new String[groups.length];
				for (int i = 0; i < groups.length; i++) {
					one[i] = m.group(groups[i]);
				}
			}
			ret.add(one);
			// 个数限制判断
			if (rownum > 0 && count >= rownum) {
				break;
			}
		}
		return ret;
	}
	
	/**
	 * <pre> 返回前N条匹配的全部分组内容.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/12/31  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param regex 正则表达式
	 * @param rownum 匹配条数限制
	 * @return 匹配的字符串内容
	 */
	@Nonnull
	public static List<String[]> getHeadGroupAll(@Nullable final String str, @Nonnull final String regex, @Nonnull final int rownum) {
		return getHeadAssignedGroup(str, regex, null, rownum);
	}
	
	/**
	 * <pre> IP正则表达式. </pre>
	 */
	public static final String REGEX_IP = "(?:(?:2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(?:2[0-4]\\d|25[0-5]|[01]?\\d\\d?)";
	
	/**
	 * <pre> 是否为IP字符串.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/05/19  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @return 是否为IP
	 */
	public static boolean isIP(@Nullable final String str) {
		if (StringUtils.isBlank(str)) {
			return false;
		}
		return str.matches(REGEX_IP);
	}
	
	/**
	 * <pre> 获得首个IP字符串.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2012/11/14  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @return 获得首个符合IP规范的字符串
	 */
	@Nullable
	public static String getIP(@Nullable final String str) {
		return getOne(str, REGEX_IP);
	}
	
	/**
	 * <pre> 正则表达式是否正确.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/07  huangys  Create
	 * </pre>
	 * 
	 * @param regex 正则表达式
	 * @return 验证是否合法
	 */
	@Nonnull
	public static boolean isRegex(@Nullable final String regex) {
		if (regex == null) {
			return false;
		}
		try {
			Pattern.compile(regex);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	/**
	 * <pre> 正则表达式错误原因.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/07  huangys  Create
	 * </pre>
	 * 
	 * @param regex 正则表达式
	 * @return 返回错误原因，返回结果为null表示无错误
	 */
	@Nullable
	public static String getWrongCause(@Nonnull final String regex) {
		try {
			Pattern.compile(regex);
		} catch (Exception e) {
			return Exceptions.getStackTrace(e);
		}
		return null;
	}
	
	/**
	 * <pre> 是否包含任意正则表达式所匹配的字符串.
	 * null, * -> false
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/14  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param regexes 正则表达式
	 * @return 是否包含任意正则表达式所匹配的字符串
	 */
	@Nonnull
	public static boolean containsAny(@Nullable final String str, @Nullable final String... regexes) {
		if (str == null) {
			return false;
		}
		// 校验正则表达式
		Checks.emptyThrow(regexes);
		for (String regex : regexes) {
			Checks.emptyThrow(regex);
		}
		for (String regex : regexes) {
			if (str.matches(".*" + regex + ".*")) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * <pre> 是否包含任意正则表达式所匹配的字符串.
	 * null, * -> false
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/14  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param regexes 正则表达式
	 * @return 是否包含任意正则表达式所匹配的字符串
	 */
	@Nonnull
	public static boolean containsAny(@Nullable final String str, @Nullable final Iterable<String> regexes) {
		if (str == null) {
			return false;
		}
		// 校验正则表达式
		Checks.emptyThrow(regexes);
		for (String regex : regexes) {
			Checks.emptyThrow(regex);
		}
		for (String regex : regexes) {
			if (str.matches(".*" + regex + ".*")) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * <pre> 是否为合法字符串，即不包含任意非法正则表达式所匹配的字符串.
	 * null, * -> false
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/14  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param regexes 正则表达式
	 * @return 是否为合法字符串
	 */
	@Nonnull
	public static boolean containsNone(@Nullable final String str, @Nullable final String... regexes) {
		if (str == null) {
			return false;
		}
		// 校验正则表达式
		Checks.emptyThrow(regexes);
		for (String regex : regexes) {
			Checks.emptyThrow(regex);
		}
		for (String regex : regexes) {
			if (str.matches(".*" + regex + ".*")) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * <pre> 是否为合法字符串，即不包含任意非法正则表达式所匹配的字符串.
	 * null, * -> false
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/14  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param regexes 正则表达式
	 * @return 是否为合法字符串
	 */
	@Nonnull
	public static boolean containsNone(@Nullable final String str, @Nullable final Iterable<String> regexes) {
		if (str == null) {
			return false;
		}
		// 校验正则表达式
		Checks.emptyThrow(regexes);
		for (String regex : regexes) {
			Checks.emptyThrow(regex);
		}
		for (String regex : regexes) {
			if (str.matches(".*" + regex + ".*")) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * <pre> 是否匹配任意正则表达式.
	 * null, * -> false
	 * *, 含null或空字符串（惰性检查） -> false
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/14  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param regexes 正则表达式
	 * @return 是否匹配任意正则表达式
	 */
	@Nonnull
	public static boolean matchAny(@Nullable final String str, @Nullable final String... regexes) {
		if (str == null) {
			return false;
		}
		// 校验正则表达式
		Checks.emptyThrow(regexes);
		for (String regex : regexes) {
			Checks.emptyThrow(regex);
		}
		for (String regex : regexes) {
			if (str.matches(regex)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * <pre> 是否匹配任意正则表达式.
	 * null, * -> false
	 * *, 含null或空字符串（惰性检查） -> false
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/14  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param regexes 正则表达式
	 * @return 是否匹配任意正则表达式
	 */
	@Nonnull
	public static boolean matchAny(@Nullable final String str, @Nullable final Iterable<String> regexes) {
		if (str == null) {
			return false;
		}
		// 校验正则表达式
		Checks.emptyThrow(regexes);
		for (String regex : regexes) {
			Checks.emptyThrow(regex);
		}
		for (String regex : regexes) {
			if (str.matches(regex)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * <pre> 是否为合法字符串，即不匹配任意非法正则表达式.
	 * null, * -> false
	 * *, 含null或空字符串（惰性检查） -> false
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/14  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param regexes 正则表达式
	 * @return 是否为合法字符串
	 */
	@Nonnull
	public static boolean matchNone(@Nullable final String str, @Nullable final String... regexes) {
		if (str == null) {
			return false;
		}
		// 校验正则表达式
		Checks.emptyThrow(regexes);
		for (String regex : regexes) {
			Checks.emptyThrow(regex);
		}
		for (String regex : regexes) {
			if (str.matches(regex)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * <pre> 是否为合法字符串，即不匹配任意非法正则表达式.
	 * null, * -> false
	 * *, 含null或空字符串（惰性检查） -> false
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/14  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param regexes 正则表达式
	 * @return 是否为合法字符串
	 */
	@Nonnull
	public static boolean matchNone(@Nullable final String str, @Nullable final Iterable<String> regexes) {
		if (str == null) {
			return false;
		}
		// 校验正则表达式
		Checks.emptyThrow(regexes);
		for (String regex : regexes) {
			Checks.emptyThrow(regex);
		}
		for (String regex : regexes) {
			if (str.matches(regex)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * <pre> 统计正则表达式匹配的字符串总计出现次数.
	 * null, * -> 0
	 * *, null -> 0
	 * *, "" -> 0
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/14  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param regex 正则表达式
	 * @return 统计正则表达式匹配的字符串总计出现次数
	 */
	@Nonnull
	public static int containsCount(@Nullable final String str, @Nullable final String regex) {
		if (str == null || StringUtils.isEmpty(regex)) {
			return 0;
		}
		return getAll(str, regex).size();
	}
	
	private static final String REGEX_SPECIAL_CHARS = "\\\\|\\[|\\]|[.*+(){}<>$^|?]";
	/**
	 * <pre> 纯字符串转为正则表达式.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/07/17  huangys  Create
	 * </pre>
	 * 
	 * @param str 纯字符串
	 * @return 正则表达式
	 */
	@Nonnull
	public static String toRegex(@Nonnull final String str) {
		Checks.nullThrow(str);
		return str.replaceAll(REGEX_SPECIAL_CHARS, "\\\\$0");
	}
	
	/**
	 * <pre> 获取匹配内容集合.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/04/29  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param regex 正则表达式
	 * @return 匹配内容集合
	 */
	@Nonnull
	public static Set<String> getSet(@Nullable final String str, @Nonnull final String regex) {
		return getSetAssignedGroup(str, regex, 0);
	}
	
	/**
	 * <pre> 获取匹配内容集合.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/05/03  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param regex 正则表达式
	 * @param group 组号
	 * @return 匹配内容集合
	 */
	public static Set<String> getSetAssignedGroup(@Nullable final String str, @Nonnull final String regex, @Nonnull final int group) {
		Set<String> ret = new HashSet<>();
		if (StringUtils.isBlank(str)) {
			return ret;
		}
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		while (m.find()) {
			ret.add(m.group(group));
		}
		return ret;
	}
	
}
