package com.icss.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.service.MeetingRoomService;

/**
 * Servlet implementation class CloseMeetingRoomServlet
 */
@WebServlet("/CloseMeetingRoomServlet")
public class CloseMeetingRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CloseMeetingRoomServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MeetingRoomService mrs = new MeetingRoomService();
		int mrId = Integer.parseInt(request.getParameter("mrId"));
		try {
			String mrMsg = null;
			//����û��������ڱ�ʹ�� ��ʾ��Ϣ �û��������ڻ�Ҫ��ʹ�� ����ͣ��
			if (mrs.ifUsedMrByMeetingService(mrId)) {
				mrMsg = "�û��������ڻ�Ҫ��ʹ�� ����ͣ��";
			} else {
				mrs.closeMrService(mrId);
			}
			request.setAttribute("mrMsg", mrMsg);
			//��ת�����л����ҽ���
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
