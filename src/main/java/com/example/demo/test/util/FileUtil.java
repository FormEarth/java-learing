package com.example.demo.test.util;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author raining_heavily
 * @date 2021年7月11日
 * @time 下午5:08:45
 */
public class FileUtil {
	
	private static int i = 0;

	/**
	 * 
	 * @param path 指定路径
	 */
	public static void checkRepeatedFile(String path) {

		File file = new File(path);
		if (file.isFile()) {
			System.err.println("指定的路径为文件");
			return;
		}
		HashMap<String, List<File>> md5Map = new HashMap<>();
		File[] files = file.listFiles();
		System.out.println("staring check……");
		for (File temp : files) {
			String absolutePath = temp.getAbsolutePath();
			String md5 = "";
			try {
				FileInputStream is = new FileInputStream(absolutePath);
				md5 = DigestUtils.md5Hex(is);
				//这里要及时关闭，否则后面文件删不掉
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (md5Map.containsKey(md5)) {
				List<File> list = md5Map.get(md5);
				list.add(temp);
			} else {
				ArrayList<File> list = new ArrayList<>();
				list.add(temp);
				md5Map.put(md5, list);
			}
		}

		md5Map.forEach((key, value) -> {
			if (value.size() > 1) {
				i++;
				System.out.println(value);
				repeatedFileHandle(value);
			}
		});
		System.out.println(i + "重复项");
	}
	
	/**
	 * 重复文件处理，删掉第一个文件
	 * @param value
	 */
	static void repeatedFileHandle(List<File> value) {
		File repeatedFile = value.get(0);
		System.out.println("first repeatd file:" + repeatedFile.exists());
		if (repeatedFile.delete()) {
			System.out.println("已删除：" + value.get(0) + "\n");
		}
	}

	public static void main(String[] args) {
		checkRepeatedFile("G:\\images\\pixiv");
	}
}
