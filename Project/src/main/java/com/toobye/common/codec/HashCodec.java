/*
 * Copyright 2013 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2013/12/20.
 * 
 */
package com.toobye.common.codec;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

import javax.annotation.Nonnull;

import com.toobye.common.lang.Checks;

/**
 * <pre> 计算Hash值.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2013/12/20  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class HashCodec {
	
	private HashCodec() { }
	
	private static final String SHA = "SHA";
	private static final String MD5 = "MD5";
	
	/**
	 * <pre> 获得文件的MD5函数值.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/17  huangys  Create
	 * </pre>
	 * 
	 * @param filename 文件名称
	 * @return MD5函数值
	 */
	@Nonnull
	public static String getFileHashMD5(@Nonnull final String filename) {
		Checks.nullThrow(filename);
		return getFileHashMD5(new File(filename));
	}
	
	/**
	 * <pre> 获得文件的MD5函数值.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/17  huangys  Create
	 * </pre>
	 * 
	 * @param file 文件
	 * @return MD5函数值
	 */
	@Nonnull
	public static String getFileHashMD5(@Nonnull final File file) {
		return getFileHash(file, MD5);
	}
	
	/**
	 * <pre> 获得文件的SHA函数值.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/17  huangys  Create
	 * </pre>
	 * 
	 * @param filename 文件名称
	 * @return SHA函数值
	 */
	@Nonnull
	public static String getFileHashSHA(@Nonnull final String filename) {
		Checks.nullThrow(filename);
		return getFileHashSHA(new File(filename));
	}
	
	/**
	 * <pre> 获得文件的SHA函数值.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/17  huangys  Create
	 * </pre>
	 * 
	 * @param file 文件
	 * @return SHA函数值
	 */
	@Nonnull
	public static String getFileHashSHA(@Nonnull final File file) {
		return getFileHash(file, SHA);
	}
	
	private static final int BUFFER_SIZE = 1024;
	private static String getFileHash(@Nonnull final File file, @Nonnull final String algorithm) {
		Checks.fileNotExistsThrow(file);
        try {
    		InputStream fis =  new FileInputStream(file);
            byte[] buffer = new byte[BUFFER_SIZE];
            MessageDigest md = MessageDigest.getInstance(algorithm);
            int numRead;
            do {
                numRead = fis.read(buffer);
                if (numRead > 0) {
                    md.update(buffer, 0, numRead);
                }
            } while (numRead != -1);

            fis.close();
            return HexCodec.encodeHexString(md.digest());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 获得字符串的MD5函数值.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/17  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @return MD5函数值
	 */
	@Nonnull
	public static String getHashMD5(@Nonnull final String str) {
		Checks.nullThrow(str);
		return getHashMD5(str.getBytes());
	}
	
	/**
	 * <pre> 获得字符串的SHA函数值.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/17  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @return SHA函数值
	 */
	@Nonnull
	public static String getHashSHA(@Nonnull final String str) {
		Checks.nullThrow(str);
		return getHashSHA(str.getBytes());
	}
	
	/**
	 * <pre> 获得字节数组的MD5函数值.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/17  huangys  Create
	 * </pre>
	 * 
	 * @param bytes 字节数组
	 * @return MD5函数值
	 */
	@Nonnull
	public static String getHashMD5(@Nonnull final byte[] bytes) {
		Checks.nullThrow(bytes);
		return getHash(bytes, MD5);
	}
	
	/**
	 * <pre> 获得字节数组的SHA函数值.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/12/17  huangys  Create
	 * </pre>
	 * 
	 * @param bytes 字节数组
	 * @return SHA函数值
	 */
	@Nonnull
	public static String getHashSHA(@Nonnull final byte[] bytes) {
		Checks.nullThrow(bytes);
		return getHash(bytes, SHA);
	}
	
	private static String getHash(@Nonnull final byte[] bytes, @Nonnull final String algorithm) {
		Checks.nullThrow(bytes);
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            return HexCodec.encodeHexString(md.digest(bytes));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
