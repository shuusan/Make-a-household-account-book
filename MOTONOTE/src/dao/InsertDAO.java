package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertDAO {
	public static void table(String reKey,String contents,int price,String calender,int year,int month){
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
			String sql = "INSERT INTO food VALUES('syu1',?,?,?,?,?,?,?);";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, re);
			pstmt.setString(2, contents);
			pstmt.setInt(3, price);
			pstmt.setString(4, calender);
			pstmt.setInt(5, year);
			pstmt.setInt(6, month);
			pstmt.setInt(7, 0);
			rs = pstmt.executeUpdate();
			System.out.println(rs);
			con.close();
		} catch (SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
