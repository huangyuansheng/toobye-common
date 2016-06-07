/*
 * Copyright 2015 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2015/08/30.
 * 
 */
package com.toobye.common.service.cronSchedule;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.annotation.Nonnull;

import org.apache.commons.logging.Log;

import com.toobye.common.collection.Collections;
import com.toobye.common.concurrent.TimeSlice;
import com.toobye.common.framework.Logs;
import com.toobye.common.io.IOReader;
import com.toobye.common.io.SmartFile;
import com.toobye.common.lang.Function;
import com.toobye.common.string.StringSplit;
import com.toobye.common.time.DateComparator;
import com.toobye.common.time.DateCronParser;

/**
 * <pre> 任务计划.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2015/08/30  huangys  v1.0      Create
 * </pre>
 * 
 */
@edu.umd.cs.findbugs.annotations.SuppressFBWarnings({"URF_UNREAD_PUBLIC_OR_PROTECTED_FIELD", "UUF_UNUSED_PUBLIC_OR_PROTECTED_FIELD", "UWF_UNWRITTEN_PUBLIC_OR_PROTECTED_FIELD" })
public class CronSchedule extends Thread {

//	create table zoo_schedule (
//	 job_name varchar(30) not null,
//	 class_name varchar(200) not null,
//	 cron_name varchar(10) not null,
//	 description varchar(200),
//	 enable varchar(1) not null,
//	 primary key(job_name)
//	)
	
	private Callable<Map<String, Cron>> cronMapGetter;
	/**
	 * <pre> 设置cronMap获取器.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/02/05  huangys  Create
	 * </pre>
	 * 
	 * @param cronMapGetter cronMap获取器
	 */
	public void setCronMapGetter(final Callable<Map<String, Cron>> cronMapGetter) {
		this.cronMapGetter = cronMapGetter;
	}
	
	/**
	 * <pre> 任务名称. </pre>
	 */
	public String job_name;
	/**
	 * <pre> 类名. </pre>
	 */
	public String class_name;
	/**
	 * <pre> cron名称. </pre>
	 */
	public String cron_name;
	/**
	 * <pre> 上次成功运行的时间. </pre>
	 */
	public Date lastRunSuccTime;
	/**
	 * <pre> 描述. </pre>
	 */
	public String description;
	/**
	 * <pre> 是否有效Y/N. </pre>
	 */
	public String enable;
	
	private volatile boolean terminated = false;
	/**
	 * <pre> 终止线程.
	 * 等待当次运行结束后结束线程。
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2014/10/20  huangys  Create
	 * </pre>
	 * 
	 */
	public void terminate() {
		terminated = true;
	}
	
	private Date nextMatchTime;
	private void calcNextMatchTime() throws Exception {
		// 计算从上次运行时间起的下次触发时间
		String[] crons = cronMapGetter.call().get(cron_name).cron_trigger.split("\\|");
		nextMatchTime = null;
		for (String cron : crons) {
			if (nextMatchTime == null) {
				nextMatchTime = DateCronParser.getNext(lastRunTime, cron.trim());
			} else {
				Date tmp = DateCronParser.getNext(lastRunTime, cron.trim());
				if (tmp.before(nextMatchTime)) {
					nextMatchTime = tmp;
				}
			}
		}
	}
	
	private Date lastRunTime;
	private Log log = Logs.get(this);
	@Override
	public void run() {
		log.info(class_name + ": Running.");
		Runnable runnable = null;
		try {
			runnable = (Runnable) Class.forName(class_name).newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		while (!terminated) {
			try {
				Date now = new Date();
				// 判断当前时间是否满足触发时间要求
				if (lastRunTime == null || !now.before(nextMatchTime)) {
					runnable.run();
					lastRunTime = now;
					calcNextMatchTime();
					lastRunSuccTime = now;
					DateComparator.dateMinus(nextMatchTime, now).sleep();
				}
			} catch (Throwable t) {
				try {
					log.error("Failed!", t);
				} catch (Throwable t2) {
				}
				try {
					TimeSlice.seconds(1).sleep();
				} catch (Throwable t2) {
				}
			}
		}
	}
	
	/**
	 * <pre> 文件方式获取CronScheduleList.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/02/05  huangys  Create
	 * </pre>
	 * 
	 * @param file 文件
	 * @return CronScheduleList获取器
	 */
	public static Callable<List<CronSchedule>> getCronScheduleList(@Nonnull final SmartFile file) {
		return getCronScheduleList(file, "");
	}
	
	private static final Object LOCK = new Object();
	/**
	 * <pre> 文件方式获取CronScheduleList.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/02/05  huangys  Create
	 * </pre>
	 * 
	 * @param file 文件
	 * @param classPrefix 类名前缀
	 * @return CronScheduleList获取器
	 */
	public static Callable<List<CronSchedule>> getCronScheduleList(@Nonnull final SmartFile file, @Nonnull final String classPrefix) {
		return new Callable<List<CronSchedule>>() {
			private List<CronSchedule> list;
			@Override
			public List<CronSchedule> call() throws Exception {
				synchronized (LOCK) {
					if (file.isChanged()) {
						list = IOReader.STANDARD.readListWithClose(file.getInputStream(), new Function<String, CronSchedule>() {
								@Override
								public CronSchedule apply(final String line) {
									String[] arr = StringSplit.splitChar(line, ',', 5);
									CronSchedule schedule = new CronSchedule();
									schedule.job_name = arr[0].trim();
									schedule.class_name = classPrefix + arr[1].trim();
									schedule.cron_name = arr[2].trim();
									schedule.description = arr[3].trim();
									schedule.enable = arr[4].trim();
									return schedule.enable.equalsIgnoreCase("Y") ? schedule : null;
								}
							});
						Collections.removeNull(list);
						// Checks.duplicateThrow(list);
					}
					return list;
				}
			}
		};
	}
	
}
