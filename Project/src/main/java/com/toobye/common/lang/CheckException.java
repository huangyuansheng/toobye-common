/*
 * Copyright 2015 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2015/12/08.
 * 
 */
package com.toobye.common.lang;

/**
 * <pre> 校验异常.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2015/12/08  huangys  v1.0      Create
 * </pre>
 * 
 */
public class CheckException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * <pre> 构造器. </pre>
	 *
	 */
	public CheckException() {
        super();
    }

    /**
     * <pre> 构造器. </pre>
     *
     * @param message 消息
     * @param cause 原因
     */
    public CheckException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * <pre> 构造器. </pre>
     *
     * @param message 消息
     */
    public CheckException(final String message) {
        super(message);
    }

    /**
     * <pre> 构造器. </pre>
     *
     * @param cause 原因
     */
    public CheckException(final Throwable cause) {
        super(cause);
    }
    
}
