<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="DTO.PostDTO" %>
<%@ page import="DTO.MemberDTO" %>
<%@ page import="java.util.List" %>
<% 
    List<PostDTO> list = (List<PostDTO>) request.getAttribute("posts"); 
    MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
    Integer currentPage = (Integer) request.getAttribute("currentPage");
    Integer totalPage = (Integer) request.getAttribute("totalPage");
    Integer size = (Integer) request.getAttribute("size");
    String msg = (String) request.getAttribute("msg");
    
    if(msg != null)
    {%>
    	<script>
    		alert('<%=msg%>');
    	</script>
   <%} 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CODE-MATE - 게시글 목록</title>
<style>
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
    .sidebar li.active a, .sidebar li a:hover {
        background-color: #495057;
        border-left-color: #0d6efd;
        color: #ffffff;
    }

    /* 우측 메인 게시판 영역 */
    .main-content {
        flex-grow: 1;
        padding: 40px;
        background-color: #f1f3f5;
        overflow-y: auto;
    }
    .board-box {
        background-color: white;
        padding: 30px;
        border-radius: 12px;
        box-shadow: 0 5px 20px rgba(0,0,0,0.05);
    }
    .board-title-bar {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 25px;
    }
    .board-title-bar h1 {
        font-size: 1.6rem;
        color: #212529;
        margin: 0;
    }

    /* 테이블 스타일링 */
    .board-table {
        width: 100%;
        border-collapse: collapse;
        margin-bottom: 25px;
        font-size: 14px;
        text-align: center;
    }
    .board-table th, .board-table td {
        padding: 14px 10px;
        border-bottom: 1px solid #dee2e6;
    }
    .board-table th {
        background-color: #f8f9fa;
        color: #495057;
        font-weight: 600;
    }
    .board-table tbody tr:hover {
        background-color: #f8f9fa;
    }
    
    .post-link {
        color: #0d6efd;
        font-weight: 500;
    }
    .post-link:hover {
        text-decoration: underline;
    }

    /* 하단 정렬 */
    .board-footer {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-top: 20px;
    }
    .search-form {
        display: flex;
        gap: 6px;
    }
    .search-input {
        padding: 8px 14px;
        border: 1px solid #ced4da;
        border-radius: 4px;
        font-size: 14px;
        outline: none;
        width: 200px;
        transition: border-color 0.2s;
    }
    .search-input:focus {
        border-color: #0d6efd;
    }
    .btn-search {
        background-color: #6c757d;
        color: white;
        border: none;
        padding: 8px 16px;
        border-radius: 4px;
        cursor: pointer;
        font-weight: 500;
        transition: background-color 0.2s;
    }
    .btn-search:hover {
        background-color: #5c636a;
    }
    
    /* 💡 하단 우측 버튼들을 가로로 나열하기 위한 스타일 */
    .footer-btns {
        display: flex;
        gap: 10px;
    }
    .btn-home {
        background-color: #e9ecef;
        color: #495057;
        border: 1px solid #ced4da;
        padding: 8px 16px;
        border-radius: 4px;
        font-size: 14px;
        font-weight: 600;
        transition: all 0.2s;
    }
    .btn-home:hover {
        background-color: #dee2e6;
        color: #212529;
    }
    /* 💡 새롭게 추가된 게시글 작성 버튼 스타일 */
    .btn-write {
        background-color: #0d6efd;
        color: white;
        padding: 8px 16px;
        border-radius: 4px;
        font-size: 14px;
        font-weight: 600;
        transition: all 0.2s;
    }
    .btn-write:hover {
        background-color: #0b5ed7;
    }
</style>
</head>
<body>

    <header>
        <div class="auth-menu">
            <% if(loginUser == null){ %>
                <a href="Login">로그인</a>
                <a href="Signup">회원가입</a>
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
            <div class="board-box">
                
                <div class="board-title-bar">
                    <h1>📋 게시글 목록</h1>
                </div>

                <table class="board-table">
                    <thead>
                        <tr>
                            <th style="width: 10%;">번호</th>
                            <th style="width: 25%;">제목</th>
                            <!-- <th style="width: 30%;">내용</th> -->
                            <th style="width: 15%;">작성자</th>
                            <th style="width: 10%;">조회수</th>
                            <th style="width: 10%;">작성 시간</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% if (list != null && !list.isEmpty()) { %>
                            <% for(PostDTO p : list){ %>
                            <tr>
                                <td><%= p.getId() %></td>
                                <td style="text-align: left; padding-left: 15px;">
                                    <a href="PostDetail?id=<%= p.getId() %>" class="post-link">
                                        <%= p.getTitle() %>
                                        [<%= p.getComment_count() %>]
                                    </a>
                                </td>
                                <!--  <td style="text-align: left; color: #6c757d;"><%= p.getContent() %></td> -->
                                <td><%= p.getPost_writer() %></td>
                                <td><%= p.getView_count() %></td>
                                <td style="color: #868e96;"><%= p.getCreated_at() %></td>
                            </tr>
                            <% } %>
                        <% } else { %>
                            <tr>
                                <td colspan="6" style="padding: 40px 0; color: #868e96;">등록된 게시글이 없습니다.</td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
                
                <div class="pagination">
                <!-- 하하.. -->
					<%
					for(int i = 1; i <= totalPage; i++)
					{
						if( i == currentPage )
						{
							%>
								<b><%= i %></b>
							<%
						}
						else
						{
							%>
							<a href="PostList?pageParam=<%= i %>&sizeParam=<%= size%>">
								<%= i %>
							</a>
							<%
						}
					}%>
				<form action="PostList" method="get">
					<select name="sizeParam">
						<option value="5">5개씩 보기</option>
						<option value="10">10개씩 보기</option>
						<option value="20">20개씩 보기</option>
						<option value="50">50개씩 보기</option>
					</select>

					<input type="hidden" name="pageParam" value="1">
					<button type="submit">적용</button>
				</form>
				
                </div>

                <div class="board-footer">
                    <form action="PostSearch" method="get" class="search-form">
                        <input type="text" name="keyword" class="search-input" placeholder="검색어를 입력하세요">
                        <select name="searchType">
						<option value="title">제목</option>
						<option value="content">내용</option>
						<option value="writer">작성자</option>
						<option value="title_content">제목+내용</option>
					</select>
                        <button type="submit" class="btn-search">검색</button>
                    </form>
                    
                    <div class="footer-btns">
                        <a href="index.jsp" class="btn-home">홈으로</a>
                        <a href="PostWrite.jsp" class="btn-write">게시글 작성</a>
                    </div>
                </div>

            </div>
        </main>
        
    </div>
</body>
</html>