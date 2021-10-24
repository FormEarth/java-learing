package com.example.demo.test.base;

/**
 * 包装类型
 * @author raining_heavily
 * @date 2019/12/9 23:03
 **/
public class IntegerTest {

    /** Integer的缓存机制 **/
    static void integerCache(){
        Integer a = new Integer(10);
        Integer b = new Integer(10);
        Integer c = 10;
        Integer d = 10;
        Integer e = 128;
        Integer f = 128;
        System.out.println(a==b);//false
        System.out.println(c==d);//true
        System.out.println(e==f);//false
    }

    static void string(){
        String a = "Hello";
        String b = "Hello";
        System.out.println(a==b);//true
    }

    public static void main(String[] args) {
//        integerCache();
        string();
    }
}
