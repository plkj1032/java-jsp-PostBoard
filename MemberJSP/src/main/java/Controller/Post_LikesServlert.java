package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import DTO.MemberDTO;
import Service.Post_likesService;

/**
 * Servlet implementation class Post_LikesServlert
 */
@WebServlet(name = "Post_LikesServlet", urlPatterns = { "/Post_Likes" })
public class Post_LikesServlert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Post_LikesServlert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		String post_id = request.getParameter("post_id");
		
		HttpSession session = request.getSession(false);
		
		MemberDTO loginUser = (MemberDTO)session.getAttribute("loginUser");
		
		if(loginUser == null)
		{
			response.getWriter().println(
					"<script>"
					+ "alert('로그인 후 이용 가능!');"
					+ "location.href='Login';"
					+ "</script>"
					);
			return;
		}

		Post_likesService service = new Post_likesService();
		
		
		
		boolean check_like = service.selectCheckLikes(loginUser.getId(), Integer.parseInt(post_id));
		
		System.out.print(check_like);
		
		if(check_like)
		{
			service.deletePostLikes(loginUser.getId(), Integer.parseInt(post_id));
		}
		else
		{
			service.insertPostLikes(loginUser.getId(), Integer.parseInt(post_id));
		}
		
		response.sendRedirect("PostDetail?id=" + post_id);
		
		
		
		//doGet(request, response);
	}

}
