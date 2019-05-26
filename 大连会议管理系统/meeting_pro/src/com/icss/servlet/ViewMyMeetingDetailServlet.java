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
 * Servlet implementation class ViewMyMeetingDetailServlet
 */
@WebServlet("/ViewMyMeetingDetailServlet")
public class ViewMyMeetingDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewMyMeetingDetailServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int mId = Integer.parseInt(request.getParameter("mId"));
		MeeingService ms = new MeeingService();
		Meeting meeting;
		try {
			//获取所有的会议室
			MeetingRoomService mrs = new MeetingRoomService();
			ArrayList<MeetingRoom> allMrs = mrs.selectAllMrsService();
			request.setAttribute("allMrs", allMrs);
			//获取所有员工
			EmployeeService es = new EmployeeService();
			ArrayList<Employee> al2 = es.selectAllUserService();
			request.setAttribute("emps", al2);
			//通过会议id获得参加该会议的所有员工
			ArrayList<Employee> empList = ms.selectEmpByMIdService(mId);
			meeting = ms.selectMeetingByIdService(mId);
			request.setAttribute("meeting", meeting);
			request.setAttribute("empList", empList);
			String oper = request.getParameter("oper");
			String title = "";
			if ("myFutureMeetings".equals(oper)) {
				title = "个人中心 > 我的会议 >查看详情";
			} else if ("allMeetings".equals(oper)) {
				title = "会议预定 > 查看所有会议 > 查看详情";
			} else if ("viewMyBookingMeeting".equals(oper)) {
				title = "个人中心 > 我的预定 > 查看详情";
			} else {
				title = "个人中心 > 最新通知 > 查看详情";
			}
			
			request.setAttribute("title", title);
			
			

			String role = request.getParameter("role");
			//如果是普通用户
			if ("user".equals(role)) {
				request.getRequestDispatcher("meetingDetailUser.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("meetingDetail.jsp").forward(request, response);
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
