<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="DTO.MemberDTO" %>
<%@ page import="DTO.PostDTO" %>
<% 
    // 세션 및 요청 객체에서 데이터 가져오기
    MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
    PostDTO pto = (PostDTO) request.getAttribute("post"); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CODE-MATE - 게시글 수정</title>
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
    .sidebar li a:hover {
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
    
    /* 수정 폼 카드 영역 */
    .edit-box {
        background-color: white;
        padding: 40px;
        border-radius: 12px;
        box-shadow: 0 5px 20px rgba(0,0,0,0.05);
        max-width: 800px;
        width: 100%;
        height: fit-content;
    }
    .edit-box h1 {
        font-size: 1.6rem;
        color: #212529;
        margin-top: 0;
        margin-bottom: 30px;
        border-bottom: 2px solid #f1f3f5;
        padding-bottom: 15px;
    }

    /* 폼 커스텀 스타일링 */
    .form-group {
        margin-bottom: 20px;
    }
    .form-group label {
        display: block;
        margin-bottom: 8px;
        font-weight: 600;
        color: #495057;
    }
    .form-control {
        width: 100%;
        padding: 12px 15px;
        border: 1px solid #ced4da;
        border-radius: 6px;
        font-size: 15px;
        box-sizing: border-box;
        font-family: inherit;
        transition: border-color 0.2s, box-shadow 0.2s;
    }
    .form-control:focus {
        outline: none;
        border-color: #0d6efd;
        box-shadow: 0 0 0 3px rgba(13, 110, 253, 0.1);
    }
    textarea.form-control {
        resize: vertical;
        min-height: 300px;
    }

    /* 하단 버튼 그룹 */
    .button-group {
        display: flex;
        justify-content: flex-end;
        gap: 10px;
        margin-top: 30px;
    }
    .btn {
        padding: 10px 20px;
        border-radius: 6px;
        font-size: 15px;
        font-weight: 600;
        cursor: pointer;
        border: none;
        transition: all 0.2s;
        text-align: center;
        line-height: normal;
    }
    .btn-primary {
        background-color: #0d6efd;
        color: white;
    }
    .btn-primary:hover {
        background-color: #0b5ed7;
    }
    .btn-secondary {
        background-color: #f8f9fa;
        color: #495057;
        border: 1px solid #ced4da;
    }
    .btn-secondary:hover {
        background-color: #e9ecef;
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
                <li><a href="PostList">게시글 목록</a></li>
            </ul>
        </aside>

        <main class="main-content">
            <div class="edit-box">
                <h1>✏️ 게시글 수정</h1>
                
                <form action="PostUpdate" method="post">
                    <input type="hidden" name="id" value="<%= pto.getId() %>">

                    <div class="form-group">
                        <label for="title">제목</label>
                        <input type="text" id="title" name="title" class="form-control" value="<%= pto.getTitle() %>" required>
                    </div>

                    <div class="form-group">
                        <label for="content">내용</label>
                        <textarea id="content" name="content" class="form-control" required><%= pto.getContent() %></textarea>
                    </div>

                    <div class="button-group">
                        <a href="index.jsp" class="btn btn-home">홈으로</a>
                        <a href="PostDetail?id=<%= pto.getId() %>" class="btn btn-secondary">취소</a>
                        <button type="submit" class="btn btn-primary">수정하기</button>
                    </div>
                </form>
            </div>
        </main>
        
    </div>

</body>
</html>