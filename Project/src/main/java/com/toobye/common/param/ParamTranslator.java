/*
 * Copyright 2016 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2016/04/29.
 * 
 */
package com.toobye.common.param;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.toobye.common.dbenums.Freq;
import com.toobye.common.lang.Checks;
import com.toobye.common.lang.Objects;

/**
 * <pre> 参数翻译器.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2016/04/29  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class ParamTranslator {
	
	private ParamTranslator() { }

	/**
	 * <pre> 转化参数（迭代）.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/05/03  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param paramMap 参数对
	 * @return 转化后字符串
	 */
	public static String translate(@Nullable final String str, @Nullable final Map<String, String> paramMap) {
		return translateWithDynamicDateParam(str, paramMap, null, null, null, null);
	}
	
	/**
	 * <pre> 转化参数含动态时间参数（迭代）.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/05/03  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param paramMap 参数对
	 * @param dateParamMap 时间参数Map
	 * @param date 时间
	 * @param freq 频率
	 * @param freqDetail 频率详细设置
	 * @return 转化后字符串
	 */
	public static String translateWithDynamicDateParam(@Nullable final String str, @Nullable final Map<String, String> paramMap, @Nullable final Map<String, Date> dateParamMap, @Nullable final Date date, @Nullable final Freq freq, @Nullable final String freqDetail) {
		Set<String> doneParamSet = new HashSet<String>();
		String ret = str;
		while (true) {
			// 获取普通参数
			Set<String> commParamSet = ParamCatcher.catchCommonParamSet(ret);
			// 转化普通参数
			for (String commParam : commParamSet) {
				ret = translateOne(ret, commParam, paramMap.get(commParam), doneParamSet);
			}
			
			Set<String> dynamicDateParamSet = null;
			if (date != null) {
				// 获取动态时间参数
				dynamicDateParamSet = ParamCatcher.catchDynamicDateParamSet(ret);
				// 转化动态时间参数
				for (String dynamicDateParam : dynamicDateParamSet) {
					ret = translateOne(ret, dynamicDateParam, DynamicDateParam.calculate(dynamicDateParam, dateParamMap, date, freq, freqDetail), doneParamSet);
				}
			}
			
			// 是否所有参数都已转化
			if (commParamSet.isEmpty() && Objects.isEmpty(dynamicDateParamSet)) {
				return ret;
			}
		}
	}

	/**
	 * <pre> 转化单个参数.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/05/03  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param param 参数
	 * @param value 值
	 * @param doneParamSet 已完成参数集合
	 * @return 转化后字符串
	 */
	private static String translateOne(@Nonnull final String str, @Nonnull final String param, @Nonnull final String value, @Nonnull final Set<String> doneParamSet) {
		// 参数值不能为Null
		Checks.nullThrow(value, "Parameter(" + param + ") cannot be found or is null");
		// 配置有误，导致死循环
		Checks.containsThrow(doneParamSet, param, "Configuration of string(" + str + ") is wrong. Parameter(" + param + ") is endless loop.");
		doneParamSet.add(param);
		// 转化参数
		return str.replace(param, value);
	}

}
