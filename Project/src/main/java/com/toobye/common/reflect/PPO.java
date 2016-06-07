/*
 * Copyright 2015 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2015/06/18.
 * 
 */
package com.toobye.common.reflect;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.toobye.common.collection.Iterables;
import com.toobye.common.collection.Maps;
import com.toobye.common.lang.Checks;
import com.toobye.common.lang.Objects;

/**
 * <pre> 公共属性.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2015/06/18  huangys  v1.0      Create
 * </pre>
 * 
 * @param <T> 对象本身类型
 */
public class PPO<T> {

	@Override
	public final String toString() {
		return Properties.toStringPublicValues(this);
	}
	
	/**
	 * <pre> 获得对象属性名称的文本信息.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/02/28  huangys  Create
	 * </pre>
	 * 
	 * @return 返回属性名称的文本信息
	 */
	@Nonnull
	public String toStringNames() {
		return Properties.toStringPublicNames(getClass());
	}
	
	/**
	 * <pre> 横向展示属性.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/09/05  huangys  Create
	 * </pre>
	 * 
	 * @return 横向展示属性
	 */
	@Nonnull
	public final String toStringHorizontal() {
		return Properties.toStringPublicHorizontal(this);
	}
	
	/**
	 * <pre> 纵向展示属性.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/09/05  huangys  Create
	 * </pre>
	 * 
	 * @return 纵向展示属性
	 */
	@Nonnull
	public final String toStringVertical() {
		return Properties.toStringPublicVertical(this);
	}
	
	/**
	 * <pre> 横向展示非空属性.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/09/05  huangys  Create
	 * </pre>
	 * 
	 * @return 横向展示非空属性
	 */
	@Nonnull
	public final String toStringHorizontalNotNull() {
		return Properties.toStringPublicHorizontalNotNull(this);
	}
	
	/**
	 * <pre> 纵向展示非空属性.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/09/05  huangys  Create
	 * </pre>
	 * 
	 * @return 纵向展示非空属性
	 */
	@Nonnull
	public final String toStringVerticalNotNull() {
		return Properties.toStringPublicVerticalNotNull(this);
	}
	
	/**
	 * <pre> 获得属性的键值对.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/07  huangys  Create
	 * </pre>
	 * 
	 * @return 属性的键值对
	 */
	@Nonnull
	public final Map<String, Object> describe() {
		return Properties.describePublic(this);
	}
	
	/**
	 * <pre> 获得非空属性的键值对.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/07  huangys  Create
	 * </pre>
	 * 
	 * @return 非空属性的键值对
	 */
	@Nonnull
	public final Map<String, Object> describeNotNull() {
		return Properties.describePublicNotNull(this);
	}
	
	/**
	 * <pre> 设置属性.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/05  huangys  Create
	 * </pre>
	 * 
	 * @param name 属性名
	 * @param value 属性值
	 */
	public final void set(@Nonnull final String name, @Nullable final Object value) {
		Properties.setPublic(this, name, value);
	}
	
	/**
	 * <pre> 设置属性.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/05  huangys  Create
	 * </pre>
	 * 
	 * @param map 属性
	 */
	public final void set(@Nonnull final Map<String, Object> map) {
		Properties.setPublic(this, map);
	}
	
	/**
	 * <pre> 设置属性.
	 * 属性名大小写不敏感。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/05  huangys  Create
	 * </pre>
	 * 
	 * @param name 属性名
	 * @param value 属性值
	 */
	public final void setInsensitive(@Nonnull final String name, @Nullable final Object value) {
		Properties.setPublicInsensitive(this, name, value);
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
	 * @param names 属性名
	 * @param values 属性值
	 */
	public final void setInsensitive(@Nonnull final String[] names, @Nonnull final Object[] values) {
		Properties.setPublicInsensitive(this, names, values);
	}
	
	/**
	 * <pre> 设置属性.
	 * 属性名大小写不敏感。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/05  huangys  Create
	 * </pre>
	 * 
	 * @param map 属性
	 */
	public final void setInsensitive(@Nonnull final Map<String, Object> map) {
		Properties.setPublicInsensitive(this, map);
	}
	
	/**
	 * <pre> 获得属性.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/05  huangys  Create
	 * </pre>
	 * 
	 * @param <V> 属性值类型
	 * @param name 属性名
	 * @return 属性值
	 */
	@Nullable
	public final <V> V get(@Nonnull final String name) {
		return Properties.getPublic(this, name);
	}
	
	/**
	 * <pre> 获得属性名称集合.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/05  huangys  Create
	 * </pre>
	 * 
	 * @return 属性值名称集合
	 */
	@Nullable
	public final Set<String> getNames() {
		return Properties.getPublicNames(this);
	}
	
	/**
	 * <pre> 设置属性.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/05  huangys  Create
	 * </pre>
	 * 
	 * @param name 属性名
	 * @param value 属性值
	 */
	public final void setFromString(@Nonnull final String name, @Nonnull final String value) {
		Checks.nullThrow(name);
		setFromString(new String[]{name}, new String[]{value});
	}
	
	/**
	 * <pre> 设置属性.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/05  huangys  Create
	 * </pre>
	 * 
	 * @param names 属性名
	 * @param values 属性值
	 */
	public final void setFromString(@Nonnull final String[] names, @Nonnull final String[] values) {
		Checks.nullThrow(names);
		Checks.nullThrow(values);
		Map<String, Field> pMap = ClassProperties.parse(getClass()).getPublic();
		Object[] objs = new Object[values.length];
		for (int i = 0; i < values.length; i++) {
			objs[i] = Objects.toObject(pMap.get(names[i]).getType(), values[i]);
		}
		set(Maps.newHashMap(names, objs));
	}
	
	/**
	 * <pre> 设置属性.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/05  huangys  Create
	 * </pre>
	 * 
	 * @param values 属性值
	 */
	public final void setFromString(@Nonnull final String values) {
		Checks.nullThrow(values);
		setFromString(Iterables.toArray(getNames()), values.split(Properties.DELIMTER));
	}
	
}
