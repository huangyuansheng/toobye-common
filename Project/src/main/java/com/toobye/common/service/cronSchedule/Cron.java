/*
 * Copyright 2015 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2015/08/30.
 * 
 */
package com.toobye.common.service.cronSchedule;

import java.util.Map;
import java.util.concurrent.Callable;

import javax.annotation.Nonnull;

import com.toobye.common.io.IOReader;
import com.toobye.common.io.SmartFile;
import com.toobye.common.lang.Function;
import com.toobye.common.lang.Pair;
import com.toobye.common.string.StringSplit;

/**
 * <pre> Cron配置.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2015/08/30  huangys  v1.0      Create
 * </pre>
 * 
 */
@edu.umd.cs.findbugs.annotations.SuppressFBWarnings({"URF_UNREAD_PUBLIC_OR_PROTECTED_FIELD", "UUF_UNUSED_PUBLIC_OR_PROTECTED_FIELD", "UWF_UNWRITTEN_PUBLIC_OR_PROTECTED_FIELD" })
public class Cron extends Thread {

//	create table zoo_cron(
//	cron_name varchar(10) not null,
//	description varchar(200),
//	cron_trigger varchar(4000) not null,
//	primary key(cron_name)
//)
	
	/**
	 * <pre> cron名称. </pre>
	 */
	public String cron_name;
	/**
	 * <pre> 描述. </pre>
	 */
	public String description;
	/**
	 * <pre> cron表达式. </pre>
	 */
	public String cron_trigger;
	
	private static final Object LOCK = new Object();
	/**
	 * <pre> 文件方式获取CronMap.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/02/05  huangys  Create
	 * </pre>
	 * 
	 * @param file 文件
	 * @return CronMap获取器
	 */
	public static Callable<Map<String, Cron>> getCronMap(@Nonnull final SmartFile file) {
		return new Callable<Map<String, Cron>>() {
			private Map<String, Cron> map;
			@Override
			public Map<String, Cron> call() throws Exception {
				synchronized (LOCK) {
					if (file.isChanged()) {
						map = IOReader.STANDARD.readMapWithClose(file.getInputStream(), new Function<String, Pair<String, Cron>>() {
								@Override
								public Pair<String, Cron> apply(final String line) {
									String[] arr = StringSplit.splitChar(line, ',', 3);
									Cron cron = new Cron();
									cron.cron_name = arr[0].trim();
									cron.description = arr[1].trim();
									cron.cron_trigger = arr[2].trim();
									return Pair.of(cron.cron_name, cron);
								}
							}, false);
					}
					return map;
				}
			}
		};
	}
	
}
