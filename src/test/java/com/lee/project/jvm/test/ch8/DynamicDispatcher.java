package com.lee.project.jvm.test.ch8;
/**
 * 方法动态分配演示,方法的重写
 * @author liguoping01
 *
 */
public class DynamicDispatcher {
	static abstract class Human{
		protected abstract void sayHello();
	}
	
	static class Man extends Human{

		@Override
		protected void sayHello() {
			System.out.println("man say Hello!");
			
		}
	
	}
	
	static class Woman extends Human{

		@Override
		protected void sayHello() {
			System.out.println("woman say Hello!");
			
		}
		
	}
	
	public static void main(String[] args) {
		Human man = new Man();
		Human woman = new Woman();
		man.sayHello();
		woman.sayHello();
		man = new Woman();
		man.sayHello();
	}
}
