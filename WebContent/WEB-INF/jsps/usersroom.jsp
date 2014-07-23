<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/css/main.css">
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/css/usersroom.css">
		<title>Персональна сторінка</title>
	</head>
	<body>
		<a id="title" href="${pageContext.request.contextPath}/">Archive Documents</a>
		<div id="info">
			Персональна інформація про користувача
			<a href="<c:url value='/j_spring_security_logout' />">Вийти з персональної сторінки</a>
		</div>
		<div id="documents">Доступні документи</div>
		<div id=gallery>Галерея з можливістю перегляду фото документів</div>
	</body>
</html>