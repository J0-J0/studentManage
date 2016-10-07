package com.jojo.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jojo.dao.DaoFactory;
import com.jojo.dao.StudentDao;
import com.jojo.dao.UserDao;
import com.jojo.model.Student;
import com.jojo.model.User;

public class UserUpdateServlet extends HttpServlet {

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
		int studentID = Integer.parseInt(request.getParameter("studentID"));
		String studentName = request.getParameter("studentName");
		String className = request.getParameter("className");
		String major = request.getParameter("major");
		String department = request.getParameter("department");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");

		// 主键不能为空
		if (request.getParameter("studentID") == null) {
			request.setAttribute("error", "学号不能为空");
			request.getRequestDispatcher("userRegister.jsp").forward(request, response);
			return;
		}
		//用户名与密码不能为空，剩下信息可以稍后补
		if (userName == null || userName.length() == 0 || password == null || password.length() == 0) {
			request.setAttribute("error", "用户名或密码不能为空！");
			request.getRequestDispatcher("userLogin.jsp").forward(request, response);
			return;
		}
		
		//老生更新，已登录，使用session中保存的currentUser与currentStudent
		HttpSession session = request.getSession();
		User currentUser = (User)session.getAttribute("currentUser");
		Student currentStudent = (Student)session.getAttribute("currentStudent");
		currentUser.setPassword(password);
		currentUser.setUserName(userName);
		currentStudent.setStudentID(studentID);
		currentStudent.setStudentName(studentName);
		currentStudent.setMajor(major);
		currentStudent.setDepartment(department);
		currentStudent.setClassName(className);
		
		UserDao userDao = DaoFactory.createUserDao();
		StudentDao studentDao = DaoFactory.createStudentDao();
		try {
			userDao.updateUser(currentUser);
			studentDao.updateStudent(currentStudent);
			
			//这边就应该弹个alert()
			response.sendRedirect("userMain.jsp");
			return;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			userDao.closeConn();
			studentDao.closeConn();
		}
	}
	
}
