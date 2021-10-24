package com.example.demo.global;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import com.example.demo.entity.User;

@Component("redisClientTest")
public class RedisClientTest {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@Autowired
	// 默认泛型为<Object,Object>，指定其它对象时需自定义
	private RedisTemplate redisTemplate;

	public void addString() throws Exception {
		stringRedisTemplate.opsForValue().set("user.commom.002", "002", 30, TimeUnit.MINUTES);
	}

	public void addObjectString() throws Exception {
		// 指定key使用String的序列化方式，key、value的序列化方式为jdk自带，若自定义类未实现序列化则会报错
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		ValueOperations<String, User> operations = redisTemplate.opsForValue();

		User user = new User("jack", null, "jack@qq.com");
		String token = getToken();
		operations.set("user.basic.info." + token, user);
		// operations.set("user.basic.info." + token, user, 30, TimeUnit.MINUTES);
		System.out.println("add done");
	}

	public void addHash() throws Exception {
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.opsForHash().putAll("demo.user.token." + getToken(), getMap());
	}

	@Autowired
	private RedisTemplate<String, Object> template;

	public void add_custom() {

		template.opsForValue().set("demo.user.string." + getToken(), getgUser());

		HashOperations<String, Object, Object> operations = template.opsForHash();
		operations.putAll("demo.user.hash." + getToken(), getMap());
		//设置指定key的过期时间
		template.expire("key", 60, TimeUnit.MINUTES);

		template.opsForList().rightPushAll("demo.user.list." + getToken(), getList());

		template.opsForSet().add("demo.user.set." + getToken(), getList());

		// template.opsForZSet().
	}

	String getToken() {
		return UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
	}

	User getgUser() {
		return new User("jack", null, "jack@qq.com");
	}

	Map<String, Object> getMap() {
		HashMap<String, Object> map = new HashMap<>();
		map.put("id", "10010");
		map.put("name", "redis_name");
		map.put("amount", 12.34D);
		map.put("age", 11);
		return map;
	}

	Integer[] getArray() {
		Integer[] array = { 35465657, 35465657, 43565763, 4344444, 2343243, 0 };
		return array;
	}

	List<Integer> getList() {
		return new ArrayList<>(Arrays.asList(getArray()));
	}
	
	static void serializeObject() {
		String string = "hello world";
		OutputStream outputStream = new ByteArrayOutputStream();
		ObjectOutputStream out;
		try {
			out = new ObjectOutputStream(outputStream);
			out.writeObject(string);
			System.out.println(outputStream.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		serializeObject();
	}

}
