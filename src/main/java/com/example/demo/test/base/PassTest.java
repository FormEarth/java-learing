package com.example.demo.test.base;


import java.util.Arrays;

import com.example.demo.test.entity.User;


/**
 * 值传递OR引用传递
 *
 * @author raining_heavily
 * @date 2019/12/5 11:36
 **/
public class PassTest {
    static void changeInt(int num) {
        num = 10;
    }

    static void changeArray(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int temp = nums[0];
            nums[0] = nums[nums.length - 1];
            nums[nums.length - 1] = temp;
        }
    }

    static void changeInteger(Integer num) {
        num = 10;
    }

    static void changeString(String num) {
        num = "10";
    }

    static void changeUser(User user) {
        user = new User("10", "10", "2019/11/21", "10");
    }

    static void setUser(User user) {
        user.setUserId("10");
        user.setName("10");
        user.setSex("10");
    }

    public static void main(String[] args) {
        //基本数据类型
        int number = 1;
        changeInt(number);
        System.out.println(number);
        //基本数据类型
        int[] nums = {1, 2, 3, 4, 5, 6};
        changeArray(nums);
        System.out.println(Arrays.toString(nums));
        //引用类型
        Integer integer = 1;
        changeInteger(1);
        System.out.println(integer);
        //对象
        String str = "1";
        changeString(str);
        System.out.println(str);
        //自定义对象
        User user = new User("1", "1", "2019/11/21", "1");
        changeUser(user);
        System.out.println(user);
        //调用对象内方法成功修改了对象
        User user1 = new User("1", "1", "2019/11/21", "1");
        setUser(user1);
        System.out.println(user1);
    }
}
