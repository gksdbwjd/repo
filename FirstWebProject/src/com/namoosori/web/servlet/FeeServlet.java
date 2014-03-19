package com.namoosori.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/fee")
public class FeeServlet extends HttpServlet{

	private static final long serialVersionUID = 8633148589599535521L;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//
			resp.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = resp.getWriter();
			
			String juminNo = req.getParameter("juminNo");
			
			writer.println("<html>");
			writer.println("<head>");
			writer.println("<title>Fee Information</title>");
			writer.println("</head>");
			writer.println("<body>");
			writer.println("<h1>요금 안내입니다.</h1>");
			writer.println("<p>" + juminNo + "</p>");
			writer.println("</body>");
			writer.println("</html>");
	}
}

