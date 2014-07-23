<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/home.css">
		<title>Archive Documents</title>
	</head>
	<body>
		<a id="title" href="${pageContext.request.contextPath}/">Archive Documents</a>
		
		<div id="bar">
			<sec:authorize access="!isAuthenticated()">
				<ul id="not_authenticated">
					<li><a href="${pageContext.request.contextPath}/login"><input type="button" value="Увійти в систему" /></a></li>
					<li><a href="${pageContext.request.contextPath}/registration"><input type="button" value="Регістрація" /></a></li>
				</ul>
			</sec:authorize>
			<sec:authorize access="isAuthenticated()">
				<ul id="authenticated">
					<li><a href="${pageContext.request.contextPath}/
						<sec:authorize access="hasRole('ROLE_USER')">usersroom</sec:authorize>
						<sec:authorize access="hasRole('ROLE_ADMIN')">adminsroom</sec:authorize>
						"><input type="button" value="До персональної сторінки" /></a></li>
					<li><a href="<c:url value='/j_spring_security_logout' />"><input type="button" value="Вийти з системи" /></a></li>
				</ul>
			</sec:authorize>
			<img id="book" src="${pageContext.request.contextPath}/static/images/book-01.png">
		</div>
	</body>
</html>