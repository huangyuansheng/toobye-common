/*
 * Copyright 2015 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2015/12/18.
 * 
 */
package com.toobye.common.io;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.io.filefilter.RegexFileFilter;

import com.toobye.common.collection.Iterables;
import com.toobye.common.lang.Checks;
import com.toobye.common.lang.Condition;

/**
 * <pre> 文件过滤.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2015/12/18  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class FileFilters {
	
	private FileFilters() { }

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
	 * <pre> 文件筛选（不含子文件）.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/11  huangys  Create
	 * </pre>
	 * 
	 * @param directory 目录
	 * @return 文件清单
	 */
	@Nonnull
	public static Collection<File> listFiles(@Nonnull final String directory) {
		return listFiles(directory, (String[]) null);
	}
	
	/**
	 * <pre> 文件筛选（不含子文件）.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/11  huangys  Create
	 * </pre>
	 * 
	 * @param directory 目录
	 * @param extensions 扩展名
	 * @return 文件清单
	 */
	@Nonnull
	public static Collection<File> listFiles(@Nonnull final String directory, @Nullable final String[] extensions) {
		return listFiles(directory, extensions, false);
	}
	
	/**
	 * <pre> 文件筛选.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/11  huangys  Create
	 * </pre>
	 * 
	 * @param directory 目录
	 * @param recursive 是否查找子文件夹
	 * @return 文件清单
	 */
	@Nonnull
	public static Collection<File> listFiles(@Nonnull final String directory, @Nonnull final boolean recursive) {
		return listFiles(directory, (String[]) null, recursive);
	}
	
	/**
	 * <pre> 文件筛选.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/11  huangys  Create
	 * </pre>
	 * 
	 * @param directory 目录
	 * @param extensions 扩展名
	 * @param recursive 是否查找子文件夹
	 * @return 文件清单
	 */
	@Nonnull
	public static Collection<File> listFiles(@Nonnull final String directory, @Nullable final String[] extensions, @Nonnull final boolean recursive) {
		Checks.emptyThrow(directory);
		return listFiles(new File(directory), extensions, recursive);
	}
	
	/**
	 * <pre> 文件筛选（不含子文件）.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/11  huangys  Create
	 * </pre>
	 * 
	 * @param directory 目录
	 * @return 文件清单
	 */
	@Nonnull
	public static Collection<File> listFiles(@Nonnull final File directory) {
		return listFiles(directory, (String[]) null);
	}
	
	/**
	 * <pre> 文件筛选（不含子文件）.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/11  huangys  Create
	 * </pre>
	 * 
	 * @param directory 目录
	 * @param extensions 扩展名
	 * @return 文件清单
	 */
	@Nonnull
	public static Collection<File> listFiles(@Nonnull final File directory, @Nullable final String[] extensions) {
		return listFiles(directory, extensions, false);
	}
	
	/**
	 * <pre> 文件筛选.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/11  huangys  Create
	 * </pre>
	 * 
	 * @param directory 目录
	 * @param recursive 是否查找子文件夹
	 * @return 文件清单
	 */
	@Nonnull
	public static Collection<File> listFiles(@Nonnull final File directory, @Nonnull final boolean recursive) {
		return listFiles(directory, (String[]) null, recursive);
	}
	
	/**
	 * <pre> 文件筛选.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/11  huangys  Create
	 * </pre>
	 * 
	 * @param directory 目录
	 * @param extensions 扩展名
	 * @param recursive 是否查找子文件夹
	 * @return 文件清单
	 */
	@Nonnull
	public static Collection<File> listFiles(@Nonnull final File directory, @Nullable final String[] extensions, @Nonnull final boolean recursive) {
		Checks.fileNotExistsThrow(directory);
		return INSTANCE.listFiles(directory, extensions, recursive);
	}
	
	/**
	 * <pre> 文件筛选.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/11  huangys  Create
	 * </pre>
	 * 
	 * @param directory 目录
	 * @param regex 正则表达式
	 * @return 文件清单
	 */
	@Nonnull
	public static File[] listFiles(@Nonnull final String directory, @Nonnull final String regex) {
		Checks.emptyThrow(directory);
		return listFiles(new File(directory), regex);
	}

	/**
	 * <pre> 文件筛选.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/11  huangys  Create
	 * </pre>
	 * 
	 * @param directory 目录
	 * @param regex 正则表达式
	 * @return 文件清单
	 */
	@Nonnull
	public static File[] listFiles(@Nonnull final File directory, @Nonnull final String regex) {
		Checks.fileNotExistsThrow(directory);
		Checks.nullThrow(regex);
		return directory.listFiles((FileFilter) new RegexFileFilter(regex));
	}
	
	/**
	 * <pre> 文件筛选(不含子目录).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/18  huangys  Create
	 * </pre>
	 * 
	 * @param directory 目录
	 * @param cond 条件
	 * @return 文件清单
	 */
	public static Collection<File> listFiles(@Nonnull final String directory, @Nonnull final Condition<File> cond) {
		return listFiles(directory, cond, false);
	}
	
	/**
	 * <pre> 文件筛选(不含子目录).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/18  huangys  Create
	 * </pre>
	 * 
	 * @param directory 目录
	 * @param cond 条件
	 * @return 文件清单
	 */
	public static Collection<File> listFiles(@Nonnull final File directory, @Nonnull final Condition<File> cond) {
		return listFiles(directory, cond, false);
	}
	
	/**
	 * <pre> 文件筛选.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/18  huangys  Create
	 * </pre>
	 * 
	 * @param directory 目录
	 * @param cond 条件
	 * @param recursive 是否查找子文件夹
	 * @return 文件清单
	 */
	public static Collection<File> listFiles(@Nonnull final String directory, @Nonnull final Condition<File> cond, @Nonnull final boolean recursive) {
		Checks.emptyThrow(directory);
		return listFiles(new File(directory), cond, recursive);
	}
	
	/**
	 * <pre> 文件筛选.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/18  huangys  Create
	 * </pre>
	 * 
	 * @param directory 目录
	 * @param cond 条件
	 * @param recursive 是否查找子文件夹
	 * @return 文件清单
	 */
	public static Collection<File> listFiles(@Nonnull final File directory, @Nonnull final Condition<File> cond, @Nonnull final boolean recursive) {
		Collection<File> files = listFiles(directory, recursive);
		Iterables.reserve(files, cond);
		return files;
	}

	/**
	 * <pre> 文件筛选（不含子目录）.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/14  huangys  Create
	 * </pre>
	 * 
	 * @param directory 目录
	 * @return 文件清单
	 */
	@Nonnull
	public static Collection<File> listFilesAndDirs(@Nonnull final String directory) {
		return listFilesAndDirs(directory, false);
	}
	
	/**
	 * <pre> 文件筛选（不含子目录）.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/14  huangys  Create
	 * </pre>
	 * 
	 * @param directory 目录
	 * @return 文件清单
	 */
	@Nonnull
	public static Collection<File> listDirs(@Nonnull final String directory) {
		return listDirs(directory, false);
	}
	
	/**
	 * <pre> 文件筛选.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/14  huangys  Create
	 * </pre>
	 * 
	 * @param directory 目录
	 * @param recursive 是否查找子文件夹
	 * @return 文件清单
	 */
	@Nonnull
	public static Collection<File> listFilesAndDirs(@Nonnull final String directory, @Nonnull final boolean recursive) {
		Checks.emptyThrow(directory);
		return listFilesAndDirs(new File(directory), recursive);
	}
	
	/**
	 * <pre> 文件筛选.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/14  huangys  Create
	 * </pre>
	 * 
	 * @param directory 目录
	 * @param recursive 是否查找子文件夹
	 * @return 文件清单
	 */
	@Nonnull
	public static Collection<File> listDirs(@Nonnull final String directory, @Nonnull final boolean recursive) {
		Checks.emptyThrow(directory);
		return listDirs(new File(directory), recursive);
	}
	
	/**
	 * <pre> 文件筛选（不含子目录）.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/14  huangys  Create
	 * </pre>
	 * 
	 * @param directory 目录
	 * @return 文件清单
	 */
	@Nonnull
	public static Collection<File> listFilesAndDirs(@Nonnull final File directory) {
		Checks.fileNotExistsThrow(directory);
		List<File> list = new ArrayList<>();
		for (File file : directory.listFiles()) {
			list.add(file);
		}
		return list;
	}
	
	/**
	 * <pre> 文件筛选（不含子目录）.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/14  huangys  Create
	 * </pre>
	 * 
	 * @param directory 目录
	 * @return 文件清单
	 */
	@Nonnull
	public static Collection<File> listDirs(@Nonnull final File directory) {
		Checks.fileNotExistsThrow(directory);
		List<File> list = new ArrayList<>();
		for (File file : directory.listFiles()) {
			if (file.isDirectory()) {
				list.add(file);
			}
		}
		return list;
	}
	
	/**
	 * <pre> 文件筛选.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/14  huangys  Create
	 * </pre>
	 * 
	 * @param directory 目录
	 * @param recursive 是否查找子文件夹
	 * @return 文件清单
	 */
	@Nonnull
	public static Collection<File> listFilesAndDirs(@Nonnull final File directory, @Nonnull final boolean recursive) {
		if (recursive) {
			Checks.fileNotExistsThrow(directory);
			List<File> list = new ArrayList<>();
			listFilesAndDirsRecursive(directory, list);
			return list;
		} else {
			return listFilesAndDirs(directory);
		}
	}
	
	/**
	 * <pre> 文件筛选.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/14  huangys  Create
	 * </pre>
	 * 
	 * @param directory 目录
	 * @param recursive 是否查找子文件夹
	 * @return 文件清单
	 */
	@Nonnull
	public static Collection<File> listDirs(@Nonnull final File directory, @Nonnull final boolean recursive) {
		if (recursive) {
			Checks.fileNotExistsThrow(directory);
			List<File> list = new ArrayList<>();
			listDirsRecursive(directory, list);
			return list;
		} else {
			return listDirs(directory);
		}
	}
	
	/**
	 * <pre> 文件筛选（包含子目录）.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/14  huangys  Create
	 * </pre>
	 * 
	 * @param directory 目录
	 * @param files 结果累存
	 */
	private static void listFilesAndDirsRecursive(@Nonnull final File directory, @Nonnull final Collection<File> files) {
		files.add(directory);
		if (directory.isDirectory()) {
			for (File f : directory.listFiles()) {
				listFilesAndDirsRecursive(f, files);
			}
		}
	}
	
	/**
	 * <pre> 文件筛选（包含子目录）.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/14  huangys  Create
	 * </pre>
	 * 
	 * @param directory 目录
	 * @param files 结果累存
	 */
	private static void listDirsRecursive(@Nonnull final File directory, @Nonnull final Collection<File> files) {
		if (directory.isDirectory()) {
			files.add(directory);
			for (File f : directory.listFiles()) {
				listDirsRecursive(f, files);
			}
		}
	}
	
	/**
	 * <pre> 文件筛选(不含子目录).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/18  huangys  Create
	 * </pre>
	 * 
	 * @param directory 目录
	 * @param cond 条件
	 * @return 文件清单
	 */
	public static Collection<File> listDirs(@Nonnull final String directory, @Nonnull final Condition<File> cond) {
		return listDirs(directory, cond, false);
	}
	
	/**
	 * <pre> 文件筛选(不含子目录).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/18  huangys  Create
	 * </pre>
	 * 
	 * @param directory 目录
	 * @param cond 条件
	 * @return 文件清单
	 */
	public static Collection<File> listDirs(@Nonnull final File directory, @Nonnull final Condition<File> cond) {
		return listDirs(directory, cond, false);
	}
	
	/**
	 * <pre> 文件筛选.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/18  huangys  Create
	 * </pre>
	 * 
	 * @param directory 目录
	 * @param cond 条件
	 * @param recursive 是否查找子文件夹
	 * @return 文件清单
	 */
	public static Collection<File> listDirs(@Nonnull final String directory, @Nonnull final Condition<File> cond, @Nonnull final boolean recursive) {
		Checks.emptyThrow(directory);
		return listDirs(new File(directory), cond, recursive);
	}
	
	/**
	 * <pre> 文件筛选.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/18  huangys  Create
	 * </pre>
	 * 
	 * @param directory 目录
	 * @param cond 条件
	 * @param recursive 是否查找子文件夹
	 * @return 文件清单
	 */
	public static Collection<File> listDirs(@Nonnull final File directory, @Nonnull final Condition<File> cond, @Nonnull final boolean recursive) {
		Collection<File> files = listDirs(directory, recursive);
		Iterables.reserve(files, cond);
		return files;
	}
	
	/**
	 * <pre> 文件筛选(不含子目录).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/18  huangys  Create
	 * </pre>
	 * 
	 * @param directory 目录
	 * @param cond 条件
	 * @return 文件清单
	 */
	public static Collection<File> listFilesAndDirs(@Nonnull final String directory, @Nonnull final Condition<File> cond) {
		return listFilesAndDirs(directory, cond, false);
	}
	
	/**
	 * <pre> 文件筛选(不含子目录).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/18  huangys  Create
	 * </pre>
	 * 
	 * @param directory 目录
	 * @param cond 条件
	 * @return 文件清单
	 */
	public static Collection<File> listFilesAndDirs(@Nonnull final File directory, @Nonnull final Condition<File> cond) {
		return listFilesAndDirs(directory, cond, false);
	}
	
	/**
	 * <pre> 文件筛选.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/18  huangys  Create
	 * </pre>
	 * 
	 * @param directory 目录
	 * @param cond 条件
	 * @param recursive 是否查找子文件夹
	 * @return 文件清单
	 */
	public static Collection<File> listFilesAndDirs(@Nonnull final String directory, @Nonnull final Condition<File> cond, @Nonnull final boolean recursive) {
		Checks.emptyThrow(directory);
		return listFilesAndDirs(new File(directory), cond, recursive);
	}
	
	/**
	 * <pre> 文件筛选.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/18  huangys  Create
	 * </pre>
	 * 
	 * @param directory 目录
	 * @param cond 条件
	 * @param recursive 是否查找子文件夹
	 * @return 文件清单
	 */
	public static Collection<File> listFilesAndDirs(@Nonnull final File directory, @Nonnull final Condition<File> cond, @Nonnull final boolean recursive) {
		Collection<File> files = listFilesAndDirs(directory, recursive);
		Iterables.reserve(files, cond);
		return files;
	}
	
	/**
	 * <pre> 文件筛选（不含子目录）.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/03/24  huangys  Create
	 * </pre>
	 * 
	 * @param directory 目录
	 * @return 文件清单
	 */
	@Nonnull
	public static Collection<File> listFilesAndEmptyDirs(@Nonnull final String directory) {
		return listFilesAndEmptyDirs(directory, false);
	}
	
	/**
	 * <pre> 文件筛选.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/03/24  huangys  Create
	 * </pre>
	 * 
	 * @param directory 目录
	 * @param recursive 是否查找子文件夹
	 * @return 文件清单
	 */
	public static Collection<File> listFilesAndEmptyDirs(final String directory, @Nonnull final boolean recursive) {
		Checks.emptyThrow(directory);
		return listFilesAndEmptyDirs(new File(directory), recursive);
	}
	
	/**
	 * <pre> 文件筛选（不含子目录）.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/03/24  huangys  Create
	 * </pre>
	 * 
	 * @param directory 目录
	 * @return 文件清单
	 */
	@Nonnull
	public static Collection<File> listFilesAndEmptyDirs(@Nonnull final File directory) {
		return listFilesAndEmptyDirs(directory, false);
	}
	
	/**
	 * <pre> 文件筛选.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/03/24  huangys  Create
	 * </pre>
	 * 
	 * @param directory 目录
	 * @param recursive 是否查找子文件夹
	 * @return 文件清单
	 */
	public static Collection<File> listFilesAndEmptyDirs(final File directory, @Nonnull final boolean recursive) {
		return listFilesAndDirs(directory, new Condition<File>() {
			@Override
			public boolean match(final File file) {
				return file.isFile() || Files.isEmptyDir(file);
			}
		}, recursive);
	}
	
	/**
	 * <pre> 文件筛选（不含子目录）.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/03/24  huangys  Create
	 * </pre>
	 * 
	 * @param directory 目录
	 * @return 文件清单
	 */
	@Nonnull
	public static Collection<File> listEmptyDirs(@Nonnull final String directory) {
		return listEmptyDirs(directory, false);
	}
	
	/**
	 * <pre> 文件筛选.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/03/24  huangys  Create
	 * </pre>
	 * 
	 * @param directory 目录
	 * @param recursive 是否查找子文件夹
	 * @return 文件清单
	 */
	public static Collection<File> listEmptyDirs(final String directory, @Nonnull final boolean recursive) {
		Checks.emptyThrow(directory);
		return listEmptyDirs(new File(directory), recursive);
	}
	
	/**
	 * <pre> 文件筛选（不含子目录）.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/03/24  huangys  Create
	 * </pre>
	 * 
	 * @param directory 目录
	 * @return 文件清单
	 */
	@Nonnull
	public static Collection<File> listEmptyDirs(@Nonnull final File directory) {
		return listEmptyDirs(directory, false);
	}
	
	/**
	 * <pre> 文件筛选.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/03/24  huangys  Create
	 * </pre>
	 * 
	 * @param directory 目录
	 * @param recursive 是否查找子文件夹
	 * @return 文件清单
	 */
	public static Collection<File> listEmptyDirs(final File directory, @Nonnull final boolean recursive) {
		return listDirs(directory, new Condition<File>() {
			@Override
			public boolean match(final File file) {
				return Files.isEmptyDir(file);
			}
		}, recursive);
	}
	
}
