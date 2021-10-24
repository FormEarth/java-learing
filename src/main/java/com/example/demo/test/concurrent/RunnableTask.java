package com.example.demo.test.concurrent;

import java.time.LocalTime;

public class RunnableTask implements Runnable {

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() +"--start--"+ LocalTime.now());
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() +"--end--"+ LocalTime.now());
	}
	
}
