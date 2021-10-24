package com.example.demo.test.concurrent;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.concurrent.*;

/**
 * 线程池
 *
 * @author raining_heavily
 * @date 2019/12/22 15:16
 **/
public class ThreadPool {
    static final File dir = new File("F:/test/thread_pool/");

    public static void main(String[] args) {
        ExecutorService es = Executors.newCachedThreadPool();
        Arrays.stream(dir.listFiles()).forEach(file -> file.delete());
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(5));
        for (int i = 0; i < 10; i++) {
            executor.execute(new Task1());
        }
        executor.shutdown();
    }
}

class Task1 implements Runnable {

    private static int num = 3;

    @Override
    public void run() {
        try {
//            synchronized (this){
                sub();
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

     void sub() throws IOException {
         String time = LocalTime.now().toString();
         System.out.println(String.format("Thread-%s time:%s %s", Thread.currentThread().getId(),time ,num));
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(num > 0){
//            File file = new File(ThreadPool.dir,time.replaceAll(":","") +".log");
//            file.createNewFile();
            num = num-1;
            System.out.println(String.format("Thread-%s time:%s %s", Thread.currentThread().getId(),time ,num));
        }

    }
}

