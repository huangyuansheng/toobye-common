/*
 * Copyright 2014 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2014/08/22.
 * 
 */
package com.toobye.common.string;

import java.util.Iterator;

import com.toobye.common.collection.Arrays;
import com.toobye.common.lang.Checks;
import com.toobye.common.lang.Condition;
import com.toobye.common.lang.Function;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * <pre> 字符串数组.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2014/08/22  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class StringArray {
	
	private String[] array;
	
	/**
	 * <pre> 返回字符串数组内容.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/22  huangys  Create
	 * </pre>
	 * 
	 * @return 字符串数组
	 */
	@Nullable
	public String[] getArray() {
		return array;
	}
	
	/**
	 * <pre> 创建处理对象.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/22  huangys  Create
	 * </pre>
	 * 
	 * @param array 字符串数组
	 * @return 处理对象
	 */
	@Nonnull
	public static StringArray of(@Nullable final String[] array) {
		return new StringArray(array);
	}

	/**
	 * <pre> 构造器. </pre>
	 *
	 * @param array 字符串数组
	 */
	private StringArray(@Nullable final String[] array) {
		this.array = array;
	}
	
	/**
	 * <pre> 移除元素左右侧的空白字符.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/21  huangys  Create
	 * </pre>
	 * 
	 * @return 对象本身
	 */
	@Nonnull
	public StringArray trim() {
		array = trim(array);
		return this;
	}
	
	/**
	 * <pre> 移除起始和末尾的指定任意字符.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/21  huangys  Create
	 * </pre>
	 * 
	 * @param searchChars 移除字符集合，null时使用空白字符
	 * @return 对象本身
	 */
	@Nonnull
	public StringArray trimAnyChar(@Nullable final String searchChars) {
		array = trimAnyChar(array, searchChars);
		return this;
	}
	
	/**
	 * <pre> 若为Null，则返回指定字符串，否则返回本身.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/21  huangys  Create
	 * </pre>
	 * 
	 * @param toStr 替换字符串
	 * @return 对象本身
	 */
	@Nonnull
	public StringArray nullTo(@Nullable final String toStr) {
		array = nullTo(array, toStr);
		return this;
	}
	
	/**
	 * <pre> 若为Null，则返回空字符串，否则返回本身.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/21  huangys  Create
	 * </pre>
	 * 
	 * @return 对象本身
	 */
	@Nonnull
	public StringArray nullToEmpty() {
		array = nullToEmpty(array);
		return this;
	}
	
	/**
	 * <pre> 若元素内容为"Null"字符串，则返回null，否则返回本身.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/21  huangys  Create
	 * </pre>
	 * 
	 * @return 对象本身
	 */
	@Nonnull
	public StringArray nullStringToNull() {
		array = nullStringToNull(array);
		return this;
	}
	
	/**
	 * <pre> 空字符串转为Null.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/21  huangys  Create
	 * </pre>
	 * 
	 * @return 对象本身
	 */
	@Nonnull
	public StringArray emptyToNull() {
		array = emptyToNull(array);
		return this;
	}
	
	/**
	 * <pre> 空字符串转为指定字符串.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/21  huangys  Create
	 * </pre>
	 * 
	 * @param toStr 指定字符串
	 * @return 对象本身
	 */
	@Nonnull
	public StringArray emptyTo(@Nullable final String toStr) {
		array = emptyTo(array, toStr);
		return this;
	}
	
	/**
	 * <pre> 空白字符串转为Null.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/21  huangys  Create
	 * </pre>
	 * 
	 * @return 对象本身
	 */
	@Nonnull
	public StringArray blankToNull() {
		array = blankToNull(array);
		return this;
	}
	
	/**
	 * <pre> 空白字符串转为空字符串.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/21  huangys  Create
	 * </pre>
	 * 
	 * @return 对象本身
	 */
	@Nonnull
	public StringArray blankToEmpty() {
		array = blankToEmpty(array);
		return this;
	}
	
	/**
	 * <pre> 空白字符串转为指定字符串.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/21  huangys  Create
	 * </pre>
	 * 
	 * @param toStr 指定字符串
	 * @return 对象本身
	 */
	@Nonnull
	public StringArray blankTo(final String toStr) {
		array = blankTo(array, toStr);
		return this;
	}
	
	/**
	 * <pre> 若元素内容为匹配字符串，则返回转换字符串，否则返回本身.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/21  huangys  Create
	 * </pre>
	 * 
	 * @param matchStr 匹配字符串
	 * @param toStr 指定字符串
	 * @return 对象本身
	 */
	@Nonnull
	public StringArray to(@Nullable final String matchStr, @Nullable final String toStr) {
		array = to(array, matchStr, toStr);
		return this;
	}
	
	/**
	 * <pre> 若元素内容为匹配字符串（忽视大小写），则返回转换字符串，否则返回本身.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/21  huangys  Create
	 * </pre>
	 * 
	 * @param matchStr 匹配字符串
	 * @param toStr 指定字符串
	 * @return 对象本身
	 */
	@Nonnull
	public StringArray toIgnoreCase(@Nullable final String matchStr, @Nullable final String toStr) {
		array = toIgnoreCase(array, matchStr, toStr);
		return this;
	}
	
	/**
	 * <pre> 满足条件的移除.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/21  huangys  Create
	 * </pre>
	 * 
	 * @param conditions 条件
	 * @return 对象本身
	 */
	@SafeVarargs
	@Nonnull
	public final StringArray delete(@Nullable final Condition<String>... conditions) {
		array = Arrays.delete(array, conditions);
		return this;
	}
	
	/**
	 * <pre> 满足条件的移除.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/21  huangys  Create
	 * </pre>
	 * 
	 * @param conditions 条件
	 * @return 对象本身
	 */
	@Nonnull
	public StringArray delete(@Nullable final Iterable<Condition<String>> conditions) {
		array = Arrays.delete(array, conditions);
		return this;
	}
	
	/**
	 * <pre> 满足条件的保留.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/21  huangys  Create
	 * </pre>
	 * 
	 * @param conditions 条件
	 * @return 对象本身
	 */
	@SafeVarargs
	@Nonnull
	public final StringArray reserve(@Nullable final Condition<String>... conditions) {
		array = Arrays.reserve(array, conditions);
		return this;
	}
	
	/**
	 * <pre> 满足条件的保留.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/21  huangys  Create
	 * </pre>
	 * 
	 * @param conditions 条件
	 * @return 对象本身
	 */
	@Nonnull
	public StringArray reserve(@Nullable final Iterable<Condition<String>> conditions) {
		array = Arrays.reserve(array, conditions);
		return this;
	}
	
	/**
	 * <pre> 移除Null元素.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/21  huangys  Create
	 * </pre>
	 * 
	 * @return 对象本身
	 */
	@Nonnull
	public StringArray deleteNull() {
		array = deleteNull(array);
		return this;
	}
	
	/**
	 * <pre> 移除Empty元素.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/21  huangys  Create
	 * </pre>
	 * 
	 * @return 对象本身
	 */
	@Nonnull
	public StringArray deleteEmpty() {
		array = deleteEmpty(array);
		return this;
	}
	
	/**
	 * <pre> 移除Blank元素.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/21  huangys  Create
	 * </pre>
	 * 
	 * @return 对象本身
	 */
	@Nonnull
	public StringArray deleteBlank() {
		array = deleteBlank(array);
		return this;
	}
	
	/**
	 * <pre> 移除内容为匹配字符串的元素.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/21  huangys  Create
	 * </pre>
	 * 
	 * @param matchStr 匹配字符串
	 * @return 对象本身
	 */
	@Nonnull
	public StringArray delete(@Nullable final String matchStr) {
		array = delete(array, matchStr);
		return this;
	}
	
	/**
	 * <pre> 移除内容为匹配字符串的元素（忽视大小写）.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/21  huangys  Create
	 * </pre>
	 * 
	 * @param matchStr 匹配字符串
	 * @return 对象本身
	 */
	@Nonnull
	public StringArray deleteIgnoreCase(@Nullable final String matchStr) {
		array = deleteIgnoreCase(array, matchStr);
		return this;
	}
	
	/**
	 * <pre> 移除左右侧的组分隔符.
	 * 仅当两侧都有组分隔符时，才会进行移除操作。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/21  huangys  Create
	 * </pre>
	 * 
	 * @return 对象本身
	 */
	@Nonnull
	public StringArray trimGroupChar() {
		array = trimGroupChar(array);
		return this;
	}
	
	/**
	 * <pre> 移除左右侧的组分隔符.
	 * 仅当两侧都有组分隔符时，才会进行移除操作。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/21  huangys  Create
	 * </pre>
	 * 
	 * @param groupChar 组分隔符
	 * @param escapeChar 逃逸符
	 * @return 对象本身
	 */
	@Nonnull
	public StringArray trimGroupChar(@Nonnull final char groupChar, @Nonnull final char escapeChar) {
		array = trimGroupChar(array, groupChar, escapeChar);
		return this;
	}
	
	/**
	 * <pre> 移除逃逸符.
	 * 仅当逃逸符右侧为组分隔符时。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/21  huangys  Create
	 * </pre>
	 * 
	 * @return 对象本身
	 */
	@Nonnull
	public StringArray clearEscapeChar() {
		array = clearEscapeChar(array);
		return this;
	}
	
	/**
	 * <pre> 移除逃逸符.
	 * 仅当逃逸符右侧为组分隔符时。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/21  huangys  Create
	 * </pre>
	 * 
	 * @param groupChar 组分隔符
	 * @param escapeChar 逃逸符
	 * @return 对象本身
	 */
	@Nonnull
	public StringArray clearEscapeChar(@Nonnull final char groupChar, @Nonnull final char escapeChar) {
		array = clearEscapeChar(array, groupChar, escapeChar);
		return this;
	}
	
	/**
	 * <pre> 将数组内的元素使用加工器处理.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param funcs 加工器
	 * @return 处理后的字符串数组
	 */
	@SafeVarargs
	@Nullable
	public final StringArray to(@Nullable final Function<String, String>... funcs) {
		array = to(array, funcs);
		return this;
	}
	
	/**
	 * <pre> 将数组内的元素使用加工器处理.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param funcs 加工器
	 * @return 处理后的字符串数组
	 */
	@Nullable
	public StringArray to(@Nullable final Iterable<Function<String, String>> funcs) {
		array = to(array, funcs);
		return this;
	}
	
	/**
	 * <pre> 移除起始和末尾的空白字符.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param array 字符串数组
	 * @return 处理后的字符串数组
	 * @see StringUtils#trim(String)
	 */
	@Nullable
	public static String[] trim(@Nullable final String[] array) {
		Arrays.process(array, StringArrayFuncs.TRIM);
		return array;
	}
	
	/**
	 * <pre> 移除起始和末尾的指定任意字符.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param array 字符串数组
	 * @param searchChars 移除字符集合，null时使用空白字符
	 * @return 处理后的字符串数组
	 * @see StringUtils#trimAnyChar(String,String)
	 */
	@Nullable
	public static String[] trimAnyChar(@Nullable final String[] array, @Nullable final String searchChars) {
		Arrays.process(array, StringArrayFuncs.trimAnyChar(searchChars));
		return array;
	}
	
	/**
	 * <pre> 若为Null，则返回空字符串，否则返回本身.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param array 字符串数组
	 * @return 处理后的字符串数组
	 * @see StringUtils#nullToEmpty(String)
	 */
	@Nullable
	public static String[] nullToEmpty(@Nullable final String[] array) {
		Arrays.process(array, StringArrayFuncs.NULL_TO_EMPTY);
		return array;
	}
	
	/**
	 * <pre> 若为Null，则返回指定字符串，否则返回本身.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param array 字符串数组
	 * @param toStr 替换字符串
	 * @return 处理后的字符串数组
	 * @see StringUtils#nullTo(String,String)
	 */
	@Nullable
	public static String[] nullTo(@Nullable final String[] array, @Nullable final String toStr) {
		Arrays.process(array, StringArrayFuncs.nullTo(toStr));
		return array;
	}
	
	/**
	 * <pre> 若元素内容为"Null"字符串，则返回null，否则返回本身.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param array 字符串数组
	 * @return 处理后的字符串数组
	 * @see StringUtils#nullStringToNull(String)
	 */
	@Nullable
	public static String[] nullStringToNull(@Nullable final String[] array) {
		Arrays.process(array, StringArrayFuncs.NULL_STRING_TO_NULL);
		return array;
	}
	
	/**
	 * <pre> 若为空字符串 ，则返回null，否则返回本身.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param array 字符串数组
	 * @return 处理后的字符串数组
	 * @see StringUtils#emptyToNull(String)
	 */
	@Nullable
	public static String[] emptyToNull(@Nullable final String[] array) {
		Arrays.process(array, StringArrayFuncs.EMPTY_TO_NULL);
		return array;
	}
	
	/**
	 * <pre> 若为空字符串 ，则返回指定字符串，否则返回本身.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param array 字符串数组
	 * @param toStr 替换字符串
	 * @return 处理后的字符串数组
	 * @see StringUtils#emptyTo(String,String)
	 */
	@Nullable
	public static String[] emptyTo(@Nullable final String[] array, @Nullable final String toStr) {
		Arrays.process(array, StringArrayFuncs.emptyTo(toStr));
		return array;
	}
	
	/**
	 * <pre> 若为空白字符串，则返回null，否则返回本身.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param array 字符串数组
	 * @return 处理后的字符串数组
	 * @see StringUtils#blankToNull(String)
	 */
	@Nullable
	public static String[] blankToNull(@Nullable final String[] array) {
		Arrays.process(array, StringArrayFuncs.BLANK_TO_NULL);
		return array;
	}
	
	/**
	 * <pre> 若为空白字符串，则返回空字符串，否则返回本身.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param array 字符串数组
	 * @return 处理后的字符串数组
	 * @see StringUtils#blankToEmpty(String)
	 */
	@Nullable
	public static String[] blankToEmpty(@Nullable final String[] array) {
		Arrays.process(array, StringArrayFuncs.BLANK_TO_EMPTY);
		return array;
	}
	
	/**
	 * <pre> 若为空白字符串，则返回指定字符串，否则返回本身.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param array 字符串数组
	 * @param toStr 替换字符串
	 * @return 处理后的字符串数组
	 * @see StringUtils#blankTo(String,String)
	 */
	@Nullable
	public static String[] blankTo(@Nullable final String[] array, @Nullable final String toStr) {
		Arrays.process(array, StringArrayFuncs.blankTo(toStr));
		return array;
	}
	
	/**
	 * <pre> 将指定字符串替换为特定字符串.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param array 字符串数组
	 * @param matchStr 指定字符串
	 * @param toStr 指定字符串
	 * @return 处理后的字符串数组
	 * @see StringUtils#to(String,String,String)
	 */
	@Nullable
	public static String[] to(@Nullable final String[] array, @Nullable final String matchStr, @Nullable final String toStr) {
		Arrays.process(array, StringArrayFuncs.to(matchStr, toStr));
		return array;
	}
	
	/**
	 * <pre> 将指定字符串替换为特定字符串（无视大小写）.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param array 字符串数组
	 * @param matchStr 指定字符串
	 * @param toStr 指定字符串
	 * @return 处理后的字符串数组
	 * @see StringUtils#toIgnoreCase(String,String,String)
	 */
	@Nullable
	public static String[] toIgnoreCase(@Nullable final String[] array, @Nullable final String matchStr, @Nullable final String toStr) {
		Arrays.process(array, StringArrayFuncs.toIgnoreCase(matchStr, toStr));
		return array;
	}
	
	/**
	 * <pre> 移除左右侧的组分隔符.
	 * 仅当两侧都有组分隔符时，才会进行移除操作。
	 * 这里使用默认的组分隔符和逃逸符。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/21  huangys  Create
	 * </pre>
	 * 
	 * @param array 字符串数组
	 * @return 处理后的字符串数组
	 * @see StringUtils#trimGroupChar(String)
	 */
	@Nullable
	public static String[] trimGroupChar(@Nullable final String[] array) {
		Arrays.process(array, StringArrayFuncs.TRIM_GROUP_CHAR);
		return array;
	}
	
	/**
	 * <pre> 移除左右侧的组分隔符.
	 * 仅当两侧都有组分隔符时，才会进行移除操作。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/21  huangys  Create
	 * </pre>
	 * 
	 * @param array 字符串数组
	 * @param groupChar 组分隔符
	 * @param escapeChar 逃逸符
	 * @return 处理后的字符串数组
	 * @see StringUtils#trimGroupChar(String,char,char)
	 */
	@Nullable
	public static String[] trimGroupChar(@Nullable final String[] array, @Nonnull final char groupChar, @Nonnull final char escapeChar) {
		Arrays.process(array, StringArrayFuncs.trimGroupChar(groupChar, escapeChar));
		return array;
	}
	
	/**
	 * <pre> 移除逃逸符.
	 * 仅当逃逸符右侧为组分隔符时。
	 * 这里使用默认的组分隔符和逃逸符。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/21  huangys  Create
	 * </pre>
	 * 
	 * @param array 字符串数组
	 * @return 处理后的字符串数组
	 * @see StringUtils#clearEscapeChar(String)
	 */
	@Nullable
	public static String[] clearEscapeChar(@Nullable final String[] array) {
		Arrays.process(array, StringArrayFuncs.CLEAR_ESCAPE_CHAR);
		return array;
	}
	
	/**
	 * <pre> 移除逃逸符.
	 * 仅当逃逸符右侧为组分隔符时。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/21  huangys  Create
	 * </pre>
	 * 
	 * @param array 字符串数组
	 * @param groupChar 组分隔符
	 * @param escapeChar 逃逸符
	 * @return 处理后的字符串数组
	 * @see StringUtils#clearEscapeChar(String,char,char)
	 */
	@Nullable
	public static String[] clearEscapeChar(@Nullable final String[] array, @Nonnull final char groupChar, @Nonnull final char escapeChar) {
		Arrays.process(array, StringArrayFuncs.clearEscapeChar(groupChar, escapeChar));
		return array;
	}
	
	/**
	 * <pre> 将数组内的元素使用加工器处理.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param array 字符串数组
	 * @param funcs 加工器
	 * @return 处理后的字符串数组
	 */
	@SafeVarargs
	@Nullable
	public static String[] to(@Nullable final String[] array, @Nullable final Function<String, String>... funcs) {
		Arrays.process(array, funcs);
		return array;
	}
	
	/**
	 * <pre> 将数组内的元素使用加工器处理.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param array 字符串数组
	 * @param funcs 加工器
	 * @return 处理后的字符串数组
	 */
	@Nullable
	public static String[] to(@Nullable final String[] array, @Nullable final Iterable<Function<String, String>> funcs) {
		Arrays.process(array, funcs);
		return array;
	}
	
	/**
	 * <pre> 移除null元素.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/21  huangys  Create
	 * </pre>
	 * 
	 * @param array 字符串数组
	 * @return 处理后的数组
	 */
	public static String[] deleteNull(@Nullable final String[] array) {
		return delete(array, StringArrayFuncs.IS_NULL);
	}
	
	/**
	 * <pre> 移除Empty元素.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/21  huangys  Create
	 * </pre>
	 * 
	 * @param array 字符串数组
	 * @return 处理后的数组
	 */
	public static String[] deleteEmpty(@Nullable final String[] array) {
		return delete(array, StringArrayFuncs.IS_EMPTY);
	}
	
	/**
	 * <pre> 移除Blank元素.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/21  huangys  Create
	 * </pre>
	 * 
	 * @param array 字符串数组
	 * @return 处理后的数组
	 */
	public static String[] deleteBlank(@Nullable final String[] array) {
		return delete(array, StringArrayFuncs.IS_BLANK);
	}
	
	/**
	 * <pre> 移除内容为匹配字符串的元素.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/21  huangys  Create
	 * </pre>
	 * 
	 * @param array 字符串数组
	 * @param matchStr 匹配字符串
	 * @return 处理后的数组
	 */
	public static String[] delete(@Nullable final String[] array, @Nullable final String matchStr) {
		Checks.nullThrow(matchStr);
		return delete(array, StringArrayFuncs.is(matchStr));
	}
	
	/**
	 * <pre> 移除内容为匹配字符串的元素(忽视大小写).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/21  huangys  Create
	 * </pre>
	 * 
	 * @param array 字符串数组
	 * @param matchStr 匹配字符串
	 * @return 处理后的数组
	 */
	public static String[] deleteIgnoreCase(@Nullable final String[] array, @Nullable final String matchStr) {
		Checks.nullThrow(matchStr);
		return delete(array, StringArrayFuncs.isIgnoreCase(matchStr));
	}
	
	/**
	 * <pre> 满足条件的移除.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/21  huangys  Create
	 * </pre>
	 * 
	 * @param array 数组
	 * @param conditions 条件
	 * @return 处理后数组
	 */
	@SafeVarargs
	@Nullable
	public static String[] delete(@Nullable final String[] array, @Nullable final Condition<String>... conditions) {
		return Arrays.delete(array, conditions);
	}
	
	/**
	 * <pre> 满足条件的移除.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/21  huangys  Create
	 * </pre>
	 * 
	 * @param array 数组
	 * @param conditions 条件
	 * @return 处理后数组
	 */
	@Nullable
	public static String[] delete(@Nullable final String[] array, @Nullable final Iterable<Condition<String>> conditions) {
		return Arrays.delete(array, conditions);
	}
	
	/**
	 * <pre> 满足条件的保留.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/21  huangys  Create
	 * </pre>
	 * 
	 * @param array 数组
	 * @param conditions 条件
	 * @return 处理后数组
	 */
	@SafeVarargs
	@Nullable
	public static String[] reserve(@Nullable final String[] array, @Nullable final Condition<String>... conditions) {
		return Arrays.reserve(array, conditions);
	}
	
	/**
	 * <pre> 满足条件的保留.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/21  huangys  Create
	 * </pre>
	 * 
	 * @param array 数组
	 * @param conditions 条件
	 * @return 处理后数组
	 */
	@Nullable
	public static String[] reserve(@Nullable final String[] array, @Nullable final Iterable<Condition<String>> conditions) {
		return Arrays.reserve(array, conditions);
	}

	/**
	 * <pre> 直接连接元素.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/30  huangys  Create
	 * </pre>
	 * 
	 * @param iterable 可迭代对象
	 * @return 连接后字符串
	 */
	public static String join(@Nullable final Iterable<?> iterable) {
		return join(iterable, "");
	}
	
	/**
	 * <pre> 连接元素.
	 * 元素为Null时，连接时使用空字符串。
	 * 
	 * (null, *) = null
	 * (Arrays.asList("1", "2"), ',') = "1,2"
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param iterable 可迭代对象
	 * @param separator 分隔符
	 * @return 连接后字符串
	 */
	@Nullable
	public static String join(@Nullable final Iterable<?> iterable, @Nonnull final char separator) {
		return INSTANCE.join(iterable, separator);
	}
	
	/**
	 * <pre> 连接元素.
	 * 元素为Null时，连接时使用空字符串。
	 * 
	 * (null, *) = null
	 * (Arrays.asList("1", "2"), null) = "12"
	 * (Arrays.asList("1", "2"), ",") = "1,2"
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param iterable 可迭代对象
	 * @param separator 分隔符，null时使用空字符串
	 * @return 连接后字符串
	 */
	@Nullable
	public static String join(@Nullable final Iterable<?> iterable, @Nullable final String separator) {
		return INSTANCE.join(iterable, separator);
	}
	
	/**
	 * <pre> 直接连接元素.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/30  huangys  Create
	 * </pre>
	 * 
	 * @param iterator 迭代器
	 * @return 连接后字符串
	 */
	public static String join(@Nullable final Iterator<?> iterator) {
		return join(iterator, "");
	}
	
	/**
	 * <pre> 连接元素.
	 * 元素为Null时，连接时使用空字符串。
	 * 
	 * (null, *) = null
	 * (Arrays.asList("1", "2").iterator(), ',') = "1,2"
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param iterator 迭代器
	 * @param separator 分隔符
	 * @return 连接后字符串
	 */
	@Nullable
	public static String join(@Nullable final Iterator<?> iterator, @Nonnull final char separator) {
		return INSTANCE.join(iterator, separator);
	}
	
	/**
	 * <pre> 连接元素.
	 * 元素为Null时，连接时使用空字符串。
	 * 
	 * (null, *) = null
	 * (Arrays.asList("1", "2").iterator(), null) = "12"
	 * (Arrays.asList("1", "2").iterator(), ",") = "1,2"
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param iterator 迭代器
	 * @param separator 分隔符，null时使用空字符串
	 * @return 连接后字符串
	 */
	@Nullable
	public static String join(@Nullable final Iterator<?> iterator, @Nullable final String separator) {
		return INSTANCE.join(iterator, separator);
	}
	
	/**
	 * <pre> 直接连接元素.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/30  huangys  Create
	 * </pre>
	 * 
	 * @param array 数组
	 * @return 连接后字符串
	 */
	public static String join(@Nullable final Object[] array) {
		return INSTANCE.join(array, "");
	}
	
	/**
	 * <pre> 直接连接元素.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/30  huangys  Create
	 * </pre>
	 * 
	 * @param array 数组
	 * @param startIndex 指定开始索引位置
	 * @param endIndex 指定结束索引位置
	 * @return 连接后字符串
	 */
	public static String join(@Nullable final Object[] array, @Nonnull final int startIndex, @Nonnull final int endIndex) {
		return INSTANCE.join(array, "", startIndex, endIndex);
	}
	
	/**
	 * <pre> 连接元素.
	 * 元素为Null时，连接时使用空字符串。
	 * 
	 * (null, *)               = null
	 * ([], *)                 = ""
	 * ([null], *)             = ""
	 * (["a", "b", "c"], ';')  = "a;b;c"
	 * (["a", "b", "c"], null) = "abc"
	 * ([null, "", "a"], ';')  = ";;a"
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param array 数组
	 * @param separator 分隔符
	 * @return 连接后字符串
	 */
	@Nullable
	public static String join(@Nullable final Object[] array, @Nonnull final char separator) {
		return INSTANCE.join(array, separator);
	}
	
	/**
	 * <pre> 连接元素.
	 * 元素为Null时，连接时使用空字符串。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param array 数组
	 * @param separator 分隔符，null时使用空字符串
	 * @param startIndex 指定开始索引位置
	 * @param endIndex 指定结束索引位置
	 * @return 连接后字符串
	 */
	@Nullable
	public static String join(@Nullable final Object[] array, @Nonnull final char separator, @Nonnull final int startIndex, @Nonnull final int endIndex) {
		return INSTANCE.join(array, separator, startIndex, endIndex);
	}
	
	/**
	 * <pre> 连接元素.
	 * 元素为Null时，连接时使用空字符串。
	 * 
	 * (null, *)                = null
	 * ([], *)                  = ""
	 * ([null], *)              = ""
	 * (["a", "b", "c"], "--")  = "a--b--c"
	 * (["a", "b", "c"], null)  = "abc"
	 * (["a", "b", "c"], "")    = "abc"
	 * ([null, "", "a"], ',')   = ",,a"
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param array 数组
	 * @param separator 分隔符，null时使用空字符串
	 * @return 连接后字符串
	 */
	@Nullable
	public static String join(@Nullable final Object[] array, @Nullable final String separator) {
		return INSTANCE.join(array, separator);
	}
	
	/**
	 * <pre> 连接元素.
	 * 元素为Null时，连接时使用空字符串。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param array 数组
	 * @param separator 分隔符，null时使用空字符串
	 * @param startIndex 指定开始索引位置
	 * @param endIndex 指定结束索引位置
	 * @return 连接后字符串
	 */
	@Nullable
	public static String join(@Nullable final Object[] array, @Nonnull final String separator, @Nonnull final int startIndex, @Nonnull final int endIndex) {
		return INSTANCE.join(array, separator, startIndex, endIndex);
	}
	
	/**
	 * <pre> 连接元素.
	 * 元素为Null时，连接时使用空字符串。
	 * 
	 * (null)            = null
	 * ([])              = ""
	 * ([null])          = ""
	 * ("a", "b", "c") = "abc"
	 * (null, "", "a") = "a"
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param elements 多个元素
	 * @param <T> 元素类型
	 * @return 连接后字符串
	 */
	@SafeVarargs
	@Nullable
	public static <T> String joinEach(@Nullable final T... elements) {
		return INSTANCE.join(elements);
	}
	
	/**
	 * <pre> 部分函数实现来源.
	 * 
	 * Modification History:
	 * Date        Author   Version   Action
	 * 2014/08/15  huangys  v1.0      Create
	 * </pre>
	 * 
	 */
	private static final class INSTANCE extends org.apache.commons.lang3.StringUtils { };
	
}
