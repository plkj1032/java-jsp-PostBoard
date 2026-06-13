package Controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import DTO.PostDTO;
import Service.PostService;

/**
 * Servlet implementation class PostListServlet
 */
@WebServlet("/PostList")
public class PostListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		PostService service = new PostService();
		
		List<PostDTO> posts = service.selectShowPost();
		
		if(posts == null || posts.isEmpty())
		{
			response.getWriter().println(
					"<script>"
					+ "alert('등록된 게시글이 없습니다!');"
					+ "location.href='index.jsp';"
					+ "</script>"
					);
			return;
		}
		for( PostDTO pto : posts)
		{
			System.out.printf("ID : \n",pto.getId());
			System.out.printf("Title : \n",pto.getTitle());
			System.out.printf("Content : \n",pto.getContent());
			System.out.printf("Post_writer : \n",pto.getPost_writer());
			System.out.printf("Created_at : \n",pto.getCreated_at());
		}
		
		request.setAttribute("posts",posts);
		
		RequestDispatcher rd =
				request.getRequestDispatcher("PostList.jsp");
		
		
		System.out.println("게시글 수 : " + posts.size());
		
		rd.forward(request, response);
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}
