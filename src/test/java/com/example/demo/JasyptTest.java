package com.example.demo;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.ulisesbocchio.jasyptspringboot.encryptor.DefaultLazyEncryptor;

/**
 * @author cy.W
 * @date 2022-5-7 14:04:36
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class JasyptTest {

	@Autowired
	private StringEncryptor stringEncryptor;

	BasicTextEncryptor textEncryptor = new BasicTextEncryptor();;

//	@Test
	public void test() {
		String username = stringEncryptor.encrypt("root");
		System.out.println("加密的值：" + username);
		System.out.println("解密的值：" + stringEncryptor.decrypt(username));
		String password = stringEncryptor.encrypt("Jason0323.");
		System.out.println("加密的值：" + password);
	}

	@Test
	public void testEnvironmentProperties() {

	}

	public static void main(String[] args) {
		System.setProperty("jasypt.encryptor.password", "HELIOS2022OMC122320");
		StringEncryptor stringEncryptor = new DefaultLazyEncryptor(new StandardEnvironment());
		// 解密方法
		// System.out.println(stringEncryptor.decrypt("U9mv0HcRDCc5DGPkZDlmMHXffdtfHzPgEYMTydoie0EbU/+ySnwFbjW7lF9GrBBM"));
		System.out.println("heliosmon" + "-" + stringEncryptor.encrypt("heliosmon"));
		System.out.println("0C3LMY23" + "-" + stringEncryptor.encrypt("0C3LMY23"));
		System.out.println("heliosmon" + "-" + stringEncryptor.encrypt("heliosmon"));
		System.out.println("3A7Q9644" + "-" + stringEncryptor.encrypt("3A7Q9644"));
		System.out.println("upgrader" + "-" + stringEncryptor.encrypt("upgrader"));
		System.out.println("upgrader12345" + "-" + stringEncryptor.encrypt("upgrader12345"));
	}
}
