/*
 * Copyright 2014 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2014/09/25.
 * 
 */
package com.toobye.common.thread;

import java.util.Collection;
import java.util.Iterator;

import javax.annotation.Nonnull;

import com.toobye.common.concurrent.TimeSlice;
import com.toobye.common.lang.FunctionPipeline;
import com.toobye.common.lang.Pair;

/**
 * <pre> 多线程执行.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2016/03/12  huangys  v1.0      Create
 * </pre>
 * 
 * @param <T> 工具
 * @param <D> 数据
 * @param <P> 产品
 */
public final class ThreadFunctionPipeline<T, D, P> {
	
	private T[] tools;
	private Iterator<D> dataIterator;
	private FunctionPipeline<T, D, P> task;
	private ThreadExectorSetting<P> setting;
	private int completeThreadCount = 0;
	
	/**
	 * <pre> 构造器. </pre>
	 *
	 * @param tools 工具
	 * @param dataIterator 数据
	 * @param setting 设置
	 * @param task 任务
	 */
	public ThreadFunctionPipeline(@Nonnull final T[] tools, @Nonnull final Iterator<D> dataIterator, final ThreadExectorSetting<P> setting, @Nonnull final FunctionPipeline<T, D, P> task) {
		this.tools = tools;
		this.dataIterator = dataIterator;
		this.task = task;
		this.setting = setting;
	}
	
	/**
	 * <pre> 实现FunctionPipeline多线程.
	 * 
	 * Modification History:
	 * Date        Author   Version   Action
	 * 2015/06/03  huangys  v1.0      Create
	 * </pre>
	 * 
	 * @param <T> 工具类型
	 * @param <D> 材料类型
	 * @param <P> 产品类型
	 */
	private static class TheThread<T, D, P> extends Thread {
		private T tool;
		private FunctionPipeline<T, D, P> task;
		private ThreadExectorSetting<P> setting;
		private ThreadFunctionPipeline<T, D, P> parent;

		/**
		 * <pre> 构造器. </pre>
		 *
		 * @param tool 工具
		 * @param task 任务
		 * @param setting 设置
		 * @param parent 主控进程
		 */
		public TheThread(final T tool, final FunctionPipeline<T, D, P> task, final ThreadExectorSetting<P> setting, final ThreadFunctionPipeline<T, D, P> parent) {
			this.tool = tool;
			this.task = task;
			this.setting = setting;
			this.parent = parent;
		}
		
		@Override
		public void run() {
			while (true) {
				try {
					Pair<Boolean, D> next = parent.getNextData();
					if (!next.getLeft()) {
						break;
					}
					P one = task.process(tool, next.getRight());
					if (setting.getResult() != null) {
						setting.getResult().add(one);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				setting.showProgress();
			}
			parent.completeThread();
		}
		
	}
	
	private synchronized void completeThread() {
		completeThreadCount++;
	}
	
	private synchronized Pair<Boolean, D> getNextData() {
		return dataIterator.hasNext() ? Pair.of(true, dataIterator.next()) : Pair.of(false, (D) null);
	}
	
	/**
	 * <pre> 并发执行.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/03/12  huangys  Create
	 * </pre>
	 * 
	 * @return 执行结果
	 */
	public Collection<P> start() {
		setting.init();
		for (int i = 0; i < tools.length; i++) {
			new TheThread<T, D, P>(tools[i], task, setting, this).start();
		}
		// 等待所有线程结束
		while (completeThreadCount != tools.length) {
			TimeSlice.milliSeconds(100).sleep();
		}
		return setting.getResult();
	}
	
}
