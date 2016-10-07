package com.jojo.dao;

import com.jojo.util.DbUtil;

/**
 * ������
 * @author flash.J
 *
 */
public class DaoFactory {

	/**
	 * ����UserDao
	 * @return
	 */
	public static UserDao createUserDao(){
		return new UserDao(DbUtil.getConn());
	}
	/**
	 * ����studentDao
	 * @return
	 */
	public static StudentDao createStudentDao(){
		return new StudentDao(DbUtil.getConn());
	}
	/**
	 * ����managerDao
	 * @return
	 */
	public static ManagerDao createManagerDao(){
		return new ManagerDao(DbUtil.getConn());
	}
}
