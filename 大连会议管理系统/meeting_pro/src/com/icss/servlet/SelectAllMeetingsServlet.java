package com.icss.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.service.EmployeeService;
import com.icss.service.MeeingService;
import com.icss.service.MeetingRoomService;
import com.icss.vo.Employee;
import com.icss.vo.Meeting;
import com.icss.vo.MeetingRoom;

/**
 * Servlet implementation class SelectAllMeetingsServlet
 */
@WebServlet("/SelectAllMeetingsServlet")
public class SelectAllMeetingsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectAllMeetingsServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MeeingService ms = new MeeingService();
		try {
			ArrayList<Meeting> al = ms.selectAllMeetingService();
			request.setAttribute("meetings", al);
			//获取所有的会议室
			MeetingRoomService mrs = new MeetingRoomService();
			ArrayList<MeetingRoom> allMrs = mrs.selectAllMrsService();
			request.setAttribute("allMrs", allMrs);
			//获取所有员工
			EmployeeService es = new EmployeeService();
			ArrayList<Employee> al2 = es.selectAllUserService();
			request.setAttribute("emps", al2);
			request.getRequestDispatcher("allMeetings.jsp").forward(request, response);
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
