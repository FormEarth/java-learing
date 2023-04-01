package com.example.demo.test.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;

import org.apache.ibatis.session.SqlSession;

import com.example.demo.test.servlet.DBUtil;

/**
 * 解析文件写入数据库
 * 
 * @author cy.W
 * @date 2022-5-30 14:58:27
 *
 */
public class FileToDatabase {

	// 保存所有mib名称
	static HashSet<String> NAMES = new HashSet<>();
	static HashMap<String, Object> MAP = new HashMap<String, Object>();

	public static void main(String[] args) throws IOException {

		SqlSession sqlsession = DBUtil.createSession();
		ObjectInterface object = sqlsession.getMapper(ObjectInterface.class);
		File file = new File("C:\\Users\\Administrator\\Desktop\\new_mib.txt");
		if (file.exists()) {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			if (reader.ready()) {
				String line;
				String currentType = "";
				int sort = 0;
				while ((line = reader.readLine()) != null) {
					String[] array = new String(line.getBytes(), StandardCharsets.UTF_8).split("\t");
//					System.out.println(array.length + " " + line);
					// 第一个没有代表是分类
					if (array.length < 8) {
						System.out.println("==========" + array[0]);
						currentType = array[0];
						sort = 0;
						continue;
					} else {
						// 0：中文名 1： 英文名 2：mib 6：RW
						if (NAMES.add(array[2])) {
							sort++;
							System.out.printf("%s %s %s %s%n", array[0], array[1], array[2], array[6]);

							ProductAttrProperty property = new ProductAttrProperty();
							property.setProductId("a73cc0275e044d08aedb090f10160271");
							property.setActionDict(currentType);
							property.setDirectoryId(999L);
							property.setIsReadOnly("R".equals(array[6]) ? 1 : 0);
							property.setPropertyType("input");
							property.setAttrProperty(array[2]);
							property.setAttrPropertyWeb(array[0]);
							property.setRemark(array[1]);
							property.setPropertyOrder(sort);
							if ("String".equalsIgnoreCase(array[5])) {
								property.setPropertyLength(Integer.valueOf(array[7]));
							}
							System.out.println(property);
							object.insert(property);
						}
					}
				}
			}
			reader.close();
		}
		sqlsession.commit();
	}

}
