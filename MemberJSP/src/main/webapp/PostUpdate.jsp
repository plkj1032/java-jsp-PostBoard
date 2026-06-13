<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="DTO.PostDTO" %>
<% PostDTO pto = (PostDTO) request.getAttribute("post"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
</head>
<body>
<form action="PostUpdate" method="post">

<input type="hidden" 
		name="id" 
		value="<%= pto.getId() %>">
		
제목 : 
<input type="text"
	 	name="title"
	  	value="<%= pto.getTitle() %>">
	  	<br><br>
	  	
내용 :
<input type="text"
		name="content"
		value="<%= pto.getContent() %>">
		<br><br>
		
<button type="submit">수정하기</button>
</form>
</body>
</html>