package com.jweb.forms;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.jweb.beans.Client;
import com.jweb.db.MySQL;


public class SubscribeForm {

	private static final String NAME = "name";
	private static final String FIRSTNAME = "firstName";
	private static final String EMAIL = "email";
	private MySQL db = new MySQL();
	
	private String result;
	private Map<String, String> errors = new HashMap<String, String>();
	
	public String getResult() {
		return result;
	}
	
	public Map<String, String> getErrors() {
		return errors;
	}
	
	public void registerClient(HttpServletRequest request) {
		
		String name = getValue(request, NAME);
		String firstName = getValue(request, FIRSTNAME);
		String email = getValue(request, EMAIL);
		
		Client c = new Client();
		
		try {
			validateName(name);
		}
		catch (Exception e) {
			setError(NAME, e.getMessage());
		}
		c.setName(name);
		
		try {
			validateFirstName(firstName);
		}
		catch (Exception e) {
			setError(FIRSTNAME, e.getMessage());
		}
		c.setFirstName(firstName);
		
		try {
			validateEmail(name);
		}
		catch (Exception e) {
			setError(EMAIL, e.getMessage());
		}
		c.setEmail(email);
		
		if (errors.isEmpty()) {
			result = "You succesfully subscribed";
			db.registerClient(c);
		}
		else {
			result = "Failed to subscribe";
		}
		request.setAttribute("client", c);
	}
	
	private void validateName(String name) throws Exception {
		if (name == null || name.length() == 0) {
			throw new Exception("Name is not valid");
		}
	}
	
	private void validateFirstName(String name) throws Exception {
		if (name == null || name.length() == 0) {
			throw new Exception("First name is not valid");
		}
	}
	
	private void validateEmail(String email) throws Exception {
		if (email == null || email.length() == 0) {
			throw new Exception("Email is not valid");
		}
	}
	
	private void setError(String key, String msg) {
		errors.put(key, msg);
	}
	
	private static String getValue(HttpServletRequest request, String key) {
		String val = request.getParameter(key);
		
		if (val == null || val.trim().length() == 0) {
			return null;
		}
		return val.trim();
	}
}
