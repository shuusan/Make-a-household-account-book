package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class DeleteDAO {
	public static void delete(ArrayList<String> list){
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
				String sql = "DELETE FROM book WHERE RE = "+array[0]+" AND content = '"+array[1]+"' AND price = "+array[2]+" LIMIT 1";
				pstmt = con.prepareStatement(sql);
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
