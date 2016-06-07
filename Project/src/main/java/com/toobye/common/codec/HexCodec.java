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

import org.apache.commons.codec.DecoderException;

/**
 * <pre> 编码处理工具类.
 * 
 * org.apache.commons.codec.binary.Hex说明
 * 字符串每个字节的高低4位分拆成十六进制字符串（字符串"1" -> 十六进制字符串"31"）:
 * 		encodeHex
 * 十六进制字符串还原为字符串（十六进制字符串"31" -> 字符串"1"）:
 * 		decodeHex
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2014/01/06  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class HexCodec extends org.apache.commons.codec.binary.Hex {
	
	private HexCodec() { }
	
	/**
	 * <pre> 字符串每个字节的高低4位分拆成十六进制字符串（字符串"1" -> 十六进制字符串"31"）.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/01  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @return 十六进制字符数组
	 */
	@Nullable
	public static char[] encodeHex(@Nullable final String str) {
		return str == null ? null : encodeHex(str.getBytes());
	}
	
	/**
	 * <pre> 字符串每个字节的高低4位分拆成十六进制字符串（字符串"1" -> 十六进制字符串"31"）.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/01  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @param toLowerCase 是否转为小写
	 * @return 十六进制字符数组
	 */
	@Nullable
	public static char[] encodeHex(@Nullable final String str, @Nonnull final boolean toLowerCase) {
		return str == null ? null : encodeHex(str.getBytes(), toLowerCase);
	}
	
	/**
	 * <pre> 字符串每个字节的高低4位分拆成十六进制字符串（字符串"1" -> 十六进制字符串"31"）.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/01  huangys  Create
	 * </pre>
	 * 
	 * @param str 字符串
	 * @return 十六进制字符串
	 */
	@Nullable
	public static String encodeHexString(@Nullable final String str) {
		return str == null ? null : encodeHexString(str.getBytes());
	}
	
	/**
	 * <pre> 十六进制字符串还原为字符串（十六进制字符串"31" -> 字符串"1"）.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/01  huangys  Create
	 * </pre>
	 * 
	 * @param str 十六进制字符数组
	 * @return 字节数组
	 */
	@Nullable
	public static byte[] decodeHex(@Nullable final String str) {
		try {
			return str == null ? null : decodeHex(str.toCharArray());
		} catch (DecoderException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 十六进制字符串还原为字符串（十六进制字符串"31" -> 字符串"1"）.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/04/01  huangys  Create
	 * </pre>
	 * 
	 * @param str 十六进制字符数组
	 * @return 字符串
	 */
	@Nullable
	public static String decodeHexString(@Nullable final String str) {
		return str == null ? null : new String(decodeHex(str));
	}
	
}
