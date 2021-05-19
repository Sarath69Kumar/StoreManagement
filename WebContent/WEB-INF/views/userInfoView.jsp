<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
			<title>User Info</title>
			<link rel="stylesheet" type="text/css" href="CSS/Basic.css">
	</head>
<body background = "Images/CommonPipistrelle_EN-US7421359791_1920x1080.jpg">
	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_menu.jsp"></jsp:include>
	
	<h3>Hello: ${user.userName}</h3>
	
	User Name : <b>${user.userName}</b> <br/>
	Gender : ${user.gender} <br/>
	
	<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>