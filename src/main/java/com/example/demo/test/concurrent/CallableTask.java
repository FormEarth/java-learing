package com.example.demo.test.concurrent;

import java.util.Random;
import java.util.concurrent.Callable;

public class CallableTask implements Callable<String> {

	private String id;

	CallableTask(String id) {
		this.id = id;
	}

	@Override
	public String call() throws Exception {
		Timer timer = Timer.begin(id);
		Random random = new Random();
		int i = random.nextInt(10);
		if (i > 5)
			throw new Exception("TIMEOUT " + i);
		Thread.sleep(i * 1000);
		timer.end();
		if (i >= 4) {
			return "0";
		} else {
			return "1";
		}

	}

}
