package com.example.demo.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.entity.User;
import com.example.demo.global.Result;
import com.example.demo.tool.Util;

@WebServlet("/demo/servlet")
public class DemoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		resp.getWriter().println(Util.MAPPER.writeValueAsString(Result.error().setMessage("GET is unsupported")));
		resp.getWriter().flush();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//		String page = req.getParameter("page");
		User user = Util.getBody(req, User.class);
		System.out.println(user);
		resp.getWriter().println(Util.MAPPER.writeValueAsString(Result.success().setData("hello!" + user.getName())));
		resp.getWriter().flush();
	}


}
