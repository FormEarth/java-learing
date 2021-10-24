package com.example.demo.test.base;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
//正则表达式
public class RegularExpressionTest{

    static void matcher(String input){
        String regex = "\\d{3}(123|abc){2}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        System.out.println(matcher.matches());
        String find = matcher.group(1);
        System.out.println(find);
    }

    public static void main(String[] args) {
        matcher("AAA123abc123");
    }
}