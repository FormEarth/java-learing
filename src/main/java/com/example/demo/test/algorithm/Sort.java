package com.example.demo.test.algorithm;

import java.util.Arrays;

/**
 * 常见排序算法
 *
 * @author raining_heavily
 * @date 2019/12/8 16:28
 **/
public class Sort {

    // 冒泡排序（常规）
    public static void bubbleSort(int[] array) {
        // if (array == null || array.length <= 1) {
        // return array;
        // }
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j + 1] < array[j]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(array));
    }

    // 冒泡排序（优化）
    public static void bubbleSortPro(int[] array) {
        // if (array == null || array.length <= 1) {
        // return array;
        // }
        for (int i = 1; i < array.length; i++) {
            boolean flag = true;
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j + 1] < array[j]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
        System.out.println(Arrays.toString(array));
    }

    // 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，
    // 然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
    public static void selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int min = i;
            for (int j = i; j < array.length; j++) {
                if (array[j] < array[min]) {
                    min = j;
                }
            }
            if (min != i) {
                // 发生了调换
                int temp = array[min];
                array[min] = array[i];
                array[i] = temp;
            }
        }
        System.out.println(Arrays.toString(array));
    }

    // TODO 快排
    static void QuickSort() {

    }

    public static void main(String[] args) {
        int[] num = { 9, -1, 4, 2, 6, 9, 3 };
         bubbleSort(num);
        // bubbleSortPro(num);
        // selectionSort(num);

    }
}
