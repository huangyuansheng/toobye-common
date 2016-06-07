/*
 * Copyright 2015 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2015/08/31.
 * 
 */
package com.toobye.common.reflect;

import java.io.File;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.toobye.common.lang.Checks;

/**
 * <pre> 类.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2015/08/31  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class SmartClass {

	private SmartClass(@Nonnull final String name, @Nullable final File file) {
		Checks.nullThrow(name);
		this.name = name;
		this.file = file;
	}
	
	/**
	 * <pre> 创建类.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/08/31  huangys  Create
	 * </pre>
	 * 
	 * @param name 类名称
	 * @param file 类文件
	 * @return 类
	 */
	public static SmartClass of(@Nonnull final String name, @Nullable final File file) {
		return new SmartClass(name, file);
	}
	
	/**
	 * <pre> 创建类.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/08/31  huangys  Create
	 * </pre>
	 * 
	 * @param name 类名称
	 * @return 类
	 */
	public static SmartClass of(@Nonnull final String name) {
		return of(name, null);
	}
	
	private String name;
	private File file;
	
	/**
	 * <pre> 获取类名.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/08/31  huangys  Create
	 * </pre>
	 * 
	 * @return 类名
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * <pre> 获取类文件.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/08/31  huangys  Create
	 * </pre>
	 * 
	 * @return 类文件
	 */
	public File getFile() {
		return file;
	}
	
}
