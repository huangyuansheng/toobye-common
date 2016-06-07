/*
 * Copyright 2015 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2015/05/30.
 * 
 */
package com.toobye.common.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.toobye.common.base.Systems;
import com.toobye.common.lang.Checks;
import com.toobye.common.lang.Function;
import com.toobye.common.lang.Pair;
import com.toobye.common.string.StringArray;

/**
 * <pre> Map工具类.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2015/05/30  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class Maps {
	
	private Maps() { }
	
	/**
	 * <pre> 创建Map.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/30  huangys  Create
	 * </pre>
	 * 
	 * @param <K> key类型
	 * @param <V> Value类型
	 * @return Map
	 */
	@Nonnull
	public static <K, V> HashMap<K, V> newHashMap() {
	  return com.google.common.collect.Maps.newHashMap();
	}

	/**
	 * <pre> 创建Map.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/30  huangys  Create
	 * </pre>
	 * 
	 * @param <K> key类型
	 * @param <V> Value类型
	 * @param map 原Map
	 * @return Map
	 */
	@Nonnull
	public static <K, V> HashMap<K, V> newHashMap(@Nonnull final Map<? extends K, ? extends V> map) {
		Checks.nullThrow(map);
		return com.google.common.collect.Maps.newHashMap(map);
	}
	
	/**
	 * <pre> 创建Map.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/30  huangys  Create
	 * </pre>
	 * 
	 * @param <K> key类型
	 * @param <V> Value类型
	 * @param elements 元素
	 * @return Map
	 */
	@SuppressWarnings("unchecked")
	@Nonnull
	public static <K, V> HashMap<K, V> newHashMap(@Nonnull final Object... elements) {
		Checks.nullThrow(elements);
		Checks.matchThrow(elements.length % 2 != 0, "Size must be even.");
		HashMap<K, V> map = newHashMap();
		for (int i = 0; i < elements.length; i += 2) {
			Checks.containsThrow(map, elements[i]);
			map.put((K) elements[i], (V) elements[i + 1]);
		}
		return map;
	}
	
	/**
	 * <pre> 创建Map.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/30  huangys  Create
	 * </pre>
	 * 
	 * @param <K> key类型
	 * @param <V> Value类型
	 * @param keys key数组
	 * @param values value数组
	 * @return Map
	 */
	@Nonnull
	public static <K, V> HashMap<K, V> newHashMap(@Nullable final K[] keys, @Nullable final V[] values) {
		Checks.notSameSizeThrow(keys, values);
		
		HashMap<K, V> map = newHashMap();
		if (keys == null || values == null) {
			return map;
		}
		
		for (int i = 0; i < keys.length; i++) {
			Checks.containsThrow(map, keys[i]);
			map.put(keys[i], values[i]);
		}
		return map;
	}
	
	/**
	 * <pre> 创建Map.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/30  huangys  Create
	 * </pre>
	 * 
	 * @param <K> key类型
	 * @param <V> Value类型
	 * @param keys key数组
	 * @param values value数组
	 * @return Map
	 */
	@Nonnull
	public static <K, V> HashMap<K, V> newHashMap(@Nullable final Iterable<? extends K> keys, @Nullable final Iterable<? extends V> values) {
		if (Iterables.isEmpty(keys) && Iterables.isEmpty(keys)) {
			return newHashMap();
		}
		if (keys == null || values == null) {
			Checks.throwException("Size of keys and values must be same.");
		}
		return newHashMap(keys.iterator(), values.iterator());
	}
	
	/**
	 * <pre> 创建Map.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/30  huangys  Create
	 * </pre>
	 * 
	 * @param <K> key类型
	 * @param <V> Value类型
	 * @param keys key数组
	 * @param values value数组
	 * @return Map
	 */
	@Nonnull
	public static <K, V> HashMap<K, V> newHashMap(@Nullable final Iterator<? extends K> keys, @Nullable final Iterator<? extends V> values) {
		if (Iterators.isEmpty(keys) && Iterators.isEmpty(keys)) {
			return newHashMap();
		}
		if (keys == null || values == null) {
			Checks.throwException("Size of keys and values must be same.");
		}
		
		HashMap<K, V> map = newHashMap();
		while (keys.hasNext()) {
			K key = keys.next();
			Checks.matchThrow(!values.hasNext(), "Size of keys more than size of values.");
			V value = values.next();
			Checks.containsThrow(map, key);
			map.put(key, value);
		}
		Checks.matchThrow(values.hasNext(), "Size of keys less than size of values.");
		return map;
	}

	/**
	 * <pre> 创建Map.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/30  huangys  Create
	 * </pre>
	 * 
	 * @param <K> key类型
	 * @param <V> Value类型
	 * @param expectedSize Map规模
	 * @return Map
	 */
	@Nonnull
	public static <K, V> HashMap<K, V> newHashMapWithExpectedSize(@Nonnull final int expectedSize) {
		return com.google.common.collect.Maps.newHashMapWithExpectedSize(expectedSize);
	}
	
	/**
	 * <pre> 创建Map.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/30  huangys  Create
	 * </pre>
	 * 
	 * @param <T> key类型
	 * @param <V> value类型
	 * @param iterable key可迭代对象
	 * @return Map
	 */
	@Nonnull
	public static <T, V> HashMap<T, V> newHashMapNullValue(@Nullable final Iterable<T> iterable) {
		if (iterable == null) {
			return newHashMap();
		}
		return newHashMapNullValue(iterable.iterator());
	}
	
	/**
	 * <pre> 创建Map.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/30  huangys  Create
	 * </pre>
	 * 
	 * @param <T> key类型
	 * @param <V> value类型
	 * @param iterator key迭代器
	 * @return Map
	 */
	@Nonnull
	public static <T, V> HashMap<T, V> newHashMapNullValue(@Nullable final Iterator<T> iterator) {
		HashMap<T, V> map = new HashMap<>();
		if (iterator == null) {
			return map;
		}
		
		while (iterator.hasNext()) {
			map.put(iterator.next(), null);
		}
		return map;
	}
	
	/**
	 * <pre> Map格式化的分隔符. </pre>
	 */
	public static final String DELIMITER = ",";
	/**
	 * <pre> 获得keys字符串，以逗号作为连接符.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/09/05  huangys  Create
	 * </pre>
	 * 
	 * @param map map
	 * @return keys字符串
	 */
	@Nullable
	public static String getKeys(@Nullable final Map<?, ?> map) {
		return getKeys(map, null);
	}
	
	/**
	 * <pre> 获得keys字符串.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/09/05  huangys  Create
	 * </pre>
	 * 
	 * @param map map
	 * @param delimiter 分隔符
	 * @return keys字符串
	 */
	@Nullable
	public static String getKeys(@Nullable final Map<?, ?> map, @Nullable final String delimiter) {
		if (map == null) {
			return null;
		}
		return join(map.keySet(), delimiter);
	}
	
	/**
	 * <pre> 获得values字符串，以逗号作为连接符.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/09/05  huangys  Create
	 * </pre>
	 * 
	 * @param map map
	 * @return values字符串
	 */
	@Nullable
	public static String getValues(@Nullable final Map<?, ?> map) {
		return getValues(map, null);
	}
	
	/**
	 * <pre> 获得values字符串.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/09/05  huangys  Create
	 * </pre>
	 * 
	 * @param map map
	 * @param delimiter 分隔符
	 * @return values字符串
	 */
	public static String getValues(@Nullable final Map<?, ?> map, @Nullable final String delimiter) {
		if (map == null) {
			return null;
		}
		return join(map.values(), delimiter);
	}
	
	private static String join(@Nonnull final Collection<?> c, @Nullable final String delimiter) {
		return StringArray.join(c, delimiter == null ? DELIMITER : delimiter);
	}
	
	private static final String DELIMITER_HORIZONTAL = " | ";
	/**
	 * <pre> 生成Map的横向描述字符串.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/09/05  huangys  Create
	 * </pre>
	 * 
	 * @param map map
	 * @return Map的横向描述字符串
	 */
	@Nullable
	public static String getFormatHorizontal(@Nullable final Map<?, ?> map) {
		return getFormatHorizontal(map, null);
	}
	
	/**
	 * <pre> 生成Map的横向描述字符串.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/09/05  huangys  Create
	 * </pre>
	 * 
	 * @param map map
	 * @param delimiter 分隔符
	 * @return Map的横向描述字符串
	 */
	@Nullable
	public static String getFormatHorizontal(@Nullable final Map<?, ?> map, @Nullable final String delimiter) {
		if (map == null) {
			return null;
		}
		List<Integer> lenths = new ArrayList<Integer>();
		for (Entry<?, ?> entry : map.entrySet()) {
			Object key = entry.getKey();
			Object value = entry.getValue();
			lenths.add(Math.max(key == null ? 4 : key.toString().length(), value == null ? 4 : value.toString().length()));
		}
		String d = delimiter == null ? DELIMITER_HORIZONTAL : delimiter;
		String format = "%-" + StringArray.join(lenths, "s" + d + "%-") + "s";
		return String.format(format, map.keySet().toArray())
				+ Systems.LINE_SEPARATOR
				+ String.format(format, map.values().toArray());
	}

	private static final String DELIMITER_VERTICAL = " : ";
	/**
	 * <pre> 生成Map的纵向描述字符串.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/09/05  huangys  Create
	 * </pre>
	 * 
	 * @param map map
	 * @return Map的纵向描述字符串
	 */
	@Nullable
	public static String getFormatVertical(@Nullable final Map<?, ?> map) {
		return getFormatVertical(map, null);
	}
	
	/**
	 * <pre> 生成Map的纵向描述字符串.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/09/05  huangys  Create
	 * </pre>
	 * 
	 * @param map map
	 * @param delimiter 分隔符
	 * @return Map的纵向描述字符串
	 */
	@Nullable
	public static String getFormatVertical(@Nullable final Map<?, ?> map, @Nullable final String delimiter) {
		if (map == null) {
			return null;
		}
		int maxLen = 0;
		for (Entry<?, ?> entry : map.entrySet()) {
			Object key = entry.getKey();
			maxLen = Math.max(maxLen, key == null ? 4 : key.toString().length());
		}
		String d = delimiter == null ? DELIMITER_VERTICAL : delimiter;
		String format = "%-" + maxLen + "s" + d + "%s" + Systems.LINE_SEPARATOR;
		StringBuffer sb = new StringBuffer();
		for (Entry<?, ?> entry : map.entrySet()) {
			sb.append(String.format(format, entry.getKey(), entry.getValue()));
		}
		return sb.toString();
	}
	
	/**
	 * <pre> 将map中的Key值都转为大写.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/28  huangys  Create
	 * </pre>
	 * 
	 * @param map 带处理Map
	 * @param <T> Map的Value类型
	 * @return 处理后Map
	 */
	@Nullable
	public static <T> Map<String, T> toUpperCase(@Nullable final Map<String, T> map) {
		if (map == null) {
			return null;
		}
		Map<String, T> ret = new HashMap<>();
		for (Entry<String, T> entry : map.entrySet()) {
			String key = entry.getKey();
			if (key == null) {
				ret.put(null, entry.getValue());
			} else {
				ret.put(key.toUpperCase(), entry.getValue());
			}
		}
		return ret;
	}
	
	/**
	 * <pre> 将map中的Key值都转为小写.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/28  huangys  Create
	 * </pre>
	 * 
	 * @param map 带处理Map
	 * @param <T> Map的Value类型
	 * @return 处理后Map
	 */
	@Nullable
	public static <T> Map<String, T> toLowerCase(@Nullable final Map<String, T> map) {
		if (map == null) {
			return null;
		}
		Map<String, T> ret = new HashMap<>();
		for (Entry<String, T> entry : map.entrySet()) {
			String key = entry.getKey();
			if (key == null) {
				ret.put(null, entry.getValue());
			} else {
				ret.put(key.toLowerCase(), entry.getValue());
			}
		}
		return ret;
	}
	
	/**
	 * <pre> 合并Map.
	 * 相同key最终呈现的value来自于后面的Map。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/28  huangys  Create
	 * </pre>
	 * 
	 * @param <K> Map的key类型
	 * @param <V> Map的Value类型
	 * @param mapInstance map实例
	 * @param maps map集合
	 * @return 合并后的Map
	 */
	@SafeVarargs
	@Nullable
	public static <K, V> Map<K, V> merge(@Nonnull final Map<K, V> mapInstance, @Nullable final Map<K, V>... maps) {
		if (maps == null) {
			return null;
		}
		for (Map<K, V> map : maps) {
			if (map != null) {
				mapInstance.putAll(map);
			}
		}
		return mapInstance.isEmpty() ? null : mapInstance;
	}
	
	/**
	 * <pre> 合并Map（非空值可覆盖）.
	 * 相同key（value非空）最终呈现的value来自于后面的Map。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/28  huangys  Create
	 * </pre>
	 * 
	 * @param <K> Map的key类型
	 * @param <V> Map的Value类型
	 * @param mapInstance map实例
	 * @param maps map集合
	 * @return 合并后的Map
	 */
	@SafeVarargs
	@Nullable
	public static <K, V> Map<K, V> mergeOverwriteNonnull(@Nonnull final Map<K, V> mapInstance, @Nullable final Map<K, V>... maps) {
		if (maps == null) {
			return null;
		}
		for (Map<K, V> map : maps) {
			if (map != null) {
				for (Entry<K, V> entry : map.entrySet()) {
					if (entry.getValue() != null || !mapInstance.containsKey(entry.getKey())) {
						mapInstance.put(entry.getKey(), entry.getValue());
					}
				}
			}
		}
		return mapInstance.isEmpty() ? null : mapInstance;
	}
	
	/**
	 * <pre> 合并Map.
	 * 相同key最终呈现的value来自于后面的Map。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/28  huangys  Create
	 * </pre>
	 * 
	 * @param <K> Map的key类型
	 * @param <V> Map的Value类型
	 * @param maps map集合
	 * @return 合并后的Map
	 */
	@SafeVarargs
	@Nullable
	public static <K, V> Map<K, V> mergeReturnHashMap(@Nullable final Map<K, V>... maps) {
		return merge(new HashMap<K, V>(), maps);
	}
	
	/**
	 * <pre> 合并Map（非空值可覆盖）.
	 * 相同key（value非空）最终呈现的value来自于后面的Map。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/28  huangys  Create
	 * </pre>
	 * 
	 * @param <K> Map的key类型
	 * @param <V> Map的Value类型
	 * @param maps map集合
	 * @return 合并后的Map
	 */
	@SafeVarargs
	@Nullable
	public static <K, V> Map<K, V> mergeOverwriteNonnullReturnHashMap(@Nullable final Map<K, V>... maps) {
		return mergeOverwriteNonnull(new HashMap<K, V>(), maps);
	}
	
	
	/**
	 * <pre> 转为两数组.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/05/03  huangys  Create
	 * </pre>
	 * 
	 * @param <K> Map的key类型
	 * @param <V> Map的Value类型
	 * @param map 待处理Map
	 * @return key-value的两个数组
	 */
	public static <K, V> Pair<K[], V[]> toArrays(@Nonnull final Map<K, V> map) {
		return Pair.of(Iterables.toArray(map.keySet()), Iterables.toArray(map.values()));
	}
	
	/**
	 * <pre> 转为两数组.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/05/03  huangys  Create
	 * </pre>
	 * 
	 * @param <K> Map的key类型
	 * @param <V> Map的Value类型
	 * @param <T> 返回的列表元素类型
	 * @param map 待处理Map
	 * @param function 处理函数
	 * @return key-value的两个数组
	 */
	@Nullable
	public static <K, V, T> List<T> toList(@Nullable final Map<K, V> map, @Nonnull final Function<Entry<K, V>, T> function) {
		if (map == null) {
			return null;
		}
		Checks.nullThrow(function);
		List<T> list = new ArrayList<>();
		for (Entry<K, V> entry : map.entrySet()) {
			list.add(function.apply(entry));
		}
		return list;
	}
	
}
