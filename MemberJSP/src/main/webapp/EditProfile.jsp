<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="DTO.MemberDTO" %>
<%
    // 세션에서 로그인된 유저 정보 가져오기
    MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
    if(loginUser == null) {
        response.sendRedirect("Login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정 - CODE-MATE</title>
<style>
    body { font-family: 'Noto Sans KR', sans-serif; background-color: #f4f6f9; margin: 0; display: flex; height: 100vh; }
    a { text-decoration: none; }
    
    .sidebar { width: 250px; background-color: #343a40; color: white; display: flex; flex-direction: column; padding-top: 20px; }
    .sidebar .logo { font-size: 24px; font-weight: bold; text-align: center; margin-bottom: 40px; }
    .sidebar .menu-item { color: #adb5bd; padding: 15px 20px; font-size: 16px; }
    
    .main-content { flex: 1; display: flex; flex-direction: column; overflow-y: auto; }
    .header { height: 60px; background-color: white; display: flex; justify-content: flex-end; align-items: center; padding: 0 30px; box-shadow: 0 2px 4px rgba(0,0,0,0.05); }
    
    .content-wrapper { padding: 40px; display: flex; justify-content: center; }
    .detail-box { background-color: white; border-radius: 8px; box-shadow: 0 4px 6px rgba(0,0,0,0.1); padding: 40px; width: 100%; max-width: 600px; }
    .detail-box h2 { margin-top: 0; color: #333; border-bottom: 2px solid #f4f6f9; padding-bottom: 15px; margin-bottom: 30px; }
    
    /* 폼 스타일링 */
    .form-group { margin-bottom: 20px; }
    .form-group label { display: block; font-weight: bold; color: #333; margin-bottom: 8px; }
    .form-control { width: 100%; padding: 10px; border: 1px solid #ced4da; border-radius: 4px; box-sizing: border-box; font-size: 14px; }
    .form-control:focus { border-color: #007bff; outline: none; }
    .form-control[readonly] { background-color: #e9ecef; cursor: not-allowed; }
    
    .button-group { margin-top: 40px; text-align: right; }
    .btn { padding: 10px 20px; border-radius: 4px; font-size: 14px; border: none; cursor: pointer; transition: 0.3s; display: inline-block; margin-left: 5px; }
    .btn-submit { background-color: #28a745; color: white; }
    .btn-cancel { background-color: #6c757d; color: white; }
    .btn:hover { opacity: 0.8; }
</style>
</head>
<body>

    <div class="sidebar">
        <div class="logo"><a href="index.jsp" style="color:white;">CODE-MATE</a></div>
    </div>

    <div class="main-content">
        <div class="header">
            <div class="user-info">
                <span><%= loginUser.getName() %>님 환영합니다</span>
            </div>
        </div>

        <div class="content-wrapper">
            <div class="detail-box">
                <h2>회원 정보 수정</h2>
                
                <form action="UpdateProfile" method="post">
                    
                    <div class="form-group">
                        <label>이름</label>
                        <input type="text" name="name" class="form-control" value="<%= loginUser.getName() %>" required>
                    </div>
                    
                    <div class="form-group">
                        <label>이메일 (아이디)</label>
                        <input type="email" name="email" class="form-control" value="<%= loginUser.getEmail() %>" readonly>
                    </div>
                    
                    <div class="form-group">
                        <label>나이</label>
                        <input type="number" name="age" class="form-control" value="<%= loginUser.getAge() %>" required>
                    </div>
                    
                    <div class="form-group">
                        <label>기본 주소</label>
                        <input type="text" name="address" class="form-control" value="<%= loginUser.getAddress() != null ? loginUser.getAddress() : "" %>">
                    </div>
                    
                    <div class="form-group">
                        <label>상세 주소</label>
                        <input type="text" name="detailAddress" class="form-control" value="<%= loginUser.getDetail_address() != null ? loginUser.getDetail_address() : "" %>">
                    </div>
                    
                    <div class="button-group">
                        <button type="submit" class="btn btn-submit">수정 완료</button>
                        <button type="button" class="btn btn-cancel" onclick="location.href='MyPage'">취소</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

</body>
</html>