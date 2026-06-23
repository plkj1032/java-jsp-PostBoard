package DBConnect;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	public static Connection getConnection() throws Exception {
		// 1. MySQL 드라이버 로드
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		// 2. DB 연결 URL (뒤에 데이터베이스 이름인 codemate_db와 필수 옵션들을 추가했습니다)
		String url = "jdbc:mysql://localhost:3306/member";
		
		// 3. Workbench에서 생성한 계정 정보
		String user = "root";
		String password = "mysql00";
		
		// 4. 연결 객체 반환
		return DriverManager.getConnection(url, user, password);
	}
}