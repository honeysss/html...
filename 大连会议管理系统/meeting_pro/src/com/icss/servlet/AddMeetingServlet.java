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

import com.icss.service.EmployeeService;
import com.icss.service.MeeingService;
import com.icss.vo.Meeting;

/**
 * Servlet implementation class AddMeetingServlet
 */
@WebServlet("/AddMeetingServlet")
public class AddMeetingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMeetingServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String mName = request.getParameter("mName");
		int mNum = Integer.parseInt(request.getParameter("mNum"));
		int mrId = 0;
		if (request.getParameter("mrId") != null && !"".equals(request.getParameter("mrId"))) {
			mrId = Integer.parseInt(request.getParameter("mrId"));
		}
		
		String mRemark = request.getParameter("mRemark");
		String startTime = request.getParameter("startTime").replace("T", " ");
		String endTime = request.getParameter("endTime").replace("T", " ");
		//获得预定时间
		Date date = new java.util.Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String reTime = sdf.format(date);
		
		HttpSession session = request.getSession();
		int reEmpId = Integer.parseInt(session.getAttribute("empId").toString());
		
		Meeting m = new Meeting(0, mName, mNum, mrId, reEmpId, null, null, null, null, mRemark, null, 0);
		
		//先增加会议
		MeeingService ms = new MeeingService();
		try {
			ms.addMeetingService(m, reTime, startTime, endTime);
			//获得最新的会议id
			int newmId = ms.newMeetingIdService();
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
        	//将会议id和员工id插入到第三表中
        	for (int i = 0; i < empIdList.size(); i ++) {
        		ms.addToMAEService(newmId, empIdList.get(i));
        	}
        	
        	//插入之后跳转到我的预定界面
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
