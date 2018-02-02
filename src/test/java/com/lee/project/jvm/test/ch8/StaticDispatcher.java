package com.lee.project.jvm.test.ch8;
/**
 * 方法静态分派演示,典型应用方法重载
 * @author liguoping01
 *
 */
public class StaticDispatcher {
	
	static abstract class Human{
		
	}
	static class man extends Human{
		
	}
	static class woman extends Human{
		
	}
	
	public void sayHello(Human guy){
		System.out.println("hello guy!");
	}
	
	public void sayHello(man guy){
		System.out.println("hello gentleman!");
	}
	
	public void sayHello(woman guy){
		System.out.println("hello lady!");
	}
	
	public static void main(String[] args) {
		Human man = new man();
		Human woman = new woman();
		StaticDispatcher sr = new StaticDispatcher();
		sr.sayHello(man);  // hello guy!
		sr.sayHello(woman);  // hello guy!
	}
}
