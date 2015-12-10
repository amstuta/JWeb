<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>JWeb</title>
		<link type="text/css" rel="stylesheet" href="inc/form.css" />
	</head>
	<body>
	
		<%@ include file="../inc/menu.jsp" %>
	
		<form method="post" action="subscribe">
			<fieldset>
				<legend>Subscribe</legend>
				
				<label for="name">Last name<span class="requis">*</span></label>
				<input type="text" id="name" name="name" value="" size="20" maxlength="20" />
				<span class="error">${ form.errors['name'] }</span>
				<br/>
				
				<label for="firstName">First name<span class="requis">*</span></label>
				<input type="text" id="firstName" name="firstName" value="" size="20" maxlength="20" />
				<span class="error">${ form.errors['firstName'] }</span>
				<br/>
				
				<label for="email">Email<span class="requis">*</span></label>
				<input type="text" id="email" name="email" value="" size="20" maxlength="20" />
				<span class="error">${ form.errors['email'] }</span>
				<br/>
				
				<input type="submit" value="Subscribe" class="sansLabel" />
				
				<p class="${empty form.errors ? 'success' : 'error'}">${ form.result }</p>
				
			</fieldset>
		</form>
	</body>
</html>