package Controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import DTO.CommentDTO;
import DTO.PostDTO;
import Service.CommentService;
import Service.PostService;
import Service.Post_likesService;

/**
 * Servlet implementation class PostDetailServlet
 */
@WebServlet("/PostDetail")
public class PostDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doHandle(request,response);
		
//		String id = request.getParameter("id");
//		
//		System.out.println(id);
//		
//		PostService service = new PostService();
//		CommentService c_service = new CommentService();
//		
//		List<CommentDTO> comments = c_service.selectComments(Integer.parseInt(id));
//		
//		boolean check = service.updatePlusView(Integer.parseInt(id));
//		
//		PostDTO post = service.selectPostDetail(Integer.parseInt(id));
//		
//		// 좋아요 갯수 추가!
//		Post_likesService p_service = new Post_likesService();
//		int likes_count = p_service.selectPostLikesCount(Integer.parseInt(id));
//		
//		request.setAttribute("likes_count", likes_count);
//		request.setAttribute("comments",comments);
//		request.setAttribute("post", post);
//		RequestDispatcher rd = request.getRequestDispatcher("PostDetail.jsp");
//		
//		rd.forward(request, response);
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		doHandle(request,response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String method = request.getMethod();
		
		if(method.equals("GET"))
		{
			String id = request.getParameter("id");
			
			int id_i = Integer.parseInt(id);
			
			PostService service = new PostService();
			CommentService c_service = new CommentService();
			
			List<CommentDTO> comments = c_service.selectComments(id_i);
			
			boolean check = service.updatePlusView(id_i);
			
			PostDTO post = service.selectPostDetail(id_i);
			
			Post_likesService p_service = new Post_likesService();
			int likes_count = p_service.selectPostLikesCount(id_i);
			
			request.setAttribute("likes_count", likes_count);
			request.setAttribute("comments", comments);
			request.setAttribute("post",post);
			
			RequestDispatcher rd = request.getRequestDispatcher("PostDetail.jsp");
			
			rd.forward(request, response);
			
		}
	}

}
