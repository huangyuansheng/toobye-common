/*
 * Copyright 2014 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2014/10/20.
 * 
 */
package com.toobye.common.thread;

import java.util.Date;

import javax.annotation.Nullable;

import com.toobye.common.concurrent.TimeSlice;

/**
 * <pre> 可终止的循序运行线程.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2014/10/20  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class ThreadLoopInterruptible extends Thread {

	private volatile boolean terminated = false;
	private volatile TimeSlice duration; 
	private volatile TimeSlice interval; 
	
	/**
	 * <pre> 终止线程.
	 * 等待当次运行结束后结束线程。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/10/20  huangys  Create
	 * </pre>
	 * 
	 */
	public void terminate() {
		terminated = true;
	}
	
	@Override
	public void run() {
		while (!terminated) {
			Date bgnTime = new Date();
			superRun();
			if (duration != null) {
				duration.sleepExceptUsed(bgnTime);
			}
			if (interval != null) {
				interval.sleep();
			}
		}
	}

	/**
	 * <pre> 获得持续时间.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/28  huangys  Create
	 * </pre>
	 * 
	 * @return 持续时间
	 */
	@Nullable
	public TimeSlice getDuration() {
		return duration;
	}

	/**
	 * <pre> 设置持续时间.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/28  huangys  Create
	 * </pre>
	 * 
	 * @param duration 持续时间
	 */
	public void setDuration(@Nullable final TimeSlice duration) {
		this.duration = duration;
	}

	/**
	 * <pre> 获得间隔时间.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/28  huangys  Create
	 * </pre>
	 * 
	 * @return 间隔时间
	 */
	@Nullable
	public TimeSlice getInterval() {
		return interval;
	}

	/**
	 * <pre> 设置间隔时间.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/28  huangys  Create
	 * </pre>
	 * 
	 * @param interval 间隔时间
	 */
	public void setInterval(@Nullable final TimeSlice interval) {
		this.interval = interval;
	}
	
	/**
	 * <pre> 运行实体.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/10/20  huangys  Create
	 * </pre>
	 * 
	 */
	public void superRun() {
		super.run();
	}
	
    /**
     * <pre> 构造体. </pre>
     *
     * @param target 运行实体
     */
    public ThreadLoopInterruptible(final Runnable target) {
    	super(target);
    }

    /**
     * <pre> 构造体. </pre>
     *
     * @param group 组名
     * @param target 运行实体
     */
    public ThreadLoopInterruptible(final ThreadGroup group, final Runnable target) {
    	super(group, target);
    }
	
}
