package com.lee.project.hystrix;

import org.junit.Assert;
import org.junit.Test;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class CommandFailure extends HystrixCommand<String>{
	
	private final String name;
	
	public CommandFailure(String name){
		 super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
		 this.name = name;
	}

	@Override
	protected String run() throws Exception {
		throw new RuntimeException("this method is error!");
	}
	
	public String getFallback(){
		return "fallback";
	}
	
	public static class UnitTest{
		
		@Test
		public void sync(){
			CommandFailure commandFailure = new CommandFailure("world");
			String executeResult = commandFailure.execute();
			System.out.println(executeResult);

			Assert.assertEquals("fallback", executeResult);
		}
	}
	
	
}
