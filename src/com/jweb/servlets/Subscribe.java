package com.jweb.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jweb.beans.Client;
import com.jweb.forms.SubscribeForm;

public class Subscribe extends HttpServlet {
	
	public static final String VUE = "/WEB-INF/index.jsp";
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException
	{
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException
	{
		SubscribeForm form = new SubscribeForm();
		Client c = form.registerClient(request);
		
		request.setAttribute("form", form);
		request.setAttribute("client", c);
		
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}
}
