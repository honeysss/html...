package com.icss.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icss.service.MeeingService;
import com.icss.service.MeetingRoomService;
import com.icss.vo.Employee;
import com.icss.vo.Meeting;
import com.icss.vo.MeetingRoom;

/**
 * Servlet implementation class ConfirmUpdateMeetingServlet
 */
@WebServlet("/ConfirmUpdateMeetingServlet")
public class ConfirmUpdateMeetingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmUpdateMeetingServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//通过会议id修改会议
		request.setCharacterEncoding("utf-8");
		MeeingService ms = new MeeingService();
		String mName = request.getParameter("mName");
		int mId = Integer.parseInt(request.getParameter("mId"));
		int mNum = Integer.parseInt(request.getParameter("mNum"));
		int mrId = Integer.parseInt(request.getParameter("mrId"));
		String mRemark = request.getParameter("mRemark");
		String startTime = request.getParameter("startTime").replace("T", " ");
		String endTime = request.getParameter("endTime").replace("T", " ");
		Meeting m = new Meeting(mId, mName, mNum, mrId, 0, null, null, null, null, mRemark, null, 0);
		//修改会议信息
		try {
			ms.updateMeetingService(m, startTime, endTime);
			ArrayList<Integer> empIdList = new ArrayList<Integer>();
	    	Object empIdList3 = request.getParameter("empIdList");
	    	if (empIdList3 != null) {
	        	String[] empIdList2 = ((((String)empIdList3).replace("[", "")).replace("]", "")).split(",");
	        	for (int i = 0; i < empIdList2.length; i ++) {
	        		if (!empIdList2[i].equals("")) {
	        			empIdList.add(Integer.parseInt(empIdList2[i].trim()));
	        		}
	        	}
	    	}
	    	//如果该该员工没有参加过该会议 将会议id和员工id插入到第三表中
	    	for (int i = 0; i < empIdList.size(); i ++) {
	    		if (!ms.ifAlreadyJoinedMeetingService(mId, empIdList.get(i))){
	    			ms.addToMAEService(mId,  empIdList.get(i));
	    		}
	    	}
	    	//会议修改完毕后跳转到我的修改后的界面
	    	//先获取当下会议的信息 和参加会议的所有员工
	    	//通过会议id获得参加该会议的所有员工
			ArrayList<Employee> empList = ms.selectEmpByMIdService(mId);
			//获取该会议的原始信息
			Meeting meeting = ms.selectMeetingByIdService(mId);
			request.setAttribute("empList", empList);
			request.setAttribute("meeting", meeting);
			//获取所有的会议室
			MeetingRoomService mrs = new MeetingRoomService();
			ArrayList<MeetingRoom> al = mrs.selectAllMrsService();
			request.setAttribute("mrs", al);
	    	request.getRequestDispatcher("updateMeetingSuccess.jsp").forward(request, response);
	    	
	    		
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
