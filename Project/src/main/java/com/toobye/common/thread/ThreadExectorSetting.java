/*
 * Copyright 2016 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2016/03/12.
 * 
 */
package com.toobye.common.thread;

import java.util.Collection;
import java.util.Date;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.logging.Log;

import com.toobye.common.concurrent.TimeSlice;
import com.toobye.common.reflect.ToString;
import com.toobye.common.time.DateComparator;
import com.toobye.common.time.DateFormat;

/**
 * <pre> 多线程执行设置.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2016/03/12  huangys  v1.0      Create
 * </pre>
 * 
 * @param <P> 产品
 */
public final class ThreadExectorSetting<P> extends ToString {

	private boolean showProcess;
	private Log log;
	private int showProgressInterval;
	private Collection<P> result;
	private int total;
	
	/**
	 * <pre> 是否显示执行进度.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/03/12  huangys  Create
	 * </pre>
	 * 
	 * @return 是否
	 */
	@Nonnull
	public boolean isShowProcess() {
		return showProcess;
	}
	
	/**
	 * <pre> 设置是否显示执行进度.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/03/12  huangys  Create
	 * </pre>
	 * 
	 * @param showProcess 是否显示
	 */
	public void setShowProcess(@Nonnull final boolean showProcess) {
		this.showProcess = showProcess;
	}
	
	/**
	 * <pre> 获得日志打印对象.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/03/12  huangys  Create
	 * </pre>
	 * 
	 * @return 日志打印对象
	 */
	@Nullable
	public Log getLog() {
		return log;
	}
	
	/**
	 * <pre> 设置日志打印对象.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/03/12  huangys  Create
	 * </pre>
	 * 
	 * @param log 日志打印对象
	 */
	public void setLog(@Nullable final Log log) {
		this.log = log;
	}
	
	/**
	 * <pre> 获取显示进度的间隔频率.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/03/12  huangys  Create
	 * </pre>
	 * 
	 * @return 显示进度的间隔频率
	 */
	@Nonnull
	public int getShowProgressInterval() {
		return showProgressInterval;
	}

	/**
	 * <pre> 设置显示进度的间隔频率.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/03/12  huangys  Create
	 * </pre>
	 * 
	 * @param showProgressInterval 显示进度的间隔频率
	 */
	public void setShowProgressInterval(@Nonnull final int showProgressInterval) {
		this.showProgressInterval = showProgressInterval;
	}
	
	/**
	 * <pre> 获得执行结果.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/03/12  huangys  Create
	 * </pre>
	 * 
	 * @return 执行结果
	 */
	@Nullable
	public Collection<P> getResult() {
		return result;
	}
	
	/**
	 * <pre> 设置执行结果（存放对象）.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/03/12  huangys  Create
	 * </pre>
	 * 
	 * @param result 执行结果（存放对象）
	 */
	public void setResult(@Nullable final Collection<P> result) {
		this.result = result;
	}
	
	/**
	 * <pre> 获得处理任务总数.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/03/12  huangys  Create
	 * </pre>
	 * 
	 * @return 任务总数
	 */
	@Nonnull
	public int getTotal() {
		return total;
	}
	
	/**
	 * <pre> 设置处理任务总数.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/03/12  huangys  Create
	 * </pre>
	 * 
	 * @param total 任务总数
	 */
	public void setTotal(@Nonnull final int total) {
		this.total = total;
	}
	
	private Date bgnTime;
	private Date lastTime;
	/**
	 * <pre> 执行前初始化.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/03/12  huangys  Create
	 * </pre>
	 * 
	 */
	public void init() {
		bgnTime = new Date();
		lastTime = bgnTime;
	}
	
	private int completeCount;
	private int lastCompleteCount = 0;
	/**
	 * <pre> 显示进度.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/03/12  huangys  Create
	 * </pre>
	 * 
	 */
	public synchronized void showProgress() {
		// 增加完成任务数
		completeCount++;
		// 是否显示进度
		if (!showProcess) {
			return;
		}
		// 是否满足显示进度的要求
		if (showProgressInterval != 0) {
			if (completeCount % showProgressInterval != 0) {
				return;
			}
		} else {
			if (total != 0) {
				// 默认百分之一输出一次
				if (100 * (completeCount - lastCompleteCount) < total) {
					return;
				}
			} else {
				// 默认一千条任务输出一次
				if (completeCount % 1000 != 0) {
					return;
				}
			}
		}
		// 耗时计算
		Date now = new Date();
		TimeSlice processElapseTime = DateComparator.dateMinus(now, lastTime);
		lastTime = now;
		TimeSlice totalElapseTime = DateComparator.dateMinus(now, bgnTime);
		long evaluateLeftSeconds = 0;
		if (total != 0) {
			evaluateLeftSeconds = processElapseTime.toSeconds() * (total - completeCount) / (completeCount - lastCompleteCount);
		}
		// 日志组装
		String msg = DateFormat.getPreciseness(now)
				+ " - " + completeCount + (total == 0 ? "" : "/" + total)
				+ " - TET/" + totalElapseTime.toSeconds() + "s"
				+ " - PET/" + processElapseTime.toSeconds() + "s"
				+ (total == 0 ? "" : " - ELT/" + evaluateLeftSeconds + "s");
		if (log == null) {
			System.out.println(msg);
		} else {
			log.info(msg);
		}
		lastCompleteCount = completeCount;
	}
	
}
