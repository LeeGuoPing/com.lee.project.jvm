package com.lee.project.jvm.test.ch10;

import java.util.List;

public class Demo2 {
	
	public void method(List<String> list){
		System.out.println("123");
	}
	
	// 因为类型擦除,所以无法编译成功
	/*public void method(List<Integer> list){
		System.out.println("123");
	}*/
}
