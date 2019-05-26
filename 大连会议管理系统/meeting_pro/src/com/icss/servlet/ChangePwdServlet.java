package com.icss.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icss.service.EmployeeService;

/**
 * Servlet implementation class ChangePwdServlet
 */
@WebServlet("/ChangePwdServlet")
public class ChangePwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePwdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡid������
		int empId = Integer.parseInt(request.getParameter("empId"));
		String newPwd = request.getParameter("newPwd");
		HttpSession session = request.getSession();
		Object username = session.getAttribute("username");
		EmployeeService es = new EmployeeService();
		try {
			es.updatePwdService(empId, newPwd);
			//��ת����½ҳ��
			Cookie[] cks = request.getCookies();
			for (Cookie ck: cks) {
				ck.setValue(null);
				ck.setMaxAge(0);
				response.addCookie(ck);
			}
			request.setAttribute("username", username);
			request.setAttribute("msg", "���޸������룬�����µ�½");
			session.invalidate();
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
