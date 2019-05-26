package com.icss.service;

import java.sql.SQLException;

import com.icss.dao.CounterDao;
import com.icss.vo.Counter;

public class CounterService {
	CounterDao cd = new CounterDao();
	//通过用户id查找该用户是否访问过该网站
	public Counter ifVisitedByIdService(int empId) throws SQLException {
		return cd.ifVisitedByIdDAO(empId);
	}
	
	//如果是第一次登陆 将id和次数添加到counter中
	public void addCounterByIdAndCounterService(int empId, int counter) throws SQLException {
		cd.addCounterByIdAndCounterDAO(empId, counter);
	}
	
	//通过员工id设置访问数量
	public void setCounterByIdService(int empId, int counter) throws SQLException {
		cd.setCounterByIdDAO(empId, counter);
	}
	
	//通过用户id删除counter中的数据
	public void deleteCounterByIdService(int empId) throws SQLException {
		cd.deleteCounterByIdDAO(empId);
	}
	
}
