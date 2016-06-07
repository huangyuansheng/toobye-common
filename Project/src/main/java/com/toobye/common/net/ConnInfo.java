/*
 * Copyright 2014 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2014/08/13.
 * 
 */
package com.toobye.common.net;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * <pre> 连接信息.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2014/08/13  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class ConnInfo {
	
	/**
	 * <pre> 构造器. </pre>
	 *
	 * @param host 主机
	 * @param port 端口
	 * @param user 用户
	 * @param password 密码
	 */
	private ConnInfo(@Nonnull final String host, @Nonnull final int port, @Nullable final String user, @Nullable final String password) {
		if (host == null) {
			throw new RuntimeException("Host cannot be null.");
		}
		this.host = host;
		this.port = port;
		this.user = user;
		this.password = password;
	}
	
	private String host;
	private int port;
	private String user;
	private String password;
	
	/**
	 * <pre> 获得主机.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/13  huangys  Create
	 * </pre>
	 * 
	 * @return 主机
	 */
	@Nonnull
	public String getHost() {
		return host;
	}
	
	/**
	 * <pre> 获取端口.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/13  huangys  Create
	 * </pre>
	 * 
	 * @return 端口
	 */
	@Nonnull
	public int getPort() {
		return port;
	}
	
	/**
	 * <pre> 获取用户.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/13  huangys  Create
	 * </pre>
	 * 
	 * @return 用户
	 */
	@Nullable
	public String getUser() {
		return user;
	}
	
	/**
	 * <pre> 获取密码.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/13  huangys  Create
	 * </pre>
	 * 
	 * @return 密码
	 */
	@Nullable
	public String getPassword() {
		return password;
	}

	/**
	 * <pre> 获取连接信息.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/13  huangys  Create
	 * </pre>
	 * 
	 * @param host 主机
	 * @param port 端口
	 * @param user 用户
	 * @param password 密码
	 * @return 连接信息
	 */
	@Nonnull
	public static ConnInfo getConnInfo(@Nonnull final String host, @Nonnull final int port, @Nullable final String user, @Nullable final String password) {
		return new ConnInfo(host, port, user, password);
	}
	
	/**
	 * <pre> 获取连接信息.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/13  huangys  Create
	 * </pre>
	 * 
	 * @param host 主机
	 * @param port 端口
	 * @return 连接信息
	 */
	@Nonnull
	public static ConnInfo getConnInfo(@Nonnull final String host, @Nonnull final int port) {
		return getConnInfo(host, port, null, null);
	}
	
	/**
	 * <pre> 获取连接信息.
	 * {主机, 端口} 或者 {主机, 端口, 用户, 密码}
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/13  huangys  Create
	 * </pre>
	 * 
	 * @param info 连接信息的字符串数组
	 * @return 连接信息
	 */
	@Nonnull
	public static ConnInfo getConnInfo(@Nonnull final String[] info) {
		if (info.length == 2) {
			return getConnInfo(info[0], Integer.parseInt(info[1]));
		} else if (info.length == 4) {
			return getConnInfo(info[0], Integer.parseInt(info[1]), info[2], info[3]);
		} else {
			throw new RuntimeException("Info must be {host, port} or {host, port, user, password}");
		}
	}
	
	private static final int SSH_DEFAULT_PORT = 22;
	/**
	 * <pre> 获取SSH连接信息.
	 * port使用默认端口号22
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/13  huangys  Create
	 * </pre>
	 * 
	 * @param host 主机
	 * @param user 用户
	 * @param password 密码
	 * @return 连接信息
	 */
	@Nonnull
	public static ConnInfo getSshConnInfo(@Nonnull final String host, @Nullable final String user, @Nullable final String password) {
		return new ConnInfo(host, SSH_DEFAULT_PORT, user, password);
	}
	
	private static final int FTP_DEFAULT_PORT = 21;
	/**
	 * <pre> 获取FTP连接信息.
	 * port使用默认端口号21
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/13  huangys  Create
	 * </pre>
	 * 
	 * @param host 主机
	 * @param user 用户
	 * @param password 密码
	 * @return 连接信息
	 */
	@Nonnull
	public static ConnInfo getFtpConnInfo(@Nonnull final String host, @Nullable final String user, @Nullable final String password) {
		return new ConnInfo(host, FTP_DEFAULT_PORT, user, password);
	}
	
}
