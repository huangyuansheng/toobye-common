/*
 * Copyright 2014 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2014/04/03.
 * 
 */
package com.toobye.common.io;

import java.io.File;
import java.util.Collection;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.toobye.common.base.Systems;
import com.toobye.common.lang.Checks;
import com.toobye.common.string.StringUtils;

/**
 * <pre> 文件名工具类.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2014/04/03  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class Filenames extends org.apache.commons.io.FilenameUtils {
	
	private Filenames() { }
	
	/**
	 * <pre> 方法实现来源.
	 * 
	 * Modification History:
	 * Date        Author   Version   Action
	 * 2014/08/11  huangys  v1.0      Create
	 * </pre>
	 * 
	 */
	private static class INSTANCE extends org.apache.commons.io.FilenameUtils { };
	
	/**
	 * <pre> 校验文件扩展名(无视大小写).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/03  huangys  Create
	 * </pre>
	 * 
	 * @param filename 文件名称
	 * @param extension 扩展名
	 * @return 是否满足
	 */
	@Nonnull
	public static boolean isExtensionIgnoreCase(@Nullable final String filename, @Nullable final String extension) {
		return INSTANCE.isExtension(filename == null ? null : filename.toUpperCase(), extension == null ? null : extension.toUpperCase());
	}
	
	/**
	 * <pre> 校验文件扩展名(大小写敏感).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/03  huangys  Create
	 * </pre>
	 * 
	 * @param file 文件
	 * @param extension 扩展名
	 * @return 是否满足
	 */
	@Nonnull
	public static boolean isExtension(@Nullable final File file, @Nullable final String extension) {
		return INSTANCE.isExtension(file == null ? null : file.getAbsolutePath(), extension);
	}
	
	/**
	 * <pre> 校验文件扩展名(无视大小写).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/03  huangys  Create
	 * </pre>
	 * 
	 * @param file 文件
	 * @param extension 扩展名
	 * @return 是否满足
	 */
	@Nonnull
	public static boolean isExtensionIgnoreCase(@Nullable final File file, @Nullable final String extension) {
		return isExtensionIgnoreCase(file == null ? null : file.getAbsolutePath(), extension);
	}
	
	/**
	 * <pre> 校验文件扩展名(大小写敏感).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/03  huangys  Create
	 * </pre>
	 * 
	 * @param file 文件
	 * @param extensions 扩展名集合
	 * @return 是否满足
	 */
	@Nonnull
	public static boolean isExtension(@Nullable final File file, @Nullable final String[] extensions) {
		return INSTANCE.isExtension(file == null ? null : file.getAbsolutePath(), extensions);
	}
	
	/**
	 * <pre> 校验文件扩展名(大小写敏感).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/03  huangys  Create
	 * </pre>
	 * 
	 * @param file 文件
	 * @param extensions 扩展名集合
	 * @return 是否满足
	 */
	@Nonnull
	public static boolean isExtension(@Nullable final File file, @Nullable final Collection<String> extensions) {
		return INSTANCE.isExtension(file == null ? null : file.getAbsolutePath(), extensions);
	}
	
	/**
	 * <pre> 文件路径与文件名连接.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/03  huangys  Create
	 * </pre>
	 * 
	 * @param basePath 基本目录
	 * @param filename 文件名称
	 * @return 完整文件名
	 */
	@Nullable
	public static String concat(@Nullable final File basePath, @Nullable final String filename) {
		return INSTANCE.concat(basePath == null ? null : basePath.getAbsolutePath(), filename);
	}
	
	/**
	 * <pre> 文件路径与文件名连接.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/03  huangys  Create
	 * </pre>
	 * 
	 * @param basePath 基本目录
	 * @param parts 文件名称片段
	 * @return 完整文件名
	 */
	@Nullable
	public static String concatMore(@Nullable final String basePath, @Nonnull final String... parts) {
		Checks.nullThrow(parts);
		String ret = basePath;
		for (String part : parts) {
			ret = INSTANCE.concat(ret, part);
		}
		return ret;
	}
	
	/**
	 * <pre> 文件路径与文件名连接.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/03  huangys  Create
	 * </pre>
	 * 
	 * @param basePath 基本目录
	 * @param parts 文件名称片段
	 * @return 完整文件名
	 */
	@Nullable
	public static String concatMore(@Nullable final File basePath, @Nonnull final String... parts) {
		Checks.nullThrow(parts);
		String ret = basePath == null ? null : basePath.getAbsolutePath();
		for (String part : parts) {
			ret = INSTANCE.concat(ret, part);
		}
		return ret;
	}
	
	/**
	 * <pre> 文件对应目录与文件名连接.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/03  huangys  Create
	 * </pre>
	 * 
	 * @param basePath 基本目录
	 * @param filename 文件名称
	 * @return 完整文件名
	 */
	@Nonnull
	public static String concatInDirectory(@Nonnull final File basePath, @Nullable final String filename) {
		Checks.nullThrow(basePath);
		return INSTANCE.concat(getFullPath(basePath), filename);
	}
	
	/**
	 * <pre> 文件路径与文件名连接.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/03  huangys  Create
	 * </pre>
	 * 
	 * @param basePath 基本目录
	 * @param filename 文件名称
	 * @return 对应文件
	 */
	@Nullable
	public static File concatToFile(@Nullable final String basePath, @Nullable final String filename) {
		String temp = INSTANCE.concat(basePath, filename);
		return temp == null ? null : new File(temp);
	}
	
	/**
	 * <pre> 文件路径与文件名连接.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/03  huangys  Create
	 * </pre>
	 * 
	 * @param basePath 基本目录
	 * @param filename 文件名称
	 * @return 对应文件
	 */
	@Nullable
	public static File concatToFile(@Nullable final File basePath, @Nullable final String filename) {
		return concatToFile(basePath == null ? null : basePath.getAbsolutePath(), filename);
	}
	
	/**
	 * <pre> 文件对应目录与文件名连接.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/03  huangys  Create
	 * </pre>
	 * 
	 * @param basePath 基本目录
	 * @param filename 文件名称
	 * @return 对应文件
	 */
	@Nullable
	public static File concatInDirectoryToFile(@Nonnull final File basePath, @Nullable final String filename) {
		Checks.nullThrow(basePath);
		return concatToFile(getFullPath(basePath), filename);
	}
	
	/**
	 * <pre> 判断路径是否包含指定的文件或文件夹(相同目录返回false).
	 * 
	 * Error:(null, *)
	 * true:(d:/a, d:/a/b)
	 * false:(d:/a, d:/a)
	 * true:(d:/a, d:/a/b)
	 * false:(*, null)
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/03  huangys  Create
	 * </pre>
	 * 
	 * @param path 目录
	 * @param filename 文件名称
	 * @return 是否满足
	 */
	@Nonnull
	public static boolean directoryContains(@Nonnull final String path, @Nullable final String filename) {
		Checks.nullThrow(path);
		try {
			return INSTANCE.directoryContains(path, filename);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 判断路径是否包含指定的文件或文件夹(相同目录返回false).
	 * 
	 * Error:(null, *)
	 * true:(d:/a, d:/a/b)
	 * false:(d:/a, d:/a)
	 * true:(d:/a, d:/a/b)
	 * false:(*, null)
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/03  huangys  Create
	 * </pre>
	 * 
	 * @param path 目录
	 * @param filename 文件名称
	 * @return 是否满足
	 */
	@Nonnull
	public static boolean directoryContains(@Nonnull final File path, @Nullable final String filename) {
		Checks.nullThrow(path);
		return directoryContains(path.getAbsolutePath(), filename);
	}
	
	/**
	 * <pre> 判断路径是否包含指定的文件或文件夹(相同目录返回false).
	 * 
	 * Error:(null, *)
	 * true:(d:/a, d:/a/b)
	 * false:(d:/a, d:/a)
	 * true:(d:/a, d:/a/b)
	 * false:(*, null)
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/03  huangys  Create
	 * </pre>
	 * 
	 * @param path 目录
	 * @param file 文件
	 * @return 是否满足
	 */
	@Nonnull
	public static boolean directoryContains(@Nonnull final String path, @Nullable final File file) {
		Checks.nullThrow(path);
		return directoryContains(path, file == null ? null : file.getAbsolutePath());
	}
	
	/**
	 * <pre> 判断路径是否包含指定的文件或文件夹(相同目录返回false).
	 * 
	 * Error:(null, *)
	 * true:(d:/a, d:/a/b)
	 * false:(d:/a, d:/a)
	 * true:(d:/a, d:/a/b)
	 * false:(*, null)
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/03  huangys  Create
	 * </pre>
	 * 
	 * @param path 目录
	 * @param file 文件
	 * @return 是否满足
	 */
	@Nonnull
	public static boolean directoryContains(@Nonnull final File path, @Nullable final File file) {
		Checks.nullThrow(path);
		return directoryContains(path.getAbsolutePath(), file);
	}
	
	/**
	 * <pre> 判断是否为同一文件.
	 * 若类型为File，则直接用equals判断即可。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/03  huangys  Create
	 * </pre>
	 * 
	 * @param filename1 文件1
	 * @param filename2 文件2
	 * @return 是否满足
	 */
	@Nonnull
	public static boolean isSameFile(@Nullable final String filename1, @Nullable final String filename2) {
		if (filename1 == null || filename2 == null) {
			return false;
		}
		return new File(filename1).equals(new File(filename2));
	}
	
	/**
	 * <pre> 移除文件扩展名.
	 * 
	 * a\b\c.jpg  --> a\b\c
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/03  huangys  Create
	 * </pre>
	 * 
	 * @param file 文件
	 * @return 移除扩展名的文件名称
	 */
	@Nullable
	public static String removeExtension(@Nullable final File file) {
		return INSTANCE.removeExtension(file == null ? null : file.getAbsolutePath());
	}
	
	/**
	 * <pre> 获得文件基本名称.
     * <pre>
     * a/b/c.txt --> c
     * a.txt     --> a
     * a/b/c     --> c
     * a/b/c/    --> ""
     * </pre>
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/03  huangys  Create
	 * </pre>
	 * 
	 * @param file 文件
	 * @return 文件基本名称
	 */
	@Nullable
	public static String getBaseName(@Nullable final File file) {
		return INSTANCE.getBaseName(file == null ? null : file.getAbsolutePath());
	}
	
	/**
	 * <pre> 获得文件路径前缀.
	 * 
     * <pre>
     * Windows:
     * a\b\c.txt           --> ""          --> relative
     * \a\b\c.txt          --> "\"         --> current drive absolute
     * C:a\b\c.txt         --> "C:"        --> drive relative
     * C:\a\b\c.txt        --> "C:\"       --> absolute
     * \\server\a\b\c.txt  --> "\\server\" --> UNC
     *
     * Unix:
     * a/b/c.txt           --> ""          --> relative
     * /a/b/c.txt          --> "/"         --> absolute
     * ~/a/b/c.txt         --> "~/"        --> current user
     * ~                   --> "~/"        --> current user (slash added)
     * ~user/a/b/c.txt     --> "~user/"    --> named user
     * ~user               --> "~user/"    --> named user (slash added)
     * </pre>
     * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/03  huangys  Create
	 * </pre>
	 * 
	 * @param file 文件
	 * @return 文件路径前缀
	 */
	@Nullable
	public static String getPrefix(@Nullable final File file) {
		return INSTANCE.getPrefix(file == null ? null : file.getAbsolutePath());
	}
	
	/**
	 * <pre> 获得文件扩展名.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/03  huangys  Create
	 * </pre>
	 * 
	 * @param file 文件
	 * @return 扩展名
	 */
	@Nullable
	public static String getExtension(@Nullable final File file) {
		return INSTANCE.getExtension(file == null ? null : file.getAbsolutePath());
	}
	
    /**
     * The Unix separator character.
     */
    private static final char UNIX_SEPARATOR = '/';

    /**
     * The Windows separator character.
     */
    private static final char WINDOWS_SEPARATOR = '\\';

    /**
     * <pre> 是否结尾是文件分隔符.
     * 
     * Modification History:
     * Date        Author   Action
     * 2015/12/17  huangys  Create
     * </pre>
     * 
     * @param path 路径
     * @return 是否
     */
    @Nonnull
    public static boolean isEndSeparator(@Nonnull final String path) {
    	Checks.nullThrow(path);
    	return path.endsWith(UNIX_SEPARATOR + "") || path.endsWith(WINDOWS_SEPARATOR + "");
    }
	
	/**
	 * <pre> 添加目录结尾的文件分隔符.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/03  huangys  Create
	 * </pre>
	 * 
	 * @param path 路径
	 * @return 结尾含文件分隔符的文件名称
	 */
	@Nullable
	public static String addEndSeparator(@Nullable final String path) {
		if (path == null) {
			return null;
		}
		return isEndSeparator(path) ? path : path + File.separatorChar;
	}
	
	/**
	 * <pre> 移除目录结尾的文件分隔符.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/03  huangys  Create
	 * </pre>
	 * 
	 * @param path 路径
	 * @return 结尾不含文件分隔符的文件名称
	 */
	@Nullable
	public static String removeEndSeparator(@Nullable final String path) {
		if (path == null) {
			return null;
		}
		return isEndSeparator(path) ? StringUtils.chop(path) : path;
	}
	
	/**
	 * <pre> 获得对应目录（结尾有文件分隔符）.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/03  huangys  Create
	 * </pre>
	 * 
	 * @param file 文件
	 * @return 目录
	 */
	@Nullable
	public static String getPath(@Nullable final File file) {
		return INSTANCE.getPath(file == null ? null : file.getAbsolutePath());
	}
	
	/**
	 * <pre> 获得对应目录（结尾有文件分隔符）.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/03  huangys  Create
	 * </pre>
	 * 
	 * @param file 文件
	 * @return 目录
	 */
	@Nullable
	public static String getFullPath(@Nullable final File file) {
		return INSTANCE.getFullPath(file == null ? null : file.getAbsolutePath());
	}
	
	/**
	 * <pre> 获得对应目录（结尾有文件分隔符）.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/03  huangys  Create
	 * </pre>
	 * 
	 * @param file 文件
	 * @return 目录
	 */
	@Nullable
	public static String getPathNoEndSeparator(@Nullable final File file) {
		return INSTANCE.getPathNoEndSeparator(file == null ? null : file.getAbsolutePath());
	}
	
	/**
	 * <pre> 获得对应目录（结尾有文件分隔符）.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/03  huangys  Create
	 * </pre>
	 * 
	 * @param file 文件
	 * @return 目录
	 */
	@Nullable
	public static String getFullPathNoEndSeparator(@Nullable final File file) {
		return INSTANCE.getFullPathNoEndSeparator(file == null ? null : file.getAbsolutePath());
	}
	
	/**
	 * <pre> 获取文件.
	 * 
	 * 若文件不存在，则在默认文件路径下查找文件，若仍找不到返回Null。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/11  huangys  Create
	 * </pre>
	 * 
	 * @param filename 文件名称
	 * @param defaultPath 可选默认路径
	 * @return 文件
	 */
	@Nullable
	public static File getFile(@Nullable final String filename, @Nullable final String defaultPath) {
		if (filename == null) {
			return null;
		}
		File f1 = new File(filename);
		if (f1.exists()) {
			return f1;
		} else {
			if (defaultPath == null) {
				return null;
			}
			File f2 = concatToFile(defaultPath, filename);
			if (f2.exists()) {
				return f2;
			} else {
				return null;
			}
		}
	}
	
	/**
	 * <pre> 获取文件名.
	 * 
	 * 若文件不存在，则在默认文件路径下查找文件，若仍找不到返回Null。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/03  huangys  Create
	 * </pre>
	 * 
	 * @param filename 文件名称
	 * @param defaultPath 可选默认路径
	 * @return 文件名称
	 */
	@Nullable
	public static String getFilename(@Nullable final String filename, @Nullable final String defaultPath) {
		File f = getFile(filename, defaultPath);
		return f == null ? null : f.getAbsolutePath();
	}
	
	/**
	 * <pre> 获取文件相对路径.
	 * 
	 * d:/a/b.txt d:/a --> b.txt
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/03/13  huangys  Create
	 * </pre>
	 * 
	 * @param file 文件
	 * @param basePath 相对目录
	 * @return 相对路径
	 */
	@Nullable
	public static String getRelativeFilename(@Nullable final File file, @Nullable final File basePath) {
		if (file == null) {
			return null;
		}
		if (basePath == null) {
			return file.getAbsolutePath();
		} else {
			String fname = file.getAbsolutePath();
			String base = basePath.getAbsolutePath() + File.separatorChar;
			return StringUtils.removeStart(fname, base);
		}
	}
	
	/**
	 * <pre> 判断是否为相对路径.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/03/25  huangys  Create
	 * </pre>
	 * 
	 * @param file 文件名称
	 * @return 是否满足
	 */
	public static boolean isRelativeFilename(final String file) {
		if (file == null) {
			return false;
		}
		if (file.startsWith("/")) {
			return false;
		}
		
		if (Systems.IS_OS_WINDOWS && file.matches("[a-zA-Z]\\:[\\/].*")) {
			return false;
		}
		return true;
	}

}
