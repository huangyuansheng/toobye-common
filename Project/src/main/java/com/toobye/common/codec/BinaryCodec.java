/*
 * Copyright 2014 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2014/01/06.
 * 
 */
package com.toobye.common.codec;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * <pre> 位(二进制)工具类.
 * 
 * org.apache.commons.codec.binary.BinaryCodec说明
 * 字符串转为位信息(字符串"1" -> 二进制字符串"00110001")：
 * 		toAsciiBytes/toAsciiChars/toAsciiString: 
 * 位信息还原为字符串(二进制字符串"00110001" -> 字符串"1")：
 * 		fromAscii
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2014/01/06  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class BinaryCodec extends org.apache.commons.codec.binary.BinaryCodec {
	
	@edu.umd.cs.findbugs.annotations.SuppressFBWarnings("NM_SAME_SIMPLE_NAME_AS_SUPERCLASS")
	private BinaryCodec() { }
	
	/**
	 * <pre> 字符串转为位信息(字符串"1" -> 二进制字符串"00110001").
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/01  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @return 位信息字节数组
	 */
	@Nonnull
	public static byte[] toAsciiBytes(@Nullable final String str) {
		return toAsciiBytes(str == null ? null : str.getBytes());
	}
	
	/**
	 * <pre> 字符串转为位信息(字符串"1" -> 二进制字符串"00110001").
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/01  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @return 位信息字符数组
	 */
	@Nonnull
	public static char[] toAsciiChars(@Nullable final String str) {
		return toAsciiChars(str == null ? null : str.getBytes());
	}
	
	/**
	 * <pre> 字符串转为位信息(字符串"1" -> 二进制字符串"00110001").
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/01  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @return 位信息字符串
	 */
	@Nonnull
	public static String toAsciiString(@Nullable final String str) {
		return toAsciiString(str == null ? null : str.getBytes());
	}
	
	/**
	 * <pre> 位信息还原为字符串(二进制字符串"00110001" -> 字符串"1").
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/01  huangys  Create
	 * </pre>
	 * 
	 * @param str 位信息字符串
	 * @return 字节数组
	 */
	@Nonnull
	public static byte[] fromAscii(@Nullable final String str) {
		return fromAscii(str == null ? null : str.getBytes());
	}
	
	/**
	 * <pre> 位信息还原为字符串(二进制字符串"00110001" -> 字符串"1").
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/01  huangys  Create
	 * </pre>
	 * 
	 * @param str 位信息字符串
	 * @return 字符串
	 */
	@Nonnull
	public static String fromAsciiString(@Nullable final String str) {
		return new String(fromAscii(str == null ? null : str.getBytes()));
	}
	
}
