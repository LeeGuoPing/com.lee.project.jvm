package com.lee.project.executors.test;

import org.junit.Test;

public class WeiTest {
	
	@Test
	public void test01(){
		int COUNT_BITS = Integer.SIZE-3;
		System.out.println(COUNT_BITS);
		System.out.println(1<<29-1);
		System.out.println(-1<<29);
		int CAPACITY   = (1 << COUNT_BITS) - 1;
		
		System.out.println("===="+Integer.toBinaryString(-536870912& ~CAPACITY));
		
		int RUNNING = -1<< COUNT_BITS;
		int SHUTDOWN = 0<< COUNT_BITS;
		int STOP = 1<< COUNT_BITS;
		int TIDYING = 2<< COUNT_BITS;
		int TERMINATED = 3<< COUNT_BITS;
		System.out.println(RUNNING);
		System.out.println("RUNNING:"+Integer.toBinaryString(RUNNING)+(RUNNING>=SHUTDOWN));
		System.out.println("SHUTDOWN:"+Integer.toBinaryString(SHUTDOWN)+(SHUTDOWN>=SHUTDOWN));
		System.out.println("STOP:"+Integer.toBinaryString(STOP)+(STOP>=SHUTDOWN));
		System.out.println("TIDYING:"+Integer.toBinaryString(TIDYING)+(TIDYING>=SHUTDOWN));
		System.out.println("TERMINATED:"+Integer.toBinaryString(TERMINATED)+(TERMINATED>=SHUTDOWN));
	}
}
