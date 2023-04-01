package com.example.demo.test.io;

import com.example.demo.test.entity.FileSource;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.io.FileUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;

public class FileDemo {

	static String PATH = "E:\\qiyuan_work\\files\\desktop_wallpaper";

	static void listFiles() throws IOException {
		File file = new File(PATH);
		if (file.isDirectory()) {

			File[] files = file.listFiles();
			for(File temp : files) {
				BasicFileAttributes attributes = Files.readAttributes(temp.toPath(),BasicFileAttributes.class);
				FileSource fs = new FileSource();
				fs.setOriginalName(temp.getName());
				String name = temp.getName();
				int i = name.lastIndexOf(".");
				if(i != -1) {
					fs.setType(name.substring(i + 1, name.length()).toUpperCase());
				}
				fs.setCreateTime(new Date(attributes.creationTime().toMillis()));
				fs.setLastModifiedTime(new Date(attributes.lastModifiedTime().toMillis())); //修改时间
				fs.setSize(temp.length());
				System.out.println(fs);
			}
		}
	}

	static void saveByte() throws IOException {

		FileWriter fileWriter = new FileWriter("E:\\test\\test.log");
		String str = "fs.setLastModifiedTime(new Date(attributes.lastModifiedTime().toMillis())); //修改时间";
		fileWriter.write(str.toCharArray());
		fileWriter.close();
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

	}

	public static String convertFileSize(long size) {
		String[] units = {"B", "KB", "MB", "GB", "TB"};
		int index = 0;
		BigDecimal bdSize = new BigDecimal(size);
//		bdSize.setScale(2, RoundingMode.HALF_UP);
		BigDecimal bd1024 = new BigDecimal(1024);
		while (bdSize.compareTo(bd1024) > 0 && index < units.length - 1) {
			bdSize = bdSize.divide(bd1024);
			index++;
		}
		return String.format("%.2f %s", bdSize.doubleValue(), units[index]);
	}

	public static void main(String[] args) throws IOException {

//		saveByte();
		System.out.println(FileUtils.byteCountToDisplaySize(6849));

		System.out.println(convertFileSize(6849));
	}

}
