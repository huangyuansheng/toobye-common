/*
 * Copyright 2016 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2016/04/29.
 * 
 */
package com.toobye.common.param;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.toobye.common.string.StringArray;
import com.toobye.common.string.StringRE;

/**
 * <pre> 参数捕获器.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2016/04/29  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class ParamCatcher {
	
	private ParamCatcher() { }

	/**
	 * <pre> 普通参数.
	 * 格式为${ID}，其中标识ID可以使用除"{}|"外所有字符
	 * </pre>
	 */
	public static final String COMMON_PARAM_REGEX = "\\$\\{[^{}|]+\\}";
	
	/**
	 * <pre> 动态时间参数.
	 * 可以嵌套所有字符，目前JAVA的正则表达式还不支持平衡组，故程序中设定嵌套最多5层。
	 * </pre>
	 * @see DynamicDateParam
	 */
	public static final String DYNAMIC_DATE_PARAM_REGEX;
	/**
	 * <pre> 动态时间参数最里层部分. </pre>
	 */
	public static final String DYNAMIC_DATE_PARAM_INNERMOST_PART_REGEX;
	static {
		String part1 = "[^{}]*";
		String part2 = "\\|\\|";
		String header = "\\$\\{";
		String tailer = "\\}";
		String placeholder = "[[[]]]";
		List<String> list = new ArrayList<>();
		String one = header + placeholder + part2
						+ "(" + part1 + part2 + "){2,}" + part1
						+ tailer;
		String tmp = placeholder;
		for (int i = 0; i < 5; i++) {
			tmp = tmp.replace(placeholder, one);
			list.add(tmp.replace(placeholder, part1));
		}
		DYNAMIC_DATE_PARAM_REGEX = StringArray.join(list, "|");
		DYNAMIC_DATE_PARAM_INNERMOST_PART_REGEX = list.get(0);
	}
	
	/**
	 * <pre> 捕获普通参数集合.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/04/29  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @return 普通参数集合
	 */
	@Nonnull
	public static Set<String> catchCommonParamSet(@Nullable final String str) {
		return StringRE.getSet(str, COMMON_PARAM_REGEX);
	}
	
	/**
	 * <pre> 捕获动态时间参数集合.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/04/29  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @return 动态时间参数集合
	 */
	@Nonnull
	public static Set<String> catchDynamicDateParamSet(@Nullable final String str) {
		return StringRE.getSet(str, DYNAMIC_DATE_PARAM_REGEX);
	}
	
	/**
	 * <pre> 捕获动态时间参数集合.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/04/29  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @return 动态时间参数集合
	 */
	@Nonnull
	public static String catchDynamicDateParamInnermostPart(@Nullable final String str) {
		return StringRE.getOne(str, DYNAMIC_DATE_PARAM_INNERMOST_PART_REGEX);
	}
	
}
