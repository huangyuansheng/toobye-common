/*
 * Copyright 2014 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2014/05/26.
 * 
 */
package com.toobye.common.dbenums;

/**
 * <pre> 数据库枚举类型.
 * 显示值和实际保存值可以不一致。
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2014/06/06  huangys  v1.0      Create
 * </pre>
 * 
 * @param <ValueType>
 */
public interface DBEnum<ValueType> {
	
	/**
	 * <pre> 获得数据库的实际保存值.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/06/06  huangys  Create
	 * </pre>
	 * 
	 * @return 数据库的实际保存值
	 */
	public ValueType getValue();
	
}
