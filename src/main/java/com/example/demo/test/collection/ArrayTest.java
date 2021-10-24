package com.example.demo.test.collection;

import java.util.Arrays;

/**
 * 数组
 * @author raining_heavily
 * @date 2019/12/9 11:53
 **/
public class ArrayTest {

    public static void systemArrayCopy(){
        int[] nums = {1,2,3,4,5,6};
        int[] anotherNum = new int[5];
        System.arraycopy(nums,0,anotherNum,0,6);
        System.out.println(Arrays.toString(anotherNum));
    }

    public static void main(String[] args) {
//        systemArrayCopy();
        int[] a = new int[10];
        a = new int[]{1,2,3,4};
        System.arraycopy(a, 2, a, 3, 3);
        System.out.println(Arrays.toString(a));
    }
}
