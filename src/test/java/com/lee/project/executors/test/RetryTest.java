package com.lee.project.executors.test;

import org.junit.Test;

public class RetryTest {

	@Test
	public void testRequest() {
		retry: // 1
		for (int j = 0; j < 2; j++) {
			for (int i = 0; i < 10; i++) {
				// retry:// 2（行4）
				while (i == 5) {
					continue retry;
				}
				System.out.println(i + ": ");
			}
		}
	}
}
