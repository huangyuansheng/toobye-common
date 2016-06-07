/*
 * Copyright 2015 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2015/12/20.
 * 
 */
package com.toobye.common.io;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.io.LineIterator;

import com.toobye.common.lang.Checks;
import com.toobye.common.lang.Function;
import com.toobye.common.lang.Pair;
import com.toobye.common.string.StringSplit;
import com.toobye.common.string.StringUtils;

/**
 * <pre> 文件流读取.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2015/12/20  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class IOReader {
	
	private boolean skipBlank = false;
	private String commentPrefix;
	private boolean skipReturnNull = false;
	private String charset;
	
	/**
	 * <pre> 跳过空行，跳过#开头的注释行，跳过空返回值. </pre>
	 */
	public static final IOReader STANDARD = new IOReader(true, "#", true);
	
	/**
	 * <pre> 构造器. </pre>
	 *
	 * @param skipBlank 跳过空行
	 * @param commentPrefix 注解前缀
	 * @param skipReturnNull 是否跳过空返回值
	 */
	public IOReader(@Nonnull final boolean skipBlank, @Nullable final String commentPrefix, @Nonnull final boolean skipReturnNull) {
		this.skipBlank = skipBlank;
		this.skipReturnNull = skipReturnNull;
		this.commentPrefix = commentPrefix;
	}
	
	/**
	 * <pre> 是否跳过空行.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/20  huangys  Create
	 * </pre>
	 * 
	 * @return 是否
	 */
	@Nonnull
	public boolean isSkipBlank() {
		return skipReturnNull;
	}

	/**
	 * <pre> 设置跳过空行选项.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/20  huangys  Create
	 * </pre>
	 * 
	 * @param skipBlank 是否跳过空行
	 */
	public void setSkipBlank(@Nonnull final boolean skipBlank) {
		this.skipBlank = skipBlank;
	}
	
	/**
	 * <pre> 是否跳过空返回值.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/20  huangys  Create
	 * </pre>
	 * 
	 * @return 是否
	 */
	@Nonnull
	public boolean isSkipReturnNull() {
		return skipReturnNull;
	}

	/**
	 * <pre> 设置跳过空返回值选项.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/20  huangys  Create
	 * </pre>
	 * 
	 * @param skipReturnNull 是否跳过空返回值
	 */
	public void setSkipReturnNull(@Nonnull final boolean skipReturnNull) {
		this.skipReturnNull = skipReturnNull;
	}

	/**
	 * <pre>  获得注解前缀.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/20  huangys  Create
	 * </pre>
	 * 
	 * @return 注解前缀
	 */
	@Nullable
	public String getCommentPrefix() {
		return commentPrefix;
	}

	/**
	 * <pre> 设置注解前缀.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/20  huangys  Create
	 * </pre>
	 * 
	 * @param commentPrefix 注解前缀
	 */
	public void setCommentPrefix(@Nullable final String commentPrefix) {
		this.commentPrefix = commentPrefix;
	}

	/**
	 * <pre> 获取字符集.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/11/24  huangys  Create
	 * </pre>
	 * 
	 * @return 字符集
	 */
	@Nullable
	public String getCharset() {
		return charset;
	}

	/**
	 * <pre> 设置字符集.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/11/24  huangys  Create
	 * </pre>
	 * 
	 * @param charset 字符集
	 */
	public void setCharset(@Nullable final String charset) {
		this.charset = charset;
	}

	/**
	 * <pre> 读取元素列表.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/20  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 元素类型
	 * @param is 输入流
	 * @param func 函数
	 * @return 元素列表
	 */
	@Nonnull
	public <T> List<T> readListWithClose(@Nonnull final InputStream is, @Nonnull final Function<String, T> func) {
		List<T> ret = null;
		try {
			Checks.nullThrow(is);
			Checks.nullThrow(func);
			ret = new ArrayList<>();
			LineIterator it = IOs.lineIterator(is, charset);
			while (it.hasNext()) {
				String line = it.next();
				if (skipBlank && StringUtils.isBlank(line)) {
					continue;
				}
				if (commentPrefix != null && (
						(commentPrefix.startsWith(" ") && line.startsWith(commentPrefix)) 
						|| (!commentPrefix.startsWith(" ") && line.trim().startsWith(commentPrefix))
						)) {
					continue;
				}
				T one = func.apply(line);
				if (!skipReturnNull || one != null) {
					ret.add(one);
				}
			}
		} finally {
			IOs.closeQuietly(is);
		}
		return ret;
	}
	
	private static final Function<String, String> FUNC_DUMMY = new Function<String, String>() {
		@Override
		public String apply(final String in) {
			return in;
		}
	};
	/**
	 * <pre> 读取元素列表.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/20  huangys  Create
	 * </pre>
	 * 
	 * @param is 输入流
	 * @return 元素列表
	 */
	@Nonnull
	public List<String> readLinesWithClose(@Nonnull final InputStream is) {
		return readListWithClose(is, FUNC_DUMMY);
	}
	
	/**
	 * <pre> 读取元素列表.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/20  huangys  Create
	 * </pre>
	 * 
	 * @param <K> Key类型
	 * @param <V> Value类型
	 * @param is 输入流
	 * @param func 函数
	 * @param allowDuplicate Map是否允许重复
	 * @return 元素列表
	 */
	@Nonnull
	public <K, V> Map<K, V> readMapWithClose(@Nonnull final InputStream is, @Nonnull final Function<String, Pair<K, V>> func, @Nonnull final boolean allowDuplicate) {
		Map<K, V> ret = null;
		try {
			Checks.nullThrow(is);
			Checks.nullThrow(func);
			ret = new HashMap<>();
			LineIterator it = IOs.lineIterator(is, charset);
			while (it.hasNext()) {
				String line = it.next();
				if (skipBlank && StringUtils.isBlank(line)) {
					continue;
				}
				if (commentPrefix != null && (
						(commentPrefix.startsWith(" ") && line.startsWith(commentPrefix)) 
						|| (!commentPrefix.startsWith(" ") && line.trim().startsWith(commentPrefix))
						)) {
					continue;
				}
				Pair<K, V> one = func.apply(line);
				if (!skipReturnNull || one != null) {
					if (!allowDuplicate) {
						Checks.containsThrow(ret, one.getLeft());
					}
					ret.put(one.getLeft(), one.getRight());
				}
			}
		} finally {
			IOs.closeQuietly(is);
		}
		return ret;
	}

	/**
	 * <pre> 读取Map（简单分隔符）.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/03/28  huangys  Create
	 * </pre>
	 * 
	 * @param is 输入流
	 * @param separator 分隔符
	 * @return map
	 */
	@Nonnull
	public Map<String, String> readMapWithCloseSimpleSeparator(@Nonnull final InputStream is, @Nonnull final String separator) {
		return readMapWithClose(is, new Function<String, Pair<String, String>>() {
			@Override
			public Pair<String, String> apply(final String line) {
				String[] fields = StringSplit.splitString(line, separator, 2);
				return Pair.of(fields[0], fields[1]);
			}
		}, true);
	}
	
	/**
	 * <pre> 读取Map（简单分隔符）.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/03/28  huangys  Create
	 * </pre>
	 * 
	 * @param is 输入流
	 * @param separator 分隔符
	 * @return map
	 */
	@Nonnull
	public Map<String, String> readMapWithCloseSimpleSeparator(@Nonnull final InputStream is, @Nonnull final char separator) {
		return readMapWithCloseSimpleSeparator(is, separator + "");
	}
	
	/**
	 * <pre> 按组读取元素列表.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/20  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 元素类型
	 * @param is 输入流
	 * @param groupCount 每组行数
	 * @param func 函数
	 * @return 元素列表
	 */
	@Nonnull
	public <T> List<T> readGroupWithClose(@Nonnull final InputStream is, @Nonnull final int groupCount, @Nonnull final Function<List<String>, T> func) {
		List<T> ret = null;
		try {
			Checks.nullThrow(is);
			Checks.nullThrow(func);
			ret = new ArrayList<>();
			LineIterator it = IOs.lineIterator(is, charset);
			List<String >group = new ArrayList<>();
			while (it.hasNext()) {
				String line = it.next();
				if (skipBlank && StringUtils.isBlank(line)) {
					continue;
				}
				if (commentPrefix != null && (
						(commentPrefix.startsWith(" ") && line.startsWith(commentPrefix)) 
						|| (!commentPrefix.startsWith(" ") && line.trim().startsWith(commentPrefix))
						)) {
					continue;
				}
				group.add(line);
				if (group.size() == groupCount) {
					T one = func.apply(group);
					if (!skipReturnNull || one != null) {
						ret.add(one);
					}
				}
			}
			if (group.size() == groupCount) {
				T one = func.apply(group);
				if (!skipReturnNull || one != null) {
					ret.add(one);
				}
			} else {
				Checks.throwException("Lines not enough.");
			}
		} finally {
			IOs.closeQuietly(is);
		}
		return ret;
	}
	
	/**
	 * <pre> 按组读取元素列表.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/20  huangys  Create
	 * </pre>
	 * 
	 * @param is 输入流
	 * @param groupCount 每组行数
	 * @return 元素列表
	 */
	@Nonnull
	public List<List<String>> readGroupWithClose(@Nonnull final InputStream is, @Nonnull final int groupCount) {
		return readGroupWithClose(is, groupCount, new Function<List<String>, List<String>>() {
			@Override
			public List<String> apply(final List<String> in) {
				return in;
			}
		});
	}
	
}
