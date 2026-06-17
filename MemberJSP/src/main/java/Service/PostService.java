package Service;

import DTO.PostDTO;

import java.util.List;

import DAO.PostDAO;

public class PostService {
	private PostDAO pao = new PostDAO();
	
	public boolean insertPost(PostDTO pto)
	{
		return pao.insertPost(pto);
	}
	
	public List<PostDTO> selectShowPost(int offset, int size)
	{
		return pao.selectShowPosts(offset, size);
	}
	
	public PostDTO selectPostDetail(int id)
	{
		return pao.selectPostDetail(id);
	}
	
	public boolean deletePost(int id)
	{
		return pao.deletePost(id);
	}
	
	public boolean updatePost(String title, String content, int post_id)
	{
		return pao.updatePost(title, content, post_id);
	}
	public boolean updatePlusView(int id)
	{
		return pao.UpdatePlusView(id);
	}
	public List<PostDTO> selectSeachPost(String keyword, int offset, int size)
	{
		return pao.selectSearchPost(keyword,offset,size);
	}
	public int PostCount()
	{
		return pao.selectPostCount();
	}
	public int SearchPostCount(String keyword)
	{
		return pao.selectSearchPostCount(keyword);
	}
}
