package com.jweb.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.jweb.beans.Client;
import com.jweb.forms.ConnexionForm;

public class Connexion extends HttpServlet {
	
	public static final String VUE = "/WEB-INF/admin.jsp";
	public static final String LOGIN = "/login";
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException
	{
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException
	{
		ConnexionForm cF = new ConnexionForm();
		Client c = cF.connect(request);
		HttpSession session = request.getSession();

		request.setAttribute("form", cF);
		if (cF.getErrors().isEmpty()) {
			session.setAttribute("sessionUser", c);
		}
		else {
			session.setAttribute("sessionUser", null);
			this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		
	}
}
