package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.SelectDTO;
public class SelectDAO {
	//表示するtableの全取得
	public static ArrayList<SelectDTO> table(int key,int key2){
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
			String sql = "SELECT userid,RE,content,price,calender "
					+ "FROM book where userid = 'syu1' AND month = '"+key+"' AND year = '"+key2+"' "
					+ "ORDER BY RE ASC, calender DESC";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			do{
				int re = rs.getInt("RE");
				String content = rs.getString("content");
				int price= rs.getInt("price");
				String calender = rs.getString("calender");
				resultList.add(new SelectDTO(re,content,price,calender));
			}while(rs.next() == true );
			con.close();
		} catch (SQLException e){

			if(rs==null){
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return resultList;
	}
	public static SelectDTO cost(int key,int key2){
		SelectDTO result = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int income=0;
		int spending=0;
		int sum=0;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/motonote?useSSL=false",
					"adminuser",
					"password");
			String sql = "SELECT userid,RE,content,price FROM book where userid = 'syu1' AND month = '"+key+"' AND year = '"+key2+"'";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			do{
				int re = rs.getInt("RE");
				int price= rs.getInt("price");
				if(re==0){
					income += price;
				}else{
					spending +=price;
				}
			}while(rs.next() == true );
			sum=income-spending;
			result = new SelectDTO(sum, spending, income);
			con.close();
		} catch (SQLException e){

			if(rs==null){
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static boolean Login(String user, String pass){
		boolean flg=false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/motonote?useSSL=false",
					"adminuser",
					"password");
			String sql = "SELECT * FROM user WHERE user = ? AND password = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user);
			pstmt.setString(2, pass);
			rs = pstmt.executeQuery();
			rs.next();
			if(user.equals(rs.getString("user"))&&pass.equals(rs.getString("password")))
				flg=true;
			con.close();
		} catch (SQLException e){
			e.printStackTrace();
			return flg;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return flg;
		}catch(NullPointerException e){
			e.printStackTrace();
			return flg;
		}
		return flg;
	}
}
