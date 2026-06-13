package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; UTF-8");
		
		String id = request.getParameter("id");
		
		PostService service = new PostService();
		
		boolean check = service.deletePost(Integer.parseInt(id));
		
		if(check)
		{
			response.getWriter().println(
					"<script>"
					+ "alert('게시글 삭제 완료!');"
					+ "location.href='index.jsp';"
					+ "</script>"
					);
		}
		else
		{
			response.getWriter().println(
					"<script>"
					+ "alert('게시글 삭제 실패!');"
					+ "location.href='index.jsp';"
					+ "</script>"
					);
		}
		
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
