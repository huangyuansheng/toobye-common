/*
 * Copyright 2016 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2016/04/29.
 * 
 */
package com.toobye.common.param;

import java.util.Date;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.toobye.common.dbenums.Freq;
import com.toobye.common.lang.Checks;
import com.toobye.common.string.StringUtils;
import com.toobye.common.time.DateFormat;
import com.toobye.common.time.DatePeriod;

/**
 * <pre> 动态时间参数.
 * 
 * 注意大小写敏感！
 * 默认返回格式：yyyyMMddHHmmss
 * 默认频率和频率详细设置：任务对应作业的频率设置
 * 支持嵌套
 * 非最外层不应设置返回格式，否则报错
 * 最内层传入时间仅支持预定义的时间参数，当为SYSDATE时传入系统当前时间，为INPUTDATE时传入输入时间
 * 
 * 格式定义中的小括号表示括号内可以忽略
 * 单层格式：${预定义的时间参数||(频率)||(频率详细设置)||偏移(||返回格式)}
 * 样例：${DATETIME_ID||S||3600||-1||yyyyMMddHH}
 * 嵌套格式（双层）：${${预定义的时间参数||(频率)||(频率详细设置)||偏移}||(频率)||(频率详细设置)||偏移(||返回格式)}
 * 样例：${${DATETIME_ID||S||3600||-1}||S||3600||-1||yyyyMMddHH}
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2016/04/29  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class DynamicDateParam {
	
	private DynamicDateParam() { }
	
	/**
	 * <pre> 计算动态时间变量.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/04/29  huangys  Create
	 * </pre>
	 * 
	 * @param dynamicDateParam 动态时间参数
	 * @param dateParamMap 时间参数Map
	 * @param date 时间
	 * @param freq 频率
	 * @param freqDetail 频率详细设置
	 * @return 计算结果
	 */
	public static String calculate(@Nonnull final String dynamicDateParam, @Nullable final Map<String, Date> dateParamMap, @Nonnull final Date date, @Nonnull final Freq freq, @Nullable final String freqDetail) {
		Checks.nullThrow(dynamicDateParam);
		Checks.nullThrow(date);
		String param = dynamicDateParam;
		String placeholder = "[[[]]]";
		// 从最内层向最外层展开
		// 获取最内层
		String innermostPart = ParamCatcher.catchDynamicDateParamInnermostPart(param);
		// 拆解信息
		Info info = parse(innermostPart);
		// 首层时间处理
		Checks.nullThrow(info.date, "Date cannot be null(" + dynamicDateParam + ").");
		Date time = info.date.equals("INPUTDATE") ? date : (info.date.equals("SYSDATE") ? new Date() : dateParamMap.get(info.date));
		Checks.nullThrow(time, "Cannot found date param(" + info.date + ").");
		time = getTime(info, time, freq, freqDetail);
		// 是否为最外层
		if (param.equals(innermostPart)) {
			if (info.format == null) {
				return DateFormat.getDetail(time);
			} else {
				return DateFormat.get(time, info.format);
			}
		}
		// 非最外层，Format必须为Null
		Checks.notNullThrow(info.format, "Format must be null except the outermost part(" + dynamicDateParam + ").");
		param = param.replace(innermostPart, placeholder);
		while (true) {
			// 获取最内层
			innermostPart = ParamCatcher.catchDynamicDateParamInnermostPart(param);
			// 拆解信息
			info = parse(innermostPart);
			// 时间校验
			Checks.notMatchThrow(info.date.equals(placeholder), "Date must be from uplevel except the innermost part(" + dynamicDateParam + ").");
			time = getTime(info, time, freq, freqDetail);
			// 是否为最外层
			if (param.equals(innermostPart)) {
				if (info.format == null) {
					return DateFormat.getDetail(time);
				} else {
					return DateFormat.get(time, info.format);
				}
			}
			Checks.notNullThrow(info.format, "Format must be null except the outermost part(" + dynamicDateParam + ").");
			param = param.replace(innermostPart, placeholder);
		}
	}
	
	private static Date getTime(@Nonnull final Info info, @Nonnull final Date date, @Nonnull final Freq freq, @Nullable final String freqDetail) {
		return DatePeriod.get(date,
					info.freq == null ? freq : info.freq,
					info.freq == null ? freqDetail : info.freqDetail,
					info.shifting);
	}

	private static Info parse(@Nonnull final String part) {
		// 拆解信息
		String[] fields = part.substring(2, part.length() - 1).split("\\|\\|");
		if (fields.length != 4 && fields.length != 5) {
			throw new RuntimeException("Part(" + part + ") syntax error.");
		}
		Info info = new Info();
		info.date = StringUtils.isBlank(fields[0]) ? null : fields[0].trim();
		info.freq = StringUtils.isBlank(fields[1]) ? null : Freq.valueOfSupper(fields[1].trim());
		info.freqDetail = StringUtils.isBlank(fields[2]) ? null : fields[2].trim();
		info.shifting = Integer.parseInt(fields[3].trim());
		if (fields.length == 5) {
			info.format = StringUtils.isBlank(fields[4]) ? null : fields[4].trim();
		}
		return info;
	}

	/**
	 * <pre> 中间信息.
	 * 
	 * Modification History:
	 * Date        Author   Version   Action
	 * 2016/04/29  huangys  v1.0      Create
	 * </pre>
	 * 
	 */
	private static class Info {
		public String date;
		public Freq freq;
		public String freqDetail;
		public int shifting;
		public String format;
	}
	
}
