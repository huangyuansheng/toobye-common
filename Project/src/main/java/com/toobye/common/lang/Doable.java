/*
 * Copyright 2014 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2014/09/25.
 * 
 */
package com.toobye.common.lang;

import javax.annotation.Nullable;

/**
 * <pre> 带参的任务.
 * 
 * Runable
 * Callable
 * Function
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2014/09/25  huangys  v1.0      Create
 * </pre>
 * 
 * @param <T> 参数类型
 */
public interface Doable<T> {

	/**
	 * <pre> 任务运行.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/09/25  huangys  Create
	 * </pre>
	 * 
	 * @param t 参数
	 */
	void run(@Nullable T t);
	
}
