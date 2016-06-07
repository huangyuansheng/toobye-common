/*
 * Copyright 2014 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2014/03/30.
 * 
 */
package com.toobye.common.random;

import java.util.UUID;

import javax.annotation.Nonnull;

import com.toobye.common.lang.Checks;

/**
 * <pre> UUID生成器.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2014/03/30  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class RandomUUID {
	
	private RandomUUID() { }

	/**
	 * <pre> 获得单个UUID(32位).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/03/30  huangys  Create
	 * </pre>
	 * 
	 * @return 具备唯一性的ID
	 */
	@Nonnull
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		// 去掉"-"符号
		return uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18) + uuid.substring(19, 23) + uuid.substring(24);
	}
	
	/**
	 * <pre> 获得指定数量的UUID.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/03/30  huangys  Create
	 * </pre>
	 * 
	 * @param n 数量
	 * @return 批量的UUID
	 */
	@Nonnull
	public static String[] getUUID(@Nonnull final int n) {
		Checks.nonPositiveThrow(n);
		String[] array = new String[n];
		for (int i = 0; i < n; i++) {
			array[i] = getUUID();
		}
		return array;
	}

}
