<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="DTO.MemberDTO" %>
<% 
    // 세션에서 로그인 정보 가져오기
    MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser"); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CODE-MATE 시작화면</title>
<style>
    /* 기본 여백 제거 및 폰트 설정 */
    body, html {
        margin: 0;
        padding: 0;
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        height: 100%;
        background-color: #f8f9fa; /* 아주 연한 회색 배경 */
    }
    a { text-decoration: none; color: inherit; }

    /* 상단 헤더 (우측 상단 로그인/회원가입) */
    header {
        display: flex;
        justify-content: flex-end; /* 우측 정렬 */
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

    /* 전체 레이아웃 (사이드바 + 중앙 영역) */
    .container {
        display: flex;
        height: calc(100vh - 60px); /* 헤더 높이 제외한 전체 화면 */
    }

    /* 좌측 사이드바 메뉴 */
    .sidebar {
        width: 250px;
        background-color: #343a40; /* 어두운 테마 */
        color: white;
        padding-top: 30px;
    }
    .sidebar h2 {
        text-align: center;
        margin-bottom: 30px;
        font-size: 1.2rem;
        color: #adb5bd;
        letter-spacing: 2px;
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
        border-left-color: #0d6efd; /* 호버 시 파란색 포인트 */
        color: #ffffff;
    }

    /* 중앙 메인 콘텐츠 (CODE-MATE 로고 영역) */
    .main-content {
        flex-grow: 1;
        display: flex;
        justify-content: center;
        align-items: center;
        background-color: #f1f3f5;
    }
    .hero-box {
        text-align: center;
        background-color: white;
        padding: 50px 80px;
        border-radius: 12px;
        box-shadow: 0 10px 30px rgba(0,0,0,0.05);
    }
    .hero-box h1 {
        font-size: 3.5rem;
        color: #212529;
        margin: 0 0 15px 0;
        font-weight: 800;
        letter-spacing: -1px;
    }
    .hero-box p {
        color: #868e96;
        font-size: 1.2rem;
        margin-bottom: 0;
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
                <a href="MyPage">마이페이지</a>    
                <a href="Logout">로그아웃</a>
            <% } %>
        </div>
    </header>

    <div class="container">
        
        <aside class="sidebar">
            <h2>MENU</h2>
            <ul>
                <li><a href="PostList">게시글 목록</a></li>
            </ul>
        </aside>

        <main class="main-content">
            <div class="hero-box">
                <h1>CODE-MATE</h1>
                <p>당신의 코드 메이트를 찾아보세요.</p>
            </div>
        </main>
        
    </div>

</body>
</html>