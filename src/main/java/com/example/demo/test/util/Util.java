package com.example.demo.test.util;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;

public class Util {

    public static final ObjectMapper MAPPER = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

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
     * @return 是否最终执行成功
     * @throws Exception 各种异常
     */
    public static boolean shellExecute(File directory, String... commands) throws Exception {

        ProcessBuilder processBuilder = new ProcessBuilder(commands);
        //使用cd无法切换目录，指定命令执行路径
        if (directory != null && directory.exists() && directory.isDirectory()) {
            processBuilder.directory(directory);
        }
        // 不重定向错误输出
        processBuilder.redirectErrorStream(false);
        Process process = processBuilder.start();
        // windows默认编码格式为GBK
        BufferedReader outReader = new BufferedReader(new InputStreamReader(process.getInputStream(), "utf-8"));
        //阻塞一直到命令执行完毕
        //指定阻塞时间，不然会一直在这
        process.waitFor(2, TimeUnit.SECONDS);
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = outReader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        System.out.println(sb.toString());
        sb.setLength(0);
        //命令执行失败
        if (process.exitValue() == 0) {
            return true;
        } else {
            // 错误输出
            BufferedReader errReader = new BufferedReader(new InputStreamReader(process.getErrorStream(), "utf-8"));
            while ((line = errReader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            System.out.print("command execute failed!path is:" + directory.toString() + ",commands:" + Arrays.asList(commands));
            throw new Exception("COMMAND_EXECUTE_FAILED");
        }
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
        return "\n" + sw.toString();
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
        for (int i = 0; i < nameLength - 1; i++) {
            String str = nameChar[random.nextInt(20)];
            //不允许出现连续重复
            while (str == flag) {
                str = nameChar[random.nextInt(20)];
            }
            name += str;
            flag = str;
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
        if (isEmpty(str)) {
            return str;
        }
        char first = str.charAt(0);
        str.intern();
        String upperChar = str.substring(0, 1).toUpperCase();
        String left = str.substring(1);
        return upperChar + left;
    }

    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
    }

    public static String toJsonStr(Object object) {
        if(object == null || object instanceof CharSequence) {
            return "";
        }
        try {
            return MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 打印一个reader
     *
     * @param reader
     * @throws IOException
     */
    public static void printReader(Reader reader) throws IOException {

        if (reader == null) {
            return;
        }
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
    }

    private static final long BYTE_SIZE = 1024L;
    public String size_format(long size) {
        if (size >= 0) {
            if (size < BYTE_SIZE) {
                return size + "B";
            } else if (size < Math.pow(BYTE_SIZE, 2)) {
                return (size / BYTE_SIZE) + "K";
            } else if (size < Math.pow(BYTE_SIZE, 3)) {
                return (size / Math.pow(BYTE_SIZE, 2)) + "M";
            } else if (size < Math.pow(BYTE_SIZE, 4)) {
                return (size / Math.pow(BYTE_SIZE, 3)) + "G";
            } else {
                return (size / Math.pow(BYTE_SIZE, 4)) + "T";
            }
        } else {
            return "0";
        }
    }

    public static void main(String[] args) {
        System.out.println(randomName());
    }
}