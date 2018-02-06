package com.lee.project.executors.test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 简单的定时任务可以开启线程池实现
 * @author liguoping01
 *
 */
public class ScheduledExecutorServiceTest {
	
	public static void main(String[] args) {
		
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
		
		scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
			
			public void run() {
				System.out.println("run every 1 second!"+System.currentTimeMillis()/1000);
				// System.out.println(System.currentTimeMillis()/1000);
				// System.out.println();
				
			}
		},0,1,TimeUnit.SECONDS);
		
		scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
			
			public void run() {
				System.out.println("run every 5 second!"+System.currentTimeMillis()/1000);
				// System.out.println(Thread.currentThread().getName());
				
			}
		}, 0, 5, TimeUnit.SECONDS);
	}
}
