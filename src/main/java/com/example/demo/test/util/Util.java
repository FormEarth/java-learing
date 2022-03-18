package com.example.demo.test.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.util.StringUtils;

public class Util {

    /**
     * 根据生日获取年龄
     *
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static int getAge(String dateStr) {

        if (dateStr == null) {
            return 0;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // 是否在之前

        Calendar calendar = Calendar.getInstance();
        int nowYear = calendar.get(Calendar.YEAR);
        int nowMonth = calendar.get(Calendar.MONTH) + 1;
        int nowDay = calendar.get(Calendar.DAY_OF_MONTH);

        calendar.setTime(date);
        int birthYear = calendar.get(Calendar.YEAR);
        int birthMonth = calendar.get(Calendar.MONTH) + 1;
        int birthDay = calendar.get(Calendar.DAY_OF_MONTH);

        int age = nowYear - birthYear;
        // 没有过生日减去一岁
        if (nowYear > birthYear) {
            if (nowMonth < birthMonth) {
                age--;
            } else if (nowMonth == birthMonth) {
                if (nowDay > birthDay) {
                    age--;
                }
            }
        }

        return age;
    }

    /**
     * 在指定路径上执行shell命令
     *
     * @param commands 命令（多条）， eg.<br>
     *                 windows: "cmd", "/c", "hexo generate"<br>
     *                 Linux: "sh", "/c", "hexo generate"
     * @return
     * @throws
     */
    public static boolean shellExecute( String... commands) throws Exception {
        ProcessBuilder processBuilder = new ProcessBuilder(commands);
        //使用cd无法切换目录，指定命令执行路径
        String path = "";
        File file = new File(path);
        processBuilder.directory(file);
        Process p = processBuilder.start();
        InputStream in = p.getInputStream();
        // windows默认编码格式为GBK
        BufferedReader bs = new BufferedReader(new InputStreamReader(in, "utf-8"));
        //阻塞一直到命令执行完毕
        //指定阻塞时间，不然会一直在这
        p.waitFor(2, TimeUnit.SECONDS);
//        StringBuffer buffer = new StringBuffer();
        List<String> list = new ArrayList<>();
        while (bs.read() > 0) {
//            String str = ;
//            buffer.append(bs.readLine()).append("\n");
            list.add(bs.readLine());
        }
        //命令执行失败
        if (p.exitValue() != 0) {
            System.out.print("command execute failed!path is:"+path+",commands:"+Arrays.asList(commands) );
            throw new Exception("COMMAND_EXECUTE_FAILED");
        }
        return true;
    }

    /**
     * 打印异常堆栈信息
     *
     * @param ex
     * @return
     */
    public static String getStackTraceString(Throwable ex) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        return "\n"+sw.toString();
    }




    public static String randomName() {
        Random random = new Random();
        //名字长度
        int nameLength = 2 + random.nextInt(3);
        String[] lastName = {"赵", "孙", "李", "周", "吴", "王", "陈", "冯", "白", "方"};
        String[] nameChar = {"春", "天", "宁", "浩", "源", "梦", "雨", "利", "白", "天", "锦", "威", "启", "昌", "铭", "维", "义", "宗", "英", "离"};
        String name = lastName[random.nextInt(10)];
        //上一个出现名字中的字
        String flag = "";
        for (int i = 0; i < nameLength-1; i++) {
            String str = nameChar[random.nextInt(20)];
            //不允许出现连续重复
            while (str==flag){
                str = nameChar[random.nextInt(20)];
            }
            name += str;
            flag =str;
        }
        return name;
    }
    
    static boolean isEmpty(String str) {
    	return (str == null || str.isEmpty());
    }
    
    /**
     * 将首字母大写
     *
     * @param str
     * @return
     */
    public static String firstUpperCase(String str) {
    	if(isEmpty(str)) {
    		return str;
    	}
    	char first = str.charAt(0);
    	str.intern();
        String upperChar = str.substring(0, 1).toUpperCase();
        String left = str.substring(1);
        return upperChar + left;
    }

    public static void main(String[] args) {
        System.out.println(randomName());
    }
}