package Service;

import java.util.List;

import DAO.CommentDAO;
import DTO.CommentDTO;

public class CommentService {
	
	CommentDAO cao = new CommentDAO();
	
	public boolean insertComments(CommentDTO cto)
	{
		return cao.insertComment(cto);
	}
	public List<CommentDTO> selectComments(int post_id)
	{
		return cao.selectComments(post_id);
	}
}
