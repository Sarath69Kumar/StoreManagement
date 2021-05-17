<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Upload To DB</title>
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_menu.jsp"></jsp:include>
		<h3>Upload Files To DB</h3>
		<div style="padding:5px; color:red; font-style:italic;">
			${errorMessage}
		</div>
		<h2>Saves the file to S directory</h2>
		
		<form method="post" action="${pageContext.request.contextPath}/uploadToDB"
		enctype="multipart/form-data">
		
		Select file to upload to DB
		<br/>
		<input type="file" name="file" />
		<br/>
		<input type="file" name="file" />
		<br/>
		Description:
		<br/>
		<input type="text" name="description" size="100" />
		<br/>
		<br/>
		<input type="submit" value="Upload" />
	</form>

</body>
</html>