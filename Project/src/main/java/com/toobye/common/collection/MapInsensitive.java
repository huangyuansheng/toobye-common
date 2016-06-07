/*
 * Copyright 2015 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2015/09/04.
 * 
 */
package com.toobye.common.collection;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.toobye.common.lang.Checks;
import com.toobye.common.lang.Function;

/**
 * <pre> 大小写不敏感的Map(Key都以大写保存).
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2015/09/04  huangys  v1.0      Create
 * </pre>
 * 
 * @param <V> value类型
 */
public class MapInsensitive<V> extends HashMap<String, V> {
	
	/**
	 * <pre> 构造一个大小写不敏感Map<Key, Key>.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/09/04  huangys  Create
	 * </pre>
	 * 
	 * @param <V> value类型
	 * @param map 原Map
	 * @return 大小写不敏感Map
	 */
	public static <V> MapInsensitive<V> ofKey(final Map<? extends V, ?> map) {
		return ofKey(map.keySet(), null);
	}
	
	/**
	 * <pre> 构造一个大小写不敏感Map<Key, Key>.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/09/04  huangys  Create
	 * </pre>
	 * 
	 * @param <V> value类型
	 * @param map 原Map
	 * @param func key生成函数
	 * @return 大小写不敏感Map
	 */
	public static <V> MapInsensitive<V> ofKey(final Map<? extends V, ?> map, final Function<V, String> func) {
		return ofKey(map.keySet(), func);
	}
	
	/**
	 * <pre> 构造一个大小写不敏感Map<Key, Key>.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/09/04  huangys  Create
	 * </pre>
	 * 
	 * @param <V> value类型
	 * @param set set
	 * @return 大小写不敏感Map
	 */
	public static <V> MapInsensitive<V> ofKey(final Collection<? extends V> set) {
		return ofKey(set, null);
	}
	
	/**
	 * <pre> 构造一个大小写不敏感Map<Key, Key>.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/09/04  huangys  Create
	 * </pre>
	 * 
	 * @param <V> value类型
	 * @param set set
	 * @param func key生成函数
	 * @return 大小写不敏感Map
	 */
	public static <V> MapInsensitive<V> ofKey(final Collection<? extends V> set, final Function<V, String> func) {
		Set<String> done = new HashSet<>();
		MapInsensitive<V> ret = new MapInsensitive<>(set.size());
		for (V one : set) {
			String key = one == null ? null : func == null ? one.toString().toUpperCase() : func.apply(one).toUpperCase();
			Checks.containsThrow(done, key);
			ret.put(key, one);
			done.add(key);
		}
		return ret;
	}
	
	/**
	 * <pre> 构造一个大小写不敏感Map.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/09/04  huangys  Create
	 * </pre>
	 * 
	 * @param <V> 元素类型
	 * @param map 原Map
	 * @return 大小写不敏感Map
	 */
	public static <V> MapInsensitive<V> of(final Map<String, ? extends V> map) {
		return new MapInsensitive<V>(map);
	}

	private static final long serialVersionUID = 1L;
	
    /**
     * Constructs an empty <tt>HashMap</tt> with the specified initial
     * capacity and load factor.
     *
     * @param  initialCapacity the initial capacity
     * @param  loadFactor      the load factor
     */
    public MapInsensitive(final int initialCapacity, final float loadFactor) {
    	super(initialCapacity, loadFactor);
    }
	
    /**
     * Constructs an empty <tt>HashMap</tt> with the specified initial
     * capacity and the default load factor (0.75).
     *
     * @param  initialCapacity the initial capacity.
     */
    public MapInsensitive(final int initialCapacity) {
    	super(initialCapacity);
    }

    /**
     * Constructs an empty <tt>HashMap</tt> with the default initial capacity
     * (16) and the default load factor (0.75).
     */
    public MapInsensitive() {
    	super();
    }

    /**
     * Constructs a new <tt>HashMap</tt> with the same mappings as the
     * specified <tt>Map</tt>.  The <tt>HashMap</tt> is created with
     * default load factor (0.75) and an initial capacity sufficient to
     * hold the mappings in the specified <tt>Map</tt>.
     *
     * @param map the map whose mappings are to be placed in this map
     */
    public MapInsensitive(final Map<String, ? extends V> map) {
        this(map.size());
        putAll(map);
    }

	private static String getKey(final Object key) {
		return key == null ? null : key.toString().toUpperCase();
	}
	
	@Override
	public boolean containsKey(final Object key) {
		return super.containsKey(getKey(key));
	}
	
	@Override
	public V get(final Object key) {
		return super.get(getKey(key));
	}
	
	@Override
	public V put(final String key, final V value) {
		return super.put(getKey(key), value);
	}
	
	@Override
	public void putAll(final Map<? extends String, ? extends V> m) {
		Set<String> done = new HashSet<>();
		for (java.util.Map.Entry<? extends String, ? extends V> entry : m.entrySet()) {
			String key = getKey(entry.getKey());
			Checks.containsThrow(done, key);
			super.put(key, entry.getValue());
			done.add(key);
		}
	}
	
	@Override
	public V remove(final Object key) {
		return super.remove(getKey(key));
	}
	
}
