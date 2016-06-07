/*
 * Copyright 2014 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2014/01/07.
 * 
 */
package com.toobye.common.base;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.toobye.common.lang.Checks;

/**
 * <pre> 枚举工具类.
 * 
 * org.apache.commons.lang3.EnumUtils说明
 * 位矢量：generateBitVector（用位表示枚举值是否已出现，注：枚举元素不可超过64个）
 * 		如enum E{A, B, C}, generateBitVector(E.class, E.A, E.C) = 5 即00000101
 * 获取对应枚举对象E：getEnum
 * 获取全部枚举对象List<E>：getEnumList
 * 获取全部枚举对象Map<String, E>：getEnumMap
 * 判断字符串是否有对应的合法枚举对象：isValidEnum
 * 通过位矢量获得对应的枚举集合：processBitVector
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2014/01/07  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class EnumUtils extends org.apache.commons.lang3.EnumUtils {
	
	@edu.umd.cs.findbugs.annotations.SuppressFBWarnings("NM_SAME_SIMPLE_NAME_AS_SUPERCLASS")
	private EnumUtils() { }
	
	/**
	 * <pre> 将枚举集合拼接到字符串起始位置.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/05/05  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 枚举类型
	 * @param enums 枚举集合
	 * @param cls 枚举类
	 * @return 拼接后字符串
	 */
	public static <T extends Enum<T>> String joinBooleanEnums(@Nullable final Set<T> enums, @Nonnull final Class<T> cls) {
		Checks.nullThrow(cls);
		StringBuffer sb = new StringBuffer();
		for (T t : getEnumList(cls)) {
			sb.append(enums != null && enums.contains(t) ? "Y" : "N");
		}
		return sb.toString();
	}
	
	/**
	 * <pre> 将枚举集合拼接到字符串起始位置.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/05/05  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 枚举类型
	 * @param enumStr 枚举字符串
	 * @param cls 枚举类
	 * @return 拼接后字符串
	 */
	public static <T extends Enum<T>> Set<T> splitBooleanEnums(@Nonnull final String enumStr, @Nonnull final Class<T> cls) {
		Checks.nullThrow(enumStr);
		Checks.nullThrow(cls);
		List<T> list = getEnumList(cls);
		Checks.matchThrow(enumStr.length() != list.size(), "Length cannot match(" + enumStr + ", Enum: " + cls.getName() + ").");
		Set<T> enums = new HashSet<>();
		char[] chars = enumStr.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			if (c == 'Y') {
				enums.add(list.get(i));
			} else if (c == 'N') {
				continue;
			} else {
				throw new RuntimeException("Enum String(" + enumStr + ") error.");
			}
		}
		return enums;
	}
	
}
