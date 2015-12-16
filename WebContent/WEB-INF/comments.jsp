<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link type="text/css" rel="stylesheet" href="<c:url value='inc/form.css'/>" />
		<link type="text/css" rel="stylesheet" href="<c:url value='inc/style.css'/>" />
		<title>Reviews</title>
	</head>
	<body>
		<%@ include file="../inc/menu.jsp" %>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<div class="myDiv">
		<fieldset>
		<legend class="myLeg">Comments</legend>
		<br/>
		
		<c:forEach items="${ comments }" var="comment" varStatus="boucle">
			<fieldset>
				<legend id="leg">${ comment.login }</legend>
       			<p>${ comment.comment }</p>
       		</fieldset>
       	</c:forEach>
       	
       	<p class="${empty error ? 'success' : 'error'}">${ error }</p>
       	</fieldset>
		 
		 <fieldset>
		 	<legend class="myLeg">Add a comment</legend>
			<form method="post" action="addReview">
				<label>Name<span class="requis">*</span></label>
				<input type="text" name="name" value="" size="20" />
				<span class="error">${ form.errors['name'] }</span>
				<br/>
				
				<label>Comment<span class="requis">*</span></label>
				<textarea name="comm" rows="10" cols="30"></textarea>
				<span class="error">${ form.errors['comm'] }</span>
				<br/>
				
				<input type="submit" value="Send" class="sansLabel" />
			</form>
			<p class="${empty form.errors ? 'success' : 'error'}">${ form.result }</p>
		</fieldset>
		</div>
	</body>
</html>