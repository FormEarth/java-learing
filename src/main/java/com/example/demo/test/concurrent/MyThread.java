package com.example.demo.test.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author raining_heavily
 * @date 2019/12/22 21:56
 **/
public class MyThread {

    private static Integer count = 3;

    void subtract() {
        System.out.println(Thread.currentThread().getName() + "--init:" + count);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (count > 0) {
            count--;
        }

    }

    synchronized void subtract1() {
        System.out.println(Thread.currentThread().getName() + "--init:" + count);
        if (count > 0) {
            count--;
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

     void subtract2() {

        synchronized (count){
            System.out.println(Thread.currentThread().getName() + "--init:" + count);
            if (count > 0) {
                count--;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    void printChar(String str) {
        synchronized (this) {
            for (int i = 0; i < str.length(); i++) {
                System.out.print(str.charAt(i));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {

        Runnable runnable = () -> new MyThread().subtract();
//        new Thread(runnable).start();
//        new Thread(runnable).start();

        ExecutorService es = Executors.newFixedThreadPool(6);
//        ExecutorService es = Executors.newSingleThreadExecutor();
//        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            es.submit(new Task());
        }
        es.shutdown();
    }
}
