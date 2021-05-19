<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="header">
	<div style = "float:left">
		<h1>Store Management System</h1>
	</div>
	<div style = "float:right; padding:10px; text-align:right;">
		<!--  User store in session with attribute: loginedUser -->
		Welcome <b>${loginedUser.userName}</b>
		Search <input name = "search">
	</div>
</div>