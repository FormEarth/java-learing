package com.example.demo.test.entity;

/**
 * @author raining_heavily
 * @date 2019/12/8 16:41
 **/
public interface Bird {

    void fly();
    void run();
    default void call() {
        System.out.println("ou ou ou~~");
    }
}
