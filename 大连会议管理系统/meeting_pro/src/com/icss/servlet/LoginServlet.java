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
		//�ύ����̨��ʱ��Ҫ�ж��Ƿ�������û���
		//�û��������Ƿ���ȷ
		//�û�״̬�Ƿ��Ѿ�ͨ�����
		//�û�������ǹ���Ա������ͨ�û�
		request.setCharacterEncoding("utf-8");
		//��ȡ�û���������
		String username = request.getParameter("username");
		String pwd = request.getParameter("pwd");
		
		EmployeeService us = new EmployeeService();
		//����һ��msg
		String msg = null;
		
		//�����û�״̬
		int status = -1;
		try {
//			System.out.println(us.selectByNameAndPwdService(username, pwd));
			if (us.selectByNameAndPwdService(username, pwd) != null) {
				status = us.selectByNameAndPwdService(username, pwd).getUserStatus();
			}
			//����û���status=1˵���û�����������ȷ �ж��û���ݲ���ת����Ա����ͨ�û�����
			if (status == 1) {
				int empId = us.selectByNameAndPwdService(username, pwd).getEmpId();
				String empName = us.selectByNameAndPwdService(username, pwd).getEmpName();
				
				
				//=======������=========
				
				CounterService cs = new CounterService();
				ServletContext sc = this.getServletContext();
				int count = 1;
				//�жϸ��û��Ƿ���ʹ�����վ
				if (cs.ifVisitedByIdService(empId) != null) {
					count = cs.ifVisitedByIdService(empId).getCounter() + 1;
					cs.setCounterByIdService(empId, count);
				} else {
					cs.addCounterByIdAndCounterService(empId, count);
				}
				sc.setAttribute("count", count);
				sc.setAttribute("empId", empId);
				//=======������=========
				
				//��session�������û����������id
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
				//��ͨ�û�
				if (role == 0) {
					session.setAttribute("role", "0");
					request.getRequestDispatcher("userIndex.jsp").forward(request, response);
				}
				//����Ա
				else {
					session.setAttribute("role", "1");
					request.getRequestDispatcher("adminIndex.jsp").forward(request, response);
				}
			}
			//����
			else {
				//ͨ���û����鲻������û�˵���û�������
				if (!us.selectByNameService(username)) {
					msg = "�û�������";
				}
				//ͨ���û���������鲻������û�˵����������
				else if(us.selectByNameAndPwdService(username, pwd) == null) {
					//�������������cookie��Ϊ�յ�ʱ���cookie���
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
						msg = "��������";
					}
					
				}
				else if (status == 0) {
					msg = "�˺����������";
				} else if (status == 2) {
					msg = "�˺����ʧ��";
				}
				
				//��ת����½����
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
