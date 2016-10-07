package com.jojo.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jojo.dao.DaoFactory;
import com.jojo.dao.ManagerDao;
import com.jojo.dao.StudentDao;
import com.jojo.model.Manager;
import com.jojo.model.Student;

public class ManagerLoginServlet extends HttpServlet{

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
		String managerName = request.getParameter("managerName");
		String password = request.getParameter("password");
		if(managerName == null || managerName.length() ==0 || password == null || password.length() == 0){
			request.setAttribute("error", "用户名或密码不能为空！");
			request.getRequestDispatcher("managerLogin.jsp").forward(request, response);
			return ;
		}
		
		//查询用manager
		Manager manager = new Manager(managerName, password);
		ManagerDao managerDao = DaoFactory.createManagerDao();
		StudentDao studentDao = DaoFactory.createStudentDao();
		Manager currentManager = null;
		try {
			currentManager = managerDao.selectManager(manager);
			if(currentManager != null){
				
				//召唤session
				HttpSession session = request.getSession();
				session.setAttribute("currentManager", currentManager);
				
				//这样传数据应该不好
				List<Student> students = studentDao.selectAllAtudent();
				session.setAttribute("students", students);
				/*Student student = studentDao.selectStudent(1);
				session.setAttribute("student", student);*/
				response.sendRedirect("managerMain.jsp");
				return;
			}else{
				request.setAttribute("error", "用户名或密码错误！");
				request.setAttribute("managerName", managerName);
				request.setAttribute("password", password);
				request.getRequestDispatcher("managerLogin.jsp").forward(request, response);
				return ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			managerDao.closeConn();
			studentDao.closeConn();
		}
	}

}
