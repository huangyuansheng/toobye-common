/*
 * Copyright 2015 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2015/12/15.
 * 
 */
package com.toobye.common.random;

import javax.annotation.Nonnull;

import org.apache.commons.math.stat.StatUtils;

import com.toobye.common.lang.Checks;

/**
 * <pre> 带权随机.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2015/12/15  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class RandomWeight {
	
	private RandomWeight() { }

	/**
	 * <pre> 随机[0, weights.length).
	 * 权重已归一化、叠加化。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/16  huangys  Create
	 * </pre>
	 * 
	 * @param weights 权重
	 * @return [0, weights.length)
	 */
	@Nonnull
	public static int nextIntNormalized(@Nonnull final double[] weights) {
		Checks.emptyThrow(weights);
		double r = RandomBase.nextDouble();
		for (int i = 0; i < weights.length; i++) {
			if (r <= weights[i]) {
				return i;
			}
		}
		return weights.length - 1;
	}
	
	/**
	 * <pre> 随机[0, weights.length).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/16  huangys  Create
	 * </pre>
	 * 
	 * @param weights 权重
	 * @return [0, weights.length)
	 */
	@Nonnull
	public static int nextInt(@Nonnull final double[] weights) {
		return nextIntNormalized(normalize(weights));
	}
	
	/**
	 * <pre> 归一化、线性叠加化权重.
	 * 
	 * 如1, 5, 4
	 * 归一化后：0.1, 0.5, 0.4
	 * 线性叠加化后：0.1, 0.6, 1
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/16  huangys  Create
	 * </pre>
	 * 
	 * @param weights 原始权重
	 * @return 归一化、线性叠加化后的权重
	 */
	@Nonnull
	public static double[] normalize(@Nonnull final double[] weights) {
		Checks.emptyThrow(weights);
		double[] ret = new double[weights.length];
		double total = StatUtils.sum(weights);
		ret[0] = weights[0] / total;
		for (int i = 1; i < ret.length - 1; i++) {
			ret[i] = weights[i] / total + ret[i - 1];
		}
		ret[ret.length - 1] = 1;
		return ret;
	}
	
}
