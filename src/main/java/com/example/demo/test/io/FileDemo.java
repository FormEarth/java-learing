package com.example.demo.test.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;

import com.example.demo.test.entity.FileSource;

public class FileDemo {

	static String PATH = "E:\\qiyuan_work\\files\\desktop_wallpaper";

	public static void main(String[] args) throws IOException {
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
				fs.setLastModifiedTime(new Date(attributes.lastModifiedTime().toMillis()));
				fs.setSize(temp.length());
				System.out.println(fs);
			}
		}
	}
}
