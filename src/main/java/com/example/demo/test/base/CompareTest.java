package com.example.demo.test.base;

import java.text.Collator;
import java.util.Arrays;
import java.util.Locale;

/**
 * 排序
 *
 * @author raining_heavily
 * @date 2019/12/5 15:47
 **/
public class CompareTest {
    static void numberSort() {
        Integer[] numbers = {7, 12, -2, 5, 6, 7, 3, 0};
        Arrays.sort(numbers);
        System.out.println(Arrays.asList(numbers));
    }

    static void englishSort() {
        String[] strings = {"abc", "a", "bc", "b", "z", "ff"};
        String[] strings1 = {"0abc", "0a", "b1c", "-1b", "ez", "ff", ",er", "+45"};
        Arrays.sort(strings);
        Arrays.sort(strings1);
        System.out.println(Arrays.asList(strings));
        System.out.println(Arrays.asList(strings1));
    }

    static void chineseSort() {
        String[] strings2 = {"恶（e）", "个（g）", "得（d）", "啊（a）", "发（f）", "词（c）"};
        Arrays.sort(strings2);//String 得排序默认实现是按照每个字符得Unicode编码顺序来的，对非英文排序会不符合习惯
        System.out.println(Arrays.asList(strings2));
        Arrays.asList(strings2).stream().map(str -> getStringUnicode(str)).forEach(x->System.out.println(x));
        Arrays.sort(strings2, Collator.getInstance(Locale.CHINA));
        System.out.println(Arrays.asList(strings2));
    }

    /**
     * 由于gb2312中的汉字编码是连续的，因此新增加的汉字不可能再按照拼音顺序
     * 插入到已有的gb2312编码中，所以新增加的汉字不是按拼音顺序排的。 同音字比较的结果不等于0 。
     */
    static void specialChineseSort() {
        String[] strings = {"怡", "张"};
        Arrays.sort(strings, Collator.getInstance(Locale.CHINA));
        System.out.println(Arrays.asList(strings));//[张, 怡]
    }

    static void chineseAndEnglishSort() {
        String[] strings = {"恶（e）", "个（g）", "得（d）", "fff"};
        Arrays.sort(strings, Collator.getInstance(Locale.CHINA));
        System.out.println(Arrays.asList(strings));//[fff, 得（d）, 恶（e）, 个（g）]
    }

    static String getStringUnicode(String str) {
        String unicode = "";
        char[] chars = str.toCharArray();
        for (char c : chars) {
            int i = (int) c;//十进制
            String x = Integer.toHexString(i);//十六进制
            unicode += x+"-";
        }
        return unicode;
    }

    public static void main(String[] args) {
//        numberSort();
//        englishSort();
//        chineseSort();
//        specialChineseSort();
//        chineseAndEnglishSort();
        System.out.println('g'-'f');

    }
}
