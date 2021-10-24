package com.example.demo.test.concurrent;

import java.util.concurrent.*;

/**
 * 多线程
 *
 * @author raining_heavily
 * @date 2019年12月3日
 * @time 下午2:20:21
 */
public class ThreadTest {
    private static int c = 0;

    // 1.继承Thread类，无法再继承其它类,无返回值
    class Processor1 extends Thread {
        @Override
        public void run() {
            System.out.println("Processor1 hello world ");
        }
    }

    // 2.实现Runnable接口，无返回值
    class Processor2 implements Runnable {
        @Override
        public void run() {
            System.out.println("Processor2 hello world");
        }
    }

    // 2.实现Callable接口，有返回值
    class Processor3 implements Callable {
        @Override
        public String call() {
            return "Processor3 hello world";
        }
    }

    static void add(int i) {
        System.out.println("线程执行");
        i++;
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadTest test = new ThreadTest();
        Thread thread1 = test.new Processor1();
        Runnable thread2 = test.new Processor2();
        thread1.start();
        ((Thread) thread2).start();
        ExecutorService es = Executors.newFixedThreadPool(10);
        Future future = es.submit(test.new Processor3());
        try {
            //获取结果
            future.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
