package com.icss.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.service.MeetingRoomService;
import com.icss.vo.MeetingRoom;
import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

/**
 * Servlet implementation class SelectAllDeptServlet
 */
@WebServlet("/RefreshRoomsServlet")
public class RefreshRoomsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RefreshRoomsServlet() {
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
		MeetingRoomService ms =new MeetingRoomService();
		String sTime = request.getParameter("starttime");
		String eTime = request.getParameter("endtime");
		ArrayList<MeetingRoom>	roomlist = new ArrayList<MeetingRoom>();
		if (sTime == null || "".equals(sTime) || eTime == null || "".equals(eTime)) {
			try {
				roomlist = ms.selectAllMrsService();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			Timestamp startime =Timestamp.valueOf(sTime);
			Timestamp endtime =Timestamp.valueOf(eTime);
			try {
				roomlist = ms.SelectAllMeeingRoomsByTimeService(startime, endtime);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			
			 
			/*for(MeetingRoom xyz : roomlist){
				System.out.println(xyz);
			}*/
			response.setContentType("text/xml;charset=utf-8");
			PrintWriter out = response.getWriter();
			response.setHeader("Cache-Control", "no-cache");
			out.println("<?xml version='1.0' encoding='" + "utf-8" + "'?>");	
			out.println("<rooms>");
			for(MeetingRoom m : roomlist){
				out.println("<option>");
				out.println("<value>" + m.getMrId()+ "</value>");
				out.println("<text>" + m.getMrName()+ "</text>");
				out.println("</option>");
			}
			out.println("</rooms>");
			out.close();					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
