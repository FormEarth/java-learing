package com.example.demo.test.concurrent.sync;

import java.time.Duration;
import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;

/**
 * synchronized关键字
 *
 * @author qiyuan
 * @date 2022-3-16 10:45:08
 */
@Slf4j
public class SynchronizedDemo {

    static private int init = 0;

    static void add() {
        init++;
    }

    /**
     * 修饰静态方法，因静态方法不仅属于当前类，所有调用都可实现同步，类锁
     */
    synchronized static void addSync() {
        init++;
    }

    void addInner() {
        init++;
    }

    /**
     * 修饰实例方法，仅对当前实例可实现同步，实例锁
     */
    synchronized void addInnerSync() {
        init++;
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(8);
        LocalTime begin = LocalTime.now();
        log.info("{} ---> {}", begin, init);
        SynchronizedDemo demo = new SynchronizedDemo();
        for (int i = 0; i < 1000; i++) {
            service.submit(() -> {
//					add();
//					addSync();
//					SynchronizedDemo demo = new SynchronizedDemo();
//					demo.addInner();
//					demo.addInnerSync();
                    //在代码块中指定获取的具体锁，类锁或实例锁
//					synchronized (SynchronizedDemo.class) {
//						add();
//					}
                synchronized (demo) {
                    demo.addInner();
                }
            });
        }
        service.shutdown();
        while (true) {
            if (service.isTerminated()) {
                LocalTime end = LocalTime.now();
                log.info("{} ---> {} {}ms", end, init, Duration.between(begin, end).toMillis());
                break;
            }
        }
    }
}
