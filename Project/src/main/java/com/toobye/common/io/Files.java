/*
 * Copyright 2013 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2013/08/11.
 * 
 */
package com.toobye.common.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.io.LineIterator;
import org.apache.commons.io.input.ReversedLinesFileReader;

import com.toobye.common.base.Systems;
import com.toobye.common.collection.Arrays;
import com.toobye.common.collection.Lists;
import com.toobye.common.concurrent.TimeSlice;
import com.toobye.common.lang.Checks;
import com.toobye.common.lang.Doable;
import com.toobye.common.time.DateFormat;

/**
 * <pre> 文件工具类.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2013/08/11  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class Files extends org.apache.commons.io.FileUtils {
	
	private Files() { }
	
	/**
	 * <pre> 方法实现来源.
	 * 
	 * Modification History:
	 * Date        Author   Version   Action
	 * 2014/08/11  huangys  v1.0      Create
	 * </pre>
	 * 
	 */
	private static class INSTANCE extends org.apache.commons.io.FileUtils { };
	
	/**
	 * <pre> 行分隔符. </pre>
	 */
	public static final String LINE_SEPARATOR = IOs.LINE_SEPARATOR;
	
	/**
	 * <pre> 文件是否存在.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/17  huangys  Create
	 * </pre>
	 * 
	 * @param filename 文件名称
	 * @return 是否存在
	 */
	@Nonnull
	public static boolean exists(@Nullable final String filename) {
		return exists(filename == null ? null : new File(filename));
	}
	
	/**
	 * <pre> 文件是否存在.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/17  huangys  Create
	 * </pre>
	 * 
	 * @param file 文件
	 * @return 是否存在
	 */
	@Nonnull
	public static boolean exists(@Nullable final File file) {
		return file != null && file.exists();
	}
	
	/**
	 * <pre> 是否为空文件夹.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/17  huangys  Create
	 * </pre>
	 * 
	 * @param filename 文件名称
	 * @return 是否空文件夹
	 */
	@Nonnull
	public static boolean isEmptyDir(@Nonnull final String filename) {
		Checks.emptyThrow(filename);
		return isEmptyDir(new File(filename));
	}
	
	/**
	 * <pre> 是否为空文件夹.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/17  huangys  Create
	 * </pre>
	 * 
	 * @param file 文件
	 * @return 是否空文件夹
	 */
	@Nonnull
	public static boolean isEmptyDir(@Nonnull final File file) {
		Checks.fileNotExistsThrow(file);
		return file.isDirectory() && file.listFiles().length == 0;
	}
	
	/**
	 * <pre> 模拟Touch命令.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/11  huangys  Create
	 * </pre>
	 * 
	 * @param filename 文件名称
	 */
	public static synchronized void touch(@Nonnull final String filename) {
		Checks.nullThrow(filename);
		touch(new File(filename));
	}
	
	/**
	 * <pre> 模拟Touch命令.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/11  huangys  Create
	 * </pre>
	 * 
	 * @param file 文件
	 */
	public static synchronized void touch(@Nonnull final File file) {
		Checks.nullThrow(file);
		try {
			INSTANCE.touch(file);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 创建文件夹.
	 * 接入时，应单线程接入，避免并发时出现的异常
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/11  huangys  Create
	 * </pre>
	 * 
	 * @param filename 文件名称
	 */
	public static synchronized void forceMkdir(@Nonnull final String filename) {
		Checks.emptyThrow(filename);
		forceMkdir(new File(filename));
	}
	
	/**
	 * <pre> 创建文件夹.
	 * 接入时，应单线程接入，避免并发时出现的异常
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/11  huangys  Create
	 * </pre>
	 * 
	 * @param file 文件
	 */
	public static synchronized void forceMkdir(@Nonnull final File file) {
		Checks.nullThrow(file);
		try {
			INSTANCE.forceMkdir(file);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 创建临时目录.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/09  huangys  Create
	 * </pre>
	 * 
	 * @return 临时目录
	 */
	@Nonnull
	public static synchronized File createTempDir() {
		return com.google.common.io.Files.createTempDir();
	}
	
	/**
	 * <pre> 创建临时文件.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/09  huangys  Create
	 * </pre>
	 * 
	 * @return 临时文件
	 */
	@SuppressWarnings("resource")
	@Nonnull
	public static synchronized File createTempFile() {
		return new TempFile().getFile();
	}
	
	/**
	 * <pre> 创建临时文件.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/09  huangys  Create
	 * </pre>
	 * 
	 * @param suffix 文件后缀
	 * @return 临时文件
	 */
	@SuppressWarnings("resource")
	@Nonnull
	public static synchronized File createTempFile(@Nullable final String suffix) {
		return new TempFile(suffix).getFile();
	}
	
	/**
	 * <pre> 创建临时文件.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/09  huangys  Create
	 * </pre>
	 * 
	 * @param prefix 文件前缀
	 * @param suffix 文件后缀
	 * @return 临时文件
	 */
	@SuppressWarnings("resource")
	@Nonnull
	public static synchronized File createTempFile(@Nonnull final String prefix, @Nullable final String suffix) {
		return new TempFile(prefix, suffix).getFile();
	}
	
	/**
	 * <pre> 在临时文件夹${java.io.tmpdir}下创建临时目录.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/09  huangys  Create
	 * </pre>
	 * 
	 * @param dirBaseName 目录基本名称
	 * @return 临时目录
	 */
	@Nonnull
	public static synchronized File createTempDir(@Nonnull final String dirBaseName) {
		String dir = Filenames.concat(Systems.getJavaIoTmpDir(), dirBaseName);
		forceMkdir(dir);
		return new File(dir);
	}
	
	/**
	 * <pre> 获得文件最后修改时间.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2012/08/15  huangys  Create
	 * </pre>
	 * 
	 * @param filename 文件名称
	 * @return 最后修改时间
	 */
	@Nonnull
	public static Date getLastModified(@Nonnull final String filename) {
		Checks.nullThrow(filename);
		return getLastModified(new File(filename));
	}
	
	/**
	 * <pre> 获得文件最后修改时间.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2012/08/15  huangys  Create
	 * </pre>
	 * 
	 * @param file 文件
	 * @return 最后修改时间
	 */
	@Nonnull
	public static Date getLastModified(@Nonnull final File file) {
		Checks.fileNotExistsThrow(file);
		return DateFormat.of(file.lastModified());
	}
	
	/**
	 * <pre> 拷贝目录.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/13  huangys  Create
	 * </pre>
	 * 
	 * @param srcDir 源目录
	 * @param destDir 目标目录
	 */
	public static void copyDirectory(@Nonnull final File srcDir, @Nonnull final File destDir) {
		Checks.fileNotExistsThrow(srcDir);
		Checks.nullThrow(destDir);
		try {
			INSTANCE.copyDirectory(srcDir, destDir);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 拷贝文件.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/13  huangys  Create
	 * </pre>
	 * 
	 * @param srcFile 源文件
	 * @param destFile 目标文件
	 */
	public static void copyFile(@Nonnull final File srcFile, @Nonnull final File destFile) {
		Checks.fileNotExistsThrow(srcFile);
		Checks.nullThrow(destFile);
		try {
			INSTANCE.copyFile(srcFile, destFile);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 拷贝文件至指定目录下.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/13  huangys  Create
	 * </pre>
	 * 
	 * @param srcFile 源文件
	 * @param destDir 目标目录
	 */
	public static void copyFileToDirectory(@Nonnull final File srcFile, @Nonnull final File destDir) {
		Checks.fileNotExistsThrow(srcFile);
		Checks.fileNotExistsThrow(destDir);
		try {
			INSTANCE.copyFileToDirectory(srcFile, destDir);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 拷贝目录至指定目录下.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/13  huangys  Create
	 * </pre>
	 * 
	 * @param srcDir 源目录
	 * @param destDir 目标目录
	 */
	public static void copyDirectoryToDirectory(@Nonnull final File srcDir, @Nonnull final File destDir) {
		Checks.fileNotExistsThrow(srcDir);
		Checks.fileNotExistsThrow(destDir);
		try {
			INSTANCE.copyDirectoryToDirectory(srcDir, destDir);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 删除目录（含其下所有文件和子目录）.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/13  huangys  Create
	 * </pre>
	 * 
	 * @param directory 目录
	 */
	public static void deleteDirectory(@Nonnull final File directory) {
		Checks.nullThrow(directory);
		try {
			INSTANCE.deleteDirectory(directory);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 删除文件或目录（含其下所有文件和子目录）.
	 * 删除失败时将抛出异常。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/13  huangys  Create
	 * </pre>
	 * 
	 * @param file 文件/目录
	 */
	public static void deleteForce(@Nonnull final File file) {
		Checks.nullThrow(file);
		try {
			INSTANCE.forceDelete(file);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 移动文件夹（只支持同一文件系统，否则考虑用Copy+Delete实现）.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/11  huangys  Create
	 * </pre>
	 * 
	 * @param srcDir 源目录
	 * @param destDir 目标目录
	 */
	public static void moveDirectory(@Nonnull final File srcDir, @Nonnull final File destDir) {
		Checks.fileNotExistsThrow(srcDir);
		Checks.nullThrow(destDir);
		try {
			INSTANCE.moveDirectory(srcDir, destDir);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * <pre> 移动文件（只支持同一文件系统，否则考虑用Copy+Delete实现）.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/11  huangys  Create
	 * </pre>
	 * 
	 * @param srcFile 源文件
	 * @param destFile 目标文件
	 */
	public static void moveFile(@Nonnull final File srcFile, @Nonnull final File destFile) {
		Checks.fileNotExistsThrow(srcFile);
		Checks.nullThrow(destFile);
		try {
			INSTANCE.moveFile(srcFile, destFile);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * <pre> 移动文件至指定目下（只支持同一文件系统，否则考虑用Copy+Delete实现）.
	 * 自动创建目标目录。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/11  huangys  Create
	 * </pre>
	 * 
	 * @param srcFile 源文件
	 * @param destDir 目标目录
	 */
	public static void moveFileToDirectory(@Nonnull final File srcFile, @Nonnull final File destDir) {
		Checks.fileNotExistsThrow(srcFile);
		Checks.fileNotExistsThrow(destDir);
		try {
			INSTANCE.moveFileToDirectory(srcFile, destDir, true);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 移动文件夹至指定目录下（只支持同一文件系统，否则考虑用Copy+Delete实现）.
	 * 自动创建目标目录。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/11  huangys  Create
	 * </pre>
	 * 
	 * @param srcDir 源目录
	 * @param destDir 目标目录
	 */
	public static void moveDirectoryToDirectory(@Nonnull final File srcDir, @Nonnull final File destDir) {
		Checks.fileNotExistsThrow(srcDir);
		Checks.fileNotExistsThrow(destDir);
		try {
			INSTANCE.moveDirectoryToDirectory(srcDir, destDir, true);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 清空文件夹.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/11  huangys  Create
	 * </pre>
	 * 
	 * @param directory 目录
	 */
	public static void cleanDirectory(@Nonnull final File directory) {
		Checks.fileNotExistsThrow(directory);
		try {
			INSTANCE.cleanDirectory(directory);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * <pre> 字符串写入文件（覆盖）.
	 * 若data为Null将导致文件内容被置空。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/09  huangys  Create
	 * </pre>
	 * 
	 * @param file 文件
	 * @param data 数据
	 */
	public static void writeLine(@Nonnull final File file, @Nullable final String data) {
		writeLine(file, data, false);
	}
	
	/**
	 * <pre> 字符串写入文件.
	 * append=false时，若data为Null将导致文件内容被置空。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/11  huangys  Create
	 * </pre>
	 * 
	 * @param file 文件
	 * @param data 数据
	 * @param append 是否续写
	 */
	public static void writeLine(@Nonnull final File file, @Nullable final String data, @Nonnull final boolean append) {
		writeString(file, data + Files.LINE_SEPARATOR, append);
	}
	
	/**
	 * <pre> 字符串写入文件（覆盖）.
	 * 若data为Null将导致文件内容被置空。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/09  huangys  Create
	 * </pre>
	 * 
	 * @param file 文件
	 * @param data 数据
	 */
	public static void writeString(@Nonnull final File file, @Nullable final String data) {
		writeString(file, data, false);
	}
	
	/**
	 * <pre> 字符串写入文件.
	 * append=false时，若data为Null将导致文件内容被置空。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/11  huangys  Create
	 * </pre>
	 * 
	 * @param file 文件
	 * @param data 数据
	 * @param append 是否续写
	 */
	public static void writeString(@Nonnull final File file, @Nullable final String data, @Nonnull final boolean append) {
		Checks.nullThrow(file);
		try {
			INSTANCE.writeStringToFile(file, data, append);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 字节数组写入文件（覆盖）.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/11  huangys  Create
	 * </pre>
	 * 
	 * @param file 文件
	 * @param data 数据
	 */
	public static void writeByteArray(@Nonnull final File file, @Nonnull final byte[] data) {
		writeByteArray(file, data, false);
	}
	
	/**
	 * <pre> 字节数组写入文件.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/11  huangys  Create
	 * </pre>
	 * 
	 * @param file 文件
	 * @param data 数据
	 * @param append 是否续写
	 */
	public static void writeByteArray(@Nonnull final File file, @Nonnull final byte[] data, @Nonnull final boolean append) {
		Checks.nullThrow(file);
		try {
			INSTANCE.writeByteArrayToFile(file, data, append);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre>行列表写入文件（覆盖）.
	 * 若lines为Null将导致文件内容被置空。
	 * 空元素，将转为空行。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/09  huangys  Create
	 * </pre>
	 * 
	 * @param file 文件
	 * @param lines 行内容
	 */
	public static void writeLines(@Nonnull final File file, @Nullable final Collection<?> lines) {
		writeLines(file, lines, false);
	}
	
	/**
	 * <pre> 行列表写入文件.
	 * append=false时，若lines为Null将导致文件内容被置空。
	 * 空元素，将转为空行。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/09  huangys  Create
	 * </pre>
	 * 
	 * @param file 文件
	 * @param lines 行内容
	 * @param append 是否续写
	 */
	public static void writeLines(@Nonnull final File file, @Nullable final Collection<?> lines, @Nonnull final boolean append) {
		Checks.nullThrow(file);
		try {
			INSTANCE.writeLines(file, lines, append);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
     * <pre> 返回行迭代器.
     * 
     * Modification History:
     * Date        Author   Action
     * 2015/12/17  huangys  Create
     * </pre>
     * 
     * @param filename 文件名称
     * @return 行迭代器
     */
	@Nonnull
    public static LineIterator lineIterator(@Nonnull final String filename) {
        return lineIterator(filename, null);
    }
  
    /**
     * <pre> 返回行迭代器.
     * 
     * Modification History:
     * Date        Author   Action
     * 2015/12/17  huangys  Create
     * </pre>
     * 
     * @param filename 文件名称
     * @param charset 字符集
     * @return 行迭代器
     */
	@Nonnull
    public static LineIterator lineIterator(@Nonnull final String filename, @Nullable final String charset) {
        Checks.emptyThrow(filename);
        return lineIterator(new File(filename), charset);
    }
	
	/**
     * <pre> 返回行迭代器.
     * 
     * Modification History:
     * Date        Author   Action
     * 2015/12/17  huangys  Create
     * </pre>
     * 
     * @param file 文件
     * @return 行迭代器
     */
	@Nonnull
    public static LineIterator lineIterator(@Nonnull final File file) {
        return lineIterator(file, null);
    }
  
    /**
     * <pre> 返回行迭代器.
     * 
     * Modification History:
     * Date        Author   Action
     * 2015/12/17  huangys  Create
     * </pre>
     * 
     * @param file 文件
     * @param charset 字符集
     * @return 行迭代器
     */
	@Nonnull
    public static LineIterator lineIterator(@Nonnull final File file, @Nullable final String charset) {
        Checks.fileNotExistsThrow(file);
		try (InputStream in = new FileInputStream(file);) {
        	return IOs.lineIterator(in, charset);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
	
	/**
	 * <pre> 读取文件.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/11  huangys  Create
	 * </pre>
	 * 
	 * @param file 文件
	 * @return 行内容
	 */
	@Nonnull
	public static List<String> readLines(@Nonnull final File file) {
		return readLines(file, (String) null);
	}
	
	/**
	 * <pre> 读取文件.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/11  huangys  Create
	 * </pre>
	 * 
	 * @param file 文件
	 * @param charset 字符集
	 * @return 行内容
	 */
	@Nonnull
	public static List<String> readLines(@Nonnull final File file, @Nullable final String charset) {
		Checks.fileNotExistsThrow(file);
		try {
			return INSTANCE.readLines(file, charset);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 读取文件.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/11  huangys  Create
	 * </pre>
	 * 
	 * @param file 文件
	 * @return 文件内容
	 */
	@Nonnull
	public static String readFileToString(@Nonnull final File file) {
		return readFileToString(file, (String) null);
	}
	
	/**
	 * <pre> 读取文件.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/11  huangys  Create
	 * </pre>
	 * 
	 * @param file 文件
	 * @param charset 字符集
	 * @return 文件内容
	 */
	@Nonnull
	public static String readFileToString(@Nonnull final File file, @Nullable final String charset) {
		Checks.fileNotExistsThrow(file);
		try {
			return INSTANCE.readFileToString(file, charset);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 读取文件.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/30  huangys  Create
	 * </pre>
	 * 
	 * @param file 文件
	 * @return 字节数组
	 */
	@Nonnull
	public static byte[] readFileToByteArray(@Nonnull final File file) {
		Checks.fileNotExistsThrow(file);
		try {
			return INSTANCE.readFileToByteArray(file);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
     * <pre> 获取文件起始指定N行.
     * 
     * Modification History:
     * Date        Author   Action
     * 2014/08/29  huangys  Create
     * </pre>
     * 
     * @param filename 文件名称
     * @param rows 行数
     * @return 行集合
     */
	@Nullable
    public static List<String> head(@Nonnull final String filename, @Nonnull final int rows) {
    	return head(filename, null, rows);
    }

	/**
     * <pre> 获取文件起始指定N行.
     * 
     * Modification History:
     * Date        Author   Action
     * 2014/08/29  huangys  Create
     * </pre>
     * 
     * @param filename 文件名称
     * @param charset 字符集
     * @param rows 行数
     * @return 行集合
	 */
	@Nullable
    public static List<String> head(@Nonnull final String filename, @Nullable final String charset, @Nonnull final int rows) {
		Checks.nullThrow(filename);
		return head(new File(filename), charset, rows);
    }
    
	/**
     * <pre> 获取文件起始指定N行.
     * 
     * Modification History:
     * Date        Author   Action
     * 2014/08/29  huangys  Create
     * </pre>
     * 
     * @param file 文件
     * @param rows 行数
     * @return 行集合
	 */
	@Nullable
    public static List<String> head(@Nonnull final File file, @Nonnull final int rows) {
    	return head(file, null, rows);
    }
    
	/**
     * <pre> 获取文件起始指定N行.
     * 
     * Modification History:
     * Date        Author   Action
     * 2014/08/29  huangys  Create
     * </pre>
     * 
     * @param file 文件
     * @param charset 字符集
     * @param rows 行数
     * @return 行集合
	 */
	public static List<String> head(@Nonnull final File file, @Nullable final String charset, @Nonnull final int rows) {
    	if (rows <= 0) {
			return null;
		}
    	
		Checks.fileNotExistsThrow(file);
		List<String> ret = new ArrayList<>();
		int temp = rows;
		try (FileInputStream fis = new FileInputStream(file);
			@SuppressWarnings("resource")
			Reader fReader = charset == null ? new InputStreamReader(fis) : new InputStreamReader(fis, charset);
			LineNumberReader lReader = new LineNumberReader(fReader);) {
			while (temp-- > 0) {
				String line = lReader.readLine();
				if (line == null) {
					break;
				}
	    		ret.add(line);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return ret;
	}
	
    /**
     * <pre> 获取文件的首行.
     * 
     * Modification History:
     * Date        Author   Action
     * 2014/08/29  huangys  Create
     * </pre>
     * 
     * @param filename 文件名称
     * @return 行内容
     */
	@Nullable
    public static String firstLine(@Nonnull final String filename) {
    	return firstLine(filename, null);
    }

	/**
     * <pre> 获取文件的首行.
     * 
     * Modification History:
     * Date        Author   Action
     * 2014/08/29  huangys  Create
     * </pre>
     * 
     * @param filename 文件名称
     * @param charset 字符集
     * @return 行内容
	 */
	@Nullable
    public static String firstLine(@Nonnull final String filename, @Nullable final String charset) {
    	Checks.nullThrow(filename);
		return firstLine(new File(filename), charset);
    }
    
	/**
     * <pre> 获取文件的首行.
     * 
     * Modification History:
     * Date        Author   Action
     * 2014/08/29  huangys  Create
     * </pre>
     * 
     * @param file 文件
     * @return 行内容
	 */
	@Nullable
    public static String firstLine(@Nonnull final File file) {
    	return firstLine(file, null);
    }
    
	/**
     * <pre> 获取文件的首行.
     * 
     * Modification History:
     * Date        Author   Action
     * 2014/08/29  huangys  Create
     * </pre>
     * 
     * @param file 文件
     * @param charset 字符集
     * @return 行内容
	 */
	@Nullable
    public static String firstLine(@Nonnull final File file, @Nullable final String charset) {
    	List<String> ret = head(file, charset, 1);
    	return ret == null ? null : ret.get(0);
    }
	
	/**
	 * <pre> 读取指定字节数.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/18  huangys  Create
	 * </pre>
	 * 
	 * @param filename 文件名称
	 * @param byteCount 字节数
	 * @return 字节数组
	 */
	public static byte[] headBytes(@Nonnull final String filename, @Nonnull final int byteCount)  {
		Checks.nullThrow(filename);
		return headBytes(new File(filename), byteCount);
	}
	
	/**
	 * <pre> 读取指定字节数.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/18  huangys  Create
	 * </pre>
	 * 
	 * @param file 文件
	 * @param byteCount 字节数
	 * @return 字节数组
	 */
	public static byte[] headBytes(@Nonnull final File file, @Nonnull final int byteCount)  {
		Checks.fileNotExistsThrow(file);
		try (FileInputStream is = new FileInputStream(file);) {
			return IOs.readBytes(is, byteCount);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 读取文件首行字节数组.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/18  huangys  Create
	 * </pre>
	 * 
	 * @param filename 文件名称
	 * @return 字节数组
	 */
	@Nonnull
	public static byte[] firstLineBytes(@Nonnull final String filename)  {
		Checks.nullThrow(filename);
		return firstLineBytes(new File(filename));
	}
	
	private static final int BUFFER_SIZE = 1024;
	/**
	 * <pre> 读取文件首行字节数组.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/30  huangys  Create
	 * </pre>
	 * 
	 * @param file 文件
	 * @return 首行字节数组
	 */
	@Nonnull
	public static byte[] firstLineBytes(@Nonnull final File file) {
		Checks.fileNotExistsThrow(file);
		List<byte[]> list = new ArrayList<>();
		try (InputStream in = new FileInputStream(file);) {
			while (true) {
				byte[] buffer = new byte[BUFFER_SIZE];
				int len = in.read(buffer);
				if (len == -1) {
					return Arrays.concat(list);
				}
				for (int i = 0; i < len; i++) {
					if (buffer[i] == '\n') {
						if (i == 0) {
							if (list.size() == 0) {
								return new byte[0];
							} else {
								byte[] last = list.get(list.size() - 1);
								if (last[BUFFER_SIZE - 1] == '\r') {
									list.remove(list.size() - 1);
									list.add(Arrays.subarray(last, 0, BUFFER_SIZE - 1));
								}
								return Arrays.concat(list);
							}
						} else {
							if (buffer[i - 1] == '\r') {
								i--;
							}
							list.add(Arrays.subarray(buffer, 0, i));
							return Arrays.concat(list);
						}
					}
				}
				if (len < BUFFER_SIZE) {
					list.add(Arrays.subarray(buffer, 0, len));
					return Arrays.concat(list);
				}
				list.add(buffer);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
     * <pre> 获取文本的字符集.
     * 
     * Modification History:
     * Date        Author   Action
     * 2014/03/14  huangys  Create
     * </pre>
     * 
     * @param fileName 文件名称
     * @return 字符集
     */
	@Nonnull
    public static String getTextFileCharset(@Nonnull final String fileName) {
    	try (BufferedInputStream bin = new BufferedInputStream(new FileInputStream(fileName));) {
            int firstTwoBytes = (bin.read() << 8) + bin.read();
            String code = null;
            //其中的 0xefbb、0xfffe、0xfeff、0x5c75这些都是这个文件的前面两个字节的16进制数
            switch (firstTwoBytes) {
                case 0xefbb:
                    code = "UTF-8";
                    break;
                case 0xfffe:
                    code = "Unicode";
                    break;
                case 0xfeff:
                    code = "UTF-16BE";
                    break;
                case 0x5c75:
                    code = "ANSI|ASCII";
                    break;
                default:
                    code = "GBK";
            }
            return code;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
    
    /**
     * <pre> 获取文件末尾指定N行.
     * 空行会被忽略。
     * 
     * Modification History:
     * Date        Author   Action
     * 2014/08/29  huangys  Create
     * </pre>
     * 
     * @param filename 文件名称
     * @param rows 行数
     * @return 行集合，首元素为末尾最后一行，第二个元素为倒数第二行，以此类推
     */
	@Nullable
    public static List<String> tail(@Nonnull final String filename, @Nonnull final int rows) {
    	return tail(filename, null, rows);
    }

	/**
     * <pre> 获取文件末尾指定N行.
     * 空行会被忽略。
     * 
     * Modification History:
     * Date        Author   Action
     * 2014/08/29  huangys  Create
     * </pre>
     * 
     * @param filename 文件名称
     * @param charset 字符集
     * @param rows 行数
     * @return 行集合，首元素为末尾最后一行，第二个元素为倒数第二行，以此类推
	 */
	@Nullable
    public static List<String> tail(@Nonnull final String filename, @Nullable final String charset, @Nonnull final int rows) {
    	Checks.nullThrow(filename);
		return tail(new File(filename), charset, rows);
    }
    
	/**
     * <pre> 获取文件末尾指定N行.
     * 空行会被忽略。
     * 
     * Modification History:
     * Date        Author   Action
     * 2014/08/29  huangys  Create
     * </pre>
     * 
     * @param file 文件
     * @param rows 行数
     * @return 行集合，首元素为末尾最后一行，第二个元素为倒数第二行，以此类推
	 */
	@Nullable
    public static List<String> tail(@Nonnull final File file, @Nonnull final int rows) {
    	return tail(file, null, rows);
    }
    
	/**
     * <pre> 获取文件末尾指定N行.
     * 空行会被忽略。
     * 
     * Modification History:
     * Date        Author   Action
     * 2014/08/29  huangys  Create
     * </pre>
     * 
     * @param file 文件
     * @param charset 字符集
     * @param rows 行数
     * @return 行集合，首元素为末尾最后一行，第二个元素为倒数第二行，以此类推
	 */
	@Nullable
    public static List<String> tail(@Nonnull final File file, @Nullable final String charset, @Nonnull final int rows) {
    	if (rows <= 0) {
			return null;
		}
    	Checks.fileNotExistsThrow(file);
    	
    	List<String> ret = new ArrayList<>();
    	ReversedLinesFileReader reader = null;
    	int temp = rows;
		try {
			reader = charset == null ? new ReversedLinesFileReader(file) : new ReversedLinesFileReader(file, 1, charset);
			while (temp-- > 0) {
				String line = reader.readLine();
				if (line == null) {
					break;
				}
	    		ret.add(line);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (reader != null) {
				try { reader.close(); } catch (Exception e2) { }
			}
		}
    	return ret;
    }
    
    /**
     * <pre> 获取文件的最后一行.
     * 空行会被忽略。
     * 
     * Modification History:
     * Date        Author   Action
     * 2014/08/29  huangys  Create
     * </pre>
     * 
     * @param filename 文件名称
     * @return 行内容
     */
	@Nullable
    public static String lastLine(@Nonnull final String filename) {
    	return lastLine(filename, null);
    }

	/**
     * <pre> 获取文件的最后一行.
     * 空行会被忽略。
     * 
     * Modification History:
     * Date        Author   Action
     * 2014/08/29  huangys  Create
     * </pre>
     * 
     * @param filename 文件名称
     * @param charset 字符集
     * @return 行内容
	 */
	@Nullable
    public static String lastLine(@Nonnull final String filename, @Nullable final String charset) {
    	Checks.nullThrow(filename);
		return lastLine(new File(filename), charset);
    }
    
	/**
     * <pre> 获取文件的最后一行.
     * 空行会被忽略。
     * 
     * Modification History:
     * Date        Author   Action
     * 2014/08/29  huangys  Create
     * </pre>
     * 
     * @param file 文件
     * @return 行内容
	 */
	@Nullable
    public static String lastLine(@Nonnull final File file) {
    	return lastLine(file, null);
    }
    
	/**
     * <pre> 获取文件的最后一行.
     * 空行会被忽略。
     * 
     * Modification History:
     * Date        Author   Action
     * 2014/08/29  huangys  Create
     * </pre>
     * 
     * @param file 文件
     * @param charset 字符集
     * @return 行内容
	 */
	@Nullable
    public static String lastLine(@Nonnull final File file, @Nullable final String charset) {
    	List<String> ret = tail(file, charset, 1);
    	return ret == null ? null : ret.get(0);
    }
    
	/**
	 * <pre> 判断文件是否传输完成.
	 * 
	 * 间隔15秒，判断文件大小一致性，连续判断3次。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/13  huangys  Create
	 * </pre>
	 * 
	 * @param filename 文件名称
	 * @return 文件是否传输完成
	 */
	@Nonnull
	public static boolean checkFileStable(@Nonnull final String filename) {
		return checkFileStable(filename, FILE_STABLE_DEFAULT_INTERVAL);
	}
	
	/**
	 * <pre> 判断文件是否传输完成.
	 * 
	 * 间隔15秒，判断文件大小一致性，连续判断3次。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/13  huangys  Create
	 * </pre>
	 * 
	 * @param filename 文件名称
	 * @param interval 时间间隔
	 * @return 文件是否传输完成
	 */
	@Nonnull
	public static boolean checkFileStable(@Nonnull final String filename, @Nonnull final TimeSlice interval) {
		return checkFileStable(filename, interval, FILE_STABLE_DEFAULT_TIMES);
	}
	
	/**
	 * <pre> 判断文件是否传输完成.
	 * 
	 * 间隔15秒，判断文件大小一致性，连续判断3次。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/13  huangys  Create
	 * </pre>
	 * 
	 * @param filename 文件名称
	 * @param interval 时间间隔
	 * @param times 检查次数
	 * @return 文件是否传输完成
	 */
	@Nonnull
	public static boolean checkFileStable(@Nonnull final String filename, @Nonnull final TimeSlice interval, @Nonnull final int times) {
		Checks.nullThrow(filename);
		return checkFileStable(new File(filename), interval, times);
	}
	
	/**
	 * <pre> 判断文件是否传输完成.
	 * 
	 * 间隔15秒，判断文件大小一致性，连续判断3次。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/13  huangys  Create
	 * </pre>
	 * 
	 * @param file 文件
	 * @return 文件是否传输完成
	 */
	@Nonnull
	public static boolean checkFileStable(@Nonnull final File file) {
		return checkFileStable(file, FILE_STABLE_DEFAULT_INTERVAL);
	}
	
	/**
	 * <pre> 判断文件是否传输完成.
	 * 
	 * 间隔15秒，判断文件大小一致性，连续判断3次。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/13  huangys  Create
	 * </pre>
	 * 
	 * @param file 文件
	 * @param interval 时间间隔
	 * @return 文件是否传输完成
	 */
	@Nonnull
	public static boolean checkFileStable(@Nonnull final File file, @Nonnull final TimeSlice interval) {
		return checkFileStable(file, interval, FILE_STABLE_DEFAULT_TIMES);
	}
	
	private static final TimeSlice FILE_STABLE_DEFAULT_INTERVAL = TimeSlice.seconds(15);
	private static final int FILE_STABLE_DEFAULT_TIMES = 3;
	/**
	 * <pre> 判断文件是否传输完成.
	 * 
	 * 间隔15秒，判断文件大小一致性，连续判断3次。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/13  huangys  Create
	 * </pre>
	 * 
	 * @param file 文件
	 * @param interval 时间间隔
	 * @param times 检查次数
	 * @return 文件是否传输完成
	 */
	@Nonnull
	public static boolean checkFileStable(@Nonnull final File file, @Nonnull final TimeSlice interval, @Nonnull final int times) {
		Checks.nullThrow(interval);
		Checks.matchThrow(times <= 0, "Times must larger than 0.");
		long size = file.length();
		for (int i = 0; i < times; i++) {
			interval.sleep();
			if (size != file.length()) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * <pre> 依次处理指定字节数.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/17  huangys  Create
	 * </pre>
	 * 
	 * @param filename 文件名称
	 * @param bufferSzie 缓存大小
	 * @param doable 处理方法
	 */
	public static void readBytes(@Nonnull final String filename, @Nonnull final int bufferSzie, @Nonnull final Doable<byte[]> doable) {
        Checks.emptyThrow(filename);
        readBytes(new File(filename), bufferSzie, doable);
	}
	
	/**
	 * <pre> 依次处理指定字节数.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/17  huangys  Create
	 * </pre>
	 * 
	 * @param file 文件
	 * @param bufferSzie 缓存大小
	 * @param doable 处理方法
	 */
	public static void readBytes(@Nonnull final File file, @Nonnull final int bufferSzie, @Nonnull final Doable<byte[]> doable) {
        Checks.fileNotExistsThrow(file);
        Checks.nullThrow(doable);
		try (InputStream in = new FileInputStream(file);) {
        	byte[] buffer = new byte[bufferSzie];
        	int len = 0;
        	while ((len = in.read(buffer)) != -1) {
        		doable.run(Arrays.subarray(buffer, 0, len));
            }
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 清空文件.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/03/07  huangys  Create
	 * </pre>
	 * 
	 * @param file 文件
	 */
	public static void clear(@Nonnull final File file) {
		writeLines(file, Lists.EMPTY);
	}
	
	/**
	 * <pre> 创建文件夹.
	 * 接入时，应单线程接入，避免并发时出现的异常
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/11  huangys  Create
	 * </pre>
	 * 
	 * @param file 文件
	 */
	public static synchronized void forceMkFilePath(@Nonnull final String file) {
		Checks.nullThrow(file);
		forceMkdir(new File(file).getParentFile().getAbsolutePath());
	}
	
	/**
	 * <pre> 创建文件夹.
	 * 接入时，应单线程接入，避免并发时出现的异常
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/11  huangys  Create
	 * </pre>
	 * 
	 * @param file 文件
	 */
	public static synchronized void forceMkFilePath(@Nonnull final File file) {
		Checks.nullThrow(file);
		forceMkFilePath(file.getAbsolutePath());
	}
	
}