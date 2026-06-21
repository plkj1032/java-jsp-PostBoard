package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import Service.CommentService;

/**
 * Servlet implementation class CommentDeleteServlet
 */
@WebServlet("/CommentDelete")
public class CommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentDeleteServlet() {
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
		response.setContentType("text/html; UTF-8");
		
		
		String id = request.getParameter("c_id");
		String post_id = request.getParameter("post_id");
		System.out.println("p_id : " + post_id);
		int p_id = Integer.parseInt(post_id);
		System.out.println("p_id 2: " + post_id);
		System.out.println("id : " + id);
		
		CommentService service = new CommentService();
		
		boolean check = service.deleteComment(Integer.parseInt(id));
		
		if(check)
		{
			response.sendRedirect("PostDetail?id=" + p_id);
		}
		//doGet(request, response);
	}

}
