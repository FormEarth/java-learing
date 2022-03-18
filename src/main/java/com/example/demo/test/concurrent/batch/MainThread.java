package com.example.demo.test.concurrent.batch;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.example.demo.test.concurrent.Timer;

public class MainThread {

	static int THREAD_NUMBER = 4;
	private ThreadPoolExecutor pool;
	private SimpleTask[] tasks = new SimpleTask[THREAD_NUMBER];

	void execute() {
		
		Timer timer = Timer.begin();
		pool = new ThreadPoolExecutor(THREAD_NUMBER, THREAD_NUMBER, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
		for (int i = 0; i < THREAD_NUMBER; i++) {
			SimpleTask task = new SimpleTask();
			pool.execute(task);
			tasks[i] = task;
		}
		for (int i = 0; i < 100; i++) {
			int index = i % THREAD_NUMBER;
			tasks[index].put(String.valueOf(i));
		}
		pool.shutdown();
		while(true) {
			if(pool.isTerminated()) {
				timer.end();
				break;
			}
		}

	}

	public static void main(String[] args) {

		MainThread mt = new MainThread();
		mt.execute();

	}
}
