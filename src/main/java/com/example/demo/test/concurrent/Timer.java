package com.example.demo.test.concurrent;

import java.time.Duration;
import java.time.LocalTime;

public class Timer {

	private LocalTime begin;
	private LocalTime end;
	private String prefix;

	private Timer() {

	}

	public static Timer begin() {
		return begin("");
	}

	public static Timer begin(String prefix) {
		Timer timer = new Timer();
		timer.prefix = prefix;
		timer.begin = LocalTime.now();
		System.out.println(String.format(prefix + " %s begin: %s", Thread.currentThread().getName(), timer.getBegin()));
		return timer;
	}

	public Timer end() {
		this.end = LocalTime.now();
		System.out.println(String.format(this.prefix + " %s end: %s,spend: %ss", Thread.currentThread().getName(),
				this.getEnd(), this.getSeconds()));
		return this;
	}

	public LocalTime getBegin() {
		return begin;
	}

	public LocalTime getEnd() {
		return end;
	}

	public long getSeconds() {
		Duration duration = Duration.between(begin, end);
		return duration.getSeconds();
	}
}
