package com.jojo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jojo.model.User;

public class UserDao {

	private Connection conn = null;

	/**
	 * 构造，采用类连接范围
	 * 
	 * @param conn
	 */
	public UserDao(Connection conn) {
		super();
		this.conn = conn;
	}

	/**
	 * 关闭连接
	 */
	public void closeConn() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 增加用户，返回用户ID
	 * @param user
	 * @return
	 * @throws SQLException 
	 */
	public User addUser(User user) throws SQLException{
		String sql = "insert into t_user values(null, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getPassword());
		int rows = pstmt.executeUpdate();
		
		
		User resultUser = null;
		if(rows == 1){
			resultUser = selectUser(user);
		}
		return resultUser;
	}
	
	/**
	 * 查找用户，要是找到就返回一个查找到的User，没有返回null
	 * 
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	public User selectUser(User user) throws SQLException {
		User resultUser = null;
		// 查找用户
		String sql = "select * from t_user where userName = ? and password = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getPassword());

		ResultSet rs = pstmt.executeQuery();

		// 要找也只会找到一个，所以这边就用if了
		//返回时带出id
		if (rs.next()) {
			resultUser = new User();
			resultUser.setId(rs.getInt("id"));
			resultUser.setUserName(rs.getString("userName"));
			resultUser.setPassword(rs.getString("password"));
		}

		return resultUser;
	}
	
	/**
	 * 修改用户名及密码
	 * @param user
	 * @return
	 * @throws SQLException 
	 */
	public int updateUser(User user) throws SQLException{
		String sql = "update t_user set userName = ?, password = ? where id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getPassword());
		pstmt.setInt(3, user.getId());
		
		int rows = pstmt.executeUpdate();
		
		//其实一共也就一行
		return rows;
	}
	
	/**
	 * 管理员删除账号
	 * @param user
	 * @return
	 * @throws SQLException 
	 */
	public int deleteUser(User user) throws SQLException{
		String sql = "delete from t_user where id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, user.getId());
		
		int rows = pstmt.executeUpdate();
		return rows;
	}
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 * @throws SQLException
	 */
	public int deleteUser(int[] ids) throws SQLException{
		String sql = "delete from t_user where id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		for(int i = 0; i < ids.length; i++){
			pstmt.setInt(i + 1, ids[i]);
			pstmt.addBatch();
		}
		int[] affectRecords = pstmt.executeBatch();
		return affectRecords.length;
	}
	
	/*public static void main(String[] args) throws SQLException {
		User user = new User("testUser1", "123456");
		UserDao userDao = DaoFactory.createUserDao();
		User currentUser = userDao.selectUser(user);
		System.out.println(currentUser.getId());
		userDao.closeConn();
	}*/
}
