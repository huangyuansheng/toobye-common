/*
 * Copyright 2015 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2015/05/14.
 * 
 */
package com.toobye.common.random;

import java.util.List;
import java.util.Random;

/**
 * <pre> 有序带权随机.
 * 
 * sliceSize越小，则概率两级化越明显。
 * 
 * Slice: 2
 * SliceInner: pow(2, 1.0 / SliceSize)
 * 
 * a1(1-q^n) / (1-q) = X
 * a1(1-q^2n) / (1-q) = 3X
 * => 1+q^n = 3
 * => q = pow(2, 1/n)
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2015/05/14  huangys  v1.0      Create
 * </pre>
 * 
 */
public class RandomWeightOrdered {
	
	private int sliceSize;
	private Random random = new Random();
	/**
	 * <pre> 构造器. </pre>
	 *
	 * @param sliceSize 切片大小
	 */
	public RandomWeightOrdered(final int sliceSize) {
		this.sliceSize = sliceSize;
		calcRandomInnerData();
	}
	
	/**
	 * <pre> 随机获取某个元素.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/02  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 元素类型
	 * @param array 数组
	 * @return 元素
	 */
	public <T> T random(final T[] array) {
		return array[random(array.length)];
	}
	
	/**
	 * <pre> 随机获取某个元素.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/06/02  huangys  Create
	 * </pre>
	 * 
	 * @param <T> 元素类型
	 * @param list 列表
	 * @return 元素
	 */
	public <T> T random(final List<T> list) {
		return list.get(random(list.size()));
	}
	
	/**
	 * <pre> 随机[0, n).
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/18  huangys  Create
	 * </pre>
	 * 
	 * @param n 范围限定
	 * @return [0, n)
	 */
	public int random(final int n) {
		int sliceCount = (int) Math.ceil(1.0 * n / sliceSize);
		byte[] sliceBytes = new byte[(int) Math.ceil(1.0 * sliceCount / 8)];
		while (true) {
			int ret = randomSlice(sliceCount, sliceBytes) * sliceSize + RandomWeight.nextIntNormalized(innerWeights);
			if (ret < n) {
				return ret;
			}
		}
	}
	
	/**
	 * 切片内随机.
	 */
	private int randomSlice(final int sliceCount, final byte[] sliceBytes) {
		while (true) {
			random.nextBytes(sliceBytes);
			int pos = 0;
			int currByte = 0;
			int bitMatch = 0x80;
			for (int i = 0; i < sliceCount; i++) {
				if ((sliceBytes[currByte] & bitMatch) > 0) {
					return pos;
				}
				pos++;
				bitMatch = bitMatch >> 1;
				if (bitMatch == 0) {
					currByte++;
					bitMatch = 0x80;
				}
			}
		}
	}
	
	private double[] innerWeights;
	/**
	 * 计算"切片内随机"所需的准备数据.
	 */
	private void calcRandomInnerData() {
		double power = Math.pow(2, 1.0 / sliceSize);
		innerWeights = new double[sliceSize];
		double total = (1 - Math.pow(power, sliceSize)) / (1 - power);
		innerWeights[sliceSize - 1] = 1;
		double tmp = innerWeights[sliceSize - 1] / total;
		for (int i = sliceSize - 2; i >= 0; i--) {
			innerWeights[i] = innerWeights[i + 1] - tmp;
			tmp *= power;
		}
	}
	
	/**
	 * <pre> 打印理论概率.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2015/05/18  huangys  Create
	 * </pre>
	 * 
	 * @param n 限定范围
	 * @return 概率数组
	 */
	public final double[] printProbability(final int n) {
		double[] ret = new double[n];
		double power = Math.pow(2, 1.0 / sliceSize);
		double weight = Math.pow(power, n - 1);
		double total = 1 * (1 - Math.pow(power, n)) / (1 - power);
		for (int i = 0; i < n; i++) {
			ret[i] = weight / total;
			System.out.println(i + ": " + weight / total);
			weight = weight / power;
		}
		return ret;
	}
	
}
