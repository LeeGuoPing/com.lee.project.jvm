package com.lee.project.hystrix;

import java.io.IOException;

import org.junit.Test;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;
/**
 * 演示错误率大于80%引发熔断DEMO
 * @author liguoping01
 *
 */
public class HystrixCommand4CircuitBreakerTest extends HystrixCommand<String>{
	
	private String name;
	
	public HystrixCommand4CircuitBreakerTest(String name){
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("CircuitBreakerTestGroup"))
				.andCommandKey(HystrixCommandKey.Factory.asKey("CircuitBreakerTestKey"))
				.andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("CircuitBreakerTestThreadKey"))
				.andThreadPoolPropertiesDefaults(  // 配置线城池核心线程数,防止线程池满引起熔断
						HystrixThreadPoolProperties
						.Setter().
						withCoreSize(200))
				.andCommandPropertiesDefaults(
						HystrixCommandProperties.
						Setter().
						withCircuitBreakerEnabled(true) // 是否启用熔断器
						.withCircuitBreakerRequestVolumeThreshold(3) // 10s内访问3次,熔断器生效
						.withCircuitBreakerErrorThresholdPercentage(80) // 当出错率超过80%后熔断器启动
						));
		this.name = name;
	}

	@Override
	protected String run() throws Exception {
		System.out.println("running run():" + name);
		Integer i = Integer.valueOf(name);
		if(i%2==0 && i<10){
			return name;
		}else{
			// 模拟超时操作
			while(true){}
		}
	}
	
	public String getFallback(){
		return "getFallback"+name;
	}
	
	public static class UnitTest{
		
		@Test
		public void test01() throws IOException{
			for(int i=0;i<50;i++){
				System.out.println(new HystrixCommand4CircuitBreakerTest(String.valueOf(i)).execute());
			}
        	
        	System.in.read();
		}
	}
	
	
}
