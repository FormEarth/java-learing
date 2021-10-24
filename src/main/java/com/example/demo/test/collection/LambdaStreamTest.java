package com.example.demo.test.collection;


import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.example.demo.test.entity.Student;



/**
 * jdk8中stream api
 *
 * @author raining_heavily
 * @date 2019/12/5 11:38
 **/
public class LambdaStreamTest {

    public static void addStudent(List<Student> students) {
//        list.add(new Student());
        students.add(new Student("20180001", "张三（zhangsan）", true, LocalDate.of(1996, 12, 12)));
        students.add(new Student("20180002", "李四（lisi）", false, LocalDate.of(1998, 3, 23)));
        students.add(new Student("20180003", "王五（wangwu）", true, LocalDate.of(1995, 2, 1)));
        students.add(new Student("20180004", "陈二（chener）", false, LocalDate.of(1997, 3, 20)));
        students.add(new Student("20180004", "赵六（zhaoliu）", true, LocalDate.of(1995, 6, 6)));
    }

    /**
     * Student按照生日的排序规则，年龄小的在前，年龄大的在后
     *
     * @return
     */
    private static Comparator<Student> getComparator() {
        return (o1, o2) -> {
            LocalDate o1birth = o1.getBirthday();
            LocalDate o2birth = o2.getBirthday();
            //1997 1996
            if (o1birth.isAfter(o2birth)) {
                return -1;
            } else if (o1birth.isBefore(o2birth)) {
                return 1;
            } else {
                return 0;
            }
        };
    }

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        addStudent(students);
        Stream<Student> stream = students.stream();
        Stream<Student> stream2 = students.parallelStream();
        List<Student> list;
        // list = students.stream().sorted().collect(Collectors.toList());//未实现Comparable直接调用sort会报ClassCastException
        //正序
        list = students.stream().sorted(getComparator()).collect(Collectors.toList());
        //倒序
        list = students.stream().sorted(getComparator().reversed()).collect(Collectors.toList());
        //对于已实现Comparable的类型(如String)，可以直接使用该字段排序
        list = students.stream().sorted(Comparator.comparing(Student::getName).reversed()).collect(Collectors.toList());
        //过滤
        list = students.stream().filter(Student -> {//很多的操作
            return Student.isMale() == false;
        }).collect(Collectors.toList());
//        List<String> stringList = students.stream().map(Student -> Student.getName().toUpperCase()).collect(Collectors.toList());
//        Map<String,String> map = students.stream().collect(Collectors.toMap(Student::getStudentNo,Student::getName));
        students.stream().forEach(System.out::println);
//        System.out.println(stringList);
//        System.out.println(students);
//        System.out.println(map);
//        HashSet set = new HashSet();
//        set.stream();
//
//        String[] strings = {"a","b","c"};
//        List<String> stringList = Stream.of(strings).map(String::toUpperCase).collect(Collectors.toList());

    }
}
