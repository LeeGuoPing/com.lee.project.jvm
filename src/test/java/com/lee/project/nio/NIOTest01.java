package com.lee.project.nio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

import org.junit.Test;

public class NIOTest01 {

	@Test
	public void test01() {
		IntBuffer buffer = IntBuffer.allocate(10); // 初始化容量为10
		System.out.println(
				"position: " + buffer.position() + ",limit " + buffer.limit() + ",capacity " + buffer.capacity());
		buffer.put(1);
		buffer.put(16);
		System.out.println(
				"position: " + buffer.position() + ",limit " + buffer.limit() + ",capacity " + buffer.capacity());
		buffer.flip(); // 由写模式切换到读模式
		// System.out.println(buffer.get(1));
		System.out.println(
				"position: " + buffer.position() + ",limit " + buffer.limit() + ",capacity " + buffer.capacity());
	}

	@Test
	public void test02() {
		FileOutputStream outputStream = null;
		FileChannel fileChannel = null;
		try {
			String[] info = { "today ", " is", " 2018", "-02", "-28" };
			File file = new File("nio.txt");
			outputStream = new FileOutputStream(file);
			fileChannel = outputStream.getChannel();
			ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
			for (int i = 0; i < info.length; i++) {
				byteBuffer.put(info[i].getBytes());
			}
			byteBuffer.flip(); // 此行代码是必须存在的
			fileChannel.write(byteBuffer);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fileChannel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	
	@Test
	public void similarCarTest(){
		System.out.println(vinSimilar("KDJSIZJ3827492832", "KFJSIZJ3838492832"));
	}
	
	public boolean vinSimilar(String carVin,String compareVin){
		if(carVin==null || carVin.isEmpty() || carVin.length()<17){
			return false;
		}
		
		if(compareVin==null || compareVin.isEmpty()|| compareVin.length()<17){
			return false;
		}
		
		int sameCount = 0;
		for(int i = 0;i<carVin.length();i++){
			if(carVin.charAt(i)==compareVin.charAt(i)){
				sameCount++;
			}else{
				continue;
			}
			
		}
		System.out.println(sameCount);
		return sameCount>=15;
		
	}
}
