package com.example.demo.test.entity;

import java.time.LocalDate;

public class Student {

	private String studentNo;
	private String name;
	private boolean isMale;
	private LocalDate birthday;

	public Student(){}

	public Student(String studentNo,String name, boolean isMale, LocalDate birthday) {
		this.studentNo =  studentNo;
		this.name = name;
		this.isMale = isMale;
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "Student{" +
				"studentNo='" + studentNo + '\'' +
				", name='" + name + '\'' +
				", isMale=" + isMale +
				", birthday=" + birthday +
				'}';
	}

	public String getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isMale() {
		return isMale;
	}

	public void setMale(boolean male) {
		isMale = male;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
}
