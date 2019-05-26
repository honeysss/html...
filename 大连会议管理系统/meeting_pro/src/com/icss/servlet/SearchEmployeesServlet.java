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
 * Servlet implementation class SearchEmployees
 */
@WebServlet("/SearchEmployeesServlet")
public class SearchEmployeesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchEmployeesServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//获取员工姓名 用户名 状态
		String empName = request.getParameter("empName");
		String username = request.getParameter("username");
		int empStatus = 3;
		if (request.getParameter("empStatus") != null && !"".equals(request.getParameter("empStatus"))) {
			empStatus = Integer.parseInt(request.getParameter("empStatus"));
		}
		
		
		
		//设置一页显示几条数据
		int showNum = 5;
		//获取一共有多少页
		EmployeeService es = new EmployeeService();
		try {
			
			//如果没有传来pageNum 让pageNum=1
			int pageNum = 1;
			if (request.getParameter("pageNum") != null && !"".equals(request.getParameter("pageNum"))) {
				pageNum = Integer.parseInt(request.getParameter("pageNum"));
			}
			
			int empCount = es.selectEmpByRulesService(empName, username, empStatus).size();
			int allPageNum = empCount % showNum == 0 ? empCount/showNum : empCount/showNum + 1;
			//如果当前页大于总页数 让当前页减一
			if (pageNum > allPageNum) {
				pageNum--;
			}
			ArrayList<Employee> al = es.selectEmpByPageService(empName, username, empStatus, pageNum, showNum);
			
			
			DepartmentService ds = new DepartmentService();
			ArrayList<Department> al2 = ds.selectAllDeptService();
			request.setAttribute("allPageNum", allPageNum);
			request.setAttribute("emps", al);
			request.setAttribute("empCount", empCount);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("depts", al2);
			request.setAttribute("username", username);
			request.setAttribute("empName", empName);
			request.setAttribute("empStatus", empStatus);
			request.getRequestDispatcher("searchEmployee.jsp").forward(request, response);
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
