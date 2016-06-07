/*
 * Copyright 2015 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2015/06/02.
 * 
 */
package com.toobye.common.lang;

import javax.annotation.Nullable;

/**
 * <pre> 流水线带参任务定义.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2015/06/02  huangys  v1.0      Create
 * </pre>
 * 
 * @param <Tool> 工具类型
 * @param <Data> 材料类型
 */
public interface DoablePipeline<Tool, Data> {

	/**
	 * <pre> 加工处理.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/02  huangys  Create
	 * </pre>
	 * 
	 * @param tool 工具
	 * @param data 材料
	 */
	 void process(@Nullable final Tool tool, @Nullable final Data data);
	
}
