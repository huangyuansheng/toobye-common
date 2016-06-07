/*
 * Copyright 2016 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2016/05/12.
 * 
 */
package com.toobye.common.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre> MapList.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2016/05/12  huangys  v1.0      Create
 * </pre>
 * 
 * @param <K> Map的Key类型
 * @param <E> List的元素类型
 */
public class MapList<K, E> extends MapCollection<E, K, List<E>> {

	@Override
	public List<E> newCollection() {
		return new ArrayList<>();
	}
	
}
