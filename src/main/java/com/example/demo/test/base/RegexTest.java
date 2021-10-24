package com.example.demo.test.base;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式
 *
 * @author raining_heavily
 * @date 2019/12/24 16:30
 **/
public class RegexTest {
    //6-10位数字或字母
    private static String NumberAndLetter = "[0-9a-zA-Z]{6,10}";
    //中文
    private static String CHINESE = "[\\u4e00-\\u9fa5]";

    //是否匹配
    public static boolean isMatch(String str) {
        return str.matches(NumberAndLetter);
        // return Pattern.matches(NumberAndLetter,str);//与上面相同
    }

    //获取
    public static List<String> getMatch(String str, String label) {
        String regex = "<" + label + ">(.*?)</" + label + ">";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        List<String> result = new ArrayList<>();
        while (matcher.find()) { //IllegalStateException
            result.add(matcher.group(1));
        }
        return result;
    }

    public static void main(String[] args) {

        System.out.println(isMatch("hellow"));
        System.out.println(getMatch("<div>1</div> <div>2</div> <div>3</div>", "div"));
    }

}
