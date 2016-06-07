/*
 * Copyright 2010 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2010-11-5.
 * 
 */
package com.toobye.common.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.concurrent.Callable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.toobye.common.concurrent.TimeSlice;
import com.toobye.common.string.StringSearch;
import com.toobye.common.thread.Invoker;

/**
 * <pre> URL Utilities.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2010-11-5  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class Urls {
	
	private TimeSlice timeout;
	
	private static final int DEFAULT_TIMEOUT_SECONDS = 10;
	
	/**
	 * <pre> 构造器. </pre>
	 *
	 * @param timeout
	 * @param proxy
	 */
	private Urls(final TimeSlice timeout) {
		this.timeout = timeout;
	}
	
	/**
	 * <pre> 实例化.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/08/10  huangys  Create
	 * </pre>
	 * 
	 * @return url工具
	 */
	public static Urls instance() {
		return new Urls(TimeSlice.seconds(DEFAULT_TIMEOUT_SECONDS));
	}
	
	/**
	 * <pre> 实例化.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/08/10  huangys  Create
	 * </pre>
	 * 
	 * @param timeout 超时设置
	 * @return url工具
	 */
	public static Urls instance(final TimeSlice timeout) {
		return new Urls(timeout);
	}
	
	/**
	 * <pre> 直连方式获取源代码内容.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/10  huangys  Create
	 * </pre>
	 * 
	 * @param url 地址
	 * @return 源代码内容
	 */
	@Nonnull
	public String getPageText(@Nonnull final String url) {
		return getPageText(url, null);
	}
	
	/**
	 * <pre> 直连方式获取源代码内容.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/10  huangys  Create
	 * </pre>
	 * 
	 * @param url 地址
	 * @param charset 字符集
	 * @return 源代码内容
	 */
	@Nonnull
	public String getPageText(@Nonnull final String url, @Nullable final String charset) {
		return Invoker.timeout(new Callable<String>() {
			@Override
			public String call() throws Exception {
				return getPageTextSub(url, charset);
			}
		}, timeout);
	}
	
	/**
	 * <pre> 直连方式获取源代码内容.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/08/10  huangys  Create
	 * </pre>
	 * 
	 * @param url 地址
	 * @param charset 字符集
	 * @return 源代码内容
	 */
	@Nonnull
	private String getPageTextSub(@Nonnull final String url, @Nullable final String charset) {
		BufferedReader reader = null;
		try {
			URLConnection con = getUrlConnection(url);
			// con.setRequestProperty("ContentType","text/xml;charset=utf-8");
			// 模拟来源页
			int pos = StringSearch.indexOfOrdinal(url, "/", 3);
			if (pos != -1) {
				con.setRequestProperty("Referer", url.substring(0, pos));
			} else {
				con.setRequestProperty("Referer", url);
			}
			// 模拟UA
			con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-GB; rv:1.9.2.13) Gecko/20101203 Firefox/3.6.13 (.NET CLR 3.5.30729)"); 
			StringBuilder builder = new StringBuilder();
			if (charset == null) {
				reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				reader = new BufferedReader(new InputStreamReader(con.getInputStream(), charset));
			}
			String line;
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}
			return builder.toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (reader != null) {
				try { reader.close(); } catch (Exception e2) { }
			}
		}
	}
	
	/**
	 * <pre> Post提交后，获取源代码内容.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/29  huangys  Create
	 * </pre>
	 * 
	 * @param url 地址
	 * @param post post信息
	 * @return 源代码内容
	 */
	@Nonnull
	public String postPageText(@Nonnull final String url, @Nonnull final String post) {
		return postPageText(url, post, null);
	}

	/**
	 * <pre> Post提交后，获取源代码内容.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/29  huangys  Create
	 * </pre>
	 * 
	 * @param url 地址
	 * @param post post信息
	 * @param charset 字符集
	 * @return 源代码内容
	 */
	@Nonnull
	public String postPageText(@Nonnull final String url, @Nonnull final String post, @Nullable final String charset) {
		return Invoker.timeout(new Callable<String>() {
			@Override
			public String call() throws Exception {
				return postPageTextSub(url, post, charset);
			}
		}, timeout);
	}
	
	/**
	 * <pre> Post提交后，获取源代码内容.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/29  huangys  Create
	 * </pre>
	 * 
	 * @param url 地址
	 * @param post post信息
	 * @param charset 字符集
	 * @return 源代码内容
	 */
	@Nonnull
	private String postPageTextSub(@Nonnull final String url, @Nonnull final String post, @Nullable final String charset) {
		String cs = (charset == null ? Charset.defaultCharset().toString() : charset);
		BufferedReader reader = null;
		try {
			URLConnection con = getUrlConnection(url);
			con.setDoOutput(true);
			con.setRequestProperty("Accept-Charset", cs);
			con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-GB; rv:1.9.2.13) Gecko/20101203 Firefox/3.6.13 (.NET CLR 3.5.30729)"); 
			try (OutputStream output = con.getOutputStream()) {
			    output.write(post.getBytes(cs));
			}
			
			StringBuilder builder = new StringBuilder();
			reader = new BufferedReader(new InputStreamReader(con.getInputStream(), cs));
			String line;
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}
			return builder.toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (reader != null) {
				try { reader.close(); } catch (Exception e2) { }
			}
		}
	}

	private URLConnection getUrlConnection(final String url) throws IOException {
		return new URL(url).openConnection();
	}
	
}