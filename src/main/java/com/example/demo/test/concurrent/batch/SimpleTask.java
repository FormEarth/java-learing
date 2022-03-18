package com.example.demo.test.concurrent.batch;

import java.util.LinkedList;
import java.util.Queue;

public class SimpleTask extends Thread {

	Queue<String> queue = new LinkedList<>();

	@Override
	public void run() {
		sleep1(100);
		while (!queue.isEmpty()) {
			String s = queue.poll();
			sleep1(100);
			System.out.println(s + "-" + Thread.currentThread().getName());
		}
	}

	void put(String str) {
		this.queue.add(str);
	}

	void sleep1(long num) {
		try {
			Thread.sleep(num);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

}
