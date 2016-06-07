/*
 * Copyright 2015 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2015/07/19.
 * 
 */
package com.toobye.common.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Nonnull;

import com.toobye.common.base.Exceptions;
import com.toobye.common.collection.Maps;
import com.toobye.common.reflect.Properties;
import com.toobye.common.reflect.Reflects;
import com.toobye.common.string.StringArray;

/**
 * <pre> sql执行工具.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2015/07/18  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class Sql {

	private Sql() { }
	
	/**
	 * <pre> 获得默认的数据库连接.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/04/20  huangys  Create
	 * </pre>
	 * 
	 * @return 默认的数据库连接
	 */
	public static Connection getDefaultConnection() {
		Exceptions.throwNoImplementation();
		return null;
	}
	
	/**
	 * <pre> 执行sql.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/07/18  huangys  Create
	 * </pre>
	 * 
	 * @return 更新条数
	 */
	public int execute() {
		try (Connection conn = getDefaultConnection();) {
			return execute(conn);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 执行sql.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/07/18  huangys  Create
	 * </pre>
	 * 
	 * @param conn 数据库连接
	 * @return 更新条数
	 * @throws SQLException 
	 */
	public int execute(final Connection conn) throws SQLException {
		try (PreparedStatement pstmt = prepare(conn);) {
			return pstmt.executeUpdate();
		}
	}
	
	/**
	 * <pre> 返回sql查询结果.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/07/18  huangys  Create
	 * </pre>
	 * 
	 * @return 查询结果
	 */
	public List<Map<String, Object>> query() {
		try (Connection conn = getDefaultConnection();) {
			return query(conn);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 返回sql查询结果.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/07/18  huangys  Create
	 * </pre>
	 * 
	 * @param conn 数据库连接
	 * @return 查询结果
	 * @throws SQLException 
	 */
	public List<Map<String, Object>> query(final Connection conn) throws SQLException {
		try (PreparedStatement pstmt = prepare(conn);) {
			ResultSet rs = pstmt.executeQuery();
			List<Map<String, Object>> list = new ArrayList<>();
			int columns = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				Map<String, Object> one = new HashMap<>();
				for (int i = 0; i < columns; i++) {
					one.put(rs.getMetaData().getColumnName(i + 1), rs.getObject(i + 1));
				}
				list.add(one);
			}
			return list;
		}
	}
	
	/**
	 * <pre> 返回sql查询结果.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/07/18  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 记录类型
	 * @param cls 记录类型
	 * @return 查询结果
	 */
	public <T> List<T> query(final Class<T> cls) {
		try (Connection conn = getDefaultConnection();) {
			return query(conn, cls);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 返回sql查询结果.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/07/18  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 记录类型
	 * @param conn 数据库连接
	 * @param cls 记录类型
	 * @return 查询结果
	 * @throws SQLException 
	 */
	public <T> List<T> query(final Connection conn, final Class<T> cls) throws SQLException {
		List<Map<String, Object>> list = query(conn);
		List<T> ret = new ArrayList<>();
		for (Map<String, Object> map : list) {
			T t = Reflects.newInstance(cls);
			Properties.set(t, map);
			ret.add(t);
		}
		return ret;
	}
	
	private void oneRecordOnlyAllowed(final List<?> list) {
		oneRecordOnlyAllowed(list.size());
	}
	
	private void oneRecordOnlyAllowed(final int records) {
		if (records == 0) {
			throw new RuntimeException("No record.");
		} else if (records > 1) {
			throw new RuntimeException("More than one record.");
		}
	}
	
	/**
	 * <pre> 执行sql，并仅影响单条记录.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/07/18  huangys  Create
	 * </pre>
	 */
	public void executeOne() {
		try (Connection conn = getDefaultConnection();) {
			executeOne(conn);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 执行sql，并仅影响单条记录.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/07/18  huangys  Create
	 * </pre>
	 * 
	 * @param conn 数据库连接
	 * @throws SQLException 
	 */
	public void executeOne(final Connection conn) throws SQLException {
		try (PreparedStatement pstmt = prepare(conn);) {
			int records = pstmt.executeUpdate();
			oneRecordOnlyAllowed(records);
		}
	}
	
	/**
	 * <pre> 返回sql查询结果，并仅允许单条记录.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/07/18  huangys  Create
	 * </pre>
	 * 
	 * @return 查询结果
	 */
	public Map<String, Object> queryOne() {
		try (Connection conn = getDefaultConnection();) {
			return queryOne(conn);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 返回sql查询结果，并仅允许单条记录.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/07/18  huangys  Create
	 * </pre>
	 * 
	 * @param conn 数据库连接
	 * @return 查询结果
	 * @throws SQLException 
	 */
	public Map<String, Object> queryOne(final Connection conn) throws SQLException {
		List<Map<String, Object>> list = query(conn);
		oneRecordOnlyAllowed(list);
		return list.get(0);
	}
	
	/**
	 * <pre> 返回sql查询结果，并仅允许单条记录.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/07/18  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 记录类型
	 * @param cls 记录类型
	 * @return 查询结果
	 */
	public <T> T queryOne(final Class<T> cls) {
		try (Connection conn = getDefaultConnection();) {
			return queryOne(conn, cls);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 返回sql查询结果，并仅允许单条记录.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/07/18  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 记录类型
	 * @param conn 数据库连接
	 * @param cls 记录类型
	 * @return 查询结果
	 * @throws SQLException 
	 */
	public <T> T queryOne(final Connection conn, final Class<T> cls) throws SQLException {
		List<T> list = query(conn, cls);
		oneRecordOnlyAllowed(list);
		return list.get(0);
	}
	
	private String sql;
	private Map<String, Object> params;
	
	/**
	 * <pre> 构造sql语句.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/07/22  huangys  Create
	 * </pre>
	 * 
	 * @param sql sql语句
	 * @return sql语句
	 */
	public static Sql of(final String sql) {
		return of(sql, null);
	}
	
	/**
	 * <pre> 带参构造sql语句.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/07/22  huangys  Create
	 * </pre>
	 * 
	 * @param sql sql语句
	 * @param obj 使用对象中Public非null属性作为参数
	 * @return sql语句
	 */
	public static Sql of(final String sql, final Object obj) {
		return of(sql, Properties.describePublicNotNull(obj));
	}
	
	/**
	 * <pre> 带参构造sql语句.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/07/22  huangys  Create
	 * </pre>
	 * 
	 * @param sql sql语句
	 * @param params 参数
	 * @return sql语句
	 */
	public static Sql of(final String sql, final Map<String, Object> params) {
		Sql instance = new Sql();
		instance.sql = sql;
		instance.params = params;
		return instance;
	}
	
	/**
	 * <pre> 构造select-sql语句.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/07/22  huangys  Create
	 * </pre>
	 * 
	 * @param cls public属性作为选择字段，类名作为表名
	 * @return sql语句
	 */
	public static Sql ofSelect(final Class<?> cls) {
		return ofSelect(cls, cls.getSimpleName());
	}
	
	/**
	 * <pre> 构造select-sql语句.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/07/22  huangys  Create
	 * </pre>
	 * 
	 * @param cls public属性作为选择字段
	 * @param tableAndWhere 表名和条件语句
	 * @return sql语句
	 */
	public static Sql ofSelect(final Class<?> cls, final String tableAndWhere) {
		return of("select " + StringArray.join(Properties.getPublicNames(cls), ", ") + " from " + tableAndWhere);
	}
	
	/**
	 * <pre> 带参构造select-sql语句.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/07/22  huangys  Create
	 * </pre>
	 * 
	 * @param obj public属性作为选择字段和条件参数
	 * @param tableAndWhere 表名和条件语句
	 * @return sql语句
	 */
	public static Sql ofSelect(final Object obj, final String tableAndWhere) {
		return of("select " + StringArray.join(Properties.getPublicNames(obj.getClass()), ", ") + " from " + tableAndWhere,
				Properties.describePublicNotNull(obj));
	}
	
	/**
	 * <pre> 带参构造Insert-sql语句.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/07/22  huangys  Create
	 * </pre>
	 * 
	 * @param obj public属性作为选择字段和条件参数
	 * @return sql语句
	 */
	public static Sql ofInsert(final Object obj) {
		return ofInsert(obj, obj.getClass().getSimpleName());
	}
	
	/**
	 * <pre> 带参构造Insert-sql语句.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/07/22  huangys  Create
	 * </pre>
	 * 
	 * @param obj public属性作为选择字段和条件参数
	 * @param table 表名
	 * @return sql语句
	 */
	public static Sql ofInsert(final Object obj, final String table) {
		return ofInsert(Properties.describePublicNotNull(obj), table);
	}
	
	/**
	 * <pre> 带参构造Insert-sql语句.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/08/10  huangys  Create
	 * </pre>
	 * 
	 * @param params 参数
	 * @param table 表名
	 * @return sql语句
	 */
	public static Sql ofInsert(@Nonnull final Map<String, Object> params, @Nonnull final String table) {
		return of("insert into " + table + "(" + StringArray.join(params.keySet(), ",") + ")values(#{" + StringArray.join(params.keySet(), "},#{") + "})", params);
	}

	/**
	 * <pre> 带参构造Insert语句.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/19  huangys  Create
	 * </pre>
	 * 
	 * @param cls 对象类型
	 * @param table 表名
	 * @return sql语句
	 */
	public static Sql ofInsert(@Nonnull final Class<?> cls, @Nonnull final String table) {
		return ofInsert(Maps.newHashMapNullValue(Properties.getPublicNames(cls)), table);
	}
	
	/**
	 * <pre> 带参构造Insert-sql语句.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/19  huangys  Create
	 * </pre>
	 * 
	 * @param obj 以对象属性为参数
	 * @param table 表名
	 * @param keys 条件键
	 * @return sql语句
	 */
	public static Sql ofInsertNotExists(@Nonnull final Object obj, @Nonnull final String table, @Nonnull final Set<String> keys) {
		return ofInsertNotExists(Properties.describePublicNotNull(obj), table, keys);
	}
	
	/**
	 * <pre> 带参构造Insert-sql语句.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/19  huangys  Create
	 * </pre>
	 * 
	 * @param params 参数
	 * @param table 表名
	 * @param keys 条件键
	 * @return sql语句
	 */
	public static Sql ofInsertNotExists(@Nonnull final Map<String, Object> params, @Nonnull final String table, @Nonnull final Set<String> keys) {
		StringBuffer where = new StringBuffer();
		for (String key : keys) {
			where.append(key + "=#{" + key + "} and ");
		}
		where.setLength(where.length() - 5);
		String insertSql =  "insert into " + table + "(" + StringArray.join(params.keySet(), ",") + ")"
				+ "select #{" + StringArray.join(params.keySet(), "},#{") + "} from dual_one where not exists(select 1 from " + table + " where " + where.toString() + ")";
		return of(insertSql, params);
	}
	
	/**
	 * <pre> 带参构造Insert语句.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/19  huangys  Create
	 * </pre>
	 * 
	 * @param cls 对象类型
	 * @param table 表名
	 * @param keys 条件键
	 * @return sql语句
	 */
	public static Sql ofInsertNotExists(@Nonnull final Class<?> cls, @Nonnull final String table, @Nonnull final Set<String> keys) {
		return ofInsertNotExists(Maps.newHashMapNullValue(Properties.getPublicNames(cls)), table, keys);
	}
	
	/**
	 * <pre> 带参构造Update语句.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/19  huangys  Create
	 * </pre>
	 * 
	 * @param obj 以对象属性为参数
	 * @param keys 条件键
	 * @return sql语句
	 */
	public static Sql ofUpdate(@Nonnull final Object obj, @Nonnull final Set<String> keys) {
		return ofUpdate(obj, obj.getClass().getSimpleName(), keys);
	}
	
	/**
	 * <pre> 带参构造Update语句.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/19  huangys  Create
	 * </pre>
	 * 
	 * @param obj 以对象属性为参数
	 * @param table 表名
	 * @param keys 条件键
	 * @return sql语句
	 */
	public static Sql ofUpdate(@Nonnull final Object obj, @Nonnull final String table, @Nonnull final Set<String> keys) {
		return ofUpdate(Properties.describePublicNotNull(obj), table, keys);
	}
	
	/**
	 * <pre> 带参构造Update语句.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/19  huangys  Create
	 * </pre>
	 * 
	 * @param params 参数
	 * @param table 表名
	 * @param keys 条件键
	 * @return sql语句
	 */
	public static Sql ofUpdate(@Nonnull final Map<String, Object> params, @Nonnull final String table, @Nonnull final Set<String> keys) {
		StringBuffer updateColums = new StringBuffer();
		for (String key : params.keySet()) {
			updateColums.append(key + "=#{" + key + "},");
		}
		updateColums.setLength(updateColums.length() - 1);
		
		StringBuffer where = new StringBuffer();
		for (String key : keys) {
			where.append(key + "=#{" + key + "} and ");
		}
		where.setLength(where.length() - 5);
		String updateSql =  "update " + table + " set " + updateColums.toString() + " where " + where.toString();
		return of(updateSql, params);
	}
	
	/**
	 * <pre> 带参构造Update语句.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/19  huangys  Create
	 * </pre>
	 * 
	 * @param cls 对象类型
	 * @param table 表名
	 * @param keys 条件键
	 * @return sql语句
	 */
	public static Sql ofUpdate(@Nonnull final Class<?> cls, @Nonnull final String table, @Nonnull final Set<String> keys) {
		return ofUpdate(Maps.newHashMapNullValue(Properties.getPublicNames(cls)), table, keys);
	}
	
	/**
	 * <pre> 带参构造Delete语句.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/19  huangys  Create
	 * </pre>
	 * 
	 * @param obj 以对象属性为参数
	 * @param keys 条件键
	 * @return sql语句
	 */
	public static Sql ofDelete(@Nonnull final Object obj, @Nonnull final Set<String> keys) {
		return ofDelete(obj, obj.getClass().getSimpleName(), keys);
	}
	
	/**
	 * <pre> 带参构造Delete语句.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/19  huangys  Create
	 * </pre>
	 * 
	 * @param obj 以对象属性为参数
	 * @param table 表名
	 * @param keys 条件键
	 * @return sql语句
	 */
	public static Sql ofDelete(@Nonnull final Object obj, @Nonnull final String table, @Nonnull final Set<String> keys) {
		return ofDelete(Properties.describePublicNotNull(obj), table, keys);
	}
	
	/**
	 * <pre> 带参构造Delete语句.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/19  huangys  Create
	 * </pre>
	 * 
	 * @param params 参数
	 * @param table 表名
	 * @param keys 条件键
	 * @return sql语句
	 */
	public static Sql ofDelete(@Nonnull final Map<String, Object> params, @Nonnull final String table, @Nonnull final Set<String> keys) {
		StringBuffer where = new StringBuffer();
		for (String key : keys) {
			where.append(key + "=#{" + key + "} and ");
		}
		where.setLength(where.length() - 5);
		String deleteSql =  "delete from " + table + " where " + where.toString();
		return of(deleteSql, params);
	}
	
	/**
	 * <pre> 带参构造Delete语句.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/19  huangys  Create
	 * </pre>
	 * 
	 * @param cls 对象类型
	 * @param table 表名
	 * @param keys 条件键
	 * @return sql语句
	 */
	public static Sql ofDelete(@Nonnull final Class<?> cls, @Nonnull final String table, @Nonnull final Set<String> keys) {
		return ofDelete(Maps.newHashMapNullValue(Properties.getPublicNames(cls)), table, keys);
	}
	
	/**
	 * <pre> 带参构造Merge语句.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/19  huangys  Create
	 * </pre>
	 * 
	 * @param obj 以对象属性为参数
	 * @return sql语句
	 */
	public static Sql ofMergeMysql(@Nonnull final Object obj) {
		return ofMergeMysql(obj, obj.getClass().getSimpleName());
	}
	
	/**
	 * <pre> 带参构造Merge语句.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/19  huangys  Create
	 * </pre>
	 * 
	 * @param obj 以对象属性为参数
	 * @param table 表名
	 * @return sql语句
	 */
	public static Sql ofMergeMysql(@Nonnull final Object obj, @Nonnull final String table) {
		return ofMergeMysql(Properties.describePublicNotNull(obj), table);
	}
	
	/**
	 * <pre> 带参构造Merge语句.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/19  huangys  Create
	 * </pre>
	 * 
	 * @param params 参数
	 * @param table 表名
	 * @return sql语句
	 */
	public static Sql ofMergeMysql(@Nonnull final Map<String, Object> params, @Nonnull final String table) {
		StringBuffer updateColums = new StringBuffer();
		for (String key : params.keySet()) {
			updateColums.append(key + "=#{" + key + "},");
		}
		updateColums.setLength(updateColums.length() - 1);
		
		String mergeSql = "insert into " + table + "(" + StringArray.join(params.keySet(), ",") + ")values(#{" + StringArray.join(params.keySet(), "},#{") + "})"
							+ " on duplicate key update " + updateColums.toString();
		return of(mergeSql, params);
	}
	
	/**
	 * <pre> 带参构造Merge语句.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/19  huangys  Create
	 * </pre>
	 * 
	 * @param cls 对象类型
	 * @param table 表名
	 * @return sql语句
	 */
	public static Sql ofMergeMysql(@Nonnull final Class<?> cls, @Nonnull final String table) {
		return ofMergeMysql(Maps.newHashMapNullValue(Properties.getPublicNames(cls)), table);
	}
	
	/**
	 * <pre> 批处理.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/08/10  huangys  Create
	 * </pre>
	 * 
	 * @param sql sql语句
	 * @param objs 参数对象列表
	 * @return 执行结果
	 */
	public static int[] executeBatch(final Sql sql, final List<?> objs) {
		try (Connection conn = getDefaultConnection();) {
			return executeBatch(getDefaultConnection(), sql, objs);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 批处理.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/08/10  huangys  Create
	 * </pre>
	 * 
	 * @param conn 数据库连接
	 * @param sql sql语句
	 * @param objs 参数对象列表
	 * @return 执行结果
	 */
	public static int[] executeBatch(final Connection conn, final Sql sql, final List<?> objs) {
		// 获取全部变量
		List<String> variables = sql.getVariables();
		// 替换变量为问号
		String theSql = sql.sql.replaceAll(REGX_VARIABLE, "?");
//		System.out.println(theSql);
//		System.out.println(objs.get(0));
		// 替换变量
		try (PreparedStatement pstmt = conn.prepareStatement(theSql);) {
			for (Object obj : objs) {
				if (obj == null) {
					pstmt.addBatch();
					continue;
				}
				// 获得对象公共属性
				Map<String, Object> params = Properties.describePublicNotNull(obj);
				if (params.isEmpty()) {
					pstmt.addBatch();
					continue;
				}
				// 参数名称全部转为大写
				Map<String, Object> map = new HashMap<>();
				for (Entry<String, Object> entry : params.entrySet()) {
					map.put(entry.getKey().toUpperCase(), entry.getValue());
				}
				// set-sql参数
				for (int i = 0; i < variables.size(); i++) {
					String variable = variables.get(i).toUpperCase();
					pstmt.setObject(i + 1, map.get(variable));
				}
				pstmt.addBatch();
			}
			return pstmt.executeBatch();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 批处理，每条语句限制影响单条记录.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/08/10  huangys  Create
	 * </pre>
	 * 
	 * @param sql sql语句
	 * @param objs 参数对象列表
	 */
	public static void executeBatchEachOne(final Sql sql, final List<?> objs) {
		try (Connection conn = getDefaultConnection();) {
			executeBatchEachOne(getDefaultConnection(), sql, objs);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 批处理，每条语句限制影响单条记录.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/08/10  huangys  Create
	 * </pre>
	 * 
	 * @param conn 数据库连接
	 * @param sql sql语句
	 * @param objs 参数对象列表
	 */
	public static void executeBatchEachOne(final Connection conn, final Sql sql, final List<?> objs) {
		int[] ret = executeBatch(conn, sql, objs);
		for (int i : ret) {
			sql.oneRecordOnlyAllowed(i);
		}
	}
	
	
	private static final String REGX_VARIABLE = "#\\{([a-zA-Z0-9_]+)\\}";
	
	/**
	 * <pre> 获得全部变量.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/07/19  huangys  Create
	 * </pre>
	 * 
	 * @return 全部变量
	 */
	private List<String> getVariables() {
		Matcher m = Pattern.compile(REGX_VARIABLE).matcher(sql);
		List<String> variables = new ArrayList<>();
		while (m.find()) {
			variables.add(m.group(1));
		}
		return variables;
	}
	
	/**
	 * <pre> 解析sql语句.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/07/19  huangys  Create
	 * </pre>
	 * 
	 * @param conn 连接
	 * @return 已解析语句
	 * @throws SQLException
	 */
	private PreparedStatement prepare(final Connection conn) throws SQLException {
		if (params == null || params.isEmpty()) {
			return conn.prepareStatement(sql);
		} else {
			// 获取全部变量
			List<String> variables = getVariables();
			// 替换变量为问号
			String theSql = sql.replaceAll(REGX_VARIABLE, "?");
			// 参数名称全部转为大写
			Map<String, Object> map = new HashMap<>();
			for (Entry<String, Object> entry : params.entrySet()) {
				map.put(entry.getKey().toUpperCase(), entry.getValue());
			}
			// 替换变量
			PreparedStatement pstmt = conn.prepareStatement(theSql);
			for (int i = 0; i < variables.size(); i++) {
				String variable = variables.get(i).toUpperCase();
				pstmt.setObject(i + 1, map.get(variable));
			}
			return pstmt;
		}
	}
	
	/**
	 * <pre> 测试.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/08/14  huangys  Create
	 * </pre>
	 * 
	 * @param args 空
	 */
	public static void main(final String[] args) {
		// 样例1
		System.out.println(Sql.of("select * from dual").query().get(0));
		// 样例2
		Map<String, Object> map = new HashMap<>();
		map.put("nAme", "baby");
		map.put("hEight", 180);
		System.out.println(Sql.of("select #{naMe}, #{heigHt} height, #{heIght} height2 from dual", map).query().get(0));
	}
	
}
