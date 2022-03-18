package com.example.demo.test.base;

import java.io.File;
import java.lang.management.ManagementFactory;

import com.sun.management.OperatingSystemMXBean;

import java.text.DecimalFormat;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Qiyuan
 * @date 2022-3-16 14:57:50
 */
@Slf4j
public class SystemInfoDemo {

    private static final transient double GB_BYTE = 1024 * 1024 * 1024.00;
    private static final transient DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##");


    /**
     * 系统信息
     */
    static void showSystemInfo() {
        Properties props = System.getProperties();
        System.out.println("os.name: " + props.getProperty("os.name"));
        System.out.println("os.arch: " + props.getProperty("os.arch"));// 架构
        System.out.println("os.version: " + props.getProperty("os.version"));
        System.out.println("java.version: " + props.getProperty("java.version"));
        System.out.println("java.vendor: " + props.getProperty("java.vendor"));
        System.out.println("java.home: " + props.getProperty("java.home"));
    }

    /**
     * 内存信息
     */
    static void showMemoryInfo() {
        OperatingSystemMXBean mxBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        long total = mxBean.getTotalPhysicalMemorySize();
        long free = mxBean.getFreePhysicalMemorySize();
        long used = total - free;
        log.info("{} {} {} {}%", toGB(total), toGB(used), toGB(free), toPercent(total, used));
    }

    /**
     * 磁盘信息
     */
    static void showDiskInfo() {
        File[] files = File.listRoots();
        long total = 0;
        long used = 0;
        long free = 0;
        for (File file : files) {
            log.info("{} {} {} {}", file.getAbsolutePath(), toGB(file.getTotalSpace()), toGB(file.getUsableSpace()),
                    toGB(file.getTotalSpace() - file.getUsableSpace()));
            total += file.getTotalSpace();
            used += file.getUsableSpace();
            free += file.getTotalSpace() - file.getUsableSpace();
        }
        log.info("{} {} {} {}%", toGB(total), toGB(used), toGB(free), toPercent(total, used));
    }

    private static String toGB(long space) {
        return DECIMAL_FORMAT.format(space / GB_BYTE) + "GB";
    }

    private static String toPercent(long total, long used) {
        return DECIMAL_FORMAT.format(used * 100.00 / total);
    }

    public static void main(String[] args) {

        showSystemInfo();
        showMemoryInfo();
        showDiskInfo();
    }

}
