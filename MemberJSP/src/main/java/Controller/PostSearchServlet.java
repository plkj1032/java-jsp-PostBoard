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
		doHandle(request,response);
		
//		request.setCharacterEncoding("UTF-8");
//		response.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html; UTF-8");
//		
//		String keyword = request.getParameter("keyword");
//		
//		
//		// 사용자가 이동하는 페이지!
//		int page = 1;
//		String pageParam = request.getParameter("pageParam");
//		
//		if(pageParam != null)
//		{
//			page = Integer.parseInt(pageParam);
//		}
//		
//		// 사용자 원하는 게시글 갯수!
//		int size = 10;
//		String sizeParam = request.getParameter("sizeParam");
//		
//		if(sizeParam != null)
//		{
//			size = Integer.parseInt(sizeParam);
//		}
//		
//		int offset = (page - 1) * size;
//
//		PostService service = new PostService();
//		
//		// 검색된 게시글의 총 갯수
//		int totalCount = service.SearchPostCount(keyword);
//		
//		int totalPage = (int)Math.ceil((double)totalCount/size);
//		
//		List<PostDTO> list = service.selectSeachPost(keyword,offset,size);
//		
//		if(list == null || list.isEmpty())
//		{
//			response.getWriter().println(
//					"<script>"
//					+ "alert('조회된 게시글이 없음!');"
//					+ "location.href='index.jsp';"
//					+ "</script>"
//					);
//			return;
//		}
//		
//		request.setAttribute("keyword",keyword);
//		request.setAttribute("size", size);
//		request.setAttribute("currentPage",page);
//		request.setAttribute("totalPage",totalPage);
//		
//		request.setAttribute("posts",list);
//		RequestDispatcher rd = request.getRequestDispatcher("PostSearch.jsp");
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
			String keyword = request.getParameter("keyword");
			String pageParam = request.getParameter("pageParam");
			String sizeParam = request.getParameter("sizeParam");
			
			// 검색 기능 강화!
			String searchType = request.getParameter("searchType");
			
			System.out.println(searchType);
			
			int page = 1;
			
			if(pageParam != null)
			{
				page = Integer.parseInt(pageParam);
			}
			
			int size = 10;
			
			if(sizeParam != null)
			{
				size = Integer.parseInt(sizeParam);
			}
			
			int offset = (page - 1) * size;
			
			PostService service = new PostService();
			
			int totalCount = service.SearchPostCount(keyword);
			int totalPage = (int)Math.ceil((double)totalCount / size);
			
			List<PostDTO> list = service.selectSeachPost(keyword, searchType, offset, size);
			
			if(list == null || list.isEmpty())
			{
				response.getWriter().println(
						"<script>"
						+ "alert('등록된 게시글 없음!');"
						+ "location.href='index.jsp';"
						+ "</script>"
						);
				return;
			}
			
			request.setAttribute("keyword", keyword);
			request.setAttribute("size", size);
			request.setAttribute("currentPage", page);
			request.setAttribute("totalPage",totalPage);
			
			request.setAttribute("posts",list);
			RequestDispatcher rd = request.getRequestDispatcher("PostSearch.jsp");
			rd.forward(request, response);
			
		}
	}

}
