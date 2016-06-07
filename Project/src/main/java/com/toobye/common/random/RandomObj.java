/*
 * Copyright 2015 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2015/05/18.
 * 
 */
package com.toobye.common.random;

import java.awt.Point;
import java.awt.Rectangle;

import javax.annotation.Nonnull;

import com.toobye.common.lang.Checks;

/**
 * <pre> 随机对象.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2015/05/18  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class RandomObj {
	
	private RandomObj() { }

	/**
	 * <pre> 随机点.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/18  huangys  Create
	 * </pre>
	 * 
	 * @param rect 指定范围
	 * @return 点
	 */
	@Nonnull
	public static Point randomPoint(@Nonnull final Rectangle rect) {
		Checks.nullThrow(rect);
		int h = RandomBase.nextInt(rect.height);
		int w = RandomBase.nextInt(rect.width);
		return new Point(rect.x + w, rect.y + h);
	}
	
	/**
	 * <pre> 随机中心区域的点.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/18  huangys  Create
	 * </pre>
	 * 
	 * @param rect 指定范围
	 * @return 点
	 */
	@Nonnull
	public static Point randomCenterPoint(@Nonnull final Rectangle rect) {
		Checks.nullThrow(rect);
		int h = RandomBase.nextInt(rect.height / 4, rect.height * 3 / 4);
		int w = RandomBase.nextInt(rect.width / 4, rect.width * 3 / 4);
		return new Point(rect.y + w, rect.y + h);
	}
	
}
