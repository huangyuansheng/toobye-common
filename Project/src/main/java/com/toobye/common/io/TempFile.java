/*
 * Copyright 2013 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2013/12/20.
 * 
 */
package com.toobye.common.io;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.toobye.common.base.Systems;
import com.toobye.common.lang.Checks;

/**
 * <pre> 临时文件.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2013/12/20  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class TempFile implements AutoCloseable {
	
	private static final String PREFIX = Systems.getIdentity();
	private static final String SUFFIX = ".tmp";
	
	private File tempFile;
	
	/**
	 * <pre> 临时文件. </pre>
	 *
	 */
	public TempFile() {
		this(PREFIX, SUFFIX);
	}
	
	/**
	 * <pre> 临时文件. </pre>
	 *
	 * @param suffix 文件名后缀
	 */
	public TempFile(@Nullable final String suffix) {
		this(PREFIX, suffix);
	}
	
	/**
	 * <pre> 临时文件. </pre>
	 *
	 * @param prefix 文件名前缀
	 * @param suffix 文件名后缀
	 */
	public TempFile(@Nonnull final String prefix, @Nullable final String suffix) {
		Checks.nullThrow(prefix);
		try {
			this.tempFile = File.createTempFile(prefix, suffix);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 写入文本.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/31  huangys  Create
	 * </pre>
	 * 
	 * @param text 文本
	 */
	public void writeString(final String text) {
		Files.writeString(tempFile, text);
	}
	
	/**
	 * <pre> 写入行集合.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/31  huangys  Create
	 * </pre>
	 * 
	 * @param lines 行集合
	 */
	public void writeLines(final Collection<?> lines) {
		Files.writeLines(tempFile, lines);
	}
	
	/**
	 * <pre> 写入字节数组.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/31  huangys  Create
	 * </pre>
	 * 
	 * @param bytes 字节数组
	 */
	public void writeByteArray(final byte[] bytes) {
		Files.writeByteArray(tempFile, bytes);
	}
	
	/**
	 * <pre> 获得临时文件.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/12/20  huangys  Create
	 * </pre>
	 * 
	 * @return 临时文件
	 */
	@Nonnull
	public File getFile() {
		return this.tempFile;
	}

	@Override
	@edu.umd.cs.findbugs.annotations.SuppressFBWarnings("RV_RETURN_VALUE_IGNORED_BAD_PRACTICE")
	public void close() {
		this.tempFile.delete();
	}

}
