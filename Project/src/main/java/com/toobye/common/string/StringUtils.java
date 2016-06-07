/*
 * Copyright 2014 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2014/04/02.
 * 
 */
package com.toobye.common.string;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.toobye.common.collection.Maps;
import com.toobye.common.lang.Objects;
import com.toobye.common.lang.Pair;

/**
 * <pre> 字符串基本工具类.
 * 
 * substring已在String中定义。
 * toString废弃。
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2014/04/02  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class StringUtils {
	
	private StringUtils() { }

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
	
	/**
	 * <pre> 左截取.
	 * 
	 * (null, *)    = null
	 * (*, -ve)     = ""
	 * ("", *)      = ""
	 * ("abc", 0)   = ""
	 * ("abc", 2)   = "ab"
	 * ("abc", 4)   = "abc"
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param len 长度
	 * @return 截取内容
	 */
	@Nullable
	public static String left(@Nullable final String str, @Nonnull final int len) {
		return INSTANCE.left(str, len);
	}
	
	/**
	 * <pre> 右截取.
	 * 
	 * (null, *)    = null
	 * (*, -ve)     = ""
	 * ("", *)      = ""
	 * ("abc", 0)   = ""
	 * ("abc", 2)   = "bc"
	 * ("abc", 4)   = "abc"
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param len 长度
	 * @return 截取内容
	 */
	@Nullable
	public static String right(@Nullable final String str, @Nonnull final int len) {
		return INSTANCE.right(str, len);
	}
	
	/**
	 * <pre> 中间截取.
	 * 
	 * (null, *, *)    = null
	 * (*, *, -ve)     = ""
	 * ("", 0, *)      = ""
	 * ("abc", 0, 2)   = "ab"
	 * ("abc", 0, 4)   = "abc"
	 * ("abc", 2, 4)   = "c"
	 * ("abc", 4, 2)   = ""
	 * ("abc", -2, 2)  = "ab"
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param pos 起始位置，负数将被替换为0
	 * @param len 长度
	 * @return 截取内容
	 */
	@Nullable
	public static String mid(@Nullable final String str, @Nonnull final int pos, @Nonnull final int len) {
		return INSTANCE.mid(str, pos, len);
	}

	/**
	 * <pre> 移除末尾换行符.
	 * 
	 * (null)          = null
	 * ("")            = ""
	 * ("abc \r")      = "abc "
	 * ("abc\n")       = "abc"
	 * ("abc\r\n")     = "abc"
	 * ("abc\r\n\r\n") = "abc\r\n"
	 * ("abc\n\r")     = "abc\n"
	 * ("abc\n\rabc")  = "abc\n\rabc"
	 * ("\r")          = ""
	 * ("\n")          = ""
	 * ("\r\n")        = ""
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @return 转换后的字符串
	 */
	@Nullable
	public static String chomp(@Nullable final String str) {
		return INSTANCE.chomp(str);
	}
	
	/**
	 * <pre> 移除末尾一字符.
	 * 
	 * (null)          = null
	 * ("")            = ""
	 * ("abc \r")      = "abc "
	 * ("abc\n")       = "abc"
	 * ("abc\r\n")     = "abc"
	 * ("abc")         = "ab"
	 * ("abc\nabc")    = "abc\nab"
	 * ("a")           = ""
	 * ("\r")          = ""
	 * ("\n")          = ""
	 * ("\r\n")        = ""
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @return 转换后的字符串
	 */
	@Nullable
	public static String chop(@Nullable final String str) {
		return INSTANCE.chop(str);
	}
	
	/**
	 * <pre> 移除起始和末尾的控制符 (char <= 32).
	 * 
	 * (null)          = null
	 * ("")            = ""
	 * ("     ")       = ""
	 * ("abc")         = "abc"
	 * ("    abc    ") = "abc"
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/09  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @return 处理后的字符串
	 */
	@Nullable
	public static String trim(@Nullable final String str) {
		return INSTANCE.trim(str);
	}
	
	/**
	 * <pre> 移除起始和末尾的指定任意字符.
	 * 
	 * (null, *)          = null
	 * ("", *)            = ""
	 * ("abc", null)      = "abc"
	 * ("  abc", null)    = "abc"
	 * ("abc  ", null)    = "abc"
	 * (" abc ", null)    = "abc"
	 * ("  abcyx", "xyz") = "  abc"
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param searchChars 移除字符集合，null时使用空白字符
	 * @return 处理后的字符串
	 */
	@Nullable
	public static String trimAnyChar(@Nullable final String str, @Nullable final String searchChars) {
		return INSTANCE.strip(str, searchChars);
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
	 * @param str 字符串
	 * @return 处理后的字符串数组
	 */
	@Nullable
	public static String trimGroupChar(@Nullable final String str) {
		return trimGroupChar(str, StringGroupSplit.DEFAULT_GROUP_CHAR, StringGroupSplit.DEFAULT_ESCAPE_CHAR);
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
	 * @param str 字符串
	 * @param groupChar 组分隔符
	 * @param escapeChar 逃逸符
	 * @return 处理后的字符串
	 */
	@Nullable
	public static String trimGroupChar(@Nullable final String str, @Nonnull final char groupChar, @Nonnull final char escapeChar) {
		if (str == null) {
			return null;
		}
		if (str.length() > 1 && str.charAt(0) == groupChar
				&& str.charAt(str.length() - 1) == groupChar
				&& str.charAt(str.length() - 2) != escapeChar) {
			return StringUtils.removeEnd(StringUtils.removeStart(str, groupChar), groupChar);
		} else {
			return str;
		}
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
	 * @param str 字符串数组
	 * @return 处理后的字符串
	 */
	@Nullable
	public static String clearEscapeChar(@Nullable final String str) {
		return clearEscapeChar(str, StringGroupSplit.DEFAULT_GROUP_CHAR, StringGroupSplit.DEFAULT_ESCAPE_CHAR);
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
	 * @param str 字符串
	 * @param groupChar 组分隔符
	 * @param escapeChar 逃逸符
	 * @return 处理后的字符串
	 */
	@Nullable
	public static String clearEscapeChar(@Nullable final String str, @Nonnull final char groupChar, @Nonnull final char escapeChar) {
		if (str == null) {
			return null;
		}
		return replace(str, "" + escapeChar + groupChar, groupChar);
	}
	
	/**
	 * <pre> 清除，等价于替换所有指定字符为空.
	 * 
	 * (null, *)       = null
	 * ("", *)         = ""
	 * ("queued", 'u') = "qeed"
	 * ("queued", 'z') = "queued"
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param clearChar 清除字符
	 * @return 转换后的字符串
	 */
	@Nullable
	public static String clear(@Nullable final String str, @Nonnull final char clearChar) {
		return INSTANCE.remove(str, clearChar);
	}
	
	/**
	 * <pre> 清除，等价于替换所有指定字符为空.
	 * 
	 * (null, *)        = null
	 * ("", *)          = ""
	 * (*, null)        = *
	 * (*, "")          = *
	 * ("queued", "ue") = "qd"
	 * ("queued", "zz") = "queued"
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param clearStr 清除字符串
	 * @return 转换后的字符串
	 */
	@Nullable
	public static String clear(@Nullable final String str, @Nullable final String clearStr) {
		return INSTANCE.remove(str, clearStr);
	}
	
	/**
	 * <pre> 清除换行符.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @return 转换后的字符串
	 */
	@Nullable
	public static String clearLineSeparator(@Nullable final String str) {
		return clear(clear(str, '\r'), '\n');
	}
	
	/**
	 * <pre>清除字符串开头内容.
	 * 
	 * (null, *)        = null
	 * ("", *)          = ""
	 * ("111123444", '1') = "23444"
	 * ("111123444", '2') = "111123444"
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/15  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param clearChar 清除字符
	 * @return 转换后的字符串
	 */
	@Nullable
	public static String clearStart(@Nullable final String str, @Nonnull final char clearChar) {
		return clearStart(str, clearChar + "");
	}
	
	/**
	 * <pre>清除字符串开头内容.
	 * 
	 * (null, *)        = null
	 * ("", *)          = ""
	 * (*, null)        = *
	 * (*, "")          = *
	 * ("111123444", "11") = "23444"
	 * ("111123444", "111") = "123444"
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/15  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param clearStr 清除字符串
	 * @return 转换后的字符串
	 */
	@Nullable
	public static String clearStart(@Nullable final String str, @Nullable final String clearStr) {
        if (isEmpty(str) || isEmpty(clearStr)) {
            return str;
        }
        int len = clearStr.length();
        int i = 0;
        while (str.startsWith(clearStr, len * i)) {
        	i++;
        }
        return str.substring(len * i);
	}
	
	/**
	 * <pre>清除字符串结尾内容.
	 * 
	 * (null, *)        = null
	 * ("", *)          = ""
	 * ("111123444", '4') = "111123"
	 * ("111123444", '2') = "111123444"
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/15  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param clearChar 清除字符
	 * @return 转换后的字符串
	 */
	@Nullable
	public static String clearEnd(@Nullable final String str, @Nonnull final char clearChar) {
		return clearEnd(str, clearChar + "");
	}
	
	/**
	 * <pre>清除字符串结尾内容.
	 * 
	 * (null, *)        = null
	 * ("", *)          = ""
	 * (*, null)        = *
	 * (*, "")          = *
	 * ("1111234444", "44") = "111123"
	 * ("1111234444", "444") = "1111234"
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/15  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param clearStr 清除字符串
	 * @return 转换后的字符串
	 */
	@Nullable
	public static String clearEnd(@Nullable final String str, @Nullable final String clearStr) {
        if (isEmpty(str) || isEmpty(clearStr)) {
            return str;
        }
        int len = clearStr.length();
        int i = 0;
        while (StringSearch.endsWith(str, clearStr, len * i)) {
        	i++;
        }
        return str.substring(0, str.length() - len * i);
	}
	
	/**
	 * <pre> 移除起始指定任意字符.
	 * 
	 * (null, *)          = null
	 * ("", *)            = ""
	 * ("abc", "")        = "abc"
	 * ("abc", null)      = "abc"
	 * ("  abc", null)    = "abc"
	 * ("abc  ", null)    = "abc  "
	 * (" abc ", null)    = "abc "
	 * ("yxabc  ", "xyz") = "abc  "
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param searchChars 字符集合，null时使用空白字符作为默认值
	 * @return 处理后的字符串
	 */
	@Nullable
	public static String clearStartAnyChar(@Nullable final String str, @Nullable final String searchChars) {
		return INSTANCE.stripStart(str, searchChars);
	}
	
	/**
	 * <pre> 移除末尾指定任意字符.
	 * 
	 * (null, *)          = null
	 * ("", *)            = ""
	 * ("abc", "")        = "abc"
	 * ("abc", null)      = "abc"
	 * ("  abc", null)    = "  abc"
	 * ("abc  ", null)    = "abc"
	 * (" abc ", null)    = " abc"
	 * ("  abcyx", "xyz") = "  abc"
	 * ("120.00", ".0")   = "12"
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param searchChars 字符集合，null时使用空白字符作为默认值
	 * @return 处理后的字符串
	 */
	@Nullable
	public static String clearEndAnyChar(@Nullable final String str, @Nullable final String searchChars) {
		return INSTANCE.stripEnd(str, searchChars);
	}
	
	/**
	 * <pre> 移除空白字符.
	 * 
	 * (null)         = null
	 * ("")           = ""
	 * ("abc")        = "abc"
	 * ("   ab  c  ") = "abc"
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串 
	 * @return 处理后的字符串
	 */
	@Nullable
	public static String clearWhitespace(@Nullable final String str) {
		return INSTANCE.deleteWhitespace(str);
	}
	
	/**
	 * <pre> 移除声调.
	 * 
	 * (null)                = null
	 * ("")                  = ""
	 * ("control")           = "control"
	 * ("éclair")     = "eclair"
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @return 处理后的字符串
	 */
	@Nullable
	public static String clearAccents(@Nullable final String str) {
		return INSTANCE.stripAccents(str);
	}
	
	/**
	 * <pre> 移除结尾字符（仅清除一个，若全部应使用clearEnd）.
	 * 
	 * (null, *)      = null
	 * ("", *)        = ""
	 * ("abcc", 'c')  = "abc"
	 * ("abcd", 'c')   = "abcd"
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/14  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param remove 移除字符
	 * @return 处理后的字符串
	 */
	@Nullable
	public static String removeEnd(@Nullable final String str, @Nonnull final char remove) {
		return INSTANCE.removeEnd(str, remove + "");
	}
	
	/**
	 * <pre> 移除末尾（仅清除一个，若全部应使用clearEnd）.
	 * 
	 * (null, *)      = null
	 * ("", *)        = ""
	 * (*, null)      = *
	 * ("www.domain.com", ".com.")  = "www.domain.com"
	 * ("www.domain.com", ".com")   = "www.domain"
	 * ("www.domain.com", "domain") = "www.domain.com"
	 * ("abc", "")    = "abc"
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param remove 移除字符
	 * @return 处理后的字符串
	 */
	@Nullable
	public static String removeEnd(@Nullable final String str, @Nullable final String remove) {
		return INSTANCE.removeEnd(str, remove);
	}
	
	/**
	 * <pre> 移除末尾（仅清除一个，忽视大小写）.
	 * 
	 * (null, *)      = null
	 * ("", *)        = ""
	 * (*, null)      = *
	 * ("www.domain.com", ".com.")  = "www.domain.com"
	 * ("www.domain.com", ".com")   = "www.domain"
	 * ("www.domain.com", "domain") = "www.domain.com"
	 * ("abc", "")    = "abc"
	 * ("www.domain.com", ".COM") = "www.domain")
	 * ("www.domain.COM", ".com") = "www.domain")
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param remove 移除字符串
	 * @return 处理后的字符串
	 */
	@Nullable
	public static String removeEndIgnoreCase(@Nullable final String str, @Nullable final String remove) {
		return INSTANCE.removeEndIgnoreCase(str, remove);
	}
	
	/**
	 * <pre> 移除开头字符（仅清除一个，若全部应使用clearStart）.
	 * 
	 * (null, *)      = null
 	 * ("", *)        = ""
 	 * ("aabc", 'a')   = "abc"
 	 * ("aabc", 'b')   = "aabc"
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/14  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串 
	 * @param remove 移除字符
	 * @return 处理后的字符串
	 */
	@Nullable
	public static String removeStart(@Nullable final String str, @Nonnull final char remove) {
		return INSTANCE.removeStart(str, remove + "");
	}
	
	/**
	 * <pre> 移除开头字符（仅清除一个，若全部应使用clearStart）.
	 * 
	 * (null, *)      = null
 	 * ("", *)        = ""
 	 * (*, null)      = *
 	 * ("www.domain.com", "www.")   = "domain.com"
 	 * ("domain.com", "www.")       = "domain.com"
 	 * ("www.domain.com", "domain") = "www.domain.com"
 	 * ("abc", "")    = "abc"
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串 
	 * @param remove 移除字符串
	 * @return 处理后的字符串
	 */
	@Nullable
	public static String removeStart(@Nullable final String str, @Nullable final String remove) {
		return INSTANCE.removeStart(str, remove);
	}
	
	/**
	 * <pre> 移除开头字符（仅清除一个，忽视大小写）.
	 * 
	 * (null, *)      = null
	 * ("", *)        = ""
	 * (*, null)      = *
	 * ("www.domain.com", "www.")   = "domain.com"
	 * ("www.domain.com", "WWW.")   = "domain.com"
	 * ("domain.com", "www.")       = "domain.com"
	 * ("www.domain.com", "domain") = "www.domain.com"
	 * ("abc", "")    = "abc"
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串 
	 * @param remove 移除字符串
	 * @return 处理后的字符串
	 */
	@Nullable
	public static String removeStartIgnoreCase(@Nullable final String str, @Nullable final String remove) {
		return INSTANCE.removeStartIgnoreCase(str, remove);
	}
	
	/**
	 * <pre> 指定字符串后的内容.
	 * 
	 * (null, *)      = null
	 * ("", *)        = ""
	 * (*, null)      = null
	 * ("abc", "a")   = "bc"
	 * ("abcba", "b") = "cba"
	 * ("abc", "c")   = ""
	 * ("abc", "d")   = ""
	 * ("abc", "")    = "abc"
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param separator 指定分隔字符串
	 * @return 处理后的字符串
	 */
	@Nullable
	public static String after(@Nullable final String str, @Nullable final String separator) {
		if (separator == null) {
			return null;
		}
		return INSTANCE.substringAfter(str, separator);
	}
	
	/**
	 * <pre> 最后一个指定字符串后的内容.
	 * 
	 * (null, *)      = null
	 * ("", *)        = ""
	 * (*, "")        = ""
	 * (*, null)      = null
	 * ("abc", "a")   = "bc"
	 * ("abcba", "b") = "a"
	 * ("abc", "c")   = ""
	 * ("a", "a")     = ""
	 * ("a", "z")     = ""
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param separator 指定分隔字符串
	 * @return 处理后的字符串
	 */
	@Nullable
	public static String afterLast(@Nullable final String str, @Nullable final String separator) {
		if (separator == null) {
			return null;
		}
		return INSTANCE.substringAfterLast(str, separator);
	}
	
	/**
	 * <pre> 指定字符串前的内容.
	 * 
	 * (null, *)      = null
	 * ("", *)        = ""
	 * ("abc", "a")   = ""
	 * ("abcba", "b") = "a"
	 * ("abc", "c")   = "ab"
	 * ("abc", "d")   = "abc"
	 * ("abc", "")    = ""
	 * ("abc", null)  = null
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param separator 指定分隔字符串
	 * @return 处理后的字符串
	 */
	@Nullable
	public static String before(@Nullable final String str, @Nullable final String separator) {
		if (separator == null) {
			return null;
		}
		return INSTANCE.substringBefore(str, separator);
	}
	
	/**
	 * <pre> 最后一个指定字符串前的内容.
	 * 
	 * (null, *)      = null
	 * ("", *)        = ""
	 * ("abcba", "b") = "abc"
	 * ("abc", "c")   = "ab"
	 * ("a", "a")     = ""
	 * ("a", "z")     = "a"
	 * ("a", null)    = null
	 * ("a", "")      = "a"
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param separator 指定分隔字符串
	 * @return 处理后的字符串
	 */
	@Nullable
	public static String beforeLast(@Nullable final String str, @Nullable final String separator) {
		if (separator == null) {
			return null;
		}
		return INSTANCE.substringBeforeLast(str, separator);
	}
	
	/**
	 * <pre> 指定字符串间的内容.
	 * 
	 * (null, *)            = null
	 * ("", "")             = ""
	 * ("", "tag")          = null
	 * ("tagabctag", null)  = null
	 * ("tagabctag", "")    = ""
	 * ("tagabctag", "tag") = "abc"
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param tag 指定分隔字符串
	 * @return 处理后的字符串
	 */
	@Nullable
	public static String between(@Nullable final String str, @Nullable final String tag) {
		return INSTANCE.substringBetween(str, tag);
	}
	
	/**
	 * <pre> 首个指定字符串间的内容.
	 * 
	 * ("wx[b]yz", "[", "]") = "b"
	 * (null, *, *)          = null
	 * (*, null, *)          = null
	 * (*, *, null)          = null
	 * ("", "", "")          = ""
	 * ("", "", "]")         = null
	 * ("", "[", "]")        = null
	 * ("yabcz", "", "")     = ""
	 * ("yabcz", "y", "z")   = "abc"
	 * ("yabczyabcz", "y", "z")   = "abc"
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param open 前分隔字符串
	 * @param close 后分隔字符串
	 * @return 处理后的字符串
	 */
	@Nullable
	public static String between(@Nullable final String str, @Nullable final String open, @Nullable final String close) {
		return INSTANCE.substringBetween(str, open, close);
	}
	
	/**
	 * <pre> 全部指定字符串间的内容.
	 * 
	 * ("[a][b][c]", "[", "]") = ["a","b","c"]
	 * (null, *, *)            = null
	 * (*, null, *)            = null
	 * (*, *, null)            = null
	 * ("", "[", "]")          = []
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param open 前分隔字符串
	 * @param close 后分隔字符串
	 * @return 处理后的字符串
	 */
	@Nullable
	public static String[] betweens(@Nullable final String str, @Nullable final String open, @Nullable final String close) {
		return INSTANCE.substringsBetween(str, open, close);
	}
	
	/**
	 * <pre> 比较（无视大小写）.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/09  huangys  Create
	 * </pre>
	 * 
	 * @param str1 字符串1
	 * @param str2 字符串2
	 * @return 是否相等
	 */
	public static boolean equalsIgnoreCase(final CharSequence str1, final CharSequence str2) {
		return INSTANCE.equalsIgnoreCase(str1, str2);
	}
	
	/**
	 * <pre> 是否全为小写字母.
	 * 
	 * (null)   = false
	 * ("")     = false
	 * ("  ")   = false
	 * ("abc")  = true
	 * ("abC") = false
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @return 是否满足
	 */
	@Nonnull
	public static boolean isAllLowerCase(@Nullable final CharSequence str) {
		return INSTANCE.isAllLowerCase(str);
	}
	
	/**
	 * <pre> 是否全为大写字母.
	 * 
	 * (null)   = false
	 * ("")     = false
	 * ("  ")   = false
	 * ("ABC")  = true
	 * ("aBC") = false
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @return 是否满足
	 */
	@Nonnull
	public static boolean isAllUpperCase(@Nullable final CharSequence str) {
		return INSTANCE.isAllUpperCase(str);
	}
	
	/**
	 * <pre> 是否全为字母.
	 * 
	 * (null)   = false
	 * ("")     = false
	 * ("  ")   = false
	 * ("abc")  = true
	 * ("ab2c") = false
	 * ("ab-c") = false
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @return 是否满足
	 */
	@Nonnull
	public static boolean isAlpha(@Nullable final CharSequence str) {
		return INSTANCE.isAlpha(str);
	}
	
	/**
	 * <pre> 是否全为字母或数字.
	 * 
	 * (null)   = false
	 * ("")     = false
	 * ("  ")   = false
	 * ("abc")  = true
	 * ("ab c") = false
	 * ("ab2c") = true
	 * ("ab-c") = false
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @return 是否满足
	 */
	@Nonnull
	public static boolean isAlphanumeric(@Nullable final CharSequence str) {
		return INSTANCE.isAlphanumeric(str);
	}
	
	/**
	 * <pre>是否全为字母或数字或空格.
	 * 
	 * (null)   = false
 	 * ("")     = true
 	 * ("  ")   = true
 	 * ("abc")  = true
 	 * ("ab c") = true
 	 * ("ab2c") = true
 	 * ("ab-c") = false
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @return 是否满足
	 */
	@Nonnull
	public static boolean isAlphanumericSpace(@Nullable final CharSequence str) {
		return INSTANCE.isAlphanumericSpace(str);
	}
	
	/**
	 * <pre> 是否全为字母或空格.
	 * 
	 * (null)   = false
	 * ("")     = true
	 * ("  ")   = true
	 * ("abc")  = true
	 * ("ab c") = true
	 * ("ab2c") = false
	 * ("ab-c") = false
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @return 是否满足
	 */
	@Nonnull
	public static boolean isAlphaSpace(@Nullable final CharSequence str) {
		return INSTANCE.isAlphaSpace(str);
	}
	
	/**
	 * <pre> 是否全为Ascii码表中的可见字符(32 >= char <= 126).
	 * 
	 * (null)     = false
	 * ("")       = true
	 * (" ")      = true
	 * ("Ceki")   = true
	 * ("ab2c")   = true
	 * ("!ab-c~") = true
	 * ("\u0020") = true
	 * ("\u0021") = true
	 * ("\u007e") = true
	 * ("\u007f") = false
	 * ("Ceki G\u00fclc\u00fc") = false
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @return 是否满足
	 */
	@Nonnull
	public static boolean isAsciiPrintable(@Nullable final CharSequence str) {
		return INSTANCE.isAsciiPrintable(str);
	}
	
	/**
	 * <pre> 是否全为空白字符.
	 * 
	 * (null)      = true
	 * ("")        = true
	 * (" ")       = true
	 * ("bob")     = false
	 * ("  bob  ") = false
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @return 是否满足
	 */
	@Nonnull
	public static boolean isBlank(@Nullable final CharSequence str) {
		return INSTANCE.isBlank(str);
	}
	
	/**
	 * <pre> 是否为非空白字符串.
	 * 
	 * (null)      = false
	 * ("")        = false
	 * (" ")       = false
	 * ("bob")     = true
	 * ("  bob  ") = true
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @return 是否满足
	 */
	@Nonnull
	public static boolean isNotBlank(@Nullable final CharSequence str) {
		return INSTANCE.isNotBlank(str);
	}
	
	/**
	 * <pre> 是否为空字符串.
	 * 
	 * (null)      = true
	 * ("")        = true
	 * (" ")       = false
	 * ("bob")     = false
	 * ("  bob  ") = false
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @return 是否满足
	 */
	@Nonnull
	public static boolean isEmpty(@Nullable final CharSequence str) {
		return INSTANCE.isEmpty(str);
	}
	
	/**
	 * <pre> 是否为非空字符串.
	 * 
	 * (null)      = false
	 * ("")        = false
	 * (" ")       = true
	 * ("bob")     = true
	 * ("  bob  ") = true
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @return 是否满足
	 */
	@Nonnull
	public static boolean isNotEmpty(@Nullable final CharSequence str) {
		return INSTANCE.isNotEmpty(str);
	}
	
	/**
	 * <pre> 是否全为数字.
	 * 
	 * (null)   = false
	 * ("")     = false
	 * ("  ")   = false
	 * ("123")  = true
	 * ("12 3") = false
	 * ("ab2c") = false
	 * ("12-3") = false
	 * ("12.3") = false
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @return 是否满足
	 */
	@Nonnull
	public static boolean isNumeric(@Nullable final CharSequence str) {
		return INSTANCE.isNumeric(str);
	}
	
	/**
	 * <pre> 是否全为数字或空格.
	 * 
	 * (null)   = false
	 * ("")     = true
	 * ("  ")   = true
	 * ("123")  = true
	 * ("12 3") = true
	 * ("ab2c") = false
	 * ("12-3") = false
	 * ("12.3") = false
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @return 是否满足
	 */
	@Nonnull
	public static boolean isNumericSpace(@Nullable final CharSequence str) {
		return INSTANCE.isNumericSpace(str);
	}
	
	/**
	 * <pre> 是否全为空格.
	 * 
	 * (null)   = false
	 * ("")     = true
	 * ("  ")   = true
	 * ("abc")  = false
	 * ("ab2c") = false
	 * ("ab-c") = false
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @return 是否满足
	 */
	@Nonnull
	public static boolean isWhitespace(@Nullable final CharSequence str) {
		return INSTANCE.isWhitespace(str);
	}
	
	/**
	 * <pre> 将指定字符串替换为特定字符串.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/08/13  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param matchStr 指定字符串
	 * @param toStr 替换字符串
	 * @return 字符串
	 */
	@Nullable
	public static String to(@Nullable final String str, @Nullable final String matchStr, @Nullable final String toStr) {
		return Objects.to(str, matchStr, toStr);
	}
	
	/**
	 * <pre> 将指定字符串替换为特定字符串（无视大小写）.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/08/13  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param matchStr 指定字符串
	 * @param toStr 替换字符串
	 * @return 字符串
	 */
	@Nullable
	public static String toIgnoreCase(@Nullable final String str, @Nullable final String matchStr, @Nullable final String toStr) {
		return equalsIgnoreCase(str, matchStr) ? toStr : str;
	}
	
	/**
	 * <pre> 将指定字符串转为null.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/08/13  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param matchStr 指定字符串
	 * @return 字符串
	 */
	@Nullable
	public static String toNull(@Nullable final String str, @Nullable final String matchStr) {
		return to(str, matchStr, null);
	}
	
	/**
	 * <pre> 若为Null，则返回空字符串，否则返回本身.
	 * 
	 * null -> ""
	 * "ab" -> "ab"
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @return 处理后的字符串
	 */
	@Nullable
	public static String nullToEmpty(@Nullable final String str) {
		return INSTANCE.defaultString(str);
	}
	
	/**
	 * <pre> 若为Null，则返回指定字符串，否则返回本身.
	 * 
	 * null, "a" -> "a"
	 * "ab", * -> "ab"
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param toStr 指定字符串
	 * @return 处理后的字符串
	 */
	@Nullable
	public static String nullTo(@Nullable final String str, @Nullable final String toStr) {
		return str == null ? toStr : str;
	}
	
	private static final String NULL_STRING = "NULL";
	/**
	 * <pre> 若元素内容为"Null"字符串，则返回null，否则返回本身.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/21  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @return 处理后的字符串
	 */
	@Nullable
	public static String nullStringToNull(@Nullable final String str) {
		return toIgnoreCase(str, NULL_STRING, null);
	}
	
	/**
	 * <pre> 若为空字符串 ，则返回null，否则返回本身.
	 * 
	 * (null)      = null
	 * ("")        = null
	 * (" ")       = " "
	 * ("bob")     = "bob"
	 * ("  bob  ") = "  bob  "
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @return 处理后的字符串
	 */
	@Nullable
	public static String emptyToNull(@Nullable final String str) {
		return isEmpty(str) ? null : str;
	}
	
	/**
	 * <pre> 若为空字符串 ，则返回指定字符串，否则返回本身.
	 * 
	 * (null, "X")      = "X"
	 * ("", "X")        = "X"
	 * (" ", "X")       = " "
	 * ("bob", "X")     = "bob"
	 * ("  bob  ", "X") = "  bob  "
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param toStr 指定字符串
	 * @return 处理后的字符串
	 */
	@Nullable
	public static String emptyTo(@Nullable final String str, @Nullable final String toStr) {
		return isEmpty(str) ? toStr : str;
	}
	
	/**
	 * <pre> 若为空白字符串，则返回null，否则返回本身.
	 * 
	 * (null)      = null
	 * ("")        = null
	 * (" ")       = null
	 * ("bob")     = "bob"
	 * ("  bob  ") = "  bob  "
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @return 处理后的字符串
	 */
	@Nullable
	public static String blankToNull(@Nullable final String str) {
		return isBlank(str) ? null : str;
	}

	/**
	 * <pre> 若为空白字符串，则返回空字符串，否则返回本身.
	 * 
	 * (null)      = ""
	 * ("")        = ""
	 * (" ")       = ""
	 * ("bob")     = "bob"
	 * ("  bob  ") = "  bob  "
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @return 处理后的字符串
	 */
	@Nonnull
	public static String blankToEmpty(@Nullable final String str) {
		return isBlank(str) ? INSTANCE.EMPTY : str;
	}
	
	/**
	 * <pre> 若为空白字符串，则返回指定字符串，否则返回本身.
	 * 
	 *  (null, "X")      = "X"
	 *  ("", "X")        = "X"
	 *  (" ", "X")       = "X"
	 *  ("bob", "X")     = "bob"
	 *  ("  bob  ", "X") = "  bob  "
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param toStr 指定字符串
	 * @return 处理后的字符串
	 */
	@Nullable
	public static String blankTo(@Nullable final String str, @Nullable final String toStr) {
		return isBlank(str) ? toStr : str;
	}
	
	/**
	 * <pre> 插入.
	 * 
	 * (null, *, *)            = null
	 * ("", "abc", 0)          = "abc"
	 * ("abcdef", null, 2)     = "abcdef"
	 * ("abcdef", "", 2)       = "abcdef"
	 * ("abcdef", "zzzz", 2)   = "abzzzzcdef"
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param insert 插入内容
	 * @param pos 插入索引位置
	 * @return 处理后的字符串
	 */
	@Nullable
	public static String insert(@Nullable final String str, @Nullable final String insert, @Nonnull final int pos) {
		return INSTANCE.overlay(str, insert, pos, pos);
	}
	
	/**
	 * <pre> 插入并覆盖指定位置.
	 * 
	 * (null, *, *, *)            = null
	 * ("", "abc", 0, 0)          = "abc"
	 * ("abcdef", null, 2, 4)     = "abef"
	 * ("abcdef", "", 2, 4)       = "abef"
	 * ("abcdef", "", 4, 2)       = "abef"
	 * ("abcdef", "zzzz", 2, 4)   = "abzzzzef"
	 * ("abcdef", "zzzz", 4, 2)   = "abzzzzef"
	 * ("abcdef", "zzzz", -1, 4)  = "zzzzef"
	 * ("abcdef", "zzzz", 2, 8)   = "abzzzz"
	 * ("abcdef", "zzzz", -2, -3) = "zzzzabcdef"
	 * ("abcdef", "zzzz", 8, 10)  = "abcdefzzzz"
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param insert 覆盖内容
	 * @param start 覆盖起始索引位置（包含）
	 * @param len 覆盖结束索引位置（排除）
	 * @return 处理后的字符串
	 */
	@Nullable
	public static String insertOver(@Nullable final String str, @Nullable final String insert, @Nonnull final int start, @Nonnull final int len) {
		return INSTANCE.overlay(str, insert, start, start + len);
	}
	
	/**
	 * <pre> 替换字符串.
	 * 
	 * (null, *, *)        = null
	 * ("", *, *)          = ""
	 * ("any", null, *)    = "any"
	 * ("aba", 'a', 'z')   = "zbz"
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/05/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param searchChar 查找字符
	 * @param replacementChar 替换字符
	 * @return 处理后的字符串
	 */
	@Nullable
	public static String replace(@Nullable final String str, @Nonnull final char searchChar, @Nonnull final char replacementChar) {
		return INSTANCE.replace(str, searchChar + "", replacementChar + "");
	}
	
	/**
	 * <pre> 替换字符串.
	 * 
	 * (null, *, *)        = null
	 * ("", *, *)          = ""
	 * ("any", null, *)    = "any"
	 * ("any", "", *)      = "any"
	 * ("aba", "a", 'z')   = "zbz"
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/14  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param searchStr 查找字符串
	 * @param replacementChar 替换字符
	 * @return 处理后的字符串
	 */
	@Nullable
	public static String replace(@Nullable final String str, @Nullable final String searchStr, @Nonnull final char replacementChar) {
		return INSTANCE.replace(str, searchStr, replacementChar + "");
	}
	
	/**
	 * <pre> 替换字符串.
	 * 
	 * (null, *, *)        = null
	 * ("", *, *)          = ""
	 * ("any", null, *)    = "any"
	 * ("any", *, null)    = "any"
	 * ("any", "", *)      = "any"
	 * ("aba", "a", null)  = "aba"
	 * ("aba", "a", "")    = "b"
	 * ("aba", "a", "z")   = "zbz"
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/14  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param searchStr 查找字符串
	 * @param replacementStr 替换字符
	 * @return 处理后的字符串
	 */
	@Nullable
	public static String replace(@Nullable final String str, @Nullable final String searchStr, @Nullable final String replacementStr) {
		return INSTANCE.replace(str, searchStr, replacementStr);
	}
	
	/**
	 * <pre> 替换换行符.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/07/16  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param replacementChar 替换字符
	 * @return 处理后的字符串
	 */
	@Nullable
	public static String replaceLineSeparator(@Nullable final String str, @Nullable final char replacementChar) {
		return replaceLineSeparator(str, replacementChar + "");
	}
	
	/**
	 * <pre> 替换换行符.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/07/16  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param replacementStr 替换字符
	 * @return 处理后的字符串
	 */
	@Nullable
	public static String replaceLineSeparator(@Nullable final String str, @Nullable final String replacementStr) {
		return replace(replace(replace(str, "\r\n", replacementStr), "\r", replacementStr), "\n", replacementStr);
	}
	
	/**
	 * <pre> 仅替换一次最前的指定字符串.
	 * 
	 * (null, *, *)        = null
	 * ("", *, *)          = ""
	 * ("any", null, *)    = "any"
	 * ("any", *, null)    = "any"
	 * ("any", "", *)      = "any"
	 * ("aba", "a", null)  = "aba"
	 * ("aba", "a", "")    = "ba"
	 * ("aba", "a", "z")   = "zba"
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param searchStr 查找字符串
	 * @param replacementStr 替换字符串
	 * @return 处理后的字符串
	 */
	@Nullable
	public static String replaceFirst(@Nullable final String str, @Nullable final String searchStr, @Nullable final String replacementStr) {
		return INSTANCE.replaceOnce(str, searchStr, replacementStr);
	}
	
	/**
	 * <pre> 仅替换一次最后的指定字符串.
	 * 
	 * null, *, * -> null
	 * "", *, * -> ""
	 * *, null, * -> self
	 * *, "", * -> self
	 * "bcabcd", "bc", "123" -> "bca123d"
	 * "bcabcd", "bc", null -> "bcad"
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/03  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param searchStr 查找字符串
	 * @param replacementStr 替换字符串
	 * @return 处理后的字符串
	 */
	@Nullable
	public static String replaceLast(@Nullable final String str, @Nullable final String searchStr, @Nullable final String replacementStr) {
		if (isEmpty(str) || isEmpty(searchStr)) {
			return str;
		}
		int pos = str.lastIndexOf(searchStr);
		if (pos == -1) {
			return str;
		} else {
			return insertOver(str, replacementStr, pos, searchStr.length());
		}
	}
	
	/**
	 * <pre> 仅替换第N次出现的指定字符串.
	 * 
	 * null, *, *, * -> null
	 * "", *, *, * -> ""
	 * *, null, *, * -> self
	 * *, "", *, * -> self
	 * "bcabcd", "bc", "123", 2 -> "bca123d"
	 * "bcabcd", "bc", null, 2 -> "bcad"
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/03  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param searchStr 查找字符串
	 * @param replacementStr 替换字符串
	 * @param index 出现次数
	 * @return 处理后的字符串
	 */
	@Nullable
	public static String replaceOrdinal(@Nullable final String str, @Nullable final String searchStr, @Nullable final String replacementStr, @Nonnull final int index) {
		if (isEmpty(str) || isEmpty(searchStr)) {
			return str;
		}
		int pos = StringSearch.indexOfOrdinal(str, searchStr, index);
		if (pos == -1) {
			return str;
		} else {
			return insertOver(str, replacementStr, pos, searchStr.length());
		}
	}
	
	/**
	 * <pre> 仅替换第N次出现（从末尾计算）的指定字符串.
	 * 
	 * null, *, *, * -> null
	 * "", *, *, * -> ""
	 * *, null, *, * -> self
	 * *, "", *, * -> self
	 * "bcabcd", "bc", "123", 1 -> "bca123d"
	 * "bcabcd", "bc", null, 1 -> "bcad"
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/03  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param searchStr 查找字符串
	 * @param replacementStr 替换字符串
	 * @param index 出现次数
	 * @return 处理后的字符串
	 */
	@Nullable
	public static String replaceLastOrdinal(@Nullable final String str, @Nullable final String searchStr, @Nullable final String replacementStr, @Nonnull final int index) {
		if (isEmpty(str) || isEmpty(searchStr)) {
			return str;
		}
		int pos = StringSearch.lastIndexOfOrdinal(str, searchStr, index);
		if (pos == -1) {
			return str;
		} else {
			return insertOver(str, replacementStr, pos, searchStr.length());
		}
	}

	/**
	 * <pre> 替换字符串（指定最大替换次数）.
	 * 
	 * (null, *, *, *)         = null
	 * ("", *, *, *)           = ""
	 * ("any", null, *, *)     = "any"
	 * ("any", *, null, *)     = "any"
	 * ("any", "", *, *)       = "any"
	 * ("any", *, *, 0)        = "any"
	 * ("abaa", "a", null, -1) = "abaa"
	 * ("abaa", "a", "", -1)   = "b"
	 * ("abaa", "a", "z", 0)   = "abaa"
	 * ("abaa", "a", "z", 1)   = "zbaa"
	 * ("abaa", "a", "z", 2)   = "zbza"
	 * ("abaa", "a", "z", -1)  = "zbzz"
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param searchStr 查找字符串
	 * @param replacementStr 替换字符串
	 * @param max 最大替换次数
	 * @return 处理后的字符串
	 */
	@Nullable
	public static String replace(@Nullable final String str, @Nullable final String searchStr, @Nullable final String replacementStr, final int max) {
		return INSTANCE.replace(str, searchStr, replacementStr, max);
	}
	
	/**
	 * <pre> 替换字符（searchChars/replaceChars按顺序映射，不迭代）.
	 * 
	 * (null, *, *)           = null
	 * ("", *, *)             = ""
	 * ("abc", null, *)       = "abc"
	 * ("abc", "", *)         = "abc"
	 * ("abc", "b", null)     = "ac"
	 * ("abc", "b", "")       = "ac"
	 * ("abcba", "bc", "yz")  = "ayzya"
	 * ("abcba", "bc", "y")   = "ayya"
	 * ("abcba", "bc", "yzx") = "ayzya"
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param searchChars 查找字符集合
	 * @param replaceChars 替换字符集合
	 * @return 处理后的字符串
	 */
	@Nullable
	public static String replaceEachChars(@Nullable final String str, @Nullable final String searchChars, @Nullable final String replaceChars) {
		return INSTANCE.replaceChars(str, searchChars, replaceChars);
	}
	
	/**
	 * <pre> 替换字符串（searchList/replacementList按顺序映射，不迭代）.
	 * 
	 * (null, *, *)        = null
	 * ("", *, *)          = ""
	 * ("aba", null, null) = "aba"
	 * ("aba", new String[0], null) = "aba"
	 * ("aba", null, new String[0]) = "aba"
	 * ("aba", new String[]{"a"}, null)  = "aba"
	 * ("aba", new String[]{"a"}, new String[]{""})  = "b"
	 * ("aba", new String[]{null}, new String[]{"a"})  = "aba"
	 * ("abcde", new String[]{"ab", "d"}, new String[]{"w", "t"})  = "wcte"
	 * ---- example of how it does not repeat ----
	 * ("abcde", new String[]{"ab", "d"}, new String[]{"d", "t"})  = "dcte"
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param searchList 查找字符串数组
	 * @param replacementList 替换字符串数组
	 * @return 处理后的字符串
	 */
	@Nullable
	public static String replaceEach(@Nullable final String str, @Nullable final String[] searchList, @Nullable final String[] replacementList) {
		return INSTANCE.replaceEach(str, searchList, replacementList);
	}
	
	/**
	 * <pre> 替换字符串.
	 * 如需考虑替换顺序，请使用传入字符串数组的方法。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/05/03  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param map 替换对
	 * @return 处理后的字符串
	 */
	@Nullable
	public static String replaceEach(@Nullable final String str, @Nullable final Map<String, String> map) {
		if (Objects.isEmpty(map)) {
			return str;
		}
		Pair<String[], String[]> pair = Maps.toArrays(map);
		return replaceEach(str, pair.getLeft(), pair.getRight());
	}
	
	/**
	 * <pre> 迭代替换字符串（searchList/replacementList按顺序映射）.
	 * 
	 * (null, *, *) = null
	 * ("", *, *) = ""
	 * ("aba", null, null) = "aba"
	 * ("aba", new String[0], null) = "aba"
	 * ("aba", null, new String[0]) = "aba"
	 * ("aba", new String[]{"a"}, null) = "aba"
	 * ("aba", new String[]{"a"}, new String[]{""}) = "b"
	 * ("aba", new String[]{null}, new String[]{"a"}) = "aba"
	 * ("abcde", new String[]{"ab", "d"}, new String[]{"w", "t"}) = "wcte"
	 * ---- example of how it repeats ----
	 * ("abcde", new String[]{"ab", "d"}, new String[]{"d", "t"}) = "tcte"
	 * ("abcde", new String[]{"ab", "d"}, new String[]{"d", "ab"}) = IllegalStateException
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param searchList 查找字符串数组
	 * @param replacementList 替换字符串数组
	 * @return 处理后的字符串
	 */
	@Nullable
	public static String replaceEachRepeatedly(@Nullable final String str, @Nullable final String[] searchList, @Nullable final String[] replacementList) {
		return INSTANCE.replaceEachRepeatedly(str, searchList, replacementList);
	}
	
	/**
	 * <pre> 迭代替换字符串.
	 * 如需考虑替换顺序，请使用传入字符串数组的方法。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/05/03  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param map 替换对
	 * @return 处理后的字符串
	 */
	@Nullable
	public static String replaceEachRepeatedly(@Nullable final String str, @Nullable final Map<String, String> map) {
		if (Objects.isEmpty(map)) {
			return str;
		}
		Pair<String[], String[]> pair = Maps.toArrays(map);
		return replaceEachRepeatedly(str, pair.getLeft(), pair.getRight());
	}
	
	/**
	 * <pre> 反转字符串.
	 * 
	 * (null)  = null
	 * ("")    = ""
	 * ("bat") = "tab"
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @return 处理后的字符串
	 */
	@Nullable
	public static String reverse(@Nullable final String str) {
		return INSTANCE.reverse(str);
	}
	
	/**
	 * <pre> 反转字符串所包含的元素位置.
	 * 分隔符隔离出来的元素顺序反转，但元素本身不反转。
	 * 
 	 * (null, *)      = null
 	 * ("", *)        = ""
 	 * ("a.b.c", 'x') = "a.b.c"
 	 * ("a.b.c", ".") = "c.b.a"
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/02  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param separatorChar 分隔符
	 * @return 处理后的字符串
	 */
	@Nullable
	public static String reverseDelimited(@Nullable final String str, @Nonnull final char separatorChar) {
		return INSTANCE.reverseDelimited(str, separatorChar);
	}

	/**
	 * <pre> 按字节截取字符串，可以剔除结尾乱码.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/18  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param byteNum 字节数
	 * @return 处理后的字符串
	 */
	@Nullable
	public static String substrb(@Nullable final String str, @Nonnull final int byteNum) {
		return substrb(str, byteNum, null);
	}
	
	/**
	 * <pre> 按字节截取字符串，可以剔除结尾乱码.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2012/11/08  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param byteNum 字节数
	 * @param charSet 字符集
	 * @return 处理后的字符串
	 */
	@Nullable
	public static String substrb(@Nullable final String str, @Nonnull final int byteNum, @Nullable final String charSet) {
		// 快速判断
		if (isEmpty(str)) {
			return str;
		}
		
		try {
			// 转为字节数组
			byte[] bytes = charSet == null ? str.getBytes() : str.getBytes(charSet);
			int length = bytes.length;
			if (length <= byteNum) {
				return str;
			} else {
				// 获得新字符串
				String newStr = charSet == null ? new String(bytes, 0, byteNum) : new String(bytes, 0, byteNum, charSet);
				// 新字符串的字符长度
				int newStrLength = newStr.length();
				// 判断字符串长度对应的字节数是否与想截取的字节数一致
				// 不一致时，表明结尾有不完整字符存在
				String origStr = str.substring(0, newStrLength);
				if ((charSet == null ? origStr.getBytes() : origStr.getBytes(charSet)).length == byteNum) {
					return newStr;
				} else {
					return str.substring(0, newStrLength - 1);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
	
	/**
	 * <pre> 截取字符串.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/05/05  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param end 结束索引
	 * @return 截取的字符串
	 */
	public static String substringEnd(@Nullable final String str, @Nonnull final int end) {
		return INSTANCE.substring(str, 0, end);
	}
	
	/**
	 * <pre> 截取字符串.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/05/05  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param start 起始索引
	 * @param end 结束索引
	 * @return 截取的字符串
	 */
	public static String substring(@Nullable final String str, @Nonnull final int start, @Nonnull final int end) {
		return INSTANCE.substring(str, start, end);
	}
	
	/**
	 * <pre> 截取字符串.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/05/05  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param start 起始索引
	 * @return 截取的字符串
	 */
	public static String substring(@Nullable final String str, @Nonnull final int start) {
		return INSTANCE.substring(str, start);
	}
	
	/**
	 * <pre> 从字符串中解析出手机号码.
	 * 
	 * null -> null
	 * "" -> null
	 * 13566778899 -> 13566778899
	 * +13566778899 -> 13566778899
	 * 8613566778899 -> 13566778899
	 * +8613566778899 -> 13566778899
	 * +86135667788990 -> null
	 * +861356677889X -> null
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2012/11/08  huangys  Create
	 * </pre>
	 * 
	 * @param phoneNoStr 手机字符串
	 * @return 手机号
	 */
	@Nullable
	public static String parseMobile(@Nullable final String phoneNoStr) {
		if (isBlank(phoneNoStr)) {
			return null;
		}
		
		// 剔除前缀+86
		String phoneNo = phoneNoStr;
		if (phoneNo.startsWith("+")) {
			phoneNo = phoneNo.substring(1);
		}
		if (phoneNo.startsWith("86")) {
			phoneNo = phoneNo.substring(2);
		}
		
		// 是否满足手机号条件，11位数字
		if (phoneNo.length() == 11 && isNumeric(phoneNo)) {
			return phoneNo;
		} else {
			return null;
		}
	}
	
	/**
	 * <pre> 过滤过长内容.
	 * 超过指定字节数返回null，否则返回字符串本身。
	 * 
	 * null, *, * -> null
	 * "中国", 4 -> null
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2012/12/04  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param byteNum 字节数
	 * @return 处理后字符串
	 */
	@Nullable
	public static String filterTooLong(@Nullable final String str, @Nonnull final int byteNum) {
		return filterTooLong(str, byteNum, null);
	}
	
	/**
	 * <pre> 过滤过长内容.
	 * 超过指定字节数返回null，否则返回字符串本身。
	 * 
	 * null, *, * -> null
	 * "中国", 4, "utf8" -> null
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2012/12/04  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param byteNum 字节数
	 * @param charSet 字符集，null时用的是系统默认字符集
	 * @return 处理后字符串
	 */
	@Nullable
	public static String filterTooLong(@Nullable final String str, @Nonnull final int byteNum, @Nullable final String charSet) {
		if (isEmpty(str)) {
			return str;
		}
		try {
			int len = charSet == null ? str.getBytes().length : str.getBytes(charSet).length;
			return  len <= byteNum ? str : null;
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	
	private static final String SN_REGX_4 = "([0-9.]+)万.*";
	private static final String SN_REGX_8 = "([0-9.]+)亿.*";
	private static final String SN_REGX_B2 = "([0-9.]+)%.*";
	private static final Object[] SN_REGX_ALL = { SN_REGX_4, 10000d, SN_REGX_8, 100000000d, SN_REGX_B2, 0.01d};
	/**
	 * <pre> 智能转为数值.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/08/13  huangys  Create
	 * </pre>
	 * 
	 * @param str 数值字符串
	 * @return 数值
	 */
	@Nullable
	public static Double parseDoubleSmart(@Nullable final String str) {
		if (isBlank(str)) {
			return null;
		}
		for (int i = 0; i < SN_REGX_ALL.length; i += 2) {
			String regx = (String) SN_REGX_ALL[i];
			double change = (Double) SN_REGX_ALL[i + 1];
			if (str.matches(regx)) {
				return Double.parseDouble(StringRE.getOneGroupFirst(str, regx)) * change;
			}
		}
		return Double.parseDouble(str);
	}
	
	/**
	 * <pre> 智能转为数值.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/08/13  huangys  Create
	 * </pre>
	 * 
	 * @param str 数值字符串
	 * @return 数值
	 */
	@Nullable
	public static Long parseLongSmart(@Nullable final String str) {
		Double ret = parseDoubleSmart(str);
		if (ret == null) {
			return null;
		} else {
			return ret.longValue();
		}
	}
	
	/**
	 * <pre> 智能转为数值.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/08/13  huangys  Create
	 * </pre>
	 * 
	 * @param str 数值字符串
	 * @return 数值
	 */
	@Nullable
	public static Integer parseIntSmart(@Nullable final String str) {
		Double ret = parseDoubleSmart(str);
		if (ret == null) {
			return null;
		} else {
			return ret.intValue();
		}
	}
	
}
