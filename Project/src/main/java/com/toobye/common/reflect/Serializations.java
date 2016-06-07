/*
 * Copyright 2014 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2014/07/30.
 * 
 */
package com.toobye.common.reflect;

import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.toobye.common.codec.HexCodec;
import com.toobye.common.string.StringArray;
import com.toobye.common.string.StringSplit;

/**
 * <pre> 序列化工具.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2014/07/30  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class Serializations {

	private Serializations() { }
	
	/**
	 * <pre> 其他函数.
	 * 序列化/反序列化，输入输出为字节数组/IOStream。
	 * 
	 * Modification History:
	 * Date        Author   Version   Action
	 * 2014/08/06  huangys  v1.0      Create
	 * </pre>
	 * 
	 */
	public static class INSTANCE extends org.apache.commons.lang3.SerializationUtils { };
	
	/**
	 * <pre> 克隆.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/08/06  huangys  Create
	 * </pre>
	 * 
	 * @param obj 对象
	 * @param <T> 实现序列化类
	 * @return 克隆的对象
	 */
	@Nullable
	public static <T extends Serializable> T clone(@Nullable final T obj) {
		return INSTANCE.clone(obj);
	}
	
	/**
	 * <pre> 序列化.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/02/05  huangys  Create
	 * </pre>
	 * 
	 * @param obj 对象
	 * @param filename 文件名称
	 */
	public static void serializeToFile(@Nullable final Serializable obj, @Nonnull final String filename) {
		serializeToFile(obj, new File(filename));
	}
	
	/**
	 * <pre> 序列化.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/02/05  huangys  Create
	 * </pre>
	 * 
	 * @param obj 对象
	 * @param file 文件
	 */
	public static void serializeToFile(@Nullable final Serializable obj, @Nonnull final File file) {
		try (OutputStream outputStream = new FileOutputStream(file);) {
			INSTANCE.serialize(obj, outputStream);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 反序列化.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/02/05  huangys  Create
	 * </pre>
	 * 
	 * @param filename 文件名称
	 * @param <T> 实现序列化类
	 * @return 反序列化的对象
	 */
	@Nullable
	public static <T extends Serializable> T deserializeFromFile(@Nonnull final String filename) {
		return deserializeFromFile(new File(filename));
	}
	
	/**
	 * <pre> 反序列化.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/02/05  huangys  Create
	 * </pre>
	 * 
	 * @param file 文件
	 * @param <T> 实现序列化类
	 * @return 反序列化的对象
	 */
	@SuppressWarnings("unchecked")
	@Nullable
	public static <T extends Serializable> T deserializeFromFile(@Nonnull final File file) {
		try (InputStream inputStream = new FileInputStream(file);) {
			return (T) INSTANCE.deserialize(inputStream);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre> 序列化为字符串.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/07/30  huangys  Create
	 * </pre>
	 * 
	 * @param obj 对象
	 * @param <T> 实现序列化类
	 * @return 序列化生成的字符串
	 */
	@Nonnull
	public static <T extends Serializable> String serialize(@Nullable final T obj) {
		try (ByteArrayOutputStream bOut = new ByteArrayOutputStream();
			  ObjectOutputStream oOut = new ObjectOutputStream(bOut);) {
			oOut.writeObject(obj);
			return HexCodec.encodeHexString(bOut.toByteArray());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * <pre> 反序列化.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/07/30  huangys  Create
	 * </pre>
	 * 
	 * @param objBody 对象内容
	 * @param <T> 实现序列化类
	 * @return 对象
	 */
	@SuppressWarnings("unchecked")
	@Nullable
	public static <T extends Serializable> T deserialize(@Nonnull final String objBody) {
		byte[] bytes = HexCodec.decodeHex(objBody);
		try (ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bytes));) {
			return (T) in.readObject();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private static final char CLASS_DELIMITER = '$';
	private static final char PROPERTY_DELIMITER = ';';
	private static final char FIELD_DELIMITER = ':';
	
	/**
	 * <pre> 序列化对象所有属性.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/07/31  huangys  Create
	 * </pre>
	 * 
	 * @param obj 对象
	 * @return 序列化生成的字符串
	 */
	@Nonnull
	public static String serializeProperties(@Nullable final Object obj) {
		if (obj == null) {
			return serialize(null);
		} else if (obj.getClass().getName().startsWith("java.lang.") && obj instanceof Serializable) {
			return serialize((Serializable) obj);
		} else {
			// 获得对象所有属性信息
			Map<String, PropertyDescriptor> pMap = ClassProperties.parse(obj.getClass()).get();
			Map<String, Object> values = Properties.describe(obj);
			// 属性字符串格式：属性名称:属性值
			List<String> properties = new ArrayList<String>();
			// 遍历所有属性
			for (Entry<String, PropertyDescriptor> entry : pMap.entrySet()) {
				String name = entry.getKey();
				PropertyDescriptor pd = entry.getValue();
				if (!name.equals("class")) {
					Class<?> type = pd.getPropertyType();
					if (type.isPrimitive() || Serializable.class.isAssignableFrom(type)) {
						properties.add(name + FIELD_DELIMITER + serialize((Serializable) values.get(name)));
					} else {
						throw new RuntimeException("Property must implements java.io.Serializable.");
					}
				} 
			}
			return obj.getClass().getName() + CLASS_DELIMITER + StringArray.join(properties, PROPERTY_DELIMITER);
		}
	}
	
	/**
	 * <pre> 反序列化.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/07/31  huangys  Create
	 * </pre>
	 * 
	 * @param objBody 对象内容
	 * @param <T> 实现序列化类
	 * @return 反序列化的对象
	 */
	@SuppressWarnings("unchecked")
	@Nullable
	public static <T> T deserializeProperties(@Nonnull final String objBody) {
		// 判断是否为直接序列化内容（基础对象/实现序列化接口的对象）
		if (objBody.indexOf(CLASS_DELIMITER) == -1) {
			return (T) deserialize(objBody);
		} else {
			String[] array = StringSplit.splitChar(objBody, CLASS_DELIMITER);
			T obj = null;
			try {
				// 实例化对象
				obj = (T) Class.forName(array[0]).newInstance();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			// 依次填充每个属性
			if (array.length == 2) {
				String[] properties = StringSplit.splitChar(array[1], PROPERTY_DELIMITER);
				for (String property : properties) {
					String[] fields = StringSplit.splitChar(property, FIELD_DELIMITER);
					Properties.set(obj, fields[0], deserialize(fields[1]));
				}
			}
			return obj;
		}
	}
	
}
