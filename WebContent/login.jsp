<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Log in administration interface</title>
		<link type="text/css" rel="stylesheet" href="inc/form.css" />
	</head>
	<body>
		
		<%@ include file="../inc/menu.jsp" %>
	
		<form method="post" action="login">
			<fieldset>
				<legend>Connection</legend>
				
				<label for="user">User name<span class="requis">*</span></label>
				<input type="text" id="user" name="user" value="" size="20" maxlength="20" />
				<span class="error">${ form.errors['user'] }</span>
				<br />
				
				<label for="passwd">Password<span class="requis">*</span></label>
				<input type="password" id="passwd" name="passwd" value="" size="20" maxlength="20" />
				<span class="error">${ form.errors['passwd'] }</span>
				<br />
				
				<input type="submit" value="Log in" class="sansLabel" />
				<br />
				
				<p class="${empty form.errors ? 'success' : 'error'}">${ form.result }</p>
				
			</fieldset>
		</form>
	</body>
</html>