<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/css/logoutsuccess.css">
		<title>Insert title here</title>
	</head>
	<body>
		<a id="title" href="${pageContext.request.contextPath}/">Archive Documents</a>
		<div>
			<h3>Ви успішно вийшли із персональної сторінки!</h3>
			<p>Якщо маєте певні побажання чи зауваження щодо роботи сайту - можете залиши відгук і ми переглянемо його якнайшвидше.</p>
			<a href="${pageContext.request.contextPath}/response"><input type="button" value="Залишити відгук"></a>
			<a href="${pageContext.request.contextPath}/"><input type="button" value="Вернутись на домашню сторінку"></a>
		</div>
	</body>
</html>