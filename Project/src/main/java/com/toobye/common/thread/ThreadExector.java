/*
 * Copyright 2015 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2015/06/02.
 * 
 */
package com.toobye.common.thread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.toobye.common.collection.Iterables;
import com.toobye.common.lang.Checks;
import com.toobye.common.lang.Doable;
import com.toobye.common.lang.DoablePipeline;
import com.toobye.common.lang.Function;
import com.toobye.common.lang.FunctionPipeline;
import com.toobye.common.lang.Processors;

/**
 * <pre> 多线程执行器.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2015/06/02  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class ThreadExector {

	private ThreadExector() { }
	
	/**
	 * <pre> 执行.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/02  huangys  Create
	 * </pre>
	 * 
	 * @param parallel 并发数
	 * @param times 执行次数
	 * @param task 任务
	 */
	public static void exec(final int parallel, final int times, final Runnable task) {
		exec(parallel, times, task, null);
	}
	
	/**
	 * <pre> 执行.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/02  huangys  Create
	 * </pre>
	 * 
	 * @param parallel 并发数
	 * @param times 执行次数
	 * @param task 任务
	 * @param setting 设置
	 */
	public static void exec(final int parallel, final int times, final Runnable task, final ThreadExectorSetting<Void> setting) {
		exec(new Void[parallel], new Void[times], Processors.toDoablePipeline(task), setting);
	}
	
	/**
	 * <pre> 执行.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/02  huangys  Create
	 * </pre>
	 * 
	 * @param <D> 材料类型
	 * @param parallel 并发数
	 * @param datas 材料
	 * @param task 任务
	 */
	public static <D> void exec(final int parallel, final D[] datas, final Doable<D> task) {
		exec(parallel, datas, task, null);
	}
	
	/**
	 * <pre> 执行.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/02  huangys  Create
	 * </pre>
	 * 
	 * @param <D> 材料类型
	 * @param parallel 并发数
	 * @param datas 材料
	 * @param task 任务
	 * @param setting 设置
	 */
	public static <D> void exec(final int parallel, final D[] datas, final Doable<D> task, final ThreadExectorSetting<Void> setting) {
		exec(new Void[parallel], Arrays.asList(datas), Processors.toDoablePipeline(task), setting);
	}
	
	/**
	 * <pre> 执行.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/02  huangys  Create
	 * </pre>
	 * 
	 * @param <D> 材料类型
	 * @param parallel 并发数
	 * @param datas 材料
	 * @param task 任务
	 */
	public static <D> void exec(final int parallel, final Iterable<D> datas, final Doable<D> task) {
		exec(parallel, datas, task, null);
	}
	
	/**
	 * <pre> 执行.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/02  huangys  Create
	 * </pre>
	 * 
	 * @param <D> 材料类型
	 * @param parallel 并发数
	 * @param datas 材料
	 * @param task 任务
	 * @param setting 设置
	 */
	public static <D> void exec(final int parallel, final Iterable<D> datas, final Doable<D> task, final ThreadExectorSetting<Void> setting) {
		exec(new Void[parallel], datas, Processors.toDoablePipeline(task), setting);
	}
	
	/**
	 * <pre> 执行.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/02  huangys  Create
	 * </pre>
	 * 
	 * @param <D> 材料类型
	 * @param parallel 并发数
	 * @param datas 材料
	 * @param task 任务
	 */
	public static <D> void exec(final int parallel, final Iterator<D> datas, final Doable<D> task) {
		exec(parallel, datas, task, null);
	}
	
	/**
	 * <pre> 执行.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/02  huangys  Create
	 * </pre>
	 * 
	 * @param <D> 材料类型
	 * @param parallel 并发数
	 * @param datas 材料
	 * @param task 任务
	 * @param setting 设置
	 */
	public static <D> void exec(final int parallel, final Iterator<D> datas, final Doable<D> task, final ThreadExectorSetting<Void> setting) {
		exec(new Void[parallel], datas, Processors.toDoablePipeline(task), setting);
	}
	
	/**
	 * <pre> 执行.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/02  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 工具类型
	 * @param <D> 材料类型
	 * @param tools 工具
	 * @param datas 材料
	 * @param task 任务
	 */
	public static <T, D> void exec(final T[] tools, final D[] datas, final DoablePipeline<T, D> task) {
		exec(tools, datas, task, null);
	}
	
	/**
	 * <pre> 执行.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/02  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 工具类型
	 * @param <D> 材料类型
	 * @param tools 工具
	 * @param datas 材料
	 * @param task 任务
	 * @param setting 设置
	 */
	public static <T, D> void exec(final T[] tools, final D[] datas, final DoablePipeline<T, D> task, final ThreadExectorSetting<Void> setting) {
		exec(tools, Arrays.asList(datas), task, setting);
	}
	
	/**
	 * <pre> 执行.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/02  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 工具类型
	 * @param <D> 材料类型
	 * @param tools 工具
	 * @param datas 材料
	 * @param task 任务
	 */
	public static <T, D> void exec(final T[] tools, final Iterable<D> datas, final DoablePipeline<T, D> task) {
		exec(tools, datas, task, null);
	}
	
	/**
	 * <pre> 执行.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/02  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 工具类型
	 * @param <D> 材料类型
	 * @param tools 工具
	 * @param datas 材料
	 * @param task 任务
	 * @param setting 设置
	 */
	public static <T, D> void exec(final T[] tools, final Iterable<D> datas, final DoablePipeline<T, D> task, final ThreadExectorSetting<Void> setting) {
		get(tools, datas, Processors.toFunctionPipeline(task), setting == null ? new ThreadExectorSetting<Void>() : setting);
	}
	
	/**
	 * <pre> 执行.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/02  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 工具类型
	 * @param <D> 材料类型
	 * @param tools 工具
	 * @param datas 材料
	 * @param task 任务
	 */
	public static <T, D> void exec(final T[] tools, final Iterator<D> datas, final DoablePipeline<T, D> task) {
		exec(tools, datas, task, null);
	}
	
	/**
	 * <pre> 执行.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/02  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 工具类型
	 * @param <D> 材料类型
	 * @param tools 工具
	 * @param datas 材料
	 * @param task 任务
	 * @param setting 设置
	 */
	public static <T, D> void exec(final T[] tools, final Iterator<D> datas, final DoablePipeline<T, D> task, final ThreadExectorSetting<Void> setting) {
		get(tools, datas, Processors.toFunctionPipeline(task), setting == null ? new ThreadExectorSetting<Void>() : setting);
	}
	
	/**
	 * <pre> 执行.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/02  huangys  Create
	 * </pre>
	 * 
	 * @param <P> 产品类型
	 * @param parallel 并发数
	 * @param times 执行次数
	 * @param task 任务
	 * @return 产品列表
	 */
	public static <P> Collection<P> get(final int parallel, final int times, final Callable<P> task) {
		return get(parallel, times, task, null);
	}
	
	/**
	 * <pre> 执行.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/02  huangys  Create
	 * </pre>
	 * 
	 * @param <P> 产品类型
	 * @param parallel 并发数
	 * @param times 执行次数
	 * @param task 任务
	 * @param setting 设置
	 * @return 产品列表
	 */
	public static <P> Collection<P> get(final int parallel, final int times, final Callable<P> task, final ThreadExectorSetting<P> setting) {
		return get(new Void[parallel], new Void[times], Processors.toFunctionPipeline(task), setting);
	}
	
	
	/**
	 * <pre> 执行.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/02  huangys  Create
	 * </pre>
	 * 
	 * @param <D> 材料类型
	 * @param <P> 产品类型
	 * @param parallel 并发数
	 * @param datas 材料
	 * @param task 任务
	 * @return 产品列表
	 */
	public static <D, P> Collection<P> get(final int parallel, final D[] datas, final Function<D, P> task) {
		return get(parallel, datas, task, null);
	}
	
	/**
	 * <pre> 执行.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/02  huangys  Create
	 * </pre>
	 * 
	 * @param <D> 材料类型
	 * @param <P> 产品类型
	 * @param parallel 并发数
	 * @param datas 材料
	 * @param task 任务
	 * @param setting 设置
	 * @return 产品列表
	 */
	public static <D, P> Collection<P> get(final int parallel, final D[] datas, final Function<D, P> task, final ThreadExectorSetting<P> setting) {
		return get(new Void[parallel], Arrays.asList(datas), Processors.toFunctionPipeline(task), setting);
	}
	
	/**
	 * <pre> 执行.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/02  huangys  Create
	 * </pre>
	 * 
	 * @param <D> 材料类型
	 * @param <P> 产品类型
	 * @param parallel 并发数
	 * @param datas 材料
	 * @param task 任务
	 * @return 产品列表
	 */
	public static <D, P> Collection<P> get(final int parallel, final Iterable<D> datas, final Function<D, P> task) {
		return get(parallel, datas, task, null);
	}
	
	/**
	 * <pre> 执行.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/02  huangys  Create
	 * </pre>
	 * 
	 * @param <D> 材料类型
	 * @param <P> 产品类型
	 * @param parallel 并发数
	 * @param datas 材料
	 * @param task 任务
	 * @param setting 设置
	 * @return 产品列表
	 */
	public static <D, P> Collection<P> get(final int parallel, final Iterable<D> datas, final Function<D, P> task, final ThreadExectorSetting<P> setting) {
		return get(new Void[parallel], datas.iterator(), Processors.toFunctionPipeline(task), setting);
	}
	
	/**
	 * <pre> 执行.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/02  huangys  Create
	 * </pre>
	 * 
	 * @param <D> 材料类型
	 * @param <P> 产品类型
	 * @param parallel 并发数
	 * @param datas 材料
	 * @param task 任务
	 * @return 产品列表
	 */
	public static <D, P> Collection<P> get(final int parallel, final Iterator<D> datas, final Function<D, P> task) {
		return get(parallel, datas, task, null);
	}
	
	/**
	 * <pre> 执行.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/02  huangys  Create
	 * </pre>
	 * 
	 * @param <D> 材料类型
	 * @param <P> 产品类型
	 * @param parallel 并发数
	 * @param datas 材料
	 * @param task 任务
	 * @param setting 设置
	 * @return 产品列表
	 */
	public static <D, P> Collection<P> get(final int parallel, final Iterator<D> datas, final Function<D, P> task, final ThreadExectorSetting<P> setting) {
		return get(new Void[parallel], datas, Processors.toFunctionPipeline(task), setting);
	}
	
	/**
	 * <pre> 执行.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/02  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 工具类型
	 * @param <D> 材料类型
	 * @param <P> 产品类型
	 * @param tools 工具
	 * @param datas 材料
	 * @param task 任务
	 * @return 产品列表
	 */
	public static <T, D, P> Collection<P> get(final T[] tools, final D[] datas, final FunctionPipeline<T, D, P> task) {
		return get(tools, datas, task, null);
	}
	
	/**
	 * <pre> 执行.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/02  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 工具类型
	 * @param <D> 材料类型
	 * @param <P> 产品类型
	 * @param tools 工具
	 * @param datas 材料
	 * @param task 任务
	 * @param setting 设置
	 * @return 产品列表
	 */
	public static <T, D, P> Collection<P> get(final T[] tools, final D[] datas, final FunctionPipeline<T, D, P> task, @Nullable final ThreadExectorSetting<P> setting) {
		return get(tools, Arrays.asList(datas), task, null);
	}
	
	/**
	 * <pre> 多线程加工.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/20  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 工具类型
	 * @param <D> 材料类型
	 * @param <P> 产品类型
	 * @param tools 工具
	 * @param dataIterator 材料
	 * @param task 任务
	 * @return 产品
	 */
	@Nonnull
	public static <T, D, P> Collection<P> get(@Nonnull final T[] tools, @Nonnull final Iterator<D> dataIterator, @Nonnull final FunctionPipeline<T, D, P> task) {
		return get(tools, dataIterator, task, null);
	}
	
	/**
	 * <pre> 多线程加工.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/20  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 工具类型
	 * @param <D> 材料类型
	 * @param <P> 产品类型
	 * @param tools 工具
	 * @param dataIterator 材料
	 * @param task 任务
	 * @param setting 设置
	 * @return 产品
	 */
	@Nonnull
	public static <T, D, P> Collection<P> get(@Nonnull final T[] tools, @Nonnull final Iterator<D> dataIterator, @Nonnull final FunctionPipeline<T, D, P> task, @Nullable final ThreadExectorSetting<P> setting) {
		Checks.nullThrow(tools);
		Checks.nullThrow(dataIterator);
		Checks.nullThrow(task);
		ThreadExectorSetting<P> tmp = setting;
		if (setting == null) {
			tmp = new ThreadExectorSetting<>();
			final List<P> result = new ArrayList<>();
			Collections.synchronizedList(result);
			tmp.setResult(result);
		}
		new ThreadFunctionPipeline<>(tools, dataIterator, tmp, task).start();
		return tmp.getResult();
	}
	
	/**
	 * <pre> 多线程加工.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/20  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 工具类型
	 * @param <D> 材料类型
	 * @param <P> 产品类型
	 * @param tools 工具
	 * @param dataIterable 材料
	 * @param task 任务
	 * @return 产品
	 */
	@Nonnull
	public static <T, D, P> Collection<P> get(@Nonnull final T[] tools, @Nonnull final Iterable<D> dataIterable, @Nonnull final FunctionPipeline<T, D, P> task) {
		Checks.nullThrow(dataIterable);
		return get(tools, dataIterable, task, null);
	}
	
	/**
	 * <pre> 多线程加工.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/20  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 工具类型
	 * @param <D> 材料类型
	 * @param <P> 产品类型
	 * @param tools 工具
	 * @param dataIterable 材料
	 * @param task 任务
	 * @param setting 设置
	 * @return 产品
	 */
	@Nonnull
	public static <T, D, P> Collection<P> get(@Nonnull final T[] tools, @Nonnull final Iterable<D> dataIterable, @Nonnull final FunctionPipeline<T, D, P> task, @Nullable final ThreadExectorSetting<P> setting) {
		Checks.nullThrow(dataIterable);
		ThreadExectorSetting<P> tmp = setting;
		if (setting == null) {
			tmp = new ThreadExectorSetting<>();
			tmp.setTotal(Iterables.size(dataIterable));
			final List<P> result = new ArrayList<>();
			// 性能差
			// final List<P> result = new CopyOnWriteArrayList<>();
			Collections.synchronizedList(result);
			tmp.setResult(result);
		} else {
			if (tmp.getTotal() == 0) {
				tmp.setTotal(Iterables.size(dataIterable));
			}
		}
		return get(tools, dataIterable.iterator(), task, setting);
	}
	
}
