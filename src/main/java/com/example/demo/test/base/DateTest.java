package com.example.demo.test.base;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;


public class DateTest {
	
	private static void date() {
		System.currentTimeMillis();
		LocalDate ld0 = LocalDate.of(2020, 1, 01);
		LocalDate ld = LocalDate.now();
		Period period = Period.between(ld, ld0);
		int days = period.getDays();
		System.out.println(days);
		
	}
	
	static void isSameDate() {
		Date date =  new Date(2021,10,28);
		Date date1 = new Date();
		System.out.println(date);
	}
	
	public static void main(String[] args) {
		date();
		int i = 01;
		System.out.println(i);
		
		
		isSameDate();
	}

}
