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
import com.icss.service.MeetingRoomService;
import com.icss.vo.Department;
import com.icss.vo.Employee;
import com.icss.vo.MeetingRoom;

/**
 * Servlet implementation class BookMeetingServlet
 */
@WebServlet("/BookMeetingServlet")
public class BookMeetingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookMeetingServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取所有部门 获取所有会议室 获取所有员工
		DepartmentService ds = new DepartmentService();
		MeetingRoomService mrs = new MeetingRoomService();
		EmployeeService es = new EmployeeService();
		try {
			ArrayList<Department> depts = ds.selectAllDeptService();
			ArrayList<MeetingRoom> mrs2 = mrs.selectAllMrsService();
			ArrayList<Employee> emps = es.selectAllUserService();
			request.setAttribute("depts", depts);
			request.setAttribute("mrs", mrs2);
			request.setAttribute("emps", emps);
			//跳转到预定会议页面
			request.getRequestDispatcher("bookMeeting.jsp").forward(request, response);
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
