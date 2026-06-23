package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DBConnect.DBConnection;
import DTO.PostDTO;

public class PostDAO {
	public boolean insertPost(PostDTO pto)
	{
		String sql = "INSERT INTO "
				+ "posts(member_id,title,content,file_name,is_notice,created_at) VALUES (?,?,?,?,?,NOW())";
		
		try(
			Connection conn = DBConnection.getConnection();
				
			PreparedStatement ps = conn.prepareStatement(sql);
				){
			
			ps.setInt(1, pto.getMember_id());
			ps.setString(2, pto.getTitle());
			ps.setString(3, pto.getContent());
			ps.setString(4, pto.getFile_name());
			ps.setInt(5, pto.getIs_notice());
			
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
	
	public List<PostDTO> selectShowPosts(int offset, int size)
	{
		String sql = "SELECT p.id, p.title, m.name AS post_write, "
				+ "COUNT(c.id) AS comment_count, p.created_at,p.view_count "
				+ "FROM posts p "
				+ "JOIN members m ON p.member_id = m.id "
				+ "LEFT JOIN comments c ON c.post_id = p.id "
				+ "GROUP BY p.id "
				+ "ORDER BY p.is_notice DESC, p.id ASC "
				+ "LIMIT ?,?";
		
		List<PostDTO> list = new ArrayList<>();
		
		try(
			Connection conn = DBConnection.getConnection();
				
			PreparedStatement ps = conn.prepareStatement(sql);
				){
			ps.setInt(1, offset);
			ps.setInt(2, size);
			
			ResultSet rs = ps.executeQuery();

			while(rs.next())
			{
				PostDTO pto = new PostDTO();
				
				pto.setId(rs.getInt("id"));
				pto.setTitle(rs.getString("title"));
				//pto.setContent(rs.getString("content"));
				pto.setPost_writer(rs.getString("post_write"));
				pto.setCreated_at(rs.getString("created_at"));
				pto.setComment_count(rs.getInt("comment_count"));
				pto.setView_count(rs.getInt("view_count"));
				
				list.add(pto);
				
			}
			return list;
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}
	
	public PostDTO selectPostDetail(int id)
	{
		PostDTO pto = null;
		
		String sql = "SELECT p.id, p.member_id, p.title, p.content,p.file_name, "
				+ "m.name AS post_writer, p.created_at "
				+ "FROM posts p "
				+ "JOIN members m ON p.member_id = m.id "
				+ "WHERE p.id = ?";
		
		try(
			Connection conn = DBConnection.getConnection();
				
			PreparedStatement ps = conn.prepareStatement(sql);
				
			
				){
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				pto = new PostDTO();
				
				pto.setId(rs.getInt("id"));
				pto.setMember_id(rs.getInt("member_id"));
				pto.setTitle(rs.getString("title"));
				pto.setContent(rs.getString("content"));
				pto.setFile_name(rs.getString("file_name"));
				pto.setPost_writer(rs.getString("post_writer"));
				pto.setCreated_at(rs.getString("created_at"));
				
				return pto;
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return pto;
	}
	
	public boolean deletePost(int id)
	{
		String sql = "DELETE FROM posts WHERE id = ?";
		
		try(
			Connection conn = DBConnection.getConnection();
				
			PreparedStatement ps = conn.prepareStatement(sql);
				){
			ps.setInt(1, id);
			
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
	
	public boolean updatePost(String title, String content, int post_id)
	{
		String sql = "UPDATE posts SET title = ?, content = ? WHERE id = ?";
		
		try(
			Connection conn = DBConnection.getConnection();
				
			PreparedStatement ps = conn.prepareStatement(sql);
				){
			ps.setString(1, title);
			ps.setString(2, content);
			ps.setInt(3, post_id);
			
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
	
	public boolean UpdatePlusView(int id)
	{
		String sql = "UPDATE posts "
				+ "SET view_count = view_count + 1 "
				+ "WHERE id = ?";
		
		try(
			Connection conn = DBConnection.getConnection();
				
			PreparedStatement ps = conn.prepareStatement(sql);
				){
			ps.setInt(1, id);
			
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
	public List<PostDTO> selectSearchPost(String keyword, String searchType, int offset, int size)
	{	
		String sql = "SELECT p.id, p.title, p.content, m.name AS post_writer, "
				+ "p.created_at, p.view_count "
				+ "FROM posts p "
				+ "JOIN members m ON p.member_id = m.id ";
				
				switch(searchType)
				{
					case "title":
						sql += "WHERE p.title LIKE ? ";
						break;
					case "content":
						sql += "WHERE p.content LIKE ? ";
						break;
					case "writer":
						sql += "WHERE m.name LIKE ? ";
						break;
					case "title_content":
						sql += "WHERE p.title LIKE ? OR p.content LIKE ? ";
						break;
				}

				sql += "ORDER BY p.id ASC ";
				sql += "LIMIT ?,?";
		
		try(
			Connection conn = DBConnection.getConnection();
				
			PreparedStatement ps = conn.prepareStatement(sql);
				){
			if(searchType.equals("title_content"))
			{
				ps.setString(1, "%" + keyword + "%");
				ps.setString(2, "%" + keyword + "%");
				ps.setInt(3, offset);
				ps.setInt(4, size);
			}
			else
			{
				ps.setString(1, "%" + keyword + "%");
				ps.setInt(2, offset);
				ps.setInt(3, size);
			}
			
			ResultSet rs = ps.executeQuery();
			
			List<PostDTO> list = new ArrayList<>();
			
			while(rs.next())
			{
				PostDTO pto = new PostDTO();
				
				pto.setId(rs.getInt("id"));
				pto.setTitle(rs.getString("title"));
				pto.setContent(rs.getString("content"));
				pto.setPost_writer(rs.getString("post_writer"));
				pto.setCreated_at(rs.getString("created_at"));
				pto.setView_count(rs.getInt("view_count"));
				
				list.add(pto);
			}
			return list;
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public int selectPostCount()
	{
		String sql = "SELECT COUNT(*) AS COUNT FROM posts";
		
		try(
			Connection conn = DBConnection.getConnection();
				
			PreparedStatement ps = conn.prepareStatement(sql);
				
			ResultSet rs = ps.executeQuery();
				){
			if(rs.next())
			{
				return rs.getInt("COUNT");
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}
	public int selectSearchPostCount(String keyword)
	{
		String sql = "SELECT COUNT(*) AS COUNT FROM posts WHERE title LIKE ?";
		
		try(
			Connection conn = DBConnection.getConnection();
				
			PreparedStatement ps = conn.prepareStatement(sql);
				){
			ps.setString(1, "%" + keyword + "%");
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				return rs.getInt("COUNT");
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return 0;
	}
}
