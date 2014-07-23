<%@ page import="com.alagezia37.archivedocuments.services.UsersService" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	

<!DOCTYPE html>
<html>
	<head>
		<title>Регістрація</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/registration.css"/>
		
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/scripts/jquery.js"></script>
		<script type="text/javascript">
			function onLoad() {
				$("#password_input").keyup(checkPasswordsMatch);
				$("#password_verify_input").keyup(checkPasswordsMatch);
				$("#form").submit(canSubmit);
			}
			
			function checkPasswordsMatch() {
				var password = $("#password_input").val();
				var password_verify = $("#password_verify_input").val();
				
				if (password.length > 7 || password_verify.length > 7) {
					if (password == password_verify) {
						$("#match_passwords_message").text("<fmt:message key='MatchedPasswords.user.password' />");
						$("#match_passwords_message").addClass("valid");
						$("#match_passwords_message").removeClass("error");
					}  else {
						$("#match_passwords_message").text("<fmt:message key='UnmatchedPasswords.user.password' />");
						$("#match_passwords_message").removeClass("valid");
						$("#match_passwords_message").addClass("error");
					}
				}
			}
			
			function canSubmit() {
				var password = $("#password_input").val();
				var password_verify = $("#password_verify_input").val();
				
				if (password != password_verify) {
					$("#match_passwords_message").text("<fmt:message key='UnmatchedPasswords.user.password'/>");
					$("#match_passwords_message").removeClass("valid");
					$("#match_passwords_message").addClass("error");
					return false;
				} else {
					return true;
				}
			}
			
			$(document).ready(onLoad);
		</script>
	</head>
	
	<body onload="document.form.name.focus();">
		<a id="title" href="${pageContext.request.contextPath}/">Archive Documents</a>
		
		<sf:form id="form" name="form" action="${pageContext.request.contextPath}/registrationverify" method="post" commandName="user">
			<table>
				<tr>
					<td class="data">Ім'я:</td>
					<td class="input"><sf:input name="name" path="name" type="text" />
					<br/><span class="error"><sf:errors path="name" /></span></td>
				</tr>
				<tr>
					<td class="data">Прізвище:</td>
					<td class="input"><sf:input name="surname" path="surname" type="text" />
					<br/><span class="error"><sf:errors path="surname" /></span></td>
				</tr>
				<tr>
					<td class="data">Дані паспорта:</td>
					<td class="input"><sf:input name="passport" path="passport" type="text" />
					<br/><span class="error"><sf:errors path="passport" /></span></td>
				</tr>
				<tr>
					<td class="data">Пароль:</td>
					<td class="input"><sf:input id="password_input" name="password" path="password" type="password" />
					<br/><span class="error"><sf:errors path="password" /></span></td>
				</tr>
				<tr>
					<td class="data">Підтвердіть пароль:</td>
					<td class="input"><input id="password_verify_input" name="password_verify" type="password" />
					<span id="match_passwords_message"></span></td>
				</tr>
				<tr>
					<td><input value="Підтвердити" type="submit" /></td>
				</tr>
			</table>
		</sf:form>
		
		<a href="${pageContext.request.contextPath}/login">
			<input id="login_button" type="button" value="Натисніть якщо вже регіструвались" />
		</a>
		<img id="egyptian-01" src="${pageContext.request.contextPath}/static/images/egyptian-01.png">
		<img id="egyptian-02" src="${pageContext.request.contextPath}/static/images/egyptian-02.png">
	</body>
</html>