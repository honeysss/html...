package com.icss.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icss.service.CounterService;
import com.icss.service.EmployeeService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//提交到后台的时候要判断是否有这个用户名
		//用户名密码是否正确
		//用户状态是否已经通过审核
		//用户的身份是管理员还是普通用户
		request.setCharacterEncoding("utf-8");
		//获取用户名和密码
		String username = request.getParameter("username");
		String pwd = request.getParameter("pwd");
		
		EmployeeService us = new EmployeeService();
		//定义一个msg
		String msg = null;
		
		//定义用户状态
		int status = -1;
		try {
//			System.out.println(us.selectByNameAndPwdService(username, pwd));
			if (us.selectByNameAndPwdService(username, pwd) != null) {
				status = us.selectByNameAndPwdService(username, pwd).getUserStatus();
			}
			//如果用户的status=1说明用户名和密码正确 判断用户身份并跳转管理员或普通用户界面
			if (status == 1) {
				int empId = us.selectByNameAndPwdService(username, pwd).getEmpId();
				String empName = us.selectByNameAndPwdService(username, pwd).getEmpName();
				
				
				//=======上下文=========
				
				CounterService cs = new CounterService();
				ServletContext sc = this.getServletContext();
				int count = 1;
				//判断该用户是否访问过该网站
				if (cs.ifVisitedByIdService(empId) != null) {
					count = cs.ifVisitedByIdService(empId).getCounter() + 1;
					cs.setCounterByIdService(empId, count);
				} else {
					cs.addCounterByIdAndCounterService(empId, count);
				}
				sc.setAttribute("count", count);
				sc.setAttribute("empId", empId);
				//=======上下文=========
				
				//在session中设置用户名和密码和id
				//===============session======================
				HttpSession session = request.getSession();
				session.setMaxInactiveInterval(600*10);
				session.setAttribute("username", username);
				session.setAttribute("pwd", pwd);
				session.setAttribute("empId", empId);
				session.setAttribute("empName", empName);
				//===============session======================
				
				//======================cookie==================
				String maxAge = request.getParameter("maxAge");
				int time = 0;
				if (maxAge != null) {
					time = Integer.parseInt(maxAge);
				}
				if (time != 0) {
					Cookie usernameCK = new Cookie("username", username);
					Cookie pwdCK = new Cookie("pwd", pwd);
					usernameCK.setMaxAge(time*60);
					pwdCK.setMaxAge(time*60);
					response.addCookie(usernameCK);
					response.addCookie(pwdCK);
				}
				//======================cookie=================
				
				
				int role = us.selectByNameAndPwdService(username, pwd).getUserRole();
				//普通用户
				if (role == 0) {
					session.setAttribute("role", "0");
					request.getRequestDispatcher("userIndex.jsp").forward(request, response);
				}
				//管理员
				else {
					session.setAttribute("role", "1");
					request.getRequestDispatcher("adminIndex.jsp").forward(request, response);
				}
			}
			//否则
			else {
				//通过用户名查不到这个用户说明用户名有误
				if (!us.selectByNameService(username)) {
					msg = "用户名有误";
				}
				//通过用户名和密码查不到这个用户说明密码有误
				else if(us.selectByNameAndPwdService(username, pwd) == null) {
					//如果密码有误并且cookie不为空的时候把cookie清除
					boolean flag = true;
					Cookie[] cks = request.getCookies();
					if (cks != null) {
						for (Cookie ck: cks) {
							if ("username".equals(ck.getName())) {
								flag = false;
							}
							if ("pwd".equals(ck.getName())) {
								flag = false;
							}
							ck.setValue(null);
							ck.setMaxAge(0);
							response.addCookie(ck);
						}
					}
					if (flag) {
						msg = "密码有误";
					}
					
				}
				else if (status == 0) {
					msg = "账号正在审核中";
				} else if (status == 2) {
					msg = "账号审核失败";
				}
				
				//跳转到登陆界面
				request.setAttribute("msg", msg);
				request.setAttribute("username", username);
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
