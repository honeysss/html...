package com.icss.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.service.MeeingService;

/**
 * Servlet implementation class DeleteEmpFromMeeting
 */
@WebServlet("/DeleteEmpFromMeeting")
public class DeleteEmpFromMeeting extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteEmpFromMeeting() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//从某个会议中删除某个员工
		request.setCharacterEncoding("utf-8");
		int empId = Integer.parseInt(request.getParameter("empId"));
		int mId = Integer.parseInt(request.getParameter("mId"));
		int mrId = 0;
		if (request.getParameter("mrId") != null && !"".equals(request.getParameter("mrId"))) {
			mrId = Integer.parseInt(request.getParameter("mrId"));
		}
		String startTime = request.getParameter("startTime").replace("T", " ");
		String endTime = request.getParameter("endTime").replace("T", " ");
		String mName = request.getParameter("mName");
		String mDescribe = request.getParameter("mDescribe");
		MeeingService ms = new MeeingService();
		try {
			ms.deleteEmpFromMeetingService(mId, empId);
			//删除成功后还跳转到该页面
			request.getRequestDispatcher("UpdateMyBookingServlet?mId=" + mId + "&startTime=" + startTime + "&endTime=" + endTime + "&mrId=" + mrId+ "&mName=" + mName + "&mDescribe=" + mDescribe).forward(request, response);
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
