<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Upload Files</title>
<link rel="stylesheet" type="text/css" href="CSS/Basic.css">
</head>
<body background = "Images/FallasBonfire_EN-US7115616221_1920x1080.jpg">
	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_menu.jsp"></jsp:include>
	<h3>Upload Files</h3>
	
	<div style = "padding:5px; color:red; font-style:italic;">
		${errorMessage}
	</div>
	<h2>Saves the file to tomcat directory</h2>
	
	<form method="post" action="${pageContext.request.contextPath}/uploadFile" enctype="multipart/form-data">
		Select file to upload:				<br/>
		<input type="file" name="file" />	<br/>
		<input type="file" name="file" />	<br/>
		Description							<br/>
		<input type="text" name="description" size="70" />
		<br/>
		<br/>
		<input type="submit" value="Upload" />
	</form>
	<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>