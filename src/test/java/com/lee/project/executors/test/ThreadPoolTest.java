package com.lee.project.executors.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolTest {
	public static void main(String[] args) {
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(10);
		newFixedThreadPool.execute(new Runnable() {
			
			public void run() {
				System.out.println("123...");
				
			}
		});
		System.out.println("over"+newFixedThreadPool);
	}
}
