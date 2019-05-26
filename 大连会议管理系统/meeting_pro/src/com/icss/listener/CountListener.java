package com.icss.listener;


import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.icss.service.CounterService;

public class CountListener implements ServletContextListener {

	CounterService cs = new CounterService();
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		ServletContext sc = arg0.getServletContext();
		Object count = sc.getAttribute("count");
		Object empId = sc.getAttribute("empId");
		try {
			cs.setCounterByIdService(Integer.parseInt(empId.toString()), Integer.parseInt(count.toString()));
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ServletContext sc = arg0.getServletContext();
		int empId = Integer.parseInt(sc.getAttribute("empId").toString());
		try {
			int count = cs.ifVisitedByIdService(empId).getCounter();
			sc.setAttribute("count", count);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
