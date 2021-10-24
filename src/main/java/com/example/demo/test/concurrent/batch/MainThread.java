package com.example.demo.test.concurrent.batch;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainThread {

	public static void main(String[] args) {

        Thread t1 = new SimpleTask();
		
		ExecutorService pool = Executors.newFixedThreadPool(3);
		
		for (int i = 0; i < 1000; i++) {

			
		}
		
	}
}
