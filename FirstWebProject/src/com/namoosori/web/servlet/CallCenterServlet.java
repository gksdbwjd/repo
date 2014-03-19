package com.namoosori.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/callcenter")
public class CallCenterServlet extends HttpServlet{

	private static final long serialVersionUID = 5138627514061841189L;

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
		String type = req.getParameter("type");
		if ("1".equals(type)) {
			// redirect 요금 서블릿
			//
//			resp.sendRedirect("fee");	// 파라미터가 전달되지 않기 때문에 보낼 직접 소스에 작성주어야 함.
			String juminNo = req.getParameter("juminNo");
			resp.sendRedirect("fee" + "?juminNo=" + juminNo);
			
		} else if ("2".equals(type)) {
			// forward 해지 서블릿
			RequestDispatcher dispatcher = req.getRequestDispatcher("stop");
			dispatcher.forward(req, resp);
			
			// redirect 해지 서블릿			
//			resp.sendRedirect("stop");
		} else {
		
			resp.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = resp.getWriter();
			
			writer.println("<html>");
			writer.println("<head>");
			writer.println("<title>CallCenter</title>");
			writer.println("</head>");
			writer.println("<body>");
			writer.println("<h1>알수 없는 요청입니다.</h1>");
			writer.println("</body>");
			writer.println("</html>");
		}
	}
}
