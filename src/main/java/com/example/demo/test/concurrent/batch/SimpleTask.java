package com.example.demo.test.concurrent.batch;

import java.util.LinkedList;
import java.util.Queue;

public class SimpleTask extends Thread {

    Queue<String> queue = new LinkedList<>();

    @Override
    public void run() {
        if (!queue.isEmpty()) {
            String s = queue.poll();
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            System.out.println(s);
        }
    }

    void put(String str) {
        this.queue.add(str);
    }


}
