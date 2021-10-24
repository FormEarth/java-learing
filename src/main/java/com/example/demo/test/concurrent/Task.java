package com.example.demo.test.concurrent;

import java.util.Random;

public class Task implements Runnable {

	private String id;

	@Override
	public void run() {
		Timer timer = Timer.begin(id);
		Random random = new Random();
		int i = random.nextInt(5);
		try {
			Thread.sleep(i * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		timer.end();
	}

	public Task setId(String id) {
		this.id = id;
		return this;
	}

}
