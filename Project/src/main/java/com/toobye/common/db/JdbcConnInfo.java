/*
 * Copyright 2014 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2014/09/28.
 * 
 */
package com.toobye.common.db;

import java.sql.Connection;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * <pre> Jdbc连接信息.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2014/09/28  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class JdbcConnInfo {
	
	/**
	 * <pre> 获得数据库连接.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/07/22  huangys  Create
	 * </pre>
	 * 
	 * @return 数据库连接
	 */
	public Connection getConnection() {
		return DBConn.getConnection(driver, url, user, password);
	}
	
	/**
	 * <pre> 构造器. </pre>
	 *
	 * @param driver 驱动程序
	 * @param url 数据库地址
	 * @param user 用户
	 * @param password 密码
	 */
	private JdbcConnInfo(@Nonnull final String driver, @Nonnull final String url, @Nullable final String user, @Nullable final String password) {
		if (driver == null || url == null) {
			throw new RuntimeException("Driver & Url cannot be null.");
		}
		this.driver = driver;
		this.url = url;
		this.user = user;
		this.password = password;
	}
	
	private String driver;
	private String url;
	private String user;
	private String password;
	
	@Override
	public int hashCode() {
		return (driver + url + user + password).hashCode();
	}
	
	@Override
	public boolean equals(final Object obj) {
		if (obj == null || !(obj instanceof JdbcConnInfo)) {
			return false;
		}
		JdbcConnInfo another = (JdbcConnInfo) obj;
		return (driver + url + user + password).equals(
						another.driver + another.url + another.user + another.password);
	}
	
	/**
	 * <pre> 获得驱动程序.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/09/28  huangys  Create
	 * </pre>
	 * 
	 * @return 驱动程序
	 */
	@Nonnull
	public String getDriver() {
		return driver;
	}

	/**
	 * <pre> 获得数据库地址.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/09/28  huangys  Create
	 * </pre>
	 * 
	 * @return 数据库地址
	 */
	@Nonnull
	public String getUrl() {
		return url;
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
	 * <pre> 获取Jdbc连接信息.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/13  huangys  Create
	 * </pre>
	 * 
	 * @param driver 驱动程序
	 * @param url 数据库地址
	 * @param user 用户
	 * @param password 密码
	 * @return Jdbc连接信息
	 */
	@Nonnull
	public static JdbcConnInfo getJdbcConnInfo(@Nonnull final String driver, @Nonnull final String url, @Nullable final String user, @Nullable final String password) {
		return new JdbcConnInfo(driver, url, user, password);
	}
	
	/**
	 * <pre> 获取Jdbc连接信息.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/13  huangys  Create
	 * </pre>
	 * 
	 * @param driver 驱动程序
	 * @param url 数据库地址
	 * @return Jdbc连接信息
	 */
	@Nonnull
	public static JdbcConnInfo getJdbcConnInfo(@Nonnull final String driver, @Nonnull final String url) {
		return getJdbcConnInfo(driver, url, null, null);
	}
	
	/**
	 * <pre> 获取Jdbc连接信息.
	 * {驱动程序, 数据库地址} 或者 {驱动程序, 数据库地址, 用户, 密码}
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/13  huangys  Create
	 * </pre>
	 * 
	 * @param info 连接信息的字符串数组
	 * @return Jdbc连接信息
	 */
	@Nonnull
	public static JdbcConnInfo getJdbcConnInfo(@Nonnull final String[] info) {
		if (info.length == 2) {
			return getJdbcConnInfo(info[0], info[1]);
		} else if (info.length == 4) {
			return getJdbcConnInfo(info[0], info[1], info[2], info[3]);
		} else {
			throw new RuntimeException("Info must be {driver, url} or {driver, url, user, password}");
		}
	}
	
	private static final String ORACLE_DEFAULT_DRIVER = "oracle.jdbc.OracleDriver";
	private static final String ORACLE_JDBC_URL_PREFIX = "jdbc:oracle:thin:@";
	private static final int ORACLE_DEFAULT_PORT = 1521;
	/**
	 * <pre> 获得Oracle-Jdbc连接信息.
	 * 
	 * 使用默认驱动程序
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/09/28  huangys  Create
	 * </pre>
	 * 
	 * @param host 主机
	 * @param port 端口
	 * @param dbname 数据库名称
	 * @param user 用户
	 * @param password 密码
	 * @return Jdbc连接信息
	 */
	public static JdbcConnInfo getOracleJdbcConnInfo(@Nonnull final String host, @Nonnull final int port, @Nonnull final String dbname, @Nullable final String user, @Nullable final String password) {
		return getJdbcConnInfo(ORACLE_DEFAULT_DRIVER, ORACLE_JDBC_URL_PREFIX + host + ":" + port + ":" + dbname, user, password);
	}
	
	/**
	 * <pre> 获得Oracle-Jdbc连接信息.
	 * 
	 * 使用默认驱动程序，默认端口
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/09/28  huangys  Create
	 * </pre>
	 * 
	 * @param host 主机
	 * @param dbname 数据库名称
	 * @param user 用户
	 * @param password 密码
	 * @return Jdbc连接信息
	 */
	public static JdbcConnInfo getOracleJdbcConnInfo(@Nonnull final String host, @Nonnull final String dbname, @Nullable final String user, @Nullable final String password) {
		return getOracleJdbcConnInfo(host, ORACLE_DEFAULT_PORT, dbname, user, password);
	}
	
	/**
	 * <pre> 获得Oracle-Jdbc连接信息.
	 * 
	 * 使用默认驱动程序
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/09/28  huangys  Create
	 * </pre>
	 * 
	 * @param host 主机
	 * @param port 端口
	 * @param dbname 数据库名称
	 * @return Jdbc连接信息
	 */
	public static JdbcConnInfo getOracleJdbcConnInfo(@Nonnull final String host, @Nonnull final int port, @Nonnull final String dbname) {
		return getOracleJdbcConnInfo(host, port, dbname, null, null);
	}
	
	/**
	 * <pre> 获得Oracle-Jdbc连接信息.
	 * 
	 * 使用默认驱动程序，默认端口
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/09/28  huangys  Create
	 * </pre>
	 * 
	 * @param host 主机
	 * @param dbname 数据库名称
	 * @return Jdbc连接信息
	 */
	public static JdbcConnInfo getOracleJdbcConnInfo(@Nonnull final String host, @Nonnull final String dbname) {
		return getOracleJdbcConnInfo(host, dbname, null, null);
	}
	
	private static final String DERBY_DEFAULT_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	private static final String DERBY_JDBC_URL_PREFIX = "jdbc:derby:";
	/**
	 * <pre> 获得Derby-Jdbc连接信息.
	 * 
	 * 使用默认驱动程序
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/09/28  huangys  Create
	 * </pre>
	 * 
	 * @param dbname 数据库名称
	 * @return Jdbc连接信息
	 */
	public static JdbcConnInfo getDerbyJdbcConnInfo(@Nonnull final String dbname) {
		return getJdbcConnInfo(DERBY_DEFAULT_DRIVER, DERBY_JDBC_URL_PREFIX + dbname);
	}
	
}
