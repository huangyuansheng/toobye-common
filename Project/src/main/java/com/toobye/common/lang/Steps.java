/*
 * Copyright 2016 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2016/04/15.
 * 
 */
package com.toobye.common.lang;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * <pre> 步骤执行工具类.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2016/04/15  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class Steps {

	private Steps() { }

	/**
	 * <pre> 执行所有步骤.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/04/14  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 步骤参数
	 * @param steps 步骤集
	 * @param params 参数
	 * @return 是否完成所有步骤
	 */
	public static <T> boolean doAll(@Nonnull final List<? extends Step<T>> steps, @Nullable final T params) {
		Checks.nullThrow(steps);
		for (Step<T> step : steps) {
			boolean isContinued = step.doStep(params);
			if (!isContinued) {
				return false;
			}
		}
		return true;
	}
	
}
