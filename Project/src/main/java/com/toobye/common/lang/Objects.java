/*
 * Copyright 2015 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2015/12/07.
 * 
 */
package com.toobye.common.lang;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.toobye.common.collection.Iterables;
import com.toobye.common.collection.Iterators;
import com.toobye.common.string.StringUtils;
import com.toobye.common.time.DateFormat;

/**
 * <pre> 对象工具类.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2015/12/07  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class Objects {
	
	private Objects() { }

	/**
	 * <pre> null检测. </pre>
	 */
	public static final Condition<Object> IS_NULL = new Condition<Object>() {
		@Override
		public boolean match(final Object t) {
			return t == null;
		}
	};
	
	/**
	 * <pre> 空检测. </pre>
	 */
	public static final Condition<Object> IS_EMPTY = new Condition<Object>() {
		@Override
		public boolean match(final Object t) {
			return isEmpty(t);
		}
	};
	
	/**
	 * <pre> 比较.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/09  huangys  Create
	 * </pre>
	 * 
	 * @param obj1 对象1
	 * @param obj2 对象2
	 * @return 是否相等
	 */
	public static boolean equals(@Nullable final Object obj1, @Nullable final Object obj2) {
		return (obj1 != null && obj1.equals(obj2)) || (obj1 == null && obj2 == null);
	}
	
	/**
	 * <pre> 判断对象是否为空.
	 * 
	 * 返回True的情况有如下：
	 *   null
	 *   基础类型，且为0(char/byte/short/int/long/float/double == 0)
	 *   空字符串，空集合，空Map(String/Collection/Map => isEmpty)
	 *   空数组(Array => length == 0)
	 *   
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/07  huangys  Create
	 * </pre>
	 * 
	 * @param obj 对象
	 * @return 是否为空
	 */
	@Nonnull
	public static boolean isEmpty(@Nullable final Object obj) {
		if (obj == null
				|| (obj instanceof String && ((String) obj).isEmpty())
				|| (obj instanceof Character && obj.equals((char) 0))
				|| (obj instanceof Byte && obj.equals((byte) 0))
				|| (obj instanceof Short && obj.equals((short) 0))
				|| (obj instanceof Integer && obj.equals((int) 0))
				|| (obj instanceof Long && obj.equals((long) 0))
				|| (obj instanceof Float && obj.equals((float) 0))
				|| (obj instanceof Double && obj.equals((double) 0))
				|| (obj.getClass().isArray() && Array.getLength(obj) == 0)
				|| (obj instanceof Collection && ((Collection<?>) obj).isEmpty())
				|| (obj instanceof Map && ((Map<?, ?>) obj).isEmpty())
				|| (obj instanceof Iterable<?> && (Iterables.isEmpty((Iterable<?>) obj)))
				|| (obj instanceof Iterator<?> && (Iterators.isEmpty((Iterator<?>) obj)))
				) {
			return true;
		}
		return false;
	}
	
	/**
	 * <pre> 取最前非Null元素.
	 * 可变参数：不仅参数个数可变，类型亦可变
	 * 若需要类型不可变，可用数组、列表代替，但使用麻烦
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/09  huangys  Create
	 * </pre>
	 * 
	 * @param elements 多个元素
	 * @param <T> 元素类型
	 * @return 最前非Null元素
	 */
	@SafeVarargs
	@Nullable
	public static <T> T nvl(@Nullable final T... elements) {
		if (elements == null) {
			return null;
		}
		for (T e : elements) {
			if (e != null) {
				return e;
			}
		}
		return null;
	}
	
	/**
	 * <pre> 取最前非Null元素.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/19  huangys  Create
	 * </pre>
	 * 
	 * @param elements 元素集合
	 * @param <T> 元素类型
	 * @return 最前非Null元素
	 */
	@Nullable
	public static <T> T nvl(@Nullable final Iterable<T> elements) {
		if (elements == null) {
			return null;
		}
		for (T e : elements) {
			if (e != null) {
				return e;
			}
		}
		return null;
	}
	
	/**
	 * <pre> 对象匹配时返回指定对象.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/08/13  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 对象类型
	 * @param obj 对象
	 * @param matchObj 匹配对象
	 * @param toObj 匹配时返回的对象
	 * @return 对象
	 */
	@Nullable
	public static <T> T to(@Nullable final T obj, @Nullable final T matchObj, @Nullable final T toObj) {
		return equals(obj, matchObj) ? toObj : obj;
	}
	
	/**
	 * <pre> 对象匹配时返回null.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/08/13  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 对象类型
	 * @param obj 对象
	 * @param matchObj 匹配对象
	 * @return 对象
	 */
	@Nullable
	public static <T> T toNull(@Nullable final T obj, @Nullable final T matchObj) {
		return to(obj, matchObj, null);
	}
	
	/**
	 * <pre> 若为Null，则返回指定对象，否则返回本身.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 对象类型
	 * @param obj 对象
	 * @param toObj 指定对象
	 * @return 对象
	 */
	@Nullable
	public static <T> T nullTo(@Nullable final T obj, @Nullable final T toObj) {
		return obj == null ? toObj : obj;
	}
	
	/**
	 * <pre> 若为空字符串 ，则返回指定对象，否则返回本身.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 对象类型
	 * @param obj 对象
	 * @param toObj 指定对象
	 * @return 对象
	 */
	@Nullable
	public static <T> T emptyTo(@Nullable final T obj, @Nullable final T toObj) {
		return isEmpty(obj) ? toObj : obj;
	}
	
	/**
	 * <pre> 若为空 ，则返回null，否则返回本身.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 对象类型
	 * @param obj 对象
	 * @return 对象
	 */
	@Nullable
	public static <T> T emptyToNull(@Nullable final T obj) {
		return isEmpty(obj) ? null : obj;
	}
	
	/**
	 * <pre> 字符串转基础对象.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/18  huangys  Create
	 * </pre>
	 * 
	 * @param cls 对象类型
	 * @param str 字符串
	 * @return 对象
	 */
	@Nullable
	public static Object toObject(@Nonnull final Class<?> cls, @Nullable final String str) {
		Checks.nullThrow(cls);
		if (str == null) {
			return null;
		}
		if (cls == Character.class || cls == char.class) {
			Checks.notMatchThrow(str.length() == 1);
			return str.charAt(0);
		} else if (cls == Byte.class || cls == byte.class) {
			return Byte.parseByte(clearNumber(str));
		} else if (cls == Short.class || cls == short.class) {
			return Short.parseShort(clearNumber(str));
		} else if (cls == Integer.class || cls == int.class) {
			return Integer.parseInt(clearNumber(str));
		} else if (cls == Long.class || cls == long.class) {
			return Long.parseLong(clearNumber(str));
		} else if (cls == Float.class || cls == float.class) {
			return Float.parseFloat(clearNumber(str));
		} else if (cls == Double.class || cls == double.class) {
			return Double.parseDouble(clearNumber(str));
		} else if (cls == Date.class) {
			return DateFormat.parsePreciseness(clearNumber(str));
		}
		return str;
	}
	
	private static String clearNumber(@Nonnull final String str) {
		return StringUtils.replace(StringUtils.clear(StringUtils.clear(str, '%'), ','), "+-", "-");
	}
	
	/**
	 * <pre> 字符串转基础对象.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/18  huangys  Create
	 * </pre>
	 * 
	 * @param cls 对象类型
	 * @param obj 字符串
	 * @return 对象
	 */
	@Nullable
	public static Object toObject(@Nonnull final Class<?> cls, @Nullable final Object obj) {
		Checks.nullThrow(cls);
		if (obj == null) {
			return null;
		}
		if (obj instanceof String) {
			return toObject(cls, (String) obj);
		}
		return cls.cast(obj);
	}
	
	/**
	 * <pre> 创建实例.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/19  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 类型
	 * @param cls 类
	 * @return 实例
	 */
	@Nonnull
	public static <T> T newInstance(@Nonnull final Class<T> cls) {
		Checks.nullThrow(cls);
		try {
			return (T) cls.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 创建实例.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/19  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 类型
	 * @param obj 对象
	 * @return 实例
	 */
	@SuppressWarnings("unchecked")
	@Nonnull
	public static <T> T newInstance(@Nonnull final T obj) {
		Checks.nullThrow(obj);
		return (T) newInstance(obj.getClass());
	}
	
}
