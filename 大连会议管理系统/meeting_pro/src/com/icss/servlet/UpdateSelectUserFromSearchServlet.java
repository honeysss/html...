package com.icss.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.service.DepartmentService;
import com.icss.service.EmployeeService;
import com.icss.vo.Department;
import com.icss.vo.Employee;

/**
 * Servlet implementation class UpdateSelectUserServlet
 */
@WebServlet("/UpdateSelectUserFromSearchServlet")
public class UpdateSelectUserFromSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateSelectUserFromSearchServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		EmployeeService es = new EmployeeService();
		int empId = Integer.parseInt(request.getParameter("empId"));
		
		String newPageNum = request.getParameter("newPageNum");
		String newUsername = request.getParameter("newUsername");
		String newEmpName = request.getParameter("newEmpName");
		String newEmpStatus = request.getParameter("newEmpStatus");
		request.setAttribute("newPageNum", newPageNum);
		request.setAttribute("newUsername", newUsername);
		request.setAttribute("newEmpName", newEmpName);
		request.setAttribute("newEmpStatus", newEmpStatus);
		
		
		DepartmentService ds = new DepartmentService();
		try {
			Employee emp = es.selectUserByIdService(empId);
			ArrayList<Department> al2 = ds.selectAllDeptService();
			request.setAttribute("emp", emp);
			request.setAttribute("depts", al2);
			request.getRequestDispatcher("updateEmployeeFromSearch.jsp").forward(request, response);
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
