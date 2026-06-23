<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<% String msg = (String) request.getAttribute("msg"); 
	if( msg != null)
	{%>
		<script>
			alert("<%=msg%>");
		</script>
	<%}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CODE-MATE - 로그인</title>
<style>
    /* 기본 테마 및 여백 설정 (기존 페이지들과 완벽 일치) */
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
    /* 현재 로그인 페이지 표시 강조 */
    .auth-menu a.active {
        color: #0d6efd;
        background-color: #e9ecef;
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

    /* 중앙 메인 콘텐츠 영역 (로그인 폼 중앙 정렬) */
    .main-content {
        flex-grow: 1;
        padding: 40px;
        background-color: #f1f3f5;
        display: flex;
        justify-content: center;
        align-items: center; /* 세로 축에서도 중앙 정렬 */
        overflow-y: auto;
    }
    
    /* 로그인 박스 카드 */
    .login-box {
        background-color: white;
        padding: 40px;
        border-radius: 12px;
        box-shadow: 0 5px 20px rgba(0,0,0,0.05);
        max-width: 450px;
        width: 100%;
        box-sizing: border-box;
    }
    .login-box h1 {
        font-size: 1.6rem;
        color: #212529;
        margin-top: 0;
        margin-bottom: 30px;
        text-align: center;
        font-weight: 700;
    }

    /* 폼 요소 스타일링 */
    .form-group {
        margin-bottom: 20px;
    }
    .form-group label {
        display: block;
        margin-bottom: 8px;
        font-weight: 600;
        color: #495057;
        font-size: 14px;
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

    /* 버튼 그룹 및 스타일 */
    .button-group {
        display: flex;
        flex-direction: column; /* 버튼들을 세로로 배치 */
        gap: 10px;
        margin-top: 25px;
    }
    .btn {
        width: 100%;
        padding: 12px 0;
        border-radius: 6px;
        font-size: 15px;
        font-weight: 600;
        cursor: pointer;
        border: none;
        transition: all 0.2s;
        text-align: center;
        box-sizing: border-box;
        display: block;
    }
    .btn-primary {
        background-color: #0d6efd;
        color: white;
    }
    .btn-primary:hover {
        background-color: #0b5ed7;
    }
    .btn-home {
        background-color: #e9ecef;
        color: #495057;
        border: 1px solid #ced4da;
    }
    .btn-home:hover {
        background-color: #dee2e6;
    }

    /* 🔥 [추가] 구분선 및 카카오 로그인 버튼 스타일링 */
    .divider {
        display: flex;
        align-items: center;
        text-align: center;
        color: #adb5bd;
        font-size: 13px;
        margin: 20px 0;
    }
    .divider::before, .divider::after {
        content: '';
        flex: 1;
        border-bottom: 1px solid #dee2e6;
    }
    .divider:not(:empty)::before { margin-right: .5em; }
    .divider:not(:empty)::after { margin-left: .5em; }

    .btn-kakao {
        background-color: #FEE500;
        color: #191919;
        display: flex;
        align-items: center;
        justify-content: center;
        gap: 8px;
    }
    .btn-kakao:hover {
        background-color: #FADA0A;
    }
    .btn-kakao img {
        width: 18px;
        height: 18px;
    }

    /* 하단 가이드 텍스트 */
    .login-footer {
        margin-top: 25px;
        text-align: center;
        font-size: 14px;
        color: #6c757d;
    }
    .login-footer a {
        color: #0d6efd;
        font-weight: 600;
        margin-left: 5px;
    }
    .login-footer a:hover {
        text-decoration: underline;
    }
</style>
</head>
<body>

    <header>
        <div class="auth-menu">
            <a href="Login.jsp" class="active">로그인</a>
            <a href="Signup.jsp">회원가입</a>
        </div>
    </header>

    <div class="container">
        
        <aside class="sidebar">
            <h2><a href="index.jsp">CODE-MATE</a></h2>
        </aside>

        <main class="main-content">
            <div class="login-box">
                <h1>🔑 로그인</h1>
                
                <form action="Login" method="post">
                    <div class="form-group">
                        <label for="email">아이디</label>
                        <input type="text" id="email" name="email" class="form-control" placeholder="아이디를 입력해주세요" required>
                    </div>

                    <div class="form-group">
                        <label for="password">비밀번호</label>
                        <input type="password" id="password" name="password" class="form-control" placeholder="비밀번호를 입력해주세요" required>
                    </div>

                    <div class="button-group">
                        <button type="submit" class="btn btn-primary">로그인</button>
                    </div>
                </form>

                <div class="divider">또는</div>
                
                <div class="button-group" style="margin-top: 0;">
                    <a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=02c9a21784832eda4f5fc3bf4fcda038&redirect_uri=http://localhost:8080/MemberJSP/callback/kakao" class="btn btn-kakao">
                        💬 카카오 로그인
                    </a>
                    <a href="index.jsp" class="btn btn-home">홈으로</a>
                </div>

                <div class="login-footer">
                    아직 회원이 아니신가요?<a href="Signup.jsp">회원가입하기</a>
                </div>
            </div>
        </main>
        
    </div>

</body>
</html>