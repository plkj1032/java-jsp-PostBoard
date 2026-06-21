package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import DBConnect.DBConnection;
import DTO.Post_likesDTO;

public class Post_likesDAO {
	public boolean insertPost_Likes(int member_id, int post_id)
	{
		String sql = "INSERT INTO post_likes(member_id,post_id) VALUES(?,?)";
		
		try(
			Connection conn = DBConnection.getConnection();
				
			PreparedStatement ps = conn.prepareStatement(sql);
				){
			ps.setInt(1, member_id);
			ps.setInt(2, post_id);
			
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
	
	public boolean deletePostLikes(int member_id, int post_id)
	{
		String sql = "DELETE FROM post_likes WHERE member_id = ? AND post_id = ?";
		
		try(
			Connection conn = DBConnection.getConnection();
				
			PreparedStatement ps = conn.prepareStatement(sql);
				){
			ps.setInt(1, member_id);
			ps.setInt(2, post_id);
			
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
	
	public boolean selectCheckLikes(int member_id, int post_id)
	{
		String sql = "SELECT COUNT(*) AS like_count "
				+ "FROM post_likes "
				+ "WHERE member_id = ? AND post_id = ?";
		
		try(
			Connection conn = DBConnection.getConnection();
				
			PreparedStatement ps = conn.prepareStatement(sql);
				){
			
			ps.setInt(1, member_id);
			ps.setInt(2, post_id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				if(rs.getInt("like_count") > 0)
				{
					return true;
				}
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public int selectPostLikesCount(int post_id)
	{
		String sql = "SELECT COUNT(*) count FROM post_likes WHERE post_id = ?";
		
		try(
			Connection conn = DBConnection.getConnection();
				
			PreparedStatement ps = conn.prepareStatement(sql);
				){
			
			ps.setInt(1, post_id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				return rs.getInt("count");
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}
	
}
