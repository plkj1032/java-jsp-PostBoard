package Controller;

import java.io.IOException;
import java.io.PrintWriter; // ✨ 알림창 출력을 위해 추가
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import DTO.MemberDTO;
import DAO.MemberDAO; // 본인의 MemberDAO 패키지 경로에 맞게 수정하세요.

@WebServlet("/UpdateProfile")
public class UpdateProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 한글 깨짐 방지 인코딩 설정
        request.setCharacterEncoding("UTF-8");
        
        // 2. 세션에서 현재 로그인한 유저의 기존 정보 가져오기
        HttpSession session = request.getSession();
        MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
        
        if (loginUser == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        // 3. 수정 폼에서 입력한 파라미터 값 꺼내기
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String address = request.getParameter("address");
        String detailAddress = request.getParameter("detailAddress");

        // 4. 기존 세션 DTO 객체의 값을 새로운 값으로 업데이트
        loginUser.setName(name);
        loginUser.setAge(age);
        loginUser.setAddress(address);
        loginUser.setDetail_address(detailAddress);

        // 5. DB 업데이트 수행
        MemberDAO dao = new MemberDAO();
        int result = dao.updateMember(loginUser);

        // 6. 응답 방식을 HTML/자바스크립트 형태로 설정 (한글 깨짐 방지)
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        if (result > 0) {
            // DB 수정 성공 시, 세션에 갱신된 DTO를 다시 저장하여 마이페이지에 바로 반영되도록 함
            session.setAttribute("loginUser", loginUser);
            
            // ✨ 알림창을 띄우고 마이페이지 서블릿(/MyPage)으로 이동시킴
            out.println("<script>");
            out.println("alert('회원 정보 수정이 완료되었습니다.');");
            out.println("location.href='MyPage';"); 
            out.println("</script>");
        } else {
            // 실패 시 알림창을 띄우고 이전 수정 페이지로 돌아감
            out.println("<script>");
            out.println("alert('정보 수정에 실패했습니다. 다시 시도해주세요.');");
            out.println("history.back();");
            out.println("</script>");
        }
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("MyPage");
    }
}