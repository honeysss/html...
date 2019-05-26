package com.icss.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.service.DepartmentService;
import com.icss.service.EmployeeService;
import com.icss.service.MeeingService;
import com.icss.service.MeetingRoomService;
import com.icss.vo.Department;
import com.icss.vo.Employee;
import com.icss.vo.Meeting;
import com.icss.vo.MeetingRoom;

/**
 * Servlet implementation class ViewMyBookingDetailServlet
 */
@WebServlet("/UpdateMyBookingServlet")
public class UpdateMyBookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMyBookingServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int mId = Integer.parseInt(request.getParameter("mId"));
		int mrId = 0;
		if (request.getParameter("mrId") != null) {
			mrId = Integer.parseInt(request.getParameter("mrId").toString());
		}
		MeeingService ms = new MeeingService();
		
		Meeting meeting;
		try {
			//��ȡ����Ա��
			EmployeeService es = new EmployeeService();
			//���û�д�����id ��ʾ����Ա��  ������˲���id ͨ������id����Ա��
			ArrayList<Employee> al = new ArrayList<Employee>();
			if (request.getParameter("deptId") != null) {
				al = es.selectUserByDeptIdService(Integer.parseInt(request.getParameter("deptId")));
			} else {
				al = es.selectAllUserService();
			}
			request.setAttribute("allEmps", al);
			//ͨ������id��òμӸû��������Ա��
			ArrayList<Employee> empList = ms.selectEmpByMIdService(mId);
			meeting = ms.selectMeetingByIdService(mId);
			request.setAttribute("meeting", meeting);
			request.setAttribute("empList", empList);
			//��ȡ���в���
			DepartmentService ds = new DepartmentService();
			ArrayList<Department> depts = ds.selectAllDeptService();
			request.setAttribute("depts", depts);
			//������еĻ�����
			MeetingRoomService mrs = new MeetingRoomService();
			ArrayList<MeetingRoom> mrs2 = mrs.selectAllMrsService();
			request.setAttribute("mrs", mrs2);
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			String mName = request.getParameter("mName");
			String mDescribe = request.getParameter("mDescribe");
			request.setAttribute("mName", mName);
			request.setAttribute("mDescribe", mDescribe);
			request.setAttribute("startTime", startTime);
			request.setAttribute("endTime", endTime);
			request.setAttribute("mrId", mrId);
			request.getRequestDispatcher("updateMyBookingMeeting.jsp").forward(request, response);
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
