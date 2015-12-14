package com.jweb.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Restriction extends HttpServlet {

	public static final String HOME = "/login.jsp";
	public static final String ADMIN = "/WEB-INF/admin.jsp";
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		
		if (session.getAttribute("sessionUser") == null) {
			response.sendRedirect(request.getContextPath() + HOME);
		}
		else {
			this.getServletContext().getRequestDispatcher(ADMIN).forward(request, response);
		}
	}
}
