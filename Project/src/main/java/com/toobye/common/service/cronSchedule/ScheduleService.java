/*
 * Copyright 2015 (C) , All Rights Reserved.
 * Company: China.
 * 
 * Create At 2015/08/30.
 * 
 */
package com.toobye.common.service.cronSchedule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;

import com.toobye.common.concurrent.TimeSlice;

/**
 * <pre> 任务计划执行服务.
 * 
 * Modification History:
 * Date        Author   Version   Action
 * 2015/08/30  huangys  v1.0      Create
 * </pre>
 * 
 */
public final class ScheduleService extends Thread {
	
	private Callable<Map<String, Cron>> cronMapGetter;
	private Callable<List<CronSchedule>> cronSchedulesGetter;
	
	/**
	 * <pre> 构造器. </pre>
	 *
	 * @param cronMapGetter cron表达式定义
	 * @param cronSchedulesGetter cron调度任务列表
	 */
	public ScheduleService(final Callable<Map<String, Cron>> cronMapGetter, final Callable<List<CronSchedule>> cronSchedulesGetter) {
		this.cronMapGetter = cronMapGetter;
		this.cronSchedulesGetter = cronSchedulesGetter;
	}
	
	private final Map<String, CronSchedule> RUNNING = new HashMap<>();
	/**
	 * <pre> 获得运行任务.
	 * 
	 * Modification History:
	 * Date        Author   Action
	 * 2016/02/06  huangys  Create
	 * </pre>
	 * 
	 * @return 运行任务
	 */
	public Map<String, CronSchedule> getRunning() {
		return RUNNING;
	}
	
	@Override
	public void run() {
		try {
			startschedule();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		while (true) {
			TimeSlice.seconds(60).sleep();
			try {
				startschedule();
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}
	}
	
	private void startschedule() throws Exception {
		// 确认
		Map<String, CronSchedule> schedules = new HashMap<>();
		for (CronSchedule schedule : cronSchedulesGetter.call()) {
			schedules.put(schedule.job_name, schedule);
		}
		for (Entry<String, CronSchedule> running : RUNNING.entrySet()) {
			CronSchedule s = running.getValue();
			if (schedules.containsKey(running.getKey())) {
				// 更新计划属性
				CronSchedule newS = schedules.get(running.getKey());
				s.class_name = newS.class_name;
				s.cron_name = newS.cron_name;
				s.description = newS.description;
				s.enable = newS.enable;
			} else {
				// 终止不存在的任务
				RUNNING.remove(running.getKey());
				s.terminate();
			}
		}
		// 新增计划执行
		for (Entry<String, CronSchedule> schedule : schedules.entrySet()) {
			if (!RUNNING.containsKey(schedule.getKey())) {
				RUNNING.put(schedule.getKey(), schedule.getValue());
				schedule.getValue().setCronMapGetter(cronMapGetter);
				schedule.getValue().start();
			}
		}
	}
	
}
