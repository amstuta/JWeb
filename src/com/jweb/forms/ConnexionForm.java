package com.jweb.forms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.jweb.beans.Client;
import com.jweb.db.MySQL;


public class ConnexionForm {
	
	private static final String USER = "user";
	private static final String PASSWD = "passwd";
	private static final String EMPTY= "";
	private MySQL db = new MySQL();
	
	private String result;
	private Map<String, String> errors = new HashMap<String, String>();
	
	public String getResult() {
		return result;
	}
	
	public void setResult(String res) {
		result = res;
	}
	
	public Map<String, String> getErrors() {
		return errors;
	}
	
	public Client connect(HttpServletRequest request) {
		String user = getValue(request, USER);
		String passwd = getValue(request, PASSWD);
		
		Client c = new Client();
		
		try {
			validateUser(user);
		}
		catch (Exception e) {
			setError(USER, e.getMessage());
		}
		c.setName(user);
		
		try {
			validatePassword(passwd);
		}
		catch (Exception e) {
			setError(PASSWD, e.getMessage());
		}
		c.setEmail(passwd);
		
		if (errors.isEmpty()) {
			result = "Connected";
			
			HttpSession session = request.getSession();
			boolean connectionAccepted = db.auth(c);
			
			if (!connectionAccepted) {
				setError(EMPTY, "");
				result = "Wrong login or password";
				session.setAttribute("sessionUser", null);
			}
			else {
				List<String> msgs = db.getClients();
				session.setAttribute("messages", msgs);
			}
			
		}
		else {
			result = "Error: connection failed";
		}
		
		return c;
	}
	
	private void validateUser(String user) throws Exception {
		if (user == null || user.isEmpty())
			throw new Exception("Invalid user");
	}
	
	private void validatePassword(String passwd) throws Exception {
		if (passwd == null || passwd.isEmpty() || passwd.length() < 5)
			throw new Exception("Password size must be >= 5");
	}
	
	private void setError(String key, String err) {
		errors.put(key, err);
	}
	
	private static String getValue(HttpServletRequest request, String key) {
		return request.getParameter(key);
	}
}
