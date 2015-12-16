<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link type="text/css" rel="stylesheet" href="<c:url value='inc/form.css'/>" />
		<link type="text/css" rel="stylesheet" href="<c:url value='inc/style.css'/>" />
		<title>Administration interface</title>
	</head>
	<body>
		<c:import url="/inc/menu.jsp" />
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<div class="myDiv">
		<p><h3>You are connected as ${ sessionScope.sessionUser.name }</h3></p>
		
		<c:forEach items="${ sessionScope.messages }" var="message" varStatus="boucle">
		<form method="post" action="deletion">
        <p>${ boucle.count }. ${ message }</p>
        <input type="hidden" name="client" value=${ message }>
        <p><input type="submit" value="delete" class="sansLabel"/></p>
        </form>
        </c:forEach>
        
        <br/>
        
        <fieldset>
        	<legend class="myLeg">Send a newsletter</legend>
			<form method="post" action="newsletter">
				<label for="news">Text<span class="requis">*</span></label>
        		<p><textarea name="news" rows="25" cols="80"></textarea></p>
        		<p><input type="submit" value="send" class="sansLabel" /></p>
        		<p>${ returnSend }</p>
        	</form>
        </fieldset>
        
        <fieldset>
        	<legend class="myLeg">Add a news</legend>
        	<form method="post" action="addNews">
        	
        	<label for="title">Title<span class="requis">*</span></label>
			<input type="text" id="title" name="title" value="" size="20" maxlength="20" />
			<span class="error">${ newsForm.errors['title'] }</span>	
			<br />
			
		  	
			<textarea id="body" name="body" rows="25" cols="80"></textarea>
			<span class="error">${ newsForm.errors['body'] }</span>	
			<br />
			
			<input type="submit" value="Register" class="sansLabel" />
			<br />
			
			<p class="${empty newsForm.errors ? 'success' : 'error'}">${ newsForm.result }</p>
        	
        	</form>
        </fieldset>
        </div>
	</body>
</html>