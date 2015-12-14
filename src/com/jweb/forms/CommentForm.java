package com.jweb.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jweb.beans.Comment;
import com.jweb.db.MySQL;


public class CommentForm {
	
	private static final String NAME = "name";
	private static final String COMM = "comm";
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
		String name = getValue(request, NAME);
		String comm = getValue(request, COMM);
		
		Comment c = new Comment();
		
		try {
			validateName(name);
		}
		catch (Exception e) {
			setError(NAME, e.getMessage());
		}
		c.setLogin(name);
		
		try {
			validateComment(comm);
		}
		catch (Exception e) {
			setError(COMM, e.getMessage());
		}
		c.setComment(comm);
		
		if (errors.isEmpty()) {
			result = "Comment added succesfully";
			db.registerComment(c);
		}
		else {
			result = "Failed to add comment";
		}
	}
	
	private void validateName(String user) throws Exception {
		if (user == null || user.length() < 5)
			throw new Exception("Invalid name");
	}
	
	private void validateComment(String comment) throws Exception {
		if (comment == null || comment.isEmpty() || comment.length() < 11)
			throw new Exception("Comment size must be > 10");
	}
	
	private void setError(String key, String err) {
		errors.put(key, err);
	}
	
	private static String getValue(HttpServletRequest request, String key) {
		return request.getParameter(key);
	}
}
