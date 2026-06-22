package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import DTO.CommentDTO;
import DTO.MemberDTO;
import Service.CommentService;

/**
 * Servlet implementation class CommenWriteServlet
 */
@WebServlet("/CommentWrite")
public class CommenWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommenWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doHandle(request,response);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request,response);
		// TODO Auto-generated method stub
//		request.setCharacterEncoding("UTF-8");
//		response.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html; UTF-8");
//		
//		String post_id = request.getParameter("post_id");
//		//String member_id = request.getParameter("member_id");
//		String content =  request.getParameter("content");
//		
//		int post_id_i = Integer.parseInt(post_id);
//		//int member_id_i = Integer.parseInt(member_id);
//		
//		CommentDTO cto = new CommentDTO();
//		
//		HttpSession session = request.getSession(false);
//		
//		MemberDTO loginUser = (MemberDTO)session.getAttribute("loginUser");
//		
//		if(loginUser == null)
//		{
//			response.getWriter().println(
//					"<script>"
//					+ "alert('로그인 후 이용 가능!');"
//					+ "location.href='PostDetail?id=" + post_id + "';"
//					+ "</script>"
//					);
//			return;
//		}
//		
//		cto.setPost_id(post_id_i);
//		cto.setMember_id(loginUser.getId());
//		cto.setContent(content);
//		
//		CommentService service = new CommentService();
//		
//		boolean check = service.insertComments(cto);
//		
//		if(check)
//		{
//			response.getWriter().println(
//					"<script>"
//					+ "alert('댓글 등록 완료!');"
//					+ "location.href='PostDetail?id=" + post_id + "';"
//					+ "</script>"
//					);
//		}
//		else
//		{
//			response.getWriter().println(
//					"<script>"
//					+ "alert('댓글 등록 실패!');"
//					+ "location.href='PostDetail?id=" + post_id + "';"
//					+ "</script>"
//					);
//		}
		
		//doGet(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String method = request.getMethod();
		
		if(method.equals("POST"))
		{
			HttpSession session = request.getSession();
			
			MemberDTO loginUser = (MemberDTO)session.getAttribute("loginUser");
			
			String post_id = request.getParameter("post_id");
			String content = request.getParameter("content");
			
			if(loginUser == null)
			{
				response.getWriter().println(
						"<script>"
						+ "alert('로그인이 필요합니다!');"
						+ "location.href=PostDetail?id= " + Integer.parseInt(post_id) + "';"
						+ "</script>"
						);
				return;
			}
			
			CommentService service = new CommentService();
			
			CommentDTO cto = new CommentDTO();
			
			cto.setMember_id(loginUser.getId());
			cto.setPost_id(Integer.parseInt(post_id));
			cto.setContent(content);
			
			boolean check = service.insertComments(cto);
			
			if(check)
			{
				response.sendRedirect("PostDetail?id=" + Integer.parseInt(post_id));
			}
			else
			{
				response.getWriter().println(
						"<script>"
						+ "alert('댓글 등록 실패!');"
						+ "location.href='PostDetail?id=" + Integer.parseInt(post_id) + "';"
						+ "</script>"
						);
			}
			
		}
	}

}
