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

import com.icss.service.EmployeeService;
import com.icss.service.MeeingService;
import com.icss.service.MeetingRoomService;
import com.icss.vo.Employee;
import com.icss.vo.Meeting;
import com.icss.vo.MeetingRoom;

/**
 * Servlet implementation class MyNotificationServlet
 */
@WebServlet("/MyNotificationServlet")
public class MyNotificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyNotificationServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		MeeingService ms = new MeeingService();
		String role = request.getParameter("role");
		HttpSession session = request.getSession();
		int empId = Integer.parseInt(session.getAttribute("empId").toString());
		try {
			//获取所有的会议室
			MeetingRoomService mrs = new MeetingRoomService();
			ArrayList<MeetingRoom> allMrs = mrs.selectAllMrsService();
			ArrayList<Meeting> futureMeetings = ms.myMeetingsSevenDayService(empId);
			ArrayList<Meeting> cancleMeetings = ms.myCancledMeetingsDAO(empId);
			request.setAttribute("futureMeetings", futureMeetings);
			request.setAttribute("cancleMeetings", cancleMeetings);
			request.setAttribute("allMrs", allMrs);
			//如果是普通用户
			if ("user".equals(role)) {
				request.getRequestDispatcher("myNotificationUser.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("myNotification.jsp").forward(request, response);
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
