import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Querys {

	//중복검사 : 해당 아이디가 존재하는가?false:ture
	public static boolean checkId(Connection conn, String userid) {
		PreparedStatement psmt = null;
	    ResultSet rs = null;
        String sql = "SELECT * FROM user WHERE xid = ?";
        int row = -1;
        try {
        	psmt = conn.prepareStatement(sql);
	        psmt.setString(1, userid);
	        rs = psmt.executeQuery();
	        
	        if(rs.next()) {
	        	row = rs.getRow();
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
        if(row != 1)
        	return true;
        return false;
	}
	//계정 insert
	public static void insertAccount(Connection conn, String xid, String xpw) {
		PreparedStatement psmt = null;
		String sql = "INSERT INTO user(xid, xpw) VALUES(?, ?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, xid);
			psmt.setString(2, xpw);
			psmt.executeUpdate();
			psmt.close();
			}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	//로그인
	public static boolean loginAccount(Connection conn, String xid, String xpw) {
		PreparedStatement psmt = null;
		String sql = "SELECT * FROM user WHERE xid = ? AND xpw = ?";
		ResultSet rs;
		int row = 0;
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, xid);
			psmt.setString(2, xpw);
			rs = psmt.executeQuery();
	        if(rs.next()) {
	        	row = rs.getRow();
	        }
		}catch (SQLException e) {
			e.printStackTrace();
		}
		if(row == 1)
			return true;
		return false;
	}
	//나만의팀 불러오기
	public static String readMyXII(Connection conn, String xid) {
		PreparedStatement psmt = null;
		String sql = "SELECT * FROM user WHERE xid = ?";
		ResultSet rs;
		String result = null;
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, xid);
			rs = psmt.executeQuery();
			if(rs.next()) {
				result = rs.getString("mPlayer");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	//나만의 팀 초기화
	public static void initMyXII(Connection conn, String xid) {
		PreparedStatement psmt = null;
		String sql = "UPDATE user SET mPlayer = ? WHERE xid = ?";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, null);
			psmt.setString(2, xid);
			psmt.executeUpdate();
			psmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	//나만의 팀 저장
	public static void saveMyXII(Connection conn, String xid, String data) {
		PreparedStatement psmt = null;
		String sql = "UPDATE user SET mPlayer = ? WHERE xid = ?";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, data);
			psmt.setString(2, xid);
			psmt.executeUpdate();
			psmt.close();
		}catch(SQLException e) {
			System.out.println("데이터 저장 실패");
		}
		System.out.println("데이터 저장 완료");
	}
	//모든 사용자들의 bestXII을 불러와 출력한다.
	public static String pop(Connection conn) {
		PreparedStatement psmt = null;
	    ResultSet rs = null;
	    String result = "";
	    String tmp;
        String sql = "SELECT mPlayer FROM user";
        
        try {
        	psmt = conn.prepareStatement(sql);
        	rs = psmt.executeQuery();
        	while(rs.next()) {
        		tmp = rs.getString("mPlayer");
        		result +=tmp+",";
        	}
        	result.substring(0,	result.length()-1);
        	
        }catch(SQLException e) {
        	e.printStackTrace();
        }
        return result;
	}
}
