<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Login</title>
		<link rel="stylesheet" type="text/css" href="CSS/Basic.css">
		
		<!-- reCAPTCHA with English language -->
		<script src='https://www.google.com/recaptcha/api.js?hl=en'></script>
		<!-- reCAPTCHA with Auto language -->
		<script src='https://www.google.com/recaptcha/api.js'></script>
		
	</head>
	<body background = "Images/WoodPartridge_EN-US11041638655_1920x1080.jpg">
		<jsp:include page="_header.jsp"></jsp:include>
		<jsp:include page="_menu.jsp"></jsp:include>
		
		<h3>Login Page</h3>
		<p style = "color : red;">${errorString}</p>
		
		<form method = "POST" action="${pageContext.request.contextPath}/login">
			<table border = "0">
				<tr>
					<td>User Name</td>
					<td><input type = "text" name = "userName" value = "${user.userName}"/></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="text" name="password" value="${user.password}"/></td>
				</tr>
				<tr>
					<td>Remember Me</td>
					<td><input type ="checkbox" name="rememberMe" value="Y"/></td>
				</tr>
			</table>
			
			<!-- reCAPTCHA -->
			<div class = "g-recaptcha"
				data-sitekey="6LelZAsTAAAAAAv1ADYDnq8AzbmPmbMvjh-xhfgB">
			</div>
			
			
			<input type="submit" value="Submit"/>
			<a href="${pageContext.request.contextPath}/">Cancel</a>
				
			
		</form>
		
		<p style="color:blue;">User Name : sarath555,sarath69 password: jerry/jerry001</p>
		
		<jsp:include page="_footer.jsp"></jsp:include>
	</body>
</html>