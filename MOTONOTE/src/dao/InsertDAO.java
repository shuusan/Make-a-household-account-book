package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class InsertDAO {
	public static void table(String reKey,String contents,int price,String calendar,int year,int month){
		Connection con = null;
		PreparedStatement pstmt = null;
		int rs = 0;
		try{
			int re=("収入".equals(reKey))?0:1;
			SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdFormat.parse(calendar);
			Calendar calendar1 = Calendar.getInstance();
			calendar1.setTime(date);
			int weekNumber=calendar1.get(Calendar.WEEK_OF_MONTH);
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
			pstmt.setString(4, calendar);
			pstmt.setInt(5, year);
			pstmt.setInt(6, month);
			pstmt.setInt(7, weekNumber);
			rs = pstmt.executeUpdate();
			System.out.println(rs);
			con.close();
		} catch (SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
