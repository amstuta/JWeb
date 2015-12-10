package com.jweb.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jweb.beans.Client;
import com.jweb.db.MySQL;


public class DeleteClient extends HttpServlet {
	
	private MySQL db = new MySQL();

	public void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException
	{
		// Delete
		HttpSession session = request.getSession();
		String clientMail = request.getParameter("client");
		
		db.removeClient(clientMail);
		List<String> tmp = (List<String>)session.getAttribute("messages");
		
		session.removeAttribute("messages");
		tmp.remove(clientMail);
		session.setAttribute("messages", tmp);
		
		Client c = (Client)session.getAttribute("sessionUser");
		c.setName(clientMail);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
	}
}
