package com.example.demo.test.servlet;

public interface OrderMapper {
	// 查
	public int getGoodsNum(long goodsId);
	// 改
	public int reduceGoodsNum(long goodsId);
}
