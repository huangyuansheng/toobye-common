/*
 * Copyright 2012 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2012/06/25.
 * 
 */
package com.toobye.common.net;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.net.ftp.FTPFile;

import com.toobye.common.concurrent.TimeSlice;
import com.toobye.common.lang.Checks;

/**
 * <pre> FTP客户端.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2014/04/23  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class FtpClient implements AutoCloseable {

	private org.apache.commons.net.ftp.FTPClient ftpClient = new org.apache.commons.net.ftp.FTPClient();
	private ConnInfo connInfo;
	
	/**
	 * <pre> 初始化FTP客户端. </pre>
	 * 
	 * @param connInfo FTP连接信息
	 */
	public FtpClient(@Nonnull final ConnInfo connInfo) {
		this.connInfo = connInfo;
	}

	/**
	 * <pre> 设置被动模式.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/12/19  huangys  Create
	 * </pre>
	 * 
	 */
	public void enablePassiveMode() {
		this.ftpClient.enterLocalPassiveMode();
	}
	
	/**
	 * <pre> 登入.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2012/06/25  huangys  Create
	 * </pre>
	 * 
	 * @return 是否成功
	 */
	public boolean login() {
		try {
			// ftpClient.setControlEncoding("UTF-8");
			this.ftpClient.connect(connInfo.getHost(), connInfo.getPort());
			return this.ftpClient.login(connInfo.getUser(), connInfo.getPassword());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void close() {
		if (this.ftpClient != null) {
			if (this.ftpClient.isAvailable()) {
				try { this.ftpClient.logout(); } catch (Exception e) { }
			}
			if (this.ftpClient.isConnected()) {
				try { this.ftpClient.disconnect(); } catch (Exception e) { }
			}
		}
	}

	/**
	 * <pre> 创建文件夹.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2012/06/25  huangys  Create
	 * </pre>
	 * 
	 * @param path 路径
	 * @return 是否成功
	 */
	public boolean mkdir(@Nonnull final String path) {
		Checks.nullThrow(path);
		try {
			return this.ftpClient.makeDirectory(path);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * <pre> 上传文件.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/18  huangys  Create
	 * </pre>
	 * 
	 * @param sourceLocal 本地文件
	 * @param destRemote 远程目标
	 * @return 是否成功
	 */
	public boolean upFile(@Nonnull final String sourceLocal, @Nonnull final String destRemote) {
		Checks.nullThrow(sourceLocal);
		Checks.nullThrow(destRemote);
		try (InputStream input = new FileInputStream(sourceLocal);) {
			this.ftpClient.setFileType(org.apache.commons.net.ftp.FTPClient.BINARY_FILE_TYPE);
			return this.ftpClient.storeFile(destRemote, input);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * <pre> 下载文件.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/18  huangys  Create
	 * </pre>
	 * 
	 * @param sourceRemote 远程文件
	 * @param destLocal 本地目标
	 * @return 是否成功
	 */
	public boolean downFile(@Nonnull final String sourceRemote, @Nonnull final String destLocal) {
		Checks.nullThrow(sourceRemote);
		Checks.nullThrow(destLocal);
		try (OutputStream output = new FileOutputStream(destLocal);) {
			 this.ftpClient.setFileType(org.apache.commons.net.ftp.FTPClient.BINARY_FILE_TYPE);
			 return ftpClient.retrieveFile(sourceRemote, output);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 检查文件是否存在(若为目录则返回为false).
	 * 
	 * Modification History:
	 * Date       Author     Action
	 * 2013/01/06 huangys    Create
	 * </pre>
	 * 
	 * @param fileName 文件名
	 * @return 是否存在
	 */
	public boolean fileExists(@Nonnull final String fileName) {
		Checks.nullThrow(fileName);
		try {
			FTPFile[] files = ftpClient.listFiles(fileName);
			if (files.length == 1) {
				if (files[0].isDirectory()) {
					return false;
				} else {
					String temp = fileName;
					if (fileName.endsWith("/")) {
						temp += files[0].getName();
					} else {
						temp += "/" + files[0].getName();
					}
					return ftpClient.listFiles(temp).length == 0;
				}
			} else {
				return false;
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * <pre> 返回目录下的文件清单.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/12/19  huangys  Create
	 * </pre>
	 * 
	 * @param path 路径
	 * @return 文件列表
	 */
	public List<String> listFiles(@Nonnull final String path) {
		return listFiles(path, null);
	}
	
	/**
	 * <pre> 返回目录下的文件清单.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/12/19  huangys  Create
	 * </pre>
	 * 
	 * @param path 路径
	 * @param nameRegexFilter 文件名正则表达式筛选
	 * @return 文件列表
	 */
	public List<String> listFiles(@Nonnull final String path, @Nullable final String nameRegexFilter) {
		Checks.nullThrow(path);
		try {
			FTPFile[] files = ftpClient.listFiles(path);
			List<String> ret = new ArrayList<String>();
			for (FTPFile ftpFile : files) {
				String name = ftpFile.getName();
				if (nameRegexFilter == null
						|| name.matches(nameRegexFilter)) {
					ret.add(name);
				}
			}
			return ret;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private static final TimeSlice INTERVAL = TimeSlice.seconds(10);
	/**
	 * <pre> 判断文件是否传输完.
	 * 
	 * 间隔10秒，判断文件大小一致性，连续判断3次。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/12/20  huangys  Create
	 * </pre>
	 * 
	 * @param filename 文件名
	 * @return 是否完成传输
	 */
	public boolean checkFileStable(@Nonnull final String filename) {
		Checks.nullThrow(filename);
		try {
			FTPFile[] files = ftpClient.listFiles(filename);
			if (files.length != 1) {
				throw new RuntimeException("Count of files is " + files.length + ".");
			}
			if (!files[0].isFile()) {
				throw new RuntimeException(filename + " is not a file.");
			}
			long size = files[0].getSize();
			for (int i = 0; i < 2; i++) {
				INTERVAL.sleep();
				long newSize = ftpClient.listFiles(filename)[0].getSize();
				if (size != newSize) {
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}