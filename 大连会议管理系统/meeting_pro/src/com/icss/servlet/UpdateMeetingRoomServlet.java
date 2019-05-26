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
 * Servlet implementation class UpdateMeetingRoomServlet
 */
@WebServlet("/UpdateMeetingRoomServlet")
public class UpdateMeetingRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMeetingRoomServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int mrId = Integer.parseInt(request.getParameter("mrId"));
		int mrNum = Integer.parseInt(request.getParameter("mrNum"));
		String mrName = request.getParameter("mrName");
		int mrCapacity = Integer.parseInt(request.getParameter("mrCapacity"));
		int mrStatus = Integer.parseInt(request.getParameter("mrStatus"));
		String mrRemark = request.getParameter("mrRemark");
		MeetingRoom mr = new MeetingRoom(mrId, mrNum, mrName, mrCapacity, mrStatus, mrRemark);
		MeetingRoomService mrs = new MeetingRoomService();
		try {
			mrs.updateMrService(mr);
			//修改成功跳转到所有会议室页面
			request.getRequestDispatcher("ViewAllMeetingRoomsServlet").forward(request, response);
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
