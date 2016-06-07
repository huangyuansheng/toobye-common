/*
 * Copyright 2016 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2016/05/12.
 * 
 */
package com.toobye.common.collection;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.sun.istack.internal.Nullable;
import com.toobye.common.lang.Function;
import com.toobye.common.string.StringArray;

/**
 * <pre> MapCollection.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2016/05/12  huangys  v1.0      Create
 * </pre>
 * 
 * @param <E> 元素类型
 * @param <K> Map的Key类型
 * @param <V> Map的Value类型
 */
public abstract class MapCollection<E, K, V extends Collection<E>> {
	
	private Map<K, V> map = new HashMap<>();

	/**
	 * <pre> 向指定类型添加元素.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/05/12  huangys  Create
	 * </pre>
	 * 
	 * @param key 分类
	 * @param one 元素
	 */
	public void add(@Nullable final K key, @Nullable final E one) {
		if (map.containsKey(key)) {
			map.get(key).add(one);
		} else {
			V v = newCollection();
			v.add(one);
			map.put(key, v);
		}
	}
	
	/**
	 * <pre> 从指定类型中移除元素.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/05/12  huangys  Create
	 * </pre>
	 * 
	 * @param key 分类
	 * @param one 元素
	 */
	public void remove(@Nullable final K key, @Nullable final E one) {
		if (map.containsKey(key)) {
			map.get(key).remove(one);
		}
	}
	
	/**
	 * <pre> 向指定类型添加元素.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/05/12  huangys  Create
	 * </pre>
	 * 
	 * @param key 分类
	 * @param collection 元素集合
	 */
	public void addAll(@Nullable final K key, @Nullable final V collection) {
		if (map.containsKey(key)) {
			map.get(key).addAll(collection);
		} else {
			map.put(key, collection);
		}
	}
	
	/**
	 * <pre> 从指定类型中移除元素.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/05/12  huangys  Create
	 * </pre>
	 * 
	 * @param key 分类
	 * @param collection 元素集合
	 */
	public void removeAll(@Nullable final K key, @Nullable final V collection) {
		if (map.containsKey(key)) {
			map.get(key).removeAll(collection);
		}
	}
	
	/**
	 * <pre> 获取指定类型的元素集合.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/05/12  huangys  Create
	 * </pre>
	 * 
	 * @param key 类型
	 * @return 元素集合
	 */
	public V get(@Nullable final K key) {
		return map.get(key);
	}
	
	/**
	 * <pre> 获取全部.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/05/12  huangys  Create
	 * </pre>
	 * 
	 * @return 全部
	 */
	public Map<K, V> getAll() {
		return map;
	}
	
	/**
	 * <pre> 创建集合.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/05/13  huangys  Create
	 * </pre>
	 * 
	 * @return 集合
	 */
	public abstract V newCollection();
	
	@Override
	public String toString() {
		return StringArray.join(
					Maps.toList(map, new Function<Entry<K, V>, String>() {
						@Override
						public String apply(final Entry<K, V> in) {
							return in.getKey().toString() + ": " + in.getValue().toString();
						}
					}), " : ");
	}
	
}
