/*
 * Copyright 2014 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2014/01/07.
 * 
 */
package com.toobye.common.io;

import java.io.File;
import java.util.Map;

import javax.annotation.Nonnull;

import com.toobye.common.codec.Cipher;
import com.toobye.common.lang.Checks;
import com.toobye.common.lang.Function;
import com.toobye.common.lang.Pair;
import com.toobye.common.string.StringUtils;

/**
 * <pre> 属性文件（制表符将被移除，故制表符可作为格式美化）.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2014/01/07  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class PropertyFile {
	
	private PropertyFile() { }
	
	/**
	 * <pre> 解析属性.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/06/07  huangys  Create
	 * </pre>
	 * 
	 * @param filename 属性文件名称
	 * @return 属性Map
	 */
	@Nonnull
	public static Map<String, String> parse(@Nonnull final String filename) {
		return parse(new SmartFile(filename));
	}
	
	/**
	 * <pre> 解析属性.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/06/07  huangys  Create
	 * </pre>
	 * 
	 * @param file 属性文件
	 * @return 属性Map
	 */
	@Nonnull
	public static Map<String, String> parse(@Nonnull final File file) {
		return parse(new SmartFile(file));
	}
	
	/**
	 * <pre> 解析属性.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/06/07  huangys  Create
	 * </pre>
	 * 
	 * @param file 属性文件
	 * @return 属性Map
	 */
	@Nonnull
	public static Map<String, String> parse(@Nonnull final SmartFile file) {
		return IOReader.STANDARD.readMapWithClose(file.getInputStream(), new Function<String, Pair<String, String>>() {
			@Override
			public Pair<String, String> apply(final String line) {
				String temp = line.trim();
				int pos = temp.indexOf('=');
				if (pos > 0) {
					String name = StringUtils.trimAnyChar(temp.substring(0, pos), "\t");
					String value = StringUtils.trimAnyChar(temp.substring(pos + 1), "\t");
					Checks.matchThrow(StringUtils.isBlank(name) || StringUtils.isBlank(value), "Property setting error(" + temp + ").");
					// 内容解密
					value = value.trim();
					if (value.toUpperCase().startsWith("ENCRYPTED ")) {
						value = Cipher.ofSystem().decrypt(value.substring(10).trim());
					}
					return Pair.of(name.trim(), value);
				} else {
					throw new RuntimeException("Property setting error(" + temp + ").");
				}
			}
		}, false);
	}
	
}