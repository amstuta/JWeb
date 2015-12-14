package com.jweb.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jweb.beans.News;
import com.jweb.db.MySQL;


public class NewsForm {

	private static final String TITLE = "title";
	private static final String BODY = "body";
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
	
	public void check(HttpServletRequest request) {
		String title = getValue(request, TITLE);
		String body = getValue(request, BODY);
		
		News c = new News();
		
		try {
			validateTitle(title);
		}
		catch (Exception e) {
			setError(TITLE, e.getMessage());
		}
		c.setTitle(title);
		
		try {
			validateBody(body);
		}
		catch (Exception e) {
			setError(BODY, e.getMessage());
		}
		c.setBody(body);
		
		if (errors.isEmpty()) {
			result = "News added succesfully";
			db.registerNews(c);
		}
		else {
			result = "Failed to add news";
		}
	}
	
	private void validateTitle(String user) throws Exception {
		if (user == null || user.length() < 5)
			throw new Exception("Invalid title");
	}
	
	private void validateBody(String comment) throws Exception {
		if (comment == null || comment.isEmpty() || comment.length() < 11)
			throw new Exception("Body size must be > 10");
	}
	
	private void setError(String key, String err) {
		errors.put(key, err);
	}
	
	private static String getValue(HttpServletRequest request, String key) {
		return request.getParameter(key);
	}
}
