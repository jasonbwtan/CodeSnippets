package com.jason;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletDemo1 extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<h1>Hello Servlet Get</h1>");
		out.println("</body>");
		out.println("</html>");
		//response.sendRedirect("http://www.three.co.uk");
		out.println("request headers:"+request.getHeaderNames());
		for(Cookie cookie : request.getCookies()){
			out.println("request cookie:"+cookie.getName()+":"+cookie.getValue());
		}

		//response.sendRedirect("/javax-example");
		response.addHeader("ServletDemo1", "true");
	}
}