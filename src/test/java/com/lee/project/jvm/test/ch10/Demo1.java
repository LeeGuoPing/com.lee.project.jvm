package com.lee.project.jvm.test.ch10;

import java.util.HashMap;
import java.util.Map;

public class Demo1 {
	
	/**
	 * 泛型类型擦除
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String,Object> map = new HashMap<String, Object>();
		Map<Integer,Object> Integermap = new HashMap<Integer, Object>();
		map.put("hello","world");
		map.put("name","pia");
		System.out.println(map.get("hello"));
		System.out.println(map.get("name"));
		System.out.println(map.getClass()==Integermap.getClass()); // 因为类型擦除,所以两者的class相等
	}
}
