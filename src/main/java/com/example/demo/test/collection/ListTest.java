package com.example.demo.test.collection;

import java.util.*;

import com.example.demo.test.entity.Teacher;
import com.example.demo.test.entity.User;


public class ListTest {
    private static List<com.example.demo.test.entity.User> users = new ArrayList<>();

    static {
        System.out.println("执行了static代码块");
        users.add(new User("00001", "Jack", "20190728", "1"));
        users.add(new User("00002", "Tom", "20190728", "1"));
        users.add(new User("00003", "Alone", "20190728", "2"));
        users.add(new User("00004", "Jan", "20190728", "2"));
    }

    /**
     * list排序，实现comparable接口
     *
     * @param
     */
    public static void listSort1() {
        System.out.println("执行了listSort1");
        Teacher teacher1 = new Teacher("123451", "123", "20180628", "1", "english", 2500);
        Teacher teacher2 = new Teacher("123451", "123", "20170528", "1", "english", 2500);
        Teacher teacher3 = new Teacher("123451", "123", "20160428", "1", "english", 2500);
        List<Teacher> teacherList = new ArrayList<>();
        teacherList.add(teacher1);
        teacherList.add(teacher2);
        teacherList.add(teacher3);
        // Collections.sort(teacherList);
        teacherList.sort(null);// 这种方法和上面的方法是一样的，null时会调用默认的，即实现Comparable重写的
        System.out.println(teacherList);
    }

    /**
     * list排序，自定义比较器Compare
     *
     * @param
     */
    public static void listSort2() {
        // User user1 = new Teacher("123452", "Jackson", "20190728", "1", "english", 2500);
        // User user2 = new Teacher("123451", "Cook", "20190728", "1", "english", 2500);
        // User user3 = new Teacher("123451", "Apple", "20190728", "1", "english", 2500);
        User user1 = new User("123452", "Jackson", "20190728", "1");
        User user2 = new User("123451", "Cook", "20190728", "1");
        User user3 = new User("123451", "Apple", "20190728", "1");
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        //userList.sort(null);//未实现Comparable接口时会报错，ClassCastException
        //自定义一个比较器
        userList.sort(new Comparator<User>() {
            @Override
            public int compare(User user1, User user2) {
                // 升序
                return user1.getName().compareTo(user2.getName());
            }
        });
        System.out.println(userList);
    }

    /**
     * List循环中安全删除元素
     */
    public static void listRemove() {
        System.out.println(users);
//        //普通循环
//        for (int i = 0; i < users.size(); i++) {
//            if ("2".equals(users.get(i).getSex())) {
//                users.remove(i);
//                //问题：若是删除，数组长度-1，eg.第0个元素被删后，循环下一个是1，实际指向了原数组的第2个元素，也就是删掉的下一个元素被删掉了
//                //优化：删除替换为users.remove(i--);在移除后将下一个减回来
//            }
//        }
//        //增强型for循环
//        for (User user : users) {
//            if ("2".equals(user.getSex())) {
//                users.remove(user);//ConcurrentModificationException
//                //break;
//                //问题：ConcurrentModificationException，在移除元素后下次循环会报错
//                //优化：移除后break跳出循环，意味着只能删除一个元素
//            }
//        }
        //迭代器
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            if ("2".equals(iterator.next().getSex())) {
                iterator.remove();
                //非多线程下建议使用iterator
            }
        }

        System.out.println(users);
    }

    /**
     * 去除list中的重复数据
     */
    static void removeRepeat() {
//        int[] nums = {1, 2, 4, 6, 3, 4, 4, 2, 7, -1, -1};//ClassCastException: [I cannot be cast to java.lang.Integer。直接使用int数组在list循环时会报错
        Integer[] nums = {1, 2, 4, 6, 3, 4, 4, 2, 7, -1, -1};
        List<Integer> list = new ArrayList(Arrays.asList(nums));
        //1.jdk8之后的stream
        list.stream().distinct().sorted().forEach(System.out::println);
        //2.利用Set的不重复性,HashSet具有无序性，不能保证list的顺序，所以采用LinkedHashSet
        LinkedHashSet set = new LinkedHashSet();
        for (Integer i : list) {
            set.add(i);
        }
        System.out.println(set);
        //3.利用list的contain
        List<Integer> result = new ArrayList<>();
        for (Integer i : list) {
            if (!result.contains(i)) {
                result.add(i);
            }
        }
        System.out.println(result);
    }

    private static void cloneList(){
        String[] array = {"A","B","C","D"};
        String[] array1 = {"E","F","C","D"};
        ArrayList<String> list = new ArrayList<>(Arrays.asList(array));
        ArrayList<String> list_clone = new ArrayList<>(list);
        ArrayList<String> list1 = new ArrayList<>(Arrays.asList(array1));
        list.removeAll(list1);
        System.out.println(list);
        System.out.println(list_clone);
    }
    
    private static void splitData() {
    	HashMap<String, Object> mibs = new HashMap<String, Object>();
		mibs.put("MIB_SYS_TIME", "2022-04-11 22:34:23");
		mibs.put("MIB_SYS_TIME_SOURCE", 0);
		mibs.put("MIB_SYS_NTP_SERVER_ADDR", "127.0.0.1");
		mibs.put("MIB_SYS_TIMEZONE", "UTC");
		mibs.put("MIB_SYS_VERIFY", "123456");
		mibs.put("MIB_MONITOR_SN", "MS000001");
		mibs.put("MIB_MONITOR_HV", "v12.343.33");
		mibs.put("MIB_MON_TEMP_OFFSET", 25);
		String fixedString = "INSTRUCT_GET#SN00001#123456#0#1#";
		ArrayList<String> list = new ArrayList<>(mibs.keySet());
		int size = list.size();
//		if() {
//			
//		}
    }

    /**
     *
     */
    private static void listToArray() {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        String[] array = list.toArray(new String[1]);
        System.out.println(Arrays.asList(array));
    }

    public static void main(String[] args) {
        // listSort1();
        //listSort2();
//        listRemove();
//        removeRepeat();
//        cloneList();
        listToArray();
    }
}