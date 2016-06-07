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
 * <pre> 字符串比较.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2014/04/02  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class StringComparator {
	
	private StringComparator() { }

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
	 * <pre> 获得相同的前缀.
	 * 从首个字符开始比较，当遇到不同时，返回已比较相同的字符。
	 * 
	 * (null) = ""
	 * (new String[] {}) = ""
	 * (new String[] {"abc"}) = "abc"
	 * (new String[] {null, null}) = ""
	 * (new String[] {"", ""}) = ""
	 * (new String[] {"", null}) = ""
	 * (new String[] {"abc", null, null}) = ""
	 * (new String[] {null, null, "abc"}) = ""
	 * (new String[] {"", "abc"}) = ""
	 * (new String[] {"abc", ""}) = ""
	 * (new String[] {"abc", "abc"}) = "abc"
	 * (new String[] {"abc", "a"}) = "a"
	 * (new String[] {"ab", "abxyz"}) = "ab"
	 * (new String[] {"abcde", "abxyz"}) = "ab"
	 * (new String[] {"abcde", "xyz"}) = ""
	 * (new String[] {"xyz", "abcde"}) = ""
	 * (new String[] {"i am a machine", "i am a robot"}) = "i am a "
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param strs 多个字符串
	 * @return 相同前缀
	 */
	@Nullable
	public static String getSamePrefix(@Nullable final String... strs) {
		return INSTANCE.getCommonPrefix(strs);
	}
	
	/**
	 * <pre> 返回字符串不同之处.
	 * 从首个字符开始比较，当遇到不同时，返回第二个字符串的剩余字符。
	 * 
	 * (null, null) = null
 	 * ("", "") = ""
 	 * ("", "abc") = "abc"
 	 * ("abc", "") = ""
 	 * ("abc", "abc") = ""
 	 * ("ab", "abxyz") = "xyz"
 	 * ("abcde", "abxyz") = "xyz"
 	 * ("abcde", "xyz") = "xyz"
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str1 字符串1
	 * @param str2 字符串2
	 * @return 不同之处
	 */
	@Nullable
	public static String getDifference(@Nullable final String str1, @Nullable final String str2) {
		return INSTANCE.difference(str1, str2);
	}
	
	/**
	 * <pre> 定位字符串不同.
	 * 从首个字符开始比较，当遇到不同时，返回该字符所处位置索引。
	 * 若字符串相同，则返回-1。
	 * 
	 * (null, null) = -1
	 * ("", "") = -1
	 * ("", "abc") = 0
	 * ("abc", "") = 0
	 * ("abc", "abc") = -1
	 * ("ab", "abxyz") = 2
	 * ("abcde", "abxyz") = 2
	 * ("abcde", "xyz") = 0
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param cs1 字符串1
	 * @param cs2 字符串2
	 * @return 不同字符的位置索引
	 */
	@Nonnull
	public static int indexOfDifference(@Nullable final CharSequence cs1, @Nullable final CharSequence cs2) {
		return INSTANCE.indexOfDifference(cs1, cs2);
	}
	
	/**
	 * <pre> 定位字符串不同.
	 * 从首个字符开始比较，当遇到不同时，返回该字符所处位置索引。
	 * 若字符串相同，则返回-1。
	 * 
	 * (null) = -1
 	 * (new String[] {}) = -1
 	 * (new String[] {"abc"}) = -1
 	 * (new String[] {null, null}) = -1
 	 * (new String[] {"", ""}) = -1
 	 * (new String[] {"", null}) = 0
 	 * (new String[] {"abc", null, null}) = 0
 	 * (new String[] {null, null, "abc"}) = 0
 	 * (new String[] {"", "abc"}) = 0
 	 * (new String[] {"abc", ""}) = 0
 	 * (new String[] {"abc", "abc"}) = -1
 	 * (new String[] {"abc", "a"}) = 1
 	 * (new String[] {"ab", "abxyz"}) = 2
 	 * (new String[] {"abcde", "abxyz"}) = 2
 	 * (new String[] {"abcde", "xyz"}) = 0
 	 * (new String[] {"xyz", "abcde"}) = 0
 	 * (new String[] {"i am a machine", "i am a robot"}) = 7
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param css 多个字符串
	 * @return 不同字符的位置索引
	 */
	@Nonnull
	public static int indexOfDifference(@Nullable final CharSequence... css) {
		return INSTANCE.indexOfDifference(css);
	}
	
	/**
	 * <pre> 字符串差异度(数值越大差异越大).
	 * 若字符串相同，则返回0。
	 * 
	 * (null, *)             = IllegalArgumentException
	 * (*, null)             = IllegalArgumentException
	 * ("","")               = 0
	 * ("","a")              = 1
	 * ("aaapppp", "")       = 7
	 * ("frog", "fog")       = 1
	 * ("fly", "ant")        = 3
	 * ("elephant", "hippo") = 7
	 * ("hippo", "elephant") = 7
	 * ("hippo", "zzzzzzzz") = 8
	 * ("hello", "hallo")    = 1
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param cs1 字符串1
	 * @param cs2 字符串2
	 * @return 差异度
	 */
	@Nonnull
	public static int getLevenshteinDistance(@Nonnull final CharSequence cs1, @Nonnull final CharSequence cs2) {
		return INSTANCE.getLevenshteinDistance(cs1, cs2);
	}
	
	/**
	 * <pre> 字符串差异度(数值越大差异越大).
	 * 超过阀值停止校验，即返回的差异度不会超过预设阀值。
	 * 
	 * (null, *, *)             = IllegalArgumentException
	 * (*, null, *)             = IllegalArgumentException
	 * (*, *, -1)               = IllegalArgumentException
	 * ("","", 0)               = 0
	 * ("aaapppp", "", 8)       = 7
	 * ("aaapppp", "", 7)       = 7
	 * ("aaapppp", "", 6))      = -1
	 * ("elephant", "hippo", 7) = 7
	 * ("elephant", "hippo", 6) = -1
	 * ("hippo", "elephant", 7) = 7
	 * ("hippo", "elephant", 6) = -1
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param cs1 字符串1
	 * @param cs2 字符串2
	 * @param threshold 最大阀值>=0
	 * @return 差异度
	 */
	@Nonnull
	public static int getLevenshteinDistance(@Nonnull final CharSequence cs1, @Nonnull final CharSequence cs2, final int threshold) {
		return INSTANCE.getLevenshteinDistance(cs1, cs2, threshold);
	}
	
}
