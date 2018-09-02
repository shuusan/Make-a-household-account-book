package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class UpdateDAO {
	public static void update(String userid,ArrayList<String> list){
		Connection con = null;
		PreparedStatement pstmt = null;
		@SuppressWarnings("unused")
		int rs = 0;
		try{
			int j = 0;
			for(int i=0;i<list.size()/2;i++){
				String[] array1 = list.get(j).split(",");
				String[] array2 = list.get(j+1).split(",");
				j=j+2;
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/motonote?useSSL=false",
						"adminuser",
						"password");
				String sql = "UPDATE book set RE = ?, content = ?, price = ? WHERE userid = ? AND RE = ? AND content = ? AND price = ? LIMIT 1;";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(array2[0]));
				pstmt.setString(2, array2[1]);
				pstmt.setInt(3, Integer.parseInt(array2[2]));
				pstmt.setString(4, userid);
				pstmt.setInt(5, Integer.parseInt(array1[0]));
				pstmt.setString(6, array1[1]);
				pstmt.setInt(7, Integer.parseInt(array1[2]));
				rs = pstmt.executeUpdate();
				con.close();
			}
		} catch (SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
