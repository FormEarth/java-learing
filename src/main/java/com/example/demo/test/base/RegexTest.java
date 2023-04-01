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
    private static final String NumberAndLetter = "[0-9a-zA-Z]{6,10}";
    
    private static final String SimplePassword = "[0-9a-zA-Z,_@.]{6,20}";
    //中文
    private static final String CHINESE = "[\\u4e00-\\u9fa5]";

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
    
    static void matcher(String input){
        String regex = "\\d{3}(123|abc){2}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        System.out.println(matcher.matches());
        String find = matcher.group(1);
        System.out.println(find);
    }
    
    static void simplePassword() {
//    	String str = "开国大典";
    	String str = "123abc@ABC_.";
    	boolean b = str.matches(SimplePassword);
    	System.out.println(b);
    }

    public static void main(String[] args) {

//        System.out.println(isMatch("hellow"));
//        System.out.println(getMatch("<div>1</div> <div>2</div> <div>3</div>", "div"));
//        matcher("AAA123abc123");
        
        simplePassword();
        
    }

}
