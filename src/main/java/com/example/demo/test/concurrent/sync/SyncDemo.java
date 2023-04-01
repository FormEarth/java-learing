package com.example.demo.test.concurrent.sync;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 多线程执行顺序的demo<br>
 * execute只能提交Runnable类型的任务，无返回值。submit既可以提交Runnable类型的任务，也可以提交Callable类型的任务，会有一个类型为Future的返回值，但当任务类型为Runnable时，返回值为null。<br>
 * execute在执行任务时，如果遇到异常会直接抛出，而submit不会直接抛出，只有在使用Future的get方法获取返回值时，才会抛出异常。
 */
public class SyncDemo {

    private final static String[] ARRAY = {"A", "B", "C"};
    static Semaphore semaphore = new Semaphore(1); //信号量
    private volatile static int count = 0;

    /**
     * 多线程执行时顺序无法保证<br>
     */
    Runnable basic(String str) {
        return () -> {
            System.out.println(str);
        };
    }

    /**
     * 使用synchronized、wait、notifyAll保证线程顺序
     *
     * @param str
     * @return
     */
    Runnable test(String str) {
        return () -> {
            for (int i = 1; i <= 10; i++) {
                synchronized (ARRAY) {
                    int index = count % ARRAY.length;
                    System.out.println(Thread.currentThread().getName() + ":  " +  Objects.equals(ARRAY[index], str));
                    while (!Objects.equals(ARRAY[index], str)) {
                        try {
                            ARRAY.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
//                        if(count > 10) {
//                            break;
//                        }
                    }
                    System.out.println(str);
                    count++;
                    ARRAY.notifyAll();
                    System.out.println(count);
                }
            }
        };
    }

    public static void main(String[] args) {

        SyncDemo demo = new SyncDemo();
        ExecutorService service = Executors.newFixedThreadPool(3);
        for (String temp : ARRAY) {
//            service.submit(demo.basic(temp));
            service.submit(demo.test(temp));
        }
        service.shutdown();
    }

}