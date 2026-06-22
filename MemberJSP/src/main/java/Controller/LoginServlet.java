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
		
		
		// index.jsp -> Servlet 요청 -> Login.jsp로 보냄 ( 내부적 처리 )
		//request.getRequestDispatcher("Login.jsp").forward(request,response);
		doHandle(request,response);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//사용자에게 값 받기!!!!!!!!!!!!!!!
		doHandle(request,response);
//		request.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html; charset=UTF-8");
//		response.setCharacterEncoding("UTF-8");
//		
//		String email = request.getParameter("email");
//		String password = request.getParameter("password");
//		
//		if(email == null || email.trim().isEmpty() ||
//			password == null || password.trim().isEmpty())
//		{
//			request.setAttribute("msg","모든 값을 입력하세요!");
//			request.getRequestDispatcher("Login.jsp").forward(request,response);
//		}
//		
//		MemberService service = new MemberService();
//		
//		MemberDTO loginUser = service.loginUser(email, password);
//		
//		if(loginUser != null)
//		{
//			HttpSession session = request.getSession();
//			session.setAttribute("loginUser", loginUser);
//			
//			request.setAttribute("msg", "로그인성공!");
//			request.getRequestDispatcher("index.jsp").forward(request,response);
//		}
//		else
//		{
//			request.setAttribute("msg","로그인실패!");
//			request.getRequestDispatcher("Login.jsp").forward(request, response);
//		}
		
		//doGet(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String method = request.getMethod();
		
		if(method.equals("GET"))
		{
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		}
		else if(method.equals("POST"))
		{
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			if(email == null || email.trim().isEmpty() ||
				password == null || password.trim().isEmpty())
			{
				request.setAttribute("msg", "아이디와 비밀번호를 입력하세요!");
				request.getRequestDispatcher("Login.jsp").forward(request, response);
			}
			
			MemberService service = new MemberService();
			
			MemberDTO loginUser = service.loginUser(email, password);
			
			if(loginUser != null)
			{
				HttpSession session = request.getSession();
				
				session.setAttribute("loginUser", loginUser);
				
				request.setAttribute("msg","로그인 성공!");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
			else
			{
				request.setAttribute("msg", "아이디 혹은 비밀번호가 틀립니다!");
				request.getRequestDispatcher("Login.jsp").forward(request, response);
			}
		}
	}

}
