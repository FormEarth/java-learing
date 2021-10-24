package com.example.demo.test.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

//import com.alibaba.fastjson.JSONObject;

@WebServlet(name = "orderServlet", urlPatterns = { "/order" } )
public class OrderServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long goodsId = (long) request.getAttribute("goodsId");
		SqlSession sqlsession = DBUtil.createSession();
		OrderMapper order = sqlsession.getMapper(OrderMapper.class);
		String message = "";
		synchronized (this) {
			if (order.getGoodsNum(goodsId) < 1) {
				message = "卖完了";
			}else {
				order.reduceGoodsNum(goodsId);
				//……其它写表
				message = "下单成功";
			}
		} 
		
		// 设置响应头
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		// 设置返回的数据
//		out.write(JSONObject.toJSONString(message));
		// 刷新和关闭输出流
		out.flush();
		out.close();

	}

	class Result {
		public Result(String message) {
			this.message = message;
		}
		String message;
	}

}
