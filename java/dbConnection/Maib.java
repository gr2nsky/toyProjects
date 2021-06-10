package dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class Maib {

	public static void main(String[] args) {
		Connection conn = DBA();
		
		
	}
	
	static Connection DBA() {
		Connection conn = null;
		final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
		final String DB_URL = "jdbc:mysql://[IP]/[Schema]?characterEncoding=UTF-8&serverTimezone=UTC";
		final String USERNAME = "user";
		final String PASSWORD = "password";
		//mysql 접속
		try {
			//드라이버 로딩
			Class.forName(JDBC_DRIVER);
			//mysql과 연결
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			System.out.println("\n- MySQL Connection");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패");
		}
		catch(Exception e) {
			System.out.println("에러 : " + e);
		} 
		return conn;
	}
}
