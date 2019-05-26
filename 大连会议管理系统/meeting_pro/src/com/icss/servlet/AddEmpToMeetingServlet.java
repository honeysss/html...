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
 * Servlet implementation class AddEmpToMeetingServlet
 */
@WebServlet("/AddEmpToMeetingServlet")
public class AddEmpToMeetingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEmpToMeetingServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String addEmpIdList = request.getParameter("addEmpIdList");
		String[] empIdList = addEmpIdList.split(",");
		int mId = Integer.parseInt(request.getParameter("mId"));
		int mrId = 0;
		if (request.getParameter("mrId") != null && !"".equals(request.getParameter("mrId"))) {
			mrId = Integer.parseInt(request.getParameter("mrId"));
		}
		MeeingService ms = new MeeingService();
		String startTime = request.getParameter("startTime").replace("T", " ");
		String endTime = request.getParameter("endTime").replace("T", " ");
		for (int i = 0; i < empIdList.length; i ++) {
			try {
				ms.addEmpToMeetingEService(mId, Integer.parseInt(empIdList[i]));
			} catch (NumberFormatException | SQLException e) {
				e.printStackTrace();
			}
		}
		//添加成功后还跳转到该页面
		String mName = request.getParameter("mName");
		String mDescribe = request.getParameter("mDescribe");
		request.getRequestDispatcher("UpdateMyBookingServlet?mId=" + mId + "&startTime=" + startTime + "&endTime=" + endTime + "&mrId=" + mrId + "&mName=" + mName + "&mDescribe=" + mDescribe).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
