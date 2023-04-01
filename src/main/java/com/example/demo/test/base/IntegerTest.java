package com.example.demo.test.base;

import java.util.Objects;

/**
 * 包装类型
 * @author raining_heavily
 * @date 2019/12/9 23:03
 **/
public class IntegerTest {

    /** Integer的缓存机制 -128~127 **/
    static void integerCache(){
        Integer a = new Integer(10);
        Integer b = new Integer(10);
        Integer c = 10;
        Integer d = 10;
        Integer e = 128;
        Integer f = 128;
        System.out.println(a==b);//false
        System.out.println(c==d);//true
        System.out.println(e==f);//false
    }

    static void string(){
        String a = "Hello";
        String b = "Hello";
        System.out.println(a==b);//true
    }
    
    static void uer(int index, int j) {
    	System.out.println((index-1) * j + 1);
    }
    
    static void swap(int x, int y) {
    	int temp = x;
    	x = y;
    	y = temp;
    	System.out.println(x + "," + y);
    }
    

    public static void main(String[] args) {
//        integerCache();
//        string();
//    	System.out.println((10<<4) + 1);
//    	System.out.println((10<<4) + 8);
//    	int index = 2;
//    	System.out.println(index);
//    	System.out.println((index-1)*16+1);
//    	System.out.println(index*16+1);
//    	uer(2,1);
    	System.out.println((1<<4)+1);
    	
    	for(int i = 1;i<=8;i++) {
			Integer index = 2 -1;
			index =	(index<<4) + i;
			System.out.println(index);
    	}
    	
    	int a = 1, b = 2;
    	swap(a,b);
    	System.out.println(a + "," + b);
    	
    	String indexStr = ((1-1)<<4) + 1 + "";
    	System.out.println(indexStr);

        System.out.println(Objects.equals(new Integer(1), 1));
    }
}
