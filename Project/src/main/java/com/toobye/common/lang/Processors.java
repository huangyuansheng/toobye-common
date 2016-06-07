/*
 * Copyright 2015 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2015/01/01.
 * 
 */
package com.toobye.common.lang;

import java.util.concurrent.Callable;

import javax.annotation.Nonnull;

/**
 * <pre> 加工类.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2015/01/01  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class Processors {
	
	private Processors() { }
	
	/**
	 * <pre> Not条件.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/03  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 判断对象类型
	 * @param condition 条件
	 * @return Not条件
	 */
	@Nonnull
	public static <T> Condition<T> not(@Nonnull final Condition<T> condition) {
		Checks.nullThrow(condition);
		return new Condition<T>() {
			@Override
			public boolean match(final T t) {
				return !condition.match(t);
			}
		};
	}
	
	/**
	 * <pre> Or条件合并.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/03  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 判断对象类型
	 * @param conditions 条件
	 * @return 合并为单条件
	 */
	@Nonnull
	public static <T> Condition<T> or(@Nonnull final Iterable<Condition<T>> conditions) {
		Checks.emptyThrow(conditions);
		for (Condition<T> c : conditions) {
			Checks.nullThrow(c);
		}
		return new Condition<T>() {
			@Override
			public boolean match(final T t) {
				for (Condition<T> c : conditions) {
					if (c.match(t)) {
						return true;
					}
				}
				return false;
			}
		};
	}
	
	/**
	 * <pre> Or条件合并.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/03  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 判断对象类型
	 * @param conditions 条件
	 * @return 合并为单条件
	 */
	@SafeVarargs
	@Nonnull
	public static <T> Condition<T> or(@Nonnull final Condition<T>... conditions) {
		Checks.emptyThrow(conditions);
		for (Condition<T> c : conditions) {
			Checks.nullThrow(c);
		}
		return new Condition<T>() {
			@Override
			public boolean match(final T t) {
				for (Condition<T> c : conditions) {
					if (c.match(t)) {
						return true;
					}
				}
				return false;
			}
		};
	}
	
	/**
	 * <pre> And条件合并.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/03  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 判断对象类型
	 * @param conditions 条件
	 * @return 合并为单条件
	 */
	@Nonnull
	public static <T> Condition<T> and(@Nonnull final Iterable<Condition<T>> conditions) {
		Checks.emptyThrow(conditions);
		for (Condition<T> c : conditions) {
			Checks.nullThrow(c);
		}
		return new Condition<T>() {
			@Override
			public boolean match(final T t) {
				for (Condition<T> c : conditions) {
					if (!c.match(t)) {
						return false;
					}
				}
				return true;
			}
		};
	}
	
	/**
	 * <pre> And条件合并.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/03  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 判断对象类型
	 * @param conditions 条件
	 * @return 合并为单条件
	 */
	@SafeVarargs
	@Nonnull
	public static <T> Condition<T> and(@Nonnull final Condition<T>... conditions) {
		Checks.emptyThrow(conditions);
		for (Condition<T> c : conditions) {
			Checks.nullThrow(c);
		}
		return new Condition<T>() {
			@Override
			public boolean match(final T t) {
				for (Condition<T> c : conditions) {
					if (!c.match(t)) {
						return false;
					}
				}
				return true;
			}
		};
	}
	
	/**
	 * <pre> 转为Callable.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/03  huangys  Create
	 * </pre>
	 * 
	 * @param task 任务
	 * @return Callable
	 */
	@Nonnull
	public static Callable<Void> toCallable(@Nonnull final Runnable task) {
		Checks.nullThrow(task);
		return new Callable<Void>() {
			@Override
			public Void call() throws Exception {
				task.run();
				return null;
			}
		};
	}
	
	/**
	 * <pre> 转为Doable.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/03  huangys  Create
	 * </pre>
	 * 
	 * @param task 任务
	 * @return Doable
	 */
	@Nonnull
	public static Doable<Void> toDoable(@Nonnull final Runnable task) {
		Checks.nullThrow(task);
		return new Doable<Void>() {
			@Override
			public void run(final Void t) {
				task.run();
			}
		};
	}
	
	/**
	 * <pre> 转为DoablePipeline.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/03  huangys  Create
	 * </pre>
	 * 
	 * @param task 任务
	 * @return DoablePipeline
	 */
	@Nonnull
	public static DoablePipeline<Void, Void> toDoablePipeline(@Nonnull final Runnable task) {
		Checks.nullThrow(task);
		return new DoablePipeline<Void, Void>() {
			@Override
			public void process(final Void tool, final Void data) {
				task.run();
			}
		};
	}
	
	/**
	 * <pre> 转为Function.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/03  huangys  Create
	 * </pre>
	 * 
	 * @param task 任务
	 * @return Function
	 */
	@Nonnull
	public static Function<Void, Void> toFunction(@Nonnull final Runnable task) {
		Checks.nullThrow(task);
		return new Function<Void, Void>() {
			@Override
			public Void apply(final Void in) {
				task.run();
				return null;
			}
		};
	}
	
	/**
	 * <pre> 转为FunctionPipeline.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/03  huangys  Create
	 * </pre>
	 * 
	 * @param task 任务
	 * @return FunctionPipeline
	 */
	@Nonnull
	public static FunctionPipeline<Void, Void, Void> toFunctionPipeline(@Nonnull final Runnable task) {
		Checks.nullThrow(task);
		return new FunctionPipeline<Void, Void, Void>() {
			@Override
			public Void process(final Void tool, final Void data) {
				task.run();
				return null;
			}
		};
	}
	
	/**
	 * <pre> 转为Function.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/03  huangys  Create
	 * </pre>
	 * 
	 * @param <P> 产品类型
	 * @param task 任务
	 * @return Function
	 */
	@Nonnull
	public static <P> Function<Void, P> toFunction(@Nonnull final Callable<P> task) {
		Checks.nullThrow(task);
		return new Function<Void, P>() {
			@Override
			public P apply(final Void in) {
				try {
					return task.call();
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		};
	}
	
	/**
	 * <pre> 转为FunctionPipeline.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/03  huangys  Create
	 * </pre>
	 * 
	 * @param <P> 产品类型
	 * @param task 任务
	 * @return FunctionPipeline
	 */
	@Nonnull
	public static <P> FunctionPipeline<Void, Void, P> toFunctionPipeline(@Nonnull final Callable<P> task) {
		Checks.nullThrow(task);
		return new FunctionPipeline<Void, Void, P>() {
			@Override
			public P process(final Void tool, final Void data) {
				try {
					return task.call();
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		};
	}
	
	/**
	 * <pre> 转为DoablePipeline.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/03  huangys  Create
	 * </pre>
	 * 
	 * @param <D> 材料类型
	 * @param task 任务
	 * @return DoablePipeline
	 */
	@Nonnull
	public static <D> DoablePipeline<Void, D> toDoablePipeline(@Nonnull final Doable<D> task) {
		Checks.nullThrow(task);
		return new DoablePipeline<Void, D>() {
			@Override
			public void process(final Void tool, final D data) {
				task.run(data);
			}
		};
	}
	
	/**
	 * <pre> 转为Function.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/03  huangys  Create
	 * </pre>
	 * 
	 * @param <D> 材料类型
	 * @param task 任务
	 * @return Function
	 */
	@Nonnull
	public static <D> Function<D, Void> toFunction(@Nonnull final Doable<D> task) {
		Checks.nullThrow(task);
		return new Function<D, Void>() {
			@Override
			public Void apply(final D in) {
				task.run(in);
				return null;
			}
		};
	}
	
	/**
	 * <pre> 转为FunctionPipeline.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/03  huangys  Create
	 * </pre>
	 * 
	 * @param <D> 材料类型
	 * @param task 任务
	 * @return FunctionPipeline
	 */
	@Nonnull
	public static <D> FunctionPipeline<Void, D, Void> toFunctionPipeline(@Nonnull final Doable<D> task) {
		Checks.nullThrow(task);
		return new FunctionPipeline<Void, D, Void>() {
			@Override
			public Void process(final Void tool, final D data) {
				task.run(data);
				return null;
			}
		};
	}
	
	/**
	 * <pre> 转为Function.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/03  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 工具类型
	 * @param <D> 材料类型
	 * @param task 任务
	 * @return Function
	 */
	@Nonnull
	public static <T, D> Function<Pair<T, D>, Void> toFunction(@Nonnull final DoablePipeline<T, D> task) {
		Checks.nullThrow(task);
		return new Function<Pair<T, D>, Void>() {
			@Override
			public Void apply(final Pair<T, D> in) {
				task.process(in.getLeft(), in.getRight());
				return null;
			}
		};
	}
	
	/**
	 * <pre> 转为FunctionPipeline.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/03  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 工具类型
	 * @param <D> 材料类型
	 * @param task 任务
	 * @return FunctionPipeline
	 */
	@Nonnull
	public static <T, D> FunctionPipeline<T, D, Void> toFunctionPipeline(@Nonnull final DoablePipeline<T, D> task) {
		Checks.nullThrow(task);
		return new FunctionPipeline<T, D, Void>() {
			@Override
			public Void process(final T tool, final D data) {
				task.process(tool, data);
				return null;
			}
		};
	}
	
	/**
	 * <pre> 转为FunctionPipeline.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/03  huangys  Create
	 * </pre>
	 * 
	 * @param <D> 材料类型
	 * @param <P> 产品类型
	 * @param task 任务
	 * @return FunctionPipeline
	 */
	@Nonnull
	public static <D, P> FunctionPipeline<Void, D, P> toFunctionPipeline(@Nonnull final Function<D, P> task) {
		Checks.nullThrow(task);
		return new FunctionPipeline<Void, D, P>() {
			@Override
			public P process(final Void tool, final D data) {
				return task.apply(data);
			}
		};
	}

}
