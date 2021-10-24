package com.example.demo.test.util;

import java.util.Base64;

/**
 * base64加解密
 *
 * @author raining_heavily
 * @date 2019/12/23 10:29
 **/
public class Base64Test {

    static String encoded(String str) {
        return Base64.getEncoder().encodeToString(str.getBytes());
    }

    static String decoded(String str) {
        return new String(Base64.getDecoder().decode(str.getBytes()));
    }

    public static void main(String[] args) {
        String str = "hello word!";
        String result;
        System.out.println(result = encoded(str));
        System.out.println(decoded(result));
    }
}
