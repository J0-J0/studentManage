package com.jojo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jojo.model.Student;
import com.jojo.model.User;

public class StudentDao {
	private Connection conn = null;

	public StudentDao(Connection conn) {
		super();
		this.conn = conn;
	}

	/**
	 * �ر����ӣ�����������
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
	 * 
	 * @param i
	 * @return
	 * @throws SQLException
	 */
	public Student selectStudent(int i) throws SQLException {
		Student resultStudent = null;

		String sql = "select * from t_student where userID = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, i);

		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) { // ��ԣ��÷�!
			resultStudent = new Student();
			resultStudent.setStudentID(rs.getInt("studentID"));
			resultStudent.setStudentName(rs.getString("studentName"));
			resultStudent.setClassName(rs.getString("className"));
			resultStudent.setMajor(rs.getString("major"));
			resultStudent.setDepartment(rs.getString("department"));
			resultStudent.setUserID(rs.getInt("userID"));
		}

		return resultStudent;
	}
	/**
	 * ���ݸ�����User��ѯ��Ӧ��Student
	 * 
	 * @param currentUser
	 * @return
	 * @throws SQLException
	 */
	public Student selectStudent(User user) throws SQLException {
		Student resultStudent = null;

		String sql = "select * from t_student where userID = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, user.getId());

		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) { // ��ԣ��÷�!
			resultStudent = new Student();
			resultStudent.setStudentID(rs.getInt("studentID"));
			resultStudent.setStudentName(rs.getString("studentName"));
			resultStudent.setClassName(rs.getString("className"));
			resultStudent.setMajor(rs.getString("major"));
			resultStudent.setDepartment(rs.getString("department"));
			resultStudent.setUserID(rs.getInt("userID"));
		}

		return resultStudent;
	}

	/**
	 * ��ʾȫ��ѧ����Ϣ����ϵ������
	 * @return
	 * @throws SQLException
	 */
	public List<Student> selectAllAtudent() throws SQLException{
		ArrayList<Student> list = new ArrayList<Student>();
		String sql = "select * from t_student order by department";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()){
			Student student = new Student();
			student.setStudentID(rs.getInt("studentID"));
			student.setStudentName(rs.getString("studentName"));
			student.setClassName(rs.getString("className"));
			student.setMajor(rs.getString("major"));
			student.setDepartment(rs.getString("department"));
			student.setUserID(rs.getInt("userID"));
			list.add(student);
		}
		
		return list;
	}
	
	/**
	 * 
	 * @param student
	 * @return
	 * @throws SQLException
	 */
	public int addStudent(Student student) throws SQLException{
		String sql = "insert into t_student values (?,?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, student.getStudentID());
		pstmt.setString(2, student.getStudentName());
		pstmt.setString(3, student.getClassName());
		pstmt.setString(4, student.getMajor());
		pstmt.setString(5, student.getDepartment());
		pstmt.setInt(6, student.getUserID());
		
		int rows = pstmt.executeUpdate();
		
		return rows;
	}
	
	/**
	 * ����ѧ����Ϣ
	 * �и����⣬��Щֵͳͳ�趨�᲻���δ֪��bug��
	 * ����ĳЩֵΪnull��ǰ����߼�����õ㡣
	 * 
	 * @param student
	 * @return
	 * @throws SQLException 
	 */
	public int updateStudent(Student student) throws SQLException {
		String sql = "update t_student set studentID = ?, studentName = ?, "
				+ "className = ?, major = ?, department = ? where userID = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, student.getStudentID());
		pstmt.setString(2, student.getStudentName());
		pstmt.setString(3, student.getClassName());
		pstmt.setString(4, student.getMajor());
		pstmt.setString(5, student.getDepartment());
		pstmt.setInt(6, student.getUserID());
		
		int rows = pstmt.executeUpdate();
		
		return rows;
	}
	
	/**
	 * ����Աɾ��ѧ��
	 * @param student
	 * @return
	 * @throws SQLException 
	 */
	public int deleteStudent(Student student) throws SQLException{
		String sql = "delete from t_student where StudentID = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, student.getStudentID());
		
		int rows = pstmt.executeUpdate();
		return rows;
	}
	/**
	 * ����ɾ��
	 * @param userIDs
	 * @return
	 * @throws SQLException 
	 */
	public int deleteStudent(int[] userIDs) throws SQLException{
		String sql = "delete from t_student where userID = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		for(int i = 0; i < userIDs.length; i++){
			pstmt.setInt(i + 1, userIDs[i]);
			pstmt.addBatch();
		}
		
		int[] affectRecords = pstmt.executeBatch();
		return affectRecords.length;
	}
}
