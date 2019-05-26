package com.icss.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.service.MeeingService;

/**
 * Servlet implementation class CancelMeetingServlet
 */
@WebServlet("/CancleMeetingServlet")
public class CancleMeetingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancleMeetingServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int mId = Integer.parseInt(request.getParameter("mId"));
		MeeingService ms = new MeeingService();
		request.setCharacterEncoding("utf-8");
		String cancleReason = request.getParameter("cancleReason");
		//获取当前系统时间
		Date date = new java.util.Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String cancleTime = sdf.format(date);
		
		try {
			ms.cancleMeetingByMIdService(mId, cancleTime, cancleReason);
			//取消完之后跳转到我的预定页面
			request.getRequestDispatcher("ViewMyBookingServlet").forward(request, response);
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
