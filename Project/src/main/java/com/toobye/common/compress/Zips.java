/*
 * Copyright 2016 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2016/01/12.
 * 
 */
package com.toobye.common.compress;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

import com.toobye.common.io.FileFilters;
import com.toobye.common.io.IOs;
import com.toobye.common.lang.Checks;
import com.toobye.common.string.StringUtils;

/**
 * <pre> Zip文件工具.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2016/01/12  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class Zips {
	
	private Zips() { }
	
	/**
	 * <pre> 添加文件夹.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/12  huangys  Create
	 * </pre>
	 * 
	 * @param zipFile 压缩文件
	 * @param folder 待添加文件夹
	 */
	public static void addFolder(@Nonnull final File zipFile, @Nonnull final File folder) {
		addFolder(zipFile, folder, folder.getParent(), null);
	}
	
	/**
	 * <pre> 添加文件夹.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/12  huangys  Create
	 * </pre>
	 * 
	 * @param zipFile 压缩文件
	 * @param folder 待添加文件夹
	 * @param zipPath 压缩文件内路径
	 */
	public static void addFolder(@Nonnull final File zipFile, @Nonnull final File folder, @Nullable final String zipPath) {
		addFolder(zipFile, folder, folder.getParent(), zipPath, null);
	}
	
	/**
	 * <pre> 添加文件夹.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/12  huangys  Create
	 * </pre>
	 * 
	 * @param zipFile 压缩文件
	 * @param folder 待添加文件夹
	 * @param zipPath 压缩文件内路径
	 * @param password 密码
	 */
	public static void addFolder(@Nonnull final File zipFile, @Nonnull final File folder, @Nullable final String zipPath, @Nullable final String password) {
		addFolder(zipFile, folder, folder.getParent(), zipPath, password);
	}
	
	/**
	 * <pre> 添加文件夹.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/12  huangys  Create
	 * </pre>
	 * 
	 * @param zipFile 压缩文件
	 * @param folder 待添加文件夹
	 * @param rootPath 文件根目录，Windows下使用"C:"可以忽略盘符保留全路径
	 * @param zipPath 压缩文件内路径
	 * @param password 密码
	 */
	public static void addFolder(@Nonnull final File zipFile, @Nonnull final File folder, @Nonnull final String rootPath, @Nullable final String zipPath, @Nullable final String password) {
		addFile(zipFile, FileFilters.listFilesAndEmptyDirs(folder, true), rootPath, zipPath, password);
	}
	
	/**
	 * <pre> 添加文件.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/12  huangys  Create
	 * </pre>
	 * 
	 * @param zipFile 压缩文件
	 * @param file 待添加文件
	 */
	public static void addFile(@Nonnull final File zipFile, @Nonnull final File file) {
		addFile(zipFile, file, null);
	}
	
	/**
	 * <pre> 添加文件.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/12  huangys  Create
	 * </pre>
	 * 
	 * @param zipFile 压缩文件
	 * @param file 待添加文件
	 * @param zipPath 压缩文件内路径
	 */
	public static void addFile(@Nonnull final File zipFile, @Nonnull final File file, @Nullable final String zipPath) {
		addFile(zipFile, file, zipPath, null);
	}
	
	/**
	 * <pre> 添加文件.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/12  huangys  Create
	 * </pre>
	 * 
	 * @param zipFile 压缩文件
	 * @param file 待添加文件
	 * @param zipPath 压缩文件内路径
	 * @param password 密码
	 */
	public static void addFile(@Nonnull final File zipFile, @Nonnull final File file, @Nullable final String zipPath, @Nullable final String password) {
		Checks.nullThrow(file);
		addFile(zipFile, file, file.getParent(), zipPath, password);
	}
	
	/**
	 * <pre> 添加文件.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/12  huangys  Create
	 * </pre>
	 * 
	 * @param zipFile 压缩文件
	 * @param file 待添加文件
	 * @param rootPath 文件根目录，Windows下使用"C:"可以忽略盘符保留全路径
	 * @param zipPath 压缩文件内路径
	 * @param password 密码
	 */
	public static void addFile(@Nonnull final File zipFile, @Nonnull final File file, @Nonnull final String rootPath, @Nullable final String zipPath, @Nullable final String password) {
		Checks.nullThrow(file);
		ArrayList<File> list = new ArrayList<File>();
		list.add(file);
		addFile(zipFile, list, rootPath, zipPath, password);
	}
	
	/**
	 * <pre> 添加文件.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/12  huangys  Create
	 * </pre>
	 * 
	 * @param zipFile 压缩文件
	 * @param files 待添加文件集合
	 * @param rootPath 文件根目录，Windows下使用"C:"可以忽略盘符保留全路径
	 */
	public static void addFile(@Nonnull final File zipFile, @Nonnull final Collection<File> files, @Nonnull final String rootPath) {
		addFile(zipFile, files, rootPath, null);
	}
	
	/**
	 * <pre> 添加文件.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/12  huangys  Create
	 * </pre>
	 * 
	 * @param zipFile 压缩文件
	 * @param files 待添加文件集合
	 * @param rootPath 文件根目录，Windows下使用"C:"可以忽略盘符保留全路径
	 * @param zipPath 压缩文件内路径
	 */
	public static void addFile(@Nonnull final File zipFile, @Nonnull final Collection<File> files, @Nonnull final String rootPath, @Nullable final String zipPath) {
		addFile(zipFile, files, rootPath, zipPath, null);
	}
	
	/**
	 * <pre> 添加文件.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/12  huangys  Create
	 * </pre>
	 * 
	 * @param zipFile 压缩文件
	 * @param files 待添加文件集合
	 * @param rootPath 文件根目录，Windows下使用"C:"可以忽略盘符保留全路径
	 * @param zipPath 压缩文件内路径
	 * @param password 密码
	 */
	public static void addFile(@Nonnull final File zipFile, @Nonnull final Collection<File> files, @Nonnull final String rootPath, @Nullable final String zipPath, @Nullable final String password) {
		Checks.nullThrow(zipFile);
		Checks.nullThrow(files);
		Checks.nullThrow(rootPath);
		try {
			ZipFile zip = new ZipFile(zipFile);
			ZipParameters parameters = new ZipParameters();
			parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
			parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_ULTRA);
			parameters.setDefaultFolderPath(rootPath);
			if (!StringUtils.isBlank(zipPath)) {
				parameters.setRootFolderInZip(zipPath);
			}
			if (!StringUtils.isEmpty(password)) {
				parameters.setEncryptFiles(true);
				parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_STANDARD);
				parameters.setPassword(password);
			}
			if (files instanceof ArrayList) {
				zip.addFiles((ArrayList<File>) files, parameters);
			} else {
				ArrayList<File> list = new ArrayList<File>();
				list.addAll(files);
				zip.addFiles(list, parameters);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 添加流.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/12  huangys  Create
	 * </pre>
	 * 
	 * @param zipFile 压缩文件
	 * @param is 待添加文件
	 * @param filenameInZip 流对应的文件名
	 */
	public static void addStreamWithClose(@Nonnull final File zipFile, @Nonnull final InputStream is, @Nonnull final String filenameInZip) {
		addStreamWithClose(zipFile, is, filenameInZip, null);
	}
	
	/**
	 * <pre> 添加流.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/12  huangys  Create
	 * </pre>
	 * 
	 * @param zipFile 压缩文件
	 * @param is 待添加文件
	 * @param filenameInZip 流对应的文件名
	 * @param password 密码
	 */
	public static void addStreamWithClose(@Nonnull final File zipFile, @Nonnull final InputStream is, @Nonnull final String filenameInZip, @Nullable final String password) {
		try {
			Checks.nullThrow(zipFile);
			Checks.nullThrow(is);
			Checks.nullThrow(filenameInZip);
			ZipFile zip = new ZipFile(zipFile);
			ZipParameters parameters = new ZipParameters();
			parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
			parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_ULTRA);
			parameters.setFileNameInZip(filenameInZip);
			parameters.setSourceExternalStream(true);
			if (!StringUtils.isEmpty(password)) {
				parameters.setEncryptFiles(true);
				parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_STANDARD);
				parameters.setPassword(password);
			}
			zip.addStream(is, parameters);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			IOs.closeQuietly(is);
		}
	}
	
	/**
	 * <pre> 添加流.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/12  huangys  Create
	 * </pre>
	 * 
	 * @param zipFile 压缩文件
	 * @param lines 行记录
	 * @param filenameInZip 流对应的文件名
	 */
	public static void addLinesWithClose(@Nonnull final File zipFile, @Nonnull final List<String> lines, @Nonnull final String filenameInZip) {
		addLinesWithClose(zipFile, lines, filenameInZip, null);
	}
	
	/**
	 * <pre> 添加流.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/12  huangys  Create
	 * </pre>
	 * 
	 * @param zipFile 压缩文件
	 * @param lines 行记录
	 * @param filenameInZip 流对应的文件名
	 * @param password 密码
	 */
	public static void addLinesWithClose(@Nonnull final File zipFile, @Nonnull final List<String> lines, @Nonnull final String filenameInZip, @Nullable final String password) {
		addStreamWithClose(zipFile, IOs.toInputStream(lines), filenameInZip, password);
	}
	
	/**
	 * <pre> 解压全部文件.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/03/02  huangys  Create
	 * </pre>
	 * 
	 * @param zipFile 压缩文件
	 * @param destPath 解压目录
	 */
	public static void extractAll(@Nonnull final File zipFile, @Nonnull final String destPath) {
		extractAll(zipFile, destPath, null);
	}
	
	/**
	 * <pre> 解压全部文件.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/03/02  huangys  Create
	 * </pre>
	 * 
	 * @param zipFile 压缩文件
	 * @param destPath 解压目录
	 * @param password 密码
	 */
	public static void extractAll(@Nonnull final File zipFile, @Nonnull final String destPath, @Nullable final String password) {
		try {
			Checks.nullThrow(zipFile);
			Checks.nullThrow(destPath);
			ZipFile zip = new ZipFile(zipFile);
			if (!StringUtils.isEmpty(password)) {
				zip.setPassword(password);
			}
			zip.extractAll(destPath);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 解压文件.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/03/02  huangys  Create
	 * </pre>
	 * 
	 * @param zipFile 压缩文件
	 * @param destPath 解压目录
	 * @param filename 文件名称
	 */
	public static void extractFile(@Nonnull final File zipFile, @Nonnull final String destPath, @Nonnull final String filename) {
		extractFile(zipFile, destPath, filename, null);
	}
	
	/**
	 * <pre> 解压文件.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/03/02  huangys  Create
	 * </pre>
	 * 
	 * @param zipFile 压缩文件
	 * @param destPath 解压目录
	 * @param filename 文件名称
	 * @param password 密码
	 */
	public static void extractFile(@Nonnull final File zipFile, @Nonnull final String destPath, @Nonnull final String filename, @Nullable final String password) {
		try {
			Checks.nullThrow(zipFile);
			Checks.nullThrow(destPath);
			ZipFile zip = new ZipFile(zipFile);
			if (!StringUtils.isEmpty(password)) {
				zip.setPassword(password);
			}
			zip.extractFile(filename, destPath);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 解压返回流.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/03/02  huangys  Create
	 * </pre>
	 * 
	 * @param zipFile 压缩文件
	 * @param filename 文件名
	 * @return 流
	 */
	public static InputStream extractStream(@Nonnull final File zipFile, @Nonnull final String filename) {
		return extractStream(zipFile, filename, null);
	}
	
	/**
	 * <pre> 解压返回流.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/03/02  huangys  Create
	 * </pre>
	 * 
	 * @param zipFile 压缩文件
	 * @param filename 文件名
	 * @param password 密码
	 * @return 流
	 */
	public static InputStream extractStream(@Nonnull final File zipFile, @Nonnull final String filename, @Nullable final String password) {
		try {
			Checks.nullThrow(zipFile);
			ZipFile zip = new ZipFile(zipFile);
			
			if (!StringUtils.isEmpty(password)) {
				zip.setPassword(password);
			}
			return zip.getInputStream(zip.getFileHeader(filename));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 获取行记录.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/03/02  huangys  Create
	 * </pre>
	 * 
	 * @param zipFile 压缩文件
	 * @param filename 文件名
	 * @return 行记录
	 */
	public static List<String> extractLines(@Nonnull final File zipFile, @Nonnull final String filename) {
		return extractLines(zipFile, filename, null);
	}
	
	/**
	 * <pre> 获取行记录.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/03/02  huangys  Create
	 * </pre>
	 * 
	 * @param zipFile 压缩文件
	 * @param filename 文件名
	 * @param password 密码
	 * @return 行记录
	 */
	public static List<String> extractLines(@Nonnull final File zipFile, @Nonnull final String filename, @Nullable final String password) {
		return extractLines(zipFile, filename, null, password);
	}
	
	/**
	 * <pre> 获取行记录.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/03/02  huangys  Create
	 * </pre>
	 * 
	 * @param zipFile 压缩文件
	 * @param filename 文件名
	 * @param charset 字符集
	 * @param password 密码
	 * @return 行记录
	 */
	public static List<String> extractLines(@Nonnull final File zipFile, @Nonnull final String filename, @Nullable final String charset, @Nullable final String password) {
		return IOs.readLinesWithClose(extractStream(zipFile, filename, password), charset);
	}
	
}
