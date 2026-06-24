package Controller; // 프로젝트에 맞는 패키지명을 입력해주세요.

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import DTO.MemberDTO;

@WebServlet("/MyPage")
public class MyPageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 1. GET 요청이 들어오면 doHandle로 전달
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doHandle(request, response);
    }

    // 2. POST 요청이 들어오면 doHandle로 전달
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doHandle(request, response);
    }

    // ✨ 3. 모든 요청을 공통으로 처리하는 doHandle 메서드
    protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
        
        // 로그인이 안 되어 있는 경우
        if(loginUser == null) {
            response.sendRedirect("Login.jsp");
            return;
        }
        
        // 정상 로그인 상태면 MyPage.jsp로 포워드
        request.getRequestDispatcher("MyPage.jsp").forward(request, response);
    }
}