package com.icss.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icss.service.DepartmentService;

import com.icss.service.EmployeeService;
import com.icss.service.MeeingService;
import com.icss.service.MeetingRoomService;
import com.icss.vo.Department;
import com.icss.vo.MeetingRoom;

/**
 * Servlet implementation class BookingMeetingServlet
 */
@WebServlet("/BookingMeetingServlet")
public class BookingMeetingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookingMeetingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String code=request.getParameter("code");
		MeetingRoomService mrs = new MeetingRoomService();
		DepartmentService ds = new  DepartmentService();
		EmployeeService es= new EmployeeService();
		MeeingService ms =new MeeingService();
		HttpSession session = request.getSession();
		String username=(String)session.getAttribute("username");
		if(code!=null&&code.equals("book")){
			String mName = request.getParameter("meetingname");
			int num=Integer.parseInt(request.getParameter("numofparticipants"));
			Timestamp startime = Timestamp.valueOf(request.getParameter("starttime"));
			Timestamp endtime = Timestamp.valueOf(request.getParameter("endtime"));
			int mrid =Integer.parseInt(request.getParameter("roomid"));
			String mdescribe = request.getParameter("description");
			String [] emp=request.getParameterValues("selSelectedEmployees");		
			try {
				ArrayList<Integer> emplist =new ArrayList<Integer>();
				for(String s:emp){
					emplist.add(Integer.parseInt(s));
				}						
				int id =es.SelectIdByNameService(username);
				ms.bookMeetingService(mName, num, mrid, id,startime, endtime, mdescribe);
				int meetingid = ms.selectMaxMeetingId();
				ms.addMeetingEmployeeDAO(meetingid, emplist);
				request.getRequestDispatcher("ViewMyBookingServlet").forward(request, response);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(code!=null&&code.equals("prepare")){
			try {
				ArrayList<Department> al= ds.SelectAllDepartmentsDAO();
				ArrayList<MeetingRoom> al1 = mrs.selectAllMrsService();
				request.setAttribute("roomsList", al1);
				request.setAttribute("deptsList", al);
				request.getRequestDispatcher("bookmeeting.jsp?username="+username).forward(request, response);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   
		
		}

	}
	
}
	
	
	

