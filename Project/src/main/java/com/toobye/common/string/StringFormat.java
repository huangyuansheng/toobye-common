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

/**
 * <pre> 字符串格式化.
 * 
 * UpperCase/LowerCase已在String中定义。
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2014/04/02  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class StringFormat {
	
	private StringFormat() { }
	
	/**
	 * <pre> 部分函数实现来源.
	 * 
	 * Modification History:
	 * Date        Author   Version   Action
	 * 2014/08/14  huangys  v1.0      Create
	 * </pre>
	 * 
	 */
	private static final class INSTANCE extends org.apache.commons.lang3.StringUtils { };

	/**
	 * <pre> 左填充.
	 * 在字符串左侧填充空格至指定长度。
	 * 
	 * (null, *)   = null
 	 * ("", 3)     = "   "
 	 * ("bat", 3)  = "bat"
 	 * ("bat", 5)  = "  bat"
 	 * ("bat", 1)  = "bat"
 	 * ("bat", -1) = "bat"

	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param size 长度
	 * @return 填充后字符串
	 */
	@Nullable
	public static String leftPad(@Nullable final String str, @Nonnull final int size) {
		return INSTANCE.leftPad(str, size);
	}
	
	/**
	 * <pre> 左填充.
	 * 在字符串左侧填充指定字符至指定长度。
	 * 
	 * (null, *, *)     = null
 	 * ("", 3, 'z')     = "zzz"
 	 * ("bat", 3, 'z')  = "bat"
 	 * ("bat", 5, 'z')  = "zzbat"
 	 * ("bat", 1, 'z')  = "bat"
 	 * ("bat", -1, 'z') = "bat"
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param size 长度
	 * @param padChar 填充字符
	 * @return 填充后字符串
	 */
	@Nonnull
	public static String leftPad(@Nullable final String str, @Nonnull final int size, @Nonnull final char padChar) {
		return INSTANCE.leftPad(str, size, padChar);
	}
	
	/**
	 * <pre> 左填充.
	 * 在字符串左侧填充指定字符串至指定长度。
	 * 
	 * (null, *, *)      = null
 	 * ("", 3, "z")      = "zzz"
 	 * ("bat", 3, "yz")  = "bat"
 	 * ("bat", 5, "yz")  = "yzbat"
 	 * ("bat", 8, "yz")  = "yzyzybat"
 	 * ("bat", 1, "yz")  = "bat"
 	 * ("bat", -1, "yz") = "bat"
 	 * ("bat", 5, null)  = "  bat"
 	 * ("bat", 5, "")    = "  bat"
 	 *
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param size 长度
	 * @param padStr 填充字符串，默认用空格
	 * @return 填充后字符串
	 */
	@Nullable
	public static String leftPad(@Nullable final String str, @Nonnull final int size, @Nullable final String padStr) {
		return INSTANCE.leftPad(str, size, padStr);
	}
	
	/**
	 * <pre> 左右填充， 字符串本身居中.
	 * 在字符串两侧填充空格至指定长度。
	 * 
	 * (null, *)   = null
 	 * ("", 4)     = "    "
 	 * ("ab", -1)  = "ab"
 	 * ("ab", 4)   = " ab "
 	 * ("abcd", 2) = "abcd"
 	 * ("a", 4)    = " a  "
 	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param size 长度
	 * @return 填充后字符串
	 */
	@Nullable
	public static String centerPad(@Nullable final String str, @Nonnull final int size) {
		return INSTANCE.center(str, size);
	}
	
	/**
	 * <pre> 左右填充， 字符串本身居中.
	 * 在字符串两侧填充指定字符至指定长度。
	 * 
	 * (null, *, *)     = null
 	 * ("", 4, ' ')     = "    "
 	 * ("ab", -1, ' ')  = "ab"
 	 * ("ab", 4, ' ')   = " ab"
 	 * ("abcd", 2, ' ') = "abcd"
 	 * ("a", 4, ' ')    = " a  "
 	 * ("a", 4, 'y')    = "yayy"
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param size 长度
	 * @param padChar 填充字符
	 * @return 填充后字符串
	 */
	@Nullable
	public static String centerPad(@Nullable final String str, @Nonnull final int size, @Nonnull final char padChar) {
		return INSTANCE.center(str, size, padChar);
	}
	
	/**
	 * <pre> 左右填充， 字符串本身居中.
	 * 在字符串两侧填充指定字符串至指定长度。
	 * 
	 * (null, *, *)     = null
 	 * ("", 4, " ")     = "    "
 	 * ("ab", -1, " ")  = "ab"
 	 * ("ab", 4, " ")   = " ab"
 	 * ("abcd", 2, " ") = "abcd"
 	 * ("a", 4, " ")    = " a  "
 	 * ("a", 4, "yz")   = "yayz"
 	 * ("abc", 7, null) = "  abc  "
 	 * ("abc", 7, "")   = "  abc  "
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param size 长度
	 * @param padStr 填充字符串，默认用空格
	 * @return 填充后字符串
	 */
	@Nullable
	public static String centerPad(@Nullable final String str, @Nonnull final int size, @Nullable final String padStr) {
		return INSTANCE.center(str, size, padStr);
	}
	
	/**
	 * <pre> 右填充.
	 * 在字符串右侧填充空格至指定长度。
	 * 
 	 * (null, *)   = null
 	 * ("", 3)     = "   "
 	 * ("bat", 3)  = "bat"
 	 * ("bat", 5)  = "bat  "
 	 * ("bat", 1)  = "bat"
 	 * ("bat", -1) = "bat"
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param size 长度
	 * @return 填充后字符串
	 */
	@Nullable
	public static String rightPad(@Nullable final String str, @Nonnull final int size) {
		return INSTANCE.rightPad(str, size);
	}
	
	/**
	 * <pre> 右填充.
	 * 在字符串右侧填充指定字符至指定长度。
	 * 
	 * (null, *, *)     = null
	 * ("", 3, 'z')     = "zzz"
	 * ("bat", 3, 'z')  = "bat"
	 * ("bat", 5, 'z')  = "batzz"
	 * ("bat", 1, 'z')  = "bat"
	 * ("bat", -1, 'z') = "bat"
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param size 长度
	 * @param padChar 填充字符
	 * @return 填充后字符串
	 */
	@Nullable
	public static String rightPad(@Nullable final String str, @Nonnull final int size, @Nonnull final char padChar) {
		return INSTANCE.rightPad(str, size, padChar);
	}
	
	/**
	 * <pre> 右填充.
	 * 在字符串右侧填充指定字符串至指定长度。
	 * 
	 * (null, *, *)      = null
	 * ("", 3, "z")      = "zzz"
	 * ("bat", 3, "yz")  = "bat"
	 * ("bat", 5, "yz")  = "batyz"
	 * ("bat", 8, "yz")  = "batyzyzy"
	 * ("bat", 1, "yz")  = "bat"
	 * ("bat", -1, "yz") = "bat"
	 * ("bat", 5, null)  = "bat  "
	 * ("bat", 5, "")    = "bat  "
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param size 长度
	 * @param padStr 填充字符串，默认用空格
	 * @return 填充后字符串
	 */
	@Nullable
	public static String rightPad(@Nullable final String str, @Nonnull final int size, @Nullable final String padStr) {
		return INSTANCE.rightPad(str, size, padStr);
	}
	
	/**
	 * <pre> 字符串所有字符重复叠加.
	 * 
	 * ('ex', 0)  = ""
	 * ('ex', 3)  = "eeexxx"
	 * ('ex', -2) = ""
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符
	 * @param repeat 重复次数
	 * @return 字符串
	 */
	@Nonnull
	public static String repeatEvery(@Nonnull final String str, @Nonnull final int repeat) {
		if (repeat <= 0) {
			return "";
		}
		if (StringUtils.isEmpty(str)) {
			return str;
		}
		StringBuffer sb = new StringBuffer();
		for (char c : str.toCharArray()) {
			sb.append(repeat(c, repeat));
		}
		return sb.toString();
	}
	
	/**
	 * <pre> 字符串重复叠加.
	 * 
	 * ('e', 0)  = ""
	 * ('e', 3)  = "eee"
	 * ('e', -2) = ""
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param ch 字符
	 * @param repeat 重复次数
	 * @return 字符串
	 */
	@Nonnull
	public static String repeat(@Nonnull final char ch, @Nonnull final int repeat) {
		return INSTANCE.repeat(ch, repeat);
	}
	
	/**
	 * <pre> 字符串重复叠加.
	 * 
	 * (null, 2) = null
	 * ("", 0)   = ""
	 * ("", 2)   = ""
	 * ("a", 3)  = "aaa"
	 * ("ab", 2) = "abab"
	 * ("a", -2) = ""
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param repeat 重复次数
	 * @return 字符串
	 */
	@Nullable
	public static String repeat(@Nullable final String str, @Nonnull final int repeat) {
		return INSTANCE.repeat(str, repeat);
	}
	
	/**
	 * <pre> 字符串重复叠加.
	 * 
	 * (null, null, 2) = null
	 * (null, "x", 2)  = null
	 * ("", null, 0)   = ""
	 * ("1", null, 3)   = "111"
	 * ("", "", 2)     = ""
	 * ("", "x", 3)    = "xxx"
	 * ("?", ", ", 3)  = "?, ?, ?"
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param separator 叠加时用的分隔符，默认无分隔符
	 * @param repeat 重复次数
	 * @return 字符串
	 */
	@Nullable
	public static String repeat(@Nullable final String str, @Nullable final String separator, @Nonnull final int repeat) {
		return INSTANCE.repeat(str, separator, repeat);
	}
	
	/**
	 * <pre> 缩写（尾部字符将被隐藏，用"..."代替）.
	 * 
	 * (null, *)      = null
	 * ("", 4)        = ""
	 * ("abcdefg", 6) = "abc..."
	 * ("abcdefg", 7) = "abcdefg"
	 * ("abcdefg", 8) = "abcdefg"
	 * ("abcdefg", 4) = "a..."
	 * ("abcdefg", 3) = IllegalArgumentException
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param maxWidth 长度>=4
	 * @return 缩写
	 */
	@Nullable
	public static String abbreviate(@Nullable final String str, @Nonnull final int maxWidth) {
		return INSTANCE.abbreviate(str, maxWidth);
	}
	
	/**
	 * <pre> 缩写.
	 * 具体规则有点复杂，请查看源码。
	 * 
	 * (null, *, *)                = null
	 * ("", 0, 4)                  = ""
	 * ("abcdefghijklmno", -1, 10) = "abcdefg..."
	 * ("abcdefghijklmno", 0, 10)  = "abcdefg..."
	 * ("abcdefghijklmno", 1, 10)  = "abcdefg..."
	 * ("abcdefghijklmno", 4, 10)  = "abcdefg..."
	 * ("abcdefghijklmno", 5, 10)  = "...fghi..."
	 * ("abcdefghijklmno", 6, 10)  = "...ghij..."
	 * ("abcdefghijklmno", 8, 10)  = "...ijklmno"
	 * ("abcdefghijklmno", 10, 10) = "...ijklmno"
	 * ("abcdefghijklmno", 12, 10) = "...ijklmno"
	 * ("abcdefghij", 0, 3)        = IllegalArgumentException
	 * ("abcdefghij", 5, 6)        = IllegalArgumentException
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param offset 左偏移
	 * @param maxWidth 长度>=4
	 * @return 缩写
	 */
	@Nullable
	public static String abbreviate(@Nullable final String str, @Nonnull final int offset, @Nonnull final int maxWidth) {
		return INSTANCE.abbreviate(str, offset, maxWidth);
	}
	
	/**
	 * <pre> 缩写（中间字符被简单隐藏）.
	 * 
	 * (null, null, 0)      = null
 	 * ("abc", null, 0)      = "abc"
 	 * ("abc", ".", 0)      = "abc"
 	 * ("abc", ".", 3)      = "abc"
 	 * ("abcdef", ".", 4)     = "ab.f"
 	 * ("abcdef", null, 4)     = "ab.f"
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param middle 中间的替换字符，默认为"."
	 * @param length 缩写长度
	 * @return 缩写
	 */
	@Nullable
	public static String abbreviateMiddle(@Nullable final String str, @Nullable final String middle, @Nonnull final int length) {
		return INSTANCE.abbreviateMiddle(str, middle == null ? "." : middle, length);
	}
	
	/**
	 * <pre> 以驼峰和字符类型来缩写字符串.
	 * 以字符类型自动分隔字符串（驼峰）。
	 * 
	 * null -> null
	 * "" -> ""
	 * ohMyGod	-> OMG
	 * oh234my345god -> O2M3G
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/03  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @return 缩写（全部大写）
	 */
	@Nullable
	public static String abbreviateByCharacterTypeCamelCase(@Nullable final String str) {
		if (StringUtils.isBlank(str)) {
			return null;
		}
		StringBuffer buf = new StringBuffer();
		for (String word : StringSplit.splitByCharacterTypeCamelCase(str)) {
			buf.append(word.charAt(0));
		}
		return buf.toString().toUpperCase();
	}
	
	/**
	 * <pre> 大写首字母.
	 * 
	 * (null)  = null
 	 * ("")    = ""
 	 * ("cat") = "Cat"
 	 * ("cAt") = "CAt"
 	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @return 大写首字母后的字符串
	 */
	@Nullable
	public static String capitalize(@Nullable final String str) {
		return INSTANCE.capitalize(str);
	}
	
	/**
	 * <pre> 除首单词外，其他单词首字母大写，即驼峰型字符串.
	 * (null)  = null
	 * ("")    = ""
	 * ("ca_ba", "_") = "caBa"
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/04/21  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param separator 分隔符
	 * @return 驼峰型字符串
	 */
	@Nullable
	public static String capitalizeCamel(@Nonnull final String str, @Nonnull final String separator) {
		if (StringUtils.isBlank(str)) {
			return str;
		}
		String[] arr = str.split("_");
		arr[0] = arr[0].toLowerCase();
		for (int i = 1; i < arr.length; i++) {
			arr[i] = capitalize(arr[i].toLowerCase());
		}
		return StringArray.join(arr);
	}
	
	/**
	 * <pre> 所有单词首字母大写.
	 * (null)  = null
	 * ("")    = ""
	 * ("ca_ba", "_") = "CaBa"
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/04/21  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param separator 分隔符
	 * @return 字符串
	 */
	@Nullable
	public static String capitalizeWords(@Nonnull final String str, @Nonnull final String separator) {
		if (StringUtils.isBlank(str)) {
			return str;
		}
		String[] arr = str.split("_");
		for (int i = 0; i < arr.length; i++) {
			arr[i] = capitalize(arr[i].toLowerCase());
		}
		return StringArray.join(arr);
	}
	
	/**
	 * <pre> 小写首字母.
	 * 
	 * (null)  = null
 	 * ("")    = ""
	 * ("Cat") = "cat"
	 * ("CAT") = "cAT"
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @return 小写首字母后的字符串
	 */
	@Nullable
	public static String uncapitalize(@Nullable final String str) {
		return INSTANCE.uncapitalize(str);
	}
	
	/**
	 * <pre> 反转大小写.
	 * 
	 * (null)  = null
 	 * ("")    = ""
	 * ("The dog has a BONE") = "tHE DOG HAS A bone"
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @return 反转大小后的字符串
	 */
	@Nullable
	public static String swapCase(@Nullable final String str) {
		return INSTANCE.swapCase(str);
	}
	
	/**
	 * <pre> 标准化空白字符.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @return 标准化空白字符后的字符串
	 */
	@Nullable
	public static String normalizeSpace(@Nullable final String str) {
		return INSTANCE.normalizeSpace(str);
	}
	
}
