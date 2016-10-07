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

public class UserLoginServlet extends HttpServlet {

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
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		if (userName == null || userName.length() == 0 || password == null || password.length() == 0) {
			request.setAttribute("error", "�û��������벻��Ϊ�գ�");
			request.getRequestDispatcher("userLogin.jsp").forward(request, response);
			return;
		}

		// ��װ��������User
		User user = new User(userName, password);
		UserDao userDao = DaoFactory.createUserDao();
		StudentDao studentDao = DaoFactory.createStudentDao();

		// �ҵ���currentUser��currentStudent����session
		User currentUser = null;
		Student currentStudent = null;
		try {
			currentUser = userDao.selectUser(user);
			
			//���ҳɹ�
			if(currentUser != null){
				currentStudent = studentDao.selectStudent(currentUser);
				HttpSession session = request.getSession();
				session.setAttribute("currentUser", currentUser);
				session.setAttribute("currentStudent", currentStudent);
				response.sendRedirect("userMain.jsp");
				return;          //���ھ������returnͦ��Ҫ�ģ�
			}else{
				request.setAttribute("error", "�û������������");
				request.setAttribute("userName", userName);
				request.setAttribute("password", password);
				request.getRequestDispatcher("userLogin.jsp").forward(request, response);
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			userDao.closeConn();
			studentDao.closeConn();
		}
	}

}
