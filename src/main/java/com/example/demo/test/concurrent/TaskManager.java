package com.example.demo.test.concurrent;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class TaskManager {

	private ThreadPoolExecutor threadPool;

	void execute() {
		ArrayList<Future<String>> counter = new ArrayList<>();
		Timer timer = Timer.begin("-->");
		// ExecutorService pool = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 20; i++) {
			Future<String> future = threadPool.submit(new CallableTask("" + i));
			counter.add(future);
		}
		threadPool.shutdown();
		while (true) {
			if (threadPool.isTerminated()) {
				Map<String, Long> map = counter.stream().map(f -> {
					try {
						return f.get();
					} catch (InterruptedException | ExecutionException e) {
						e.printStackTrace();
						return "-1";
					}
				}).collect(Collectors.groupingBy(t -> t, Collectors.counting()));
				System.out.println(String.format("total: %s,success: %s,failed: %s,exception: %s", counter.size(),
						map.get("1"), map.get("0"), map.get("-1")));
				timer.end();
				break;
			}
		}
	}

	void test() {
		ExecutorService pool = Executors.newCachedThreadPool();// maximumPoolSize:Integer.MAX_VALUE,�ɴ������������߳̿��ܵ���OOM
		// LinkedBlockingQueue,
		ExecutorService pool1 = Executors.newSingleThreadExecutor();
		ExecutorService pool2 = Executors.newFixedThreadPool(1);
	}
	
	void atFixedRate() {
		ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(2);
		scheduled.scheduleAtFixedRate(new RunnableTask(), 0, 5, TimeUnit.SECONDS);
	}
	
	void withFixedDelay() {
		//
		ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(2);
		scheduled.scheduleWithFixedDelay(new RunnableTask(), 0, 5, TimeUnit.SECONDS);
	}

	void rejectPolicy_default() {
		//AbortPolicy 
		this.threadPool = new ThreadPoolExecutor(3, 3, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
	}
	
	void rejectPolicy_discard() {
		//DiscardPolicy 
		this.threadPool = new ThreadPoolExecutor(3, 3, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10),Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardPolicy());
	}
	
	void rejectPolicy_discardoldest() {
		//DiscardOldestPolicy 
		this.threadPool = new ThreadPoolExecutor(3, 3, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10),Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardOldestPolicy());
	}
	
	void rejectPolicy_callerrunspolicy() {
		//CallerRunsPolicy 
		this.threadPool = new ThreadPoolExecutor(3, 3, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10),Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
	}

	public static void main(String[] args) {
		TaskManager tm = new TaskManager();
//		tm.rejectPolicy_default();
//		tm.execute();
//		tm.atFixedRate();
		tm.withFixedDelay();
	}
}
