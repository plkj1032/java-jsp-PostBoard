<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="DTO.MemberDTO" %>
<% MemberDTO loginUser = 
	(MemberDTO) session.getAttribute("loginUser"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>3번째 도전..</title>
</head>
<body>
<h3>3번째 JSP 이해하기 도전!</h3>
<%if(loginUser == null){ %>
<a href="Signup.jsp">회원가입</a>
<a href="Login.jsp">로그인</a>
<a href="PostList">게시글 목록</a>
<a href="PostWrite.jsp">게시글작성</a>
<%} else { %>
<a href="PostList">게시글 목록</a>
<a href="PostWrite.jsp">게시글작성</a>
<a href="Logout">로그아웃</a>
<%} %>
</body>
</html>