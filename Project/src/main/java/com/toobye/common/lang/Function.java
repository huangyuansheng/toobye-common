/*
 * Copyright 2014 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2014/08/21.
 * 
 */
package com.toobye.common.lang;

import javax.annotation.Nullable;

/**
 * <pre> 函数定义.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2014/08/21  huangys  v1.0      Create
 * </pre>
 * 
 * @param <IN> 传入元素类型
 * @param <OUT> 输出元素类型
 */
public interface Function<IN, OUT> {
	
	/**
	 * <pre> 函数处理过程.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/21  huangys  Create
	 * </pre>
	 * 
	 * @param in 传入元素
	 * @return 输出元素
	 */
	@Nullable
	OUT apply(@Nullable final IN in);
	
}