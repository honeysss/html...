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
		//ͨ������id�޸Ļ���
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
		//�޸Ļ�����Ϣ
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
	    	//����ø�Ա��û�вμӹ��û��� ������id��Ա��id���뵽��������
	    	for (int i = 0; i < empIdList.size(); i ++) {
	    		if (!ms.ifAlreadyJoinedMeetingService(mId, empIdList.get(i))){
	    			ms.addToMAEService(mId,  empIdList.get(i));
	    		}
	    	}
	    	//�����޸���Ϻ���ת���ҵ��޸ĺ�Ľ���
	    	//�Ȼ�ȡ���»������Ϣ �Ͳμӻ��������Ա��
	    	//ͨ������id��òμӸû��������Ա��
			ArrayList<Employee> empList = ms.selectEmpByMIdService(mId);
			//��ȡ�û����ԭʼ��Ϣ
			Meeting meeting = ms.selectMeetingByIdService(mId);
			request.setAttribute("empList", empList);
			request.setAttribute("meeting", meeting);
			//��ȡ���еĻ�����
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
