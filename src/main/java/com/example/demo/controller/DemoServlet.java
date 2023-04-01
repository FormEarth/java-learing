package com.example.demo.controller;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.entity.User;
import com.example.demo.global.Result;
import com.example.demo.tool.RequestUtil;

@WebServlet("/demo/servlet")
public class DemoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		resp.getWriter().println(RequestUtil.MAPPER.writeValueAsString(Result.error().setMessage("GET is unsupported")));
		resp.getWriter().flush();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//		String page = req.getParameter("page");
		User user = RequestUtil.getBody(req, User.class);
		System.out.println(user);
		Result<String> result = new Result<>();
		resp.getWriter().println(RequestUtil.MAPPER.writeValueAsString(result.setData("hello!" + user.getName())));
		resp.getWriter().flush();
	}


}
