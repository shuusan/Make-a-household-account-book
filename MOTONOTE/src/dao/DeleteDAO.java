package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class DeleteDAO {
	public static void delete(String userid,ArrayList<String> list){
		Connection con = null;
		PreparedStatement pstmt = null;
		int rs = 0;
		try{
			for(int i=0;i<list.size();i++){
				String[] array = list.get(i).split(",");
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/motonote?useSSL=false",
						"adminuser",
						"password");
				String sql = "DELETE FROM book WHERE userid = ? AND RE = ? AND content = ? AND price = ? LIMIT 1";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, userid);
				pstmt.setInt(2, Integer.parseInt(array[0]));
				pstmt.setString(3, array[1]);
				pstmt.setInt(4, Integer.parseInt(array[2]));
				rs = pstmt.executeUpdate();
				System.out.println(rs);
				con.close();
			}
		} catch (SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
