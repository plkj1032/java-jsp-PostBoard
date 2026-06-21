package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import Service.CommentService;

/**
 * Servlet implementation class CommentUpdateServlet
 */
@WebServlet("/CommentUpdate")
public class CommentUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentUpdateServlet() {
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
		
		String current_id = request.getParameter("c_id");
		String content = request.getParameter("content");
		String post_id = request.getParameter("post_id");
		
		CommentService service = new CommentService();
		
		boolean check = service.updateComment(Integer.parseInt(current_id), content);
		
		if(check)
		{
			response.sendRedirect("PostDetail?id=" + Integer.parseInt(post_id));
		}
		
		//doGet(request, response);
	}

}
