/*
 * Copyright 2015 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2015/12/09.
 * 
 */
package com.toobye.common.string;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.toobye.common.lang.Condition;
import com.toobye.common.lang.Function;
import com.toobye.common.lang.Objects;

/**
 * <pre> 字符串数组处理函数.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2015/12/09  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class StringArrayFuncs {
	
	private StringArrayFuncs() { }

	/**
	 * <pre> 移除起始和末尾的控制符 (char <= 32). </pre>
	 * @see StringUtils#trim(String)
	 */
	public static final Function<String, String> TRIM = new Function<String, String>() {
		@Override
		public String apply(final String str) {
			return StringUtils.trim(str);
		}
	};
	
	/**
	 * <pre> 移除起始和末尾的指定任意字符. </pre>
	 * 
	 * @param searchChars 移除字符集合，null时使用空白字符
	 * @return 处理函数
	 * @see StringUtils#trimAnyChar(String,String)
	 */
	@Nonnull
	public static Function<String, String> trimAnyChar(@Nullable final String searchChars) {
		return new Function<String, String>() {
			@Override
			public String apply(final String str) {
				return StringUtils.trimAnyChar(str, searchChars);
			}
		};
	}
	
	/**
	 * <pre> 若为Null，则返回空字符串，否则返回本身. </pre>
	 * @see StringUtils#nullToEmpty(String)
	 */
	public static final Function<String, String> NULL_TO_EMPTY = new Function<String, String>() {
		@Override
		public String apply(final String str) {
			return StringUtils.nullToEmpty(str);
		}
	};
	
	/**
	 * <pre> 若为Null，则返回指定字符串，否则返回本身. </pre>
	 * 
	 * @param toStr 替换字符串
	 * @return 处理函数
	 * @see StringUtils#nullTo(String,String)
	 */
	@Nonnull
	public static Function<String, String> nullTo(@Nullable final String toStr) {
		return new Function<String, String>() {
			@Override
			public String apply(final String str) {
				return StringUtils.nullTo(str, toStr);
			}
		};
	}
	
	/**
	 * <pre> 若元素内容为"Null"字符串，则返回null，否则返回本身. </pre>
	 * @see StringUtils#nullStringToNull(String)
	 */
	public static final Function<String, String> NULL_STRING_TO_NULL = new Function<String, String>() {
		@Override
		public String apply(final String str) {
			return StringUtils.nullStringToNull(str);
		}
	};
	
	/**
	 * <pre> 若为空字符串 ，则返回null，否则返回本身. </pre>
	 * @see StringUtils#emptyToNull(String)
	 */
	public static final Function<String, String> EMPTY_TO_NULL = new Function<String, String>() {
		@Override
		public String apply(final String str) {
			return StringUtils.emptyToNull(str);
		}
	};
	
	/**
	 * <pre> 若为空字符串 ，则返回指定字符串，否则返回本身. </pre>
	 * 
	 * @param toStr 替换字符串
	 * @return 处理函数
	 * @see StringUtils#emptyTo(String,String)
	 */
	@Nonnull
	public static Function<String, String> emptyTo(@Nullable final String toStr) {
		return new Function<String, String>() {
			@Override
			public String apply(final String str) {
				return StringUtils.emptyTo(str, toStr);
			}
		};
	}
	
	/**
	 * <pre> 若为空白字符串，则返回null，否则返回本身. </pre>
	 * @see StringUtils#blankToNull(String)
	 */
	public static final Function<String, String> BLANK_TO_NULL = new Function<String, String>() {
		@Override
		public String apply(final String str) {
			return StringUtils.blankToNull(str);
		}
	};
	
	/**
	 * <pre> 若为空白字符串，则返回空字符串，否则返回本身. </pre>
	 * @see StringUtils#blankToEmpty(String)
	 */
	public static final Function<String, String> BLANK_TO_EMPTY = new Function<String, String>() {
		@Override
		public String apply(final String str) {
			return StringUtils.blankToEmpty(str);
		}
	};
	
	/**
	 * <pre> 若为空白字符串，则返回指定字符串，否则返回本身. </pre>
	 * 
	 * @param toStr 替换字符串
	 * @return 处理函数
	 * @see StringUtils#blankTo(String,String)
	 */
	@Nonnull
	public static Function<String, String> blankTo(@Nullable final String toStr) {
		return new Function<String, String>() {
			@Override
			public String apply(final String str) {
				return StringUtils.blankTo(str, toStr);
			}
		};
	}
	
	/**
	 * <pre> 将指定字符串替换为特定字符串. </pre>
	 * 
	 * @param matchStr 指定字符串
	 * @param toStr 替换字符串
	 * @return 处理函数
	 * @see StringUtils#to(String,String,String)
	 */
	@Nonnull
	public static Function<String, String> to(@Nullable final String matchStr, @Nullable final String toStr) {
		return new Function<String, String>() {
			@Override
			public String apply(final String str) {
				return StringUtils.to(str, matchStr, toStr);
			}
		};
	}
	
	/**
	 * <pre> 将指定字符串替换为特定字符串（无视大小写）. </pre>
	 * 
	 * @param matchStr 指定字符串
	 * @param toStr 替换字符串
	 * @return 处理函数
	 * @see StringUtils#toIgnoreCase(String,String,String)
	 */
	@Nonnull
	public static Function<String, String> toIgnoreCase(@Nullable final String matchStr, @Nullable final String toStr) {
		return new Function<String, String>() {
			@Override
			public String apply(final String str) {
				return StringUtils.toIgnoreCase(str, matchStr, toStr);
			}
		};
	}
	
	/**
	 * <pre> 移除左右侧的组分隔符.
	 * 仅当两侧都有组分隔符时，才会进行移除操作。
	 * 这里使用默认的组分隔符和逃逸符。</pre>
	 * 
	 * @see StringUtils#trimGroupChar(String)
	 */
	public static final Function<String, String> TRIM_GROUP_CHAR = new Function<String, String>() {
		@Override
		public String apply(final String str) {
			return StringUtils.trimGroupChar(str);
		}
	};
	
	/**
	 * <pre> 移除左右侧的组分隔符.
	 * 仅当两侧都有组分隔符时，才会进行移除操作。 </pre>
	 * 
	 * @param groupChar 组分隔符
	 * @param escapeChar 逃逸符
	 * @return 处理函数
	 * @see StringUtils#trimGroupChar(String,char,char)
	 */
	@Nonnull
	public static Function<String, String> trimGroupChar(@Nonnull final char groupChar, @Nonnull final char escapeChar) {
		return new Function<String, String>() {
			@Override
			public String apply(final String str) {
				return StringUtils.trimGroupChar(str, groupChar, escapeChar);
			}
		};
	}
	
	/**
	 * <pre> 移除逃逸符.
	 * 仅当逃逸符右侧为组分隔符时。
	 * 这里使用默认的组分隔符和逃逸符。</pre>
	 * 
	 * @see StringUtils#clearEscapeChar(String)
	 */
	public static final Function<String, String> CLEAR_ESCAPE_CHAR = new Function<String, String>() {
		@Override
		public String apply(final String str) {
			return StringUtils.clearEscapeChar(str);
		}
	};
	
	/**
	 * <pre> 移除逃逸符.
	 * 仅当逃逸符右侧为组分隔符时。 </pre>
	 * 
	 * @param groupChar 组分隔符
	 * @param escapeChar 逃逸符
	 * @return 处理函数
	 * @see StringUtils#clearEscapeChar(String,char,char)
	 */
	@Nonnull
	public static Function<String, String> clearEscapeChar(@Nonnull final char groupChar, @Nonnull final char escapeChar) {
		return new Function<String, String>() {
			@Override
			public String apply(final String str) {
				return StringUtils.clearEscapeChar(str, groupChar, escapeChar);
			}
		};
	}
	
	/**
	 * <pre> 是否为Null. </pre>
	 */
	public static final Condition<String> IS_NULL = new Condition<String>() {
		@Override
		public boolean match(final String t) {
			return t == null;
		}
	};
	
	/**
	 * <pre> 是否为空. </pre>
	 */
	public static final Condition<String> IS_EMPTY = new Condition<String>() {
		@Override
		public boolean match(final String t) {
			return StringUtils.isEmpty(t);
		}
	};
	
	/**
	 * <pre> 是否为Blank. </pre>
	 */
	public static final Condition<String> IS_BLANK = new Condition<String>() {
		@Override
		public boolean match(final String t) {
			return StringUtils.isBlank(t);
		}
	};
	
	/**
	 * <pre> 是. </pre>
	 * 
	 * @param matchStr 匹配字符串
	 * @return 处理函数
	 * @see StringUtils#clearEscapeChar(String,char,char)
	 */
	@Nonnull
	public static Condition<String> is(@Nullable final String matchStr) {
		return new Condition<String>() {
			@Override
			public boolean match(final String t) {
				return Objects.equals(t, matchStr);
			}
		};
	}
	
	/**
	 * <pre> 是(忽视大小写). </pre>
	 * 
	 * @param matchStr 匹配字符串
	 * @return 处理函数
	 * @see StringUtils#clearEscapeChar(String,char,char)
	 */
	@Nonnull
	public static Condition<String> isIgnoreCase(@Nullable final String matchStr) {
		return new Condition<String>() {
			@Override
			public boolean match(final String t) {
				return StringUtils.equalsIgnoreCase(t, matchStr);
			}
		};
	}
	
}
