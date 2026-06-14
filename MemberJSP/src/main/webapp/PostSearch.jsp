<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="DTO.PostDTO" %>
<%@ page import="java.util.List" %>
<% List<PostDTO> posts =
(List<PostDTO>) request.getAttribute("posts"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>검색된 게시글 보기</title>
</head>
<body>
<table border=1>
<tr>
	<th>게시글 번호</th>
	<th>게시글 제목</th>
	<th>게시글 내용</th>
	<th>작성자</th>
	<th>조회수</th>
	<th>작성 시간</th>
</tr>
<% for(PostDTO p : posts){ %>
<tr>
    <td><%= p.getId() %></td>
    <td><%= p.getTitle() %></td>
    <td><%= p.getContent() %></td>
    <td><%= p.getPost_writer() %></td>
    <td><%= p.getView_count() %></td>
    <td><%= p.getCreated_at() %></td>
</tr>
<% } %>
</table>

</body>
</html>