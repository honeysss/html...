package com.icss.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.service.MeetingRoomService;

/**
 * Servlet implementation class ifTheMRUsedServlet
 */
@WebServlet("/IfTheMRUsedServlet")
public class IfTheMRUsedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IfTheMRUsedServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String startTime = request.getParameter("startTime").replace("T", " ");
		String endTime = request.getParameter("endTime").replace("T", " ");
		int mrId = Integer.parseInt(request.getParameter("mrId"));
		MeetingRoomService mrs = new MeetingRoomService();
		try {
			boolean flag = mrs.ifTheMRUsedService(startTime, endTime, mrId);
			PrintWriter out = response.getWriter();
			out.print(flag);
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
