package com.jweb.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jweb.db.MySQL;

public class News extends HttpServlet {

	private static final String VUE = "/WEB-INF/news.jsp";
	private MySQL db = new MySQL();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		List<com.jweb.beans.News> news = db.getLastNews();
		
		if (news != null) {
			request.setAttribute("news", news);	
		}
		
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}
}
