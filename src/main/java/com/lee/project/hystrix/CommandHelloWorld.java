package com.lee.project.hystrix;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.Future;

import org.junit.Test;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import rx.Observable;
import rx.Observer;
import rx.functions.Action1;

public class CommandHelloWorld extends HystrixCommand<String> {

	private final String name;

	public CommandHelloWorld(String name) {
		super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
		this.name = name;
	}

	@Override
	protected String run() {
		// a real example would do work like a network call here
		return "Hello " + name + "!";
	}

	public static class UnitTest {
		
		/**
		 * 同部方法测试
		 * @throws Exception
		 */
		@Test
		public void testSynchronous() throws Exception{
			assertEquals("Hello World!",new CommandHelloWorld("World").execute());
			assertEquals("Hello Bob!",new CommandHelloWorld("Bob").execute());
			System.in.read();  // 主线程不执行退出,等待其他线程执行
		}
		/**
		 * 异步方法测试
		 * @throws Exception
		 */
		@Test
		public void testAsychronous1() throws Exception{
			assertEquals("Hello World!",new CommandHelloWorld("World").queue().get());
			assertEquals("Hello Bob!",new CommandHelloWorld("Bob").queue().get());
		}
		/**
		 * 异步方法测试02
		 * @throws Exception
		 */
		@Test
		public void testAsychronous2() throws Exception{
			Future<String> fWorld = new CommandHelloWorld("World").queue();
			Future<String> fBob = new CommandHelloWorld("Bob").queue();
			
			assertEquals("Hello World!", fWorld.get());
			assertEquals("Hello Bob!",fBob.get());
		}
		
		@Test
		public void testObservable() throws Exception{
			Observable<String> fWorld = new CommandHelloWorld("World").observe();
			Observable<String> fBob = new CommandHelloWorld("Bob").observe();
			
			// Observable<String> fWorld = new CommandHelloWorld("World").toObservable(); // 这种方式不可行
			// Observable<String> fBob = new CommandHelloWorld("Bob").toObservable();
			assertEquals("Hello World!",fWorld.toBlocking().single());
			assertEquals("Hello Bob!",fBob.toBlocking().single());
	
			fWorld.subscribe(new Observer<String>(){  // 观察者模式的应用

				public void onCompleted() {
					System.out.println("onCompleted!");
					
				}

				public void onError(Throwable e) {
					e.printStackTrace();
					
				}

				public void onNext(String v) {
					System.out.println("onNext: "+v);
					
				}
				
			});
			
			fBob.subscribe(new Action1<String>() {

				public void call(String v) {
					System.out.println("onNext: "+v);
					
				}
			});
		}
		
		
		
	}

}
