package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DBConnect.DBConnection;
import DTO.CommentDTO;

public class CommentDAO {
	public boolean insertComment(CommentDTO cto)
	{
		String sql = "INSERT INTO comments(member_id,post_id,content) VALUES(?,?,?)";
		
		try(
			Connection conn = DBConnection.getConnection();
				
			PreparedStatement ps = conn.prepareStatement(sql);
				){
			ps.setInt(1, cto.getMember_id());
			ps.setInt(2, cto.getPost_id());
			ps.setString(3, cto.getContent());
			
			int result = ps.executeUpdate();
			
			if(result > 0)
			{
				return true;
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	public List<CommentDTO> selectComments(int post_id)
	{
		String sql = "SELECT c.id,c.post_id, c.member_id, c.content, c.created_at, m.name AS writer "
				+ "FROM comments c "
				+ "JOIN members m ON m.id = c.member_id "
				+ "WHERE c.post_id = ? "
				+ "ORDER BY c.id ASC";
		
		try(
			Connection conn = DBConnection.getConnection();
				
			PreparedStatement ps = conn.prepareStatement(sql);
				){
			ps.setInt(1, post_id);
			
			ResultSet rs = ps.executeQuery();
			
			List<CommentDTO> list = new ArrayList<>();
			
			while(rs.next())
			{
				CommentDTO cto = new CommentDTO();
				
				cto.setId(rs.getInt("id"));
				cto.setPost_id(rs.getInt("post_id"));
				cto.setMember_id(rs.getInt("member_id"));
				cto.setContent(rs.getString("content"));
				cto.setCreated_at(rs.getString("created_at"));
				cto.setWriter(rs.getString("writer"));
				
				list.add(cto);
			}
			return list;
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	public boolean updateComment(int id, String content)
	{
		String sql = "UPDATE comments SET content = ? WHERE id = ?";
		
		try(
			Connection conn = DBConnection.getConnection();
				
			PreparedStatement ps = conn.prepareStatement(sql);
				){
			ps.setString(1, content);
			ps.setInt(2, id);
			
			int result = ps.executeUpdate();
			
			if(result > 0)
			{
				return true;
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteComment(int id)
	{
		String sql = "DELETE FROM comments WHERE id = ?";
		
		try(
			Connection conn = DBConnection.getConnection();
				
			PreparedStatement ps = conn.prepareStatement(sql);
				){
			ps.setInt(1,id);
			
			int result = ps.executeUpdate();
			
			if(result > 0)
			{
				return true;
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return false;
	}
}
