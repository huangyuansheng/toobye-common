/*
 * Copyright 2014 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2014/01/07.
 * 
 */
package com.toobye.common.base;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import javax.annotation.Nonnull;

import com.toobye.common.lang.Checks;

/**
 * <pre> 异常工具类.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2014/01/07  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class Exceptions {
	
	private Exceptions() { }
	
	/**
	 * <pre> 获得异常的栈信息.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2011/11/15  huangys  Create
	 * </pre>
	 * 
	 * @param t 异常
	 * @return 异常的栈信息
	 */
	@Nonnull
	public static String getStackTrace(@Nonnull final Throwable t) {
		Checks.nullThrow(t);
		Writer sw = new StringWriter();
	    t.printStackTrace(new PrintWriter(sw));
	   	return sw.toString();
	}

	/**
	 * <pre> 抛出"未实现"异常.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/01  huangys  Create
	 * </pre>
	 * 
	 */
	public static void throwNoImplementation() {
		throw new RuntimeException("No Implementation.");
	}
	
	/**
	 * <pre> 获取代码位置信息.
	 * 用在明确知道错误原因，且无需异常方式结束程序的地方。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/05/07  huangys  Create
	 * </pre>
	 * 
	 * @return 代码位置信息
	 */
	public static String getCodeLocation() {
		return "Position: " + new Exceptions().generateCodeLocation(2) + '\n';
	}

	/**
	 * <pre> 生成代码位置信息.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/05/07  huangys  Create
	 * </pre>
	 * 
	 * @return 代码位置信息
	 */
	private String generateCodeLocation(final int stacksPosition) {
        StackTraceElement e = new Throwable().getStackTrace()[stacksPosition];
        StringBuffer sb = new StringBuffer()
        					.append(e.getClassName())
        					.append(".").append(e.getMethodName())
        					.append("(Line ").append(e.getLineNumber()).append(")");
        return sb.toString();
	}
	
}
