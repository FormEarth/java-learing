package com.example.demo.test.base;

/**
 * 运算符相关
 *
 * @author raining_heavily
 * @date 2019/12/8 20:26
 **/
public class OperatorTest {
    //先赋值、后自增
    private static void countFirst(int i) {
        int result = 2 + i++;
        System.out.println(result);
    }
    //先自增、后赋值
    private static void assignmentFirst(int i) {
        int result = 2 + ++i;
        System.out.println(result);
    }

    public static void main(String[] args) {
        int a = 0, b = 0;
        countFirst(a) ;
        assignmentFirst(b);
    }
}
