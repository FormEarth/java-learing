package com.example.demo.test.collection;

import java.util.*;

/**
 * @author raining_heavily
 * @date 2020/3/26 15:44
 **/
public class SetTest {

    public void set(){
        //HashSet无序，添加的顺序会被打乱
        Set<String> set = new HashSet();
        set.add("ccc");
        set.add("bbb");
        set.add("aaa");
        set.stream().forEach(s -> System.out.println(s));
        //LinkedHashSet有序，保证添加的顺序
        Set<String> linkedSet = new LinkedHashSet<>();
        linkedSet.add("ccc");
        linkedSet.add("bbb");
        linkedSet.add("aaa");
        linkedSet.stream().forEach(s -> System.out.println(s));
        //TreeSet,可以保证排序，默认为自然排序,可以指定排序规则，这里指定倒序
        TreeSet<String> treeSet = new TreeSet<>(Comparator.reverseOrder());
        treeSet.add("ccc");
        treeSet.add("bbb");
        treeSet.add("aaa");
        treeSet.stream().forEach(s -> System.out.println(s));
    }

    public static void main(String[] args) {
        new SetTest().set();
    }
}
