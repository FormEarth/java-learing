package com.example.demo.test.base;


import java.util.HashSet;

import com.example.demo.test.entity.User;

/**
 * @author raining_heavily
 * @date 2021/10/19 22:34
 **/
public class EqualsTest {
    public static void main(String[] args) {
        User user = new User();
        user.setUserId("100");
        user.setName("jack");
        User user1 = new User();
        user1.setUserId("101");
        user1.setName("jack");
        User user2 = new User();
        user2.setUserId("100");
        user2.setName("jane");

        HashSet<User> set = new HashSet<>();
        set.add(user);
        set.add(user1);
        System.out.println(set.size());
        System.out.println(set);

    }
}
