package com.namoosori.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello") // 여러개인 경우는 {"/hello", "/bye", "/goodmorning"} 과 같이 괄호로 감싸주면 됨.
public class HelloWorldServlet extends HttpServlet{

	
	private static final long serialVersionUID = 5742628710116017048L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//post 방식으로 값을 전달하는 경우에 request객체 바디 부분을 통해 값이 전달되기 때문에
		// request 객체의 인코딩을 맞춰주면 한글이 깨지는 문제를 해결할 수 있음. 
		req.setCharacterEncoding("utf-8");
		
		String name = req.getParameter("name");
		
//		resp.setContentType("text/html");
//		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = resp.getWriter();
		
		writer.println("<html>");
		writer.println("");
		writer.println("<head>");
		writer.println("<title>HelloServlet</title>");
		writer.println("</head>");
		writer.println("<body>");
		writer.println("<h1>" + name + " 님</h1>");
		writer.println("<h1> hello~ Servlet!</h1>");
		writer.println("</body>");
		writer.println("</html>");
	}
}
