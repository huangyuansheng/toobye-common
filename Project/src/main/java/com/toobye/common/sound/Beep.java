/*
 * Copyright 2014 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2014/11/07.
 * 
 */
package com.toobye.common.sound;

import java.awt.Toolkit;

import com.toobye.common.concurrent.TimeSlice;
import com.toobye.common.thread.Invoker;

/**
 * <pre> 蜂鸣.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2014/11/07  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class Beep {
	
	private Beep() { }

	private static final Toolkit KIT = Toolkit.getDefaultToolkit();
	/**
	 * <pre> 单次蜂鸣.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/11/07  huangys  Create
	 * </pre>
	 * 
	 */
	public static void beep() {
		KIT.beep();
	}
	
	/**
	 * <pre> 多次蜂鸣.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/11/07  huangys  Create
	 * </pre>
	 * 
	 * @param times 次数
	 * @param interval 间隔时间
	 */
	public static void beep(final int times, final TimeSlice interval) {
		for (int i = 0; i < times - 1; i++) {
			Invoker.last(new Runnable() {
				@Override
				public void run() {
					beep();
				}
			}, interval);
		}
		beep();
	}
	
}
