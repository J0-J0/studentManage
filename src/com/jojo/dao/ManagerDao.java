package com.jojo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jojo.model.Manager;

public class ManagerDao {

	private Connection conn = null;
	
	public ManagerDao(Connection conn) {
		this.conn = conn;
	}
	/**
	 * 关闭连接
	 */
	public void closeConn(){
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * 根据给定的manager进行查询，返回时带出id
	 * @param manager
	 * @return
	 * @throws SQLException
	 */
	public Manager selectManager(Manager manager) throws SQLException{
		Manager resultManager = null;
		String sql = "select * from t_manager where managerName = ? and password = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, manager.getManagerName());
		pstmt.setString(2, manager.getPassword());
		
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()){
			resultManager = new Manager();
			resultManager.setId(rs.getInt("id"));
			resultManager.setManagerName(rs.getString("managerName"));
			resultManager.setPassword(rs.getString("password"));
		}
		
		return resultManager;
	}
}
