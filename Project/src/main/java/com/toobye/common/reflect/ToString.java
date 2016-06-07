/*
 * Copyright 2014 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2014/02/28.
 * 
 */
package com.toobye.common.reflect;

/**
 * <pre> 自动生成ToString方法.
 * 该方法会自动格式化对象内所有属性，可以提高代码测试效率。
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2014/02/28  huangys  v1.0      Create
 * </pre>
 * 
 */
public class ToString {
	
	@Override
	public final String toString() {
		return Properties.toStringSmartHorizontal(this);
	}

}
