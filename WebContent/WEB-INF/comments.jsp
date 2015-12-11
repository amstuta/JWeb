<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link type="text/css" rel="stylesheet" href="<c:url value='inc/form.css'/>" />
		<title>Reviews</title>
	</head>
	<body>
		<%@ include file="../inc/menu.jsp" %>
	
		<h2>Comments</h2>
		<br/>
		
		<c:forEach items="${ comments }" var="comment" varStatus="boucle">
			<fieldset>
				<legend>${ comment.login }</legend>
       			<p>${ comment.comment }</p>
       		</fieldset>
       	</c:forEach>
       	
       	<p class="${empty error ? 'success' : 'error'}">${ error }</p>
		
		<!-- 
		<form method="post" action="sendReview">
		
		</form>
		 -->
	</body>
</html>