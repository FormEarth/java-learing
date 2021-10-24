package com.example.demo.test.entity;


import java.util.Objects;

import com.example.demo.test.util.Util;

public class User implements Cloneable{

    /** 用户id */
    private String userId;
    /** 用户名 */
    private String name;
    /** 生日 */
    private String birthday;
    /** 性别 */
    private String sex;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public User() {
    }

    public User(String userId, String name, String birthday, String sex) {
        this.userId = userId;
        this.name = name;
        this.birthday = birthday;
        this.sex = sex;
    }

    /**
     * 获取年龄
     * @return 年龄
     */
    public int getAge() {
		return Util.getAge(this.birthday);
    }
    
    @Override
    public String toString() {
        return "User [birthday=" + birthday + ", name=" + name + ", sex=" + sex + ", userId=" + userId + "]";
    }

	public String getUserId() {
		return userId;
	}

	public User setUserId(String userId) {
		this.userId = userId;
		return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getBirthday() {
        return birthday;
    }

    public User setBirthday(String birthday) {
        this.birthday = birthday;
        return this;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId.equals(user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}

