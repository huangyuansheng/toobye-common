/*
 * Copyright 2016 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2016/05/09.
 * 
 */
package com.toobye.common.collection;

import java.util.HashSet;

/**
 * <pre> 大小写不敏感的Map(Key都以大写保存).
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2016/05/09  huangys  v1.0      Create
 * </pre>
 */
public class SetInsensitive extends HashSet<String> {
	
	private static final long serialVersionUID = 1L;

	@Override
	public boolean contains(final Object o) {
		return super.contains(getKey(o));
	}

	@Override
	public boolean add(final String e) {
		return super.add(getKey(e));
	}

	@Override
	public boolean remove(final Object o) {
		return super.remove(getKey(o));
	}
	
	private static String getKey(final Object key) {
		return key == null ? null : key.toString().toUpperCase();
	}

}
