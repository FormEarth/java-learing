package com.example.demo.test.design_pattern;

/**
 * 设计模式：单例模式
 *
 * @author raining_heavily
 * @date 2019/12/8 16:46
 **/
public class Singleton<Synchronized> {

    // 私有化构造方法，禁止在该类之外实例化
    private Singleton() {
    }

    private static volatile Singleton singleton = null;

    //	饿汉式
    public static Singleton getHungryInstance() {
        return singleton;
    }

    //	懒汉式(双重同步锁)
    synchronized public static Singleton getLazyInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    System.out.println("lazyInstance is null!");
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

    //静态内部类
    private static class SingletonHelper {
        static Singleton singleton = new Singleton();
    }

    public static Singleton getStaticInnerInstance() {
        return SingletonHelper.singleton;
    }

    private String str = "Hello Word!";

    public String getStr() {
        return str;
    }

    public static void main(String[] args) {
        Singleton singleton = Singleton.getLazyInstance();
        singleton = null;
        Singleton singleton1 = Singleton.getHungryInstance();
        System.out.println(singleton1.getStr());
        Singleton singleton2 = Singleton.getStaticInnerInstance();

    }

}
