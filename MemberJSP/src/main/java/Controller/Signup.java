package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import DTO.MemberDTO;
import Service.MemberService;

/**
 * Servlet implementation class Signup
 */
@WebServlet(name = "SignupServlet", urlPatterns = { "/Signup" })
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//request.getRequestDispatcher("Signup.jsp").forward(request, response);
		request.getRequestDispatcher("Signup.jsp").forward(request, response);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 사이트에서 값 받아오기!!!!!!!!!!
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		if(name == null || name.trim().isEmpty() ||
			age == null || age.trim().isEmpty() ||
			email == null || email.trim().isEmpty() ||
			password == null || password.trim().isEmpty())
		{
			request.setAttribute("msg","모든 값 입력!!");
			request.getRequestDispatcher("Signup.jsp").forward(request, response);
		}
		
		int i_age = Integer.parseInt(age);
		
		MemberDTO dto = new MemberDTO();
		
		dto.setName(name);
		dto.setAge(i_age);
		dto.setEmail(email);
		dto.setPassword(password);
		
		MemberService service = new MemberService();
		
		boolean check = service.insertMember(dto);
		
		if(check)
		{
			request.setAttribute("msg","회원가입 성공!");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		else
		{
			request.setAttribute("msg", "회원가입 실패!");
			request.getRequestDispatcher("Signup.jsp").forward(request, response);
		}
		
		//doGet(request, response);
	}

}
