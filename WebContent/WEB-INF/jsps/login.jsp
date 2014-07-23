<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.alagezia37.archivedocuments.services.UsersService" %>

<!DOCTYPE html>
<html>
	<head>
		<meta content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/login.css">
		<title>Вхід в кабінет</title>
	</head>
	<body onload="document.form.j_username.focus();">
		
		<a id="title" href="${pageContext.request.contextPath}/">Archive Documents</a>
		
		<form name="form" action="${pageContext.request.contextPath}/j_spring_security_check" method="post">
			<table>
				<tr id="passport">
					<td class="data">Дані паспорта:</td>
					<td class="input"><input name="j_username" type="text" value="" /></td>
				</tr>  
				<tr id="password">
					<td class="data">Пароль:</td>
					<td class="input"><input name="j_password" type="password" /></td>
				</tr>
				<tr>
					<td><input id="submit_button" value="Увійти" type="submit" /></td>
					<td>
						<a href="${pageContext.request.contextPath}/registration">
							<input id="registration_button" type="button" value="Натисніть якщо ще не регіструвались" />
						</a>
					</td>
				</tr>  
			</table> 
		</form>
		
		<c:choose>
			<c:when test="${param.error != null }">
				<p>Неправильно введено дані. Спробуйте знову.</p>
				<img id="error_feather" src="${pageContext.request.contextPath}/static/images/error-feather-01.png" width="350" height="350">
				<img id="error_inkstad" src="${pageContext.request.contextPath}/static/images/error-inkstad-01.png" width="500" height="335">
			</c:when>
			<c:otherwise>
				<img id="no_error_inkstad" src="${pageContext.request.contextPath}/static/images/no-error-inkstad-01.png" width="568" height="337">
			</c:otherwise>
		</c:choose>
	</body>
</html>