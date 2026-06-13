package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import DTO.MemberDTO;
import Service.MemberService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//사용자에게 값 받기!!!!!!!!!!!!!!!
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		if(email == null || email.trim().isEmpty() ||
			password == null || password.trim().isEmpty())
		{
			response.getWriter().println(
					"<script>"
					+ "alert('모든 값을 입력하세요!');"
					+ "location.href='index.jsp';"
					+ "</script>"
					);
			return;
		}
		
		MemberService service = new MemberService();
		
		MemberDTO loginUser = service.loginUser(email, password);
		
		if(loginUser != null)
		{
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", loginUser);
			
			response.getWriter().println(
					"<script>"
					+ "alert('로그인 성공!');"
					+ "location.href='index.jsp';"
					+ "</script>"
					);
			return;
		}
		else
		{
			response.getWriter().println(
					"<script>"
					+ "alert('로그인 실패!');"
					+ "location.href='index.jsp';"
					+ "</script>"
					);
			return;
		}
		
		//doGet(request, response);
	}

}
