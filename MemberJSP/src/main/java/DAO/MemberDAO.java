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
				loginUser.setRole(rs.getString("role"));
				
				return loginUser;
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return loginUser;
	}
	
	// 1. 카카오 고유 ID로 기존에 가입된 회원 정보가 있는지 조회
    public MemberDTO findBySns(String snsId, String snsType) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        MemberDTO dto = null;

        String sql = "SELECT * FROM members WHERE sns_id = ? AND sns_type = ?";

        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, snsId);
            pstmt.setString(2, snsType);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                dto = new MemberDTO();
                dto.setId(rs.getInt("id"));
                dto.setName(rs.getString("name"));
                dto.setAge(rs.getInt("age"));
                dto.setEmail(rs.getString("email"));
                dto.setPassword(rs.getString("password"));
                dto.setSnsId(rs.getString("sns_id"));
                dto.setSnsType(rs.getString("sns_type"));
                dto.setRole(rs.getString("role"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll(rs, pstmt, conn);
        }
        return dto;
    }
	
 // 2. 카카오로 처음 로그인한 신규 유저를 로컬 DB에 자동 회원가입(INSERT)
    public boolean insertSnsMember(String snsId, String snsType, String nickname, String email) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean isSuccess = false;

        // name에는 카카오 닉네임, email에는 카카오 이메일 바인딩
        // password는 NULL 허용이므로 명시적으로 지정하지 않거나 생략 (DB 구조 충돌 방지)
        String sql = "INSERT INTO members (name, email, sns_id, sns_type, role) VALUES (?, ?, ?, ?, 'USER')";

        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nickname);
            pstmt.setString(2, email);
            pstmt.setString(3, snsId);
            pstmt.setString(4, snsType);

            int result = pstmt.executeUpdate();
            if (result > 0) {
                isSuccess = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll(null, pstmt, conn);
        }
        return isSuccess;
    }
    
 // 자원 해제 공통 메서드
    private void closeAll(ResultSet rs, PreparedStatement pstmt, Connection conn) {
        try { if (rs != null) rs.close(); } catch (Exception e) {}
        try { if (pstmt != null) pstmt.close(); } catch (Exception e) {}
        try { if (conn != null) conn.close(); } catch (Exception e) {}
    }
}
