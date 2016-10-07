package com.jojo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jojo.model.User;

public class UserDao {

	private Connection conn = null;

	/**
	 * ���죬���������ӷ�Χ
	 * 
	 * @param conn
	 */
	public UserDao(Connection conn) {
		super();
		this.conn = conn;
	}

	/**
	 * �ر�����
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
	 * �����û��������û�ID
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
	 * �����û���Ҫ���ҵ��ͷ���һ�����ҵ���User��û�з���null
	 * 
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	public User selectUser(User user) throws SQLException {
		User resultUser = null;
		// �����û�
		String sql = "select * from t_user where userName = ? and password = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getPassword());

		ResultSet rs = pstmt.executeQuery();

		// Ҫ��Ҳֻ���ҵ�һ����������߾���if��
		//����ʱ����id
		if (rs.next()) {
			resultUser = new User();
			resultUser.setId(rs.getInt("id"));
			resultUser.setUserName(rs.getString("userName"));
			resultUser.setPassword(rs.getString("password"));
		}

		return resultUser;
	}
	
	/**
	 * �޸��û���������
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
		
		//��ʵһ��Ҳ��һ��
		return rows;
	}
	
	/**
	 * ����Աɾ���˺�
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
	 * ����ɾ��
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
