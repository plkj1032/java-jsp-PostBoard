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
		String id = request.getParameter("id");
		
		System.out.println(id);
		
		PostService service = new PostService();
		CommentService c_service = new CommentService();
		
		List<CommentDTO> comments = c_service.selectComments(Integer.parseInt(id));
		
		boolean check = service.updatePlusView(Integer.parseInt(id));
		
		PostDTO post = service.selectPostDetail(Integer.parseInt(id));
		
		request.setAttribute("comments",comments);
		request.setAttribute("post", post);
		RequestDispatcher rd = request.getRequestDispatcher("PostDetail.jsp");
		
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
