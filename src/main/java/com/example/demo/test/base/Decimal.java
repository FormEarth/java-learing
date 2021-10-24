package com.example.demo.test.base;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Decimal {
	
	static void decimalFormat() {
		//取两位整数和四位小数，整数不足部分以0填补。
		DecimalFormat df = new DecimalFormat("00.0000");
		System.out.println(df.format(2.135));	
		
		df.setRoundingMode(RoundingMode.DOWN);
		System.out.println(df.format(BigDecimal.valueOf(2.13115)));
		df.setRoundingMode(RoundingMode.HALF_UP);
		System.out.println(df.format(BigDecimal.valueOf(2.13115)));
		
		DecimalFormat df1 = new DecimalFormat(",###.##");
		System.out.println(df1.format(545794696.975478));
		
	}
	
	static void decimalScale() {
		
		//Note: Never construct BigDecimals from floats or doubles. Construct them from ints or strings. floats and doubles loose precision.
		//ROUND_HALF_UP 四舍五入
		//ROUND_HALF_DOWN 五舍六入
		//ROUND_DOWN 截断，即直接舍弃多余
		
		BigDecimal bd = new BigDecimal(2132.115);
		System.out.println(bd.setScale(2,BigDecimal.ROUND_HALF_UP));//2132.11       这里精度丢失了，舍入错误
		System.out.println(bd.setScale(2,BigDecimal.ROUND_HALF_DOWN));//2132.11
		System.out.println(bd.setScale(2,BigDecimal.ROUND_DOWN) + "\n");//2132.11
		
		BigDecimal bd1 = new BigDecimal(2132.125);
		System.out.println(bd1.setScale(2,BigDecimal.ROUND_HALF_UP));//2132.13
		System.out.println(bd1.setScale(2,BigDecimal.ROUND_HALF_DOWN));//2132.12
		System.out.println(bd1.setScale(2,BigDecimal.ROUND_DOWN) + "\n");//2132.12
		
		BigDecimal bd2 = new BigDecimal("2132.115");
		System.out.println(bd2.setScale(2,BigDecimal.ROUND_HALF_UP));//2132.12         这里使用字符串来创建则没有问题
		System.out.println(bd2.setScale(2,BigDecimal.ROUND_HALF_DOWN));//2132.11
		System.out.println(bd2.setScale(2,BigDecimal.ROUND_DOWN) + "\n");//2132.11
		
		
		BigDecimal bdd = new BigDecimal(-1.235);
		System.out.println(bdd.setScale(2,BigDecimal.ROUND_UP));//向远离0的方向舍入
		
	}

	public static void main(String[] args) {
		decimalFormat();
//		decimalScale();
	}
		

}
