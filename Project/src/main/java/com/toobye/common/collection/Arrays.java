/*
 * Copyright 2014 () , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2014/01/07.
 * 
 */
package com.toobye.common.collection;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.toobye.common.lang.Checks;
import com.toobye.common.lang.Condition;
import com.toobye.common.lang.Function;
import com.toobye.common.lang.Objects;
import com.toobye.common.lang.Processors;

/**
 * <pre> 数组工具类.
 * 
 * org.apache.commons.lang3.ArrayUtils说明：
 * 添加元素：add/addAll
 * 拷贝数组：clone
 * 查找元素：contains/indexOf/lastIndexOf
 * 空操作：isEmpty/isNotEmpty/nullToEmpty
 * 比较：isEquals（元素值比较）/isSameType（元素类型）/isSameLength（数组长度）
 * 移除元素：remove/removeAll/removeElement
 * 反转：reverse
 * 子串：subarray
 * 快速构造Map：toMap
 * 基本数据类型与包装类互转：toObject/toPrimitive（包装类可为null，故方法中提供了Null的默认值设置）
 * 快速展示：toString（包装类可为null，故方法中提供了Null的默认值设置）
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2014/01/07  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class Arrays extends org.apache.commons.lang3.ArrayUtils {

	@edu.umd.cs.findbugs.annotations.SuppressFBWarnings("NM_SAME_SIMPLE_NAME_AS_SUPERCLASS")
	private Arrays() { }
	
	/**
	 * <pre> 是否相同大小.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/14  huangys  Create
	 * </pre>
	 * 
	 * @param arr1 数组1
	 * @param arr2 数组2
	 * @return 是否相同大小
	 */
	@Nonnull
	public static boolean isSameSize(@Nullable final Object[] arr1, @Nullable final Object[] arr2) {
		int size1 = isEmpty(arr1) ? 0 : arr1.length;
		int size2 = isEmpty(arr1) ? 0 : arr2.length;
		return size1 == size2;
	}
	
	/**
	 * <pre> 数组内指定元素的总个数.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/06/12  huangys  Create
	 * </pre>
	 * 
	 * @param arr 数组
	 * @param assigned 指定元素
	 * @return 总个数
	 */
	@Nonnull
	public static int countEqual(@Nonnull final boolean[] arr, @Nonnull final boolean assigned) {
		Checks.nullThrow(arr);
		int count = 0;
		for (boolean b : arr) {
			if (b == assigned) {
				count++;
			}
		}
		return count;
	}
	
	/**
	 * <pre> 数组内指定元素的总个数.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/06/12  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 元素类型
	 * @param arr 数组
	 * @param assigned 指定元素
	 * @return 总个数
	 */
	@Nonnull
	public static <T> int countEqual(@Nonnull final T[] arr, @Nullable final T assigned) {
		Checks.nullThrow(arr);
		int count = 0;
		for (T t : arr) {
			if (Objects.equals(t, assigned)) {
				count++;
			}
		}
		return count;
	}
	
	/**
	 * <pre> 以Null补足数组大小.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/07/12  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 元素类型
	 * @param array 原数组
	 * @param needSize 需要补足的大小
	 * @return 满足大小的数组
	 */
	@Nonnull
	public static <T> T[] addNull(@Nonnull final T[] array, @Nonnull final int needSize) {
		Checks.nullThrow(array);
		if (array.length >= needSize) {
			return array;
		}
		// 以Null填充
		return addAll(array, newInstance(array, needSize - array.length));
	}
	
	/**
	 * <pre> 创建新数组.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/19  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 元素类型
	 * @param componentType 元素类型
	 * @return 新数组
	 */
	@Nullable
	public static <T> T[] newInstanceEmpty(@Nullable final Class<T> componentType) {
		return newInstance(componentType, 0);
	}
	
	/**
	 * <pre> 创建新数组.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/19  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 元素类型
	 * @param componentType 元素类型
	 * @param size 数组大小
	 * @return 新数组
	 */
	@SuppressWarnings("unchecked")
	@Nullable
	public static <T> T[] newInstance(@Nullable final Class<T> componentType, @Nonnull final int size) {
		return (T[]) Array.newInstance(componentType, size);
	}
	
	/**
	 * <pre> 创建新数组.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/19  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 元素类型
	 * @param array 参考数组
	 * @return 新数组
	 */
	@Nullable
	public static <T> T[] newInstance(@Nullable final T[] array) {
		if (array == null) {
			return null;
		}
		return newInstance(array, array.length);
	}
	
	/**
	 * <pre> 创建新数组.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/19  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 元素类型
	 * @param array 参考数组
	 * @param size 数组大小
	 * @return 新数组
	 */
	@SuppressWarnings("unchecked")
	@Nullable
	public static <T> T[] newInstance(@Nullable final T[] array, @Nonnull final int size) {
		if (array == null) {
			return null;
		}
		return (T[]) newInstance(array.getClass().getComponentType(), size);
	}
	
	/**
	 * <pre> 数组加工.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/08  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 元素类型
	 * @param <E> 元素类型
	 * @param array 数组
	 * @param funcs 函数集
	 */
	@SafeVarargs
	public static <T extends E, E> void process(@Nullable final T[] array, @Nullable final Function<E, T>... funcs) {
		if (isEmpty(array) || isEmpty(funcs)) {
			return;
		}
		
		for (int i = 0; i < array.length; i++) {
			for (Function<E, T> func : funcs) {
				array[i] = func.apply(array[i]);
			}
		}
	}
	
	/**
	 * <pre> 数组加工.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/08  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 元素类型
	 * @param <E> 元素类型
	 * @param array 数组
	 * @param funcs 函数集
	 */
	public static <T extends E, E> void process(@Nullable final T[] array, @Nullable final Iterable<Function<E, T>> funcs) {
		if (isEmpty(array) || Iterables.isEmpty(funcs)) {
			return;
		}
		
		for (int i = 0; i < array.length; i++) {
			for (Function<E, T> func : funcs) {
				array[i] = func.apply(array[i]);
			}
		}
	}
	
	/**
	 * <pre> 满足条件的移除.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/21  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 元素类型
	 * @param <E> 元素类型
	 * @param array 数组
	 * @param conditions 条件
	 * @return 处理后数组
	 */
	@SafeVarargs
	@Nullable
	public static <T extends E, E> T[] delete(@Nullable final T[] array, @Nullable final Condition<E>... conditions) {
		if (array == null) {
			return null;
		}
		
		if (array.length == 0 || isEmpty(conditions)) {
			return clone(array);
		}
		return reserve(array, Processors.not(Processors.or(conditions)));
	}
	
	/**
	 * <pre> 满足条件的移除.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/21  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 元素类型
	 * @param <E> 元素类型
	 * @param array 数组
	 * @param conditions 条件
	 * @return 处理后数组
	 */
	@Nullable
	public static <T extends E, E> T[] delete(@Nullable final T[] array, @Nullable final Iterable<Condition<E>> conditions) {
		if (array == null) {
			return null;
		}
		
		if (array.length == 0 || Iterables.isEmpty(conditions)) {
			return clone(array);
		}
		return reserve(array, Processors.not(Processors.or(conditions)));
	}
	
	/**
	 * <pre> 满足条件的保留.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/21  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 元素类型
	 * @param <E> 元素类型
	 * @param array 数组
	 * @param conditions 条件
	 * @return 处理后数组
	 */
	@SafeVarargs
	@Nullable
	public static <T extends E, E> T[] reserve(@Nullable final T[] array, @Nullable final Condition<E>... conditions) {
		if (array == null) {
			return null;
		}
		
		if (array.length == 0 || isEmpty(conditions)) {
			return newInstance(array, 0);
		}
		
		List<T> ret = new ArrayList<>();
		Condition<E> orCondition = Processors.or(conditions);
		for (T t : array) {
			if (orCondition.match(t)) {
				ret.add(t);
			}
		}
		return Iterables.toArray(ret);
	}
	
	/**
	 * <pre> 满足条件的保留.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/21  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 元素类型
	 * @param <E> 元素类型
	 * @param array 数组
	 * @param conditions 条件
	 * @return 处理后数组
	 */
	@Nullable
	public static <T extends E, E> T[] reserve(@Nullable final T[] array, @Nullable final Iterable<Condition<E>> conditions) {
		if (array == null) {
			return null;
		}
		
		if (array.length == 0 || Iterables.isEmpty(conditions)) {
			return newInstance(array, 0);
		}
		
		List<T> ret = new ArrayList<>();
		Condition<E> orCondition = Processors.or(conditions);
		for (T t : array) {
			if (orCondition.match(t)) {
				ret.add(t);
			}
		}
		return Iterables.toArray(ret);
	}
	
    /**
     * <pre> 连接字符数组.
     * 
     * Modification History:
     * Date        Author   Action
     * 2015/12/18  huangys  Create
     * </pre>
     * 
     * @param moreBytes 多个字符数组
     * @return 字符数组
     */
    public static byte[] concat(@Nullable final Iterable<byte[]> moreBytes) {
    	if (moreBytes == null) {
			return null;
		}
    	
    	int size = 0;
    	for (byte[] bs : moreBytes) {
			if (bs != null) {
				size += bs.length;
			}
		}
        byte[] ret = new byte[size];
        int pos = 0;
    	for (byte[] bs : moreBytes) {
			if (bs != null) {
				System.arraycopy(bs, 0, ret, pos, bs.length);
				pos += bs.length;
			}
		}
        return ret;  
    }
    
	/**
	 * <pre> 创建新数组.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/19  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 元素类型
	 * @param array 数组
	 * @return 新数组
	 */
	@Nullable
	public static <T> List<T> toList(@Nullable final T[] array) {
		if (array == null) {
			return null;
		}
		List<T> ret = new ArrayList<>();
		for (T t : array) {
			ret.add(t);
		}
		return ret;
	}
	
}
