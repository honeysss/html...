package com.icss.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icss.service.CounterService;
import com.icss.service.EmployeeService;

/**
 * Servlet implementation class DeleteUserServlet
 */
@WebServlet("/DeleteUserFromSearchServlet")
public class DeleteUserFromSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUserFromSearchServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int empId = Integer.parseInt(request.getParameter("empId"));
		EmployeeService es = new EmployeeService();
		try {
			es.deleteEmpByIdService(empId);
			//ɾ��Ա����ʱ�򽫻����Ա�����е�Ա��ɾ��
			es.deleteMeetingAndEmployeeByEmpIdService(empId);
			//ͬʱ��counter���е�����ɾ��
			CounterService cs = new CounterService();
			cs.deleteCounterByIdService(empId);
			//ɾ����ʱ���ж��Ƿ�ɾ�������Լ����˺�
			HttpSession session = request.getSession();
			int oldEmpId = Integer.parseInt(session.getAttribute("empId").toString());
			if (empId == oldEmpId) {
				request.setAttribute("msg", "��ע�����˺ţ������µ�½");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("SearchEmployeesServlet").forward(request, response);
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
