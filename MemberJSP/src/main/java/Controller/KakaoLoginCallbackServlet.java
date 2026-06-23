package Controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import DTO.MemberDTO;
import Service.MemberService;

//카카오 디벨로퍼스에 등록한 Redirect URI 주소, 로그인 완료시 이 주소로 사용자를 돌려보냄
@WebServlet("/callback/kakao")
public class KakaoLoginCallbackServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private MemberService memberService = new MemberService();
    
    /* 
     * 처음에는 doGet/doPost 나누어서 구현했었는데
     * doHandle로 수정했고 작동하는것도 확인함
     */
    // 💡 HTTP GET 요청 유입 시 -> doHandle 통합 메서드로 연결
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doHandle(request, response);
    }

    // 💡 HTTP POST 요청 유입 시 -> doHandle 통합 메서드로 연결
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doHandle(request, response);
    }

    /**
     * 🚀 [구조 개선] 모든 카카오 로그인 비즈니스 로직을 하나로 일원화 처리하는 마스터 메서드
     */
    private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {    	
    	/* 
    	 * 인가코드 : 사용자가 카카오톡 ID/PW 입력했을때 정보가 맞을 경우 바로 액세스 토큰을
    	 * 발급하는 것이 아닌 인가 코드를 발급함
    	 * 이후 사용자가 이 코드르 들고 kakaologincallbackservlet으로 찾아오면
    	 * 서버에서 이 인가코드와 REST API를 챙겨서 카카오에게 찾아감
    	 * 이 두가지가 모두 유효하면 액세스토큰(유저 정보에 접근할 수 있는 권한)을 발급
    	 * 그렇다면 why?
    	 * => 보안 때문! 바로 액세스 토큰을 발급하면 해킹 우려
    	 * 인가코드와 REST API키가 둘 다 있어야 진짜 액세스 토큰으로 바꿀 수 있다면 훨씬 안전 
    	 */
        // 카카오가 보낸 인가 코드를 꺼냄
    	String code = request.getParameter("code");
        
        if (code != null) {
            // 인가 코드로 토큰 요청
            String accessToken = getAccessToken(code);
            
            if (accessToken != null) {
                // 토큰으로 사용자 정보 추출 및 DB 저장 처리 연동
                MemberDTO loginUser = getKakaoUserInfo(accessToken);
                
                if (loginUser != null) {
                    // 로그인 성공 시 세션 바구니에 저장
                    HttpSession session = request.getSession();
                    session.setAttribute("loginUser", loginUser);
                    
                    System.out.println("[로그인 성공] " + loginUser.getName() + " 유저 세션 부여 완료");
                    response.sendRedirect(request.getContextPath() + "/index.jsp");
                    return;
                }
            }
        }
        
        // 인증 절차 에러 시 로그인 화면으로 복귀시키며 파라미터 전송
        response.sendRedirect(request.getContextPath() + "/login.jsp?error=kakao_fail");
    }
    
    private String getAccessToken(String code) {
        String accessToken = "";
        String reqURL = "https://kauth.kakao.com/oauth/token"; //카카오 토큰 발급 서버 주소
        
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            
            // ⚠️ 본인의 REST API 키값 입력이 필요합니다.
            String client_id = "02c9a21784832eda4f5fc3bf4fcda038"; 
            String redirect_uri = "http://localhost:8080/MemberJSP/callback/kakao"; 
            
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=").append(client_id);
            sb.append("&redirect_uri=").append(redirect_uri);
            sb.append("&code=").append(code);
            
            java.io.OutputStream os = conn.getOutputStream();
            os.write(sb.toString().getBytes("UTF-8"));
            os.flush();
            os.close();
            
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                String line = "";
                StringBuilder result = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    result.append(line);
                }
                br.close();
                
                JsonObject jsonObject = JsonParser.parseString(result.toString()).getAsJsonObject();
                accessToken = jsonObject.get("access_token").getAsString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accessToken;
    }

    private MemberDTO getKakaoUserInfo(String accessToken) {
        String reqURL = "https://kapi.kakao.com/v2/user/me";
        MemberDTO userDto = null;
        
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + accessToken);
            
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                String line = "";
                StringBuilder result = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    result.append(line);
                }
                br.close();
                
                JsonObject jsonObject = JsonParser.parseString(result.toString()).getAsJsonObject();
                
                String snsId = jsonObject.get("id").getAsString();
                String snsType = "kakao";
                
                JsonObject properties = jsonObject.getAsJsonObject("properties");
                String nickname = properties.get("nickname").getAsString();
                
                String email = null;
                if (jsonObject.has("kakao_account")) {
                    JsonObject kakaoAccount = jsonObject.getAsJsonObject("kakao_account");
                    if (kakaoAccount.has("email")) {
                        email = kakaoAccount.get("email").getAsString();
                    }
                }
                
                System.out.println("[카카오 파싱 완료] ID: " + snsId + ", 닉네임: " + nickname + ", 이메일: " + email);
                
                // 정비된 파라미터 순서에 알맞게 비즈니스 브레인 연동 호출
                userDto = memberService.loginOrCreateSnsUser(snsId, snsType, nickname, email);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userDto;
    }
}