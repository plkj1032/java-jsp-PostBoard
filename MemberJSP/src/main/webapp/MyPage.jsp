<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="DTO.MemberDTO"%>
<%
    // 서블릿에서 이미 로그인 검증을 마쳤으므로, 출력용으로 세션에서 객체만 꺼내옵니다.
    MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지 - CODE-MATE</title>
<style>
/* 기존 CODE-MATE 공통 테마 CSS */
body {
	font-family: 'Noto Sans KR', sans-serif;
	background-color: #f4f6f9;
	margin: 0;
	display: flex;
	height: 100vh;
}

a {
	text-decoration: none;
}

/* 사이드바 */
.sidebar {
	width: 250px;
	background-color: #343a40;
	color: white;
	display: flex;
	flex-direction: column;
	padding-top: 20px;
}

.sidebar .logo {
	font-size: 24px;
	font-weight: bold;
	text-align: center;
	margin-bottom: 40px;
}

.sidebar .menu-list {
	display: flex;
	flex-direction: column;
}

.sidebar .menu-item {
	color: #adb5bd;
	padding: 15px 20px;
	font-size: 16px;
	transition: 0.3s;
}

.sidebar .menu-item:hover, .sidebar .menu-item.active {
	background-color: #495057;
	color: white;
	border-left: 4px solid #007bff;
}

/* 메인 콘텐츠 영역 */
.main-content {
	flex: 1;
	display: flex;
	flex-direction: column;
	overflow-y: auto;
}

.header {
	height: 60px;
	background-color: white;
	display: flex;
	justify-content: flex-end;
	align-items: center;
	padding: 0 30px;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.05);
}

.user-info span {
	margin-right: 15px;
	font-weight: bold;
	color: #333;
}

.btn {
	padding: 8px 15px;
	border-radius: 4px;
	font-size: 14px;
	border: none;
	cursor: pointer;
	transition: 0.3s;
	text-decoration: none;
	display: inline-block;
	margin-left: 5px;
}

.btn:hover {
	opacity: 0.8;
}

/* 카드 레이아웃 (디테일 박스) */
.content-wrapper {
	padding: 40px;
	display: flex;
	justify-content: center;
}

.detail-box {
	background-color: white;
	border-radius: 8px;
	box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
	padding: 40px;
	width: 100%;
	max-width: 600px;
}

.detail-box h2 {
	margin-top: 0;
	color: #333;
	border-bottom: 2px solid #f4f6f9;
	padding-bottom: 15px;
}

/* 마이페이지 정보 영역 */
.mypage-info {
	margin-top: 20px;
	line-height: 2;
	font-size: 16px;
	color: #555;
}

.mypage-info strong {
	width: 120px;
	display: inline-block;
	color: #333;
}

.profile-img-placeholder {
	text-align: center;
	margin-bottom: 30px;
}

.profile-img-placeholder span {
	display: inline-block;
	width: 100px;
	height: 100px;
	background-color: #e9ecef;
	border-radius: 50%;
	line-height: 100px;
	font-size: 30px;
	color: #adb5bd;
}

/* 하단 버튼 */
.button-group {
	margin-top: 40px;
	text-align: right;
}

.btn-home {
	background-color: #6c757d;
	color: white;
}
</style>
</head>
<body>

	<div class="sidebar">
		<div class="logo">
			<a href="index.jsp" style="color: white;">CODE-MATE</a>
		</div>
		<div class="menu-list">
			<a href="PostList" class="menu-item">게시글 목록확인</a>
		</div>
	</div>

	<div class="main-content">
		<div class="header">
			<div class="user-info">
				<span><%= loginUser.getName() %>님 환영합니다</span> <a
					href="LogoutServlet" class="btn"
					style="background-color: #6c757d; color: white;">로그아웃</a>
			</div>
		</div>

		<div class="content-wrapper">
			<div class="detail-box">
				<h2>내 정보</h2>

				<div class="profile-img-placeholder">
					<span>👤</span>
				</div>

				<div class="mypage-info">
					<p>
						<strong>이름</strong>
						<%= loginUser.getName() %></p>
					<p>
						<strong>아이디(이메일)</strong>
						<%= loginUser.getEmail() %></p>
					<p>
						<strong>나이</strong>
						<%= loginUser.getAge() %>세
					</p>
					<p>
						<strong>주소</strong>
						<%
            			String address = loginUser.getAddress();
            			// DTO에 만든 상세주소 Getter 이름(getDetailAddress 등)으로 맞춰주세요!
            			String detailAddress = loginUser.getDetail_address(); 

            			if (address != null && !address.trim().isEmpty() && !address.equals("null")) {
                		out.print(address); // 기본 주소 출력
                
                			// 상세 주소가 있으면 이어서 출력
                			if (detailAddress != null && !detailAddress.trim().isEmpty() && !detailAddress.equals("null")) {
                    		out.print(" " + detailAddress);
                			}
            			} else {
                			out.print("<span style='color: #adb5bd;'>등록된 주소가 없습니다.</span>");
            			} 
        				%>
					</p>
				</div>

				<div class="button-group">
					<button class="btn" style="background-color: #007bff; color: white;" onclick="location.href='EditProfile.jsp'">정보 수정</button>
					<button class="btn btn-home" onclick="location.href='index.jsp'">홈으로</button>
				</div>
			</div>
		</div>
	</div>

</body>
</html>