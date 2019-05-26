package com.icss.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.service.MeetingRoomService;
import com.icss.vo.MeetingRoom;

/**
 * Servlet implementation class ViewOneMeetingRoomServlet
 */
@WebServlet("/ViewOneMeetingRoomServlet")
public class ViewOneMeetingRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewOneMeetingRoomServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int mrId = Integer.parseInt(request.getParameter("mrId"));
		//通过id查找会议室
		MeetingRoomService mrs = new MeetingRoomService();
		try {
			MeetingRoom mr = mrs.selectMrByMrIdService(mrId);
			request.setAttribute("mr", mr);
			request.getRequestDispatcher("meetingRoomDetail.jsp").forward(request, response);
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
