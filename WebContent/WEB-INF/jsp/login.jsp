<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String message = (String) request.getAttribute("loginFailure");
%>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title>第10章　ひとこと掲示板</title>
</head>
<body>
<h2>ひとこと掲示板へようこそ</h2>
<hr>
<% if (message != null) { %>
	<p><%= message %></p>
<% } %>
<form action = "./SingleWord" method = "post">
	<input type = "hidden" name = "action" value = "action.LoginLogic" />
	<label for = "userName">ユーザー名：</label>
	<input type = "text" name = "userName" id = "userName" /><br />
	<label for = "password">パスワード：</label>
	<input type = "password" name = "password" id = "password" /><br />
	<input type = "submit" value = "ログイン" />
</form>
</body>
</html>