package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertDAO {
	public static void table(String user,String reKey,String contents,int price,String calendar,int year,int month){
		Connection con = null;
		PreparedStatement pstmt = null;
		int rs = 0;
		try{
			int re=("収入".equals(reKey))?0:1;
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/motonote?useSSL=false",
					"adminuser",
					"password");
			String sql = "INSERT INTO book VALUES('"+user+"',?,?,?,?,?,?);";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, re);
			pstmt.setString(2, contents);
			pstmt.setInt(3, price);
			pstmt.setString(4, calendar);
			pstmt.setInt(5, year);
			pstmt.setInt(6, month);
			rs = pstmt.executeUpdate();
			System.out.println(rs);
			con.close();
		} catch (SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static boolean user(String userid,String password,String mail){
		Connection con = null;
		PreparedStatement pstmt = null;
		int rs = 0;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/motonote?useSSL=false",
					"adminuser",
					"password");
			String sql = "INSERT INTO user VALUES(?,?,?);";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setString(2, password);
			pstmt.setString(3, mail);
			rs = pstmt.executeUpdate();
			System.out.println(rs);
			con.close();
		} catch (SQLException e){
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
