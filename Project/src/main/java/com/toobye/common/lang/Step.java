/*
 * Copyright 2016 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2016/04/15.
 * 
 */
package com.toobye.common.lang;

import javax.annotation.Nullable;

/**
 * <pre> 步骤.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2016/04/15  huangys  v1.0      Create
 * </pre>
 * 
 * @param <T> 步骤参数
 */
public interface Step<T> {

	/**
	 * <pre> 执行步骤.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/04/14  huangys  Create
	 * </pre>
	 * 
	 * @param params 参数
	 * @return 是否继续执行后续步骤
	 */
	public boolean doStep(@Nullable final T params);
	
}
