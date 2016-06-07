/*
 * Copyright 2014 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2014/12/26.
 * 
 */
package com.toobye.common.net;

import java.io.IOException;
import java.net.Socket;

import com.toobye.common.string.StringUtils;

/**
 * <pre> Ping工具类.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2014/12/28  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class Ping {
	
	// http://stackoverflow.com/questions/3584210/preferred-java-way-to-ping-a-http-url-for-availability
	
	private Ping() { }
	
	private static final String TEST_DOMAIN = "www.baidu.com";
	/**
	 * <pre> 检测Internet网络是否正常.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/12/28  huangys  Create
	 * </pre>
	 * 
	 * @return 是否正常
	 */
	public static boolean ping() {
		return pingHttp(TEST_DOMAIN);
	}
	
	/**
	 * <pre> 检测域名是否可以连通.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/12/28  huangys  Create
	 * </pre>
	 * 
	 * @param host 地址
	 * @return 是否可以连通
	 */
	public static boolean pingHttp(final String host) {
		return ping(StringUtils.removeStart(host, "http://"), 80);
	}
	
	/**
	 * <pre> 检测指定地址-端口是否可以连通.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/12/28  huangys  Create
	 * </pre>
	 * 
	 * @param host 地址
	 * @param port 端口
	 * @return 是否可以连通
	 */
	public static boolean ping(final String host, final int port) {
		Socket socket = null;
		try {
		    socket = new Socket(host, port);
		    return true;
		} catch (IOException e) {
			return false;
		} finally {            
		    if (socket != null) {
		    	try { socket.close(); } catch (IOException e) { }
		    }
		}
	}

}