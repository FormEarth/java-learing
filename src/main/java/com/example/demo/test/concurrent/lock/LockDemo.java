package com.example.demo.test.concurrent.lock;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author raining_heavily
 * @date 2022/1/18 20:24
 **/
@Slf4j
public class LockDemo {
    static private ConcurrentHashMap<String, ReentrantLock> DEVICES = new ConcurrentHashMap();

    synchronized static ReentrantLock getLock(String key) {
        ReentrantLock lock = DEVICES.get(key);
        if (lock == null) {
            lock = addLock(key);
        }
        return lock;
    }

    static ReentrantLock addLock(String key) {
        ReentrantLock lock = new ReentrantLock();
        DEVICES.put(key, lock);
        log.info("{} {}", key, "1st enter");
        return lock;
    }

    public static void main(String[] args) {

        ExecutorService service = Executors.newFixedThreadPool(3);
        List<String> list = Arrays.asList("SN001", "SN001", "SN001", "SN002", "SN002", "SN001", "SN001", "SN003");

        for (String dev : list) {
            service.submit(() -> {
                LocalDateTime start = LocalDateTime.now();
                ReentrantLock lock = getLock(dev);
                boolean b = false;
                try {
                    b = lock.tryLock(6, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("{} {}", dev, b);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    log.error("", e);
                }
                LocalDateTime end = LocalDateTime.now();
                log.info("{} {},{},{}ms", dev, start, end, Duration.between(start, end).toMillis());
                lock.unlock();
            });
        }

        service.shutdown();
    }
}
