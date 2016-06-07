/*
 * Copyright 2014 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2014/04/02.
 * 
 */
package com.toobye.common.string;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.toobye.common.lang.Checks;

/**
 * <pre> 字符串拆分工具.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2014/04/02  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class StringSplit {
	
	private StringSplit() { }

	/**
	 * <pre> 部分函数实现来源.
	 * 
	 * Modification History:
	 * Date        Author   Version   Action
	 * 2014/08/21  huangys  v1.0      Create
	 * </pre>
	 * 
	 */
	private static final class INSTANCE extends org.apache.commons.lang3.StringUtils { };

	/**
	 * <pre> 以空白字符分隔（视多个连续空白字符为单一分隔符，无空串元素）.
	 * Whitespace is defined by {@link Character#isWhitespace(char)}.
	 * 
	 * (null)       = null
	 * ("")         = []
	 * ("abc def")  = ["abc", "def"]
	 * ("abc  def") = ["abc", "def"]
	 * (" abc ")    = ["abc"]
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/03  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @return 拆分后的字符串数组
	 */
	@Nullable
	public static String[] splitWhitespace(@Nullable final String str) {
		return INSTANCE.split(str);
	}
	
	/**
	 * <pre> 以空白字符分隔（视多个连续空白字符为单一分隔符，无空串元素）.
	 * Whitespace is defined by {@link Character#isWhitespace(char)}.
	 * 
	 * (null, *, *)            = null
	 * ("", *, *)              = []
	 * ("ab de fg", 0)   = ["ab", "cd", "ef"]
	 * ("ab   de fg", 0) = ["ab", "cd", "ef"]
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/03  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param max 返回数组的最大元素个数（非正数忽略，即元素个数不受限）
	 * @return 拆分后的字符串数组
	 */
	@Nullable
	public static String[] splitWhitespace(@Nullable final String str, @Nonnull final int max) {
		return INSTANCE.split(str, null, max);
	}
	
	/**
	 * <pre> 以指定字符分隔（视多个连续分隔字符为单一分隔符，无空串元素）.
	 * 
	 * (null, *)         = null
	 * ("", *)           = []
	 * ("a.b.c", '.')    = ["a", "b", "c"]
	 * ("a..b.c", '.')   = ["a", "b", "c"]
	 * ("a:b:c", '.')    = ["a:b:c"]
	 * ("a b c", ' ')    = ["a", "b", "c"]
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/03  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param separatorChar 分隔符
	 * @return 拆分后的字符串数组
	 */
	@Nullable
	public static String[] splitChar(@Nullable final String str, @Nonnull final char separatorChar) {
		return INSTANCE.split(str, separatorChar);
	}
	
	/**
	 * <pre> 以指定字符分隔（视多个连续分隔字符为单一分隔符，无空串元素）.
	 * 
	 * (null, *, *)            = null
	 * ("", *, *)              = []
	 * ("ab:cd:ef", ':', 0)    = ["ab", "cd", "ef"]
	 * ("ab:cd:ef", ':', 2)    = ["ab", "cd:ef"]
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/03  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param separatorChar 分隔符
	 * @param max 返回数组的最大元素个数（非正数忽略，即元素个数不受限）
	 * @return 拆分后的字符串数组
	 */
	@Nullable
	public static String[] splitChar(@Nullable final String str, @Nonnull final char separatorChar, @Nonnull final int max) {
		return INSTANCE.split(str, separatorChar + "", max);
	}
	
	/**
	 * <pre> 以指定任意字符分隔（视多个连续分隔字符为单一分隔符，无空串元素）.
	 * 
	 * (null, *)         = null
	 * ("", *)           = []
	 * ("abc def", null) = ["abc", "def"]
	 * ("abc def", " ")  = ["abc", "def"]
	 * ("abc  def", " ") = ["abc", "def"]
	 * ("ab:cd;ef", ":;") = ["ab", "cd", "ef"]
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/03  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param separatorChars 分割字符集合，null则使用空白字符作为分隔符
	 * @return 拆分后的字符串数组
	 */
	@Nullable
	public static String[] splitAnyChar(@Nullable final String str, @Nullable final String separatorChars) {
		return INSTANCE.split(str, separatorChars);
	}

	/**
	 * <pre> 以指定任意字符分隔（视多个连续分隔字符为单一分隔符，无空串元素）.
	 * 
	 * (null, *, *)         = null
	 * ("", *, *)           = []
	 * ("abc def", null, 0) = ["abc", "def"]
	 * ("abc def", " ", 0)  = ["abc", "def"]
	 * ("abc  def", " ", 0) = ["abc", "def"]
	 * ("ab:cd;ef", ":;", 2) = ["ab", "cd;ef"]
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/03  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param separatorChars 分割字符集合，null则使用空白字符作为分隔符
	 * @param max 返回数组的最大元素个数（非正数忽略，即元素个数不受限）
	 * @return 拆分后的字符串数组
	 */
	@Nullable
	public static String[] splitAnyChar(@Nullable final String str, @Nullable final String separatorChars, @Nonnull final int max) {
		return INSTANCE.split(str, separatorChars, max);
	}
	
	/**
	 * <pre> 以字符串分隔（视多个连续分隔字符为单一分隔符，无空串元素）.
	 * 
	 * (null, *)               = null
	 * ("", *)                 = []
	 * ("ab de fg", null)      = ["ab", "de", "fg"]
	 * ("ab   de fg", null)    = ["ab", "de", "fg"]
	 * ("ab:cd:ef", ":")       = ["ab", "cd", "ef"]
	 * ("ab-!-cd-!-ef", "-!-") = ["ab", "cd", "ef"]
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/03  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param separator 分隔符，null则使用空白字符作为分隔符
	 * @return 拆分后的字符串数组
	 */
	@Nullable
	public static String[] splitString(@Nullable final String str, @Nullable final String separator) {
		return INSTANCE.splitByWholeSeparator(str, separator);
	}
	
	/**
	 * <pre> 以字符串分隔（视多个连续分隔字符为单一分隔符，无空串元素）.
	 * 
	 * (null, *, *)               = null
	 * ("", *, *)                 = []
	 * ("ab de fg", null, 0)      = ["ab", "de", "fg"]
	 * ("ab   de fg", null, 0)    = ["ab", "de", "fg"]
	 * ("ab:cd:ef", ":", 2)       = ["ab", "cd:ef"]
	 * ("ab-!-cd-!-ef", "-!-", 5) = ["ab", "cd", "ef"]
	 * ("ab-!-cd-!-ef", "-!-", 2) = ["ab", "cd-!-ef"]
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/03  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param separator 分隔符，null则使用空白字符作为分隔符
	 * @param max 返回数组的最大元素个数（非正数忽略，即元素个数不受限）
	 * @return 拆分后的字符串数组
	 */
	@Nullable
	public static String[] splitString(@Nullable final String str, @Nullable final String separator, @Nonnull final int max) {
		return INSTANCE.splitByWholeSeparator(str, separator, max);
	}

	/**
	 * <pre> 以空白字符分隔（视多个连续分隔字符为多个空串元素相连，保留空串元素）.
	 * Whitespace is defined by {@link Character#isWhitespace(char)}.
	 * 
	 * (null)       = null
 	 * ("")         = []
 	 * ("abc def")  = ["abc", "def"]
 	 * ("abc  def") = ["abc", "", "def"]
 	 * (" abc ")    = ["", "abc", ""]
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/03  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @return 拆分后的字符串数组
	 */
	@Nullable
	public static String[] splitWhitespacePreserveAllTokens(@Nullable final String str) {
		return INSTANCE.splitPreserveAllTokens(str);
	}
	
	/**
	 * <pre> 以空白字符分隔（视多个连续分隔字符为多个空串元素相连，保留空串元素）.
	 * Whitespace is defined by {@link Character#isWhitespace(char)}.
	 * 
	 * (null, *, *)            = null
 	 * ("", *, *)              = []
 	 * ("ab de fg", null, 0)   = ["ab", "cd", "ef"]
 	 * ("ab   de fg", null, 0) = ["ab", "cd", "ef"]
 	 * ("ab:cd:ef", ":", 0)    = ["ab", "cd", "ef"]
 	 * ("ab:cd:ef", ":", 2)    = ["ab", "cd:ef"]
 	 * ("ab   de fg", null, 2) = ["ab", "  de fg"]
 	 * ("ab   de fg", null, 3) = ["ab", "", " de fg"]
 	 * ("ab   de fg", null, 4) = ["ab", "", "", "de fg"]
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/03  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param max  返回数组的最大元素个数（非正数忽略，即元素个数不受限）
	 * @return 拆分后的字符串数组
	 */
	@Nullable
	public static String[] splitWhitespacePreserveAllTokens(@Nullable final String str, @Nonnull final int max) {
		return INSTANCE.splitPreserveAllTokens(str, null, max);
	}
	
	/**
	 * <pre> 以指定字符分隔（视多个连续分隔字符为多个空串元素相连，保留空串元素）.
	 * 
	 * (null, *)         = null
	 * ("", *)           = []
	 * ("a.b.c", '.')    = ["a", "b", "c"]
	 * ("a..b.c", '.')   = ["a", "", "b", "c"]
	 * ("a:b:c", '.')    = ["a:b:c"]
	 * ("a\tb\nc", null) = ["a", "b", "c"]
	 * ("a b c", ' ')    = ["a", "b", "c"]
	 * ("a b c ", ' ')   = ["a", "b", "c", ""]
	 * ("a b c  ", ' ')   = ["a", "b", "c", "", ""]
	 * (" a b c", ' ')   = ["", a", "b", "c"]
	 * ("  a b c", ' ')  = ["", "", a", "b", "c"]
	 * (" a b c ", ' ')  = ["", a", "b", "c", ""]
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/03  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param separatorChar 分割字符
	 * @return 拆分后的字符串数组
	 */
	@Nullable
	public static String[] splitCharPreserveAllTokens(@Nullable final String str, @Nonnull final char separatorChar) {
		return INSTANCE.splitPreserveAllTokens(str, separatorChar);
	}
	
	/**
	 * <pre> 以指定字符分隔（视多个连续分隔字符为多个空串元素相连，保留空串元素）.
	 * 
	 * (null, *, *)            = null
	 * ("", *, *)              = []
	 * ("ab:cd:ef", ':', 0)    = ["ab", "cd", "ef"]
	 * ("ab:cd:ef", ':', 2)    = ["ab", "cd:ef"]
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/03  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param separatorChar 分割字符
	 * @param max  返回数组的最大元素个数（非正数忽略，即元素个数不受限）
	 * @return 拆分后的字符串数组
	 */
	@Nullable
	public static String[] splitCharPreserveAllTokens(@Nullable final String str, @Nonnull final char separatorChar, @Nonnull final int max) {
		return INSTANCE.splitPreserveAllTokens(str, separatorChar + "", max);
	}
	
	/**
	 * <pre> 以指定任意字符分隔（视多个连续分隔字符为多个空串元素相连，保留空串元素）.
	 * 
	 * (null, *)           = null
	 * ("", *)             = []
	 * ("abc def", null)   = ["abc", "def"]
	 * ("abc def", " ")    = ["abc", "def"]
	 * ("abc  def", " ")   = ["abc", "", def"]
	 * ("ab:cd:ef", ":")   = ["ab", "cd", "ef"]
	 * ("ab:cd:ef:", ":")  = ["ab", "cd", "ef", ""]
	 * ("ab:cd:ef::", ":") = ["ab", "cd", "ef", "", ""]
	 * ("ab::cd:ef", ":")  = ["ab", "", cd", "ef"]
	 * (":cd;ef", ":;")     = ["", cd", "ef"]
	 * ("::cd;ef", ":;")    = ["", "", cd", "ef"]
	 * (":cd;ef:", ":;")    = ["", cd", "ef", ""]
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/03  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param separatorChars 分割字符集合，null则使用空白字符作为分隔符
	 * @return 拆分后的字符串数组
	 */
	@Nullable
	public static String[] splitAnyCharPreserveAllTokens(@Nullable final String str, @Nullable final String separatorChars) {
		return INSTANCE.splitPreserveAllTokens(str, separatorChars);
	}
	
	/**
	 * <pre> 以指定任意字符分隔（视多个连续分隔字符为多个空串元素相连，保留空串元素）.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/03  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param separatorChars 分割字符集合，null则使用空白字符作为分隔符
	 * @param max  返回数组的最大元素个数（非正数忽略，即元素个数不受限）
	 * @return 拆分后的字符串数组
	 */
	@Nullable
	public static String[] splitAnyCharPreserveAllTokens(@Nullable final String str, @Nullable final String separatorChars, @Nonnull final int max) {
		return INSTANCE.splitPreserveAllTokens(str, separatorChars, max);
	}
	
	/**
	 * <pre> 以字符串分隔（视多个连续分隔字符为多个空串元素相连，保留空串元素）.
	 * 
	 * (null, *)               = null
	 * ("", *)                 = []
	 * ("ab de fg", null)      = ["ab", "de", "fg"]
	 * ("ab   de fg", null)    = ["ab", "", "", "de", "fg"]
	 * ("ab:cd:ef", ":")       = ["ab", "cd", "ef"]
	 * ("ab-!-cd-!-ef", "-!-") = ["ab", "cd", "ef"]
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/03  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param  separator 分割符，null则使用空白字符作为分隔符
	 * @return 拆分后的字符串数组
	 */
	@Nullable
	public static String[] splitStringPreserveAllTokens(@Nullable final String str, @Nullable final String separator) {
		return INSTANCE.splitByWholeSeparatorPreserveAllTokens(str, separator);
	}
	
	/**
	 * <pre> 以字符串分隔（视多个连续分隔字符为多个空串元素相连，保留空串元素）.
	 * 
	 * (null, *, *)               = null
	 * ("", *, *)                 = []
	 * ("ab de fg", null, 0)      = ["ab", "de", "fg"]
	 * ("ab   de fg", null, 0)    = ["ab", "", "", "de", "fg"]
	 * ("ab:cd:ef", ":", 2)       = ["ab", "cd:ef"]
	 * ("ab-!-cd-!-ef", "-!-", 5) = ["ab", "cd", "ef"]
	 * ("ab-!-cd-!-ef", "-!-", 2) = ["ab", "cd-!-ef"]
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/03  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param  separator 分割符，null则使用空白字符作为分隔符
	 * @param max  返回数组的最大元素个数（非正数忽略，即元素个数不受限）
	 * @return 拆分后的字符串数组
	 */
	@Nullable
	public static String[] splitStringPreserveAllTokens(@Nullable final String str, @Nullable final String separator, @Nonnull final int max) {
		return INSTANCE.splitByWholeSeparatorPreserveAllTokens(str, separator, max);
	}
	
	/**
	 * <pre> 以字符类型自动分隔字符串.
	 * 
	 * (null)         = null
	 * ("")           = []
	 * ("ab de fg")   = ["ab", " ", "de", " ", "fg"]
	 * ("ab   de fg") = ["ab", "   ", "de", " ", "fg"]
	 * ("ab:cd:ef")   = ["ab", ":", "cd", ":", "ef"]
	 * ("number5")    = ["number", "5"]
	 * ("fooBar")     = ["foo", "B", "ar"]
	 * ("foo200Bar")  = ["foo", "200", "B", "ar"]
	 * ("ASFRules")   = ["ASFR", "ules"]
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/03  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @return 拆分后的字符串数组
	 */
	@Nullable
	public static String[] splitByCharacterType(@Nullable final String str) {
		return INSTANCE.splitByCharacterType(str);
	}
	
	/**
	 * <pre> 以字符类型自动分隔字符串（驼峰）.
	 * 
	 * (null)         = null
	 * ("")           = []
	 * ("ab de fg")   = ["ab", " ", "de", " ", "fg"]
	 * ("ab   de fg") = ["ab", "   ", "de", " ", "fg"]
	 * ("ab:cd:ef")   = ["ab", ":", "cd", ":", "ef"]
	 * ("number5")    = ["number", "5"]
	 * ("fooBar")     = ["foo", "Bar"]
	 * ("foo200Bar")  = ["foo", "200", "Bar"]
	 * ("ASFRules")   = ["ASF", "Rules"]
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/03  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @return 拆分后的字符串数组
	 */
	@Nullable
	public static String[] splitByCharacterTypeCamelCase(@Nullable final String str) {
		return INSTANCE.splitByCharacterTypeCamelCase(str);
	}
	
	/**
	 * <pre> 首字母大写拼接（驼峰）.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/03  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @return 快速缩写
	 */
	@Nullable
	public static String getHeadSplitByCharacterTypeCamelCase(@Nullable final String str) {
		if (str == null) {
			return null;
		}
		StringBuffer buf = new StringBuffer();
		for (String one : splitByCharacterTypeCamelCase(str)) {
			buf.append(one.substring(0, 1).toUpperCase());
		}
		return buf.toString().toUpperCase();
	}
	
	/**
	 * <pre> 以指定长度拆分字符串.
	 * 
	 * (null, *)         = null
	 * ("", *)           = []
	 * ("abc def", 2) = ["ab", "c ", "de", "f"]
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/03  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param len 长度
	 * @return 拆分后的字符串数组
	 */
	@Nullable
	public static String[] splitAssignedLength(@Nullable final String str, @Nullable final int len) {
		if (str == null) {
			return null;
		}
		Checks.nonPositiveThrow(len);
		int size = (int) Math.ceil(1.0 * str.length() / len);
		String[] ret = new String[size];
		for (int i = 0; i < size - 1; i++) {
			ret[i] = str.substring(i * len, i * len + len);
		}
		ret[size - 1] = str.substring(len * size - len);
		return ret;
	}
	
}
