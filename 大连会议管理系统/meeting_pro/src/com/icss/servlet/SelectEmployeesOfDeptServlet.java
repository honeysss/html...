package com.icss.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.service.EmployeeService;
import com.icss.vo.Employee;
import com.icss.vo.MeetingRoom;

/**
 * Servlet implementation class SelectEmployeesOfDeptServlet
 */
@WebServlet("/SelectEmployeesOfDeptServlet")
public class SelectEmployeesOfDeptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectEmployeesOfDeptServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int deptId =Integer.parseInt(request.getParameter("departmentid"));
		EmployeeService es = new EmployeeService();
		try {
			ArrayList<Employee> emplist =es.SelectEmployeesOfDeptDAO(deptId);
			response.setContentType("text/xml;charset=utf-8");
			PrintWriter out = response.getWriter();
			response.setHeader("Cache-Control", "no-cache");
			out.println("<?xml version='1.0' encoding='" + "utf-8" + "'?>");	
			out.println("<employees>");
			for(Employee m : emplist){
				out.println("<option>");
				out.println("<value>" + m.getEmpId()+ "</value>");
				out.println("<text>" + m.getEmpName()+ "</text>");
				out.println("</option>");
			}
			out.println("</employees>");
			out.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
