/*
 * Copyright 2014 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2014/03/26.
 * 
 */
package com.toobye.common.reflect;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.reflections.scanners.ResourcesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import com.toobye.common.string.StringUtils;

/**
 * <pre> 反射工具类.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2014/03/26  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class Reflections {
	
	private Reflections() { }
	
	/**
	 * <pre> 返回包内指定类的所有子类(遍历子包).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/03/26  huangys  Create
	 * </pre>
	 * 
	 * @param packageName 包名，如com.toobye.tools或者com/toobye/tools
	 * @param cls 父类
	 * @param <T> 继承指定类
	 * @return 所有子类
	 */
	@Nonnull
	public static <T> Set<Class<? extends T>> getSubTypesOf(@Nonnull final String packageName, @Nonnull final Class<T> cls) {
		return new org.reflections.Reflections(packageName).getSubTypesOf(cls);
	}
	
	/**
	 * <pre> 所有资源文件（不含子包）.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/05/02  huangys  Create
	 * </pre>
	 * 
	 * @param packageName 包名，如com.toobye.tools或者com/toobye/tools
	 * @return 所有资源文件
	 */
	@Nonnull
	public static Set<String> getAllResources(@Nonnull final String packageName) {
		return getAllResources(packageName, false);
	}
	
	/**
	 * <pre> 所有资源文件.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/05/02  huangys  Create
	 * </pre>
	 * 
	 * @param packageName 包名，如com.toobye.tools或者com/toobye/tools
	 * @param includeSub 是否包含子包
	 * @return 所有资源文件
	 */
	@Nonnull
	public static Set<String> getAllResources(@Nonnull final String packageName, @Nonnull final boolean includeSub) {
		return getResources(packageName, null, includeSub);
	}
	
	/**
	 * <pre> 匹配的资源文件（不含子包）.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/05/02  huangys  Create
	 * </pre>
	 * 
	 * @param packageName 包名，如com.toobye.tools或者com/toobye/tools
	 * @param regex 匹配正则表达式
	 * @return 所有符合正则表达式的资源文件
	 */
	@Nonnull
	public static Set<String> getResources(@Nonnull final String packageName, @Nullable final String regex) {
		return getResources(packageName, regex, false);
	}
	
	/**
	 * <pre> 匹配的资源文件.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/05/02  huangys  Create
	 * </pre>
	 * 
	 * @param packageName 包名，如com.toobye.tools或者com/toobye/tools
	 * @param regex 匹配正则表达式
	 * @param includeSub 是否包含子包
	 * @return 所有符合正则表达式的资源文件
	 */
	@Nonnull
	public static Set<String> getResources(@Nonnull final String packageName, @Nullable final String regex, @Nonnull final boolean includeSub) {
		org.reflections.Reflections reflections = new org.reflections.Reflections(
				new ConfigurationBuilder()
					.addUrls(ClasspathHelper.forPackage(packageName))
					.setScanners(new ResourcesScanner()));
		 Set<String> set = reflections.getResources(Pattern.compile(regex == null ? ".*" : regex));
		String prefix = packageName.replace(".", "/") + "/";
		prefix = prefix.replace("\\", "/");
		if (prefix.startsWith("/")) {
			prefix = prefix.substring(1);
		}
		Set<String> ret = new HashSet<String>();
		for (String filename : set) {
			if (filename.startsWith(prefix)
					&& (includeSub || !StringUtils.removeStart(filename, prefix).contains("/"))) {
				ret.add(filename);
			}
		}
		return ret;
	}
	
}
