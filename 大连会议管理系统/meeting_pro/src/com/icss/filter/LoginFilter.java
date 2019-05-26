package com.icss.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/LoginFilter")
public class LoginFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public LoginFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		String requestUri = req.getRequestURI();
	    String contextPath = req.getContextPath();
	    String url = requestUri.substring(contextPath.length());
		String[] urlList= {"/login.jsp", "/LoginServlet", "/IsAlreadyExistTheUsernameServlet", "/ToUserRegisterJspServlet", "/userRegister.jsp",
				"/UserRegisterServlet", "/js/common.js", "/images/footer.png",
				"/js/jquery-1.8.3.min.js", "/images/down.png", "/images/footer.png","/images/header.png",
				"/images/left.png","/images/right.png","/images/up.png",
				"/include/adminPageBar.jsp", "/include/head.jsp", "/include/page-footer.jsp", "/include/page-header.jsp", "/include/userPageBar.jsp",
				"/styles/common.css"};
		int flag1 = 0;
		for(String tmp:urlList) {
			if(tmp.equals(url)) {
				flag1 = 1;
			}
		}
		if(flag1 == 0) {
			String username = (String) session.getAttribute("username");
			if (username == null) {
				req.setAttribute("msg", "请先登陆");
				req.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
