package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import DBConnect.DBConnection;
import DTO.MemberDTO;
import Util.PasswordUtil;

public class MemberDAO {
	public boolean checkEmail(String email)
	{
		String sql = "SELECT * FROM members WHERE email = ?";
		
		try(
			Connection conn = DBConnection.getConnection();
				
			PreparedStatement ps = conn.prepareStatement(sql);
				){
			ps.setString(1, email);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				return true;
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	
	public boolean insertMember(MemberDTO dto)
	{
		String sql = "insert into "
				+ "members(name,age,email,password) VALUES (?,?,?,?)";
		
		try(
			Connection conn = DBConnection.getConnection();
				
			PreparedStatement ps = conn.prepareStatement(sql);
				){
			ps.setString(1,dto.getName());
			ps.setInt(2, dto.getAge());
			ps.setString(3, dto.getEmail());
			String hashPw = PasswordUtil.encrypt(dto.getPassword());
			ps.setString(4, hashPw);
			
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
	
	public MemberDTO selectLoginUser(String email,String password)
	{
		MemberDTO loginUser = null;
		
		String sql = "SELECT * FROM members WHERE email = ? AND password = ?";
		
		try(
			Connection conn = DBConnection.getConnection();
				
			PreparedStatement ps = conn.prepareStatement(sql);
				)
		{
			ps.setString(1,email);
			
			String hashPw = PasswordUtil.encrypt(password);
			ps.setString(2,hashPw);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				loginUser = new MemberDTO();
				
				loginUser.setId(rs.getInt("id"));
				loginUser.setName(rs.getString("name"));
				loginUser.setAge(rs.getInt("age"));
				loginUser.setEmail(rs.getString("email"));
				
				return loginUser;
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return loginUser;
	}
}
