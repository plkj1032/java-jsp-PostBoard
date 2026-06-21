<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="DTO.MemberDTO" %>
<%@ page import="DTO.PostDTO" %>
<%@ page import="DTO.CommentDTO" %>
<%@ page import="java.util.List" %>
<% 
    // 세션 및 요청 객체에서 데이터 가져오기
    MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
    PostDTO post = (PostDTO) request.getAttribute("post"); 
    List<CommentDTO> comments = (List<CommentDTO>) request.getAttribute("comments");
    String edit_id = request.getParameter("edit_id");
    Integer likes_count = (Integer) request.getAttribute("likes_count");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CODE-MATE - 게시글 상세 보기</title>
<style>
    /* 기본 테마 및 여백 설정 */
    body, html {
        margin: 0;
        padding: 0;
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        height: 100%;
        background-color: #f8f9fa;
    }
    a { text-decoration: none; color: inherit; }

    /* 상단 헤더 */
    header {
        display: flex;
        justify-content: flex-end;
        align-items: center;
        padding: 15px 30px;
        background-color: #ffffff;
        box-shadow: 0 2px 5px rgba(0,0,0,0.05);
        height: 60px;
        box-sizing: border-box;
    }
    .auth-menu a {
        margin-left: 20px;
        font-weight: 600;
        color: #495057;
        padding: 8px 16px;
        border-radius: 4px;
        transition: background-color 0.3s;
    }
    .auth-menu a:hover {
        background-color: #e9ecef;
        color: #007bff;
    }
    .welcome-text {
        font-weight: bold;
        color: #007bff;
    }

    /* 레이아웃 컨테이너 */
    .container {
        display: flex;
        height: calc(100vh - 60px);
    }

    /* 좌측 사이드바 */
    .sidebar {
        width: 250px;
        background-color: #343a40;
        color: white;
        padding-top: 30px;
        flex-shrink: 0;
    }
    /* 사이드바 타이틀에 홈 링크 적용 */
    .sidebar h2 {
        text-align: center;
        margin-bottom: 30px;
        font-size: 1.2rem;
        letter-spacing: 2px;
    }
    .sidebar h2 a {
        color: #adb5bd;
        transition: color 0.2s;
    }
    .sidebar h2 a:hover {
        color: #ffffff;
    }
    .sidebar ul {
        list-style: none;
        padding: 0;
        margin: 0;
    }
    .sidebar li a {
        display: block;
        padding: 15px 30px;
        font-size: 16px;
        border-left: 4px solid transparent;
        transition: all 0.2s ease-in-out;
    }
    /* 게시글 상세 페이지이므로 '게시글 목록'에 활성화 표시 유지 */
    .sidebar li.active a, .sidebar li a:hover {
        background-color: #495057;
        border-left-color: #0d6efd;
        color: #ffffff;
    }

    /* 중앙 메인 콘텐츠 영역 */
    .main-content {
        flex-grow: 1;
        padding: 40px;
        background-color: #f1f3f5;
        display: flex;
        justify-content: center;
        overflow-y: auto;
    }
    
    /* 게시글 상세 카드 영역 */
    .detail-box {
        background-color: white;
        padding: 40px;
        border-radius: 12px;
        box-shadow: 0 5px 20px rgba(0,0,0,0.05);
        max-width: 800px;
        width: 100%;
        height: fit-content;
    }

    /* 본문 상단 타이틀 영역 */
    .post-header {
        border-bottom: 2px solid #f1f3f5;
        padding-bottom: 20px;
        margin-bottom: 30px;
    }
    .post-title {
        font-size: 1.8rem;
        color: #212529;
        margin: 0 0 15px 0;
        font-weight: 700;
        line-height: 1.3;
    }
    .post-meta {
        display: flex;
        gap: 20px;
        color: #868e96;
        font-size: 14px;
    }
    .post-meta span {
        display: flex;
        align-items: center;
    }

    /* 본문 내용 영역 */
    .post-content {
        font-size: 16px;
        line-height: 1.6;
        color: #495057;
        min-height: 300px;
        white-space: pre-wrap; /* 엔터(줄바꿈) 등 공백을 유지해 줌 */
    }

    /* 하단 버튼 컨트롤 영역 */
    .button-group {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-top: 40px;
        padding-top: 20px;
        border-top: 1px solid #f1f3f5;
    }
    .left-btns, .right-btns {
        display: flex;
        gap: 10px;
    }

    /* 공통 버튼 스타일 */
    .btn {
        padding: 8px 16px;
        border-radius: 6px;
        font-size: 14px;
        font-weight: 600;
        cursor: pointer;
        border: none;
        transition: all 0.2s;
        text-align: center;
    }
    .btn-list {
        background-color: #0d6efd;
        color: white;
    }
    .btn-list:hover {
        background-color: #0b5ed7;
    }
    .btn-edit {
        background-color: #ffffff;
        color: #198754;
        border: 1px solid #198754;
    }
    .btn-edit:hover {
        background-color: #198754;
        color: white;
    }
    .btn-delete {
        background-color: #ffffff;
        color: #dc3545;
        border: 1px solid #dc3545;
    }
    .btn-delete:hover {
        background-color: #dc3545;
        color: white;
    }
    .btn-home {
        background-color: #e9ecef;
        color: #495057;
        border: 1px solid #ced4da;
    }
    .btn-home:hover {
        background-color: #dee2e6;
    }
</style>
</head>
<body>

    <header>
        <div class="auth-menu">
            <% if(loginUser == null){ %>
                <a href="Login.jsp">로그인</a>
                <a href="Signup.jsp">회원가입</a>
            <% } else { %>
                <span class="welcome-text">환영합니다!</span>
                <a href="Logout">로그아웃</a>
            <% } %>
        </div>
    </header>

    <div class="container">
        
        <aside class="sidebar">
            <h2><a href="index.jsp">CODE-MATE</a></h2>
            <ul>
                <li class="active"><a href="PostList">게시글 목록</a></li>
            </ul>
        </aside>

        <main class="main-content">
            <div class="detail-box">
                
                <div class="post-header">
                    <h1 class="post-title"><%= post.getTitle() %></h1>
                    <div class="post-meta">
                        <span>👤 작성자 : <%= post.getPost_writer() %></span>
                        <span>🕒 작성 시간 : <%= post.getCreated_at() %></span>
                    </div>
                </div>

                <div class="post-content"><%= post.getContent() %></div>
                <% if(post.getFile_name() != null)
                	{%>
                		<img src="uploads/<%= post.getFile_name() %>" width="300">
                <% }%>
                

                <div class="button-group">
                    <div class="left-btns">
                        <a href="index.jsp" class="btn btn-home">홈으로</a>
                        <a href="PostList" class="btn btn-list">목록으로</a>
                        <form action="Post_Likes" method="post">
                        	<input type="hidden" name="post_id" value="<%=post.getId() %>">
                        	<button type="submit">좋아요</button>
                        </form>
                    </div>
                    
                    <div class="right-btns">
                        <% 
                            // 현재 로그인한 사용자와 게시글 작성자가 일치할 때만 수정/삭제 버튼 노출
                            if( loginUser != null && loginUser.getId() == post.getMember_id()) { 
                        %>
                            <a href="PostUpdate?id=<%= post.getId() %>" class="btn btn-edit">게시글 수정</a>
                            <a href="PostDelete?id=<%= post.getId() %>" class="btn btn-delete">게시글 삭제</a>
                        <% } %>
                    </div>
                </div>
                
                <div class="pagination">
                	<form action="CommentWrite" method="post">
                		<input type="hidden" name="post_id" value="<%= post.getId() %>">
                		
                		<textarea name="content" placeholder="댓글을 입력하세요"></textarea>
                		
                		<% if(loginUser != null) {%>
                		<button type="submit">댓글 등록</button>
                		<%} else { %>
                			<button type="button"
                				onclick="alert('로그인 후 이용 가능합니다.'); location.href='Login.jsp';">
                				댓글 등록
                			</button>
                		<%} %>
                		<p>좋아요 수 : <%=likes_count %></p>
                	
                	</form>
                </div>
                
               	<table border=1>
               		<tr>
               			<th>멤버 번호</th>
               			<th>내용</th>
               			<th>작성자</th>
               			<th>작성시간</th>
               			<th>관리</th>
               		</tr>
               		<% if( comments != null && !comments.isEmpty()) {%>
               		
                		<% for( CommentDTO c : comments) {%>
                		
                			<%
                			boolean editMode = edit_id != null && edit_id.equals(String.valueOf(c.getId()));
                			%>
                		<tr>
                			<td><%= c.getMember_id() %></td>
                			<td>
	                			<% if(editMode){ %>
	                				<form action="CommentUpdate" method="post">
	                					<input type="hidden" name="c_id" value="<%= c.getId() %>">
	                					<input type="hidden" name="post_id" value="<%= c.getPost_id() %>">
	                					<textarea id="content" name="content"><%= c.getContent() %></textarea>
	                					
	                					<button type="submit">수정완료</button>
	                					<a href="PostDetail?id=<%= c.getPost_id() %>">취소</a>
	                				</form>
	                			<% } else { %>
	                			<%= c.getContent() %>
	                			<%} %>
                			</td>
                			<td><%= c.getWriter() %></td>
                			<td><%= c.getCreated_at() %>
                			<td>
                			<% if( loginUser != null && loginUser.getId() == c.getMember_id())
                				{%>
                					<% if(!editMode) { %>
                						<a href="PostDetail?id=<%= c.getPost_id() %>&edit_id=<%= c.getId() %>">
                							수정
                						</a>
                					<% } %>
                					<form action="CommentDelete" method="post">
                						<input type="hidden" name="post_id" value="<%= c.getPost_id() %>">
                						<input type="hidden" name="c_id" value="<%= c.getId() %>">
                						<button type="submit">삭제</button>
                					</form>
                				<%} %>
                			</td>
                		</tr>
                		<%}  
                		} else {%>
                			<tr>
                				<td colspan="5">아직 댓글이 없습니다.</td>
                			</tr>
                		<% } %>
               	</table>
               

            </div>
        </main>
        
    </div>

</body>
</html>