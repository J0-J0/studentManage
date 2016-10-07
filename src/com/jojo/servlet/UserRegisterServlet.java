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

public class UserRegisterServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int studentID = Integer.parseInt(request.getParameter("studentID"));
		String studentName = request.getParameter("studentName");
		String className = request.getParameter("className");
		String major = request.getParameter("major");
		String department = request.getParameter("department");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");

		// ��������Ϊ��
		if (request.getParameter("studentID") == null) {
			request.setAttribute("error", "ѧ�Ų���Ϊ��");
			request.getRequestDispatcher("userRegister.jsp").forward(request, response);
			return;
		}
		//�û��������벻��Ϊ�գ�ʣ����Ϣ�����Ժ�
		if (userName == null || userName.length() == 0 || password == null || password.length() == 0) {
			request.setAttribute("error", "�û��������벻��Ϊ�գ�");
			request.getRequestDispatcher("userLogin.jsp").forward(request, response);
			return;
		}
		
		User user = new User(userName, password);
		UserDao userDao = DaoFactory.createUserDao();
		StudentDao studentDao = DaoFactory.createStudentDao();
		try {
			
			//����ط������ݿⱨ��Ҫ��ô����
			//���У�������Ūuser�������ܾ��ú�Σ�ա�����
			user = userDao.addUser(user);
			Student student = new Student(studentID, studentName, className, major, department, user.getId());
			studentDao.addStudent(student);
			
			HttpSession session = request.getSession();
			session.setAttribute("currentUser", user);
			session.setAttribute("currentStudent", student);
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
