<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page</title>
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_menu.jsp"></jsp:include>
	
	<h3>Home Page</h3>
	
	Store Management software helps you find the stock you have. ${pageContext.request.contextPath} <br><br>
	<b> Functions </b>
	<ul>
		<li>Login</li>
		<li>Storing User Info in cookies</li>
		<li>Product List</li>
		<li>Create Product</li>
		<li>Edit Product</li>
		<li>Delete Product</li>
	</ul>
	<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>