<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="DTO.PostDTO" %>
<%@ page import="DTO.MemberDTO" %>
<%@ page import="java.util.List" %>
<% 
    // 요청(request)과 세션(session)에서 데이터 가져오기
    List<PostDTO> posts = (List<PostDTO>) request.getAttribute("posts"); 
    MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CODE-MATE - 검색 결과</title>
<style>
    /* 기본 테마 및 여백 설정 (전체 페이지 통일) */
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
    /* 검색도 목록의 연장이므로 게시글 목록 탭 활성화 */
    .sidebar li.active a, .sidebar li a:hover {
        background-color: #495057;
        border-left-color: #0d6efd;
        color: #ffffff;
    }

    /* 우측 메인 콘텐츠 영역 */
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
    .search-info-text {
        font-size: 14px;
        color: #6c757d;
    }
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
    .btn-list {
        background-color: #0d6efd;
        color: white;
        padding: 8px 16px;
        border-radius: 4px;
        font-size: 14px;
        font-weight: 600;
        transition: all 0.2s;
    }
    .btn-list:hover {
        background-color: #0b5ed7;
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
            <div class="board-box">
                
                <div class="board-title-bar">
                    <h1>🔍 검색 결과</h1>
                </div>

                <table class="board-table">
                    <thead>
                        <tr>
                            <th style="width: 10%;">번호</th>
                            <th style="width: 25%;">제목</th>
                            <th style="width: 30%;">내용</th>
                            <th style="width: 15%;">작성자</th>
                            <th style="width: 10%;">조회수</th>
                            <th style="width: 10%;">작성 시간</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% if (posts != null && !posts.isEmpty()) { %>
                            <% for(PostDTO p : posts){ %>
                            <tr>
                                <td><%= p.getId() %></td>
                                <td style="text-align: left; padding-left: 15px;">
                                    <a href="PostDetail?id=<%= p.getId() %>" class="post-link">
                                        <%= p.getTitle() %>
                                    </a>
                                </td>
                                <td style="text-align: left; color: #6c757d;"><%= p.getContent() %></td>
                                <td><%= p.getPost_writer() %></td>
                                <td><%= p.getView_count() %></td>
                                <td style="color: #868e96;"><%= p.getCreated_at() %></td>
                            </tr>
                            <% } %>
                        <% } else { %>
                            <tr>
                                <td colspan="6" style="padding: 40px 0; color: #868e96;">검색 결과와 일치하는 게시글이 없습니다.</td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>

                <div class="board-footer">
                    <div class="search-info-text">
                        <% if (posts != null) { %>
                            총 <strong><%= posts.size() %></strong>개의 게시글이 검색되었습니다.
                        <% } %>
                    </div>
                    
                    <div class="footer-btns">
                        <a href="index.jsp" class="btn-home">홈으로</a>
                        <a href="PostList" class="btn-list">전체 목록으로</a>
                    </div>
                </div>

            </div>
        </main>
        
    </div>

</body>
</html>