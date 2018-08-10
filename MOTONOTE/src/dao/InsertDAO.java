package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertDAO {
	public static void table(String key1,String key2,int key3,String key4){
		Connection con = null;
		PreparedStatement pstmt = null;
		int rs = 0;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/motonote?useSSL=false",
					"adminuser",
					"password");
			String sql = "INSERT INTO food VALUES('syu1',?,?,?,?,?);";
			pstmt = con.prepareStatement(sql);
			int re=("収入".equals(key1))?0:1;
			pstmt.setInt(1, re);
			pstmt.setString(2, key2);
			pstmt.setInt(3, key3);
			pstmt.setString(4, key4);
//			String[] num = key4.split("-");
//			Calendar cal = Calendar.getInstance();
//			cal.get(Calendar.WEEK_OF_MONTH);
			pstmt.setInt(5, 1);
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
