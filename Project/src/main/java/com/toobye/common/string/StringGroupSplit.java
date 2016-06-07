/*
 * Copyright 2014 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2014/01/09.
 * 
 */
package com.toobye.common.string;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.toobye.common.lang.Checks;

/**
 * <pre> 字符串拆分.
 * 逃逸符仅对组分隔符有效
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2014/01/14  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class StringGroupSplit {
	
	private static final String[] EMPTY_RESULT = {""};
	/**
	 * <pre> 默认组分隔符. </pre>
	 */
	public static final char DEFAULT_GROUP_CHAR = '"';
	/**
	 * <pre> 默认逃逸符. </pre>
	 */
	public static final char DEFAULT_ESCAPE_CHAR = '\\';

	private String str;
	private String separator;
	private char groupChar;
	private char escapeChar;
	private String[] result;
	private int resultSize;
	
	// 智能空格容错机制
	private boolean spaceIntelligent = false;
	// 分隔符是否为正则表达式
	private boolean isRegexSeparator;
	
	// 处理过程中的全局临时变量
	private int index = 0;
	private int strLength;
	private boolean groupEnable = false;
	private StringBuilder field = new StringBuilder();
	
	/**
	 * <pre> 返回拆分结果.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/14  huangys  Create
	 * </pre>
	 * 
	 * @return 拆分后字符串数组
	 */
	public String[] getResult() {
		return result;
	}
	
	/**
	 * <pre> 拆分字符串. </pre>
	 * 
	 * 组分隔符默认为双引号
	 * 逃逸符默认为右斜杠
	 *
	 * @param str 字符串（非正则表达式）
	 * @param separator 分隔符
	 * @return 字符串数组加工类，可以对结果进行加工
	 */
	public static StringArray split(@Nullable final String str, @Nonnull final String separator) {
		return split(str, separator, DEFAULT_GROUP_CHAR, DEFAULT_ESCAPE_CHAR, false);
	}
	
	/**
	 * <pre> 拆分字符串. </pre>
	 * 
	 * 组分隔符默认为双引号
	 * 逃逸符默认为右斜杠
	 *
	 * @param str 字符串（非正则表达式）
	 * @param separator 分隔符
	 * @param resultSize 返回结果的最大元素个数
	 * @return 字符串数组加工类，可以对结果进行加工
	 */
	public static StringArray split(@Nullable final String str, @Nonnull final String separator, @Nonnull final int resultSize) {
		return split(str, separator, DEFAULT_GROUP_CHAR, DEFAULT_ESCAPE_CHAR, resultSize);
	}

	/**
	 * <pre> 拆分字符串. </pre>
	 * 
	 * 组分隔符默认为双引号
	 * 逃逸符默认为右斜杠
	 *
	 * @param str 字符串
	 * @param separator 分隔符
	 * @param separatorIsRegex 是否为正则表达式
	 * @return 字符串数组加工类，可以对结果进行加工
	 */
	public static StringArray split(@Nullable final String str, @Nonnull final String separator, @Nonnull final boolean separatorIsRegex) {
		return split(str, separator, DEFAULT_GROUP_CHAR, DEFAULT_ESCAPE_CHAR, separatorIsRegex);
	}
	
	/**
	 * <pre> 拆分字符串. </pre>
	 * 
	 * 组分隔符默认为双引号
	 * 逃逸符默认为右斜杠
	 *
	 * @param str 字符串
	 * @param separator 分隔符
	 * @param resultSize 返回结果的最大元素个数
	 * @param separatorIsRegex 分隔符是否为正则表达式
	 * @return 字符串数组加工类，可以对结果进行加工
	 */
	public static StringArray split(@Nullable final String str, @Nonnull final String separator, @Nonnull final int resultSize, @Nonnull final boolean separatorIsRegex) {
		return split(str, separator, DEFAULT_GROUP_CHAR, DEFAULT_ESCAPE_CHAR, resultSize, separatorIsRegex);
	}
	
	/**
	 * <pre> 拆分字符串. </pre>
	 *
	 * @param str 字符串（非正则表达式）
	 * @param separator 分隔符
	 * @param groupChar 组分隔符
	 * @param escapeChar 逃逸符，仅对组分隔符有效
	 * @return 字符串数组加工类，可以对结果进行加工
	 */
	public static StringArray split(@Nullable final String str, @Nonnull final String separator, @Nonnull final char groupChar, @Nonnull final char escapeChar) {
		return split(str, separator, groupChar, escapeChar, false);
	}
	
	/**
	 * <pre> 拆分字符串. </pre>
	 *
	 * @param str 字符串（非正则表达式）
	 * @param separator 分隔符
	 * @param groupChar 组分隔符
	 * @param escapeChar 逃逸符，仅对组分隔符有效
	 * @param resultSize 返回结果的最大元素个数
	 * @return 字符串数组加工类，可以对结果进行加工
	 */
	public static StringArray split(@Nullable final String str, @Nonnull final String separator, @Nonnull final char groupChar, @Nonnull final char escapeChar, @Nonnull final int resultSize) {
		return split(str, separator, groupChar, escapeChar, resultSize, false);
	}
	
	/**
	 * <pre> 拆分字符串. </pre>
	 *
	 * @param str 字符串（非正则表达式）
	 * @param separator 分隔符
	 * @param groupChar 组分隔符
	 * @param escapeChar 逃逸符，仅对组分隔符有效
	 * @param separatorIsRegex 分隔符是否为正则表达式
	 * @return 字符串数组加工类，可以对结果进行加工
	 */
	public static StringArray split(@Nullable final String str, @Nonnull final String separator, @Nonnull final char groupChar, @Nonnull final char escapeChar, @Nonnull final boolean separatorIsRegex) {
		return split(str, separator, groupChar, escapeChar, 0, separatorIsRegex);
	}
	
	/**
	 * <pre> 拆分字符串. </pre>
	 *
	 * @param str 字符串（非正则表达式）
	 * @param separator 分隔符
	 * @param groupChar 组分隔符
	 * @param escapeChar 逃逸符，仅对组分隔符有效
	 * @param separatorIsRegex 分隔符是否为正则表达式
	 * @param resultSize 返回结果的最大元素个数
	 * @return 字符串数组加工类，可以对结果进行加工
	 */
	public static StringArray split(@Nullable final String str, @Nonnull final String separator, @Nonnull final char groupChar, @Nonnull final char escapeChar, @Nonnull final int resultSize, @Nonnull final boolean separatorIsRegex) {
		return StringArray.of(new StringGroupSplit(str, separator, groupChar, escapeChar, resultSize, separatorIsRegex).getResult());
	}
	
	/**
	 * <pre> 构造器. </pre>
	 *
	 * @param str 字符串（非正则表达式）
	 * @param separator 分隔符
	 * @param groupChar 组分隔符
	 * @param escapeChar 逃逸符，仅对组分隔符有效
	 * @param separatorIsRegex 分隔符是否为正则表达式
	 * @param resultSize 返回结果的最大元素个数
	 */
	private StringGroupSplit(@Nullable final String str, @Nonnull final String separator, @Nonnull final char groupChar, @Nonnull final char escapeChar, @Nonnull final int resultSize, @Nonnull final boolean separatorIsRegex) {
		this.str = str;
		this.separator = separator;
		this.groupChar = groupChar;
		this.escapeChar = escapeChar;
		this.resultSize = resultSize;
		isRegexSeparator = separatorIsRegex;
		// 检查参数
		checkParameters();
		// 特殊处理
		if (str == null) {
			result = null;
		} else if (str.equals("")) {
			result = EMPTY_RESULT;
		} else {
			strLength = str.length();
			splitGroup();
		}
	}
	
	/**
	 * <pre> 检查参数.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/14  huangys  Create
	 * </pre>
	 * 
	 */
	private void checkParameters() {
		Checks.matchThrow(groupChar == escapeChar, "GroupChar is the same as escapeChar.");
		if (!isRegexSeparator) {
			// 检查参数间是否有冲突
			Checks.nullThrow(separator, "Separator cannot be null.");
			Checks.matchThrow(separator.contains(groupChar + ""), "Separator cannot cantain groupChar.");
			Checks.matchThrow(separator.contains(escapeChar + ""), "Separator cannot cantain escapeChar.");
			// 智能空格容错机制
			if (!(separator.contains(" ") || groupChar == ' ' || escapeChar == ' ')) {
				spaceIntelligent = true;
			}
		}
	}
	
	/**
	 * <pre> 判断是否为分隔符.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/14  huangys  Create
	 * </pre>
	 * 
	 * @param str
	 * @param separator
	 * @return
	 */
	private boolean isSeparator() {
		if (isRegexSeparator) {
			if (index >= strLength) {
				return false;
			}
			String temp = "^(" + separator + ").*$";
			if (str.substring(index).matches(temp)) {
				index = index + StringRE.getOneGroupFirst(str.substring(index), temp).length() - 1;
				return true;
			} else {
				return false;
			}
		} else {
			for (int i = 0; i < separator.length(); i++) {
				if (index + i >= strLength) {
					return false;
				}
				if (!(str.charAt(index + i) == separator.charAt(i))) {
					return false;
				}
			}
			index = index + separator.length() - 1;
			return true;
		}
	}
	
	/**
	 * <pre> 分组符结束时，判断是否为分隔符.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/14  huangys  Create
	 * </pre>
	 * 
	 * @param str
	 * @param separator
	 * @return
	 */
	private boolean isSeparatorGroupClose() {
		// 保留定位
		int old = index;
		if (isRegexSeparator) {
			index++;
			boolean ret = isSeparator();
			if (!ret) {
				index = old;
			}
			return ret;
		} else {
			// 空格的容错机制
			if (spaceIntelligent) {
				do {
					index++;
					if (index >= strLength) {
						return false;
					}
				} while (str.charAt(index) == ' ');
			} else {
				index++;
			}
			// 判断结尾是否分隔符，若不是则定位回退到原始位置
			boolean ret = isSeparator();
			if (!ret) {
				index = old;
			}
			return ret;
		}
	}
	
	/**
	 * <pre> 保存运算过程中的结果.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/14  huangys  Create
	 * </pre>
	 * 
	 * @param resList 结果list
	 */
	private void addToList(@Nonnull final List<String> resList) {
		if (field.length() > 0) {
			resList.add(field.toString());
			field.setLength(0);
		} else {
			resList.add("");
		}
		groupEnable = false;
	}
	
	private void splitGroup() {
		// 初始化返回结果集
		List<String> resList = new ArrayList<String>();
		int len = strLength;
		for (index = 0; index < len; index++) {
			// 取出每个字符
			char c = str.charAt(index);
			// 组分隔符未打开、且后续内容为分隔符时，结束该次拆分
			if (!groupEnable && isSeparator()) {
				addToList(resList);
				if (resultSize != 0 && resList.size() >= resultSize) {
					break;
				}
			} else if (c == groupChar) {
				field.append(c);
				if (groupEnable) {
					if (isSeparatorGroupClose()) {
						addToList(resList);
						if (resultSize != 0 && resList.size() >= resultSize) {
							break;
						}
					}
				} else {
					if (field.length() == 1) {
						groupEnable = true;
					} else if (field.toString().trim().equals("" + groupChar)) {
						groupEnable = true;
						field.setLength(0);
						field.append(groupChar);
					}
				}
			} else if (c == escapeChar) {
				field.append(escapeChar);
				// 转义仅对组分隔符有效
				if (index + 1 >= strLength) {
					continue;
				} else if (str.charAt(index + 1) == groupChar) {
					field.append(groupChar);
					index++;
				}
				// // 遇到转义字符，连取两个字符
				// field.append(c);
				// field.append(str.charAt(index + 1));
				// index++;
			} else {
				// 通常
				field.append(c);
			}
		}
		if (resultSize == 0 || resList.size() < resultSize) {
			addToList(resList);
		}
		// 返回结果集
		result = resList.toArray(new String[resList.size()]);
	}

	/**
	 * <pre> 使用空白字符作为分隔符，拆分字符串.
	 * 自动忽略字符串起始、末尾的空白字符。
	 * 
	 * 组分隔符："
	 * 逃逸符：\
	 * 逃逸符仅对组分隔符有效
	 * 
	 * 对内容为"null"的元素直接转为null
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/15  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @return 拆分后的字符串数组
	 */
	public static String[] splitWhitespace(final String str) {
		String temp = str;
		// 移除起始的空白字符
		if (temp.matches("^(\\s+).*$")) {
			temp = temp.substring(StringRE.getOneGroupFirst(temp, "^(\\s+).*").length());
		}
		// 移除末尾的空白字符
		if (temp.matches("^.*(\\s+)$")) {
			temp = temp.substring(0, temp.length() - StringRE.getOneGroupFirst(temp, "^.*(\\s+)$").length() - 1);
		}
		return split(temp, "\\s+", true).trimGroupChar().nullStringToNull().clearEscapeChar().getArray();
	}
	
}
