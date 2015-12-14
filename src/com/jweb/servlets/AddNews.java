package com.jweb.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jweb.db.MySQL;
import com.jweb.forms.NewsForm;

public class AddNews extends HttpServlet {

	private static final String VUE = "/WEB-INF/admin.jsp";
	private MySQL db = new MySQL();
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		NewsForm nF = new NewsForm();
		com.jweb.beans.News n = nF.check(request);
		
		request.setAttribute("newsForm", nF);
		
		if (nF.getErrors().isEmpty()) {
			db.registerNews(n);
		}
		
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}
}
