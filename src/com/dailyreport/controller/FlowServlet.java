package com.dailyreport.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FlowServlet
 */
@WebServlet({ "/login", "/staff", "/leader", "/manager", "/director" })
public class FlowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FlowServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();
		System.out.println(path);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String empCode = request.getParameter("employeeCode");
//		String password = request.getParameter("password");
		if ("giaduc".equals(empCode)) {
			request.getRequestDispatcher("default.jsp").forward(request, response);
		} else {
			request.setAttribute("message", "Login failed!");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

}
