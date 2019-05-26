package com.icss.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icss.service.EmployeeService;
import com.icss.vo.Employee;


/**
 * Servlet implementation class UpdateEmpServlet
 */
@WebServlet("/UpdateEmpFromSearchServlet")
public class UpdateEmpFromSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateEmpFromSearchServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		EmployeeService es = new EmployeeService();
		
		int empId = Integer.parseInt(request.getParameter("empId"));
		String empName = request.getParameter("empName");
		String empTel = request.getParameter("empTel");
		String empEmail = request.getParameter("empEmail");
		int empDeptId = Integer.parseInt(request.getParameter("empDeptId"));
		String username = request.getParameter("username");
		String userPwd = request.getParameter("userPwd");
		String oldUsername = request.getParameter("oldUsername");
		String odlPwd = request.getParameter("odlPwd");
		int userStatus = Integer.parseInt(request.getParameter("userStatus"));
		int userRole = Integer.parseInt(request.getParameter("userRole"));
		Employee emp = new Employee(empId, empName, empTel, empEmail, empDeptId, username, userPwd, userStatus, userRole);
		try {
			es.updateEmpService(emp);
			boolean flag = false;
			//获取角色 如果该员工的id是登录的人 并且把自己的权限修改为普通用户 则提示您修改了自己的权限 请重新登录
			String msg = "";
			HttpSession session = request.getSession();
			int oldEmpId = Integer.parseInt(session.getAttribute("empId").toString());
			if (oldEmpId == empId) {
				if (userRole == 0) {
					flag = true;
					msg = "您修改了权限，请重新登录";
				} else if (userStatus != 1) {
					flag = true;
					msg = "您修改了账号状态，请重新登录";
				} else if (!username.equals(oldUsername)) {
					flag = true;
					msg = "您修改了用户名，请重新登录";
				} else if (!userPwd.equals(odlPwd)) {
					flag = true;
					msg = "您修改了密码，请重新登录";
				}
				
				session.invalidate();
				Cookie[] cks = request.getCookies();
				for (Cookie ck: cks) {
					ck.setValue(null);
					ck.setMaxAge(0);
					response.addCookie(ck);
				}
				
			} 
			if (flag) {
				request.setAttribute("msg", msg);
				request.setAttribute("username", username);
				request.getRequestDispatcher("login.jsp").forward(request, response);
			} else {
				String newPageNum = request.getParameter("newPageNum");
				String newUsername = request.getParameter("newUsername");
				String newEmpName = request.getParameter("newEmpName");
				String newEmpStatus = request.getParameter("newEmpStatus");
				request.getRequestDispatcher("SearchEmployeesServlet?pageNum=" + newPageNum
						+ "&username=" + newUsername + "&empName=" + newEmpName + 
						"&empStatus=" + newEmpStatus).forward(request, response);
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
