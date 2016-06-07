/*
 * Copyright 2014 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2014/01/07.
 * 
 */
package com.toobye.common.base;

import java.awt.GraphicsEnvironment;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.annotation.Nonnull;

import com.toobye.common.string.StringSearch;

/**
 * <pre> 系统工具类.
 * 
 * org.apache.commons.lang3.SystemUtils说明
 * Java主目录：getJavaHome
 * Java临时目录：getJavaIoTmpDir
 * 当前程序运行目录：getUserDir
 * 当前用户主目录：getUserHome
 * 在无显示设备、键盘和鼠标的服务器上，用cpu计算方式模拟特性的设定：isJavaAwtHeadless
 * 当前系统是否支持Java版本：isJavaVersionAtLeast（传入枚举值）
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2014/01/07  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class Systems extends org.apache.commons.lang3.SystemUtils {
	
	@edu.umd.cs.findbugs.annotations.SuppressFBWarnings("NM_SAME_SIMPLE_NAME_AS_SUPERCLASS")
	private Systems() { }
	
	/**
	 * <pre> 获取本机IP地址.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2013/02/20  huangys  Create
	 * </pre>
	 * 
	 * @return 本地IP
	 */
	public static String getLocalHostIP() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * <pre> 获得系统字符集.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/04/30  huangys  Create
	 * </pre>
	 * 
	 * @return 系统字符集名称
	 */
	public static String getSystemEncoding() {
		return System.getProperty("sun.jnu.encoding");
	}

	/**
	 * <pre> 获得系统所有支持的字体.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/29  huangys  Create
	 * </pre>
	 * 
	 * @return 所有支持的字体
	 */
	public static String[] getAllFonts() {
        //获取系统中可用的字体的名字
      GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
      return e.getAvailableFontFamilyNames();
	}

	/**
	 * <pre> 获得标识（包名前缀）.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/01/09  huangys  Create
	 * </pre>
	 * 
	 * @return 返回标识
	 */
	@Nonnull
	public static String getIdentity() {
		String pkg = Systems.class.getPackage().getName();
		return pkg.substring(0, StringSearch.indexOfOrdinal(pkg, ".", 2));
	}
	
	private static final String ROOT = new File("").getAbsolutePath();
	/**
	 * <pre> 获得程序根目录.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/04/23  huangys  Create
	 * </pre>
	 * 
	 * @return 程序根目录
	 */
	
	public static String getRoot() {
		return ROOT;
	}
	
}
