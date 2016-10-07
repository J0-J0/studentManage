package com.jojo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)servletRequest;
		HttpSession session = request.getSession();
		boolean flag = false;
		String path = request.getServletPath();
		
		Object currentUser = session.getAttribute("currentUser");
		Object currentManager = session.getAttribute("currentManager");
		if(currentUser != null || currentManager != null){
			flag = flag || true;
		}
		if(path.contains("userLogin") || path.contains("managerLogin") || path.contains("images")){
			flag = flag || true;
		}
		
		if(!flag){
			request.getRequestDispatcher("filter.jsp").forward(servletRequest, servletResponse);
		}else{
			filterChain.doFilter(servletRequest, servletResponse);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
