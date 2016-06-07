/*
 * Copyright 2010 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2010/02/01.
 * 
 */
package com.toobye.common.framework;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.toobye.common.lang.Checks;
import com.toobye.common.string.StringUtils;

/**
 * <pre> 日志输出工具.
 * Utility for Log Output.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2010/02/01  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class Logs {
	
	private Logs() { }
	
	static {
		System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.Log4JLogger");
	}
	
	/**
	 * <pre> 初始化.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/02/11  huangys  Create
	 * </pre>
	 * 
	 * @param logFile 日志文件名称
	 */
	public static void init(@Nonnull final String logFile) {
		Checks.nullThrow(logFile);
		System.setProperty("SERVICE_LOG_FILE_NAME", logFile);
	}
	
    /**
     * <pre> 取得一个日志实例.
     * 
     * Modification History:
     * Date        Author   Action
     * 2016/02/08  huangys  Create
     * </pre>
     * 
     * @return Logger
     */
	@Nonnull
    public static Log getRoot() {
        return LogFactory.getLog("");
    }
	
    /**
     * <pre> 取得一个日志实例.
     * 
     * Modification History:
     * Date        Author   Action
     * 2013/08/10  huangys  Create
     * </pre>
     * 
     * @param logName Logger名称
     * @return Logger
     */
    @Nonnull
    public static Log get(@Nullable final String logName) {
        return StringUtils.isBlank(logName) ? getRoot() : LogFactory.getLog(logName);
    }

    /**
     * <pre> 取得一个日志实例.
     * 
     * Modification History:
     * Date        Author   Action
     * 2013/08/10  huangys  Create
     * </pre>
     * 
     * @param obj 以该对象的Class名称作为Logger名称
     * @return Logger
     */
    @Nonnull
    public static Log get(@Nullable final Object obj) {
    	if (obj == null) {
			return getRoot();
		}
    	if (obj instanceof Class<?>) {
			return LogFactory.getLog(((Class<?>) obj).getName());
		} else {
			return get(obj.getClass().getName());
		}
    }
    
}
