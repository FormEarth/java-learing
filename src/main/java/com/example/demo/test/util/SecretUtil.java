package com.example.demo.test.util;

import org.springframework.util.StringUtils;

/**
 *
 */
public class SecretUtil {

    public static final char REPLACE_CHAR = '*';

    enum MODE {
        BEFORE, CENTER, AFTER
    }

    private static String secret(int length, String value) {

        if (StringUtils.hasText(value)) {
            int valueLength = value.length();
            int beginIndex = 0;
            int endIndex;
            if (valueLength <= length * 2) {
                // 全部替换
                endIndex = valueLength - 1;
            } else {
                beginIndex = length;
                endIndex = value.length() - length - 1;
            }
            System.out.println(beginIndex + "  " + endIndex);
            char[] chars = value.toCharArray();
            for (int i = beginIndex; i <= endIndex; i++) {
                chars[i] = REPLACE_CHAR;
            }
            return new String(chars);
        }
        return "";
    }

    public static void main(String[] args) {

        System.out.println(secret(2, "18203649358"));

        System.out.println(secret(6, "1820364935"));
    }

}
