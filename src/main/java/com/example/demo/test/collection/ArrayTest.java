package com.example.demo.test.collection;

import java.util.Arrays;

/**
 * 数组
 * @author raining_heavily
 * @date 2019/12/9 11:53
 **/
public class ArrayTest {

    static void printArray(){
        int[] ints = {1,2,3,4};
        System.out.println(ints);
        System.out.println(Arrays.toString(ints));
    }

    static void arrayInit() {
        String[] arr = new String[4];
        // error: Array constants can only be used in initializers
        // arr = { "a", "b", "c","d" };
        String[] arr1 = { "a", "b", "c", "d" };
        String[] arr2 = new String[] { "a", "b", "c", "d" };
        System.out.println(arr + "," + arr1 + "," + arr2);
    }

    static void arrayCopy() {
        String[] arr1 = { "a", "b", "c", "d" };
        String[] arr2 = new String[] { "e", "f", "g" };
        int origin_length = arr1.length;
        // 扩容数组
        arr1 = Arrays.copyOf(arr1, arr1.length + arr2.length);
        System.out.println(Arrays.asList(arr1));
        // Object src：the source array. 源数组
        // int srcPos：starting position in the source array. 在源数组中，开始复制的位置
        // Object dest：the destination array. 目标数组
        // int destPos：starting position in the destination data. 在目标数组中，开始赋值的位置
        // int length：the number of array elements to be copied. 被复制的数组元素的数量
        System.arraycopy(arr2, 0, arr1, origin_length, arr2.length);
        System.out.println(Arrays.asList(arr1));
    }

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
