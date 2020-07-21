<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "bean.User" %>
<%
	User user = (User) session.getAttribute("user");
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
<% if( user != null ) { %>
	<p>ログインに成功しました。</p>
	<p>ようこそ<%= user.getUserName() %>さん</p>
	<a href = "./SingleWord?action=action.SpeechLogic">発言・閲覧へ</a>
<% } %>
</body>
</html>