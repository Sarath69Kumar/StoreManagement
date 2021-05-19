<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Delete Product</title>
	<link rel="stylesheet" type="text/css" href="CSS/Basic.css">
</head>
<body background = "Images/PlatteRiver_EN-US4569107551_1920x1080.jpg">
	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_menu.jsp"></jsp:include>
	
	<h3>Delete Product</h3>
	
	<p style="color:red;">${errorString}</p>
	<a href="productList">Product List</a>
	
	<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>