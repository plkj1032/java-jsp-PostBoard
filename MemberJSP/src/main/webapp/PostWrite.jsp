<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="DTO.MemberDTO" %>

<% MemberDTO loginUser = 
(MemberDTO)session.getAttribute("loginUser");

if(loginUser == null)
{
%>
<script>
alert('로그인이 필요합니다!');
location.href='index.jsp';
</script>
<%
return;
}%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성!</title>
</head>
<body>
<form action="PostWrite" method="post">

게시글 제목 :
<input type="text" name="title">
<br><br>

게시글 :
<textarea name="content" rows="10" cols="50"></textarea>
<br><br>

<button type="submit">게시글 등록</button>

</form>
</body>
</html>