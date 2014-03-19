package com.namoosori.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CalculatorServlet extends HttpServlet{

	private static final long serialVersionUID = 6646376876351037906L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//
		doPost(req, resp);
		//
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//
		resp.setContentType("text/html");
		resp.setCharacterEncoding("utf-8");	// UTF-8
		
		String paramNum1 = req.getParameter("num1");
		String paramOperator = req.getParameter("operator");
		String paramNum2 = req.getParameter("num2");
		
		System.out.println(paramNum1);
		System.out.println(paramOperator);
		System.out.println(paramNum2);
		
		int num1 = Integer.parseInt(paramNum1);
		int num2 = Integer.parseInt(paramNum2);
		int result = 0;
		if (paramOperator.equals("+")) {
			result = num1 + num2;
		} else if (paramOperator.equals("-")) {
			result = num1 - num2;
		} else if (paramOperator.equals("*")) {
			result = num1 * num2;
		} else if (paramOperator.equals("/")) {
			result = num1 / num2;
		}
		
		PrintWriter writer = resp.getWriter();
		
		writer.println("<html>");
		writer.println("<head>");
		writer.println("<title>계산결과</title>");
		writer.println("</head>");
		writer.println("<body>");
		//
		writer.println(num1 + paramOperator + num2 + "=" + result);
		
		writer.println("</body>");
		writer.println("</html>");
		
	}
}
