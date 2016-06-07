/*
 * Copyright 2013 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2014/08/11.
 * 
 */
package com.toobye.common.io;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.LineIterator;

import com.toobye.common.collection.Arrays;
import com.toobye.common.lang.Checks;
import com.toobye.common.string.StringArray;

/**
 * <pre> IO工具类.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2014/08/11  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class IOs extends org.apache.commons.io.IOUtils {

	private IOs() { }
	
	/**
	 * <pre> 函数实现来源.
	 * 
	 * Modification History:
	 * Date        Author   Version   Action
	 * 2014/08/11  huangys  v1.0      Create
	 * </pre>
	 * 
	 */
	private static class INSTANCE extends org.apache.commons.io.IOUtils { };
	
	/**
	 * <pre> 读取全部行内容.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/11  huangys  Create
	 * </pre>
	 * 
	 * @param in 输入流
	 * @return 行集合
	 */
	@Nonnull
	public static List<String> readLines(@Nonnull final InputStream in) {
		try {
			return INSTANCE.readLines(in);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 读取全部行内容.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/11/24  huangys  Create
	 * </pre>
	 * 
	 * @param in 输入流
	 * @param charset 字符集
	 * @return 行集合
	 */
	@Nonnull
	public static List<String> readLines(@Nonnull final InputStream in, @Nullable final String charset) {
		try {
			return INSTANCE.readLines(in, charset);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 读取全部行内容.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/11  huangys  Create
	 * </pre>
	 * 
	 * @param in 输入流
	 * @return 行集合
	 */
	@Nonnull
	public static List<String> readLinesWithClose(@Nonnull final InputStream in) {
		try {
			return readLines(in);
		} finally {
			INSTANCE.closeQuietly(in);
		}
	}
	
	/**
	 * <pre> 读取全部行内容.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/11/24  huangys  Create
	 * </pre>
	 * 
	 * @param in 输入流
	 * @param charset 字符集
	 * @return 行集合
	 */
	@Nonnull
	public static List<String> readLinesWithClose(@Nonnull final InputStream in, @Nullable final String charset) {
		try {
			return readLines(in, charset);
		} finally {
			INSTANCE.closeQuietly(in);
		}
	}
	
	/**
	 * <pre> 向输出流写入行数据.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/11/24  huangys  Create
	 * </pre>
	 * 
	 * @param lines 待写入内容
	 * @param out 输出流
	 */
	public static void writeLines(@Nullable final Collection<?> lines, @Nonnull final OutputStream out) {
		try {
			INSTANCE.writeLines(lines, null, out);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 向输出流写入行数据.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/11/24  huangys  Create
	 * </pre>
	 * 
	 * @param lines 待写入内容
	 * @param out 输出流
	 * @param charset 字符集
	 */
	public static void writeLines(@Nullable final Collection<?> lines, @Nonnull final OutputStream out, @Nullable final String charset) {
		try {
			INSTANCE.writeLines(lines, null, out, charset);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 向输出流写入行数据.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/11/24  huangys  Create
	 * </pre>
	 * 
	 * @param lines 待写入内容
	 * @param out 输出流
	 */
	public static void writeLinesWithClose(@Nullable final Collection<?> lines, @Nonnull final OutputStream out) {
		try {
			writeLines(lines, out);
		} finally {
			INSTANCE.closeQuietly(out);
		}
	}
	
	/**
	 * <pre> 向输出流写入行数据.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/11/24  huangys  Create
	 * </pre>
	 * 
	 * @param lines 待写入内容
	 * @param out 输出流
	 * @param charset 字符集
	 */
	public static void writeLinesWithClose(@Nullable final Collection<?> lines, @Nonnull final OutputStream out, @Nullable final String charset) {
		try {
			writeLines(lines, out, charset);
		} finally {
			INSTANCE.closeQuietly(out);
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
     * @param input 输入流
     * @return 行迭代器
     */
	@Nonnull
    public static LineIterator lineIterator(@Nonnull final InputStream input) {
        return lineIterator(input, (String) null);
    }
  
    /**
     * <pre> 返回行迭代器.
     * 
     * Modification History:
     * Date        Author   Action
     * 2015/12/17  huangys  Create
     * </pre>
     * 
     * @param input 输入流
     * @param encoding 字符集
     * @return 行迭代器
     */
	@Nonnull
    public static LineIterator lineIterator(@Nonnull final InputStream input, @Nullable final String encoding) {
        Checks.nullThrow(input);
		try {
        	return INSTANCE.lineIterator(input, encoding);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
	
	/**
	 * <pre> 读取指定个数的字节.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/18  huangys  Create
	 * </pre>
	 * 
	 * @param is 输入流
	 * @param len 读取字节数
	 * @return 字节数组
	 */
	@Nonnull
	public static byte[] readBytes(@Nonnull final InputStream is, @Nonnull final int len) {
		try {
			byte[] bytes = new byte[len];
			int count = INSTANCE.read(is, bytes);
			Checks.notMatchThrow(count == len, "InputStream doesnot have enough bytes.");
			return bytes;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 读入2字节整型.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/18  huangys  Create
	 * </pre>
	 * 
	 * @param is 输入流
	 * @return 两字节整型
	 */
	@Nonnull
	public static int readShort(@Nonnull final InputStream is) {
		return toInt(readBytes(is, 2));
	}
	
	/**
	 * <pre> 读入4字节整型.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/18  huangys  Create
	 * </pre>
	 * 
	 * @param is 输入流
	 * @return 四字节整型
	 */
	@Nonnull
	public static int readInt(@Nonnull final InputStream is) {
		return toInt(readBytes(is, 4));
	}
	
	/**
	 * <pre> 读入4字节浮点数.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/18  huangys  Create
	 * </pre>
	 * 
	 * @param is 输入流
	 * @return 四字节浮点数
	 */
	@Nonnull
	public static float readFloat(@Nonnull final InputStream is) {
		return toFloat(readBytes(is, 4));
	}
	
	/**
	 * <pre> 读入指定字节数的字符串.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/31  huangys  Create
	 * </pre>
	 * 
	 * @param is 输入流
	 * @param length 字节数
	 * @return 字符串
	 */
	@Nonnull
	public static String readString(@Nonnull final InputStream is, @Nonnull final int length) {
		return readString(is, length, null);
	}
	
	/**
	 * <pre> 读入指定字节数的字符串，跳过为00的字节.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/31  huangys  Create
	 * </pre>
	 * 
	 * @param is 输入流
	 * @param length 字节数
	 * @return 字符串
	 */
	@Nonnull
	public static String readStringSkipEmpty(@Nonnull final InputStream is, @Nonnull final int length) {
		return readStringSkipEmpty(is, length, null);
	}
	
	/**
	 * <pre> 读入指定字节数的字符串.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/31  huangys  Create
	 * </pre>
	 * 
	 * @param is 输入流
	 * @param length 字节数
	 * @param charset 字符集
	 * @return 字符串
	 */
	@Nonnull
	public static String readString(@Nonnull final InputStream is, @Nonnull final int length, @Nullable final String charset) {
		return toString(readBytes(is, length), charset);
	}
	
	/**
	 * <pre> 读入指定字节数的字符串，跳过为00的字节.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/31  huangys  Create
	 * </pre>
	 * 
	 * @param is 输入流
	 * @param length 字节数
	 * @param charset 字符集
	 * @return 字符串
	 */
	@Nonnull
	public static String readStringSkipEmpty(@Nonnull final InputStream is, @Nonnull final int length, @Nullable final String charset) {
		return toStringSkipEmpty(readBytes(is, length), charset);
	}
	
	/**
	 * <pre> 字节数组转为整型.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/18  huangys  Create
	 * </pre>
	 * 
	 * @param bytes 字节数组
	 * @return 整型
	 */
	@Nonnull
	public static int toInt(@Nonnull final byte[] bytes) {
		Arrays.reverse(bytes);
		return new BigInteger(bytes).intValue();
	}
	
	/**
	 * <pre> 字节数组转为浮点数.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/18  huangys  Create
	 * </pre>
	 * 
	 * @param bytes 字节数组
	 * @return 浮点数
	 */
	@Nonnull
	public static float toFloat(@Nonnull final byte[] bytes) {
		return Float.intBitsToFloat(toInt(bytes));
	}
	
	/**
	 * <pre> 字节数组转为字符串.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/31  huangys  Create
	 * </pre>
	 * 
	 * @param bytes 字节数组
	 * @param charset 字符集
	 * @return 字符串
	 */
	@Nonnull
	public static String toString(@Nonnull final byte[] bytes, @Nullable final String charset) {
		try {
			return charset == null ? new String(bytes) : new String(bytes, charset);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 字节数组转为字符串，跳过为00的字节.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/31  huangys  Create
	 * </pre>
	 * 
	 * @param bytes 字节数组
	 * @param charset 字符集
	 * @return 字符串
	 */
	@Nonnull
	public static String toStringSkipEmpty(@Nonnull final byte[] bytes, @Nullable final String charset) {
		List<Byte> list = new ArrayList<Byte>();
		for (Byte b : bytes) {
			if (b != 0) {
				list.add(b);
			}
		}
		return toString(Arrays.toPrimitive(list.toArray(new Byte[list.size()])), charset);
	}

    /**
     * <pre> 字符串转为流.
     * 
     * Modification History:
     * Date        Author   Action
     * 2016/03/02  huangys  Create
     * </pre>
     * 
     * @param cs 字符串
     * @return 流
     */
    public static InputStream toInputStream(@Nonnull final CharSequence cs) {
        return toInputStream(cs, (String) null);
    }

    /**
     * <pre> 字符串转为流.
     * 
     * Modification History:
     * Date        Author   Action
     * 2016/03/02  huangys  Create
     * </pre>
     * 
     * @param cs 字符串
     * @param charset 字符集
     * @return 流
     */
    public static InputStream toInputStream(@Nonnull final CharSequence cs, @Nullable final String charset) {
        return INSTANCE.toInputStream(cs, Charsets.toCharset(charset));
    }
	
    /**
     * <pre> 行记录转为流.
     * 
     * Modification History:
     * Date        Author   Action
     * 2016/03/02  huangys  Create
     * </pre>
     * 
     * @param lines 行记录
     * @return 流
     */
    public static InputStream toInputStream(@Nonnull final List<String> lines) {
        return toInputStream(lines, null);
    }

    /**
     * <pre> 行记录转为流.
     * 
     * Modification History:
     * Date        Author   Action
     * 2016/03/02  huangys  Create
     * </pre>
     * 
     * @param lines 行记录
     * @param charset 字符集
     * @return 流
     */
    public static InputStream toInputStream(@Nonnull final List<String> lines, @Nullable final String charset) {
        return INSTANCE.toInputStream(StringArray.join(lines, Files.LINE_SEPARATOR), Charsets.toCharset(charset));
    }
    
}
