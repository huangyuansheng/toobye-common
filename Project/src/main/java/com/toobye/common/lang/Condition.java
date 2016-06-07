/*
 * Copyright 2014 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2014/08/21.
 * 
 */
package com.toobye.common.lang;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * <pre> 条件.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2014/08/21  huangys  v1.0      Create
 * </pre>
 * 
 * @param <T> 元素类型
 */
public interface Condition<T> {

	/**
	 * <pre> 条件判断.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/21  huangys  Create
	 * </pre>
	 * 
	 * @param t 元素
	 * @return 是否满足条件
	 */
	@Nonnull
	boolean match(@Nullable final T t);
	
}
