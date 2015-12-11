package com.jweb.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jweb.beans.Client;
import com.jweb.mail.SendMail;


public class SendNewsletter extends HttpServlet {
	
	private static final String ADMIN = "/WEB-INF/admin.jsp";
	private SendMail mail = new SendMail();

	public void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		Client c = (Client) session.getAttribute("sessionUser");
		
		String sender = c.getName();
		List<String> cls = (List<String>) session.getAttribute("messages");
		String msg = request.getParameter("news");
		
		mail.send(request, cls, sender, msg);
		
		this.getServletContext().getRequestDispatcher(ADMIN).forward(request, response);
	}
}
