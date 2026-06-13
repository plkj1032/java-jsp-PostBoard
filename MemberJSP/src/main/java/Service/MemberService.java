package Service;

import DTO.MemberDTO;

import DAO.MemberDAO;

public class MemberService {
	private MemberDAO dao = new MemberDAO();
	
	public boolean insertMember(MemberDTO dto)
	{
		if(!dao.checkEmail(dto.getEmail()))
		{
			return dao.insertMember(dto);
		}
		else
		{
			return false;
		}
	}
	
	public MemberDTO loginUser(String email,String password)
	{
		return dao.selectLoginUser(email, password);
	}
}
