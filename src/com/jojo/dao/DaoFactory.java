package com.jojo.dao;

import com.jojo.util.DbUtil;

/**
 * 工厂类
 * @author flash.J
 *
 */
public class DaoFactory {

	/**
	 * 返回UserDao
	 * @return
	 */
	public static UserDao createUserDao(){
		return new UserDao(DbUtil.getConn());
	}
	/**
	 * 返回studentDao
	 * @return
	 */
	public static StudentDao createStudentDao(){
		return new StudentDao(DbUtil.getConn());
	}
	/**
	 * 返回managerDao
	 * @return
	 */
	public static ManagerDao createManagerDao(){
		return new ManagerDao(DbUtil.getConn());
	}
}
