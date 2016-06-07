/*
 * Copyright 2014 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2014/11/24.
 * 
 */
package com.toobye.common.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.toobye.common.string.StringUtils;
import com.toobye.common.time.DateFormat;

/**
 * <pre> 智能文件.
 *
 * Modification History:
 * Date        Author   Version   Action
 * 2014/11/24  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class SmartFile {
	
	/**
	 * <pre> 文件类型.
	 * 
	 * Modification History:
	 * Date        Author   Version   Action
	 * 2014/11/24  huangys  v1.0      Create
	 * </pre>
	 * 
	 */
	public static enum FileType {
		/**
		 * <pre> 本地文件. </pre>
		 */
		LOCAL,
		/**
		 * <pre> Jar包内文件. </pre>
		 */
		JAR
	}
	
	private FileType type;
	private URI uri;
	private boolean findLocalFirst = true;
	
	/**
	 * <pre> 构造器. </pre>
	 *
	 * @param file 文件
	 */
	public SmartFile(@Nonnull final File file) {
		if (!file.exists()) {
			throw new RuntimeException("Cannot find the file(" + file.getAbsolutePath() + ")");
		}
		type = FileType.LOCAL;
		uri = file.toURI();
	}
	
	/**
	 * <pre> 构造器. </pre>
	 * <div style="color:red;font-weight:bold">优先选择本地文件。</div>
	 *
	 * @param filename 文件名称
	 */
	public SmartFile(@Nonnull final String filename) {
		if (!find(filename)) {
			throw new RuntimeException("Cannot find the file(" + filename + ")");
		}
	}
	
	/**
	 * <pre> 构造器. </pre>
	 * <div style="color:red;font-weight:bold">优先选择本地文件、优先查找默认路径。</div>
	 *
	 * @param filename 文件名称
	 * @param defaultPath 默认路径
	 */
	public SmartFile(@Nonnull final String filename, @Nonnull final String defaultPath) {
		if (!find(Filenames.concat(defaultPath, filename))) {
			if (!find(filename)) {
				throw new RuntimeException("Cannot find the file(" + filename + ", " + defaultPath +  ")");
			}
		}
	}
	
	/**
	 * <pre> 构造器. </pre>
	 * <div style="color:red;font-weight:bold">优先选择默认类对应资源文件。</div>
	 * 
	 * @param filename 文件名称
	 * @param defaultClass 路径默认对应类
	 */
	public SmartFile(@Nonnull final String filename, @Nonnull final Class<?> defaultClass) {
		URL url = defaultClass.getResource(filename);
		if (url == null) {
			if (!find(defaultClass.getPackage().getName() + "." + filename) && !find(filename)) {
				throw new RuntimeException("Cannot find the file(" + filename + ", " + defaultClass.getName() + ")");
			}
		} else {
			try {
				uri = url.toURI();
			} catch (URISyntaxException e) {
				throw new RuntimeException(e);
			}
			type = FileType.JAR;
		}
	}
	
	/**
	 * <pre> 文件查找，优先查找本地文件.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/11/24  huangys  Create
	 * </pre>
	 * 
	 * @param filename 文件名称
	 * @return 是否查找到文件
	 */
	private boolean find(@Nonnull final String filename) {
		if (findLocalFirst) {
			return findLocal(filename) ? true : findJar(filename);
		} else {
			return findJar(filename) ? true : findLocal(filename);
		}
	}
	
	/**
	 * <pre> 本地文件查找.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/11/24  huangys  Create
	 * </pre>
	 * 
	 * @param filename
	 * @return
	 */
	private boolean findLocal(@Nonnull final String filename) {
		File f = new File(Filenames.normalize(filename));
		if (f.exists()) {
			uri = f.toURI();
			type = FileType.LOCAL;
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * <pre> 包内文件查找.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/11/24  huangys  Create
	 * </pre>
	 * 
	 * @param filename
	 * @return
	 */
	private boolean findJar(@Nonnull final String filename) {
		String temp = filename.replace('/', '.').replace('\\', '.');
		if (!temp.startsWith(".")) {
			temp = "." + temp;
		}
		URL url = null;
		while (url == null && temp.contains(".")) {
			temp = StringUtils.replaceFirst(temp, ".", "/");
			url = SmartFile.class.getResource(temp);
		}
		if (url == null) {
			return false;
		} else {
			try {
				uri = url.toURI();
			} catch (URISyntaxException e) {
				throw new RuntimeException(e);
			}
			type = FileType.JAR;
			return true;
		}
	}
	
	/**
	 * <pre> 获取文件类型.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/11/24  huangys  Create
	 * </pre>
	 * 
	 * @return 文件类型
	 */
	public FileType getType() {
		return type;
	}

	/**
	 * <pre> 获得输入流.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/11/24  huangys  Create
	 * </pre>
	 * 
	 * @return 输入流
	 */
	public InputStream getInputStream() {
		try {
			return uri.toURL().openStream();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * <pre> 读取行内容.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/22  huangys  Create
	 * </pre>
	 * 
	 * @return 行内容
	 */
	@Nonnull
	public List<String> readLines() {
		return IOs.readLines(getInputStream());
	}
	
	/**
	 * <pre> 读取行内容.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/22  huangys  Create
	 * </pre>
	 * 
	 * @param charset 字符集
	 * @return 行内容
	 */
	@Nonnull
	public List<String> readLines(@Nullable final String charset) {
		return IOs.readLines(getInputStream(), charset);
	}
	
	@Override
	public String toString() {
		return "Type: " + type + "; Uri: " + uri;
	}
	
	/**
	 * <pre> 获取文件资源路径.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/11/24  huangys  Create
	 * </pre>
	 * 
	 * @return 文件资源路径
	 */
	public URI getUri() {
		return uri;
	}
	
	/**
	 * <pre> 获得对应文件.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/11/24  huangys  Create
	 * </pre>
	 * 
	 * @return 文件
	 */
	public File getFile() {
		return new File(uri);
//		switch (type) {
//		case LOCAL:
//			return new File(uri);
//		case JAR:
//			throw new RuntimeException("Jar File Cannot Supported.");
//		default:
//			throw new RuntimeException("Unknown File type.");
//		}
	}

	/**
	 * <pre> 获得文件最后修改时间.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2012/08/15  huangys  Create
	 * </pre>
	 * 
	 * @return 最后修改时间
	 */
	@Nonnull
	public Date getLastModified() {
		try {
			return DateFormat.of(uri.toURL().openConnection().getLastModified());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 是否优先查找本地文件.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/26  huangys  Create
	 * </pre>
	 * 
	 * @return 是否
	 */
	public boolean isFindLocalFirst() {
		return findLocalFirst;
	}

	private static final Map<String, Long> CHANGEABLE_FILES = new HashMap<>();
	/**
	 * <pre> 文件是否被修改.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/02/05  huangys  Create
	 * </pre>
	 * 
	 * @return 文件是否被修改
	 */
	public boolean isChanged() {
		Long tmp = CHANGEABLE_FILES.get(uri.toString());
		long lastMoified = getLastModified().getTime();
		if (tmp == null || !tmp.equals(lastMoified)) {
			CHANGEABLE_FILES.put(uri.toString(), lastMoified);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * <pre> 设置是否优先查找本地文件.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/26  huangys  Create
	 * </pre>
	 * 
	 * @param findLocalFirst 是否优先查找本地文件
	 */
	public void setFindLocalFirst(@Nonnull final boolean findLocalFirst) {
		this.findLocalFirst = findLocalFirst;
	}
	
	/**
	 * <pre>  获取文件.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/22  huangys  Create
	 * </pre>
	 * 
	 * @param filename 文件名
	 * @return 文件
	 */
	@Nonnull
	public static File ofFile(@Nonnull final String filename) {
		return new SmartFile(filename).getFile();
	}
	
	/**
	 * <pre>  获取文件.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/22  huangys  Create
	 * </pre>
	 * 
	 * @param filename 文件名
	 * @param defaultPath 默认路径
	 * @return 文件
	 */
	@Nonnull
	public static File ofFile(@Nonnull final String filename, @Nonnull final String defaultPath) {
		return new SmartFile(filename, defaultPath).getFile();
	}
	
	/**
	 * <pre>  获取文件.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/22  huangys  Create
	 * </pre>
	 * 
	 * @param filename 文件名
	 * @param defaultClass 默认类路径
	 * @return 文件
	 */
	@Nonnull
	public static File ofFile(@Nonnull final String filename, @Nonnull final Class<?> defaultClass) {
		return new SmartFile(filename, defaultClass).getFile();
	}
	
	/**
	 * <pre>  获取输入流.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/22  huangys  Create
	 * </pre>
	 * 
	 * @param filename 文件名
	 * @return 输入流
	 */
	@Nonnull
	public static InputStream ofInputStream(@Nonnull final String filename) {
		return new SmartFile(filename).getInputStream();
	}
	
	/**
	 * <pre>  获取输入流.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/22  huangys  Create
	 * </pre>
	 * 
	 * @param filename 文件名
	 * @param defaultPath 默认路径
	 * @return 输入流
	 */
	@Nonnull
	public static InputStream ofInputStream(@Nonnull final String filename, @Nonnull final String defaultPath) {
		return new SmartFile(filename, defaultPath).getInputStream();
	}
	
	/**
	 * <pre>  获取输入流.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/22  huangys  Create
	 * </pre>
	 * 
	 * @param filename 文件名
	 * @param defaultClass 默认类路径
	 * @return 输入流
	 */
	@Nonnull
	public static InputStream ofInputStream(@Nonnull final String filename, @Nonnull final Class<?> defaultClass) {
		return new SmartFile(filename, defaultClass).getInputStream();
	}
	
	/**
	 * <pre>  获取文件行内容.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/22  huangys  Create
	 * </pre>
	 * 
	 * @param filename 文件名
	 * @return 文件行内容
	 */
	@Nonnull
	public static List<String> ofLines(@Nonnull final String filename) {
		return new SmartFile(filename).readLines();
	}
	
	/**
	 * <pre>  获取文件行内容.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/22  huangys  Create
	 * </pre>
	 * 
	 * @param filename 文件名
	 * @param defaultPath 默认路径
	 * @return 文件行内容
	 */
	@Nonnull
	public static List<String> ofLines(@Nonnull final String filename, @Nonnull final String defaultPath) {
		return new SmartFile(filename, defaultPath).readLines();
	}
	
	/**
	 * <pre>  获取文件行内容.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/22  huangys  Create
	 * </pre>
	 * 
	 * @param filename 文件名
	 * @param defaultClass 默认类路径
	 * @return 文件行内容
	 */
	@Nonnull
	public static List<String> ofLines(@Nonnull final String filename, @Nonnull final Class<?> defaultClass) {
		return new SmartFile(filename, defaultClass).readLines();
	}
	
	/**
	 * <pre>  获取文件行内容.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/22  huangys  Create
	 * </pre>
	 * 
	 * @param filename 文件名
	 * @param charset 字符集
	 * @return 文件行内容
	 */
	@Nonnull
	public static List<String> ofLinesAssignedCharset(@Nonnull final String filename, @Nullable final String charset) {
		return new SmartFile(filename).readLines(charset);
	}
	
	/**
	 * <pre>  获取文件行内容.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/22  huangys  Create
	 * </pre>
	 * 
	 * @param filename 文件名
	 * @param defaultPath 默认路径
	 * @param charset 字符集
	 * @return 文件行内容
	 */
	@Nonnull
	public static List<String> ofLinesAssignedCharset(@Nonnull final String filename, @Nonnull final String defaultPath, @Nullable final String charset) {
		return new SmartFile(filename, defaultPath).readLines(charset);
	}
	
	/**
	 * <pre>  获取文件行内容.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/22  huangys  Create
	 * </pre>
	 * 
	 * @param filename 文件名
	 * @param defaultClass 默认类路径
	 * @param charset 字符集
	 * @return 文件行内容
	 */
	@Nonnull
	public static List<String> ofLinesAssignedCharset(@Nonnull final String filename, @Nonnull final Class<?> defaultClass, @Nullable final String charset) {
		return new SmartFile(filename, defaultClass).readLines(charset);
	}
	
}
