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
			//��ȡ���еĻ�����
			MeetingRoomService mrs = new MeetingRoomService();
			ArrayList<MeetingRoom> allMrs = mrs.selectAllMrsService();
			request.setAttribute("allMrs", allMrs);
			//��ȡ����Ա��
			EmployeeService es = new EmployeeService();
			ArrayList<Employee> al2 = es.selectAllUserService();
			request.setAttribute("emps", al2);
			//ͨ������id��òμӸû��������Ա��
			ArrayList<Employee> empList = ms.selectEmpByMIdService(mId);
			meeting = ms.selectMeetingByIdService(mId);
			request.setAttribute("meeting", meeting);
			request.setAttribute("empList", empList);
			String oper = request.getParameter("oper");
			String title = "";
			if ("myFutureMeetings".equals(oper)) {
				title = "�������� > �ҵĻ��� >�鿴����";
			} else if ("allMeetings".equals(oper)) {
				title = "����Ԥ�� > �鿴���л��� > �鿴����";
			} else if ("viewMyBookingMeeting".equals(oper)) {
				title = "�������� > �ҵ�Ԥ�� > �鿴����";
			} else {
				title = "�������� > ����֪ͨ > �鿴����";
			}
			
			request.setAttribute("title", title);
			
			

			String role = request.getParameter("role");
			//�������ͨ�û�
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
