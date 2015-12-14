<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link type="text/css" rel="stylesheet" href="<c:url value='inc/form.css'/>" />
		<title>Administration interface</title>
	</head>
	<body>
		<c:import url="/inc/menu.jsp" />
		
		<p>You are connected as ${ sessionScope.sessionUser.name }</p>
		
		<c:forEach items="${ sessionScope.messages }" var="message" varStatus="boucle">
		<form method="post" action="deletion">
        <p>${ boucle.count }. ${ message }</p>
        <input type="hidden" name="client" value=${ message }>
        <p><input type="submit" value="delete" class="sansLabel"/></p>
        </form>
        </c:forEach>
        
        <br/>
        
        <fieldset>
        	<legend>Send a newsletter</legend>
			<form method="post" action="newsletter">
				<label for="news">Text<span class="requis">*</span></label>
        		<p><input type="text" name="news" value="" /></p>
        		<p><input type="submit" value="send" class="sansLabel" /></p>
        		<p>${ returnSend }</p>
        	</form>
        </fieldset>
        
        <fieldset>
        	<legend>Add a news</legend>
        	<form method="post" action="addNews">
        	
        	<label for="title">Title<span class="requis">*</span></label>
			<input type="text" id="title" name="title" value="" size="20" maxlength="20" />
			<span class="error">${ newsForm.errors['title'] }</span>	
			<br />
			
		  	<label for="body">Body<span class="requis">*</span></label>
			<input type="text" id="body" name="body" value="" size="20"/>
			<span class="error">${ newsForm.errors['body'] }</span>	
			<br />
			
			<input type="submit" value="Register" class="sansLabel" />
			<br />
			
			<p class="${empty newsForm.errors ? 'success' : 'error'}">${ newsForm.result }</p>
        	
        	</form>
        </fieldset>
	</body>
</html>