package Controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import DTO.PostDTO;
import Service.PostService;

/**
 * Servlet implementation class PostUdateServlet
 */
@WebServlet(name = "PostUpdateServlet", urlPatterns = { "/PostUpdate" })
public class PostUdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostUdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request,response);
		
		// doGet() 단일로 받는 경우 동작
		
//		String id = request.getParameter("id");
//		
//		PostService service = new PostService();
//		
//		PostDTO post = service.selectPostDetail(Integer.parseInt(id));
//		
//		request.setAttribute("post",post);
//		
//		RequestDispatcher rd = request.getRequestDispatcher("PostUpdate.jsp");
//		
//		rd.forward(request, response);
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request,response);
		
		
		// doGet()에서 게시글 조회 후 해당 게시글을 받아 수정하는 부분
//		request.setCharacterEncoding("UTF-8");
//		response.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html; Charset=UTF-8");
//		
//		String id = request.getParameter("id");
//		String title = request.getParameter("title");
//		String content = request.getParameter("content");
//		
//		System.out.println("id : " + id);
//		System.out.println("title : " + title);
//		System.out.println("content : " + content);
//		
//		if( id == null || id.trim().isEmpty() ||
//			title == null || title.trim().isEmpty() ||
//			content  == null || content.trim().isEmpty())
//		{
//			response.getWriter().println(
//					"<script>"
//					+ "alert('모든 값 입력!');"
//					+ "location.href='index.jsp';"
//					+ "</script>"
//					);
//			return;
//		}
//		
//		int id_i = Integer.parseInt(id);
//		
//		PostService service = new PostService();
//		
//		boolean check = service.updatePost(title, content, id_i);
//		
//		if(check)
//		{			
//			response.getWriter().println(
//					"<script>"
//					+ "alert('게시글 수정 완료!');"
//					+ "location.href='PostDetail?id=" + id + "';"
//					+ "</script>"
//					);
//		}
//		else
//		{
//			response.getWriter().println(
//					"<script>"
//					+ "alert('게시글 수정 실패!');"
//					+ "history.back();"
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
		
		if(method.equals("GET"))
		{
			String id = request.getParameter("id");
			
			PostService service = new PostService();
			
			PostDTO post = service.selectPostDetail(Integer.parseInt(id));
			
			request.setAttribute("post", post);
			
			RequestDispatcher rd = request.getRequestDispatcher("PostUpdate.jsp");
			rd.forward(request, response);
			
			return;
		}
		
		if(method.equals("POST"))
		{
			String id = request.getParameter("id");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			if(id == null || id.trim().isEmpty() || 
				title == null || title.trim().isEmpty() ||
				content == null || content.trim().isEmpty())
			{
				response.getWriter().println(
						"<script>"
						+ "alert('제목과 내용을 모두 입력하세요!');"
						+ "location.href='PostDetatil?id=" + id + "';"
						+ "</script>"
						);
				return;
			}
			
			int id_i = Integer.parseInt(id);
			
			PostService service = new PostService();
			
			boolean check = service.updatePost(title, content, id_i);
			
			if(check)
			{
				response.getWriter().println(
						"<script>"
						+ "alert('게시글 수정 완료!');"
						+ "location.href='PostDetail?id=" + id_i + "';"
						+ "</script>"
						);
			}
			else
			{
				response.getWriter().println(
						"<script>"
						+ "alert('게시글 수정 실패!');"
						+ "location.href=PostDetail?id=" + id_i + "';"
						+ "</script>"
						);
			}
		}
		
	}

}
