package com.example.demo.test.base;

import com.example.demo.test.entity.User;

/**
 * 拷贝、浅拷贝与深拷贝
 * @author raining_heavily
 * @date 2019/11/21 13:46
 **/
public class CloneTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        User user = new User("1","1","2019/11/21","1");
        User user1 = user;
        user.setName("2");
        System.out.println(user1);
//        user1.setName("2");
//        System.out.println(user);
//        User user2 = (User) user.clone();
//        user2.setName("3");
//        System.out.println(user);
    }
}
