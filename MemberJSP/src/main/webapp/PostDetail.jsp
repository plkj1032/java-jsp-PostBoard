<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="DTO.MemberDTO" %>
<%@ page import="DTO.PostDTO" %>
<% 
MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
PostDTO post = (PostDTO) request.getAttribute("post"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세 보기</title>
</head>
<body>
<h2><%= post.getTitle() %></h2>
<p>작성자 : <%= post.getPost_writer() %></p>
<p>작성 시간 : <%= post.getCreated_at() %></p>
<hr>
<p>내용 : <%= post.getContent() %></p>
<% if( loginUser != null && loginUser.getId() == post.getMember_id()) {%>
<a href="PostUpdate?id=<%= post.getId() %>">게시글 수정</a>
<a href="PostDelete?id=<%= post.getId() %>">게시글 삭제</a>
<%} %>
<a href="PostList">목록으로</a>
<a href="index.jsp">홈으로</a>

</body>
</html>