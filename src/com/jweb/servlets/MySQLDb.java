package com.jweb.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jweb.db.MySQL;

public class MySQLDb extends HttpServlet {

	public static final String VUE = "/WEB-INF/affMySQL.jsp";
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		MySQL tmp = new MySQL();
		List<String> msgs = tmp.getClients();
		
		request.setAttribute("messages", msgs);
		
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}
}
