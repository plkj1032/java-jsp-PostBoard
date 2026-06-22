package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import DTO.MemberDTO;
import Service.PostService;

/**
 * Servlet implementation class PostDeleteServlet
 */
@WebServlet("/PostDelete")
public class PostDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doHandle(request,response);
//		request.setCharacterEncoding("UTF-8");
//		response.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html; Charset=UTF-8");
//		
//		String id = request.getParameter("id");
//		
//		PostService service = new PostService();
//		
//		boolean check = service.deletePost(Integer.parseInt(id));
//		
//		if(check)
//		{
//			response.sendRedirect("PostList");
//		}
//		else
//		{
//			response.getWriter().println(
//					"<script>"
//					+ "alert('게시글 삭제 실패!');"
//					+ "location.href='PostList';"
//					+ "</script>"
//					);
//		}
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doHandle(request,response);
		//doGet(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String method = request.getMethod();
		
		if(method == "GET")
		{
			String id = request.getParameter("id");
			
			HttpSession session = request.getSession();
			
			MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
			
			if(loginUser == null)
			{
				response.getWriter().println(
						"<script>"
						+ "alert('로그인이 필요합니다!');"
						+ "location.href='Login';"
						+ "</script>"
						);
				return;
			}
			
			PostService service = new PostService();
			
			boolean check = service.deletePost(Integer.parseInt(id));
			
			if(check)
			{
				response.sendRedirect("PostList");
			}
			else
			{
				response.getWriter().println(
						"<script>"
						+ "alert('게시글 삭제 실패!');"
						+ "location.href='PostList';"
						+ "</script>"
						);
			}
		}
	}

}
