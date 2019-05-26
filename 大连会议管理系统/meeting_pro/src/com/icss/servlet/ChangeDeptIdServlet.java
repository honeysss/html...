package com.icss.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.service.EmployeeService;
import com.icss.vo.Employee;

/**
 * Servlet implementation class ChangeDeptIdServlet
 */
@WebServlet("/ChangeDeptIdServlet")
public class ChangeDeptIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeDeptIdServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("utf-8");
//		DepartmentService ds = new DepartmentService();
//		MeetingRoomService mrs = new MeetingRoomService();
//		EmployeeService es = new EmployeeService();
//		String mName = request.getParameter("mName");
//		String mNum = request.getParameter("mNum");
//		String mRemark = request.getParameter("mRemark");
//		String startTime = request.getParameter("startTime").replace("T", " ");
//		String endTime = request.getParameter("endTime").replace("T", " ");
//		String oper = request.getParameter("oper");
//		int mrId = 0;
//		if (request.getParameter("mrId") != null && !"".equals(request.getParameter("mrId"))) {
//			mrId = Integer.parseInt(request.getParameter("mrId"));
//		}
//		int mId = 0;
//		if (request.getParameter("mId") != null && !"".equals(request.getParameter("mId"))) {
//			mId = Integer.parseInt(request.getParameter("mId"));
//		}
//		int deptId = Integer.parseInt(request.getParameter("deptId"));
//		String empIdList = request.getParameter("empIdList");
//		
//		//员工要通过部门id查找
//		try {
//			ArrayList<Department> depts = ds.selectAllDeptService();
//			ArrayList<MeetingRoom> mrs2 = mrs.selectAllMrsService();
//			ArrayList<Employee> emps = null;
//			if (deptId == 0) {
//				emps = es.selectAllUserService();
//			} else {
//				emps = es.selectUserByDeptIdService(deptId);
//			}
//			request.setAttribute("depts", depts);
//			request.setAttribute("mrs", mrs2);
//			request.setAttribute("emps", emps);
//			request.setAttribute("mName", mName);
//			request.setAttribute("mNum", mNum);
//			request.setAttribute("mrId", mrId);
//			request.setAttribute("mRemark", mRemark);
//			request.setAttribute("empIdList", empIdList);
//			request.setAttribute("startTime", startTime);
//			request.setAttribute("endTime", endTime);
//			request.setAttribute("deptId", deptId);
//			if ("update".equals(oper)) {
//				request.getRequestDispatcher("UpdateMyBookingServlet?mId=" + mId + "&startTime=" + startTime + "&endTime=" + endTime + "&mrId=" + mrId).forward(request, response);
//			} else {
//				//跳转到预定会议页面
//				request.getRequestDispatcher("bookMeeting.jsp").forward(request, response);
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		
		
		//ajax
		//通过部门id获取该部门的所有员工
		EmployeeService es = new EmployeeService();
		int deptId = Integer.parseInt(request.getParameter("deptId"));
		try {
			ArrayList<Employee> emps = es.selectUserByDeptIdService(deptId);
			
			//如果有员工的id和已添加的员工id相同 删除这个员工
			if (request.getParameter("empIdList") != null && !"".equals(request.getParameter("empIdList"))) {
				String[] empIdList = request.getParameter("empIdList").split(",");
				//转成int类型
				ArrayList<Integer> empIds = new ArrayList<Integer>();
				for (int i = 0; i < empIdList.length; i ++) {
					if (!"".equals(empIdList[i])) {
						empIds.add(Integer.parseInt(empIdList[i]));
					}
				}
				
				for (int i = 0; i < emps.size(); i ++) {
					if (empIds.contains(emps.get(i).getEmpId())) {
						emps.remove(emps.get(i));
						i--;
					}
				}
			}
			
			response.setContentType("text/xml; charset=utf-8");
			PrintWriter out = response.getWriter();
			response.setHeader("Cache-Control", "no-cache");
			out.println("<?xml version='1.0' encoding='"+"utf-8" + "'?>");
			
			out.println("<employee>");
			for (Employee emp: emps) {
				out.println("<option>");
				out.println("<value>" + emp.getEmpId() + "</value>");
				out.println("<text>" + emp.getEmpName() + "</text>");
				out.println("</option>");
			}
			out.println("</employee>");
			out.close();
			
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
