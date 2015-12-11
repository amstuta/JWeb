package com.jweb.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jweb.beans.Comment;
import com.jweb.db.MySQL;

public class Reviews extends HttpServlet {

	private static final String VUE = "/WEB-INF/comments.jsp";
	private MySQL db = new MySQL();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		//HttpSession session = request.getSession();
		List<Comment> comments = db.getComments();
		
		if (comments != null) {
			request.setAttribute("comments", comments);
		}
		else {
			request.setAttribute("error", "Unable to retrieve comments.");
		}
		
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}
}
