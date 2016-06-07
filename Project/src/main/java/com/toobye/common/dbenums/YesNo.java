/*
 * Copyright 2014 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2014/05/26.
 * 
 */
package com.toobye.common.dbenums;

import javax.annotation.Nonnull;

/**
 * <pre> 是否.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2014/05/26  huangys  v1.0      Create
 * </pre>
 * 
 */
public enum YesNo implements DBEnum<String> {
	
	/**
	 * <pre> 是/有效. </pre>
	 */
	Yes("Y"),
	/**
	 * <pre> 否/无效. </pre>
	 */
	No("N");

	private String value;
	
	private YesNo(@Nonnull final String value) {
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
	public static YesNo valueOfSupper(@Nonnull final String value) {
		return DBEnums.valueOf(YesNo.class, value);
	}
	
}
