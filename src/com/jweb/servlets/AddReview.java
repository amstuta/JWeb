package com.jweb.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jweb.beans.Comment;
import com.jweb.db.MySQL;
import com.jweb.forms.CommentForm;

public class AddReview extends HttpServlet {
	
	private static final String VUE = "/reviews";
	private MySQL db = new MySQL();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	public void doPost (HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		CommentForm cF = new CommentForm();
		Comment c = cF.check(request);
		
		request.setAttribute("form", cF);
		
		if (cF.getErrors().isEmpty()) {
			db.registerComment(c);
		}
		
		response.sendRedirect(request.getContextPath() + VUE);
		//this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}
}
