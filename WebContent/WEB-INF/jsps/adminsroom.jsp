<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/css/adminsroom.css">
		<title>Персональна сторінка</title>
	</head>
	<body>
		<a id="title" href="${pageContext.request.contextPath}/">Archive Documents</a>
		<div>
			<h2>Дані користувачів:</h2>
			
				<c:forEach var="row" items="${users}">
					<div class="user_data">
    					Name: ${row.name}<br/>
    					Surname: ${row.surname}<br/>
    					Passport: ${row.passport}<br/>
    				</div>
				</c:forEach>
		</div>
		
		<h2 id="responses">Переглянути відгуки від користувачів:</h2>
		
		<a href="<c:url value='/j_spring_security_logout' />">Вийти з персональної сторінки</a>
	</body>
</html>