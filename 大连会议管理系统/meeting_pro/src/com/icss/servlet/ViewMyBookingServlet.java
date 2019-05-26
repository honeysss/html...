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

import com.icss.service.MeeingService;
import com.icss.service.MeetingRoomService;
import com.icss.vo.Employee;
import com.icss.vo.Meeting;
import com.icss.vo.MeetingRoom;

/**
 * Servlet implementation class ViewMyBookingServlet
 */
@WebServlet("/ViewMyBookingServlet")
public class ViewMyBookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewMyBookingServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MeeingService ms = new MeeingService();
		HttpSession session = request.getSession();
		int empId = Integer.parseInt(session.getAttribute("empId").toString());
		try {
			//获取我预定的所有会议
			//如果该会议已经取消 或者该会议已经结束 只提供查看选项
			ArrayList<Meeting> al = ms.selectMeetingByEmpIdService(empId);
			request.setAttribute("meetings", al);
			//获取所有的会议室
			MeetingRoomService mrs = new MeetingRoomService();
			ArrayList<MeetingRoom> allMrs = mrs.selectAllMrsService();
			request.setAttribute("allMrs", allMrs);
			request.getRequestDispatcher("myBookings.jsp").forward(request, response);
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
