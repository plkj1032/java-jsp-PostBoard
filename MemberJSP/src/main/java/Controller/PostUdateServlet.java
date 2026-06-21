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
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		
		PostService service = new PostService();
		
		PostDTO post = service.selectPostDetail(Integer.parseInt(id));
		
		request.setAttribute("post",post);
		
		RequestDispatcher rd = request.getRequestDispatcher("PostUpdate.jsp");
		
		rd.forward(request, response);
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; Charset=UTF-8");
		
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		System.out.println("id : " + id);
		System.out.println("title : " + title);
		System.out.println("content : " + content);
		
		if( id == null || id.trim().isEmpty() ||
			title == null || title.trim().isEmpty() ||
			content  == null || content.trim().isEmpty())
		{
			response.getWriter().println(
					"<script>"
					+ "alert('모든 값 입력!');"
					+ "location.href='index.jsp';"
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
					+ "location.href='PostDetail?id=" + id + "';"
					+ "</script>"
					);
		}
		else
		{
			response.getWriter().println(
					"<script>"
					+ "alert('게시글 수정 실패!');"
					+ "history.back();"
					+ "</script>"
					);
		}
		
		//doGet(request, response);
	}

}
