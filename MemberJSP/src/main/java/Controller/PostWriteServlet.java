package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import DTO.MemberDTO;
import DTO.PostDTO;
import Service.PostService;

/**
 * Servlet implementation class PostWriteServlet
 */
@WebServlet("/PostWrite")
public class PostWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostWriteServlet() {
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
		response.setContentType("text/html; utf-8");
		
		HttpSession session = request.getSession(false);
		
		if(session == null)
		{
			response.getWriter().println(
					"<script>"
					+ "alert('로그인 후 이용 가능!');"
					+ "location.href='index.jsp';"
					+ "</script>"
					);
			return;
		}
		
		MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		if(title == null || title.trim().isEmpty()||
			content == null || content.trim().isEmpty())
		{
			response.getWriter().println(
					"<script>"
					+ "alert('모든 값을 입력하세요!');"
					+ "location.href='index.jsp';"
					+ "</script>"
					);
			return;
		}
		
		PostDTO pto = new PostDTO();
		
		pto.setMember_id(loginUser.getId());
		pto.setTitle(title);
		pto.setContent(content);
		
		PostService service = new PostService();
		
		boolean check = service.insertPost(pto);
		
		if(check)
		{
			response.getWriter().println(
					"<script>"
					+ "alert('게시글 등록 완료!');"
					+ "location.href='PostList';"
					+ "</script>"
					);
		}
		else
		{
			response.getWriter().println(
					"<script>"
					+ "alert('게시글 등록 실패!');"
					+ "history.back();"
					+ "</script>"
					);
		}
		
		//doGet(request, response);
	}

}
