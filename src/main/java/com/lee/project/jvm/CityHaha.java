package com.lee.project.jvm;

import java.io.IOException;

import org.apache.commons.io.IOUtils;

public class CityHaha {
	public static void main(String[] args) throws IOException {
		// getResourceAsStream会先到缓存中读取文件，若缓存中没有，才会到真正的路径下去读取文件。所以用getResourceAsStream方法获取配置文件时，获取的不是最新配置！！！！
		String cityJSON = IOUtils.toString(CityHaha.class.getResourceAsStream("citylist.json"));
		System.out.println(cityJSON);
	}
}
