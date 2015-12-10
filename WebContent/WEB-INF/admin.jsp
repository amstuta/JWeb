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
        <p>${ boucle.count }. ${ message }</p>
        </c:forEach>
	</body>
</html>