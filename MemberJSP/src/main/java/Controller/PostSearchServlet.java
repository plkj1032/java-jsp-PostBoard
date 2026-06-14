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
 * Servlet implementation class PostSearchServlet
 */
@WebServlet("/PostSearch")
public class PostSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostSearchServlet() {
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
		
		String keyword = request.getParameter("keyword");
		
		PostService service = new PostService();
		
		List<PostDTO> list = service.selectSeachPost(keyword);
		
		if(list == null || list.isEmpty())
		{
			response.getWriter().println(
					"<script>"
					+ "alert('조회된 게시글이 없음!');"
					+ "location.href='index.jsp';"
					+ "</script>"
					);
			return;
		}
		
		request.setAttribute("posts",list);
		RequestDispatcher rd = request.getRequestDispatcher("PostSearch.jsp");
		
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
