package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertDAO {
	//追加登録SQL　table(ユーザーID、収入(0) or 収支(1)、内容、コスト、日時、年、月)
	public static void table(String user,String reKey,String contents,int price,String calendar,int year,int month){
		Connection con = null;
		PreparedStatement pstmt = null;
		@SuppressWarnings("unused")
		int rs = 0;
		try{
			int re=Integer.parseInt(reKey);
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/motonote?useSSL=false",
					"adminuser",
					"password");
			String sql = "INSERT INTO book VALUES(?,?,?,?,?,?,?);";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user);
			pstmt.setInt(2, re);
			pstmt.setString(3, contents);
			pstmt.setInt(4, price);
			pstmt.setString(5, calendar);
			pstmt.setInt(6, year);
			pstmt.setInt(7, month);
			rs = pstmt.executeUpdate();
			con.close();
		} catch (SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	//ユーザー登録SQL　user(ユーザーID、パスワード、メールアドレス)
	public static boolean user(String userid,String password,String mail){
		Connection con = null;
		PreparedStatement pstmt = null;
		@SuppressWarnings("unused")
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
