package com.example.demo.test.servlet;

import com.example.demo.test.util.Util;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class DBUtil {

	/**
	 * 创建一个SqlSession
	 * @return
	 * @throws IOException
	 */
	public static SqlSession createSession() throws IOException {
		//通过配置文件获取数据库连接信息
//		/java-learning/src/main/java/com/example/demo/test/servlet/mybatis-conf.xml
		Reader reader = Resources.getResourceAsReader("com/example/demo/test/servlet/mybatis-conf.xml");
		//通过配置信息构建一个SqlSessionFactory
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		//通过SqlSessionFactory打开一个数据库会话
		SqlSession sqlsession = sqlSessionFactory.openSession();
		return sqlsession;
		}

	public static void main(String[] args) throws IOException {

		Reader reader = Resources.getResourceAsReader("com/example/demo/test/servlet/mybatis-conf.xml");
		Util.printReader(reader);

	}

}
