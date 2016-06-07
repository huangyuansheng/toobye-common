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
 * <pre> 字符串查找.
 * 
 * contains/indexOf/lastIndexOf
 * equals/equalsIgnoreCase/endsWith/startsWith
 * 已在String中定义。
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2014/04/02  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class StringSearch {
	
	private StringSearch() { }
	
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
	 * <pre> 是否包含任意指定字符.
	 * 
	 * (null, *)                = false
	 * ("", *)                  = false
	 * (*, null)                = false
	 * (*, [])                  = false
	 * ("zzabyycdxx",['z','!']) = true
	 * ("zzabyycdxx",['b','y']) = true
	 * ("aba", ['z'])           = false
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param cs 字符串
	 * @param searchChars 指定字符
	 * @return 是否包含任意指定字符
	 */
	@Nonnull
	public static boolean containsAnyChar(final CharSequence cs, @Nullable final char... searchChars) {
		return INSTANCE.containsAny(cs, searchChars);
	}
	
	/**
	 * <pre> 是否包含任意指定字符.
	 * 
	 * (null, *)            = false
	 * ("", *)              = false
	 * (*, null)            = false
	 * (*, "")              = false
	 * ("zzabyycdxx", "z!") = true
	 * ("zzabyycdxx", "by") = true
	 * ("aba","z")          = false
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param cs 字符串
	 * @param searchChars 看作多个指定字符
	 * @return 是否包含任意指定字符
	 */
	@Nonnull
	public static boolean containsAnyChar(@Nullable final CharSequence cs, @Nullable final CharSequence searchChars) {
		return INSTANCE.containsAny(cs, searchChars);
	}
	
	/**
	 * <pre> 是否包含指定字符串（忽视大小写）.
	 * 
	 * (null, *) = false
	 * (*, null) = false
	 * ("", "") = true
	 * ("abc", "") = true
	 * ("abc", "a") = true
	 * ("abc", "z") = false
	 * ("abc", "A") = true
	 * ("abc", "Z") = false
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param cs 字符串
	 * @param searchStr 搜索字符串
	 * @return 是否包含指定字符串
	 */
	@Nonnull
	public static boolean containsIgnoreCase(@Nullable final CharSequence cs, @Nullable final CharSequence searchStr) {
		return INSTANCE.containsIgnoreCase(cs, searchStr);
	}
	
	/**
	 * <pre> 是否为合法字符串，即不包含指定非法字符.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param cs 字符串
	 * @param invalidChars 非法字符集合
	 * @return 是否满足条件
	 */
	@Nonnull
	public static boolean containsNone(@Nullable final CharSequence cs, @Nullable final char... invalidChars) {
		return INSTANCE.containsNone(cs, invalidChars);
	}
	
	/**
	 * <pre> 是否为合法字符串，即不包含指定非法字符.
	 * 
	 * (null, *)       = true
	 * (*, null)       = true
	 * ("", *)         = true
	 * ("ab", "")      = true
	 * ("abab", "xyz") = true
	 * ("ab1", "xyz")  = true
	 * ("abz", "xyz")  = false
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param cs 字符串
	 * @param invalidChars 非法字符集合
	 * @return 是否满足条件
	 */
	@Nonnull
	public static boolean containsNone(@Nullable final CharSequence cs, @Nullable final String invalidChars) {
		return INSTANCE.containsNone(cs, invalidChars);
	}
	
	/**
	 * <pre> 仅包含指定合法字符.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param cs 字符串
	 * @param validChars 合法字符集合
	 * @return 是否满足条件
	 */
	@Nonnull
	public static boolean containsOnly(@Nullable final CharSequence cs, @Nullable final char... validChars) {
		return INSTANCE.containsOnly(cs, validChars);
	}
	
	/**
	 * <pre> 仅包含指定合法字符.
	 * 
	 * (null, *)       = false
	 * (*, null)       = false
	 * ("", *)         = true
	 * ("ab", "")      = false
	 * ("abab", "abc") = true
	 * ("ab1", "abc")  = false
	 * ("abz", "abc")  = false
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param cs 字符串
	 * @param validChars 合法字符集合
	 * @return 是否满足条件
	 */
	@Nonnull
	public static boolean containsOnly(@Nullable final CharSequence cs, @Nullable final String validChars) {
		return INSTANCE.containsOnly(cs, validChars);
	}
	
	/**
	 * <pre> 是否包含空白字符.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param cs 字符串
	 * @return 是否满足条件
	 */
	@Nonnull
	public static boolean containsWhitespace(@Nullable final CharSequence cs) {
		return INSTANCE.containsWhitespace(cs);
	}
	
	/**
	 * <pre> 统计指定字符串总出现次数.
	 * 
	 * (null, *)       = 0
	 * ("", *)         = 0
	 * ("abba", null)  = 0
	 * ("abba", "")    = 0
	 * ("abba", "a")   = 2
	 * ("abba", "ab")  = 1
	 * ("abba", "xxx") = 0
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param sub 指定查找字符串
	 * @return 总计次数
	 */
	@Nonnull
	public static int containsCount(@Nullable final CharSequence str, @Nullable final CharSequence sub) {
		return INSTANCE.countMatches(str, sub);
	}

	/**
	 * <pre> 定位字符串（忽视大小写）.
	 * 
	 * (null, *)          = -1
	 * (*, null)          = -1
	 * ("", "")           = 0
	 * ("aabaabaa", "a")  = 0
	 * ("aabaabaa", "b")  = 2
	 * ("aabaabaa", "ab") = 1
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param searchStr 指定查找字符串
	 * @return 索引位置，未找到返回-1
	 */
	@Nonnull
	public static int indexOfIgnoreCase(@Nullable final CharSequence str, @Nullable final CharSequence searchStr) {
		return INSTANCE.indexOfIgnoreCase(str, searchStr);
	}
	
	/**
	 * <pre> 定位字符串（忽视大小写）.
	 * 
	 * (null, *, *)          = -1
	 * (*, null, *)          = -1
	 * ("", "", 0)           = 0
	 * ("aabaabaa", "A", 0)  = 0
	 * ("aabaabaa", "B", 0)  = 2
	 * ("aabaabaa", "AB", 0) = 1
	 * ("aabaabaa", "B", 3)  = 5
	 * ("aabaabaa", "B", 9)  = -1
	 * ("aabaabaa", "B", -1) = 2
	 * ("aabaabaa", "", 2)   = 2
	 * ("abc", "", 9)        = 3
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param searchStr 指定查找字符串
	 * @param startPos 其实索引位置
	 * @return 索引位置，未找到返回-1
	 */
	@Nonnull
	public static int indexOfIgnoreCase(@Nullable final CharSequence str, @Nullable final CharSequence searchStr, @Nonnull final int startPos) {
		return INSTANCE.indexOfIgnoreCase(str, searchStr, startPos);
	}
	
	/**
	 * <pre> 定位指定任意字符串.
	 * 
	 * (null, *)                = -1
	 * ("", *)                  = -1
	 * (*, null)                = -1
	 * (*, [])                  = -1
	 * ("zzabyycdxx",['z','a']) = 0
	 * ("zzabyycdxx",['b','y']) = 3
	 * ("aba", ['z'])           = -1
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param cs 字符串
	 * @param searchChars 指定字符串集合
	 * @return 索引位置，未找到返回-1
	 */
	@Nonnull
	public static int indexOfAnyChar(@Nullable final CharSequence cs, @Nullable final char... searchChars) {
		return INSTANCE.indexOfAny(cs, searchChars);
	}
	
	/**
	 * <pre> 定位指定任意字符串.
	 * 
	 * (null, *)                     = -1
	 * (*, null)                     = -1
	 * (*, [])                       = -1
	 * ("zzabyycdxx", ["ab","cd"])   = 2
	 * ("zzabyycdxx", ["cd","ab"])   = 2
	 * ("zzabyycdxx", ["mn","op"])   = -1
	 * ("zzabyycdxx", ["zab","aby"]) = 1
	 * ("zzabyycdxx", [""])          = 0
	 * ("", [""])                    = 0
	 * ("", ["a"])                   = -1
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param cs 字符串
	 * @param searchChars 指定字符串集合
	 * @return 索引位置，未找到返回-1
	 */
	@Nonnull
	public static int indexOfAnyString(@Nullable final CharSequence cs, @Nullable final CharSequence... searchChars) {
		return INSTANCE.indexOfAny(cs, searchChars);
	}
	
	/**
	 * <pre> 定位非指定任意字符.
	 * 
	 * (null, *)                              = -1
	 * ("", *)                                = -1
	 * (*, null)                              = -1
	 * (*, [])                                = -1
	 * ("zzabyycdxx", new char[] {'z', 'a'} ) = 3
	 * ("aba", new char[] {'z'} )             = 0
	 * ("aba", new char[] {'a', 'b'} )        = -1
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param cs 字符串
	 * @param searchChars 指定字符集合
	 * @return 索引位置，未找到返回-1
	 */
	@Nonnull
	public static int indexOfAnyCharBut(@Nullable final CharSequence cs, @Nullable final char... searchChars) {
		return INSTANCE.indexOfAnyBut(cs, searchChars);
	}
	
	/**
	 * <pre> 定位非指定任意字符.
	 * 
	 * (null, *)            = -1
	 * ("", *)              = -1
	 * (*, null)            = -1
	 * (*, "")              = -1
	 * ("zzabyycdxx", "za") = 3
	 * ("zzabyycdxx", "")   = -1
	 * ("aba","ab")         = -1
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param cs 字符串
	 * @param searchChars 指定字符集合
	 * @return 索引位置，未找到返回-1
	 */
	@Nonnull
	public static int indexOfAnyCharBut(@Nullable final CharSequence cs, @Nullable final CharSequence searchChars) {
		return INSTANCE.indexOfAnyBut(cs, searchChars);
	}
	
	/**
	 * <pre> 定位指定字符串第N次数出现.
	 * 
	 * (null, *, *)          = -1
	 * (*, null, *)          = -1
	 * ("", "", *)           = 0
	 * ("aabaabaa", "a", 1)  = 0
	 * ("aabaabaa", "a", 2)  = 1
	 * ("aabaabaa", "b", 1)  = 2
	 * ("aabaabaa", "b", 2)  = 5
	 * ("aabaabaa", "ab", 1) = 1
	 * ("aabaabaa", "ab", 2) = 4
	 * ("aabaabaa", "", 1)   = 0
	 * ("aabaabaa", "", 2)   = 0
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param searchStr 指定查找字符串
	 * @param ordinal 指定出现次数
	 * @return 索引位置，未找到返回-1
	 */
	@Nonnull
	public static int indexOfOrdinal(@Nullable final CharSequence str, @Nullable final CharSequence searchStr, @Nonnull final int ordinal) {
		return INSTANCE.ordinalIndexOf(str, searchStr, ordinal);
	}
	
	/**
	 * <pre> 定位字符串（忽视大小写，从末尾反向查找）.
	 * 
	 * (null, *)          = -1
	 * (*, null)          = -1
	 * ("aabaabaa", "A")  = 7
	 * ("aabaabaa", "B")  = 5
	 * ("aabaabaa", "AB") = 4
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param searchStr 查找字符串
	 * @return 索引位置，未找到返回-1
	 */
	@Nonnull
	public static int lastIndexOfIgnoreCase(@Nullable final CharSequence str, @Nullable final CharSequence searchStr) {
		return INSTANCE.lastIndexOfIgnoreCase(str, searchStr);
	}
	
	/**
	 * <pre> 定位字符串（忽视大小写，从末尾反向查找）.
	 * 
	 * (null, *, *)          = -1
	 * (*, null, *)          = -1
	 * ("aabaabaa", "A", 8)  = 7
	 * ("aabaabaa", "B", 8)  = 5
	 * ("aabaabaa", "AB", 8) = 4
	 * ("aabaabaa", "B", 9)  = 5
	 * ("aabaabaa", "B", -1) = -1
	 * ("aabaabaa", "A", 0)  = 0
	 * ("aabaabaa", "B", 0)  = -1
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param searchStr 查找字符串
	 * @param startPos 开始查找的索引位置，因这里是反向查找，所以也就是忽略该索引为位置之后的字符，该索引位置上的字符不包含在内，仍被查找
	 * @return 索引位置，未找到返回-1
	 */
	@Nonnull
	public static int lastIndexOfIgnoreCase(@Nullable final CharSequence str, @Nullable final CharSequence searchStr, @Nonnull final int startPos) {
		return INSTANCE.lastIndexOfIgnoreCase(str, searchStr, startPos);
	}
	
	/**
	 * <pre> 定位指定任意字符串（从末尾反向查找）.
	 * 
	 * (null, *)                   = -1
	 * (*, null)                   = -1
	 * (*, [])                     = -1
	 * (*, [null])                 = -1
	 * ("zzabyycdxx", ["ab","cd"]) = 6
	 * ("zzabyycdxx", ["cd","ab"]) = 6
	 * ("zzabyycdxx", ["mn","op"]) = -1
	 * ("zzabyycdxx", ["mn","op"]) = -1
	 * ("zzabyycdxx", ["mn",""])   = 10
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param searchStrs 指定查找字符串集合
	 * @return 索引位置，未找到返回-1
	 */
	@Nonnull
	public static int lastIndexOfAnyString(@Nullable final CharSequence str, @Nullable final CharSequence... searchStrs) {
		return INSTANCE.lastIndexOfAny(str, searchStrs);
	}
	
	/**
	 * <pre> 定位指定字符串第N次数出现的索引位置（从末尾反向查找）.
	 * 
	 * (null, *, *)          = -1
	 * (*, null, *)          = -1
	 * ("", "", *)           = 0
	 * ("aabaabaa", "a", 1)  = 7
	 * ("aabaabaa", "a", 2)  = 6
	 * ("aabaabaa", "b", 1)  = 5
	 * ("aabaabaa", "b", 2)  = 2
	 * ("aabaabaa", "ab", 1) = 4
	 * ("aabaabaa", "ab", 2) = 1
	 * ("aabaabaa", "", 1)   = 8
	 * ("aabaabaa", "", 2)   = 8
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param searchStr 指定查找字符串
	 * @param ordinal 指定出现次数
	 * @return 索引位置，未找到返回-1
	 */
	@Nonnull
	public static int lastIndexOfOrdinal(@Nullable final CharSequence str, @Nullable final CharSequence searchStr, @Nonnull final int ordinal) {
		return INSTANCE.lastOrdinalIndexOf(str, searchStr, ordinal);
	}
	
	/**
	 * <pre> 字符串是否以指定任意字符结尾.
	 * 
	 * (null, *)         = false
	 * ("", *)           = false
	 * (*, null)         = false
	 * (*, "")           = false
	 * ("abcdef", "FED") = false
	 * ("ABCDEF", "FED") = true
	 * 
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/03  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param searchChars 指定字符集合
	 * @return 是否满足条件
	 */
	@Nonnull
	public static boolean endsWithAnyChar(@Nullable final CharSequence str, @Nullable final CharSequence searchChars) {
        if (StringUtils.isEmpty(str) || StringUtils.isEmpty(searchChars)) {
            return false;
        }
        return charSequenceContains(searchChars, str.charAt(str.length() - 1));
	}
	
	/**
	 * <pre> 字符串是否以指定任意字符串起始.
	 * 
	 * (null, *)         = false
	 * ("", *)           = false
	 * (*, null)         = false
	 * (*, "")           = false
	 * ("abcdef", "CBA") = false
	 * ("ABCDEF", "CBA") = true
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/03  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param searchChars 指定字符集合
	 * @return 是否满足条件
	 */
	@Nonnull
	public static boolean startsWithAnyChar(@Nullable final CharSequence str, @Nullable final CharSequence searchChars) {
        if (StringUtils.isEmpty(str) || StringUtils.isEmpty(searchChars)) {
            return false;
        }
        return charSequenceContains(searchChars, str.charAt(0));
	}
	
	@Nonnull
	private static boolean charSequenceContains(@Nonnull final CharSequence cs, @Nonnull final char c) {
        for (int i = 0; i < cs.length(); i++) {
        	if (c == cs.charAt(i)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * <pre> 字符串是否以指定字符串结尾（忽视大小写）.
	 * 
	 * (null, *)         = false
	 * ("", *)           = false
	 * (*, null)         = false
	 * (*, "")           = false
	 * ("abcdef", "def") = true
	 * ("ABCDEF", "def") = true
	 * ("ABCDEF", "cde") = false
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param searchStr 指定字符串
	 * @return 是否满足条件
	 */
	@Nonnull
	public static boolean endsWithIgnoreCase(@Nullable final CharSequence str, @Nullable final CharSequence searchStr) {
        if (StringUtils.isEmpty(str) || StringUtils.isEmpty(searchStr)) {
			return false;
		}
		return INSTANCE.endsWithIgnoreCase(str, searchStr);
	}
	
	/**
	 * <pre> 字符串是否以指定任意字符串结尾.
	 * 
	 * (null, *)              = false
	 * ("", *)                = false
	 * (*, null)              = false
	 * (*, new String[] {""}) = false
	 * ("abcxyz", new String[] {"xyz"}) = true
	 * ("abcxyz", new String[] {null, "xyz", "abc"}) = true
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param searchStrs 指定字符串集合
	 * @return 是否满足条件
	 */
	@Nonnull
	public static boolean endsWithAnyString(@Nullable final CharSequence str, @Nullable final CharSequence... searchStrs) {
		return INSTANCE.endsWithAny(str, searchStrs);
	}
	
	/**
	 * <pre> 字符串是否以指定字符串起始（忽视大小写）.
	 * 
	 * (null, *)         = false
	 * ("", *)           = false
	 * (*, null)         = false
	 * (*, "")           = false
	 * ("abcdef", "abc") = true
	 * ("ABCDEF", "abc") = true
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param searchStr 指定字符串
	 * @return 是否满足条件
	 */
	@Nonnull
	public static boolean startsWithIgnoreCase(@Nullable final CharSequence str, @Nullable final CharSequence searchStr) {
        if (StringUtils.isEmpty(str) || StringUtils.isEmpty(searchStr)) {
			return false;
		}
		return INSTANCE.startsWithIgnoreCase(str, searchStr);
	}
	
	/**
	 * <pre> 字符串是否以指定任意字符串起始.
	 * 
	 * (null, *)              = false
	 * ("", *)                = false
	 * (*, null)              = false
	 * (*, new String[] {""}) = false
	 * ("abcxyz", new String[] {"abc"}) = true
	 * ("abcxyz", new String[] {null, "xyz", "abc"}) = true
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param searchStrs 指定字符串集合
	 * @return 是否满足条件
	 */
	@Nonnull
	public static boolean startsWithAnyString(@Nullable final CharSequence str, @Nullable final CharSequence... searchStrs) {
		return INSTANCE.startsWithAny(str, searchStrs);
	}
	
	/**
	 * <pre> 字符串是否以指定后缀结尾.
	 * 
	 * (null, *, *)         = false
	 * ("", *, *)           = false
	 * (*, null, *)         = false
	 * (*, "", *)           = false
	 * ("abc", "ab", 1)     = true
	 * ("abc", "ab", 0)     = false
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/15  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param suffix 结尾后缀
	 * @param toffset 偏移，即忽略尾部若干字符，负数则返回false
	 * @return 是否满足条件
	 */
	@Nonnull
    public static boolean endsWith(@Nullable final String str, @Nullable final String suffix, @Nonnull final int toffset) {
        if (StringUtils.isEmpty(str) || StringUtils.isEmpty(suffix)) {
			return false;
		}
		char[] ta = str.toCharArray();
        int len = str.length();
        int to = toffset;
        char[] pa = suffix.toCharArray();
        int po = suffix.length();
        int pc = po;
        if ((toffset < 0) || (toffset > len - pc)) {
            return false;
        }
        while (--pc >= 0) {
            if (ta[len - ++to] != pa[--po]) {
                return false;
            }
        }
        return true;
    }
	
}
