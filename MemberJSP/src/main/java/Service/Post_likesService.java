package Service;

import DAO.Post_likesDAO;

public class Post_likesService {
	
	private Post_likesDAO plo = new Post_likesDAO();
	
	public boolean insertPostLikes(int member_id, int post_id)
	{
		return plo.insertPost_Likes(member_id, post_id);
	}
	
	public boolean deletePostLikes(int member_id, int post_id)
	{
		return plo.deletePostLikes(member_id, post_id);
	}
	
	public boolean selectCheckLikes(int member_id, int post_id)
	{
		return plo.selectCheckLikes(member_id, post_id);
	}
	
	public int selectPostLikesCount(int post_id)
	{
		return plo.selectPostLikesCount(post_id);
	}
}
