package com.example.demo.test.algorithm;

/**
 * 递归
 *
 * @author raining_heavily
 * @date 2019/12/19 17:07
 **/
public class RecursiveTest {

    //1~100求和
    static int mac(int i) {
        while (i <= 4) {
            System.out.println("---");
            return i + mac(i + 1);
        }
        System.out.println("---");
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(mac(1));
    }
}
