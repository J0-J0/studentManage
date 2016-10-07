package com.jojo.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jojo.dao.DaoFactory;
import com.jojo.dao.StudentDao;
import com.jojo.dao.UserDao;

public class StudentDeleteServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//获取要删除的学生编号
		String[] deleteNumber = request.getParameter("deleteNumber").split(",");
		int[] nums = new int[deleteNumber.length];
		for(int i = 0; i < deleteNumber.length; i++){
			nums[i] = Integer.parseInt(deleteNumber[i]);
		}
		
		UserDao userDao = DaoFactory.createUserDao();
		StudentDao studentDao = DaoFactory.createStudentDao();
		try {
			int n = userDao.deleteUser(nums);
			int m = studentDao.deleteStudent(nums);
			
			//这边，老实讲最好加个事务处理，以后再补吧
			if(n == nums.length && m == nums.length){
				response.sendRedirect("managerMain.jsp");
				return ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			userDao.closeConn();
			studentDao.closeConn();
		}
	}
	
}
