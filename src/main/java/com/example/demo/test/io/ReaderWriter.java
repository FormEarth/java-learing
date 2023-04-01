package com.example.demo.test.io;

import org.apache.commons.io.FileUtils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public class ReaderWriter {

	static File file_gbk = new File("E:\\qiyuan_work\\files\\test\\test.gbk.txt");
	static File file_utf8 = new File("E:\\qiyuan_work\\files\\test\\test.utf8.txt");
	static File file_null = new File("E:\\qiyuan_work\\files\\test\\test.null.txt");
	// private Reader reader;

	void fileReader() throws IOException {
		// FileReader extens InputStreamReader
		FileReader reader = new FileReader(file_gbk);
		int temp = 0;
		int len = 0;
		char[] c = new char[1024];
		while ((temp = reader.read()) != -1) {
			c[len] = (char) temp;
			len++;
		}
		reader.close();
		System.out.println(String.valueOf(c).replaceAll("\n", "|").replaceAll("\r", "|"));
	}

	void bufferedReader() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file_gbk));
		if (reader.ready()) {
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		}
		reader.close();
	}

	void tyy() throws IOException {
		List<String> list = FileUtils.readLines(file_utf8, StandardCharsets.UTF_8);
	}
	
	void bufferedReader_1() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(file_utf8.toPath()), StandardCharsets.UTF_8));
		if (reader.ready()) {
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		}
		reader.close();
	}

	void inputStreamReader() throws IOException {
		FileInputStream fis = new FileInputStream(file_utf8);
		InputStreamReader reader = new InputStreamReader(fis, "utf-8");
		char[] temp = new char[128];
		reader.read(temp);
		reader.close();
		System.out.println(String.valueOf(temp));
	}

	void fileInputStream() throws IOException {
		FileInputStream fis = new FileInputStream(file_utf8);
		long length = file_gbk.length();
		if (length > Integer.MAX_VALUE) {
			fis.close();
			return;
		}
		byte[] temp = new byte[(int) length];
		fis.read(temp);
		fis.close();
		System.out.println(String.valueOf(temp));
		System.out.println(new String(temp));
	}

	void bufferedInputStream() throws FileNotFoundException {
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file_gbk));
		
	}

	public static void main(String[] args) throws IOException {
		String defaultCharset = Charset.defaultCharset().displayName();
		System.out.println(defaultCharset);
		ReaderWriter rw = new ReaderWriter();
		// rw.fileReader();
		// rw.bufferedReader();
		// rw.inputStreamReader();
//		rw.fileInputStream();
		rw.bufferedReader_1();
	}
}
