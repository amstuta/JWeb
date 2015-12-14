package com.jweb.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jweb.forms.CommentForm;


public class AddReview extends HttpServlet {
	
	private static final String VUE = "/reviews";
	private static final String VIEW = "/WEB-INF/comments.jsp";
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	public void doPost (HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		CommentForm cF = new CommentForm();
		cF.check(request);
		
		request.setAttribute("form", cF);

		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}
}
