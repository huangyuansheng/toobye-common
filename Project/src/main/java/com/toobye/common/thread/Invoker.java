/*
 * Copyright 2014 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2014/10/15.
 * 
 */
package com.toobye.common.thread;

import java.util.Date;
import java.util.concurrent.Callable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.util.concurrent.SimpleTimeLimiter;
import com.toobye.common.concurrent.TimeSlice;
import com.toobye.common.lang.Checks;
import com.toobye.common.lang.Processors;

/**
 * <pre> 调用工具.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2014/10/15  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class Invoker {
	
	private Invoker() { }
	
	/**
	 * <pre> 看门狗.
 	 * 	即使异常，SimpleTimeLimiter对象仍在线程中运行。 </pre>
	 */
	private static final SimpleTimeLimiter STL = new SimpleTimeLimiter();
	
	/**
	 * <pre> 超时监视.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/30  huangys  Create
	 * </pre>
	 * 
	 * @param task 任务
	 * @param timeout 超时时间
	 */
	public static void timeout(@Nonnull final Runnable task, @Nullable final TimeSlice timeout) {
		timeout(Processors.toCallable(task), timeout);
	}
	
	/**
	 * <pre> 超时监视.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/29  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 调用对象的返回类型
	 * @param task 任务
	 * @param timeout 超时时间
	 * @return 调用对象的返回结果
	 */
	@Nullable
	public static <T> T timeout(@Nonnull final Callable<T> task, @Nullable final TimeSlice timeout) {
		try {
			if (timeout == null) {
				return task.call();
			} else {
				return STL.callWithTimeout(task, timeout.getValue(), timeout.getUnit(), true);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 调用并持续指定时间（含运行时间，超时不中断）.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/05  huangys  Create
	 * </pre>
	 * 
	 * @param task 任务
	 * @param duration 持续时间
	 */
	@Nullable
	public static void last(@Nonnull final Runnable task, @Nullable final TimeSlice duration) {
		last(Processors.toCallable(task), duration);
	}
	
	/**
	 * <pre> 调用并持续指定时间（含运行时间，超时不中断）.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/10/15  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 返回值类型
	 * @param task 任务
	 * @param duration 持续时间
	 * @return 任务调用返回值
	 */
	@Nullable
	public static <T> T last(@Nonnull final Callable<T> task, @Nullable final TimeSlice duration) {
		Checks.nullThrow(task);
		try {
			Date bgnTime = new Date();
			T ret = task.call();
			if (duration != null) {
				duration.sleepExceptUsed(bgnTime);
			}
			return ret;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 调用后暂停指定时间.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/05  huangys  Create
	 * </pre>
	 * 
	 * @param task 任务
	 * @param interval 间隔时间
	 */
	@Nullable
	public static void halt(@Nonnull final Runnable task, @Nullable final TimeSlice interval) {
		halt(Processors.toCallable(task), interval);
	}
	
	/**
	 * <pre> 调用后暂停指定时间.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/10/15  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 返回值类型
	 * @param task 任务
	 * @param interval 间隔时间
	 * @return 任务调用返回值
	 */
	@Nullable
	public static <T> T halt(@Nonnull final Callable<T> task, @Nullable final TimeSlice interval) {
		Checks.nullThrow(task);
		try {
			T ret = task.call();
			if (interval != null) {
				interval.sleep();
			}
			return ret;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 多次尝试调用.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/11/18  huangys  Create
	 * </pre>
	 * 
	 * @param task 任务
	 * @param times 尝试次数
	 */
	public static void retry(@Nonnull final Runnable task, @Nonnull final int times) {
		retry(task, times, null);
	}
	
	/**
	 * <pre> 多次尝试调用.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/11/18  huangys  Create
	 * </pre>
	 * 
	 * @param task 任务
	 * @param times 尝试次数
	 * @param interval 重试的时间间隔
	 */
	public static void retry(@Nonnull final Runnable task, @Nonnull final int times, @Nullable final TimeSlice interval) {
		retry(Processors.toCallable(task), times, interval);
	}
	
	/**
	 * <pre> 多次尝试调用.
	 * 返回值允许为Null.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/12/24  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 返回值类型
	 * @param task 任务
	 * @param times 尝试次数
	 * @return 任务调用的返回值
	 */
	@Nullable
	public static <T> T retry(@Nonnull final Callable<T> task, @Nonnull final int times) {
		return retry(task, times, true);
	}
	
	/**
	 * <pre> 多次尝试调用.
	 * 返回值不允许为Null.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/12/24  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 返回值类型
	 * @param task 任务
	 * @param times 尝试次数
	 * @return 任务调用的返回值
	 */
	@Nullable
	public static <T> T retryNotNull(@Nonnull final Callable<T> task, @Nonnull final int times) {
		return retry(task, times, false);
	}
	
	/**
	 * <pre> 多次尝试调用.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/12/24  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 返回值类型
	 * @param task 任务
	 * @param times 尝试次数
	 * @param allowedReturnNull 任务调用的返回值是否允许为空
	 * @return 任务调用的返回值
	 */
	@Nullable
	public static <T> T retry(@Nonnull final Callable<T> task, @Nonnull final int times, @Nonnull final boolean allowedReturnNull) {
		return retry(task, times, allowedReturnNull, null);
	}
	
	/**
	 * <pre> 多次尝试调用.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/05  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 返回值类型
	 * @param task 任务
	 * @param times 尝试次数
	 * @param interval 重试的时间间隔
	 * @return 任务调用的返回值
	 */
	@Nullable
	public static <T> T retry(@Nonnull final Callable<T> task, @Nonnull final int times, @Nullable final TimeSlice interval) {
		return retry(task, times, true, interval);
	}
	
	/**
	 * <pre> 多次尝试调用.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/12/24  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 返回值类型
	 * @param task 任务
	 * @param times 尝试次数
	 * @param allowedReturnNull 任务调用的返回值是否允许为空
	 * @param interval 重试的时间间隔
	 * @return 任务调用的返回值
	 */
	@Nullable
	public static <T> T retry(@Nonnull final Callable<T> task, @Nonnull final int times, @Nonnull final boolean allowedReturnNull, @Nullable final TimeSlice interval) {
		Checks.nullThrow(task);
		int tmp = times;
		while (--tmp > 0) {
			try {
				T ret = task.call();
				if (allowedReturnNull || ret != null) {
					return ret;
				}
			} catch (Exception e) {
				// Nothing
			}
			if (interval != null) {
				interval.sleep();
			}
		}
		
		// 最后尝试一次，异常可以抛出
		T ret = null;
		try {
			ret = task.call();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		if (allowedReturnNull || ret != null) {
			return ret;
		} else {
			throw new RuntimeException("Return null!");
		}
	}
	
	/**
	 * <pre> 指定时间内尝试调用.
	 * 当尝试调用的用时超出了limit或次数超过了maxtimes后，将进行最后一次尝试。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/05  huangys  Create
	 * </pre>
	 * 
	 * @param task 任务
	 * @param limit 允许执行的时间限制
	 */
	@Nullable
	public static void waitFor(@Nonnull final Runnable task, @Nullable final TimeSlice limit) {
		waitFor(task, limit, null);
	}
	
	/**
	 * <pre> 指定时间内尝试调用.
	 * 当尝试调用的用时超出了limit或次数超过了maxtimes后，将进行最后一次尝试。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/05  huangys  Create
	 * </pre>
	 * 
	 * @param task 任务
	 * @param limit 允许执行的时间限制
	 * @param interval 每次重试的时间间隔
	 */
	@Nullable
	public static void waitFor(@Nonnull final Runnable task, @Nullable final TimeSlice limit, @Nullable final TimeSlice interval) {
		waitFor(task, limit, null, interval, null);
	}
	
	/**
	 * <pre> 指定时间内尝试调用.
	 * 当尝试调用的用时超出了limit或次数超过了maxtimes后，将进行最后一次尝试。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/05  huangys  Create
	 * </pre>
	 * 
	 * @param task 任务
	 * @param limit 允许执行的时间限制
	 * @param duration 每次调用的持续时间
	 * @param interval 每次重试的时间间隔
	 * @param maxTimes 允许做大尝试次数
	 */
	@Nullable
	public static void waitFor(@Nonnull final Runnable task, @Nullable final TimeSlice limit, @Nullable final TimeSlice duration, @Nullable final TimeSlice interval, @Nullable final Integer maxTimes) {
		waitFor(Processors.toCallable(task), limit, duration, interval, maxTimes, true);
	}
	
	/**
	 * <pre> 指定时间内尝试调用.
	 * 当尝试调用的用时超出了limit后，将进行最后一次尝试。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/05  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 返回值类型
	 * @param task 任务
	 * @param limit 允许执行的时间限制
	 * @return 任务调用的返回值
	 */
	@Nullable
	public static <T> T waitForNotNull(@Nonnull final Callable<T> task, @Nullable final TimeSlice limit) {
		return waitFor(task, limit, null, null, null, false);
	}
	
	/**
	 * <pre> 指定时间内尝试调用.
	 * 当尝试调用的用时超出了limit后，将进行最后一次尝试。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/05  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 返回值类型
	 * @param task 任务
	 * @param limit 允许执行的时间限制
	 * @param interval 每次重试的时间间隔
	 * @return 任务调用的返回值
	 */
	@Nullable
	public static <T> T waitForNotNull(@Nonnull final Callable<T> task, @Nullable final TimeSlice limit, @Nullable final TimeSlice interval) {
		return waitFor(task, limit, null, interval, null, false);
	}
	
	/**
	 * <pre> 指定时间内尝试调用.
	 * 当尝试调用的用时超出了limit后，将进行最后一次尝试。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/05  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 返回值类型
	 * @param task 任务
	 * @param limit 允许执行的时间限制
	 * @param allowedReturnNull 任务调用的返回值是否允许为空
	 * @return 任务调用的返回值
	 */
	@Nullable
	public static <T> T waitFor(@Nonnull final Callable<T> task, @Nullable final TimeSlice limit, @Nonnull final boolean allowedReturnNull) {
		return waitFor(task, limit, null, null, null, allowedReturnNull);
	}
	
	/**
	 * <pre> 指定时间内尝试调用.
	 * 当尝试调用的用时超出了limit后，将进行最后一次尝试。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/05  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 返回值类型
	 * @param task 任务
	 * @param limit 允许执行的时间限制
	 * @param interval 每次重试的时间间隔
	 * @param allowedReturnNull 任务调用的返回值是否允许为空
	 * @return 任务调用的返回值
	 */
	@Nullable
	public static <T> T waitFor(@Nonnull final Callable<T> task, @Nullable final TimeSlice limit, @Nullable final TimeSlice interval, @Nonnull final boolean allowedReturnNull) {
		return waitFor(task, limit, null, interval, null, allowedReturnNull);
	}
	
	/**
	 * <pre> 指定时间内尝试调用.
	 * 当尝试调用的用时超出了limit或次数超过了maxtimes后，将进行最后一次尝试。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/05  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 返回值类型
	 * @param task 任务
	 * @param limit 允许执行的时间限制
	 * @param duration 每次调用的持续时间
	 * @param interval 每次重试的时间间隔
	 * @param maxTimes 允许做大尝试次数
	 * @param allowedReturnNull 任务调用的返回值是否允许为空
	 * @return 任务调用的返回值
	 */
	@Nullable
	public static <T> T waitFor(@Nonnull final Callable<T> task, @Nullable final TimeSlice limit, @Nullable final TimeSlice duration, @Nullable final TimeSlice interval, @Nullable final Integer maxTimes, @Nonnull final boolean allowedReturnNull) {
		Checks.nullThrow(task);
		int tmp = maxTimes == null ? Integer.MAX_VALUE : maxTimes;
		long bgnDate = new Date().getTime();
		long mills = limit == null ? Long.MAX_VALUE : duration.toMillis();
		while ((new Date().getTime() - bgnDate) <= mills && --tmp > 0) {
			Date bgnTime = new Date();
			try {
				T ret = task.call();
				if (allowedReturnNull || ret != null) {
					return ret;
				}
			} catch (Exception e) {
				// Nothing
			}
			if (duration != null) {
				duration.sleepExceptUsed(bgnTime);
			}
			if (interval != null) {
				interval.sleep();
			}
		}
		
		// 最后尝试一次，异常可以抛出
		T ret = null;
		try {
			ret = task.call();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		if (allowedReturnNull || ret != null) {
			return ret;
		} else {
			throw new RuntimeException("Return null!");
		}
	}
	
}
