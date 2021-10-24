package com.example.demo.test.servlet;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBUtil {

	/**
	 * 创建一个SqlSession
	 * @return
	 * @throws IOException
	 */
	public static SqlSession createSession() throws IOException {
		//通过配置文件获取数据库连接信息
		Reader reader = Resources.getResourceAsReader("com.huel.servlet.mybatis-conf.xml");
		//通过配置信息构建一个SqlSessionFactory
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		//通过SqlSessionFactory打开一个数据库会话
		SqlSession sqlsession = sqlSessionFactory.openSession();
		return sqlsession;
		}

}
