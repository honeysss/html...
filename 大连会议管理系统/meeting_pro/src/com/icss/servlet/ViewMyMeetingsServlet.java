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

import com.icss.service.EmployeeService;
import com.icss.service.MeeingService;
import com.icss.service.MeetingRoomService;
import com.icss.vo.Employee;
import com.icss.vo.Meeting;
import com.icss.vo.MeetingRoom;

/**
 * Servlet implementation class ViewMyMeetingsServlet
 */
@WebServlet("/ViewMyMeetingsServlet")
public class ViewMyMeetingsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewMyMeetingsServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ͨ��Ա��id���Ҹ�Ա���μӵ����л���
		HttpSession session = request.getSession();
		int empId = Integer.parseInt(session.getAttribute("empId").toString());
		MeeingService ms = new MeeingService();
		EmployeeService es = new EmployeeService();
		try {
			ArrayList<Meeting> al = ms.selectAllMeetingByEmpIdService(empId);
			request.setAttribute("meetings", al);
			//������������ת���ҵĻ���ҳ��
			//��ȡ����Ա��
			ArrayList<Employee> al2 = es.selectAllUserService();
			request.setAttribute("emps", al2);
			//��ȡ���еĻ�����
			//��ȡ���еĻ�����
			MeetingRoomService mrs = new MeetingRoomService();
			ArrayList<MeetingRoom> allMrs = mrs.selectAllMrsService();
			request.setAttribute("allMrs", allMrs);

			String role = request.getParameter("role");
			//�������ͨ�û�
			if ("user".equals(role)) {
				request.getRequestDispatcher("myMeetingsUser.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("myMeetings.jsp").forward(request, response);
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
