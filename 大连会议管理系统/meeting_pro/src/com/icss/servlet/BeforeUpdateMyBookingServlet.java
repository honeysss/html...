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
import com.icss.service.MeeingService;
import com.icss.service.MeetingRoomService;
import com.icss.vo.Department;
import com.icss.vo.Employee;
import com.icss.vo.Meeting;
import com.icss.vo.MeetingRoom;

/**
 * Servlet implementation class BeforeUpdateMyBookingServlet
 */
@WebServlet("/BeforeUpdateMyBookingServlet")
public class BeforeUpdateMyBookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BeforeUpdateMyBookingServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//在这里取到会议最开始的信息 会议最开始的参加人员 存起来
		int mId = Integer.parseInt(request.getParameter("mId"));
		MeeingService ms = new MeeingService();
		
		Meeting meeting;
		try {
			//通过会议id获得参加该会议的所有员工
			ArrayList<Employee> empList = ms.selectEmpByMIdService(mId);
			//获取该会议的原始信息
			meeting = ms.selectMeetingByIdService(mId);
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(60*10);
			session.setAttribute("oldMeeting", meeting);
			session.setAttribute("oldEmpList", empList);
			request.getRequestDispatcher("UpdateMyBookingServlet?mId=" + mId).forward(request, response);
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
