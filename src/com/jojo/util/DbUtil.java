package com.jojo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {

	/**
	 * 获取数据库连接
	 * @return
	 */
	public static Connection getConn(){
		String driverName = "com.mysql.jdbc.Driver";
		String user = "root";
		String password = "123456";
		String url = "jdbc:mysql://127.0.0.1:3306/db_student";
		
		Connection conn = null;
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	/*public static void main(String[] args) throws Exception {
		Connection conn = getConn();
		String sql = "select * from t_user where userName = ? and password = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, "test");
		pstmt.setString(2, "123456");
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()){
			System.out.println(rs.getString("userName"));
			System.out.println(rs.getString("password"));
		}
	}*/
}
