package com.example.demo.test.concurrent;

import java.time.Duration;
import java.time.LocalTime;

import lombok.extern.slf4j.Slf4j;

/**
 * 计时的工具类
 * 
 * @author qiyuan
 * @date 2022-3-16 14:44:59
 *
 */
@Slf4j
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
		log.info("{} {} begin: {}", prefix, Thread.currentThread().getName(), timer.getBegin());
		return timer;
	}

	public Timer end() {
		this.end = LocalTime.now();
		log.info("{} {} end: {},spend: {}ms", this.prefix, Thread.currentThread().getName(), this.getEnd(),
				this.getSeconds());
		return this;
	}

	public LocalTime getBegin() {
		return begin;
	}

	public LocalTime getEnd() {
		return end;
	}

	public Duration getDuration() {
		return Duration.between(begin, end);
	}

	public long getSeconds() {
		Duration duration = Duration.between(begin, end);
		return duration.toMillis();
	}

	public static void main(String[] args) {
		System.out.println(4%3);
	}
}
