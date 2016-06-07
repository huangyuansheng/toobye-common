/*
 * Copyright 2010 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2010/02/02.
 * 
 */
package com.toobye.common.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.concurrent.Callable;

import com.toobye.common.concurrent.TimeSlice;
import com.toobye.common.thread.Invoker;

/**
 * <pre> 数据库连接.
 * Support Anonymous.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2013/03/25  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class DBConn {
	
	private DBConn() { }
	
	private static final int DEFAULT_RETRY_TIMES = 3;
	private static final TimeSlice DEFAULT_RETRY_INTERVAL = TimeSlice.seconds(30);
	
	/**
	 * <pre> 获得数据库连接.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/21  huangys  Create
	 * </pre>
	 * 
	 * @param driver 连接驱动
	 * @param url 连接URL
	 * @return 数据库连接
	 */
	public static Connection getConnection(final String driver, final String url) {
		return getConnection(driver, url, null, null);
	}
	
	/**
	 * <pre> 获得数据库连接.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/21  huangys  Create
	 * </pre>
	 * 
	 * @param driver 连接驱动
	 * @param url 连接URL
	 * @param user 用户
	 * @param password 密码
	 * @return 数据库连接
	 */
	public static Connection getConnection(final String driver, final String url, final String user, final String password) {
		return getConnection(driver, url, user, password, DEFAULT_RETRY_TIMES, DEFAULT_RETRY_INTERVAL);
	}
	
	/**
	 * <pre> 获得数据库连接.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/21  huangys  Create
	 * </pre>
	 * 
	 * @param driver 连接驱动
	 * @param url 连接URL
	 * @param user 用户
	 * @param password 密码
	 * @param retryTimes 尝试次数
	 * @param retryInterval 尝试间隔时间
	 * @return 数据库连接
	 */
	public static Connection getConnection(final String driver, final String url, final String user, final String password, final int retryTimes, final TimeSlice retryInterval) {
		return Invoker.retry(new Callable<Connection>() {
			@Override
			public Connection call() throws Exception {
				return getConnectionOnce(driver, url, user, password);
			}
		}, retryTimes, retryInterval);
	}
	
	/**
	 * <pre> 获得数据库连接.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/21  huangys  Create
	 * </pre>
	 * 
	 * @param driver 连接驱动
	 * @param url 连接URL
	 * @return 数据库连接
	 */
	public static Connection getConnectionOnce(final String driver, final String url) {
		return getConnectionOnce(driver, url, null, null);
	}
	
	/**
	 * <pre> 获得数据库连接.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/01/21  huangys  Create
	 * </pre>
	 * 
	 * @param driver 连接驱动
	 * @param url 连接URL
	 * @param user 用户
	 * @param password 密码
	 * @return 数据库连接
	 */
	public static Connection getConnectionOnce(final String driver, final String url, final String user, final String password) {
		try {
			Class.forName(driver);
			if (user != null && password != null) {
				Properties myprop = System.getProperties();
				myprop.setProperty("user", user);
				myprop.setProperty("password", password);
				return DriverManager.getConnection(url, myprop);
			} else {
				return DriverManager.getConnection(url);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 获得数据库连接.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/04/16  huangys  Create
	 * </pre>
	 * 
	 * @param jdbcConnInfo jdbc连接信息
	 * @return 数据库连接
	 */
	public static Connection getConnection(final JdbcConnInfo jdbcConnInfo) {
		return getConnection(jdbcConnInfo.getDriver(), jdbcConnInfo.getUrl(), jdbcConnInfo.getUser(), jdbcConnInfo.getPassword());
	}
	
	/**
	 * <pre> 获得数据库连接.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/04/16  huangys  Create
	 * </pre>
	 * 
	 * @param jdbcConnInfo jdbc连接信息
	 * @param retryTimes 尝试次数
	 * @param retryInterval 尝试间隔时间
	 * @return 数据库连接
	 */
	public static Connection getConnection(final JdbcConnInfo jdbcConnInfo, final int retryTimes, final TimeSlice retryInterval) {
		return getConnection(jdbcConnInfo.getDriver(), jdbcConnInfo.getUrl(), jdbcConnInfo.getUser(), jdbcConnInfo.getPassword(), retryTimes, retryInterval);
	}
	
}