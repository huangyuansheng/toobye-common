package com.toobye.common.reflect;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.tools.JavaFileObject.Kind;

import com.toobye.common.io.SmartFile;
import com.toobye.common.lang.Checks;

/**
 * <pre> 智能加载类.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2015/08/31  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class SmartClassLoader extends ClassLoader {
	
	/**
	 * <pre> 动态加载类.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/08/31  huangys  Create
	 * </pre>
	 * 
	 * @param name 类名
	 * @return 类
	 */
	@Nonnull
	public static Class<?> ofClass(@Nonnull final String name) {
		return ofClass(name, null);
	}
	
	/**
	 * <pre> 动态加载类.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/08/31  huangys  Create
	 * </pre>
	 * 
	 * @param sc 类
	 * @return 类
	 */
	@Nonnull
	public static Class<?> ofClass(@Nonnull final SmartClass sc) {
		return ofClass(sc, null);
	}
	
	/**
	 * <pre> 动态加载类.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/08/31  huangys  Create
	 * </pre>
	 * 
	 * @param name 类
	 * @param parent 父类加载器
	 * @return 类
	 */
	@Nonnull
	public static Class<?> ofClass(@Nonnull final String name, @Nullable final ClassLoader parent) {
		return ofClass(SmartClass.of(name), parent);
	}
	
	/**
	 * <pre> 动态加载类.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/08/31  huangys  Create
	 * </pre>
	 * 
	 * @param sc 类
	 * @param parent 父类加载器
	 * @return 类
	 */
	@Nonnull
	public static Class<?> ofClass(@Nonnull final SmartClass sc, @Nullable final ClassLoader parent) {
		Checks.nullThrow(sc);
		return ofClass(sc.getName(), Arrays.asList(sc), parent);
	}
	
	/**
	 * <pre> 动态加载类.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/08/31  huangys  Create
	 * </pre>
	 * 
	 * @param returnClassName 返回类的名称
	 * @param classes 初始化类集合
	 * @param parent 父类加载器
	 * @return 类
	 */
	@Nonnull
	public static Class<?> ofClass(@Nonnull final String returnClassName, @Nonnull final Iterable<SmartClass> classes, @Nullable final ClassLoader parent) {
		Checks.nullThrow(returnClassName);
		Checks.nullThrow(classes);
		try {
			return new SmartClassLoader(classes, parent).loadClass(returnClassName);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 动态加载类.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/08/31  huangys  Create
	 * </pre>
	 * 
	 * @param classes 初始化类集合
	 * @param parent 父类加载器
	 * @return 类
	 */
	@Nonnull
	public static List<Class<?>> ofClass(@Nonnull final List<SmartClass> classes, @Nullable final ClassLoader parent) {
		Checks.nullThrow(classes);
		try {
			SmartClassLoader cl = new SmartClassLoader(classes, parent);
			List<Class<?>> ret = new ArrayList<>();
			for (SmartClass sc : classes) {
				ret.add(cl.loadClass(sc.getName()));
			}
			return ret;
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	private Iterable<SmartClass> classes;
	
	/**
	 * <pre> 智能加载类. </pre>
	 *
	 * @param classes 需加载类
	 * @param parent 父类
	 */
	public SmartClassLoader(@Nonnull final Iterable<SmartClass> classes, @Nullable final ClassLoader parent) {
		super(parent);
		Checks.nullThrow(classes);
		this.classes = classes;
		loadClass();
	}
	
	/**
	 * <pre> 智能加载类. </pre>
	 *
	 * @param classes 需加载类
	 */
	public SmartClassLoader(@Nonnull final Iterable<SmartClass> classes) {
		super();
		Checks.nullThrow(classes);
		this.classes = classes;
		loadClass();
	}
	
	private void loadClass() {
		try {
			for (SmartClass sc : classes) {
				loadClass(sc);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private void loadClass(@Nonnull final SmartClass sc) throws IOException {
		Checks.nullThrow(sc);
		try (InputStream is = sc.getFile() == null ? SmartFile.ofInputStream(sc.getName() + Kind.CLASS.extension) : new FileInputStream(sc.getFile())) {
	        byte[] raw = new byte[is.available()];
	        is.read(raw);
	        is.close();
	        defineClass(sc.getName(), raw, 0, raw.length);
		}
	}
    
}
