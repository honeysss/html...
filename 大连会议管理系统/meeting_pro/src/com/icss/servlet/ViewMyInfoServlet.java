package com.icss.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icss.service.DepartmentService;
import com.icss.service.EmployeeService;
import com.icss.vo.Department;
import com.icss.vo.Employee;

/**
 * Servlet implementation class ViewMyInfoServlet
 */
@WebServlet("/ViewMyInfoServlet")
public class ViewMyInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewMyInfoServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int empId = Integer.parseInt(session.getAttribute("empId").toString());
		EmployeeService es = new EmployeeService();
		try {
			Employee emp = es.selectUserByIdService(empId);
			request.setAttribute("emp", emp);
			//获取所有部门
			DepartmentService ds = new DepartmentService();
			ArrayList<Department> dept = ds.selectAllDeptService();
			request.setAttribute("depts", dept);
			request.getRequestDispatcher("selectMyInfo.jsp").forward(request, response);
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
