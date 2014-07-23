<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/css/registrationsuccess.css"/>
		<title>Успішна регістрація</title>
	</head>
	<body>
		<a id="title" href="${pageContext.request.contextPath}/">Archive Documents</a>
		<div>
			<h2>Вас успішно зарегістровано!</h2>
			<h3>Тепер Ви можете ввести свої дані що вказали при регістрації та зайти на свою персональну сторінку:</h3>
			<h3><a href="${pageContext.request.contextPath}/login">Увійти в систему</a></h3>
		</div>
	</body>
</html>