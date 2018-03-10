package com.lee.project.hystrix;

import org.junit.Test;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class CommandHelloWorldObserve extends HystrixObservableCommand<String>{
	private final String name;
	
	public CommandHelloWorldObserve(String name){
		super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
		this.name = name;
	}

	@Override
	protected Observable<String> construct() {
		return Observable.create(new Observable.OnSubscribe<String>() {

			public void call(Subscriber<? super String> observer) {
				try {
                    if (!observer.isUnsubscribed()) {
                        // a real example would do work like a network call here
                    	
                    	// 可以发送多条数据
                        observer.onNext("Hello");
                        observer.onNext(name + "!");
                        observer.onCompleted();
                    }
                } catch (Exception e) {
                    observer.onError(e);
                }
				
			}
		}).subscribeOn(Schedulers.io());
	}
	
	public static class UnitTest{
		/**
		 * execute()方法的等效
		 * @throws Exception
		 */
		@Test
		public void execute() throws Exception{
			CommandHelloWorldObserve commandHelloWorldObserve = new CommandHelloWorldObserve("LiLei");
			Observable<String> observable = commandHelloWorldObserve.toObservable().single();
			observable.subscribe(new Action1<String>() {

				public void call(String v) {	
					System.out.println(v);
					
				}
			
			});
		}
		
		/**
		 * queue()方法等效
		 * @throws Exception
		 */
		@Test
		public void queue() throws Exception{
			CommandHelloWorldObserve commandHelloWorldObserve = new CommandHelloWorldObserve("LiLei");
			Observable<String> observable = commandHelloWorldObserve.toObservable().single();
			observable.subscribe(new Observer<String>() {

				public void onCompleted() {
					// TODO Auto-generated method stub
					
				}

				public void onError(Throwable arg0) {
					// TODO Auto-generated method stub
					
				}

				public void onNext(String arg0) {
					// TODO Auto-generated method stub
					
				}
			
			});
		}
	}
}
