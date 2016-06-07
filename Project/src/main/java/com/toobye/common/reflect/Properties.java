/*
 * Copyright 2016 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2016/01/05.
 * 
 */
package com.toobye.common.reflect;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.beanutils.PropertyUtils;

import com.toobye.common.collection.MapInsensitive;
import com.toobye.common.collection.Maps;
import com.toobye.common.lang.Checks;
import com.toobye.common.lang.Objects;
import com.toobye.common.string.StringArray;

/**
 * <pre> 属性工具类.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2016/01/05  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class Properties {
	
	private Properties() { }
	
	/**
	 * <pre> 获得属性名称集合(get/set).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/05  huangys  Create
	 * </pre>
	 * 
	 * @param obj 对象
	 * @return 属性名称集合
	 */
	@Nonnull
	public static Set<String> getNames(@Nonnull final Object obj) {
		Checks.nullThrow(obj);
		return getNames(obj.getClass());
	}
	
	/**
	 * <pre> 获得属性名称集合(get/set).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/05  huangys  Create
	 * </pre>
	 * 
	 * @param cls 类
	 * @return 属性名称集合
	 */
	@Nonnull
	public static Set<String> getNames(@Nonnull final Class<?> cls) {
		Checks.nullThrow(cls);
		return ClassProperties.parse(cls).get().keySet();
	}
	
	/**
	 * <pre> 获得属性名称集合(public).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/05  huangys  Create
	 * </pre>
	 * 
	 * @param obj 对象
	 * @return 属性名称集合
	 */
	@Nonnull
	public static Set<String> getPublicNames(@Nonnull final Object obj) {
		Checks.nullThrow(obj);
		return getPublicNames(obj.getClass());
	}
	
	/**
	 * <pre> 获得属性名称集合(public).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/05  huangys  Create
	 * </pre>
	 * 
	 * @param cls 类
	 * @return 属性名称集合
	 */
	@Nonnull
	public static Set<String> getPublicNames(@Nonnull final Class<?> cls) {
		Checks.nullThrow(cls);
		return ClassProperties.parse(cls).getPublic().keySet();
	}
	
	/**
	 * <pre> 获得属性名称集合(get/set[优先] & public).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/05  huangys  Create
	 * </pre>
	 * 
	 * @param obj 对象
	 * @return 属性名称集合
	 */
	@Nonnull
	public static Set<String> getSmartNames(@Nonnull final Object obj) {
		Checks.nullThrow(obj);
		return getSmartNames(obj.getClass());
	}
	
	/**
	 * <pre> 获得属性名称集合(get/set[优先] & public).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/05  huangys  Create
	 * </pre>
	 * 
	 * @param cls 类
	 * @return 属性名称集合
	 */
	@Nonnull
	public static Set<String> getSmartNames(@Nonnull final Class<?> cls) {
		Checks.nullThrow(cls);
		return ClassProperties.parse(cls).getSmart().keySet();
	}
	
	/**
	 * <pre> 设置属性(get/put).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/05  huangys  Create
	 * </pre>
	 * 
	 * @param obj 对象
	 * @param name 属性名
	 * @param value 属性值
	 */
	public static void set(@Nonnull final Object obj, @Nonnull final String name, @Nullable final Object value) {
		Checks.nullThrow(obj);
		Checks.nullThrow(name);
		PropertyDescriptor pd = ClassProperties.parse(obj.getClass()).get().get(name);
		set(obj, pd, value);
	}
	
	/**
	 * <pre> 设置属性(get/put).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/05  huangys  Create
	 * </pre>
	 * 
	 * @param obj 对象
	 * @param map 属性
	 */
	public static void set(@Nonnull final Object obj, @Nonnull final Map<String, Object> map) {
		Checks.nullThrow(obj);
		Checks.nullThrow(map);
		Map<String, PropertyDescriptor> pMap = ClassProperties.parse(obj.getClass()).get();
		for (Entry<String, Object> entry : map.entrySet()) {
			set(obj, pMap.get(entry.getKey()), entry.getValue());
		}
	}
	
	/**
	 * <pre> 获得属性(get/set).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/05  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 属性值类型
	 * @param obj 对象
	 * @param name 属性名
	 * @return 属性值
	 */
	@Nullable
	public static <T> T get(@Nonnull final Object obj, @Nonnull final String name) {
		Checks.nullThrow(obj);
		Checks.nullThrow(name);
		PropertyDescriptor pd = ClassProperties.parse(obj.getClass()).get().get(name);
		Checks.nullThrow(pd, "Class(" + obj.getClass() + ") doesnot contains property(" + name + ")");
		return get(obj, pd);
	}
	
	/**
	 * <pre> 设置属性(public).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/05  huangys  Create
	 * </pre>
	 * 
	 * @param obj 对象
	 * @param name 属性名
	 * @param value 属性值
	 */
	public static void setPublic(@Nonnull final Object obj, @Nonnull final String name, @Nullable final Object value) {
		Checks.nullThrow(obj);
		Checks.nullThrow(name);
		Map<String, Field> pMap = ClassProperties.parse(obj.getClass()).getPublic();
		Checks.notMatchThrow(pMap.containsKey(name), "Class(" + obj.getClass() + ") doesnot contains property(" + name + ")");
		setPublic(obj, pMap.get(name), value);
	}
	
	/**
	 * <pre> 设置属性(public).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/05  huangys  Create
	 * </pre>
	 * 
	 * @param obj 对象
	 * @param map 属性
	 */
	public static void setPublic(@Nonnull final Object obj, @Nonnull final Map<String, Object> map) {
		Checks.nullThrow(obj);
		Checks.nullThrow(map);
		Map<String, Field> pMap = ClassProperties.parse(obj.getClass()).getPublic();
		for (Entry<String, Object> entry : map.entrySet()) {
			if (pMap.containsKey(entry.getKey())) {
				setPublic(obj, pMap.get(entry.getKey()), entry.getValue());
			}
		}
	}
	
	/**
	 * <pre> 获得属性(public).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/05  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 属性值类型
	 * @param obj 对象
	 * @param name 属性名
	 * @return 属性值
	 */
	@Nullable
	public static <T> T getPublic(@Nonnull final Object obj, @Nonnull final String name) {
		Checks.nullThrow(obj);
		Checks.nullThrow(name);
		Map<String, Field> pMap = ClassProperties.parse(obj.getClass()).getPublic();
		Checks.notMatchThrow(pMap.containsKey(name), "Class(" + obj.getClass() + ") doesnot contains property(" + name + ")");
		return getPublic(obj, pMap.get(name));
	}
	
	/**
	 * <pre> 设置属性(get/set[优先] & public).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/05  huangys  Create
	 * </pre>
	 * 
	 * @param obj 对象
	 * @param name 属性名
	 * @param value 属性值
	 */
	public static void setSmart(@Nonnull final Object obj, @Nonnull final String name, @Nullable final Object value) {
		Checks.nullThrow(obj);
		Checks.nullThrow(name);
		Map<String, Object> map = ClassProperties.parse(obj.getClass()).getSmart();
		Checks.notMatchThrow(map.containsKey(name),
				"Class(" + obj.getClass() + ") doesnot contains property(" + name + ")");
		setSmart(obj, map.get(name), name, value);
	}
	
	/**
	 * <pre> 设置属性(get/set[优先] & public).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/05  huangys  Create
	 * </pre>
	 * 
	 * @param obj 对象
	 * @param map 属性名
	 */
	public static void setSmart(@Nonnull final Object obj, @Nonnull final Map<String, Object> map) {
		Checks.nullThrow(obj);
		Checks.nullThrow(map);
		Map<String, Object> pMap = ClassProperties.parse(obj.getClass()).getSmart();
		for (Entry<String, Object> entry : map.entrySet()) {
			setSmart(obj, pMap.get(entry.getKey()), entry.getKey(), entry.getValue());
		}
	}
	
	/**
	 * <pre> 获得属性(get/set[优先] & public).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/05  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 属性值类型
	 * @param obj 对象
	 * @param name 属性名
	 * @return 属性值
	 */
	@Nullable
	public static <T> T getSmart(@Nonnull final Object obj, @Nonnull final String name) {
		Checks.nullThrow(obj);
		Checks.nullThrow(name);
		Map<String, Object> map = ClassProperties.parse(obj.getClass()).getSmart();
		Checks.notMatchThrow(map.containsKey(name),
				"Class(" + obj.getClass() + ") doesnot contains property(" + name + ")");
		return getSmart(obj, map.get(name), name);
	}
	
    /**
     * <pre> 设置属性.
     * 
     * Modification History:
     * Date        Author   Action
     * 2016/01/05  huangys  Create
     * </pre>
     * 
     * @param obj 对象
     * @param pd 属性描述
     * @param value 属性值
     */
	public static void set(@Nonnull final Object obj, @Nonnull final PropertyDescriptor pd, @Nullable final Object value) {
		Checks.nullThrow(obj);
		Checks.nullThrow(pd);
		try {
			pd.getWriteMethod().invoke(obj, new Object[] { Objects.toObject(pd.getPropertyType(), value) });
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
      
    /**
     * <pre> 获得属性.
     * 
     * Modification History:
     * Date        Author   Action
     * 2016/01/05  huangys  Create
     * </pre>
     * 
     * @param <T> 属性值类型
     * @param obj 对象
     * @param pd 属性描述
     * @return 属性值
     */
    @SuppressWarnings("unchecked")
	@Nullable
	public static <T> T get(@Nonnull final Object obj, @Nonnull final PropertyDescriptor pd) {
		Checks.nullThrow(obj);
		Checks.nullThrow(pd);
		try {
			return (T) pd.getReadMethod().invoke(obj, new Object[] {});
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
    
    private static void setPublic(@Nonnull final Object obj, @Nonnull final Field field, @Nullable final Object value) {
		try {
			field.set(obj, Objects.toObject(field.getType(), value));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
    
    @SuppressWarnings("unchecked")
	private static <T> T getPublic(@Nonnull final Object obj, @Nonnull final Field field) { 
		try {
			return (T) field.get(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
    
	private static void setSmart(@Nonnull final Object obj, @Nonnull final Object pdOrField, @Nonnull final String name, @Nullable final Object value) {
		Checks.nullThrow(pdOrField);
		if (pdOrField instanceof Field) {
			setPublic(obj, (Field) pdOrField, value);
		} else {
			set(obj, (PropertyDescriptor) pdOrField, value);
		}
	}
	
	@Nullable
	private static <T> T getSmart(@Nonnull final Object obj, @Nonnull final Object pdOrField, @Nonnull final String name) {
    	Checks.nullThrow(pdOrField);
		if (pdOrField instanceof Field) {
			return getPublic(obj, (Field) pdOrField);
		} else {
			return get(obj, (PropertyDescriptor) pdOrField);
		}
	}
    
	/**
	 * <pre> 获得属性的键值对(get/set).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/07  huangys  Create
	 * </pre>
	 * 
	 * @param obj 对象
	 * @return 属性的键值对(get/set)
	 */
	@Nonnull
	public static Map<String, Object> describe(@Nonnull final Object obj) {
		return describe(obj, true);
	}
	
	/**
	 * <pre> 获得属性的键值对(public).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/07  huangys  Create
	 * </pre>
	 * 
	 * @param obj 对象
	 * @return 属性的键值对(public)
	 */
	@Nonnull
	public static Map<String, Object> describePublic(@Nonnull final Object obj) {
		return describePublic(obj, true);
	}
	
	/**
	 * <pre> 获得属性的键值对(get/set[优先] & public).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/07  huangys  Create
	 * </pre>
	 * 
	 * @param obj 对象
	 * @return 属性的键值对(get/set[优先] & public)
	 */
	@Nonnull
	public static Map<String, Object> describeSmart(@Nonnull final Object obj) {
		return describeSmart(obj, true);
	}
	
	/**
	 * <pre> 获得非空属性的键值对(get/set).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/07  huangys  Create
	 * </pre>
	 * 
	 * @param obj 对象
	 * @return 非空属性的键值对(get/set)
	 */
	@Nonnull
	public static Map<String, Object> describeNotNull(@Nonnull final Object obj) {
		return describe(obj, false);
	}
	
	/**
	 * <pre> 获得非空属性的键值对(public).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/07  huangys  Create
	 * </pre>
	 * 
	 * @param obj 对象
	 * @return 非空属性的键值对(public)
	 */
	@Nonnull
	public static Map<String, Object> describePublicNotNull(@Nonnull final Object obj) {
		return describePublic(obj, false);
	}
	
	/**
	 * <pre> 获得非空属性的键值对(get/set[优先] & public).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/07  huangys  Create
	 * </pre>
	 * 
	 * @param obj 对象
	 * @return 非空属性的键值对(get/set[优先] & public)
	 */
	@Nonnull
	public static Map<String, Object> describeSmartNotNull(@Nonnull final Object obj) {
		return describeSmart(obj, false);
	}
	
	/**
	 * <pre> 获得属性的键值对(get/set).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/07  huangys  Create
	 * </pre>
	 * 
	 * @param obj 对象
	 * @param reserveNull 是否保留Null属性
	 * @return 属性的键值对(get/set)
	 */
	@Nonnull
	public static Map<String, Object> describe(@Nonnull final Object obj, @Nonnull final boolean reserveNull) {
		Checks.nullThrow(obj);
		Map<String, Object> ret = new HashMap<>();
		for (Entry<String, PropertyDescriptor> entry : ClassProperties.parse(obj.getClass()).get().entrySet()) {
			Object value = get(obj, entry.getValue());
			if (reserveNull || value != null) {
				ret.put(entry.getKey(), value);
			}
		}
		return ret;
	}
	
	/**
	 * <pre> 获得属性的键值对(public).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/07  huangys  Create
	 * </pre>
	 * 
	 * @param obj 对象
	 * @param reserveNull 是否保留Null属性
	 * @return 属性的键值对(public)
	 */
	@Nonnull
	public static Map<String, Object> describePublic(@Nonnull final Object obj, @Nonnull final boolean reserveNull) {
		Checks.nullThrow(obj);
		Map<String, Object> ret = new HashMap<>();
		for (Entry<String, Field> entry : ClassProperties.parse(obj.getClass()).getPublic().entrySet()) {
			Object value = getPublic(obj, entry.getValue());
			if (reserveNull || value != null) {
				ret.put(entry.getKey(), value);
			}
		}
		return ret;
	}
	
	/**
	 * <pre> 获得属性的键值对(get/set[优先] & public).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/07  huangys  Create
	 * </pre>
	 * 
	 * @param obj 对象
	 * @param reserveNull 是否保留Null属性
	 * @return 属性的键值对(get/set[优先] & public)
	 */
	@Nonnull
	public static Map<String, Object> describeSmart(@Nonnull final Object obj, @Nonnull final boolean reserveNull) {
		Checks.nullThrow(obj);
		Map<String, Object> ret = new HashMap<>();
		for (Entry<String, Object> entry : ClassProperties.parse(obj.getClass()).getSmart().entrySet()) {
			Object value = getSmart(obj, entry.getValue(), entry.getKey());
			if (reserveNull || value != null) {
				ret.put(entry.getKey(), value);
			}
		}
		return ret;
	}

	/**
	 * <pre> 字符串连接分隔符. </pre>
	 */
	public static final String DELIMTER = ",";
	
	/**
	 * <pre> 获得对象属性名称的文本信息(get/set).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/02/28  huangys  Create
	 * </pre>
	 * 
	 * @param obj 对象
	 * @return 返回属性名称的文本信息(get/set)
	 */
	@Nonnull
	public static String toStringNames(@Nonnull final Object obj) {
		Checks.nullThrow(obj);
		return toStringNames(obj.getClass());
	}
	
	/**
	 * <pre> 获得类属性名称的文本信息(get/set).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/02/28  huangys  Create
	 * </pre>
	 * 
	 * @param cls 类
	 * @return 返回属性名称的文本信息(get/set)
	 */
	@Nonnull
	public static String toStringNames(@Nonnull final Class<?> cls) {
		Checks.nullThrow(cls);
		return StringArray.join(ClassProperties.parse(cls).get().keySet(), DELIMTER);
	}
	
	/**
	 * <pre> 获得对象属性名称的文本信息(public).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/02/28  huangys  Create
	 * </pre>
	 * 
	 * @param obj 对象
	 * @return 返回属性名称的文本信息(public)
	 */
	@Nonnull
	public static String toStringPublicNames(@Nonnull final Object obj) {
		Checks.nullThrow(obj);
		return toStringPublicNames(obj.getClass());
	}
	
	/**
	 * <pre> 获得类属性名称的文本信息(public).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/02/28  huangys  Create
	 * </pre>
	 * 
	 * @param cls 类
	 * @return 返回属性名称的文本信息(public)
	 */
	@Nonnull
	public static String toStringPublicNames(@Nonnull final Class<?> cls) {
		Checks.nullThrow(cls);
		return StringArray.join(ClassProperties.parse(cls).getPublic().keySet(), DELIMTER);
	}
	
	/**
	 * <pre> 获得对象属性名称的文本信息(get/set[优先] & public).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/02/28  huangys  Create
	 * </pre>
	 * 
	 * @param obj 对象
	 * @return 返回属性名称的文本信息(get/set[优先] & public)
	 */
	@Nonnull
	public static String toStringSmartNames(@Nonnull final Object obj) {
		Checks.nullThrow(obj);
		return toStringSmartNames(obj.getClass());
	}
	
	/**
	 * <pre> 获得类属性名称的文本信息(get/set[优先] & public).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/02/28  huangys  Create
	 * </pre>
	 * 
	 * @param cls 类
	 * @return 返回属性名称的文本信息(get/set[优先] & public)
	 */
	@Nonnull
	public static String toStringSmartNames(@Nonnull final Class<?> cls) {
		Checks.nullThrow(cls);
		return StringArray.join(ClassProperties.parse(cls).getSmart().keySet(), DELIMTER);
	}
	
	/**
	 * <pre> 获得对象属性值的文本信息(get/set).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/02/28  huangys  Create
	 * </pre>
	 * 
	 * @param obj 对象
	 * @return 返回属性值的文本信息(get/set)
	 */
	@Nonnull
	public static String toStringValues(@Nonnull final Object obj) {
		return StringArray.join(describe(obj).values(), DELIMTER);
	}
	
	/**
	 * <pre> 获得对象属性值的文本信息(public).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/02/28  huangys  Create
	 * </pre>
	 * 
	 * @param obj 对象
	 * @return 返回属性值的文本信息(public)
	 */
	@Nonnull
	public static String toStringPublicValues(@Nonnull final Object obj) {
		return StringArray.join(describePublic(obj).values(), DELIMTER);
	}
	
	/**
	 * <pre> 获得对象属性值的文本信息(get/set[优先] & public).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/02/28  huangys  Create
	 * </pre>
	 * 
	 * @param obj 对象
	 * @return 返回属性值的文本信息(get/set[优先] & public)
	 */
	@Nonnull
	public static String toStringSmartValues(@Nonnull final Object obj) {
		return StringArray.join(describeSmart(obj).values(), DELIMTER);
	}
	
	/**
	 * <pre> 横向展示属性(get/set).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/18  huangys  Create
	 * </pre>
	 * 
	 * @param obj 对象
	 * @return 横向展示属性(get/set)
	 */
	@Nonnull
	public static String toStringHorizontal(@Nonnull final Object obj) {
		return Maps.getFormatHorizontal(describe(obj));
	}
	
	/**
	 * <pre> 纵向展示属性(get/set).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/18  huangys  Create
	 * </pre>
	 * 
	 * @param obj 对象
	 * @return 纵向展示属性(get/set)
	 */
	@Nonnull
	public static String toStringVertical(@Nonnull final Object obj) {
		return Maps.getFormatVertical(describe(obj));
	}
	
	/**
	 * <pre> 横向展示非空属性(get/set).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/18  huangys  Create
	 * </pre>
	 * 
	 * @param obj 对象
	 * @return 横向展示非空属性(get/set)
	 */
	@Nonnull
	public static String toStringHorizontalNotNull(@Nonnull final Object obj) {
		return Maps.getFormatHorizontal(describeNotNull(obj));
	}
	
	/**
	 * <pre> 纵向展示非空属性(get/set).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/18  huangys  Create
	 * </pre>
	 * 
	 * @param obj 对象
	 * @return 纵向展示非空属性(get/set)
	 */
	@Nonnull
	public static String toStringVerticalNotNull(@Nonnull final Object obj) {
		return Maps.getFormatVertical(describeNotNull(obj));
	}
	
	/**
	 * <pre> 横向展示属性(public).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/18  huangys  Create
	 * </pre>
	 * 
	 * @param obj 对象
	 * @return 横向展示属性(public)
	 */
	@Nonnull
	public static String toStringPublicHorizontal(@Nonnull final Object obj) {
		return Maps.getFormatHorizontal(describePublic(obj));
	}
	
	/**
	 * <pre> 纵向展示属性(public).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/18  huangys  Create
	 * </pre>
	 * 
	 * @param obj 对象
	 * @return 纵向展示属性(public)
	 */
	@Nonnull
	public static String toStringPublicVertical(@Nonnull final Object obj) {
		return Maps.getFormatVertical(describePublic(obj));
	}
	
	/**
	 * <pre> 横向展示非空属性(public).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/18  huangys  Create
	 * </pre>
	 * 
	 * @param obj 对象
	 * @return 横向展示非空属性(public)
	 */
	@Nonnull
	public static String toStringPublicHorizontalNotNull(@Nonnull final Object obj) {
		return Maps.getFormatHorizontal(describePublicNotNull(obj));
	}
	
	/**
	 * <pre> 纵向展示非空属性(public).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/18  huangys  Create
	 * </pre>
	 * 
	 * @param obj 对象
	 * @return 纵向展示非空属性(public)
	 */
	@Nonnull
	public static String toStringPublicVerticalNotNull(@Nonnull final Object obj) {
		return Maps.getFormatVertical(describePublicNotNull(obj));
	}
	
	/**
	 * <pre> 横向展示属性(get/set[优先] & public).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/18  huangys  Create
	 * </pre>
	 * 
	 * @param obj 对象
	 * @return 横向展示属性(get/set[优先] & public)
	 */
	@Nonnull
	public static String toStringSmartHorizontal(@Nonnull final Object obj) {
		return Maps.getFormatHorizontal(describeSmart(obj));
	}
	
	/**
	 * <pre> 纵向展示属性(get/set[优先] & public).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/18  huangys  Create
	 * </pre>
	 * 
	 * @param obj 对象
	 * @return 纵向展示属性(get/set[优先] & public)
	 */
	@Nonnull
	public static String toStringSmartVertical(@Nonnull final Object obj) {
		return Maps.getFormatVertical(describeSmart(obj));
	}
	
	/**
	 * <pre> 横向展示非空属性(get/set[优先] & public).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/18  huangys  Create
	 * </pre>
	 * 
	 * @param obj 对象
	 * @return 横向展示非空属性(get/set[优先] & public)
	 */
	@Nonnull
	public static String toStringSmartHorizontalNotNull(@Nonnull final Object obj) {
		return Maps.getFormatHorizontal(describeSmartNotNull(obj));
	}
	
	/**
	 * <pre> 纵向展示非空属性(get/set[优先] & public).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/18  huangys  Create
	 * </pre>
	 * 
	 * @param obj 对象
	 * @return 纵向展示非空属性(get/set[优先] & public)
	 */
	@Nonnull
	public static String toStringSmartVerticalNotNull(@Nonnull final Object obj) {
		return Maps.getFormatVertical(describeSmartNotNull(obj));
	}
	
	/**
	 * <pre> 设置属性(get/put).
	 * 属性名大小写不敏感。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/05  huangys  Create
	 * </pre>
	 * 
	 * @param obj 对象
	 * @param name 属性名
	 * @param value 属性值
	 */
	public static void setInsensitive(@Nonnull final Object obj, @Nonnull final String name, @Nullable final Object value) {
		Checks.nullThrow(obj);
		Checks.nullThrow(name);
		Map<String, Object> map = Maps.newHashMap(name, obj);
		setInsensitive(obj, map);
	}
	
	/**
	 * <pre> 设置属性(get/put).
	 * 属性名大小写不敏感。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/05  huangys  Create
	 * </pre>
	 * 
	 * @param obj 对象
	 * @param map 属性
	 */
	public static void setInsensitive(@Nonnull final Object obj, @Nonnull final Map<String, Object> map) {
		Checks.nullThrow(obj);
		Checks.nullThrow(map);
		Map<String, PropertyDescriptor> pMap = ClassProperties.parse(obj.getClass()).get();
		Map<String, String> mapInsensitive = MapInsensitive.ofKey(pMap);
		for (Entry<String, Object> entry : map.entrySet()) {
			set(obj, pMap.get(mapInsensitive.get(entry.getKey())), entry.getValue());
		}
	}
	
	/**
	 * <pre> 设置属性(public).
	 * 属性名大小写不敏感。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/05  huangys  Create
	 * </pre>
	 * 
	 * @param obj 对象
	 * @param name 属性名
	 * @param value 属性值
	 */
	public static void setPublicInsensitive(@Nonnull final Object obj, @Nonnull final String name, @Nullable final Object value) {
		Checks.nullThrow(obj);
		Checks.nullThrow(name);
		Map<String, Object> map = Maps.newHashMap(name, obj);
		setPublicInsensitive(obj, map);
	}
	
	/**
	 * <pre> 设置属性(public).
	 * 属性名大小写不敏感。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/05  huangys  Create
	 * </pre>
	 * 
	 * @param obj 对象
	 * @param names 属性名
	 * @param values 属性值
	 */
	public static void setPublicInsensitive(@Nonnull final Object obj, @Nonnull final String[] names, @Nonnull final Object[] values) {
		Checks.nullThrow(obj);
		Checks.nullThrow(names);
		Checks.nullThrow(values);
		setPublicInsensitive(obj, Maps.newHashMap(names, values));
	}
	
	/**
	 * <pre> 设置属性(public).
	 * 属性名大小写不敏感。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/05  huangys  Create
	 * </pre>
	 * 
	 * @param obj 对象
	 * @param map 属性
	 */
	public static void setPublicInsensitive(@Nonnull final Object obj, @Nonnull final Map<String, Object> map) {
		Checks.nullThrow(obj);
		Checks.nullThrow(map);
		Map<String, Field> pMap = ClassProperties.parse(obj.getClass()).getPublic();
		Map<String, String> mapInsensitive = MapInsensitive.ofKey(pMap);
		for (Entry<String, Object> entry : map.entrySet()) {
			Checks.notContainsThrow(mapInsensitive, entry.getKey());
			String name = mapInsensitive.get(entry.getKey());
			setPublic(obj, pMap.get(name), entry.getValue());
		}
	}
	
	/**
	 * <pre> 设置属性(get/set[优先] & public).
	 * 属性名大小写不敏感。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/05  huangys  Create
	 * </pre>
	 * 
	 * @param obj 对象
	 * @param name 属性名
	 * @param value 属性值
	 */
	public static void setSmartInsensitive(@Nonnull final Object obj, @Nonnull final String name, @Nullable final Object value) {
		Checks.nullThrow(obj);
		Checks.nullThrow(name);
		Map<String, Object> map = Maps.newHashMap(name, obj);
		setSmartInsensitive(obj, map);
	}
	
	/**
	 * <pre> 设置属性(get/set[优先] & public).
	 * 属性名大小写不敏感。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/05  huangys  Create
	 * </pre>
	 * 
	 * @param obj 对象
	 * @param map 属性名
	 */
	public static void setSmartInsensitive(@Nonnull final Object obj, @Nonnull final Map<String, Object> map) {
		Checks.nullThrow(obj);
		Checks.nullThrow(map);
		Map<String, Object> pMap = ClassProperties.parse(obj.getClass()).getSmart();
		Map<String, String> mapInsensitive = MapInsensitive.ofKey(pMap);
		for (Entry<String, Object> entry : map.entrySet()) {
			String name = mapInsensitive.get(entry.getKey());
			setSmart(obj, pMap.get(name), name, entry.getValue());
		}
	}
	
	/**
	 * <pre> 拷贝属性.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/10  huangys  Create
	 * </pre>
	 * 
	 * @param orig 来源
	 * @param dest 目标
	 */
	public static void copy(@Nonnull final Object orig, @Nonnull final Object dest) {
		Checks.nullThrow(orig);
		Checks.nullThrow(dest);
		try {
			PropertyUtils.copyProperties(dest, orig);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 拷贝属性.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/10  huangys  Create
	 * </pre>
	 * 
	 * @param orig 来源
	 * @param dest 目标
	 */
	public static void copy(@Nonnull final Map<String, Object> orig, @Nonnull final Object dest) {
		Checks.nullThrow(orig);
		Checks.nullThrow(dest);
		try {
			org.apache.commons.beanutils.BeanUtils.populate(dest, orig);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 拷贝属性.
	 * 默认dest中的Key大小写不敏感。
	 * dest中的Value可以被null覆盖。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/10  huangys  Create
	 * </pre>
	 * 
	 * @param orig 来源
	 * @param dest 目标
	 */
	public static void copy(@Nonnull final Object orig, @Nonnull final Map<String, Object> dest) {
		Checks.nullThrow(orig);
		Checks.nullThrow(dest);
		copy(orig, dest, false, true);
	}
	
	/**
	 * <pre> 拷贝属性.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/10  huangys  Create
	 * </pre>
	 * 
	 * @param orig 来源
	 * @param dest 目标
	 * @param sensiable 是否大小写敏感
	 * @param nullable 是否允许Null值覆盖
	 */
	public static void copy(@Nonnull final Object orig, @Nonnull final Map<String, Object> dest, @Nonnull final boolean sensiable, @Nonnull final boolean nullable) {
		Map<String, Object> beanMap = describe(orig);
		copy(beanMap, dest, sensiable, nullable);
	}
	
	/**
	 * <pre> 拷贝属性.
	 * 默认dest中的Key大小写不敏感。
	 * dest中的Value可以被null覆盖。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/10  huangys  Create
	 * </pre>
	 * 
	 * @param orig 来源
	 * @param dest 目标
	 */
	public static void copy(@Nonnull final Map<String, Object> orig, @Nonnull final Map<String, Object> dest) {
		copy(orig, dest, false, true);
	}
	
	/**
	 * <pre> 拷贝属性.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/10  huangys  Create
	 * </pre>
	 * 
	 * @param orig 原始
	 * @param dest 目标
	 * @param sensiable 是否大小写敏感
	 * @param nullable 是否允许Null值覆盖
	 */
	public static void copy(@Nonnull final Map<String, Object> orig, @Nonnull final Map<String, Object> dest, @Nonnull final boolean sensiable, @Nonnull final boolean nullable) {
		Checks.nullThrow(orig);
		Checks.nullThrow(dest);
		// Key大小写敏感
		if (sensiable) {
			for (Entry<String, ?> entry : orig.entrySet()) {
				if (dest.containsKey(entry.getKey())) {
					Object value = entry.getValue();
					if (nullable || value != null) {
						dest.put(entry.getKey(), value);
					}
				}
			}
		} else {
			MapInsensitive<Object> newOrig = MapInsensitive.of(orig);
			// 拷贝
			for (String destKey : dest.keySet()) {
				if (newOrig.containsKey(destKey)) {
					Object value = newOrig.get(destKey);
					if (nullable || value != null) {
						dest.put(destKey, value);
					}
				}
			}
		}
	}

	/**
	 * <pre> 拷贝属性.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/10  huangys  Create
	 * </pre>
	 * 
	 * @param orig 来源
	 * @param dest 目标
	 */
	public static void copyPublic(@Nonnull final Object orig, @Nonnull final Object dest) {
		Checks.nullThrow(orig);
		Checks.nullThrow(dest);
		setPublic(dest, describePublic(orig));
	}
	
}
