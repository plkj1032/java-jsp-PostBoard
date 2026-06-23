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
	
	/**
     * 카카오 로그인 유저 검증 및 자동 회원가입 처리 브레인 로직
     */
    public MemberDTO loginOrCreateSnsUser(String snsId, String snsType, String nickname, String email) {
        // 1. 먼저 해당 카카오 계정으로 가입된 기록이 있는지 DB 조회
        MemberDTO user = dao.findBySns(snsId, snsType);
        
        // 2. 가입 기록이 없다면 (신규 소셜 로그인 유저)
        if (user == null) {
            System.out.println("[소셜 회원가입] 첫 방문 유저입니다. 자동 회원가입을 진행합니다.");
            boolean isInserted = dao.insertSnsMember(snsId, snsType, nickname, email);
            
            // 3. 정상적으로 DB에 INSERT 되었다면, 세션에 안겨줄 완벽한 DTO를 만들기 위해 다시 한번 조회
            if (isInserted) {
                user = dao.findBySns(snsId, snsType);
            } else {
                System.out.println("[오류] 소셜 회원가입 실패");
                return null;
            }
        } else {
            System.out.println("[소셜 로그인] 이미 가입된 회원입니다. 즉시 로그인을 승인합니다.");
        }
        
        return user;
    }
}
