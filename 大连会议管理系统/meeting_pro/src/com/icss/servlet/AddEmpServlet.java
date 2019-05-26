package com.icss.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.service.EmployeeService;
import com.icss.vo.Employee;

/**
 * Servlet implementation class AddEmpServlet
 */
@WebServlet("/AddEmpServlet")
public class AddEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEmpServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
//		得到empName, empTel, empEmail, empDeptId, username, userPwd, userStatus, userRole
		String empName = request.getParameter("empName");
		String empTel = request.getParameter("empTel");
		String empEmail = request.getParameter("empEmail");
		int empDeptId = Integer.parseInt(request.getParameter("empDeptId"));
		String username = request.getParameter("username");
		String userPwd = request.getParameter("userPwd");
		int userStatus = Integer.parseInt(request.getParameter("userStatus"));
		int userRole = Integer.parseInt(request.getParameter("userRole"));
		
		EmployeeService us = new EmployeeService();
		Employee emp = new Employee(0, empName, empTel, empEmail, empDeptId, username, userPwd, userStatus, userRole);
		try {
			us.adminAddEmployeeService(emp);
			//增加完用户跳转到所有员工页面
			request.getRequestDispatcher("SearchEmployeesServlet?username=" + username).forward(request, response);
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
