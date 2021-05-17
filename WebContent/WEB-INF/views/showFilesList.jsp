<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DB file List</title>
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_menu.jsp"></jsp:include>
	
	<h3>DB file list</h3>
	
	<p style = "color:red;">${errorString}</p>
	<table border="1" cellpadding="5" cellspacing="1">
		<tr>
			<th>File Id </th>
			<th>File Name</th>
			<th>Description</th>
		</tr>
		<c:forEach items="${dbFileList}" var="attachment">
			<tr>
				<td>
					<a href="getSelectedFile?fileID=${attachment.fileId}">${attachment.fileId}</a>
				</td>
				<td>
					<a href="getSelectedFile?fileID=${attachment.fileId}">${attachment.fileName}</a>
				</td>
				<td>
					<a href="getSelectedFile?fileID=${attachment.fileId}">${attachment.description}</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	
	<a href = "uploadToDB">Upload File To DB</a>
	
	<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>