<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/css/denied.css">
		<title>Access denied!</title>
	</head>
	<body>
		<a id="title" href="${pageContext.request.contextPath}/">Archive Documents</a>
		<div>
			<h1>HTTP Error 404: Not Found</h1>
			<h2>Access denied!</h2>
			
			<h3>Можливі причини того, що вибачите дану сторінку:</h3>
			<ul>
				<li>Ви ввели адресу, що не є дійсною.</li>
				<li>Ви намагались перейти на сторіку, до якої не маєте дозволу. 
					В такому випадку зайдіть під акаунтом з відповідними правами доступу до даного ресурсу. </li>
			</ul>
			<h3>Щоб повернутись на домашню сторінку - натисніть на панель <span>Archive Documents</span> що знаходиться вгорі сторінки,<br>
		   		або ж перейдіть по наступному посиланні:</h3>
		   	<a href="${pageContext.request.contextPath}/">Вернутись на домашню сторінку</a>
		</div>
	</body>
</html>