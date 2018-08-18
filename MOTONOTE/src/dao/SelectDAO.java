package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.SelectDTO;
public class SelectDAO {
//tableの全取得
	public static ArrayList<SelectDTO> table(int key){
		ArrayList<SelectDTO> resultList = new ArrayList<SelectDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/motonote?useSSL=false",
					"adminuser",
					"password");
			String sql = "SELECT userid,RE,content,price FROM food where userid = 'syu1' AND month = '"+key+"'";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			do{
				int re = rs.getInt("RE");
				String content = rs.getString("content");
				int price= rs.getInt("price");
				resultList.add(new SelectDTO(re,content,price));
			}while(rs.next() == true );
			con.close();
		} catch (SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return resultList;
	}
}
