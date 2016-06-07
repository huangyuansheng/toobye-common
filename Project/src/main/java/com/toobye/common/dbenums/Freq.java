/*
 * Copyright 2014 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2014/06/12.
 * 
 */
package com.toobye.common.dbenums;

import javax.annotation.Nonnull;

/**
 * <pre> 时间周期频率.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2014/06/12  huangys  v1.0      Create
 * </pre>
 * 
 */
public enum Freq implements DBEnum<String> {
	
	/**
	 * <pre> Cron表达式. </pre>
	 */
	CRON("C"),
	/**
	 * <pre> 秒. </pre>
	 */
	SECONDS("S"),
	/**
	 * <pre> 天. </pre>
	 */
	DAY("D"),
	/**
	 * <pre> 周. </pre>
	 */
	WEEK("W"),
	/**
	 * <pre> 旬. </pre>
	 */
	TENDAYS("T"),
	/**
	 * <pre> 月. </pre>
	 */
	MONTH("M"),
	/**
	 * <pre> 季. </pre>
	 */
	QUARTER("Q"),
	/**
	 * <pre> 半年. </pre>
	 */
	HALFYEAR("H"),
	/**
	 * <pre> 年. </pre>
	 */
	YEAR("Y");

	private String value;
	
	private Freq(@Nonnull final String value) {
		this.value = value;
	}
	
	@Override
	public String getValue() {
		return value;
	}
	
	/**
	 * <pre> 获取对应枚举值.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/04/29  huangys  Create
	 * </pre>
	 * 
	 * @param value 值
	 * @return 对应枚举值
	 */
	public static Freq valueOfSupper(@Nonnull final String value) {
		return DBEnums.valueOf(Freq.class, value);
	}
	
}
