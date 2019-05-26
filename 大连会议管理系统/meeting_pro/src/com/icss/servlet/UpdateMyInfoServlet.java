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
import com.icss.vo.Employee;

/**
 * Servlet implementation class UpdateMyInfoServlet
 */
@WebServlet("/UpdateMyInfoServlet")
public class UpdateMyInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMyInfoServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		EmployeeService es = new EmployeeService();
		
		int empId = Integer.parseInt(request.getParameter("empId"));
		String empName = request.getParameter("empName");
		String empTel = request.getParameter("empTel");
		String empEmail = request.getParameter("empEmail");
		String username = request.getParameter("username");
		String oldUsername = request.getParameter("oldUsername");
		Employee emp = new Employee(empId, empName, empTel, empEmail, 0, username, null, 0, 0);
		try {
			es.updateEmpByUserService(emp);
			//����û��������˱仯 ��ʾ ���޸����û��� �����µ�¼
			if (!username.equals(oldUsername)) {
				request.setAttribute("msg", "���޸����û��� �����µ�¼");
				request.setAttribute("username", username);
				HttpSession session = request.getSession();
				session.invalidate();
				Cookie[] cks = request.getCookies();
				for (Cookie ck: cks) {
					ck.setValue(null);
					ck.setMaxAge(0);
					response.addCookie(ck);
				}
				request.getRequestDispatcher("login.jsp").forward(request, response);
			} else {
				//������ת���鿴��Ϣ����
				request.getRequestDispatcher("ViewMyInfoServlet").forward(request, response);
			}
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
