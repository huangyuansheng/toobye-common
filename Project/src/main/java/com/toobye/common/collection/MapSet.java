/*
 * Copyright 2016 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2016/05/12.
 * 
 */
package com.toobye.common.collection;

import java.util.HashSet;
import java.util.Set;

/**
 * <pre> MapSet.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2016/05/12  huangys  v1.0      Create
 * </pre>
 * 
 * @param <K> Map的Key类型
 * @param <E> Set的元素类型
 */
public class MapSet<K, E> extends MapCollection<E, K, Set<E>> {

	@Override
	public Set<E> newCollection() {
		return new HashSet<>();
	}
	
}
